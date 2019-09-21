package com.smyld.db.schema;

import com.smyld.text.TextUtil;

public class ForeignKey extends PrimaryKey {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PrimaryKey primaryKey;
	String table;

	public ForeignKey() {
		setSchemaType(TAG_NAME_F_KEY);
	}

	public PrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(PrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	@Override
	public boolean equals(Object compare) {
		if (compare instanceof ForeignKey) {
			ForeignKey comp = (ForeignKey) compare;
			if (TextUtil.compare(name, comp.getName()))
				if (TextUtil.compare(table, comp.getTable()))
					if (TextUtil.compare(columnName, comp.getColumnName()))
						return (TextUtil.compare(primaryKey.getColumnName(),
								comp.getPrimaryKey().getColumnName()));
		}
		return false;
	}

}
