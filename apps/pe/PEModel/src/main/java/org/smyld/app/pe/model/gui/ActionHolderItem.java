package org.smyld.app.pe.model.gui;

import org.smyld.gui.GUIAction;

public class ActionHolderItem extends GUIComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GUIAction menuAction;
	String type;

	public ActionHolderItem() {

	}

	public GUIAction getAction() {
		return menuAction;
	}

	public void setAction(GUIAction menuAction) {
		this.menuAction = menuAction;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

}
