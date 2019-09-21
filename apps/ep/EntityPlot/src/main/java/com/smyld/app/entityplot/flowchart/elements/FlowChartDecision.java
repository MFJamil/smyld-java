package com.smyld.app.entityplot.flowchart.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import com.smyld.app.entityplot.PlotShape;
import com.smyld.app.entityplot.flowchart.EntityBasicFlowChart;

public class FlowChartDecision extends EntityBasicFlowChart {
	
	int x0  = 0;
	int y0  = 0;
	int x1  = 0;
	int y1  = 0;
	int x2  = 0;
	int y2  = 0;
	int x3  = 0;
	int y3  = 0;
	
	public FlowChartDecision(int x, int y, int width, int height) {
		super(x,y,width,height,PlotShape.Free,ChartElement.Decision);
		computePoints(x,y);
		bodyGrad.setFromColor(new Color(251,172,66));
		bodyGrad.setToColor(new Color(253,230,199));
		
		
	}
	private void computePoints(int origX,int origY){
		int dis = curve/2;
		x0 = origX+dis;
		y0 = origY+height/2;
		x1 = origX+width/2;
		y1 = origY+dis;
		x2 = origX + width-dis;
		y2 = origY+height/2;
		x3 = origX+width/2;
		y3 = origY+height-dis;
		
	}
	protected void plotShadow(Graphics2D g){
		g.setStroke(new BasicStroke(14,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		System.out.println("Drop shadow is called");
		g.setColor(new Color(0f,0f,0f,.1f));
		computePoints(x+shLen,y+shLen);
		Shape result = drawDecisionBody();
		g.draw(result);
		g.fill(result);
		g.setColor(new Color(0f,0f,0f,.2f));
		computePoints(x+shLen-1, y+shLen-1);
		result = drawDecisionBody();
		g.draw(result);
		g.fill(result);
		g.setColor(new Color(0f,0f,0f,.4f));
		computePoints(x+shLen-2, y+shLen-2);
		result = drawDecisionBody();
		g.draw(result);
		g.fill(result);
		/*
				bodyShape = new Rectangle2D.Float(x+shLen, y+shLen, width, height);
				g.fill(bodyShape);
				g.setColor(new Color(0f,0f,0f,.2f));
				bodyShape = new Rectangle2D.Float(x+shLen-1, y+shLen-1, width, height);
				g.fill(bodyShape);
				g.setColor(new Color(0f,0f,0f,.4f));
				bodyShape = new Rectangle2D.Float(x+shLen-2, y+shLen-2, width, height);
				g.fill(bodyShape);
		*/
	}
	
	protected void paintBody(Graphics2D g) {
		computePoints(x,y);
		g.setPaint(getContentGradiant().generate(x, y, width, height));
		g.setStroke(new BasicStroke(14,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		Shape result = drawDecisionBody();
		g.draw(result);
		g.fill(result);
		
	}
	private Shape drawDecisionBody(){
	    GeneralPath gp = new GeneralPath();
	    gp.moveTo(x0,y0);
	    gp.lineTo(x1, y1);
	    gp.lineTo(x2, y2);
	    gp.lineTo(x3, y3);
	    gp.lineTo(x0,y0);
	    gp.closePath();
	    return gp;
	}
	protected void paintContents(Graphics2D g,int th){
		//setContents("Hi there I guess this will overload\n this is my text check \n I do not know how it will be");
		super.paintContents(g, th);
		/*
		drawContentsBackground(g);
		g.setFont(contentsFont);
		g.setColor(contentFontColor);
		setContents("Hi there\n this is my text check \n I do not know how it will be");
		adjustToTextSize(g);
		*/
	}
	private void adjustToTextSize(Graphics2D g){
		
		if (contents!=null){

			String[] conts = contents.split("\n");
			if (conts.length>=1){
				Rectangle2D rectFirstLine    = contentsFont.getStringBounds(conts[0],g.getFontRenderContext());
				double      singleLineHeight = rectFirstLine.getHeight();
				int         lineNo           = conts.length;
				int         totalTextHeight  = (int)(lineNo*singleLineHeight) + ((lineNo-1)*textLineMargin);
				int         upperLineX       = (int)(rectFirstLine.getWidth()/2);
				boolean     isEven           = (lineNo%2==0);
				System.out.println("Single line height is : " + singleLineHeight);
				System.out.println("Number of lines are   : " + lineNo);
				System.out.println("Total text height is  : " + totalTextHeight);
				System.out.println("Uppder line X is      : " + upperLineX);
				System.out.println("lines number is " + (isEven?"Even":"Odd"));
				
				// Calculating the upper text line limits
				
				// Calculating the lower text line limits
				
				
			}
		}
		
	}
	
	private static final int curve = 14;

}
