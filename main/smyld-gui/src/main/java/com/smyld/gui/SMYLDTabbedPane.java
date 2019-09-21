package com.smyld.gui;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class SMYLDTabbedPane extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComponent[] tabs;
	@SuppressWarnings("unused")
	private String[] tabTitles;

	public SMYLDTabbedPane() {
		super();
	}

	public SMYLDTabbedPane(JComponent[] tabElems, String[] titles) {
		super();
		this.tabs = tabElems;
		this.tabTitles = titles;
	}

	public void addTab(String title, JComponent component) {
		super.addTab(title, component);
	}

	@SuppressWarnings("unused")
	private void initTabs() {
		for (JComponent elem : tabs) {
		}
	}

}
