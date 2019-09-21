package org.smyld.transaction.util;

import org.smyld.SMYLDObject;
import org.smyld.text.TextUtil;

public class TransUtility extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String scrambleCardNo(String sourceCardNo, int keepDigits) {
		return scrambleCardNo(sourceCardNo, keepDigits, -1);
	}

	public static String scrambleCardNo(String sourceCardNo, int keepDigits,
			long fakeNumber) {
		if (sourceCardNo.length() < keepDigits)
			return sourceCardNo;

		String resultantCard = sourceCardNo.substring(0, keepDigits);
		// int fakeNumberWidth = sourceCardNo - keepDigits - 1;
		if (fakeNumber != -1) {
			resultantCard += Long.toString(fakeNumber);
		} else {
			int fakeNumberWidth = sourceCardNo.length() - keepDigits - 1;
			StringBuffer buffer = new StringBuffer();
			String newNumber = Long.toString(System.currentTimeMillis());
			if (newNumber.length() > fakeNumberWidth)
				buffer.append(newNumber.substring(newNumber.length()
						- fakeNumberWidth));
			// Random rand = new Random(1);
			// for (int i = 0; i < fakeNumberWidth; i++){
			// int newInt = rand.nextInt(10);
			// buffer.append(newInt);
			// }
			resultantCard += buffer.toString();
		}
		String resultCardNum = generateValidCardNo(resultantCard);
		if (resultCardNum.equals(sourceCardNo))
			resultCardNum = scrambleCardNo(sourceCardNo, keepDigits, fakeNumber);
		return resultCardNum;
	}

	public static String generateValidCardNo(String cardNo) {
		int luhnDigit = generateLUHN(cardNo);
		cardNo += Integer.toString(luhnDigit);
		return cardNo;
	}

	public static String scrambleCardNoWithKey(String sourceCardNo,
			int keepDigits, long keyValue) {
		if (sourceCardNo.length() < keepDigits)
			return sourceCardNo;
		if ((keepDigits <= MIN_KEEP_DIGITS) || (keepDigits > MAX_KEEP_DIGITS))
			keepDigits = DEFAULT_KEEP_DIGITS; // Setting the default value for
												// the digits to keep
		StringBuffer buffer = new StringBuffer();
		String resultantCard = sourceCardNo.substring(0, keepDigits);
		buffer.append(resultantCard);
		if (keyValue != -1) {
			String keyText = Long.toString(keyValue);
			int keyLength = keyText.length();
			int scrNumWidth = sourceCardNo.length() - keepDigits - 1;
			long originalValue = Long.parseLong(sourceCardNo.substring(
					keepDigits, keepDigits + scrNumWidth));
			if (scrNumWidth < keyLength)
				keyValue = Long.parseLong(keyText.substring(keyLength
						- scrNumWidth));
			long resultValue = originalValue ^ keyValue;
			buffer.append(TextUtil.fillLeftSide(resultValue, scrNumWidth, '9'));
		}
		String resultCardNum = generateValidCardNo(buffer.toString());
		return resultCardNum;
	}

	public static int generateLUHN(String cardNo) {
		int result = 0;
		boolean odd = true;
		for (int i = cardNo.length() - 1; i >= 0; i--) {
			int curNo = Integer.parseInt(cardNo.substring(i, i + 1));
			if (odd) {
				curNo = curNo * 2;
				if (curNo > 8)
					curNo -= 9;
			}
			odd = !odd;
			result += curNo;
		}
		if (result % 10 == 0)
			return 0;
		return 10 - result % 10;
	}

	public static boolean validateLuhn(String cardNo) {
		// TODO writing a code for validating the LUHN digit
		if ((cardNo == null) || (!TextUtil.isNumeric(cardNo)))
			return false;
		int luhnDigit = generateLUHN(cardNo.substring(0, cardNo.length() - 1));
		int checkDigit = Integer
				.parseInt(cardNo.substring(cardNo.length() - 1));
		return (luhnDigit == checkDigit);
	}

	public static final int DEFAULT_KEEP_DIGITS = 9;
	public static final int MIN_KEEP_DIGITS = 4;
	public static final int MAX_KEEP_DIGITS = 16;
}
