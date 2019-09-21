package org.smyld.text;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;

import org.smyld.SMYLDObject;
import org.smyld.io.StreamReader;
import org.smyld.math.HexaDecimal;

/**
 * This class will decode the text (i.e. convert the given text from any code it
 * have into ASCII code).
 */
public class TextDecoder extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * EBCDIC code
	 */
	public static final int CODE_EBCDIC = 1;
	/**
	 * BCD code
	 */
	public static final int CODE_BCD = 2;
	/**
	 * ASCII Code
	 */
	public static final int CODE_ASCII = 3;
	/**
	 * Binary code
	 */
	public static final int CODE_BINARY = 4;
	/**
	 * ASCII NIBBLE code
	 */
	public static final int CODE_ASCII_NIBBLE = 5;
	/**
	 * EBCDIC code
	 */
	private static final String CODE_EBCDIC_TEXT = "Cp500";
	/**
	 * BCD code
	 */
	@SuppressWarnings("unused")
	private static final String CODE_BCD_TEXT = "Cp500";
	/**
	 * ASCII Code
	 */
	private static final String CODE_ASCII_TEXT = "8859_1";
	// private static final String CODE_ASCII_TEXT = "ASCII";
	/**
	 * Binary code
	 */
	private static final String CODE_BINARY_TEXT = "Binary";
	/**
	 * ASCII NIBBLE code
	 */
	@SuppressWarnings("unused")
	private static final String CODE_ASCII_NIBBLE_TEXT = "ASCII";

	/**
	 * Will convert the given encoded text passed as string into ASCII code,
	 * according to the gievn code.
	 * 
	 * @param encodedText
	 *            the encoded text
	 * @param TextCode
	 *            code of text passed
	 * @return The converted text or null if the code is not supported, end of
	 *         file reached, the passed text is null or any other error while
	 *         processing.
	 */
	public String decode(String EncodedText, int TextCode) {
		// returning null in case of error
		if ((EncodedText == null) || (!supportsCode(TextCode)))
			return null;
		// ASCII is the default format
		if ((TextCode == CODE_ASCII) || (TextCode == CODE_ASCII_NIBBLE))
			return EncodedText;
		String decodedText = null;
		if (TextCode == CODE_BINARY) {
			decodedText = readBinaryCode(EncodedText);
		} else {
			decodedText = readNormalCodec(EncodedText, getCodecText(TextCode));
		}
		return decodedText;
	}

	public String decode(byte[] encodedBytes, int TextCode) {
		// returning null in case of error
		if ((encodedBytes == null) || (!supportsCode(TextCode)))
			return null;
		// ASCII is the default format
		if ((TextCode == CODE_ASCII) || (TextCode == CODE_ASCII_NIBBLE))
			return new String(encodedBytes);
		String decodedText = null;
		if (TextCode == CODE_BINARY) {
			decodedText = HexaDecimal.parse(encodedBytes);
		} else {
			decodedText = readNormalCodec(encodedBytes, getCodecText(TextCode));
		}
		return decodedText;
	}

	/**
	 * Will convert the given encoded text passed as string into ASCII code,
	 * according to the gievn code.
	 * 
	 * @param encodedText
	 *            the encoded text
	 * @param TextCode
	 *            code of text passed
	 * @return The converted text or null if the code is not supported, end of
	 *         file reached, the passed text is null or any other error while
	 *         processing.
	 */
	public String decode(File EncodedTextFile, int TextCode) {
		// returning null in case of error
		if ((EncodedTextFile == null) || (!supportsCode(TextCode)))
			return null;
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(EncodedTextFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		StreamReader reader = new StreamReader(fin);
		String fileData = null;
		try {
			fileData = reader.read();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (fileData != null)
			return decode(fileData, TextCode);
		return null;
	}

	/**
	 * Will convert the given binary encoded text passed as string into ASCII
	 * code.
	 * 
	 * @param encodedText
	 *            the encoded text
	 * @return The converted text or null if the code is not supported, end of
	 *         file reached, the passed text is null or any other error while
	 *         processing.
	 */

	private String readBinaryCode(String EncodedText) {
		return HexaDecimal.parse(EncodedText);
	}

	/**
	 * Will convert the given encoded text passed as string into ASCII code,
	 * according to the gievn code.
	 * 
	 * @param encodedText
	 *            the encoded text
	 * @param TextCode
	 *            code of text passed
	 * @return The converted text or null if the code is not supported, end of
	 *         file reached, the passed text is null or any other error while
	 *         processing.
	 */

	private String readNormalCodec(String EncodedText, String TextCodec) {
		return readNormalCodec(EncodedText.getBytes(), TextCodec);
	}

	private String readNormalCodec(byte[] encodedByte, String TextCodec) {
		String decodedText = null;
		/*
		 * InputStreamReader inStrReader = null; try{ inStrReader = new
		 * InputStreamReader(new
		 * ByteArrayInputStream(EncodedText.getBytes()),TextCodec);
		 * 
		 * }catch(Exception ex){ ex.printStackTrace(); } StreamReader reader =
		 * new StreamReader(inStrReader); try{ decodedText = reader.read();
		 * }catch(Exception ex){ ex.printStackTrace(); }
		 */

		try {
			decodedText = new String(encodedByte, TextCodec);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decodedText;
	}

	/**
	 * Returns the text identical to the given text code which is passed as
	 * integer
	 * 
	 * @param textCode
	 *            the code passed as integer
	 * @return the identical code text according to the defined code standard
	 */
	public static String getCodecText(int textCode) {
		String resultedCodecText = null;
		switch (textCode) {
		// botch EBCDIC and BCD use the same set
		case CODE_EBCDIC:
		case CODE_BCD:
			resultedCodecText = CODE_EBCDIC_TEXT;
			break;
		// botch ASCII and ASCII_NIBBLE use the same
		case CODE_ASCII:
		case CODE_ASCII_NIBBLE:
			resultedCodecText = CODE_ASCII_TEXT;
			break;
		case CODE_BINARY:
			resultedCodecText = CODE_BINARY_TEXT;
			break;
		}
		return resultedCodecText;
	}

	/**
	 * This methode will check if the given code is supported or not.
	 * 
	 * @param codeSupported
	 *            the code to test
	 * @return the test result
	 */
	public boolean supportsCode(int codeSupported) {
		if ((codeSupported >= CODE_EBCDIC)
				|| (codeSupported <= CODE_ASCII_NIBBLE))
			return true;
		return false;
	}

	@SuppressWarnings("unused")
	private static final int DATA_BLOCK = 256;
}
