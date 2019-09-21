package com.smyld.util;

import java.io.Serializable;
import java.util.HashMap;

import com.smyld.SMYLDObject;

public class Translator extends SMYLDObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,String> data = new HashMap<String,String>();
	String language;

	public Translator(String Language) {
		language = Language;
	}

	public String translateWord(String module, String id) {
		String text = (String) data.get(module.toLowerCase() + id);
		return text;
	}

	public void addWord(String module, String id, String text) {
		data.put(module.toLowerCase() + id, text);
	}

	public String getLanguage() {
		return language;
	}

	public HashMap<String,String> getData() {
		return data;
	}

	public static final String LANG_ARABIC = "Arabic";
	public static final String LANG_ENGLISH = "English";
	public static final String LANG_GERMAN = "German";

}
