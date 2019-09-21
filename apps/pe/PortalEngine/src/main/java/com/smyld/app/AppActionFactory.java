package com.smyld.app;

import java.util.HashMap;

import com.smyld.gui.Actions;
import com.smyld.gui.GUIAction;
import com.smyld.gui.event.ActionHandler;
import com.smyld.app.pe.model.gui.PEAction;

public class AppActionFactory extends AppBaseFactory {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ActionHandler actionHandler;
	protected Actions actions;
	protected HashMap<String,PEAction> pactions = new HashMap<String,PEAction>();
	 

	public AppActionFactory(ActionHandler newActionHandler) {
		actionHandler = newActionHandler;
		creatActions();
	}

	protected void creatActions() {
	}

	public ActionHandler getHandler() {

		return actionHandler;
	}
	

	public PEAction getAction(String actionID) {
		return pactions.get(actionID);
	}
	
	public boolean canUse(GUIAction action){
		return canUse(action.getID());
	}
	public boolean canView(GUIAction action){
		return canView(action.getID());
	}

}
