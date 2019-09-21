package com.smyld.test;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.smyld.SMYLDObject;

public class TestPCControlClient extends SMYLDObject implements
		PCControlerListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel tPanel;
	JFrame tFrame;
	BufferedImage lastShot;
	PCViewer viewer;

	public TestPCControlClient() {
		init();
	}

	private void init() {
		try {
			viewer = new PCViewer();
			viewer.addPCControlViewer(this);
		} catch (AWTException e) {
			e.printStackTrace();
		}

		tFrame = new JFrame("PC Desktop");
		tFrame.setSize(600, 400);
		tPanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				// System.out.println("Repainting the panel");
				if (lastShot != null) {
					Graphics2D g2d = (Graphics2D) g;
					g2d.drawImage(lastShot, null, 0, 0);
				}
			}
		};

		tFrame.getContentPane().add(tPanel);
		viewer.startView();
		tFrame.setVisible(true);
	}

	public int getCaptureInterval() {
		return 20;
	}

	public void newShot(BufferedImage shot) {
		// System.out.println("new shot ...");
		lastShot = shot;
		tPanel.repaint();

		tFrame.repaint();

	}
}
