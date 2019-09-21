package com.smyld.math;

/**
 * This class is wrapper for the hexadecimal values
 */
public class HexaHandler {
	/**
	 * Holds the real value of the class
	 */
	private String value;

	private static StringBuffer buffHexFrmAsc;
	private static StringBuffer buffBinValue;
	private static StringBuffer buffParseValue;
	private static StringBuffer buffConvToBinValue;

	/**
	 * Constructs the class with the value provided as text
	 * 
	 * @param hexData
	 *            the object starting value provided as text
	 * @exception NumberFormatException
	 *                if the provided text do not represents hexadecimal text
	 */
	public HexaHandler(String hexData) throws NumberFormatException {
		if (!isHex(hexData))
			throw new NumberFormatException(
					"Invalid Hexadeciaml characters in parsing (" + hexData
							+ ") value.");
		value = hexData.toLowerCase();
	}

	public HexaHandler() throws NumberFormatException {
	}
	/**
	 * Transform the hexa value to its binary equivalent and return it as string
	 * 
	 * @return value representing the binary equivalent of the hexa value
	 */
	public String toBinary() {
		return convertToBinary(value);
	}

	public byte[] toByte() {
		byte[] byteResult = new byte[value.length() / 2];
		String currentHexValue = null;
		int idx = 0, curValue = 0;

		for (int i = 0; i < value.length(); i += 2) {
			currentHexValue = value.substring(i, i + 2);
			curValue = Integer.parseInt(currentHexValue, 16);
			byteResult[idx] = (byte) Math.abs(curValue);
			idx++;
		}
		return byteResult;
	}

	public String convertToBinary(String HexaTextValue) {
		if (HexaTextValue == null)
			return null;
		buffConvToBinValue = doInitBuffer(buffConvToBinValue);
		@SuppressWarnings("unused")
		String currentBinValue = null;
		HexaTextValue = HexaTextValue.toLowerCase();
		for (int i = 0; i < HexaTextValue.length(); i++) {
			char currentDigit = HexaTextValue.charAt(i);
			buffConvToBinValue.append(getBinaryValueText(currentDigit));
		}
		return buffConvToBinValue.toString();
	}

	public String getBinaryValueText(char hexaChar) {
		String resultantBinValue = null;
		switch (hexaChar) {
		case '0':
			resultantBinValue = hex_bin_0;
			break;
		case '1':
			resultantBinValue = hex_bin_1;
			break;
		case '2':
			resultantBinValue = hex_bin_2;
			break;
		case '3':
			resultantBinValue = hex_bin_3;
			break;
		case '4':
			resultantBinValue = hex_bin_4;
			break;
		case '5':
			resultantBinValue = hex_bin_5;
			break;
		case '6':
			resultantBinValue = hex_bin_6;
			break;
		case '7':
			resultantBinValue = hex_bin_7;
			break;
		case '8':
			resultantBinValue = hex_bin_8;
			break;
		case '9':
			resultantBinValue = hex_bin_9;
			break;
		case 'a':
			resultantBinValue = hex_bin_a;
			break;
		case 'b':
			resultantBinValue = hex_bin_b;
			break;
		case 'c':
			resultantBinValue = hex_bin_c;
			break;
		case 'd':
			resultantBinValue = hex_bin_d;
			break;
		case 'e':
			resultantBinValue = hex_bin_e;
			break;
		case 'f':
			resultantBinValue = hex_bin_f;
			break;
		}
		return resultantBinValue;
	}

	/**
	 * This method will return the hexadecimal value as string for the given
	 * integer
	 * 
	 * @param integerValue
	 *            the integer value to convert, it must be in the range of byte
	 *            (i.e. 0..255)
	 * @return the resultant converted hexavalue as string, represented in two
	 *         pairs of charachters, or null if the passed integer out of range
	 */
	public String toHexString(int integerValue) {
		if ((integerValue > 256) || (integerValue < 0))
			return null;
		return hexLookup[integerValue];
	}

