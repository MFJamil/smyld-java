package org.smyld.lang.script.util;

import java.util.Vector;

import org.smyld.SMYLDObject;
import org.smyld.text.MultiTextPatternTockenizer;

public abstract class BooleanExpression extends SMYLDObject {
	protected Vector<Expression> conditions;
	protected String text;
	protected String[] condsSep;
	protected String[] singleCondSep;

	protected MultiTextPatternTockenizer condsTocken;

	public BooleanExpression() {
		conditions = new Vector<Expression>();
		condsTocken = new MultiTextPatternTockenizer(getSeparators(), true);
		condsTocken.setPattern("(", ")");
	}

	public BooleanExpression(String Text) {
		this();
		text = Text.trim();
	}

	public boolean isMultiCondition(String condText) {
		return false;
	}

	public abstract String[] getSeparators();

	public void parseText() {
		if (conditions.size() > 0) {
			conditions.removeAllElements();
		}
		parseConditions(text, conditions, null);
		if (conditions.size() == 0) {
			conditions.add(new Expression("0", null, text, null));
		}

	}

	private void parseConditions(String text, Vector<Expression> currentConditions,
			String parentID) {
		String currentID = null;
		Object[] conds = null;
		condsTocken.setText(text);
		conds = condsTocken.parseTokens();
		for (int i = 0; i < conds.length; i = i + 2) {
			if (i == 0) {
				currentID = "0";
				currentConditions.add(new Expression(currentID, parentID,
						conds[i].toString(), null));
			} else {
				currentID = Integer.toString(i - 1);
				currentConditions.add(new Expression(currentID, parentID,
						conds[i].toString(), conds[i - 1].toString()));
			}
			if (isMultiCondition(conds[i].toString())) {
				parseConditions(conds[i].toString(), currentConditions,
						currentID);
			}
		}
		// return currentConditions;
	}

	public String getText() {
		return text;
	}

	public void setText(String newText) {
		text = newText;
	}

}
