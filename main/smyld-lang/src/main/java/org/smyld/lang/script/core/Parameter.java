/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.lang.script.core;

import org.smyld.SMYLDObject;

public class Parameter extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String Name;
	String Type;

	public Parameter(String parameterName, String parameterType) {
		setName(parameterName);
		setType(parameterType);
	}

	public String getName() {
		return Name;
	}

	public void setName(String newName) {
		Name = newName;
	}

	public String getType() {
		return Type;
	}

	public void setType(String newType) {
		Type = newType;
	}
}
