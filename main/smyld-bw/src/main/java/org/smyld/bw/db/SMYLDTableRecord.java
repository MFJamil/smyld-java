package org.smyld.bw.db;

import org.smyld.SMYLDObject;

public class SMYLDTableRecord extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected StringBuffer buffer = new StringBuffer();
	String flatValueSeparator;
	String alterSeparator;
	String regexSeparator;
	protected int recordType;

	public SMYLDTableRecord() {
		setFlatValueSeparator("|");
		setRegexSeparator("\\|");
		setAlterSeparator("-");

		reset();

	}

	public void addFlatValues() {
	}

	public String printFlatValues() {
		addFlatValues();
		String value = buffer.toString();
		int idx = value.lastIndexOf(getFlatValueSeparator());
		return value.substring(0, idx);
	}

	protected void addFlatValue(String value) {
		if (value != null) {
			value = value.replaceAll(getRegexSeparator(), getAlterSeparator());
			value = value.replaceAll(OS_NEW_LINE, "");
			buffer.append(value);
		}
		buffer.append(flatValueSeparator);
	}

	public void reset() {
		buffer.setLength(0);
	}

	public String getFlatValueSeparator() {
		return flatValueSeparator;
	}

	public void setFlatValueSeparator(String flatValueSeparator) {
		this.flatValueSeparator = flatValueSeparator;
	}

	public String getAlterSeparator() {
		return alterSeparator;
	}

	public void setAlterSeparator(String alterSeparator) {
		this.alterSeparator = alterSeparator;
	}

	public String getRegexSeparator() {
		return regexSeparator;
	}

	public void setRegexSeparator(String regexSeparator) {
		this.regexSeparator = regexSeparator;
	}

	public int getRecordType() {
		return recordType;
	}

}
