package com.smyld.net;

import com.smyld.SMYLDObject;

public class Station extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String IPAddress;
	int PortNo;

	public Station() {
	}

	public String getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(String newIPAddress) {
		IPAddress = newIPAddress;
	}

	public int getPortNo() {
		return PortNo;
	}

	public void setPortNo(int newPortNo) {
		PortNo = newPortNo;
	}
}
