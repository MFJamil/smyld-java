package com.smyld.util.keyboard;

public interface KeyboardConverter {
	public String convertKeys(String incomingText) throws Exception;

	public byte[] convertKeysToBytes(String incomingText) throws Exception;

	public String getTargetCharSetName();

	public String getSourceKeyboardName();

	public String getTargetKeyboardName();

	public static final int GERMAN_TO_ARABIC = 1;

}
