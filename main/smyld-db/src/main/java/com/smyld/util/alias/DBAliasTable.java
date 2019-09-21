package com.smyld.util.alias;

import com.smyld.SMYLDObject;

public class DBAliasTable extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String schema;
	String name;
	String target;

	public DBAliasTable() {
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public static String createKey(String schema, String tableName) {
		return schema.toLowerCase() + "." + tableName.toLowerCase();
	}

	public String createKey() {
		return createKey(schema, name);
	}
}
