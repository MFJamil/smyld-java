package org.smyld.gui;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JTable;

import org.smyld.gui.table.SMYLDTableModel;

public class SMYLDTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMYLDTable() {
		super();
		init();
	}

	public SMYLDTable(SMYLDTableModel model) {
		super(model);
		init();
	}

	public SMYLDTable(Vector<Vector<Object>> rowData, Vector<String> colNames) {
		super(rowData, colNames);
		init();
	}

	public SMYLDTable(Object[][] rowData, Object[] colNames) {
		super(rowData, colNames);
		init();
	}

	public SMYLDTable(int rows, int cols) {
		super(rows, cols);
		init();
	}

	private void init() {
		setBorder(BorderFactory.createEtchedBorder());
		getTableHeader().setVisible(true);
		setShowHorizontalLines(true);
	}

}
