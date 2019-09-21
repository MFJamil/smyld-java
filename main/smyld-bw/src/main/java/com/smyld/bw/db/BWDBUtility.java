package com.smyld.bw.db;

import java.sql.SQLException;

import com.smyld.bw.data.structurs.SystemJob;
import com.smyld.db.DBConnection;
import com.smyld.db.DBErrorHandler;
import com.smyld.db.oracle.OracleDBUtil;

public class BWDBUtility extends OracleDBUtil // implements Runnable
{
	boolean liveConnection;
	boolean reconnecting = false;
	BWDataBase mainClass;

	/*
	 * public BWDBUtility(java.sql.Connection activeConnection,DBErrorHandler
	 * handler, BWDataBase MainClass) { super(activeConnection, handler);
	 * mainClass = MainClass; }
	 */

	public BWDBUtility(DBConnection activeDBConnection, DBErrorHandler handler,
			BWDataBase MainClass, boolean LiveConnection) throws SQLException {
		super(activeDBConnection, handler);
		mainClass = MainClass;
	}

	protected void handleDBError(Exception ex, SystemJob sourceJob) {
		handleDBError(ex);
	}

	@Override
	public void handleDBError(Exception ex) {
		if (!smyldDbConnection.detectConnectionError(ex))
			super.handleDBError(ex);
	}

	public static final int DB_RECONNECT_SLEEP_TIME = 1000;

}
