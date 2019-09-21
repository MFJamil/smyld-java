package org.smyld.gui.layout;

import org.smyld.SMYLDObject;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class SMYLDXYConstraints extends SMYLDObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x;
	int y;
	int width;
	int height;

	/**
	 * 
	 * @see
	 * @since
	 */
	public SMYLDXYConstraints() {
	}

	public SMYLDXYConstraints(int X, int Y, int Width, int Height) {
		x = X;
		y = Y;
		width = Width;
		height = Height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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
