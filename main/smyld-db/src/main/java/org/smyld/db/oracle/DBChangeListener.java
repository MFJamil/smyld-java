package org.smyld.db.oracle;

import java.sql.ResultSet;
import java.util.HashMap;

public interface DBChangeListener {
	
	//public void newChange(int operation,String rowID);
	//public void newChange(ResultSet row);
	public void newChange(String tableName,ResultSet row);
	public void newChanges(HashMap<String, ResultSet> changes);
	public void timeOut();
	public static final int OPERATION_DELETE = 1;
	public static final int OPERATION_INSERT = 2;
	public static final int OPERATION_UPDATE = 3;

}
