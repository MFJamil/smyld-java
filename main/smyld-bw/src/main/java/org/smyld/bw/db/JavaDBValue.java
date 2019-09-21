package org.smyld.bw.db;

public interface JavaDBValue extends DBValue {
	// --------------------->Constants used in
	// SMYLD_MIGRATE<------------------------

	// Parameter file types prefixes
	public static final String PARAM_FILE_PREFIX_DIC = "DIC";
	public static final String PARAM_FILE_PREFIX_MSK = "MSK";
	public static final String PARAM_FILE_PREFIX_SCR = "SCR";
	public static final String PARAM_FILE_PREFIX_MNU = "MNU";
	public static final String PARAM_FILE_PREFIX_PRC = "PRC";
	public static final String PARAM_FILE_PREFIX_BCH = "BCH";
	public static final String PARAM_FILE_PREFIX_DSP = "DSP";
	public static final String PARAM_FILE_PREFIX_LST = "LST";

	// Parameter file types
	public static final int PARAM_FILE_PREFIX_DIC_IDX = 0;
	public static final int PARAM_FILE_PREFIX_MSK_IDX = 1;
	public static final int PARAM_FILE_PREFIX_SCR_IDX = 2;
	public static final int PARAM_FILE_PREFIX_MNU_IDX = 3;
	public static final int PARAM_FILE_PREFIX_PRC_IDX = 4;
	public static final int PARAM_FILE_PREFIX_BCH_IDX = 5;
	public static final int PARAM_FILE_PREFIX_DSP_IDX = 7;
	public static final int PARAM_FILE_PREFIX_LST_IDX = 8;

	// menu id prefixes stored in the MDI menu table
	public static final String GUI_MENU_PREFIX_MENU = "mnu";
	public static final String GUI_MENU_PREFIX_MENU_ITEM = "mni";
	public static final String GUI_MENU_PREFIX_SEPARATOR = "sep";
	public static final String GUI_MENU_PREFIX_CHECKBOX = "chk";

	// POPUP menu IDS used in the table
	public static final String GUI_POPUP_MENU_ID_TREE_MENU = "tree_menu_edit";

	// Constant string values used in the class "MskSQLExtractor" for replacing
	// the
	// sql select query strings
	public static final String SQL_EXTRACTOR_SQLSELECT = "SQLSelect";
	public static final String SQL_EXTRACTOR_SQLREF = "SQLRef";
	public static final String GUI_ROOT_MNU_NAME = "sys_main_system.mnu";
	public static final String GUI_LANG_ID_MENU_CAPTION = "MENU_CAPTION";

	// Constants used in proces console
	public static final String GUI_LANG_ID_COMBOITEM_ALL = "combo_itemAll";
	public static final String GUI_LANG_ID_MNU_OPTIONS = "mnu_optiontitle";
	public static final String GUI_LANG_ID_MNUITEM_EXIT = "mnu_optionitem_exit";

	public static final String GUI_LANG_ID_BTN_RUN = "btn_run";
	public static final String GUI_LANG_ID_BTN_RUNUNTIL = "btn_rununtil";

	public static final String GUI_LANG_ID_LBL_PREPROCESS = "lbl_preprocess";
	public static final String GUI_LANG_ID_LBL_POSTPROCESS = "lbl_postprocess";
	public static final String GUI_LANG_ID_LBL_PHASE_TWO = "lbl_phase_two";
	public static final String GUI_LANG_ID_LBL_PHASE_THREE = "lbl_phase_three";
	public static final String GUI_LANG_ID_LBL_PHASE_FOUR = "lbl_phase_four";

	public static final String GUI_LANG_ID_LBL_SEND = "lbl_send";
	public static final String GUI_LANG_ID_LBL_SAVE_AS = "lbl_save_as";
	public static final String GUI_LANG_ID_LBL_STATUS = "lbl_status";
	public static final String GUI_LANG_ID_LBL_WIN_TITLE = "lbl_win_title";

