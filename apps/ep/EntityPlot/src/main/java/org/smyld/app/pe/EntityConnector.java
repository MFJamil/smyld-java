package org.smyld.app.pe;

import static org.smyld.app.pe.PlotConstants.ALIGNMENT_CENTER;
import static org.smyld.app.pe.PlotConstants.ALIGNMENT_MIDDLE;
import static org.smyld.app.pe.PlotConstants.POSITION_BUT;
import static org.smyld.app.pe.PlotConstants.POSITION_MID_LEFT;
import static org.smyld.app.pe.PlotConstants.POSITION_MID_RIGHT;
import static org.smyld.app.pe.PlotConstants.POSITION_TOP;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
public class EntityConnector {
	protected EntityPlotter      from;
	protected EntityPlotter      to;
	protected Point              fromPoint;
	protected Point              toPoint;
	protected int                frmPos  = EntityPlotter.POSITION_MID_RIGHT;
	protected int                toPos   = EntityPlotter.POSITION_MID_LEFT;
	protected TextPlotter        comment;
	protected Point              dragPoint;
	protected Rectangle          midRect = new Rectangle(0,0,4,4);
	protected String             id;
	protected int                conPointSize;
	protected int                borderWidth = 2,commentDistance=10;
	protected int mx,my;
	protected Point              hittedPoint;
	protected boolean            inDrag;
	protected boolean            fromDisconnected;
	protected boolean            deactivated;
	protected boolean            showArrow = true;
	//Color            conColor = Color.BLUE;
	protected Color              conColor = new Color(83,105,174);
	protected ConnectionCategory category = ConnectionCategory.Organic; // This is the default category value
	protected ConnectionType     type     = ConnectionType.OneToOne; // This is the default type value
	
	
	public EntityConnector(EntityPlotter from,EntityPlotter to,int conSize,Color conColor){
		this(from,to,conSize);
		this.conColor = conColor;
		init();
	}
	public EntityConnector(EntityPlotter from,EntityPlotter to,int conSize,Color conColor,String commentText){
		this(from,to,conSize);
		this.conColor = conColor;
		addComment(commentText);
		init();
	}
	

	public EntityConnector(EntityPlotter from,EntityPlotter to,int conSize){
		this.from         = from;
		this.to           = to;
		this.conPointSize = conSize;
		id = from.getId() + to.getId();
		init();
	}
	private void init(){
		
	}
	protected void addComment(String commentText){
		if ((commentText!=null)&&(!commentText.isEmpty())){
			comment = new TextPlotter(commentText);
			comment.setAutoExpand(true);
			comment.setHorizontalAlignment(ALIGNMENT_CENTER);
			comment.setVerticalAlignment(ALIGNMENT_MIDDLE);
		}
	}
	
	
	protected void detectEntityConnectionPoints(){
		Rectangle2D frmRect = from.getBounds();
		Rectangle2D toRect  = to.getBounds();
		frmPos  = POSITION_MID_RIGHT;
		toPos   = POSITION_MID_LEFT;
		
		if (onTop(frmRect, toRect)){
			frmPos  = POSITION_BUT;
			toPos   = POSITION_TOP;
		}else if(onBut(frmRect, toRect)){
			frmPos  = POSITION_TOP;
			toPos   = POSITION_BUT;

		}else if(onRight(frmRect, toRect)){
			frmPos  = POSITION_MID_LEFT;
			toPos   = POSITION_MID_RIGHT;
		}
		Point       fromP = from.getConnectionPoint(frmPos,true);
		Point       toP   = to.getConnectionPoint(toPos,true);
		setConnectorPoints(fromP, toP);
		
	}
	
