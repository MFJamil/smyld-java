package org.smyld.lang.script.java;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.smyld.lang.script.util.Method;
import org.smyld.text.TextUtil;

public class JavaMethod extends Method {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,String> exceptions;
	boolean withBody;
	boolean isConstructor = false;

	public JavaMethod(String Name, String Scope, String ReturnType) {
		super(Name, Scope, ReturnType);
	}

	public JavaMethod(String Name, String Scope, String ReturnType,
			boolean Construtor) {
		this(Name, Scope, ReturnType);
		isConstructor = Construtor;
	}

	@Override
	public void addModifier(String newModifier) {
		if (!newModifier.equals(org.smyld.lang.script.java.JavaClassBody.MODIFIER_FINAL)) {
			super.addModifier(newModifier);
		}
	}

	@Override
	public boolean parseMethod(String codeLine) {
		return false;
	}

	public void addExceptionThrowing(String exceptionClass) {
		if (exceptions == null) {
			exceptions = new HashMap<String,String>();
		}
		exceptions.put(exceptionClass, exceptionClass);
	}

	@Override
	public String print(int tabSeq) {
		String margin = null;
		if (tabSeq > 0) {
			margin = TextUtil.createWord("\t", tabSeq);
		}

		body.setLength(0);
		// Printing the method header
		body.append(margin + scope + " ");
		fillInModifiers();
		if (!isConstructor) {
			if (returnType == null) {
				returnType = RETURN_TYPE_VOID;
			}
			body.append(returnType + " ");
		}
		body.append(name);
		body.append("(");
		fillInParameters();
		body.append(")");
		if (exceptions != null) {
			if (exceptions.size() > 0) {
				body.append("throws ");
				Iterator<String> itr = exceptions.values().iterator();
				while (itr.hasNext()) {
					body.append(itr.next());
					if (itr.hasNext()) {
						body.append(",");
					}
				}
			}
		}
		// In case the method is abstract then it must be ended with semicolon
		if (modifiers.containsKey(JavaClassBody.MODIFIER_ABSTRACT)) {
			body.append(";");
		} else {
			adjustCodeLines();
			tabSeq++;
			body.append("{\n");
			if (codeLines.size() > 0) {
				
				for (String currentLineCode : codeLines) {
					if (isBlockEnd(currentLineCode.trim())) {
						tabSeq--;
					}
					margin = TextUtil.createWord("\t", tabSeq);
					if (isBlockStart(currentLineCode.trim())) {
						tabSeq++;
					}
					body.append(margin + currentLineCode);
					if (isLineEnd(currentLineCode.trim())) {
						body.append(";");
					}
					body.append("\n");
				}
			}
			tabSeq--;
			margin = TextUtil.createWord("\t", tabSeq);
			body.append(margin + "}\n");
		}
		return body.toString();
	}

	private int getLongestEquation() {
		int equalIndex = 0;
		for (String currentLineCode : codeLines) {
			if (currentLineCode != null) {
				if (equalIndex < currentLineCode.indexOf("=")) {
					equalIndex = currentLineCode.indexOf("=");
				}
			}
		}
		return equalIndex;
	}

	private int getLongestNew() {
		int newIndex = 0;
		int bracketIndex = 0;
		for (String currentLineCode : codeLines) {
			newIndex = currentLineCode.indexOf("new ");
			if (newIndex != -1) {
				if (bracketIndex < currentLineCode.indexOf("(")) {
					bracketIndex = currentLineCode.indexOf("(");
				}
			}
		}
		return bracketIndex;
	}

	private void adjustCodeLines() {
		// Adjusting equal sign
		Vector<String> bufferLines = new Vector<String>(codeLines.size());
		String currentLineCode = null;

		int longestCode = getLongestEquation();
		for (int i = 0; i < codeLines.size(); i++) {
			currentLineCode = (String) codeLines.get(i);
			if (isAdjustable(currentLineCode)) {
				currentLineCode = allignCodeLine(currentLineCode, longestCode,
						"=");
			}
			bufferLines.add(i, currentLineCode);
		}
		codeLines = bufferLines;
		bufferLines = new Vector<String>(codeLines.size());
		// adjust new instances lines
		int longestNew = getLongestNew();
		// System.out.println("longestNew : " + longestNew);
		for (int i = 0; i < codeLines.size(); i++) {
			currentLineCode = (String) codeLines.get(i);
			if (isAdjustable(currentLineCode)) {
				currentLineCode = allignCodeLine(currentLineCode, longestNew,
						"(");
			}
			bufferLines.add(i, currentLineCode);
		}
		codeLines = bufferLines;
	}

	private boolean isAdjustable(String codeLine) {
		return ((codeLine.indexOf("=") != -1) && (codeLine.indexOf("new ") != -1));
	}

	private String allignCodeLine(String codeLine, int longIndex,
			String offsetText) {
		StringBuffer buffer = new StringBuffer();
		int currentIndex = codeLine.indexOf(offsetText);
		if (currentIndex != -1) {
			if (longIndex > currentIndex) {
				buffer.append(codeLine.substring(0, currentIndex));
				buffer.append(TextUtil
						.createWord(' ', longIndex - currentIndex));
				buffer.append(offsetText);
				buffer.append(codeLine.substring(currentIndex + 1));
				codeLine = buffer.toString();
			}
		}
		return codeLine;
	}

	private boolean isLineEnd(String lineCode) {
		if (!lineCode.endsWith("}")) {
			if (!lineCode.endsWith("{")) {
				if (!lineCode.endsWith(":")) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isBlockStart(String lineCode) {
		return (lineCode.endsWith("{"));
	}

	private boolean isBlockEnd(String lineCode) {
		return ((lineCode.endsWith("}")) || (lineCode.endsWith("})")));
	}

	public static final String RETURN_TYPE_VOID = "void";

}
