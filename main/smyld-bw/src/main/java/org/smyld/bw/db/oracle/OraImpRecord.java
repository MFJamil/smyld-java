package org.smyld.bw.db.oracle;

import org.smyld.bw.db.SMYLDTableRecord;

public class OraImpRecord extends SMYLDTableRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] data;

	public OraImpRecord() {
	}

	@Override
	public void addFlatValues() {
		for (int i = 0; i < data.length; i++)
			addFlatValue(data[i]);
	}

	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}

}
