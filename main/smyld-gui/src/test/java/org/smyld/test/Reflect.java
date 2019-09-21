package org.smyld.test;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Reflect extends JComponent {

    private BufferedImage image;

    public Reflect() {
        try {
            image = ImageIO.read( new File( "//home/jamil/downloads/images/verein_logo.png" ) );
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    
    public void paintComponent( Graphics g ) {
        Graphics2D g2d = (Graphics2D)g;
        int width = getWidth();
        int height = getHeight();
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int gap = 20;
        float opacity = 0.2f;
        float fadeHeight = 0.6f;

        g2d.setPaint( new GradientPaint( 0, 0, Color.black, 0, height, Color.darkGray ) );
        g2d.fillRect( 0, 0, width, height );
        g2d.translate( (width-imageWidth)/2, height/2-imageHeight );
//        g2d.translate( (width-imageWidth)/2, imageHeight );
//        g2d.scale( 1, -1 );
        g2d.drawRenderedImage( image, null );
        g2d.translate( 0, 2*imageHeight+gap );
        g2d.scale( 1, -1 );

        BufferedImage reflection = new BufferedImage( imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB );
		Graphics2D rg = reflection.createGraphics();
        rg.drawRenderedImage( image, null );
		rg.setComposite( AlphaComposite.getInstance( AlphaComposite.DST_IN ) );
        rg.setPaint( 
            new GradientPaint( 
                0, imageHeight*fadeHeight, new Color( 0.0f, 0.0f, 0.0f, 0.0f ),
                0, imageHeight, new Color( 0.0f, 0.0f, 0.0f, opacity )
            )
        );
        rg.fillRect( 0, 0, imageWidth, imageHeight );
        rg.dispose();
        g2d.drawRenderedImage( reflection, null );
    }
    
    public Dimension getPreferredSize() {
        return new Dimension( 500, 500 );
    }
    
    public static void main (String args[]) {
        JFrame f = new JFrame();
        Reflect r = new Reflect();
        f.getContentPane().add( r );
        f.pack();
        f.setVisible(true);
    }
    /*
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
 
     */

    
}

