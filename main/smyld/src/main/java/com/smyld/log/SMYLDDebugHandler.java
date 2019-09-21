package com.smyld.log;

import java.io.IOException;

import com.smyld.SMYLDObject;

/**
 * This class will hold the implementation of debugging, that can be classified
 * into different levels
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class SMYLDDebugHandler extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LogManager currentLogManager;
	int DebugLevel;
	int maximumDebugLevel;
	int echoDebugLevel;

	/**
	 * 
	 * @see
	 * @since
	 */
	public SMYLDDebugHandler(LogManager systemLogManager, int MaximumDebugLevel) {
		currentLogManager = systemLogManager;
		maximumDebugLevel = MaximumDebugLevel;
	}

	public void echoToDebugLevel(int echoDebugLevel) {
		this.echoDebugLevel = echoDebugLevel;
	}

	public void addRecord(LogRecord record, int msgDebugLevel) {
		if (msgDebugLevel <= echoDebugLevel)
			System.out.println(record.getLogMessage());
		if ((msgDebugLevel <= DebugLevel)
				&& (msgDebugLevel <= maximumDebugLevel)) {
			currentLogManager.addRecord(record);
		}
	}

	public void closeLog() throws IOException {
		currentLogManager.closeLog();
	}

	public int getDebugLevel() {
		return DebugLevel;
	}

	public void setDebugLevel(int DebugLevel) {
		this.DebugLevel = DebugLevel;
	}

}
