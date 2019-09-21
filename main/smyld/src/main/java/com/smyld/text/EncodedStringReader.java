package com.smyld.text;

public interface EncodedStringReader extends StringReader {
	/**
	 * Reads the text and fetchs the characters according to the given
	 * charachters no also the pointer is advanced accordingly, the returned
	 * text is decoded according to the given code
	 * 
	 * @param charNo
	 *            Number of characters to be read
	 * @param code
	 *            the code of the text
	 * @return The resulted string and null if the passed code is not supported,
	 *         end of file reached or any error occured
	 */
	String getText(int charNo, int code);

}
