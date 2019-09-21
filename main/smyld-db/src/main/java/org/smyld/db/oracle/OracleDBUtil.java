package org.smyld.db.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.StructDescriptor;

//import org.smyld.bw.db.DynamicSQLStatements;
//import org.smyld.bw.db.SQLStatements;
import org.smyld.db.DBConnection;
import org.smyld.db.DBErrorHandler;
import org.smyld.db.SMYLDSQLException;
import org.smyld.db.Utility;
import org.smyld.db.schema.Table;
import org.smyld.db.schema.TableColumn;

public class OracleDBUtil extends Utility implements OraConstants {
	/***************************************************************************
	 * public OracleDBUtil(java.sql.Connection activeConnection,DBErrorHandler
	 * handler) { super(activeConnection,handler); }
	 **************************************************************************/

	public OracleDBUtil(DBConnection activeConnection, DBErrorHandler handler) {
		super(activeConnection, handler);
	}

	public ARRAY executeSingleParamArrayFunction(String function,
			String arrayTypeName, int param1) {
		CallableStatement cstmt = null;
		try {
			cstmt = dbConnection
					.prepareCall(constructDoubleParamProcedureCall(function));
			if (arrayTypeName != null)
				cstmt.registerOutParameter(2, OracleTypes.ARRAY, arrayTypeName);
			else
				cstmt.registerOutParameter(2, OracleTypes.ARRAY);
			cstmt.setInt(1, param1);
			cstmt.execute();
			ARRAY resultantArray = (ARRAY) cstmt.getArray(2);
			return resultantArray;
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(cstmt);
		}
		return null;
	}

	public ARRAY executeSingleParamProcedure(String procedure,
			String paramValue, int paramNo) {
		CallableStatement cstmt = null;
		try {
			cstmt = dbConnection
					.prepareCall(constructDoubleParamProcedureCall(procedure));
			cstmt.registerOutParameter(2, OracleTypes.ARRAY);
			cstmt.setInt(1, paramNo);
			cstmt.execute();
			ARRAY resultantArray = (ARRAY) cstmt.getArray(2);
			return resultantArray;
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(cstmt);
		}
		return null;

	}

	@Override
	public Connection getConnection() {
		return dbConnection;
	}

	public StructDescriptor getStructDescriptor(String structTypeName) {
		try {
			return StructDescriptor.createDescriptor(structTypeName,
					dbConnection);
		} catch (Exception ex) {
			handleDBError(ex);
		}
		return null;
	}

	public String getSingleParamFunctionStringReturnString(String function,
			String param1) {
		String result = null;
		CallableStatement cstmt = null;
		try {
			cstmt = dbConnection
					.prepareCall(constructSingleParamFunctionCall(function));

			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, param1);
			cstmt.execute();
			result = cstmt.getString(1);
		} catch (Exception ex) {
			handler.addError(ex, dbConnection);
			// ex.printStackTrace();
		} finally {
			closeCursor(cstmt);
		}
		return result;

	}

	public String getDoubleParamFunctionStringReturnString(String function,
			String param1, String param2) {
		String result = null;
		CallableStatement cstmt = null;
		try {
			cstmt = dbConnection
					.prepareCall(constructDoubleParamFunctionCall(function));

			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, param1);
			cstmt.setString(3, param2);
			cstmt.execute();
			result = cstmt.getString(1);
		} catch (Exception ex) {
			handler.addError(ex, dbConnection);
		} finally {
			closeCursor(cstmt);
		}
		return result;

	}

	public String getSingleParamFunctionString(String function, String param1) {
		String result = null;
		CallableStatement cstmt = null;
		try {
			cstmt = dbConnection
					.prepareCall(constructSingleParamFunctionCall(function));

			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2, param1);
			cstmt.execute();
			result = cstmt.getString(1);
		} catch (Exception ex) {
			handler.addError(ex, dbConnection);
			// ex.printStackTrace();
		} finally {
			closeCursor(cstmt);
		}
		return result;

	}

	public SMYLDSQLException parseSqlException(SQLException ex) {
		return new OracleSqlException(ex);
	}

	public HashMap<String,String> getTablesContainingFieldName(String fieldName) {
		ResultSet rs = null;
		HashMap<String,String> tables = new HashMap<String,String>();
		try {
			// TODO need to extract the functions from SMYLD BW project because it should belong to the general layer
			//rs = getSingleParamSQL(fieldName,SQLStatements.SEL_sys_all_tables_SINGLE_FIELD);
			while (rs.next()) {
				String currentTableName = rs.getString(1);
				// System.out.println(currentTableName);
				tables.put(currentTableName, currentTableName);
			}
			return tables;
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(rs);
		}
		return null;
	}

	public HashMap<String,Table> getTablesContainingFieldNames(String[] fieldNames) {
		ResultSet rs = null;
		HashMap<String,Table> tables = new HashMap<String,Table>();
		try {
			for(String curField:fieldNames){
				// TODO need to extract the functions from SMYLD BW project because it should belong to the general layer
				//rs = getSingleParamSQL(curField,SQLStatements.SEL_sys_all_tables_SINGLE_FIELD);
				while (rs.next()) {
					String currentTableName = rs.getString(1);
					Table curTable = tables.get(currentTableName);
					if(curTable==null){
						curTable= new Table();
						curTable.setName(currentTableName);
					}
					TableColumn newField = new TableColumn();
					newField.setName(curField);
					curTable.addColumn(newField);
					tables.put(currentTableName, curTable);
				}
			}
			
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			closeCursor(rs);
		}
		return tables;
	}

	
	
	public String getOracleDirectory(String directoryName) {
		return getSingleParamSQLData(directoryName,
				OraSqlStatements.SEL_dba_directories_SINGLE_RECORD,
				COL_DBA_DIRS_DIRECTORY_PATH);
	}

	// public long parseErrorNumber

	
	
	public static void main(String[] args){
		try {
			DBConnection conn = new DBConnection("bw3","bw3data","smyldbw","1521","192.168.12.180");
			OracleDBUtil instance = new OracleDBUtil(conn,new DBErrorHandler(){
				public boolean addError(Exception e, Connection c){
					e.printStackTrace();
					return true;
				}
			});
			String[] cols = {"CARD_NUMBER","PARENT_CARD_NUMBER"};
			HashMap<String, Table> result = instance.getTablesContainingFieldNames(cols);
			for(Table curTable:result.values()){
				System.out.println("=============================================");
				System.out.println(curTable.getName() + " : contains (" + curTable.getCols().size() + ") col(s) " );
				// TODO need to extract the functions from SMYLD BW project because it should belong to the general layer
				//System.out.println(DynamicSQLStatements.getTableSelect(curTable));
				//System.out.println(DynamicSQLStatements.getTableUpdate(curTable));
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
