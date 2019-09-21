package org.smyld.gui.portal.engine.sources;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import org.smyld.app.pe.model.Constants;
import org.smyld.app.pe.model.gui.*;
import org.jdom2.Element;

import org.smyld.db.DBConnection;
import org.smyld.db.DBErrorHandler;
import org.smyld.db.SMYLDDataBaseHandler;
import org.smyld.db.oracle.OracleSqlException;
import org.smyld.util.multilang.LangSource;
import org.smyld.xml.XMLUtil;

public class PortalDBWriter extends SMYLDDataBaseHandler implements
		SQLStatements, Constants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PESwingApplicationReader reader;
	String appID;
	int menuSepNo;
	int toolbarSepNo;

	public PortalDBWriter(DBErrorHandler e, DBConnection conn) {
		super(e, conn);
	}

	public void deleteApplication(String appID) {
		dbUtility.doSingleParamSQL(appID, DEL_APP_PANELS);
		dbUtility.doSingleParamSQL(appID, DEL_APP_WINDOWS);
		dbUtility.doSingleParamSQL(appID, DEL_APP_MENUS);
		dbUtility.doSingleParamSQL(appID, DEL_APP_TOOLBARS);
		dbUtility.doSingleParamSQL(appID, DEL_APP_ACTIONS);
		dbUtility.doSingleParamSQL(appID, DEL_APP_IMAGES);
		dbUtility.doSingleParamSQL(appID, DEL_APP_LIBRARIES);
		dbUtility.doSingleParamSQL(appID, DEL_APP_RESOURCES);
		dbUtility.doSingleParamSQL(appID, DEL_APP_LANGS);
		dbUtility.doSingleParamSQL(appID, DEL_APP);
	}

	public void generateApplication(PESwingApplicationReader reader, String appID) {
		deleteApplication(appID);
		this.reader = reader;
		this.appID = appID;
		// Code for generating the application
		insertNewApplication();
		insertNewAppWindows();
		insertNewAppPanels();
		insertNewAppActions();
		insertNewAppMenus();
		insertNewAppImages();
		insertNewAppLangs();
		insertNewAppLibraries();
		insertNewAppResources();
		insertNewAppToolbars();
	}

	private boolean insertNewApplication() {
		PreparedStatement st = null;
		try {
			st = dbConnection.prepareStatement(INS_NEW_APP);
			st.setString(1, appID);
			st.setString(2, reader.getHomePath());
			st.setString(3, reader.getAppName());
			st.setString(4, reader.getAppType());
			st.setString(5, reader.getSourcePath());
			st.setString(6, reader.getClassPath());
			st.setString(7, reader.getComponentType());
			st.setString(8, reader.getLAF());
			st.setString(9, reader.getMainClassPackage());
			st.setString(10, reader.getAppSettingsFile().getFileName());
			st.setString(11, reader.getAppSettingsFile().getFilePath());
			st.setString(12, reader.getTargetJarName());
			st.setString(13, reader.getTargetJarPath());
			st.setString(14, reader.getLogFile().getFileName());
			st.setString(15, reader.getLogFile().getFilePath());
			return (st.executeUpdate() > 0);
		} catch (SQLException e) {
			handleDBError(e);
		} finally {
			dbUtility.closeCursor(st);
		}
		return false;
	}

	private void insertNewAppActions() {
		HashMap<String,PEAction> actions = reader.loadActions();
		for (PEAction action : actions.values()) {
			insertNewAction(action);
		}
	}

	private void insertNewAction(PEAction newAction) {
		PreparedStatement st = null;
		try {
			st = dbConnection.prepareStatement(INS_NEW_ACTION);
			st.setString(1, appID);
			st.setString(2, newAction.getID());
			st.setString(3, newAction.getCommand());
			st.setString(4, newAction.getLabel());
			st.setString(5, newAction.getParameters());
			st.setString(6, newAction.getTarget());
			st.executeUpdate();
		} catch (SQLException e) {
			handleDBError(e);
		} finally {
			dbUtility.closeCursor(st);
		}
	}

	private void insertNewAppMenus() {
		HashMap<String,MenuItem> menus = reader.loadMenus();
		for (MenuItem item : menus.values()) {
			insertNewMenu(item.getID(), item.getID(), item, 1);
		}
	}

	private void insertNewAppToolbars() {
		HashMap<String, GUIToolbar> toolbars = reader.loadToolbars();
		for (GUIToolbar curToolbar : toolbars.values()) {
			insertToolbar(curToolbar);
		}
	}

	private void insertToolbar(GUIToolbar newToolbar) {
		PreparedStatement st = null;
		try {
			int order = 1;
			for (GUIComponent newGUIItem : newToolbar.getChildren()) {
				ActionHolderItem newItem = (ActionHolderItem)newGUIItem;
				int itemType = MENU_TYPE_ITEM;
				if ((newItem.getType() != null)
						&& (newItem.getType()
								.equals(TAG_ATT_MENU_TYPE_SEPARATOR))) {
					itemType = MENU_TYPE_SEP;
					toolbarSepNo++;
				}

				st = dbConnection.prepareStatement(INS_NEW_TOOLBAR);
				st.setString(1, appID);
				st.setString(2, newToolbar.getID());
				if (itemType == MENU_TYPE_SEP) {
					st.setString(3, "sep_" + Integer.toString(toolbarSepNo));
				} else {
					st.setString(3, newItem.getID());
				}
				if (newItem.getAction() != null) {
					st.setString(4, newItem.getAction().getID());
				} else {
					st.setString(4, null);
				}
				st.setString(5, newItem.getIcon());
				st.setInt(6, order++);
				st.setInt(7, itemType);
				st.executeUpdate();
			}
		} catch (SQLException e) {
			handleDBError(e);
		} finally {
			dbUtility.closeCursor(st);
		}

	}

	private void insertNewMenu(String menuBar, String parentID,
			MenuItem newMenu, int order) {
		PreparedStatement st = null;
		try {
			int menuType = MENU_TYPE_ITEM;
			if ((newMenu.getType() != null)
					&& (newMenu.getType().equals(TAG_ATT_MENU_TYPE_SEPARATOR))) {
				menuType = MENU_TYPE_SEP;
				menuSepNo++;
			}

			st = dbConnection.prepareStatement(INS_NEW_MENU);
			st.setString(1, appID);
			st.setString(2, menuBar);
			if (menuType == MENU_TYPE_SEP) {
				st.setString(3, "sep_" + Integer.toString(menuSepNo));
			} else {
				st.setString(3, newMenu.getID());
			}
			if (!menuBar.equals(newMenu.getID())) {
				st.setString(4, parentID);
			} else {
				st.setString(4, null);
			}
			st.setString(5, newMenu.getIcon());
			if (newMenu.getAction() != null) {
				st.setString(6, newMenu.getAction().getID());
			} else {
				st.setString(6, "");
			}
			st.setInt(7, menuType);
			st.setInt(8, order);
			if ((st.executeUpdate() > 0) && (newMenu.hasChildren())) {
				int orderCounter = 1;
				for (GUIComponent curGUIComp : newMenu.getChildren()) {
					MenuItem child = (MenuItem) curGUIComp;
					insertNewMenu(menuBar, newMenu.getID(), child, orderCounter);
					orderCounter++;
				}
			}
		} catch (SQLException e) {
			handleDBError(e);
		} finally {
			dbUtility.closeCursor(st);
		}
	}

	private void insertNewAppWindows() {
		HashMap<String,Element> windows = reader.loadWindows();
		insertNewAppElements(windows, INS_NEW_APP_WINDOW);
	}

	private void insertNewAppPanels() {
		HashMap<String,Element> windows = reader.loadPanels();
		insertNewAppElements(windows, INS_NEW_APP_PANEL);
	}

	private void insertNewAppElements(HashMap<String,Element> elements, String sqlSt) {
		if ((elements != null) && (elements.size() > 0)) {
			for (String newElID : elements.keySet()) {
				Element newEl = elements.get(newElID);
				insertNewElement(newElID, newEl, sqlSt);
			}
		}

	}

	private boolean insertNewElement(String newElID, Element newEl, String sqlSt) {
		PreparedStatement st = null;
		try {
			st = dbConnection.prepareStatement(sqlSt);
			st.setString(1, appID);
			st.setString(2, newElID);
			st.setString(3, XMLUtil.getElementStringValue(newEl));

			return (st.executeUpdate() > 0);
		} catch (Exception e) {
			handleDBError(e);
		} finally {
			dbUtility.closeCursor(st);
		}
		return false;
	}

	private void insertNewAppLibraries() {
		HashMap<String,String> libs = reader.loadLibraries();
		insertNewElements(libs, INS_NEW_LIBRARY, INS_NEW_APP_LIBRARY);
	}

	private void insertNewAppImages() {
		HashMap<String,String> imgs = reader.loadSourceImages();
		insertNewElements(imgs, INS_NEW_IMG, INS_NEW_APP_IMG);
	}

	private void insertNewAppResources() {
		HashMap<String,String> res = reader.loadResources();
		insertNewElements(res, INS_NEW_RES, INS_NEW_APP_RES);
	}

	private void insertNewAppLangs() {
		HashMap<String,LangSource> langs = reader.loadLanguages();
		if ((langs != null) && (langs.size() > 0)) {
			for (String langName : langs.keySet()) {
				LangSource lang = langs.get(langName);
				PreparedStatement st = null;
				try {
					st = dbConnection.prepareStatement(INS_NEW_LANG);
					st.setString(1, lang.getName());
					st.setString(2, lang.getSourceFileName());
					st.setString(3, lang.getTargetFileName());
					if (st.executeUpdate() > 0) {
						dbUtility.doTrippleParamUpdateSQL(appID, langName, lang
								.getSourceFileName(), INS_NEW_APP_LANG);
					}
				} catch (Exception e) {
					if (isUniqueError(e)) {
						dbUtility.doTrippleParamUpdateSQL(appID, langName, lang
								.getSourceFileName(), INS_NEW_APP_LANG);
					} else {
						handleDBError(e);
					}
				} finally {
					dbUtility.closeCursor(st);
				}

			}
		}
	}

	private void insertNewElements(HashMap<String,String> items, String mainSql, String linkSql) {
		if ((items != null) && (items.size() > 0)) {
			for (String newElID : items.keySet()) {
				String newExtra = items.get(newElID);
				insertNewElement(newElID, newExtra, mainSql, linkSql);
			}
		}

	}

	private boolean insertNewElement(String newElID, String newEl,
			String mainSql, String linkSql) {
		PreparedStatement st = null;
		try {
			st = dbConnection.prepareStatement(mainSql);
			st.setString(1, newElID);
			st.setString(2, newEl);
			if (st.executeUpdate() > 0) {
				dbUtility.doDoubleParamSQL(appID, newElID, linkSql);
			}
		} catch (Exception e) {
			if (!handleExistentRecords(e, newElID, linkSql)) {
				handleDBError(e);
			}
		} finally {
			dbUtility.closeCursor(st);
		}
		return false;
	}

	private boolean handleExistentRecords(Exception e, String ID, String linkSql) {
		if (isUniqueError(e)) {
			dbUtility.doDoubleParamSQL(appID, ID, linkSql);
			return true;
		}
		return false;
	}

	private boolean isUniqueError(Exception e) {
		if (e instanceof SQLException) {
			OracleSqlException exBody = new OracleSqlException(e);
			return (exBody.getErrorNumber() == DB_ERR_UNIQUE_CONSTRAINT);
		}
		return false;
	}
}
