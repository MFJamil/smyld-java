package org.smyld.gui;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Window;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class SMYLDDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3689874490753827161L;
	protected HashMap<String, Component> compRef;

	public SMYLDDialog() {
		super();
	}

	public SMYLDDialog(Frame owner) {
		super(owner);
	}
	public SMYLDDialog(Window owner) {
		super(owner);
	}

	public void centerWindow() {
		SMYLDFrame.centerComponentInScreen(this);

	}

	public void setIcon(ImageIcon newIcon) {
		// Nothing will be done here because the parent class does not have this
		// utility

		 if (newIcon!=null)
			 setIconImage(newIcon.getImage());

	}

	public void centerWindowInScreen() {
		SMYLDFrame.centerComponentInScreen(this);
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
