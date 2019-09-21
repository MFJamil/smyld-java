package com.smyld.gui;

import com.smyld.gui.event.ActionHandler;

public interface ActionHandle {
	public GUIAction getGUIAction();

	public void setActionListener(ActionHandler Handler);

}
