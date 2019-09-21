package org.smyld.db;

import org.smyld.SMYLDObject;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class DBSettings extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String host;
	String name;
	String port;
	String userName;
	String userPassword;
	String SchemaOwner;
	String vendor;
	String driver;
	String ID;

	/**
	 * 
	 * @see
	 * @since
	 */
	public DBSettings() {
	}

	/**
	 * 
	 * @see
	 * @since
	 */
	public DBSettings(String dbHost, String dbName, String dbPort,
			String dbUserName, String dbUserPassword) {
		setName(dbName);
		setHost(dbHost);
		setPort(dbPort);
		setUserName(dbUserName);
		setUserPassword(dbUserPassword);

	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getSchemaOwner() {
		return SchemaOwner;
	}

	public void setSchemaOwner(String SchemaOwner) {
		this.SchemaOwner = SchemaOwner;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getPrintablePublicDBInfo() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Connecting to database ..");
		buffer.append(OS_NEW_LINE);
		// Adding the connection id
		if (ID != null) {
			buffer.append(" - Alias : ");
			buffer.append(ID);
			buffer.append(OS_NEW_LINE);
		}
		buffer.append(" - Driver : ");
		buffer.append(driver);
		buffer.append(OS_NEW_LINE);
		buffer.append(" - Host   : ");
		buffer.append(host);
		buffer.append(OS_NEW_LINE);
		buffer.append(" - Name   : ");
		buffer.append(name);
		buffer.append(OS_NEW_LINE);
		buffer.append(" - Port   : ");
		buffer.append(port);

		return buffer.toString();
	}

	public void outputDBInfo() {
		System.out.println(getPrintablePublicDBInfo());
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

}
