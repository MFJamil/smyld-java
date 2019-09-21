package org.smyld.gui.portal.engine.sources;

import static org.smyld.app.AppConstants.MENU_TYPE_CLASSIC;
import static org.smyld.app.AppConstants.MENU_TYPE_TREE;
import static org.smyld.app.AppConstants.SETT_XML_ATT_DEFAULT;
import static org.smyld.app.AppConstants.SETT_XML_NODE_LAF;
import static org.smyld.app.AppConstants.SETT_XML_NODE_LAFS;
import static org.smyld.app.AppConstants.SETT_XML_NODE_LANG;
import static org.smyld.app.AppConstants.SETT_XML_NODE_LANGS;
import static org.smyld.app.AppConstants.SETT_XML_NODE_RES;
import static org.smyld.app.AppConstants.SETT_XML_NODE_RESS;
import static org.smyld.app.pe.model.Constants.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.smyld.app.pe.annotations.PEApplicationSource;
import org.smyld.app.pe.annotations.PELayoutHandler;
import org.smyld.app.pe.annotations.PEReadApplication;
import org.smyld.app.pe.exceptions.PortalFatal;
import org.smyld.app.pe.input.xml.PEAppXMLReader;
import org.smyld.app.pe.logging.LogFile;
import org.smyld.app.pe.model.ApplicationReader;
import org.smyld.app.pe.model.ApplicationType;
import org.smyld.app.pe.model.GUIToolkit;
import org.smyld.app.pe.model.LayoutType;
import org.smyld.app.pe.model.gui.*;
import org.smyld.app.pe.model.user.UserConstraint;
import org.smyld.app.pe.projectbuilder.MavenProjectBuilder;
import org.smyld.app.pe.projectbuilder.ProjectBuildSource;
import org.smyld.app.pe.projectbuilder.ProjectBuilder;
import org.smyld.app.pe.security.AppSecurity;
import org.jdom2.Document;
import org.jdom2.Element;

