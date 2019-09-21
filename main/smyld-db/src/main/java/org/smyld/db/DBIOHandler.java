package org.smyld.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.smyld.db.schema.TableColumn;
import org.smyld.text.TextUtil;

public class DBIOHandler extends SMYLDDataBaseHandler implements SQL {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedReader reader;
	protected PreparedStatement pst;
	String sep;
	String[] data;
	Vector<TableColumn> schemaCols;
	int rejNo;
	int addNo;
	int fldNo;
	int lineNo;
	int offSet;
	boolean isQouted;

	public DBIOHandler(DBErrorHandler e, DBConnection dbConn) {
		super(e, dbConn);
	}

	public void importFile(String dumpKey, String targetTable, String fileName,
			String separator) throws Exception {

		lineNo = 0;
		sep = separator;
		String currentLine = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));

			doInitializeProcess(targetTable);
			while ((currentLine = reader.readLine()) != null) {
				lineNo++;
				if ((!isQouted) && (currentLine.startsWith("\""))) {
					isQouted = true;
					sep = "\"" + sep + "\"";
				}
				if (!currentLine.startsWith(dumpKey)) {
					insertRecord(currentLine);
				} else {
					// Need to add additional check for the field names and the
					// sequence
				}
			}
			doClearProcess();
		} catch (Exception e) {
			throw new DBIOException(e, currentLine);
		}
	}

	protected void doInitializeProcess(String targetTable) throws Exception {
		// String sql = createInsertSQL(targetTable);
		String sql = dbUtility.createInsertSQL(targetTable);
		schemaCols = dbUtility.getTableCols(targetTable);
		fldNo = schemaCols.size();
		// System.out.println(sql);

		pst = prepareStatement(sql);

	}

	protected void doClearProcess() throws Exception {
		System.out.println(getLineNumber() + " line added.");
		// System.out.println(rejNo + " line rejected.");
		dbUtility.closeCursor(pst);
		dbConnection.commit();

	}

	protected PreparedStatement prepareStatement(String sql) throws Exception {
		return smyldDBConnection.getConnection().prepareStatement(sql);
	}

	protected void insertRecord(String currentLine) throws Exception {
		data = readData(currentLine);
		insertLine(data);
	}

	public String[] getLastDataLine() {
		return data;
	}

	protected String createInsertSQL(String tableName) throws SQLException {
		DatabaseMetaData rsmeta = smyldDBConnection.getConnection().getMetaData();
		StringBuffer buffer = new StringBuffer();
		buffer.append(INS);
		buffer.append(tableName);
		buffer.append(PO);
		ResultSet rsTableCols = rsmeta.getColumns(null, smyldDBConnection
				.getSettings().getSchemaOwner().toUpperCase(), tableName, null);
		fldNo = 0;
		schemaCols = new Vector<TableColumn>();
		while (rsTableCols.next()) {
			String colName = rsTableCols.getString("COLUMN_NAME");
			int colSize = rsTableCols.getInt("COLUMN_SIZE");
			TableColumn newColumn = new TableColumn();
			newColumn.setName(colName);
			newColumn.setSize(colSize);
			schemaCols.add(newColumn);
			buffer.append(colName);
			buffer.append(COM);
			fldNo++;
		}
		buffer.replace(buffer.length() - 2, buffer.length() - 1, PC);
		buffer.append(VAO);
		for (int i = 0; i < fldNo; i++) {
			buffer.append(QUM);
			buffer.append(COM);
		}
		buffer.replace(buffer.length() - 2, buffer.length() - 1, PC);

		return buffer.toString();
	}

	protected String[] readData(String currentLine) throws Exception {
		String[] data = currentLine.split(sep);
		if (isQouted) {
			data[0] = data[0].substring(1);
			data[data.length - 1] = data[data.length - 1].substring(0,
					data[data.length - 1].length() - 2);
		}
		return data;
	}

	public int getLineNumber() {
		return lineNo;
	}

	protected void insertLine(String[] data) throws Exception {
		for (int i = 0; i < fldNo; i++)
			pst.setString(i + 1, data[i]);
		// if (data.length>fldNo)

		if (pst.execute())
			rejNo++;
		else
			addNo++;
	}

	protected void fillData(String[] data, int offset) throws Exception {
		if (offset > 0)
			this.offSet = offset;
		int dataIdx = 0;
		for (int i = offset; i < fldNo; i++) {
			if (data[dataIdx] != null)
				data[dataIdx] = data[dataIdx].trim();
			else
				data[dataIdx] = "";
			pst.setString(i + 1, data[dataIdx]);
			dataIdx++;
		}
		// if (data.length>fldNo)
	}

	public Vector<String> detectSchemaError(String recordLine) {
		String[] dataFlds = recordLine.split(sep);
		String curFieldValue = null;
		Vector<String> errorLines = new Vector<String>();
		int dataIdx = 0;
		if (schemaCols.size() > dataFlds.length)
			errorLines.add("There are " + (schemaCols.size() - dataFlds.length)
					+ " data info missing!");
		for (int i = offSet; i < dataFlds.length; i++) {
			if (i < schemaCols.size()) {
				TableColumn curColumn = (TableColumn) schemaCols.get(i);
				curFieldValue = (dataFlds[dataIdx] != null) ? dataFlds[dataIdx]
						.trim() : "";
				if (curFieldValue != null) {
					if (curColumn.getSize() < curFieldValue.length()) {
						errorLines.add("Column " + curColumn.getName()
								+ " length is " + curColumn.getSize()
								+ " while data length is "
								+ curFieldValue.length() + " containing ("
								+ dataFlds[dataIdx] + ")");
					}
					if (isNumeric(curColumn)) {
						if ((curFieldValue == null)
								|| (!TextUtil.isDecimal(curFieldValue)))
							errorLines.add("Column " + curColumn.getName()
									+ " type is " + curColumn.getType()
									+ " while data value (" + curFieldValue
									+ ") do not match");
					}
				}
			} else {
				errorLines.add("Additional field data detected for the value ("
						+ curFieldValue + ")");
			}
			dataIdx++;
		}
		if (errorLines.size() == 0)
			return null;
		return errorLines;

	}

	public void rollback() throws Exception {
		dbConnection.rollback();
	}

	public void commit() throws Exception {
		dbConnection.commit();
	}

	private boolean isNumeric(TableColumn curColumn) {
		return ("'INTEGER','NUMBER'".indexOf(curColumn.getType()) != -1);
	}

	protected void postLine() throws Exception {
		if (pst.execute())
			rejNo++;
		else
			addNo++;
	}

}
