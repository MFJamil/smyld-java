package org.smyld.app;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public interface AppSettingsReader {
	public String getDefaultLanguageName();

	public String getDefaultLanguageFile();

	public ActiveApplication getActiveApplication();
}
