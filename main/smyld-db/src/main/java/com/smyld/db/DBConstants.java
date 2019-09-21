package com.smyld.db;

public interface DBConstants {
	public static final int DB_ERR_UNIQUE_CONSTRAINT = 1;

	public static final int DB_ERR_CONNECTION_RESET = 2;

	public static final String DB_ERR_CONNECTION_RESET_TEXT = "Connection reset by peer";

	public static final long DB_ERR_UNKNOWN = 999999999;

	public static final String DB_VENDOR_ORACLE = "oracle";
	public static final String DB_VENDOR_MYSQL = "mysql";
	public static final String DB_VENDOR_SYBASE = "sybase";
	public static final String DB_VENDOR_DB2 = "db2";
	public static final String DB_VENDOR_SQL_SERVER = "sqlserver";

	public static final String DB_VENDOR_DEFAULT = DB_VENDOR_ORACLE;

}
