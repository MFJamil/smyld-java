package org.smyld.app.pe.model.gui;

import java.util.ArrayList;

public class ItemsHolder extends ActionHolderItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ArrayList<GUIComponent> children;

	/**
	 * 
	 * @see
	 * @since
	 */
	public ItemsHolder() {
	}

	@Override
	public ArrayList<GUIComponent> getChildren() {
		return children;
	}

	public void addChild(ActionHolderItem newChild) {
		if (children == null) {
			children = new ArrayList<GUIComponent>();
		}
		children.add(newChild);
	}

	public boolean hasChildren() {
		return ((children != null) && (children.size() > 0));
	}

}
