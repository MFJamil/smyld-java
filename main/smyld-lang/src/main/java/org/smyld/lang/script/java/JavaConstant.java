package org.smyld.lang.script.java;

public class JavaConstant extends JavaVariable implements JavaConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JavaConstant(String name, String type, String value) {
		super(name, SCOPE_PUBLIC, type, value);
		addModifier(MODIFIER_STATIC);
		addModifier(MODIFIER_FINAL);
	}

	public JavaConstant(String name, String type) {
		super(name, SCOPE_PUBLIC, type);
		addModifier(MODIFIER_STATIC);
		addModifier(MODIFIER_FINAL);
	}

}
