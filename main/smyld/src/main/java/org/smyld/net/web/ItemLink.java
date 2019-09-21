package org.smyld.net.web;

import org.smyld.SMYLDObject;

public class ItemLink extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	int startIndex;
	int endIndex;
	int type;

	public ItemLink() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
