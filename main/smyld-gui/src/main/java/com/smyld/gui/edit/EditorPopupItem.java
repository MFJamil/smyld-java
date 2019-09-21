package com.smyld.gui.edit;

import javax.swing.ImageIcon;

public interface EditorPopupItem {
	//In case only that this item wants to have its own icon, otherwise the icon will be taked from the category
	public ImageIcon getIcon();
	public String getName();
	public String getDescription();
	public String getContents();
	public EditorPopupItemCategory getCategory();
	
}
