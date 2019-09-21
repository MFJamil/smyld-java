package org.smyld.app;

import java.util.HashMap;
import java.util.SortedSet;

import org.smyld.resources.FileInfo;
import org.smyld.resources.LookAndFeelResource;
import org.smyld.util.multilang.LangSource;

public interface ActiveApplication {
	public LangSource                            getDefaultLanguage();
	public LookAndFeelResource                   getDefaultLookAndFeel();
	public HashMap<String, LookAndFeelResource>  getLookAndFeels();
	public HashMap<String, LangSource>           getLanguages();
	public FileInfo                              getFileLog();
	public FileInfo                              getSettingsFile();
	public void                                  setCurrentLookAndFeel(String lafName);
	public void                                  setCurrentLanguage(String langName);
	public SortedSet<String>                     getUserRoles();
	public String                                getGroup();
	
}
