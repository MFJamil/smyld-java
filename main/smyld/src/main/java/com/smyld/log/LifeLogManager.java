package com.smyld.log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

public class LifeLogManager extends AbstractLogManager implements LogManager {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FileOutputStream fout;
	LogRecordWriter recordWriter;

	public LifeLogManager(String filePath, LogRecordWriter RecordWriter,boolean append)
		throws IOException {
		fout = new FileOutputStream(filePath, append);
		recordWriter = RecordWriter;
		if (recordWriter.printFileHeader() != null)
			writeLineToFile(recordWriter.printFileHeader());
	}

	public LifeLogManager(String filePath, LogRecordWriter RecordWriter)
			throws IOException {
		this(filePath,RecordWriter,true);
	}

	public synchronized void addRecord(LogRecord record) {
		writeLineToFile(recordWriter.printRecord(record));
	}

	private synchronized void writeLineToFile(String text) {
		try {
			fout.write((text + SYS_NEW_LINE).getBytes());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Collection<LogRecord> getRecords() {
		return null;
	}

	public LogRecord fetchRecord(Object ob) {
		return null;
	}

	public void writeReports(String filepath) {
	}

	public void closeLog() throws IOException {
		fout.close();
	}

}
