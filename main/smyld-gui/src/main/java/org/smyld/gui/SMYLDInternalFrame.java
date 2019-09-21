package org.smyld.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

public class SMYLDInternalFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6062966391411324132L;
	protected BorderLayout layoutMain;
	protected HashMap<String, Component> compRef;
	SMYLDPanel   statusPanel;
	SMYLDToolbar toolbar;
	public SMYLDInternalFrame() {
		super();
		setDefaultApprearance();
	}

	public SMYLDInternalFrame(String Title) {
		super(Title);
		setDefaultApprearance();
	}

	public SMYLDInternalFrame(String Title, boolean Resizeable) {
		super(Title, Resizeable);
	}

	public SMYLDInternalFrame(String Title, boolean Resizeable, boolean Closeable) {
		super(Title, Resizeable, Closeable);
	}

	public SMYLDInternalFrame(String Title, boolean Resizeable,
			boolean Closeable, boolean Maximizable) {

		super(Title, Resizeable, Closeable, Maximizable);
	}

	public SMYLDInternalFrame(String Title, boolean Resizeable,
			boolean Closeable, boolean Maximizable, boolean Iconifiable) {
		super(Title, Resizeable, Closeable, Maximizable, Iconifiable);
	}

	private void setDefaultApprearance() {
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setResizable(true);

	}

	public void setIcon(ImageIcon newIcon) {
		setFrameIcon(newIcon);
	}

	public ImageIcon getIcon() {
		return (ImageIcon) getFrameIcon();
	}

	public void setBGColor(String newColor) {
		try {
			setBackground(Color.decode(newColor));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setToolbar(SMYLDToolbar newToolbar) {
		setBorderLayout();
		this.toolbar = newToolbar;
		this.getContentPane().add(newToolbar, BorderLayout.NORTH);
	}
	public SMYLDToolbar getToolbar(){
		return toolbar;
	}

	public void setStatusBar(SMYLDPanel statusPanel) {
		setBorderLayout();
		this.statusPanel = statusPanel;
		// this.getContentPane().add(statusPanel,BorderLayout.SOUTH);
	}

	public SMYLDPanel getStatusBar() {
		return statusPanel;
	}

	protected void setBorderLayout() {
		if (this.getContentPane().getLayout() == null) {
			layoutMain = new BorderLayout();
			this.getContentPane().setLayout(layoutMain);
		}

	}

	public void addComponent(String refName, Component newComp) {
		addNewComponentToList(refName, newComp);
		this.getContentPane().add(newComp);
	}

	protected void addNewComponentToList(String refName, Component newComp) {
		if (compRef == null) {
			compRef = new HashMap<String, Component>();
		}
		compRef.put(refName, newComp);
	}

	public Component getComponent(String compID) {
		return (compRef.get(compID));
	}

}
