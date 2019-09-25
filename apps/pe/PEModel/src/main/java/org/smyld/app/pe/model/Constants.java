package org.smyld.app.pe.model;


public interface Constants{
	public static final String PORTAL_ENIGINE_NAME = "SMYLD GUI Portal Engine";
	public static final String PORTAL_ENIGINE_VERSION = "1.00.002";

	// Settings constants
	public static final String RUN_SYS_PROPERTY_LINK_CLASS = "gui.main.class";
	public static final String RUN_SYS_PROPERTY_CONF_FILE = "configFile";
	public static final String RUN_ARG_SETTING_FILE = "-setting";
	public static final String RUN_ARG_APP_FILE     = "-app";
	public static final String SET_XML_NODE_LOG = "log";
	public static final String SET_XML_NODE_COMPILE = "compile";
	public static final String SET_XML_NODE_APPLICATION = "application";
	public static final String SET_XML_NODE_SOURCE = "source";

	public static final int MENU_AS_BAR = 1;
	public static final int MENU_AS_TREE = 2;
	public static final int MENU_AS_POPUP = 4;

	// main Application constants
	public static final String APP_MAIN_CLASS_NAME = "PEGuiMainClass";
	public static final String APP_MAIN_CLASS_PACKAGE = "org.smyld.app.pe.active";
	public static final String APP_MANAGER_INTERFACE_NAME = "ApplicationManager";
	public static final String APP_INSTANCE_MAIN_WINDOW = "appMainWindow";
	/*
	public static final String APP_INSTANCE_APP_MANAGER = "appManager";
	public static final String APP_INSTANCE_MNU_FACTORY = "appMenuFactory";
	public static final String APP_INSTANCE_WIN_FACTORY = "appWindowsFactory";
	public static final String APP_INSTANCE_ACT_FACTORY = "appActionsFactory";
	public static final String APP_INSTANCE_TLB_FACTORY = "appToolbarsFactory";
	public static final String APP_INSTANCE_PNL_FACTORY = "appPanelsFactory";
	*/
	public static final String APP_INSTANCE_APP_MANAGER = "childManager";
	public static final String APP_INSTANCE_MNU_FACTORY = "childMenuFactory";
	public static final String APP_INSTANCE_WIN_FACTORY = "childWindowsFactory";
	public static final String APP_INSTANCE_ACT_FACTORY = "childActionsFactory";
	public static final String APP_INSTANCE_TLB_FACTORY = "childToolbarsFactory";
	public static final String APP_INSTANCE_PNL_FACTORY = "childPanelsFactory";
	public static final String ACT_FCT_INSTANCE         = "appActionsFactory";


	public static final String APP_METH_APP_INIT = "applicationInit";
	public static final String APP_METH_INTERFACE_CREATED = "interfaceCreated";
	public static final String APP_METH_APP_EXIT = "applicationExit";
	public static final String APP_METH_APP_GUI_ACTION = "processGUIAction";
	public static final String APP_METH_PARM_ACTION = "incomingAction";
	public static final String APP_METH_GET_IMAGE = "getImage";
	public static final String APP_METH_TRANSLATE = "translate";
	public static final String APP_METH_ACTIVATE_TRANSL = "activateTranslator";
	public static final String APP_METH_ACTIVATE_RESOURCE = "activateResource";
	public static final String APP_METH_SET_WIN_FACTORY = "setWindowsFactory";
	public static final String APP_METH_SET_APP_MANAGER = "setApplicationManager";
	public static final String APP_METH_SET_APP_ACT_FCT = "setAppActionsFactory";
	public static final String APP_METH_SET_APP_TLB_FCT = "setAppToolbarsFactory";
	public static final String APP_METH_SET_APP_MNU_FCT = "setAppMenuFactory";
	public static final String APP_METH_SET_APP_PNL_FCT = "setAppPanelsFactory";

	public static final String APP_METH_GET_APP_ORIENT = "getOrientation";

	public static final String APP_METH_ACTIV_APP_FRM_XML = "activateApplicationFrmXML";
	public static final String APP_METH_ACTIV_APP_FRM_DB = "activateApplicationFrmDB";

	public static final String WIN_INSTANCE_LISTENER = "windowListener";
	public static final String WIN_METH_ACTIVE_WIN_HDL = "activeWindowHandle";
	public static final String WIN_METH_OPEN_ACTION = "openAction";
	public static final String PNL_METH_ACTIVE_PNL_HDL = "activePanelHandle";