	/**
	 * This method will return the hexadecimal value as string for the given
	 * byte
	 * 
	 * @param byteValue
	 *            the byte to convert
	 * @return the resultant converted hexavalue as string, represented in two
	 *         pairs of charachters.
	 */
	public String toHexString(byte byteValue) {
		return toHexString(0xff & byteValue);
	}
	
	public String toHexString(byte[] byteValues) {
		StringBuilder buff = new StringBuilder();
		for(byte curByte:byteValues)
			buff.append(toHexString(curByte));
		return buff.toString();
	}


	/*
	 * public static String toByteString(byte byteValue){ return
	 * toHexString(0xff & byteValue); }
	 */

	public String getHexaFromAscii(String acsiiText) {
		buffHexFrmAsc = doInitBuffer(buffHexFrmAsc);
		byte[] data = acsiiText.getBytes();
		for (int i = 0; i < data.length; i++) {
			buffHexFrmAsc.append(toHexString(data[i]));
		}
		return buffHexFrmAsc.toString();
	}

	/**
	 * This method will parse given string which represents array of bytes and
	 * returns the equivalent hexa values as String.
	 * 
	 * @param textValue
	 *            the binary value represented as string
	 * @return the resultant converted hexavalue as string, representing each
	 *         byte in two pairs of charachters, or null if any error occured
	 */
	public String parse(String textValue) {
		return parse(textValue.getBytes());
	}

	public String parse(byte[] byteValue) {
		buffParseValue = doInitBuffer(buffParseValue);
		for (int i = 0; i < byteValue.length; i++)
			buffParseValue.append(toHexString(byteValue[i]));
		return buffParseValue.toString();
	}

	private StringBuffer doInitBuffer(StringBuffer buffer) {
		if (buffer == null)
			buffer = new StringBuffer();
		else
			buffer.setLength(0);
		return buffer;
	}

	/**
	 * Test to see if the provided information is hexadeciaml or not
	 * 
	 * @param testData
	 *            the data to test
	 * @return true if the text right and false if it contains invalid
	 *         characters
	 */
	private boolean isHex(String testData) {
		for (int i = 0; i < testData.length(); i++)
			if (HexValues.indexOf(testData.charAt(i)) == -1)
				return false;
		return true;
	}

	public String parseBinaryValueText(String binaryValue) {
		buffBinValue = doInitBuffer(buffBinValue);
		for (int i = 0; i < binaryValue.length(); i += 4)
			buffBinValue.append(getHexFromBinary(binaryValue
					.substring(i, i + 4)));
		return buffBinValue.toString();
	}

	public String getHexFromBinary(String binaryValue) {
		if (binaryValue.equals(hex_bin_0))
			return "0";
		if (binaryValue.equals(hex_bin_1))
			return "1";
		if (binaryValue.equals(hex_bin_2))
			return "2";
		if (binaryValue.equals(hex_bin_3))
			return "3";
		if (binaryValue.equals(hex_bin_4))
			return "4";
		if (binaryValue.equals(hex_bin_5))
			return "5";
		if (binaryValue.equals(hex_bin_6))
			return "6";
		if (binaryValue.equals(hex_bin_7))
			return "7";
		if (binaryValue.equals(hex_bin_8))
			return "8";
		if (binaryValue.equals(hex_bin_9))
			return "9";
		if (binaryValue.equals(hex_bin_a))
			return "a";
		if (binaryValue.equals(hex_bin_b))
			return "b";
		if (binaryValue.equals(hex_bin_c))
			return "c";
		if (binaryValue.equals(hex_bin_d))
			return "d";
		if (binaryValue.equals(hex_bin_e))
			return "e";
		if (binaryValue.equals(hex_bin_f))
			return "f";
		return null;
	}

