package org.smyld.bw.db;

/**
 * This class will hold all the data base names including tables,columns,views
 * ..etc to work with the alias in order to maintain the code easily
 */
public interface DBName {
	/*
	 * The abbreviation is used to facilitate the long names and make them more
	 * usage friendly the list as follows: ACQUIRER : ACQ ADDENDUM : ADD
	 * ADDITIONAL : ADI ADDRESS : ADR AMOUNT : AMT APPLICATION : APP
	 * AUTHORIZATION: AUTH BUSINESS : BUSS CATEGORY : CATG CANCELLED : CNC
	 * CAPABILITY : CAPA CAPTUR : CAPT CHANNEL : CHNL CHARGES : CHG CLASS : CLS
	 * CLIENT : CLNT COMMODITY : COMD COMPLETE : CMPL CONTRACT : CNTR CONVERSION :
	 * CONV COUNTRY : CTRY CRYPTOGRAM : CRYPT CURRENCY : CUR CUSTOMER : CUS
	 * DESCRIPTION : DESC DESTINATION : DEST DETAILS : DTL DOCUMENT : DOC
	 * DUPLICATE : DUP ESTABLISHMENT: EST EXPIRATION : EXP EXTERNAL : EXT EXTRA :
	 * EXR EXPONENT : EXPN FIELD : FLD FORMAT : FRMT FUNCTION : FNC GROUP : GRP
	 * INSTITUTION : INST INDEX : IDX INDICATOR : IND ISSUER : ISS INFORMATION :
	 * INFO INTERCHANGE : INTR LINKS : LNK LIST : LST LOCATION : LOC MERCHANT :
	 * MERCH MESSAGE : MSG METHOD : METH NUMBER : NUM OCCURRENCE : OCC OFFICER :
	 * OFC ORGANIZATION : ORGA ORIGINAL : ORG PACKAGE : PKG PARAMETER : PARM
	 * PAYMENT : PAY PROCEDURE : PRO PROCESS : PRC RECORD : REC REFERENCE : REF
	 * REGION : RGN REGISTERATION: RGS REJECTED : REJ REVERSAL : REV SELECTION :
	 * SEL SEQUENCE : SEQ SERVICE : SRV SETTLEMENT : SETL SOURCE : SRC STATUS :
	 * STA SUBFIELD : SFLD SYSTEM : SYS TELEPHONE : TEL TERMINAL : TERM
	 * TRANSACTION : TRN USER : USR VERSION : VER
	 * 
	 */
	// ****************************** ISO Table Names
	// ***************************
	// Table iso_8583_data_types
	public static final String TABLE_ISO_8583_DATA_TYPES = "ISO_8583_DATA_TYPES";
	// Table iso_8583_data_types fields names
	public static final String COL_ISO_8583_DATA_TYPES_ID = "DATA_TYPE_ID";
	public static final String COL_ISO_8583_DATA_TYPES_NAME = "DATA_TYPE_NAME";
	// Table iso_8583_field_formats
	public static final String TABLE_ISO_8583_FIELD_FORMATS = "ISO_8583_FIELD_FORMATS";
	// Table iso_8583_field_formats fields names
	public static final String COL_ISO_8583_FIELD_FORMATS_ID = "FIELD_FORMAT_ID";
	public static final String COL_ISO_8583_FIELD_FORMATS_NAME = "FIELD_FORMAT_NAME";
	public static final String COL_ISO_8583_FIELD_FORMATS_DESC = "FIELD_FORMAT_DESCRIPTION";
	// Table iso_8583_field_length_type
	public static final String TABLE_ISO_8583_FIELD_LENGTH_TYPES = "ISO_8583_FIELD_LENGTH_TYPES";
	// Table iso_8583_field_length_type fields names
	public static final String COL_ISO_8583_FIELD_LENGTH_TYPES_ID = "LENGTH_TYPE_ID";
	public static final String COL_ISO_8583_FIELD_LENGTH_TYPES_MASK = "LENGTH_TYPE_MASKS";
	public static final String COL_ISO_8583_FIELD_LENGTH_TYPES_DESC = "LENGTH_TYPE_DESCRIPTION";

	// Table iso_8583_field_types
	public static final String TABLE_ISO_8583_FIELD_TYPES = "ISO_8583_FIELD_TYPES";
	// Table iso_8583_field_types fields names
	public static final String COL_ISO_8583_FIELD_TYPES_ID = "FIELD_TYPE_ID";
	public static final String COL_ISO_8583_FIELD_TYPES_NAME = "TYPE_NAME";
	public static final String COL_ISO_8583_FIELD_TYPES_DESC = "TYPE_DESCRIPTION";

	// Table iso_8583_formats
	public static final String TABLE_ISO_8583_FORMATS = "ISO_8583_FORMATS";
	// Table iso_8583_formats field names
	public static final String COL_ISO_8583_FORMATS_ID = "FORMAT_ID";
	public static final String COL_ISO_8583_FORMATS_NAME = "FORMAT_NAME";

	// Table iso_8583_file_usage
	public static final String TABLE_ISO_8583_FILE_USAGE = "ISO_8583_FILE_USAGE";
	// Table iso_8583_file_usage field names
	public static final String COL_ISO_8583_FILE_USAGE_ID = "FILE_USAGE_ID";
	public static final String COL_ISO_8583_FILE_USAGE_NAME = "FILE_USAGE_NAME";
	// Table iso_8583_bitmaps
	public static final String TABLE_ISO_8583_BITMAPS = "ISO_8583_BITMAPS";
	// Table iso_8583_bitmaps field names
	public static final String COL_ISO_8583_BITMAPS_MSG_ID = "MESSAGE_ID";
	public static final String COL_ISO_8583_BITMAPS_MSG_BITMAP = "MESSAGE_BITMAP";
	// Table iso_8583_structurs
	public static final String TABLE_ISO_8583_STRUCTURS = "ISO_8583_STRUCTURS";
	// Table iso_8583_structurs field names
	public static final String COL_ISO_8583_STRUCTURS_FLD_ORDER = "FIELD_ORDER";
	public static final String COL_ISO_8583_STRUCTURS_FLD_USAGE = "FIELD_USAGE";
	// Table iso_8583_fields
	public static final String TABLE_ISO_8583_FIELDS = "ISO_8583_FIELDS";
	// Table iso_8583_fields field names
	public static final String COL_ISO_8583_FIELDS_ID = "FIELD_ID";
	public static final String COL_ISO_8583_FIELDS_PARENT_ID = "PARENT_ID";
	public static final String COL_ISO_8583_FIELDS_NAME = "FIELD_NAME";
	public static final String COL_ISO_8583_FIELDS_LENGTH = "FIELD_LENGTH";
	public static final String COL_ISO_8583_FIELDS_SFLD_PRESENT = "SUBFIELDS_PRESENT";
	public static final String COL_ISO_8583_FIELDS_SFLD_ORDER = "SUBFIELD_ORDER";
	// ****************************** INT Table Names
	// ***************************
	// ****************************** SYS Table Names
	// ***************************
	// Table SYS_USER_INFORMATION
	public static final String TABLE_SYS_USR_INFO = "SYS_USER_INFORMATION";
	// Table SYS_USER_INFORMATION field names
	public static final String COL_SYS_USR_INFO_ID = "USERID";
	public static final String COL_SYS_USR_INFO_SHORT_NAME = "USERSHORTNAME";
	public static final String COL_SYS_USR_INFO_PWD = "PASS_WORD";
	public static final String COL_SYS_USR_INFO_NAME = "USERNAME";
	public static final String COL_SYS_USR_INFO_LANG = "LANGUAGE";
	public static final String COL_SYS_USR_INFO_DEF_INST = "DEFAULTINSTITUTION";
	public static final String COL_SYS_USR_INFO_BANK_CODE = "BANKCODE";
	public static final String COL_SYS_USR_INFO_BRANCH_CODE = "BRANCHCODE";
	public static final String COL_SYS_USR_INFO_DEPT_CODE = "DEPARTMENTCODE";
	public static final String COL_SYS_USR_INFO_ADMIN = "ADMINISTRATOR";
	public static final String COL_SYS_USR_INFO_DEVELOPER = "DEVELOPER";
	public static final String COL_SYS_USR_INFO_SMYLD_INTERN = "INTERNALSMYLD";
	public static final String COL_SYS_USR_INFO_STATUS = "STATUS";
	public static final String COL_SYS_USR_INFO_PWD_DATE = "PASSWORDDATE";
	public static final String COL_SYS_USR_INFO_LAST_LOG = "LASTLOGON";
	public static final String COL_SYS_USR_INFO_ACCESS_GRP = "ACCESSGROUPS";
	public static final String COL_SYS_USR_INFO_VALID_INST = "VALIDINSTITUTIONS";
	public static final String COL_SYS_USR_INFO_MODULE_ACCESS = "MODULEACCESS";

	// Table SYS_INSTITUTION_LICENCE
	public static final String TABLE_SYS_INST_LICENCE = "SYS_INSTITUTION_LICENCE";
	// Table SYS_INSTITUTION_LICENCE field names
	public static final String COL_SYS_INST_REC_DATE = "RECORD_DATE";
	public static final String COL_SYS_INST_INST_NUM = "INSTITUTION_NUMBER";
	public static final String COL_SYS_INST_INST_NAME = "INSTITUTION_NAME";
	public static final String COL_SYS_INST_INST_LICENCE_NUM = "INSTITUTION_LICENCE_NUMBER";
	public static final String COL_SYS_INST_NUM_OF_USERS = "NUMBER_OF_USERS";
	public static final String COL_SYS_INST_EXPIRY_DATE = "EXPIRY_DATE";
	public static final String COL_SYS_INST_ACTIVATION_KEY = "ACTIVATION_KEY";
	public static final String COL_SYS_INST_INSTALLATION_NUM = "INSTALLATION_NUMBER";
	public static final String COL_SYS_INST_INST_ID = "INSTITUTION_ID";
	public static final String COL_SYS_INST_ACTIVATION_CODE = "ACTIVATION_CODE";
	// **** Table (INT_SUNDRY_TRANSACTIONS) ****
	public final static String TABLE_INT_SUNDRY_TRNS = "INT_SUNDRY_TRANSACTIONS";
	public final static String COL_INT_SUNDRY_TRNS_REC_DATE = "RECORD_DATE";
	public final static String COL_INT_SUNDRY_TRNS_INST_NUM = "INSTITUTION_NUMBER";
	public final static String COL_INT_SUNDRY_TRNS_AUDIT_TRAIL = "AUDIT_TRAIL";
	public final static String COL_INT_SUNDRY_TRNS_TRN_SLIP = "TRANSACTION_SLIP";
	public final static String COL_INT_SUNDRY_TRNS_SUNDRY_TRN_SLIP = "SUNDRY_TRANSACTION_SLIP";
	public final static String COL_INT_SUNDRY_TRNS_TRN_SRC = "TRANSACTION_SOURCE";
	public final static String COL_INT_SUNDRY_TRNS_TRN_DEST = "TRANSACTION_DESTINATION";
	public final static String COL_INT_SUNDRY_TRNS_SUNDRY_TYPE = "SUNDRY_TYPE";
	public final static String COL_INT_SUNDRY_TRNS_SUNDRY_REASON = "SUNDRY_REASON";
	public final static String COL_INT_SUNDRY_TRNS_DOC_CODE = "DOCUMENT_CODE";
	public final static String COL_INT_SUNDRY_TRNS_SUNDRY_REF = "SUNDRY_REFERENCE";
	public final static String COL_INT_SUNDRY_TRNS_SUNDRY_STA = "SUNDRY_STATUS";
	public final static String COL_INT_SUNDRY_TRNS_MSG_SUNDRY_TEXT = "MESSAGE_SUNDRY_TEXT";
	public final static String COL_INT_SUNDRY_TRNS_NOTE_TEXT = "NOTE_TEXT";
	public final static String COL_INT_SUNDRY_TRNS_ACTION_TAKEN = "ACTION_TAKEN";
	public final static String COL_INT_SUNDRY_TRNS_TRN_DATE = "TRANSACTION_DATE";
	public final static String COL_INT_SUNDRY_TRNS_SETL_CUR = "SETTLEMENT_CURRENCY";
	public final static String COL_INT_SUNDRY_TRNS_SETL_AMT_GR = "SETTLEMENT_AMOUNT_GR";
	public final static String COL_INT_SUNDRY_TRNS_TRAN_CUR = "TRAN_CURRENCY";
	public final static String COL_INT_SUNDRY_TRNS_TRAN_AMT_GR = "TRAN_AMOUNT_GR";
	public final static String COL_INT_SUNDRY_TRNS_DEST_ACCOUNT = "DESTINATION_ACCOUNT";
	public final static String COL_INT_SUNDRY_TRNS_CARD_NUM = "CARD_NUMBER";
	public final static String COL_INT_SUNDRY_TRNS_RECIEVING_MEMBER_ID = "RECIEVING_MEMBER_ID";
	public final static String COL_INT_SUNDRY_TRNS_PROCESSING_CLS = "PROCESSING_CLASS";
	public final static String COL_INT_SUNDRY_TRNS_TRANSFER_FLAG = "TRANSFER_FLAG";
	public final static String COL_INT_SUNDRY_TRNS_INVALID_RESPONSE = "INVALID_RESPONSE";
	public final static String COL_INT_SUNDRY_TRNS_MASTERCOM_IND = "MASTERCOM_INDICATOR";
	public final static String COL_INT_SUNDRY_TRNS_FILE_NUM = "FILE_NUMBER";
	public final static String COL_INT_SUNDRY_TRNS_REV_FLAG = "REVERSAL_FLAG";
	public final static String COL_INT_SUNDRY_TRNS_SUB_FRAUD_TYPE = "SUB_FRAUD_TYPE";
	public final static String COL_INT_SUNDRY_TRNS_COUNTERFEIT_CODE = "COUNTERFEIT_CODE";
	public final static String COL_INT_SUNDRY_TRNS_FRAUD_CARD_TYPE = "FRAUD_CARD_TYPE";
	public final static String COL_INT_SUNDRY_TRNS_CHARGEBACK_FLAG = "CHARGEBACK_FLAG";
	public final static String COL_INT_SUNDRY_TRNS_DELETE_FLAG = "DELETE_FLAG";
	public final static String COL_INT_SUNDRY_TRNS_CLNT_NUM = "CLIENT_NUMBER";
	public final static String COL_INT_SUNDRY_TRNS_FORWARDING_MEMBER_ID = "FORWARDING_MEMBER_ID";
	public final static String COL_INT_SUNDRY_TRNS_ACCOUNT_TYPE_ID = "ACCOUNT_TYPE_ID";
	public final static String COL_INT_SUNDRY_TRNS_CARD_EXP_DATE = "CARD_EXPIRATION_DATE";
	public final static String COL_INT_SUNDRY_TRNS_FRAUD_AUTH_TYPE = "FRAUD_AUTH_TYPE";
	public final static String COL_INT_SUNDRY_TRNS_APPROVAL_CODE = "APPROVAL_CODE";
	public final static String COL_INT_SUNDRY_TRNS_INVESTIGATION_STA = "INVESTIGATION_STATUS";
	public final static String COL_INT_SUNDRY_TRNS_SRV_CNTR_ID = "SERVICE_CONTRACT_ID";
	public final static String COL_INT_SUNDRY_TRNS_ACCT_CUR = "ACCT_CURRENCY";
	public final static String COL_INT_SUNDRY_TRNS_GRP_NUM = "GROUP_NUMBER";
	public final static String COL_INT_SUNDRY_TRNS_ALT_ACCT_NUM = "ALT_ACCT_NUMBER";
	public final static String COL_INT_SUNDRY_TRNS_ALT_CLNT_NUM = "ALT_CLIENT_NUMBER";
	public final static String COL_INT_SUNDRY_TRNS_ALT_GRP_NUM = "ALT_GROUP_NUMBER";
	public final static String COL_INT_SUNDRY_TRNS_ALT_SRV_CNTR_ID = "ALT_SERVICE_CONTRACT_ID";
	public final static String COL_INT_SUNDRY_TRNS_ALT_ACCOUNT_TYPE_ID = "ALT_ACCOUNT_TYPE_ID";
	public final static String COL_INT_SUNDRY_TRNS_ALT_ACCT_CUR = "ALT_ACCT_CURRENCY";
	public final static String COL_INT_SUNDRY_TRNS_MERCH_ID = "MERCHANT_ID";
	public final static String COL_INT_SUNDRY_TRNS_MERCH_POST_CODE = "MERCHANT_POST_CODE";
	public final static String COL_INT_SUNDRY_TRNS_POS_ENTRY_MODE = "POS_ENTRY_MODE";
	public final static String COL_INT_SUNDRY_TRNS_AUTH_RESPONSE_CODE = "AUTH_RESPONSE_CODE";
	public final static String COL_INT_SUNDRY_TRNS_TERM_ATTENDANCE = "TERMINAL_ATTENDANCE";
	public final static String COL_INT_SUNDRY_TRNS_CARDHOLDER_PRESENCE = "CARDHOLDER_PRESENCE";
	public final static String COL_INT_SUNDRY_TRNS_CAT_LEVEL = "CAT_LEVEL";
	public final static String COL_INT_SUNDRY_TRNS_POS_INPUT_CAPA = "POS_INPUT_CAPABILITY";
	public final static String COL_INT_SUNDRY_TRNS_ECOM_SECURITY_LEVEL = "ECOM_SECURITY_LEVEL";
	public final static String COL_INT_SUNDRY_TRNS_POSITIVE_APPROVAL = "POSITIVE_APPROVAL";
	public final static String COL_INT_SUNDRY_TRNS_CVC_INVALID = "CVC_INVALID";
	public final static String COL_INT_SUNDRY_TRNS_REPORTED_DATE = "REPORTED_DATE";
	public final static String COL_INT_SUNDRY_TRNS_RETRIEVAL_REF = "RETRIEVAL_REFERENCE";
	public final static String COL_INT_SUNDRY_TRNS_TERM_CAPA = "TERMINAL_CAPABILITY";
	public final static String COL_INT_SUNDRY_TRNS_CAPTURE_METH = "CAPTURE_METHOD";
	public final static String COL_INT_SUNDRY_TRNS_AUTH_CODE = "AUTH_CODE";
	public final static String COL_INT_SUNDRY_TRNS_FEE_SEQ_NUM = "FEE_SEQUENCE_NUMBER";
	public final static String COL_INT_SUNDRY_TRNS_ACQ_REF = "ACQUIRER_REFERENCE";
	public final static String COL_INT_SUNDRY_TRNS_CARD_CAPA = "CARD_CAPABILITY";

