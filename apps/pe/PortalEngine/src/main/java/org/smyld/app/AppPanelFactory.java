package org.smyld.app;

import org.smyld.gui.SMYLDPanel;

public class AppPanelFactory extends AppBaseFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppPanelFactory() {
	}

	public SMYLDPanel getPanel(String panelID,Object appManager) {
		System.out.println("Hi this is the main Panles Factory ---");
		return null;
	}
	
}
