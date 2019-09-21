package org.smyld.db;

public interface DBConnectionListener {
	public void connectionClosed();

	public void connectionResumed(DBConnection newConnection);

}