	// Table INT_PROCESS_LOG
	public static final String TABLE_INT_PROC_LOG = "INT_PROCESS_LOG";
	// Table INT_PROCESS_LOG field names
	public static final String COL_INT_PROC_LOG_PRC_NUM = "PROCESS_NUMBER";
	public static final String COL_INT_PROC_LOG_INST_NUM = "INSTITUTION_NUMBER";
	public static final String COL_INT_PROC_LOG_PRC_NAME = "PROCESS_NAME";
	public static final String COL_INT_PROC_LOG_PRC_ID = "PROCESS_ID";
	public static final String COL_INT_PROC_LOG_USER_ID = "USER_ID";
	public static final String COL_INT_PROC_LOG_PRC_START_DATE = "PROCESS_START_DATE";
	public static final String COL_INT_PROC_LOG_PRC_END_DATE = "PROCESS_END_DATE";
	public static final String COL_INT_PROC_LOG_PRC_START_TIME = "PROCESS_START_TIME";
	public static final String COL_INT_PROC_LOG_PRC_END_TIME = "PROCESS_END_TIME";
	public static final String COL_INT_PROC_LOG_STATION_NUM = "STATION_NUMBER";
	public static final String COL_INT_PROC_LOG_APP_NAME = "APPLICATION_NAME";
	public static final String COL_INT_PROC_LOG_APP_VER = "APPLICATION_VERSION";
	public static final String COL_INT_PROC_LOG_POSTING_DATE = "POSTING_DATE";
	public static final String COL_INT_PROC_LOG_PRC_STATUS = "PROCESSING_STATUS";
	public static final String COL_INT_PROC_LOG_PARM_IN = "PARAMETERS_IN";
	public static final String COL_INT_PROC_LOG_PARM_OUT = "PARAMETERS_OUT";
	public static final String COL_INT_PROC_LOG_PARENT_PRC_NUM = "PARENT_PROCESS_NUMBER";
	public static final String COL_INT_PROC_LOG_PRIOR_PRC_NUM = "PRIOR_PROCESS_NUMBER";
	public static final String COL_INT_PROC_LOG_BRANCH_PRC_NUM = "BRANCH_PROCESS_NUMBER";
	public static final String COL_INT_PROC_LOG_PRO_PRC_NAME = "PROCEDURE_PROCESS_NAME";
	public static final String COL_INT_PROC_LOG_PRO_NUM = "PROCEDURE_NUMBER";
	public static final String COL_INT_PROC_LOG_ORG_PRO_NAME = "ORIGINAL_PROCEDURE_NAME";
	public static final String COL_INT_PROC_LOG_ORG_PRO_NUM = "ORIGINAL_PROCEDURE_NUMBER";
	public static final String COL_INT_PROC_LOG_NUM_OF_TRNS = "NUMBER_OF_TRANS";

	// Table INT_PROCESS_PARAMS
	public static final String TABLE_INT_PRC_PARMS = "INT_PROCESS_PARAMS";
	// Table INT_PROCESS_PARAMS field names
	public static final String COL_INT_PRC_PARMS_PRC_NUM = "PROCESS_NUMBER";
	public static final String COL_INT_PRC_PARMS_PARM_TYPE = "PARAM_TYPE";
	public static final String COL_INT_PRC_PARMS_PARM_NUM = "PARAM_NUMBER";
	public static final String COL_INT_PRC_PARMS_PARM_VALUE = "PARAMETER_VALUE";

	// Table INT_FILE_LOG_DETAILS
	public static final String TABLE_INT_FILE_LOG_DETAILS = "INT_FILE_LOG_DETAILS";
	// Table INT_FILE_LOG_DETAILS field names
	public static final String COL_INT_FILE_LOG_REC_DATE = "RECORD_DATE";
	public static final String COL_INT_FILE_LOG_AUDIT_TRAIL = "AUDIT_TRAIL";
	public static final String COL_INT_FILE_LOG_CLEARING_CHANNEL = "CLEARING_CHANNEL";
	public static final String COL_INT_FILE_LOG_FILE_TYPE = "FILE_TYPE";
	public static final String COL_INT_FILE_LOG_FILE_ID = "FILE_ID";
	public static final String COL_INT_FILE_LOG_INST_NUM = "INSTITUTION_NUMBER";
	public static final String COL_INT_FILE_LOG_FILE_NUM = "FILE_NUMBER";
	public static final String COL_INT_FILE_LOG_ORG_FILE_NAME = "ORIGINAL_FILE_NAME";
	public static final String COL_INT_FILE_LOG_FILE_DATE = "FILE_DATE";
	public static final String COL_INT_FILE_LOG_START_TIME = "START_TIME";
	public static final String COL_INT_FILE_LOG_END_TIME = "END_TIME";
	public static final String COL_INT_FILE_LOG_PRC_STATUS = "PROCESSING_STATUS";
	public static final String COL_INT_FILE_LOG_EMPTY_BATCHES = "EMPTY_BATCHES";
	public static final String COL_INT_FILE_LOG_DUP_BATCHES = "DUPLICATE_BATCHES";
	public static final String COL_INT_FILE_LOG_DUP_TRNS = "DUPLICATE_TRANSACTIONS";
	public static final String COL_INT_FILE_LOG_CNC_BATCHES = "CANCELLED_BATCHES";
	public static final String COL_INT_FILE_LOG_CNC_TRNS = "CANCELLED_TRANSACTIONS";
	public static final String COL_INT_FILE_LOG_REJ_BATCHES = "REJECTED_BATCHES";
	public static final String COL_INT_FILE_LOG_REJ_TRNS = "REJECTED_TRANSACTIONS";
	public static final String COL_INT_FILE_LOG_PRC_BATCHES = "PROCESSED_BATCHES";
	public static final String COL_INT_FILE_LOG_PRC_TRNS = "PROCESSED_TRANSACTIONS";
	public static final String COL_INT_FILE_LOG_PHASE_2_CMPL = "PHASE_2_COMPLETE";
	public static final String COL_INT_FILE_LOG_PHASE_3_CMPL = "PHASE_3_COMPLETE";
	public static final String COL_INT_FILE_LOG_PHASE_4_CMPL = "PHASE_4_COMPLETE";
	public static final String COL_INT_FILE_LOG_POSTING_SRC_CMPL = "POSTING_SOURCE_COMPLETE";
	public static final String COL_INT_FILE_LOG_PRC_NAME = "PROCESS_NAME";
	public static final String COL_INT_FILE_LOG_POSTING_DEST_CMPL = "POSTING_DEST_COMPLETE";
	public static final String COL_INT_FILE_LOG_GL_OUTPUT_CMPL = "GL_OUTPUT_COMPLETE";
	public static final String COL_INT_FILE_LOG_TRAN_DATA_LOC = "TRAN_DATA_LOCATION";
	public static final String COL_INT_FILE_LOG_ARCHIVED_TABLENAME = "ARCHIVED_TABLENAME";
	public static final String COL_INT_FILE_LOG_OUT_FILE_SEL = "OUT_FILE_SELECTION";
	public static final String COL_INT_FILE_LOG_USE_TEMP_TRAN_TABLE = "USE_TEMP_TRAN_TABLE";
	public static final String COL_INT_FILE_LOG_TEMP_TRAN_TABLE_NAME = "TEMP_TRAN_TABLE_NAME";
	public static final String COL_INT_FILE_LOG_PHASE_COPY_CMPL = "PHASE_COPY_COMPLETE";
	// Table INT_PROCESS_FILE_LOG
	public static final String TABLE_INT_PROCESS_FILE_LOG = "INT_PROCESS_FILE_LOG";
	// Table INT_PROCESS_FILE_LOG field names
	public static final String COL_INT_PROC_FILE_PRC_NUM = "PROCESS_NUMBER";
	public static final String COL_INT_PROC_FILE_INST_NUM = "INSTITUTION_NUMBER";
	public static final String COL_INT_PROC_FILE_FILE_NUM = "FILE_NUMBER";
	// Table INT_PROCESS_MESSAGE_LOG
	public static final String TABLE_INT_PROC_MSG_LOG = "INT_PROCESS_MESSAGE_LOG";
	// Table INT_PROCESS_MESSAGE_LOG field names
	public static final String COL_INT_PROC_MSG_LOG_MSG_NUM = "MESSAGE_NUMBER";
	public static final String COL_INT_PROC_MSG_LOG_PRC_NUM = "PROCESS_NUMBER";
	public static final String COL_INT_PROC_MSG_LOG_INST_NUM = "INSTITUTION_NUMBER";
	public static final String COL_INT_PROC_MSG_LOG_FILE_NUM = "FILE_NUMBER";
	public static final String COL_INT_PROC_MSG_LOG_TRN_SLIP = "TRANSACTION_SLIP";
	public static final String COL_INT_PROC_MSG_LOG_SYS_DATE = "SYSTEM_DATE";
	public static final String COL_INT_PROC_MSG_LOG_SYS_TIME = "SYSTEM_TIME";
	public static final String COL_INT_PROC_MSG_LOG_PRC_MSG_TYPE = "PROCESS_MESSAGE_TYPE";
	public static final String COL_INT_PROC_MSG_LOG_MSG_CODE = "MESSAGE_CODE";
	public static final String COL_INT_PROC_MSG_LOG_MSG_PARM_1 = "MESSAGE_PARAMETER_1";
	public static final String COL_INT_PROC_MSG_LOG_MSG_PARM_2 = "MESSAGE_PARAMETER_2";
	public static final String COL_INT_PROC_MSG_LOG_MSG_PARM_3 = "MESSAGE_PARAMETER_3";
	public static final String COL_INT_PROC_MSG_LOG_MSG_PARM_4 = "MESSAGE_PARAMETER_4";
	public static final String COL_INT_PROC_MSG_LOG_MSG_PARM_5 = "MESSAGE_PARAMETER_5";
	public static final String COL_INT_PROC_MSG_LOG_MSG_PARM_6 = "MESSAGE_PARAMETER_6";
	public static final String COL_INT_PROC_MSG_LOG_MSG_PARM_7 = "MESSAGE_PARAMETER_7";
	public static final String COL_INT_PROC_MSG_LOG_MSG_PARM_8 = "MESSAGE_PARAMETER_8";
	public static final String COL_INT_PROC_MSG_LOG_MSG_PARM_9 = "MESSAGE_PARAMETER_9";
	public static final String COL_INT_PROC_MSG_LOG_MSG_PARM_10 = "MESSAGE_PARAMETER_10";
	public static final String COL_INT_PROC_MSG_LOG_MSG_PARM_11 = "MESSAGE_PARAMETER_11";
	public static final String COL_INT_PROC_MSG_LOG_MSG_PARM_12 = "MESSAGE_PARAMETER_12";
	public static final String COL_INT_PROC_MSG_LOG_MSG_PARM_13 = "MESSAGE_PARAMETER_13";
	public static final String COL_INT_PROC_MSG_LOG_MSG_PARM_14 = "MESSAGE_PARAMETER_14";
	public static final String COL_INT_PROC_MSG_LOG_MSG_PARM_15 = "MESSAGE_PARAMETER_15";
	public static final String COL_INT_PROC_MSG_LOG_MSG_PARM_16 = "MESSAGE_PARAMETER_16";
	public static final String COL_INT_PROC_MSG_LOG_MSG_OCC = "MESSAGE_OCCURRENCE";
	public static final String COL_INT_PROC_MSG_LOG_PRC_MSG_SRC = "PROCESS_MESSAGE_SOURCE";
	public static final String COL_INT_PROC_MSG_LOG_EXT_CODE = "EXTERNAL_CODE";
	public static final String COL_INT_PROC_MSG_LOG_ADI_TEXT = "ADDITIONAL_TEXT";
	public static final String COL_INT_PROC_MSG_LOG_FNC_NAME = "FUNCTION_NAME";