import org.smyld.deploy.DeploymentDescriptor;
import org.smyld.deploy.web.WebDeploymentDescriptor;
import org.smyld.deploy.web.jnlp.JNLPDeploymentDescriptor;
import org.smyld.deploy.web.jnlp.JNLPHTMLDocument;
import org.smyld.deploy.web.jnlp.ShortcutDescriptor;
import org.smyld.gui.GUIAction;
import org.smyld.io.FileSystem;
import org.smyld.resources.FileInfo;
import org.smyld.resources.LookAndFeelResource;
import org.smyld.security.SMYLDKey;
import org.smyld.util.multilang.LangSource;
import org.smyld.web.Server;
import org.smyld.xml.XMLUtil;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
@PELayoutHandler(name = "Swing XML Application Reader", applicationType = ApplicationType.Desktop, guiToolkit = GUIToolkit.swing, layoutType = LayoutType.XML)
public class PortalAppXMLSetReaderPESwing extends PEAppXMLReader implements
		PESwingApplicationReader {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Document xmlDocument;
	protected Element application;
	protected Element buildApplication;
	Element jarNode;
	Element linkNode;
	Element appSetNode;
	Element logNode;
	HashMap<String, String>               resources;
	HashMap<String, PEAction>             actions;
	HashMap<String, LangSource>           languages;
	HashMap<String, String>               images;
	HashMap<String, GUIToolbar>           toolbars;
	HashMap<String, String>               sourceImages;
	HashMap<String, MenuItem>             menus;
	HashMap<String, LookAndFeelResource>  lafs;
	HashMap<String, Element>              panels;
	Vector<DeploymentDescriptor>          deployments;
	SettingsVariablesMapper               elMapper;
	// HashMap libraries;
	LogFile     logFile;
	FileInfo    appSetFile;
	AppSecurity appSecurity;
	String      appName;
	String      appType;
	InputStream sourceStream;
	Optional<ProjectBuilder> projectBuilder = Optional.empty();

	public PortalAppXMLSetReaderPESwing(){}

	/**
	 * 
	 * @see
	 * @since
	 */
	public PortalAppXMLSetReaderPESwing(String appXMLDoc, ProjectBuilder prjBuilder) throws Exception {
		super(appXMLDoc);
		projectBuilder = prjBuilder!=null? Optional.of(prjBuilder): Optional.empty();
		doInit();
	}
	public PortalAppXMLSetReaderPESwing(InputStream appXMLStream, ProjectBuilder prjBuilder) throws Exception {
		super(appXMLStream);
		projectBuilder = prjBuilder!=null? Optional.of(prjBuilder): Optional.empty();
		doInit();
	}

	@PEReadApplication
	public ApplicationReader readApplicationFile(@PEApplicationSource String fileName) throws Exception{
		rootNode = XMLUtil.getRootNode(fileName);
		doInit();
		
		return this;

	}





	protected void doInit() throws Exception{

		application = rootNode;
		buildApplication = application.getChild(TAG_NAME_BUILD);
		jarNode = buildApplication.getChild(TAG_NAME_TARGET_JAR);
		linkNode = application.getChild(TAG_NAME_LINK);
		logNode = application.getChild(SET_XML_NODE_LOG);
		appSetNode = buildApplication.getChild(TAG_NAME_SET_FILE);

		doLoadImages();
		// Creating the log file
		if (logNode != null) {
			logFile = new LogFile();
			addFileInfo(logFile, logNode);
		}
		// loading the application settings file
		appSetFile = new FileInfo();
		addFileInfo(appSetFile, appSetNode);
		appName = XMLUtil.getChildValue(application, TAG_NAME_NAME,"Unknown");
		appType = application.getAttributeValue(TAG_ATT_TYPE);
		// loading the languages
		doLoadActions();
		doLoadMenus();
		doLoadToolbars();
		doLoadLangs();
		doLoadLafs();
		doLoadResources();
		doLoadAppSecurity();
		doLoadDeployments();
		if ((menus != null) && (menus.size() > 0)) {
			analyseMenuReferences(rootNode);
			// System.out.println("End of XML document .... ");
		}

	}

	@SuppressWarnings("unchecked")
	private void analyseMenuReferences(Element parentNode) throws Exception {
		// Navigation through the whole document
		List children = parentNode.getChildren();
		Iterator itr = children.iterator();
		while (itr.hasNext()) {
			Element curEl = (Element) itr.next();
			String menuID = null;
			int curType = 0;

			if (curEl.getName().equals(TAG_NAME_MENUBAR)) {
				menuID = curEl.getAttributeValue(TAG_ATT_ID);
				if ((menuID != null)
						&& (!parentNode.getName().equals(TAG_NAME_MENUS))) {
					String menuType = curEl.getAttributeValue(TAG_ATT_TYPE);
					if (menuType.equals(MENU_TYPE_CLASSIC)) {
						curType = MENU_AS_BAR;
					}
					if (menuType.equals(MENU_TYPE_TREE)) {
						curType = MENU_AS_TREE;
					}
				}
			}
			if (curEl.getAttributeValue(TAG_COMP_ATT_POPUP) != null) {
				menuID = curEl.getAttributeValue(TAG_COMP_ATT_POPUP);
				curType = MENU_AS_POPUP;
			}
			if ((curEl.getAttributeValue(TAG_COMP_ATT_COM) != null)
					&& (curEl.getAttributeValue(TAG_COMP_ATT_COM)
							.equals(GUIAction.COM_OPEN_POPUP))) {
				menuID = curEl.getAttributeValue(TAG_COMP_ATT_TARGET);
				curType = MENU_AS_POPUP;
			}

			if ((curType != 0) && (menuID != null)) {
				MenuItem curMenu = menus.get(menuID);
				if (curMenu == null) {
					throw new PortalFatal("Menu referenced does not exist! - ("
							+ menuID + ")");
				}
				curMenu.setUsage(curMenu.getUsage() | curType);
			}
			if ((curEl.getChildren()!=null)&&(curEl.getChildren().size()>0)) {
				analyseMenuReferences(curEl);
			}

		}
	}

	@SuppressWarnings("unchecked")
	private void doLoadAppSecurity() {
		appSecurity = new AppSecurity();
		Element secNode = buildApplication.getChild(TAG_NAME_SECURITY);
		// Loading key in case exists
		if ((secNode != null) && (secNode.getChildren(TAG_NAME_KEY) != null)) {
			Iterator itr = secNode.getChildren(TAG_NAME_KEY).iterator();
			while (itr.hasNext()) {
				Element curKey = (Element) itr.next();
				if ((curKey.getAttributeValue(TAG_ATT_ID) != null)
						&& ((curKey.getAttributeValue(TAG_ATT_PASS) != null))) {
					SMYLDKey newKey = new SMYLDKey();
					newKey.setName(curKey.getAttributeValue(TAG_ATT_ID));
					newKey.setPassword(curKey.getAttributeValue(TAG_ATT_PASS));

					Element desc = curKey.getChild(TAG_NAME_DESC);
					if (desc != null) {
						newKey.setOwnerCompleteName(XMLUtil.getChildValue(desc,
								TAG_NAME_OWNER_NAME));
						newKey.setOrganizationName(XMLUtil.getChildValue(desc,
								TAG_NAME_ORAGNIZATION));
						newKey.setOrganizationUnitName(XMLUtil.getChildValue(
								desc, TAG_NAME_ORG_UNIT));
						newKey.setCountryCode(XMLUtil.getChildValue(desc,
								TAG_NAME_COUNTRY));
						newKey.setLocality(XMLUtil.getChildValue(desc,
								TAG_NAME_CITY));
						newKey.setState(XMLUtil.getChildValue(desc,
								TAG_NAME_STATE));
					}
					Element keyStore = curKey.getChild(TAG_NAME_STORE);
					if (keyStore != null) {
						newKey.setKeyStore(keyStore
								.getAttributeValue(TAG_ATT_ID));
					}
					appSecurity.addKey(newKey);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void doLoadLangs() {
		Element langs = buildApplication.getChild(SETT_XML_NODE_LANGS);
		if (langs == null) {
			return;
		}
		List langList = langs.getChildren(SETT_XML_NODE_LANG);
		if (langList.size() > 0) {
			languages = new HashMap<String, LangSource>(langList.size());
			Iterator itr = langList.iterator();
			while (itr.hasNext()) {
				Element curLang = (Element) itr.next();
				LangSource newLang = new LangSource();
				String langName = curLang.getAttributeValue(TAG_COMP_ATT_NAME);
				String langFile = curLang
						.getAttributeValue(TAG_COMP_ATT_SOURCE);
				String destFile = curLang
						.getAttributeValue(TAG_COMP_ATT_TARGET);
				newLang.setName(langName);
				newLang.setSourceFileName(langFile);
				newLang.setTargetFileName(destFile);
				languages.put(langName, newLang);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void doLoadLafs() {
		Element appearance = application.getChild(TAG_NAME_APPEARANCE);
		if (appearance == null) {
			return;
		}
		Element lafsEl = appearance.getChild(SETT_XML_NODE_LAFS);
		if (lafsEl == null) {
			return;
		}
		List lafList = lafsEl.getChildren(SETT_XML_NODE_LAF);
		if (lafList.size() > 0) {
			lafs = new HashMap<String, LookAndFeelResource>(lafList.size());
			Iterator itr = lafList.iterator();
			while (itr.hasNext()) {
				Element curLaf = (Element) itr.next();
				LookAndFeelResource newLaf = new LookAndFeelResource();
				String lafName = curLaf.getAttributeValue(TAG_COMP_ATT_NAME);
				String lafClass = curLaf.getAttributeValue(TAG_ATT_CLASS);
				String selected = curLaf.getAttributeValue(SETT_XML_ATT_DEFAULT);
				if (selected!=null) newLaf.setSelected(Boolean.parseBoolean(selected));

				newLaf.setName(lafName);
				newLaf.setClassName(lafClass);
				lafs.put(lafName, newLaf);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void doLoadResources() {
		Element res = buildApplication.getChild(SETT_XML_NODE_RESS);
		if (res == null) {
			return;
		}
		List resList = res.getChildren(SETT_XML_NODE_RES);
		if (resList.size() > 0) {
			resources = new HashMap<String, String>(resList.size());
			Iterator itr = resList.iterator();
			while (itr.hasNext()) {
				Element curRes = (Element) itr.next();
				String resName = curRes.getAttributeValue(TAG_COMP_ATT_NAME);
				String resFile = curRes.getAttributeValue(TAG_COMP_ATT_SOURCE);
				resources.put(resName, resFile);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void doLoadToolbars() {
		toolbars = new HashMap<String, GUIToolbar>();
		Element toolbarsNode = application.getChild(TAG_NAME_TLBS);
		if (toolbarsNode != null) {
			List toolbarList = toolbarsNode.getChildren(TAG_NAME_TLB);
			if (toolbarList != null) {
				Iterator itr = toolbarList.iterator();
				while (itr.hasNext()) {
					Element curToolbar = (Element) itr.next();
					GUIToolbar newToolbar = createToolbar(curToolbar);
					toolbars.put(newToolbar.getID(), newToolbar);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private GUIToolbar createToolbar(Element curToolbar) {
		GUIToolbar newToolbar = new GUIToolbar();
		newToolbar.setID(curToolbar.getAttributeValue(TAG_COMP_ATT_ID));
		newToolbar
				.setEnabled(curToolbar.getAttributeValue(TAG_COMP_ATT_ENABLE));
		newToolbar.setTooltip(curToolbar
				.getAttributeValue(TAG_COMP_ATT_TOOLTIP));
		List menuItems = curToolbar.getChildren();
		Iterator itr = menuItems.iterator();
		while (itr.hasNext()) {
			Element curItem = (Element) itr.next();
			ActionHolderItem newActionItem = createMenuItem(curItem);
			newToolbar.addChild(newActionItem);
		}
		return newToolbar;
	}

	@SuppressWarnings("unchecked")
	private void doLoadDeployments() {
		Element deploys = buildApplication.getChild(TAG_NAME_DEPLOYMENTS);
		if ((deploys != null)
				&& (deploys.getChildren(TAG_NAME_DEPLOYMENT) != null)) {
			deployments = new Vector<DeploymentDescriptor>();
			Iterator itr = deploys.getChildren(TAG_NAME_DEPLOYMENT).iterator();
			while (itr.hasNext()) {
				DeploymentDescriptor newDeployment = null;
				Element curDeloyment = (Element) itr.next();
				String deployType = curDeloyment
						.getAttributeValue(TAG_ATT_TYPE);
				if (deployType.equals(TAG_ATT_DEPLOY_TYPE_JNLP)) {
					newDeployment = createJNLPDeployDescr(curDeloyment);
				}
				deployments.add(newDeployment);
			}
		}
	}

	private void loadDeployDescr(DeploymentDescriptor desc, Element deployNode) {
		desc.setTitle(XMLUtil.getChildValue(deployNode, TAG_NAME_TITLE));
		desc.setTooltip(XMLUtil.getChildValue(deployNode, TAG_NAME_TOOLTIP));
		desc.setDescription(XMLUtil.getChildValue(deployNode, TAG_NAME_DESC));
		desc.setVendor(XMLUtil.getChildValue(deployNode, TAG_NAME_VENDOR));
		desc.setVendor(XMLUtil.getChildValue(deployNode, TAG_NAME_VENDOR));
		desc.setName(XMLUtil.getChildValue(deployNode, TAG_NAME_NAME));
		desc.setIcon(XMLUtil.getChildValue(deployNode, TAG_NAME_ICON));
	}

	@SuppressWarnings("unchecked")
	private void loadWebDeployDescr(WebDeploymentDescriptor desc,
			Element deployNode) {
		loadDeployDescr(desc, deployNode);
		desc.setCodeBase(XMLUtil.getChildValue(deployNode, TAG_NAME_CODE_BASE)
				+ desc.getName());
		// Code for loading the server object
		if (deployNode.getChild(TAG_NAME_SERVER) != null) {
			Element serverNode = deployNode.getChild(TAG_NAME_SERVER);
			Server targetServer = new Server();
			targetServer.setName(serverNode.getAttributeValue(TAG_ATT_NAME));
			targetServer.setType(serverNode.getAttributeValue(TAG_ATT_TYPE));
			targetServer.setPath(XMLUtil.getChildValue(serverNode,
					TAG_NAME_PATH));
			desc.setTargetServer(targetServer);
		}
		// Code for loading the security permissions of the application
		Element secNode = deployNode.getChild(TAG_NAME_SECURITY);
		if (secNode != null) {
			// Reading the key (need to extend the criteria to contain the
			// information of already existing keys in the future)
			Element key = secNode.getChild(TAG_NAME_KEY);
			if (key != null) {
				String keyName = key.getAttributeValue(TAG_ATT_ID);
				if (appSecurity.containsKey(keyName)) {
					desc.setSecurityKey(appSecurity.getKey(keyName));
				}
			}
			// Reading the permissions
			if (secNode.getChildren(TAG_NAME_PERMISSION) != null) {
				Iterator itr = secNode.getChildren(TAG_NAME_PERMISSION)
						.iterator();
				while (itr.hasNext()) {
					Element curPermission = (Element) itr.next();
					desc.addPermission(curPermission.getText());
				}
			}
		}
	}

	private JNLPDeploymentDescriptor createJNLPDeployDescr(Element deployNode) {
		JNLPDeploymentDescriptor desc = new JNLPDeploymentDescriptor();
		loadWebDeployDescr(desc, deployNode);
		if (deployNode.getChild(TAG_NAME_OFF_ALLOWED) != null) {
			desc.setOfflineAllowed(true);
		}
		// Code for creating the shortcut
		if (deployNode.getChild(TAG_NAME_SHORTCUT) != null) {
			ShortcutDescriptor newShortCut = new ShortcutDescriptor();
			Element shortCut = deployNode.getChild(TAG_NAME_SHORTCUT);
			newShortCut
					.setDesktop(Boolean.valueOf(
							shortCut.getAttributeValue(TAG_ATT_DESKTOP))
							.booleanValue());
			newShortCut.setOnline(Boolean.valueOf(
					shortCut.getAttributeValue(TAG_ATT_FIRESONLINE))
					.booleanValue());
			newShortCut.setMenu(shortCut.getAttributeValue(TAG_ATT_TITLE));
			desc.setShortcut(newShortCut);
		}
		// Code for creating the web page
		if (deployNode.getChild(TAG_NAME_LAUNCH_PAGE) != null) {
			Element pageNode = deployNode.getChild(TAG_NAME_LAUNCH_PAGE);
			JNLPHTMLDocument newPage = new JNLPHTMLDocument();
			newPage.setTitle(XMLUtil.getChildValue(pageNode, TAG_NAME_TITLE));
			newPage.setHeadline(XMLUtil.getChildValue(pageNode,
					TAG_NAME_HEADLINE));
			// newPage.addLink();
			desc.setHtmlDocument(newPage);
		}
		return desc;
	}

	@SuppressWarnings("unchecked")
	private void doLoadImages() {
		images = new HashMap<String, String>();
		sourceImages = new HashMap<String, String>();
		Element imagesNode = buildApplication.getChild(TAG_NAME_IMAGES);
		if (imagesNode == null) {
			return;
		}
		List dirs = imagesNode.getChildren(TAG_NAME_DIR);
		Iterator itr = dirs.iterator();
		while (itr.hasNext()) {

			Element curDir = (Element) itr.next();
			String imgDir = curDir.getAttributeValue(TAG_COMP_ATT_SOURCE);
			if (imgDir != null) {
				sourceImages.put(imgDir, IMG_SRC_TYPE_DIR);
				FileSystem.loadImagesInFolder(imgDir, images);
			}
		}
		List images = imagesNode.getChildren(TAG_NAME_IMAGE);
		itr = images.iterator();
		while (itr.hasNext()) {
			Element curImage = (Element) itr.next();
			File newImage = new File(curImage
					.getAttributeValue(TAG_COMP_ATT_SOURCE));
			if (newImage != null) {
				sourceImages.put(newImage.getPath(), IMG_SRC_TYPE_FILE);
				addImage(newImage);
			}
		}
	}

	private void addImage(File newImageFile) {
		images.put(newImageFile.getName(), newImageFile.getPath());
	}

	@Override
	public void addFileInfo(FileInfo targetFile, Element inforElement) {
		if (inforElement != null) {
			targetFile.setFileName(inforElement.getChild(TAG_COMP_ATT_NAME)
					.getText());
			targetFile.setFilePath(inforElement.getChild(TAG_COMP_ATT_PATH)
					.getText());
		}
	}

	public HashMap<String, Element> loadPanels() {
		if (panels==null)
			panels = loadElements(application, TAG_NAME_PANELS);
		return panels;
	}
	public boolean containsPanelID(String panelID){
		if (panels!=null){
			return panels.containsKey(panelID);
		}
		return false;
	}
	
	public HashMap<String, Element> loadWindows() {
		return loadElements(application, TAG_NAME_WINDOWS);
	}

	public Element getSplash() {
		return application.getChild("splash");
	}

	public HashMap<String, String> loadLibraries() {
		return loadElementsAsText(buildApplication, TAG_NAME_LIBS);
		// return libraries;
	}
	public boolean exportLibraries(){
		Element libs = buildApplication.getChild(TAG_NAME_LIBS);
		if (libs!=null)
			return XMLUtil.getBooleanAttribute(libs,"export");
		return false;
	}

	/*
	 * private void doLoadLibraries() { libraries = new HashMap(); Element
	 * libsNode = buildApplication.getChild(TAG_NAME_LIBS); List childList =
	 * libsNode.getChildren(); Iterator itr = childList.iterator();
	 * while(itr.hasNext()){ Element currentLib = (Element) itr.next(); String
	 * libID = currentLib.getAttributeValue(TAG_COMP_ATT_ID); String libSrc =
	 * currentLib.getText(); libraries.put(libID,libSrc); } }
	 */

	public HashMap<String, MenuItem> loadMenus() {
		return menus;
	}

	public void doLoadMenus() {
		HashMap<String, Element> menusEls = loadElements(application,
				TAG_NAME_MENUS);
		if ((menusEls != null) && (menusEls.size() > 0)) {
			menus = new HashMap<String, MenuItem>();
			for (Element currentMenuBar : menusEls.values()) {
				MenuItem currentMenuItem = new MenuItem();
				currentMenuItem.setID(currentMenuBar
						.getAttributeValue(TAG_COMP_ATT_ID));
				buildMenu(currentMenuItem, currentMenuBar);
				menus.put(currentMenuItem.getID(), currentMenuItem);
			}
		}

	}

	@SuppressWarnings("unchecked")
	private void buildMenu(MenuItem parentMenuItem, Element menuElement) {
		if (hasChildren(menuElement)) {
			List childs = menuElement.getChildren(TAG_NAME_MENU);
			Iterator itr = childs.iterator();
			while (itr.hasNext()) {
				Element currentItem = (Element) itr.next();
				MenuItem newItem = createMenuItem(currentItem);
				parentMenuItem.addChild(newItem);
				if (hasChildren(currentItem)) {
					buildMenu(newItem, currentItem);
				}
			}
		}
	}
	private boolean hasChildren(Element curEl){
		return ((curEl.getChildren()!=null)&&(curEl.getChildren().size()>0));
	}

	/*
	 * private ActionHolderItem createActionItem(Element menuElement){
	 * ActionHolderItem newItem = new ActionHolderItem(); String itemID =
	 * menuElement.getAttributeValue(TAG_COMP_ATT_ID); String itemIcon =
	 * menuElement.getAttributeValue(TAG_COMP_ATT_ICON); newItem.setID(itemID);
	 * newItem.setType(menuElement.getAttributeValue(TAG_COMP_ATT_TYPE)); if
	 * (itemIcon!=null) newItem.setIcon(itemIcon); GUIAction menuAction
	 * =(GUIAction)
	 * actions.get(menuElement.getAttributeValue(TAG_COMP_ATT_ACTION));
	 * newItem.setAction(menuAction); return newItem; }
	 */

	private MenuItem createMenuItem(Element menuElement) {
		MenuItem newItem = new MenuItem();
		String mnuID = menuElement.getAttributeValue(TAG_COMP_ATT_ID);
		String mnuIcon = menuElement.getAttributeValue(TAG_COMP_ATT_ICON);
		newItem.setID(mnuID);
		newItem.setLabel(menuElement.getAttributeValue(TAG_COMP_ATT_LABEL));
		newItem.setEnabled(menuElement.getAttributeValue(TAG_COMP_ATT_ENABLE));
		newItem.setTooltip(menuElement.getAttributeValue(TAG_COMP_ATT_TOOLTIP));
		newItem.setAccelerator(menuElement
				.getAttributeValue(TAG_COMP_ATT_ACCELERATOR));
		newItem.setPopupMenu(menuElement.getAttributeValue(TAG_COMP_ATT_POPUP));
		String menuType = menuElement.getAttributeValue(TAG_COMP_ATT_TYPE);
		newItem.setUserConstraint(readConstraint(menuElement));
		/*
		if ((menuType != null)
				&& (menuType.equals(TAG_ATT_MENU_TYPE_SEPARATOR))) {
			menuType = Integer.toString(MENU_TYPE_SEP);
		} else {
			menuType = Integer.toString(MENU_TYPE_ITEM);
		}
		*/
		newItem.setType(menuType);
		newItem.setMenuType(MenuType.parse(menuType));
		if (mnuIcon != null) {
			newItem.setIcon(mnuIcon);
		}
		PEAction menuAction = actions.get(menuElement
				.getAttributeValue(TAG_COMP_ATT_ACTION));
		newItem.setAction(menuAction);
		// Checking the values for the case of the combo box in the toolbar
		detectValues(menuElement,newItem);
		return newItem;
	}
	private void detectValues(Element currentElement, GUIComponent curComp) {
		Element values = currentElement.getChild(TAG_NAME_VALUES);
		if ((values != null) && (hasChildren(values))) {
			List valueList = values.getChildren(TAG_NAME_VALUE);
			Object[] comValues = new Object[valueList.size()];
			Iterator itr = valueList.iterator();
			int count = 0;
			while (itr.hasNext()) {
				Element curValue = (Element) itr.next();
				comValues[count] = curValue.getText();
				count++;
			}	
			
			curComp.setValues(comValues);
		}
	}


	@SuppressWarnings("unchecked")
	public void doLoadActions() {
		actions = new HashMap<String, PEAction>();
		Element actionsNode = application.getChild(TAG_NAME_ACTIONS);
		List actionsList = actionsNode.getChildren();
		Iterator itr = actionsList.iterator();
		while (itr.hasNext()) {
			Element currentAction = (Element) itr.next();
			PEAction newAction = new PEAction();
			newAction.setID(currentAction.getAttributeValue(TAG_COMP_ATT_ID));
			newAction.setLabel(currentAction.getAttributeValue(TAG_COMP_ATT_LABEL));
			newAction.setTitle(currentAction.getAttributeValue(TAG_COMP_ATT_TITLE));
			newAction.setIconText(currentAction.getAttributeValue(TAG_COMP_ATT_ICON));
			newAction.setTarget(currentAction.getAttributeValue(TAG_COMP_ATT_TARGET));
			newAction.setParameters(currentAction.getAttributeValue(TAG_COMP_ATT_PARAM));
			newAction.setCommand(currentAction.getAttributeValue(TAG_COMP_ATT_COM));
			newAction.setUserObject(currentAction.getAttributeValue(TAG_COMP_ATT_OBJECT));
			newAction.setUserConstraint(readConstraint(currentAction));
			actions.put(newAction.getID(), newAction);
		}
	}
	public UserConstraint readConstraint(Element el){
		if ((el.getAttributeValue(TAG_COMP_ATT_ROLE)!=null)||
				(el.getAttributeValue(TAG_COMP_ATT_SHOW_ROLE)!=null)){
			UserConstraint userConst = new UserConstraint(el.getAttributeValue(TAG_COMP_ATT_ID),el.getAttributeValue(TAG_COMP_ATT_ROLE),el.getAttributeValue(TAG_COMP_ATT_SHOW_ROLE));
			return userConst;
		}
		return null;
	}

	public HashMap<String, PEAction> loadActions() {
		return actions;
	}

	public String getGroup() {
		return XMLUtil.getChildValue(buildApplication,TAG_NAME_GROUP,"org.smyld.apps");
	}


	public String getMainClassPackage() {
		return APP_MAIN_CLASS_PACKAGE;
		/* TODO We need to change the design of hardcoding the location of the main gui class
		if(projectBuilder.getSource()==ProjectBuildSource.Maven)
			return ((MavenProjectBuilder)projectBuilder).getGroup();
		return buildApplication.getChild(TAG_NAME_MAINCLASS).getText();
		*/
	}

	public String getHomePath() {
		String resultPath = processEL(buildApplication.getChild(TAG_NAME_HOME).getText()); 
		//System.out.println("Home path : " + resultPath);
		if(resultPath==null)
			resultPath = System.getProperty("user.dir");
		return resultPath;
		//return buildApplication.getChild(TAG_NAME_HOME).getText();
	}
	public static void main(String[] args){
		try {
			PortalAppXMLSetReaderPESwing reader = new PortalAppXMLSetReaderPESwing("/home/jamil/workspace/tools-billing-interface/sources/test_el.xml",null);
			SettingsVariablesMapper map = new SettingsVariablesMapper();
			map.addVariable("basedir", "/home/jamil/trunk/billing/frontend");
			reader.setVariableMapper(map);
			System.out.println("Home path is : " + reader.getHomePath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	// This implementation need to be removed later
	public String processEL(String value){
		
		if ((elMapper!=null)&&(value!=null)){
			StringBuilder sb = new StringBuilder(value);
			int start = value.indexOf("${"); 
			if (start!=-1){
				int end = value.indexOf("}",start+2);
				if (end!=-1){
					String elVariable = value.substring(start+2,end);
					String varValue =  elMapper.map(elVariable);
					if (varValue!=null){
						sb.replace(start, end+1, varValue);
						System.out.println(" --- document variable (" + elVariable + "), resultant value : " + sb.toString());
						return sb.toString();
					}
				}
			}
			
		}
			
		return value;
	}

	public String getSourcePath() {

		if (projectBuilder.isPresent()) {
			if (projectBuilder.get().getSource() == ProjectBuildSource.Maven)
				return ((MavenProjectBuilder) projectBuilder.get()).getSourcePath();
		}
		String resultPath = processEL(buildApplication.getChild(TAG_NAME_SOURCE).getText());
		//System.out.println("Source path : " + resultPath);
		return resultPath;
		
		//return processEL(buildApplication.getChild(TAG_NAME_SOURCE).getText());
	}

	public String getLAF() {
		return XMLUtil.getChildValue(buildApplication, SETT_XML_NODE_LAF);
	}

	public String getClassPath() {
		return processEL(buildApplication.getChild(TAG_NAME_CLASSES).getText());
	}

	public String getComponentType() {
		//Setting default component type is swimg
		Element compTypeEl = buildApplication.getChild(TAG_NAME_COMP_TYPE);
		return (compTypeEl!=null?compTypeEl.getText():TAG_COMP_TYPE_SWING);
	}

	public String getTargetJarName() {
		return jarNode.getChild(TAG_NAME_TARGET_JAR_NAME).getText();
	}

	public String getTargetJarPath() {
		if (jarNode!=null)
			return processEL(jarNode.getChild(TAG_NAME_TARGET_JAR_PATH).getText());
		return null;
	}

	public String getAppStartupClass() {
		return application.getAttributeValue(TAG_COMP_ATT_ON_STARTUP);
	}

	public String getAppManagerClass() {
		if (linkNode != null) {
			Element linkingAppClass = linkNode.getChild(TAG_NAME_APP_MANAGER);
			if (linkingAppClass != null) {
				return linkingAppClass.getText();
			}
		}
		return null;
	}

	public LogFile getLogFile() {
		return logFile;
	}

	public HashMap<String, LangSource> loadLanguages() {
		return languages;
	}

	public FileInfo getAppSettingsFile() {
		return appSetFile;
	}

	public HashMap<String, String> loadImages() {
		return images;
	}

	public HashMap<String, String> loadSourceImages() {
		return sourceImages;
	}

	public HashMap<String, String> loadResources() {
		return resources;
	}

	public String getAppSettingsSourceType() {
		if (appSetNode!=null)
			return appSetNode.getAttributeValue(TAG_COMP_ATT_SOURCE_TYPE);
		return null;
	}

	public HashMap<String, GUIToolbar> loadToolbars() {
		return toolbars;
	}

	public Vector<DeploymentDescriptor> loadDeployments() {
		// Code for returning all the deployments mentioned in the settings
		return deployments;
	}

	public AppSecurity getAppSecurity() {
		return appSecurity;
	}

	public String getAppName() {
		return appName;
	}

	public HashMap<String, LookAndFeelResource> loadLookAndFeels() {
		return lafs;
	}

	public FileInfo getAppJarFile() {
		return null;
	}

	public String getAppType() {
		return appType;
	}

	public boolean compileClasses() {
		return XMLUtil.getBooleanAttribute(buildApplication, "compile");
	}

	public boolean createJarFile() {
		return XMLUtil.getBooleanAttribute(jarNode, "deploy");
	}
	public void setVariableMapper(SettingsVariablesMapper mapper){
		elMapper = mapper;
	}

	@Override
	public ProjectBuilder getProjectBuilder() {
		return projectBuilder.get();
	}

	public InputStream getSourceStream() {
		return sourceStream;
	}
	public void setSourceStream(InputStream sourceStream) {
		this.sourceStream = sourceStream;
	}
	
}
