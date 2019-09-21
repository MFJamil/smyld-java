package org.smyld.gui;

import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import org.smyld.gui.event.ActionHandler;

public class SMYLDMenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9169759753603859434L;
	HashMap<String, Object> items = new HashMap<String, Object>();
	SMYLDToolbar toolBar = new SMYLDToolbar();
	ActionHandler handler;

	public SMYLDMenuBar() {

	}

	public SMYLDMenuBar(ActionHandler newActionHandler) {
		handler = newActionHandler;
	}

	public void add(SMYLDMenu newMenu, String itemID) {
		super.add(newMenu);
		if (handler != null) {
			((SMYLDMenuClass) newMenu).setActionListener(handler);
		}
		if ((newMenu.getMenuItems() != null)
				&& (newMenu.getMenuItems().size() > 0)) {
			for (JMenuItem curObj : newMenu.getMenuItems().values()) {
				if (curObj instanceof SMYLDMenuItem) {
					SMYLDMenuItem curMenuItem = (SMYLDMenuItem) curObj;
					items.put(curMenuItem.getMenuID(), curMenuItem);
				} else if (curObj instanceof SMYLDMenu) {
					SMYLDMenu curMenuItem = (SMYLDMenu) curObj;
					items.put(curMenuItem.getMenuID(), curMenuItem);
				}
			}
		}
		addObjectToList(newMenu, itemID);
	}

	public void add(SMYLDMenu newMenu) {
		if (newMenu==null) return;
		this.add(newMenu, newMenu.getMenuID());
	}
	public void add(JComponent comp) {
		if (comp==null) return;
		if (comp instanceof SMYLDMenu){
			add((SMYLDMenu)comp);
		}else if(comp instanceof SMYLDMenuItem){
			add((SMYLDMenuItem)comp);
		}
			
	}

	public void add(JComponent comp, Object obj) {
		if (comp instanceof SMYLDMenu){
			add((SMYLDMenu)comp,(String)obj);
		}else if(comp instanceof SMYLDMenuItem){
			add((SMYLDMenuItem)comp,(String)obj);
		}
			
	}
	public void add(SMYLDMenuClass mnuClass) {
		if (mnuClass==null) return;
		if (mnuClass instanceof SMYLDMenu){
			add((SMYLDMenu)mnuClass,((SMYLDMenu)mnuClass).getMenuID());
		}else if(mnuClass instanceof SMYLDMenuItem){
			add((SMYLDMenuItem)mnuClass,((SMYLDMenuItem)mnuClass).getMenuID());
		}
			
	}
	public void add(SMYLDMenuClass mnuClass,String id) {
		if (mnuClass==null) return;
		if (mnuClass instanceof SMYLDMenu){
			add((SMYLDMenu)mnuClass,id);
		}else if(mnuClass instanceof SMYLDMenuItem){
			add((SMYLDMenuItem)mnuClass,id);
		}
			
	}
	
	public void add(SMYLDMenuItem newMenu) {
		if (newMenu==null) return;
		add(newMenu,newMenu.getMenuID());
	}

	public void add(SMYLDMenuItem newMenu, String itemID) {
		if (newMenu==null) return;
		super.add(newMenu);
		if (handler != null) {
			newMenu.setActionListener(handler);
		}
		addObjectToList(newMenu, itemID);
	}

	public void add(JMenuItem newMenu, String itemID) {
		if (newMenu==null) return;
		super.add(newMenu);
		if (handler != null) {
			((SMYLDMenuClass) newMenu).setActionListener(handler);
		}
		addObjectToList(newMenu, itemID);
	}

	public void addObjectToList(Object newItem, String itemID) {
		SMYLDMenuClass newMenuItem = (SMYLDMenuClass) newItem;
		if (newMenuItem != null) {
			if (newMenuItem.getToolbarButton() != null) {
				toolBar.add(newMenuItem.getToolbarButton());
			}
		}
		items.put(itemID, newItem);
	}

	public void addSeparator() {
		// doing nothing because this is a menu bar
	}

	public JMenu getMenu(String menuID) {
		return (JMenu) items.get(menuID);
	}

	public JToolBar getToolbar() {
		return toolBar;
	}

	public JMenuItem getMenuItem(String itemID) {
		return (JMenuItem) items.get(itemID);
	}

}
