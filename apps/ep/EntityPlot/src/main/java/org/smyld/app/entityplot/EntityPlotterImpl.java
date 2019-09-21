package org.smyld.app.entityplot;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Vector;

import org.smyld.app.entityplot.util.GUIUtil;


public class EntityPlotterImpl implements EntityPlotter {
	
	protected Object  userObject;
	protected int textLeftMargin=10,textButtomMargin=10,textRightMargin=10,titleTopMargin=4,titleSideMargin=4,titleRightMargin=22	,textTopMargin=4,textLineMargin=2,borderWidth=1,connectorSize=8,selectionWidth=6;
	protected int dndX,dndY,x,y,width,height; // Drag point information
	protected int type,contentsHorizontalAlignment= ALIGNMENT_LEFT,contentsVerticalAlignment= ALIGNMENT_TOP,titleAlignment= ALIGNMENT_LEFT,cornerCurve=5;
	protected double totalContentHeight;
	protected float     bgImageTransperancy    = .4f,entityTransperancy=.4f,origImageTransperancy;
	protected int       shLen                  = 4;
	protected Dimension ts;
	protected Rectangle bounds                 = new Rectangle(x,y,width,height);
	protected Font      titleFont              = new Font("Newtimes Roman",Font.BOLD,12); // Would be set as parameter in the future
	protected Font      contentsFont           = new Font("Newtimes Roman",Font.BOLD,10); // Would be set as parameter in the future
	protected Color     connectorColor         = Color.BLUE,contentBackground,titleBackground,borderColor,titleFontColor = Color.WHITE,contentFontColor = Color.WHITE,highlightColor=new Color(189,136,136);
	protected Color     connectStartBackground = new Color(59,123,219),selectedBackground=new Color(190,100,100),titleDarkColor=new Color(12,12,136),titleLightColor=new Color(138,154,239);
	protected String    title                  = "Title";
	protected String id,contents;
	protected boolean autoExpand=true,draggable=true,doHighlight=false,connectFrom=true,connectTo=true,visible=true;
	protected boolean selected,doShowConnecting=false,showTitle=true,showBorder=true,setTransperant=false;
	protected boolean bufferActivated = true,refreshImgBuffer = false,changeImgSize=false,dropShadow=false,dropReflection=false;
	protected PlotShape shape;
	protected TextPlotter textPlot = new TextPlotter();
	protected Image         backgroundImage;
	protected BufferedImage normalState;
	protected HashMap<String,BufferedImage> imgBuffer         = new HashMap<String, BufferedImage>(); 
	protected HashMap<Integer,Integer>      connectionPoints  = new HashMap<Integer, Integer>();
	protected HashMap<String,EntityPlotter> connectedEntities = new HashMap<String, EntityPlotter>();	
	protected Vector<EntityConnector>       connections       = new Vector<EntityConnector>();
	protected Shape bodyShape;
	int imgSeq = 0;
	
	
	
	// Constructors
	public EntityPlotterImpl(int x,int y,int width,int height){
		this.x=x;this.y=y;this.width=width;this.height=height;
		bounds.setBounds(x, y, width, height);
	}

	public EntityPlotterImpl(int x,int y,int width,int height,PlotShape shape,Color contentBGColor){
		this(x,y,width,height);
		this.shape = shape;
		this.contentBackground = contentBGColor;
	}
	public EntityPlotterImpl(int x,int y,int width,int height,PlotShape shape,Color contentBGColor,Color borderColor){
		this(x,y,width,height,shape,contentBGColor);
		this.borderColor = borderColor;
		
		
	}
	public EntityPlotterImpl(int x,int y,int width,int height,PlotShape shape,Color contentBGColor,Color borderColor,String title){
		this(x,y,width,height,shape,contentBGColor,borderColor);
		this.title =title;
		
	}
	
	
	public void highlight(boolean highlight){
		doHighlight = highlight;
	}
	public void setConnectingState(boolean connecting){
		doShowConnecting=connecting;
		
	}
	
	

	public void setPosition(Point p){
		x=p.x;
		y=p.y;
		bounds.setLocation(x,y);
	}
	public void setRelativePosition(Point p){
		x = p.x-dndX;
		y = p.y-dndY;
		bounds.setLocation(x,y);
	}
	public void startDragForPosition(Point p){
		dndX = p.x-x;
		dndY = p.y-y;
	}
	public boolean hitPoint(Point p){
		return  (((p.x >=x)&&(p.x <=x+width))&&((p.y >=y)&&(p.y <=y+height)));
	}
	
	public boolean hitConnector(Point p){
		return  (((p.x >=x)&&(p.x <=x+width))&&((p.y >=y)&&(p.y <=y+height)));
	}
	
