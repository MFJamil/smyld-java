package org.smyld.text.html;

import org.smyld.SMYLDObject;

public class TextHTMLUtil extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TextHTMLUtil() {
	}

	// Very short but descriptive way to filter only qutations for now
	public static String convertToJavaLitral(String htmlText) {
		return htmlText.replaceAll("\"", "\\\"");
	}
}
