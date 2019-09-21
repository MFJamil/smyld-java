package org.smyld.db.schema.oracle;

import org.smyld.db.schema.Table;

public class OraTable extends Table {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	OraTableSpace tableSpace;

	public OraTable() {
	}

	public OraTableSpace getTableSpace() {
		return tableSpace;
	}

	
	public void setTableSpace(OraTableSpace tableSpace) {
		this.tableSpace = tableSpace;
	}

	@Override
	public boolean equals(Object compTable) {
		if (super.equals(compTable)) {
			if (compTable instanceof OraTable) {
				OraTable table = (OraTable) compTable;
				if (!tableSpace.equals(table.getTableSpace()))
					addSchemaLog(this, table);
			}
		}
		return (logs.size() == 0);
	}

}
