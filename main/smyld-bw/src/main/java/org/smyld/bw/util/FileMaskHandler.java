package org.smyld.bw.util;

import org.smyld.SMYLDObject;
import org.smyld.text.TextUtil;
import org.smyld.util.SMYLDDate;

public class FileMaskHandler extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StringBuffer buffer = new StringBuffer();

	public FileMaskHandler() {
	}

	public String convertToMask(String pattern) {
		if (isNew(pattern))
			return convertToNewMask(pattern);
		return convertToOldMask(pattern);
	}

	public String convertToNewMask(String pattern) {
		buffer.setLength(0);
		boolean insidePattern = false;
		for (int i = 0; i < pattern.length(); i++) {
			char currentChar = pattern.charAt(i);
			if (currentChar == '<')
				insidePattern = true;
			else if (currentChar == '>')
				insidePattern = false;
			else if (insidePattern)
				buffer.append('|');
			else
				buffer.append(currentChar);
		}
		return buffer.toString();
	}

	public String convertToOldMask(String pattern) {
		buffer.setLength(0);
		//boolean insidePattern = false;
		for (int i = 0; i < pattern.length(); i++) {
			char currentChar = pattern.charAt(i);
			if (PAT_RESERVED.indexOf(currentChar) == -1)
				buffer.append(currentChar);
			else
				buffer.append('|');
		}
		return buffer.toString();
	}

	public boolean match(String name, String mask) {
		if (name.length() == mask.length()) {
			for (int i = 0; i < name.length(); i++) {
				char maskChar = mask.charAt(i);
				if (maskChar != '|')
					if (maskChar != name.charAt(i))
						return false;
			}
			return true;
		}
		return false;
	}

	public boolean match(String name, String mask, String maskPattern) {
		if (match(name, mask)) {
			String FileNameMaskValue = getFileNameMaskValue(name, mask);
			return validate(maskPattern, FileNameMaskValue);
		}
		return false;
	}

	public String getFileNameMaskValue(String fileName, String fileMask) {
		buffer.setLength(0);
		for (int i = 0; i < fileMask.length(); i++) {
			if (fileMask.charAt(i) == '|')
				buffer.append(fileName.charAt(i));
		}
		return buffer.toString();
	}

	public String getFileMaskPattern(String Pattern) {
		if (isNew(Pattern))
			return getNewFileMaskPattern(Pattern);
		return getOldFileMaskPattern(Pattern);
	}

	private boolean isNew(String pattern) {
		return ((pattern.indexOf('<') != -1) && (pattern.indexOf('>') != -1));
	}

	public String getNewFileMaskPattern(String Pattern) {
		buffer.setLength(0);
		boolean insidePattern = false;
		for (int i = 0; i < Pattern.length(); i++) {
			char currentChar = Pattern.charAt(i);
			if (currentChar == '<')
				insidePattern = true;
			else if (currentChar == '>')
				insidePattern = false;
			else if (insidePattern)
				buffer.append(currentChar);
		}
		return buffer.toString();
	}

	public String getOldFileMaskPattern(String Pattern) {
		buffer.setLength(0);
		for (int i = 0; i < Pattern.length(); i++) {
			char currentChar = Pattern.charAt(i);
			if (PAT_RESERVED.indexOf(currentChar) != -1)
				buffer.append(currentChar);
		}
		return buffer.toString();
	}

	public boolean validate(String maskPattern, String maskValue) {
		int position = 0;
		boolean result = true;
		buffer.setLength(0);
		/*
		 * Almost all the patterns refere to numbers so we will check for the
		 * numbers first then we could proceed with ranges, only if the pattern
		 * contains U which denotes customer specific reference that could
		 * contain chars
		 */
		String integerChecking = maskValue;
		if (maskPattern.indexOf('U') != -1) {
			// Filtering out the U equivalent charachters
			for (int i = 0; i < maskPattern.length(); i++)
				if (maskPattern.charAt(i) != 'U')
					buffer.append(maskValue.charAt(i));
			integerChecking = buffer.toString();
			buffer.setLength(0);
		}
		if (!TextUtil.isNumeric(integerChecking))
			return false;
		String value = null;
		// Looping through the text for checking the domains of the patterns
		while (position < maskPattern.length()) {
			char currentChar = maskPattern.charAt(position);
			switch (currentChar) {
			case 'Y':
				value = getCharsValue(maskValue, maskPattern, position, 'Y');
				position += value.length();
				if (value.length() == 4)
					result = TextUtil.withinRange(value, 1900, 2050);
				break;
			case 'M':
				value = maskValue.substring(position, position + 2);
				if (maskPattern.charAt(position + 1) == 'M')
					result = TextUtil.withinRange(value, 1, 12);
				else
					result = TextUtil.withinRange(value, 1, 60);
				position += 2;
				break;
			case 'D':
				value = maskValue.substring(position, position + 2);
				if (maskPattern.charAt(position + 1) == 'D') {
					result = TextUtil.withinRange(value, 1, 31);
					position += 2;
				}
				break;
			case 'H':
				value = maskValue.substring(position, position + 2);
				if (maskPattern.charAt(position + 1) == 'H') {
					result = TextUtil.withinRange(value, 0, 23);
					position += 2;
				}
				break;
			case 'S':
				value = maskValue.substring(position, position + 2);
				if (maskPattern.charAt(position + 1) == 'S') {
					result = TextUtil.withinRange(value, 1, 60);
					position += 2;
				}
				break;
			case 'J':
				value = maskValue.substring(position, position + 3);
				if (maskPattern.substring(position, position + 3).equals(
						PAT_DATE_JULIAN)) {
					result = TextUtil.withinRange(value, 1, 366);
					position += 3;
				}
				break;
			default:
				position++;
			}
			if (!result)
				return false;
		}
		return true;
	}

	private String getCharsValue(String value, String pattern,
			int startPosition, char targetChar) {
		buffer.setLength(0);
		while (pattern.charAt(startPosition) == targetChar) {
			buffer.append(value.charAt(startPosition));
			startPosition++;
		}
		return buffer.toString();
	}

	public boolean compare(String textPattern, String textValue) {
		String patternMask = convertToMask(textPattern);
		String maskPattern = getFileMaskPattern(textPattern);
		return match(textValue, patternMask, maskPattern);
	}

	public boolean validate(String fileMask) throws MaskFormatException {
		for (int i = 0; i < fileMask.length(); i++) {
			char currentChar = fileMask.charAt(i);
			switch (currentChar) {
			case FIRST_CHAR_JULIAN_DATE:
				if (fileMask.indexOf(PAT_DATE_JULIAN) == -1)
					throw new MaskFormatException("Julian Date mask error");
				break;
			case FIRST_CHAR_HOUR:
				if (fileMask.indexOf(PAT_DATE_HOUR) == -1)
					throw new MaskFormatException("Hours mask error");
				break;
			case FIRST_CHAR_SECOND:
				if (fileMask.indexOf(PAT_DATE_SECOND) == -1)
					throw new MaskFormatException("Seconds mask error");
				break;
			case FIRST_CHAR_MONTH_MINUTE:
				if (fileMask.indexOf(PAT_DATE_MONTH) == -1)
					if (fileMask.indexOf(PAT_DATE_MINUTE) == -1)
						throw new MaskFormatException("M exists  mask error");
				break;

			}
		}
		return true;
	}

	public String changeFileNameUsingMask(String sourceFileName, String destMask)
			throws MaskFormatException {
		int fileNamePointPos = sourceFileName.indexOf(".");
		int maskPointPos = destMask.indexOf(".");
		SMYLDDate currentDate = new SMYLDDate();
		if (fileNamePointPos != -1) {
			if (maskPointPos != -1) {

				String fileName = sourceFileName.substring(0, fileNamePointPos);
				String fileExt = sourceFileName.substring(fileNamePointPos + 1);
				String maskName = destMask.substring(0, maskPointPos);
				String maskExt = destMask.substring(maskPointPos + 1);
				boolean isNewFormat = isNew(maskName) || isNew(maskExt);
				StringBuffer newName = new StringBuffer();
				if (isNewFormat)
					newName.append(changeUsingMaskNewFormat(fileName, maskName,
							currentDate));
				else
					newName.append(changeUsingMaskOldFormat(fileName, maskName,
							currentDate));
				newName.append(".");
				if (isNewFormat)
					newName.append(changeUsingMaskNewFormat(fileExt, maskExt,
							currentDate));
				else
					newName.append(changeUsingMaskOldFormat(fileExt, maskExt,
							currentDate));
				return newName.toString();
			} else {
				throw new MaskFormatException(
						"Mask does not contain extension separator");
			}
		}
		throw new MaskFormatException("File name does not contain extension");
	}

	private String changeUsingMaskNewFormat(String sourceName, String destMask,
			SMYLDDate currentDate) throws MaskFormatException {
		boolean insideFormat = false;
		if (currentDate == null)
			currentDate = new SMYLDDate();
		buffer.setLength(0); // Resetting mask value
		for (int i = 0; i < destMask.length(); i++) {
			char currentChar = destMask.charAt(i);
			switch (currentChar) {
			case CHAR_FORMAT_BEGIN:
				insideFormat = true;
				break;
			case CHAR_FORMAT_END:
				insideFormat = false;
				break;
			default:
				if (insideFormat)
					i += convertMask(buffer, sourceName, destMask, i,
							currentChar, currentDate);
				else
					buffer.append(currentChar);

			}
		}
		return buffer.toString();
	}

	private String changeUsingMaskOldFormat(String sourceName, String destMask,
			SMYLDDate currentDate) throws MaskFormatException {
		if (currentDate == null)
			currentDate = new SMYLDDate();
		buffer.setLength(0); // Resetting mask value
		for (int i = 0; i < destMask.length(); i++) {
			char currentChar = destMask.charAt(i);
			int advancePos = convertMask(buffer, sourceName, destMask, i,
					currentChar, currentDate);
			if (advancePos == -1)
				buffer.append(currentChar);
			else
				i += advancePos;
		}
		return buffer.toString();
	}

	private int convertMask(StringBuffer resultBuffer, String sourceName,
			String destMask, int pos, char currentChar, SMYLDDate currentDate)
			throws MaskFormatException {
		int advancingCharacter = -1;
		switch (currentChar) {
		case CHAR_COPY_ORIGIN:
			resultBuffer.append(sourceName);
			advancingCharacter = 0;
			break;
		case FIRST_CHAR_JULIAN_DATE:
			String julianDateMask = destMask.substring(pos, pos + 3);
			if (julianDateMask != null)
				if (julianDateMask.equals(PAT_DATE_JULIAN)) {
					resultBuffer.append(currentDate.toString(SMYLDDate.FRM_DDD));
					advancingCharacter = 2;
					break;
				}
			throw new MaskFormatException("Wrong Julian Date");
		case FIRST_CHAR_HOUR:
			String hourMask = destMask.substring(pos, pos + 2);
			if (hourMask != null)
				if (hourMask.equals(PAT_DATE_HOUR)) {
					resultBuffer.append(currentDate.toString(SMYLDDate.FRM_HH));
					advancingCharacter = 1;
					break;
				}
			throw new MaskFormatException("Wrong hours mask");
		case FIRST_CHAR_SECOND:
			String secondsMask = destMask.substring(pos, pos + 2);
			if (secondsMask != null)
				if (secondsMask.equals(PAT_DATE_SECOND)) {
					resultBuffer.append(currentDate.toString(SMYLDDate.FRM_ss));
					advancingCharacter = 1;
					break;
				}
			throw new MaskFormatException("Wrong seconds mask");

		case FIRST_CHAR_MONTH_MINUTE:
			String monthMinuteMask = destMask.substring(pos, pos + 2);
			if (monthMinuteMask != null) {
				if (monthMinuteMask.equals(PAT_DATE_MINUTE)) {
					resultBuffer.append(currentDate.toString(SMYLDDate.FRM_mm));
					advancingCharacter = 1;
					break;
				} else if (monthMinuteMask.equals(PAT_DATE_MONTH)) {
					resultBuffer.append(currentDate.toString(SMYLDDate.FRM_MM));
					advancingCharacter = 1;
					break;
				}
			}
			throw new MaskFormatException("Wrong monthes/minutes mask");
		}
		return advancingCharacter;

	}

	/*
	 * public String changeUsingMask(String sourceName,String destMask) throws
	 * MaskFormatException{ boolean insideFormat = false; SMYLDDate currentDate =
	 * new SMYLDDate(); buffer.setLength(0); // Resetting mask value for (int i =
	 * 0; i < destMask.length(); i++){ char currentChar = destMask.charAt(i);
	 * switch(currentChar){ case CHAR_FORMAT_BEGIN: insideFormat = true; break;
	 * case CHAR_FORMAT_END: insideFormat = false; break; default: if
	 * (insideFormat){ switch(currentChar){ case CHAR_COPY_ORIGIN:
	 * buffer.append(sourceName); break; case FIRST_CHAR_JULIAN_DATE: String
	 * julianDateMask = destMask.substring(i,i+3); if (julianDateMask!=null) if
	 * (julianDateMask.equals(PAT_DATE_JULIAN)){
	 * buffer.append(currentDate.toString(SMYLDDate.FRM_DDD)); i+=2; break; }
	 * throw new MaskFormatException("Wrong Julian Date"); case FIRST_CHAR_HOUR:
	 * String hourMask = destMask.substring(i,i+2); if (hourMask!=null) if
	 * (hourMask.equals(PAT_DATE_HOUR)){
	 * buffer.append(currentDate.toString(SMYLDDate.FRM_HH)); i++; break; } throw
	 * new MaskFormatException("Wrong hours mask"); case FIRST_CHAR_SECOND:
	 * String secondsMask = destMask.substring(i,i+2); if (secondsMask!=null) if
	 * (secondsMask.equals(PAT_DATE_SECOND)){
	 * buffer.append(currentDate.toString(SMYLDDate.FRM_ss)); i++; break; } throw
	 * new MaskFormatException("Wrong seconds mask");
	 * 
	 * case FIRST_CHAR_MONTH_MINUTE: String monthMinuteMask =
	 * destMask.substring(i,i+2); if (monthMinuteMask!=null){ if
	 * (monthMinuteMask.equals(PAT_DATE_MINUTE)){
	 * buffer.append(currentDate.toString(SMYLDDate.FRM_mm)); i++; break; }else if
	 * (monthMinuteMask.equals(PAT_DATE_MONTH)){
	 * buffer.append(currentDate.toString(SMYLDDate.FRM_MM)); i++; break; } }
	 * throw new MaskFormatException("Wrong monthes/minutes mask"); default:
	 * buffer.append(currentChar); } } } } return buffer.toString(); }
	 */

	// Constant declaration
	public static final char FIRST_CHAR_JULIAN_DATE = 'J';
	public static final char FIRST_CHAR_YEAR = 'Y';
	public static final char FIRST_CHAR_MONTH_MINUTE = 'M';
	public static final char FIRST_CHAR_HOUR = 'H';
	public static final char FIRST_CHAR_SECOND = 'S';
	public static final char CHAR_COPY_ORIGIN = '*';
	public static final char CHAR_FORMAT_BEGIN = '<';
	public static final char CHAR_FORMAT_END = '>';

	// Date patterns
	public static final String PAT_DATE_YEAR = "Y";
	public static final String PAT_DATE_MONTH = "MM";
	public static final String PAT_DATE_DAY = "DD";
	public static final String PAT_DATE_HOUR = "HH";
	public static final String PAT_DATE_MINUTE = "MI";
	public static final String PAT_DATE_SECOND = "SS";
	public static final String PAT_DATE_JULIAN = "JJJ";
	// Counter patterns
	public static final String PAT_DAILY_SEQ_0 = "$";
	public static final String PAT_DAILY_SEQ_1 = "#";
	public static final String PAT_DAILY_SEQ_1_ALL = "^";
	public static final String PAT_FILE_SEQ = "%";
	public static final String PAT_FILE_SEQ_YEARLY = "+";
	// Other patterns
	public static final String PAT_CLIENT_SPECIFIC = "U";
	public static final String PAT_SEQ_NUM = "nnn";
	public static final String PAT_INST_NUM = "!!!!!!!!";
	public static final String PAT_RESERVED = "YMDHISJ$#^%+Un!";

}
