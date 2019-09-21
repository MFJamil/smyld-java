package org.smyld.app;

import java.util.HashMap;
import java.util.TreeSet;

import org.smyld.SMYLDObject;
import org.smyld.resources.FileInfo;
import org.smyld.resources.LookAndFeelResource;
import org.smyld.util.multilang.LangSource;

public class DefaultLiveApplication extends SMYLDObject implements
		ActiveApplication {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LangSource                           defaultLanguage;
	LookAndFeelResource                  defaultLookAndFeel;
	HashMap<String, LookAndFeelResource> lookAndFeels;
	HashMap<String, LangSource>          languages;
	TreeSet<String>                      userRoles;
	FileInfo                             settingsFile;
	FileInfo                             fileLog;
	String                               group;

	public DefaultLiveApplication() {
	}

	public LangSource getDefaultLanguage() {
		return defaultLanguage;
	}

	public LookAndFeelResource getDefaultLookAndFeel() {
		return defaultLookAndFeel;
	}

	public HashMap<String, LookAndFeelResource> getLookAndFeels() {
		return lookAndFeels;
	}


	public FileInfo getFileLog() {
		return fileLog;
	}

	public FileInfo getSettingsFile() {
		return settingsFile;
	}

	public void setDefaultLanguage(LangSource defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public void setDefaultLookAndFeel(LookAndFeelResource defaultLookAndFeel) {
		this.defaultLookAndFeel = defaultLookAndFeel;
	}

	public void setLookAndFeels(
			HashMap<String, LookAndFeelResource> lookAndFeels) {
		this.lookAndFeels = lookAndFeels;
	}

	public HashMap<String, LangSource> getLanguages() {
		return languages;
	}

	public void setLanguages(HashMap<String, LangSource> languages) {
		this.languages = languages;
	}

	public void setSettingsFile(FileInfo settingsFile) {
		this.settingsFile = settingsFile;
	}

	public void setFileLog(FileInfo fileLog) {
		this.fileLog = fileLog;
	}

	public void setCurrentLookAndFeel(String lafName) {
		if (lookAndFeels.containsKey(lafName)) {
			setDefaultLookAndFeel(lookAndFeels.get(lafName));
		}
	}

	public void setCurrentLanguage(String langName) {
		if (languages.containsKey(langName)) {
			setDefaultLanguage(languages.get(langName));
		}

	}

	/**
	 * @return the userRoles
	 */
	public TreeSet<String> getUserRoles() {
		return userRoles;
	}

	@Override
	public String getGroup() {
		return group;
	}

	/**
	 * @param userRoles the userRoles to set
	 */
	public void setUserRoles(TreeSet<String> userRoles) {
		this.userRoles = userRoles;
	}

	public void setGroup(String group) {
		this.group = group;
	}
}
