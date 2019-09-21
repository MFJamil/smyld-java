package org.smyld.app.pe.model.gui;

import java.util.HashMap;

public class GUITable extends GUIComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,GUITableColumn> cols;

	public GUITable() {

	}

	public void addColumn(GUITableColumn newCol) {
		if (cols == null) {
			cols = new HashMap<String,GUITableColumn>();
		}
		cols.put(newCol.getID(), newCol);
	}

	public HashMap<String,GUITableColumn> getColumns() {
		return cols;
	}

}
