package org.smyld.db.schema;

import java.util.HashMap;
import java.util.Vector;

public class Table extends SchemaObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,SchemaObject> cols;
	HashMap<String,SchemaObject> primaryKeys;
	HashMap<String,SchemaObject> foreignKeys;
	protected Vector<Object> logs;

	public Table() {
		setSchemaType(TAG_NAME_TABLE);
	}

	public HashMap<String,SchemaObject> getCols() {
		return cols;
	}

	public void setCols(HashMap<String,SchemaObject> cols) {
		this.cols = cols;
	}

	public HashMap<String,SchemaObject> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(HashMap<String,SchemaObject> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public HashMap<String,SchemaObject> getForeignKeys() {
		return foreignKeys;
	}

	public void setForeignKeys(HashMap<String,SchemaObject> foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

	@Override
	public boolean equals(Object compTable) {
		if (compTable instanceof Table) {
			Table table = (Table) compTable;
			compareColumns(table.getCols());
			comparePKeys(table.getPrimaryKeys());
			compareFKeys(table.getForeignKeys());
		}
		return (logs.size() == 0);
	}

	private boolean compareColumns(HashMap<String,SchemaObject> cols) {
		return compare(this.cols, cols, "Columns");
	}

	private boolean comparePKeys(HashMap<String,SchemaObject> pkeys) {
		return compare(this.primaryKeys, pkeys, "Primary Keys");
	}

	private boolean compareFKeys(HashMap<String,SchemaObject> fkeys) {
		return compare(this.foreignKeys, fkeys, "Foreign Keys");
	}

	private boolean compare(HashMap<String,SchemaObject> orig, HashMap<String,SchemaObject> comp, String keyword) {
		if ((comp == null) && (orig != null)) {
			logs.add(keyword + " do not exsit !!");
			return false;
		} else if ((comp == null) && (orig == null))
			return true;
		boolean result = true;
		for (String  key : orig.keySet()) {
			SchemaObject origObj = orig.get(key);
			SchemaObject compObj = comp.get(key);
			if ((origObj == null) && (compObj == null)) {
				result = true;
			} else if ((origObj != null) && (compObj != null)) {
				result = origObj.equals(compObj);
			} else {
				result = false;
			}
			if (!result) {
				addSchemaLog(origObj, compObj);
			}
			// logs.add(keyword + " does not match on " + key);

		}
		return result;
	}
	
	public void addColumn(TableColumn newCol){
		if (cols == null)
			cols = new HashMap<String, SchemaObject>();
		cols.put(newCol.getName(), newCol);
	}
	public TableColumn getColumn(String colName){
		if (cols!=null) return (TableColumn)cols.get(colName);
		return null;
	}

	protected void addSchemaLog(SchemaObject origObj, SchemaObject compObj) {
		if (logs == null)
			logs = new Vector<Object>();
		SchemaLog newLog = new SchemaLog();
		newLog.setID(origObj.getName());
		newLog.setOriginalObject(origObj);
		newLog.setComparedObject(compObj);
		logs.add(newLog);
	}

	public Vector<Object> getCompareLogs() {
		return logs;
	}

}