	protected void setContentBackgroundState(Graphics2D g){
		if (doShowConnecting)
			//contentFontColor = 
			g.setColor(connectStartBackground);
		else if (doHighlight)
			g.setColor(highlightColor);
		else
			g.setColor(contentBackground);
		
	}
	protected void paintBody(Graphics2D g) {
		if (contentBackground==null) return;
		setContentBackgroundState(g);
		switch(shape){
			case Rectangle:
				bodyShape = new Rectangle2D.Float(x, y, width, height);
				//g.fillRect(x, y, width, height);
				g.fill(bodyShape);
				break;
	
			case RoundedRectangle:
				bodyShape = new RoundRectangle2D.Float(x, y, width, height,cornerCurve,cornerCurve);
				//g.fillRoundRect(x, y, width, height,cornerCurve,cornerCurve);
				g.fill(bodyShape);
				break;
			case Circle:
				bodyShape = new Ellipse2D.Float(x, y, width, height);
				//g.fillArc(x, y, width, width, 0, 360);
				g.fill(bodyShape);
				break;
			case Oval:
				g.fillOval(x, y, width, height);
				break;
		}
	}
	public void resetConnectionPoints(){
		for(Integer curPos:connectionPoints.keySet())
			connectionPoints.put(curPos,0);
	}
	protected void paintBorder(Graphics2D g) {
		g.setStroke(new BasicStroke(borderWidth));
		g.setColor(borderColor);
		switch(shape){
			case Rectangle:
				//g.drawRect(x+borderWidth/2, y+borderWidth/2, width-borderWidth, height-borderWidth);
				g.drawRect(x, y, width, height);
				break;
			case RoundedRectangle:
				//g.drawRoundRect(x+borderWidth/2, y+borderWidth/2, width-borderWidth, height-borderWidth,cornerCurve,cornerCurve);
				g.drawRoundRect(x, y, width, height,cornerCurve,cornerCurve);
				break;
			case Circle:
				g.drawArc(x, y, width, width, 0, 360);
				break;
			case Oval:
				g.drawOval(x, y, width, height);
				break;
		}
	}	
	protected void fillBufferedImage(BufferedImage bim){
		bim.flush();
		Graphics2D g = bim.createGraphics();

		AffineTransform tx = new AffineTransform();
		tx.translate(-x, -y);
		/* these steps are needed to eliminate any previous drawing in case we are using the same image again
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, bim.getWidth(), bim.getHeight());
		g.setColor(new Color(0,0,0,0));
		g.fillRect(0, 0, bim.getWidth(), bim.getHeight());
		*/
		g.setTransform(tx);
		g.setColor(Color.WHITE);
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING      , RenderingHints.VALUE_ANTIALIAS_ON); 
		g.setRenderingHint(RenderingHints.KEY_RENDERING         , RenderingHints.VALUE_RENDER_QUALITY); 
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING , RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION     , RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		//g.fillRect(0, 0, bim.getWidth(), bim.getHeight());
		if (dropReflection){
			if (dropShadow){
				dropShadow = false;
				paintEntity(g);
				addReflection(g,bim,height);
				dropShadow = true;
				paintEntity(g);
			}else{
				paintEntity(g);
				addReflection(g,bim,height);
			}
		}else{
			paintEntity(g);
		}
		g.dispose();
		
		
	}
	private void addReflection(Graphics2D g,BufferedImage image,int imageHeight){
		int   imageWidth  = width;
        int   gap         = 0;
        float opacity     = 0.3f;
        float fadeHeight  = 0.8f;
        BufferedImage reflection = new BufferedImage( imageWidth+1, imageHeight, BufferedImage.TYPE_INT_ARGB );
		Graphics2D rg = reflection.createGraphics();
		rg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		rg.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY); 
		rg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		rg.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		rg.translate(0, imageHeight+5);
		rg.scale(1,-1);
		rg.drawRenderedImage( image, null );
		rg.setComposite( AlphaComposite.getInstance( AlphaComposite.DST_IN ) );
        rg.setPaint(new GradientPaint(0, imageHeight*fadeHeight, new Color( 0.0f, 0.0f, 0.0f, 0.0f ),0, imageHeight, new Color( 0.0f, 0.0f, 0.0f, opacity )));
        rg.fillRect( 0, 0, imageWidth+1, imageHeight+5 );
        rg.dispose();
        g.drawImage(reflection, x, y + imageHeight+gap, null );
	}
	
	public void refreshBuffer(){
		refreshImgBuffer = true;
	}
	private void doRefreshBuffer(){
		
		//if (internalPropertyChange) return;
		BufferedImage normal      = null;
		BufferedImage highlight   = null;
		BufferedImage connecting  = null;
		BufferedImage transperant = null;
//		if ((imgBuffer.size()==0)||(changeImgSize)){
			int imgHeight = height+borderWidth;
			int imgWidth  = width+borderWidth;
			if (dropShadow){
				imgHeight += shLen;
				imgWidth  += shLen;
			}
			if (dropReflection)
				imgHeight += height;
			normal      = new BufferedImage(imgWidth,imgHeight,BufferedImage.TYPE_INT_ARGB);
			highlight   = new BufferedImage(imgWidth,imgHeight,BufferedImage.TYPE_INT_ARGB);
			connecting  = new BufferedImage(imgWidth,imgHeight,BufferedImage.TYPE_INT_ARGB);
			transperant = new BufferedImage(imgWidth,imgHeight,BufferedImage.TYPE_INT_ARGB);

			
//		}else{
//			normal      = imgBuffer.get(ENTITY_STATE_NORMAL    );
//			highlight   = imgBuffer.get(ENTITY_STATE_HIGHLIGHT );
//			connecting  = imgBuffer.get(ENTITY_STATE_CONNECTING);
//			transperant = imgBuffer.get(ENTITY_STATE_TRANSPERANT);
//		}
		doShowConnecting = false;
		doHighlight      = false;
		setTransperant   = false;
		fillBufferedImage(normal);
		setTransperant   = true;
		fillBufferedImage(transperant);
		setTransperant   = false;
		doShowConnecting = false;
		doHighlight      = true;
		fillBufferedImage(highlight);
		doShowConnecting = true;
		doHighlight      = false;
		fillBufferedImage(connecting);
		
		
		doHighlight      = false;
		doShowConnecting = false;
		refreshImgBuffer = false;
		changeImgSize    = false;
		
		imgBuffer.put(ENTITY_STATE_NORMAL     , normal);
		imgBuffer.put(ENTITY_STATE_HIGHLIGHT  , highlight);
		imgBuffer.put(ENTITY_STATE_CONNECTING , connecting);
		imgBuffer.put(ENTITY_STATE_TRANSPERANT, transperant);

		// Must be removed directly after test
		/*
		try {
			ImageIO.write(normal, "png", new FileOutputStream("test/" + getId() + "_imag_" + imgSeq++ +".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//*/
		
	}
	public void paintEntity(Graphics2D g) {
		if((dropShadow)&&(!setTransperant))
			plotShadow(g);
		//System.out.println("(" +getId() + ") Transperancy is set to " + setTransperant + " with value of (" + entityTransperancy + ")");
		if (setTransperant){
			AlphaComposite myAlpha = AlphaComposite.getInstance(AlphaComposite.DST_ATOP,entityTransperancy);
			//AlphaComposite myAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,entityTransperancy);
		//	System.out.println("(" +getId() + ")Setting the transperancey to " +entityTransperancy);
			g.setComposite(myAlpha);

		}
		ts = getTitleSize(g);
		if (contents!=null) checkContents(g,ts.height); 
		if(!showBorder) borderWidth = 0;
		// Plotting the background
		paintBody(g);
		
		// Plotting the border
		if ((showBorder)&&(borderColor!=null))
			paintBorder(g);
		
		// Plotting the title
		if(showTitle)
			paintTitle(g, ts);
		// Plotting the contents
		paintContents(g,ts.height);
		// Drawing the selection (if exists)
		
	}
	protected void plotShadow(Graphics2D g){
		g.setColor(new Color(0f,0f,0f,.1f));
		
		switch(shape){
			case Rectangle:
				bodyShape = new Rectangle2D.Float(x+shLen, y+shLen, width, height);
				g.fill(bodyShape);
				g.setColor(new Color(0f,0f,0f,.2f));
				bodyShape = new Rectangle2D.Float(x+shLen-1, y+shLen-1, width, height);
				g.fill(bodyShape);
				g.setColor(new Color(0f,0f,0f,.4f));
				bodyShape = new Rectangle2D.Float(x+shLen-2, y+shLen-2, width, height);
				g.fill(bodyShape);
				break;
	
			case RoundedRectangle:
				bodyShape = new RoundRectangle2D.Float(x+shLen, y+shLen, width, height,cornerCurve,cornerCurve);
				g.fill(bodyShape);
				g.setColor(new Color(0f,0f,0f,.2f));
				bodyShape = new RoundRectangle2D.Float(x+shLen-1, y+shLen-1, width, height,cornerCurve,cornerCurve);
				g.fill(bodyShape);
				g.setColor(new Color(0f,0f,0f,.3f));
				bodyShape = new RoundRectangle2D.Float(x+shLen-2, y+shLen-2, width, height,cornerCurve,cornerCurve);
				g.fill(bodyShape);

				break;
			case Circle:
				bodyShape = new Ellipse2D.Float(x+shLen, y+shLen, width, height);
				g.fill(bodyShape);
				g.setColor(new Color(0f,0f,0f,.2f));
				bodyShape = new Ellipse2D.Float(x+shLen-1, y+shLen-1, width, height);
				g.fill(bodyShape);
				g.setColor(new Color(0f,0f,0f,.3f));
				bodyShape = new Ellipse2D.Float(x+shLen-2, y+shLen-2, width, height);
				g.fill(bodyShape);

				break;
			case Oval:
				g.fillOval(x+shLen, y+shLen, width, height);
				g.setColor(new Color(0f,0f,0f,.2f));
				g.fillOval(x+shLen-1, y+shLen-1, width, height);
				g.setColor(new Color(0f,0f,0f,.3f));
				g.fillOval(x+shLen-2, y+shLen-2, width, height);
				break;
		}
		
	}

	public void plot(Graphics2D g) {
		//System.out.println("(" + getId() +") -- Plot called ...");
		resetConnectionPoints();
		
		
		if (bufferActivated){
			if ((imgBuffer.size()==0)||(refreshImgBuffer)){
				calibrate(g);
				doRefreshBuffer();
			}
			BufferedImage curImg = null;
			if (doShowConnecting){
				curImg = imgBuffer.get(ENTITY_STATE_CONNECTING);
			}else if (doHighlight){
				curImg = imgBuffer.get(ENTITY_STATE_HIGHLIGHT);
			//}else if (setTransperant){
			///	curImg = imgBuffer.get(ENTITY_STATE_TRANSPERANT);
			}else{	
				curImg = imgBuffer.get(ENTITY_STATE_NORMAL);
			}
			
			if (setTransperant){
				Composite old = g.getComposite();				
				AlphaComposite myAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,entityTransperancy);
				g.setComposite(myAlpha);
				g.drawImage(curImg,x, y,null);
				g.setComposite(old);
			}else{
				g.drawImage(curImg,x, y,null);
			}

			
			
		}else{
			paintEntity(g);
		}
		if (isSelected())
			doPaintSelected(g);
		
	}
	
	protected void paintTitle(Graphics2D g,Dimension ts){
		switch(shape){
		case RoundedRectangle:
			paintTitleBackground(5, g, ts);
			g.setFont(titleFont);
			g.setColor(titleFontColor);
			TextLayout titleTxtLayout = new TextLayout(getTitle(),titleFont,g.getFontRenderContext());
			titleTxtLayout.draw(g, x+titleSideMargin, y+ts.height- (ts.height/4));
			//postTitlePaint(g, ts, titleTxtLayout);
			//g.drawString(getTitle(), x+titleSideMargin, y+ts.height- (ts.height/4));
			break;
		case Circle:
			//g.fillArc(x, y, width, width, 0, 360);
			g.drawLine((int)(x+width*0.09), y+30, (int)(x+width*0.91), y+30);
			g.setPaint(new GradientPaint(x,y,Color.BLUE, x+width, y+ts.height,Color.WHITE));
			
			//g.fillArc(x+borderWidth, y+borderWidth, width-(2*borderWidth),width-(2*borderWidth),45,90);
			//g.drawLine(x, y+getTitleHeight(g), x+width, y+getTitleHeight(g));
			g.setFont(titleFont);
			g.setColor(titleFontColor);
			g.drawString(getTitle(), x+10, y+ts.height- (ts.height/4));

			break;
		}
		
		
	}
	private void postTitlePaint(Graphics2D g,Dimension ts){
		TextLayout titleTxtLayout = new TextLayout(getTitle(),titleFont,g.getFontRenderContext());
		double eotX = x+titleSideMargin+ titleTxtLayout.getBounds().getX()+titleTxtLayout.getBounds().getWidth();
		int dis = 8;
		CubicCurve2D.Double titleTermDark = new CubicCurve2D.Double(eotX+dis,y+ts.height-borderWidth*2,eotX + 5 + dis,y+ts.getHeight()*.7,eotX+dis,y+ts.height/4,eotX + 10+dis,y);
		g.setColor(titleLightColor);
		g.draw(titleTermDark);
		dis++;
		CubicCurve2D.Double titleTermLight = new CubicCurve2D.Double(eotX+dis,y+ts.height-borderWidth*2,eotX + 5 + dis,y+ts.getHeight()*.7,eotX+dis,y+ts.height/4,eotX + 10+dis,y);
		
		g.setColor(titleDarkColor);
		g.draw(titleTermLight);
		
		
	}
	
	private void paintTitleBackground(int pattenNo,Graphics2D g,Dimension ts){
		switch(pattenNo){
			case 1:
				paintTitleBGPattern1(g, ts);
				break;
			case 2:
				paintTitleBGPattern2(g, ts);
				break;
			case 3:
				paintTitleBGPattern3(g, ts);
				break;
			case 4:
				paintTitleBGPattern4(g, ts);
				break;
			case 5:
				paintTitleBGPattern5(g, ts);
				break;
				
		}
	}
	
	private void paintTitleBGPattern1(Graphics2D g,Dimension ts){
		g.drawRoundRect(x+borderWidth/2, y+borderWidth, width-borderWidth, ts.height,cornerCurve,cornerCurve);
		Color[] colors = {titleLightColor.brighter(),titleLightColor, titleDarkColor,titleLightColor.darker()};
		float[] dist   = {0.0f,0.2f,0.7f,1.0f};
		g.setPaint(new LinearGradientPaint(x+width/2,y,x+width/2, y+ts.height,dist,colors));
		g.fillRoundRect(x+borderWidth, y+borderWidth, width-(borderWidth*2), ts.height,cornerCurve,cornerCurve);
	}
	
	private void paintTitleBGPattern2(Graphics2D g,Dimension ts){
		g.drawRoundRect(x+borderWidth/2, y+borderWidth, width-borderWidth, ts.height,cornerCurve,cornerCurve);
		Color middleColor = titleDarkColor;
		g.setPaint(new GradientPaint(x+width/2,y,titleLightColor, x+width/2, y+ts.height/2,middleColor));
		g.fillRoundRect(x+borderWidth, y+borderWidth, width-(borderWidth*2), ts.height/2+2,cornerCurve,cornerCurve);
		g.setPaint(new GradientPaint(x+width/2,y+ts.height/2,middleColor, x+width/2, y+ts.height,titleDarkColor));
		g.fillRoundRect(x+borderWidth, y+borderWidth + ts.height/2-2, width-(borderWidth*2), ts.height/2+3,cornerCurve,cornerCurve);
	}
	

	private void paintTitleBGPattern3(Graphics2D g,Dimension ts){
	
		Area upper = new Area(bodyShape);
		upper.intersect(new Area(new Rectangle2D.Float(x,y,width,ts.height/2)));
		Color midColor = GUIUtil.getMiddleColor(titleDarkColor,titleLightColor);
		g.setPaint(new GradientPaint(x+width/2,y,titleLightColor, x+width/2, y+ts.height/2,midColor));
		g.fill(upper);
		Area lower = new Area(bodyShape);
		lower.intersect(new Area(new Rectangle2D.Float(x,y+ts.height/2,width,ts.height/2)));
		g.setPaint(new GradientPaint(x+width/2,y+ts.height/2,titleDarkColor, x+width/2, y+ts.height,midColor));
		g.fill(lower);
	}

	private void paintTitleBGPattern4(Graphics2D g,Dimension ts){
		GeneralPath gp = new GeneralPath();
		gp.moveTo(x,y+ts.height);
		gp.quadTo(x+ts.height, y+ts.height/2, x+ts.height, y+ts.height/2);
		gp.lineTo(x+width-ts.height, y+ts.height/2);
		gp.quadTo(x+width, y+ts.height/4, x+width, y);
		gp.lineTo(x, y);
		gp.closePath();
		Area upper = new Area(bodyShape);
		upper.intersect(new Area(gp));
		g.setPaint(new GradientPaint(x+width/2,y,titleLightColor, x+width/2, y+ts.height,titleDarkColor));
		g.fill(upper);
		gp = new GeneralPath();
		gp.moveTo(x,y+ts.height);
		gp.quadTo(x+ts.height, y+ts.height/2, x+ts.height, y+ts.height/2);
		gp.lineTo(x+width-ts.height, y+ts.height/2);
		gp.quadTo(x+width, y+ts.height/4, x+width, y);
		gp.lineTo(x+width, y+ts.height);
		gp.lineTo(x, y+ts.height);
		gp.closePath();
		Area lower = new Area(bodyShape);
		lower.intersect(new Area(gp));
		g.setPaint(new GradientPaint(x+width/2,y,titleDarkColor, x+width/2, y+ts.height,titleLightColor.darker()));
		g.fill(lower);
	}
	private void paintTitleBGPattern5(Graphics2D g,Dimension ts){
		// Drawing all title lower background
		Color midColor = GUIUtil.getMiddleColor(titleDarkColor,titleLightColor);
		Area lower = new Area(bodyShape);
		lower.intersect(new Area(new Rectangle2D.Float(x,y,width,ts.height+borderWidth+1)));
		g.setPaint(new GradientPaint(x+width/2,y+ts.height/2,titleDarkColor, x+width/2, y+ts.height,midColor));
		g.fill(lower);
		// Drawing all title upper background
		Area upper = new Area(bodyShape);
		upper.intersect(new Area(new RoundRectangle2D.Float(x,y+ts.height/2-height,width,height,cornerCurve,cornerCurve)));
		g.setPaint(new GradientPaint(x+width/2,y,titleLightColor, x+width/2, y+ts.height/2,midColor));
		g.fill(upper);
		
		// Title side effect

		
		TextLayout titleTxtLayout = new TextLayout(getTitle(),titleFont,g.getFontRenderContext());
		double eotX = x+titleSideMargin+ titleTxtLayout.getBounds().getX()+titleTxtLayout.getBounds().getWidth();
		int dis = 8;
		// Drawing title text dark background
		GeneralPath gp = new GeneralPath();
		gp.moveTo(x,y+ts.height);
		gp.lineTo(eotX+dis,y+ts.height);
		gp.curveTo(eotX + 5 + dis,y+ts.getHeight()*.7,eotX+dis,y+ts.height/4,eotX + 10+dis,y);
		gp.lineTo(x,y);
		gp.closePath();
		Area title = new Area(bodyShape);
		title.intersect(new Area(gp));
		Color[] colors = {titleLightColor,midColor, titleDarkColor,midColor};
		float[] dist   = {0.0f,0.2f,0.7f,1.0f};
		g.setPaint(new LinearGradientPaint(x+width/2,y,x+width/2, y+ts.height,dist,colors));
		g.fill(title);
		
		
		
		
		gp = new GeneralPath();
		gp.moveTo(x,y+ts.height);
		gp.lineTo(eotX+dis,y+ts.height);
		gp.curveTo(eotX + 5 + dis,y+ts.getHeight()*.7,eotX+dis,y+ts.height/4,eotX + 10+dis,y);
		//g.setColor(titleDarkColor);
		g.setColor(Color.DARK_GRAY);
		g.draw(gp);

		dis++;
		gp = new GeneralPath();
		gp.moveTo(x,y+ts.height+1);
		gp.lineTo(eotX+dis,y+ts.height+1);
		gp.curveTo(eotX + 5 + dis,y+ts.getHeight()*.7,eotX+dis,y+ts.height/4,eotX + 10+dis,y);
		g.setColor(titleLightColor);
		g.draw(gp);
		
		
		
	}

	private static Color getMixedColor(Color c1, float pct1, Color c2, float pct2) {
	    float[] clr1 = c1.getComponents(null);
	    float[] clr2 = c2.getComponents(null);
	    for (int i = 0; i < clr1.length; i++) {
	        clr1[i] = (clr1[i] * pct1) + (clr2[i] * pct2);
	    }
	    return new Color(clr1[0], clr1[1], clr1[2], clr1[3]);
	}
	
	public void calibrate(Graphics2D g){
		if (contents!=null) checkContents(g,getTitleSize(g).height);
	}
	private void doPaintSelected(Graphics2D g){
		g.setColor(selectedBackground);
		g.setStroke(new BasicStroke(1.0f));
		drawSingleSelectionPoint(getConnectionPoint(POSITION_TOP,false), g);
		drawSingleSelectionPoint(getConnectionPoint(POSITION_MID_LEFT,false), g);
		drawSingleSelectionPoint(getConnectionPoint(POSITION_MID_RIGHT,false), g);
		drawSingleSelectionPoint(getConnectionPoint(POSITION_BUT,false), g);
		
		
	}
	private void drawSingleSelectionPoint(Point p,Graphics2D g){
		g.drawRect(p.x-selectionWidth/2, p.y-selectionWidth/2, selectionWidth, selectionWidth);
	}
	private Dimension getTitleSize(Graphics2D g){
		if (!showTitle) return new Dimension(0,0);
		// Need to specify the height depending on the font size
		Rectangle2D titleBounds = titleFont.getStringBounds(title, g.getFontRenderContext());//.getHeight() + titleTopMargin *2;
		Dimension result = new Dimension((int)(titleBounds.getWidth()+titleSideMargin+titleRightMargin), (int)(titleBounds.getHeight() + titleTopMargin *2));
		if ((autoExpand)&&(result.width>width)) {
			changeWidth(result.width);
		}
		return result;
		//return 20;
	}
	
	private Shape createClipShape() {
	    float border = 20.0f;
	    float widht = 500;
	    float height = 500;
	    float x1 = border;
	    float y1 = border;
	    float x2 = width - border;
	    float y2 = height - border;

	    float adj = 3.0f; // helps round out the sharp corners
	    float arc = 8.0f;
	    float dcx = 0.18f * width;
	    float cx1 = x1-dcx;
	    float cy1 = 0.40f * height;
	    float cx2 = x1+dcx;
	    float cy2 = 0.50f * height;

	    GeneralPath gp = new GeneralPath();
	    gp.moveTo(x1-adj, y1+adj);
	    gp.quadTo(x1, y1, x1+adj, y1);
	    gp.lineTo(x2-arc, y1);
	    gp.quadTo(x2, y1, x2, y1+arc);
	    gp.lineTo(x2, y2-arc);
	    gp.quadTo(x2, y2, x2-arc, y2);
	    gp.lineTo(x1+adj, y2);
	    gp.quadTo(x1, y2, x1, y2-adj);
	    gp.curveTo(cx2, cy2, cx1, cy1, x1-adj, y1+adj);
	    gp.closePath();
	    return gp;
	}
	
	
	
	
	protected void drawContentsBackground(Graphics2D g){
		Composite origComposite = g.getComposite();
		if (backgroundImage!=null){
			AlphaComposite myAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,bgImageTransperancy);
			if (setTransperant)
				myAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,entityTransperancy);
			
				
			g.setComposite(myAlpha);
			//AffineTransform atImageSpace = new AffineTransform();
			int imgWidth  = backgroundImage.getWidth(null);
			int imgHeight = backgroundImage.getHeight(null);
			int imgLocX = x + width/2 -imgWidth/2;
			int imgLocY = y + height/2 -imgHeight/2;
			g.drawImage(backgroundImage,imgLocX,imgLocY,null);			
		}
		g.setComposite(origComposite);
	}
	protected void paintContents(Graphics2D g,int th){
		drawContentsBackground(g);
		g.setFont(contentsFont);
		g.setColor(contentFontColor);
		if (contents!=null){
			String[] conts = contents.split("\n");
			if (conts.length>=1){
				double singleLineHeight   = contentsFont.getStringBounds(conts[0],g.getFontRenderContext()).getHeight();
				// Drawing contents
				float posY=detectContentsFirstLine(g, th, singleLineHeight, conts.length);
				switch(contentsHorizontalAlignment){
					case ALIGNMENT_CENTER:
						// Default value for the text is to be left aligned
						for(String curLine:conts){
							int txtWidth = (int)contentsFont.getStringBounds(curLine,g.getFontRenderContext()).getWidth();
							float posX = (float)(x+((width-txtWidth)/2));
							g.drawString(curLine, posX, posY);
							posY += singleLineHeight + 2;
						}

						break;
					case ALIGNMENT_RIGHT:
						// Default value for the text is to be left aligned
						for(String curLine:conts){
							double txtWidth = contentsFont.getStringBounds(curLine,g.getFontRenderContext()).getWidth();
							float posX = (float)(x+ width-(txtWidth+textRightMargin));
							g.drawString(curLine, posX, posY);
							posY += singleLineHeight + 2;
						}
						break;
					default:
						// Default value for the text is to be left aligned
						float posX = (float)(x+textLeftMargin+borderWidth);
						for(String curLine:conts){
							g.drawString(curLine, posX, posY);
							posY += singleLineHeight + 2;
						}
				}
			}
		}
	}
	protected float detectContentsFirstLine(Graphics2D g,int th,double singleLineHeight,int lineNo){
		float posY = 0; 
		switch(contentsVerticalAlignment){
			case ALIGNMENT_TOP:
				posY = (float)(y+th+borderWidth+textTopMargin + singleLineHeight);
				break;
			case ALIGNMENT_MIDDLE:
				posY = (float)(y+th+borderWidth+(height-th)/2 -(totalContentHeight/2)+ singleLineHeight);
				break;
			case ALIGNMENT_BUTTOM:
				posY = (float)(y+height + singleLineHeight - (totalContentHeight + textButtomMargin));
				break;
		
		}
		
		return posY;
	}
	private void checkContents(Graphics2D g,int th){
		if (contents!=null){
			//internalPropertyChange = true;
			String[] conts = contents.split("\n");
			if (conts.length>=1){
				double singleLineHeight   = contentsFont.getStringBounds(conts[0],g.getFontRenderContext()).getHeight();
				       totalContentHeight = ((singleLineHeight+textLineMargin)*conts.length) - textLineMargin;
				double maxTextWidth = 0;
				String longestLine = null;
				for(String curLine:conts){
					double curWidth = contentsFont.getStringBounds(curLine,g.getFontRenderContext()).getWidth();
					maxTextWidth = curWidth>maxTextWidth? curWidth:maxTextWidth; 
				}
				double totalContentWidth = textLeftMargin + maxTextWidth + (2*borderWidth) + textRightMargin;
				//System.out.println("Total contents height is : " + totalContentHeight);
				int addHeight = textTopMargin  + textButtomMargin + (2*borderWidth);
				if ((height-th)<(totalContentHeight+addHeight)){
					if (autoExpand){
						changeHeight((int)(totalContentHeight+th+addHeight));
					}else{
						// Need to clip the rest
					}
				}
				if (autoExpand){
					if ((width)<totalContentWidth){
						changeWidth((int)totalContentWidth);
					}
					/*
					if ((width)<totalContentWidth){
						if (autoExpand){
							changeWidth((int)totalContentWidth);
						}else{
							// Need to clip the rest
						}
					}
					*/
				}
			}
			//internalPropertyChange = false;
		}
	}
	
	protected int  processConPointDis(int position){
		Point target = null;
		Integer pointsCount = connectionPoints.get(position); 
		if(pointsCount==null){
			pointsCount = new Integer(0);
			connectionPoints.put(position, pointsCount);
			
		}	
		int conCount = pointsCount.intValue()+1;
		int dis = (int)Math.round(conCount/2)*4;
		dis *=(conCount%2==0?1:-1);
		connectionPoints.put(position, conCount);
		return dis;
	}
	public Point getConnectionPoint(int position,boolean checkDisplacement){
		Point target = null;
		int dis = checkDisplacement?processConPointDis(position):0;
		switch(position){
			case POSITION_TOP_LEFT:
				return new Point(x,y);
			case POSITION_TOP:
				target = new Point(x+width/2 + dis ,y-borderWidth);
				return target;
			case POSITION_TOP_RIGHT:
				return new Point(x+width,y);
			case POSITION_MID:
				return new Point(x+width/2,y+height/2);
			case POSITION_MID_RIGHT:
				return new Point(x+width,y+height/2+ dis);
			case POSITION_MID_LEFT:
				return new Point(x-borderWidth,y+height/2+ dis);
			case POSITION_BUT:
				return new Point(x+width/2 + dis,y+height);
			case POSITION_BUT_RIGHT:
				return new Point(x+width,y+height);
			case POSITION_BUT_LEFT:
				return new Point(x,y+height);
		}
		return null;
	}
	
	
	public Point getConnectionPoint1(int position){
		switch(position){
			case POSITION_TOP_LEFT:
				return new Point(x,y);
			case POSITION_TOP:
				return new Point(x+width/2,y-borderWidth);
			case POSITION_TOP_RIGHT:
				return new Point(x+width,y);
			case POSITION_MID:
				return new Point(x+width/2,y+height/2);
			case POSITION_MID_RIGHT:
				return new Point(x+width,y+height/2);
			case POSITION_MID_LEFT:
				return new Point(x-borderWidth,y+height/2);
			case POSITION_BUT:
				return new Point(x+width/2,y+height);
			case POSITION_BUT_RIGHT:
				return new Point(x+width,y+height);
			case POSITION_BUT_LEFT:
				return new Point(x,y+height);
		}
		return null;
	}
	public void paintConnectorOn(int position,Graphics2D g){
		Point conPoint = getConnectionPoint(position,true);
		switch(position){
			case POSITION_TOP:
				g.fillArc(conPoint.x-connectorSize/2, conPoint.y-borderWidth, connectorSize, connectorSize, 0, 180);
				break;
			case POSITION_MID_RIGHT:
				g.fillArc(conPoint.x, conPoint.y-connectorSize/2, connectorSize, connectorSize, 270, 450);
				break;
			case POSITION_MID_LEFT:
				g.fillArc(conPoint.x-borderWidth, conPoint.y-connectorSize/2, connectorSize, connectorSize, 90,270);
				break;
			case POSITION_BUT:
				g.fillArc(conPoint.x-connectorSize/2, conPoint.y, connectorSize, connectorSize, 180, 360);
				break;

		}
		
	}
	public String dumpLayoutInfo(){
		StringBuilder sb = new StringBuilder();
		sb.append(getId());
		sb.append("|");
		sb.append(bounds.x);
		sb.append("|");
		sb.append(bounds.y);
		sb.append("|");
		sb.append(getWidth());
		sb.append("|");
		sb.append(getHeight());
		
		return sb.toString();
	}
	public String dumpGUIConfig(){
		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toString(getType()));
		// Title information
		sb.append("|");
		sb.append(exportColor(getTitleDarkColor()));
		sb.append("|");
		sb.append(exportColor(getTitleLightColor()));
		sb.append("|");
		sb.append(exportColor(getTitleFontColor()));
		sb.append("|");
		sb.append(exportFont(getTitleFont()));
		// Content information
		sb.append("|");
		sb.append(exportColor(getContentBackground()));
		sb.append("|");
		sb.append(exportColor(getContentFontColor()));
		sb.append("|");
		sb.append(exportFont(getContentsFont()));
		return sb.toString();
		
	}
	public void  adjustToGUIConfigInfo(String data){
		String[] info = data.split("\\|");
		if ((info!=null)&&(info.length>0)){
			setTitleDarkColor(importColor(info[0]));
			setTitleLightColor(importColor(info[1]));
			setTitleFontColor(importColor(info[2]));
			setTitleFont(importFont(info[3]));
			setContentBackground(importColor(info[4]));
			setContentFontColor(importColor(info[5]));
			setContentsFont(importFont(info[6]));
		}
	}
	
	
	private String exportFont(Font font){
		StringBuilder sb = new StringBuilder();
		sb.append(font.getFamily());
		sb.append(",");
		sb.append(Integer.toString(font.getStyle()));
		sb.append(",");
		sb.append(Integer.toString(font.getSize()));
		return sb.toString();
	}
	
	private Font importFont(String fontText){
		String[] fntInfo = fontText.split(",");
		return new Font(fntInfo[0],Integer.parseInt(fntInfo[1]),Integer.parseInt(fntInfo[2]));
	}
	private Color importColor(String colorText){
		String[] colInfo = colorText.split(",");
		return new Color(Integer.parseInt(colInfo[0]),Integer.parseInt(colInfo[1]),Integer.parseInt(colInfo[2]));
	}
	
	private String exportColor(Color color){
		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toString(color.getRed()));
		sb.append(",");
		sb.append(Integer.toString(color.getGreen()));
		sb.append(",");
		sb.append(Integer.toString(color.getBlue()));
		return sb.toString();
		
	}
	public void  adjustToLayoutInfo(String data){
		String[] info = data.split("\\|");
		if ((info!=null)&&(info.length>0)){
			String id = info[0];
			int    x  = Integer.parseInt(info[1]);
			int    y  = Integer.parseInt(info[2]);
			int    w  = Integer.parseInt(info[3]);
			int    h  = Integer.parseInt(info[4]);
			//confs.put(id,new Rectangle(x,y,w,h));
			setX(x);
			setY(y);
			changeWidth(w);
			changeHeight(h);			
		}

		
	}

	
	public boolean equals(EntityPlotter compare){
		if ((compare.getId()!=null)&&(id!=null)) return id.equals(compare.getId());
		return false;
	}
	public void activateConnectionTo(boolean activate){connectTo=activate;}
	public void activateConnectionFrom(boolean activate){connectFrom=activate;}
	
	public boolean canConnectFrom(){
		return connectFrom;
	}
	public boolean canConnectTo(){
		return connectTo;
	}

	
	// Setter and Getter methods
	public HashMap<String,EntityPlotter> getConnectedEntities(){
		return connectedEntities;
	}
	public void newConnection(EntityConnector newCon){
		if (newCon.from.equals(this))
			connectedEntities.put(newCon.to.getId(), newCon.to);
		else if (newCon.to.equals(this))
			connectedEntities.put(newCon.from.getId(), newCon.from);
		connections.add(newCon);
	}
	public void removeConnection(EntityConnector delCon){
		if (delCon.from.equals(this))
			connectedEntities.remove(delCon.to.getId());
		else if (delCon.to.equals(this))
			connectedEntities.remove(delCon.from.getId());
		connections.remove(delCon);
		
		
	}
	
	public EntityConnector  getConnectorForEntity(EntityPlotter entity){
		if ((entity==null)||(!connectedEntities.containsValue(entity))) return null;
		for(EntityConnector curConn:connections)
			if ((curConn.getFrom().equals(entity))||(curConn.getTo().equals(entity))) return curConn;
		return null;
	}
	
	
	
	public boolean isDraggable() {
		return draggable;
	}

	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
	}

	public void setShape(PlotShape shape){
		this.shape = shape;
	}
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
		bounds.setLocation(x, y);
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		bounds.setLocation(x, y);
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		changeWidth(width);
		
		refreshBuffer();
	}
	protected void changeWidth(int width){
		changeImgSize    = true;
		bounds.setSize(width, height);
		this.width = width;
		
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		changeHeight(height);
		
		refreshBuffer();
	}
	protected void changeHeight(int height) {
		changeImgSize    = true;
		bounds.setSize(width, height);
		this.height = height;
	}
	
	public Color getContentBackground() {
		return contentBackground;
	}
	public void setContentBackground(Color contentBackground) {
		this.contentBackground = contentBackground;
		refreshBuffer();
	}
	public PlotShape getShape() {
		return shape;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
		refreshBuffer();
		
	}
	public Rectangle2D getBounds(){
		return bounds;
	}
	
	public void setAutoExpand(boolean expand){
		this.autoExpand = expand;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
		refreshBuffer();
	}
	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
		refreshBuffer();
	}
	public Color getTitleBackground() {
		return titleBackground;
	}
	public int getTextLeftMargin() {
		return textLeftMargin;
	}
	public void setTextLeftMargin(int textLeftMargin) {
		this.textLeftMargin = textLeftMargin;
		refreshBuffer();
	}

	public void setTitleBackground(Color titleBackground) {
		this.titleBackground = titleBackground;
		refreshBuffer();
	}
	public int getConnectorSize(){
		return connectorSize;
	}
	public boolean isHighlighted(){
		return doHighlight;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		
		this.selected = selected;
		//refreshBuffer();
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Object getUserObject() {
		return userObject;
	}

	public void setUserObject(Object userObject) {
		this.userObject = userObject;
	}

	public boolean isShowTitle() {
		return showTitle;
	}

	public void setShowTitle(boolean showTitle) {
		this.showTitle = showTitle;
		refreshBuffer();
	}

	public Font getContentsFont() {
		return contentsFont;
		
	}

	public void setContentsFont(Font contentsFont) {
		this.contentsFont = contentsFont;
		refreshBuffer();
		
	}

	public int getContentsHorizontalAlignment() {
		return contentsHorizontalAlignment;
	}

	public void setContentsHorizontalAlignment(int contentsAlignment) {
		this.contentsHorizontalAlignment = contentsAlignment;
		refreshBuffer();
	}

	public int getTitleAlignment() {
		return titleAlignment;
	}

	public void setTitleAlignment(int titleAlignment) {
		this.titleAlignment = titleAlignment;
		refreshBuffer();
	}

	public void setCornerCurve(int cornerCurve) {
		this.cornerCurve = cornerCurve;
		refreshBuffer();
	}

	public Color getTitleDarkColor() {
		return titleDarkColor;
	}

	public void setTitleDarkColor(Color titleDarkColor) {
		this.titleDarkColor = titleDarkColor;
		refreshBuffer();
	}

	public Color getTitleLightColor() {
		return titleLightColor;
	}

	public void setTitleLightColor(Color titleLightColor) {
		this.titleLightColor = titleLightColor;
		refreshBuffer();
	}

	public Font getTitleFont() {
		return titleFont;
	}

	public void setTitleFont(Font titleFont) {
		
		this.titleFont = titleFont;
		refreshBuffer();
	}

	public Color getTitleFontColor() {
		return titleFontColor;
	}

	public void setTitleFontColor(Color titleFontColor) {
		this.titleFontColor = titleFontColor;
		refreshBuffer();
	}

	public Color getContentFontColor() {
		return contentFontColor;
	}

	public void setContentFontColor(Color contentFontColor) {
		this.contentFontColor = contentFontColor;
		refreshBuffer();
	}

	public int getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
		refreshBuffer();
	}

	public boolean isShowBorder() {
		return showBorder;
	}

	public void setShowBorder(boolean showBorder) {
		this.showBorder = showBorder;
		refreshBuffer();
	}

	public int getContentsVerticalAlignment() {
		return contentsVerticalAlignment;
	}

	public void setContentsVerticalAlignment(int contentsVerticalAlignment) {
		this.contentsVerticalAlignment = contentsVerticalAlignment;
		refreshBuffer();
	}

	public Color getConnectorColor() {
		return connectorColor;
	}

	public void setConnectorColor(Color connectorColor) {
		this.connectorColor = connectorColor;
		refreshBuffer();
	}

	public Image getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
		refreshBuffer();
	}
	
	
	
	public Vector<EntityConnector> getConnections() {
		return connections;
	}

	public void setBGImageTransperancy(float newValue){
		if ((newValue>=0)&&(newValue<=1))
			bgImageTransperancy = newValue;
	}
	
	

	/**
	 * @return the entityTransperancy
	 */
	public float getEntityTransperancy() {
		return entityTransperancy;
	}

	/**
	 * @param entityTransperancy the entityTransperancy to set
	 */
	public void setEntityTransperancy(float entityTransperancy) {
		if (this.entityTransperancy!= entityTransperancy){
			this.entityTransperancy = entityTransperancy;
			
			//refreshBuffer();
		}
	}

	/**
	 * @return the setTransperant
	 */
	public boolean isTransperant() {
		return setTransperant;
	}

	/**
	 * @param setTransperant the setTransperant to set
	 */
	public void setTransperant(boolean setTransperant) {
		this.setTransperant = setTransperant;
	}
	
	/**
	 * @return the dropShadow
	 */
	public boolean isDropShadow() {
		return dropShadow;
	}

	/**
	 * @param dropShadow the dropShadow to set
	 */
	public void setDropShadow(boolean dropShadow) {
		this.dropShadow = dropShadow;
	}

	/**
	 * @return the dropReflection
	 */
	public boolean isDropReflection() {
		return dropReflection;
	}

	/**
	 * @param dropReflection the dropReflection to set
	 */
	public void setDropReflection(boolean dropReflection) {
		this.dropReflection = dropReflection;
	}







	private static final String ENTITY_STATE_NORMAL      = "normal";
	private static final String ENTITY_STATE_HIGHLIGHT   = "hightlight";
	private static final String ENTITY_STATE_CONNECTING  = "connecting";
	private static final String ENTITY_STATE_TRANSPERANT = "transperant";
	
	
	
}
