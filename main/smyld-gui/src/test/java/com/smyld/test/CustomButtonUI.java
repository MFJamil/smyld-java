package com.smyld.test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

public class CustomButtonUI extends BasicButtonUI {
	 public static final float[] BLUR3x3 = {
	        0.1f, 0.1f, 0.1f,
	        0.1f, 0.2f, 0.1f,
	        0.1f, 0.1f, 0.1f };
	 public static final float[] BLUR2x2 = {
	        0.1f, 0.1f,
	        0.1f, 0.1f};
	 public static final float[] BLUR1x1 = {
	        0.1f, 0.1f};
	            
	    public static ComponentUI createUI(JComponent c) {
	        return new CustomButtonUI();
	    }
	    
	    public void paint(Graphics g, JComponent comp) {

	        Graphics2D panelG2 = (Graphics2D)g;

	        //  Create a buffered image to hold the rendering
	        //  of the component that is passed in.

	        BufferedImage image = new BufferedImage(
	            comp.getWidth(),
	            comp.getHeight(),
	            BufferedImage.TYPE_INT_ARGB);

	        //  Draw the component onto the buffered image.
	        Graphics2D g2 = image.createGraphics();
	        
	        super.paint(g2, comp);

	        
	        //  Draw the resulting buffered image onto the current 
	        //  Graphics context with the same blurring convolution 
	        //  kernel as in Code Example 1.
	        
	        if (!comp.isEnabled()) {
	            Kernel kernel = new Kernel(1, 1, BLUR1x1);
	            ConvolveOp cop = new ConvolveOp(kernel,
	                                            ConvolveOp.EDGE_NO_OP,
	                                            null);
	            Image newImage = cop.filter(image, null);
	            panelG2.drawImage(newImage, 0, 0, null);
	        } else {
	            panelG2.drawImage(image, 0, 0, null);
	        }

	    }

}
