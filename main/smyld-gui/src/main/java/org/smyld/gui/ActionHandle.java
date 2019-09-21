package org.smyld.gui;

import org.smyld.gui.event.ActionHandler;

public interface ActionHandle {
	public GUIAction getGUIAction();

	public void setActionListener(ActionHandler Handler);

}
