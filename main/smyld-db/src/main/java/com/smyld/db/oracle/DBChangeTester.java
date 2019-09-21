package com.smyld.db.oracle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import oracle.jdbc.pool.OracleDataSource;

public class DBChangeTester implements DBChangeListener{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new DBChangeTester();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DBChangeTester()throws Exception{
		testDBChangeListener();
		
	}
	private void testDBChangeListener() throws Exception{
		Connection conn = getConnection();
		DBChangeManager manager = new DBChangeManager(conn,10);
		String[] tables = {"SVC_CARD_STATUS_CHANGE","SVC_CLIENT_CARDS","SVC_CARD_ACCUMULATOR"};
		manager.addChangeListener(tables, this);
		manager.activate();
		
	}
	
	private Connection getConnection() throws SQLException{
		OracleDataSource oraSource = new OracleDataSource();
		oraSource.setDriverType("thin");
		/*
		oraSource.setUser("bw3");
		oraSource.setPassword("bw3data");
		oraSource.setURL("jdbc:oracle:thin:@192.168.12.180:1521:smyldbw");
		 */
		oraSource.setUser("cca_dev");
		oraSource.setPassword("bw3data");
		oraSource.setURL("jdbc:oracle:thin:@192.168.12.111:1521:cbs");

		return oraSource.getConnection();
		
	}

	public void newChange(int operation, String rowID) {
		// TODO Auto-generated method stub
		System.out.println("Change occurred .. ! on row id " + rowID + " operation holds value " + operation );
		
	}

	public void newChange(int operation, ResultSet row) {
		// TODO Auto-generated method stub
		
	}

	public void newChange(ResultSet row) {
		// TODO Auto-generated method stub
		
	}

	public void newChange(String tableName, ResultSet row) {
		// TODO Auto-generated method stub
		
	}

	public void newChanges(HashMap<String, ResultSet> changes) {
		for(String curTable:changes.keySet()){
			ResultSet tableChanges = changes.get(curTable);
			try {
				while(tableChanges.next()){
					System.out.println("Table " + curTable +  " has new Change on card " + tableChanges.getString("CARD_NUMBER") + " the operation is " + tableChanges.getString("versions_operation"));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	public void timeOut() {
		// TODO Auto-generated method stub
		
	}

}
