package com.smyld.db;

import com.smyld.db.mysql.SMYLDMySQLConnection;
import com.smyld.db.oracle.SMYLDOracleConnection;

public class DBConnectionFactory implements DBConstants {
	public DBConnectionFactory() {
	}

	public DBConnection createDBConnection(DBSettings incSettings)
			throws Exception {
		DBConnection targetConnection = null;
		if (incSettings.getVendor() == null)
			incSettings.setVendor(DB_VENDOR_DEFAULT);
		if (incSettings.getVendor().equals(DB_VENDOR_ORACLE)) {
			targetConnection = new SMYLDOracleConnection(incSettings);
		} else if (incSettings.getVendor().equals(DB_VENDOR_MYSQL)) {
			targetConnection = new SMYLDMySQLConnection(incSettings);
		}
		return targetConnection;
	}
}
