package org.smyld.gui;

import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;

import org.smyld.gui.event.ActionHandler;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class SMYLDDesktopPane extends JDesktopPane implements ActionHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see
	 * @since
	 */
	public SMYLDDesktopPane() {
	}

	public void processGUIAction(GUIAction incomingAction) {
		if (incomingAction != null) {
			Object incObj = incomingAction.getUserObject();
			if (incObj != null) {
				if (incObj instanceof SMYLDInternalFrame) {
					addInternalFrame((SMYLDInternalFrame) incObj);
				}
			}
		}
	}

	public void addInternalFrame(SMYLDInternalFrame newInternFrame) {
		newInternFrame.setVisible(true);
		add(newInternFrame);
		try {
			newInternFrame.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

	}
}
