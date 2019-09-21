package com.smyld.db;

import java.sql.SQLException;

import com.smyld.SMYLDException;

public class SMYLDSQLException extends SMYLDException implements DBConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int errorNumber;
	String originalMessage;
	Exception originalException;

	public SMYLDSQLException() {
	}

	public SMYLDSQLException(Exception sqlException) {
		super(sqlException.getMessage());
		originalException = sqlException;
		doParseError(sqlException);
	}

	// ORA-00001: unique constraint (BW3.PK_PRT_IMAGES) violated
	public void doParseError(Exception srcException) {
		if (srcException instanceof SQLException) {
			if (srcException.getMessage().indexOf(DB_ERR_CONNECTION_RESET_TEXT) != -1) {
				errorNumber = DB_ERR_CONNECTION_RESET;
			}
		}
	}

	public String getOriginalMessage() {
		return originalMessage;
	}

	public void setOriginalMessage(String originalMessage) {
		this.originalMessage = originalMessage;
	}

	public int getErrorNumber() {
		return errorNumber;
	}

}
