package com.smyld.db.schema;

import com.smyld.SMYLDObject;

public class SchemaObject extends SMYLDObject implements SchemaConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String schemaType;

	public SchemaObject() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchemaType() {
		return schemaType;
	}

	public void setSchemaType(String schemaType) {
		this.schemaType = schemaType;
	}

	public String printSchemaData() {
		return schemaType + " " + name;
	}
}
