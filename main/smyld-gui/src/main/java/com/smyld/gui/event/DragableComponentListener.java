package com.smyld.gui.event;

import javax.swing.JComponent;

public interface DragableComponentListener {
	public void dragEnded();

	public void dragStarted();

	public JComponent getComponent();

	public boolean isInternal();
}
