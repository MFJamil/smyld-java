package org.smyld.lang.script.vb;

import org.smyld.lang.script.util.Method;

public class VBMethod extends Method implements VBConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean isFunction = true;

	public VBMethod(String Name, String Scope, String ReturnType) {
		super(Name, Scope, ReturnType);
	}

	public VBMethod() {
		super();
	}

	@Override
	public boolean parseMethod(String codeLine) {
		codeLine = codeLine.trim();
		String[] elements = exctractDefinitionPart(codeLine);
		if (isScopWord(elements[0])) {
			scope = elements[0];
			detectFunction(elements[1]);
			name = elements[2];
		} else {
			detectFunction(elements[0]);
			name = elements[1];
		}
		String[] parameters = exctractParameters(codeLine);
		if (parameters != null) {
			parseParameters(parameters);
		}
		int returnTypeIndex = codeLine.lastIndexOf(VB_CODE_AS);
		if (returnTypeIndex != -1) {
			returnType = codeLine.substring(returnTypeIndex
					+ VB_CODE_AS.length());
		}
		return true;

	}

	private void parseParameters(String[] params) {
		VBVariable vbVariable = new VBVariable();
		for (String element : params) {
			vbVariable.parseVariable(element);
			addParameter(vbVariable.getName(), vbVariable.getType());
		}

	}

	private boolean isScopWord(String word) {
		return ((word.equals(SCOPE_PUBLIC)) || (word.equals(SCOPE_PRIVATE)));
	}

	private void detectFunction(String word) {
		if (word.equals(VB_CODE_SUB)) {
			isFunction = false;
		} else if (word.equals(VB_CODE_FUN)) {
			isFunction = true;
		}
	}

	@Override
	public String fillParameter(String name, String type) {
		return VBVariable.constructVariable(name, type);
	}

	@Override
	public String print() {
		if (scope != null) {
			body.append(scope + " ");
		}
		fillInModifiers();
		if (returnType == null) {
			isFunction = false;
		}
		if (isFunction) {
			body.append("Function ");
		} else {
			body.append("Sub ");
		}
		body.append(name);
		body.append("(");
		fillInParameters();
		body.append(") ");
		if (isFunction) {
			body.append("As ");
			body.append(returnType);
		}
		body.append("\n");
		if (isFunction) {
			body.append("End Function");
		} else {
			body.append("End Sub");
		}
		return body.toString();
	}

}
