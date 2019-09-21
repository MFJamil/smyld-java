package com.smyld.app;

import com.smyld.gui.GUIAction;
import com.smyld.app.pe.model.user.PEUser;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public interface AppManager {
	public abstract void applicationInit(String[] args);

	public abstract void interfaceCreated();

	public abstract void actionFired(GUIAction action);
	
	public abstract PEUser getActiveUser();

}
