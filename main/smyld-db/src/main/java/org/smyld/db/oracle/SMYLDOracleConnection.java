package org.smyld.db.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

import javax.sql.ConnectionEvent;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.pool.OraclePooledConnection;

import org.smyld.db.DBConnection;
import org.smyld.db.DBSettings;
import org.smyld.text.TextUtil;

/**
 * This class will return the data base connection to the caller class, using
 * encapsulation, this class can be updated to make the connection free in
 * selecting the data base engine.
 */

public class SMYLDOracleConnection extends DBConnection {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int currentDriver = DRIVER_THIN;
	SMYLDOraConCashImpl occim;
	OracleConnectionPoolDataSource ocpds;
	OracleDataSource ods;
	// Connection conn = null;
	String dbURL;
	OraclePooledConnection pooledConnection;
	Vector<Connection> openedConnections = new Vector<Connection>();
	boolean poolingConnections;

	public SMYLDOracleConnection(String path) throws Exception {
		super(path);
		Properties props = loadDBParams(getConnectionPath());
		// Read the parameters from the properties
		DBSettings newDBSettings = new DBSettings();
		newDBSettings.setHost(props.getProperty("HOST"));
		newDBSettings.setName(props.getProperty("SERVICE"));
		newDBSettings.setUserName(props.getProperty("NAME"));
		newDBSettings.setUserPassword(props.getProperty("PWD"));
		newDBSettings.setPort(props.getProperty("PORT"));
		setSettings(newDBSettings);
		init();
	}

	public static int getDriverType(DBSettings oraDBSett) {
		if (oraDBSett.getDriver().equals(TXT_DRIVER_SERVER))
			return DRIVER_SERVER;
		else if (oraDBSett.getDriver().equals(TXT_DRIVER_THIN))
			return DRIVER_THIN;
		return DRIVER_OCI;
	}

	public SMYLDOracleConnection(Connection DBConnection) {
		dbConnection = DBConnection;
	}

	public SMYLDOracleConnection(String newUserName, String newUserPass,
			String newDBName, String newPort, String newHost) throws Exception {
		super(newUserName, newUserPass, newDBName, newPort, newHost);
		init();
	}

	public SMYLDOracleConnection(int driver, String newUserName,
			String newUserPass, String newDBName, String newPort, String newHost)
			throws Exception {
		super(newUserName, newUserPass, newDBName, newPort, newHost);
		currentDriver = driver;
		init();
	}

	public SMYLDOracleConnection(int driver, DBSettings newSettings)
			throws Exception {
		super(newSettings);
		currentDriver = driver;
		init();
	}

	public SMYLDOracleConnection(DBSettings newSettings, boolean withPooling)
			throws Exception {
		super(newSettings);
		poolingConnections = withPooling;
		if (newSettings.getDriver() != null)
			currentDriver = getDriverType(newSettings);
		else
			currentDriver = DRIVER_THIN;
		init();
	}

	public SMYLDOracleConnection(DBSettings newSettings) throws Exception {
		this(newSettings, false);
	}

	public SMYLDOracleConnection() throws Exception {
		// setConnectionPath("D:/Projects/resources/oracledb.properties");
		init();
	}

	public void setClientID(String connectionClientID) throws SQLException {
		// ((OracleConnection)dbConnection).setClientIdentifier(connectionClientID);
		setClientIDValue(dbConnection, connectionClientID);
	}

	public void clearClientID() throws SQLException {
		setClientIDValue(dbConnection, "");
	}

