package com.smyld.util.multilang;

import com.smyld.SMYLDException;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class LangNotFoundException extends SMYLDException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String language;

	/**
	 * 
	 * @see
	 * @since
	 */
	public LangNotFoundException(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}
}
