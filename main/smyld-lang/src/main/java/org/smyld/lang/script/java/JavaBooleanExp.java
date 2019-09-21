package org.smyld.lang.script.java;

import org.smyld.lang.script.util.BooleanExpression;

public class JavaBooleanExp extends BooleanExpression {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JavaBooleanExp() {
	}

	public JavaBooleanExp(String Text) {
		super(Text);
	}

	@Override
	public String[] getSeparators() {
		return separators;
	}

	@Override
	public boolean isMultiCondition(String condText) {
		if (condText.indexOf(COND_GROUP_SEP_AND) == -1) {
			if (condText.indexOf(COND_GROUP_SEP_OR) == -1) {
				return false;
			}
		}
		return true;
	}

	public static final String COND_GROUP_SEP_AND = "&&";
	public static final String COND_GROUP_SEP_OR = "||";
	public static final String COND_SEP_EQ = "==";
	public static final String COND_SEP_NOT_EQ = "!=";
	public static final String COND_SEP_NOT = "!";
	public static final String COND_SEP_LESS = "<";
	public static final String COND_SEP_GREATER = ">";
	public static final String COND_SEP_LESS_EQ = "<=";
	public static final String COND_SEP_GREATER_EQ = ">=";
	public static final String[] separators = { COND_GROUP_SEP_AND,
			COND_GROUP_SEP_OR };
}
