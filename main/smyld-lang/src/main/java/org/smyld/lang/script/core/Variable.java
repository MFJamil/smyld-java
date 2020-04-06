package org.smyld.lang.script.core;

import java.util.HashMap;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.smyld.SMYLDObject;

@Slf4j
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class Variable extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3213958873985871452L;
	@NonNull protected String name;
	@NonNull Langs srcLang;
	protected String type;
	protected String library;
	protected String scope;
	protected String defaultValue;
	protected HashMap<String, String> modifiers = new HashMap<String, String>();
	protected StringBuffer body = new StringBuffer();

	public Variable(String Name) {
		this.name = Name;
	}

	public Variable(Langs srcLang) {
		this.srcLang = srcLang;
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
		this.defaultValue = defaultValue;
	}


	public void addModifier(String newModifier) {
		modifiers.put(newModifier, newModifier);
	}

	public Variable setName(String newName){
		this.name = newName;
		return this;
	}
	public Variable setScope(String scope){
		this.scope = scope;
		return this;
	}
	public Variable setType(String type){
		this.type = type;
		return this;
	}
	public Variable setLibrary(String library){
		this.library = library;
		return this;
	}
	public Variable setDefaultValue(String value){
		this.defaultValue = value;
		return this;
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
		defaultValue = srcVariable.defaultValue;
		type = srcVariable.type;
	}

	public String print(String indent) {
		return indent + print();
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

}
