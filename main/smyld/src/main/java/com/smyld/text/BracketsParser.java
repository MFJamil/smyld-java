package com.smyld.text;

import java.util.Vector;

import com.smyld.SMYLDObject;

public class BracketsParser extends SMYLDObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String text;
	char openingBracket;
	char closingBracket;
	int[][] foundBrackets;
	Vector<String> foundBracketText;
	boolean withSeparator;

	public BracketsParser(char OpeningBracket, char ClosingBracket,
			boolean WithSeparator) {
		closingBracket = ClosingBracket;
		openingBracket = OpeningBracket;
		foundBrackets = new int[50][2];
		foundBracketText = new Vector<String>();
		withSeparator = WithSeparator;
	}

	public BracketsParser(String Text, char OpeningBracket,
			char ClosingBracket, boolean WithSeparator) {
		this(OpeningBracket, ClosingBracket, WithSeparator);
		text = Text;
	}

	public void reset() {
		foundBracketText.clear();
	}

	public void setText(String newText) {
		text = newText;
	}

	public Object[] parseBracket() {
		int resultantBrakets = fillIndex(text.toCharArray(), foundBrackets);
		for (int j = 0; j < resultantBrakets; j++) {
			foundBracketText.add(text.substring(foundBrackets[j][0] + 1,
					foundBrackets[j][1]));
			if ((withSeparator) && (j < resultantBrakets - 1))
				foundBracketText.add(text.substring(foundBrackets[j][1] + 1,
						foundBrackets[j + 1][0]).trim());
		}
		if (foundBracketText.size() > 0)
			return foundBracketText.toArray();
		return null;
	}

	public int fillIndex(char[] textChars, int[][] bracketIndex) {
		int foundIndex = 0;
		int internalBrackets = 0;
		boolean startCounting = false;
		for (int i = 0; i < textChars.length; i++) {
			if (textChars[i] == openingBracket) {
				if (!startCounting) {
					bracketIndex[foundIndex][0] = i;
					startCounting = true;
				} else {
					internalBrackets++;
				}
			} else if (textChars[i] == closingBracket) {
				if (startCounting)
					if (internalBrackets > 0) {
						internalBrackets--;
					} else {
						bracketIndex[foundIndex][1] = i;
						startCounting = false;
						foundIndex++;
					}
			}
		}
		return foundIndex;

	}

}
