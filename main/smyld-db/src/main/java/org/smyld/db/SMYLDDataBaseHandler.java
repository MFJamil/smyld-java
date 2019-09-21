package org.smyld.db;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.smyld.SMYLDObject;
//import org.smyld.bw.data.structurs.SystemJob;
import org.smyld.db.oracle.SMYLDOracleConnection;

/**
 * This class will handle the whole connection with the data base holding the
 * connection object together with the utility object as static declared and
 * accordingly all the child classes will use the very same connection object
 * and utility.
 * 
 */
public class SMYLDDataBaseHandler extends SMYLDObject implements DBConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Holds the connection to the data base
	 */
	protected java.sql.Connection dbConnection = null;
	protected DBConnection smyldDBConnection;
	/**
	 * serves for the different data base access methods
	 */
	protected SMYLDDBUtility dbUtility = null;
	/**
	 * Holds the names of tables and fields
	 */
	/**
	 * Holds the data base connection status whether it is connected or not
	 */

	

	@SuppressWarnings("unused")
	private DBErrorHandler handler;

	/**
	 * In this constructor, the class will open connection to the data base, and
	 * will load the required tables according to method calls
	 */
	public SMYLDDataBaseHandler(DBErrorHandler e) {
		init(e, null);
	}

	public SMYLDDataBaseHandler(DBErrorHandler e, DBConnection connection) {
		try {
			smyldDBConnection = connection;
			dbConnection = smyldDBConnection.getConnection();
			smyldDBConnection
					.addInternalDBConnectionListener(new DBConnectionListener() {
						public void connectionClosed() {

						}

						public void connectionResumed(DBConnection newConnection) {
							setSMYLDConnection(newConnection);
						}
					});
			init(e, connection);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public SMYLDDataBaseHandler(DBErrorHandler e, String userName,
			String userPassword, String host, String port, String dbname) {
		try {
			smyldDBConnection = new SMYLDOracleConnection(userName, userPassword,
					dbname, port, host);
			dbConnection = smyldDBConnection.getConnection();
			init(e, smyldDBConnection);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public SMYLDDataBaseHandler(int driver, DBErrorHandler e, String userName,
			String userPassword, String host, String port, String dbname) {
		try {
			smyldDBConnection = new SMYLDOracleConnection(driver, userName,
					userPassword, dbname, port, host);
			dbConnection = smyldDBConnection.getConnection();
			init(e, smyldDBConnection);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void init(DBErrorHandler e, DBConnection activeDBConnection) {
		try {
			if (dbUtility == null) {
				if (activeDBConnection != null) {
					dbUtility = new SMYLDDBUtility(activeDBConnection, e, this,
							true);
					setDBConnection(activeDBConnection);
				}
			}
		} catch (Exception ex) {
			dbUtility.handleDBError(ex);
			ex.printStackTrace();
		}
	}

	public void setSMYLDConnection(DBConnection newActiveConnection) {
		smyldDBConnection = newActiveConnection;
		dbConnection = smyldDBConnection.getConnection();
		dbUtility.setSMYLDConnection(newActiveConnection);
	}

	public Utility getUtility() {
		return dbUtility;
	}

	public boolean isConnected() {
		return smyldDBConnection.isConnected();

	}

	public DBConnection getSMYLDConnection() {
		return smyldDBConnection;
	}

	public void closeConnection() throws SQLException {
		// System.out.println("Closing connection in progress .... ");
		dbConnection.close();
	}

	public synchronized void doCommit() {
		try {
			dbConnection.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public synchronized void doRollBack() {
		try {
			dbConnection.rollback();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void addDBConnectionListener(
			DBConnectionListener newConnectionListener) {
		smyldDBConnection.addDBConnectionListener(newConnectionListener);
		/*
		 * if (connectionListeners==null) connectionListeners = new Vector();
		 * connectionListeners.add(newConnectionListener);
		 */
	}

	public void addDBConnectionListener(
			DBConnectionListener newConnectionListener, int priorty) {
		smyldDBConnection.addDBConnectionListener(newConnectionListener, priorty);
		/*
		 * if (connectionListeners==null) connectionListeners = new Vector();
		 * connectionListeners.add(newConnectionListener);
		 */
	}

	public synchronized void executeDoubleParamFunction(String function,
			String param1, String param2) {
		dbUtility.executeDoubleParamFunction(function, param1, param2);
	}

	public synchronized void executeDoubleParamFunction(String function,
			int param1, int param2) {
		dbUtility.executeDoubleParamFunction(function, param1, param2);
	}

	public synchronized void executeTrippleParamFunction(String function,
			String param1, String param2, String param3) {
		dbUtility.executeTrippleParamFunction(function, param1, param2, param3);
	}

	public synchronized void executeDoubleParamProcedure(String procedure,
			int param1, int param2) {
		dbUtility.executeDoubleParamProcedure(procedure, param1, param2);
	}

	public synchronized void executeTrippleParamProcedure(String procedure,
			String param1, int param2, int param3) {
		dbUtility.executeTrippleParamProcedure(procedure, param1, param2,
				param3);
	}

	public synchronized Array executeSingleParamOraArrayFun(String function,
			String arrayTypeName, int param1) {
		return dbUtility.executeSingleParamArrayFunction(function,
				arrayTypeName, param1);
	}

	public synchronized void executeMultiParamProcedure(String procedureName,
			String[] params) {
		dbUtility.executeMultiParamProcedure(procedureName, params);
	}

	protected synchronized ResultSet executeQuery(String sql) {
		return dbUtility.executeQuery(sql);
	}

	public void setDBConnection(DBConnection newActiveDBConnection) {
		smyldDBConnection = newActiveDBConnection;
	}


	// All data base classes will forward the exceptions to this method
	protected synchronized void handleDBError(Exception ex) {
		dbUtility.handleDBError(ex);
	}

	public int getTableTotalRecordsNumber(String tableName) {
		int recNo = 0;
		ResultSet rs = dbUtility.executeQuery(SQL.SELCRN + tableName);
		try {
			if (rs.next())
				recNo = rs.getInt(1);
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rs);
		}
		return recNo;

	}

	/**
	 * Holds value (in data base) of the parent id in case there is not parent
	 */
	public static final String DB_VAL_NoParentFieldValue = "0";
	public static final int DB_SEQUENTIAL_NO_WIDTH = 11;
}