	public static final String ACT_FCTR_CLASS_NAME = "ActionFactory";
	public static final String ACT_FCTR_METH_ACTIONS_CREATE = "creatActions";
	public static final String ACT_FCTR_METH_PARM_ACT_HDL = "srcActionHandler";
	public static final String ACT_FCTR_INSTANCE_ACTIONS = "pactions";
	public static final String ACT_FCTR_INSTANCE_ACTION_HDL = "actionHandler";

	public static final String TLB_FCTR_CLASS_NAME = "ToolbarFactory";
	public static final String TLB_FCTR_METH_BUILD_TLBS = "buildToolbars";
	public static final String TLB_FCTR_METH_PARM_ACT_HDL = "srcActionHandler";
	public static final String TLB_FCTR_INSTANCE_ACTION_HDL = ACT_FCT_INSTANCE
			+ ".getHandler()";

	public static final String MENU_FCTR_CLASS_NAME = "MenuFactory";
	public static final String MENU_FCTR_METH_GET_MENU = "getMenu";
	public static final String MENU_FCTR_METH_PRM_MNU_METH = "menuMethName";
	public static final String MENU_FCTR_METH_ACTIONS_CREATE = "creatActions";
	public static final String MENU_FCTR_METH_PARM_ACT_HDL = "srcActionHandler";
	public static final String MENU_FCTR_INSTANCE_ACTIONS = "actions";

	public static final String MENU_FCTR_INSTANCE_ACTION_HDL = ACT_FCT_INSTANCE
			+ ".getHandler()";

	public static final String PNL_FCTR_METH_GET_PANEL = "getPanel";
	public static final String PNL_FCTR_CLASS_NAME = "PanelsFactory";

	public static final String PNL_CLASS_METH_GET_MAIN_COMP = "getMainComponent";

	public static final String WINDS_FCTR_CLASS_NAME = "WindowsFactory";
	public static final String WINDS_FCTR_METH_OPEN_WIN = "openWindow";
	public static final String WINDS_FCTR_METH_SET_MDI = "setActiveMDI";
	public static final String WINDS_FCTR_INST_ACTV_MDI = "activeMDIWindow";
	public static final String WINDS_FCTR_METH_ADD_WINDOW = "addWindow";

	public static final String APP_WINDS_FCTR_CLASS_NAME = "AppWindowsFactory";

	public static final String APP_WINDS_FCTR_FP_CLASS_NAME = "org.smyld.app.AppWindowsFactory";
	public static final String APP_MANAGER_CLASS_NAME = "AppManager";
	public static final String APP_MANAGER_FP_CLASS_NAME = "org.smyld.app.AppManager";

	// public static final String APP_ACTIONS_FCTR_CLASS_NAME =
	// "AppActionFactory";
	// public static final String APP_TOOLBARS_FCTR_CLASS_NAME =
	// "AppToolbarsFactory";
	// public static final String APP_ACTIONS_FCTR_FP_CLASS_NAME =
	// "org.smyld.app.AppActionFactory";
	// public static final String APP_TOOLBARS_FCTR_FP_CLASS_NAME =
	// "org.smyld.app.AppToolbarsFactory";

	public static final String SMYLD_MDI_METH_ADD_INT_FRM = "addInternalFrame";
	public static final String SMYLD_MDI_METH_SET_COLOR = "setForeground";
	public static final String SMYLD_MDI_METH_SET_BG_COLOR = "setBGColor";

	public static final String TAG_NAME_BUILD = "build";
	public static final String TAG_NAME_TOOLkit = "toolkit";
	public static final String TAG_NAME_ACTIONS = "actions";
	public static final String TAG_NAME_ACTION = "action";
	public static final String TAG_NAME_WINDOWS = "windows";
	public static final String TAG_NAME_WINDOW = "window";
	public static final String TAG_NAME_PANELS = "panels";
	public static final String TAG_NAME_MENUS = "menus";
	public static final String TAG_NAME_MENUBAR = "menubar";
	public static final String TAG_NAME_MENU = "menu";

