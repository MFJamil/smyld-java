package com.smyld.app;

import java.util.HashMap;
import java.util.SortedSet;

import com.smyld.resources.FileInfo;
import com.smyld.resources.LookAndFeelResource;
import com.smyld.util.multilang.LangSource;

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
