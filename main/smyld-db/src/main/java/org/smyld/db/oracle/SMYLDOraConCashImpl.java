package org.smyld.db.oracle;

import java.sql.SQLException;

import javax.sql.ConnectionPoolDataSource;

import oracle.jdbc.pool.OracleConnectionCacheImpl;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class SMYLDOraConCashImpl extends OracleConnectionCacheImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMYLDOraConCashImpl() throws SQLException {
	}

	public SMYLDOraConCashImpl(ConnectionPoolDataSource connPoolDS)
			throws SQLException {
		super(connPoolDS);

	}

	public void closePooledConnection(OracleConnectionPoolDataSource connection)
			throws SQLException {
		super.closePooledConnection(connection.getPooledConnection());
		// System.out.println("Close pooled connection is called ..... ");
	}

}
