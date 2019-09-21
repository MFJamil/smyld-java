package org.smyld.app.pe.model.gui;

import org.smyld.SMYLDObject;

public class GUIField extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ID;
	String label;
	String width;
	String height;

	public GUIField() {
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

}
