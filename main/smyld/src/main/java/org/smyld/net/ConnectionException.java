package org.smyld.net;

import org.smyld.SMYLDException;

public class ConnectionException extends SMYLDException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ipAddress;

	public ConnectionException(int exceptionNo) {
		super(exceptionNo);
	}

	public ConnectionException(int exceptionNo, String IPAddress) {
		super(exceptionNo);
	}

	public String getIPAddress() {
		return ipAddress;
	}

	public static final int CONNECTION_CLOSED = 1;
}
