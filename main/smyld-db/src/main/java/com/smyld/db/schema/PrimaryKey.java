package com.smyld.db.schema;

import com.smyld.text.TextUtil;

public class PrimaryKey extends SchemaObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String columnName;
	int sequence;

	public PrimaryKey() {
		setSchemaType(TAG_NAME_P_KEY);
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@Override
	public boolean equals(Object compare) {
		if (compare instanceof PrimaryKey) {
			PrimaryKey comp = (PrimaryKey) compare;
			if (TextUtil.compare(name, comp.getName()))
				if (TextUtil.compare(columnName, comp.getColumnName()))
					return (sequence == comp.getSequence());
		}
		return false;
	}

}
