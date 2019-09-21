package org.smyld.app.pe.model.gui;

import java.util.ArrayList;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class MenuItem extends ItemsHolder {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String accelerator;
	String popupMenu;
	int usage;
	MenuType menuType;

	/**
	 * 
	 * @see
	 * @since
	 */
	public MenuItem() {
	}

	public void addChild(MenuItem newChild) {
		if (children == null) {
			children = new ArrayList<GUIComponent>();
		}
		children.add(newChild);
	}

	public String getAccelerator() {
		return accelerator;
	}

	public void setAccelerator(String accelerator) {
		this.accelerator = accelerator;
	}

	public String getPopupMenu() {
		return popupMenu;
	}

	public void setPopupMenu(String popupMenu) {
		this.popupMenu = popupMenu;
	}

	public int getUsage() {
		return usage;
	}

	public void setUsage(int usage) {
		this.usage = usage;
	}

	public MenuType getMenuType() {
		return menuType;
	}

	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}
	

}
