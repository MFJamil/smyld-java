package com.smyld.text;

import com.smyld.SMYLDObject;

public class WordFilter extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String specialCharacters;
	String filterCharacters;
	StringBuffer buffer = new StringBuffer();
	WordFilterProcessor addProcessor;

	public WordFilter(boolean supportDefault) {
		doInit(Default_SPECIAL_CHAR_SET, Default_FILTER_CHAR_SET, null,
				supportDefault);
	}

	public WordFilter(String SpecialCharacterSet, String FilterCharacterSet,
			boolean supportDefault) {
		doInit(SpecialCharacterSet, FilterCharacterSet, null, supportDefault);
	}

	public WordFilter(String SpecialCharacterSet, String FilterCharacterSet,
			WordFilterProcessor newProcessor) {
		doInit(SpecialCharacterSet, FilterCharacterSet, newProcessor, false);
	}

	private void doInit(String specialSet, String filterSet,
			WordFilterProcessor wordProcessor, boolean supportDefault) {
		specialCharacters = specialSet;
		filterCharacters = filterSet;
		if (wordProcessor != null) {
			addProcessor = wordProcessor;
		} else if (supportDefault) {
			addProcessor = new WordFilterProcessor() {
				public boolean filterChar(char wordChar) {
					return doDefaultFiltering(wordChar);
				}
			};
		}

	}

	public boolean doDefaultFiltering(char filterChar) {
		return ((filterChar >= 126) || (filterChar < 32) || (filterChar == 96) || ((filterChar > 90) && (filterChar < 95)));
	}

	public String doFilter(String word) {
		buffer.setLength(0);
		for (int i = 0; i < word.length(); i++) {
			char currentChar = word.charAt(i);
			int charIndex = specialCharacters.indexOf(currentChar);
			if (charIndex != -1) {
				currentChar = filterCharacters.charAt(charIndex);
			} else if (addProcessor != null) {
				if (addProcessor.filterChar(currentChar))
					currentChar = ' ';
			} else if (!Character.isLetterOrDigit(currentChar)) {
				currentChar = ' ';
			}
			buffer.append(currentChar);
		}
		return buffer.toString();
	}

	public boolean containsInvalidCharacters(String word) {
		for (int i = 0; i < word.length(); i++) {
			char currentChar = word.charAt(i);
			int charIndex = specialCharacters.indexOf(currentChar);
			if (charIndex != -1) {
				currentChar = filterCharacters.charAt(charIndex);
			} else if (addProcessor != null) {
				if (addProcessor.filterChar(currentChar))
					return true;
			} else if (!Character.isLetterOrDigit(currentChar)) {
				return true;
			}
		}
		return false;
	}

	public static final String Default_SPECIAL_CHAR_SET = "����������������������������ܢ�����Ѫ�";
	public static final String Default_FILTER_CHAR_SET = "CueaaaaceeeiiiAAEaAooouuuyyOUcaiounNao";

}
