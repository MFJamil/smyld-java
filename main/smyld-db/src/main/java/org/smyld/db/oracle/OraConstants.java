package org.smyld.db.oracle;

public interface OraConstants {

	public static final int ORACLE_ERR_UNIQUE_CONSTRAINT = 900000;
	public static final int ORACLE_ERR_INVALID_LOGON = 1017;
	public static final int ORACLE_ERR_IMMEDIATE_SHUTDOWN = 1089;
	public static final int ORACLE_ERR_INIT_OR_SHUT_IN_PROG = 1033;
	public static final int ORACLE_ERR_ORA_NOT_AVAILABLE = 1034;
	public static final int ORACLE_ERR_CONNECTION_RESET = 17002;

	public static final int ORACLE_ERR_PACKAGE_STATE_DISCARDED = 4068;
	public static final int ORACLE_ERR_PACKAGE_STATE_INVALIDATED = 4061;
	public static final int ORACLE_ERR_PACKAGE_HAS_ERROR = 4063;
	public static final int ORACLE_ERR_NOT_EXEC_ALTERED_DROP_PACKAGE = 4065;
	public static final int ORACLE_ERR_UNIT_PROGRAM_NOT_FOUND = 6508;

	public static final String ORA_ENV_VAR_NAME = "ORACLE_HOME";
	public static final String ORA_PATH_NAME_NET_ADMIN = "/network/admin";
	public static final String ORA_FILE_NAME_TNS_NAMES = "tnsnames.ora";

	public static final String VIEW_USER_SOURCE = "USER_SOURCE";
	public static final String VIEW_USER_DEPENDENCIES = "USER_DEPENDENCIES";
	public static final String VIEW_USER_TABLES = "USER_TABLES";
	public static final String VIEW_USER_TABLESPACES = "USER_TABLESPACES";

	/*
	 * Types access views USER_TYPE_ATTRS for fields inside the type
	 * USER_TYPE_VERSIONS for the code lines of the type USER_TYPES for the type
	 * declaration USER_TRIGGERS USER_SEQUENCES USER_QUEUES
	 */

	/*
	 * 
	 * public static final String VIEW_USER_ = "USER_"; public static final
	 * String VIEW_USER_ = "USER_"; public static final String VIEW_USER_ =
	 * "USER_"; public static final String VIEW_USER_ = "USER_";
	 * 
	 */
	public static final String VIEW_USER_OBJECTS = "USER_OBJECTS";
	public static final String VIEW_USER_INDEXES = "USER_INDEXES";
	public static final String VIEW_USER_CONSTRAINTS = "USER_CONSTRAINTS";

	public static final String VIEW_DBA_DIRS = "DBA_DIRECTORIES";
	public static final String COL_DBA_DIRS_OWNER = "OWNER";
	public static final String COL_DBA_DIRS_DIRECTORY_NAME = "DIRECTORY_NAME";
	public static final String COL_DBA_DIRS_DIRECTORY_PATH = "DIRECTORY_PATH";

	public static final String COL_USER_SOURCE_NAME = "NAME";
	public static final String COL_USER_SOURCE_TYPE = "TYPE";
	public static final String COL_USER_SOURCE_LINE = "LINE";
	public static final String COL_USER_SOURCE_TEXT = "TEXT";

	public static final String COL_USER_DPNC_NAME = "NAME";
	public static final String COL_USER_DPNC_TYPE = "TYPE";
	public static final String COL_USER_DPNC_REF_OWNER = "REFERENCED_OWNER";
	public static final String COL_USER_DPNC_REF_NAME = "REFERENCED_NAME";
	public static final String COL_USER_DPNC_REF_TYPE = "REFERENCED_TYPE";
	public static final String COL_USER_DPNC_REF_LNK_NAME = "REFERENCED_LINK_NAME";
	public static final String COL_USER_DPNC_SCHEMAID = "SCHEMAID";
	public static final String COL_USER_DPNC_DPNC_TYPE = "DEPENDENCY_TYPE";

	public static final String SOURCE_TYPE_FUNCTION = "FUNCTION";
	public static final String SOURCE_TYPE_JAVA_CLASS = "JAVA CLASS";
	public static final String SOURCE_TYPE_NON_EXISTENT = "NON-EXISTENT";
	public static final String SOURCE_TYPE_PACKAGE = "PACKAGE";
	public static final String SOURCE_TYPE_PACKAGE_BODY = "PACKAGE BODY";
	public static final String SOURCE_TYPE_PROCEDURE = "PROCEDURE";
	public static final String SOURCE_TYPE_SEQUENCE = "SEQUENCE";
	public static final String SOURCE_TYPE_SYNONYM = "SYNONYM";
	public static final String SOURCE_TYPE_TRIGGER = "TRIGGER";
	public static final String SOURCE_TYPE_TABLE = "TABLE";
	public static final String SOURCE_TYPE_TYPE = "TYPE";
	public static final String SOURCE_TYPE_VIEW = "VIEW";

	public static final String COL_USER_TABLES_TABLE_NAME = "TABLE_NAME";
	public static final String COL_USER_TABLES_TABLESPACE_NAME = "TABLESPACE_NAME";
	public static final String COL_USER_TABLES_PCT_FREE = "PCT_FREE";
	public static final String COL_USER_TABLES_INITIAL_EXTENT = "INITIAL_EXTENT";
	public static final String COL_USER_TABLES_NEXT_EXTENT = "NEXT_EXTENT";
	public static final String COL_USER_TABLES_MIN_EXTENTS = "MIN_EXTENTS";
	public static final String COL_USER_TABLES_MAX_EXTENTS = "MAX_EXTENTS";
	public static final String COL_USER_TABLES_LOGGING = "LOGGING";

	public static final String COL_USER_TABLESPACE_TABLESPACE_NAME = "TABLESPACE_NAME";
	public static final String COL_USER_TABLESPACE_BLOCK_SIZE = "BLOCK_SIZE";
	public static final String COL_USER_TABLESPACE_INITIAL_EXTENT = "INITIAL_EXTENT";
	public static final String COL_USER_TABLESPACE_NEXT_EXTENT = "NEXT_EXTENT";
	public static final String COL_USER_TABLESPACE_MIN_EXTENTS = "MIN_EXTENTS";
	public static final String COL_USER_TABLESPACE_MAX_EXTENTS = "MAX_EXTENTS";
	public static final String COL_USER_TABLESPACE_LOGGING = "LOGGING";
	public static final String COL_USER_TABLESPACE_SEG_SPACE_MNG = "SEGMENT_SPACE_MANAGEMENT";

}
