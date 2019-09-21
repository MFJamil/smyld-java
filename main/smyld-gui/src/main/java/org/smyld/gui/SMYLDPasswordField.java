package org.smyld.gui;

import javax.swing.JPasswordField;

public class SMYLDPasswordField extends JPasswordField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMYLDPasswordField() {
		super();
	}

	public SMYLDPasswordField(int cols) {
		super(cols);
	}

	public void reset() {
		super.setText("");
	}

}
