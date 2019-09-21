package com.smyld.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.lang.reflect.Method;


//import javax.swing.JFrame;

public class FrameTest01 extends Window {

	FrameTest01() throws HeadlessException {
		super(null);
		
	}
	BufferedImage buff;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FrameTest01 t = new FrameTest01();
		t.setSize(600,400);
		t.setVisible(true);
	}
	public void paint(Graphics gr){
		
		setWindowNonOpaque(this);
		//
		super.paint(gr);
		if (buff==null) createObject();
		Graphics2D g = (Graphics2D)gr;
		//getGlassPane().setBackground(new Color(0f,1f,1f,0f));
		//getGlassPane().setVisible(true);
		g.drawImage(buff, 0, 0, getWidth(), getHeight(), null);
		
		
	}
	private void createObject(){
		
		buff = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = buff.createGraphics();
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		g.drawString("Hi there", 50, 50);
		
	}
	
	private void checkGraphicCapability(){
		GraphicsEnvironment env =  GraphicsEnvironment.getLocalGraphicsEnvironment();
	       GraphicsDevice[] devices = env.getScreenDevices();
	       GraphicsConfiguration translucencyCapableGC = null;
	     for (int i = 0; i < devices.length && translucencyCapableGC == null; i++) {
	    	 GraphicsConfiguration[] configs = devices[i].getConfigurations();    
	    	 for (int j = 0; j < configs.length && translucencyCapableGC == null; j++) {              
	    		 /*
	    		 if (AWTUtilities.isTranslucencyCapable(configs[j])) {                  
	    			 translucencyCapableGC = configs[j];              
	    		  }
	    		  */          
	    	 }      
	    } 		
	}
	 private void setWindowNonOpaque(Window w)
	  {
	    try 
	    {
	      Class<?> c = Class.forName("com.sun.awt.AWTUtilities");
	      Method m = c.getMethod("setWindowOpaque", Window.class, boolean.class);
	      m.invoke(null, w, false);
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    }    
	  }

}
