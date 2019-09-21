package org.smyld.gui;

import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.smyld.SMYLDObject;
import org.smyld.gui.event.ActionHandler;
import org.smyld.gui.event.SMYLDActionListener;

public class SMYLDMenuObject extends SMYLDObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ID;
	String status;
	GUIAction action;
	ActionHandler handler;
	JMenuItem parentClass;
	SMYLDButton toolbarButton;
	SMYLDActionListener smyldActionListener;

	public SMYLDMenuObject(JMenuItem ParentClass, String menuID) {
		parentClass = ParentClass;
		ID = menuID;
	}

	public SMYLDMenuObject(JMenuItem ParentClass, String menuID, String Status) {
		this(ParentClass, menuID);
		status = Status;
		if (status != null) {
			if (status.equals(STATUS_DISABLED)) {
				parentClass.setEnabled(false);
			}
		}
	}

	public SMYLDMenuObject(JMenuItem ParentClass, String menuID, String Status,
			String shortKey) {
		this(ParentClass, menuID, Status);
		if (shortKey != null) {
			setAccelerator(shortKey);
		}
	}

	public void setActionListener(ActionHandler Handler) {
		handler = Handler;
		processActionHandler();
	}

	private void processActionHandler() {
		boolean wasSet = smyldActionListener != null;
		if (wasSet) {
			parentClass.removeActionListener(smyldActionListener);
			if (toolbarButton != null) {
				toolbarButton.removeActionListener(smyldActionListener);
			}
		}
		smyldActionListener = new SMYLDActionListener(handler, action);
		parentClass.addActionListener(smyldActionListener);
		if (toolbarButton != null) {
			toolbarButton.addActionListener(smyldActionListener);
		}
	}

	public JMenuItem getParentClass() {
		return parentClass;
	}

	public SMYLDButton getToolbarButton() {
		return toolbarButton;
	}

	public void setToolbarIcon(ImageIcon toolbarIcon) {
		toolbarButton = new SMYLDButton(toolbarIcon);
		toolbarButton.setEnabled(parentClass.isEnabled());
	}

	public void setToolbarButton(String lable, ImageIcon toolbarIcon) {
		setToolbarIcon(toolbarIcon);
		toolbarButton.setText(lable);
	}

	public GUIAction getGUIAction() {
		return action;
	}

	public void setGUIAction(GUIAction newAction) {
		action = newAction;
		if (handler != null) {
			processActionHandler();
		}
	}

	public void setAccelerator(String newAccelerator) {
		KeyStroke shortKey = KeyStroke
				.getKeyStroke(validateKey(newAccelerator));
		if (shortKey != null) {
			parentClass.setAccelerator(shortKey);
		}
	}

	private String validateKey(String orgKey) {
		if ((orgKey == null) || (orgKey.length() == 0)) {
			return null;
		}
		orgKey = orgKey.replace('+', ' ');
		orgKey = orgKey.replaceAll("Ctrl", "ctrl");
		return orgKey;
	}

	public void setTooltipText(String tooltipText) {
		parentClass.setToolTipText(tooltipText);
		if (toolbarButton != null) {
			toolbarButton.setToolTipText(tooltipText);
		}
	}

	public String getID() {
		return ID;
	}

	public static final String STATUS_DISABLED = "Disabled";

}
