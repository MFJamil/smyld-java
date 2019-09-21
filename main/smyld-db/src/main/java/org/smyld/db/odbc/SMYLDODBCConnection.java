package org.smyld.db.odbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.smyld.db.DBConnection;
import org.smyld.db.DBSettings;

public class SMYLDODBCConnection extends DBConnection {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMYLDODBCConnection(String newUserName, String newUserPass,
			String newDBName) {
		super(newUserName, newUserPass, newDBName);
	}

	public SMYLDODBCConnection(String path) {
		setConnectionPath(path);
		Properties param = loadDBParams(getConnectionPath());
		settings = new DBSettings();
		settings.setName(param.getProperty("DBNAME"));
		settings.setUserName(param.getProperty("UID"));
		settings.setUserPassword(param.getProperty("PWD"));
	}

	/**
	 * This methode will return the data base connection according to the
	 * required distination and will be updated from time to time, to fullfill
	 * the required environment
	 * 
	 * @exception SQLException
	 *                if the connection to the database failed
	 * @return The connection object and null if it fails
	 */
	@Override
	public Connection getConnection() {
		return dbConnection;
	}

	@Override
	public synchronized Connection createConnection() throws SQLException {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			dbConnection = DriverManager.getConnection(getDB_URL());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dbConnection;
	}

	public String getDB_URL() {
		/*
		 * Properties param = loadDBParams(getConnectionPath()); String dbname =
		 * param.getProperty("DBNAME"); String uid = param.getProperty("UID");
		 * String pwd = param.getProperty("PWD");
		 */
		String myStr = "jdbc:odbc:" + settings.getName() + ";" + "UID="
				+ settings.getUserName() + ";" + "PWD="
				+ settings.getUserPassword();
		System.out.println("DBURL:" + myStr);
		return myStr;
	}

}
