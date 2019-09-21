package com.smyld.gui.event;

import java.io.Serializable;

import com.smyld.gui.GUIAction;

public interface ActionHandler extends Serializable {

	// public void processAction(String incomingAction);
	public void processGUIAction(GUIAction incomingAction);
}
