package org.smyld.log;

public class PlaingLogWriter extends AbstractLogWriter implements
		LogRecordWriter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlaingLogWriter(LogStructure newStruct) {
		super(newStruct);
	}

	public PlaingLogWriter() {
		super();
	}

	public String printRecord(LogRecord newRecord) {
		return null;
	}

	public String printFileHeader() {
		return null;
	}
}
