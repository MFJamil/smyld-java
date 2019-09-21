package com.smyld.util.keyboard;

import java.io.ByteArrayOutputStream;

import com.smyld.SMYLDObject;

public class BasicKeyboardConverter extends SMYLDObject implements
		KeyboardConverter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	byte[][] convertKeySet;
	String targetCode;
	String sourceKeyboardName;
	String targetKeyboardName;

	public BasicKeyboardConverter() {
	}

	public String convertKeys(String incomingText) throws Exception {
		return new String(convertKeysToBytes(incomingText), targetCode);
	}

	public byte[] convertKeysToBytes(String incomingText) throws Exception {
		// System.out.println(incomingText);
		System.out.println(new String(incomingText.getBytes("windows-1256")));
		// detecting the length of the incoming text in bytes
		byte[] letters = incomingText.getBytes();
		int incKeyLength = letters.length;
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		for (int i = 0; i < incKeyLength; i++)
			bout.write(detectKey(letters[i]));
		return bout.toByteArray();
	}

	private byte[] detectKey(byte incKey) {
		byte[] result = new byte[1];
		result[0] = incKey;
		if (incKey != 32) {
			for (int i = 0; i < convertKeySet.length; i++) {
				if (convertKeySet[i][0] == incKey) {
					result = new byte[convertKeySet[i].length - 1];
					for (int j = 1; j < convertKeySet[i].length; j++) {
						result[j - 1] = convertKeySet[i][j];
					}
					return result;
				}
			}
		}
		return result;
	}

	@SuppressWarnings("unused")
	private int getLength(byte incKey) {
		int length = 1;
		for (int i = 0; i < convertKeySet.length; i++)
			if (convertKeySet[i][0] == incKey)
				return convertKeySet[i].length - 1;
		return length;
	}

	void init() {
	}

	public String getTargetCharSetName() {
		return targetCode;
	}

	public String getSourceKeyboardName() {
		return sourceKeyboardName;
	}

	public String getTargetKeyboardName() {
		return targetKeyboardName;
	}
}
