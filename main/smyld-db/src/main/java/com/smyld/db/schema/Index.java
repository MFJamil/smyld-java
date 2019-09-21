package com.smyld.db.schema;

public class Index extends SchemaObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String type;
	boolean unique;
	String tableName;
	boolean asc;
	//HashMap cols;

	public Index() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	/* I removed it because I did not find reference for it!
	public HashMap getCols() {
		return cols;
	}

	public void setCols(HashMap cols) {
		this.cols = cols;
	}
	*/

}
