package com.smyld.gui;

import java.awt.ComponentOrientation;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import com.smyld.gui.event.ActionHandler;

public interface SMYLDMenuClass extends ActionHandle {
	public void setAccelerator(String newAccelerator);

	public JMenuItem getParentClass();

	public void setTooltipText(String tooltipText);

	public void setToolbarIcon(ImageIcon toolbarIcon);
	public void setIcon(ImageIcon icon);
	
	public void setText(String text);
	public String getText();
	public void applyComponentOrientation(ComponentOrientation o);
	public void setEnabled(boolean enabled);
	public void setActionListener(ActionHandler Handler);
	
	public void setToolbarButton(String lable, ImageIcon toolbarIcon);

	public SMYLDButton getToolbarButton();

	public void setGUIAction(GUIAction newAction);

	public GUIAction getGUIAction();


}
