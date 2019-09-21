package org.smyld.log;

import org.smyld.util.SMYLDDate;

public class ExcelWriter extends AbstractLogWriter implements LogRecordWriter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcelWriter(LogStructure newStruct) {
		super(newStruct);
	}

	public ExcelWriter() {
		super(new LogRecord());
	}

	public String printRecord(LogRecord newRecord) {
		return super.printRecord(newRecord, "\t");
	}

	@SuppressWarnings("unused")
	private String getTime(String currentLogTime) {
		if (currentLogTime == null)
			return new SMYLDDate(SMYLDDate.FRM_HHmmss_SEP_2points).toString();
		return currentLogTime;
	}

	public String printFileHeader() {
		return super.printFileHeader("\t");
	}

}
