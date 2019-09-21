package org.smyld.gui.portal.engine.sources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.smyld.app.pe.logging.LogFile;
import org.smyld.app.pe.model.Constants;
import org.smyld.app.pe.model.gui.*;
import org.jdom2.Element;

import org.smyld.resources.FileInfo;
import org.smyld.util.multilang.LangSource;
import org.smyld.xml.XMLFileWriter;
import org.smyld.xml.XMLUtil;

import static org.smyld.app.AppConstants.*;

public class PortalAppXMLSetWriter extends XMLFileWriter implements Constants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PESwingApplicationReader reader;

	public PortalAppXMLSetWriter() {
	}

	@Override
	public void compose() {
		// Code for creating the file
		rootElement = new Element(TAG_NAME_APPLICATION);
		Element appName = new Element(TAG_NAME_NAME);
		appName.setText(reader.getAppName());
		rootElement.addContent(appName);
		rootElement.setAttribute(TAG_ATT_TYPE, reader.getAppType());
		addLog(reader.getLogFile());
		addAppManager(reader.getAppManagerClass());
		addBuildNode();
		addElements(reader.loadPanels(), TAG_NAME_PANELS);
		addElements(reader.loadWindows(), TAG_NAME_WINDOWS);
		addMenus();
		addToolbars();
		addActions();
	}

	private void addActions() {
		Element actionsNode = new Element(TAG_NAME_ACTIONS);
		HashMap<String, PEAction> actions = reader.loadActions();
		for (String actionID : actions.keySet()) {
			PEAction curAction = actions.get(actionID);
			Element actionNode = new Element(TAG_NAME_ACTION);
			XMLUtil.setAttribute(actionNode, TAG_ATT_ID, curAction.getID());
			XMLUtil.setAttribute(actionNode, TAG_COMP_ATT_COM, curAction
					.getCommand());
			XMLUtil.setAttribute(actionNode, TAG_COMP_ATT_TARGET, curAction
					.getTarget());
			XMLUtil.setAttribute(actionNode, TAG_COMP_ATT_PARAM, curAction
					.getParameters());
			XMLUtil.setAttribute(actionNode, TAG_COMP_ATT_LABEL, curAction
					.getLabel());
			if (curAction.getUserConstraint()!=null){
				XMLUtil.setAttribute(actionNode, TAG_COMP_ATT_ROLE, curAction.getUserConstraint().getAllRoles());
				XMLUtil.setAttribute(actionNode, TAG_COMP_ATT_SHOW_ROLE, curAction.getUserConstraint().getAllShowRoles());

			}
			actionsNode.addContent(actionNode);
		}
		rootElement.addContent(actionsNode);

	}

	private void addToolbars() {
		Element toolbarsNode = new Element(TAG_NAME_TLBS);
		HashMap<String, GUIToolbar> tlbs = reader.loadToolbars();
		if ((tlbs != null) && (tlbs.size() > 0)) {
			for (String tlbID : tlbs.keySet()) {
				GUIToolbar curtlb = tlbs.get(tlbID);
				Element tlbNode = new Element(TAG_NAME_TLB);
				tlbNode.setAttribute(TAG_ATT_ID, tlbID);
				buildToolbar(tlbNode, curtlb);
				toolbarsNode.addContent(tlbNode);
			}
			rootElement.addContent(toolbarsNode);
		}
	}

	private void buildToolbar(Element parentElement, GUIToolbar toolbar) {
		for (GUIComponent curGUIComp : toolbar.getChildren()) {
			ActionHolderItem curItem = (ActionHolderItem) curGUIComp;
			Element newItem = new Element(TAG_NAME_MENU);
			XMLUtil.setAttribute(newItem, TAG_ATT_ID, curItem.getID());
			if (curItem.getAction() != null) {
				XMLUtil.setAttribute(newItem, TAG_COMP_ATT_ACTION, curItem
						.getAction().getID());
			}
			XMLUtil.setAttribute(newItem, TAG_COMP_ATT_ICON, curItem.getIcon());
			parentElement.addContent(newItem);
		}

	}

	private void addMenus() {
		Element menusNode = new Element(TAG_NAME_MENUS);
		HashMap<String,MenuItem> menus = reader.loadMenus();
		for (String menuID : menus.keySet()) {
			MenuItem curMenu = menus.get(menuID);
			Element barMenu = new Element(TAG_NAME_MENUBAR);
			barMenu.setAttribute(TAG_ATT_ID, menuID);
			buildMenu(barMenu, curMenu);
			menusNode.addContent(barMenu);
		}
		rootElement.addContent(menusNode);
	}

	private void buildMenu(Element parentElement, MenuItem parentMenu) {
		
		ArrayList<GUIComponent> items = parentMenu.getChildren();
		if ((items != null) && (items.size() > 0)) {
			for (GUIComponent curGUIComp : items) {
				MenuItem curItem = (MenuItem) curGUIComp;
				Element childItem = new Element(TAG_NAME_MENU);
				XMLUtil.setAttribute(childItem, TAG_ATT_ID, curItem.getID());
				if (curItem.getAction() != null) {
					XMLUtil.setAttribute(childItem, TAG_COMP_ATT_ACTION,
							curItem.getAction().getID());
				}
				XMLUtil.setAttribute(childItem, TAG_COMP_ATT_ICON, curItem
						.getIcon());
				String menuType = curItem.getType();
				if (menuType != null) {
					if (menuType.equals(Integer.toString(MENU_TYPE_SEP))) {
						XMLUtil.setAttribute(childItem, TAG_COMP_ATT_TYPE,
								TAG_ATT_MENU_TYPE_SEPARATOR);
					}
				}
				parentElement.addContent(childItem);
				if (curItem.hasChildren()) {
					buildMenu(childItem, curItem);
				}
			}
		}
	}

	private void addElements(HashMap<String,Element> items, String parentNode) {
		if ((items != null) && (items.size() > 0)) {
			Element itemEls = new Element(parentNode);
			for (String itemId : items.keySet()) {
				Element itemEl = items.get(itemId);
				// System.out.println(windowId);
				itemEls.addContent((Element) itemEl.clone());
			}
			rootElement.addContent(itemEls);
		}
	}

	private void addBuildNode() {
		Element buildNode = new Element(TAG_NAME_BUILD);
		addChildNode(buildNode, TAG_NAME_HOME, reader.getHomePath());
		addChildNode(buildNode, TAG_NAME_SOURCE, reader.getSourcePath());
		addChildNode(buildNode, TAG_NAME_CLASSES, reader.getClassPath());
		addChildNode(buildNode, TAG_NAME_MAINCLASS, reader
				.getMainClassPackage());
		addChildNode(buildNode, TAG_NAME_COMP_TYPE, reader.getComponentType());
		addChildNode(buildNode, SETT_XML_NODE_LAF, reader.getLAF());
		addImageSource(buildNode);
		addLibraries(buildNode);
		addLanguages(buildNode);
		addResources(buildNode);
		addSettingsFile(buildNode);
		addTargetJar(buildNode);
		rootElement.addContent(buildNode);

	}

	private void addTargetJar(Element parentEl) {
		FileInfo jarFile = reader.getAppJarFile();
		if (jarFile != null) {
			Element jarNode = new Element(TAG_NAME_TARGET_JAR);
			addChildNode(jarNode, TAG_NAME_TARGET_JAR_NAME, jarFile
					.getFileName());
			addChildNode(jarNode, TAG_NAME_TARGET_JAR_PATH, jarFile
					.getFilePath());
			parentEl.addContent(jarNode);
		}
	}

	private void addSettingsFile(Element parentEl) {
		FileInfo setFile = reader.getAppSettingsFile();
		if (setFile != null) {
			Element settings = new Element(TAG_NAME_SET_FILE);
			XMLUtil.setAttribute(settings, TAG_COMP_ATT_SOURCE_TYPE, reader
					.getAppSettingsSourceType());
			if (reader.getAppSettingsSourceType() != null) {
			}
			addChildNode(settings, TAG_NAME_NAME, setFile.getFileName());
			addChildNode(settings, TAG_NAME_PATH, setFile.getFilePath());
			parentEl.addContent(settings);
		}
	}

	private void addLanguages(Element parentEl) {
		HashMap<String,LangSource> langs = reader.loadLanguages();
		if ((langs != null) && (langs.size() > 0)) {
			Element langsNode = new Element(SETT_XML_NODE_LANGS);
			for (String langName : langs.keySet()) {
				LangSource langSrc = langs.get(langName);
				Element langEl = new Element(SETT_XML_NODE_LANG);
				langEl.setAttribute(TAG_COMP_ATT_NAME, langName);
				langEl.setAttribute(TAG_COMP_ATT_SOURCE, langSrc
						.getSourceFileName());
				langEl.setAttribute(TAG_COMP_ATT_TARGET, langSrc
						.getTargetFileName());
				langsNode.addContent(langEl);
			}
			parentEl.addContent(langsNode);
		}
	}

	private void addResources(Element parentEl) {
		HashMap<String,String> ress = reader.loadResources();
		if ((ress != null) && (ress.size() > 0)) {
			Element resNode = new Element(SETT_XML_NODE_RESS);
			for (String resName : ress.keySet()) {
				String resSrc = ress.get(resName);
				Element resEl = new Element(SETT_XML_NODE_RES);
				resEl.setAttribute(TAG_COMP_ATT_NAME, resName);
				resEl.setAttribute(TAG_COMP_ATT_SOURCE, resSrc);
				resNode.addContent(resEl);
			}
			parentEl.addContent(resNode);
		}
	}

	private void addLibraries(Element parentEl) {
		HashMap<String,String> libs = reader.loadLibraries();
		if ((libs != null) && (libs.size() > 0)) {
			Element libsNode = new Element(TAG_NAME_LIBS);
			for (String libID : libs.keySet()) {
				String libSrc = libs.get(libID);
				Element libEl = new Element(TAG_NAME_LIB);
				libEl.setAttribute(TAG_COMP_ATT_ID, libID);
				libEl.setText(libSrc);
				libsNode.addContent(libEl);
			}
			parentEl.addContent(libsNode);
		}
	}

	private void addImageSource(Element parentEl) {
		HashMap<String,String> srcImages = reader.loadSourceImages();
		if ((srcImages != null) && (srcImages.size() > 0)) {
			Element imgs = new Element(TAG_NAME_IMAGES);
			for (String imageSrc : srcImages.keySet()) {
				String imageType = srcImages.get(imageSrc);
				Element imgSrcEl = null;
				if (imageType.equals(IMG_SRC_TYPE_DIR)) {
					imgSrcEl = new Element(TAG_NAME_DIR);
				} else {
					imgSrcEl = new Element(TAG_NAME_IMAGE);
				}
				imgSrcEl.setAttribute(TAG_COMP_ATT_SOURCE, imageSrc);
				imgs.addContent(imgSrcEl);
			}
			parentEl.addContent(imgs);
		}

	}

	private void addChildNode(Element parentEl, String childName,
			String childValue) {
		if (childValue != null) {
			Element childNode = new Element(childName);
			childNode.setText(childValue);
			parentEl.addContent(childNode);
		}
	}

	private void addAppManager(String appManagerClass) {
		if (appManagerClass != null) {
			Element appLink = new Element(TAG_NAME_LINK);
			addChildNode(appLink, TAG_NAME_APP_MANAGER, appManagerClass);
			rootElement.addContent(appLink);
		}
	}

	private void addLog(LogFile log) {
		// Adding the log node
		if (log != null) {
			Element logNode = new Element(SET_XML_NODE_LOG);
			addChildNode(logNode, TAG_NAME_NAME, log.getFileName());
			addChildNode(logNode, TAG_NAME_PATH, log.getFilePath());
			rootElement.addContent(logNode);
		}
	}

	public void generateXML(PESwingApplicationReader reader, String targetFile)
			throws IOException {
		this.reader = reader;
		writeFileTo(targetFile);
	}

}
