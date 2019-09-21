package com.smyld.gui.table;

import java.util.Vector;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class SMYLDTableColumnModel extends DefaultTableColumnModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMYLDTableColumnModel() {
		super();
	}

	@Override
	public void addColumn(TableColumn col) {
		super.addColumn(col);
	}

	public void setColumns(Vector<TableColumn> columns) {
		tableColumns = columns;
	}

}
