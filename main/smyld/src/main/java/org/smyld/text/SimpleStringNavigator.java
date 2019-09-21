package org.smyld.text;

import org.smyld.SMYLDObject;

public class SimpleStringNavigator extends SMYLDObject implements StringReader {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int curPointer;
	String textValue;

	public SimpleStringNavigator(String textValue) {
		setText(textValue);
	}

	public void setText(String newValue) {
		this.textValue = newValue;
		curPointer = 0;

	}

	public char getChar() {
		char targetChar = ' ';
		if ((curPointer + 1) <= textValue.length()) {
			targetChar = textValue.charAt(curPointer);
			curPointer++;
		}

		return targetChar;
	}

	public String getText(int charNo, int code) {

		return getText(charNo);
	}

	public String getText(int charNo) {
		String targetValue = null;
		if ((curPointer + charNo) <= textValue.length()) {
			targetValue = textValue.substring(curPointer, curPointer + charNo);
			curPointer += charNo;
		} else if (curPointer < textValue.length()) {
			targetValue = textValue.substring(curPointer);
			curPointer = textValue.length();
		}

		return targetValue;
	}

	public void jump(int stepsNo) {
		if (stepsNo >= 0)
			curPointer += stepsNo;
	}

	public String getText() {
		return textValue;
	}
}
