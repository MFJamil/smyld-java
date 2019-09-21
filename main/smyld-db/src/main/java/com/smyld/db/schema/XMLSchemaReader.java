package com.smyld.db.schema;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;

import com.smyld.SMYLDObject;
import org.jdom2.input.SAXBuilder;

public class XMLSchemaReader extends SMYLDObject implements SchemaConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,Table> tables;
	Element root;

	public XMLSchemaReader() {
	}

	public void readSchemaTables(String sourceFile) throws Exception {
		root = new SAXBuilder().build(new File(sourceFile)).getRootElement();
		readTables(root.getChild(TAG_NAME_TABLES));
	}

	@SuppressWarnings("unchecked")
	private void readTables(Element tablesNode) {
		if (tablesNode == null)
			return;
		if (tables == null)
			tables = new HashMap<String,Table>();
		else
			tables.clear();
		List tableList = tablesNode.getChildren(TAG_NAME_TABLE);
		Iterator itr = tableList.iterator();
		while (itr.hasNext()) {
			Element curTable = (Element) itr.next();
			readTable(curTable);
		}
	}

	@SuppressWarnings("unchecked")
	private void readTable(Element tableNode) {
		if (tableNode == null)
			return;
		Table newTable = new Table();
		newTable.setName(tableNode.getAttributeValue(TAG_ATT_NAME));
		// Reading the table columns
		Element colsNode = tableNode.getChild(TAG_NAME_COLUMNS);
		List colList = colsNode.getChildren(TAG_NAME_COLUMN);
		if ((colsNode != null) && (colList != null)) {
			HashMap cols = new HashMap();
			Iterator itr = colList.iterator();
			while (itr.hasNext()) {
				TableColumn newCol = new TableColumn();
				Element colNode = (Element) itr.next();
				readCol(colNode, newCol);
				cols.put(newCol.getName(), newCol);
			}
			newTable.setCols(cols);
		}
		// Reading the table primarykeys
		Element pkeysNode = tableNode.getChild(TAG_NAME_P_KEYS);
		if ((pkeysNode != null)
				&& (pkeysNode.getChildren(TAG_NAME_KEY) != null)) {
			List pkeysList = pkeysNode.getChildren(TAG_NAME_KEY);
			HashMap pkeys = new HashMap();
			Iterator itr = pkeysList.iterator();
			while (itr.hasNext()) {
				PrimaryKey newKey = new PrimaryKey();
				Element keyNode = (Element) itr.next();
				readPrimaryKey(keyNode, newKey);
				pkeys.put(newKey.getColumnName(), newKey);
			}
			newTable.setPrimaryKeys(pkeys);
		}
		// Reading the table foreign keys
		Element fkeysNode = tableNode.getChild(TAG_NAME_F_KEYS);
		if ((fkeysNode != null)
				&& (fkeysNode.getChildren(TAG_NAME_KEY) != null)) {
			List fkeysList = fkeysNode.getChildren(TAG_NAME_KEY);
			HashMap fkeys = new HashMap();
			Iterator itr = fkeysList.iterator();
			while (itr.hasNext()) {
				ForeignKey newKey = new ForeignKey();
				Element keyNode = (Element) itr.next();
				readForeignKey(keyNode, newKey);
				fkeys.put(newKey.getName(), newKey);
			}
			newTable.setForeignKeys(fkeys);
		}

		tables.put(newTable.getName(), newTable);
	}

	private void readCol(Element colNode, TableColumn newCol) {
		newCol.setName(colNode.getAttributeValue(TAG_ATT_NAME));
		newCol.setType(colNode.getAttributeValue(TAG_ATT_TYPE));
		newCol.setSize(Integer
				.parseInt(colNode.getAttributeValue(TAG_ATT_SIZE)));
		newCol.setNullable("true".equals(colNode
				.getAttributeValue(TAG_ATT_NULLABLE)));
	}

	private void readPrimaryKey(Element keyNode, PrimaryKey newKey) {
		newKey.setName(keyNode.getAttributeValue(TAG_ATT_NAME));
		newKey.setColumnName(keyNode.getAttributeValue(TAG_ATT_COLUMN));
		newKey.setSequence(Integer.parseInt(keyNode
				.getAttributeValue(TAG_ATT_SEQUENCE)));
	}

	private void readForeignKey(Element keyNode, ForeignKey newKey) {
		newKey.setName(keyNode.getAttributeValue(TAG_ATT_NAME));
		newKey.setColumnName(keyNode.getAttributeValue(TAG_ATT_COLUMN));
		newKey.setTable(keyNode.getAttributeValue(TAG_ATT_TABLE));
		PrimaryKey ref = new PrimaryKey();
		ref.setColumnName(keyNode.getAttributeValue(TAG_ATT_COLUMN));
		newKey.setPrimaryKey(ref);
	}

	public HashMap<String,Table> getTables() {
		return tables;
	}

}
