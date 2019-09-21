package com.smyld.text;

/**
 * This interface will work like the filter for the methods provided by String
 * Navigator class, to let other objects to access the class for read purpose
 * only and navigate through it without the ability to change any thing in the
 * contents.
 */
public interface StringReader {
	/**
	 * Reads the text and fetchs the characters according to the given
	 * charachters no also the pointer is advanced accordingly
	 * 
	 * @param charNo
	 *            Number of characters to be read
	 * @return The resulted string and null if any error occured
	 */
	public String getText(int charNo);

	public String getText(int charNo, int code);

	/**
	 * The pointer is advanced with the given position number
	 * 
	 * @param stepsNo
	 *            Number of positions to jumb
	 */
	public void jump(int setpsNo);

	/**
	 * Returns the text
	 */
	String getText();
}
