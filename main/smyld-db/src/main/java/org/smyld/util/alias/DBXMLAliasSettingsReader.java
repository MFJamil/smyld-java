package org.smyld.util.alias;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;

import org.smyld.db.DBSettings;
import org.smyld.xml.XMLUtil;
import org.jdom2.input.SAXBuilder;

import static org.smyld.util.alias.AliasConstants.*;


public class DBXMLAliasSettingsReader implements XMLAliasConstants {
	AliasEngineSettings engineSettings;
	String curTarget;
	String curSchema;

	Element dbXMLSettings;
	Element application;
	Element buildApplication;

	DBSettings dbSettings;

	public DBXMLAliasSettingsReader(String xmlDocument) throws Exception {
		init(xmlDocument);
	}

	public DBSettings loadDBSettings() {
		return dbSettings;
	}

	// New design methodes
	private void init(String xmlDocument) throws Exception {
		engineSettings = new AliasEngineSettings();
		application = new SAXBuilder().build(new File(xmlDocument))
				.getRootElement();

		// Loading alias
		Element dbAlias = application.getChild(SETT_XML_NODE_DB_ALIAS);
		if (dbAlias != null)
			engineSettings.setAliasSettings(doLoadAlias(dbAlias));

		// Loading the classes
		Element classes = getClasses(application.getChild(SETT_XML_NODE_BUILD));
		if (classes != null) {
			engineSettings.setClasses(doLoadClasses(classes));
		} else {
			throw new Exception("No target classes available ... ");
		}

		// Loading sources
		Element sources = application.getChild(SETT_XML_NODE_SRCS);
		if (sources != null) {
			boolean sourcesAvailable = true;
			Element xmlSources = sources.getChild(SETT_XML_NODE_XML);
			if (xmlSources != null) {
				engineSettings.setXmlSources(doLoadXMLSources(xmlSources));
			} else {
				sourcesAvailable = false;
			}
			Element dbSources = sources.getChild(SETT_XML_NODE_DB);
			if (dbSources != null) {
				engineSettings.setDbSources(doLoadDBSources(dbSources));
			} else {
				if (!sourcesAvailable) {
					throw new Exception("No sources available ... ");
				}
			}

		} else {
			throw new Exception("No sources available ... ");
		}
	}

	private Element getClasses(Element build) {
		if (build != null) {
			Element classes = build.getChild(SETT_XML_NODE_CLASSES);
			if (classes != null)
				return classes;
		}
		return null;

	}

	// Loading alias
	private AliasSettings doLoadAlias(Element al) {
		HashMap<String,String> prefixes = new HashMap<String,String>(4);
		AliasSettings newSett = new AliasSettings();
		// Reading prefixes
		Element prefix = al.getChild(SETT_XML_NODE_AL_PREFIX);
		if (prefix != null) {
			prefixes.put(SETT_XML_NODE_AL_PREFIX_TBL, XMLUtil.getChildValue(
					prefix, SETT_XML_NODE_AL_PREFIX_TBL));
			prefixes.put(SETT_XML_NODE_AL_PREFIX_COL, XMLUtil.getChildValue(
					prefix, SETT_XML_NODE_AL_PREFIX_COL));
			prefixes.put(SETT_XML_NODE_AL_PREFIX_TAG, XMLUtil.getChildValue(
					prefix, SETT_XML_NODE_AL_PREFIX_TAG));
			prefixes.put(SETT_XML_NODE_AL_PREFIX_ATT, XMLUtil.getChildValue(
					prefix, SETT_XML_NODE_AL_PREFIX_ATT));
			newSett.setPrefixes(prefixes);
		}
		// reading the name separator
		newSett.setNameSeparator(XMLUtil.getChildValue(al,
				SETT_XML_NODE_AL_NAME_SEP));
		// Reading shortings
		Element shortings = al.getChild(SETT_XML_NODE_AL_SHORTINGS);
		if (shortings != null)
			newSett.setShortings(loadShortings(shortings));
		return newSett;

	}

