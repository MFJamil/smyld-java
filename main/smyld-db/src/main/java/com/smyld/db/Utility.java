/*
 * Copyrights 1992-2002 SMYLD Software 
 */
package com.smyld.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Vector;

import com.smyld.db.oracle.OracleSqlException;
import com.smyld.db.schema.TableColumn;


/**
 * To facilitate the use of JDBC classes and methods and strengthen the sql
 * statements fetch and update operations
 * 
 * @version 1.0 03/03/03
 * @author Mohammed Jamil
 */
public class Utility {

	/**
	 * Represents the connection object that is used by the class to execute SQL
	 * statements
	 */
	protected Connection dbConnection;
	protected DBConnection smyldDbConnection;
	protected DBIOHandler ioHandler;

	/**
	 * Handles the errors regarding this connection
	 */
	protected DBErrorHandler handler;

	/**
	 * Constructs the class with the given connection object to be used for the
	 * execution of statements
	 * 
	 * @param activeConnection
	 *            the active data base connection object
	 * @param e
	 *            the error handler object that takes care of the errors raised
	 *            during this connection
	 */
	public Utility(DBConnection activeDBConnection, DBErrorHandler e) {
		smyldDbConnection = activeDBConnection;
		this.dbConnection = smyldDbConnection.getConnection();

		this.handler = e;
	}

	/**
	 * Constructs the class with the given connection object to be used for the
	 * execution of statements
	 * 
	 * @param activeConnection
	 *            the active data base connection object
	 * @param e
	 *            the error handler object that takes care of the errors raised
	 *            during this connection
	 * 
	 * public Utility(Connection activeConnection, DBErrorHandler e){
	 * this.dbConnection = activeConnection; this.handler = e; }
	 */
	/**
	 * Close the cursor opened through the given result set object
	 * 
	 * @param rsRecords
	 *            the result set of the opened cursor
	 */
	public void closeCursor(ResultSet rsRecords) {
		try {
			if (rsRecords != null) {
				rsRecords.close();
				rsRecords.getStatement().close();
			}
		} catch (Exception ex) {
			handleDBError(ex);
		}
	}

	/**
	 * Close the cursor opened through the given prepared statement object
	 * 
	 * @param stRecords
	 *            the prepared statement of the opened cursor
	 */
	public void closeCursor(PreparedStatement stRecords) {
		try {
			if (stRecords != null) {
				stRecords.close();
			}
		} catch (Exception ex) {
			handleDBError(ex);
		}
	}

	/**
	 * To invoke the given sql and set the given 2 parameters using execute
	 * method
	 * 
	 * @param firstParam
	 *            the first parameter value
	 * @param secondParam
	 *            the second parameter value
	 * @param sqlText
	 *            the SQL statement
	 * @return true if the operation succeded false otherwise
	 * @version 1.0.0
	 */
	public boolean doDoubleParamSQL(String firstParam, String secondParam,
			String sqlText) {
		boolean changeResult = false;
		PreparedStatement stReport = null;
		try {
			stReport = dbConnection.prepareStatement(sqlText);
			stReport.setString(1, firstParam);
			stReport.setString(2, secondParam);
			stReport.execute();
			changeResult = true;
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(stReport);
		}
		return changeResult;
	}

	public void setSMYLDConnection(DBConnection activeConnection) {
		smyldDbConnection = activeConnection;
		dbConnection = smyldDbConnection.getConnection();
	}

	/**
	 * To invoke the given sql and set the given 2 parameters using
	 * executeUpdate method
	 * 
	 * @param firstParam
	 *            the first parameter value
	 * @param secondParam
	 *            the second parameter value
	 * @param sqlText
	 *            the SQL statement
	 * @return >0 means the number of affected records in data base after
	 *         executing the statement or 0 if nothing returned
	 * @version 1.0.0
	 */
	public int doDoubleParamUpdateSQL(String firstParam, String secondParam,
			String sqlText) {
		int changeResult = 0;
		PreparedStatement stReport = null;
		try {
			stReport = dbConnection.prepareStatement(sqlText);
			stReport.setString(1, firstParam);
			stReport.setString(2, secondParam);
			changeResult = stReport.executeUpdate();
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(stReport);
		}
		return changeResult;
	}

