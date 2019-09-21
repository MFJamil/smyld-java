package org.smyld.db.schema;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Vector;

import org.smyld.SMYLDObject;

public class SchemaComparator extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean debug;

	public SchemaComparator() {
	}

	public boolean compare(HashMap<String,Table> origTables, HashMap<String,Table> compTables)
			throws Exception {
		boolean result = true;
		HashMap<String,Vector<Object>> logs = new HashMap<String,Vector<Object>>();
		if ((origTables == null) || (compTables == null))
			return false;
		for (String tableName : origTables.keySet()) {
			addDebug("Comparing table " + tableName);
			Table compTab = compTables.get(tableName);
			Table origTab = origTables.get(tableName);
			result = origTab.equals(compTab);
			if (!result) {
				logs.put(tableName, origTab.getCompareLogs());
			}
		}
		System.out.println(logs.size());
		if (logs.size() > 0)
			dumpLogsTo(logs, "d:/temp/schemaCompareResults.txt");
		return result;
	}

	public void dumpLogsTo(HashMap<String,Vector<Object>>  logs, String fileName) throws Exception {
		FileOutputStream fout = new FileOutputStream(fileName);
		for (String tableName : logs.keySet()) {
			fout.write(("Table " + tableName
					+ " has the following differences " + OS_NEW_LINE)
					.getBytes());
			Vector<Object> diffLogs = logs.get(tableName);
			for (Object diffObject : diffLogs) {
				if (diffObject instanceof SchemaLog){
					SchemaLog diffItem = (SchemaLog) diffObject;
					fout.write(("\t" + diffItem.getOriginalObject().getSchemaType()
							+ " " + diffItem.getID() + OS_NEW_LINE).getBytes());
					fout
							.write(("\t\tOriginal "
									+ diffItem.getOriginalObject()
											.printSchemaData() + OS_NEW_LINE)
									.getBytes());
					fout
							.write(("\t\tCompared "
									+ diffItem.getComparedObject()
											.printSchemaData() + OS_NEW_LINE)
									.getBytes());
				}
			}
		}
		fout.close();
	}

	private void addDebug(String message) {
		if (debug) {
			System.out.println(message);
		}
	}

	public void setDebug(boolean debugActive) {
		debug = debugActive;
	}
}
