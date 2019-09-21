package org.smyld.db.oracle;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OraclePreparedStatement;

import org.smyld.db.DBConnection;
import org.smyld.db.DBErrorHandler;
import org.smyld.db.DBIOHandler;

public class OraDBIOHandler extends DBIOHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int cashSize;
	int commitSize;
	int commitIdx;

	public OraDBIOHandler(DBErrorHandler e, DBConnection dbConn, int batchSize,
			int commitValue) {
		super(e, dbConn);
		setCashSize(batchSize);
		setCommitSize(commitValue);
	}

	public OraDBIOHandler(DBErrorHandler e, DBConnection dbConn) {
		super(e, dbConn);
	}

	private void initilize() {
		try {
			((OracleConnection) smyldDBConnection.getConnection())
					.setAutoCommit(false);
			// ((OracleConnection)smyldDBConnection.getConnection()).setDefaultExecuteBatch(getCashSize());
			// ((OraclePreparedStatement)pst).setExecuteBatch(getCashSize());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void deInitilize() {
		try {
			((OracleConnection) smyldDBConnection.getConnection())
					.setAutoCommit(true);
			// ((OracleConnection)smyldDBConnection.getConnection()).setDefaultExecuteBatch(0);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void importFile(String dumpKey, String targetTable, String fileName,
			String separator) throws Exception {
		initilize();
		super.importFile(dumpKey, targetTable, fileName, separator);
		deInitilize();
	}

	@Override
	protected PreparedStatement prepareStatement(String sql) throws Exception {
		PreparedStatement curStatement = super.prepareStatement(sql);
		((OraclePreparedStatement) curStatement).setExecuteBatch(getCashSize());
		return curStatement;
	}

	public int getCashSize() {
		return cashSize;
	}

	public void setCashSize(int cashSize) {
		this.cashSize = cashSize;
	}

	public int getCommitSize() {
		return commitSize;
	}

	public void setCommitSize(int commitSize) {
		this.commitSize = commitSize;
	}

	@Override
	public void postLine() throws Exception {

		pst.executeUpdate();
		commitIdx++;
		if ((getCommitSize() != -1) && (commitIdx >= getCommitSize())) {
			dbConnection.commit();
			commitIdx = 0;
		}
	}

}
