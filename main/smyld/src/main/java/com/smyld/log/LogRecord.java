package com.smyld.log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

public class LogRecord implements Serializable, LogStructure {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String className;
	private String modName;
	private String message;
	private String startTime;
	private String endTime;
	private HashMap<String,String> fields = new HashMap<String,String>();

	public LogRecord() {
		fields.put(FLD_CLASS_NAME, FLD_CLASS_NAME);
		fields.put(FLD_MODULE_NAME, FLD_MODULE_NAME);
		fields.put(FLD_MESSAGE, FLD_MESSAGE);
		fields.put(FLD_START_TIME, FLD_START_TIME);
		fields.put(FLD_END_TIME, FLD_END_TIME);
	}

	public LogRecord(String message, String className) {
		setClassName(className);
		setMessage(message);
	}

	@SuppressWarnings("unchecked")
	public LogRecord(String message, Class srouceClass) {
		setClassName(srouceClass.getName());
		setMessage(message);
	}

	public void reset() {
		/*
		 * setClassName(null); setEndTime(null); setStartTime(null);
		 * setModuleName(null);
		 */
	}

	// *
	public String getClassName() {
		return getFieldValue(FLD_CLASS_NAME);
	}

	public String getModuleName() {
		return getFieldValue(FLD_MODULE_NAME);
	}

	public String getLogMessage() {
		return getFieldValue(FLD_MESSAGE);
	}

	public String getStartTime() {
		return getFieldValue(FLD_START_TIME);
	}

	public String getEndTime() {
		return getFieldValue(FLD_END_TIME);
	}

	public void setClassName(String name) {
		setField(FLD_CLASS_NAME, name);
	}

	public void setModuleName(String name) {
		setField(FLD_MODULE_NAME, name);
	}

	public void setMessage(String message) {
		setField(FLD_MESSAGE, message);
	}

	public void setStartTime(String time) {
		setField(FLD_START_TIME, time);
	}

	public void setEndTime(String time) {
		setField(FLD_END_TIME, time);
	}

	// */

	public String getFieldValue(String fieldName) {
		return (String) fields.get(fieldName);
	}

	public void addField(String fieldName, String fieldValue) {
		fields.put(fieldName, fieldValue);
	}

	public void setField(String fieldName, String fieldValue) {
		fields.put(fieldName, fieldValue);
	}

	/**
	 * Returns this log record as string with tabs seperating contents of this
	 * log record
	 * 
	 * @return this log record as string
	 */
	@Override
	public String toString() {
		return className + "\t" + modName + "\t" + message + "\t" + startTime
				+ "\t" + endTime + "\n";
	}

	// By default we do the settings in this parent class, and it up to the
	// developer to skip it
	public static final String FLD_MESSAGE = "Message";
	public static final String FLD_MODULE_NAME = "ModuleName";
	public static final String FLD_CLASS_NAME = "ClassName";
	public static final String FLD_START_TIME = "StartTime";
	public static final String FLD_END_TIME = "EndTime";

	public static final String FLDHD_MESSAGE = "Message";
	public static final String FLDHD_MODULE_NAME = "Module Name";
	public static final String FLDHD_CLASS_NAME = "Class Name";
	public static final String FLDHD_START_TIME = "Start Time";
	public static final String FLDHD_END_TIME = "End Time";

	public Vector<String> getFieldsSeq() {
		Vector<String> fieldsSeq = new Vector<String>();
		fieldsSeq.add(FLD_CLASS_NAME);
		fieldsSeq.add(FLD_MODULE_NAME);
		fieldsSeq.add(FLD_MESSAGE);
		fieldsSeq.add(FLD_START_TIME);
		fieldsSeq.add(FLD_END_TIME);
		return fieldsSeq;
	}

	public Vector<String> getFieldsHeader() {
		Vector<String> fieldsHdr = new Vector<String>();
		fieldsHdr.add(FLDHD_CLASS_NAME);
		fieldsHdr.add(FLDHD_MODULE_NAME);
		fieldsHdr.add(FLDHD_MESSAGE);
		fieldsHdr.add(FLDHD_START_TIME);
		fieldsHdr.add(FLDHD_END_TIME);
		return fieldsHdr;

	}

}
