package org.smyld.gui;

import java.util.HashMap;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class Actions extends HashMap<String, GUIAction> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1819544549568174191L;

	/**
	 * 
	 * @see
	 * @since
	 */
	public Actions() {
	}

	public void addAction(GUIAction newAction) {
		put(newAction.getID(), newAction);
	}

	public boolean isActionExist(GUIAction targetAction) {
		return isActionExist(targetAction.getID());
	}

	public boolean isActionExist(String targetActionID) {
		return containsKey(targetActionID);
	}

	public void deleteAction(String actionID) {
		remove(actionID);
	}

	public void deleteAction(GUIAction action) {
		deleteAction(action.getID());
	}

	public GUIAction getAction(String actionID) {
		return get(actionID);
	}

}
