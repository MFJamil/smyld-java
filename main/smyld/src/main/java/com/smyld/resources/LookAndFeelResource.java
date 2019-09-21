package com.smyld.resources;

import java.util.HashMap;

import com.smyld.SMYLDObject;

public class LookAndFeelResource extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String className;
	HashMap<String,String> sourceFiles;
	boolean packaged;
	boolean selected;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public LookAndFeelResource() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public HashMap<String,String> getSourceFiles() {
		return sourceFiles;
	}

	public void setSourceFiles(HashMap<String,String> sourceFiles) {
		this.sourceFiles = sourceFiles;
	}

	public boolean isPackaged() {
		return packaged;
	}

	public void setPackaged(boolean packaged) {
		this.packaged = packaged;
	}
}
