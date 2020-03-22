package org.smyld.app.pe.model.gui;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
@Slf4j
@Getter
@Setter
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



}
