package com.smyld.db;

import com.smyld.SMYLDObject;

public class DBConnAddress extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ip;
	String port;

	public DBConnAddress() {
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
}
