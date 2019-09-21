package com.smyld.gui;

import java.awt.BorderLayout;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.smyld.gui.event.ActionHandler;
import com.smyld.gui.panels.DockableDesktop;

public class SMYLDMDI extends SMYLDFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel statusBar = new JLabel();
	@SuppressWarnings("unused")
	private JPanel panelCenter = new JPanel();
	private SMYLDDesktopPane desktopPane = new SMYLDDesktopPane();
	private SMYLDPanel contentPanel;
	private ActionHandler mainActionHandler;

	public SMYLDMDI(ActionHandler MainActionHandler) {
		mainActionHandler = MainActionHandler;
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SMYLDMDI() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setContentPane(SMYLDPanel contentPanel,
			ActionHandler actionHandler) {
		mainActionHandler = actionHandler;
		this.contentPanel = contentPanel;
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
	}

	private void jbInit() throws Exception {

		setBorderLayout();
		// panelCenter.setLayout(null);
		// statusBar.setText("");
		this.getContentPane().add(statusBar, BorderLayout.SOUTH);
		this.getContentPane().add(desktopPane, BorderLayout.CENTER);
		desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	}

	public void addInternalFrame(SMYLDInternalFrame newInternFrame) {
		if ((contentPanel != null) && (contentPanel instanceof DockableDesktop)) {
			((DockableDesktop) contentPanel).addInternalFrame(newInternFrame);
		} else {
			desktopPane.addInternalFrame(newInternFrame);
		}
	}

	@Override
	public void processGUIAction(GUIAction newAction) {
		if (mainActionHandler != null) {
			mainActionHandler.processGUIAction(newAction);
		} else {
			desktopPane.processGUIAction(newAction);
		}
	}

}
