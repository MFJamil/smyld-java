package org.smyld.app.pe.model.gui;

public class GUITableColumn extends GUIField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int orderInTable;

	public GUITableColumn() {
	}

	public GUITableColumn(int order, String colID, String colLable,
			String colWidth) {
		setOrderInTable(order);
		setID(colID);
		setLabel(colLable);
		setWidth(colWidth);
	}

	public int getOrderInTable() {
		return orderInTable;
	}

	public void setOrderInTable(int orderInTable) {
		this.orderInTable = orderInTable;
	}

	@Override
	public int hashCode() {
		return orderInTable;
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
