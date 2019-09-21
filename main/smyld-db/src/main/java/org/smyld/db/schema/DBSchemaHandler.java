package org.smyld.db.schema;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.smyld.SMYLDObject;
import org.smyld.db.DBConnection;


public class DBSchemaHandler extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DBConnection dbConn;
	DatabaseMetaData dbMetaData;

	FileOutputStream fout;
	boolean debug;

	public DBSchemaHandler(DBConnection conn) {
		this.dbConn = conn;
	}

	public void generateXMLSchemaDocument(HashMap<String,String> tables, String targetFileName)
			throws Exception {
		generateXMLSchemaDocument(tables, new FileOutputStream(targetFileName));
		/*
		 * DBSchemaReader reader = new DBSchemaReader(); XMLSchemaWriter writer =
		 * new XMLSchemaWriter(); reader.readSchemaTables(conn,tables); HashMap
		 * dbTables = reader.getTables(); if
		 * ((tables!=null)&&(tables.size()>0)){ writer.setTables(dbTables);
		 * writer.writeFileTo(targetFileName); }
		 */
	}

	public void generateXMLSchemaDocument(HashMap<String,String> tables, OutputStream out)
			throws Exception {
		DBSchemaReader reader = new DBSchemaReader(null, dbConn);
		XMLSchemaWriter writer = new XMLSchemaWriter();
		reader.readSchemaTables(tables);
		HashMap<String,Table> dbTables = reader.getTables();
		if ((tables != null) && (tables.size() > 0)) {
			writer.setTables(dbTables);
			writer.writeFileToStream(out);
		}
	}

	public boolean compare(HashMap<String,String> tables, String xmlFileName) throws Exception {
		XMLSchemaReader xmlReader = new XMLSchemaReader();
		DBSchemaReader dbReader = new DBSchemaReader(null, dbConn);
		xmlReader.readSchemaTables(xmlFileName);
		dbReader.readSchemaTables(tables);
		SchemaComparator comparator = new SchemaComparator();
		comparator.setDebug(isDebug());
		return comparator.compare(xmlReader.getTables(), dbReader.getTables());
	}

	// Other methodes have to be mapped to the proper sub class

	public void generateSchemaDocument(HashMap<String,String> tables, String targetFileName)
			throws Exception {
		fout = new FileOutputStream("d:/temp/dbSchema.txt");
		// ((OracleConnection)dbConn.getConnection()).setRemarksReporting(true);
		dbMetaData = dbConn.getConnection().getMetaData();
		// printSchemas(dbMetaData.getSchemas());
		// printProcedures(dbMetaData.getProcedures(null,"BW3",null));

		ResultSet rsCatalogs = dbMetaData.getTables(null, dbConn.getSettings()
				.getSchemaOwner(), null, null);
		// addLine("Tables details :");
		while (rsCatalogs.next()) {
			String curTableName = rsCatalogs.getString("TABLE_NAME");
			if (tables.containsKey(curTableName)) {
				addLine("create table " + curTableName + "(");
				// System.out.println("old table name (" + curTableName + ")
				// while the new Table Name is (" + newTableName + ")");
				// System.out.println("Table (" + curTableName + ") contains the
				// following columns : ");

				ResultSet rsTableCols = dbMetaData.getColumns(null, dbConn
						.getSettings().getSchemaOwner(), curTableName, null);
				// printRSMeta(rsTableCols.getMetaData());
				printCols(rsTableCols);
				ResultSet rsKeyCols = dbMetaData.getPrimaryKeys(null, dbConn
						.getSettings().getSchemaOwner(), curTableName);
				// printRSMeta(rsKeyCols.getMetaData());
				printKeys(rsKeyCols);
				ResultSet rsIKeys = dbMetaData.getImportedKeys(null, dbConn
						.getSettings().getSchemaOwner(), curTableName);
				// printall(rsIKeys);
				// printRSMeta(rsIKeys.getMetaData());
				printFKeys(rsIKeys);
				addLine(")");
			}

		}
		rsCatalogs.close();
	}

	private void printKeys(ResultSet rsKeyCols) throws Exception {
		StringBuffer buffer = new StringBuffer();
		String pkName = null;
		Vector<String> keys = new Vector<String>(0);

		while (rsKeyCols.next()) {
			String keyName = rsKeyCols.getString("COLUMN_NAME");
			int keySeq = rsKeyCols.getInt("KEY_SEQ");
			pkName = rsKeyCols.getString("PK_NAME");
			if (keySeq > keys.size())
				keys.setSize(keySeq);
			keys.add(keySeq, keyName);
		}
		buffer.append("\t\tconstraint ");
		buffer.append(pkName);
		buffer.append(" primary key (");
		Iterator<String> itr = keys.iterator();
		while (itr.hasNext()) {
			String curKey = itr.next();
			if (curKey != null) {
				buffer.append(curKey);
				if (itr.hasNext())
					buffer.append(",");
			}
		}
		buffer.append("),");
		addLine(buffer.toString());
		rsKeyCols.close();
	}

	private void printFKeys(ResultSet rsKeyCols) throws Exception {
		while (rsKeyCols.next()) {
			String keyName = rsKeyCols.getString("PKCOLUMN_NAME");
			String keyF = rsKeyCols.getString("FKCOLUMN_NAME");
			String tableF = rsKeyCols.getString("PKTABLE_NAME");
			String fkName = rsKeyCols.getString("FK_NAME");
			addLine("\t\tconstraint " + fkName + " foreign key (" + keyF
					+ ") reference " + tableF + "(" + keyName + "),");
			// System.out.println(keyName + "," + keyF + "," + fkName);
		}
		rsKeyCols.close();
	}

	private void printCols(ResultSet rsTableCols) throws Exception {
		//ResultSetMetaData rsMeta = rsTableCols.getMetaData();
		// printRSMeta(rsMeta);
		while (rsTableCols.next()) {
			String colName = rsTableCols.getString("COLUMN_NAME");
			String colType = rsTableCols.getString("TYPE_NAME");
			String colSize = rsTableCols.getString("COLUMN_SIZE");
			String colNull = rsTableCols.getString("IS_NULLABLE");

			// String colDType = rsTableCols.getString("COLUMN_DEF");
			// System.out.println(colDType);

			if ("NO".equals(colNull))
				colNull = "\t\tnot null";
			else
				colNull = "";
			addLine("\t" + colName + "\t" + colType + "(" + colSize + ")"
					+ colNull + ",");
		}
		rsTableCols.close();

	}
	
	
	@SuppressWarnings("unused")
	private void printall(ResultSet rs) throws Exception {
		ResultSetMetaData meta = rs.getMetaData();
		while (rs.next()) {
			for (int i = 1; i < meta.getColumnCount() + 1; i++) {
				System.out.print(rs.getString(i) + ",");
			}
			System.out.println("");

		}

	}
	@SuppressWarnings("unused")
	private void printRSMeta(ResultSetMetaData meta) throws Exception {
		int size = meta.getColumnCount();
		for (int i = 1; i < size + 1; i++) {
			System.out.println(meta.getColumnName(i));

		}

	}
	@SuppressWarnings("unused")
	private void printSchemas(ResultSet rs) throws Exception {
		while (rs.next()) {
			System.out.println(rs.getString("TABLE_SCHEM"));
			// System.out.println(rs.getString("TABLE_CATALOG"));
		}
	}
	@SuppressWarnings("unused")
	private void printProcedures(ResultSet rs) throws Exception {
		addLine("Procedures details :");
		String lastPack = null;
		while (rs.next()) {
			String procPack = rs.getString("PROCEDURE_CAT");
			String procName = rs.getString("PROCEDURE_NAME");
			if ((lastPack == null) && (procPack != null)) {
				lastPack = procPack;
			} else if ((lastPack != null) && (!lastPack.equals(procPack))) {
				addLine("\tPackage (" + procPack + ") : ");
				lastPack = procPack;
			}
			if (procPack != null) {
				addLine("\t\tProcedure (" + procName + ")");
			}

			// System.out.println(rs.getString("TABLE_CATALOG"));
		}
	}

	private void addLine(String line) throws Exception {
		fout.write((line + OS_NEW_LINE).getBytes());
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
