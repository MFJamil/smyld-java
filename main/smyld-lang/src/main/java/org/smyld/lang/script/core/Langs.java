/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.lang.script.core;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Langs {
	Java("java"),
	VisualBasic6("vb6"),
	PLSQL("plsql"),
	TypeScript("ts"),
	JavaScript("js"),
	Unknown("NA");

	@NonNull
	private String shortName;

}
