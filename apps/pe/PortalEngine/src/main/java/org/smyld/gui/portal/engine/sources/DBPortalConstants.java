package org.smyld.gui.portal.engine.sources;

public interface DBPortalConstants {

	// Shorting word used list :
	// INST : INSTITUTION
	// IDX : INDEX
	// DOC : DOCUMENT
	// EST : ESTABLISHMENT
	// EXT : EXTERNAL
	// LST : LIST
	// SFLD : SUBFIELD
	// NUM : NUMBER
	// EXR : EXTRA
	// SRV : SERVICE
	// METH : METHOD
	// PRO : PROCEDURE
	// STA : STATUS
	// CLNT : CLIENT
	// CNTR : CONTRACT
	// ORG : ORIGINAL
	// CAPT : CAPTUR
	// CHNL : CHANNEL
	// CRYP : CRYPTOGRAM
	// AUTH : AUTHORIZATION
	// LNK : LINKS
	// TERM : TERMINAL
	// PKG : PACKAGE
	// CONV : CONVERSION
	// CHG : CHARGES
	// OFC : OFFICER
	// FNC : FUNCTION
	// CUS : CUSTOMER
	// SYS : SYSTEM
	// FRMT : FORMAT
	// GRP : GROUP
	// ADD : ADDENDUM
	// VER : VERSION
	// AMT : AMOUNT
	// ORGA : ORGANIZATION
	// BUSS : BUSINESS
	// REC : RECORD
	// DEST : DESTINATION
	// MERC : MERCHANT
	// DESC : DESCRIPTION
	// CNC : CANCELLED
	// EXP : EXPIRATION
	// PAY : PAYMENT
	// COMD : COMMODITY
	// APP : APPLICATION
	// LOC : LOCATION
	// CAPA : CAPABILITY
	// RGN : REGION
	// USR : USER
	// CMPL : COMPLETE
	// INFO : INFORMATION
	// SRC : SOURCE
	// ADI : ADDITIONAL
	// CATG : CATEGORY
	// MSG : MESSAGE
	// SETL : SETTLEMENT
	// DTL : DETAILS
	// FLD : FIELD
	// INTR : INTERCHANGE
	// DUP : DUPLICATE
	// CLS : CLASS
	// ISS : ISSUER
	// PRC : PROCESS
	// CTRY : COUNTRY
	// REV : REVERSAL
	// OCC : OCCURRENCE
	// CUR : CURRENCY
	// EXPN : EXPONENT
	// SEQ : SEQUENCE
	// SEL : SELECTION
	// REF : REFERENCE
	// TEL : TELEPHONE
	// PARM : PARAMETER
	// ACQ : ACQUIRER
	// IND : INDICATOR
	// REJ : REJECTED
	// TRN : TRANSACTION
	// ADR : ADDRESS
	public final static String TABLE_PRT_ACTIONS = "PRT_ACTIONS";
	public final static String COL_PRT_ACTIONS_APP_ID = "APP_ID";
	public final static String COL_PRT_ACTIONS_ACTION_ID = "ACTION_ID";
	public final static String COL_PRT_ACTIONS_ACT_COM = "ACT_COM";
	public final static String COL_PRT_ACTIONS_ACT_LABEL = "ACT_LABEL";
	public final static String COL_PRT_ACTIONS_ACT_TARGET = "ACT_TARGET";
	public final static String COL_PRT_ACTIONS_ACT_PARAM = "ACT_PARAM";
	public final static String TABLE_PRT_APPLICATIONS = "PRT_APPLICATIONS";
	public final static String COL_PRT_APPLICATIONS_ID = "ID";
	public final static String COL_PRT_APPLICATIONS_TYPE = "TYPE";
	public final static String COL_PRT_APPLICATIONS_NAME = "NAME";
	public final static String COL_PRT_APPLICATIONS_HOME = "HOME";
	public final static String COL_PRT_APPLICATIONS_SRC = "SOURCE";
	public final static String COL_PRT_APPLICATIONS_CLASSES = "CLASSES";
	public final static String COL_PRT_APPLICATIONS_MAINCLASS = "MAINCLASS";
	public final static String COL_PRT_APPLICATIONS_APPEARANCE = "APPEARANCE";
	public final static String COL_PRT_APPLICATIONS_LINK = "LINK";
	public final static String COL_PRT_APPLICATIONS_COMPONENT_TYPE = "COMPONENT_TYPE";
	public final static String COL_PRT_APPLICATIONS_SETTINGS_NAME = "SETTINGS_NAME";
	public final static String COL_PRT_APPLICATIONS_SETTINGS_PATH = "SETTINGS_PATH";
	public final static String COL_PRT_APPLICATIONS_JAR_NAME = "JAR_NAME";
	public final static String COL_PRT_APPLICATIONS_JAR_PATH = "JAR_PATH";
	public final static String COL_PRT_APPLICATIONS_LOG_NAME = "LOG_NAME";
	public final static String COL_PRT_APPLICATIONS_LOG_PATH = "LOG_PATH";
	public final static String TABLE_PRT_APP_IMAGES = "PRT_APP_IMAGES";
	public final static String COL_PRT_APP_IMAGES_APP_ID = "APP_ID";
	public final static String COL_PRT_APP_IMAGES_IMG_SRC = "IMG_SRC";
	public final static String TABLE_PRT_APP_LANG = "PRT_APP_LANG";
	public final static String COL_PRT_APP_LANG_NAME = "NAME";
	public final static String COL_PRT_APP_LANG_SRC = "SOURCE";
	public final static String COL_PRT_APP_LANG_ID = "ID";
	public final static String TABLE_PRT_APP_LIBRARY = "PRT_APP_LIBRARY";
	public final static String COL_PRT_APP_LIBRARY_APP_ID = "APP_ID";
	public final static String COL_PRT_APP_LIBRARY_LIB_ID = "LIB_ID";
	public final static String TABLE_PRT_APP_RESOURCES = "PRT_APP_RESOURCES";
	public final static String COL_PRT_APP_RESOURCES_APP_ID = "APP_ID";
	public final static String COL_PRT_APP_RESOURCES_RES_ID = "RES_ID";
	public final static String TABLE_PRT_APP_TYPE = "PRT_APP_TYPE";
	public final static String COL_PRT_APP_TYPE_TYPE_ID = "TYPE_ID";
	public final static String COL_PRT_APP_TYPE_DESC = "DESCRIPTION";
	public final static String TABLE_PRT_IMAGES = "PRT_IMAGES";
	public final static String COL_PRT_IMAGES_IMG_SRC = "IMG_SRC";
	public final static String COL_PRT_IMAGES_IMG_TYPE = "IMG_TYPE";
	public final static String TABLE_PRT_LANG = "PRT_LANG";
	public final static String COL_PRT_LANG_NAME = "NAME";
	public final static String COL_PRT_LANG_SRC = "SOURCE";
	public final static String COL_PRT_LANG_TARGET = "TARGET";
	public final static String TABLE_PRT_LIBRARY = "PRT_LIBRARY";
	public final static String COL_PRT_LIBRARY_ID = "ID";
	public final static String COL_PRT_LIBRARY_SRC = "SOURCE";
	public final static String TABLE_PRT_MENU = "PRT_MENU";
	public final static String COL_PRT_MENU_APP_ID = "APP_ID";
	public final static String COL_PRT_MENU_BAR_ID = "BAR_ID";
	public final static String COL_PRT_MENU_MNU_ID = "MNU_ID";
	public final static String COL_PRT_MENU_PARENT_ID = "PARENT_ID";
	public final static String COL_PRT_MENU_ACTION_ID = "ACTION_ID";
	public final static String COL_PRT_MENU_MNU_ICON = "MNU_ICON";
	public final static String COL_PRT_MENU_MNU_ORDER = "MNU_ORDER";
	public final static String COL_PRT_MENU_MNU_TYPE = "MNU_TYPE";
	public final static String TABLE_PRT_PANELS = "PRT_PANELS";
	public final static String COL_PRT_PANELS_APP_ID = "APP_ID";
	public final static String COL_PRT_PANELS_ID = "ID";
	public final static String COL_PRT_PANELS_BODY = "BODY";
	public final static String TABLE_PRT_RESOURCES = "PRT_RESOURCES";
	public final static String COL_PRT_RESOURCES_ID = "ID";
	public final static String COL_PRT_RESOURCES_SRC = "SOURCE";
	public final static String TABLE_PRT_TOOLBARS = "PRT_TOOLBARS";
	public final static String COL_PRT_TOOLBARS_APP_ID = "APP_ID";
	public final static String COL_PRT_TOOLBARS_TLB_ID = "TLB_ID";
	public final static String COL_PRT_TOOLBARS_ITEM_ID = "ITEM_ID";
	public final static String COL_PRT_TOOLBARS_ACTION_ID = "ACTION_ID";
	public final static String COL_PRT_TOOLBARS_ITEM_ICON = "ITEM_ICON";
	public final static String COL_PRT_TOOLBARS_ITEM_ORDER = "ITEM_ORDER";
	public final static String COL_PRT_TOOLBARS_ITEM_TYPE = "ITEM_TYPE";
	public final static String TABLE_PRT_WINDOWS = "PRT_WINDOWS";
	public final static String COL_PRT_WINDOWS_APP_ID = "APP_ID";
	public final static String COL_PRT_WINDOWS_ID = "ID";
	public final static String COL_PRT_WINDOWS_TYPE = "TYPE";
	public final static String COL_PRT_WINDOWS_ICON = "ICON_";
	public final static String COL_PRT_WINDOWS_WIDTH = "WIDTH";
	public final static String COL_PRT_WINDOWS_HEIGHT = "HEIGHT";
	public final static String COL_PRT_WINDOWS_BODY = "BODY";

}
