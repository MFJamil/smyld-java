package com.smyld.gui;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class SMYLDSplashScreen extends JWindow implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Frame     owner;
	JPanel    contents;
	ImageIcon image;
	int       showTime;

	public SMYLDSplashScreen() {
		init();
	}

	public SMYLDSplashScreen(ImageIcon splashImage) {
		image = splashImage;
		init();
	}

	public SMYLDSplashScreen(ImageIcon splashImage, int showTime) {
		this.showTime = showTime;
		image = splashImage;
		init();
	}

	public SMYLDSplashScreen(Frame ownerFrame) {
		super(ownerFrame);
		owner = ownerFrame;
		init();
	}

	public SMYLDSplashScreen(Frame ownerFrame, int showTime) {
		super(ownerFrame);
		this.showTime = showTime;
		owner = ownerFrame;
		init();
	}

	public SMYLDSplashScreen(JPanel contentsPanel) {
		contents = contentsPanel;
		init();
	}

	public SMYLDSplashScreen(JPanel contentsPanel, int showTime) {
		this.showTime = showTime;
		contents = contentsPanel;
		init();
	}

	private void init() {
		if (contents != null) {
			getContentPane().add(contents, BorderLayout.CENTER);
		} else if (image != null) {
			getContentPane().add(new JLabel(image), BorderLayout.CENTER);
		}

		pack();
		if (owner == null) {
			SMYLDFrame.centerComponentInScreen(this);
		}
		setVisible(true);
		if (showTime != 0) {
			processTime();
		}

	}

	private void processTime() {
		try {
			Thread.sleep(showTime * 1000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		dispose();

	}

	public void run() {
		setVisible(true);
		if (showTime != 0) {
			processTime();
		}

	}

}
