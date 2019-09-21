package org.smyld.db;

import java.sql.SQLException;

//import org.smyld.bw.data.structurs.SystemJob;
import org.smyld.db.oracle.OracleDBUtil;

public class SMYLDDBUtility extends OracleDBUtil // implements Runnable
{
	boolean liveConnection;
	boolean reconnecting = false;
	SMYLDDataBaseHandler mainClass;

	public SMYLDDBUtility(DBConnection activeDBConnection,
			DBErrorHandler handler, SMYLDDataBaseHandler MainClass,
			boolean LiveConnection) throws SQLException {
		super(activeDBConnection, handler);
		mainClass = MainClass;
	}

	@Override
	public void handleDBError(Exception ex) {
		if (!smyldDbConnection.detectConnectionError(ex))
			super.handleDBError(ex);
	}

	@Override
	public SMYLDSQLException parseSqlException(SQLException ex) {
		return null;
		// if(
		// return new SMYLDSQLException(ex);
	}

	public static final int DB_RECONNECT_SLEEP_TIME = 1000;

}
