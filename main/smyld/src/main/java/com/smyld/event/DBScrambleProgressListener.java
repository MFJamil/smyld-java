package com.smyld.event;

public interface DBScrambleProgressListener extends ProgressListener {
	public void totalTablesNumber(int tablesNo);

	public void newScrambleTable(String tableName, int totalTableRecords);

	public boolean progressPerTable();

}
