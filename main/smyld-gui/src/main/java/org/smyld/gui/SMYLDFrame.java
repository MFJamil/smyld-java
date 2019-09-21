package org.smyld.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.smyld.gui.event.ActionHandler;

public class SMYLDFrame extends JFrame implements ActionHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1685744892361638762L;
	protected BorderLayout layoutMain;
	protected HashMap<String, Component> compRef;
	private   SMYLDToolbar toolbar;
	public SMYLDFrame() {
		super();
		doInit();
		// closeWindow();
	}

	public SMYLDFrame(String title) {
		super(title);
		doInit();
		// closeWindow();
	}

	public void doInit() {
		setDefaultLookAndFeelDecorated(true);
	}

	public void centerWindowInScreen() {
		centerComponentInScreen(this);
	}

	public static void centerComponentInScreen(Component component) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = component.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		component.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
	}

	public void setToMaximum() {
		maximizeFrame();
		setLocation(0, 0);
	}
	public void maximizeWidth(int height){
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,height);
	}
	public void maximizeHeight(int width){
		this.setSize(width,Toolkit.getDefaultToolkit().getScreenSize().height);
	}

	public void maximizeFrame() {
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
	}

	public void setBGColor(String newColor) {
		try {
			setBackground(Color.decode(newColor));
		} catch (Exception e) {
			e.printStackTrace();
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

	/**
	 * Override this method in the sub classes to respond to the window closing
	 * Default: exits the application
	 */
	public void close() {
		System.exit(-1);
	}

	@SuppressWarnings("unused")
	private void closeWindow() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				close();
			}
		});
	}

	public void setIcon(ImageIcon newIcon) {
		if (newIcon != null) {
			setIconImage(newIcon.getImage());
		}
	}

	public void setToolbar(SMYLDToolbar newToolbar) {
		setBorderLayout();
		toolbar = newToolbar;
		this.getContentPane().add(newToolbar, BorderLayout.NORTH);
	}
	public SMYLDToolbar getToolbar(){
		return toolbar;
	}

	public void setStatusBar(SMYLDPanel statusPanel) {
		setBorderLayout();
		this.getContentPane().add(statusPanel, BorderLayout.SOUTH);
	}

	protected void setBorderLayout() {
		if (this.getContentPane().getLayout() == null) {
			layoutMain = new BorderLayout();
			this.getContentPane().setLayout(layoutMain);
		}

	}

	public void processGUIAction(GUIAction incomingAction) {

	}

}
