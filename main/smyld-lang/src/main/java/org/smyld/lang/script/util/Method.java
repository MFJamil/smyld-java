package org.smyld.lang.script.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.smyld.text.TextTokenizer;

public abstract class Method extends Variable {
	protected String returnType;
	HashMap<String,String> parametersKeys;// new HashMap();
	Vector<Parameter> parameters;
	protected Vector<String> codeLines = new Vector<String>();

	public Method() {
	}

	public Method(String Name) {
		super(Name);
	}

	public Method(String Name, String Scope) {
		super(Name, Scope);
	}

	public void addCodeLine(String newCodeLine) {
		if (newCodeLine != null) {
			codeLines.add(newCodeLine);
		}
	}

	public void addPrintingLineCode(String newCodeLine) {
		if (newCodeLine != null) {
			codeLines.add("System.out.println(\"" + newCodeLine + "\")");
		}
	}

	public void addCodeLines(Vector<String> newCodeLines) {
		for (String currentCodeLine : newCodeLines) {
			addCodeLine(currentCodeLine);
		}
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

	public String print(int tabSeq) {
		return super.print();
	}

	public abstract boolean parseMethod(String codeLine);

}
