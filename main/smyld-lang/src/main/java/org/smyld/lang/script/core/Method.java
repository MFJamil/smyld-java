/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.lang.script.core;

import java.util.*;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.smyld.text.TextTokenizer;
import org.smyld.text.TextUtil;

@Slf4j
@Setter
@Getter
public abstract class Method extends Variable {
	protected String returnType;
	boolean constructor = false;
	HashMap<String,String> parametersKeys;// new HashMap();
	Vector<Parameter> parameters;
	protected List<String> codeLines = new ArrayList<String>();

	public Method() {
	}

	public Method(String Name) {
		super(Name);
	}

	public Method(String Name, String Scope) {
		super(Name, Scope);
	}

	public Method addCodeLine(String newCodeLine) {
		if (newCodeLine != null) {
			codeLines.add(newCodeLine);
		}
		return this;
	}

	public void addPrintingLineCode(String newCodeLine) {
		if (newCodeLine != null) {
			codeLines.add("System.out.println(\"" + newCodeLine + "\")");
		}
	}

	public Method addCodeLines(List<String> newCodeLines) {
		for (String currentCodeLine : newCodeLines) {
			addCodeLine(currentCodeLine);
		}
		return this;
	}

	public Method(String Name, String Scope, String ReturnType) {
		this(Name, Scope);
		returnType = ReturnType;
	}

	public String[] exctractParameters(String codeLine) {
		int begin = codeLine.indexOf("(");
		int end = codeLine.indexOf(")");
		if ((begin != -1) && (end != -1)) {
			return new TextTokenizer(codeLine.substring(begin + 1, end), ",")
					.parseTokens();
		}
		return null;
	}

	public String[] exctractDefinitionPart(String codeLine) {
		int begin = codeLine.indexOf("(");
		if (begin != -1) {
			return new TextTokenizer(codeLine.substring(0, begin), " ")
					.parseTokens();
		}
		return null;
	}
	protected void adjustCodeLines() {
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
	protected boolean isAdjustable(String codeLine) {
		return ((codeLine.indexOf("=") != -1) && (codeLine.indexOf("new ") != -1));
	}


	protected int getLongestEquation() {
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

	protected int getLongestNew() {
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

	protected String allignCodeLine(String codeLine, int longIndex,
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

	protected boolean isLineEnd(String lineCode) {
		if (!lineCode.endsWith("}")) {
			if (!lineCode.endsWith("{")) {
				if (!lineCode.endsWith(":")) {
					return true;
				}
			}
		}
		return false;
	}

	protected boolean isBlockStart(String lineCode) {
		return (lineCode.endsWith("{"));
	}

	protected boolean isBlockEnd(String lineCode) {
		return ((lineCode.endsWith("}")) || (lineCode.endsWith("})")));
	}



	public void fillInParameters() {
		if (parameters != null) {
			if (parameters.size() > 0) {
				
				Iterator<Parameter> parms = parameters.iterator();
				while (parms.hasNext()) {
					Parameter currentParameter = parms.next();
					String paramName = currentParameter.getName();
					String paramType = currentParameter.getType();
					body.append(fillParameter(paramName, paramType));
					if (parms.hasNext())
						body.append(",");
				}
			}
		}
	}

	public String fillParameter(String name, String type) {
		return type + " " + name;
	}

	public void addParameter(String parameterName, String parameterType) {
		if (parametersKeys == null) {
			parametersKeys = new HashMap<String,String>();
			parameters = new Vector<Parameter>();
		}
		if (!parametersKeys.containsKey(parameterName)) {
			parametersKeys.put(parameterName, parameterName);
			parameters.add(new Parameter(parameterName, parameterType));
		}
	}

	@Override
	public String print() {
		return super.print();
	}

	@Override
	public String print(String indent) {
		return super.print(indent);
	}

	public String print(int tabSeq) {
		return super.print();
	}

	public abstract boolean parseMethod(String codeLine);

}
