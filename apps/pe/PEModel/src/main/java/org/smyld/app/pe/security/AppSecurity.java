package org.smyld.app.pe.security;

import org.smyld.SMYLDObject;
import org.smyld.security.SMYLDKey;

import java.util.HashMap;

public class AppSecurity extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,SMYLDKey> keys;

	public AppSecurity() {
	}

	public void addKey(SMYLDKey newKey) {
		if (keys == null) {
			keys = new HashMap<String,SMYLDKey>();
		}
		keys.put(newKey.getName(), newKey);
	}

	public boolean containsKey(String keyName) {
		return keys.containsKey(keyName);
	}

	public SMYLDKey getKey(String keyName) {
		return (SMYLDKey) keys.get(keyName);
	}

	public HashMap<String,SMYLDKey> getKeys() {
		return keys;
	}
}
