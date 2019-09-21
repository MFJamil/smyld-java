package com.smyld.util.multilang;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.JDOMException;

import com.smyld.SMYLDObject;
import com.smyld.db.DBConnection;
import com.smyld.util.Translator;
import com.smyld.xml.XMLUtil;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class MultiLangSource extends SMYLDObject implements MultiLangConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean xmlSource = false;
	DBConnection dbConnection;
	Element rootElement;

	/**
	 * 
	 * @see
	 * @since
	 */
	public MultiLangSource(String xmlSourceFile) throws IOException,
			JDOMException {
		this(new File(xmlSourceFile));
	}

	public MultiLangSource(File xmlSourceFile) throws IOException,
			JDOMException {
		xmlSource = true;
		rootElement = XMLUtil.getRootNode(xmlSourceFile);

	}

	public MultiLangSource(InputStream xmlSourceStream) throws IOException,
			JDOMException {
		xmlSource = true;
		rootElement = XMLUtil.getRootNode(xmlSourceStream);

	}

	public MultiLangSource(URL xmlURL) throws IOException, JDOMException {
		xmlSource = true;
		rootElement = XMLUtil.getRootNode(xmlURL);

	}

	public MultiLangSource(DBConnection dbSourceConnection) throws Exception {
		dbConnection = dbSourceConnection;
		// throw new Exception("Not implemented yet....");
	}

	public Translator loadLanguage(String language)
			throws LangNotFoundException {
		if (xmlSource)
			return loadXMLLanguage(language);
		else
			return loadDBLanguage(language);
		// throw new LangNotFoundException(language);
	}

	private Translator loadDBLanguage(String language)
			throws LangNotFoundException {
		return null;

	}

	@SuppressWarnings("unchecked")
	private Translator loadXMLLanguage(String language)
			throws LangNotFoundException {
		List langs = rootElement.getChildren(XML_NODE_LANG);
		Iterator itr = langs.iterator();
		while (itr.hasNext()) {
			Element curElement = (Element) itr.next();
			if (language
					.equals(curElement.getAttributeValue(XML_NODE_ATT_NAME))) {
				return buildXML(curElement);
			}
		}
		throw new LangNotFoundException(language);
	}

	@SuppressWarnings("unchecked")
	private HashMap<String,Translator> loadXMLLanguages() {
		HashMap<String,Translator> result = null;
		List langs = rootElement.getChildren(XML_NODE_LANG);
		Iterator itr = langs.iterator();
		if (langs.size() > 0)
			result = new HashMap();
		while (itr.hasNext()) {
			Element curElement = (Element) itr.next();
			Translator newTranslator = buildXML(curElement);
			if (newTranslator != null) {
				result.put(newTranslator.getLanguage(), newTranslator);
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private Translator buildXML(Element langaugeElement) {
		Translator newTranslator = null;
		String language = langaugeElement.getAttributeValue(XML_NODE_ATT_NAME);
		List words = langaugeElement.getChildren(XML_NODE_WORD);
		if (words.size() > 0)
			newTranslator = new Translator(language);
		Iterator itr = words.iterator();
		while (itr.hasNext()) {
			Element currentWord = (Element) itr.next();
			String wordID = currentWord.getAttributeValue(XML_NODE_ATT_ID);
			String wordMOD = currentWord.getAttributeValue(XML_NODE_ATT_MOD);
			String wordText = currentWord
					.getAttributeValue(XML_NODE_ATT_TRANSLATE);
			newTranslator.addWord(wordMOD, wordID, wordText);
		}
		return newTranslator;
	}

	public HashMap<String,Translator> loadLanguages() {
		if (xmlSource) {
			return loadXMLLanguages();
		}
		return null;
	}

}
