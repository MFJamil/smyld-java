package org.smyld.lang.script.vb;

import org.smyld.lang.script.util.BooleanExpression;

public class VBBooleanExp extends BooleanExpression {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VBBooleanExp() {
	}

	public VBBooleanExp(String Text) {
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

	@SuppressWarnings("unused")
	public String printCode() {
		StringBuffer result;
		if (conditions.size() > 0) {
			result = new StringBuffer();

		}
		return null;
	}

	public static final String COND_GROUP_SEP_AND = "and";
	public static final String COND_GROUP_SEP_OR = "or";
	public static final String COND_SEP_EQ = "=";
	public static final String COND_SEP_NOT_EQ = "<>";
	public static final String COND_SEP_NOT = "not";
	public static final String COND_SEP_LESS = "<";
	public static final String COND_SEP_GREATER = ">";
	public static final String COND_SEP_LESS_EQ = "<=";
	public static final String COND_SEP_GREATER_EQ = ">=";
	public static final String[] separators = { COND_GROUP_SEP_AND,
			COND_GROUP_SEP_OR };

}