	public static final String TAG_NAME_HOME = "home";
	public static final String TAG_NAME_SOURCE = "source";
	public static final String TAG_NAME_CLASSES = "classes";
	public static final String TAG_NAME_MAINCLASS = "mainClass";
	public static final String TAG_NAME_GROUP = "group";
	public static final String TAG_NAME_COMP_TYPE = "componenttype";
	public static final String TAG_NAME_LIBS = "libraries";
	public static final String TAG_NAME_LIB = "library";
	public static final String TAG_NAME_BODY = "body";
	public static final String TAG_NAME_APPEARANCE = "appearance";
	public static final String TAG_NAME_TARGET_JAR = "targetjar";
	public static final String TAG_NAME_TARGET_JAR_NAME = "name";
	public static final String TAG_NAME_TARGET_JAR_PATH = "path";
	public static final String TAG_NAME_LINK = "link";
	public static final String TAG_NAME_APPLICATION = "application";
	public static final String TAG_NAME_APP_MANAGER = "appmanager";

	public static final String TAG_NAME_SET_FILE = "settingsfile";
	public static final String TAG_NAME_IMAGES = "images";
	public static final String TAG_NAME_IMAGE = "image";
	public static final String TAG_NAME_DIR = "dir";
	public static final String TAG_NAME_TLBS = "toolbars";
	public static final String TAG_NAME_TLB = "toolbar";
	public static final String TAG_NAME_STATUS_BAR = "statusbar";
	public static final String TAG_NAME_COL = "col";
	public static final String TAG_NAME_HEADER = "header";
	public static final String TAG_NAME_DEPLOYMENTS = "deployments";
	public static final String TAG_NAME_DEPLOYMENT = "deployment";
	public static final String TAG_NAME_TITLE = "title";
	public static final String TAG_NAME_TOOLTIP = "tooltip";
	public static final String TAG_NAME_DESC = "desc";
	public static final String TAG_NAME_ICON = "icon";
	public static final String TAG_NAME_VENDOR = "vendor";
	public static final String TAG_NAME_CODE_BASE = "codebase";
	public static final String TAG_NAME_SHORTCUT = "shortcut";
	public static final String TAG_NAME_OFF_ALLOWED = "offline-allowed";
	public static final String TAG_NAME_SERVER = "server";
	public static final String TAG_NAME_PATH = "path";
	public static final String TAG_NAME_LAUNCH_PAGE = "launchpage";
	public static final String TAG_NAME_NAME = "name";
	public static final String TAG_NAME_HEADLINE = "headline";
	public static final String TAG_NAME_SECURITY = "security";
	public static final String TAG_NAME_KEY = "key";
	public static final String TAG_NAME_OWNER_NAME = "ownername";
	public static final String TAG_NAME_ORAGNIZATION = "organization";
	public static final String TAG_NAME_ORG_UNIT = "organizationunit";
	public static final String TAG_NAME_CITY = "city";
	public static final String TAG_NAME_STATE = "state";
	public static final String TAG_NAME_COUNTRY = "country";
	public static final String TAG_NAME_STORE = "store";
	public static final String TAG_NAME_PERMISSION = "permission";
	public static final String TAG_NAME_HOST = "host";
	public static final String TAG_NAME_PORT = "port";
	public static final String TAG_NAME_DBNAME = "dbname";
	public static final String TAG_NAME_USER_NAME = "username";
	public static final String TAG_NAME_USER_PASS = "userpass";
	public static final String TAG_NAME_VALUES = "values";
	public static final String TAG_NAME_VALUE = "value";

	public static final String TAG_COMP_TYPE_SWING = "swing";
	public static final String TAG_COMP_TYPE_AWT = "awt";
	public static final String TAG_COMP_TYPE_HTML = "html";

	public static final String TAG_COMP_CONT_SPLIT = "split";
	public static final String TAG_COMP_CONT_SPLIT_SIDE = "side";
	public static final String TAG_COMP_CONT_SCRLP = "scrollpane";
	public static final String TAG_COMP_CONT_PANEL = "panel";
	public static final String TAG_COMP_CONT_DESKTOP_PANE = "desktoppane";
	public static final String TAG_COMP_CONT_DOCKABLE_DESKTOP = "dockabledesktop";
	public static final String TAG_COMP_CONT_TABBED_PANEL = "tabbedpanel";
	public static final String TAG_COMP_CONT_TAB = "tab";

