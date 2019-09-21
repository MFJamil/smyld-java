package org.smyld.gui.layout;

import org.smyld.SMYLDObject;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class SMYLDRCConstraints extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int row;
	int column;
	int width;
	int height;

	/**
	 * 
	 * @see
	 * @since
	 */
	public SMYLDRCConstraints(int Column, int Row, int Width, int Height) {
		row = Row;
		column = Column;
		width = Width;
		height = Height;
	}

	public SMYLDRCConstraints() {
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
