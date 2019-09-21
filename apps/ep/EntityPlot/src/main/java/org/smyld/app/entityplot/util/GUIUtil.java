package org.smyld.app.entityplot.util;

import java.awt.Color;

public class GUIUtil {

	
	public static Color getMiddleColor(Color col1,Color col2){
		int resRed   = (int)(col1.getRed()+col2.getRed())/2;
		int resGreen = (int)(col1.getGreen()+col2.getGreen())/2;
		int resBlue  = (int)(col1.getBlue()+col2.getBlue())/2;
		int resAlpha = (int)(col1.getAlpha()+col2.getAlpha())/2;
		return new Color(resRed,resGreen,resBlue,resAlpha);
	}
	

}
