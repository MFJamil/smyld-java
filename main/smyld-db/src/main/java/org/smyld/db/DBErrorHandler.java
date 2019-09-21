package org.smyld.db;

import java.sql.Connection;

public interface DBErrorHandler {
	public boolean addError(Exception e, Connection c);

	// public Collection getAllErrors(Connection c);

}
