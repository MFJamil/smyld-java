package com.smyld.util.alias;

import java.util.HashMap;
import com.smyld.db.DBConnection;
import com.smyld.db.DBSettings;
import static com.smyld.util.alias.AliasConstants.*;

public class DBAliasSource extends AliasSource {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,DBAliasTable> tables;
	HashMap<String,String> schemas;
	DBSettings dbConnSettings;
	DBConnection smyldDBConnection;

	public DBAliasSource() {
		super(ALIAS_SRC_TYPE_DB);
	}

	public DBSettings getDbConnSettings() {
		return dbConnSettings;
	}

	public void setDbConnSettings(DBSettings dbConnSettings) {
		this.dbConnSettings = dbConnSettings;
	}

	public HashMap<String,DBAliasTable> getTables() {
		return tables;
	}

	public void setTables(HashMap<String,DBAliasTable> tables) {
		this.tables = tables;
	}

	public void fillSchemas() {
		if ((tables != null) && (tables.size() > 0)) {
			schemas = new HashMap<String,String>();
			for (DBAliasTable curTable : tables.values()) {
				String curSchema = curTable.getSchema();
				if (!schemas.containsKey(curSchema))
					schemas.put(curSchema, curSchema);
			}
		}
	}

	public HashMap<String,String> getSchemaList() {
		if ((schemas != null) && (schemas.size() > 0))
			return schemas;
		return null;
	}

	public DBConnection getRs2DBConnection() {
		return smyldDBConnection;
	}

	public void setRs2DBConnection(DBConnection smyldDBConnection) {
		this.smyldDBConnection = smyldDBConnection;
	}
}
