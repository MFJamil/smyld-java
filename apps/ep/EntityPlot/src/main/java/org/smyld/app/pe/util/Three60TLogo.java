package org.smyld.app.pe.util;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class Three60TLogo {
	BufferedImage logoImg;
	BufferedImage reflectImg;
	int height = 45;
	int width  = 296;
	
	public Three60TLogo(){
		paintLogo();
	}
	
	private void paintLogo(){
		int imHi          = 45;
		int gap           = 1;
		if ((logoImg==null)&&(reflectImg==null)){
			int imWd          = 296;
			//int imageHeight   = (int)(imHi*1.4);
	        float opacity     = 0.3f;
	        float fadeHeight  = 0.5f;
	
			logoImg    = new BufferedImage(imWd,imHi,BufferedImage.TYPE_INT_ARGB);
			reflectImg = new BufferedImage(imWd,imHi,BufferedImage.TYPE_INT_ARGB);
	
			Graphics2D rg = logoImg.createGraphics();
			rg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
			rg.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY); 
			rg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			rg.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			paintSingleLogo(rg, 0);
			rg.dispose();
			
			rg = reflectImg.createGraphics();
			rg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
			rg.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY); 
			rg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			rg.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			rg.translate(0, imHi+gap);
			rg.scale(1,-1);
			rg.drawRenderedImage( logoImg, null );
			rg.setComposite( AlphaComposite.getInstance( AlphaComposite.DST_IN ) );
	        rg.setPaint(new GradientPaint(0, imHi*fadeHeight, new Color( 0.0f, 0.0f, 0.0f, 0.0f ),0, imHi, new Color( 0.0f, 0.0f, 0.0f, opacity )));
	        rg.fillRect( 0, 0, imWd+1, imHi+5 );
	        rg.dispose();
		}
	}
	public void paintSingleLogo(Graphics2D g,int offset){
		int imOff  = offset;
		int corn   = 15;
		int txtSp  = 10;
		int txtWd  = 60;
		int txtHi  = 25;
		int txtTop = imOff + 10;
		int txtBut = txtTop+txtHi;
		int txtMid = txtTop + txtHi/2;
		int txtLn  = 5;
		int txLnD  = txtLn/2;
		int txtTh  = txtWd/3;
		
		Color  grColor    = new Color(0,74,0);
		Color  gaColor    = new Color(203,205,202);
		Stroke textStroke = new BasicStroke(txtLn,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		Stroke arrStroke  = new BasicStroke(1    ,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL);
		
		RoundRectangle2D.Float mainShape  = new RoundRectangle2D.Float(imOff,imOff,width,height,corn,corn);
		RoundRectangle2D.Float sh360cut   = new RoundRectangle2D.Float(imOff+2,imOff+2,width,height-2,corn,corn);
		Rectangle2D.Float      sh360rcut  = new Rectangle2D.Float(imOff,imOff,width-txtWd-(2*txtSp)+2,height-2);
		
		g.setColor(grColor);
		g.fill(mainShape);
		Area sh360ar1 = new Area(sh360cut);
		sh360ar1.intersect(new Area(sh360rcut));
		g.setColor(gaColor);
		g.fill(sh360ar1);
		
		g.setStroke(textStroke);
		g.drawLine(imOff+width-txtSp-txtWd  , txtTop      ,imOff+width-txtSp        , txtTop);
		g.drawLine(imOff+width-txtSp-txtWd/2, txtTop+txLnD,imOff+width-txtSp-txtWd/2, txtBut);
		g.setColor(grColor);
		GeneralPath gp = new GeneralPath();
		// Painting 3
		gp.moveTo(imOff+txtSp             , txtTop);
		gp.lineTo(imOff+txtSp+txtWd       , txtTop);
		gp.lineTo(imOff+txtSp+txtWd       , txtBut);
		gp.lineTo(imOff+txtSp             , txtBut);
		gp.moveTo(imOff+txtSp+txtWd-txLnD , txtMid);
		gp.lineTo(imOff+txtSp*2           , txtMid);
		
		// Painting 6
		gp.moveTo(imOff+txtSp*2+txtWd*2     , txtTop);
		gp.lineTo(imOff+txtSp*2+txtWd       , txtTop);
		gp.lineTo(imOff+txtSp*2+txtWd       , txtBut);
		gp.lineTo(imOff+txtSp*2+txtWd*2     , txtBut);
		gp.lineTo(imOff+txtSp*2+txtWd*2     , txtMid);
		gp.lineTo(imOff+txtSp*2+txtWd+txLnD , txtMid);
		
		// Painting 0
		gp.moveTo(imOff+txtSp*3+txtWd*2 + txtTh , txtTop);
		gp.lineTo(imOff+txtSp*3+txtWd*2 , txtTop);
		gp.lineTo(imOff+txtSp*3+txtWd*2 , txtBut);
		gp.lineTo(imOff+txtSp*3+txtWd*3 , txtBut);
		gp.lineTo(imOff+txtSp*3+txtWd*3 , txtTop);
		gp.lineTo(imOff+txtSp*3+txtWd*2 + txtTh *2, txtTop);
		g.draw(gp);
		g.setStroke(arrStroke);
		gp = new GeneralPath();
		gp.moveTo(imOff+txtSp*3+txtWd*2 + txtTh     , txtTop-txLnD );
		gp.lineTo(imOff+txtSp*3+txtWd*2 + txtTh     , txtTop-txLnD-2);
		gp.lineTo(imOff+txtSp*3+txtWd*2 + txtTh*1.5 , txtTop+txLnD);
		gp.lineTo(imOff+txtSp*3+txtWd*2 + txtTh     , txtTop+txLnD+3);
		g.fill(gp);

		// Painting T
		gp = new GeneralPath();
		gp.moveTo(imOff+txtSp*3+txtWd*2 + txtTh*2   , txtTop-txLnD );
		gp.lineTo(imOff+txtSp*3+txtWd*2 + txtTh*2   , txtTop-txLnD-2);
		gp.lineTo(imOff+txtSp*3+txtWd*2 + txtTh*1.5 , txtTop+txLnD);
		gp.lineTo(imOff+txtSp*3+txtWd*2 + txtTh*2   , txtTop+txLnD+3);
		g.fill(gp);
		
	}

	/**
	 * @return the logoImg
	 */
	public BufferedImage getLogoImg() {
		return logoImg;
	}

	/**
	 * @param logoImg the logoImg to set
	 */
	public void setLogoImg(BufferedImage logoImg) {
		this.logoImg = logoImg;
	}

	/**
	 * @return the reflectImg
	 */
	public BufferedImage getReflectImg() {
		return reflectImg;
	}

	/**
	 * @param reflectImg the reflectImg to set
	 */
	public void setReflectImg(BufferedImage reflectImg) {
		this.reflectImg = reflectImg;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
