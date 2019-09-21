package org.smyld.gui.portal.engine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.smyld.app.pe.input.xml.PEXmlFileReader;
import org.smyld.app.pe.logging.LogFile;
import org.smyld.app.pe.model.Constants;
import org.jdom2.Element;

import org.smyld.db.DBSettings;
import org.smyld.xml.XMLUtil;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class PotalEngineXMLSetReader extends PEXmlFileReader implements
		Constants, SettingsReader {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @see
	 * @since
	 */
	Element rootElement;
	Element logNode;
	Element compileNode;
	Element buildApplication;
	LogFile logFile;

	public PotalEngineXMLSetReader(String settingsFile) throws Exception {
		super(settingsFile);
		rootElement = rootNode;
		logNode = rootElement.getChild(SET_XML_NODE_LOG);
		compileNode = rootElement.getChild(SET_XML_NODE_COMPILE);
		buildApplication = rootElement.getChild(TAG_NAME_BUILD);
		init();

	}

	private void init() {
		// Initializing the log
		if (logNode!=null){
			logFile = new LogFile();
			logFile.setFileName(getLogFileName());
			logFile.setFilePath(getLogFilePath());
			
			String showJar = logNode.getAttributeValue(TAG_COMP_ATT_SHOW_JAR_DUP);
			if ((showJar != null) && (showJar.equals(TAG_ATT_SHOW_JAR_DUP_YES))) {
				logFile.setShowJarDuplicates(true);
			}
		}

	}

	public LogFile getLogFile() {
		return logFile;
	}

	private String getLogFilePath() {
		return XMLUtil.getChildValue(logNode, TAG_COMP_ATT_PATH);
	}

	private String getLogFileName() {
		return XMLUtil.getChildValue(logNode, TAG_COMP_ATT_NAME);
	}

	@SuppressWarnings("unchecked")
	public Vector<Application> loadApplications() {
		List appList = compileNode.getChildren(SET_XML_NODE_APPLICATION);
		Iterator itr = appList.iterator();
		Vector<Application> apps = new Vector<Application>();
		while (itr.hasNext()) {
			Application newApp = buildApplication((Element) itr.next());
			if (newApp != null) {
				apps.add(newApp);
			}
		}
		if (apps.size() == 0) {
			apps = null;
		}
		return apps;
	}

	private Application buildApplication(Element appNode) {
		Application newApplication = new Application();
		newApplication.setName(appNode.getChild(TAG_COMP_ATT_NAME).getText());
		Element sourceNode = appNode.getChild(SET_XML_NODE_SOURCE);
		newApplication.setSourceFile(sourceNode.getText().trim());
		if (hasChildren(sourceNode)) {
			DBSettings dbSettings = new DBSettings();
			dbSettings
					.setHost(XMLUtil.getChildValue(sourceNode, TAG_NAME_HOST));
			dbSettings.setName(XMLUtil.getChildValue(sourceNode,
					TAG_NAME_DBNAME));
			dbSettings
					.setPort(XMLUtil.getChildValue(sourceNode, TAG_NAME_PORT));
			dbSettings.setUserName(XMLUtil.getChildValue(sourceNode,
					TAG_NAME_USER_NAME));
			dbSettings.setUserPassword(XMLUtil.getChildValue(sourceNode,
					TAG_NAME_USER_PASS));
			newApplication.setDbSource(dbSettings);
		}
		return newApplication;
	}
	private boolean hasChildren(Element curEl){
		return ((curEl.getChildren()!=null)&&(curEl.getChildren().size()>0));
	}


	public HashMap<String,String> loadLibraries() {
		return loadElementsAsText(buildApplication, TAG_NAME_LIBS);
	}

}
