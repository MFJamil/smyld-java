package com.smyld.gui;

import javax.swing.JProgressBar;

public class SMYLDProgressBar extends JProgressBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SMYLDLabel label;

	public SMYLDProgressBar() {
		super();
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		label.setVisible(visible);
	}

	public SMYLDProgressBar(String lbl) {
		this();
		this.label = new SMYLDLabel(lbl);
	}

	public void setLabel(String newLabel) {
		label.setText(newLabel);
	}

	public SMYLDLabel getLabel() {
		return label;
	}

}
