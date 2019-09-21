package com.smyld.util.alias;

import java.io.File;
import java.util.HashMap;

import com.smyld.SMYLDObject;
import com.smyld.lang.script.java.JavaInterface;

public class AliasClassSettings extends SMYLDObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String classPackage;
	String classPath;
	String nameSeparator;
	String classKey;
	String id;
	HashMap<String,AliasSource> srcRef;
	HashMap<String,String> shortings;
	JavaInterface realClass;

	public AliasClassSettings() {
	}

	public String getName() {
		return name;
	}

	public void setName(String Name) {
		this.name = Name;
	}

	public HashMap<String,String> getShortings() {
		return shortings;
	}

	public void setShortings(HashMap<String,String> shortings) {
		this.shortings = shortings;
	}

	public String getClassPackage() {
		return classPackage;
	}

	public void setClassPackage(String classPackage) {
		this.classPackage = classPackage;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getNameSeparator() {
		return nameSeparator;
	}

	public void setNameSeparator(String nameSeparator) {
		this.nameSeparator = nameSeparator;
	}

	public String getClassKey() {
		if (classKey == null)
			classKey = classPath + File.separator + classPackage + "." + name;
		return classKey;
	}

	public void addAliasSource(AliasSource src) {
		srcRef.put(src.getId(), src);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void ActivateClass() {
		realClass = new JavaInterface(getName(), getClassPackage(), null, true);
		realClass.setAllignVariableCount(7);
		realClass.setCreateOnSequence(true);
	}

	public void exportClass() throws Exception {
		realClass.sortFieldsAlphabetically(false);
		realClass.exportFileToPath(getClassPath());
	}

	public JavaInterface getActiveClass() {
		return realClass;
	}

}
