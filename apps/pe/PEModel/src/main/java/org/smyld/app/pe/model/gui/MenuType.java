package org.smyld.app.pe.model.gui;

import org.smyld.app.pe.model.Constants;


public enum MenuType implements Constants {
	ITEM,SEPARATOR,COMBO,CHECK,RADIO,GROUP,LABEL,NULL;
	public static MenuType parse(String incType){
		if (incType==null) return ITEM;
		if (incType.equals(TAG_ATT_MENU_TYPE_SEPARATOR)) return SEPARATOR;
		if (incType.equals(TAG_ATT_MENU_TYPE_COMBO)) return COMBO;
		if (incType.equals(TAG_ATT_MENU_TYPE_CHECK)) return CHECK;
		if (incType.equals(TAG_ATT_MENU_TYPE_RADIO)) return RADIO;
		if (incType.equals(TAG_ATT_MENU_TYPE_GROUP)) return GROUP;
		if (incType.equals(TAG_ATT_MENU_TYPE_LABEL)) return LABEL;
		
		
		return ITEM;
	}
}
