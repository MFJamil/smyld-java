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


	@Override
	public String toString(){
		// TODO we need to recursively navigate through the menus in the future
		StringBuffer sb = new StringBuffer();
		addToObjectInfo("Menu ID        ", getID(),sb);
		addToObjectInfo("Menu Label     ", getLabel(),sb);
		addToObjectInfo("Menu Icon      ", getIcon(),sb);
		if (getAction()!=null)
			addToObjectInfo("Menu Action    ", getAction().getID(),sb);
		if (hasChildren()){
			getChildren().forEach(comp ->{
				addToObjectInfo("Menu ID        ", comp.getID(),sb);
				addToObjectInfo("Menu Label     ", comp.getLabel(),sb);
				addToObjectInfo("Menu Icon      ", comp.getIcon(),sb);
			});
		}


		return sb.toString();
	}
}