	public static final String TAG_COMP_TEXT_FIELD     = "textField";
	public static final String TAG_COMP_PASSWORD       = "password";
	public static final String TAG_COMP_TEXT_AREA      = "textArea";
	public static final String TAG_COMP_TABLE          = "table";
	public static final String TAG_COMP_TREE           = "tree";
	public static final String TAG_COMP_LIST_FIELD     = "listField";
	public static final String TAG_COMP_COMBO          = "comboField";
	public static final String TAG_COMP_BUTTON         = "button";
	public static final String TAG_COMP_CHECKBOX       = "checkbox";
	public static final String TAG_COMP_RADIOBUTTON    = "radiobutton";
	public static final String TAG_COMP_LABEL          = "label";
	public static final String TAG_COMP_PROGRESS_BAR   = "progress";
	public static final String TAG_COMP_INTERNET_PANEL = "internet";
	public static final String TAG_COMP_DATE           = "date";

	public static final String TAG_COMP_ATT_ON_STARTUP = "onstartup";
	public static final String TAG_COMP_ATT_NAME = "name";
	public static final String TAG_COMP_ATT_ID = "id";
	public static final String TAG_COMP_ATT_PACKAGE = "package";
	public static final String TAG_COMP_ATT_SCOPE = "scope";
	public static final String TAG_COMP_ATT_DEFAULT = "default";
	public static final String TAG_COMP_ATT_ROW = "row";
	public static final String TAG_COMP_ATT_COL = "col";
	public static final String TAG_COMP_ATT_ROWS = "rows";
	public static final String TAG_COMP_ATT_COLS = "cols";
	public static final String TAG_COMP_ATT_VERTICAL_GAP = "vgap";
	public static final String TAG_COMP_ATT_HORIZONTAL_GAP = "hgap";
	public static final String TAG_COMP_ATT_STARTUP = "startup";
	public static final String TAG_COMP_ATT_TIME = "time";
	public static final String TAG_COMP_ATT_BODY = "body";

	public static final String TAG_COMP_ATT_WIDTH = "width";
	public static final String TAG_COMP_ATT_HEIGHT = "height";
	public static final String TAG_COMP_ATT_COMP_WIDTH = "compwidth";
	public static final String TAG_COMP_ATT_LABEL = "label";
	public static final String TAG_COMP_ATT_SELECT = "selected";
	public static final String TAG_COMP_ATT_ON_CLK = "onclick";
	public static final String TAG_COMP_ATT_ON_FOC_LOST = "onlostfocus";
	public static final String TAG_COMP_ATT_ON_FOC_GOT = "ongotfocus";
	public static final String TAG_COMP_ATT_ON_MOUS_CLK = "onmouseclick";
	public static final String TAG_COMP_ATT_ON_MOUS_R_CLK = "onmouserightclick";
	public static final String TAG_COMP_ATT_ON_MOUS_DBL_CLK = "onmousedoubleclick";
	public static final String TAG_COMP_ATT_ON_CLOSE = "onclose";
	public static final String TAG_COMP_ATT_ON_OPEN = "onopen";
	public static final String TAG_COMP_ATT_ON_WIN_FOC_GOT = "onwindowgotfocus";
	public static final String TAG_COMP_ATT_ON_ACTIVE = "onactive";
	public static final String TAG_COMP_ATT_ON_DEACTIVE = "ondeactive";
	public static final String TAG_COMP_ATT_ON_ICONIFY = "oniconify";
	public static final String TAG_COMP_ATT_ON_DEICONIFY = "ondeiconify";
	public static final String TAG_COMP_ATT_ON_CHANGE = "onchange";

