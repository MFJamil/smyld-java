package com.smyld.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.Popup;

public class SMYLDPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Insets borders;
	public SMYLDPanel() {
		// setLayout(null);
		// borders = getInsets();
	}

	/*
	 * public void addComponent(Component newComponent,int x,int y,int width,int
	 * height){ add(newComponent); newComponent.setBounds(borders.left +
	 * x,borders.top + y,width,height); } public void repaint(){ //borders =
	 * getInsets(); super.repaint(); }
	 */
	public void setBGColor(String newColor) {
		try {
			setBackground(Color.decode(newColor));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Component getPanelWindow() {
		return getPanelWindow(this);
	}

	public Component getPanelWindow(Component child) {
		Component parent = child.getParent();
		if ((parent != null)
				&& ((parent instanceof JFrame)
						|| (parent instanceof JInternalFrame) || (parent instanceof JDialog))) {
			return parent;
		} else if (parent != null) {
			return getPanelWindow(parent);
		} else {
			return null;
		}
	}

	public void setToolTip(JComponent comp) {
		SMYLDToolTipManager manager = SMYLDToolTipManager.sharedInstance();
		manager.setTipComponent(comp);
		// manager.setLightWeightPopupEnabled(true);
		manager.setEnabled(true);
		manager.registerComponent(this);
		// manager.registerComponent(comp);
		/*
		 * PopupFactory popupFactory = PopupFactory.getSharedInstance();
		 * popupFactory.setPopupType(PopupFactory.MEDIUM_WEIGHT_POPUP);
		 * tipWindow = popupFactory.getPopup(insideComponent, tip, location.x,
		 * location.y);
		 * popupFactory.setPopupType(PopupFactory.LIGHT_WEIGHT_POPUP);
		 * 
		 * tipWindow.show();
		 * 
		 * Window componentWindow = SwingUtilities.windowForComponent(
		 * insideComponent);
		 * 
		 * window = SwingUtilities.windowForComponent(tip); if (window != null &&
		 * window != componentWindow) { window.addMouseListener(this); } else {
		 * window = null; }
		 * 
		 * insideTimer.start(); tipShowing = true;
		 * 
		 * ToolTipManager toolTipManager = ToolTipManager.sharedInstance(); if
		 * (comp != null) { toolTipManager.setDismissDelay(100);
		 * toolTipManager.setReshowDelay(100); toolTipManager.setEnabled(true);
		 * toolTipManager.setLightWeightPopupEnabled(true);
		 * toolTipManager.registerComponent(comp); }
		 * //toolTipManager.unregisterComponent(this);
		 */
	}

	transient Popup tipWindow;
}
