package org.smyld.deploy.web.jnlp;

import org.smyld.deploy.web.WebDescConstants;

public interface JNLPConstants extends WebDescConstants {
	public static final String TAG_NAME_ROOT = "jnlp";
	public static final String TAG_NAME_INFO = "information";
	public static final String TAG_NAME_RES = "resources";
	public static final String TAG_NAME_APP_DESC = "application-desc";
	public static final String TAG_NAME_APL_DESC = "applet-desc";
	public static final String TAG_NAME_COMP_DESC = "component-desc";
	public static final String TAG_NAME_INST_DESC = "installer-desc";
	public static final String TAG_NAME_TITLE = "title";
	public static final String TAG_NAME_VENDOR = "vendor";
	public static final String TAG_NAME_HOME_PAGE = "homepage";
	public static final String TAG_NAME_OFF_ALWD = "offline-allowed";
	public static final String TAG_NAME_J2SE = "j2se";
	public static final String TAG_NAME_JAR = "jar";
	public static final String TAG_NAME_ICON = "icon";
	public static final String TAG_NAME_SHORTCUT = "shortcut";
	public static final String TAG_NAME_DESKTOP = "desktop";
	public static final String TAG_NAME_MENU = "menu";
	public static final String TAG_NAME_NAME = "name";
	public static final String TAG_NAME_WIDTH = "width";
	public static final String TAG_NAME_HEIGHT = "height";
	public static final String TAG_NAME_PARAM = "param";
	public static final String TAG_NAME_RELATED_CONTENT = "related-content";
	public static final String TAG_NAME_ARGUMENT = "argument";
	public static final String TAG_NAME_PROPERTY = "property";
	public static final String TAG_NAME_EXTENSION = "extension";
	public static final String TAG_NAME_SECURITY = "security";
	public static final String TAG_NAME_ALL_PERMISSIONS = "all-permissions";

	public static final String TAG_ATT_REF = "href";
	public static final String TAG_ATT_VER = "version";
	public static final String TAG_ATT_MAIN = "main";
	public static final String TAG_ATT_DOWNLOAD = "download";
	public static final String TAG_ATT_ONLINE = "online";
	public static final String TAG_ATT_SPEC = "spec";
	public static final String TAG_ATT_CODE_BASE = "codebase";
	public static final String TAG_ATT_ENC = "encoding";
	public static final String TAG_ATT_MAIN_CLS = "main-class";
	public static final String TAG_ATT_KIND = "kind";
	public static final String TAG_ATT_LOCALE = "locale";
	public static final String TAG_ATT_SUBMENU = "submenu";
	public static final String TAG_ATT_VALUE = "value";
	public static final String TAG_ATT_OS = "os";
	public static final String TAG_ATT_PART = "part";
	public static final String TAG_ATT_RECURSIVE = "recursive";
	public static final String TAG_ATT_IN_HEAP_SIZE = "initial-heap-size";

	public static final String ATT_DOWNLOAD_EAGER = "eager";
	public static final String ATT_DOWNLOAD_LAZY = "lazy";

	public static final String ATT_BOOLEAN_TRUE = "true";
	public static final String ATT_BOOLEAN_FALSE = "false";

	public static final String ATT_KIND_TOOLTIP = "tooltip";
	public static final String ATT_SPEC_DEFAULT = "1.0+";
	public static final String ATT_CODEBASE_DEFAULT = ".";
	public static final String ATT_NAME_DEFAULT = "app.jnlp";
	public static final String ATT_J2SE_DEFAULT = "1.4+";

	public static final String HTML_TITLE_DEFAULT = "SMYLD JNLP launcher page .. ";
	public static final String HTML_FILE_NAME = "index.html";
	public static final String HTML_HEADLINE_DEFAULT = "SMYLD JNLP launcher page .. ";
	public static final String FOLDER_APPS = "apps/";
	public static final String JNLP_SIGN_DEF_NAME = "JNLP-INF/APPLICATION.JNLP";

}
