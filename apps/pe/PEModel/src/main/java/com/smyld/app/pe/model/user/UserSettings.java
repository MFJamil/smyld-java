package com.smyld.app.pe.model.user;

import com.smyld.resources.LookAndFeelResource;
import com.smyld.util.multilang.LangSource;

import java.util.HashMap;

public interface UserSettings {

	public String                               getLanguage();
	public String                               getLookAndFeel();
	public HashMap<String, LookAndFeelResource> getLookAndFeelList();
	public HashMap<String, LangSource>          getLangList();
}
