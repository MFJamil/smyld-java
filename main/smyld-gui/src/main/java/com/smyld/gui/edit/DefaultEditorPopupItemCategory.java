package com.smyld.gui.edit;

import javax.swing.ImageIcon;

public class DefaultEditorPopupItemCategory implements EditorPopupItemCategory {
	String iD;
	String description;
	String name;
	ImageIcon icon;
	boolean   needProcessing;
	
	public DefaultEditorPopupItemCategory(){
		
	}

	public DefaultEditorPopupItemCategory(String iD,String description,String name){
		setID(iD);setDescription(description);setName(name);
	}

	public DefaultEditorPopupItemCategory(String iD,String description,String name,ImageIcon icon){
		setID(iD);setDescription(description);setName(name);setIcon(icon);
		
	}

	public String getDescription() {
		return description;
	}

	public String getID() {
		return iD;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public String getName() {
		return name;
	}

	/**
	 * @param id the iD to set
	 */
	public void setID(String id) {
		iD = id;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	/**
	 * @return the needProcessing
	 */
	public boolean doNeedProcessing() {
		return needProcessing;
	}

	/**
	 * @param needProcessing the needProcessing to set
	 */
	public void setNeedProcessing(boolean needProcessing) {
		this.needProcessing = needProcessing;
	}
	

}
