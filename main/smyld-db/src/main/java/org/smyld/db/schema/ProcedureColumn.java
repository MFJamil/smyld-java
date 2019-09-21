package org.smyld.db.schema;

public class ProcedureColumn extends TableColumn {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int direction;

	public ProcedureColumn() {
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}
