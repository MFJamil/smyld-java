package org.smyld.db.oracle;

import javax.sql.ConnectionEvent;
import javax.sql.DataSource;

import oracle.jdbc.pool.OracleConnectionEventListener;

public class SMYLDOraConEvtListener extends OracleConnectionEventListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMYLDOraConEvtListener() {
	}

	public SMYLDOraConEvtListener(DataSource dataSource) {
		super(dataSource);

	}

	@Override
	public void connectionErrorOccurred(ConnectionEvent ce) {
		System.out.println("Connection Error Occured");

		// handleConnectionErrors(ce);
	}

	@Override
	public void connectionClosed(ConnectionEvent ce) {
		System.out.println("Connection Closed");
		// handleConnectionErrors(ce);
	}

}
