package org.smyld.log;

import java.io.IOException;
import java.util.Collection;

public interface LogManager {

	void addRecord(LogRecord record);

	Collection<LogRecord> getRecords();

	LogRecord fetchRecord(Object ob);

	void setLogStructure(LogStructure logStruct);

	LogStructure getLogStructure();

	void writeReports(String filepath);

	public void closeLog() throws IOException;

	public static final String SYS_NEW_LINE = System
			.getProperty("line.separator");
}
