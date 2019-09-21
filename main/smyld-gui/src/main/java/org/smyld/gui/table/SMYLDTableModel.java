package org.smyld.gui.table;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class SMYLDTableModel extends DefaultTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMYLDTableModel() {
		super();
	}

	public SMYLDTableModel(Vector<TableColumn> colnames, int rowcount) {
		super(colnames, rowcount);
	}

	public SMYLDTableModel(Object[][] data, Object[] colNames) {
		super(data, colNames);
	}

}
