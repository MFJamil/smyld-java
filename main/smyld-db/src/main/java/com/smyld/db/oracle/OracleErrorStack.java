package com.smyld.db.oracle;

import com.smyld.SMYLDObject;
import com.smyld.text.TextUtil;

public class OracleErrorStack extends SMYLDObject implements OraConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String errorLine;
	String errorObject;
	int errorNumber;

	public OracleErrorStack(String errorLine) {
		this.errorLine = errorLine;
		doParseError();
	}

	private void doParseError() {
		int startIndex = errorLine.indexOf("ORA-");
		if (startIndex != -1) {
			startIndex += 4;
			int semiColon = errorLine.indexOf(":", startIndex);
			if (semiColon != -1) {
				String errorNoText = errorLine.substring(startIndex, semiColon);
				errorNumber = Integer.parseInt(errorNoText);
				processObjectError(semiColon);
			}
		} else {
			errorNumber = -1;
		}
	}

	private void processObjectError(int index) {
		if (containsObjectName(errorNumber))
			errorObject = TextUtil.getSectionText(errorLine, "\"", "\"", index);

	}

	public String getErrorObjectName() {
		return errorObject;
	}

	public static boolean containsObjectName(int errorNumber) {
		switch (errorNumber) {
		case ORACLE_ERR_PACKAGE_STATE_INVALIDATED:
		case ORACLE_ERR_PACKAGE_HAS_ERROR:
			return true;

		}
		return false;
	}

	public String getErrorLine() {
		return errorLine;
	}

	public int getErrorNumber() {
		return errorNumber;
	}

}
