package com.smyld.lang.script.util;

import com.smyld.SMYLDObject;

public class Expression extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ID;
	String parentID;
	String text;
	String linkingText;

	public Expression(String itemID, String ParentID, String Text,
			String LinkingText) {
		ID = itemID;
		parentID = ParentID;
		text = Text;
		linkingText = LinkingText;
	}
}
