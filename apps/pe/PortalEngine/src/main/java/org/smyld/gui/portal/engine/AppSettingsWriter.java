package org.smyld.gui.portal.engine;

import java.io.File;
import java.io.FileOutputStream;
import java.text.Format;
import java.util.HashMap;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import org.smyld.SMYLDObject;
import org.smyld.app.ActiveApplication;
import org.smyld.app.AppConstants;
import org.smyld.resources.FileInfo;
import org.smyld.resources.LookAndFeelResource;
import org.smyld.util.multilang.LangSource;
import org.smyld.xml.XMLUtil;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class AppSettingsWriter extends SMYLDObject implements AppConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4450668298987723406L;
	File settingsFile;
	Element groupElement;
	Element rootElement;
	Element langElement;
	Element lafElement;
	Element logElement;
	HashMap<String, String> addedLangs;
	HashMap<String, LookAndFeelResource> addedLafs;

	/**
	 * 
	 * @see
	 * @since
	 */

	public AppSettingsWriter(String settingsFileName) {
		this(new File (settingsFileName));
	}

	public AppSettingsWriter(File settingsFileName) {
		settingsFile = settingsFileName;
		rootElement = new Element(SETT_XML_NODE_ROOT);
	}

	public void addLanguage(String langName, String langFile) {
		if (addedLangs == null) {
			addedLangs = new HashMap<String, String>();
			langElement = new Element(SETT_XML_NODE_LANGS);
			langElement.setAttribute(SETT_XML_ATT_DEFAULT, langName);
		}
		if (!addedLangs.containsKey(langName)) {
			addedLangs.put(langName, langFile);
			Element newLang = new Element(SETT_XML_NODE_LANG);
			XMLUtil.setAttribute(newLang, SETT_XML_ATT_NAME, langName);
			XMLUtil.setAttribute(newLang, SETT_XML_ATT_SOURCE, langFile);
			langElement.getChildren().add(newLang);
			
		}
	}

	public void addLookAndFeel(LookAndFeelResource lafRes) {
		if (addedLafs == null) {
			addedLafs = new HashMap<String, LookAndFeelResource>();
			lafElement = new Element(SETT_XML_NODE_LAFS);
		}
		if (!addedLafs.containsKey(lafRes.getName())) {
			
			if (lafRes.isSelected()) lafElement.setAttribute(SETT_XML_ATT_DEFAULT, lafRes.getName());
			addedLafs.put(lafRes.getName(), lafRes);
			Element newLaf = new Element(SETT_XML_NODE_LAF);
			XMLUtil.setAttribute(newLaf, SETT_XML_ATT_NAME, lafRes.getName());
			XMLUtil.setAttribute(newLaf, SETT_XML_ATT_CLASS, lafRes
					.getClassName());
			lafElement.getChildren().add(newLaf);
			
		}
	}

	public void addLogFile(FileInfo logFile) {
		logElement = new Element(SETT_XML_NODE_LOG);
		Element logName = new Element(SETT_XML_ATT_NAME);
		Element logPath = new Element(SETT_XML_NODE_PATH);
		logElement.getChildren().add(logName);
		logElement.getChildren().add(logPath);
	}

	public void addGroup(String group) {
		groupElement = new Element(SETT_XML_NODE_GROUP);
		groupElement.setText(group);
	}

	public void importSettings(ActiveApplication app) {
		if (app != null) {
			for (LangSource curLang : app.getLanguages().values()) {
				addLanguage(curLang.getName(), curLang.getSourceFileName());
			}
			langElement.setAttribute(SETT_XML_ATT_DEFAULT, app
					.getDefaultLanguage().getName());
			for (LookAndFeelResource curLaf : app.getLookAndFeels().values()) {
				addLookAndFeel(curLaf);
			}
			lafElement.setAttribute(SETT_XML_ATT_DEFAULT, app
					.getDefaultLookAndFeel().getName());
			addLogFile(app.getFileLog());
		}
	}

	public void createFile() {
		try {
			if (groupElement != null) {
				rootElement.getChildren().add(groupElement);
			}

			if (logElement != null) {
				rootElement.getChildren().add(logElement);
			}
			if (langElement != null) {
				rootElement.getChildren().add(langElement);
			}
			if (lafElement != null) {
				rootElement.getChildren().add(lafElement);
			}

			XMLOutputter domOutput = new XMLOutputter();
			domOutput.setFormat(org.jdom2.output.Format.getPrettyFormat());

			domOutput.output(new Document(rootElement), new FileOutputStream(
					settingsFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
