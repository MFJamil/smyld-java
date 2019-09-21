package org.smyld.app.entityplot;

import java.awt.Color;
import java.awt.GradientPaint;

public class GradientPlot {
	Color             fromColor;
	Color             toColor;
	int               width,height;
	GradientDirection direction;
	GradientType      type;
	
	public GradientPlot(){
		
	}
	public GradientPlot(Color from,Color to){
		setFromColor(from);
		setToColor(to);
	}
	
	
	public GradientPaint generate(){
		return generate(0,0,width,height);
	}

	public GradientPaint generate(int x,int y,int width,int height){
		int startX = x;
		int startY = y;
		int endX   = x;
		int endY   = y;
		
		switch(direction){
			case LeftToRight:
				startY = endY = y+height/2;
				endX += width; 
				break;
			case RightToLeft:
				startY = endY = y+height/2;
				startX += width; 
				break;
			case TopToButtom:
				endY = y+height;
				startX = endX = x+width/2; 
				break;
			case ButtomToTop:
				startY = y+height;
				startX = endX = x+width/2; 
				break;
			case Free:
				// We assume that the width  and height are reflecting the second free point
				endX = width;
				endY = height;
				break;
		}
		//TODO  Implementation for the gradient type will be done later
		return new GradientPaint(startX,startY,fromColor,endX,endY,toColor);
	}
	
	
	public Color getFromColor() {
		return fromColor;
	}
	public void setFromColor(Color fromColor) {
		this.fromColor = fromColor;
	}
	public Color getToColor() {
		return toColor;
	}
	public void setToColor(Color toColor) {
		this.toColor = toColor;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public GradientDirection getDirection() {
		return direction;
	}
	public void setDirection(GradientDirection direction) {
		this.direction = direction;
	}
	public GradientType getType() {
		return type;
	}
	public void setType(GradientType type) {
		this.type = type;
	}













	public enum GradientDirection{
		RightToLeft,
		LeftToRight,
		TopToButtom,
		ButtomToTop,
		Free;
		
	}
	public enum GradientType{
	  Linear,
	  BiLinear;
	  
	}	 
}
