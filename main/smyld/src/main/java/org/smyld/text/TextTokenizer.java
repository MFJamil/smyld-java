package org.smyld.text;

import java.util.StringTokenizer;

/**
 * This class will tokenize the text, according to the provided delimiter, it
 * extends String Tokenizer class and add the functionality of returning the
 * tokens as array of strings
 */

public class TextTokenizer extends StringTokenizer {
	public TextTokenizer(String textList, String seperator) {
		super(textList, seperator);

	}

	/**
	 * This methode will parse the supplied text and separator and return the
	 * results
	 */
	public String[] parseTokens() {
		String[] resultTokens = new String[countTokens()];
		int tokenCounter = 0;
		while (hasMoreElements()) {
			resultTokens[tokenCounter] = nextToken();
			tokenCounter++;
		}
		return resultTokens;
	}

}
