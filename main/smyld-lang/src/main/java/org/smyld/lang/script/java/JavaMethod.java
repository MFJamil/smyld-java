package org.smyld.lang.script.java;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.smyld.lang.script.core.LangContants;
import org.smyld.lang.script.core.Method;
import org.smyld.lang.script.util.CodeUtils;
import org.smyld.text.TextUtil;

public class JavaMethod extends Method {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,String> exceptions;
	boolean withBody;


	public JavaMethod(String Name, String Scope, String ReturnType) {
		super(Name, Scope, ReturnType);
	}

	public JavaMethod(String Name, String Scope, String ReturnType,
			boolean isConstrutor) {
		this(Name, Scope, ReturnType);
		setConstructor(isConstrutor);
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
		String margin = CodeUtils.getTabsIndent(tabSeq);
		body.setLength(0);
		// Printing the method header
		body.append(margin + scope + " ");
		fillInModifiers();
		if (!isConstructor()) {
			if (returnType == null) {
				returnType = LangContants.RETURN_TYPE_VOID;
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









}
