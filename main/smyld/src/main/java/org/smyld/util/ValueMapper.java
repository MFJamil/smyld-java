package org.smyld.util;

import java.util.HashMap;

import org.smyld.SMYLDObject;

public class ValueMapper extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,Object> keyTable = new HashMap<String,Object>();

	public ValueMapper() {
	}

	public void addKey(String parentMapperID, String keyID, Object keyValue) {
		if (parentMapperID == null) {
			keyTable.put(keyID, keyValue);
		} else {
			// Need to continue from here

			// keyTable.get(parentMapperID)
		}
	}

	public synchronized boolean containsKey(String keyValue) {
		return keyTable.containsKey(keyValue);
	}

	public ValueMapper addMappingKey(String parentID, String keyID) {
		ValueMapper newMapper = null;
		if (parentID == null) {
			newMapper = new ValueMapper();
			keyTable.put(keyID, newMapper);
		}
		return newMapper;
	}

	public synchronized Object getKeyValue(String keyID) {
		return keyTable.get(keyID);
	}

	public synchronized String getKeyString(String keyID) {
		return (String) getKeyValue(keyID);
	}

	public synchronized Object getKeyValue(String keyID1, String keyID2) {
		Object targetValue = keyTable.get(keyID1);
		if ((targetValue != null) && (targetValue instanceof ValueMapper))
			return ((ValueMapper) targetValue).getKeyValue(keyID2);
		return targetValue;
	}

	public synchronized Object getKeyValue(String keyID1, String keyID2,
			String keyID3) {
		Object targetValue = keyTable.get(keyID1);
		if ((targetValue != null) && (targetValue instanceof ValueMapper))
			return ((ValueMapper) targetValue).getKeyValue(keyID2, keyID3);
		return targetValue;

	}

}
