package com.smyld.db.oracle;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.Vector;

import com.smyld.db.SMYLDSQLException;
import com.smyld.db.Utility;

public class OracleSqlException extends SMYLDSQLException implements OraConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector<OracleErrorStack> errorStack;
	String errorObjectName;

	public OracleSqlException() {
	}

	public OracleSqlException(Exception exception) {
		super(exception);
	}

	public int getExceptionCategory() {
		return getExceptionCategory(getErrorNumber());
	}

	public String getErrorObjectName() {
		return errorObjectName;
	}

	public int getExceptionCategory(int errNo) {
		switch (errNo) {
		case ORACLE_ERR_IMMEDIATE_SHUTDOWN:
		case ORACLE_ERR_INIT_OR_SHUT_IN_PROG:
		case ORACLE_ERR_INVALID_LOGON:
		case ORACLE_ERR_CONNECTION_RESET:
		case DB_ERR_CONNECTION_RESET:
			return Utility.ERR_CATEGORY_CONNECTION;
		case ORACLE_ERR_PACKAGE_STATE_DISCARDED:
			// Code for detecting whether the package was recompiled or contains
			// error
			errorObjectName = getErrorStack(1).getErrorObjectName();
			if ((getErrorNumber(1) == ORACLE_ERR_PACKAGE_STATE_INVALIDATED)
					&& (getErrorNumber(2) == ORACLE_ERR_NOT_EXEC_ALTERED_DROP_PACKAGE)) {
				return Utility.ERR_CATEGORY_STORED_PROCEDURES_RECOMPILED;
			} else if (getErrorNumber(1) == ORACLE_ERR_PACKAGE_HAS_ERROR) {
				return Utility.ERR_CATEGORY_STORED_PROCEDURES_ERROR;
			}
		default:
			return Utility.ERR_CATEGORY_SQL;
		}
	}

	public OracleErrorStack getErrorStack(int stackIndex) {
		if (stackIndex <= errorStack.size())
			return (OracleErrorStack) errorStack.get(stackIndex);
		return null;
	}

	public int getErrorNumber(int stackIndex) {
		if (stackIndex <= errorStack.size())
			return ((OracleErrorStack) errorStack.get(stackIndex))
					.getErrorNumber();
		return -1;
	}

	// ORA-00001: unique constraint (BW3.PK_PRT_IMAGES) violated
	@Override
	public void doParseError(Exception exception) {
		super.doParseError(exception);
		setOriginalMessage(exception.getMessage());
		LineNumberReader reader = new LineNumberReader(new StringReader(
				getOriginalMessage()));
		String currentErrorLine = null;
		try {
			while ((currentErrorLine = reader.readLine()) != null) {
				if (errorStack == null)
					errorStack = new Vector<OracleErrorStack>();
				OracleErrorStack currStack = new OracleErrorStack(
						currentErrorLine);
				errorStack.add(currStack);
			}
			if ((errorStack != null) && (errorStack.size() > 0))
				errorNumber = ((OracleErrorStack) errorStack.get(0))
						.getErrorNumber();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * String excText = exception.getMessage(); if
		 * ((excText!=null)&&(excText.startsWith("ORA"))){
		 * setOriginalMessage(excText); String eNoText =
		 * TextUtil.getSectionText(excText,"-",":");
		 * 
		 * if (eNoText!=null){ errorNumber = Integer.parseInt(eNoText); /*if
		 * (err==ORACLE_ERR_UNIQUE_CONSTRAINT)
		 * setErrorNumber(DB_ERR_UNIQUE_CONSTRAINT); else
		 * setErrorNumber(DB_ERR_UNKNOWN);
		 *  } if (excText.indexOf(":")!=-1)
		 * setOriginalMessage(excText.substring(excText.indexOf(":")+1)); }
		 */
	}

	public Vector<OracleErrorStack> getOracleErrorStack() {
		return errorStack;
	}

}