	public static final String TAG_COMP_ATT_POPUP         = "popup";
	public static final String TAG_COMP_ATT_ORIENT        = "orient";
	public static final String TAG_COMP_ATT_POSITION      = "position";
	public static final String TAG_COMP_ATT_LAYOUT        = "layout";
	public static final String TAG_COMP_ATT_TITLE         = "title";
	public static final String TAG_COMP_ATT_TYPE          = "type";
	public static final String TAG_COMP_ATT_TARGET        = "target";
	public static final String TAG_COMP_ATT_PARAM         = "param";
	public static final String TAG_COMP_ATT_PATH          = "path";
	public static final String TAG_COMP_ATT_LINK_LISTNR   = "linklistenerto";
	public static final String TAG_COMP_ATT_SHOW_JAR_DUP  = "jarduplicate";
	public static final String TAG_COMP_ATT_ACTION        = "action";
	public static final String TAG_COMP_ATT_ALIGN         = "align";
	public static final String TAG_COMP_ATT_COM           = "command";
	public static final String TAG_COMP_ATT_SOURCE        = "src";
	public static final String TAG_COMP_ATT_SOURCE_TYPE   = "srctype";
	public static final String TAG_COMP_ATT_TARGET_NAME   = "targetName";
	public static final String TAG_COMP_ATT_ICON          = "icon";
	public static final String TAG_COMP_ATT_RESIZABLE     = "resize";
	public static final String TAG_ATT_LINK_LISTNR_WINDOW = "window";
	public static final String TAG_ATT_LINK_LISTNR_PANEL  = "panel";
	public static final String TAG_COMP_ATT_BG_COLOR      = "bgcolor";
	public static final String TAG_COMP_ATT_COLOR         = "color";
	public static final String TAG_COMP_ATT_BORDER_TYPE   = "bordertype";
	public static final String TAG_COMP_ATT_BORDER_WIDTH  = "borderwidth";
	public static final String TAG_COMP_ATT_BORDER_TITLE  = "bordertitle";
	public static final String TAG_COMP_ATT_LINK_HANDLER  = "linkhandler";
	public static final String TAG_COMP_ATT_LBL_POSITION  = "labelposition";
	public static final String TAG_COMP_ATT_ENABLE        = "enable";
	public static final String TAG_COMP_ATT_SCROLLABLE    = "scrollable";
	public static final String TAG_COMP_ATT_TOOLTIP       = "tooltip";
	public static final String TAG_COMP_ATT_ACCELERATOR   = "accelerator";
	public static final String TAG_COMP_ATT_OBJECT        = "object";
	public static final String TAG_COMP_ATT_TOP_MARGIN    = "topmargin";
	public static final String TAG_COMP_ATT_TITLE_TYPE    = "titletype";
	public static final String TAG_COMP_ATT_FIELD_WIDTH   = "fieldwidth";
	public static final String TAG_COMP_ATT_ROLE          = "role";
	public static final String TAG_COMP_ATT_SHOW_ROLE     = "showrole";

	public static final String TAG_ATT_METH_ACTION = "action.";

	public static final String TAG_ATT_BOOLEAN_TRUE = "true";
	public static final String TAG_ATT_BOOLEAN_FLASE = "false";

	public static final String TAG_ATT_BORDER_TYPE_RAISED   = "raised";
	public static final String TAG_ATT_BORDER_TYPE_LINE     = "line";
	public static final String TAG_ATT_BORDER_TYPE_LOWERED  = "lowered";
	public static final String TAG_ATT_BORDER_TYPE_ETCHED   = "etched";
	public static final String TAG_ATT_BORDER_TYPE_EMPTY    = "empty";
	public static final String TAG_ATT_BORDER_TYPE_ETCH_LOW = "etchedlowered";
	public static final String TAG_ATT_BORDER_TYPE_ETCH_RAS = "etchedraised";

	public static final String TAG_ATT_SHOW_JAR_DUP_YES = "true";
	public static final String TAG_ATT_SHOW_JAR_DUP_NO = "false";

	public static final String TAG_ATT_SCOPE_PUBLIC = "public";
	public static final String TAG_ATT_SCOPE_PRIVATE = "private";
	public static final String TAG_ATT_SCOPE_PROTECTED = "protected";

	public static final String TAG_ATT_TITLE_TYPE_ICON = "icon";
	public static final String TAG_ATT_TITLE_TYPE_TEXT = "text";
	public static final String TAG_ATT_TITLE_TYPE_IC_TXT = "icontext";

	public static final String TAG_ATT_LAYOUT_BORDER = "border";
	public static final String TAG_ATT_LAYOUT_XY     = "xy";
	public static final String TAG_ATT_LAYOUT_RC     = "rc";
	public static final String TAG_ATT_LAYOUT_GRID   = "grid";
	public static final String TAG_ATT_LAYOUT_FLOW   = "flow";
	public static final String TAG_ATT_LAYOUT_SPRING = "spring";

	public static final String TAG_ATT_ORIENT_VERTICAL = "vertical";
	public static final String TAG_ATT_ORIENT_HORIZONTAL = "horizontal";

	public static final String LAYOUT_ATT_SUPPORT_ADD = TAG_ATT_LAYOUT_GRID
			+ "," + TAG_ATT_LAYOUT_FLOW + "," + TAG_ATT_LAYOUT_SPRING;