	@SuppressWarnings("unchecked")
	private HashMap<String,String> loadShortings(Element shortingsNode) {
		HashMap<String,String> shortings = new HashMap<String,String>();
		Iterator itr = shortingsNode.getChildren().iterator();
		while (itr.hasNext()) {
			Element curSHorting = (Element) itr.next();
			String curWord = curSHorting.getChild(SETT_XML_NODE_AL_SHORT_WORD)
					.getText();
			String curShort = curSHorting.getChild(SETT_XML_NODE_AL_SHORT_SHRT)
					.getText();
			shortings.put(curWord, curShort);
		}
		return shortings;
	}

	// Loading Classes
	@SuppressWarnings("unchecked")
	private HashMap<String,AliasClassSettings> doLoadClasses(Element classes) {
		HashMap<String,AliasClassSettings> newClasses = new HashMap<String,AliasClassSettings>();
		List clsList = classes.getChildren(SETT_XML_NODE_CLASS);
		Iterator clsItr = clsList.iterator();
		while (clsItr.hasNext()) {
			Element curClass = (Element) clsItr.next();
			AliasClassSettings newClass = loadClass(curClass);
			if (newClass != null)
				newClasses.put(newClass.getId(), newClass);
		}
		return newClasses;
	}

	private AliasClassSettings loadClass(Element cls) {
		AliasClassSettings newClass = new AliasClassSettings();
		newClass.setId(cls.getAttributeValue(SETT_XML_TAG_ID));
		newClass.setName(XMLUtil.getChildValue(cls, SETT_XML_NODE_NAME));
		newClass.setClassPackage(XMLUtil.getChildValue(cls,
				SETT_XML_NODE_PACKAGE));
		newClass.setClassPath(XMLUtil.getChildValue(cls, SETT_XML_NODE_PATH));
		return newClass;
	}

	@SuppressWarnings("unchecked")
	private void loadTargets(Element srcEl, AliasSource src) {
		List trgts = srcEl.getChildren(SETT_XML_TAG_TARGET);
		if ((trgts != null) && (trgts.size() > 0)) {
			Iterator itr = trgts.iterator();
			while (itr.hasNext()) {
				String newTarget = ((Element) itr.next()).getText();
				src.addClass(newTarget);
			}
		}
	}

	// Loading XML Sources
	@SuppressWarnings("unchecked")
	private HashMap<String,AliasSource> doLoadXMLSources(Element xmls) {
		HashMap<String,AliasSource> xmlSrcs = new HashMap<String,AliasSource>();
		List srcs = xmls.getChildren(SETT_XML_NODE_SRC);
		Iterator crsItr = srcs.iterator();
		while (crsItr.hasNext()) {
			Element curSrc = (Element) crsItr.next();
			XMLAliasSource newXMLSrc = loadXMLSource(curSrc);
			if (newXMLSrc != null)
				xmlSrcs.put(newXMLSrc.getId(), newXMLSrc);
			loadTargets(curSrc, newXMLSrc);
		}
		return xmlSrcs;
	}

	private XMLAliasSource loadXMLSource(Element xmlSrc) {
		XMLAliasSource newSrc = new XMLAliasSource();
		newSrc.setId(xmlSrc.getAttributeValue(SETT_XML_TAG_ID));
		String schemaType = xmlSrc.getAttributeValue(SETT_XML_TAG_SCHEMA_TYPE);
		newSrc.setSchemaType(XML_SCHEME_TYPE_TAGS);
		if ((schemaType!=null)&&(schemaType.toLowerCase().equals("fields")))
			newSrc.setSchemaType(XML_SCHEME_TYPE_FIELDS);
		newSrc.setTarget(XMLUtil.getChildValue(xmlSrc,SETT_XML_TAG_TARGET));
		newSrc.setName(XMLUtil.getChildValue(xmlSrc, SETT_XML_TAG_SRC_FILE));
		return newSrc;
	}

