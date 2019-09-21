package org.smyld;

import org.smyld.db.DBConnection;
import org.smyld.db.DBErrorHandler;
import org.smyld.db.oracle.OraDBIOHandler;

public class TestOraDB extends OraDBIOHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestOraDB(DBErrorHandler e, DBConnection dbConn, int batchSize,
			int commitValue) {
		super(e, dbConn, batchSize, commitValue);
	}

	@Override
	protected void insertRecord(String currentLine) throws Exception {
		String[] curdata = readData(currentLine);
		fillData(curdata, 1);
		pst.setString(1, "00000007");
		postLine();
	}

}