	public static void setClientIDValue(Connection conn, String value)
			throws SQLException {
		CallableStatement st = conn
				.prepareCall("{call SYS.DBMS_SESSION.SET_IDENTIFIER(?)}");
		st.setString(1, value);
		st.executeQuery();
		st.close();
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
	private void init() throws Exception {
		try {
			// * Connceting to any remote DB
			/*
			 * @TODO : (JDBC) the following line of code is temp for the class
			 * forname
			 */
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			// Loading the driver
			switch (currentDriver) {
			case DRIVER_OCI:
				if (!TextUtil.isEmpty(settings.getID())) {
					dbURL = getOCIDBTNSURL();
				} else {
					dbURL = getOCIDBURL();
				}
				break;
			case DRIVER_SERVER:
				dbURL = getInternalServerDBURL();
				break;
			default:
				dbURL = getThinDBURL();
			}
			if (poolingConnections) {
				ocpds = new OracleConnectionPoolDataSource();
				ocpds.setURL(dbURL);
				ocpds.setUser(settings.getUserName());
				ocpds.setPassword(settings.getUserPassword());
				// setImplicitCachingEnabled(true);
				pooledConnection = (OraclePooledConnection) ocpds
						.getPooledConnection();
				dbConnection = pooledConnection.getConnection();

			} else {
				ods = new OracleDataSource();
				ods.setURL(dbURL);
				ods.setUser(settings.getUserName());
				ods.setPassword(settings.getUserPassword());
				dbConnection = ods.getConnection();
			}
			openedConnections.add(dbConnection);
			// dbConnection.setTransactionIsolation(Connection.tra);
			connected = true;

		} catch (Exception ex) {
			OracleSqlException oraException = new OracleSqlException(ex);
			throw oraException;

		}
	}

	public void setImplicitCachingEnabled(boolean enable) throws SQLException {
		ocpds.setImplicitCachingEnabled(enable);
	}

	public void setExplicitCachingEnabled(boolean enable) throws SQLException {
		ocpds.setExplicitCachingEnabled(enable);

	}

	@Override
	public void releaseConnections() throws SQLException {
		System.out.println("ocpds.getPooledConnection().close();");
		// ocpds.getPooledConnection().close();
		if (pooledConnection != null)
			pooledConnection.close();
		else {
			for (Connection curConn : openedConnections) 
				curConn.close();
			
		}
		// System.out.println("occim.closePooledConnection(ocpds);");
		// occim.closePooledConnection(ocpds);
		// System.out.println("occim.close();");
		// occim.close();
	}

	/** This method handles the connection fatal errors */
	@SuppressWarnings("unused")
	private void handleConnectionErrors(ConnectionEvent cevt) {
		System.out.println("Data base error occured : "
				+ cevt.getSQLException());
		Connection errConn = (Connection) cevt.getSource();
		// if the connection is assigned as live connection a thread for
		// reconnecting must be established here
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
		// return pooledConnection.getConnection();
		Connection newConn = null;
		if (poolingConnections)
			newConn = ocpds.getPooledConnection().getConnection();
		else
			newConn = ods.getConnection();
		openedConnections.add(newConn);
		return newConn;
	}

	@Override
	public Object clone() {
		try {
			return new SMYLDOracleConnection(currentDriver, settings);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * Read the parameters in the properties file and create the db url string
	 * to create the db connection
	 * 
	 * @return the db url required to attain the connection
	 */
	public String getDB_URL() {
		String myStr = "jdbc:oracle:thin:" + settings.getUserName() + "/"
				+ settings.getUserPassword() + "@" + settings.getHost() + ":"
				+ getPortNumber() + ":" + settings.getName();
		return myStr;
	}

	public String getInternalServerDBURL() {
		String lsReturn = "jdbc:oracle:kprb:@" + settings.getHost();
		return lsReturn;
	}

	public String getOCIDBURL() {
		String lsReturn = "jdbc:oracle:oci:@(description=(address=(host="
				+ settings.getHost() + ")(protocol=tcp)(port="
				+ getPortNumber() + "))(connect_data=(sid="
				+ settings.getName() + ")(server=DEDICATED)))";

		return lsReturn;
	}

	public String getOCIDBTNSURL() {

		String lsReturn = "jdbc:oracle:oci:@" + settings.getID();

		return lsReturn;
	}

	public String getThinDBURL() {

		String myStr = "jdbc:oracle:thin:" + settings.getUserName() + "/"
				+ settings.getUserPassword() + "@" + settings.getHost() + ":"
				+ getPortNumber() + ":" + settings.getName();
		return myStr;

	}

	/*
	 * public void setStatementCashSize(int size) throws SQLException{
	 * occim.setStmtCacheSize(size); } public void getStatementCashSize() throws
	 * SQLException{ occim.getStmtCacheSize(); }
	 */

	/*
	 * ORA-04068: existing state of packages has been discarded Need to take
	 * care for this error, because it comes from the compilation of the package
	 * during connection.
	 * 
	 */

	private String getPortNumber() {
		return settings.getPort() != null ? settings.getPort() : "1521";
	}

	public static final String TXT_DRIVER_THIN = "thin";
	public static final String TXT_DRIVER_OCI = "oci";
	public static final String TXT_DRIVER_SERVER = "server";

	public static final String TXT_DRIVER_DEFAULT = TXT_DRIVER_THIN;

	public static final int DRIVER_THIN = 1;
	public static final int DRIVER_OCI = 2;
	public static final int DRIVER_SERVER = 3;
}
