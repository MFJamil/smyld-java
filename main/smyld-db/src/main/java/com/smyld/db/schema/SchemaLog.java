package com.smyld.db.schema;

import com.smyld.SMYLDObject;

public class SchemaLog extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ID;
	String parentID;
	SchemaObject originalObject;
	SchemaObject comparedObject;
	String description;

	public SchemaLog() {
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public SchemaObject getOriginalObject() {
		return originalObject;
	}

	public void setOriginalObject(SchemaObject originalObject) {
		this.originalObject = originalObject;
	}

	public SchemaObject getComparedObject() {
		return comparedObject;
	}

	public void setComparedObject(SchemaObject comparedObject) {
		this.comparedObject = comparedObject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
