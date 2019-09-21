package org.smyld.gui;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import org.smyld.gui.event.ActionHandler;

public class SMYLDMenuItem extends JMenuItem implements SMYLDMenuClass {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SMYLDMenuObject menuObject;
	GUIAction menuAction;

	public SMYLDMenuItem(String title, String menuID) {
		super(title);
		menuObject = new SMYLDMenuObject(this, menuID);

	}

	public SMYLDMenuItem(GUIAction newMenuAction, String menuID) {
		this(newMenuAction.getLabel(), menuID);
		menuObject.setGUIAction(newMenuAction);
	}

	public SMYLDMenuItem(String title, String menuID, String Status,
			String shortKey) {
		super(title);
		menuObject = new SMYLDMenuObject(this, menuID, Status, shortKey);
	}

	public String getMenuID() {
		if (menuObject != null) {
			return menuObject.getID();
		}
		return null;
	}

	public void setGUIAction(GUIAction newAction) {
		menuObject.setGUIAction(newAction);
	}

	public GUIAction getGUIAction() {
		return menuObject.getGUIAction();
	}

	public void setAccelerator(String newAccelerator) {
		menuObject.setAccelerator(newAccelerator);
	}

	public void setActionListener(ActionHandler Handler) {
		menuObject.setActionListener(Handler);
	}

	public JMenuItem getParentClass() {
		return menuObject.getParentClass();
	}

	public void setTooltipText(String tooltipText) {
		menuObject.setTooltipText(tooltipText);
	}

	public void setToolbarIcon(ImageIcon toolbarIcon) {
		menuObject.setToolbarIcon(toolbarIcon);
	}

	public SMYLDButton getToolbarButton() {
		return menuObject.getToolbarButton();
	}

	public void setToolbarButton(String lable, ImageIcon toolbarIcon) {
		menuObject.setToolbarButton(lable, toolbarIcon);
	}

	public void setIcon(ImageIcon icon) {
		super.setIcon(icon);
		
	}

	public void add(SMYLDMenuClass child) {
		// TODO Auto-generated method stub
		
	}
}
