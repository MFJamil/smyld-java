package org.smyld.lang.script.java;

import org.smyld.lang.script.converter.VBtoJavaConverter;
import org.smyld.lang.script.core.Langs;
import org.smyld.lang.script.core.Variable;

public class JavaVariable extends Variable implements JavaConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2537531745427683934L;

	public JavaVariable(String Name) {
		super(Name, Langs.Java);
	}

	public JavaVariable(String Name, String Scope) {
		super(Name, Scope);
	}

	public JavaVariable(String Name, String Scope, String Type) {
		super(Name, Scope, Type);
	}

	/**
	 * @param Name
	 * @param Scope
	 */
	public JavaVariable(String Name, String Scope, String Type,
			String defaultValue) {
		super(Name, Scope, Type, defaultValue);
	}

	@Override
	public String print() {
		StringBuffer varBody = new StringBuffer(super.print());
		if (getDefaultValue() != null) {
			varBody.append(" = " + getDefaultValue());
		}
		varBody.append(";");
		return varBody.toString();
	}

	@Override
	public void importVariable(Variable srcVariable) {
		super.importVariable(srcVariable);
		if (srcVariable.getSrcLang() == Langs.VisualBasic6) {
			modifiers = VBtoJavaConverter.convertModifiers(srcVariable
					.getModifiers());
			type = VBtoJavaConverter.convertType(srcVariable.getType());
			if (type.equals(JAVA_PRIMITIVE_BOOLEAN)) {
				if (defaultValue != null) {
					defaultValue = defaultValue.toLowerCase();
				}
			}
		}
	}

}
