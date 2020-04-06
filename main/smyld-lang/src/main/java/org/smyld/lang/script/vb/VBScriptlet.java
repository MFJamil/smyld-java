package org.smyld.lang.script.vb;

import java.util.HashMap;

import org.smyld.lang.script.core.BooleanExpression;
import org.smyld.lang.script.core.Scriptlet;

public class VBScriptlet extends Scriptlet implements VBConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// IntObjectHashTable vbKeyWords;
	public VBScriptlet() {
		init();

	}

	private void init() {
		scriptBooleanExp = new VBBooleanExp();
		/*
		 * vbKeyWords = new IntObjectHashTable();
		 * vbKeyWords.addElement(CODE_TYPE_IF ,VB_CODE_IF );
		 * vbKeyWords.addElement(CODE_TYPE_IF_END ,VB_CODE_IF_END );
		 * vbKeyWords.addElement(CODE_TYPE_ELSE ,VB_CODE_ELSE );
		 * vbKeyWords.addElement(CODE_TYPE_FOR ,VB_CODE_FOR );
		 * vbKeyWords.addElement(CODE_TYPE_FOR_END ,VB_CODE_FOR_END );
		 * vbKeyWords.addElement(CODE_TYPE_FUNCTION ,VB_CODE_FUN );
		 * vbKeyWords.addElement(CODE_TYPE_FUNCTION_END ,VB_CODE_FUN_END );
		 * vbKeyWords.addElement(CODE_TYPE_PROCEDURE ,VB_CODE_SUB );
		 * vbKeyWords.addElement(CODE_TYPE_PROCEDURE_END,VB_CODE_SUB_END );
		 * vbKeyWords.addElement(CODE_TYPE_COMMENT ,VB_CODE_COM );
		 * vbKeyWords.addElement(CODE_TYPE_CONSTANT ,VB_CODE_CONSTANT);
		 * vbKeyWords.addElement(CODE_TYPE_DO ,VB_CODE_DO );
		 * vbKeyWords.addElement(CODE_TYPE_DO_END ,VB_CODE_LOOP );
		 * vbKeyWords.addElement(CODE_TYPE_EXIT_DO ,VB_CODE_EXIT_DO );
		 * vbKeyWords.addElement(CODE_TYPE_EXIT_FOR ,VB_CODE_EXIT_FOR);
		 * vbKeyWords.addElement(CODE_TYPE_EXIT_FUN ,VB_CODE_EXIT_FUN);
		 * vbKeyWords.addElement(CODE_TYPE_EXIT_SUB ,VB_CODE_EXIT_SUB);
		 * vbKeyWords.addElement(CODE_TYPE_CALL ,VB_CODE_CALL );
		 * vbKeyWords.addElement(CODE_TYPE_GOTO ,VB_CODE_GOTO );
		 * //vbKeyWords.addElement(CODE_TYPE_,VB_CODE_);
		 */
	}

	@Override
	public int getCodeLineType(String codeLine) {
		codeLine = codeLine.trim().toLowerCase();
		if (codeLine.startsWith(VB_CODE_DIM)) {
			return CODE_TYPE_DECLARE;
		} else if (codeLine.startsWith(VB_CODE_SUB)) {
			return CODE_TYPE_PROCEDURE;
		} else if (codeLine.startsWith(VB_CODE_SUB_END)) {
			return CODE_TYPE_PROCEDURE_END;
		} else if (codeLine.startsWith(VB_CODE_FUN)) {
			return CODE_TYPE_FUNCTION;
		} else if (codeLine.startsWith(VB_CODE_FUN_END)) {
			return CODE_TYPE_FUNCTION_END;
		} else if (codeLine.startsWith(VB_CODE_IF)) {
			return CODE_TYPE_IF;
		} else if (codeLine.startsWith(VB_CODE_ELSE)) {
			return CODE_TYPE_ELSE;
		} else if (codeLine.startsWith(VB_CODE_IF_END)) {
			return CODE_TYPE_IF_END;
		} else if (codeLine.startsWith(VB_CODE_COM)) {
			return CODE_TYPE_COMMENT;
		} else if (codeLine.startsWith(VB_CODE_CALL)) {
			return CODE_TYPE_CALL;
		} else if (codeLine.startsWith(VB_CODE_DECLARE)) {
			return CODE_TYPE_DECLARE;
		} else if (codeLine.startsWith(VB_CODE_PCONSTANT)) {
			return CODE_TYPE_CONSTANT;
		} else if (codeLine.startsWith(VB_CODE_DO)) {
			return CODE_TYPE_DO;
		} else if (codeLine.startsWith(VB_CODE_LOOP)) {
			return CODE_TYPE_DO_END;
		} else if (codeLine.startsWith(VB_CODE_FOR)) {
			return CODE_TYPE_FOR;
		} else if (codeLine.startsWith(VB_CODE_FOR_END)) {
			return CODE_TYPE_FOR_END;
		} else if (codeLine.startsWith(VB_CODE_EXIT_DO)) {
			return CODE_TYPE_EXIT_DO;
		} else if (codeLine.startsWith(VB_CODE_EXIT_FOR)) {
			return CODE_TYPE_EXIT_FOR;
		} else if (codeLine.startsWith(VB_CODE_EXIT_FUN)) {
			return CODE_TYPE_EXIT_FUN;
		} else if (codeLine.startsWith(VB_CODE_EXIT_SUB)) {
			return CODE_TYPE_EXIT_SUB;
		} else if (codeLine.startsWith(VB_CODE_GOTO)) {
			return CODE_TYPE_GOTO;
		} else if (codeLine.startsWith(VB_CODE_ON_ERR)) {
			return CODE_TYPE_ON_ERR;
		} else if (codeLine.startsWith(VB_CODE_OPT_EXPL)) {
			return CODE_TYPE_OPT_EXPL;
		} else if (codeLine.startsWith(VB_CODE_RESUME)) {
			return CODE_TYPE_RESUME;
		} else if (codeLine.startsWith(VB_CODE_BLK_END)) {
			return CODE_TYPE_BLOCK_END;
		} else if (codeLine.startsWith(VB_CODE_PRIVATE)) {
			return CODE_TYPE_PRIVATE;
		} else if (codeLine.startsWith(VB_CODE_SELECT)) {
			return CODE_TYPE_SELECT;
		} else if (codeLine.startsWith(VB_CODE_SELECT_END)) {
			return CODE_TYPE_SELECT_END;
		} else if (codeLine.startsWith(VB_CODE_CASE)) {
			return CODE_TYPE_CASE;
		} else if (codeLine.startsWith(VB_CODE_CONSTANT)) {
			return CODE_TYPE_CONSTANT;
		}

		if (isLable(codeLine)) {
			return CODE_TYPE_LABLE;
		}
		if (!isEquation(codeLine)) {
			return CODE_TYPE_UNKNOWN;
		}
		return CODE_TYPE_EQUATION;
	}

	/* Code for detecting if the code line is lable */
	private boolean isLable(String codeLine) {
		int spaceIndex = codeLine.indexOf(" ");
		int dotIndex = codeLine.indexOf(":");
		if (dotIndex != -1) {
			if (spaceIndex != -1) {
				if (spaceIndex > dotIndex) {
					return true;
				}
			} else {
				return true;
			}
		}
		return false;
	}

	/* Code for checking if the code line is equation */
	// The underlaying code is very simple and must be extended to cover more
	// sophisticated issues
	private boolean isEquation(String codeLine) {
		int equalIndex = codeLine.indexOf("=");
		if (equalIndex == -1) {
			return false;
		}
		return true;
	}

	@Override
	public String doFilterComment(String codeLine) {
		int pos = 0;

		if ((pos = codeLine.indexOf(getCodeComment())) != -1) {
			return codeLine.substring(0, pos);
		}
		return codeLine;
	}

	public BooleanExpression doParseIfStatement(String ifCodeLine) {
		if (containsQutations(ifCodeLine)) {
			HashMap<String, String> result = filterOutQutations(ifCodeLine);
			ifCodeLine = result.get(FILTER_CODE_ID);
		}
		ifCodeLine = doFilterComment(ifCodeLine);
		String ifCondition = ifCodeLine.substring(ifCodeLine
				.indexOf(VB_CODE_IF)
				+ VB_CODE_IF.length(), ifCodeLine.indexOf(VB_CODE_THEN));
		scriptBooleanExp.setText(ifCondition);
		scriptBooleanExp.parseText();
		return scriptBooleanExp;
	}

	public HashMap<String, String> filterOutQutations(String codeLine) {
		// Looping a long the word
		boolean insideQute = false;
		HashMap<String, String> qutations = new HashMap<String, String>();
		StringBuffer filteredCode = new StringBuffer();
		int endPos = 0, startPos = 0, qutationsCount = 0;
		for (int i = 0; i < codeLine.length(); i++) {
			char currentChar = codeLine.charAt(i);
			if (currentChar == '\"') {
				if (!insideQute) {
					insideQute = true;
					startPos = i + 1;
				} else {
					insideQute = false;
					endPos = i;
					String quteID = QUTATION_ID + qutationsCount;
					qutations.put(quteID, codeLine.substring(startPos, endPos));
					filteredCode.append(quteID);
					qutationsCount++;
				}
			} else {
				if (!insideQute) {
					filteredCode.append(currentChar);
				}
			}
		}
		qutations.put(FILTER_CODE_ID, filteredCode.toString());
		return qutations;
		// codeLine
	}

	public VBMethod doParseFunction(String funCodeLine) {
		funCodeLine = doFilterComment(funCodeLine);
		VBMethod newMethod = new VBMethod();
		// if (newMethod.parseMethod(funCodeLine))
		// System.out.println( "Code Generated :- " + newMethod.print());
		return newMethod;
	}

	@Override
	public String getCodeComment() {
		return VB_CODE_COM;
	}

}
