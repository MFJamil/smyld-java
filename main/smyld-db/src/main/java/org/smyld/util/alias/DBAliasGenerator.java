package org.smyld.util.alias;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.HashMap;

public class DBAliasGenerator extends AliasGenerator {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DatabaseMetaData dbMetaData;

	public DBAliasGenerator(HashMap<String,AliasClassSettings> classes) {
		super(classes);
	}

	@Override
	public void processSource(AliasSource alSrc, AliasSettings alSettings)
			throws Exception {
		super.processSource(alSrc, alSettings);
		boolean commentAdded = false;
		DBAliasSource dbSrc = (DBAliasSource) alSrc;
		dbMetaData = dbSrc.getRs2DBConnection().getConnection().getMetaData();
		HashMap<String, String> schema = dbSrc.getSchemaList();
		String tablePrefix = (String) alSettings.getPrefixes().get(
				SETT_XML_NODE_AL_PREFIX_TBL);
		String columnPrefix = (String) alSettings.getPrefixes().get(
				SETT_XML_NODE_AL_PREFIX_COL);
		if (schema != null) {
			for (String curSchema : schema.keySet()) {
				curSchema = curSchema.toUpperCase();
				ResultSet rsCatalogs = dbMetaData.getTables(null, curSchema,
						null, null);
				while (rsCatalogs.next()) {
					String curTableName = rsCatalogs.getString("TABLE_NAME");
					if (dbSrc.getTables().containsKey(
							DBAliasTable.createKey(curSchema, curTableName
									.toLowerCase()))) {
						if (!commentAdded) {
							addComment(" ******************************** Constants DB source from \""
									+ dbSrc.getDbConnSettings().getName()
									+ "\" data base  ******************************** ");
							commentAdded = true;
						}
						String newTableName = convertName(curTableName);
						// System.out.println("old table name (" + curTableName
						// + ") while the new Table Name is (" + newTableName +
						// ")");
						// System.out.println("Table (" + curTableName + ")
						// contains the following columns : ");

						addComment(" **** Table (" + curTableName + ") **** ");
						addConstant(tablePrefix + settings.getNameSeparator()
								+ newTableName, curTableName);
						ResultSet rsTableCols = dbMetaData.getColumns(null,
								curSchema, curTableName, null);
						while (rsTableCols.next()) {
							String colName = rsTableCols
									.getString("COLUMN_NAME");
							String newColName = newTableName
									+ settings.getNameSeparator()
									+ convertName(colName);
							addConstant(columnPrefix
									+ settings.getNameSeparator() + newColName,
									colName);
							// System.out.println("\t Old column name ( " +
							// colName + " ) and the new column name is (" +
							// newColName + ")");
						}
						rsTableCols.close();
					}
				}
				rsCatalogs.close();

			}
		}

	}

}
