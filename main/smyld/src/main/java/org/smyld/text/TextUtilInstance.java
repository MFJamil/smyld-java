	package org.smyld.text;

	import java.io.File;
	import java.io.FileReader;
	import java.io.LineNumberReader;
	import java.io.Reader;
	import java.io.StringReader;
	import java.util.Vector;

	import org.smyld.SMYLDObject;
	import org.smyld.io.FileSystem;

	public class TextUtilInstance extends SMYLDObject {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private static int lastPosition = 0;

		public TextUtilInstance() {

		}

		public  String toTitleCase(String textToChange) {
			String firstLetter = textToChange.substring(0, 1);
			firstLetter = firstLetter.toUpperCase();
			return firstLetter + textToChange.substring(1);
		}

		public  String createWord(char fillingChar, int width) {
			char[] wordChars = new char[width];
			for (int i = 0; i < width; i++)
				wordChars[i] = fillingChar;
			return new String(wordChars);
		}

		public  String createWord(String fillingText,
				int iterationNo) {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < iterationNo; i++)
				buffer.append(fillingText);
			return buffer.toString();
		}

		public  String resize(String value, int width) {
			if (!isEmpty(value)) {
				if (value.length() > width) {
					if (value.trim().length() > width) {
						value = value.trim().substring(0, width);
					} else {
						value = fillRightSide(value.trim(), width, ' ');
					}
				} else {
					value = fillRightSide(value, width, ' ');
				}
			} else {
				value = createWord(' ', width);
			}
			return value;
		}

		public  String right(String value, int digitsNumber) {
			if (!isEmpty(value))
				if (value.length() > digitsNumber)
					return value.substring(value.length() - digitsNumber);
			return value;
		}

		public  String left(String value, int digitsNumber) {
			if (!isEmpty(value))
				if (value.length() > digitsNumber)
					return value.substring(0, digitsNumber);
			return value;
		}

		public  boolean isEmpty(String value) {
			if (value != null)
				if (value.trim().length() > 0)
					return false;
			return true;
		}

		public  boolean within(String value, String range) {
			if (isEmpty(value))
				return false;
			return (range.indexOf(value) != -1);
		}

		public  String fillLeftSideWithSpaces(String value,
				int width) {
			return fillText(value, width, ' ', true);
		}

		public  String fillRightSideWithSpaces(String value,
				int width) {
			return fillText(value, width, ' ', false);
		}

		public  String fillLeftSide(long value, int width,
				char fillingChar) {
			return fillText(Long.toString(value), width, fillingChar, true);
		}

		public  String fillLeftSide(String value, int width,
				char fillingChar) {
			return fillText(value, width, fillingChar, true);
		}

		public  String fillRightSide(String value, int width,
				char fillingChar) {
			return fillText(value, width, fillingChar, false);
		}

		public  boolean isNumeric(String textValue) {
			try {
				@SuppressWarnings("unused")
				long value = Long.parseLong(textValue);
				return true;
			} catch (Exception ex) {
			}
			return false;
		}

		public  boolean isDecimal(String textValue) {
			if (textValue == null)
				return false;
			for (int i = 0; i < textValue.length(); i++) {
				char curChar = textValue.charAt(i);
				if ((!Character.isDigit(curChar) && (curChar != '-') && (curChar != '.')))
					return false;
			}
			return true;
		}

		public  boolean withinRange(String textValue,
				long lowerRange, long upperRange) {
			long currentValue = -1;
			try {
				currentValue = Long.parseLong(textValue);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if (currentValue != -1)
				return ((currentValue >= lowerRange) && (currentValue <= upperRange));
			return false;
		}

		public  String doTrim(String word) {
			if (word != null)
				return word.trim();
			return "";
		}

		public  String fillText(String value, int width,
				char fillingChar, boolean fillingLeftSide) {
			String resultantField = null;
			if (value == null)
				return fillText(" ", width, fillingChar, fillingLeftSide);
			int diff = width - value.length();
			if (diff < 0) {
				resultantField = value.substring(0, width);
			} else if (diff == 0) {
				resultantField = value;
			} else {
				String fillingSpace = createWord(fillingChar, diff);
				if (fillingLeftSide)
					resultantField = fillingSpace + value;
				else
					resultantField = value + fillingSpace;
			}
			return resultantField;
		}

		public  String stripLeadingZeros(String value) {
			for (int i = 0; i < value.length(); i++)
				if (value.charAt(i) != '0')
					return value.substring(i);
			return "";
		}

		public  String composeWithSeparator(String[] data,
				String separator) {
			StringBuffer buffer = new StringBuffer();
			String result = null;
			if (data != null)
				if (data.length > 0) {
					for (int i = 0; i < data.length; i++) {
						buffer.append(data[i]);
						if (i < data.length - 1)
							buffer.append(separator);
					}
					result = buffer.toString();
				}
			return result;
		}

		/** This function strips the special characters out of the passed word */
		public  String stripSpecialChars(String word) {
			StringBuffer buffer = new StringBuffer(word.length());
			for (int i = 0; i < word.length(); i++) {
				char currentChar = word.charAt(i);
				// if (!Character.isLetter(currentChar))
				int charIndex = SPECIAL_CHAR_SET.indexOf(currentChar);
				if (charIndex != -1)
					currentChar = FILTER_CHAR_SET.charAt(charIndex);
				buffer.append(currentChar);
			}
			return buffer.toString();
		}

		public  String replace(StringBuffer sourceDocument,
				String startBlock, String endBlock, String newSectionData) {
			return replace(new StringReader(sourceDocument.toString()), startBlock,
					endBlock, newSectionData);
		}

		public  String replace(File targetFile,
				String startBlock, String endBlock, String newSectionData) {
			FileReader sourceFileReader = null;
			try {
				sourceFileReader = new FileReader(targetFile);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if (sourceFileReader != null)
				return replace(sourceFileReader, startBlock, endBlock,
						newSectionData);
			return null;
		}

		public  String replace(Reader sourceDataReader,
				String startBlock, String endBlock, String newSectionData) {
			boolean sectionFound = false;
			StringBuffer data = new StringBuffer();
			try {
				boolean doRead = true;
				int endRead = 0, startRead = 0;

				LineNumberReader lr = new LineNumberReader(sourceDataReader);
				String curLine = lr.readLine();
				do {
					startRead = 0;
					if (curLine.indexOf(startBlock) != -1) {
						doRead = false;
						endRead = curLine.indexOf(startBlock) + startBlock.length();
						data.append(curLine.substring(startRead, endRead)
								+ NEW_LINE);
						data.append(newSectionData);
						sectionFound = true;
						if (curLine.indexOf(endBlock) != -1) {
							startRead = curLine.indexOf(endBlock);
							endRead = curLine.length();
							data.append(curLine.substring(startRead, endRead)
									+ NEW_LINE);
							doRead = true;
						}
					} else if (curLine.indexOf(endBlock) != -1) {
						startRead = curLine.indexOf(endBlock);
						doRead = true;
					}
					if (doRead) {
						endRead = curLine.length();
						data.append(curLine.substring(startRead, endRead)
								+ NEW_LINE);
					}
					curLine = lr.readLine();
				} while (curLine != null);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if (sectionFound)
				return data.toString();
			return null;
		}

		public  String fetchSectionData(File searchFile,
				String startBlock, String endBlock) {
			String fileContents = FileSystem.readStringFile(searchFile).toString();
			return getSectionText(fileContents, startBlock, endBlock);
			/*
			 * int startPos = 0; int endPos = fileContents.length(); if
			 * (startBlock!=null) if
			 * (fileContents.toLowerCase().indexOf(startBlock.toLowerCase())!=-1){
			 * startPos =
			 * fileContents.toLowerCase().indexOf(startBlock.toLowerCase()) +
			 * startBlock.length(); }else{return "";}
			 * 
			 * if (endBlock!=null) if
			 * (fileContents.toLowerCase().indexOf(endBlock.toLowerCase())!=-1){
			 * endPos = fileContents.toLowerCase().indexOf(endBlock.toLowerCase());
			 * }else{return "";} return fileContents.substring(startPos,endPos);
			 */
		}

		public  String getSectionText(String targetText,
				String firstText, String lastText, int startPosition) {
			int startPos = startPosition;
			int endPos = targetText.length();
			if (firstText != null) {
				startPos = targetText.toLowerCase().indexOf(
						firstText.toLowerCase(), startPos);
				if (startPos != -1) {
					startPos += firstText.length();
				} else {
					return "";
				}
			}

			if (lastText != null)
				endPos = targetText.toLowerCase().indexOf(lastText.toLowerCase(),
						startPos);
			if (endPos == -1)
				return "";
			if (endPos != targetText.length())
				lastPosition = endPos;
			else
				lastPosition = -1;
			return targetText.substring(startPos, endPos);
		}

		public  String getSectionText(String targetText,
				String firstText, String lastText) {
			return getSectionText(targetText, firstText, lastText, 0);
		}

		public  Vector<String> getAllSectionText(String targetText,
				String firstText, String lastText) {
			Vector<String> result = new Vector<String>();
			String lastResult = "   ";
			while ((lastPosition != -1) && (!lastResult.equals(""))) {
				lastResult = getSectionText(targetText, firstText, lastText,
						lastPosition + 1);
				if (!lastResult.equals(""))
					result.add(lastResult);
			}
			return result;
		}

		public  boolean doEqual(String value, String compareValue) {
			return ((value != null) && (value.equals(compareValue)));
		}

		public  boolean compare(String orig, String comp) {
			if ((orig != null) && (comp != null))
				return orig.equals(comp);
			if ((orig == null) && (comp == null))
				return true;
			return false;
		}

		public  int occuranceOf(String word, String findString) {
			int occurance = 0;
			int curOccur = 0;
			int lastOcur = 0;
			while ((curOccur = word.indexOf(findString, lastOcur)) != -1) {
				occurance++;
				lastOcur = curOccur + 1;
			}
			return occurance;
		}

		private static final String SPECIAL_CHAR_SET = "����������������������������ܢ�����Ѫ��`'.";
		private static final String FILTER_CHAR_SET = "CueaaaaceeeiiiAAEaAooouuuyyOUcaiounNao    ";

	
}
