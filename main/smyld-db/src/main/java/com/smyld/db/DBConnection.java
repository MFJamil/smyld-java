package com.smyld.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

import com.smyld.SMYLDObject;

public class DBConnection extends SMYLDObject implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * String UserName; String UserPassword; String Host; String Port; String
	 * DBName;
	 */
	protected DBSettings settings;
	String ConnectionPath;
	protected boolean reconnecting;
	protected boolean connected;
	protected Connection dbConnection;
	protected Vector<DBConnectionListener> connectionListeners;
	protected Vector<DBConnectionListener> internalConnectionListeners;

	public DBConnection(String newUserName, String newUserPass, String newDBName) {
		settings = new DBSettings();
		settings.setUserName(newUserName);
		settings.setUserPassword(newUserPass);
		settings.setName(newDBName);
	}

	public DBConnection(DBSettings settings) {
		setSettings(settings);
	}

	public DBConnection(String newUserName, String newUserPass,
			String newDBName, String newPort, String newHost) {
		this(newUserName, newUserPass, newDBName);
		settings.setPort(newPort);
		settings.setHost(newHost);
	}

	public DBConnection(String path) {
		setConnectionPath(path);
	}

	public DBConnection() {

	}

	public void setSettings(DBSettings newSettings) {
		settings = newSettings;
	}

	public DBSettings getSettings() {
		return settings;
	}

	@SuppressWarnings("unused")
	private void init() {
		// ConnectionPoolDataSource conPolDS = new ConnectionPoolDataSource();

	}

	public void releaseConnections() throws SQLException {
		throw new SQLException("Release connection function must be provided");
	}

	/**
	 * Basic implementation that throws a SQLException
	 */
	public Connection getConnection() {
		return dbConnection;
	}

	public synchronized Connection createConnection() throws SQLException {
		return null;
	}

	public synchronized void reconnect() throws SQLException {
		dbConnection = createConnection();
	}

	public synchronized boolean isReconnecting() {
		return reconnecting;
	}

	/**
	 * Loads the properties file from the given path
	 */
	public static Properties loadDBParams(String path) {
		Properties params = new Properties();
		try {

			File f = new File(path);
			params.load(new FileInputStream(f));
		} catch (IOException e)

		{
			e.printStackTrace();
		}
		return params;
	}

	/*
	 * Setters and Getters public String getUserName() { return UserName; }
	 * 
	 * public void setUserName(String newUserName) { UserName = newUserName; }
	 * 
	 * public String getUserPassword() { return UserPassword; }
	 * 
	 * public void setUserPassword(String newUserPassword) { UserPassword =
	 * newUserPassword; }
	 * 
	 * public String getHost() { return Host; }
	 * 
	 * public void setHost(String newHost) { Host = newHost; }
	 * 
	 * public String getPort() { return Port; }
	 * 
	 * public void setPort(String newPort) { Port = newPort; }
	 * 
	 * public String getDBName() { return DBName; }
	 * 
	 * public void setDBName(String newDBName) { DBName = newDBName; }
	 */

	public String getConnectionPath() {
		return ConnectionPath;
	}

	public void setConnectionPath(String newConnectionPath) {
		ConnectionPath = newConnectionPath;
	}

	@Override
	public Object clone() {
		return new DBConnection();
	}

	public void addInternalDBConnectionListener(
			DBConnectionListener newConnectionListener) {
		if (internalConnectionListeners == null)
			internalConnectionListeners = new Vector<DBConnectionListener>();
		internalConnectionListeners.add(newConnectionListener);
	}

	public void addInternalDBConnectionListener(
			DBConnectionListener newConnectionListener, int priorty) {
		if (internalConnectionListeners == null)
			internalConnectionListeners = new Vector<DBConnectionListener>();
		internalConnectionListeners.add(priorty, newConnectionListener);
	}

	public void addDBConnectionListener(
			DBConnectionListener newConnectionListener, int priorty) {
		if (connectionListeners == null)
			connectionListeners = new Vector<DBConnectionListener>();
		connectionListeners.add(priorty, newConnectionListener);
	}

	public void addDBConnectionListener(
			DBConnectionListener newConnectionListener) {
		if (connectionListeners == null)
			connectionListeners = new Vector<DBConnectionListener>();
		connectionListeners.add(newConnectionListener);
	}

	public boolean detectConnectionError(Exception ex) {
		if (Utility.getErrorCategory(ex) == Utility.ERR_CATEGORY_CONNECTION) {
			if (!reconnecting) {
				connected = false;
				reconnecting = true;
				// System.out.println("BWDbUtility : DB connection is closed,
				// class hash (" + hashCode() + ")");
				informConnClosed(internalConnectionListeners);
				informConnClosed(connectionListeners);
				new Thread(this).start(); // Firing the thread to keep
											// reconneting to the data base
			}
			return true;
		}
		return false;
	}

	public boolean isConnected() {
		return connected;
	}

	/**
	 * Code for keep reconnecting until the connection for the data base is
	 * established again
	 */
	public void run() {
		while (!connected) {
			try {
				reconnect();
				// mainClass.dbConnection = smyldDbConnection.getConnection();
				// dbConnection = mainClass.dbConnection;
				// System.out.println("BWDbUtility : DB connection is retrieved,
				// class hash (" + hashCode() + ") connection Hash code (" +
				// ((Object)dbConnection).hashCode() + ")");
				connected = true;
				reconnecting = false;
				informConnResumed(internalConnectionListeners);
				informConnResumed(connectionListeners);

			} catch (Exception ex) {// System.out.println("Still not
									// connecting");
				// ex.printStackTrace();
				try {
					Thread.sleep(DB_RECONNECT_SLEEP_TIME);
				} catch (Exception sleepex) {
					sleepex.printStackTrace();
				}
			}
		}
	}

	private void informConnClosed(Vector<DBConnectionListener> listeners) {
		for (DBConnectionListener connectionListener : listeners) {
			connectionListener.connectionClosed();
		}
	}

	private void informConnResumed(Vector<DBConnectionListener> listeners) {
		for (DBConnectionListener connectionListener : listeners) {
			connectionListener.connectionResumed(this);
		}
	}

	public static final int DB_RECONNECT_SLEEP_TIME = 1000;

}