	/**
	 * To invoke the given sql and set the given integer parameter using execute
	 * method
	 * 
	 * @param firstParam
	 *            the first parameter value
	 * @param sqlText
	 *            the SQL statement
	 * @return true if the operation succeded false otherwise
	 * @version 1.0.0
	 */
	public boolean doSingleParamSQL(int firstParam, String sqlText) {
		boolean result = false;

		PreparedStatement stReport = null;
		try {
			stReport = dbConnection.prepareStatement(sqlText);
			stReport.setInt(1, firstParam);
			stReport.execute();
			result = true;
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(stReport);
		}
		return result;
	}

	public String detectLongFieldError(PreparedStatement st)
			throws SQLException {
		StringBuffer buffer = new StringBuffer();
		ResultSetMetaData rsm = st.getMetaData();
		ResultSet rs = st.getResultSet();
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			int dbSize = rsm.getColumnDisplaySize(i + 1);
			int stSize = rs.getString(i + 1).length();
			if (dbSize != stSize)
				buffer.append(rsm.getColumnName(i + 1));
			buffer.append(",");
		}
		return buffer.toString();

	}

	/**
	 * To invoke the given sql and set the given 3 parameters using
	 * executeUpdate method
	 * 
	 * @param firstParam
	 *            the first parameter value
	 * @param secondParam
	 *            the second parameter value
	 * @param thirdParam
	 *            the third parameter value
	 * @param sqlText
	 *            the SQL statement
	 * @return >0 means the number of affected records in data base after
	 *         executing the statement or 0 if nothing returned
	 * @version 1.0.0
	 */
	public int doTrippleParamUpdateSQL(String firstParam, String secondParam,
			String thirdParam, String sqlText) {
		int changeResult = 0;
		PreparedStatement stReport = null;
		try {
			stReport = dbConnection.prepareStatement(sqlText);
			stReport.setString(1, firstParam);
			stReport.setString(2, secondParam);
			stReport.setString(3, thirdParam);
			changeResult = stReport.executeUpdate();
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(stReport);
		}
		return changeResult;
	}

	/**
	 * To invoke the given sql and set the given 3 parameters using
	 * executeUpdate method
	 * 
	 * @param firstParam
	 *            the first parameter value
	 * @param secondParam
	 *            the second parameter value
	 * @param thirdParam
	 *            the third parameter value
	 * @param sqlText
	 *            the SQL statement
	 * @return >0 means the number of affected records in data base after
	 *         executing the statement or 0 if nothing returned
	 * @version 1.0.0
	 */
	public int doQuadParamUpdateSQL(String firstParam, String secondParam,
			String thirdParam, String fourthParam, String sqlText) {
		int changeResult = 0;
		PreparedStatement stReport = null;
		try {
			stReport = dbConnection.prepareStatement(sqlText);
			stReport.setString(1, firstParam);
			stReport.setString(2, secondParam);
			stReport.setString(3, thirdParam);
			stReport.setString(4, fourthParam);
			changeResult = stReport.executeUpdate();
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(stReport);
		}
		return changeResult;
	}

	/**
	 * To invoke the given sql and set the given 3 parameters using executeQuery
	 * method and returns the resultant result set
	 * 
	 * @param firstParam
	 *            the first parameter value
	 * @param secondParam
	 *            the second parameter value
	 * @param thirdParam
	 *            the third parameter value
	 * @param sqlText
	 *            the SQL statement
	 * @return The resultant result set if succeded and null otherwise
	 * @version 1.0.0
	 */
	public ResultSet getTrippleParamSQL(String firstParam, String secondParam,
			String thirdParam, String sqlText) {
		ResultSet rsResult = null;
		PreparedStatement stReport = null;
		try {
			stReport = dbConnection.prepareStatement(sqlText);
			stReport.setString(1, firstParam);
			stReport.setString(2, secondParam);
			stReport.setString(3, thirdParam);
			rsResult = stReport.executeQuery();
		} catch (Exception ex) {
			handleDBError(ex);
			closeCursor(stReport);
		}
		return rsResult;
	}

	public ResultSet getQuadParamSQL(String firstParam, String secondParam,
			String thirdParam, String fourthParam, String sqlText) {
		ResultSet rsResult = null;
		PreparedStatement stReport = null;
		try {
			stReport = dbConnection.prepareStatement(sqlText);
			stReport.setString(1, firstParam);
			stReport.setString(2, secondParam);
			stReport.setString(3, thirdParam);
			stReport.setString(4, fourthParam);
			rsResult = stReport.executeQuery();
		} catch (Exception ex) {
			handleDBError(ex);
			closeCursor(stReport);
		}
		return rsResult;
	}

	/**
	 * To invoke the given sql and set the given 3 parameters using executeQuery
	 * method and returns the resultant result set
	 * 
	 * @param firstParam
	 *            the first parameter value
	 * @param secondParam
	 *            the second parameter value
	 * @param thirdParam
	 *            the third parameter value
	 * @param sqlText
	 *            the SQL statement
	 * @return The resultant result set if succeded and null otherwise
	 * @version 1.0.0
	 */
	public ResultSet getTrippleParamSQL(int firstParam, int secondParam,
			String thirdParam, String sqlText) {
		return getTrippleParamSQL(Integer.toString(firstParam), Integer
				.toString(secondParam), thirdParam, sqlText);
	}

	/**
	 * To invoke the given sql and set the given string parameter using
	 * executeUpdate method and returns the affected records number
	 * 
	 * @param firstParam
	 *            the first parameter value
	 * @param sqlText
	 *            the SQL statement
	 * @return >0 means the number of affected records in data base after
	 *         executing the statement or 0 if nothing returned
	 * @version 1.0.0
	 */
	public int doSingleParamUpdateSQL(String firstParam, String sqlText) {
		int result = 0;
		PreparedStatement stReport = null;
		try {
			stReport = dbConnection.prepareStatement(sqlText);
			stReport.setString(1, firstParam);
			result = stReport.executeUpdate();
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(stReport);
		}
		return result;
	}

	/**
	 * To invoke the given sql and set the given string parameter using execute
	 * method
	 * 
	 * @param firstParam
	 *            the first parameter value
	 * @param sqlText
	 *            the SQL statement
	 * @return true if the operation succeded false otherwise
	 * @version 1.0.0
	 */
	public boolean doSingleParamSQL(String firstParam, String sqlText) {
		boolean result = false;
		PreparedStatement stReport = null;
		try {
			stReport = dbConnection.prepareStatement(sqlText);
			stReport.setString(1, firstParam);
			stReport.execute();
			result = true;
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(stReport);
		}
		return result;
	}

	/**
	 * To invoke the given sql using executeQuery method
	 * 
	 * @param sqlText
	 *            the SQL statement
	 * @return The resultant result set if succeded and null otherwise
	 * @version 1.0.0
	 */
	public ResultSet executeQuery(String sqlText) {
		ResultSet rsResult = null;
		try {
			PreparedStatement stRecords = dbConnection
					.prepareStatement(sqlText);
			rsResult = stRecords.executeQuery();
		} catch (Exception ex) {
			handleDBError(ex);
			closeCursor(rsResult);
		}
		return rsResult;
	}

	/**
	 * To invoke the given sql using class executeQuery method, and returning
	 * the gievn column name value
	 * 
	 * @param sqlText
	 *            the SQL statement
	 * @param columnName
	 *            the column name of the desired column value
	 * @return The column value if succeded and null otherwise
	 * @version 1.0.0
	 */
	public String getSQLData(String sqlText, String columnName) {
		String info = null;
		ResultSet rsData = null;
		try {
			rsData = executeQuery(sqlText);
			if (rsData.next())
				info = rsData.getString(columnName);
			rsData.close();
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(rsData);
		}
		return info;
	}

	/**
	 * To invoke the given sql using class getSingleParamSQL method,setting the
	 * given parameter and returning the gievn column name value
	 * 
	 * @param firstParam
	 *            the first parameter value
	 * @param sqlText
	 *            the SQL statement
	 * @param columnName
	 *            the column name of the desired column value
	 * @return The column value if succeded and null otherwise
	 * @version 1.0.0
	 */
	public String getSingleParamSQLData(String firstParam, String sqlText,
			String columnName) {
		String info = null;
		ResultSet rsData = null;
		try {
			rsData = getSingleParamSQL(firstParam, sqlText);
			if (rsData.next())
				info = rsData.getString(columnName);
			rsData.close();
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(rsData);
		}
		return info;
	}

	public int getCount(ResultSet rs) {
		try {
			if (rs.next()) {
				return rs.getInt(SQL.COUNT_COLUMN);
			}
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(rs);
		}
		return -1;
	}

	/**
	 * To invoke the given sql using executeQuery method,setting the given
	 * parameter and returning the resultant result set
	 * 
	 * @param firstParam
	 *            the first parameter value
	 * @param sqlText
	 *            the SQL statement
	 * @return The resultant result set if succeded and null otherwise
	 * @version 1.0.0
	 */
	public ResultSet getSingleParamSQL(String paramValue, String sqlText) {
		PreparedStatement stReport = null;
		try {
			stReport = dbConnection.prepareStatement(sqlText);
			stReport.setString(1, paramValue);
			return stReport.executeQuery();
		} catch (Exception ex) {
			handleDBError(ex);
			closeCursor(stReport);
		}
		return null;
	}

	public ResultSet getSQLResultSet(String sqlText) {
		PreparedStatement stReport = null;
		try {
			stReport = dbConnection.prepareStatement(sqlText);
			return stReport.executeQuery();
		} catch (Exception ex) {
			handleDBError(ex);
			closeCursor(stReport);
		}
		return null;
	}

	/**
	 * To invoke the given sql using executeQuery method,setting the given 2
	 * parameter and returning the resultant result set
	 * 
	 * @param firstParam
	 *            the first parameter value
	 * @param secondParam
	 *            the second parameter value
	 * @param sqlText
	 *            the SQL statement
	 * @return The resultant result set if succeded and null otherwise
	 * @version 1.0.0
	 */
	public ResultSet getDoubleParamSQL(String firstParam, String secondParam,
			String sqlText) {
		PreparedStatement stReport = null;
		try {
			stReport = dbConnection.prepareStatement(sqlText);
			setString(stReport, firstParam, 1);
			setString(stReport, secondParam, 2);
			stReport.execute();
			return stReport.getResultSet();
		} catch (Exception ex) {
			handleDBError(ex);
			closeCursor(stReport);
		}
		return null;
	}

	private void setString(PreparedStatement pst, String paramValue,
			int paramIndex) throws SQLException {
		if (paramValue != null)
			pst.setString(paramIndex, paramValue);
		else
			pst.setNull(paramIndex, Types.VARCHAR);
	}

	/**
	 * To invoke the given sql using executeQuery method,setting the given 2
	 * parameter and returning the resultant result set
	 * 
	 * @param firstParam
	 *            the first parameter value
	 * @param secondParam
	 *            the second parameter value
	 * @param sqlText
	 *            the SQL statement
	 * @return The resultant result set if succeded and null otherwise
	 * @version 1.0.0
	 */
	public ResultSet getDoubleParamSQL(int firstParam, String secondParam,
			String sqlText) {
		return getDoubleParamSQL(Integer.toString(firstParam), secondParam,
				sqlText);
	}

	/**
	 * To invoke the given sql using executeQuery method,setting the given 2
	 * parameter and returning the resultant result set
	 * 
	 * @param firstParam
	 *            the first parameter value
	 * @param secondParam
	 *            the second parameter value
	 * @param sqlText
	 *            the SQL statement
	 * @return The resultant result set if succeded and null otherwise
	 * @version 1.0.0
	 */
	public ResultSet getDoubleParamSQL(int firstParam, int secondParam,
			String sqlText) {
		return getDoubleParamSQL(Integer.toString(firstParam), Integer
				.toString(secondParam), sqlText);
	}

	/**
	 * Calculate the value of the given column name in the given table and
	 * increase it by one. To use it for the increased records counter columns
	 * 
	 * @param tableName
	 *            required table name
	 * @return the resultant column value if succeded and -1 otherwise
	 */
	public int getSequentialID(String tableName, String idColName) {
		return getSequentialID(tableName, idColName, null, null);
	}

	/**
	 * Calculate the value of the given column name in the given table and
	 * increase it by one. To use it for the increased records counter columns
	 * 
	 * @param tableName
	 *            required table name
	 * @return the resultant column value if succeded and -1 otherwise
	 */
	public int getSequentialID(String tableName, String idColName,
			String whereColumnName, String whereColumnValue) {
		String nestedSQL = "SELECT MAX(" + idColName + ") as "
				+ COL_MATH_MAX_ID + " from " + tableName;
		if ((whereColumnName != null) && (whereColumnValue != null))
			nestedSQL += " where " + whereColumnName + "=" + whereColumnValue;
		ResultSet rsRecord = executeQuery(nestedSQL);
		int recordID = 1;
		try {
			if (rsRecord.next())
				recordID = rsRecord.getInt(COL_MATH_MAX_ID) + 1;
		} catch (Exception ex) {
			handleDBError(ex);
			recordID = -1;
		} finally {
			closeCursor(rsRecord);
		}
		return recordID;
	}

	public int getSequenceValue(String seqName) {
		String nestedSQL = "SELECT " + seqName + ".nextval from dual";
		ResultSet rsRecord = executeQuery(nestedSQL);
		int recordID = 1;
		try {
			if (rsRecord.next())
				recordID = rsRecord.getInt(0) + 1;
		} catch (Exception ex) {
			handleDBError(ex);
			recordID = -1;
		} finally {
			closeCursor(rsRecord);
		}
		return recordID;
	}

	/**
	 * Checking if the given result set contains records or not
	 * 
	 * @param rsRecords
	 *            The result set to be checked
	 * @return true if it contains at least one record and false otherwise
	 */
	public boolean isExist(ResultSet rsRecords) {
		boolean result = false;
		try {
			result = rsRecords.next();
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(rsRecords);
		}
		return result;
	}

	/**
	 * Checking if the given single parameter sql statement contains records or
	 * not
	 * 
	 * @param firstParam
	 *            The first parameter value
	 * @param sqlText
	 *            The SQL text
	 * @return true if it contains at least one record and false otherwise
	 */
	public boolean isSingleExist(String firstParam, String sqlText) {
		ResultSet rsRecords = getSingleParamSQL(firstParam, sqlText);
		return isExist(rsRecords);
	}

	/**
	 * Checking if the given single parameter sql statement contains records or
	 * not
	 * 
	 * @param firstParam
	 *            The first parameter value
	 * @param sqlText
	 *            The SQL text
	 * @return true if it contains at least one record and false otherwise
	 */
	public boolean isExist(String sqlText) {
		ResultSet rsRecords = getSQLResultSet(sqlText);
		return isExist(rsRecords);
	}

	/**
	 * Checking if the given double parameter sql statement contains records or
	 * not
	 * 
	 * @param firstParam
	 *            The first parameter value
	 * @param secondParam
	 *            The second parameter value
	 * @param sqlText
	 *            The SQL text
	 * @return true if it contains at least one record and false otherwise
	 */
	public boolean isDoubleExist(String firstParam, String secondParam,
			String sqlText) {
		ResultSet rsRecords = getDoubleParamSQL(firstParam, secondParam,
				sqlText);
		return isExist(rsRecords);
	}

	/**
	 * Checking if the given tripple parameter sql statement contains records or
	 * not
	 * 
	 * @param firstParam
	 *            The first parameter value
	 * @param secondParam
	 *            The second parameter value
	 * @param thirdParam
	 *            The third parameter value
	 * @param sqlText
	 *            The SQL text
	 * @return true if it contains at least one record and false otherwise
	 */
	public boolean isTrippleExist(String firstParam, String secondParam,
			String thirdParam, String sqlText) {
		ResultSet rsRecords = getTrippleParamSQL(firstParam, secondParam,
				thirdParam, sqlText);
		return isExist(rsRecords);
	}

	/**
	 * Fetches the all the records of the given table with out any conditions
	 * 
	 * @param tableName
	 *            comments
	 * @return The resultant result set and null if any error happened
	 */
	public ResultSet fetchTableData(String tableName) {
		String sqlStatement = SQL.SELA + tableName;
		return executeQuery(sqlStatement);
	}

	/**
	 * Fetchs the given table name data and returns HashMap object containing
	 * com.smyld.db.Field objects created upon the given field Name/Value pair
	 * (represents 2 column names in the given table name)
	 * 
	 * @param TableName
	 *            the target table of data
	 * @param FieldName
	 *            the target table column representing the field Name
	 * @param FieldValue
	 *            the target table column representing the field Value
	 * @return returns HashMap object of com.smyld.db.Field type, holding the
	 *         information of the table records or null in case of any error
	 */
	public HashMap<String,Field> FetchTableFields(String TableName, String FieldName,
			String FieldValue) {
		ResultSet rsTableData = null;
		HashMap<String,Field> resultFields = null;
		try {
			rsTableData = fetchTableData(TableName);
			while (rsTableData.next()) {
				if (resultFields == null)
					resultFields = new HashMap<String,Field>();
				String colName = rsTableData.getString(FieldName);
				String colValue = rsTableData.getString(FieldValue);
				Field newField = new Field(colName, colValue);
				resultFields.put(colName, newField);
			}
		} catch (Exception ex) {
			handleDBError(ex);
			resultFields = null;
		} finally {
			closeCursor(rsTableData);
		}
		return resultFields;
	}

	public void FillColumnNames(ResultSetMetaData rsMetaData,
			HashMap<String,Integer> results) {
		try {
			for (int i = 0; i < rsMetaData.getColumnCount(); i++)
				results.put(rsMetaData.getColumnName(i), i);
		} catch (Exception ex) {
			handleDBError(ex);
		}
	}

	/**
	 * Returns the index represented as the required number of digit text. For
	 * example index with the value of 5 and a digitWidth of 3 means "005" and
	 * so on
	 * 
	 * @param digitWidth
	 *            the width of the required digits
	 * @return the index represented as the 3 digit text
	 */
	public static String getStringIndex(int digitWidth, int index) {
		String resultIndex = null;
		if (index > 0) {
			String indexText = Integer.toString(index);
			resultIndex = indexText;
			int indexDigitNo = indexText.length();
			if (indexDigitNo < digitWidth) {
				resultIndex = "";
				for (int i = 0; i < digitWidth - indexDigitNo; i++)
					resultIndex += "0";
				resultIndex += indexText;
				return resultIndex;
			}
		}
		return resultIndex;
	}

	public void handleDBError(Exception ex) {
		if (handler != null)
			handler.addError(ex, dbConnection);
		else
			ex.printStackTrace();
	}

	public static String constructSingleParamProcedureCall(String ProcedureName) {
		return "{call " + ProcedureName + "(?)}";
	}

	public static String constructDoubleParamProcedureCall(String ProcedureName) {
		return "{call " + ProcedureName + "(?,?)}";
	}

	public static String constructTrippleParamProcedureCall(String ProcedureName) {
		return "{call " + ProcedureName + "(?,?,?)}";
	}

	public static String constructSingleParamFunctionCall(String functionName) {
		return "{? = call " + functionName + " (?)}";
	}

	public static String constructDoubleParamFunctionCall(String functionName) {
		return "{? = call " + functionName + " (?,?)}";
	}

	public static String constructTrippleParamFunctionCall(String functionName) {
		return "{? = call " + functionName + " (?,?,?)}";
	}

	public static String constructMultiParamFunctionCall(String functionName,
			int paramsNo) {
		return "{? = call " + functionName + getParamsText(paramsNo) + "}";
	}

	public static String constructMultiParamProcedureCall(String ProcedureName,
			int paramsNo) {
		return "{call " + ProcedureName + getParamsText(paramsNo) + "}";
	}

	protected static String getParamsText(int paramsNo) {
		char[] result = new char[2 * paramsNo + 1];
		int arrayCount = 0;
		result[arrayCount] = '(';
		for (int i = 0; i < paramsNo; i++) {
			arrayCount++;
			result[arrayCount] = '?';
			arrayCount++;
			result[arrayCount] = ',';
		}
		result[result.length - 1] = ')';
		return new String(result);
	}

	public void executeDoubleParamFunction(String function, String param1,
			String param2) {
		CallableStatement cstmt = null;
		try {
			cstmt = dbConnection
					.prepareCall(constructDoubleParamFunctionCall(function));
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2, param1);
			cstmt.setString(3, param2);
			cstmt.execute();
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(cstmt);
		}
	}

	public void executeTrippleParamFunction(String function, String param1,
			String param2, String param3) {
		CallableStatement cstmt = null;
		try {
			cstmt = dbConnection
					.prepareCall(constructTrippleParamFunctionCall(function));
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2, param1);
			cstmt.setString(3, param2);
			cstmt.setString(4, param3);
			cstmt.execute();
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(cstmt);
		}
	}

	public void executeDoubleParamFunction(String function, int param1,
			int param2) {
		CallableStatement cstmt = null;
		try {
			cstmt = dbConnection
					.prepareCall(constructDoubleParamFunctionCall(function));
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setInt(2, param1);
			cstmt.setInt(3, param2);
			cstmt.execute();
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(cstmt);
		}
	}

	public void executeMultiParamProcedure(String procedure, String[] params) {
		int paramNo = params.length;
		CallableStatement cstmt = null;
		try {
			String resultantCall = constructMultiParamProcedureCall(procedure,
					paramNo);
			// System.out.println(resultantCall);
			cstmt = dbConnection.prepareCall(resultantCall);
			for (int i = 1; i < paramNo + 1; i++) {
				cstmt.setString(i, params[i - 1]);
				// System.out.println("Setting the string for index " + i + "
				// with the value " + params[i-1]);
			}
			cstmt.execute();
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(cstmt);
		}
	}

	public void executeDoubleParamProcedure(String procedure, int param1,
			int param2) {
		CallableStatement cstmt = null;
		try {
			cstmt = dbConnection
					.prepareCall(constructDoubleParamProcedureCall(procedure));
			cstmt.setInt(1, param1);
			cstmt.setInt(2, param2);
			cstmt.execute();
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(cstmt);
		}
	}

	public void executeDoubleParamProcedure(String procedure, String param1,
			String param2) {
		CallableStatement cstmt = null;
		try {
			cstmt = dbConnection
					.prepareCall(constructDoubleParamProcedureCall(procedure));
			cstmt.setString(1, param1);
			cstmt.setString(2, param2);
			cstmt.execute();
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(cstmt);
		}
	}

	public void executeTrippleParamProcedure(String procedure, String param1,
			int param2, int param3) {
		CallableStatement cstmt = null;
		try {
			cstmt = dbConnection
					.prepareCall(constructTrippleParamProcedureCall(procedure));
			cstmt.setString(1, param1);
			cstmt.setInt(2, param2);
			cstmt.setInt(3, param3);
			cstmt.execute();
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(cstmt);
		}
	}

	public Connection getConnection() {
		return dbConnection;
	}

	public void importFile(String targetTable, String fileName)
			throws Exception {
		ioHandler = new DBIOHandler(handler, smyldDbConnection);

	}

	public static int getErrorCategory(Exception ex) {
		int result = ERR_CATEGORY_SQL;

		// CHG This line have to be removed to another class in the hirarchy
		OracleSqlException oraExc = new OracleSqlException(ex);
		result = oraExc.getExceptionCategory();
		ex = oraExc;
		/*
		 * if (ex instanceof SQLException){ SQLException sqlEx = (SQLException)
		 * ex; //CHG This line have to be removed to another class in the
		 * hirarchy result =
		 * OracleSqlException.getExceptionCategory(sqlEx.getErrorCode());
		 * /*switch(sqlEx.getErrorCode()){ case EXCEPTION_CONNECTION_RESET:case
		 * EXCEPTION_CONNECTION_SHUT: result = ERR_CATEGORY_CONNECTION; break;
		 * default: System.out.println("Err Code : " + sqlEx.getErrorCode());
		 * System.out.println("Message : " + sqlEx.getMessage());
		 * System.out.println("SQL State : " + sqlEx.getSQLState()); }
		 * 
		 * }else{ //AQOracleSQLException aqExc = new AQOracleSQLException(); }
		 */
		return result;
	}

	public String createInsertSQL(String tableName) throws SQLException {
		DatabaseMetaData rsmeta = dbConnection.getMetaData();
		StringBuffer buffer = new StringBuffer();
		buffer.append(SQL.INS);
		buffer.append(tableName);
		buffer.append(SQL.PO);
		ResultSet rsTableCols = rsmeta.getColumns(null, smyldDbConnection
				.getSettings().getSchemaOwner().toUpperCase(), tableName, null);
		int fldNo = 0;
		// schemaCols = new Vector();
		while (rsTableCols.next()) {
			String colName = rsTableCols.getString("COLUMN_NAME");
			//int colSize = rsTableCols.getInt("COLUMN_SIZE");
			buffer.append(colName);
			buffer.append(SQL.COM);
			fldNo++;
		}
		buffer.replace(buffer.length() - 2, buffer.length() - 1, SQL.PC);
		buffer.append(SQL.VAO);
		for (int i = 0; i < fldNo; i++) {
			buffer.append(SQL.QUM);
			buffer.append(SQL.COM);
		}
		buffer.replace(buffer.length() - 2, buffer.length() - 1, SQL.PC);

		return buffer.toString();
	}

	public Vector<TableColumn> getTableCols(String tableName) throws SQLException {
		DatabaseMetaData rsmeta = dbConnection.getMetaData();
		ResultSet rsTableCols = rsmeta.getColumns(null, smyldDbConnection
				.getSettings().getSchemaOwner().toUpperCase(), tableName, null);
		Vector<TableColumn> schemaCols = new Vector<TableColumn>();
		while (rsTableCols.next()) {
			String colName = rsTableCols.getString("COLUMN_NAME");
			int colSize = rsTableCols.getInt("COLUMN_SIZE");
			String dataType = rsTableCols.getString("TYPE_NAME");
			TableColumn newColumn = new TableColumn();
			newColumn.setName(colName);
			newColumn.setSize(colSize);
			newColumn.setType(dataType);
			schemaCols.add(newColumn);
		}
		return schemaCols;
	}

	public static final int ERR_CATEGORY_SQL = 0;
	public static final int ERR_CATEGORY_CONNECTION = 1;
	public static final int ERR_CATEGORY_STORED_PROCEDURES_RECOMPILED = 10;
	public static final int ERR_CATEGORY_STORED_PROCEDURES_ERROR = 11;
	// JDBC error list
	public static final int EXCEPTION_CONNECTION_RESET = 17002;
	public static final int EXCEPTION_CONNECTION_SHUT = 1089;

	@SuppressWarnings("unused")
	private static final String COL_MATH_COUNTER = "counter";
	private static final String COL_MATH_MAX_ID = "MaxID";

}