	// Table INT_NON_FIN_AUTH
	public final static String TABLE_INT_NON_FIN_AUTH = "INT_NON_FIN_AUTH";
	public final static String COL_INT_NON_FIN_AUTH_INST_NUM = "INSTITUTION_NUMBER";
	public final static String COL_INT_NON_FIN_AUTH_REC_DATE = "RECORD_DATE";
	public final static String COL_INT_NON_FIN_AUTH_FILE_NUM = "FILE_NUMBER";
	public final static String COL_INT_NON_FIN_AUTH_TRN_ID = "TRANSACTION_ID";
	public final static String COL_INT_NON_FIN_AUTH_MSGTYPE = "MSGTYPE";
	public final static String COL_INT_NON_FIN_AUTH_PCODE = "PCODE";
	public final static String COL_INT_NON_FIN_AUTH_CARD_NUM = "CARD_NUMBER";
	public final static String COL_INT_NON_FIN_AUTH_AMT = "AMOUNT";
	public final static String COL_INT_NON_FIN_AUTH_CUR = "CURRENCY";
	public final static String COL_INT_NON_FIN_AUTH_AVAL_BALANCE = "AVAL_BALANCE";
	public final static String COL_INT_NON_FIN_AUTH_ISS = "ISSUER";
	public final static String COL_INT_NON_FIN_AUTH_ACQ = "ACQUIRER";
	public final static String COL_INT_NON_FIN_AUTH_TRN_DATE = "TRANSACTION_DATE";
	public final static String COL_INT_NON_FIN_AUTH_TRN_TIME = "TRANSACTION_TIME";
	public final static String COL_INT_NON_FIN_AUTH_TRACE = "TRACE";
	public final static String COL_INT_NON_FIN_AUTH_TXNSRC = "TXNSRC";
	public final static String COL_INT_NON_FIN_AUTH_TXNDEST = "TXNDEST";
	public final static String COL_INT_NON_FIN_AUTH_FILLER3 = "FILLER3";
	public final static String COL_INT_NON_FIN_AUTH_TERM_ID = "TERMINAL_ID";
	public final static String COL_INT_NON_FIN_AUTH_RETRIEVAL_REF = "RETRIEVAL_REFERENCE";
	public final static String COL_INT_NON_FIN_AUTH_ACC_NUM = "ACC_NUM";
	public final static String COL_INT_NON_FIN_AUTH_TRN_CLS = "TRANSACTION_CLASS";
	public final static String COL_INT_NON_FIN_AUTH_TRN_CATG = "TRANSACTION_CATEGORY";
	public final static String COL_INT_NON_FIN_AUTH_TRN_TYPE = "TRANSACTION_TYPE";
	public final static String COL_INT_NON_FIN_AUTH_TRN_SRC = "TRANSACTION_SOURCE";
	public final static String COL_INT_NON_FIN_AUTH_TRN_DEST = "TRANSACTION_DESTINATION";
	public final static String COL_INT_NON_FIN_AUTH_BUSS_CLS = "BUSINESS_CLASS";
	public final static String COL_INT_NON_FIN_AUTH_CLNT_NUM = "CLIENT_NUMBER";
	public final static String COL_INT_NON_FIN_AUTH_GRP_NUM = "GROUP_NUMBER";
	public final static String COL_INT_NON_FIN_AUTH_SRV_CNTR_ID = "SERVICE_CONTRACT_ID";
	public final static String COL_INT_NON_FIN_AUTH_ACCT_NUM = "ACCT_NUMBER";
	public final static String COL_INT_NON_FIN_AUTH_AUTHORIZED_BY = "AUTHORIZED_BY";

	// ****************************** CBR Table Names
	// ***************************
	// Table CBR_CHANNEL_DEFINITION
	public static final String TABLE_CBR_CHANNEL_DEF = "CBR_CHANNEL_DEFINITION";
	// Table CBR_CHANNEL_DEFINITION field names
	public static final String COL_CBR_CHANNEL_DEF_REC_DATE = "RECORD_DATE";
	public static final String COL_CBR_CHANNEL_DEF_INST_NUM = "INSTITUTION_NUMBER";
	public static final String COL_CBR_CHANNEL_DEF_CARD_ORGA = "CARD_ORGANIZATION";
	public static final String COL_CBR_CHANNEL_DEF_AREA_OF_EVENT = "AREA_OF_EVENT";
	public static final String COL_CBR_CHANNEL_DEF_TRN_DEST = "TRANSACTION_DESTINATION";
	public static final String COL_CBR_CHANNEL_DEF_AUDIT_TRAIL = "AUDIT_TRAIL";
	public static final String COL_CBR_CHANNEL_DEF_CLNT_NUM = "CLIENT_NUMBER";
	public static final String COL_CBR_CHANNEL_DEF_TRN_SRC = "TRANSACTION_SOURCE";
	public static final String COL_CBR_CHANNEL_DEF_SETL_CUR = "SETTLEMENT_CURRENCY";
	public static final String COL_CBR_CHANNEL_DEF_SRV_TYPE = "SERVICE_TYPE";
	public static final String COL_CBR_CHANNEL_DEF_CLNT_BUSS_TYPE = "CLIENT_BUSINESS_TYPE";
	public static final String COL_CBR_CHANNEL_DEF_CHG_TYPE = "CHARGE_TYPE";
	public static final String COL_CBR_CHANNEL_DEF_INTR_INST = "INTERCHANGE_INSTITUTION";
	public static final String COL_CBR_CHANNEL_DEF_REC_TYPE = "RECORD_TYPE";
	// public static final String COL_CBR_CHANNEL_DEF_TRN_FRMT =
	// "TRANSACTION_FORMAT";
	public static final String COL_CBR_CHANNEL_DEF_TRN_CUR = "TRAN_CURRENCY";
	// Table CBR_SERVICE_CONTRACT
	public static final String TABLE_CBR_SVR_CNTR = "CBR_SERVICE_CONTRACT";
	// Table CBR_SERVICE_CONTRACT field names
	public static final String COL_CBR_SVR_CNTR_REC_DATE = "RECORD_DATE";
	public static final String COL_CBR_SVR_CNTR_INST_NUM = "INSTITUTION_NUMBER";
	public static final String COL_CBR_SVR_CNTR_ID = "SERVICE_CONTRACT_ID";
	public static final String COL_CBR_SVR_CNTR_NOTE_TXT = "NOTE_TEXT";
	public static final String COL_CBR_SVR_CNTR_AUDIT_TRAIL = "AUDIT_TRAIL";
	public static final String COL_CBR_SVR_CNTR_TYPE = "CONTRACT_TYPE";
	public static final String COL_CBR_SVR_CNTR_REVIEW_PERIOD = "REVIEW_PERIOD";

