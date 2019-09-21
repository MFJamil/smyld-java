package com.smyld.db.schema;

public class View extends SchemaObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String queryText;

	public View() {
		setSchemaType(TAG_NAME_VIEW);
	}

	public String getQueryText() {
		return queryText;
	}

	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

}
