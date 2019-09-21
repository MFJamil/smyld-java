package org.smyld.util.alias;

import org.smyld.SMYLDObject;

public class ClassAliasSettings extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	String name;
	String classPackage;
	String path;

	public ClassAliasSettings() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassPackage() {
		return classPackage;
	}

	public void setClassPackage(String classPackage) {
		this.classPackage = classPackage;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