	// ****************************** XFC Table Names
	// ***************************
	/*
	 * //Table XFC_BATCH_DATA public static final String TABLE_XFC_BATCH_DATA =
	 * "XFC_BATCH_DATA"; //Table XFC_BATCH_DATA field names public static final
	 * String COL_XFC_BATCH_INST_NUM = "INSTITUTION_NUMBER"; public static final
	 * String COL_XFC_BATCH_FILE_NUM = "FILE_NUMBER"; public static final String
	 * COL_XFC_BATCH_TRN_SLIP = "TRANSACTION_SLIP"; public static final String
	 * COL_XFC_BATCH_NUM_ORG_SLIP = "NUMBER_ORIGINAL_SLIP"; public static final
	 * String COL_XFC_BATCH_TRN_SRC = "TRANSACTION_SOURCE"; public static final
	 * String COL_XFC_BATCH_TRN_CUR = "TRAN_CURRENCY"; public static final
	 * String COL_XFC_BATCH_SETL_CUR = "SETTLEMENT_CURRENCY"; public static
	 * final String COL_XFC_BATCH_CLNT_NUM = "CLIENT_NUMBER"; public static
	 * final String COL_XFC_BATCH_ACQ_REF = "ACQUIRER_REFERENCE"; public static
	 * final String COL_XFC_BATCH_TRN_STA = "TRANSACTION_STATUS"; public static
	 * final String COL_XFC_BATCH_REV_FLAG = "REVERSAL_FLAG"; public static
	 * final String COL_XFC_BATCH_NUM_SLIPS = "NUMBER_SLIPS"; public static
	 * final String COL_XFC_BATCH_MERCH_NUM = "MERCHANT_NUMBER"; public static
	 * final String COL_XFC_BATCH_ORG_REF_NUM = "ORIGINAL_REF_NUMBER"; public
	 * static final String COL_XFC_BATCH_TRN_DATE = "TRANSACTION_DATE"; public
	 * static final String COL_XFC_BATCH_TERMINAL_ID = "TERMINAL_ID";
	 * 
	 * //Table XFC_TRANSACTION_DATA public static final String
	 * TABLE_XFC_TRANSACTION_DATA = "XFC_TRANSACTION_DATA"; //Table
	 * XFC_TRANSACTION_DATA field names public static final String
	 * COL_XFC_TRN_INST_NUM = "INSTITUTION_NUMBER"; public static final String
	 * COL_XFC_TRN_FILE_NUM = "FILE_NUMBER"; public static final String
	 * COL_XFC_TRN_TRN_SLIP = "TRANSACTION_SLIP"; public static final String
	 * COL_XFC_TRN_SUMMARY_SETL = "SUMMARY_SETTLEMENT"; public static final
	 * String COL_XFC_TRN_NUM_ORG_SLIP = "NUMBER_ORIGINAL_SLIP"; public static
	 * final String COL_XFC_TRN_TRN_STA = "TRANSACTION_STATUS"; public static
	 * final String COL_XFC_TRN_TRN_SRC = "TRANSACTION_SOURCE"; public static
	 * final String COL_XFC_TRN_REV_FLAG = "REVERSAL_FLAG"; public static final
	 * String COL_XFC_TRN_TRN_DATE = "TRANSACTION_DATE"; public static final
	 * String COL_XFC_TRN_TRN_TIME = "TRANSACTION_TIME"; public static final
	 * String COL_XFC_TRN_BUSS_CLS = "BUSINESS_CLASS"; public static final
	 * String COL_XFC_TRN_TRN_CLS = "TRANSACTION_CLASS"; public static final
	 * String COL_XFC_TRN_TRN_CATG = "TRANSACTION_CATEGORY"; public static final
	 * String COL_XFC_TRN_TRN_TYPE = "TRANSACTION_TYPE"; public static final
	 * String COL_XFC_TRN_CAPT_METH = "CAPTURE_METHOD"; public static final
	 * String COL_XFC_TRN_TERM_CAPA = "TERMINAL_CAPABILITY"; public static final
	 * String COL_XFC_TRN_CARD_NUM = "CARD_NUMBER"; public static final String
	 * COL_XFC_TRN_AUTH_CODE = "AUTH_CODE"; public static final String
	 * COL_XFC_TRN_TRAN_CUR = "TRAN_CURRENCY"; public static final String
	 * COL_XFC_TRN_TRAN_AMT_GR = "TRAN_AMOUNT_GR"; public static final String
	 * COL_XFC_TRN_ACQ_REF = "ACQUIRER_REFERENCE"; public static final String
	 * COL_XFC_TRN_RETRIEVAL_REF = "RETRIEVAL_REFERENCE"; public static final
	 * String COL_XFC_TRN_CARD_EXP_DATE = "CARD_EXPIRATION_DATE"; public static
	 * final String COL_XFC_TRN_MERCH_NAME = "MERCHANT_NAME"; public static
	 * final String COL_XFC_TRN_MERCH_CITY = "MERCHANT_CITY"; public static
	 * final String COL_XFC_TRN_TERL_ID = "TERMINAL_ID"; public static final
	 * String COL_XFC_TRN_CARD_ORGA = "CARD_ORGANIZATION"; public static final
	 * String COL_XFC_TRN_TRAN_AUTH = "TRAN_AUTHORIZED"; public static final
	 * String COL_XFC_TRN_TRAN_SECURED = "TRAN_SECURED"; public static final
	 * String COL_XFC_TRN_CASH_BACK_AMT = "CASHBACK_AMOUNT"; public static final
	 * String COL_XFC_TRN_LOCAL_CUR = "LOCAL_CURRENCY"; public static final
	 * String COL_XFC_TRN_LOCAL_AMT_GR = "LOCAL_AMOUNT_GR"; public static final
	 * String COL_XFC_TRN_DCC_IND = "DCC_INDICATOR"; public static final String
	 * COL_XFC_TRN_MERCH_COUNTRY = "MERCHANT_COUNTRY"; public static final
	 * String COL_XFC_TRN_MERCH_STATE = "MERCHANT_STATE"; public static final
	 * String COL_XFC_TRN_MERCH_POST_CODE = "MERCHANT_POST_CODE"; public static
	 * final String COL_XFC_TRN_ORG_REF_NUM = "ORIGINAL_REF_NUMBER"; public
	 * static final String COL_XFC_TRN_CARD_BRAND = "CARD_BRAND"; public static
	 * final String COL_XFC_TRN_UCAF_IND = "UCAF_INDICATOR"; public static final
	 * String COL_XFC_TRN_CHNL_DATA = "CHANNEL_DATA"; public static final String
	 * COL_XFC_TRN_ADD_TYPE = "ADDENDUM_TYPE"; public static final String
	 * COL_XFC_TRN_TRACE_ID = "TRACE_ID"; public static final String
	 * COL_XFC_TRN_ADD_LST = "ADDENDUM_LIST"; public static final String
	 * COL_XFC_TRN_AUTH_AMNT = "AUTH_AMOUNT"; public static final String
	 * COL_XFC_TRN_AUTH_CUR = "AUTH_CURRENCY"; public static final String
	 * COL_XFC_TRN_FRWD_MEMB_ID = "FORWARDING_MEMBER_ID"; public static final
	 * String COL_XFC_TRN_SETTL_CUR = "SETTLEMENT_CURRENCY"; public static final
	 * String COL_XFC_TRN_SETTL_AMNT_GR = "SETTLEMENT_AMOUNT_GR"; public static
	 * final String COL_XFC_TRN_SETTL_AMNT_OUTCHG = "SETTLEMENT_AMOUNT_OUT_CHG";
	 * public static final String COL_XFC_TRN_SETTL_AMNT_CHG =
	 * "SETTLEMENT_AMOUNT_CHG";
	 * 
	 * 
	 * 
	 * //Table XFC_ADDENDUM_ISO public static final String
	 * TABLE_XFC_ADDENDUM_ISO = "XFC_ADDENDUM_ISO"; //Table XFC_ADDENDUM_ISO
	 * field names public static final String COL_XFC_ADD_ISO_INST_NUM =
	 * "INSTITUTION_NUMBER"; public static final String COL_XFC_ADD_ISO_FILE_NUM =
	 * "FILE_NUMBER"; public static final String COL_XFC_ADD_ISO_TRN_SLIP =
	 * "TRANSACTION_SLIP"; public static final String
	 * COL_XFC_ADD_ISO_ISO_8583_DATA = "ISO_8583_DATA"; //Table
	 * XFC_ADDENDUM_LIMITED_USE public static final String TABLE_XFC_ADD_LIM_USE =
	 * "XFC_ADDENDUM_LIMITED_USE"; //Table XFC_ADDENDUM_LIMITED_USE field names
	 * public static final String COL_XFC_ADD_LIM_USE_INST_NUM =
	 * "INSTITUTION_NUMBER"; public static final String
	 * COL_XFC_ADD_LIM_USE_FILE_NUM = "FILE_NUMBER"; public static final String
	 * COL_XFC_ADD_LIM_USE_TRN_SLIP = "TRANSACTION_SLIP"; public static final
	 * String COL_XFC_ADD_LIM_USE_MSG_ID = "MESSAGE_IDENTIFIER"; public static
	 * final String COL_XFC_ADD_LIM_USE_MERCH_ORDER_NUM =
	 * "MERCHANT_ORDER_NUMBER"; public static final String
	 * COL_XFC_ADD_LIM_USE_CUS_VAT_NUM = "CUSTOMER_VAT_NUMBER"; public static
	 * final String COL_XFC_ADD_LIM_USE_COMD_CODE = "COMMODITY_CODE"; public
	 * static final String COL_XFC_ADD_LIM_USE_MERCH_VAT_NUM =
	 * "MERCHANT_VAT_NUMBER"; public static final String
	 * COL_XFC_ADD_LIM_USE_NATIONAL_TAX_AMT = "NATIONAL_TAX_AMOUNT"; public
	 * static final String COL_XFC_ADD_LIM_USE_NATIONAL_TAX_INCLUDED =
	 * "NATIONAL_TAX_INCLUDED"; public static final String
	 * COL_XFC_ADD_LIM_USE_OTHER_TAX_AMT = "OTHER_TAX_AMOUNT"; public static
	 * final String COL_XFC_ADD_LIM_USE_LOCAL_TAX_AMT = "LOCAL_TAX_AMOUNT";
	 * public static final String COL_XFC_ADD_LIM_USE_LOCAL_TAX_INCLUDED =
	 * "LOCAL_TAX_INCLUDED"; public static final String
	 * COL_XFC_ADD_LIM_USE_TIME_OF_PURCHASE = "TIME_OF_PURCHASE"; public static
	 * final String COL_XFC_ADD_LIM_USE_CUS_CODE = "CUSTOMER_CODE";
	 * 
	 * 
	 * 
	 * //Table XFC_ADDENDUM_LINE_ITEM public static final String
	 * TABLE_XFC_ADD_LINE_ITEM = "XFC_ADDENDUM_LINE_ITEM"; //Table
	 * XFC_ADDENDUM_LINE_ITEM field names public static final String
	 * COL_XFC_ADD_LINE_ITEM_INST_NUM = "INSTITUTION_NUMBER"; public static
	 * final String COL_XFC_ADD_LINE_ITEM_FILE_NUM = "FILE_NUMBER"; public
	 * static final String COL_XFC_ADD_LINE_ITEM_TRN_SLIP = "TRANSACTION_SLIP";
	 * public static final String COL_XFC_ADD_LINE_ITEM_ITEM_SEQ_NO =
	 * "ITEM_SEQ_NO"; public static final String COL_XFC_ADD_LINE_ITEM_MSG_ID =
	 * "MESSAGE_IDENTIFIER"; public static final String
	 * COL_XFC_ADD_LINE_ITEM_SRV_ID = "SERVICE_ID"; public static final String
	 * COL_XFC_ADD_LINE_ITEM_DISCOUNT_AMT = "DISCOUNT_AMOUNT"; public static
	 * final String COL_XFC_ADD_LINE_ITEM_SHIPPING_AMT = "SHIPPING_AMOUNT";
	 * public static final String COL_XFC_ADD_LINE_ITEM_DUTY_AMT =
	 * "DUTY_AMOUNT"; public static final String
	 * COL_XFC_ADD_LINE_ITEM_DEST_POST_CODE = "DESTINATION_POST_CODE"; public
	 * static final String COL_XFC_ADD_LINE_ITEM_SHIP_POST_CODE =
	 * "SHIP_POST_CODE"; public static final String
	 * COL_XFC_ADD_LINE_ITEM_DEST_COUNTRY_CODE = "DESTINATION_COUNTRY_CODE";
	 * public static final String COL_XFC_ADD_LINE_ITEM_VAT_REF_NO =
	 * "VAT_REFERENCE_NO"; public static final String
	 * COL_XFC_ADD_LINE_ITEM_ORDER_DATE = "ORDER_DATE"; public static final
	 * String COL_XFC_ADD_LINE_ITEM_FREIGHT_TAX_AMT = "FREIGHT_TAX_AMOUNT";
	 * public static final String COL_XFC_ADD_LINE_ITEM_FREIGHT_TAX_RATE =
	 * "FREIGHT_TAX_RATE"; public static final String
	 * COL_XFC_ADD_LINE_ITEM_ITEM_COMD_CODE = "ITEM_COMMODITY_CODE"; public
	 * static final String COL_XFC_ADD_LINE_ITEM_ITEM_DESCRIPTOR =
	 * "ITEM_DESCRIPTOR"; public static final String
	 * COL_XFC_ADD_LINE_ITEM_PRODUCT_CODE = "PRODUCT_CODE"; public static final
	 * String COL_XFC_ADD_LINE_ITEM_QUANTITY = "QUANTITY"; public static final
	 * String COL_XFC_ADD_LINE_ITEM_UNIT_MEASURE_CODE = "UNIT_MEASURE_CODE";
	 * public static final String COL_XFC_ADD_LINE_ITEM_UNIT_COST = "UNIT_COST";
	 * public static final String COL_XFC_ADD_LINE_ITEM_VAT_AMT = "VAT_AMOUNT";
	 * public static final String COL_XFC_ADD_LINE_ITEM_VAT_RATE = "VAT_RATE";
	 * public static final String COL_XFC_ADD_LINE_ITEM_LINE_ITEM_TOTAL =
	 * "LINE_ITEM_TOTAL"; public static final String
	 * COL_XFC_ADD_LINE_ITEM_LINE_ITEM_DETAIL_IND = "LINE_ITEM_DETAIL_IND";
	 * public static final String COL_XFC_ADD_LINE_ITEM_MERCH_POST_CODE =
	 * "MERCHANT_POST_CODE"; public static final String
	 * COL_XFC_ADD_LINE_ITEM_MERCH_STATE = "MERCHANT_STATE"; public static final
	 * String COL_XFC_ADD_LINE_ITEM_CUS_CODE = "CUSTOMER_CODE"; public static
	 * final String COL_XFC_ADD_LINE_ITEM_MERCH_TYPE = "MERCHANT_TYPE"; public
	 * static final String COL_XFC_ADD_LINE_ITEM_MERCH_TAX_ID =
	 * "MERCHANT_TAX_ID"; public static final String
	 * COL_XFC_ADD_LINE_ITEM_MERCH_REF_NO = "MERCHANT_REF_NUMBER"; public static
	 * final String COL_XFC_ADD_LINE_ITEM_SALES_TAX_COLLECTED_IDX =
	 * "SALES_TAX_COLLECTED_IND"; public static final String
	 * COL_XFC_ADD_LINE_ITEM_ALT_TAX_AMT = "ALT_TAX_AMOUNT"; public static final
	 * String COL_XFC_ADD_LINE_ITEM_ALT_TAX_AMT_IDX = "ALT_TAX_AMOUNT_IND";
	 * public static final String COL_XFC_ADD_LINE_ITEM_EXT_ITEM_AMT_DR_CR_IND =
	 * "EXT_ITEM_AMOUNT_DR_CR_IND"; public static final String
	 * COL_XFC_ADD_LINE_ITEM_EXT_ITEM_AMT_IND = "EXT_ITEM_AMOUNT_IND"; public
	 * static final String COL_XFC_ADD_LINE_ITEM_DISCOUNT_IND =
	 * "DISCOUNT_INDICATOR"; public static final String
	 * COL_XFC_ADD_LINE_ITEM_TAX_TYPE = "TAX_TYPE"; public static final String
	 * COL_XFC_ADD_LINE_ITEM_NAICS_CODE = "NAICS_CODE"; public static final
	 * String COL_XFC_ADD_LINE_ITEM_DISCOUNT_RATE = "DISCOUNT_RATE";
	 * 
	 * //Table XFC_ADDENDUM_MERCHANT public static final String
	 * TABLE_XFC_ADD_MERCHANT = "XFC_ADDENDUM_MERCHANT"; //Table
	 * XFC_ADDENDUM_MERCHANT field names public static final String
	 * COL_XFC_ADD_MERCH_INST_NUM = "INSTITUTION_NUMBER"; public static final
	 * String COL_XFC_ADD_MERCH_FILE_NUM = "FILE_NUMBER"; public static final
	 * String COL_XFC_ADD_MERCH_TRN_SLIP = "TRANSACTION_SLIP"; public static
	 * final String COL_XFC_ADD_MERCH_SUNDRY_TYPE = "SUNDRY_TYPE"; public static
	 * final String COL_XFC_ADD_MERCH_SUNDRY_REASON = "SUNDRY_REASON"; public
	 * static final String COL_XFC_ADD_MERCH_SUNDRY_STA = "SUNDRY_STATUS";
	 * public static final String COL_XFC_ADD_MERCH_DOC_CODE = "DOCUMENT_CODE";
	 * public static final String COL_XFC_ADD_MERCH_MSG_SUNDRY_TEXT =
	 * "MESSAGE_SUNDRY_TEXT"; //Table XFC_ADDENDUM_SMART_CARD public static
	 * final String TABLE_XFC_ADD_SMART_CARD= "XFC_ADDENDUM_SMART_CARD"; //Table
	 * XFC_ADDENDUM_SMART_CARD field names public static final String
	 * COL_XFC_ADD_SCARD_INST_NUM = "INSTITUTION_NUMBER"; public static final
	 * String COL_XFC_ADD_SCARD_FILE_NUM = "FILE_NUMBER"; public static final
	 * String COL_XFC_ADD_SCARD_TRN_SLIP = "TRANSACTION_SLIP"; public static
	 * final String COL_XFC_ADD_SCARD_CRYPT_TRN_TYPE =
	 * "CRYPTOGRAM_TRANSACTION_TYPE"; public static final String
	 * COL_XFC_ADD_SCARD_CARD_SEQ_NUM = "CARD_SEQUENCE_NUMBER"; public static
	 * final String COL_XFC_ADD_SCARD_TER_TRN_DATE =
	 * "TERMINAL_TRANSACTION_DATE"; public static final String
	 * COL_XFC_ADD_SCARD_TER_CAPA_PROFILE = "TERMINAL_CAPABILITY_PROFILE";
	 * public static final String COL_XFC_ADD_SCARD_TER_COUNTRY_CODE =
	 * "TERMINAL_COUNTRY_CODE"; public static final String
	 * COL_XFC_ADD_SCARD_TER_SERIAL_NUM = "TERMINAL_SERIAL_NUMBER"; public
	 * static final String COL_XFC_ADD_SCARD_UNPREDICTABLE_NUMBER =
	 * "UNPREDICTABLE_NUMBER"; public static final String
	 * COL_XFC_ADD_SCARD_APP_TRAN_COUNTER = "APPLICATION_TRAN_COUNTER"; public
	 * static final String COL_XFC_ADD_SCARD_APP_INTER_PROFILE =
	 * "APPLICATION_INTER_PROFILE"; public static final String
	 * COL_XFC_ADD_SCARD_CRYPT = "CRYPTOGRAM"; public static final String
	 * COL_XFC_ADD_SCARD_DERIVATION_KEY_INDEX = "DERIVATION_KEY_INDEX"; public
	 * static final String COL_XFC_ADD_SCARD_CRYPT_VER = "CRYPTOGRAM_VERSION";
	 * public static final String COL_XFC_ADD_SCARD_TERM_VERIFICATION_RESULTS =
	 * "TERM_VERIFICATION_RESULTS"; public static final String
	 * COL_XFC_ADD_SCARD_CARD_VERIFICATION_RESULTS =
	 * "CARD_VERIFICATION_RESULTS"; public static final String
	 * COL_XFC_ADD_SCARD_ISS_SCRIPT_1_RESULTS = "ISSUER_SCRIPT_1_RESULTS";
	 * public static final String COL_XFC_ADD_SCARD_POS_ENTRY_CAPA =
	 * "POS_ENTRY_CAPABILITY"; public static final String
	 * COL_XFC_ADD_SCARD_CHIP_CONDITION_CODE = "CHIP_CONDITION_CODE"; public
	 * static final String COL_XFC_ADD_SCARD_CVV_RESULTS_CODE =
	 * "CVV_RESULTS_CODE"; public static final String COL_XFC_ADD_SCARD_AUTH_AMT =
	 * "AUTHORIZED_AMOUNT"; public static final String
	 * COL_XFC_ADD_SCARD_AUTH_CUR = "AUTHORIZED_CURRENCY"; public static final
	 * String COL_XFC_ADD_SCARD_RESPONSE_CODE = "RESPONSE_CODE"; public static
	 * final String COL_XFC_ADD_SCARD_AUTH_RESP_CRYPT = "AUTH_RESP_CRYPTOGRAM";
	 * public static final String COL_XFC_ADD_SCARD_AUTH_RESP_CRYPT_CODE =
	 * "AUTH_RESP_CRYPTOGRAM_CODE"; public static final String
	 * COL_XFC_ADD_SCARD_CARD_AUTHEN_RESULTS_CODE = "CARD_AUTHEN_RESULTS_CODE";
	 * public static final String COL_XFC_ADD_SCARD_CRYPT_AMT =
	 * "CRYPTOGRAM_AMOUNT"; public static final String
	 * COL_XFC_ADD_SCARD_CRYPT_CUR_CODE = "CRYPTOGRAM_CURRENCY_CODE"; public
	 * static final String COL_XFC_ADD_SCARD_CRYPT_CASHBACK_AMT =
	 * "CRYPTOGRAM_CASHBACK_AMOUNT"; public static final String
	 * COL_XFC_ADD_SCARD_ISS_DISCRETIONARY_DATA = "ISSUER_DISCRETIONARY_DATA";
	 * public static final String COL_XFC_ADD_SCARD_CARD_AUTHEN_RELIABILITY_IND =
	 * "CARD_AUTHEN_RELIABILITY_IND"; public static final String
	 * COL_XFC_ADD_SCARD_TERM_TYPE = "TERMINAL_TYPE"; public static final String
	 * COL_XFC_ADD_SCARD_TRANS_CATG_CODE = "TRANS_CATEGORY_CODE"; public static
	 * final String COL_XFC_ADD_SCARD_DEDICATED_FILE_NAME =
	 * "DEDICATED_FILE_NAME"; public static final String
	 * COL_XFC_ADD_SCARD_TERM_APPL_VER_NUM = "TERMINAL_APPL_VER_NUMBER"; public
	 * static final String COL_XFC_ADD_SCARD_TRANS_SEQ_NUM =
	 * "TRANS_SEQUENCE_NUMBER";
	 * 
	 * //Table XFC_ADDENDUM_AUTO_RENTAL public static final String
	 * TABLE_XFC_ADD_AUTO = "XFC_ADDENDUM_AUTO_RENTAL"; //Table
	 * XFC_ADDENDUM_AUTO_RENTAL field names public static final String
	 * COL_XFC_ADD_AUTO_INST_NUM = "INSTITUTION_NUMBER"; public static final
	 * String COL_XFC_ADD_AUTO_FILE_NUM = "FILE_NUMBER"; public static final
	 * String COL_XFC_ADD_AUTO_TRN_SLIP = "TRANSACTION_SLIP"; public static
	 * final String COL_XFC_ADD_AUTO_ADD_NUM = "ADDENDUM_NUMBER"; public static
	 * final String COL_XFC_ADD_AUTO_EST_NAME = "ESTABLISHMENT_NAME"; public
	 * static final String COL_XFC_ADD_AUTO_EST_ADR = "ESTABLISHMENT_ADDRESS";
	 * public static final String COL_XFC_ADD_AUTO_EST_TYPE =
	 * "ESTABLISHMENT_TYPE"; public static final String
	 * COL_XFC_ADD_AUTO_RENTAL_NUM = "RENTAL_NUMBER"; public static final String
	 * COL_XFC_ADD_AUTO_RENTAL_DATE = "RENTAL_DATE"; public static final String
	 * COL_XFC_ADD_AUTO_RENTAL_TIME = "RENTAL_TIME"; public static final String
	 * COL_XFC_ADD_AUTO_RENTAL_ADR = "RENTAL_ADDRESS"; public static final
	 * String COL_XFC_ADD_AUTO_RENTAL_NAME = "RENTAL_NAME"; public static final
	 * String COL_XFC_ADD_AUTO_RETURN_ADR = "RETURN_ADDRESS"; public static
	 * final String COL_XFC_ADD_AUTO_RETURN_TIME = "RETURN_TIME"; public static
	 * final String COL_XFC_ADD_AUTO_RETURN_DATE = "RETURN_DATE"; public static
	 * final String COL_XFC_ADD_AUTO_RETURN_CITY = "RETURN_CITY"; public static
	 * final String COL_XFC_ADD_AUTO_RETURN_COUNTRY = "RETURN_COUNTRY"; public
	 * static final String COL_XFC_ADD_AUTO_RETURN_STATE = "RETURN_STATE";
	 * public static final String COL_XFC_ADD_AUTO_RETURN_LOCATION_ID =
	 * "RETURN_LOCATION_ID"; public static final String
	 * COL_XFC_ADD_AUTO_TOTAL_DISTANCE = "TOTAL_DISTANCE"; public static final
	 * String COL_XFC_ADD_AUTO_CHECKOUT_DATE = "CHECKOUT_DATE"; public static
	 * final String COL_XFC_ADD_AUTO_NO_SHOW_IND = "NO_SHOW_INDICATOR"; public
	 * static final String COL_XFC_ADD_AUTO_MILE_KM_IND = "MILE_KM_INDICATOR";
	 * public static final String COL_XFC_ADD_AUTO_EXTRA_CHARGES_1 =
	 * "EXTRA_CHARGES_1"; public static final String
	 * COL_XFC_ADD_AUTO_EXTRA_CHARGES_2 = "EXTRA_CHARGES_2"; public static final
	 * String COL_XFC_ADD_AUTO_EXTRA_CHARGES_3 = "EXTRA_CHARGES_3"; public
	 * static final String COL_XFC_ADD_AUTO_EXTRA_CHARGES_4 = "EXTRA_CHARGES_4";
	 * public static final String COL_XFC_ADD_AUTO_EXTRA_CHARGES_5 =
	 * "EXTRA_CHARGES_5"; public static final String
	 * COL_XFC_ADD_AUTO_EXTRA_CHARGES_6 = "EXTRA_CHARGES_6"; //Table
	 * XFC_ADDENDUM_HOTEL_LODGING public static final String TABLE_XFC_ADD_HOTEL =
	 * "XFC_ADDENDUM_HOTEL_LODGING"; //Table XFC_ADDENDUM_HOTEL_LODGING field
	 * names public static final String COL_XFC_ADD_HOTEL_INST_NUM =
	 * "INSTITUTION_NUMBER"; public static final String
	 * COL_XFC_ADD_HOTEL_FILE_NUM = "FILE_NUMBER"; public static final String
	 * COL_XFC_ADD_HOTEL_TRN_SLIP = "TRANSACTION_SLIP"; public static final
	 * String COL_XFC_ADD_HOTEL_ADD_NUM = "ADDENDUM_NUMBER"; public static final
	 * String COL_XFC_ADD_HOTEL_FOLIO_NUM = "FOLIO_NUMBER"; public static final
	 * String COL_XFC_ADD_HOTEL_NO_SHOW_IND = "NO_SHOW_INDICATOR"; public static
	 * final String COL_XFC_ADD_HOTEL_EXTRA_CHARGES_1 = "EXTRA_CHARGES_1";
	 * public static final String COL_XFC_ADD_HOTEL_EXTRA_CHARGES_2 =
	 * "EXTRA_CHARGES_2"; public static final String
	 * COL_XFC_ADD_HOTEL_EXTRA_CHARGES_3 = "EXTRA_CHARGES_3"; public static
	 * final String COL_XFC_ADD_HOTEL_EXTRA_CHARGES_4 = "EXTRA_CHARGES_4";
	 * public static final String COL_XFC_ADD_HOTEL_EXTRA_CHARGES_5 =
	 * "EXTRA_CHARGES_5"; public static final String
	 * COL_XFC_ADD_HOTEL_EXTRA_CHARGES_6 = "EXTRA_CHARGES_6"; public static
	 * final String COL_XFC_ADD_HOTEL_ARRIVAL_DATE = "ARRIVAL_DATE"; //Table
	 * XFC_ADDENDUM_ADDITIONAL_DATA public static final String
	 * TABLE_XFC_ADD_ADT_DATA = "XFC_ADDENDUM_ADDITIONAL_DATA"; //Table
	 * XFC_ADDENDUM_ADDITIONAL_DATA field names public static final String
	 * COL_XFC_ADD_ADT_DATA_INST_NUM = "INSTITUTION_NUMBER"; public static final
	 * String COL_XFC_ADD_ADT_DATA_FILE_NUM = "FILE_NUMBER"; public static final
	 * String COL_XFC_ADD_ADT_DATA_TRN_SLIP = "TRANSACTION_SLIP"; public static
	 * final String COL_XFC_ADD_ADT_DATA_SUBMISSION_ID = "SUBMISSION_ID"; public
	 * static final String COL_XFC_ADD_ADT_DATA_AUTH_DATE = "AUTH_DATE"; public
	 * static final String COL_XFC_ADD_ADT_DATA_SEQ_NUM = "SEQUENCE_NUMBER";
	 * public static final String COL_XFC_ADD_ADT_DATA_DCC_CONV_FLAG =
	 * "DCC_CONVERSION_FLAG"; public static final String
	 * COL_XFC_ADD_ADT_DATA_DCC_ISS_CUR_CODE = "DCC_ISSUER_CURR_CODE"; public
	 * static final String COL_XFC_ADD_ADT_DATA_DCC_ISS_AMT =
	 * "DCC_ISSUER_AMOUNT"; public static final String
	 * COL_XFC_ADD_ADT_DATA_CUS_DATA = "CUSTOM_DATA"; public static final String
	 * COL_XFC_ADD_ADT_DATA_FEE_SEQ_NUM = "FEE_SEQUENCE_NUMBER"; public static
	 * final String COL_XFC_ADD_ADT_DATA_ACQ_REF = "ACQUIRER_REFERENCE"; public
	 * static final String COL_XFC_ADD_ADT_DATA_GMT_TIMEZONE = "GMT_TIMEZONE";
	 * public static final String COL_XFC_ADD_ADT_DATA_ADJ_IND =
	 * "ADJUSTMENT_INDICATOR"; public static final String
	 * COL_XFC_ADD_ADT_DATA_AMEX_CHG_DESCR = "AMEX_CHARGE_DESCRIPTOR"; public
	 * static final String COL_XFC_ADD_ADT_DATA_BUS_TYPE = "BUSINESS_TYPE";
	 * public static final String COL_XFC_ADD_ADT_DATA_ORG_CUS_DATA =
	 * "ORIGINAL_CUSTOM_DATA"; public static final String
	 * COL_XFC_ADD_ADT_DATA_INT_MERCH_ACCT = "INTERNAL_MERCHANT_ACCT"; public
	 * static final String COL_XFC_ADD_ADT_DATA_CRD_HLD_NAME =
	 * "CARDHOLDER_NAME"; public static final String
	 * COL_XFC_ADD_ADT_DATA_AV_CODE = "AV_CODE"; public static final String
	 * COL_XFC_ADD_ADT_DATA_ORDER_DATE = "ORDER_DATE"; public static final
	 * String COL_XFC_ADD_ADT_DATA_PRODUCT_SRV_DESC = "PRODUCT_SERVICE_DESC";
	 * public static final String COL_XFC_ADD_ADT_DATA_SHIPPING_ADR =
	 * "SHIPPING_ADDRESS";
	 * 
	 * //Table XFC_ADDENDUM_PAYMENT_SERVICE public static final String
	 * TABLE_XFC_ADD_PAY_SRV = "XFC_ADDENDUM_PAYMENT_SERVICE"; //Table
	 * XFC_ADDENDUM_PAYMENT_SERVICE field names public static final String
	 * COL_XFC_ADD_PAY_SRV_INST_NUM = "INSTITUTION_NUMBER"; public static final
	 * String COL_XFC_ADD_PAY_SRV_FILE_NUM = "FILE_NUMBER"; public static final
	 * String COL_XFC_ADD_PAY_SRV_TRN_SLIP = "TRANSACTION_SLIP"; public static
	 * final String COL_XFC_ADD_PAY_SRV_AUTH_CUR = "AUTHORIZED_CURRENCY"; public
	 * static final String COL_XFC_ADD_PAY_SRV_AUTH_AMT = "AUTHORIZED_AMOUNT";
	 * public static final String COL_XFC_ADD_PAY_SRV_RESPONSE_CODE =
	 * "RESPONSE_CODE"; public static final String COL_XFC_ADD_PAY_SRV_AUTH_IND =
	 * "AUTHORIZATION_INDICATOR"; public static final String
	 * COL_XFC_ADD_PAY_SRV_VALIDATION_CODE = "VALIDATION_CODE"; public static
	 * final String COL_XFC_ADD_PAY_SRV_TRAN_ID = "TRANSACTION_IDENTIFIER";
	 * public static final String COL_XFC_ADD_PAY_SRV_AUTH_DATA_IND =
	 * "AUTH_DATA_INDICATOR"; public static final String
	 * COL_XFC_ADD_PAY_SRV_TOTAL_AUTH_AMT = "TOTAL_AUTHORIZED_AMOUNT"; public
	 * static final String COL_XFC_ADD_PAY_SRV_INFO_IND =
	 * "INFORMATION_INDICATOR"; public static final String
	 * COL_XFC_ADD_PAY_SRV_MERCH_TEL = "MERCHANT_TELEPHONE"; public static final
	 * String COL_XFC_ADD_PAY_SRV_MERCH_VOLUME_IND =
	 * "MERCHANT_VOLUME_INDICATOR"; public static final String
	 * COL_XFC_ADD_PAY_SRV_EXCLUDED_TRAN_ID_REASON = "EXCLUDED_TRAN_ID_REASON";
	 * public static final String COL_XFC_ADD_PAY_SRV_CRS_PREOCESSING_CODE =
	 * "CRS_PROCESSING_CODE"; public static final String
	 * COL_XFC_ADD_PAY_SRV_CHARGEBACK_RIGHTS_IND =
	 * "CHARGEBACK_RIGHTS_INDICATOR"; public static final String
	 * COL_XFC_ADD_PAY_SRV_MULTI_CLEAR_SEQ_NUM = "MULTI_CLEAR_SEQ_NUMBER";
	 * public static final String COL_XFC_ADD_PAY_SRV_MULTI_CLEAR_SEQ_COUNT =
	 * "MULTI_CLEAR_SEQ_COUNT"; public static final String
	 * COL_XFC_ADD_PAY_SRV_ADT_DATA_IND = "ADDITIONAL_DATA_INDICATOR"; public
	 * static final String COL_XFC_ADD_PAY_SRV_PRODUCT_ID = "PRODUCT_ID";
	 * 
	 * 
	 * //Table XFC_ADDENDUM_AIRLINE public static final String
	 * TABLE_XFC_ADD_AIRLINE = "XFC_ADDENDUM_AIRLINE"; //Table
	 * XFC_ADDENDUM_AIRLINE field names public static final String
	 * COL_XFC_ADD_AIRLINE_INST_NUM = "INSTITUTION_NUMBER"; public static final
	 * String COL_XFC_ADD_AIRLINE_FILE_NUM = "FILE_NUMBER"; public static final
	 * String COL_XFC_ADD_AIRLINE_TRN_SLIP = "TRANSACTION_SLIP"; public static
	 * final String COL_XFC_ADD_AIRLINE_ADD_NUM = "ADDENDUM_NUMBER"; public
	 * static final String COL_XFC_ADD_AIRLINE_AIRLINE_PLAN_NUM =
	 * "AIRLINE_PLAN_NUMBER"; public static final String
	 * COL_XFC_ADD_AIRLINE_CARRIER_NAME = "CARRIER_NAME"; public static final
	 * String COL_XFC_ADD_AIRLINE_CUS_REF_NUM = "CUSTOMER_REF_NUMBER"; public
	 * static final String COL_XFC_ADD_AIRLINE_PASSENGER_NAME =
	 * "PASSENGER_NAME"; public static final String
	 * COL_XFC_ADD_AIRLINE_RESTR_TICKET_IND = "RESTR_TICKET_INDICATOR"; public
	 * static final String COL_XFC_ADD_AIRLINE_TICKET_ISS_ADR =
	 * "TICKET_ISSUER_ADDRESS"; public static final String
	 * COL_XFC_ADD_AIRLINE_TICKET_NUM = "TICKET_NUMBER"; public static final
	 * String COL_XFC_ADD_AIRLINE_TRAN_AMT = "TRAN_AMOUNT"; public static final
	 * String COL_XFC_ADD_AIRLINE_TRAN_CUR = "TRAN_CURRENCY"; public static
	 * final String COL_XFC_ADD_AIRLINE_TRAVEL_AGENCY_CODE =
	 * "TRAVEL_AGENCY_CODE"; public static final String
	 * COL_XFC_ADD_AIRLINE_TRAVEL_AGENCY_NAME = "TRAVEL_AGENCY_NAME";
	 * 
	 * //Table XFC_ADDENDUM_AIRLINE_LEGS public static final String
	 * TABLE_XFC_ADD_AIRLINE_LEGS = "XFC_ADDENDUM_AIRLINE_LEGS"; //Table
	 * XFC_ADDENDUM_AIRLINE_LEGS field names public static final String
	 * COL_XFC_ADD_AIRLINE_LEGS_INST_NUM = "INSTITUTION_NUMBER"; public static
	 * final String COL_XFC_ADD_AIRLINE_LEGS_FILE_NUM = "FILE_NUMBER"; public
	 * static final String COL_XFC_ADD_AIRLINE_LEGS_TRN_SLIP =
	 * "TRANSACTION_SLIP"; public static final String
	 * COL_XFC_ADD_AIRLINE_LEGS_ADD_NUM = "ADDENDUM_NUMBER"; public static final
	 * String COL_XFC_ADD_AIRLINE_LEGS_LEG_NUM = "LEG_NUMBER"; public static
	 * final String COL_XFC_ADD_AIRLINE_LEGS_DEPARTURE_DATE = "DEPARTURE_DATE";
	 * public static final String COL_XFC_ADD_AIRLINE_LEGS_DEPARTURE_AIRPORT =
	 * "DEPARTURE_AIRPORT"; public static final String
	 * COL_XFC_ADD_AIRLINE_LEGS_DEPARTURE_TAX = "DEPARTURE_TAX"; public static
	 * final String COL_XFC_ADD_AIRLINE_LEGS_CARRIER_CODE = "CARRIER_CODE";
	 * public static final String COL_XFC_ADD_AIRLINE_LEGS_SRV_CLS =
	 * "SERVICE_CLASS"; public static final String
	 * COL_XFC_ADD_AIRLINE_LEGS_STOP_OVER_CODE = "STOP_OVER_CODE"; public static
	 * final String COL_XFC_ADD_AIRLINE_LEGS_DEST_AIRPORT =
	 * "DESTINATION_AIRPORT"; public static final String
	 * COL_XFC_ADD_AIRLINE_LEGS_FARE_BASIS_CODE = "FARE_BASIS_CODE";
	 *  // **** Table (XFC_SUNDRY) **** public final static String
	 * TABLE_XFC_SUNDRY = "XFC_SUNDRY"; public final static String
	 * COL_XFC_SUNDRY_INST_NUM = "INSTITUTION_NUMBER"; public final static
	 * String COL_XFC_SUNDRY_FILE_NUM = "FILE_NUMBER"; public final static
	 * String COL_XFC_SUNDRY_SUNDRY_TRN_SLIP = "SUNDRY_TRANSACTION_SLIP"; public
	 * final static String COL_XFC_SUNDRY_TRN_SLIP = "TRANSACTION_SLIP"; public
	 * final static String COL_XFC_SUNDRY_TRN_SRC = "TRANSACTION_SOURCE"; public
	 * final static String COL_XFC_SUNDRY_TRN_DEST = "TRANSACTION_DESTINATION";
	 * public final static String COL_XFC_SUNDRY_SUNDRY_TYPE = "SUNDRY_TYPE";
	 * public final static String COL_XFC_SUNDRY_SUNDRY_REASON =
	 * "SUNDRY_REASON"; public final static String COL_XFC_SUNDRY_DOC_CODE =
	 * "DOCUMENT_CODE"; public final static String COL_XFC_SUNDRY_SUNDRY_REF =
	 * "SUNDRY_REFERENCE"; public final static String COL_XFC_SUNDRY_SUNDRY_STA =
	 * "SUNDRY_STATUS"; public final static String
	 * COL_XFC_SUNDRY_MSG_SUNDRY_TEXT = "MESSAGE_SUNDRY_TEXT"; public final
	 * static String COL_XFC_SUNDRY_NOTE_TEXT = "NOTE_TEXT"; public final static
	 * String COL_XFC_SUNDRY_ACTION_TAKEN = "ACTION_TAKEN"; public final static
	 * String COL_XFC_SUNDRY_TRN_DATE = "TRANSACTION_DATE"; public final static
	 * String COL_XFC_SUNDRY_SETL_CUR = "SETTLEMENT_CURRENCY"; public final
	 * static String COL_XFC_SUNDRY_SETL_AMT_GR = "SETTLEMENT_AMOUNT_GR"; public
	 * final static String COL_XFC_SUNDRY_TRAN_CUR = "TRAN_CURRENCY"; public
	 * final static String COL_XFC_SUNDRY_TRAN_AMT_GR = "TRAN_AMOUNT_GR"; public
	 * final static String COL_XFC_SUNDRY_DEST_ACCOUNT = "DESTINATION_ACCOUNT";
	 * public final static String COL_XFC_SUNDRY_CARD_NUM = "CARD_NUMBER";
	 * public final static String COL_XFC_SUNDRY_RECIEVING_MEMBER_ID =
	 * "RECIEVING_MEMBER_ID"; public final static String
	 * COL_XFC_SUNDRY_PROCESSING_CLS = "PROCESSING_CLASS"; public final static
	 * String COL_XFC_SUNDRY_TRANSFER_FLAG = "TRANSFER_FLAG"; public final
	 * static String COL_XFC_SUNDRY_INVALID_RESPONSE = "INVALID_RESPONSE";
	 * public final static String COL_XFC_SUNDRY_MASTERCOM_IND =
	 * "MASTERCOM_INDICATOR"; public final static String COL_XFC_SUNDRY_REV_FLAG =
	 * "REVERSAL_FLAG"; public final static String COL_XFC_SUNDRY_SUB_FRAUD_TYPE =
	 * "SUB_FRAUD_TYPE"; public final static String
	 * COL_XFC_SUNDRY_COUNTERFEIT_CODE = "COUNTERFEIT_CODE"; public final static
	 * String COL_XFC_SUNDRY_FRAUD_CARD_TYPE = "FRAUD_CARD_TYPE"; public final
	 * static String COL_XFC_SUNDRY_CHARGEBACK_FLAG = "CHARGEBACK_FLAG"; public
	 * final static String COL_XFC_SUNDRY_DELETE_FLAG = "DELETE_FLAG"; public
	 * final static String COL_XFC_SUNDRY_CLNT_NUM = "CLIENT_NUMBER"; public
	 * final static String COL_XFC_SUNDRY_FORWARDING_MEMBER_ID =
	 * "FORWARDING_MEMBER_ID"; public final static String
	 * COL_XFC_SUNDRY_ACCOUNT_TYPE_ID = "ACCOUNT_TYPE_ID"; public final static
	 * String COL_XFC_SUNDRY_CARD_EXP_DATE = "CARD_EXPIRATION_DATE"; public
	 * final static String COL_XFC_SUNDRY_FRAUD_AUTH_TYPE = "FRAUD_AUTH_TYPE";
	 * public final static String COL_XFC_SUNDRY_APPROVAL_CODE =
	 * "APPROVAL_CODE"; public final static String
	 * COL_XFC_SUNDRY_INVESTIGATION_STA = "INVESTIGATION_STATUS"; public final
	 * static String COL_XFC_SUNDRY_SRV_CNTR_ID = "SERVICE_CONTRACT_ID"; public
	 * final static String COL_XFC_SUNDRY_ACCT_CUR = "ACCT_CURRENCY"; public
	 * final static String COL_XFC_SUNDRY_GRP_NUM = "GROUP_NUMBER"; public final
	 * static String COL_XFC_SUNDRY_ALT_ACCT_NUM = "ALT_ACCT_NUMBER"; public
	 * final static String COL_XFC_SUNDRY_ALT_CLNT_NUM = "ALT_CLIENT_NUMBER";
	 * public final static String COL_XFC_SUNDRY_ALT_GRP_NUM =
	 * "ALT_GROUP_NUMBER"; public final static String
	 * COL_XFC_SUNDRY_ALT_SRV_CNTR_ID = "ALT_SERVICE_CONTRACT_ID"; public final
	 * static String COL_XFC_SUNDRY_ALT_ACCOUNT_TYPE_ID = "ALT_ACCOUNT_TYPE_ID";
	 * public final static String COL_XFC_SUNDRY_ALT_ACCT_CUR =
	 * "ALT_ACCT_CURRENCY"; public final static String COL_XFC_SUNDRY_MERCH_ID =
	 * "MERCHANT_ID"; public final static String COL_XFC_SUNDRY_MERCH_POST_CODE =
	 * "MERCHANT_POST_CODE"; public final static String
	 * COL_XFC_SUNDRY_POS_ENTRY_MODE = "POS_ENTRY_MODE"; public final static
	 * String COL_XFC_SUNDRY_AUTH_RESPONSE_CODE = "AUTH_RESPONSE_CODE"; public
	 * final static String COL_XFC_SUNDRY_TERM_ATTENDANCE =
	 * "TERMINAL_ATTENDANCE"; public final static String
	 * COL_XFC_SUNDRY_CARDHOLDER_PRESENCE = "CARDHOLDER_PRESENCE"; public final
	 * static String COL_XFC_SUNDRY_CAT_LEVEL = "CAT_LEVEL"; public final static
	 * String COL_XFC_SUNDRY_POS_INPUT_CAPA = "POS_INPUT_CAPABILITY"; public
	 * final static String COL_XFC_SUNDRY_ECOM_SECURITY_LEVEL =
	 * "ECOM_SECURITY_LEVEL"; public final static String
	 * COL_XFC_SUNDRY_POSITIVE_APPROVAL = "POSITIVE_APPROVAL"; public final
	 * static String COL_XFC_SUNDRY_CVC_INVALID = "CVC_INVALID"; public final
	 * static String COL_XFC_SUNDRY_REPORTED_DATE = "REPORTED_DATE"; public
	 * final static String COL_XFC_SUNDRY_RETRIEVAL_REF = "RETRIEVAL_REFERENCE";
	 * public final static String COL_XFC_SUNDRY_TERM_CAPA =
	 * "TERMINAL_CAPABILITY"; public final static String
	 * COL_XFC_SUNDRY_CAPTURE_METH = "CAPTURE_METHOD"; public final static
	 * String COL_XFC_SUNDRY_AUTH_CODE = "AUTH_CODE"; public final static String
	 * COL_XFC_SUNDRY_FEE_SEQ_NUM = "FEE_SEQUENCE_NUMBER"; public final static
	 * String COL_XFC_SUNDRY_ACQ_REF = "ACQUIRER_REFERENCE"; public final static
	 * String COL_XFC_SUNDRY_CARD_CAPA = "CARD_CAPABILITY";
	 * 
	 */
	// XFC prefix table names
	public static final String TABLE_XFC_BATCH_PRE = "XFC_BATCH_";
	public static final String TABLE_XFC_TRN_PRE = "XFC_TRAN_";
	public static final String TABLE_XFC_SUNDRY_PRE = "XFC_SUNDRY_";
	public static final String TABLE_XFC_ADD_ISO_PRE = "XFC_ADD_ISO_";
	public static final String TABLE_XFC_ADD_MRC_PRE = "XFC_ADD_MRC_";
	public static final String TABLE_XFC_ADD_AR_PRE = "XFC_ADD_AR_";
	public static final String TABLE_XFC_ADD_HL_PRE = "XFC_ADD_HL_";
	public static final String TABLE_XFC_ADD_DATA_PRE = "XFC_ADD_DATA_";
	public static final String TABLE_XFC_ADD_LNI_PRE = "XFC_ADD_LNI_";
	public static final String TABLE_XFC_ADD_LMU_PRE = "XFC_ADD_LMU_";
	public static final String TABLE_XFC_ADD_AIR_PRE = "XFC_ADD_AIR_";
	public static final String TABLE_XFC_ADD_ALEG_PRE = "XFC_ADD_ALEG_";
	public static final String TABLE_XFC_ADD_SMC_PRE = "XFC_ADD_SMC_";
	public static final String TABLE_XFC_ADD_PAYM_PRE = "XFC_ADD_PAYM_";