	public static final String TAG_ATT_POS_LEFT = "left";
	public static final String TAG_ATT_POS_RIGHT = "right";
	public static final String TAG_ATT_POS_UP = "top";
	public static final String TAG_ATT_POS_DOWN = "buttom";
	public static final String TAG_ATT_POS_CENTER = "center";
	public static final String TAG_ATT_POS_NORTH = "north";
	public static final String TAG_ATT_POS_SOUTH = "south";
	public static final String TAG_ATT_POS_DOCK_SIDE = "side";
	public static final String TAG_ATT_POS_DOCK_DOWN = "down";
	public static final String TAG_ATT_POS_DOCK_MAIN = "main";
	public static final String TAG_ATT_DESKTOP = "desktop";
	public static final String TAG_ATT_FIRESONLINE = "firesonline";
	public static final String TAG_ATT_TITLE = "title";
	public static final String TAG_ATT_TYPE = "type";
	public static final String TAG_ATT_NAME = "name";
	public static final String TAG_ATT_PASS = "password";
	public static final String TAG_ATT_ID = "id";
	public static final String TAG_ATT_CLASS = "class";
	public static final String TAG_ATT_DOCKABLE = "dockable";
	public static final String TAG_ATT_DRAGABLE = "dragable";

	public static final String TAG_ATT_PANEL_TEMP_DOCKABLE = "TemplateDockable";

	public static final String TAG_ATT_DEPLOY_TYPE_JNLP = "jnlp";

	public static final String TAG_ATT_WINDOW_TYPE_MDI = "mdi";
	public static final String TAG_ATT_WINDOW_TYPE_NORMAL = "normal";
	public static final String TAG_ATT_WINDOW_TYPE_INTERNAL = "internal";
	public static final String TAG_ATT_WINDOW_TYPE_FRAME = "frame";
	public static final String TAG_ATT_WINDOW_TYPE_DIALOG = "dialog";
	public static final String TAG_ATT_WINDOW_TYPE_STARTUP = "startup";

	public static final String TAG_ATT_MENUBAR_TYPE_CLASSIC = "classic";
	public static final String TAG_ATT_MENUBAR_TYPE_TREE = "tree";

	public static final String MENUBAR_TYPE_METHOD_SUFIX_CLASSIC = "Bar";
	public static final String MENUBAR_TYPE_METHOD_SUFIX_TREE = "Tree";

	public static final String IMG_SRC_TYPE_DIR = "1";
	public static final String IMG_SRC_TYPE_FILE = "0";
	public static final int MENU_TYPE_ITEM   = 1;
	public static final int MENU_TYPE_SEP    = 2;
	public static final int MENU_TYPE_COMBO  = 3;
	public static final int MENU_TYPE_CHKB   = 4;
	

	public static final String TAG_ATT_MENU_TYPE_SEPARATOR = "separator";
	public static final String TAG_ATT_MENU_TYPE_GROUP     = "group";
	public static final String TAG_ATT_MENU_TYPE_RADIO     = "radio";
	public static final String TAG_ATT_MENU_TYPE_CHECK     = "check";
	public static final String TAG_ATT_MENU_TYPE_COMBO     = "combo";
	public static final String TAG_ATT_MENU_TYPE_LABEL     = "label";

	public static final String TAG_ATT_DOCKABLE_YES = "true";
	public static final String TAG_ATT_DOCKABLE_NO = "false";

	public static final String TAG_ATT_DRAGABLE_INTERNAL = "internal";
	public static final String TAG_ATT_DRAGABLE_EXTERNAL = "external";

	public static final String TAG_ATT_LINK_HANDLER_SELF = "self";

	public static final String PACK_NAME_SMYLD_GUI = "org.smyld.gui";
	public static final String CLASS_METHOD_INIT = "init";

	public static final String CLASS_INTERFACE = "windowInterface";
	public static final String CLASS_INTERFACE_SUFFIX = "Listener";

