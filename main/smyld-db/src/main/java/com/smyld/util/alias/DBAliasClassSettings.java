package com.smyld.util.alias;

import java.util.HashMap;

import com.smyld.db.DBConnection;

public class DBAliasClassSettings extends AliasClassSettings {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DBConnection activeDBConnection;
	HashMap<String,String> tableNames;
	String schemaName;
	String tablePrefix = "TABLE";
	String columnPrefix = "COL";

	public DBAliasClassSettings() {
	}

	public HashMap<String,String> getTableNames() {
		return tableNames;
	}

	public void setTableNames(HashMap<String,String> tableNames) {
		this.tableNames = tableNames;
	}

	public DBConnection getActiveDBConnection() {
		return activeDBConnection;
	}

	public void setActiveDBConnection(DBConnection activeDBConnection) {
		this.activeDBConnection = activeDBConnection;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public String getColumnPrefix() {
		return columnPrefix;
	}

	public void setColumnPrefix(String columnPrefix) {
		this.columnPrefix = columnPrefix;
	}

}
