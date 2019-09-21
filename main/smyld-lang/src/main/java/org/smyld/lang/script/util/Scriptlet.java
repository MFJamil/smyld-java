package org.smyld.lang.script.util;

import org.smyld.SMYLDObject;
import org.smyld.text.TextTokenizer;

public abstract class Scriptlet extends SMYLDObject {

	protected BooleanExpression scriptBooleanExp;

	public Scriptlet() {
	}

	public static String[] getCodeLineElements(String codeLine) {
		return new TextTokenizer(codeLine, " ").parseTokens();
	}

	public String doFilterComment(String codeLine) {
		int pos = 0;
		if ((pos = codeLine.indexOf(getCodeComment())) != -1) {
			return codeLine.substring(0, pos);
		}
		return codeLine;
	}

	public boolean containsQutations(String codeLine) {
		return codeLine.indexOf("\"") != -1;
	}

	public abstract int getCodeLineType(String codeLine);

	public abstract String getCodeComment();

	/*
	 * public static final int CODE_TYPE_DECLARE = 1; public static final int
	 * CODE_TYPE_METHODE = 2; public static final int CODE_TYPE_FUNCTION = 3;
	 * public static final int CODE_TYPE_PROCEDURE = 4; public static final int
	 * CODE_TYPE_IF = 5; public static final int CODE_TYPE_IF_END = 5; public
	 * static final int CODE_TYPE_FOR = 6; public static final int
	 * CODE_TYPE_FOR_END = 6; public static final int CODE_TYPE_COMMENT = 7;
	 * public static final int CODE_TYPE_WHILE = 8; public static final int
	 * CODE_TYPE_WHILE_END = 8; public static final int CODE_TYPE_SWITCH = 9;
	 * public static final int CODE_TYPE_SWITCH_END = 9; public static final int
	 * CODE_TYPE_BLOCK_END = 10; public static final int CODE_TYPE_CALL = 11;
	 * public static final int CODE_TYPE_CONSTANT = 12; public static final int
	 * CODE_TYPE_LOOP = 13;
	 */
	public static final String QUTATION_ID = "q~";
	public static final String FILTER_CODE_ID = "fileteredCode";
	ClassBody ActiveClass;

	public ClassBody getActiveClass() {
		return ActiveClass;
	}

	public void setActiveClass(ClassBody ActiveClass) {
		this.ActiveClass = ActiveClass;
	}

}
