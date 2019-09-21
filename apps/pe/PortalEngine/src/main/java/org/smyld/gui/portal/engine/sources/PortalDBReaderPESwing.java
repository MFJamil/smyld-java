package org.smyld.gui.portal.engine.sources;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;

import org.smyld.app.pe.logging.LogFile;
import org.smyld.app.pe.model.ApplicationType;
import org.smyld.app.pe.model.Constants;
import org.smyld.app.pe.model.GUIToolkit;
import org.smyld.app.pe.model.LayoutType;
import org.smyld.app.pe.model.gui.GUIToolbar;
import org.smyld.app.pe.projectbuilder.ProjectBuilder;
import org.smyld.app.pe.security.AppSecurity;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import org.smyld.db.DBConnection;
import org.smyld.db.DBErrorHandler;
import org.smyld.db.SMYLDDataBaseHandler;
import org.smyld.deploy.DeploymentDescriptor;
import org.smyld.gui.GUIAction;
import org.smyld.app.pe.model.gui.ActionHolderItem;
import org.smyld.app.pe.model.gui.MenuItem;
import org.smyld.app.pe.model.gui.PEAction;
import org.smyld.io.FileSystem;
import org.smyld.resources.FileInfo;
import org.smyld.resources.LookAndFeelResource;
import org.smyld.util.multilang.LangSource;
import org.smyld.xml.XMLUtil;

import static org.smyld.app.AppConstants.SETT_TYPE_XML;

