package com.smyld.text;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class will navigate through the given text, this text is supposed to be
 * encoded in more than one codec format, the supported formats can be tested,
 * and the text read can be converted according to the supplied code.
 */
public class EncodedStringNavigator extends StringNavigator implements
		EncodedStringReader {
	/**
	 * Will hold all the processing needed to decode the read string
	 * 
	 * @association <com.smyld.text.JavaAssociation1> com.smyld.text.TextDecoder
	 */
	protected TextDecoder textDecoder = new TextDecoder();

	/**
	 * Constructs the class with the given text
	 * 
	 * @param Text
	 *            the text for navigation
	 */
	public EncodedStringNavigator(String Text) {
		super(Text);
	}

	/**
	 * Constructs the class with the given
	 * 
	 * @param textFile
	 *            the file that contains the text for navigation
	 * @exception IOException
	 *                if the file does not exist or any error occured while
	 *                reading it
	 */
	public EncodedStringNavigator(File textFile) throws IOException {
		super(textFile);
	}

	/**
	 * Constructs the class with the given
	 * 
	 * @param textFile
	 *            the file that contains the text for navigation
	 * @exception IOException
	 *                if the file does not exist or any error occured while
	 *                reading it
	 */
	public EncodedStringNavigator(File textFile, int DataBlockSizeInKelloByte)
			throws IOException {
		super(textFile, DataBlockSizeInKelloByte);
	}

	/**
	 * Constructs the class with the given
	 * 
	 * @param textFile
	 *            the file that contains the text for navigation
	 * @exception IOException
	 *                if the file does not exist or any error occured while
	 *                reading it
	 */
	public EncodedStringNavigator(InputStream dataStream,
			int DataBlockSizeInKelloByte) throws IOException {
		super(dataStream, DataBlockSizeInKelloByte);
	}

	/**
	 * This methode will check if the given code is supported or not.
	 * 
	 * @param codeSupported
	 *            the code to test
	 * @return the test result
	 */
	public boolean supportsCode(int codeSupported) {
		return textDecoder.supportsCode(codeSupported);
	}

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
	@Override
	public String getText(int charNo, int code) {
		if (supportsCode(code))
			return textDecoder.decode(super.getText(charNo), code);
		return null;
	}
}
