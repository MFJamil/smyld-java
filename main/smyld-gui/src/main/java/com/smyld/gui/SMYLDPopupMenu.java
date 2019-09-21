package com.smyld.gui;

import java.util.HashMap;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.smyld.gui.event.ActionHandler;

public class SMYLDPopupMenu extends JPopupMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7940223701876952756L;
	HashMap<String, Object> items = new HashMap<String, Object>();
	ActionHandler actionHandler;
	String ID;

	public SMYLDPopupMenu() {
	}

	public SMYLDPopupMenu(String popupID, String label,
			ActionHandler menuActionHandler) {
		super(label);
		ID = popupID;
		actionHandler = menuActionHandler;
	}

	public void add(JMenuItem newMenu, String itemID) {
		if (newMenu==null) return;
		super.add(newMenu);
		addObjectToList(newMenu, itemID);
	}
	public void add(String itemID,SMYLDMenuClass newMenu) {
		if (newMenu==null) return;
		super.add((JMenuItem)newMenu);
		addObjectToList(newMenu, itemID);
	}

	public void addObjectToList(Object newItem, String itemID) {
		// SMYLDMenuClass newMenuItem = (SMYLDMenuClass) newItem;
		items.put(itemID, newItem);
	}

	public JMenu getMenu(String menuID) {
		return (JMenu) items.get(menuID);
	}
	public HashMap<String, Object> getItems(){
		return items;
	}

	public JMenuItem getMenuItem(String itemID) {
		return (JMenuItem) items.get(itemID);
	}

	public String getID() {
		return ID;
	}

}
