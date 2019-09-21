package com.smyld.gui;

import javax.swing.ImageIcon;

import com.smyld.SMYLDObject;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class GUIAction extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String    ID;
	String    label;
	String    target;
	String    command;
	String    parameters;
	String    title;
	Object    userObject;
	String    iconText;
	ImageIcon icon;
	
	/**
	 * 
	 * @see
	 * @since
	 */
	public GUIAction() {
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public Object getUserObject() {
		return userObject;
	}

	public void setUserObject(Object userObject) {
		this.userObject = userObject;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
	

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}



	/**
	 * @return the iconText
	 */
	public String getIconText() {
		return iconText;
	}

	/**
	 * @param iconText the iconText to set
	 */
	public void setIconText(String iconText) {
		this.iconText = iconText;
	}

	/**
	 * @return the icon
	 */
	public ImageIcon getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}



	public static final String COM_OPEN_WINDOW = "openWindow";
	public static final String COM_RUN_APP     = "runApplication";
	public static final String COM_ASSIGN_APP  = "assingToApplication";
	public static final String COM_CLOSE_APP   = "closeApplication";
	public static final String COM_ADD_COMP    = "addComponent";
	public static final String COM_OPEN_POPUP  = "openPopup";
	public static final String COM_ASSIGN_WIN  = "assignToWindow";

	


}
