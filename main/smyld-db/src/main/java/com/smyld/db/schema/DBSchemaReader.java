package com.smyld.db.schema;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;

import com.smyld.db.DBConnection;
import com.smyld.db.DBErrorHandler;
import com.smyld.db.SMYLDDataBaseHandler;

public class DBSchemaReader extends SMYLDDataBaseHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected DatabaseMetaData dbMetaData;
	protected HashMap<String,Table> dbTables;
	protected Vector<Object> dbStoredProcedures;
	protected HashMap<String,ProceduresPackage> dbPackages;

	public DBSchemaReader(DBErrorHandler e, DBConnection conn) {
		super(e, conn);
	}

	public void readSchemaTables(HashMap<String,String> tables) throws Exception {
		dbMetaData = dbConnection.getMetaData();
		String schemaOwner = smyldDBConnection.getSettings().getSchemaOwner();
		ResultSet rsTables = dbMetaData
				.getTables(null, schemaOwner, null, null);
		// initCollection(dbTables);
		dbTables = new HashMap<String,Table>();
		while (rsTables.next()) {
			String curTableName = rsTables.getString("TABLE_NAME");
			if (tables.containsKey(curTableName)) {
				Table newTable = createTable();
				newTable.setName(curTableName);
				ResultSet rsTableCols = dbMetaData.getColumns(null,
						schemaOwner, curTableName, null);
				newTable.setCols(readCols(rsTableCols));
				ResultSet rsKeyCols = dbMetaData.getPrimaryKeys(null,
						schemaOwner, curTableName);
				newTable.setPrimaryKeys(readPKeys(rsKeyCols));
				ResultSet rsIKeys = dbMetaData.getImportedKeys(null,
						schemaOwner, curTableName);
				newTable.setForeignKeys(readFKeys(rsIKeys));
				dbTables.put(curTableName, newTable);
			}
		}
		rsTables.close();
	}

	protected Table createTable() {
		return new Table();
	}

	private HashMap<String,SchemaObject> readPKeys(ResultSet rsKeyCols) throws Exception {
		HashMap<String,SchemaObject>  keys = null;
		while (rsKeyCols.next()) {
			if (keys == null)
				keys = new HashMap<String,SchemaObject>();
			PrimaryKey newKey = new PrimaryKey();
			newKey.setName(rsKeyCols.getString("PK_NAME"));
			newKey.setSequence(rsKeyCols.getInt("KEY_SEQ"));
			newKey.setColumnName(rsKeyCols.getString("COLUMN_NAME"));
			keys.put(newKey.getColumnName(), newKey);
		}
		rsKeyCols.close();
		return keys;
	}

	private HashMap<String,SchemaObject> readFKeys(ResultSet rsKeyCols) throws Exception {
		HashMap<String,SchemaObject> keys = null;
		while (rsKeyCols.next()) {
			if (keys == null)
				keys = new HashMap<String,SchemaObject>();
			ForeignKey newKey = new ForeignKey();
			newKey.setName(rsKeyCols.getString("FK_NAME"));
			newKey.setTable(rsKeyCols.getString("PKTABLE_NAME"));
			newKey.setColumnName(rsKeyCols.getString("PKCOLUMN_NAME"));
			PrimaryKey ref = new PrimaryKey();
			ref.setColumnName(rsKeyCols.getString("FKCOLUMN_NAME"));
			newKey.setPrimaryKey(ref);
			keys.put(newKey.getName(), newKey);
		}
		rsKeyCols.close();
		return keys;
	}

	private HashMap<String,SchemaObject> readCols(ResultSet rsTableCols) throws Exception {
		HashMap<String,SchemaObject> cols = null;
		while (rsTableCols.next()) {
			if (cols == null)
				cols = new HashMap<String,SchemaObject>();
			TableColumn newCol = new TableColumn();
			newCol.setName(rsTableCols.getString("COLUMN_NAME"));
			newCol.setType(rsTableCols.getString("TYPE_NAME"));
			newCol.setSize(rsTableCols.getInt("COLUMN_SIZE"));
			String colNull = rsTableCols.getString("IS_NULLABLE");
			newCol.setNullable("YES".equals(colNull));
			cols.put(newCol.getName(), newCol);
		}
		rsTableCols.close();
		return cols;
	}

	/*	
	private void initCollection(HashMap collection) {
		if (collection == null)
			collection = new HashMap();
		else
			collection.clear();
	}*/
	/*
	private void initCollection(Vector collection) {
		if (collection == null)
			collection = new Vector();
		else
			collection.removeAllElements();
	}*/

	public void readSchemaStoredProcedures(DBConnection conn) throws Exception {
		dbMetaData = dbConnection.getMetaData();
		ResultSet rs = dbMetaData.getProcedures(null, smyldDBConnection
				.getSettings().getSchemaOwner(), null);
		//String lastPack = null;
		if (dbPackages == null)
			dbPackages = new HashMap<String,ProceduresPackage>();
		else
			dbPackages.clear();
		if (dbStoredProcedures == null)
			dbStoredProcedures = new Vector<Object>();
		else
			dbStoredProcedures.removeAllElements();
		ProceduresPackage curPackage = null;
		while (rs.next()) {
			String procPack = rs.getString("PROCEDURE_CAT");

			if (procPack != null) {
				if (dbPackages.containsKey(procPack)) {
					curPackage = (ProceduresPackage) dbPackages.get(procPack);
				} else {
					curPackage = new ProceduresPackage();
					curPackage.setName(procPack);
					dbPackages.put(procPack, curPackage);
				}
				curPackage.addProcedure(creatProcedure(rs, procPack));
			} else {
				dbStoredProcedures.add(creatProcedure(rs, procPack));
			}
		}
		if (((dbPackages != null) && (dbPackages.size() > 0))
				&& ((dbStoredProcedures != null) && (dbStoredProcedures.size() > 0))) {
			dbStoredProcedures.addAll(dbPackages.values());
		}

	}

	private StoredProcedure creatProcedure(ResultSet rs, String packName)
			throws Exception {
		StoredProcedure newProcedure = new StoredProcedure();
		newProcedure.setName(rs.getString("PROCEDURE_NAME"));
		newProcedure.setPack(packName);
		ResultSet rsCols = dbMetaData.getProcedureColumns(null, smyldDBConnection
				.getSettings().getSchemaOwner(), newProcedure.getName(), null);
		newProcedure.setParams(readProcCols(rsCols));
		return newProcedure;
	}

	private HashMap<String,ProcedureColumn> readProcCols(ResultSet rsProcCols) throws Exception {
		HashMap<String,ProcedureColumn> cols = null;
		while (rsProcCols.next()) {
			if (cols == null)
				cols = new HashMap<String,ProcedureColumn>();
			ProcedureColumn newCol = new ProcedureColumn();
			newCol.setName(rsProcCols.getString("COLUMN_NAME"));
			newCol.setType(rsProcCols.getString("TYPE_NAME"));
			newCol.setDirection(rsProcCols.getInt("COLUMN_TYPE"));
			newCol.setSize(rsProcCols.getInt("LENGTH"));
			String colNull = rsProcCols.getString("NULLABLE");
			newCol.setNullable("1".equals(colNull));
			cols.put(newCol.getName(), newCol);
		}
		rsProcCols.close();
		return cols;
	}

	public HashMap<String,Table> getTables() {
		return dbTables;
	}

	public Vector<Object> getStoredProcedures() {
		return dbStoredProcedures;
	}
}
