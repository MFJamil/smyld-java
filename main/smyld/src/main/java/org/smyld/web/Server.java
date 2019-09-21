package org.smyld.web;

import org.smyld.SMYLDObject;

public class Server extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String type;
	String path;
	String vendor;

	public Server() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
}
