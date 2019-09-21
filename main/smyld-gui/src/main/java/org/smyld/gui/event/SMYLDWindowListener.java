package org.smyld.gui.event;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;

import org.smyld.SMYLDObject;

public class SMYLDWindowListener extends SMYLDObject implements WindowListener,
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMYLDWindowListener() {
	}

	public void windowOpened(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	public void windowClosed(WindowEvent e) {
		System.exit(0);
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}
}