	// Table CIS_CLIENT_DETAILS
	public static final String TABLE_CIS_CLIENT_DETAILS = "CIS_CLIENT_DETAILS";
	// Table CIS_CLIENT_DETAILS field names
	public static final String COL_CIS_CLIENT_DETAILS_INST_NUM = "INSTITUTION_NUMBER";
	public static final String COL_CIS_CLIENT_DETAILS_CLNT_NUM = "CLIENT_NUMBER";
	public static final String COL_CIS_CLIENT_DETAILS_CLNT_REGION = "CLIENT_REGION";
	public static final String COL_CIS_CLIENT_DETAILS_BUSS_CLS = "BUSINESS_CLASS";
	public static final String COL_CIS_CLIENT_DETAILS_OUR_REF = "OUR_REFERENCE";
	public static final String COL_CIS_CLIENT_DETAILS_CLNT_STATE = "CLIENT_STATE";
	public static final String COL_CIS_CLIENT_DETAILS_CLNT_CTRY = "CLIENT_COUNTRY";
	public static final String COL_CIS_CLIENT_DETAILS_CLNT_CITY = "CLIENT_CITY";
	public static final String COL_CIS_CLIENT_DETAILS_CLNT_STA = "CLIENT_STATUS";
	public final static String COL_CIS_CLNT_DTL_RCC = "RCC";
	public final static String COL_CIS_CLNT_DTL_POST_CODE = "POST_CODE";
	public final static String COL_CIS_CLNT_DTL_TRADE_NAME = "TRADE_NAME";
	public final static String COL_CIS_CLNT_DTL_SRV_TEL_NUM = "SERVICE_TEL_NUMBER";
	public final static String COL_CIS_CLNT_DTL_COMPANY_NAME = "COMPANY_NAME";
	public final static String COL_CIS_CLNT_DTL_RGS_NUM = "REGISTRATION_NUMBER";

