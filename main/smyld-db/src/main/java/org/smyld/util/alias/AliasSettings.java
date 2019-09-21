package org.smyld.util.alias;

import java.util.HashMap;

import org.smyld.SMYLDObject;

public class AliasSettings extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,String> shortings;
	String nameSeparator;
	HashMap<String,String> prefixes;

	public AliasSettings() {
	}

	public HashMap<String,String> getShortings() {
		return shortings;
	}

	public void setShortings(HashMap<String,String> shortings) {
		this.shortings = shortings;
	}

	public String getNameSeparator() {
		return nameSeparator;
	}

	public void setNameSeparator(String nameSeparator) {
		this.nameSeparator = nameSeparator;
	}

	public HashMap<String,String> getPrefixes() {
		return prefixes;
	}

	public void setPrefixes(HashMap<String,String> prfixes) {
		this.prefixes = prfixes;
	}
}
