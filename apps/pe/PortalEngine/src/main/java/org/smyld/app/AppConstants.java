package org.smyld.app;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public interface AppConstants {

	public static final String APP_DEF_SETTINGS_FILE = "InterfaceSettings.xml";

	public static final String ACTION_COM_OPEN_WINDOW = "openWindow";
	public static final String ACTION_COM_RUN_APP = "runApplication";
	public static final String ACTION_COM_ASSIGN_APP = "assingToApplication";
	public static final String ACTION_COM_CLOSE_APP = "closeApplication";

	public static final String SETT_XML_NODE_ROOT = "applicationSettings";
	public static final String SETT_XML_NODE_LANGS = "languages";
	public static final String SETT_XML_NODE_RESS = "resources";
	public static final String SETT_XML_NODE_LANG = "language";
	public static final String SETT_XML_NODE_RES = "resource";
	public static final String SETT_XML_NODE_LOG = "log";
	public static final String SETT_XML_NODE_PATH = "path";
	public static final String SETT_XML_NODE_GROUP = "group";


	public static final String SETT_XML_ATT_NAME = "name";
	public static final String SETT_XML_ATT_SOURCE = "src";
	public static final String SETT_XML_ATT_DEFAULT = "default";
	public static final String SETT_XML_ATT_SRCTYPE = "srctype";
	public static final String SETT_XML_ATT_CLASS = "class";

	public static final String SETT_XML_NODE_LAFS  = "lookandfeels";
	public static final String SETT_XML_NODE_LAF   = "lookandfeel";
	public static final String SETT_XML_NODE_ROLES = "roles";
	public static final String SETT_XML_NODE_ROLE  = "role";

	public static final String SETT_TYPE_XML = "xml";
	public static final String SETT_TYPE_DB = "DB";

	public static final String JAR_FOLDER_IMAGES = "org/smyld/resources/images/";
	public static final String JAR_FOLDER_RESS = "org/smyld/resources/files/";
	public static final String JAR_FOLDER_LANGS = "org/smyld/resources/lang/";
	public static final String JAR_RES_CLASS = "org.smyld.resources.Resource";
	public static final String JAR_RES_IMAGES = "images/";
	public static final String JAR_RES_LANGS = "lang/";
	public static final String JAR_RES_FILES = "files/";

	public static final String OBJ_TYPE_MENU = "menu";
	public static final String OBJ_TYPE_TLB = "toolbar";
	public static final String OBJ_TYPE_PANEL = "panel";

	public static final String MENU_TYPE_CLASSIC = "classic";
	public static final String MENU_TYPE_TREE = "tree";

}