	// sys_configuration
	// Key ID
	public static final String SYS_CONF_SECTION_IPM = "IPM";
	public static final String SYS_CONF_SECTION_PROCESSING = "Processing";
	public static final String SYS_CONF_SECTION_PREPROCESS = "HighVolumeEngine";
	public static final String SYS_CONF_CONF_KEY_IPM_OLD_ICAS = "OldICA";
	public static final String SYS_CONF_CONF_KEY_IPM_DEF_ICA = "DefaultICA";
	public static final String SYS_CONF_CONF_KEY_ALLOWED_IPS = "AllowedIps";
	public static final String SYS_CONF_CONF_KEY_LOG_FILE_PATH = "LogFileName";
	public static final String SYS_CONF_CONF_KEY_JAVA_OUT_PATH = "JavaOutputFileName";
	public static final String SYS_CONF_CONF_KEY_JAR_FILE_PATH = "JarFilePath";
	public static final String SYS_CONF_CONF_KEY_AQ_QUEUE = "AQQueueName";
	public static final String SYS_CONF_CONF_KEY_SUBNET_MASK = "SubnetMask";
	public static final String SYS_CONF_CONF_KEY_MAX_PAR_PRC = "MaxParallelProcesses";
	public static final String SYS_CONF_CONF_KEY_DEBUG_PER_PRC = "debugPerProcess";
	public static final String SYS_CONF_CONF_KEY_DEBUG_LEVEL = "debugLevel";
	public static final String SYS_CONF_CONF_KEY_DEBUG_FOLDER = "debugFolder";
	public static final String SYS_CONF_CONF_KEY_DEBUG_FILE_EXT = "debugFileExtension";
	public static final String SYS_CONF_CONF_KEY_AQ_INTERVAL = "Preprocess_Response";
	public static final String SYS_CONF_CONF_KEY_XFC_SAVE_POLICY = "XFCSavePolicy";
	public static final String SYS_CONF_CONF_KEY_XFC_DUMP_FOLDER = "XFCDumpFolder";
	public static final String SYS_CONF_CONF_KEY_SEP_IPM_ICA = ",";
	public static final String SYS_CONF_CONF_KEY_LOOKUP_LNK_BY_TRNDATE = "LookupLinkByTranDate";
	public static final String SYS_CONF_CONF_KEY_MULTI_MERCH_LINKS = "MultipleMerchantLinks";
	public static final String SYS_CONF_CONF_KEY_EN_GRP_MERCH_COMIS = "EnableGroupMerchCommission";
	public static final String SYS_CONF_CONF_KEY_ALLOW_SPACE_AS_STATE = "AllowSpacesAsState";
	public static final String SYS_CONF_CONF_KEY_BATCH_INSERT_SIZE = "JavaBatchInsertSize";
	public static final String SYS_CONF_CONF_KEY_DO_SAVE_DATA = "saveData";
	public static final String SYS_CONF_CONF_KEY_PROCESS_TEST_FILES = "processTestFiles";

	public static final String SYS_CONF_CONF_KEY_BOOLEAN_TRUE = "-1";
	public static final String SYS_CONF_CONF_KEY_BOOLEAN_FALSE = "0";

	// Special instance constant names
	public static final String CNTR_DETAIL_INST_GRP_CLNT_NUM = "GROUP_CLIENT_NUMBER";
	// Key Value
	public static final String KEY_DEBUG_PER_PRC_YES = "true";
	public static final String KEY_DEBUG_PER_PRC_NO = "false";
	public static final String KEY_DEBUG_LEVEL_1 = "1";
	public static final String KEY_DEBUG_LEVEL_2 = "2";
	public static final String KEY_DEBUG_LEVEL_3 = "3";

	public static final String KEY_XFC_SAVE_POLICY_INTERNAL = "internal";
	public static final String KEY_XFC_SAVE_POLICY_EXTERNAL = "external";

	public static final String INSTALLATION_DPR_NUM = "XXX";

	public static final char CHAR_DEBIT = 'D';
	public static final char CHAR_CREDIT = 'C';

	public static final String DBA_DIR_UDUMP = "UDUMP_DIR";

	public static final String XFC_POLICY_INTERNAL = "internal";
	public static final String XFC_POLICY_EXTERNAL = "external";

	// Below are values that have to be in the DBValue class but temporarly
	// created here

	public static final String PROC_NAME_RB_MDS = "580";

}
