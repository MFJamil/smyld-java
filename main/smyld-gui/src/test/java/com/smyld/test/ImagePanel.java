package com.smyld.test;



import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ImagePanel extends JPanel {
	private final class MouseHandler implements MouseListener,
			MouseMotionListener {
		int pressX, pressY;

		public void mousePressed(MouseEvent e) {
			Point dragWindowOffset = e.getPoint();
			Component source = (Component) e.getSource();

			Point convertedDragWindowOffset = SwingUtilities.convertPoint(
					source, dragWindowOffset, ImagePanel.this);

			Window w = SwingUtilities.getWindowAncestor(ImagePanel.this);
			dragWindowOffset = SwingUtilities.convertPoint(source,
					dragWindowOffset, w);

			pressX = dragWindowOffset.x;
			pressY = dragWindowOffset.y;
		}

		public void mouseReleased(MouseEvent e) {
			pressX = -1;
			pressY = -1;
		}

		public void mouseMoved(MouseEvent e) {
			//System.out.println("Mouse moved");
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseDragged(MouseEvent e) {
			//System.out.println("Mouse dragged");
			Point loc = e.getLocationOnScreen();
			int dx = loc.x - pressX;
			int dy = loc.y - pressY;

			Window w = SwingUtilities.getWindowAncestor(ImagePanel.this);
			w.setLocation(dx, dy);
		}
	}

	BufferedImage avatar;

	public ImagePanel(BufferedImage avatar) {
		this.avatar = avatar;
		MouseHandler mHandler = new MouseHandler();
		this.addMouseListener(mHandler);
		this.addMouseMotionListener(mHandler);
	}

	public void setAvatar(BufferedImage avatar) {
		this.avatar = avatar;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();

		// code from
		// http://weblogs.java.net/blog/campbell/archive/2006/07/java_2d_tricker.html
		int avatarWidth = avatar.getWidth();
		int avatarHeight = avatar.getHeight();

		int myWidth = getWidth();
		int myHeight = getHeight();

		GraphicsConfiguration gc = g2d.getDeviceConfiguration();
		BufferedImage img = gc.createCompatibleImage(avatarWidth, avatarHeight,
				Transparency.TRANSLUCENT);
		Graphics2D g2 = img.createGraphics();

		g2.setComposite(AlphaComposite.Clear);
		g2.fillRect(0, 0, avatarWidth, avatarHeight);

		g2.setComposite(AlphaComposite.Src);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fillRoundRect(0, 0, avatarWidth, avatarHeight + 10, 10, 10);

		g2.setComposite(AlphaComposite.SrcAtop);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(avatar, 0, 0, null);
		g2.dispose();

		// at this point the 'img' contains a soft
		// clipped round rectangle with the avatar

		// do the reflection with the code from
		// http://www.jroller.com/gfx/entry/swing_glint

		BufferedImage reflection = new BufferedImage(avatarWidth, avatarHeight,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D reflectionGraphics = reflection.createGraphics();

		AffineTransform tranform = AffineTransform.getScaleInstance(1.0, -1.0);
		tranform.translate(0, -avatarHeight);
		reflectionGraphics.drawImage(img, tranform, this);

		GradientPaint painter = new GradientPaint(0.0f, 0.0f, new Color(0.0f,
				0.0f, 0.0f, 0.7f), 0.0f, avatarHeight / 2.0f, new Color(0.0f,
				0.0f, 0.0f, 1.0f));

		reflectionGraphics.setComposite(AlphaComposite.DstOut);
		reflectionGraphics.setPaint(painter);
		reflectionGraphics.fill(new Rectangle2D.Double(0, 0, avatarWidth,
				avatarHeight));

		reflectionGraphics.dispose();

		g2d.drawImage(img, 0, 0, this);
		g2d.drawImage(reflection, 0, avatarHeight, this);

		g2d.dispose();
	}
}
