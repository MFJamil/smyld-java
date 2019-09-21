package com.smyld.util.alias;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import com.smyld.db.DBConnectionFactory;
import com.smyld.lang.script.java.JavaInterface;
import com.smyld.text.TextUtil;

public class AliasBuilder {
	AliasEngineSettings     engineSett;
	DBConnectionFactory     dbConnFactory;
	DBAliasGenerator        dbAlGenerator;
	XMLAliasGenerator       xmlAlGenerator;
	XMLFieldsAliasGenerator xmlFieldsAliasGenerator;

	public AliasBuilder() {
	}

	public void build(String XMLAliasDocument) {
		try {
			dbConnFactory = new DBConnectionFactory();
			DBXMLAliasSettingsReader dbXMLReader = new DBXMLAliasSettingsReader(
					XMLAliasDocument);
			engineSett = dbXMLReader.getEngineSettings();
			dbAlGenerator            = new DBAliasGenerator(engineSett.getClasses());
			xmlAlGenerator           = new XMLAliasGenerator(engineSett.getClasses());
			xmlFieldsAliasGenerator  = new XMLFieldsAliasGenerator(engineSett.getClasses());
			doInitObjects();
			processAliasSource(engineSett.getDbSources(), dbAlGenerator);
			processAliasSource(engineSett.getXmlSources(), xmlAlGenerator);
			processAliasSource(engineSett.getXmlSources(), xmlFieldsAliasGenerator);
			exportClasses();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void processAliasSource(HashMap<String,AliasSource> alSrcs, AliasGenerator generator)
			throws Exception {
		if ((alSrcs != null) && (alSrcs.size() > 0)) {
			for (AliasSource curSource : alSrcs.values()) {
				generator.processSource(curSource, engineSett
						.getAliasSettings());
			}
		}

	}

	private void doInitObjects() throws Exception {
		// Scanning class references in order to avoid creating them for nothing
		// Creating classes for the settings classes
		createClasses();
		// Creating db connections for the DB sources
		if (engineSett.getDbSources() != null)
			createDBConnections();
		// Checking for non existing or sources that could not contain targets
		if (engineSett.getXmlSources() != null)
			checkXMLSources();
		// Updating the class reference inside the sources
	}

	private boolean isClassReferenced(String className) {
		boolean classReferenced = false;
		// Checking for DB Sources
		if (engineSett.getDbSources() != null) {
			for (AliasSource curSource : engineSett.getDbSources().values()) 
				classReferenced = curSource.containsTarget(className);
		}

		if ((!classReferenced) && (engineSett.getXmlSources() != null)) {
			for (AliasSource curSource : engineSett.getXmlSources().values()) 
				classReferenced = curSource.containsTarget(className);
		}
		return classReferenced;
	}

	private void createClasses() {
		Iterator<AliasClassSettings> itr = engineSett.getClasses().values().iterator();
		while (itr.hasNext()) {
			AliasClassSettings curClass =itr.next();
			if (isClassReferenced(curClass.getId())) {
				curClass.ActivateClass();
				addShortingsComment(curClass.getActiveClass());
			} else {
				System.out.println("Class ID (" + curClass.getId()
						+ ") is not referenced in the sources ..!");
				itr.remove();
			}
		}
	}

	private void createDBConnections() {
		Iterator<AliasSource> itr = engineSett.getDbSources().values().iterator();
		while (itr.hasNext()) {
			DBAliasSource dbSrc = (DBAliasSource) itr.next();
			if (dbSrc.containsTargets()) {
				try {
					dbSrc.setRs2DBConnection(dbConnFactory
							.createDBConnection(dbSrc.getDbConnSettings()));
				} catch (Exception e) {
					System.out.println("Database source ID (" + dbSrc.getId()
							+ ") connection can not be established ..!");
					itr.remove();
				}
			} else {
				System.out.println("Database source ID (" + dbSrc.getId()
						+ ") contains no Targets ..!");
				itr.remove();
			}
		}
	}

	private void checkXMLSources() {
		Iterator<AliasSource> itr = engineSett.getXmlSources().values().iterator();
		while (itr.hasNext()) {
			XMLAliasSource xmlSrc = (XMLAliasSource) itr.next();
			if (xmlSrc.containsTargets()) {
				if (!new File(xmlSrc.getName()).exists()) {
					System.out.println("XML source ID (" + xmlSrc.getId()
							+ ") does not exists ..!");
					itr.remove();
				}
			} else {
				System.out.println("XML source ID (" + xmlSrc.getId()
						+ ") contains no Targets ..!");
				itr.remove();
			}
		}
	}

	protected void addShortingsComment(JavaInterface newClass) {
		newClass.addSingleCommentLine(" Shorting word used list : ");
		for (String word : engineSett.getAliasSettings().getShortings().keySet()) {
			String shortCut = engineSett.getAliasSettings().getShortings().get(word);
			shortCut = TextUtil.fillRightSide(shortCut, 4, ' ');
			newClass.addSingleCommentLine(shortCut + "  :  " + word);
		}
	}

	private void exportClasses() throws Exception {
		for (AliasClassSettings curClass : engineSett.getClasses().values()) {
			curClass.exportClass();
		}
	}
}
