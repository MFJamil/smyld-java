package org.smyld.app;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.smyld.app.pe.input.xml.PEXmlFileReader;
import org.jdom2.Element;

import org.smyld.resources.FileInfo;
import org.smyld.resources.LookAndFeelResource;
import org.smyld.util.multilang.LangSource;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class XMLAppSetReader extends PEXmlFileReader implements
		AppSettingsReader, AppConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Element rootElement;
	Element langsElement;
	Element lafsElement;
	Element rolsElement;
	Element groupElement;
	String defaultLang;
	String defaultLangFile;
	DefaultLiveApplication currApplication;

	/**
	 * 
	 * @see
	 * @since
	 */
	public XMLAppSetReader(String settingsFile) throws Exception {
		super(settingsFile);
		// Document doc = new DOMBuilder().build(new File(settingsFile));
		// rootElement = doc.getRootElement();
		langsElement = rootNode.getChild(SETT_XML_NODE_LANGS);
		lafsElement  = rootNode.getChild(SETT_XML_NODE_LAFS);
		rolsElement  = rootNode.getChild(SETT_XML_NODE_ROLES);
		groupElement  = rootNode.getChild(SETT_XML_NODE_GROUP);
		init();
	}
	public XMLAppSetReader(InputStream settingsStream) throws Exception {
		super(settingsStream);
		// Document doc = new DOMBuilder().build(new File(settingsFile));
		// rootElement = doc.getRootElement();
		langsElement = rootNode.getChild(SETT_XML_NODE_LANGS);
		lafsElement  = rootNode.getChild(SETT_XML_NODE_LAFS);
		rolsElement  = rootNode.getChild(SETT_XML_NODE_ROLES);
		init();
	}

	@SuppressWarnings("unchecked")
	private void init() {
		currApplication = new DefaultLiveApplication();
		if (groupElement!=null){
			currApplication.setGroup(groupElement.getText());
		}
		defaultLang = langsElement.getAttributeValue(SETT_XML_ATT_DEFAULT);
		// currApplication.setDefaultLanguage(defaultLang);
		if (defaultLang != null) {
			List langs = langsElement.getChildren(SETT_XML_NODE_LANG);
			Iterator itr = langs.iterator();
			HashMap<String, LangSource> newLangs = new HashMap<String, LangSource>();
			while (itr.hasNext()) {
				Element curLang = (Element) itr.next();
				String langName = curLang.getAttributeValue(SETT_XML_ATT_NAME);
				String langSrc = curLang.getAttributeValue(SETT_XML_ATT_SOURCE);
				LangSource newSource = new LangSource();
				newSource.setName(langName);
				newSource.setSourceFileName(langSrc);
				newLangs.put(langName, newSource);
				if (defaultLang.equals(langName)) {
					currApplication.setDefaultLanguage(newSource);
				}
			}
			currApplication.setLanguages(newLangs);
		}
		if (lafsElement != null) {
			String defaultLaf = lafsElement
					.getAttributeValue(SETT_XML_ATT_DEFAULT);
			HashMap<String, LookAndFeelResource> newLafs = new HashMap<String, LookAndFeelResource>();
			List lafs = lafsElement.getChildren(SETT_XML_NODE_LAF);
			Iterator itr = lafs.iterator();
			while (itr.hasNext()) {
				Element curLaf = (Element) itr.next();
				LookAndFeelResource lafRes = new LookAndFeelResource();
				lafRes.setName(curLaf.getAttributeValue(SETT_XML_ATT_NAME));
				lafRes.setClassName(curLaf
						.getAttributeValue(SETT_XML_ATT_CLASS));
				newLafs.put(lafRes.getName(), lafRes);
				if (defaultLaf.equals(lafRes.getName())) {
					currApplication.setDefaultLookAndFeel(lafRes);
				}
			}
			currApplication.setLookAndFeels(newLafs);
		}
		if (rolsElement != null) {
			TreeSet<String> newRoles = new TreeSet<String>();
			List     roles = rolsElement.getChildren(SETT_XML_NODE_ROLE);
			Iterator itr   = roles.iterator();
			while (itr.hasNext()) {
				Element curRole = (Element) itr.next();
				newRoles.add(curRole.getText());
			}
			currApplication.setUserRoles(newRoles);
		}

		if (rootNode.getChild(SETT_XML_NODE_LOG) != null) {
			FileInfo logFile = new FileInfo();
			addFileInfo(logFile, rootNode.getChild(SETT_XML_NODE_LOG));
			currApplication.setFileLog(logFile);
		}

	}

	public String getDefaultLanguageName() {
		return defaultLang;
	}

	public String getDefaultLanguageFile() {
		return defaultLangFile;
	}

	public ActiveApplication getActiveApplication() {
		return currApplication;
	}

}
