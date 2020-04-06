/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.lang.script.core;

import org.smyld.SMYLDObject;

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
