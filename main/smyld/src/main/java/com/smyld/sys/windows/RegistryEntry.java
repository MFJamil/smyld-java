package com.smyld.sys.windows;

import java.util.Vector;

public class RegistryEntry {

	String Name;
	Vector<String> contents;
	boolean addition;

	public RegistryEntry() {
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public Vector<String> getContents() {
		return contents;
	}

	public void setContents(Vector<String> contents) {
		this.contents = contents;
	}

	public boolean isAddition() {
		return addition;
	}

	public void setAddition(boolean addition) {
		this.addition = addition;
	}

	public String printValue() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		if (!isAddition())
			buffer.append("-");
		buffer.append(getName());
		buffer.append("]");
		return buffer.toString();
	}

}
