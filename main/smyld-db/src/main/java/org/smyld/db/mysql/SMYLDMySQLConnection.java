package org.smyld.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

import org.smyld.db.DBConnection;
import org.smyld.db.DBSettings;

public class SMYLDMySQLConnection extends DBConnection implements MySQLConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMYLDMySQLConnection(DBSettings dbSettings) throws Exception {
		super(dbSettings);
		init();

	}

	public SMYLDMySQLConnection(Connection DBConnection) {
		dbConnection = DBConnection;
	}

	public SMYLDMySQLConnection(String newUserName, String newUserPass,
			String newDBName, String newPort, String newHost) throws Exception {
		super(newUserName, newUserPass, newDBName, newPort, newHost);
		init();
	}

	private void init() throws Exception {
		Class.forName(DRIVER_CLASS).newInstance();
		dbConnection = DriverManager.getConnection(getDBURL());
		connected = (dbConnection != null);
		// Need to trap the exception to throw a more meaningful exceptions
	}

	public String getDBURL() {
		// example url "jdbc:mysql://localhost:9100/test?user=root&password=123"
		String lsReturn = "jdbc:mysql://" + settings.getHost() + ":"
				+ getPortNumber() + "/" + settings.getName() + "?user="
				+ settings.getUserName() + "&password="
				+ settings.getUserPassword();

		return lsReturn;
	}

	private String getPortNumber() {
		return settings.getPort() != null ? settings.getPort()
				: DEFAULT_PORT_NUMBER;
	}

}
