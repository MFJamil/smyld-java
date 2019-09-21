package com.smyld;

import java.io.Serializable;

/**
 * This class represents the base class for all the new classes in SMYLD projects,
 * the default inherited base class in Java is java.lang.Object, but for SMYLD
 * work it will be com.smyld.SMYLDObject. This will give us more flexiable solutions
 * in our work
 */
public class SMYLDObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * overrids the object class toString() method in order to give suffiecent
	 * information about the class
	 * 
	 * @return useful information about the message
	 */
	@Override
	public String toString() {
		classInstancesValue = new StringBuffer("SMYLD Created Class"
				+ OS_NEW_LINE);
		insertInstanceValue("==================");
		insertInstanceValue("==================");
		insertInstanceValue("Class Name  :" + this.getClass().getName());
		insertInstanceValue("Object Info");
		insertInstanceValue("===========");
		printInstanceValues();
		return classInstancesValue.toString();
	}

	/**
	 * Print the current instance values in order to use it with toString()
	 * method
	 * 
	 * @return The instance vlaues
	 */
	public void printInstanceValues() {
		insertInstanceValue("This is SMYLDObject class");
	}

	/**
	 * Print the current instance values in order to use it with toString()
	 * method
	 * 
	 * @return The instance vlaues
	 */
	public void insertInstanceValue(String newInsatnce) {
		classInstancesValue.append(newInsatnce + "." + OS_NEW_LINE);
	}

	protected static void debug(String st) {
		System.out.println(st);
	}

	public String getLenName(String value) {
		if (value != null)
			return "(" + value.length() + ") - \"" + value + "\"";
		return "(0) - \"\"";
	}

	/**
	 * Holds the value for making new line in print text
	 */
	public static final String NEW_LINE = "\n";
	public static final String OS_NEW_LINE = System
			.getProperty("line.separator");
	private StringBuffer classInstancesValue;

}
