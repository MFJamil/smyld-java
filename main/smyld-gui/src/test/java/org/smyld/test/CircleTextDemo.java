package org.smyld.test;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

    public class CircleTextDemo extends Canvas {
    Frame myframe;
    TextField text;
    Font myfont;
    Color textcolor;
    Color circlecolor;
    
        public CircleTextDemo() {
        this("Serif", Font.PLAIN, 18, Color.pink, Color.black);
    }
    
        public CircleTextDemo(String ff, int fs, int fz, Color bg, Color fg) {
        setBackground(bg);
        circlecolor = bg.brighter();
        textcolor = fg;
        myfont = new Font(ff, fs, fz);
        
        text = new TextField("Text on a circle using Java 2D Graphics!");
        myframe = new Frame("CircleTextDemo");
        myframe.add(text, BorderLayout.NORTH);
        myframe.add(this, BorderLayout.CENTER);
        myframe.setSize(new Dimension(300,340));
        myframe.setLocation(150,140);
            myframe.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
            text.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                repaint();
            }
        });
        myframe.setVisible(true);
    }
    
        public void paint(Graphics g) {
        String st = text.getText();
        if (st.length() == 0) return;
            if (g instanceof Graphics2D) {
            Dimension cd = getSize();
            Point pt = new Point(cd.width / 2, cd.height / 2);
            int radius = (int)(pt.x * 0.84);
            g.setColor(circlecolor);
            g.drawArc(pt.x - radius, pt.y - radius, 
            radius*2-1, radius*2-1, 
            0, 360);
            g.setColor(textcolor);
            g.setFont(myfont);
            drawCircleText((Graphics2D)g, st, pt, radius, -Math.PI/2, 1.0);
        }
            else {
            System.out.println("Cannot draw curved text without a Graphics2D");
        }
    }
    
    /**
    * Draw a piece of text on a circular curve, one
    * character at a time. This is harder than it looks.
    */
    static void drawCircleText(Graphics2D g, String st, Point center,
    double r, double a1, double af)
        {
        double curangle = a1;
        Point2D c = new Point2D.Double(center.x, center.y);
        char ch[] = st.toCharArray();
        FontMetrics fm = g.getFontMetrics();
        AffineTransform xform1, cxform;
        xform1 = AffineTransform.getTranslateInstance(c.getX(),c.getY());
            for(int i = 0; i < ch.length; i++) {
            double cwid = (double)(getWidth(ch[i],fm));
                if (!(ch[i] == ' ' || Character.isSpaceChar(ch[i]))) {
                cwid = (double)(fm.charWidth(ch[i]));
                cxform = new AffineTransform(xform1);
                cxform.rotate(curangle, 0.0, 0.0);
                String chstr = new String(ch, i, 1);
                g.setTransform(cxform);
                g.drawString(chstr, (float)(-cwid/2), (float)(-r));
            }
            
            // compute advance of angle assuming cwid<
                if (i < (ch.length - 1)) {
                double adv = cwid/2.0 + fm.getLeading() + getWidth(ch[i + 1],fm)/2.0;
                curangle += Math.sin(adv / r);
            }
        }
    }
    
        static int getWidth(char c, FontMetrics fm) {
            if (c == ' ' || Character.isSpaceChar(c)) {
            return fm.charWidth('n');
        }
            else {
            return fm.charWidth(c);
        }
    }
    
        public static void main(String args[]) {
        CircleTextDemo ctd;
        ctd = new CircleTextDemo();
    }
}
