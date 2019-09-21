package org.smyld.gui;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.smyld.gui.event.ActionHandler;

public class SMYLDMenu extends JMenu implements SMYLDMenuClass {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3546521889681249164L;
	SMYLDMenuObject menuObject;
	GUIAction menuAction;
	HashMap<String, JMenuItem> menuItems;

	public SMYLDMenu(String title, String menuID) {
		super(title);
		menuObject = new SMYLDMenuObject(this, menuID);
	}

	public SMYLDMenu(GUIAction newMenuAction, String menuID) {
		this(newMenuAction.getLabel(), menuID);
		menuObject.setGUIAction(newMenuAction);
	}

	public SMYLDMenu(String title, String menuID, String Status) {
		super(title);

		menuObject = new SMYLDMenuObject(this, menuID, Status);

	}

	public HashMap<String, JMenuItem> getMenuItems() {
		return menuItems;
	}

	public void add(SMYLDMenuItem menuItem) {
		if (menuItem==null) return;
		if (menuItems == null) {
			menuItems = new HashMap<String, JMenuItem>();
		}
		super.add(menuItem);
		String id = menuItem.getMenuID();
		if (id != null) {
			menuItems.put(id, menuItem);
		}

	}
	public void add(SMYLDMenuClass mnuClass) {
		if (mnuClass==null) return;
		if (mnuClass instanceof SMYLDMenu){
			add((SMYLDMenu)mnuClass);
		}else if(mnuClass instanceof SMYLDMenuItem){
			add((SMYLDMenuItem)mnuClass);
		}
			
	}

	public void add(JComponent comp) {
		if (comp==null) return;
		if (comp instanceof SMYLDMenu){
			add((SMYLDMenu)comp);
		}else if(comp instanceof SMYLDMenuItem){
			add((SMYLDMenuItem)comp);
		}
			
	}
	public Component add(Component comp) {
		if (comp==null) return null;
		if (comp instanceof SMYLDMenu){
			add((SMYLDMenu)comp);
		}else if(comp instanceof SMYLDMenuItem){
			add((SMYLDMenuItem)comp);
		}
		return comp;
			
	}

	public void add(JComponent comp, Object obj) {
		if (comp==null) return;
		if (comp instanceof SMYLDMenu){
			add((SMYLDMenu)comp,(String)obj);
		}else if(comp instanceof SMYLDMenuItem){
			add((SMYLDMenuItem)comp,(String)obj);
		}
			
	}


	public void add(SMYLDMenu menuItem) {
		if (menuItem==null) return;
		if (menuItems == null) {
			menuItems = new HashMap<String, JMenuItem>();
		}
		super.add(menuItem);
		String id = menuItem.getMenuID();
		if (id != null) {
			menuItems.put(id, menuItem);
		}

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
}
