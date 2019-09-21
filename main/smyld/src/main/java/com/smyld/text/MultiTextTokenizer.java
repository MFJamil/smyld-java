package com.smyld.text;

import java.util.Arrays;
import java.util.Vector;

import com.smyld.SMYLDObject;

public class MultiTextTokenizer extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String text;
	String[] separators;
	int[] separatorsFound;
	boolean withSeparators;

	public MultiTextTokenizer(String[] Separators, boolean WithSeparators) {
		separators = Separators;
		withSeparators = WithSeparators;
	}

	public MultiTextTokenizer(String Text, String[] Separators,
			boolean WithSeparators) {
		this(Separators, WithSeparators);
		setText(Text);
	}

	public void setText(String Text) {
		text = Text.trim();
		separatorsFound = new int[text.length()];
	}

	public String[] parseTokens() {
		Vector<String> tokenz = new Vector<String>();
		int[] resultantIndex = null;
		int separatorsNo = fillIndexes();
		if (separatorsNo > 0) {
			resultantIndex = new int[separatorsNo];
			System.arraycopy(separatorsFound, 0, resultantIndex, 0,
					separatorsNo);
			Arrays.sort(resultantIndex);
			int lastIndex = 0;
			int elementsJump = 1;
			if (withSeparators)
				elementsJump = 0;
			for (int i = 0; i < resultantIndex.length; i += (1 + elementsJump)) {
				tokenz.add(text.substring(lastIndex, resultantIndex[i]));
				lastIndex = resultantIndex[i + elementsJump];
			}
			tokenz.add(text.substring(
					resultantIndex[resultantIndex.length - 1], text.length()));

		}
		String[] result = new String[tokenz.size()];
		tokenz.copyInto(result);
		return result;
	}

	protected int fillIndexes() {
		int fromIndex = 0;
		int foundIndex = 0;
		for (int i = 0; i < separators.length; i++) {
			while ((fromIndex = text.indexOf(separators[i], fromIndex)) != -1) {
				separatorsFound[foundIndex] = fromIndex;
				separatorsFound[foundIndex + 1] = fromIndex
						+ separators[i].length();
				foundIndex += 2;
				fromIndex++;
			}
		}
		return foundIndex;
	}
}