	// **** Table (CIS_CLIENT_REFERENCE) ****
	public final static String TABLE_CIS_CLNT_REF = "CIS_CLIENT_REFERENCE";
	public final static String COL_CIS_CLNT_REF_INST_NUM = "INSTITUTION_NUMBER";
	public final static String COL_CIS_CLNT_REF_REC_DATE = "RECORD_DATE";
	public final static String COL_CIS_CLNT_REF_AUDIT_TRAIL = "AUDIT_TRAIL";
	public final static String COL_CIS_CLNT_REF_CLNT_NUM = "CLIENT_NUMBER";
	public final static String COL_CIS_CLNT_REF_CLNT_REF_NUM = "CLIENT_REFERENCE_NUMBER";
	public final static String COL_CIS_CLNT_REF_CLNT_REF_TYPE = "CLIENT_REFERENCE_TYPE";

	// Table CIS_INTERCHANGE_DETAILS
	public static final String TABLE_CIS_INTR_DTL = "CIS_INTERCHANGE_DETAILS";
	// Columns contained in the table CIS_INTERCHANGE_DETAILS
	public static String COL_CIS_INTR_DTL_INST_NUM = "INSTITUTION_NUMBER";
	public static String COL_CIS_INTR_DTL_CARD_ORGA = "CARD_ORGANIZATION";
	public static String COL_CIS_INTR_DTL_CLNT_RGN = "CLIENT_REGION";
	public static String COL_CIS_INTR_DTL_ORGA_ID = "ORGANIZATION_ID";
	// Table CIS_CLIENT_LINKS
	public static final String TABLE_CIS_CLNT_LNK = "CIS_CLIENT_LINKS";
	// Table CIS_CLIENT_LINKS field names
	public static final String COL_CIS_CLNT_LNK_INST_NUM = "INSTITUTION_NUMBER";
	public static final String COL_CIS_CLNT_LNK_REC_DATE = "RECORD_DATE";
	public static final String COL_CIS_CLNT_LNK_GRP_NUM = "GROUP_NUMBER";
	public static final String COL_CIS_CLNT_LNK_CLNT_NUM = "CLIENT_NUMBER";
	public static final String COL_CIS_CLNT_LNK_EFFECTIVE_DATE = "EFFECTIVE_DATE";
	public static final String COL_CIS_CLNT_LNK_EXPIRY_DATE = "EXPIRY_DATE";
	public static final String COL_CIS_CLNT_LNK_CNTR_STATUS = "CONTRACT_STATUS";
	public static final String COL_CIS_CLNT_LNK_PARENT_CLNT_NUM = "PARENT_CLIENT_NUMBER";
	public static final String COL_CIS_CLNT_LNK_CLNT_LEVEL = "CLIENT_LEVEL";
	public static final String COL_CIS_CLNT_LNK_AUDIT_TRAIL = "AUDIT_TRAIL";
	public static final String COL_CIS_CLNT_LNK_SRV_CNTR_ID = "SERVICE_CONTRACT_ID";
	public static final String COL_CIS_CLNT_LNK_CLNT_TARIFF = "CLIENT_TARIFF";
	public static final String COL_CIS_CLNT_LNK_BANK_REF = "BANK_REFERENCE";
	public static final String COL_CIS_CLNT_LNK_CNTR_REF = "CONTRACT_REFERENCE";
	public static final String COL_CIS_CLNT_LNK_SETT_METH = "SETTLEMENT_METHOD";
	public static final String COL_CIS_CLNT_LNK_POSTING_METH = "POSTING_METHOD";
	public static final String COL_CIS_CLNT_LNK_CLNT_BRANCH = "CLIENT_BRANCH";
	public static final String COL_CIS_CLNT_LNK_INST_ACCT_OFC = "INSTITUTION_ACCT_OFFICER";
	public static final String COL_CIS_CLNT_LNK_PROVIDER_ACCT_OFC = "PROVIDER_ACCT_OFFICER";
	public static final String COL_CIS_CLNT_LNK_CNTR_CATG = "CONTRACT_CATEGORY";
	public static final String COL_CIS_CLNT_LNK_COST_CENTER = "COST_CENTER";
	public static final String COL_CIS_CLNT_LNK_CHG_TIER_LEVEL = "CHARGE_TIER_LEVEL";

	// Table BWT_TRANSACTION_TYPE
	public static final String TABLE_BWT_TRANSACTION_TYPE = "BWT_TRANSACTION_TYPE";
	// Table BWT_TRANSACTION_TYPE field names
	public static final String COL_BWT_TRANSACTION_TYPE_INST_NUM = "INSTITUTION_NUMBER";
	public static final String COL_BWT_TRANSACTION_TYPE_INX_FLD = "INDEX_FIELD";
	public static final String COL_BWT_TRANSACTION_TYPE_DEST_SIGN = "DESTINATION_SIGN";
	public static final String COL_BWT_TRANSACTION_TYPE_SRC_SIGN = "SOURCE_SIGN";
	// Table BWT_ISO_BUSS_CLASS
	public static final String TABLE_BWT_ISO_BUS_CLS = "BWT_ISO_BUSS_CLASS";
	// Table BWT_ISO_BUSS_CLASS field names
	public static final String COL_BWT_ISO_BUS_CLS_IDX_FLD = "INDEX_FIELD";
	public static final String COL_BWT_ISO_BUS_CLS_ISO_CODE = "ISO_CODE";
	// Table BWT_EXTRA_CHARGES_AUTO_RENTAL
	public static final String TABLE_BWT_EXR_CHG_AUTO_RENTAL = "BWT_EXTRA_CHARGES_AUTO_RENTAL";
	// Table BWT_EXTRA_CHARGES_AUTO_RENTAL field names
	public static final String COL_BWT_EXR_CHG_AUTO_RENTAL_IDX_FLD = "INDEX_FIELD";
	public static final String COL_BWT_EXR_CHG_AUTO_RENTAL_VISA_CODE = "VISA_CODE";
	public static final String COL_BWT_EXR_CHG_AUTO_RENTAL_INET_CODE = "INET_CODE";
	// Table BWT_EXTRA_CHARGES_LODGING
	public static final String TABLE_BWT_EXR_CHG_LODGING = "BWT_EXTRA_CHARGES_LODGING";
	// Table BWT_EXTRA_CHARGES_LODGING field names
	public static final String COL_BWT_EXR_CHG_LODGING_IDX_FLD = "INDEX_FIELD";
	public static final String COL_BWT_EXR_CHG_LODGING_VISA_CODE = "VISA_CODE";
	public static final String COL_BWT_EXR_CHG_LODGING_INET_CODE = "INET_CODE";
	// Table BWT_COUNTRY
	public static final String TABLE_BWT_CTRY = "BWT_COUNTRY";
	// Table BWT_COUNTRY field names
	public static final String COL_BWT_CTRY_IDX_FLD = "INDEX_FIELD";
	public static final String COL_BWT_CTRY_CTRY_CODE_2 = "COUNTRY_CODE_2";
	public static final String COL_BWT_CTRY_CTRY_CODE_3 = "COUNTRY_CODE_3";
	public static final String COL_BWT_CTRY_CLNT_CTRY = "CLIENT_COUNTRY";
	public static final String COL_BWT_CTRY_TEL_CODE = "TEL_CODE";
	public static final String COL_BWT_CTRY_NATIONALITY = "NATIONALITY";

