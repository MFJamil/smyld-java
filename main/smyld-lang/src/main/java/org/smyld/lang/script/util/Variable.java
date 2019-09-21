package org.smyld.lang.script.util;

import java.util.HashMap;

import org.smyld.SMYLDObject;

public class Variable extends SMYLDObject implements LangsConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3213958873985871452L;
	protected String name;
	protected String type;
	protected String scope;
	protected String DefaultValue;
	protected HashMap<String, String> modifiers = new HashMap<String, String>();
	protected StringBuffer body = new StringBuffer();

	public Variable() {
	}

	public Variable(String Name) {
		name = Name;
	}

	public Variable(int langSrc) {
		this.srcLang = langSrc;
	}

	public Variable(String Name, int langSrc) {
		name = Name;
		this.srcLang = langSrc;
	}

	public Variable(String Name, String Scope) {
		this(Name);
		scope = Scope;
	}

	public Variable(String Name, String Scope, String Type) {
		this(Name, Scope);
		type = Type;
	}

	public Variable(String Name, String Scope, String Type, String defaultValue) {
		this(Name, Scope, Type);
		DefaultValue = defaultValue;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void addModifier(String newModifier) {
		modifiers.put(newModifier, newModifier);
	}

	public void fillInModifiers() {
		if (modifiers.size() > 0) {
			for (String curMod : modifiers.values()) {
				body.append(curMod);
				body.append(" ");
			}
		}
	}

	public void importVariable(Variable srcVariable) {
		name = srcVariable.getName();
		modifiers = srcVariable.modifiers;
		scope = srcVariable.scope;
		DefaultValue = srcVariable.DefaultValue;
		type = srcVariable.type;
	}

	public String print() {
		body.setLength(0);
		body.append(scope);
		body.append(" ");
		fillInModifiers();
		body.append(type);
		body.append(" ");
		body.append(name);
		return body.toString();
	}

	public static final String SCOPE_PUBLIC = "public";
	public static final String SCOPE_PRIVATE = "private";
	int srcLang;

	public String getDefaultValue() {
		return DefaultValue;
	}

	public void setDefaultValue(String newDefaultValue) {
		DefaultValue = newDefaultValue;
	}

	public int getSrcLang() {
		return srcLang;
	}

	public void setSrcLang(int srcLang) {
		this.srcLang = srcLang;
	}

	public HashMap<String, String> getModifiers() {
		return modifiers;
	}

}