	// Loading DB Sources
	@SuppressWarnings("unchecked")
	private HashMap<String,AliasSource> doLoadDBSources(Element srcs) {
		HashMap<String,AliasSource> dbSrces = new HashMap<String,AliasSource>();
		List srcsList = srcs.getChildren(SETT_XML_NODE_SRC);
		Iterator srcItr = srcsList.iterator();
		while (srcItr.hasNext()) {
			Element curSrc = (Element) srcItr.next();
			DBAliasSource newDBSrc = loadDBSource(curSrc);
			if (newDBSrc != null)
				dbSrces.put(newDBSrc.getId(), newDBSrc);
			loadTargets(curSrc, newDBSrc);
		}
		return dbSrces;
	}

	private DBAliasSource loadDBSource(Element src) {
		DBAliasSource newDB = new DBAliasSource();
		newDB.setDbConnSettings(loadConnection(src
				.getChild(SETT_XML_NODE_DB_CONN)));
		newDB.setId(src.getAttributeValue(SETT_XML_TAG_ID));
		newDB.setTables(loadTables(src.getChild(SETT_XML_NODE_AL_TABLES)));
		newDB.fillSchemas();
		return newDB;
	}

	private DBSettings loadConnection(Element conn) {
		DBSettings dbSettings = new DBSettings();
		dbSettings.setHost(conn.getChild(SETT_XML_NODE_DB_HOST).getText());
		dbSettings.setVendor(XMLUtil.getChildValue(conn,
				SETT_XML_NODE_DB_VENDOR));
		dbSettings.setName(conn.getChild(SETT_XML_NODE_DB_NAME).getText());
		dbSettings.setPort(conn.getChild(SETT_XML_NODE_DB_PORT).getText());

		dbSettings.setUserName(conn.getChild(SETT_XML_NODE_DB_USR_NAME)
				.getText());
		dbSettings.setUserPassword(conn.getChild(SETT_XML_NODE_DB_USR_PASS)
				.getText());
		return dbSettings;
	}

	@SuppressWarnings("unchecked")
	private HashMap<String,DBAliasTable> loadTables(Element tables) {
		HashMap<String,DBAliasTable> tabls = new HashMap<String,DBAliasTable>();
		List schemasList = tables.getChildren(SETT_XML_NODE_AL_SCHEMA);
		Iterator schemasItr = schemasList.iterator();
		while (schemasItr.hasNext()) {
			Element curSchemaEl = (Element) schemasItr.next();
			curSchema = curSchemaEl.getAttributeValue(SETT_XML_TAG_NAME)
					.toLowerCase();
			if (curSchemaEl.getAttributeValue(SETT_XML_TAG_TARGET) != null)
				curTarget = curSchemaEl.getAttributeValue(SETT_XML_TAG_TARGET);
			tabls = loadTableNames(curSchemaEl, tabls);
		}
		return tabls;
	}

	@SuppressWarnings("unchecked")
	private HashMap<String,DBAliasTable> loadTableNames(Element tablesNode, HashMap<String,DBAliasTable> tabls) {
		Iterator itr = tablesNode.getChildren().iterator();
		while (itr.hasNext()) {
			DBAliasTable newTable = new DBAliasTable();
			Element curTable = (Element) itr.next();
			String curTableName = curTable.getText().toLowerCase();
			if (curTable.getAttributeValue(SETT_XML_TAG_TARGET) != null) {
				newTable.setTarget(curTable
						.getAttributeValue(SETT_XML_TAG_TARGET));
			} else {
				newTable.setTarget(curTarget);
			}
			newTable.setName(curTableName);
			newTable.setSchema(curSchema);
			tabls.put(newTable.createKey(), newTable);
		}
		return tabls;
	}

	public AliasEngineSettings getEngineSettings() {
		return engineSettings;
	}

	public void setEngineSettings(AliasEngineSettings engineSettings) {
		this.engineSettings = engineSettings;
	}

}
