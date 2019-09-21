package com.smyld.log;

public interface LogRecordWriter {
	public String printRecord(LogRecord newRecord);

	public String printFileHeader();

	public LogStructure getLogStruct();

}
