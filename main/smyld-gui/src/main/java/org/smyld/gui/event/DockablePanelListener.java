package org.smyld.gui.event;

import java.io.Serializable;

import org.smyld.gui.panels.DockablePanel;

public interface DockablePanelListener extends Serializable {
	public void panelClosed(DockablePanel panel);

	public void panelHidden(DockablePanel panel);

	public void panelShown(DockablePanel panel);

	public void panelDragged(DockablePanel panel);

	public void panelDropped(DockablePanel panel);
}
