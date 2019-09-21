package com.smyld.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

/**
 * Default implemetation of the interface LogManager which manages the log
 * records in a hashmap
 */
public class DefaultLogManager extends AbstractLogManager implements LogManager {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String,LogRecord> records;
	private static int seqKey = -1;

	/**
	 * Default constructor
	 */
	public DefaultLogManager() {
		this.records = new HashMap<String,LogRecord>();
	}

	/**
	 * Adds the given log record to this log manger without any verification if
	 * the record is already contained in this manager
	 */
	public void addRecord(LogRecord record) {
		seqKey++;
		records.put("" + seqKey, record);
	}

	/**
	 * Gets the set of log records maintained in this monitor
	 * 
	 * @return the set of log records contained in this monitor
	 */
	public Collection<LogRecord> getRecords() {
		return records.values();
	}

	/**
	 * Gets the record contained in this monitor with the given key
	 * 
	 * @param ob
	 *            the key of log record to be fetched from this monitor
	 * @return the logrecord with the given key, returns null when none with the
	 *         given key is found NOTE: This method may need revision
	 */
	public LogRecord fetchRecord(Object ob) {
		if (!records.containsKey(ob))
			return null;
		LogRecord lrec = (LogRecord) records.get(ob);
		return lrec;
	}

	/**
	 * Writes the reports contained in this monitor to the file
	 * 
	 * @param filepath
	 *            the path of the files where the records are to be written
	 */
	public void writeReports(String filepath) {
		StringBuffer str = new StringBuffer();
		str
				.append("Class name\t Module name\t Message\t Start time\t End time\n");
		int size = records.size();
		for (int i = 0; i < size; i++) {
			LogRecord rec = (LogRecord) records.get("" + i);
			str.append(rec.toString());
		}
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filepath));
			out.write(str.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeLog() throws IOException {
		// Doing nothing at the moment
	}

}
