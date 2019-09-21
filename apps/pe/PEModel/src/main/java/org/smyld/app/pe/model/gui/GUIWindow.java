package org.smyld.app.pe.model.gui;


/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class GUIWindow extends GUIComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String lableID;
	String body;
	String bodyType;
	String bodyID;
	String startUpMethod;
	String bodyListenerTarget;
	String menuBarID;
	String menuHandler;
	String resizable;
	String toolbarID;
	String startup;
	String statusBar;

	/**
	 * 
	 * @see
	 * @since
	 */
	public GUIWindow() {
	}

	public String getLableID() {
		return lableID;
	}

	public void setLableID(String lableID) {
		if (lableID != null) {
			this.lableID = lableID;
		}
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		if (body != null) {
			this.body = body;
		}
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		if (bodyType != null) {
			this.bodyType = bodyType;
		}
	}

	public String getBodyID() {
		return bodyID;
	}

	public void setBodyID(String bodyID) {
		this.bodyID = bodyID;
	}

	public String getStartUpMethod() {
		return startUpMethod;
	}

	public void setStartUpMethod(String startUpMethod) {
		this.startUpMethod = startUpMethod;
	}

	public String getBodyListenerTarget() {
		return bodyListenerTarget;
	}

	public void setBodyListenerTarget(String bodyListenerTarget) {
		this.bodyListenerTarget = bodyListenerTarget;
	}

	public String getMenuBarID() {
		return menuBarID;
	}

	public void setMenuBarID(String menuBarID) {
		this.menuBarID = menuBarID;
	}

	public String getMenuHandler() {
		return menuHandler;
	}

	public void setMenuHandler(String menuHandler) {
		this.menuHandler = menuHandler;
	}

	public String getResizable() {
		return resizable;
	}

	public void setResizable(String resizable) {
		this.resizable = resizable;
	}

	public String getToolbarID() {
		return toolbarID;
	}

	public void setToolbarID(String toolbarID) {
		this.toolbarID = toolbarID;
	}

	public String getStartup() {
		return startup;
	}

	public void setStartup(String startup) {
		this.startup = startup;
	}

	public String getStatusBar() {
		return statusBar;
	}

	public void setStatusBar(String statusBar) {
		this.statusBar = statusBar;
	}

}
