package org.smyld.db.oracle;

import org.smyld.SMYLDObject;
import org.smyld.db.SQL;

public class OraSqlStatements extends SMYLDObject implements SQL, OraConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OraSqlStatements() {
	}

	public static final String SEL_user_source_SINGLE_SOURCE = SEL
			+ COL_USER_SOURCE_TEXT + FRM + VIEW_USER_SOURCE + WHR
			+ COL_USER_SOURCE_TYPE + EQM + AND + COL_USER_SOURCE_NAME + EQM
			+ ORD + COL_USER_SOURCE_LINE;
	public static final String SEL_user_source_SINGLE_PACKAGE = SEL
			+ COL_USER_SOURCE_TEXT + COM + COL_USER_SOURCE_TYPE + FRM
			+ VIEW_USER_SOURCE + WHR + COL_USER_SOURCE_NAME + EQM + ORD
			+ COL_USER_SOURCE_TYPE + COM + COL_USER_SOURCE_LINE;

	public static final String SEL_user_source_ALL_TYPE_RECORDS = SEL
			+ COL_USER_SOURCE_NAME + FRM + VIEW_USER_SOURCE + WHR
			+ COL_USER_SOURCE_TYPE + EQM;
	public static final String SEL_user_dependencies_SINGLE_PACKAGE = SEL
			+ COL_USER_DPNC_REF_TYPE + COM + COL_USER_DPNC_REF_NAME + COM
			+ COL_USER_DPNC_DPNC_TYPE + FRM + VIEW_USER_DEPENDENCIES + WHR
			+ COL_USER_DPNC_NAME + EQM + AND + COL_USER_DPNC_TYPE + IN + PO
			+ SQ + SOURCE_TYPE_PACKAGE + SQ + COM + SQ
			+ SOURCE_TYPE_PACKAGE_BODY + SQ + PC;
	public static final String SEL_user_dependencies_SINGLE_TABLE = SEL
			+ COL_USER_DPNC_REF_TYPE + COM + COL_USER_DPNC_REF_NAME + COM
			+ COL_USER_DPNC_DPNC_TYPE + FRM + VIEW_USER_DEPENDENCIES + WHR
			+ COL_USER_DPNC_NAME + EQM + AND + COL_USER_DPNC_TYPE + IN + PO
			+ SQ + SOURCE_TYPE_TABLE + SQ + PC;

	public static final String SEL_user_dependencies_SINGLE_VIEW = SEL
			+ COL_USER_DPNC_REF_TYPE + COM + COL_USER_DPNC_REF_NAME + COM
			+ COL_USER_DPNC_DPNC_TYPE + FRM + VIEW_USER_DEPENDENCIES + WHR
			+ COL_USER_DPNC_REF_OWNER + EQM + AND + COL_USER_DPNC_NAME + EQM
			+ AND + COL_USER_DPNC_TYPE + IN + PO + SQ + SOURCE_TYPE_VIEW + SQ
			+ PC;

	public static final String SEL_user_dependencies_SINGLE_Object = SEL
			+ COL_USER_DPNC_REF_TYPE + COM + COL_USER_DPNC_REF_NAME + COM
			+ COL_USER_DPNC_DPNC_TYPE + FRM + VIEW_USER_DEPENDENCIES + WHR
			+ COL_USER_DPNC_REF_OWNER + EQM + AND + COL_USER_DPNC_NAME + EQM
			+ AND + COL_USER_DPNC_TYPE + IN + PO + QUM + PC;

	public static final String SEL_user_tables_SINGLE_RECORD = SELA
			+ VIEW_USER_TABLES + WHR + COL_USER_TABLES_TABLE_NAME + EQM;
	public static final String SEL_user_table_spaces_SINGLE_RECORD = SELA
			+ VIEW_USER_TABLESPACES + WHR + COL_USER_TABLESPACE_TABLESPACE_NAME
			+ EQM;
	public static final String SEL_dba_directories_SINGLE_RECORD = SELA
			+ VIEW_DBA_DIRS + WHR + COL_DBA_DIRS_DIRECTORY_NAME + EQM;

}
