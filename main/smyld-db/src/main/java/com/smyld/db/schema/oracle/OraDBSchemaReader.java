package com.smyld.db.schema.oracle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import com.smyld.db.DBConnection;
import com.smyld.db.DBErrorHandler;
import com.smyld.db.oracle.OraConstants;
import com.smyld.db.oracle.OraSqlStatements;
import com.smyld.db.schema.DBSchemaHandler;
import com.smyld.db.schema.DBSchemaReader;
import com.smyld.db.schema.ProceduresPackage;
import com.smyld.db.schema.StoredProcedure;
import com.smyld.db.schema.Table;
import com.smyld.io.SMYLDFileOutputStream;
import com.smyld.text.TextUtil;
import com.smyld.util.jar.SMYLDJARWriter;

public class OraDBSchemaReader extends DBSchemaReader implements OraConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DBConnection conn;
	PreparedStatement stPackage;
	PreparedStatement stSource;
	PreparedStatement stPackageDpnd;
	PreparedStatement stTable;

	// Temporary referenced objects
	SMYLDFileOutputStream fout;
	HashMap<String,String> refTypes;
	HashMap<String,String> refObjects = new HashMap<String,String>();

	public OraDBSchemaReader(DBErrorHandler e, DBConnection connection) {
		super(e, connection);
	}

	@Override
	public void readSchemaStoredProcedures(DBConnection conn) throws Exception {
		// super.readSchemaStoredProcedures(conn);
		this.conn = conn;
		if ((dbStoredProcedures != null) && (dbStoredProcedures.size() > 0)) {
			@SuppressWarnings("unused")
			PreparedStatement st = dbConnection
					.prepareStatement(OraSqlStatements.SEL_user_source_SINGLE_SOURCE);
			for (Object curProc : dbStoredProcedures) {
				if (curProc instanceof StoredProcedure) {
					StoredProcedure sp = (StoredProcedure) curProc;
					@SuppressWarnings("unused")
					Vector<String> body = getSourceText(sp.getName(),
							SOURCE_TYPE_PROCEDURE);
				} else {
					ProceduresPackage pack = (ProceduresPackage) curProc;
					@SuppressWarnings("unused")
					Vector<String> body = getSourceText(pack.getName(),
							SOURCE_TYPE_PACKAGE_BODY);
				}
			}
		}
	}

	@Override
	protected Table createTable() {
		return new OraTable();
	}

	@Override
	public void readSchemaTables(HashMap<String,String> tables) throws Exception {
		super.readSchemaTables(tables);  
		if ((dbTables != null) && (dbTables.size() > 0)) {
			for (Table parentTable : dbTables.values()) {
				OraTable curTable = (OraTable) parentTable;
				ResultSet rsTable = dbUtility.getSingleParamSQL(curTable
						.getName(),
						OraSqlStatements.SEL_user_tables_SINGLE_RECORD);
				if (rsTable.next()) {
					OraTableSpace newTableSpace = new OraTableSpace();
					newTableSpace.setName(rsTable
							.getString(COL_USER_TABLESPACE_TABLESPACE_NAME));
					newTableSpace.setInitialExtent(rsTable
							.getInt(COL_USER_TABLESPACE_INITIAL_EXTENT));
					newTableSpace.setNextExtent(rsTable
							.getInt(COL_USER_TABLESPACE_NEXT_EXTENT));
					newTableSpace.setMinExtent(rsTable
							.getInt(COL_USER_TABLESPACE_MIN_EXTENTS));
					newTableSpace.setMaxExtent(rsTable
							.getInt(COL_USER_TABLESPACE_MAX_EXTENTS));
					newTableSpace.setSpaceManagement(rsTable
							.getString(COL_USER_TABLESPACE_SEG_SPACE_MNG));
					newTableSpace.setLogging("LOGGING".equals(rsTable
							.getString(COL_USER_TABLESPACE_LOGGING)));
					curTable.setTableSpace(newTableSpace);
				}
			}
		}
	}

	@SuppressWarnings("unused")
	private ResultSet getTable(String tableName) throws Exception {
		if (stTable == null)
			stTable = dbConnection
					.prepareStatement(OraSqlStatements.SEL_user_tables_SINGLE_RECORD);
		stTable.setString(1, tableName);
		return stTable.executeQuery();

	}

	public void exportStoredProcedures(Vector<String> procedures, String exportFile)
			throws Exception {
		fout = new SMYLDFileOutputStream("d:/temp/dependencies.txt");
		fout.setWithIndent(true);
		refTypes = new HashMap<String,String>();
		SMYLDJARWriter writer = new SMYLDJARWriter(exportFile);

		//PreparedStatement st = dbConnection
		//		.prepareStatement(OraSqlStatements.SEL_user_source_SINGLE_PACKAGE);
		for (String pckgName : procedures) {
			pckgName = pckgName.toUpperCase();
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			exportPackage(pckgName, bout);
			writer.addStreamFile(new ByteArrayInputStream(bout.toByteArray()),
					pckgName + ".pkg");
		}
		HashMap<String,String> tableList = getReferencedTables(procedures);
		ByteArrayOutputStream xmlSchema = new ByteArrayOutputStream();
		DBSchemaHandler handler = new DBSchemaHandler(smyldDBConnection);
		handler.generateXMLSchemaDocument(tableList, xmlSchema);
		writer.addStreamFile(new ByteArrayInputStream(xmlSchema.toByteArray()),
				"Schema.xml");
		writer.close();

		// Temp
		fout.writeln("All Referenced Types :", 0);
		fout.setIndentLevel(1);
		for (String curRef : refTypes.keySet()) {
			fout.writeln(curRef);
		}

	}

	public void exportPackage(String packageName, OutputStream out)
			throws Exception {
		Vector<String> pckLines = getSourceText(packageName, SOURCE_TYPE_PACKAGE);
		Vector<String> bodLines = getSourceText(packageName, SOURCE_TYPE_PACKAGE_BODY);
		out.write(("******* SMYLD Package Export Utility *******" + OS_NEW_LINE)
				.getBytes());
		out.write(("* Package : " + packageName + OS_NEW_LINE).getBytes());
		out.write(("* Date    : " + new Date().toString() + OS_NEW_LINE)
				.getBytes());
		out.write(("******************************************" + OS_NEW_LINE)
				.getBytes());
		out.write((OS_NEW_LINE + "####### Header #######" + OS_NEW_LINE)
				.getBytes());
		addCodeLines(out, pckLines);
		out.write((OS_NEW_LINE + "####### Body   #######" + OS_NEW_LINE)
				.getBytes());
		addCodeLines(out, bodLines);
	}

	private void addCodeLines(OutputStream out, Vector<String> lines) throws Exception {
		if (lines == null)
			return;
		for (String curLine : lines) {
			out.write((curLine + OS_NEW_LINE).getBytes());
		}
	}

	public HashMap<String,String> getReferencedTables(Vector<String> procedures) throws Exception {
		HashMap<String,String> dependencies = new HashMap<String,String>();
		@SuppressWarnings("unused")
		PreparedStatement st = dbConnection
				.prepareStatement(OraSqlStatements.SEL_user_source_SINGLE_PACKAGE);
		for (String pckgName : procedures) {
			pckgName = pckgName.toUpperCase();
			// analyseDependency(pckgName);
			readDependency(pckgName, dependencies);
		}
		System.out.println("Total tables referenced : " + dependencies.size());
		return dependencies;
	}

	public void analyseDependency(String procedure) throws Exception {
		String code = getSourceTextAsString(procedure, SOURCE_TYPE_PACKAGE_BODY);
		Vector<String> sects = TextUtil.getAllSectionText(code, "from", "where");
		if ((sects != null) && (sects.size() > 0)) {
			for (String curLine : sects) {
				System.out.println(curLine);
			}
		}
	}

	public void readDependency(String procedure, HashMap<String,String> dependencies)
			throws Exception {
		// System.out.println(OraSqlStatements.SEL_user_dependencies_SINGLE_PACKAGE);
		int refNo = 0;
		refObjects.put(procedure, procedure);
		// if (stPackageDpnd==null) stPackageDpnd =
		// dbConnection.prepareStatement(OraSqlStatements.SEL_user_dependencies_SINGLE_PACKAGE);
		PreparedStatement stDP = dbConnection
				.prepareStatement(OraSqlStatements.SEL_user_dependencies_SINGLE_PACKAGE);
		stDP.setString(1, procedure);
		fout.setIndentLevel(1);
		fout.writeln(procedure + ":");
		fout.setIndentLevel(2);
		ResultSet rs = stDP.executeQuery();
		while (rs.next()) {
			String refType = rs.getString(COL_USER_DPNC_REF_TYPE);
			String refName = rs.getString(COL_USER_DPNC_REF_NAME);
			//String dpType = rs.getString(COL_USER_DPNC_DPNC_TYPE);

			System.out.println("Package (" + procedure + ") refrencing "
					+ refType + " (" + refName + ")");
			if (!refTypes.containsKey(refType))
				refTypes.put(refType, refType);
			if (refType.equals(SOURCE_TYPE_TABLE)) {
				dependencies.put(refName, refName);
				readDependency(refName, SOURCE_TYPE_TABLE, dependencies);
				refNo++;
			} else if (refType.equals(SOURCE_TYPE_VIEW)) {
				readDependency(refName, SOURCE_TYPE_VIEW, dependencies);
			} else if ((refType.equals(SOURCE_TYPE_PACKAGE))
					&& (!refName.equals("STANDARD"))
					&& (!refType.equals(SOURCE_TYPE_NON_EXISTENT))
					&& (!refName.equals(procedure)) && (!isReferenced(refName))) {
				readDependency(refName, dependencies);

			}
		}
		fout.setIndentLevel(3);
		fout.writeln(procedure + " referenced (" + refNo + ") table");

	}

	private boolean isReferenced(String objectName) {
		return refObjects.containsKey(objectName);
	}

	public void readDependency(String objectName, String objectType,
			HashMap<String,String> dependencies) throws Exception {
		// System.out.println(OraSqlStatements.SEL_user_dependencies_SINGLE_PACKAGE);
		refObjects.put(objectName, objectName);
		int refNo = 0;
		PreparedStatement stDpnd = dbConnection
				.prepareStatement(OraSqlStatements.SEL_user_dependencies_SINGLE_PACKAGE);
		stDpnd.setString(1, objectName);
		stDpnd.setString(1, objectType);
		fout.setIndentLevel(1);
		fout.writeln(objectName + ":");
		fout.setIndentLevel(2);
		ResultSet rs = stDpnd.executeQuery();
		while (rs.next()) {
			String refType = rs.getString(COL_USER_DPNC_REF_TYPE);
			String refName = rs.getString(COL_USER_DPNC_REF_NAME);
			@SuppressWarnings("unused")
			String dpType = rs.getString(COL_USER_DPNC_DPNC_TYPE);
			System.out.println(objectType + " (" + objectName + ") refrencing "
					+ refType + " (" + refName + ")");
			if (!refTypes.containsKey(refType))
				refTypes.put(refType, refType);
			// fout.writeln(refType + ":" + refName + "," + dpType);
			if (refType.equals(SOURCE_TYPE_TABLE)) {
				dependencies.put(refName, refName);
				readDependency(refName, SOURCE_TYPE_TABLE, dependencies);
				refNo++;
			} else if ((refType.equals(SOURCE_TYPE_PACKAGE))
					&& (!refName.equals("STANDARD"))
					&& (!refType.equals(SOURCE_TYPE_NON_EXISTENT))) {
				readDependency(refName, dependencies);
			} else if (refType.equals(SOURCE_TYPE_TYPE)) {
				readDependency(refName, SOURCE_TYPE_VIEW, dependencies);

			}
		}
		fout.setIndentLevel(3);
		fout.writeln(objectName + " referenced (" + refNo + ") table");

	}

	private Vector<String> getSourceText(String name, String type) throws Exception {
		if (stSource == null)
			stSource = dbConnection
					.prepareStatement(OraSqlStatements.SEL_user_source_SINGLE_SOURCE);
		Vector<String> textLines = null;
		stSource.setString(1, type);
		stSource.setString(2, name);
		ResultSet rs = stSource.executeQuery();
		while (rs.next()) {
			if (textLines == null)
				textLines = new Vector<String>();
			textLines.add(rs.getString(1));
		}
		return textLines;

	}

	private String getSourceTextAsString(String name, String type)
			throws Exception {
		if (stSource == null)
			stSource = conn.getConnection().prepareStatement(
					OraSqlStatements.SEL_user_source_SINGLE_SOURCE);
		StringBuffer buffer = new StringBuffer();
		stSource.setString(1, type);
		stSource.setString(2, name);
		ResultSet rs = stSource.executeQuery();
		while (rs.next()) {
			buffer.append(rs.getString(1));
		}
		return buffer.toString();

	}

}
