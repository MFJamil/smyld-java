package org.smyld.db.schema;

import java.util.HashMap;
import org.jdom2.Element;
import org.smyld.xml.XMLFileWriter;

public class XMLSchemaWriter extends XMLFileWriter implements SchemaConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,Table> tables;

	public XMLSchemaWriter() {
	}

	@Override
	public void compose() {
		rootElement = new Element(TAG_NAME_DB);
		if ((tables != null) && (tables.size() > 0)) {
			Element tablesNode = new Element(TAG_NAME_TABLES);
			for (String tableName : tables.keySet()) {
				Table curTable = (Table) tables.get(tableName);
				Element tableNode = new Element(TAG_NAME_TABLE);
				tableNode.setAttribute(TAG_ATT_NAME, curTable.getName());
				addTable(curTable, tableNode);
				tablesNode.addContent(tableNode);
			}
			rootElement.addContent(tablesNode);
		}

	}

	private void addTable(Table curTable, Element tableNode) {
		// Adding columns to the table
		HashMap<String,SchemaObject> cols = curTable.getCols();
		if ((cols != null) && (cols.size() > 0)) {
			Element colsNode = new Element(TAG_NAME_COLUMNS);
			for (String colName : cols.keySet()) {
				TableColumn curCol = (TableColumn)cols.get(colName);
				Element colNode = new Element(TAG_NAME_COLUMN);
				addColumn(curCol, colNode);
				colsNode.addContent(colNode);
			}
			tableNode.addContent(colsNode);
		}
		// Adding primary Keys to the table
		HashMap<String,SchemaObject> pkeys = curTable.getPrimaryKeys();
		if ((pkeys != null) && (pkeys.size() > 0)) {
			Element pkeysNode = new Element(TAG_NAME_P_KEYS);
			for (String keyName : pkeys.keySet()) {
				PrimaryKey curKey = (PrimaryKey) pkeys.get(keyName);
				Element keyNode = new Element(TAG_NAME_KEY);
				addPKey(curKey, keyNode);
				pkeysNode.addContent(keyNode);
			}
			tableNode.addContent(pkeysNode);
		}
		// Adding foreign Keys to the table
		HashMap<String,SchemaObject> fkeys = curTable.getForeignKeys();
		if ((fkeys != null) && (fkeys.size() > 0)) {
			Element fkeysNode = new Element(TAG_NAME_F_KEYS);
			for (String keyName : fkeys.keySet()) {
				ForeignKey curKey = (ForeignKey) fkeys.get(keyName);
				Element keyNode = new Element(TAG_NAME_KEY);
				addFKey(curKey, keyNode);
				fkeysNode.addContent(keyNode);
			}
			tableNode.addContent(fkeysNode);
		}

	}

	private void addFKey(ForeignKey curkey, Element keyNode) {
		keyNode.setAttribute(TAG_ATT_NAME, curkey.getName());
		keyNode.setAttribute(TAG_ATT_COLUMN, curkey.getColumnName());
		keyNode.setAttribute(TAG_ATT_TABLE, curkey.getTable());
		keyNode.setAttribute(TAG_ATT_REF_COLUMN, curkey.getPrimaryKey()
				.getColumnName());

	}

	private void addPKey(PrimaryKey curkey, Element keyNode) {
		keyNode.setAttribute(TAG_ATT_NAME, curkey.getName());
		keyNode.setAttribute(TAG_ATT_COLUMN, curkey.getColumnName());
		keyNode.setAttribute(TAG_ATT_SEQUENCE, Integer.toString(curkey
				.getSequence()));
	}

	private void addColumn(TableColumn curCol, Element colNode) {
		colNode.setAttribute(TAG_ATT_NAME, curCol.getName());
		colNode.setAttribute(TAG_ATT_TYPE, curCol.getType());
		colNode.setAttribute(TAG_ATT_NULLABLE, Boolean.toString(curCol
				.isNullable()));
		colNode.setAttribute(TAG_ATT_SIZE, Integer.toString(curCol.getSize()));
	}

	public HashMap<String,Table> getTables() {
		return tables;
	}

	public void setTables(HashMap<String,Table> tables) {
		this.tables = tables;
	}
}
