package com.smyld.db;

import com.smyld.SMYLDException;

public class DBIOException extends SMYLDException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String recordContents;

	public DBIOException(Exception e, String recordValue) {
		super(e.getMessage());
		recordContents = recordValue;

	}

	public String getRecordContents() {
		return recordContents;
	}

}
