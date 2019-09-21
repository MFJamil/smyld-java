package org.smyld.app;

import org.smyld.gui.GUIAction;
import org.smyld.app.pe.model.user.PEUser;

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
