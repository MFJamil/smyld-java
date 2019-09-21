package org.smyld.app.entityplot;

import static org.smyld.app.entityplot.PlotConstants.POSITION_BUT;
import static org.smyld.app.entityplot.PlotConstants.POSITION_MID_LEFT;
import static org.smyld.app.entityplot.PlotConstants.POSITION_MID_RIGHT;
import static org.smyld.app.entityplot.PlotConstants.POSITION_TOP;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;


public class OrthogonalEntityConnector extends EntityConnector {

	public OrthogonalEntityConnector(EntityPlotter from, EntityPlotter to,
			int conSize, Color conColor, String commentText) {
		super(from, to, conSize, conColor, commentText);
		init();
	}

	public OrthogonalEntityConnector(EntityPlotter from, EntityPlotter to,
			int conSize, Color conColor) {
		super(from, to, conSize, conColor);
		init();
	}

	public OrthogonalEntityConnector(EntityPlotter from, EntityPlotter to,
			int conSize) {
		super(from, to, conSize);
		init();
	}
	
	private void init(){
		category = ConnectionCategory.Orthogonal;
		//showArrow = false;
	}
	protected void detectEntityConnectionPoints(){
		Rectangle2D frmRect = from.getBounds();
		Rectangle2D toRect  = to.getBounds();
		frmPos  = POSITION_MID_RIGHT;
		toPos   = POSITION_MID_LEFT;
		
		if (onMidTop(frmRect, toRect)){
			frmPos  = POSITION_BUT;
			toPos   = POSITION_TOP;
			if(onMidRight(frmRect, toRect)){
				frmPos  = POSITION_MID_LEFT;
			}else if (onMidLeft(frmRect, toRect)){
				frmPos  = POSITION_MID_RIGHT;
			}
		}else if(onMidBut(frmRect, toRect)){
			frmPos  = POSITION_TOP;
			toPos   = POSITION_BUT;
			if(onMidRight(frmRect, toRect)){
				toPos  = POSITION_MID_RIGHT;
			}else if (onMidLeft(frmRect, toRect)){
				toPos  = POSITION_MID_LEFT;
			}

		}else if(onMidRight(frmRect, toRect)){
			frmPos  = POSITION_MID_LEFT;
			toPos   = POSITION_MID_RIGHT;
		}
		Point       fromP = from.getConnectionPoint(frmPos,true);
		Point       toP   = to.getConnectionPoint(toPos,true);
		setConnectorPoints(fromP, toP);
		
	}
	protected Shape drawOrthogonalLine(Graphics2D g,float p1x,float p1y,float p2x,float p2y){
		int arr = conPointSize;
		GeneralPath path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
		path.moveTo(p1x, p1y);
		int    ysign        = p1y>p2y?1:-1;
		int    xsign        = p1x>p2x?1:-1;
		float  bx           = p2x;
		float  by           = p2y;
		int    arrSign      = 1;
		switch(frmPos){
			case POSITION_MID_RIGHT:case POSITION_MID_LEFT:
				switch(toPos){
					case POSITION_MID_RIGHT:case POSITION_MID_LEFT:
						if (p1y!=p2y){
							float midX = (p2x-p1x)/2;
							path.lineTo(p1x+midX, p1y);
							path.lineTo(p1x+midX, p2y);
						}
						bx += arrSign * (toPos==POSITION_MID_RIGHT?1:-1);
						break;
					case POSITION_TOP:case POSITION_BUT:
						if (Math.abs(mx)<Math.abs(my))mx = fromPoint.x - toPoint.x;
						else  my = 0;
						path.lineTo(p2x, p1y);
						by+=arrSign*(toPos==POSITION_BUT?1:-1);
						break;
				}
				break;
			case POSITION_TOP:case POSITION_BUT:
				switch(toPos){
					case POSITION_MID_RIGHT:case POSITION_MID_LEFT:
						if (Math.abs(mx)<Math.abs(my)) mx = 0;
						else my = fromPoint.y - toPoint.y;
						path.lineTo(p1x, p2y);
						bx+=arrSign*(toPos==POSITION_MID_RIGHT?1:-1);
						break;
					case POSITION_TOP:case POSITION_BUT:
						if (p1x!=p2x){
							float midY = (p2y-p1y)/2;
							path.lineTo(p1x, p1y+midY);
							path.lineTo(p2x, p1y+midY);
						}
						by +=arrSign*(frmPos==POSITION_BUT?1:-1);
						break;
						
				}
		
		}
		if (showArrow){
			path.lineTo(bx, by);
			drawArrow(g,bx,by, p2x, p2y, 0, ysign, xsign);
		}else{
			path.lineTo(p2x, p2y);
		}
		return path;
	}
	protected void drawMidRect(Graphics2D g){
		midRect.setLocation(fromPoint.x - mx-2, fromPoint.y - my-2);
		g.fillRect(midRect.x,midRect.y , midRect.width, midRect.height);
	}
	


}
