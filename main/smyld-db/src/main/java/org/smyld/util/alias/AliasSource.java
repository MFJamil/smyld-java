package org.smyld.util.alias;

import java.util.HashMap;

import org.smyld.SMYLDObject;


public class AliasSource extends SMYLDObject  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,AliasClassSettings> classRef;
	String id;
	int type;
	

	public AliasSource() {
	}

	public AliasSource(int alType) {
		
		type = alType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void addClass(AliasClassSettings classSettings) {
		if (classRef == null)
			classRef = new HashMap<String,AliasClassSettings>();
		classRef.put(classSettings.getId(), classSettings);
	}

	public void addClass(String classID) {
		if (classRef == null)
			classRef = new HashMap<String,AliasClassSettings>();
		classRef.put(classID, null);
	}

	public boolean containsTarget(String targetName) {
		if (classRef != null)
			return classRef.containsKey(targetName);
		return false;
	}

	public boolean containsTargets() {
		return ((classRef != null) && (classRef.size() > 0));
	}

	public int getType() {
		return type;
	}
}