	// Event classes
	public static final String CLASS_NAME_FP_ACT_LSTN    = "java.awt.event.ActionListener";
	public static final String CLASS_NAME_FP_ACT_EVNT    = "java.awt.event.ActionEvent";
	public static final String CLASS_NAME_FP_FOC_LSTN    = "java.awt.event.FocusListener";
	public static final String CLASS_NAME_FP_FOC_ADPT    = "java.awt.event.FocusAdapter";
	public static final String CLASS_NAME_FP_FOC_EVNT    = "java.awt.event.FocusEvent";
	public static final String CLASS_NAME_FP_MOUS_ADPT   = "java.awt.event.MouseAdapter";
	public static final String CLASS_NAME_FP_MOUS_EVNT   = "java.awt.event.MouseEvent";
	public static final String CLASS_NAME_FP_WIN_ADPT    = "java.awt.event.WindowAdapter";
	public static final String CLASS_NAME_FP_WIN_EVNT    = "java.awt.event.WindowEvent";
	public static final String CLASS_NAME_FP_ITM_LISTN   = "java.awt.event.ItemListener";
	public static final String CLASS_NAME_FP_ITM_EVNT    = "java.awt.event.ItemEvent";
	public static final String CLASS_NAME_FP_IF_ADPT     = "javax.swing.event.InternalFrameAdapter";
	public static final String CLASS_NAME_FP_IF_EVNT     = "javax.swing.event.InternalFrameEvent";
	public static final String CLASS_NAME_FP_SWING_COMP  = "javax.swing.JComponent";
	public static final String CLASS_NAME_SWING_COMP     = "JComponent";
	public static final String CLASS_NAME_FP_CHANGE_LIST = "org.smyld.gui.event.SMYLDChangeListener";
	public static final String CLASS_NAME_CHANGE_LIST    = "SMYLDChangeEvent";

	public static final String CLASS_NAME_FP_WIND_LIST = "org.smyld.app.event.WindowListener";
	public static final String CLASS_NAME_WIND_LIST = "WindowListener";
	public static final String CLASS_NAME_FP_APP_WIN_FCT = "org.smyld.app.AppWindowsFactory";
	public static final String CLASS_NAME_APP_WIN_FCT = "AppWindowsFactory";
	public static final String CLASS_NAME_FP_APP_ACT_FCT = "org.smyld.app.AppActionFactory";
	public static final String CLASS_NAME_APP_ACT_FCT = "AppActionFactory";
	public static final String CLASS_NAME_FP_APP_MNU_FCT = "org.smyld.app.AppMenuFactory";
	public static final String CLASS_NAME_APP_MNU_FCT = "AppMenuFactory";
	public static final String CLASS_NAME_FP_APP_TLB_FCT = "org.smyld.app.AppToolbarsFactory";
	public static final String CLASS_NAME_APP_TLB_FCT = "AppToolbarsFactory";
	public static final String CLASS_NAME_FP_APP_PNL_FCT = "org.smyld.app.AppPanelFactory";
	public static final String CLASS_NAME_APP_PNL_FCT = "AppPanelFactory";

	// Componenet Classes
	public static final String CLASS_NAME_FP_SMYLD_PANEL      = "org.smyld.gui.SMYLDPanel";
	public static final String CLASS_NAME_FP_PE_PANEL        = "org.smyld.gui.portal.engine.PEPanel";
	public static final String CLASS_NAME_FP_USER_CONSTRAINT = "org.smyld.app.pe.model.user.UserConstraint";
	public static final String CLASS_NAME_FP_PE_ACTION       = "org.smyld.app.pe.model.gui.PEAction";
	public static final String CLASS_NAME_FP_GUI_TABLE_COL   = "org.smyld.app.pe.model.gui.GUITableColumn";

	public static final String CLASS_NAME_SMYLD_PANEL      = "SMYLDPanel";
	public static final String CLASS_NAME_PE_PANEL        = "PEPanel";
	public static final String CLASS_NAME_PE_ACTION       = "PEAction";
	public static final String CLASS_NAME_GUI_TABLE_COL   = "GUITableColumn";
	public static final String CLASS_NAME_USER_CONSTRAINT = "UserConstraint";

	public static final String SWING_METH_SET_COMP_ORIENT = "setComponentOrientation";
	public static final String SWING_METH_APPLY_COMP_ORIENT = "applyComponentOrientation";
	public static final String SWING_METH_SET_DEF_LAF = ".setDefaultLookAndFeelDecorated(true)";
	public static final String MODULE_NAME_WINDOWS = "windows";
	public static final String MODULE_NAME_MENUS = "menus";
	public static final String MODULE_NAME_ACTIONS = "pactions";
	public static final String MODULE_NAME_TLBS = "toolbars";

	public static final String JNLP_DEFAULT_APP_FOLDER = "apps/";
	public static final String FILE_TEST_OBJ = "D:/Projects/OtherProjects/PortalEngine/public_html/window.xml";
	public static final String FILE_TEST_WINDOW = "D:/Projects/OtherProjects/PortalEngine/public_html/Container.xml";
}
