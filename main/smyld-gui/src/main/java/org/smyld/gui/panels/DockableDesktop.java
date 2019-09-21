package org.smyld.gui.panels;

import org.smyld.gui.GUIAction;
import org.smyld.gui.SMYLDDesktopPane;
import org.smyld.gui.SMYLDInternalFrame;
import org.smyld.gui.event.DockableDesktopListener;

public class DockableDesktop extends DockableTemplate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private DockableDesktopListener desktopListener;
	private SMYLDDesktopPane deskTopPane = new SMYLDDesktopPane();

	public DockableDesktop() {
		super(new SMYLDDesktopPane());
		deskTopPane = (SMYLDDesktopPane) super.mainComponent;
	}

	public DockableDesktop(DockableDesktopListener listener) {
		this();
		desktopListener = listener;
	}

	public void addInternalFrame(SMYLDInternalFrame newInternFrame) {
		deskTopPane.addInternalFrame(newInternFrame);
	}

	@Override
	public void doProcessGUIAction(GUIAction incomingAction) {
		deskTopPane.processGUIAction(incomingAction);
	}
}
