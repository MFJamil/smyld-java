package org.smyld.app.pe.model.user;

import org.smyld.resources.LookAndFeelResource;
import org.smyld.util.multilang.LangSource;

import java.util.HashMap;

public interface UserSettings {

	public String                               getLanguage();
	public String                               getLookAndFeel();
	public HashMap<String, LookAndFeelResource> getLookAndFeelList();
	public HashMap<String, LangSource>          getLangList();
}
