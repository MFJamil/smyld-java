package org.smyld.gui.edit;

import javax.swing.ImageIcon;

public interface EditorPopupItemCategory {
	public String getID();
	public String getName();
	public ImageIcon getIcon();
	public String getDescription();
	public boolean doNeedProcessing();

}