	public void drawLine(Graphics2D g){
		Composite ocomp = null;
		Color prevCol = g.getColor();
		g.setColor(getColor());
		g.setStroke(new BasicStroke(borderWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
			
		detectEntityConnectionPoints();
		// Drawing point in the middle
		mx = (fromPoint.x - toPoint.x)/2;
		my = (fromPoint.y - toPoint.y)/2;
		
		if (isInDrag()){
			if (fromDisconnected){
				drawConnector(g,getDragPoint(),toPoint);
				//to.paintConnectorOn(toPos, g);
			}else{
				drawConnector(g,fromPoint,getDragPoint());
				//from.paintConnectorOn(frmPos, g);
			}
		}else{

			drawConnector(g,fromPoint,toPoint);
			drawMidRect(g);
			//from.paintConnectorOn(frmPos, g);
			//to.paintConnectorOn(toPos, g);
		}
		if (comment!=null){
			int connectX=fromPoint.x,connectY=fromPoint.y;
			comment.calibrate(g);
			switch(frmPos){
				case POSITION_MID_RIGHT:
					comment.setX(fromPoint.x + commentDistance);
					connectX += commentDistance/2;
					comment.setY(fromPoint.y - (int)comment.getHeight() - commentDistance);
					break;
				case POSITION_MID_LEFT:
					comment.setX(fromPoint.x - comment.getWidth() -commentDistance);
					connectX -= commentDistance/2;
					comment.setY(fromPoint.y - (int)comment.getHeight() - commentDistance);
					break;
				case POSITION_TOP:
					comment.setX(fromPoint.x + commentDistance);
					connectY -= commentDistance/2;
					comment.setY(fromPoint.y - (int)comment.getHeight() - commentDistance);
					break;
				case POSITION_BUT:
					comment.setX(fromPoint.x + commentDistance);
					connectY += commentDistance/2;
					comment.setY(fromPoint.y + commentDistance);
					break;

			
			}
			comment.paintContents(g,connectX,connectY);
		}
		g.setColor(prevCol);
	}
	protected void drawMidRect(Graphics2D g){
		midRect.setLocation(fromPoint.x - mx-2, fromPoint.y - my-2);
		g.fillRoundRect(midRect.x,midRect.y , midRect.width, midRect.height,4,4);
	}
	protected void drawConnector(Graphics2D g, Point from,Point to){
		Shape connector = null; 
		switch(category){
			case Orthogonal:
				if (type==ConnectionType.OneToOne)
					connector = drawOrthogonalLine(g,from.x,from.y, to.x,to.y);
				else 
					connector = drawManyOrthogonalLine(g,from.x,from.y, to.x,to.y);
				break;
			default:
				connector = drawOrganicLine(g,from.x,from.y, to.x,to.y);
		}
		g.draw(connector);
		
	}

	protected Shape drawOrthogonalLine(Graphics2D g,float p1x,float p1y,float p2x,float p2y){
		int arr = conPointSize;
		GeneralPath path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
		path.moveTo(p1x, p1y);
		int    ysign        = p1y>p2y?1:-1;
		int    xsign        = p1x>p2x?1:-1;

		switch(frmPos){
			case POSITION_MID_RIGHT:case POSITION_MID_LEFT:
				if (showArrow){
					int arrSign = frmPos==POSITION_MID_LEFT?1:-1;
					path.lineTo(p2x+(arrSign * arr), p2y);
					drawArrow(g,p2x+(arrSign * arr),p2y, p2x, p2y, 0, ysign, xsign);
				}else{
					path.lineTo(p2x, p2y);
				}
				
				break;
		
			case POSITION_TOP:case POSITION_BUT:
				if (p1x!=p2x){
					float midY = (p2y-p1y)/2;
					path.lineTo(p1x , p1y + midY);
					path.lineTo(p2x , p1y + midY);
				}
				if (showArrow){
					int arrSign = frmPos==POSITION_BUT?1:-1;
					path.lineTo(p2x, p2y+(arrSign*arr));
					drawArrow(g,p2x,p2y+(arrSign*arr), p2x, p2y, 0, ysign, xsign);
				}else{
					path.lineTo(p2x, p2y);
				}
				
				break;

		}
		
		return path;
	}

	protected Shape drawManyOrthogonalLine(Graphics2D g,float p1x,float p1y,float p2x,float p2y){
		int arr = conPointSize;
		GeneralPath path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
		path.moveTo(p1x, p1y);
		int    ysign        = p1y>p2y?1:-1;
		int    xsign        = p1x>p2x?1:-1;

		switch(frmPos){
			case POSITION_MID_RIGHT:case POSITION_MID_LEFT:
				if (p1y!=p2y){
					float midX = (p2x-p1x)/2;
					path.lineTo(p1x+midX, p1y);
					path.lineTo(p1x+midX, p2y);
				}
				
				if (showArrow){
					int arrSign = frmPos==POSITION_MID_LEFT?1:-1;
					path.lineTo(p2x+(arrSign * arr), p2y);
					drawArrow(g,p2x+(arrSign * arr),p2y, p2x, p2y, 0, ysign, xsign);
				}else{
					path.lineTo(p2x, p2y);
				}
				
				break;
		
			case POSITION_TOP:case POSITION_BUT:
				if (p1x!=p2x){
					float midY = (p2y-p1y)/2;
					path.lineTo(p1x , p1y + midY);
					path.lineTo(p2x , p1y + midY);
				}
				if (showArrow){
					int arrSign = frmPos==POSITION_BUT?1:-1;
					path.lineTo(p2x, p2y+(arrSign*arr));
					drawArrow(g,p2x,p2y+(arrSign*arr), p2x, p2y, 0, ysign, xsign);
				}else{
					path.lineTo(p2x, p2y);
				}
				
				break;

		}
		
		return path;
	}
	
	
	protected Shape drawOrganicLine(Graphics2D g,float p1x,float p1y,float p2x,float p2y){
		int arr = conPointSize;
		GeneralPath path = new GeneralPath();
		path.moveTo(p1x, p1y);
		
		int    ysign        = p1y>p2y?1:-1;
		int    xsign        = p1x>p2x?1:-1;

		
		if (showArrow){
			// Vertical line
			if (p1x==p2x){
				int sign = (p2y<p1y?+1:-1);
				path.lineTo(p2x,p2y + sign * arr);
				drawArrow(g,p2x,p2y + sign * arr, p2x, p2y, 0, ysign, xsign);
			// Horizontal
			}else if (p1y==p2y){
				int sign = (p2x<p1x?+1:-1);
				path.lineTo(p2x + sign * arr,p2y );
				drawArrow(g,p2x+ sign * arr,p2y, p2x, p2y, 0, ysign, xsign);
			// free
			}else{
				double dy           = Math.abs(p2y-p1y);
				double dx           = Math.abs(p1x-p2x);
				double lineAngle    = Math.atan(dy/dx);
				double arBasePointX = arr*Math.cos(lineAngle);
				double arBasePointY = arr*Math.sin(lineAngle);
				double arrStartX    = p2x + (xsign*arBasePointX);
				double arrStartY    = p2y + (ysign*arBasePointY);
				path.lineTo(arrStartX,arrStartY);
				drawArrow(g,arrStartX,arrStartY, p2x, p2y, lineAngle, ysign, xsign);
			}
		}else{
			path.lineTo(p2x,p2y);
		}
		return path;
	}

	protected void drawArrow(Graphics2D g,double p1x,double p1y,double p2x,double p2y,double lineAngle ,int ysign,int xsign){
		GeneralPath path = new GeneralPath();
		int arr = conPointSize;
		path.moveTo(p1x, p1y);
		// Vertical line
		if (p1x==p2x){
			path.lineTo(p2x,p2y + ysign * arr);
			path.lineTo(p2x+arr/2,p2y + ysign * arr);
			path.lineTo(p2x,p2y);
			path.lineTo(p2x-arr/2,p2y + ysign * arr);
			path.lineTo(p2x,p2y + ysign * arr);
		// Horizontal
		}else if (p1y==p2y){
			path.lineTo(p2x + xsign * arr,p2y );
			path.lineTo(p2x + xsign * arr,p2y + arr/2);
			path.lineTo(p2x,p2y);
			path.lineTo(p2x + xsign * arr,p2y - arr/2);
			path.lineTo(p2x + xsign * arr,p2y );
			
		// free
		}else{
			double arTopPointX  = (arr/2)*Math.sin(lineAngle);
			double arTopPointY  = (arr/2)*Math.cos(lineAngle);
			path.lineTo(p1x,p1y);
			path.lineTo(p1x  + (-1*xsign*arTopPointX),p1y + (ysign*arTopPointY));
			path.lineTo(p2x,p2y);
			path.lineTo(p1x + (xsign*arTopPointX),p1y + (-1*ysign*arTopPointY));
			path.lineTo(p1x,p1y);
		}
		path.closePath();
		g.fill(path);
	}

	public boolean connects(EntityPlotter from,EntityPlotter to){
		return ((this.from.getId()==from.getId())&&(this.to.getId()==to.getId()));
	}
	public Color getColor(){return this.conColor;}
	public String getId(){return id;}
	public void setConnectorPoints(Point fromP,Point toP){
		this.fromPoint = fromP;
		this.toPoint   = toP;
	}
	public boolean isDeactivated(){
		return deactivated;
	}
	public void setDeactivated(boolean value){
		deactivated = value;
	}
	
	public boolean hitPoints(int x,int y){
		/*
		if (doHitPoint(x, y, fromPoint)){
			fromDisconnected = true;
			return true;
		}else if (doHitPoint(x, y, toPoint)){
			fromDisconnected = false;
			return true;
			
		}
		return false;
		*/
		return (((x >=midRect.x)&&(x <=midRect.x+conPointSize))&&((y >=midRect.y)&&(y <=midRect.y+conPointSize)));
		
	}
	public void setInDrag(boolean inDrag){
		this.inDrag = inDrag;
	}
	public boolean isInDrag(){
		return inDrag;
	}
	
	public void setDragPoint(Point dragPoint){
		this.dragPoint = dragPoint;
	}
	public Point getDragPoint(){return dragPoint;}
	private boolean doHitPoint(int x,int y,Point t){
		/*
		int   lDx = fromPoint.x - toPoint.x;
		int   lDy = fromPoint.y - toPoint.y;
		float lRat = lDx/lDy;
		int   pDx = x - toPoint.x;
		int   pDy = y - toPoint.y;
		float pRat = pDx/pDy;
		return (lRat==pRat);
		*/
		if (((x >=t.x)&&(x <=t.x+conPointSize))&&((y >=t.y)&&(y <=t.y+conPointSize))){
			hittedPoint = t;
			return true;
		}
		return false;
		
	}
	protected boolean onRight(Rectangle2D from,Rectangle2D to){
		return (from.getX()>(to.getX()+to.getWidth()));
	}
	protected boolean onLeft(Rectangle2D from,Rectangle2D to){
		return ((from.getX()+from.getWidth())<(to.getX()));
	}
	protected boolean onTop(Rectangle2D from,Rectangle2D to){
		return ((from.getY()+from.getHeight())<(to.getY()));
	}
	protected boolean onBut(Rectangle2D from,Rectangle2D to){
		return ((from.getY())>(to.getY()+to.getHeight()));
	}
	protected boolean onMidRight(Rectangle2D from,Rectangle2D to){
		return (from.getX()+from.getWidth()/2>(to.getX()+to.getWidth()));
	}
	protected boolean onMidLeft(Rectangle2D from,Rectangle2D to){
		return ((from.getX()+from.getWidth()/2)<(to.getX()));
	}
	protected boolean onMidTop(Rectangle2D from,Rectangle2D to){
		return ((from.getY()+from.getHeight()/2)<(to.getY()));
	}
	protected boolean onMidBut(Rectangle2D from,Rectangle2D to){
		return ((from.getY())>(to.getY()+to.getHeight()/2));
	}

	public boolean isShowArrow() {
		return showArrow;
	}

	public void setShowArrow(boolean showArrow) {
		this.showArrow = showArrow;
	}

	public int getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}
	
	
	public enum ConnectionCategory{
		Orthogonal,
		Organic;
		
	}
	
	public enum ConnectionType{
		ManyToOne,
		ManyToMany,
		OneToMany,
		OneToOne;
		
	}

	public EntityPlotter getFrom() {
		return from;
	}
	public void setFrom(EntityPlotter from) {
		this.from = from;
	}
	public EntityPlotter getTo() {
		return to;
	}
	public void setTo(EntityPlotter to) {
		this.to = to;
	}
	public String getCommentText() {
		if (comment!=null)
			return comment.getText();
		return null;
	}
	public void setCommentText(String commentText) {
		if (this.comment!=null) comment.setText(commentText);
		else addComment(commentText);
			
		
	}
	public Color getConColor() {
		return conColor;
	}
	public void setConColor(Color conColor) {
		this.conColor = conColor;
	}
	
	

}
