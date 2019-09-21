package org.smyld.db.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DBChangeManager implements Runnable {
	String              targetTable;
	DBChangeListener    listener;
	long                interval  = 0;
	long                timeOut   = 0;
	long                startTime = 0;
	Connection          conn;
	boolean             active = false;
	boolean             timeOutCheck = false;
	PreparedStatement   pst;
	HashMap<String, Integer>           operationMapper = new HashMap<String, Integer>();
	HashMap<String, PreparedStatement> tables          = new HashMap<String, PreparedStatement>();
	
	
	public DBChangeManager(Connection dbConnection,long checkInterval){
		this(dbConnection);
		setInterval(checkInterval);
		init();
	}
	public DBChangeManager(Connection dbConnection,long checkInterval,long timeOut){
		this(dbConnection);
		this.timeOut = timeOut;
		timeOutCheck = true;
		setInterval(checkInterval);
		init();
	}
	
	private void init(){
		operationMapper.put("I", DBChangeListener.OPERATION_INSERT);
		operationMapper.put("U", DBChangeListener.OPERATION_UPDATE);
		operationMapper.put("D", DBChangeListener.OPERATION_DELETE);
	}

	public DBChangeManager(Connection dbConnection){
		this.conn = dbConnection;
	}
	public void addChangeListener(String[] targetTables,DBChangeListener listener) throws SQLException{
		//this.targetTable = targetTable;
		for(String curTable:targetTables){
			String sql = "Select " + curTable + ".*,versions_operation,VERSIONS_STARTTIME,rowid from "
			  + curTable + " VERSIONS BETWEEN TIMESTAMP" +
	          " systimestamp-interval '" + (interval/1000.0f) + "' second" +
	          " AND systimestamp" + 
	          " where versions_operation is not null" +
	          " order by VERSIONS_STARTTIME";
			//System.out.println("Resultant Sql is : " + sql);
			pst = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY,ResultSet.HOLD_CURSORS_OVER_COMMIT);
			tables.put(curTable, pst);
			
		}
		this.listener = listener;
	}
	
	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}
	public void activate() throws SQLException{
		active = true;
		if (timeOutCheck){
			startTime = System.currentTimeMillis();
		}
		new Thread(this).start();

	}
	public void stop(){
		active = false;
	}

	public void run() {
		while(active){
			if (timeOutCheck){
				if (System.currentTimeMillis()-startTime>timeOut){
					active= false;
					listener.timeOut();
				}
			}
			try {
				HashMap<String, ResultSet>  changes = null;
				for(String curTable:tables.keySet()){
					ResultSet rs = tables.get(curTable).executeQuery();
					// Testing which way should be the best way
					if ((rs.next())&&(listener!=null)){
						if (changes==null) changes = new HashMap<String, ResultSet>();
						rs.previous();
						changes.put(curTable, rs);
						//listener.newChange(curTable,rs);
					}
				}
				if (changes!=null){
					listener.newChanges(changes);
				}
				/*
				while(rs.next()){
					if (listener!=null){
						//String rowID     = rs.getString("rowid");
						String operation = rs.getString("versions_operation");
						//listener.newChange(operationMapper.get(operation), rowID);
						listener.newChange(operationMapper.get(operation), rs);
					}
				}
				*/
			} catch (Exception e) {
				//System.out.println(e);
				e.printStackTrace();
			}finally{
				try {
					Thread.sleep(interval);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	

}
