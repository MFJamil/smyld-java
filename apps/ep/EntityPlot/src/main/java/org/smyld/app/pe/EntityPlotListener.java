package org.smyld.app.pe;

import java.awt.Point;

public interface EntityPlotListener {
	public void entityPopup(EntityPlotter entity,Point mouseLocation);
	public void entityDoubleClicked(EntityPlotter entity);
	public void entityDeleted(EntityPlotter entity);
	public void entityConnected(EntityPlotter from,EntityPlotter to);
	public void entityDisconnected(EntityPlotter from,EntityPlotter to);

}