	/**
	 * Holds all the possible values for hexadecimal numbers
	 */
	private static final String HexValues = "0123456789ABCDEFabcdef";
	/**
	 * Holds the binary value for hexadecimal number 0
	 */
	private static final String hex_bin_0 = "0000";
	/**
	 * Holds the binary value for hexadecimal number 1
	 */
	private static final String hex_bin_1 = "0001";
	/**
	 * Holds the binary value for hexadecimal number 2
	 */
	private static final String hex_bin_2 = "0010";
	/**
	 * Holds the binary value for hexadecimal number 3
	 */
	private static final String hex_bin_3 = "0011";
	/**
	 * Holds the binary value for hexadecimal number 4
	 */
	private static final String hex_bin_4 = "0100";
	/**
	 * Holds the binary value for hexadecimal number 5
	 */
	private static final String hex_bin_5 = "0101";
	/**
	 * Holds the binary value for hexadecimal number 6
	 */
	private static final String hex_bin_6 = "0110";
	/**
	 * Holds the binary value for hexadecimal number 7
	 */
	private static final String hex_bin_7 = "0111";
	/**
	 * Holds the binary value for hexadecimal number 8
	 */
	private static final String hex_bin_8 = "1000";
	/**
	 * Holds the binary value for hexadecimal number 9
	 */
	private static final String hex_bin_9 = "1001";
	/**
	 * Holds the binary value for hexadecimal number a
	 */
	private static final String hex_bin_a = "1010";
	/**
	 * Holds the binary value for hexadecimal number b
	 */
	private static final String hex_bin_b = "1011";
	/**
	 * Holds the binary value for hexadecimal number c
	 */
	private static final String hex_bin_c = "1100";
	/**
	 * Holds the binary value for hexadecimal number d
	 */
	private static final String hex_bin_d = "1101";
	/**
	 * Holds the binary value for hexadecimal number e
	 */
	private static final String hex_bin_e = "1110";
	/**
	 * Holds the binary value for hexadecimal number f
	 */
	private static final String hex_bin_f = "1111";
	/**
	 * holds the hexa values for lookup purpose
	 */
	private static final String[] hexLookup = { "00", "01", "02", "03", "04",
			"05", "06", "07", "08", "09", "0a", "0b", "0c", "0d", "0e", "0f",
			"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1a",
			"1b", "1c", "1d", "1e", "1f", "20", "21", "22", "23", "24", "25",
			"26", "27", "28", "29", "2a", "2b", "2c", "2d", "2e", "2f", "30",
			"31", "32", "33", "34", "35", "36", "37", "38", "39", "3a", "3b",
			"3c", "3d", "3e", "3f", "40", "41", "42", "43", "44", "45", "46",
			"47", "48", "49", "4a", "4b", "4c", "4d", "4e", "4f", "50", "51",
			"52", "53", "54", "55", "56", "57", "58", "59", "5a", "5b", "5c",
			"5d", "5e", "5f", "60", "61", "62", "63", "64", "65", "66", "67",
			"68", "69", "6a", "6b", "6c", "6d", "6e", "6f", "70", "71", "72",
			"73", "74", "75", "76", "77", "78", "79", "7a", "7b", "7c", "7d",
			"7e", "7f", "80", "81", "82", "83", "84", "85", "86", "87", "88",
			"89", "8a", "8b", "8c", "8d", "8e", "8f", "90", "91", "92", "93",
			"94", "95", "96", "97", "98", "99", "9a", "9b", "9c", "9d", "9e",
			"9f", "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9",
			"aa", "ab", "ac", "ad", "ae", "af", "b0", "b1", "b2", "b3", "b4",
			"b5", "b6", "b7", "b8", "b9", "ba", "bb", "bc", "bd", "be", "bf",
			"c0", "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "ca",
			"cb", "cc", "cd", "ce", "cf", "d0", "d1", "d2", "d3", "d4", "d5",
			"d6", "d7", "d8", "d9", "da", "db", "dc", "dd", "de", "df", "e0",
			"e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "ea", "eb",
			"ec", "ed", "ee", "ef", "f0", "f1", "f2", "f3", "f4", "f5", "f6",
			"f7", "f8", "f9", "fa", "fb", "fc", "fd", "fe", "ff" };
}
