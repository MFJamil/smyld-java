package org.smyld.log;

public class CVSLogWriter extends AbstractLogWriter implements LogRecordWriter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String separator = ","; // Default setting for the log

	public CVSLogWriter(LogStructure newStruct) {
		super(newStruct);
	}

	public CVSLogWriter() {
		super();
	}

	public String printRecord(LogRecord newRecord) {
		return printRecord(newRecord, getSeparator());
	}

	public String printFileHeader() {
		return printFileHeader(getSeparator());
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}
}
