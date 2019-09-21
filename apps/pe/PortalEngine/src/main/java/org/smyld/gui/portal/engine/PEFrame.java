package org.smyld.gui.portal.engine;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PEFrame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PEFrame() {
		setBorder(BorderFactory.createLoweredBevelBorder());
	}

	public PEFrame(String title) {
		setBorder(BorderFactory.createTitledBorder(new BevelBorder(
				BevelBorder.LOWERED), title));
	}
}
