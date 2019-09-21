package com.smyld.log;

import com.smyld.SMYLDObject;

public class AbstractLogManager extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LogStructure logStruct;

	public AbstractLogManager() {
	}

	public void setLogStructure(LogStructure logStruct) {
		this.logStruct = logStruct;
	}

	public LogStructure getLogStructure() {
		return logStruct;
	}

}