	// Table BWT_CURRENCY
	public static final String TABLE_BWT_CUR = "BWT_CURRENCY";
	// Table BWT_CURRENCY field names
	public static final String COL_BWT_CUR_ISO_CODE = "ISO_CODE";
	public static final String COL_BWT_CUR_SWIFT_CODE = "SWIFT_CODE";
	public static final String COL_BWT_CUR_LOCAL_CODE = "LOCAL_CODE";
	public static final String COL_BWT_CUR_NAME = "NAME";
	public static final String COL_BWT_CUR_EXPN = "EXPONENT";
	public static final String COL_BWT_CUR_CALC_BASE = "CALC_BASE";
	public static final String COL_BWT_CUR_SYMBOL = "SYMBOL";
	public static final String COL_BWT_CUR_SORT_FLD = "SORT_FIELD";
	public static final String COL_BWT_CUR_LANG = "LANGUAGE";

	// Table BWT_COUNTRY_STATE
	public static final String TABLE_BWT_CTRY_STATE = "BWT_COUNTRY_STATE";
	// Table BWT_COUNTRY_STATE field names
	public static final String COL_BWT_CTRY_STATE_IDX_FLD = "INDEX_FIELD";
	public static final String COL_BWT_CTRY_STATE_CTRY_CODE = "COUNTRY_CODE";
	public static final String COL_BWT_CTRY_STATE_STATE_CODE = "STATE_CODE";
	// Table BWT_REGION
	public static final String TABLE_BWT_RGN = "BWT_REGION";
	// Table BWT_REGION field names
	public static final String COL_BWT_RGN_IDX_FLD = "INDEX_FIELD";
	public static final String COL_BWT_RGN_INST_NUM = "INSTITUTION_NUMBER";
	public static final String COL_BWT_RGN_LANG = "LANGUAGE";
	public static final String COL_BWT_RGN_RGN = "REGION";

	// Table GUI_MDI_MENU
	public static final String TABLE_GUI_MDI_MENU = "GUI_MDI_MENU";
	// Table BWT_COUNTRY_STATE field names
	public static final String COL_GUI_MDI_MENU_ID = "MENU_ID";
	public static final String COL_GUI_MDI_MENU_PARENT_ID = "PARENT_ID";
	public static final String COL_GUI_MDI_MENU_ORDER_NO = "ORDER_NO";
	public static final String COL_GUI_MDI_MENU_SHORT_KEY = "SHORT_KEY";
	public static final String COL_GUI_MDI_MENU_STATUS = "STATUS";
	public static final String COL_GUI_MDI_MENU_TOLTIP_ID = "TOOL_TIP_ID";
	public static final String COL_GUI_MDI_MENU_ACTION_CODE = "ACTION_CODE";
	public static final String COL_GUI_MDI_MENU_TOOLBAR_ICON = "TOOLBAR_ICON";
	public static final String COL_GUI_MDI_MENU_TOOLBAR_LBL_ID = "TOOLBAR_LABLE_ID";
	// Table GUI_POPUP_MENU
	public static final String TABLE_GUI_POPUP_MENUS = "GUI_POPUP_MENUS";
	// Table GUI_POPUP_MENU field names
	public static final String COL_GUI_POPUP_MENU_POPUP_ID = "POPUP_ID";
	public static final String COL_GUI_POPUP_MENU_ID = "MENU_ID";
	public static final String COL_GUI_POPUP_MENU_PARENT_ID = "PARENT_ID";
	public static final String COL_GUI_POPUP_MENU_ORDER_NO = "ORDER_NO";
	public static final String COL_GUI_POPUP_MENU_SHORT_KEY = "SHORT_KEY";
	public static final String COL_GUI_POPUP_MENU_STATUS = "STATUS";
	public static final String COL_GUI_POPUP_MENU_TOLTIP_ID = "TOOL_TIP_ID";
	public static final String COL_GUI_POPUP_MENU_ACTION_CODE = "ACTION_CODE";

	// Table GUI_LANG_SOURCE
	public static final String TABLE_GUI_LANG_SOURCE = "GUI_LANG_SOURCE";
	// Table GUI_LANG_SOURCE field names
	public static final String COL_GUI_LANG_SOURCE_MODULE = "MODULE";
	public static final String COL_GUI_LANG_SOURCE_ID = "ID";
	public static final String COL_GUI_LANG_SOURCE_LANG = "LANGUAGE";
	public static final String COL_GUI_LANG_SOURCE_TEXT = "TEXT";
	// Table GUI_PARAM_FILE
	public static final String TABLE_GUI_PARAM_FILE = "GUI_PARAM_FILE";
	// Table GUI_PARAM_FILE field names
	public static final String COL_GUI_PARAM_FILE_PARMFILEID = "PARAM_FILE_ID";
	public static final String COL_GUI_PARAM_FILE_PARMFILENAME = "PARAM_FILE_NAME";
	public static final String COL_GUI_PARAM_FILE_PARMFILETYPE = "PARAM_FILE_TYPE";
	public static final String COL_GUI_PARAM_FILE_DESC = "DESCRIPTION";
	public static final String COL_GUI_PARAM_FILE_PARMDATA = "PARAM_DATA";
	public static final String COL_GUI_PARAM_FILE_SRCETIMESTAMP = "SOURCE_TIME_STAMP";
	public static final String COL_GUI_PARAM_FILE_PRCDATA = "PROCESS_DATA";
	public static final String COL_GUI_PARAM_FILE_PCODETIMESTAMP = "PCODE_TIME_STAMP";
	public static final String COL_GUI_PARAM_FILE_PCODE = "PCODE";
	public static final String COL_GUI_PARAM_FILE_CHOICETABLES = "CHOICE_TABLES";
	public static final String COL_GUI_PARAM_FILE_EXECUTABLES = "EXECUTABLES";
	public static final String COL_GUI_PARAM_FILE_SQLCREATESTRING = "SQL_CREATE_STRING";
	public static final String COL_GUI_PARAM_FILE_TIME_STAMP = "TIME_STAMP";
	public static final String COL_GUI_PARAM_FILE_CREATEDBY = "CREATED_BY";
	public static final String COL_GUI_PARAM_FILE_AMENDEDBY = "AMENDED_BY";
	public static final String COL_GUI_PARAM_FILE_MODULEKEY = "MODULE_KEY";
	public static final String COL_GUI_PARAM_FILE_DBTABLE = "DBTABLE";
	public static final String COL_GUI_PARAM_FILE_VERMAJOR = "VERSION_MAJOR";
	public static final String COL_GUI_PARAM_FILE_VERMINOR = "VERSION_MINOR";
	public static final String COL_GUI_PARAM_FILE_VERREVISION = "VERSION_REVISION";
	public static final String COL_GUI_PARAM_FILE_PARMID = "PARAM_ID";
	// Table GUI_MSK_MENU
	public static final String TABLE_GUI_MSK_MENU = "GUI_MSK_MENU";
	// Table GUI_MSK_MENU field names
	public static final String COL_GUI_MSK_MENU_PARMFILEID = "PARAM_FILE_ID";
	public static final String COL_GUI_MSK_MENU_PARMFILENAME = "PARAM_FILE_NAME";
	public static final String COL_GUI_MSK_MENU_ID = "MENU_ID";
	public static final String COL_GUI_MSK_MENU_ACTION_CODE = "ACTION_CODE";
	public static final String COL_GUI_MSK_MENU_PARENT_ID = "PARENT_ID";
	public static final String COL_GUI_MSK_MENU_SHORT_KEY = "SHORT_KEY";
	public static final String COL_GUI_MSK_MENU_STATUS = "STATUS";
	public static final String COL_GUI_MSK_MENU_TOOL_TIP_ID = "TOOL_TIP_ID";
	public static final String COL_GUI_MSK_MENU_COL_NUM = "COL_NUM";
	public static final String COL_GUI_MSK_MENU_ACTION_PARAM = "ACTION_PARAM";

	// Migration Access DB Constants
	public static final String TABLE_PARAMFILE = "paramfiles";
	// Table GUI_LANG_SOURCE field names
	public static final String COL_PARAMFILE_PARMFILENAME = "ParamFileName";
	public static final String COL_PARAMFILE_PARMFILETYPE = "ParamFileType";
	public static final String COL_PARAMFILE_DESC = "Description";
	public static final String COL_PARAMFILE_PARMDATA = "ParamData";
	public static final String COL_PARAMFILE_SRCETIMESTAMP = "SourceTimeStamp";
	public static final String COL_PARAMFILE_PRCDATA = "ProcessData";
	public static final String COL_PARAMFILE_PCODETIMESTAMP = "PCodeTimeStamp";
	public static final String COL_PARAMFILE_PCODE = "PCode";
	public static final String COL_PARAMFILE_CHOICETABLES = "ChoiceTables";
	public static final String COL_PARAMFILE_EXECUTABLES = "Executables";
	public static final String COL_PARAMFILE_SQLCREATESTRING = "SQLCreateString";
	public static final String COL_PARAMFILE_TIMESTAMP = "Time_Stamp";
	public static final String COL_PARAMFILE_CREATEDBY = "CreatedBy";
	public static final String COL_PARAMFILE_AMENDEDBY = "AmendedBy";
	public static final String COL_PARAMFILE_MODULEKEY = "ModuleKey";
	public static final String COL_PARAMFILE_DBTABLE = "DbTable";
	public static final String COL_PARAMFILE_VERMAJOR = "VersionMajor";
	public static final String COL_PARAMFILE_VERMINOR = "VersionMinor";
	public static final String COL_PARAMFILE_VERREVISION = "VersionRevision";
	public static final String COL_PARAMFILE_PARMID = "ParamID";

	// Table GUI_PARAM_TYPE
	public static final String TABLE_GUI_PARM_FIL_TYPE = "GUI_PARAM_TYPE";
	// Table GUI_PARAM_TYPE field names
	public static final String COL_GUI_PARM_FIL_TYPE_ID = "PARAM_FILE_TYPE";
	public static final String COL_GUI_PARM_FIL_TYPE_NAME = "PARAM_TYPE_NAME";
	public static final String COL_GUI_PARM_FIL_TYPE_DESC = "DESCRIPTION";

	// Table GUI_PARAM_DATA
	public static final String TABLE_GUI_PARAM_DATA = "GUI_PARAM_DATA";
	// Table GUI_PARAM_DATA field names
	public static final String COL_PARAM_FILE_ID = "PARAM_FILE_ID";
	public static final String COL_PROCESS_DATA = "PROCESS_DATA";
	// Table GUI_PARAM_PROCESS
	public static final String TABLE_GUI_PARAM_PROCESS = "GUI_PARAM_PROCESS";
	// Table GUI_PARAM_PROCESS field names
	public static final String COL_PARAM_DATA = "PARAM_DATA";
	// insert into GUI_PARAM_DATA(PARAM_FILE_ID, PARAM_DATA) values(?,?)
	// Table CHT_PROCESS_NAME
	public static final String TABLE_CHT_PRC_NAME = "CHT_PROCESS_NAME";
	// Table CHT_PROCESS_NAME field names
	public static final String COL_CHT_PRC_NAME_ID = "INDEX_FIELD";
	public static final String COL_CHT_PRC_NAME_LANG = "LANGUAGE";
	public static final String COL_CHT_PRC_NAME_TEXT = "PROCESS_NAME";
	// Table CHT_PROCESS_GROUP
	public static final String TABLE_CHT_PRC_GRP = "CHT_PROCESS_GROUP";
	// Table CHT_PROCESS_GROUP field names
	public static final String COL_CHT_PRC_GRP_ID = "INDEX_FIELD";
	public static final String COL_CHT_PRC_GRP_LANG = "LANGUAGE";
	public static final String COL_CHT_PRC_GRP_TEXT = "PROCESS_GROUP";
	// **** Table (CHT_CHARGEBACK_REASON) ****
	public final static String TABLE_CHT_CHGB_RSN = "CHT_CHARGEBACK_REASON";
	public final static String COL_CHT_CHGB_RSN_IDX_FLD = "INDEX_FIELD";
	public final static String COL_CHT_CHGB_RSN_SUNDRY_RSN = "SUNDRY_REASON";
	public final static String COL_CHT_CHGB_RSN_VISA_RSN_CODE = "VISA_REASON_CODE";
	public final static String COL_CHT_CHGB_RSN_ECMC_RSN_CODE = "ECMC_REASON_CODE";
	public final static String COL_CHT_CHGB_RSN_INET_RSN_CODE = "INET_REASON_CODE";
	public final static String COL_CHT_CHGB_RSN_LANGUAGE = "LANGUAGE";
	public final static String COL_CHT_CHGB_RSN_GROUPS = "GROUPS";
	public final static String COL_CHT_CHGB_RSN_INST_NUM = "INSTITUTION_NUMBER";
	public final static String COL_CHT_CHGB_RSN_IPM_RSN_CODE = "IPM_REASON_CODE";
	public final static String COL_CHT_CHGB_RSN_MDS_RSN_CODE = "MDS_REASON_CODE";

	public static final String MOD_DEF_CONSOLE_GUI = "CONSOLE_GUI";

	// Table SYS_CONFIGURATION
	public static final String TABLE_SYS_CONFIG = "SYS_CONFIGURATION";
	// Columns contained in the table sys_configuration
	public static String COL_SYS_CONFIG_SECTION = "CONFIG_SECTION";
	public static String COL_SYS_CONFIG_KEYWORD = "CONFIG_KEYWORD";
	public static String COL_SYS_CONFIG_INSTNR = "INSTITUTION_NUMBER";
	public static String COL_SYS_CONFIG_VALUE = "CONFIG_VALUE";
	public static String COL_SYS_CONFIG_NOTES = "CONFIG_NOTES";

	// Table SYS_PROCESS_SECLETION
	public static final String TABLE_SYS_PROCESS_SEL = "SYS_CONFIGURATION";
	// Columns contained in the table sys_process_selection
	public static String COL_SYS_PROCESS_SEL_PROCESSNAME = "PROCESS_NAME";
	public static String COL_SYS_PROCESS_SEL_INSTNR = "INSTITUTION_NUMBER";
	public static String COL_SYS_PROCESS_SEL_PROCESSGROUP = "PROCESS_GROUP";
	public static String COL_SYS_PROCESS_SEL_SELMODE = "SELECTION_MODE";

	public static final String TABLE_SYS_SEL_CAPTIONS = "SYS_SELECTION_CAPTIONS";
	// Columns contained in the table sys_process_selection
	public static String COL_SYS_SEL_CAP_COL_HEADER = "COLUMN_HEADER";

	// Table SYS_COUNTRY_REGION
	public static final String TABLE_SYS_CTRY_RGN = "SYS_COUNTRY_REGION";
	// Columns contained in the table SYS_COUNTRY_REGION
	public static String COL_SYS_CTRY_RGN_INST_NUM = "INSTITUTION_NUMBER";
	public static String COL_SYS_CTRY_RGN_CTRY = "COUNTRY";
	public static String COL_SYS_CTRY_RGN_RGN = "REGION";

