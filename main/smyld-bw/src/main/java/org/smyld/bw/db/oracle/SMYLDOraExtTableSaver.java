package org.smyld.bw.db.oracle;

import java.io.File;
import java.io.IOException;

import org.smyld.SMYLDObject;
import org.smyld.bw.db.BWDataBase;
import org.smyld.bw.db.DBName;
import org.smyld.bw.db.SMYLDTableRecord;
import org.smyld.io.SMYLDFileOutputStream;

public class SMYLDOraExtTableSaver extends SMYLDObject implements DBName {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String folder;
	String tableName;
	SMYLDFileOutputStream fout;
	BWDataBase bwDB;
	String processName;

	public SMYLDOraExtTableSaver() {
	}

	public SMYLDOraExtTableSaver(String folder, String tableName,
			BWDataBase bwDataBase) {
		setFolder(folder);
		setTableName(tableName);
		bwDB = bwDataBase;
	}

	public void init() throws Exception {
		fout = initOutputFile();
	}

	private SMYLDFileOutputStream initOutputFile() throws Exception {
		return new SMYLDFileOutputStream(initFile());
	}

	private File initFile() {
		String newPath = getFolder();
		if (newPath == null)
			newPath = "";
		else
			newPath += File.separator;
		return new File(newPath + getTableName() + "_EXT.dat");
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void saveRecord(SMYLDTableRecord record) throws IOException {
		fout.writeln(record.printFlatValues());
	}

	public void close() throws Exception {
		// the stored procedure for starting the copy process must be done here
		fout.close();
		int result = bwDB.callInsertExternal(getTableName(), getProcessName());
		if (result == 0)
			throw new Exception("Error while writing externally to the table "
					+ tableName);
		// Adding code for deleting the created external file
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}
}
