package org.smyld.app.entityplot;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GUITester01 {

	    
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI(); 
	            }
	        });
	    }

	    private static void createAndShowGUI() {
	        System.out.println("Created GUI on EDT? "+
	        SwingUtilities.isEventDispatchThread());
	        JFrame f = new JFrame("Swing Paint Demo");
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	        f.add(new MyPanel());
	        f.pack();
	        f.setVisible(true);
	        f.setSize(800,400);
	    } 
	}

	class MyPanel extends JPanel {

	    private int squareX = 50;
	    private int squareY = 50;
	    private int squareW = 20;
	    private int squareH = 20;

	    public MyPanel() {

	        //setBorder(BorderFactory.createLineBorder(Color.black));
	        
	        addMouseListener(new MouseAdapter() {
	            public void mousePressed(MouseEvent e) {
	                moveSquare(e.getX(),e.getY());
	            }
	        });

	        addMouseMotionListener(new MouseAdapter() {
	            public void mouseDragged(MouseEvent e) {
	                moveSquare(e.getX(),e.getY());
	            	
	                
	            }
	        });
	        
	    }
	    
	    private void moveSquare(int x, int y) {
	        int OFFSET = 1;
	        if ((squareX!=x) || (squareY!=y)) {
	            //repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
	            squareX=x;
	            squareY=y;
	            repaint();
	            //repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
	        } 
	    }
	    

	    public Dimension getPreferredSize() {
	        return new Dimension(250,200);
	    }
	    
	    protected void paintComponent(Graphics gr) {
	    	Graphics2D g = (Graphics2D)gr;
	    	g.clearRect(0, 0, getWidth(), getHeight());
	    	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,	 RenderingHints.VALUE_ANTIALIAS_ON);
	        
//	    	paintSingleOn(200,200,g);
//	    	paintSingleOn(350,200,g);
//	    	paintSingleOn(500,200,g);
//	    	paintSingleOn(650,200,g);
//	    	paintSingleOn(800,200,g);
	    	
	    	BufferedImage img = new BufferedImage(220,220,BufferedImage.TYPE_INT_ARGB);
	    	Graphics2D graph =  img.createGraphics();
	    	graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING,	 RenderingHints.VALUE_ANTIALIAS_ON);
	    	paintSingleOn(10, 220, graph);
	    	int smX = 30;//55;
	    	int smY = 22;//42;
	    	Image sm =  img.getScaledInstance(smX, smY, BufferedImage.SCALE_SMOOTH);
	    	
	    	AffineTransform rot = AffineTransform.getRotateInstance(Math.toRadians(90),11,15);
	    	BufferedImage vm = new BufferedImage((int)(smY*1.2),(int)(smX*1.2),BufferedImage.TYPE_INT_ARGB);
	    	Graphics2D gvm =  vm.createGraphics();
	    	gvm.drawImage(sm,rot,null);
	    	
	    	gvm.translate(-11, -15);
	    	//gvm.setColor(Color.BLACK);
	    	//gvm.fillRect(0, 0, smY, smX);
	    	gvm.dispose();
	    	int xpos = 50;
	    	int ypos = 50;
	    	for(int i=0;i<20;i++){
	    		
	    		g.drawImage(sm, xpos, ypos, smX, smY, null);
	    		xpos +=(int)(smX*.65);
	    	}
	    	xpos +=(int)(smX*.2);
	    	ypos += smX/1.8;
	    	for(int i=0;i<20;i++){
	    		
	    		g.drawImage(vm,xpos, ypos , smY, smX, null);
	    		if (i<20) ypos += (int)(smY*.75);
	    	}

	    	//g.drawImage(vm, 200, 120, smY, smX, null);
	    	
	    	//g.get
	    	
	    	
	    	