	// Table SYS_PROCESS_SETUP
	public static final String TABLE_SYS_PRC_USR_SETUP = "SYS_PROCESS_USER_SETUP";
	// Columns contained in the table SYS_PROCESS_SETUP
	public static String COL_SYS_PRC_USR_SETUP_INST_NAME = "INSTITUTION_NUMBER";
	public static String COL_SYS_PRC_USR_SETUP_PRC_NAME = "PROCESS_NAME";
	public static String COL_SYS_PRC_USR_SETUP_FILE_PATH = "FILE_PATH";
	public static String COL_SYS_PRC_USR_SETUP_FILE_MASK = "FILE_MASK";
	public static String COL_SYS_PRC_USR_SETUP_FILE_MOVE_PATH = "FILE_MOVE_PATH";
	public static String COL_SYS_PRC_USR_SETUP_FILE_RENAME_MASK = "FILE_RENAME_MASK";

	// Table SYS_PROCEDURE_PARAMETER
	public static final String TABLE_SYS_PRO_PARAM = "SYS_PROCEDURE_PARAMETER";
	// Columns contained in the table SYS_PROCEDURE_PARAMETER
	public static String COL_SYS_PRO_PARAM_PKG_NAME = "PACKAGE_NAME";
	public static String COL_SYS_PRO_PARAM_PRO_NAME = "PROCEDURE_NAME";
	public static String COL_SYS_PRO_PARAM_NUM = "PARAMETER_NUMBER";
	public static String COL_SYS_PRO_PARAM_NAME = "PARAMETER_NAME";
	// Table TABLE_SYS_ALL_TABLES
	public static final String TABLE_SYS_ALL_TABLES = "SYS.ALL_ALL_TABLES";
	// Columns contained in the table SYS_PROCEDURE_PARAMETER
	public static String COL_SYS_ALL_TABLES_TABLE_NAME = "table_name";

	// Table TABLE_SYS_USER_TAB_COLS
	public static final String TABLE_SYS_USER_TAB_COLS = "SYS.USER_TAB_COLS";
	public static String COL_SYS_USER_TAB_COLS_COL_NAME = "column_name";

	public static final String TABLE_SVC_CLNT_CARDS = "SVC_CLIENT_CARDS";
	public static final String TABLE_SVC_CARD_STA_CHANGE = "SVC_CARD_STATUS_CHANGE";
	public static final String TABLE_SVC_CARD_ACCUM = "SVC_CARD_ACCUMULATOR";
	public static final String COL_SVC_CLNT_CARDS_CARD_NUM = "CARD_NUMBER";
	/*
	 * 
	 * public final static String TABLE_RCN_FILE_LOG_DTL =
	 * "RCN_FILE_LOG_DETAILS"; public final static String
	 * COL_RCN_FILE_LOG_DTL_INST_NUM = "INSTITUTION_NUMBER"; public final static
	 * String COL_RCN_FILE_LOG_DTL_FILE_NUM = "FILE_NUMBER"; public final static
	 * String COL_RCN_FILE_LOG_DTL_REC_DATE = "RECORD_DATE"; public final static
	 * String COL_RCN_FILE_LOG_DTL_AUDIT_TRAIL = "AUDIT_TRAIL"; public final
	 * static String COL_RCN_FILE_LOG_DTL_RECON_CHNL = "RECON_CHANNEL"; public
	 * final static String COL_RCN_FILE_LOG_DTL_FILE_ID = "FILE_ID"; public
	 * final static String COL_RCN_FILE_LOG_DTL_ORG_FILE_NAME =
	 * "ORIGINAL_FILE_NAME"; public final static String
	 * COL_RCN_FILE_LOG_DTL_FILE_DATE = "FILE_DATE"; public final static String
	 * COL_RCN_FILE_LOG_DTL_START_TIME = "START_TIME"; public final static
	 * String COL_RCN_FILE_LOG_DTL_END_TIME = "END_TIME"; public final static
	 * String COL_RCN_FILE_LOG_DTL_PROCESSING_STA = "PROCESSING_STATUS"; public
	 * final static String COL_RCN_FILE_LOG_DTL_EXACT_MATCH_TRANS =
	 * "EXACT_MATCH_TRANS"; public final static String
	 * COL_RCN_FILE_LOG_DTL_INEXACT_MATCH_TRANS = "INEXACT_MATCH_TRANS"; public
	 * final static String COL_RCN_FILE_LOG_DTL_NOT_MATCHED_TRANS =
	 * "NOT_MATCHED_TRANS"; public final static String
	 * COL_RCN_FILE_LOG_DTL_PRC_NAME = "PROCESS_NAME"; public final static
	 * String COL_RCN_FILE_LOG_DTL_USE_TEMP_TRAN_TABLE = "USE_TEMP_TRAN_TABLE";
	 * public final static String COL_RCN_FILE_LOG_DTL_TRAN_DATA_LOC =
	 * "TRAN_DATA_LOCATION"; public final static String
	 * COL_RCN_FILE_LOG_DTL_FILE_TYPE = "FILE_TYPE";
	 * 
	 * public final static String TABLE_RCN_PRC_FILE_LOG =
	 * "RCN_PROCESS_FILE_LOG"; public final static String
	 * COL_RCN_PRC_FILE_LOG_PRC_NUM = "PROCESS_NUMBER"; public final static
	 * String COL_RCN_PRC_FILE_LOG_INST_NUM = "INSTITUTION_NUMBER"; public final
	 * static String COL_RCN_PRC_FILE_LOG_FILE_NUM = "FILE_NUMBER";
	 * 
	 * 
	 * public final static String TABLE_RCN_TRNS = "RCN_TRANSACTIONS"; public
	 * final static String COL_RCN_TRNS_INST_NUM = "INSTITUTION_NUMBER"; public
	 * final static String COL_RCN_TRNS_RECON_SLIP = "RECON_SLIP"; public final
	 * static String COL_RCN_TRNS_FILE_NUM = "FILE_NUMBER"; public final static
	 * String COL_RCN_TRNS_RECON_CHNL = "RECON_CHANNEL"; public final static
	 * String COL_RCN_TRNS_REC_DATE = "RECORD_DATE"; public final static String
	 * COL_RCN_TRNS_AUDIT_TRAIL = "AUDIT_TRAIL"; public final static String
	 * COL_RCN_TRNS_CARD_NUM = "CARD_NUMBER"; public final static String
	 * COL_RCN_TRNS_TRN_DATE = "TRANSACTION_DATE"; public final static String
	 * COL_RCN_TRNS_TRAN_CUR = "TRAN_CURRENCY"; public final static String
	 * COL_RCN_TRNS_TRAN_AMT_GR = "TRAN_AMOUNT_GR"; public final static String
	 * COL_RCN_TRNS_SETTL_AMT_GR = "SETTL_AMOUNT_GR"; public final static String
	 * COL_RCN_TRNS_AUTH_CODE = "AUTH_CODE"; public final static String
	 * COL_RCN_TRNS_TRN_CLS = "TRANSACTION_CLASS"; public final static String
	 * COL_RCN_TRNS_TRN_CATG = "TRANSACTION_CATEGORY"; public final static
	 * String COL_RCN_TRNS_TRN_TYPE = "TRANSACTION_TYPE"; public final static
	 * String COL_RCN_TRNS_REV_FLAG = "REVERSAL_FLAG"; public final static
	 * String COL_RCN_TRNS_TERM_ID = "TERMINAL_ID"; public final static String
	 * COL_RCN_TRNS_CAPTURE_METH = "CAPTURE_METHOD"; public final static String
	 * COL_RCN_TRNS_BUSS_CLS = "BUSINESS_CLASS"; public final static String
	 * COL_RCN_TRNS_MERCH_NAME = "MERCHANT_NAME"; public final static String
	 * COL_RCN_TRNS_MERCH_CITY = "MERCHANT_CITY"; public final static String
	 * COL_RCN_TRNS_MERCH_CTRY = "MERCHANT_COUNTRY"; public final static String
	 * COL_RCN_TRNS_MERCH_STATE = "MERCHANT_STATE"; public final static String
	 * COL_RCN_TRNS_MERCH_POST_CODE = "MERCHANT_POST_CODE"; public final static
	 * String COL_RCN_TRNS_MERCH_NUM = "MERCHANT_NUMBER"; public final static
	 * String COL_RCN_TRNS_MERCH_STREET = "MERCHANT_STREET"; public final static
	 * String COL_RCN_TRNS_SETL_CUR = "SETTLEMENT_CURRENCY"; public final static
	 * String COL_RCN_TRNS_LOCAL_CUR = "LOCAL_CURRENCY"; public final static
	 * String COL_RCN_TRNS_TRAN_AMT_CHG = "TRAN_AMOUNT_CHG"; public final static
	 * String COL_RCN_TRNS_TRAN_AMT_NET = "TRAN_AMOUNT_NET"; public final static
	 * String COL_RCN_TRNS_SETTL_AMT_CHG = "SETTL_AMOUNT_CHG"; public final
	 * static String COL_RCN_TRNS_SETTL_AMT_NET = "SETTL_AMOUNT_NET"; public
	 * final static String COL_RCN_TRNS_LOCAL_AMT_INW_GR =
	 * "LOCAL_AMOUNT_INW_GR"; public final static String
	 * COL_RCN_TRNS_LOCAL_AMT_INW_CHG = "LOCAL_AMOUNT_INW_CHG"; public final
	 * static String COL_RCN_TRNS_LOCAL_AMT_INW_NET = "LOCAL_AMOUNT_INW_NET";
	 * public final static String COL_RCN_TRNS_NUM_ORG_SLIP =
	 * "NUMBER_ORIGINAL_SLIP"; public final static String
	 * COL_RCN_TRNS_ORG_REF_NUM = "ORIGINAL_REF_NUMBER"; public final static
	 * String COL_RCN_TRNS_RETRIEVAL_REF = "RETRIEVAL_REFERENCE"; public final
	 * static String COL_RCN_TRNS_TRACE_ID = "TRACE_ID"; public final static
	 * String COL_RCN_TRNS_TIME_TRN = "TIME_TRANSACTION"; public final static
	 * String COL_RCN_TRNS_RECON_STA = "RECON_STATUS"; public final static
	 * String COL_RCN_TRNS_MATCH_DATE = "MATCH_DATE"; public final static String
	 * COL_RCN_TRNS_MATCH_RULE = "MATCH_RULE"; public final static String
	 * COL_RCN_TRNS_MATCH_TRN_SLIP = "MATCH_TRANSACTION_SLIP"; public final
	 * static String COL_RCN_TRNS_MATCH_RESULT = "MATCH_RESULT"; public final
	 * static String COL_RCN_TRNS_MATCH_DAYS_DIFF = "MATCH_DAYS_DIFF"; public
	 * final static String COL_RCN_TRNS_MATCH_AMT_DIFF = "MATCH_AMOUNT_DIFF";
	 * public final static String COL_RCN_TRNS_AREA_OF_EVENT = "AREA_OF_EVENT";
	 * public final static String COL_RCN_TRNS_CARD_ORGA = "CARD_ORGANIZATION";
	 */
	public final static String TABLE_SYS_MDS_BIN_TABLE = "SYS_MDS_BIN_TABLE";
	public final static String COL_SYS_MDS_BIN_TABLE_END_BIN_VALUE = "END_BIN_VALUE";
	public final static String COL_SYS_MDS_BIN_TABLE_START_BIN_VALUE = "START_BIN_VALUE";
	public final static String COL_SYS_MDS_BIN_TABLE_SEL_KEY = "SELECTION_KEY";
	public final static String COL_SYS_MDS_BIN_TABLE_CARD_LENGTH = "CARD_LENGTH";
	public final static String COL_SYS_MDS_BIN_TABLE_BIN_LENGTH = "BIN_LENGTH";
	public final static String COL_SYS_MDS_BIN_TABLE_USAGE_DOMAIN = "USAGE_DOMAIN";
	public final static String COL_SYS_MDS_BIN_TABLE_PROCESSING_CLS = "PROCESSING_CLASS";
	public final static String COL_SYS_MDS_BIN_TABLE_AREA_OF_EVENT = "AREA_OF_EVENT";
	public final static String COL_SYS_MDS_BIN_TABLE_SRV_TYPE = "SERVICE_TYPE";
	public final static String COL_SYS_MDS_BIN_TABLE_CARD_BRAND = "CARD_BRAND";
	public final static String COL_SYS_MDS_BIN_TABLE_CARD_ORGA = "CARD_ORGANIZATION";
	public final static String COL_SYS_MDS_BIN_TABLE_MEMBER_ID = "MEMBER_ID";
	public final static String COL_SYS_MDS_BIN_TABLE_TERM_CATG = "TERMINAL_CATEGORY";
	public final static String COL_SYS_MDS_BIN_TABLE_CTRY_CODE = "COUNTRY_CODE";
	public final static String COL_SYS_MDS_BIN_TABLE_CIRRUS_FLAG = "CIRRUS_FLAG";
	public final static String COL_SYS_MDS_BIN_TABLE_MASTERCARD_FLAG = "MASTERCARD_FLAG";
	public final static String COL_SYS_MDS_BIN_TABLE_PLUS_FLAG = "PLUS_FLAG";
	public final static String COL_SYS_MDS_BIN_TABLE_VISA_FLAG = "VISA_FLAG";
	public final static String COL_SYS_MDS_BIN_TABLE_MAESTRO_FLAG = "MAESTRO_FLAG";
	public final static String COL_SYS_MDS_BIN_TABLE_MASTER_MONEY_FLAG = "MASTER_MONEY_FLAG";
	public final static String COL_SYS_MDS_BIN_TABLE_MASTER_BANKING_FLAG = "MASTER_BANKING_FLAG";
	public final static String COL_SYS_MDS_BIN_TABLE_STAND_IN_FLAG = "STAND_IN_FLAG";
	public final static String COL_SYS_MDS_BIN_TABLE_AMERICAN_EXPRESS_FLAG = "AMERICAN_EXPRESS_FLAG";
	public final static String COL_SYS_MDS_BIN_TABLE_MASTER_CASH_FLAG = "MASTER_CASH_FLAG";
	public final static String COL_SYS_MDS_BIN_TABLE_VISA_CASH_FLAG = "VISA_CASH_FLAG";
	public final static String COL_SYS_MDS_BIN_TABLE_PROCESSOR_ID = "PROCESSOR_ID";

	public final static String TABLE_SYS_DOMESTIC_BIN_TABLE = "SYS_DOMESTIC_BIN_TABLE";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_BIN_LENGTH = "BIN_LENGTH";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_START_BIN_VALUE = "START_BIN_VALUE";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_END_BIN_VALUE = "END_BIN_VALUE";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_CARD_LENGTH = "CARD_LENGTH";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_MEMBER_ID = "MEMBER_ID";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_PROCESSING_CLS = "PROCESSING_CLASS";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_CARD_BRAND = "CARD_BRAND";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_SRV_TYPE = "SERVICE_TYPE";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_CARD_ORGA = "CARD_ORGANIZATION";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_AREA_OF_EVENT = "AREA_OF_EVENT";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_USAGE_DOMAIN = "USAGE_DOMAIN";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_TERM_CATG = "TERMINAL_CATEGORY";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_SEL_KEY = "SELECTION_KEY";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_CARD_PRODUCT_CODE = "CARD_PRODUCT_CODE";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_CARD_PRODUCT_DESC = "CARD_PRODUCT_DESC";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_MEMBER_NAME = "MEMBER_NAME";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_CDV_VALIDATION = "CDV_VALIDATION";
	public final static String COL_SYS_DOMESTIC_BIN_TABLE_ISS_RGN = "ISSUER_REGION";

	public static final String COL_ROW_ID = "ROWID";

	// ---------> Names of the PL/SQL procedures referenced <-----------------
	public static final String PROC_GET_SELECTION_LIST = "Bw_Process_Select.GetSelectionList";
	public static final String PROC_GET_SELECTION_COLS = "Bw_Process_Select.GetSelectionColumns";
	public static final String PROC_GET_SELECTION_FOR_PROCESS = "Bw_Process_Select.GetSelectionForProcess";

	public static final String FUNC_INS_EXT_TABLE = "Bw_Lib_Temptables.InsertUsingExternalTable";
	public static final String FUNC_CREATE_XFC_TEMP_TABLE = "Bw_Lib_Temptables.CreateXFCTempTable";

}
