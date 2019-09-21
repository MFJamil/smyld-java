package org.smyld.log;

import java.util.Iterator;

import org.smyld.SMYLDObject;

public class AbstractLogWriter extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LogStructure logStruct;
	StringBuffer buffer = new StringBuffer();

	public AbstractLogWriter(LogStructure newStruct) {
		logStruct = newStruct;
	}

	public AbstractLogWriter() {
		logStruct = new DefaultLogRecordStrcut();
	}

	public LogStructure getLogStruct() {
		return logStruct;
	}

	public void setLogStruct(LogStructure logStruct) {
		this.logStruct = logStruct;
	}

	public String printRecord(LogRecord newRecord, String sep) {

		buffer.setLength(0);
		Iterator<String> itr = logStruct.getFieldsSeq().iterator();
		while (itr.hasNext()) {
			String fieldName = itr.next();
			buffer.append(newRecord.getFieldValue(fieldName));
			if (itr.hasNext())
				buffer.append(sep);
		}
		return buffer.toString();
	}

	public String printFileHeader(String sep) {
		buffer.setLength(0);
		Iterator<String> itr = logStruct.getFieldsHeader().iterator();
		while (itr.hasNext()) {
			String headerName = itr.next();
			buffer.append(headerName);
			if (itr.hasNext())
				buffer.append(sep);
		}
		return buffer.toString();
	}

}
