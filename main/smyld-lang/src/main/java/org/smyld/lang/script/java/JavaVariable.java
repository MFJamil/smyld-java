package org.smyld.lang.script.java;

import org.smyld.lang.script.converter.VBtoJavaConverter;
import org.smyld.lang.script.util.LangsConstants;
import org.smyld.lang.script.util.Variable;

public class JavaVariable extends Variable implements JavaConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2537531745427683934L;

	public JavaVariable(String Name) {
		super(Name, LangsConstants.SRC_LANG_JAVA);
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
		if (srcVariable.getSrcLang() == SRC_LANG_VB6) {
			modifiers = VBtoJavaConverter.convertModifiers(srcVariable
					.getModifiers());
			type = VBtoJavaConverter.convertType(srcVariable.getType());
			if (type.equals(JAVA_PRIMITIVE_BOOLEAN)) {
				if (DefaultValue != null) {
					DefaultValue = DefaultValue.toLowerCase();
				}
			}
		}
	}

}
