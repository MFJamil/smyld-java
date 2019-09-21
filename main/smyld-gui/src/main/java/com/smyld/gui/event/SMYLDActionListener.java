package com.smyld.gui.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import com.smyld.SMYLDObject;
import com.smyld.gui.GUIAction;

public class SMYLDActionListener extends SMYLDObject implements ActionListener,
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ActionHandler handler;
	GUIAction action;

	public SMYLDActionListener(ActionHandler Handler, GUIAction Action) {
		action = Action;
		handler = Handler;
	}

	public void actionPerformed(ActionEvent evt) {
		if (handler!=null)
			handler.processGUIAction(action);
	}
}
