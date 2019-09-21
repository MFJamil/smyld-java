package com.smyld.db;

import java.util.HashMap;
import java.util.Vector;

import com.smyld.db.oracle.OraSqlStatements;
import com.smyld.db.oracle.SMYLDOracleConnection;
import com.smyld.db.schema.DBSchemaHandler;
import com.smyld.db.schema.oracle.OraDBSchemaReader;

public class DBSchemaTester {
	public DBSchemaTester() {
		try {
			// testXMLSchemaGenerator();
			// testDBSchema();
			// printSQL();
			testSinglePackage();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}

	}

	public static void main(String[] args) {
		new DBSchemaTester();
	}
	@SuppressWarnings("unused")
	private void printSQL() {
		System.out
				.println(OraSqlStatements.SEL_user_dependencies_SINGLE_PACKAGE);
	}

	@SuppressWarnings("unused")
	private void testXMLSchemaGenerator() throws Exception {
		DBSchemaHandler handler = new DBSchemaHandler(getConnection());
		HashMap<String,String> tables = new HashMap<String,String>();
		tables.put("ISO_8583_STRUCTURS", "ISO_8583_STRUCTURS");
		tables.put("INT_PROCESS_LOG", "INT_PROCESS_LOG");
		tables.put("INT_PROCESS_MESSAGE_LOG", "INT_PROCESS_MESSAGE_LOG");
		try {
			handler.generateXMLSchemaDocument(tables, "d:/temp/schema.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void testSinglePackage() throws Exception {
		// System.out.println(OraSqlStatements.SEL_user_source_SINGLE_SOURCE);
		OraDBSchemaReader reader = new OraDBSchemaReader(null, getConnection());
		try {
			Vector<String> stProc = new Vector<String>();
			stProc.add("BW_CMDL_EXEC");
			reader.exportStoredProcedures(stProc, "d:/temp/PLSQL.zip");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// reader.readSchemaStoredProcedures(getConnection(),);
	}
	@SuppressWarnings("unused")
	private void testDBSchema() throws Exception {
		// System.out.println(OraSqlStatements.SEL_user_source_SINGLE_SOURCE);
		OraDBSchemaReader reader = new OraDBSchemaReader(null, getConnection());
		try {
			Vector<String> stProc = fillHVEProcedures();
			reader.exportStoredProcedures(stProc, "d:/temp/PLSQL.zip");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// reader.readSchemaStoredProcedures(getConnection(),);
	}

	public static SMYLDOracleConnection getConnection() throws Exception {
		SMYLDOracleConnection conn = new SMYLDOracleConnection(
				SMYLDOracleConnection.DRIVER_THIN, DB_USER_NAME, DB_USER_PASS,
				DB_SERVICE_NAME, DB_SERVICE_PORT, DB_HOST_NAME);
		conn.getSettings().setSchemaOwner("OMNI");
		return conn;
	}

	private Vector<String> fillHVEProcedures() {
		Vector<String> stProc = new Vector<String>();
		stProc.add("Acct_Ageing");
		stProc.add("Batch_In");
		stProc.add("Bw3com");
		stProc.add("Bw3sql");
		stProc.add("Bw3_Acct");
		stProc.add("Bw3_Global");
		stProc.add("Bw3_Standard");
		stProc.add("Bw3_Tran");
		stProc.add("Bw_Aq");
		stProc.add("Bw_Pmon");
		stProc.add("Bw_Util");
		stProc.add("Cmdl_Exec");
		stProc.add("Cmn_Proc");
		stProc.add("Cmn_Scrp");
		stProc.add("Cmn_Type");
		stProc.add("Const");
		stProc.add("Const2");
		stProc.add("Dynsql");
		stProc.add("Eurocurr");
		stProc.add("Exp_Tran");
		stProc.add("External_Process");
		stProc.add("Hashing");
		stProc.add("Int_Posting");
		stProc.add("Inward_Console");
		stProc.add("Inw_Func");
		stProc.add("Lib_Binl");
		stProc.add("Lib_Chrg");
		stProc.add("Lib_Config");
		stProc.add("Lib_Crco32");
		stProc.add("Lib_Crin32");
		stProc.add("Lib_Date");
		stProc.add("Lib_Ddl");
		stProc.add("Lib_Fx");
		stProc.add("Lib_Gl");
		stProc.add("Lib_Incl");
		stProc.add("Lib_Int");
		stProc.add("Lib_Merch");
		stProc.add("Lib_Merch_Chrg");
		stProc.add("Lib_Merch_Chrg2");
		stProc.add("Lib_Merch_Priv");
		stProc.add("Lib_Messages");
		stProc.add("Lib_Mis2");
		stProc.add("Lib_Misc");
		stProc.add("Lib_Plsql");
		stProc.add("Lib_Proc_Log");
		stProc.add("Lib_Recl");
		stProc.add("Lib_Scri");
		stProc.add("Lib_Seq");
		stProc.add("Lib_Temptables");
		stProc.add("Lib_Valb");
		stProc.add("Lib_Valb2");
		stProc.add("Lib_Xfcmerchant");
		stProc.add("Merch_Proc");
		stProc.add("Omni_Create_Dta");
		stProc.add("Outward_Console");
		stProc.add("Phase_2_3");
		stProc.add("Prc_Res");
		stProc.add("Process_Control");
		stProc.add("Process_Select");
		stProc.add("Switches");
		return stProc;
	}

	// *local DB
	public static final String DB_USER_NAME = "bw3";
	public static final String DB_USER_PASS = "bw3data";
	public static final String DB_HOST_NAME = "localhost";
	public static final String DB_SERVICE_PORT = "1521";
	public static final String DB_SERVICE_NAME = "smyldbw";
	// */
	/*
	 * Omintest on network public static final String DB_USER_NAME = "omni";
	 * public static final String DB_USER_PASS = "bw3data"; public static final
	 * String DB_HOST_NAME = "192.168.12.103"; public static final String
	 * DB_SERVICE_PORT = "1521"; public static final String DB_SERVICE_NAME =
	 * "omnitest"; //
	 */

}
