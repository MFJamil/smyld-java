package org.smyld.app;

import java.awt.Component;
import java.util.HashMap;

import org.smyld.gui.GUIAction;
import org.smyld.gui.SMYLDMDI;
import org.smyld.gui.event.ActionHandler;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class AppWindowsFactory extends AppBaseFactory {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected SMYLDMDI activeMDIWindow;
	protected HashMap<String, Object> openedWindows;
	boolean targetForComponent;

	/**
	 * 
	 * @see
	 * @since
	 */
	public AppWindowsFactory() {
	}

	public void openWindow(String windowName, AppManager appManager,
			GUIAction incomingAction) {

	}

	public void addWindow(String windowID, Object newWindow) {
		if (openedWindows == null) {
			openedWindows = new HashMap<String, Object>();
		}
		openedWindows.put(windowID, newWindow);
	}
	public Object getActiveWindow(String windowID){
		if (openedWindows!=null)
			return openedWindows.get(windowID);
		return null;
	}
	public void assignActionToWindow(GUIAction action) {

	}

	public void setActiveMDI(SMYLDMDI newActiveWindow) {
		activeMDIWindow = newActiveWindow;

	}

	public void sendWindow(String windowID, GUIAction newAction) {
		Object targetWindow = openedWindows.get(getWindowID(windowID));
		if ((targetWindow != null) && (targetWindow instanceof SMYLDMDI)) {
			if (targetForComponent) {
				String compID = windowID.substring(windowID.indexOf(".") + 1);
				Component targetComp = ((SMYLDMDI) targetWindow)
						.getComponent(compID);
				if ((targetComp != null)
						&& (targetComp instanceof ActionHandler)) {
					((ActionHandler) targetComp).processGUIAction(newAction);
				}
			} else {
				((SMYLDMDI) targetWindow).processGUIAction(newAction);
			}
		}
	}

	private String getWindowID(String target) {
		targetForComponent = target.indexOf(".") != -1;
		if (targetForComponent) {
			return target.substring(0, target.indexOf("."));
		}
		return target;
	}

	public SMYLDMDI getActiveMDIWindow() {
		return activeMDIWindow;
	}

	public void setActiveMDIWindow(SMYLDMDI activeMDIWindow) {
		this.activeMDIWindow = activeMDIWindow;
	}
	

}