//	    	((Graphics2D)img.getGraphics()).rotate(Math.toRadians(90));
//	    	Image vm =  img.getScaledInstance(smY, smX, BufferedImage.SCALE_SMOOTH);
//	    	
//	    	g.drawImage(vm, 10, 20, smX, smY, null);
	    	/*
	    	g.drawImage(sm, 200, 200, smX, smY, null);
	    	g.drawImage(sm, 350, 200, smX, smY, null);
	    	g.drawImage(sm, 500, 200, smX, smY, null);
	    	g.drawImage(sm, 650, 200, smX, smY, null);
	    	*/
	    	g.dispose();
	        
	    }  
	    private void paintSingleOn(int px,int py,Graphics2D g){
	    	paintLowerOn(px,py,g);
	    	paintUpperOn(px+100,py-100,g);
	    	g.dispose();
	    }
	    private void paintLowerOn(int px,int py,Graphics2D g){
	        g.setStroke(new BasicStroke(8));
	        g.setColor(Color.BLACK);
	        GeneralPath gp = new GeneralPath();
	        gp.moveTo(px, py);
	        gp.curveTo(px+20, py-100,px+80, py-50, px+100, py-100);
	    	gp.curveTo(px+120, py-50,px+180, py-100, px+200, py);
	    	gp.closePath();
	    	g.draw(gp);
	    	
	        px += 60;
	    	g.setStroke(new BasicStroke(6));
	        gp = new GeneralPath();
	        gp.moveTo(px, py);
	        gp.curveTo(px+10, py-100,px+30, py-50, px+40, py-100);
	    	gp.curveTo(px+50, py-50,px+70, py-100, px+80, py);
	    	g.draw(gp);
	    	
	    	px += 40;
	    	gp = new GeneralPath();
	    	gp.moveTo(px, py-10);
	    	gp.quadTo(px-20,py-35, px, py-60);
	    	gp.quadTo(px+20,py-35, px, py-10);
	    	g.setColor(Color.RED);
	    	g.fill(gp);
	    	g.setColor(Color.BLACK);
	    	g.draw(gp);
	    	

	    }
	    
	    private void paintUpperOn(int px,int py,Graphics2D g){
	        g.setStroke(new BasicStroke(4));
	        

	        GeneralPath gp = new GeneralPath();
	        gp.moveTo(px   ,py);
	        gp.quadTo(px   ,py-40,px-40,py-40);
	        gp.quadTo(px-20,py-50,px-40,py-60);
	        gp.quadTo(px   ,py-60,px   ,py-100);
	        gp.quadTo(px   ,py-60,px+40,py-60);
	        gp.quadTo(px+20,py-50,px+40,py-40);
	        gp.quadTo(px   ,py-40,px   ,py);
	        g.draw(gp);
	        Color[] maincolors = {Color.DARK_GRAY, Color.LIGHT_GRAY};
	        float[] mainDist = {0.0f, 0.9f};

	        g.setPaint(new RadialGradientPaint(px,py-50,40,mainDist,maincolors));
	        g.fill(gp);
	        Color[] colors = {Color.YELLOW, Color.RED};
	        float[] dist = {0.0f, 0.9f};

	        g.setPaint(new RadialGradientPaint(px-40,py-50,10,dist,colors));
	        Ellipse2D cir = new Ellipse2D.Float(px-50,py-60,20,20);
	        g.fill(cir);
	        g.setStroke(new BasicStroke(1));
	        g.setColor(Color.DARK_GRAY);
	        g.draw(cir);
	        
	        g.setPaint(new RadialGradientPaint(px+40,py-50,10,dist,colors));
	        cir = new Ellipse2D.Float(px+30,py-60,20,20);
	        g.fill(cir);
	        g.setStroke(new BasicStroke(1));
	        g.setColor(Color.DARK_GRAY);
	        g.draw(cir);
	        
	        // Centered Pearl
	        cir = new Ellipse2D.Float(px-10,py-70,20,40);
	        g.setPaint(new RadialGradientPaint(px-4,py-70,24,dist,colors,CycleMethod.NO_CYCLE));
	        g.fill(cir);
	        g.setStroke(new BasicStroke(1));
	        g.setColor(Color.DARK_GRAY);
	        g.draw(cir);
	        
	        // Helal
	        gp = new GeneralPath();
	        gp.moveTo(px-20, py-120);
	        gp.quadTo(px, py-94, px+20, py-120);
	        gp.quadTo(px, py-80, px-20, py-120);
	        g.setColor(Color.YELLOW);
	        g.fill(gp);
	        g.setStroke(new BasicStroke(1));
	        g.setColor(Color.BLACK);
	        g.draw(gp);
	    	
	    }
	
	

}