public class PortalDBReaderPESwing extends SMYLDDataBaseHandler implements
		DBPortalConstants, SQLStatements, PESwingApplicationReader, Constants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String appName;
	String appHome;
	String appSource;
	String appClasses;
	String appLink;
	String appAppearance;
	String appMainClass;
	String appCompType;
	String appType;
	HashMap<String,String>     appLibs;
	HashMap<String,String>     appRes;
	HashMap<String,LangSource> appLangs;
	HashMap<String,PEAction>   appActions;
	HashMap<String,MenuItem>   appMenus;
	HashMap<String,Element>    appPanels;
	HashMap<String,Element>    appWindows;
	HashMap<String,String>     appImages;
	HashMap<String,String>     appSourceImages;
	HashMap<String,GUIToolbar> appToolbars;
	FileInfo appSettings;
	FileInfo appJarFile;
	LogFile appLog;

	public PortalDBReaderPESwing(DBErrorHandler e, DBConnection conn) {
		super(e, conn);
	}
	
	public boolean containsPanelID(String panelID){return false;}

	public void readApplication(String appID) {
		doLoadApplication(appID); // Reading the main application record
		doLoadAppLibraries(appID); // Reading the libraries
		doLoadAppResources(appID); // Reading the resources
		doLoadAppLangs(appID); // Reading the languages
		doLoadAppActions(appID); // Reading the actions
		doLoadAppMenus(appID); // Reading the menus
		doLoadPanels(appID); // Reading the panels
		doLoadWindows(appID); // Reading the windows
		doLoadImages(appID); // Reading the images
		doLoadToolbars(appID); // Reading the images

	}

	private void doLoadImages(String appID) {
		ResultSet rsImgs = dbUtility.getSingleParamSQL(appID, SEL_APP_IMAGES);
		try {
			while (rsImgs.next()) {
				if (appImages == null) {
					appImages = new HashMap<String,String>();
					appSourceImages = new HashMap<String,String>();
				}
				String imgSrc = rsImgs.getString(COL_PRT_IMAGES_IMG_SRC);
				String imgType = rsImgs.getString(COL_PRT_IMAGES_IMG_TYPE);
				appSourceImages.put(imgSrc, imgType);
				if (imgType.equals(IMG_SRC_TYPE_DIR)) {
					FileSystem.loadImagesInFolder(imgSrc, appImages);
				} else {
					File imgFile = new File(imgSrc);
					if (imgFile.exists()) {
						appImages.put(imgFile.getName(), imgFile.getPath());
					}
				}

			}

		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsImgs);
		}

	}

	private void doLoadPanels(String appID) {
		appPanels = doLoadItemsAsXML(appID, SEL_APP_PANELS, COL_PRT_PANELS_ID,
				COL_PRT_PANELS_BODY);
	}

	private void doLoadWindows(String appID) {
		appWindows = doLoadItemsAsXML(appID, SEL_APP_WINDOWS,
				COL_PRT_WINDOWS_ID, COL_PRT_WINDOWS_BODY);
	}

	private HashMap<String,Element> doLoadItemsAsXML(String appID, String sql, String elID,
			String elBody) {
		HashMap<String,String>   els    = doLoadItems(appID, sql, elID, elBody);
		HashMap<String, Element> result = null; 
		if ((els != null) && (els.size() > 0)) {
			result = new HashMap<String, Element>(els.size());
			for (String ID : els.keySet()) {
				String Body = els.get(ID);
				ByteArrayInputStream in = new ByteArrayInputStream(Body
						.getBytes());
				try {
					Element rootEl = XMLUtil.getRootNode(in);
					result.put(ID, rootEl);
				} catch (JDOMException|IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private void doLoadApplication(String appID) {
		ResultSet rsApp = dbUtility.getSingleParamSQL(appID, SEL_APPLICATION);
		try {
			if (rsApp.next()) {
				appName = rsApp.getString(COL_PRT_APPLICATIONS_NAME);
				appType = rsApp.getString(COL_PRT_APPLICATIONS_TYPE);
				appHome = rsApp.getString(COL_PRT_APPLICATIONS_HOME);
				appSource = rsApp.getString(COL_PRT_APPLICATIONS_SRC);
				appClasses = rsApp.getString(COL_PRT_APPLICATIONS_CLASSES);
				appLink = rsApp.getString(COL_PRT_APPLICATIONS_LINK);
				appAppearance = rsApp
						.getString(COL_PRT_APPLICATIONS_APPEARANCE);
				appMainClass = rsApp.getString(COL_PRT_APPLICATIONS_MAINCLASS);
				appCompType = rsApp
						.getString(COL_PRT_APPLICATIONS_COMPONENT_TYPE);
				appSettings = new FileInfo();
				appSettings.setFileName(rsApp
						.getString(COL_PRT_APPLICATIONS_SETTINGS_NAME));
				appSettings.setFilePath(rsApp
						.getString(COL_PRT_APPLICATIONS_SETTINGS_PATH));
				appJarFile = new FileInfo();
				appJarFile.setFileName(rsApp
						.getString(COL_PRT_APPLICATIONS_JAR_NAME));
				appJarFile.setFilePath(rsApp
						.getString(COL_PRT_APPLICATIONS_JAR_PATH));
				appLog = new LogFile();
				appLog.setFileName(rsApp
						.getString(COL_PRT_APPLICATIONS_LOG_NAME));
				appLog.setFilePath(rsApp
						.getString(COL_PRT_APPLICATIONS_LOG_PATH));

			}

		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsApp);
		}

	}

	private void doLoadAppLibraries(String appID) {
		appLibs = doLoadItems(appID, SEL_APP_LIBRARIES, COL_PRT_LIBRARY_ID,
				COL_PRT_LIBRARY_SRC);
	}

	private void doLoadAppResources(String appID) {
		appRes = doLoadItems(appID, SEL_APP_RESOURCES, COL_PRT_RESOURCES_ID,
				COL_PRT_RESOURCES_SRC);
	}

	private void doLoadAppLangs(String appID) {
		ResultSet rsLangs = dbUtility.getSingleParamSQL(appID, SEL_APP_LANGS);
		try {
			while (rsLangs.next()) {
				if (appLangs == null) {
					appLangs = new HashMap<String,LangSource>();
				}
				LangSource newLang = new LangSource();
				newLang.setName(rsLangs.getString(COL_PRT_LANG_NAME));
				newLang.setSourceFileName(rsLangs.getString(COL_PRT_LANG_SRC));
				newLang.setTargetFileName(rsLangs
						.getString(COL_PRT_LANG_TARGET));
				appLangs.put(newLang.getName(), newLang);
			}

		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsLangs);
		}
	}

	private void doLoadAppActions(String appID) {
		ResultSet rsActions = dbUtility.getSingleParamSQL(appID,
				SEL_APP_ACTIONS);
		try {
			while (rsActions.next()) {
				if (appActions == null) {
					appActions = new HashMap<String,PEAction>();
				}
				PEAction newAction = new PEAction();
				newAction.setID(rsActions.getString(COL_PRT_ACTIONS_ACTION_ID));
				newAction.setLabel(rsActions
						.getString(COL_PRT_ACTIONS_ACT_LABEL));
				newAction.setTarget(rsActions
						.getString(COL_PRT_ACTIONS_ACT_TARGET));
				newAction.setParameters(rsActions
						.getString(COL_PRT_ACTIONS_ACT_PARAM));
				newAction.setCommand(rsActions
						.getString(COL_PRT_ACTIONS_ACT_COM));
				appActions.put(newAction.getID(), newAction);
			}

		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsActions);
		}
	}

	private void doLoadToolbars(String appID) {
		if (appActions == null) {
			doLoadAppActions(appID);
		}
		ResultSet rsToolBars = dbUtility.getSingleParamSQL(appID,
				SEL_APP_TOOL_BARS);
		try {
			while (rsToolBars.next()) {
				if (appToolbars == null) {
					appToolbars = new HashMap<String,GUIToolbar>();
				}
				GUIToolbar newToolBar = new GUIToolbar();
				newToolBar.setID(rsToolBars.getString(COL_PRT_TOOLBARS_TLB_ID));
				buildToolbar(appID, newToolBar);
				appToolbars.put(newToolBar.getID(), newToolBar);
			}

		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsToolBars);
		}
	}

	private void buildToolbar(String appID, GUIToolbar toolbar) {
		ResultSet rsToolBar = dbUtility.getDoubleParamSQL(appID, toolbar
				.getID(), SEL_APP_TOOL_BAR);
		try {
			while (rsToolBar.next()) {
				ActionHolderItem newItem = new ActionHolderItem();
				newItem.setID(rsToolBar.getString(COL_PRT_TOOLBARS_ITEM_ID));
				newItem
						.setIcon(rsToolBar
								.getString(COL_PRT_TOOLBARS_ITEM_ICON));
				String itemAction = rsToolBar
						.getString(COL_PRT_TOOLBARS_ACTION_ID);
				if ((itemAction != null) && (appActions != null)) {
					newItem.setAction((GUIAction) appActions.get(itemAction));
				}
				toolbar.addChild(newItem);
			}

		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsToolBar);
		}

	}

	private void doLoadAppMenus(String appID) {
		if (appActions == null) {
			doLoadAppActions(appID);
		}
		ResultSet rsMenuBars = dbUtility.getSingleParamSQL(appID,
				SEL_APP_MENU_BARS);
		try {
			while (rsMenuBars.next()) {
				if (appMenus == null) {
					appMenus = new HashMap<String,MenuItem>();
				}
				MenuItem newMenuBar = new MenuItem();
				newMenuBar.setID(rsMenuBars.getString(COL_PRT_MENU_BAR_ID));
				buildMenu(newMenuBar.getID(), appID, newMenuBar);
				appMenus.put(newMenuBar.getID(), newMenuBar);
			}

		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsMenuBars);
		}
	}

	private void buildMenu(String menuBar, String appID, MenuItem parentItem) {
		ResultSet rsMenus = dbUtility.getTrippleParamSQL(appID, menuBar,
				parentItem.getID(), SEL_APP_MENU_CHILDREN);
		try {
			while (rsMenus.next()) {
				MenuItem newMenu = new MenuItem();
				newMenu.setID(rsMenus.getString(COL_PRT_MENU_MNU_ID));
				newMenu.setIcon(rsMenus.getString(COL_PRT_MENU_MNU_ICON));
				newMenu.setType(rsMenus.getString(COL_PRT_MENU_MNU_TYPE));
				String menuAction = rsMenus.getString(COL_PRT_MENU_ACTION_ID);
				if ((menuAction != null) && (appActions != null)) {
					newMenu.setAction((GUIAction) appActions.get(menuAction));
				}
				parentItem.addChild(newMenu);
				if (dbUtility.isExist(dbUtility.getTrippleParamSQL(appID,
						menuBar, newMenu.getID(), SEL_APP_MENU_CHILDREN))) {
					buildMenu(menuBar, appID, newMenu);
				}
			}

		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsMenus);
		}

	}

	private HashMap<String,String> doLoadItems(String appID, String sql, String idCol,
			String srcCol) {
		ResultSet rsItems = dbUtility.getSingleParamSQL(appID, sql);
		HashMap<String,String> collection = null;
		try {
			while (rsItems.next()) {
				if (collection == null) {
					collection = new HashMap<String,String>();
				}
				String ID = rsItems.getString(idCol);
				String Src = rsItems.getString(srcCol);
				collection.put(ID, Src);
			}

		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsItems);
		}
		return collection;
	}

	public HashMap<String, String> loadLibraries() {
		return appLibs;
	}

	public HashMap<String, Element> loadWindows() {
		return appWindows;
	}

	public HashMap<String, Element> loadPanels() {
		return appPanels;
	}

	public HashMap<String, PEAction> loadActions() {
		return appActions;
	}

	public HashMap<String, MenuItem> loadMenus() {
		return appMenus;
	}

	public HashMap<String, LangSource> loadLanguages() {
		return appLangs;
	}

	public HashMap<String, String> loadResources() {
		return appRes;
	}

	public String getMainClassPackage() {
		return appMainClass;
	}

	@Override
	public String getGroup() {
		return null;
	}

	public String getHomePath() {
		return appHome;
	}

	public String getSourcePath() {
		return appSource;
	}

	public String getClassPath() {
		return appClasses;
	}

	public String getLAF() {
		return appAppearance;
	}

	public HashMap<String, String> loadImages() {
		return appImages;
	}

	public HashMap<String, String> loadSourceImages() {
		return appSourceImages;
	}

	public String getComponentType() {
		return appCompType;
	}

	public String getTargetJarName() {
		return appJarFile.getFileName();
	}

	public String getTargetJarPath() {
		return appJarFile.getFilePath();
	}

	public String getAppManagerClass() {
		return appLink;
	}

	public LogFile getLogFile() {
		return appLog;
	}

	public FileInfo getAppJarFile() {
		return appJarFile;
	}

	public FileInfo getAppSettingsFile() {
		return appSettings;
	}

	public String getAppSettingsSourceType() {
		return SETT_TYPE_XML;
	}

	public String getAppName() {
		return appName;

	}

	public String getAppType() {
		return appType;
	}

	public AppSecurity getAppSecurity() {
		return null;
	}

	public HashMap<String, GUIToolbar> loadToolbars() {
		return appToolbars;
	}

	public String getAppStartupClass() {
		return null;
	}

	public Vector<DeploymentDescriptor> loadDeployments() {
		return null;
	}

	public HashMap<String,LookAndFeelResource> loadLookAndFeels() {
		return null;
	}

	public Element getSplash() {
		return null;
	}

	public boolean compileClasses() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean createJarFile() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean exportLibraries(){return false;}
	public void setVariableMapper(SettingsVariablesMapper mapper){
		
	}

	@Override
	public ProjectBuilder getProjectBuilder() {
		return null;
	}

	public InputStream getSourceStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GUIToolkit getGUIToolkit() {
		return GUIToolkit.swing;
	}

	@Override
	public ApplicationType getType() {
		return ApplicationType.Desktop;
	}

	@Override
	public LayoutType getLayout() {
		return LayoutType.DB;
	}
}
