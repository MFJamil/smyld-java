package com.smyld.util.alias;

import java.util.HashMap;

import com.smyld.SMYLDObject;

public class AliasEngineSettings extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,AliasSource> dbSources;
	HashMap<String,AliasSource> xmlSources;
	HashMap<String,AliasClassSettings> classes;
	AliasSettings aliasSettings;

	public AliasEngineSettings() {
	}

	public HashMap<String,AliasSource> getDbSources() {
		return dbSources;
	}

	public void setDbSources(HashMap<String,AliasSource> dbSources) {
		this.dbSources = dbSources;
	}

	public HashMap<String,AliasSource> getXmlSources() {
		return xmlSources;
	}

	public void setXmlSources(HashMap<String,AliasSource> xmlSources) {
		this.xmlSources = xmlSources;
	}

	public AliasSettings getAliasSettings() {
		return aliasSettings;
	}

	public void setAliasSettings(AliasSettings aliasSettings) {
		this.aliasSettings = aliasSettings;
	}

	public HashMap<String,AliasClassSettings> getClasses() {
		return classes;
	}

	public void setClasses(HashMap<String,AliasClassSettings> classes) {
		this.classes = classes;
	}
}
