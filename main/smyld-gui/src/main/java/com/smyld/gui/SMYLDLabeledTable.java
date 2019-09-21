package com.smyld.gui;

import javax.swing.JComponent;

public class SMYLDLabeledTable extends SMYLDLabeledComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SMYLDTable tableField;
	Object[][] tableData;
	Object[] tableCols;

	public SMYLDLabeledTable(String Label, Object[][] data, Object[] cols) {
		super(Label);
		tableData = data;
		tableCols = cols;
		init();
		preset();
	}

	@Override
	public JComponent createMainComponent() {
		if ((tableData != null) && (tableCols != null)) {
			tableField = new SMYLDTable(tableData, tableCols);
		} else {
			tableField = new SMYLDTable();
		}
		return tableField;
	}

	private void preset() {
		setAutoscrolls(true);
	}

	public SMYLDTable getTable() {
		return tableField;
	}

	public Object[] getColumns() {
		return tableCols;
	}

}
