package com.smyld.bw.db;

public interface BWDBValue {

	// '
	// 'Public Const TRN_CATEG_BALANCE_ADJ As String = "030" 'RECYCLE (used by
	// now-redundant Telekurs functionality)
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '019-061 = transaction types used for merchant phase 2
	// '
	// '
	// '
	// '
	// '
	// 'functionality]. Now using range 470-499
	// '
	// '
	// ' Standard BW Non financial transactions
	// ' (used with category = "014", Class = "011")
	// '
	// 'TRN_TYPES_BALANCE_ENQUIRY As String = "016" ---- defined above
	// '
	// '
	// '
	// '(beware duplication of code with octagon !)
	// '
	// '
	// '
	// '*** DO NOT HARD-CODE ANYTHING IN THE RANGE 401-469 !!! THESE ARE
	// USER-DEFINED ***
	// '
	// '*** 470-499 reserved for installment loans functionality ***
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// 'IPM
	// '
	// '
	// '
	// '--- e-commerce START 'if e-commerce capture methods are added, then
	// modify function IsEcommerceCaptM()
	// '
	// '--- e-commerce END
	// '
	// '
	// '
	// ' 025 Gabci 20040924
	// 'Public Const TRN_CLEAR_ please use As String = "220"
	// '
	// 'RECONCILIATION SPECIAL CLEARING CHANNELS (not actual CLEARING channels,
	// but usefule to have them as clearing channels)
	// 'Range 490-499 reserved for matching results (GROUPS='M')
	// 'Range 501-515 reserved for Saudi Bank Reconilication channels
	// (GROUPS='R')
	// '
	// 'Range 800 through 899 is used for domestic clearing channels
	// 'In the first phase this channels will be hard-coded, but later on this
	// will be defined using
	// 'an oracle table (SYS_PROCESS_CHANNELS)
	// '
	// '
	// 'The following is a list of all merchant inward channels (can be used
	// directly in sql statements)
	// '
	// '
	// '
	// '
	// '
	// 'fee applied in tiers and copied to bonus club. Settlement to bonus
	// 'club (intra-acct). Both triggered by bonus club's settlement months.
	// '
	// '(having acct category: 001)
	// '(having acct category: 004)
	// '(having acct category: 001), taking trigger balance from security limit
	// 'NOTE: the above do not make use of LAST_SETTLEMENT_DATE
	// '
	// '
	// '
	// 'value high indicates expiry period in months.
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// ' 'range 420 - 500 reserved for Service Provide fee trigger sources
	// '
	// '
	// '434 to 449, + 451, 486, 487 define the SP Transactions fee (except
	// trigger 440)
	// 'These vary by: (i) % of Value or Base Fee * Count
	// ' (ii) DCC/ Non-DCC/ All
	// ' (iii) tier calculation: monthly, yearly, contract life
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// 'that can affect the SP Residency fee, must have
	// 'index field LESS than 499 !!
	// '
	// '
	// '
	// ' "009" BiMonthly (1/3) ?? (check LUBJ) not implemented
	// ' "010" BiMonthly (1/4) ?? (check LUBJ) not implemented
	// '
	// '
	// '
	// '
	// '----Range 200-299 of bwt_fee_category reserved for multiple transaction
	// charge functionality----
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// 'Range: 500-599 reserved for Merchant-specific ONUS area of events (used
	// to differentiate issuing charges)
	// 'Range: 600-699 reserved for Client tariff based ONUS area of events
	// (used to differentiate acquiring charges)
	// '
	// '
	// '
	// 'New Tariff added 26/03/01 after reviewing EPI's Interchange fee
	// calculation
	// 'New tariffs added for domestic UK and VISA Int 12/10/2001
	// 'New tariffs added 02/07/2002 for US domestic car rental transactions
	// '
	// 'UCAF Tariffs added 07/08/2002
	// '
	// 'New tariff added for the MC Electronic Interchange Program ECCSS 02.1
	// '
	// '
	// 'New tariffs for MC dom Switzerland
	// '
	// '
	// 'US Rates
	// '[EJ] 2005-02-04 Added to support new chip logic
	// '
	// 'New tariffs added 2004-10-29 for chip transactions
	// '[EJ] 2004-11-14 Change it from General to Authorized
	// '[EJ] 2004-11-14 Add tariffs for EMV
	// '
	// '[EJ] 2005-02-25 New tariffs for 05.1 Release
	// '
	// '[EJ 2005-05-24] Add tariff for Turkish Supercard
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// 'by MasterCom or VIEW external systems. Fulfillment messages still need
	// to be loaded
	// 'in BW3 but suppressed and not picked by the exception processing.
	// '
	// '
	// '
	// '
	// '
	// '
	// 'bwt_production_types
	// '
	// '
	// 'Public Const SEQ_BATCH_INP_BATCH_NO As String = "041" 'obsolete... may
	// be reused
	// 'RESERVED RANGE FOR BWNET SEQUENCES ......... = "200" to "299"
	// 'USER DEFINED RANGE ...... "800" to "899"
	// '
	// '
	// '
	// '
	// '
	// 'range 001 - 099 is user-definable and should not be hardcoded (except
	// for range 020-029)
	// '
	// '
	// 'range 301-399 is user-definable and should not be hardcoded
	// '
	// '
	// '
	// 'range 600-630: use for accrual / capitalization frequencies: Use groups
	// I = accrual, J = capitalization, K = both
	// '
	// '
	// '
	// 'reserved for Card Organization Statistics. Please do not use !!! [s.m.
	// 07/01/2003]
	// '
	// 'for clients that card renewals base on client tariff
	// '
	// 'DO NOT CHANGE THE FOLLOWING COMMENT LINE IN VB:
	// 'PLSQL_CUTOFF_LINE - VB
	// ONLY----------------------------------------------------------------------------------
	// '
	// 'Public Const PROC_NAME_STAT_OUT As String = "057" RECYCLE !!
	// 'Public Const PROC_NAME_PAYORD_OUT As String = "065" RECYCLE!! (telekurs)
	// 'Public Const PROC_NAME_NATW_DD_OUT As String = "103" 'Recycle!! (natwest
	// process)
	// 'Public Const PROC_NAME_NWB_AIRTIME_CH As String = "114" RECYCLE !!!
	// ' 026 Gabci 20040624
	// 'Public Const PROC_NAME_KDB_TRAN_DOWNLOAD_ACQ As String = "117"
	// 'Boris: conflict - change to 240
	// 'Public Const PROC_NAME_IAPA_EMBOSS As String = "233"
	// 'Public Const PROC_NAME_CURR_RATES_UPDATE As String = "243" RECYCLE!!
	// (belonged to telekurs)
	// 'Public Const PROC_NAME_STAT_OUT_TKS_NEW As String = "365" RECYCLE !!
	// 'Public Const PROC_NAME_XPON_DIR_DEBIT_OUT As String = "476"
	// 'RECYCLE...process never used
	// 'Public Const PROC_NAME_KDB_TRAN_DOWNLOAD_NCH As String = "486"
	// ' 027 Gabci 20050406 Removed
	// ' Public Const PROC_NAME_KDB_CTLF_NO_INW As String = "507"
	// '----------------------Process name 540-560 reserved for new KBS
	// interfaces
	// ' Riyadh Bank/Saudi Banks reconciliation (report) files.
	// '
	// '
	// ' SSC - Sport Sponsor concept
	// '
	// '----insert new process name here----
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '900...999 reserved for internal use
	// '
	// '
	// '
	// '
	// 'range 001-099 to be used for account billing cycles
	// ' creation and expiry dates.
	// '
	// 'range 100-149 to be used for service expiry dates
	// '
	// 'range 150-199 to be used for interest capitalisation cycles
	// '
	// '
	// '
	// 'Range 100 - 199 reserved for Procard RK functionality (refer CEKAB,
	// MERCSTAT)
	// 'Public Const CHRG_INTEREST_3_MONTH As String = "103" 'no longer
	// hardcoded - range 100-199 is reserved
	// 'Public Const CHRG_INTEREST_6_MONTH As String = "106"
	// 'Public Const CHRG_INTEREST_12_MONTH As String = "112"
	// '
	// 'Range 200 - 299 reserved for multiple charge functionality (in
	// conjunction with cht_fee_category)
	// '
	// '
	// '
	// 'The low range 001-099 is used for value-balance based formulas
	// '
	// 'Range 100-200 reserved for monthly balance based formulas
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// 'Card brands 013 to 014 reserved for Procard Proprietary
	// 'Card brands 017 to 027 reserved for Procard Proprietary
	// 'Card brand 035 reserved for Procard Proprietary
	// '
	// 'Range 000-099 is reserved for BW3 standard card brands
	// 'Range 100-199 may be used for any installation specific proprietary
	// brands
	// '
	// '
	// '007-to-019 reserved for Procard Proprietary organizations
	// 'use 100-to-199 for proprietary (may overlap between various
	// installations)
	// '
	// '
	// '
	// '
	// '
	// '
	// 'Public Const INST_CACB_YEMEN As String = "0009" 'Cooperative &
	// Agricultural Credit Bank
	// 'Public Const INST_VARBANK As String = "0015" no longer supported SV
	// 18/02/2003
	// 'Public Const INST_EXPANDIA As String = "0016" no longer supported SV
	// 18/02/2003
	// 'Public Const INST_LJUBLJANSKA As String = "0018"
	// 'Public Const INST_BOLIVIA_ATC As String = "0020" 'project discontinued
	// (BankworksX)
	// 'Public Const INST_ECC_EGYPT As String = "0038" 'Egypt - ECC [SV
	// 15/12/2004 project discontinued]
	// 'Public Const INST_SCB_CHINA As String = "0042" 'China - Standard
	// Chartered Bank [SV 15/12/2004 project discontinued]
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// 'Add any new constants below to class ProcMsgLog in procedure
	// 'ValidateMsgMode()'
	// '
	// 'use in HashInit & ReplaceString
	// '
	// '
	// 'Public Const FMT_NUM_LZ As Integer = 1
	// '
	// 'Use with GetIPMAmount
	// 'Use with ISOSetPDSSubFieldByName
	// 'Use with FormatAmountOut
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// 'use in DateGetDiff()
	// '
	// 'to this set as it is used for DateGetDiff() only
	// '
	// 'use in DateAddDays(), DateAddMonths(), DateLastInMonth()
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// 'PIN required
	// '
	// '
	// '
	// '
	// '
	// '
	// 'Constants used by function ValidateData in Lib_Misc2
	// '
	// 'The following are used for the CREATE_ON_DEMAND field.
	// 'This field previously took its values from bwt_confirmation.
	// 'The meaning of values "000" (CONF_NO), "001" (CONF_YES) and
	// '"003" (CONF_CONDITIONAL) has been retained for backward
	// 'compatibility.
	// '
	// '
	// 'Constants used by function ConvertDate
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// 'Further IGP modes can be user defined (using new field in
	// 'cht_value_balance_mode to indicate the IGP sub-balances.
	// 'So far, it is assumed that all mode >= "002" are all IGP modes.
	// '
	// 'Constants used by LV (list view) functions
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '
	// '--Installment loans
	// '
	// 'bwt_contract_charge_source
	// '
	// '001-099: normal charge sources
	// '100-199: complex charge sources (based on formulas)
	// '500-599: user defined compound charge sources
	// '
	// '
	// '
	// '
	// '-----------
	// '
	// 'Process Message Logging Library
	// '
	// '
	// '
	// '
	// '
	// '
	// 'Groups that use other groups
	// 'Special MCC Groups
	// '
	// '001-899: user-defined regions
	// '
	// '
	// '... others are not being used
	// '
	public final static String TRN_CLASS_BATCH_CONTROL = "001";
	public final static String TRN_CLASS_CLEARING_TRANS = "002";
	public final static String TRN_CLASS_GL_TRANS = "003";
	public final static String TRN_CLASS_INTERNAL_OWN = "004";
	public final static String TRN_CLASS_SETTL_TRANS = "005";
	public final static String TRN_CLASS_DETAIL_GROUP = "006";
	public final static String TRN_CLASS_SETTL_GROUP = "007";
	public final static String TRN_CLASS_CLEARING_ADV = "009";
	public final static String TRN_CLASS_AUTHORIZATION = "010";
	public final static String TRN_CLASS_NON_FINANCIAL = "011";
	public final static String TRN_CLASS_SOURCE_CTRL = "012";
	public final static String TRN_CLASS_DESTINATION_CTRL = "013";
	public final static String TRN_CLASS_ACCUMULATE_TRANS = "014";
	public final static String TRN_CLASS_CLR_SOURCE_CTRL = "015";
	public final static String TRN_CLASS_CLR_DEST_CTRL = "016";
	public final static String TRN_CATEG_PRESENTMENTS = "001";
	public final static String TRN_CATEG_CHARGEBACKS = "002";
	public final static String TRN_CATEG_REPRESENTMENTS = "003";
	public final static String TRN_CATEG_RETRIEVALS = "004";
	public final static String TRN_CATEG_INT_FEES = "005";
	public final static String TRN_CATEG_INTEREST = "006";
	public final static String TRN_CATEG_CHARGES_FEES = "007";
	public final static String TRN_CATEG_PAYMENTS = "008";
	public final static String TRN_CATEG_BONUS = "009";
	public final static String TRN_CATEG_DETAIL_INFO = "011";
	public final static String TRN_CATEG_INVALID_RESP = "012";
	public final static String TRN_CATEG_ADVICE = "014";
	public final static String TRN_CATEG_SUMMARY_CONTROL = "015";
	public final static String TRN_CATEG_SOURCE_CONTROL = "016";
	public final static String TRN_CATEG_DEST_CONTROL = "017";
	public final static String TRN_CATEG_GL_CONTROL = "018";
	public final static String TRN_CATEG_SETTL_INFO = "019";
	public final static String TRN_CATEG_RETRV_FULLF = "020";
	public final static String TRN_CATEG_SOURCE_FEES = "021";
	public final static String TRN_CATEG_FEE_COLLECT = "022";
	public final static String TRN_CATEG_FULLF_FEE = "023";
	public final static String TRN_CATEG_REJECT_FINANCIAL = "024";
	public final static String TRN_CATEG_REJECT_NON_FINAN = "025";
	public final static String TRN_CATEG_REJECT_BATCH = "026";
	public final static String TRN_CATEG_OLD_REJECT_REVERSAL = "027";
	public final static String TRN_CATEG_AUTHORISATIONS = "027";
	public final static String TRN_CATEG_INT_CHARGES = "028";
	public final static String TRN_CATEG_FRAUD_DETAIL = "031";
	public final static String TRN_CATEG_FRAUD_REJECT = "032";
	public final static String TRN_CATEG_FRAUD_WARNING = "033";
	public final static String TRN_CATEG_CHECK = "034";
	public final static String TRN_CATEG_PAYMENT_REQUEST = "035";
	public final static String TRN_CATEG_REJECT_REVERSAL = "036";
	public final static String TRN_CATEG_ACCRUED_INTEREST = "037";
	public final static String TRN_CATEG_INT_BELOW_THRESHOLD = "038";
	public final static String TRN_CATEG_PAYMENT_ADJUSTMENT = "039";
	public final static String TRN_CATEG_DECLINES = "040";
	public final static String TRN_CATEG_REFERRALS = "041";
	public final static String TRN_CATEG_FAILURES = "042";
	public final static String TRN_CATEG_PAYMENT_REJECTS = "043";
	public final static String TRN_CATEG_FEE_CHARGEBACK = "044";
	public final static String TRN_CATEG_FEE_RESUBMISSION = "045";
	public final static String TRN_CATEG_FEE_SECOND_CHARGE = "046";
	public final static String TRN_CATEG_FRAUD_REPORT = "047";
	public final static String TRN_CATEG_PAYM_REJECT_NON_REPR = "048";
	public final static String TRN_CATEG_ADJUSTMENT = "050";
	public final static String TRN_CATEG_ALL = "999";
	public final static String TRN_STATE_ENTERED = "001";
	public final static String TRN_STATE_POSTED = "002";
	public final static String TRN_STATE_ERROR = "003";
	public final static String TRN_STATE_PAID = "004";
	public final static String TRN_STATE_PENDING = "005";
	public final static String TRN_STATE_MATCHED = "006";
	public final static String TRN_STATE_PROCESSED = "007";
	public final static String TRN_STATE_RELEASED = "008";
	public final static String TRN_STATE_CLEARED = "009";
	public final static String TRN_STATE_UNPOSTED = "010";
	public final static String TRN_STATE_REPROCESSED = "011";
	public final static String TRN_STATE_SUPPRESSED = "012";
	public final static String TRN_STATE_PURGED = "013";
	public final static String TRN_STATE_DECLINED = "020";
	public final static String TRN_STATE_CHARGEBACKED = "050";
	public final static String TRN_STATE_NO_BIN_TAB = "200";
	public final static String TRN_STATE_NO_SERVICE = "201";
	public final static String TRN_STATE_NO_ASSIGNED_SVC = "202";
	public final static String TRN_STATE_NO_INW_FEES = "203";
	public final static String TRN_STATE_NO_OUT_FEES = "204";
	public final static String TRN_STATE_NO_FEES = "205";
	public final static String TRN_STATE_LUHN_FAILED = "206";
	public final static String TRN_STATE_NO_DESTINATION = "207";
	public final static String TRN_STATE_INVALID_POS_CD = "208";
	public final static String TRN_STATE_NO_SVC_CONTRACT = "209";
	public final static String TRN_STATE_NOT_RECONCILED = "210";
	public final static String TRN_STATE_NO_CLIENT = "211";
	public final static String TRN_STATE_NO_POST_INST = "212";
	public final static String TRN_STATE_NO_ACCT_REC = "213";
	public final static String TRN_STATE_GL_FAILED = "214";
	public final static String TRN_STATE_NO_TERM_CATEGORY = "215";
	public final static String TRN_STATE_INV_CARDNO_LENGTH = "216";
	public final static String TRN_STATE_ZERO_VALUE = "217";
	public final static String TRN_STATE_CONTRACT_CLOSED = "218";
	public final static String TRN_STATE_DCC_MISMATCH = "219";
	public final static String TRN_STATE_NO_PRESENTMENT = "220";
	public final static String TRN_STATE_DEVICE_NOT_FOUND = "221";
	public final static String TRN_STATE_DCC_INV_FORMAT = "222";
	public final static String TRN_STATE_NO_PAYMENT = "223";
	public final static String TRN_STATE_INVALID_TRAN_DATE = "224";
	public final static String TRN_STATE_CONTRACT_INVALID = "225";
	public final static String TRN_STATE_CYCLE_CLOSED = "226";
	public final static String TRN_STATE_FUTURE_TRAN_DATE = "227";
	public final static String TRN_STATE_LARGE_VALUE = "228";
	public final static String TRN_STATE_LATE_PRESENTMENT = "229";
	public final static String TRN_STATE_AUTH_CODE_REQUIRED = "230";
	public final static String TRN_STATE_WRONG_CARD_STATUS = "231";
	public final static String TRN_STATE_WRONG_ACCT_STATUS = "232";
	public final static String TRN_STATE_WRONG_CNTRCT_STATUS = "233";
	public final static String TRN_STATE_PH1_NO_POST_INST = "234";
	public final static String TRN_STATE_NO_ACQUIRING_BIN = "235";
	public final static String TRN_STATE_EMPTY_FEE_RULE = "236";
	public final static String TRN_STATE_INVALID_BUSS_CLASS = "237";
	public final static String TRN_STATE_INVALID_AMEX_SE_NO = "238";
	public final static String TRN_STATE_INV_CARD_PREFIX = "239";
	public final static String TRN_STATE_SUSPECTED_FRAUD = "300";
	public final static String TRN_STATE_CONFIRMED_FRAUD = "301";
	public final static String TRN_STATE_NO_FRAUD = "302";
	public final static String TRN_STATE_FRAUD_BANK_LOSS = "303";
	public final static String TRN_STATE_FRAUD_MERCH_LOSS = "304";
	public final static String TRN_STATE_DISCARD_VALUE = "310";
	public final static String TRN_STATE_RK_CREDIT_NA = "311";
	public final static String TRN_STATE_NO_RK_FEES = "312";
	public final static String TRN_STATE_NO_COD_FEES = "313";
	public final static String TRN_STATE_PAYM_REJECTED = "400";
	public final static String TRN_STATE_PAYM_CANCELLED = "401";
	public final static String TRN_STATE_PAYM_TRANSFER_BACK = "402";
	public final static String TRN_STATE_EXACT_MATCH = "501";
	public final static String TRN_STATE_MATCHED_BY_AUTH_CODE = "502";
	public final static String TRN_STATE_APPROX_MATCH = "503";
	public final static String TRN_STATE_NOT_MATCHED = "504";
	public final static String TRN_STATE_ITEMTYP_MISC = "800";
	public final static String TRN_STATE_ITEMTYP_32 = "801";
	public final static String TRN_STATE_ITEMTYP_33 = "802";
	public final static String TRN_STATE_ITEMTYP_42 = "803";
	public final static String TRN_STATE_ITEMTYP_43 = "804";
	public final static String TRN_STATE_ITEMTYP_55 = "805";
	public final static String TRN_STATE_ITEMTYP_81 = "806";
	public final static String TRN_STATE_ITEMTYP_82 = "807";
	public final static String TRN_STATE_ITEMTYP_83 = "808";
	public final static String TRN_STATE_ITEMTYP_85 = "809";
	public final static String TRN_TYPES_NA = "000";
	public final static String TRN_TYPES_INTERCHANGE_PAYMENT = "002";
	public final static String TRN_TYPES_INT_PAY_INCOME = "003";
	public final static String TRN_TYPES_RETAIL = "005";
	public final static String TRN_TYPES_CREDIT = "006";
	public final static String TRN_TYPES_TELLER_CASH = "007";
	public final static String TRN_TYPES_UNIQUE = "008";
	public final static String TRN_TYPES_ATM_CASH = "009";
	public final static String TRN_TYPES_ACCT_FUNDING_DR = "011";
	public final static String TRN_TYPES_ACCT_FUNDING_CR = "012";
	public final static String TRN_TYPES_FUNDS_TRANSFER = "014";
	public final static String TRN_TYPES_CAMPAIGN_PURCH = "015";
	public final static String TRN_TYPES_BALANCE_ENQUIRY = "016";
	public final static String TRN_TYPES_MERCH_PAYMENTS = "250";
	public final static String TRN_TYPES_FEE_COLLECTION_DR = "256";
	public final static String TRN_TYPES_MERCH_COLLECTION = "270";
	public final static String TRN_TYPES_CHG_COLLECTION = "273";
	public final static String TRN_TYPES_CHG_CREDIT = "274";
	public final static String TRN_TYPES_ADJ_CREDIT = "640";
	public final static String TRN_TYPES_ADJ_COLLECTION = "641";
	public final static String TRN_TYPES_FEE_COLLECTION_CR = "712";
	public final static String TRN_TYPES_CASH_PURCHASE = "019";
	public final static String TRN_TYPES_CASH_PURCHASE_CREDIT = "020";
	public final static String TRN_TYPES_CFT_EXPENDITURE = "024";
	public final static String TRN_TYPES_RETAIL_INCOME = "025";
	public final static String TRN_TYPES_CREDIT_EXPENDITURE = "026";
	public final static String TRN_TYPES_CASH_INCOME = "027";
	public final static String TRN_TYPES_UNIQUE_INCOME = "028";
	public final static String TRN_TYPES_ATM_INCOME = "029";
	public final static String TRN_TYPES_MERCH_PURCH_DEPOSIT = "030";
	public final static String TRN_TYPES_MERCH_CASH_DEPOSIT = "031";
	public final static String TRN_TYPES_MERCH_CASH_MANUAL = "032";
	public final static String TRN_TYPES_MERCH_CASH_POS = "033";
	public final static String TRN_TYPES_MERCH_CASH_ATM = "034";
	public final static String TRN_TYPES_MERCH_DEBIT = "060";
	public final static String TRN_TYPES_MERCH_DEBIT_DR_COMM = "061";
	public final static String TRN_TYPES_INSTALLMENT_PURCH = "065";
	public final static String TRN_TYPES_LOYALTY_CHECK = "070";
	public final static String TRN_TYPES_LOYALTY_CHECK_FEE = "071";
	public final static String TRN_TYPES_LOYALTY_PROG_CHG = "072";
	public final static String TRN_TYPES_LOYALTY_CHECK_DR = "073";
	public final static String TRN_TYPES_LOYALTY_CHECK_FEE_CR = "074";
	public final static String TRN_TYPES_LOYALTY_PROG_CHG_CR = "075";
	public final static String TRN_TYPES_MINI_STMT = "080";
	public final static String TRN_TYPES_PIN_CHANGE = "081";
	public final static String TRN_TYPES_STMT_REQ = "082";
	public final static String TRN_TYPES_CHQ_BK_REQ = "083";
	public final static String TRN_TYPES_E_SERVICE = "084";
	public final static String TRN_TYPES_INTERN_ACC_XFR = "085";
	public final static String TRN_TYPES_CHQ_DEPOSIT = "086";
	public final static String TRN_TYPES_CR_CRD_PAYM = "087";
	public final static String TRN_TYPES_LOAN_PAYM = "088";
	public final static String TRN_TYPES_TRAFFIC_FINES = "089";
	public final static String TRN_TYPES_BILL_TELECOM = "090";
	public final static String TRN_TYPES_BILL_ELEC = "091";
	public final static String TRN_TYPES_BILL_WATER = "092";
	public final static String TRN_TYPES_BONUS_CR = "100";
	public final static String TRN_TYPES_BONUS_CHECK_CR = "101";
	public final static String TRN_TYPES_REDEEM_CHECK_DR = "102";
	public final static String TRN_TYPES_REDEEM_CHECK_DR_2 = "103";
	public final static String TRN_TYPES_REDEEM_CHECK_CR = "104";
	public final static String TRN_TYPES_REDEEM_CHECK_CR_2 = "105";
	public final static String TRN_TYPES_PAYMENTS = "200";
	public final static String TRN_TYPES_DD_PAYMENTS = "201";
	public final static String TRN_TYPES_PAYMENTS_BY_BANK = "202";
	public final static String TRN_TYPES_MIN_PAYM_REQUEST = "205";
	public final static String TRN_TYPES_PAYMNT_REQ_DIR_DEB = "206";
	public final static String TRN_TYPES_PAYMNT_REQ_PAPER = "207";
	public final static String TRN_TYPES_ADD_PAYMNT_REQUEST = "208";
	public final static String TRN_TYPES_PAYMNT_BANK_ORDER = "209";
	public final static String TRN_TYPES_REJECT_OCT_CR = "210";
	public final static String TRN_TYPES_REJECT_OCT_DR = "211";
	public final static String TRN_TYPES_FINAL_BALANCE_CR = "212";
	public final static String TRN_TYPES_REJECT_MT_OCT_CR = "213";
	public final static String TRN_TYPES_REJECT_MT_OCT_DR = "214";
	public final static String TRN_TYPES_SALARY_DEPOSIT = "213";
	public final static String TRN_TYPES_PAYMENT_BS = "220";
	public final static String TRN_TYPES_DR_INT_CHARGES = "303";
	public final static String TRN_TYPES_CR_INT_CHARGES = "400";
	public final static String TRN_TYPES_SECURITY_DEPOSIT = "486";
	public final static String TRN_TYPES_MISC_DR_FEES = "500";
	public final static String TRN_TYPES_RETRIEVE_FEE_DR = "502";
	public final static String TRN_TYPES_FEE_COLLECT_DR = "520";
	public final static String TRN_TYPES_CARD_FEE_MAIN = "531";
	public final static String TRN_TYPES_CARD_FEE_SUB = "532";
	public final static String TRN_TYPES_STORAGE_FEE = "537";
	public final static String TRN_TYPES_ADJ_MERCH_CR = "594";
	public final static String TRN_TYPES_ADJ_MERCH_DR = "595";
	public final static String TRN_TYPES_MISC_CR_FEES = "600";
	public final static String TRN_TYPES_FEE_COLLECT_CR = "601";
	public final static String TRN_TYPES_RETRIEVE_FEE_CR = "602";
	public final static String TRN_TYPES_PROC_CHARGES_DR = "610";
	public final static String TRN_TYPES_PROC_CHARGES_CR = "611";
	public final static String TRN_TYPES_IOI_FEES_DR = "612";
	public final static String TRN_TYPES_IOI_FEES_CR = "613";
	public final static String TRN_TYPES_AIRLINE_FEES_DR = "614";
	public final static String TRN_TYPES_AIRLINE_FEES_CR = "615";
	public final static String TRN_TYPES_CONV_FEES_DR = "616";
	public final static String TRN_TYPES_CONV_FEES_CR = "617";
	public final static String TRN_TYPES_CONV_FEES_A_DR = "618";
	public final static String TRN_TYPES_CONV_FEES_A_CR = "619";
	public final static String TRN_TYPES_REJ_CHARGES_DR = "620";
	public final static String TRN_TYPES_REJ_CHARGES_CR = "621";
	public final static String TRN_TYPES_FEE_DIFF_DR = "622";
	public final static String TRN_TYPES_FEE_DIFF_CR = "623";
	public final static String TRN_TYPES_REVERSAL_DIFF_DR = "624";
	public final static String TRN_TYPES_REVERSAL_DIFF_CR = "625";
	public final static String TRN_TYPES_INT_SVC_ASSESS_DR = "626";
	public final static String TRN_TYPES_INT_SVC_ASSESS_CR = "627";
	public final static String TRN_TYPES_ISA_ALLOCATION_DR = "628";
	public final static String TRN_TYPES_ISA_ALLOCATION_CR = "629";
	public final static String TRN_TYPES_FAX_FEE = "630";
	public final static String TRN_TYPES_TRANSFER_FEE = "631";
	public final static String TRN_TYPES_SETTLEMENT_FEE = "632";
	public final static String TRN_TYPES_ADJ_INT_CR = "829";
	public final static String TRN_TYPES_ADJ_INT_DR = "834";
	public final static String TRN_TYPES_ASSESSMENT_FEE_DR = "835";
	public final static String TRN_TYPES_PER_TRAN_FEE_DR = "836";
	public final static String TRN_TYPES_INTCHG_FEE_DR = "844";
	public final static String TRN_TYPES_INTCHG_FEE_CR = "845";
	public final static String TRN_TYPES_GL_DR = "900";
	public final static String TRN_TYPES_GL_CR = "901";
	public final static String TRN_TYPES_MISC_DR = "950";
	public final static String TRN_TYPES_CASH_TRIP_ALLOW = "953";
	public final static String TRN_TYPES_MISC_CR = "951";
	public final static String TRN_TYPES_ALL_CR = "997";
	public final static String TRN_TYPES_ALL_DR = "998";
	public final static String TRN_TYPES_ALL = "999";
	public final static String TRN_TYPES_PAYMENT_DUE = "PYD";
	public final static String TRN_TYPES_PAYMENT_RCVD = "PYR";
	public final static String TRN_TYPES_PAYMENT_CREATED = "PYC";
	public final static String TRN_TYPES_INT_FEES_POSTED = "IFP";
	public final static String TRN_TYPES_INTEREST_POSTED = "INT";
	public final static String TRN_TYPES_PENALTY_POSTED = "PNY";
	public final static String TRN_TYPES_FEE_POSTED = "FEE";
	public final static String TRN_TYPES_CHARGE_POSTED = "CHG";
	public final static String TRN_TERMC_MANUAL = "001";
	public final static String TRN_TERMC_POS = "002";
	public final static String TRN_TERMC_POS_PIN = "003";
	public final static String TRN_TERMC_POS_ICC = "004";
	public final static String TRN_TERMC_ATM = "005";
	public final static String TRN_TERMC_ATM_ICC = "006";
	public final static String TRN_TERMC_CAT1_AUTO_DISP = "007";
	public final static String TRN_TERMC_CAT2_SELF_SERV = "008";
	public final static String TRN_TERMC_CAT3_LIMITED_AMT = "009";
	public final static String TRN_TERMC_CAT4_IN_FLIGHT = "010";
	public final static String TRN_TERMC_CAT_REMOTE = "011";
	public final static String TRN_TERMC_POS_ICC_MS_KE = "012";
	public final static String TRN_TERMC_POS_ICC_MS = "013";
	public final static String TRN_TERMC_POS_ICC_KE = "014";
	public final static String TRN_TERMC_POS_ICC_CNTCTLES = "015";
	public final static String TRN_TERMC_ATM_ICC_MS = "016";
	public final static String TRN_TERMC_CAT6_ELEC_COMM = "017";
	public final static String TRN_TERMC_CAT7_TRANSP = "018";
	public final static String TRN_TERMC_ALL = "999";
	public final static String TRN_CAPTM_SWIPED_SIGNED = "001";
	public final static String TRN_CAPTM_SWIPED_PIN = "002";
	public final static String TRN_CAPTM_CUST_YES_KEY = "003";
	public final static String TRN_CAPTM_CUST_NO_KEY = "004";
	public final static String TRN_CAPTM_MANUAL = "010";
	public final static String TRN_CAPTM_ATM = "020";
	public final static String TRN_CAPTM_ATM_CHIP = "021";
	public final static String TRN_CAPTM_MAIL = "030";
	public final static String TRN_CAPTM_TEL = "040";
	public final static String TRN_CAPTM_MASTERPHONE = "041";
	public final static String TRN_CAPTM_CHIP_FULL_GRD = "043";
	public final static String TRN_CAPTM_CHIP_PARTIAL_GRD = "044";
	public final static String TRN_CAPTM_CNP_RECURRING = "045";
	public final static String TRN_CAPTM_SET_YES_CERT = "050";
	public final static String TRN_CAPTM_SET_NO_CERT = "051";
	public final static String TRN_CAPTM_ELEC_COMMERCE = "052";
	public final static String TRN_CAPTM_NON_SET = "053";
	public final static String TRN_CAPTM_CHANNEL_ENCRYPT = "054";
	public final static String TRN_CAPTM_SET_MERCHANT = "055";
	public final static String TRN_CAPTM_FULL_SET = "056";
	public final static String TRN_CAPTM_ENCRYPT_YES_CERT = "V5";
	public final static String TRN_CAPTM_ENCRYPT_NO_CERT = "V6";
	public final static String TRN_CAPTM_ENCRYPT_SSL = "V9";
	public final static String TRN_CAPTM_CHIP_INIT = "060";
	public final static String TRN_CAPTM_CHIP_NOT_REL = "061";
	public final static String TRN_CAPTM_CHIP_READ_SIGN = "062";
	public final static String TRN_CAPTM_CHIP_READ_PIN = "063";
	public final static String TRN_CAPTM_CHIP_OFFLINE = "064";
	public final static String TRN_CAPTM_CHIP_CNTCTLES = "065";
	public final static String TRN_CAPTM_CHIP_CNTLES_SIGN = "066";
	public final static String TRN_CAPTM_CAT1_PIN = "070";
	public final static String TRN_CAPTM_CAT2_AUTHORIZED = "071";
	public final static String TRN_CAPTM_CAT3_OFFLINE = "072";
	public final static String TRN_CAPTM_CAT4_IN_FLIGHT = "073";
	public final static String TRN_CAPTM_CAT_REMOTE = "074";
	public final static String TRN_CAPTM_CAT6_ELEC_COMM = "075";
	public final static String TRN_CAPTM_CAT7_TRANSP = "076";
	public final static String TRN_CAPTM_ELEC = "100";
	public final static String TRN_CAPTM_PAPER = "101";
	public final static String TRN_CAPTM_ALL = "999";
	public final static String TRN_BUSSC_MISCELLANEOUS = "504";
	public final static String TRN_BUSSC_TELLER = "505";
	public final static String TRN_BUSSC_ATM = "506";
	public final static String TRN_BUSSC_CASINO = "583";
	public final static String TRN_BUSSCISO_MISC = "5999";
	public final static String TRN_BUSSCISO_TELLER = "6010";
	public final static String TRN_BUSSCISO_ATM = "6011";
	public final static String TRN_BUSSCISO_CASINO = "7995";
	public final static String TRN_CLEAR_NA = "000";
	public final static String TRN_CLEAR_INW_ECCF = "001";
	public final static String TRN_CLEAR_OUT_ECCF = "002";
	public final static String TRN_CLEAR_INW_VISA_II = "003";
	public final static String TRN_CLEAR_OUT_VISA_II = "004";
	public final static String TRN_CLEAR_BATCH_INPUT = "005";
	public final static String TRN_CLEAR_BATCH_INPUT_ISS = "006";
	public final static String TRN_CLEAR_EPS_NET = "007";
	public final static String TRN_CLEAR_VISA_I = "009";
	public final static String TRN_CLEAR_INW_MC_INET = "011";
	public final static String TRN_CLEAR_OUT_MC_INET = "012";
	public final static String TRN_CLEAR_OUT_AMEX = "014";
	public final static String TRN_CLEAR_INW_MERCHANT = "015";
	public final static String TRN_CLEAR_OUT_MERCHANT = "016";
	public final static String TRN_CLEAR_INW_ON_US = "017";
	public final static String TRN_CLEAR_OUT_ON_US = "018";
	public final static String TRN_CLEAR_INW_CEKAB = "019";
	public final static String TRN_CLEAR_INW_LOYALTY = "020";
	public final static String TRN_CLEAR_INW_AURIGA = "021";
	public final static String TRN_CLEAR_OUT_BW3_ISSUER = "024";
	public final static String TRN_CLEAR_INW_VISA_NNSS = "025";
	public final static String TRN_CLEAR_OUT_VISA_NNSS = "026";
	public final static String TRN_CLEAR_OUT_BW3_FANTOM = "028";
	public final static String TRN_CLEAR_OUT_PAYM_PGIRO = "029";
	public final static String TRN_CLEAR_OUT_PAYM_BGIRO = "030";
	public final static String TRN_CLEAR_INW_ON_US_FEES = "031";
	public final static String TRN_CLEAR_INW_ON_US_PAYM = "032";
	public final static String TRN_CLEAR_OUT_DB_PAYM = "033";
	public final static String TRN_CLEAR_INW_CR_PAYM = "034";
	public final static String TRN_CLEAR_OUT_CR_PAYM = "035";
	public final static String TRN_CLEAR_INW_CONRAD = "036";
	public final static String TRN_CLEAR_INW_EXCEPTION = "037";
	public final static String TRN_CLEAR_BAL_ADJUST = "038";
	public final static String TRN_CLEAR_INW_VISA_ON_US = "039";
	public final static String TRN_CLEAR_INW_INT_ON_US = "040";
	public final static String TRN_CLEAR_BSC_OUT_DIRECT_DBTS = "040";
	public final static String TRN_CLEAR_RECLASSIFICATION = "041";
	public final static String TRN_CLEAR_OUT_SVC_PROV = "042";
	public final static String TRN_CLEAR_INW_EXCP_EPS = "043";
	public final static String TRN_CLEAR_INW_EXCP_UBS = "044";
	public final static String TRN_CLEAR_INW_EXCP_CSG = "045";
	public final static String TRN_CLEAR_INW_MC_SAFE = "046";
	public final static String TRN_CLEAR_OUT_MC_SAFE = "047";
	public final static String TRN_CLEAR_INW_VISA_USD = "048";
	public final static String TRN_CLEAR_OUT_PAYM_REQUEST = "049";
	public final static String TRN_CLEAR_OUT_PAYORD = "050";
	public final static String TRN_CLEAR_OUT_ACQUIRER = "051";
	public final static String TRN_CLEAR_OUT_MERCHANT_ZIB = "052";
	public final static String TRN_CLEAR_OUT_CARDSACCTS = "053";
	public final static String TRN_CLEAR_OUT_CARDFEES = "054";
	public final static String TRN_CLEAR_OUT_EMBOSS = "055";
	public final static String TRN_CLEAR_INW_ENCODING = "056";
	public final static String TRN_CLEAR_INW_GL_TRANS = "057";
	public final static String TRN_CLEAR_OUT_CSB_GL = "058";
	public final static String TRN_CLEAR_INW_REJ_DIRECT_DBTS = "060";
	public final static String TRN_CLEAR_OUT_BKR = "061";
	public final static String TRN_CLEAR_OUTW_OCTAGON = "065";
	public final static String TRN_CLEAR_INW_OCTAGON = "066";
	public final static String TRN_CLEAR_INW_BW2 = "067";
	public final static String TRN_CLEAR_CSB_DATA_CONV = "068";
	public final static String TRN_CLEAR_OUT_PAYM_HANDEL = "069";
	public final static String TRN_CLEAR_OUT_VISA_ON_US = "071";
	public final static String TRN_CLEAR_INW_ECCF_DOM = "072";
	public final static String TRN_CLEAR_OUT_ECCF_DOM = "073";
	public final static String TRN_CLEAR_OUT_TRN_EXPORT = "074";
	public final static String TRN_CLEAR_INW_JNB_POS = "075";
	public final static String TRN_CLEAR_NWB_AIRTIME_TXN = "076";
	public final static String TRN_CLEAR_INW_INET_ON_US = "077";
	public final static String TRN_CLEAR_OUT_INET_ON_US = "078";
	public final static String TRN_CLEAR_BAS_OUTW = "079";
	public final static String TRN_CLEAR_INW_ATM = "080";
	public final static String TRN_CLEAR_PROV_INW_BONUS = "081";
	public final static String TRN_CLEAR_INW_EXPN_AFFI = "082";
	public final static String TRN_CLEAR_INW_APACS29 = "083";
	public final static String TRN_CLEAR_INW_APACS40 = "084";
	public final static String TRN_CLEAR_INW_BACS = "085";
	public final static String TRN_CLEAR_OUT_BACS_CR = "086";
	public final static String TRN_CLEAR_OUT_BMS_MANUAL = "087";
	public final static String TRN_CLEAR_OUT_AUTOGIRO_BG_COMM = "088";
	public final static String TRN_CLEAR_OUT_AUTOGIRO_PG = "089";
	public final static String TRN_CLEAR_OUT_OCR_GIRO = "090";
	public final static String TRN_CLEAR_EP_STOPLIST = "091";
	public final static String TRN_CLEAR_VISA_STOPLIST = "092";
	public final static String TRN_CLEAR_OUT_MATERCARD_MATCH = "093";
	public final static String TRN_CLEAR_OUT_PAYM_FRISPAR = "094";
	public final static String TRN_CLEAR_OUT_AUTOGIRO_BG_PRIV = "095";
	public final static String TRN_CLEAR_INW_ECCF_ON_US = "096";
	public final static String TRN_CLEAR_OUT_ECCF_ON_US = "097";
	public final static String TRN_CLEAR_INW_AUTOGIRO_BGP = "098";
	public final static String TRN_CLEAR_INW_AUTOGIRO_BGC = "099";
	public final static String TRN_CLEAR_INW_AUTOGIRO_PG = "100";
	public final static String TRN_CLEAR_INW_INTRABANK = "101";
	public final static String TRN_CLEAR_OUT_INTRABANK = "102";
	public final static String TRN_CLEAR_OUT_NLB_PAYMENT = "105";
	public final static String TRN_CLEAR_OUT_NLB_DEBIT_CARD = "109";
	public final static String TRN_CLEAR_PBS_INWARD = "110";
	public final static String TRN_CLEAR_INW_ILF = "112";
	public final static String TRN_CLEAR_MKB_RBS_CHANNEL = "113";
	public final static String TRN_CLEAR_OUT_GSS_MERCH_PAYM = "114";
	public final static String TRN_CLEAR_INW_APPL_PROC_MISC = "115";
	public final static String TRN_CLEAR_OUT_BETALINGSSERVICE = "116";
	public final static String TRN_CLEAR_INW_BETALINGSSERVICE = "117";
	public final static String TRN_CLEAR_INW_ON_US_INSTALL = "118";
	public final static String TRN_CLEAR_OUT_BS_TOTAL = "119";
	public final static String TRN_CLEAR_INW_APPL_PROC_ACQ = "120";
	public final static String TRN_CLEAR_INW_AUTH_ACQ = "121";
	public final static String TRN_CLEAR_INW_VISA_TC57 = "122";
	public final static String TRN_CLEAR_INW_MDS = "123";
	public final static String TRN_CLEAR_OUT_MDS = "124";
	public final static String TRN_CLEAR_OUT_DOM_JONB_SPARROW = "125";
	public final static String TRN_CLEAR_INW_SPARROW = "126";
	public final static String TRN_CLEAR_OUT_PAYM_ATLAS = "127";
	public final static String TRN_CLEAR_OUT_DINERS = "128";
	public final static String TRN_CLEAR_INW_VUB_DOM_ONUS = "129";
	public final static String TRN_CLEAR_INW_VUB_DOM_NON_ONUS = "130";
	public final static String TRN_CLEAR_INW_VUB_DOM_EXTERNAL = "131";
	public final static String TRN_CLEAR_OUT_VUB_DOM_ONUS = "132";
	public final static String TRN_CLEAR_OUT_VUB_DOM_NON_ONUS = "133";
	public final static String TRN_CLEAR_OUT_VUB_DOM_EXTERNAL = "134";
	public final static String TRN_CLEAR_INW_HSBC_BRANCH_DEP = "135";
	public final static String TRN_CLEAR_INW_HSBC_DB_DEPOSIT = "136";
	public final static String TRN_CLEAR_INW_COMSERVER_POS = "137";
	public final static String TRN_CLEAR_OUT_HSBC_PLAZA_BONUS = "138";
	public final static String TRN_CLEAR_OUT_HSBC_QUIKCASH = "139";
	public final static String TRN_CLEAR_OUT_HSBC_MIMBOL = "140";
	public final static String TRN_CLEAR_INW_FDMS_TRANS = "141";
	public final static String TRN_CLEAR_OUT_HSBC_MERCHANT = "142";
	public final static String TRN_CLEAR_OUT_HSBC_MERCH_OTH = "143";
	public final static String TRN_CLEAR_OUT_HSBC_MERCH_DRAFT = "144";
	public final static String TRN_CLEAR_OUT_GSS_BNS_CHCK_ISS = "145";
	public final static String TRN_CLEAR_OUT_GFF_MERCH_PAY = "146";
	public final static String TRN_CLEAR_OUT_ICC_PAY_RQST_BNK = "147";
	public final static String TRN_CLEAR_INW_ICC_PAY_RESP_BNK = "148";
	public final static String TRN_CLEAR_OUT_BACS_EUR_CR = "149";
	public final static String TRN_CLEAR_OUT_BACS_EUR_DR = "150";
	public final static String TRN_CLEAR_OUT_WELLS_PAYMENT = "151";
	public final static String TRN_CLEAR_INW_WELLS_PAYM_RESP = "152";
	public final static String TRN_CLEAR_INW_MC_IPM = "153";
	public final static String TRN_CLEAR_OUT_MC_IPM = "154";
	public final static String TRN_CLEAR_INW_MC_IPM_DOM = "155";
	public final static String TRN_CLEAR_OUT_MC_IPM_DOM = "156";
	public final static String TRN_CLEAR_INW_BOV = "157";
	public final static String TRN_CLEAR_OUT_BOV = "158";
	public final static String TRN_CLEAR_INW_JCB_XCHNG = "159";
	public final static String TRN_CLEAR_OUT_JCB_XCHNG = "160";
	public final static String TRN_CLEAR_INW_FDMS_DCC_TRANS = "161";
	public final static String TRN_CLEAR_INW_IPM_ON_US = "162";
	public final static String TRN_CLEAR_OUT_IPM_ON_US = "163";
	public final static String TRN_CLEAR_OUT_BMS_LASER = "164";
	public final static String TRN_CLEAR_OUT_PAYM_PAYCE = "165";
	public final static String TRN_CLEAR_OUT_ONUS_ATMP = "166";
	public final static String TRN_CLEAR_INW_HSBC_ATMP_CCP = "167";
	public final static String TRN_CLEAR_INW_AEGN = "168";
	public final static String TRN_CLEAR_OUT_AEGN = "169";
	public final static String TRN_CLEAR_OUT_AMEXDC = "170";
	public final static String TRN_CLEAR_OUT_AMEX_FRAUD = "171";
	public final static String TRN_CLEAR_INW_AMEX_FRAUD = "172";
	public final static String TRN_CLEAR_OUT_VISA_SMS = "173";
	public final static String TRN_CLEAR_OUT_VISA_SMS_NNSS = "174";
	public final static String TRN_CLEAR_INW_TRANSMASTER = "176";
	public final static String TRN_CLEAR_OUT_TRANSMASTER = "177";
	public final static String TRN_CLEAR_INW_VISA_NMAS = "178";
	public final static String TRN_CLEAR_OUT_VISA_NMAS = "179";
	public final static String TRN_CLEAR_INW_VISA_CSB_PROP = "180";
	public final static String TRN_CLEAR_INW_DINACARD = "181";
	public final static String TRN_CLEAR_OUT_DINACARD = "182";
	public final static String TRN_CLEAR_OUT_BMSN_CAMS = "198";
	public final static String TRN_CLEAR_OUT_BACS_DR = "199";
	public final static String TRN_CLEAR_INW_HB_PAYMENT = "200";
	public final static String TRN_CLEAR_INW_GTP_DD = "201";
	public final static String TRN_CLEAR_INW_ACH_REJECT_RESP = "202";
	public final static String TRN_CLEAR_INW_BBS = "203";
	public final static String TRN_CLEAR_INW_GBC = "204";
	public final static String TRN_CLEAR_OUT_GBC = "205";
	public final static String TRN_CLEAR_OUT_BSF_VISA_ELECTRN = "206";
	public final static String TRN_CLEAR_INW_INSTALLMENT_ACQ = "207";
	public final static String TRN_CLEAR_INW_RECON = "208";
	public final static String TRN_CLEAR_INW_BBS_INC_PYMT = "209";
	public final static String TRN_CLEAR_OUT_BBS_PYMT = "210";
	public final static String TRN_CLEAR_INW_BBS_AG_PYMT_REQ = "211";
	public final static String TRN_CLEAR_OUT_BBS_DIRECT_REMIT = "212";
	public final static String TRN_CLEAR_INW_ATM_FILE_RB = "213";
	public final static String TRN_CLEAR_OUT_GFF2_MERCH_PAY = "214";
	public final static String TRN_CLEAR_INW_POS_FILE_RB = "215";
	public final static String TRN_CLEAR_INW_SAMA_DOM = "216";
	public final static String TRN_CLEAR_OUT_SAMA_DOM = "217";
	public final static String TRN_CLEAR_INW_SAMA_GCC = "218";
	public final static String TRN_CLEAR_OUT_SAMA_GCC = "219";
	public final static String TRN_CLEAR_INW_SHETAB = "221";
	public final static String TRN_CLEAR_OUT_SHETAB = "222";
	public final static String TRN_CLEAR_INW_SAMA_GCC_UAE = "223";
	public final static String TRN_CLEAR_OUT_HSBC_BONUS_POINT = "224";
	public final static String TRN_CLEAR_RECON_EXACT_MATCH = "490";
	public final static String TRN_CLEAR_RECON_NO_MTCHD_RCON = "491";
	public final static String TRN_CLEAR_RECON_NO_MTCHD_TRAN = "492";
	public final static String TRN_CLEAR_INEXACT_MATCH = "493";
	public final static String TRN_CLEAR_RECON_SAMA_POS12 = "501";
	public final static String TRN_CLEAR_RECON_SAMA_POS02 = "502";
	public final static String TRN_CLEAR_RECON_SAMA_STAT6 = "503";
	public final static String TRN_CLEAR_RECON_SAMA_STAT7 = "504";
	public final static String TRN_CLEAR_RECON_SAMA_GCCISS = "505";
	public final static String TRN_CLEAR_RECON_SAMA_GCCACQ = "506";
	public final static String TRN_CLEAR_RECON_MDS = "507";
	public final static String TRN_CLEAR_DOMESTIC_START = "800";
	public final static String TRN_CLEAR_DOMESTIC_END = "899";
	public final static String TRN_CLEAR_OUT_MC_IPM_DOM_CA = "800";
	public final static String TRN_CLEAR_INW_MC_IPM_DOM_CA = "801";
	public final static String TRN_CLEAR_INW_VISA_NNSS_CA = "802";
	public final static String TRN_CLEAR_OUT_VISA_NNSS_CA = "803";
	public final static String TRN_CLEAR_INW_VISA_NNSS_UK = "804";
	public final static String TRN_CLEAR_OUT_VISA_NNSS_UK = "805";
	public final static String TRN_CLEAR_INW_VISA_NNSS_DE = "806";
	public final static String TRN_CLEAR_OUT_VISA_NNSS_DE = "807";
	public final static String TRN_CLEAR_INW_ECCF_DOM_DE = "808";
	public final static String TRN_CLEAR_OUT_ECCF_DOM_DE = "809";
	public final static String TRN_CLEAR_INW_ECCF_DOM_UK = "810";
	public final static String TRN_CLEAR_OUT_ECCF_DOM_UK = "811";
	public final static String TRN_CLEAR_INW_MC_IPM_DOM_US = "812";
	public final static String TRN_CLEAR_OUT_MC_IPM_DOM_US = "813";
	public final static String TRN_CLEAR_INW_VISA_NNSS_US = "814";
	public final static String TRN_CLEAR_OUT_VISA_NNSS_US = "815";
	public final static String TRN_CLEAR_INW_ECCF_DOM_NL = "816";
	public final static String TRN_CLEAR_OUT_ECCF_DOM_NL = "817";
	public final static String TRN_CLEAR_INW_VISA_NNSS_CH = "818";
	public final static String TRN_CLEAR_OUT_VISA_NNSS_CH = "819";
	public final static String TRN_CLEAR_INW_ECCF_DOM_CH = "820";
	public final static String TRN_CLEAR_OUT_ECCF_DOM_CH = "821";
	public final static String TRN_CLEAR_INW_ECCF_DOM_NO = "822";
	public final static String TRN_CLEAR_OUT_ECCF_DOM_NO = "823";
	public final static String TRN_CLEAR_INW_VISA_NNSS_DK = "824";
	public final static String TRN_CLEAR_OUT_VISA_NNSS_DK = "825";
	public final static String TRN_CLEAR_INW_ECCF_DOM_DK = "826";
	public final static String TRN_CLEAR_OUT_ECCF_DOM_DK = "827";
	public final static String TRN_CLEAR_INW_ECCF_DOM_FI = "828";
	public final static String TRN_CLEAR_OUT_ECCF_DOM_FI = "829";
	public final static String TRN_CLEAR_INW_ECCF_DOM_ES = "830";
	public final static String TRN_CLEAR_OUT_ECCF_DOM_ES = "831";
	public final static String TRN_CLEAR_INW_ECCF_DOM_AT = "832";
	public final static String TRN_CLEAR_OUT_ECCF_DOM_AT = "833";
	public final static String TRN_CLEAR_INW_VISA_NNSS_ES = "834";
	public final static String TRN_CLEAR_OUT_VISA_NNSS_ES = "835";
	public final static String TRN_CLEAR_INW_VISA_NNSS_AT = "836";
	public final static String TRN_CLEAR_OUT_VISA_NNSS_AT = "837";
	public final static String TRN_CLEAR_OUT_MC_IPM_DOM_HK = "838";
	public final static String TRN_CLEAR_INW_MC_IPM_DOM_HK = "839";
	public final static String TRN_CLEAR_OUT_MC_IPM_DOM_AU = "840";
	public final static String TRN_CLEAR_INW_MC_IPM_DOM_AU = "841";
	public final static String TRN_CLEAR_INW_VISA_NNSS_AU = "842";
	public final static String TRN_CLEAR_OUT_VISA_NNSS_AU = "843";
	public final static String TRN_CLEAR_OUT_AU_CARDS_DR = "844";
	public final static String TRN_CLEAR_INW_VISA_NNSS_HK = "845";
	public final static String TRN_CLEAR_OUT_VISA_NNSS_HK = "846";
	public final static String TRN_CLEAR_OUT_AU_CARDS_CR = "847";
	public final static String TRN_CLEAR_OUT_AUTHORIZATION = "998";
	public final static String TRN_CLEAR_UNMATCHED = "999";
	public final static String TRN_CLEAR_MERCHANT_SOURCES = TRN_CLEAR_INW_CEKAB;
	public final static String SUN_TYPES_FIRST_CHARGE = "001";
	public final static String SUN_TYPES_SECOND_CHARGE = "002";
	public final static String SUN_TYPES_REPRESENTMENTS = "003";
	public final static String SUN_TYPES_FULFILLMENT = "004";
	public final static String SUN_TYPES_INVALID_RESPONSE = "005";
	public final static String SUN_TYPES_FEE_CHARGEBACK = "006";
	public final static String SUN_TYPES_FEE_COLLECTION = "007";
	public final static String SUN_TYPES_RET_REQUEST = "008";
	public final static String SUN_TYPES_REVERSAL = "009";
	public final static String SUN_TYPES_FULF_FEE = "010";
	public final static String SUN_TYPES_FUNDS_DISB = "011";
	public final static String SUN_TYPES_TRANSFER = "012";
	public final static String SUN_TYPES_REJECTS = "013";
	public final static String SUN_TYPES_RE_PRC_BATCH = "014";
	public final static String SUN_TYPES_FRAUD_ADD = "015";
	public final static String SUN_TYPES_FRAUD_CHANGE = "016";
	public final static String SUN_TYPES_FRAUD_DELETE = "017";
	public final static String SUN_TYPES_FRAUD_CONFIRM = "018";
	public final static String SUN_TYPES_FRAUD_DUPLICATE = "019";
	public final static String SUN_TYPES_FRAUD_PRESTATUS = "020";
	public final static String SUN_TYPES_FRAUD_REACTIVATE = "021";
	public final static String SUN_TYPES_FRAUD_ADD_DUPLICATE = "022";
	public final static String SUN_TYPES_FRAUD_WARNING = "023";
	public final static String SUN_TYPES_FRAUD_CHGBCK_RIGHT = "024";
	public final static String SUN_TYPES_FRAUD_AMOUNT_LT_1USD = "025";
	public final static String SUN_TYPES_FEE_RESUBMISSION = "026";
	public final static String SUN_TYPES_FEE_SECOND_CHARGE = "027";
	public final static String SUN_TYPES_SOURCE_TRANSFER = "030";
	public final static String SUN_TYPES_AMEND_CLIENT_NO = "031";
	public final static String SUN_TYPES_AMEND_CARD_NO = "032";
	public final static String SUN_TYPES_MERCHANT_RETURN = "033";
	public final static String SUN_TYPES_REVERSE_FILE = "034";
	public final static String SUN_TYPES_NEW_PRESENTMENT = "035";
	public final static String SUN_TYPES_MC_MATCH_ADD = "036";
	public final static String SUN_TYPES_MC_MATCH_INQUIRY = "037";
	public final static String SUN_TYPES_BALANCE_TRANSFER = "038";
	public final static String SUN_TYPES_PAYMENT_HISTORY_ADJ = "039";
	public final static String SUN_TYPES_MERCH_CHGBK_TRANSFER = "040";
	public final static String SUN_TYPES_AMEND_TRAN_DATE = "041";
	public final static String SUN_TYPES_AMEND_AUTH_CODE = "042";
	public final static String SUN_TYPES_PAYMENT_REJECTS = "043";
	public final static String SUN_TYPES_PAYMENT_TRAN_ADJ = "044";
	public final static String SUN_TYPES_UNDO_TRANSFER = "045";
	public final static String SUN_TYPES_SPLIT_PRESENTMENT = "046";
	public final static String SUN_TYPES_FRAUD_RPT_ACQUIRER = "047";
	public final static String SUN_TYPES_FRAUD_RPT_ISSUER = "048";
	public final static String SUN_TYPES_FRAUD_RPT_CHGBK = "049";
	public final static String SUN_TYPES_GTP_PAYMENT_REJECT = "050";
	public final static String SUN_TYPES_CANCELLATION = "051";
	public final static String SUN_TYPES_INTR_FREE_CREDIT = "200";
	public final static String SUN_TYPES_CASH_ON_DELIVERY = "201";
	public final static String SUN_DOC_HARDCOPY = "010";
	public final static String SUN_DOC_MICORFILM = "011";
	public final static String SUN_DOC_FAX = "012";
	public final static String SUN_DOC_ORIG_PAPER = "013";
	public final static String SUN_DOC_RECEIPT_COPY = "014";
	public final static String SUN_DOC_NOT_APPLIC = "999";
	public final static String REF_OBJ_SERVICE_NEW = "002";
	public final static String REF_OBJ_SERVICE_EMERGENCY = "003";
	public final static String REF_OBJ_SERVICE_RENEWAL = "004";
	public final static String REF_OBJ_SERVICE_REPLACE = "005";
	public final static String REF_OBJ_PAYMENT_STATUS = "006";
	public final static String REF_OBJ_SERVICE_ACTIVE = "007";
	public final static String REF_OBJ_SERVICE_PIN_REISSUE = "008";
	public final static String REF_OBJ_SERVICE_REISSUE = "009";
	public final static String REF_OBJ_CARD_DELIVERY_EXPRESS = "010";
	public final static String REF_OBJ_FIRST_SERVICE = "011";
	public final static String REF_OBJ_ADDITIONAL_SERVICES = "012";
	public final static String REF_OBJ_CLOSING_BALANCE = "101";
	public final static String REF_OBJ_BATCH_PER_VALUE_DATE = "102";
	public final static String REF_OBJ_CLIENT_PER_VALUE_DATE = "103";
	public final static String REF_OBJ_FILE_PER_VALUE_DATE = "104";
	public final static String REF_OBJ_TRAN_PER_VALUE_DATE = "105";
	public final static String REF_OBJ_INC_AMT_ARREARS = "106";
	public final static String REF_OBJ_TOT_AMT_ARREARS = "107";
	public final static String REF_OBJ_CLOSING_BAL_TOTAL_DUE = "108";
	public final static String REF_OBJ_CHGBCK_VALUE = "109";
	public final static String REF_OBJ_FEE_COLLECT_VALUE = "110";
	public final static String REF_OBJ_PRESENTMENT_VALUE = "111";
	public final static String REF_OBJ_INT_CHRGS_VALUE = "112";
	public final static String REF_OBJ_PRINCIPAL_AMOUNT = "112";
	public final static String REF_OBJ_SOURCE_SUMM_VALUE = "113";
	public final static String REF_OBJ_PAYMENT_VALUE = "114";
	public final static String REF_OBJ_START_UP_CLOSING_BAL = "115";
	public final static String REF_OBJ_FINAL_BALANCE = "116";
	public final static String REF_OBJ_PAYMENT_DUE_DAYS = "117";
	public final static String REF_OBJ_ACCT_LIMIT_SETTL = "118";
	public final static String REF_OBJ_SUB_BAL_CHARGES = "119";
	public final static String REF_OBJ_ALL_TRANSACTIONS = "120";
	public final static String REF_OBJ_SUB_BAL_CR_PAYMENTS = "121";
	public final static String REF_OBJ_REJECT_PAYMENT = "122";
	public final static String REF_OBJ_MERCH_COMMISION_TRANS = "123";
	public final static String REF_OBJ_EXTENDED_CREDIT_FEE = "124";
	public final static String REF_OBJ_PAYMENT_REJECT_COUNT = "125";
	public final static String REF_OBJ_SRCE_SUMM_VALUE_CLIENT = "126";
	public final static String REF_OBJ_SRCE_SUMM_VALUE_FILE = "127";
	public final static String REF_OBJ_SUB_BAL_CR_BONUS = "128";
	public final static String REF_OBJ_AGEING_NUMBER_DAYS = "129";
	public final static String REF_OBJ_OVERDUE_DAYS = "130";
	public final static String REF_OBJ_PREV_AMT_ARREARS = "131";
	public final static String REF_OBJ_OVER_30_CLOSING_BAL = "132";
	public final static String REF_OBJ_OVER_60_CLOSING_BAL = "133";
	public final static String REF_OBJ_OVER_90_CLOSING_BAL = "134";
	public final static String REF_OBJ_OVER_120_CLOSING_BAL = "135";
	public final static String REF_OBJ_OVER_150_CLOSING_BAL = "136";
	public final static String REF_OBJ_CYCLE_BALANCE = "137";
	public final static String REF_OBJ_ACCOUNT_ACTIVITY = "138";
	public final static String REF_OBJ_HBF_INVOICE_FEE = "139";
	public final static String REF_OBJ_ALWAYS = "140";
	public final static String REF_OBJ_REMINDER_LETTER = "141";
	public final static String REF_OBJ_PAYMENT_LAB1_PERCENT = "142";
	public final static String REF_OBJ_BONUS_RULE = "150";
	public final static String REF_OBJ_TURNOVER_BALANCE = "151";
	public final static String REF_OBJ_EXPIRED_BONUS_CHECKS = "152";
	public final static String REF_OBJ_CLOSED_MEMBER_BALANCE = "153";
	public final static String REF_OBJ_CLOSING_BAL_IN_EXCESS = "154";
	public final static String REF_OBJ_CLOSING_BALANCE_NO_OL = "155";
	public final static String REF_OBJ_ACCT_LIMIT_NO_OL = "156";
	public final static String REF_OBJ_CLOSE_BAL_CASH_NO_OL = "157";
	public final static String REF_OBJ_ACCT_LIMIT_CASH_NO_OL = "158";
	public final static String REF_OBJ_BEGIN_BAL_LESS_PAYM = "159";
	public final static String REF_OBJ_CLOSING_BAL_INTRA = "160";
	public final static String REF_OBJ_REPLENISH_FROM_DEBIT = "161";
	public final static String REF_OBJ_REPLENISH_FROM_SEC_DEP = "162";
	public final static String REF_OBJ_REPL_FROM_DEBIT_INDIV = "163";
	public final static String REF_OBJ_OVERLIMIT_BALANCE = "164";
	public final static String REF_OBJ_OVER_RBS_BALANCE = "165";
	public final static String REF_OBJ_VALUE_BALANCE = "166";
	public final static String REF_OBJ_HBF_GRP_PAYM_DISTR = "169";
	public final static String REF_OBJ_LAB1_GROUP = "170";
	public final static String REF_OBJ_LAB2_GROUP = "171";
	public final static String REF_OBJ_LAB3_GROUP = "172";
	public final static String REF_OBJ_LAB1_BASE = "173";
	public final static String REF_OBJ_LAB2_BASE = "174";
	public final static String REF_OBJ_LAB1_CAMPAIGN = "175";
	public final static String REF_OBJ_LAB2_CAMPAIGN = "176";
	public final static String REF_OBJ_CLOSED_CAMPAIGN_BAL = "177";
	public final static String REF_OBJ_RECON_TRAN_BATCH = "180";
	public final static String REF_OBJ_ACCOUNT_NEW = "200";
	public final static String REF_OBJ_ACCOUNT_ANNIVERSARY = "201";
	public final static String REF_OBJ_OVERLIMIT_AMOUNT = "202";
	public final static String REF_OBJ_CLIENT_NEW = "203";
	public final static String REF_OBJ_CLIENT_ANNIVERSARY = "204";
	public final static String REF_OBJ_ACCOUNT_ANNUAL_FEE = "205";
	public final static String REF_OBJ_OVERLIMIT_PERCENT = "206";
	public final static String REF_OBJ_COMB_OVERLIMIT_AMOUNT = "207";
	public final static String REF_OBJ_EACH_POS_DEVICE = "210";
	public final static String REF_OBJ_NUMBER_OF_POS_DEVICE = "211";
	public final static String REF_OBJ_ACCT_LIMIT = "220";
	public final static String REF_OBJ_ACCOUNT = "221";
	public final static String REF_OBJ_ATM_BALANCE_ENQUIRY = "222";
	public final static String REF_OBJ_HBF_MERCH_TRAN_VOLUME = "223";
	public final static String REF_OBJ_HBF_MERCH_PORTFOLIO = "224";
	public final static String REF_OBJ_EXPIRED_BONUS_POINTS = "225";
	public final static String REF_OBJ_INSTALLMENT_LOANS = "230";
	public final static String REF_OBJ_PERIODIC_REVENUE = "231";
	public final static String REF_OBJ_ACCOUNT_TURNOVER = "250";
	public final static String REF_OBJ_COMMISSION_CYCLE = "251";
	public final static String REF_OBJ_DCC_TRANSACTIONS = "260";
	public final static String REF_OBJ_INTERCHANGE_FEE = "270";
	public final static String REF_OBJ_CRD_SC_VISA_DOM_CPT = "272";
	public final static String REF_OBJ_CRD_SC_VISA_DOM_PER = "273";
	public final static String REF_OBJ_CRD_SC_ECMC_DOM_CPT = "274";
	public final static String REF_OBJ_CRD_SC_ECMC_DOM_PER = "275";
	public final static String REF_OBJ_CRD_SC_VISA_INTRA_CPT = "276";
	public final static String REF_OBJ_CRD_SC_VISA_INTRA_PER = "277";
	public final static String REF_OBJ_CRD_SC_ECMC_INTRA_CPT = "278";
	public final static String REF_OBJ_CRD_SC_ECMC_INTRA_PER = "279";
	public final static String REF_OBJ_CRD_SC_VISA_INTER_CPT = "280";
	public final static String REF_OBJ_CRD_SC_VISA_INTER_PER = "281";
	public final static String REF_OBJ_CRD_SC_ECMC_INTER_CPT = "282";
	public final static String REF_OBJ_CRD_SC_ECMC_INTER_PER = "283";
	public final static String REF_OBJ_MERCH_VOLUME_FEE_DR = "284";
	public final static String REF_OBJ_MERCH_VOLUME_FEE_CR = "285";
	public final static String REF_OBJ_MERCH_VISA_SALES = "286";
	public final static String REF_OBJ_MERCH_VISA_RETURNS = "287";
	public final static String REF_OBJ_MERCH_ECMC_SALES = "288";
	public final static String REF_OBJ_MERCH_ECMC_RETURNS = "289";
	public final static String REF_OBJ_STATEMENT_GENERATION = "300";
	public final static String REF_OBJ_STMNT_MAIN_PRINT_FILE = "301";
	public final static String REF_OBJ_STMNT_COPY_PRINT_FILE = "302";
	public final static String REF_OBJ_STATEMENT_EMAIL = "303";
	public final static String REF_OBJ_STATEMENT_FEE_POST = "304";
	public final static String REF_OBJ_STATEMENT_FEE_NON_BANK = "305";
	public final static String REF_OBJ_STATEMENT_FEE_BALTIC = "306";
	public final static String REF_OBJ_STATEMENT_FEE_FOREIGN = "307";
	public final static String REF_OBJ_STATEMENT_FEE_BANK = "308";
	public final static String REF_OBJ_TIER_NO_TRAN_YEAR = "400";
	public final static String REF_OBJ_TIER_VAL_LOC_TRAN_YEAR = "401";
	public final static String REF_OBJ_INSURANCE_FEE_CYCL_TOT = "440";
	public final static String REF_OBJ_SP_VAL_TRN_YR_ALL = "434";
	public final static String REF_OBJ_SP_VAL_TRN_YR_DCC = "435";
	public final static String REF_OBJ_SP_VAL_TRN_YR_NON_DCC = "436";
	public final static String REF_OBJ_SP_NUM_TRN_CON_ALL = "437";
	public final static String REF_OBJ_SP_NUM_TRN_CON_DCC = "438";
	public final static String REF_OBJ_SP_NUM_TRN_CON_NON_DCC = "439";
	public final static String REF_OBJ_SP_VAL_TRN_CON_ALL = "441";
	public final static String REF_OBJ_SP_VAL_TRN_CON_DCC = "442";
	public final static String REF_OBJ_SP_VAL_TRN_CON_NON_DCC = "443";
	public final static String REF_OBJ_SP_NUM_TRN_MTH_ALL = "444";
	public final static String REF_OBJ_SP_NUM_TRN_MTH_DCC = "445";
	public final static String REF_OBJ_SP_NUM_TRN_MTH_NON_DCC = "446";
	public final static String REF_OBJ_SP_VAL_TRN_MTH_ALL = "447";
	public final static String REF_OBJ_SP_VAL_TRN_MTH_DCC = "448";
	public final static String REF_OBJ_SP_VAL_TRN_MTH_NON_DCC = "449";
	public final static String REF_OBJ_SP_NEW_MERCHANTS = "450";
	public final static String REF_OBJ_SP_NUM_TRAN_YEAR_ALL = "451";
	public final static String REF_OBJ_SP_NUM_ELECT_APPR = "452";
	public final static String REF_OBJ_SP_NUM_ELECT_DECL = "453";
	public final static String REF_OBJ_SP_NUM_ELECT_REFR = "454";
	public final static String REF_OBJ_SP_NUM_ELECT_CANC = "455";
	public final static String REF_OBJ_SP_NUM_RETRIEVALS = "456";
	public final static String REF_OBJ_SP_NUM_CHARGEBACKS = "457";
	public final static String REF_OBJ_SP_NUM_VOICE_APPR = "458";
	public final static String REF_OBJ_SP_NUM_VOICE_DECL = "459";
	public final static String REF_OBJ_SP_NUM_VOICE_REFR = "460";
	public final static String REF_OBJ_SP_NUM_ARU_APPR = "461";
	public final static String REF_OBJ_SP_NUM_ARU_DECL = "462";
	public final static String REF_OBJ_SP_NUM_ARU_REFR = "463";
	public final static String REF_OBJ_MERCHANT_AUTH = "464";
	public final static String REF_OBJ_NUM_CHARGEBACKS = "465";
	public final static String REF_OBJ_SP_ADDITIONAL_ACCT = "466";
	public final static String REF_OBJ_SP_NUM_TRAN_CURR_CONV = "467";
	public final static String REF_OBJ_SP_NUM_BATCHES = "468";
	public final static String REF_OBJ_NUM_ELECT_APPR = "469";
	public final static String REF_OBJ_NUM_ELECT_DECL = "470";
	public final static String REF_OBJ_NUM_ELECT_REFR = "471";
	public final static String REF_OBJ_NUM_ELECT_CANC = "472";
	public final static String REF_OBJ_NUM_VOICE_APPR = "473";
	public final static String REF_OBJ_NUM_VOICE_DECL = "474";
	public final static String REF_OBJ_NUM_VOICE_REFR = "475";
	public final static String REF_OBJ_NUM_ARU_APPR = "476";
	public final static String REF_OBJ_NUM_ARU_DECL = "477";
	public final static String REF_OBJ_NUM_ARU_REFR = "478";
	public final static String REF_OBJ_NUM_CHARGEBACKS_REV = "479";
	public final static String REF_OBJ_NUM_BATCHES = "480";
	public final static String REF_OBJ_MERCH_PROC_FEE_CYCLE = "481";
	public final static String REF_OBJ_NUM_AVS = "482";
	public final static String REF_OBJ_SP_NUM_AVS_DCC = "483";
	public final static String REF_OBJ_SP_NUM_AVS_NON_DCC = "484";
	public final static String REF_OBJ_SP_NUM_AVS = "485";
	public final static String REF_OBJ_SP_TRN_YEAR_DCC = "486";
	public final static String REF_OBJ_SP_TRN_YEAR_NON_DCC = "487";
	public final static String REF_OBJ_SP_NUM_RETR_DCC = "488";
	public final static String REF_OBJ_SP_NUM_RETR_NON_DCC = "489";
	public final static String REF_OBJ_SP_NUM_CHRBCKS_DCC = "490";
	public final static String REF_OBJ_SP_NUM_CHRBCKS_NON_DCC = "491";
	public final static String REF_OBJ_SP_NUM_EL_APP_DCC = "492";
	public final static String REF_OBJ_SP_NUM_EL_APP_NON_DCC = "493";
	public final static String REF_OBJ_SP_NUM_EL_DEC_DCC = "494";
	public final static String REF_OBJ_SP_NUM_EL_DEC_NON_DCC = "495";
	public final static String REF_OBJ_SP_NUM_EL_REF_DCC = "496";
	public final static String REF_OBJ_SP_NUM_EL_REF_NON_DCC = "497";
	public final static String REF_OBJ_SP_NUM_EL_CANC_DCC = "498";
	public final static String REF_OBJ_SP_RESIDENCY = "499";
	public final static String REF_OBJ_SP_NUM_EL_CANC_NON_DCC = "500";
	public final static String REF_OBJ_SP_NUM_VCE_APP_DCC = "501";
	public final static String REF_OBJ_SP_NUM_VCE_APP_NON_DCC = "502";
	public final static String REF_OBJ_SP_NUM_VCE_DEC_DCC = "503";
	public final static String REF_OBJ_SP_NUM_VCE_DEC_NON_DCC = "504";
	public final static String REF_OBJ_SP_NUM_VCE_REF_DCC = "505";
	public final static String REF_OBJ_SP_NUM_VCE_REF_NON_DCC = "506";
	public final static String REF_OBJ_SP_NUM_ARU_APP_DCC = "507";
	public final static String REF_OBJ_SP_NUM_ARU_APP_NON_DCC = "508";
	public final static String REF_OBJ_SP_NUM_ARU_DEC_DCC = "509";
	public final static String REF_OBJ_SP_NUM_ARU_DEC_NON_DCC = "510";
	public final static String REF_OBJ_SP_NUM_ARU_REF_DCC = "511";
	public final static String REF_OBJ_SP_NUM_ARU_REF_NON_DCC = "512";
	public final static String REF_OBJ_SP_ACTIVE_MERCHANTS = "513";
	public final static String REF_OBJ_NUM_RETRIEVALS = "514";
	public final static String REF_OBJ_INTERNET_START = "700";
	public final static String REF_OBJ_INTERENT_END = "799";
	public final static String REF_PER_POSTING_DATE = "001";
	public final static String REF_PER_END_OF_CYCLE = "002";
	public final static String REF_PER_START_OF_CYCLE = "003";
	public final static String REF_PER_END_PREV_CYCLE = "004";
	public final static String REF_PER_TRANSACTION_DATE = "005";
	public final static String REF_PER_DAY_OF_MONTH = "006";
	public final static String REF_PER_DAY_OF_WEEK = "007";
	public final static String REF_PER_HALF_MONTHLY = "008";
	public final static String REF_PER_END_OF_MONTH = "011";
	public final static String REF_PER_END_OF_NEXT_CYCLE = "012";
	public final static String REF_PER_END_OF_NEXT_MONTH = "013";
	public final static String REF_PER_POSTING_DATE_BUSDAYS = "014";
	public final static String REF_PER_END_NEXT_CYCLE_BUSDAYS = "015";
	public final static String REF_PER_SETTLE_CALENDAR = "030";
	public final static String REF_PER_AGEING_END_OF_CYCLE = "100";
	public final static String REF_PER_AGEING_PAYM_DUE_DATE = "101";
	public final static String OBJ_LEVEL_NA = "000";
	public final static String OBJ_LEVEL_MAIN_ACCOUNT = "001";
	public final static String OBJ_LEVEL_SUB_ACCOUNT = "002";
	public final static String OBJ_LEVEL_BILLING_ACCOUNT = "003";
	public final static String OBJ_LEVEL_FIRST_SERVICE = "101";
	public final static String OBJ_LEVEL_SUPPL_SERVICE = "102";
	public final static String OBJ_LEVEL_GROUP_CLT = "201";
	public final static String OBJ_LEVEL_SUBGROUP_CLT = "202";
	public final static String OBJ_LEVEL_MEMBER_CLT = "203";
	public final static String OBJ_LEVEL_ALL = "999";
	public final static String SETT_CATG_PAYABLE = "001";
	public final static String SETT_CATG_RECEIVABLE = "002";
	public final static String SETTLM_CAT_PAYABLE = "001";
	public final static String SETTLM_CAT_RECIEVABLE = "002";
	public final static String SETTLM_CAT_INTRA_ACCT = "003";
	public final static String SETTLM_CAT_REQUEST = "004";
	public final static String SETTLM_CAT_TRANSFER = "005";
	public final static String SETTLM_CAT_INSTALLMENTS = "006";
	public final static String SETTLM_CAT_INTRA_ACCT_STRT = "300";
	public final static String SETTLM_CAT_INTRA_ACCT_END = "399";
	public final static String FEE_CAT_NA = "000";
	public final static String FEE_CAT_ACCOUNT = "001";
	public final static String FEE_CAT_SERVICE = "002";
	public final static String FEE_CAT_TRANSACTION = "003";
	public final static String FEE_CAT_INTERCHANGE_PLUS = "004";
	public final static String FEE_CAT_INTERCHANGE_PASSTRU = "005";
	public final static String FEE_CAT_SOURCE_FEE_TO_DEST = "006";
	public final static String FEE_CAT_DCC_INTERCHANGE_PLUS = "007";
	public final static String FEE_CAT_PRICING_PLUS_FEES = "008";
	public final static String FEE_CAT_LOAN = "009";
	public final static String FEE_CAT_SECURITY_TYPE = "010";
	public final static String FEE_CAT_SERVICE_FEE_TARIFF = "011";
	public final static String FEE_CAT_NOTIONAL_INT_PASSTRU = "012";
	public final static String FEE_CAT_SERVICE_PROVIDER = "020";
	public final static String FEE_CAT_INTERNET_ACTION = "101";
	public final static String FEE_CAT_MULTI_CHRG_START = "200";
	public final static String FEE_CAT_MULTI_CHRG_END = "299";
	public final static String FEE_CAT_TRANSACTION_PRECALC = "300";
	public final static String AREA_FOREIGN = "001";
	public final static String AREA_DOMESTIC = "002";
	public final static String AREA_ONUS = "003";
	public final static String AREA_INTRABANK = "004";
	public final static String AREA_DOMESTIC_START = "100";
	public final static String AREA_DOMESTIC_END = "199";
	public final static String AREA_DOM_BILATERAL_START = "100";
	public final static String AREA_DOM_BILATERAL_END = "199";
	public final static String AREA_DOMESTIC_MINB_STB = "190";
	public final static String AREA_FOREIGN_START = "200";
	public final static String AREA_FOREIGN_EMEA = "201";
	public final static String AREA_FOREIGN_CEMEA = "205";
	public final static String AREA_FOREIGN_REST = "202";
	public final static String AREA_FOREIGN_EURO = "203";
	public final static String AREA_FOREIGN_MASTER = "204";
	public final static String AREA_FOREIGN_EU = "210";
	public final static String AREA_FOREIGN_US = "211";
	public final static String AREA_FOREIGN_AP = "206";
	public final static String AREA_FOREIGN_END = "299";
	public final static String AREA_BW3_EXTERNAL = "300";
	public final static String AREA_ONUS_EXTERNAL_START = "400";
	public final static String AREA_ONUS_EXTERNAL_END = "499";
	public final static String AREA_DOMESTIC_COUNTRY_START = "700";
	public final static String AREA_DOMESTIC_COUNTRY_END = "799";
	public final static String TARIFF_NA = "000";
	public final static String TARIFF_GENERAL = "908";
	public final static String TARIFF_GENERAL_LT_4 = "909";
	public final static String TARIFF_AUTHORIZED_LT_4 = "910";
	public final static String TARIFF_AUTHORIZED_LT_30 = "913";
	public final static String TARIFF_SECURED_LT_4 = "915";
	public final static String TARIFF_SECURED = "927";
	public final static String TARIFF_ELECTRONIC_LT_4 = "923";
	public final static String TARIFF_ELECTRONIC = "928";
	public final static String TARIFF_GENERAL_LT_5 = "905";
	public final static String TARIFF_AUTHORIZED_LT_5 = "911";
	public final static String TARIFF_GENERAL_LT_8 = "904";
	public final static String TARIFF_GENERAL_LT_20 = "903";
	public final static String TARIFF_PETROL = "906";
	public final static String TARIFF_AIRLINE = "907";
	public final static String TARIFF_AIRLINE_LT_5 = "902";
	public final static String TARIFF_AIRLINE_LT_8 = "901";
	public final static String TARIFF_AIRLINE_LT_20 = "900";
	public final static String TARIFF_GROCERY = "922";
	public final static String TARIFF_LIQOUR = "921";
	public final static String TARIFF_RATEII_5_NO_FUEL = "924";
	public final static String TARIFF_SECURED_LT_2 = "926";
	public final static String TARIFF_LARGE_30_NO_TE = "925";
	public final static String TARIFF_AUTHORIZED_LT_2 = "970";
	public final static String TARIFF_AUTHORIZED = "971";
	public final static String TARIFF_SET_MERCHANT = "978";
	public final static String TARIFF_SEC_LT_4_AIRLINE = "817";
	public final static String TARIFF_SEC_LT_4_PETROL = "966";
	public final static String TARIFF_SEC_LT_4_TRAVEL = "969";
	public final static String TARIFF_SEC_LT_4_SUPERMKT = "981";
	public final static String TARIFF_ELEC_LT_4_AIRLINE = "816";
	public final static String TARIFF_ELEC_LT_4_PETROL = "965";
	public final static String TARIFF_ELEC_LT_4_TRAVEL = "968";
	public final static String TARIFF_ELEC_LT_4_SUPERMKT = "980";
	public final static String TARIFF_GENERAL_PETROL = "964";
	public final static String TARIFF_GENERAL_TRAVEL = "967";
	public final static String TARIFF_GENERAL_SUPERMKT = "979";
	public final static String TARIFF_LARGE_TICKET = "930";
	public final static String TARIFF_DATA_RATE_II = "931";
	public final static String TARIFF_SECURED_LT_4_I = "932";
	public final static String TARIFF_ELECTRONIC_LT_4_I = "933";
	public final static String TARIFF_GENERAL_I = "934";
	public final static String TARIFF_LATE_GT_46 = "935";
	public final static String TARIFF_SECURED_LT_5 = "936";
	public final static String TARIFF_AIRLINE_LT_10 = "937";
	public final static String TARIFF_UK_INTERNET_LT_3 = "938";
	public final static String TARIFF_UK_CNP_LT_2 = "939";
	public final static String TARIFF_COMMERCIAL_VAT = "940";
	public final static String TARIFF_COMMERCIAL_SUM = "941";
	public final static String TARIFF_COMMERCIAL_LID = "942";
	public final static String TARIFF_UK_CAT_LT_2 = "944";
	public final static String TARIFF_UK_EHC_LT_2 = "945";
	public final static String TARIFF_SECURED_LT_3 = "946";
	public final static String TARIFF_UK_CNP_LT_3 = "947";
	public final static String TARIFF_UK_INTERNET = "949";
	public final static String TARIFF_CPS_HOTEL_AUTO_CP = "950";
	public final static String TARIFF_CPS_HOTEL_AUTO_CNP = "951";
	public final static String TARIFF_US_STANDARD = "952";
	public final static String TARIFF_US_EIRF = "953";
	public final static String TARIFF_CPS_RETAIL_CR = "972";
	public final static String TARIFF_CPS_ELECTRONIC = "992";
	public final static String TARIFF_MERCHANT_UCAF = "960";
	public final static String TARIFF_FULL_UCAF = "961";
	public final static String TARIFF_MERCHANT_UCAF_I = "962";
	public final static String TARIFF_FULL_UCAF_I = "963";
	public final static String TARIFF_MC_ELECTRONIC_CRD = "948";
	public final static String TARIFF_AUTHORIZED_LT_3 = "912";
	public final static String TARIFF_PETROL_5541 = "994";
	public final static String TARIFF_PETROL_5542 = "995";
	public final static String TARIFF_AUTO_PARKING = "996";
	public final static String TARIFF_ELECTRONIC_LT_2 = "998";
	public final static String TARIFF_NON_AIRLINE = "997";
	public final static String TARIFF_CPS_KEY_ENTRY = "801";
	public final static String TARIFF_CPS_SUPERMKT_CR = "802";
	public final static String TARIFF_CPS_RETAIL_2 = "803";
	public final static String TARIFF_CPS_RETAIL_CHCK = "804";
	public final static String TARIFF_CPS_SUPERMKT_CHCK = "805";
	public final static String TARIFF_NON_AIR_TELEMKTG = "806";
	public final static String TARIFF_CPS_CRD_NOT_PRES = "807";
	public final static String TARIFF_CPS_ECOM_BASIC = "808";
	public final static String TARIFF_CPS_ECOM_PREF = "809";
	public final static String TARIFF_CPS_AIRLINE = "810";
	public final static String TARIFF_CPS_ACCT_FUND = "811";
	public final static String TARIFF_CPS_FUEL = "812";
	public final static String TARIFF_SEC_LT_3_EPS = "813";
	public final static String TARIFF_GSA_LARGE_TICKET = "814";
	public final static String TARIFF_US_LARGE_TICKET = "815";
	public final static String TARIFF_CPS_HOTEL_AUTO_ECP = "818";
	public final static String TARIFF_CPS_AIRLINE_ECP = "819";
	public final static String TARIFF_CAR_RENTAL = "820";
	public final static String TARIFF_HOTEL = "821";
	public final static String TARIFF_RESTAURANT = "822";
	public final static String TARIFF_SUPERMKT_FOODST = "823";
	public final static String TARIFF_SPAIN_MCC_2_75 = "824";
	public final static String TARIFF_SPAIN_MCC_2_55 = "825";
	public final static String TARIFF_SPAIN_MCC_2_13 = "827";
	public final static String TARIFF_TELECOM_SERVICE = "828";
	public final static String TARIFF_SPAIN_MCC_1_91 = "829";
	public final static String TARIFF_SPAIN_MCC_1_70 = "830";
	public final static String TARIFF_SPAIN_MCC_1_30 = "831";
	public final static String TARIFF_SPAIN_MCC_0_85 = "832";
	public final static String TARIFF_US_AIRLINE = "833";
	public final static String TARIFF_CPS_SMALL_TICKET = "834";
	public final static String TARIFF_GSA_LARGE_TICKET_1 = "835";
	public final static String TARIFF_CPS_RESTAURANT = "836";
	public final static String TARIFF_CPS_SERVICE_ST = "837";
	public final static String TARIFF_AUTH_LT_4_CVV2 = "838";
	public final static String TARIFF_CPS_LVL2_DATA = "839";
	public final static String TARIFF_CPS_LVL3_DATA = "840";
	public final static String TARIFF_AUTH_LT_2_CVV2 = "841";
	public final static String TARIFF_AUTH_LT_4_CHIP_I = "842";
	public final static String TARIFF_AUTH_CHIP_TERM = "843";
	public final static String TARIFF_AUTH_LT_4_CHIP = "844";
	public final static String TARIFF_AUTH_LT_2_CHIP = "845";
	public final static String TARIFF_AUTHORIZED_LT_4_I = "974";
	public final static String TARIFF_AUTH_LT_4_ICHIP = "975";
	public final static String TARIFF_AUTH_LT_4_ACHIP = "973";
	public final static String TARIFF_SEC_LT_2_CHIP = "846";
	public final static String TARIFF_CPS_REWARDS_1 = "847";
	public final static String TARIFF_CPS_REWARDS_2 = "848";
	public final static String TARIFF_AUTH_LT_3_ACHIP = "849";
	public final static String TARIFF_AUTH_LT_3_ICHIP = "850";
	public final static String TARIFF_TR_SUPERCARD = "851";
	public final static String TARIFF_ALL = "999";
	public final static String STA_SRC_CLIENT = "001";
	public final static String STA_SRC_CONTRACT = "002";
	public final static String STA_SRC_ACCOUNT = "003";
	public final static String STA_SRC_LIMIT = "004";
	public final static String STA_SRC_AGING = "005";
	public final static String STA_SRC_SERVICE = "006";
	public final static String STA_CARD_INACTIVE = "000";
	public final static String STA_CARD_ACTIVE = "001";
	public final static String STA_CARD_PU_LOST = "002";
	public final static String STA_CARD_PU_STOLEN = "003";
	public final static String STA_CARD_BLOCKED = "004";
	public final static String STA_CARD_ACTIVE_DEBLOCK = "005";
	public final static String STA_CARD_BLOCKED_NEG_BAL = "006";
	public final static String STA_CARD_BLOC_BANK_ORDR = "007";
	public final static String STA_CARD_BLOC_DUPL_PAN = "008";
	public final static String STA_CARD_TEMP_SUSPENDED = "009";
	public final static String STA_CARD_CLOSED = "010";
	public final static String STA_CARD_NOT_ACTIVE = "011";
	public final static String STA_CARD_GIFT_NOT_LOADED = "012";
	public final static String STA_CARD_CLOSED_SEND = "017";
	public final static String STA_CARD_CLOSED_REFRESH = "018";
	public final static String STA_CARD_HOT = "020";
	public final static String STA_CARD_WARM = "021";
	public final static String STA_SERVICE_INACTIVE = "000";
	public final static String STA_SERVICE_ACTIVE = "001";
	public final static String STA_SERVICE_CLOSED = "002";
	public final static String STA_CARD_FOR_PRODUCTION = "001";
	public final static String STA_CARD_IN_PRODUCTION = "002";
	public final static String STA_CARD_PRODUCED = "003";
	public final static String STA_CARD_FEE_GENERATED = "004";
	public final static String STA_CARD_PROD_COMPLETED = "005";
	public final static String STA_CARD_FEES_ERROR = "006";
	public final static String STA_CARD_DECLINED = "007";
	public final static String STA_CARD_PRODUCTION_ERROR = "008";
	public final static String STA_CARD_PRE_PRODUCTION = "010";
	public final static String STA_CARD_ONLINE = "011";
	public final static String STA_APPL_CAPTURED = "001";
	public final static String STA_APPL_APPROVED = "002";
	public final static String STA_APPL_DECLINED = "003";
	public final static String STA_APPL_PROCESSED = "004";
	public final static String STA_APPL_CANCELLED = "005";
	public final static String STA_APPL_ENTERED = "006";
	public final static String STA_APPL_FOR_AMEND = "007";
	public final static String STA_APPL_ERROR = "008";
	public final static String STA_APPL_RESTRICTED_ID = "101";
	public final static String STA_APPL_RESTRICTED_PC = "102";
	public final static String STA_APPL_RESTR_ID_CLOSED = "103";
	public final static String STA_APPL_RESTR_PC_CLOSED = "104";
	public final static String STA_APPL_FOR_PREPROCESS = "120";
	public final static String STA_APPL_MODIFY_PREPRO = "121";
	public final static String STA_APPL_APPROVED_PREPROC = "122";
	public final static String STA_APPL_PRE_PROCESSED = "123";
	public final static String STA_APPL_VERIFIED_ENTERED = "125";
	public final static String STA_APPL_AWAIT_SCORE_ENTERED = "126";
	public final static String STA_APPL_PENDING_SCORE = "130";
	public final static String STA_APPL_WAIT_SCORE = "131";
	public final static String STA_APPL_APPROVED_CCL = "132";
	public final static String STA_APPL_DECLINED_SCORE = "133";
	public final static String STA_APPL_VERIFIED_PREAPPROVED = "136";
	public final static String STA_APPL_AWAIT_SCR_PREAPPROVED = "137";
	public final static String STA_APPL_REJECTED_CD = "127";
	public final static String STA_APPL_APPROVED_CD = "128";
	public final static String STA_APPL_PREAPPROVED_CD = "129";
	public final static String STA_APPL_INV_COMBINATION = "200";
	public final static String STA_APPL_ACCT_IN_USE = "201";
	public final static String STA_APPL_INVALID_NO_OF_RECS = "202";
	public final static String STA_APPL_INVALID_COUNTRY = "203";
	public final static String STA_APPL_INVALID_ACCT_CURR = "204";
	public final static String STA_APPL_INVALID_LANGUAGE = "205";
	public final static String STA_APPL_INVALID_ADDRESS = "206";
	public final static String STA_ACCT_FULLY_PAID = "000";
	public final static String STA_ACCT_ACTIVE = "001";
	public final static String STA_ACCT_CLOSED = "002";
	public final static String STA_ACCT_SUSPENDED = "003";
	public final static String STA_ACCT_ACTIVE_BANKRUPT = "005";
	public final static String STA_ACCT_DORMANT = "005";
	public final static String STA_ACCT_OVER_30 = "006";
	public final static String STA_ACCT_OVER_60 = "007";
	public final static String STA_ACCT_OVER_90 = "008";
	public final static String STA_ACCT_OVER_120 = "009";
	public final static String STA_ACCT_CLOSED_BANKRUPT = "011";
	public final static String STA_ACCT_COLLECTION = "016";
	public final static String STA_ACCT_COLL_STOPPED = "017";
	public final static String STA_ACCT_CLOSED_BY_REQUEST = "024";
	public final static String STA_ACCT_FEE_EXEMPTED = "025";
	public final static String STA_ACCT_INACTIVE = "026";
	public final static String STA_CUST_ACTIVE = "001";
	public final static String STA_CUST_DORMANT = "002";
	public final static String STA_CUST_SUSPENDED = "003";
	public final static String STA_CUST_CLOSED = "004";
	public final static String STA_CUST_ACTIVE_BANKRUPT = "005";
	public final static String STA_CUST_ACTIVE_HOLD_PAY = "006";
	public final static String STA_CUST_CLOSED_NEW_OWNER = "010";
	public final static String STA_CUST_CLOSED_BANKRUPT = "011";
	public final static String STA_CUST_CLOSED_EMERGENCY = "012";
	public final static String STA_CUST_CLOSED_DECEASED = "013";
	public final static String STA_CUST_ACTIVE_CANCELLED = "014";
	public final static String STA_CUST_CANCELLED_OVERDUE = "015";
	public final static String STA_CUST_CLOSED_COLLECTION = "016";
	public final static String STA_CUST_CLOSED_SEND = "017";
	public final static String STA_CUST_CLOSED_REFRESH = "018";
	public final static String STA_CUST_RESTRICTED_POSTCODE = "019";
	public final static String STA_CUST_INACTIVE = "020";
	public final static String STA_PROC_IN_PROCESS = "001";
	public final static String STA_PROC_COMPLETED = "002";
	public final static String STA_PROC_ERROR = "003";
	public final static String STA_PROC_CURRENT = "004";
	public final static String STA_PROC_LOADED = "005";
	public final static String STA_PROC_UNMATCHED = "006";
	public final static String STA_PROC_ENTERED = "007";
	public final static String STA_PROC_APPROVED = "008";
	public final static String STA_PROC_ACCRUED = "009";
	public final static String STA_PROC_CAPITALIZED = "010";
	public final static String STA_PROC_ARCHIVED = "011";
	public final static String STA_PROC_CORRECTION = "012";
	public final static String STA_PROC_CLOSED = "015";
	public final static String STA_PROC_PURGED = "016";
	public final static String STA_PROC_CANCELLED = "017";
	public final static String STA_PROC_CLEARED = "018";
	public final static String STA_PROC_REJECTED = "019";
	public final static String STA_PROC_PENDING = "020";
	public final static String STA_PROC_AUTHORIZED = "021";
	public final static String STA_PROC_TIMEOUT = "022";
	public final static String STA_PROC_RESPONDED = "023";
	public final static String STA_PROC_DUPLICATE = "024";
	public final static String STA_PROC_DUPL_UNMATCHED = "025";
	public final static String STA_PROC_DUPL_UNMATCH_TOT = "026";
	public final static String STA_PROC_DUPL_UNMATCH_COMPL = "027";
	public final static String STA_PROC_DUPL_UNMATCH_TOT_CMPL = "028";
	public final static String STA_PROC_TRANSFERRED = "029";
	public final static String STA_PROC_REVERSED = "030";
	public final static String STA_PROC_FULFILLED = "031";
	public final static String STA_PROC_MATCHED = "032";
	public final static String STA_PROC_CONFIRMED = "033";
	public final static String STA_PROC_RECONCILED = "034";
	public final static String STA_PROC_INSTRUCTION_ENTERED = "101";
	public final static String STA_PROC_INSTRUCTION_COMPLETED = "102";
	public final static String STA_PROC_INSTRUCTION_CANCELLED = "103";
	public final static String STA_PROC_CS_ERROR_START = "200";
	public final static String STA_PROC_CS_ERROR_END = "299";
	public final static String STA_VAL_VALID = "001";
	public final static String STA_VAL_DELAYED = "030";
	public final static String STA_VAL_OUT_OF_TIMEFRAME = "031";
	public final static String STA_VAL_XML_ERROR = "050";
	public final static String STA_VAL_NOT_EXPECTED = "051";
	public final static String STA_VAL_STRUCTURAL_ERROR = "052";
	public final static String STA_VAL_FAILED_ARITHM_CHECKS = "053";
	public final static String STA_VAL_SOURCE_AMT_MISMATCH = "070";
	public final static String STA_VAL_ELECTRNIC_NOT_TAX_EVID = "071";
	public final static String STA_VAL_NOT_TAX_INVOICE = "072";
	public final static String STA_VAL_NOT_ORIGINAL = "073";
	public final static String STA_VAL_INVALID_SUPPL_VAT_NUM = "074";
	public final static String STA_VAL_MISSING_CUSTOMER_REF = "075";
	public final static String STA_BIN_RANGE_ACTIVE = "001";
	public final static String PROD_TYP_NO_PRODUCTION = "000";
	public final static String PROD_TYP_DO_NOT_RENEW = "998";
	public final static String PROD_TYP_NO_PROD_OVERDUE = "999";
	public final static String PROD_TYP_NEW_CARD = "100";
	public final static String PROD_TYP_MANUAL_RENEWAL = "101";
	public final static String PROD_TYP_REPLACEMENT = "102";
	public final static String PROD_TYP_EMERGENCY_REPLACEMENT = "107";
	public final static String PROD_TYP_PIN_RE_ISSUE = "108";
	public final static String PROD_TYP_CARD_RE_ISSUE = "109";
	public final static String PROD_TYP_SCHEDULED_RENEWAL = "110";
	public final static String PROD_TYP_EXPRESS_CARD = "111";
	public final static String PROD_TYP_EMERGENCY_CARD = "112";
	public final static String PROD_TYP_OTHER_REPLACEMENT = "113";
	public final static String PROD_TYP_URGENT_NEW_CARD = "114";
	public final static String PROD_TYP_PIN_CHANGE = "115";
	public final static String PROD_TYP_PIN_DROP_OR_STOP = "116";
	public final static String FIL_UNKNOWN = "001";
	public final static String FIL_ACQ_POS = "002";
	public final static String FIL_ACQ_ATM = "003";
	public final static String FIL_ACQ_POS_ACK = "004";
	public final static String FIL_TEST = "005";
	public final static String FIL_ISS_PRESENTMENTS = "006";
	public final static String FIL_DETAILED_POSITIONS = "007";
	public final static String FIL_INCOMING_TRANSACTIONS = "008";
	public final static String FIL_OUTGOING_TRANSACTIONS = "009";
	public final static String FIL_GL_TRANSACTIONS = "010";
	public final static String FIL_STATEMENT = "011";
	public final static String FIL_DATA = "012";
	public final static String FIL_SETTLEMENT = "013";
	public final static String FIL_EXCEPTIONS = "014";
	public final static String FIL_BATCH_INPUT = "015";
	public final static String FIL_RECON = "016";
	public final static String SEQ_FILE_NUMBER = "001";
	public final static String SEQ_OUR_UNIQUE_REF_NO = "002";
	public final static String SEQ_REPORT_ID = "003";
	public final static String SEQ_GROUP_ID = "004";
	public final static String SEQ_DETAIL_ID = "005";
	public final static String SEQ_GL_FILE_NUMBER = "006";
	public final static String SEQ_BANK_SERIAL_NO = "007";
	public final static String SEQ_POSTGIRO_NO = "008";
	public final static String SEQ_EP_IRN_RETRVL = "009";
	public final static String SEQ_CLIENT_NUMBER = "010";
	public final static String SEQ_LAST_TEST_NO = "013";
	public final static String SEQ_RECORD_ID = "015";
	public final static String SEQ_APPLICATION_NO = "017";
	public final static String SEQ_GROUP_NUMBER = "018";
	public final static String SEQ_TRANSACTION_NO = "019";
	public final static String SEQ_CENTER_BATCH_ID = "020";
	public final static String SEQ_STATEMENT_NUMBER = "021";
	public final static String SEQ_EUROPAY_STAN = "022";
	public final static String SEQ_EP_IRN_CHGBACK = "023";
	public final static String SEQ_VISA_IRN_CHGBACK = "024";
	public final static String SEQ_VISA_IRN_RETRVL = "025";
	public final static String SEQ_ISS_FAN_FILE_NO = "026";
	public final static String SEQ_ISS_CON_FILE_NO = "027";
	public final static String SEQ_PRAEGE_FILE_NO = "028";
	public final static String SEQ_ISS_STATEMENT_NO = "029";
	public final static String SEQ_RBS_BASE_ACCT = "032";
	public final static String SEQ_COLLECTION = "035";
	public final static String SEQ_GL_BATCH_NO = "037";
	public final static String SEQ_GL_TRANSACTION_NO = "038";
	public final static String SEQ_OCTAGON_RECORD_ID = "039";
	public final static String SEQ_CSB_DAY_PARAMETER = "040";
	public final static String SEQ_BATCH_INP_TRAN_NO = "042";
	public final static String SEQ_CLIENT_NUMBER_ISS = "043";
	public final static String SEQ_ECB_ATM_REQUEST = "044";
	public final static String SEQ_VISA_NMAS_LOCATOR = "045";
	public final static String SEQ_VISA_NMAS_SLIP = "046";
	public final static String SEQ_ACCOUNT_NUMBER = "047";
	public final static String SEQ_MKB_PAYM_REQ = "050";
	public final static String SEQ_FX_RATE_SEQ_NO = "051";
	public final static String SEQ_MATCH_REFERENCE = "065";
	public final static String SEQ_VOICE_AUTH_COUNTER = "066";
	public final static String SEQ_PGATLAS_NO = "067";
	public final static String SEQ_PROCESS_NO = "068";
	public final static String SEQ_OCTA_INPUT_SEQ_NO = "069";
	public final static String SEQ_BONUS_CHECK_NO = "070";
	public final static String SEQ_BBS_TRAN_ASSIGN_NO = "071";
	public final static String SEQ_BBS_DIR_REMIT_NO = "072";
	public final static String SEQ_RECON_FILE_NUMBER = "073";
	public final static String SEQ_RECON_SLIP_NUMBER = "074";
	public final static String SEQ_TEST = "999";
	public final static int SEQ_NO_ALLOC = 50;
	public final static String CONF_NO = "000";
	public final static String CONF_YES = "001";
	public final static String CONF_PENDING = "002";
	public final static String CONF_CONDITIONAL = "003";
	public final static String SIGN_NA = "000";
	public final static String SIGN_CREDIT = "001";
	public final static String SIGN_PLUS = "001";
	public final static String SIGN_DEBIT = "002";
	public final static String SIGN_MINUS = "002";
	public final static String SIGN_EQ = "003";
	public final static String SIGN_LT = "004";
	public final static String SIGN_GT = "005";
	public final static String SIGN_NE = "006";
	public final static String SIGN_LE = "007";
	public final static String SIGN_GE = "008";
	public final static String SIGN_BE = "009";
	public final static String SIGN_MULTIPLY = "010";
	public final static String SIGN_CONSTANT = "011";
	public final static String PER_NA = "000";
	public final static String PER_DAILY = "001";
	public final static String PER_FORTNIGHT = "003";
	public final static String PER_MONTHLY = "005";
	public final static String PER_QUARTERLY = "006";
	public final static String PER_YEARLY = "007";
	public final static String PER_START_OF_CYCLE = "020";
	public final static String PER_END_OF_CYCLE = "021";
	public final static String PER_CONTRACT_INIT = "022";
	public final static String PER_CONTRACT_ANNIVERSARY_2 = "023";
	public final static String PER_PAYM_DUE_DATE = "024";
	public final static String PER_SERVICE_ANNIVERSARY = "025";
	public final static String PER_CONTRACT_ANNIVERSARY = "026";
	public final static String PER_ACCOUNT_ANNIVERSARY = "027";
	public final static String PER_NEW_ACCOUNT = "028";
	public final static String MONTHLY_CYCLE_1 = "301";
	public final static String REPL_THRESH_1_MTH = "400";
	public final static String REPL_THRESH_2_MTH = "401";
	public final static String REPL_THRESH_3_MTH = "402";
	public final static String REPL_THRESH_4_MTH = "403";
	public final static String PER_BILLING_CYCLE_DATE = "501";
	public final static String PER_MONTH_END = "600";
	public final static String PER_CYCLE_END = "601";
	public final static String PER_MONTH_AND_CYCLE_END = "602";
	public final static String PER_TIME_OF_ACTION = "701";
	public final static String PER_APPLICATION_PROCESSING = "702";
	public final static String PER_FIRST_INSTALLMENT = "703";
	public final static String PER_NEW_APPLICATION = "704";
	public final static String PER_APPLICATION_EXTENSION = "705";
	public final static String PER_FIRST_TRANSACTION = "706";
	public final static String PER_FIRST_DR_INTEREST = "707";
	public final static String PER_DAILY_STAT = "901";
	public final static String PER_WEEKLY_STAT = "902";
	public final static String PER_WEEKLY_AVER_STAT = "903";
	public final static String PER_MONTHLY_STAT = "904";
	public final static String PER_90_DAYS_STAT = "905";
	public final static String PER_YEARLY_STAT = "906";
	public final static String PER_CLIENT_TARIFF = "998";
	public final static String PROC_NAME_EPI_INW = "001";
	public final static String PROC_NAME_CEKAB = "002";
	public final static String PROC_NAME_VISA_INW = "003";
	public final static String PROC_NAME_VISA_INT_OUT = "004";
	public final static String PROC_NAME_VISA_DOM_OUT = "005";
	public final static String PROC_NAME_EPI_OUT = "006";
	public final static String PROC_NAME_CONRAD_OUT = "007";
	public final static String PROC_NAME_FANTOM_OUT = "008";
	public final static String PROC_NAME_PAYMENT_PG_OUT = "009";
	public final static String PROC_NAME_PAYMENT_BG_OUT = "010";
	public final static String PROC_NAME_GL_OUT = "011";
	public final static String PROC_NAME_MERCH_PAYM = "013";
	public final static String PROC_NAME_ACCT_RESET = "014";
	public final static String PROC_NAME_FEES_INW = "015";
	public final static String PROC_NAME_VISA_BIN = "016";
	public final static String PROC_NAME_ECMC_BIN = "017";
	public final static String PROC_NAME_ENECUR = "018";
	public final static String PROC_NAME_SWE_DOM = "019";
	public final static String PROC_NAME_RECON = "020";
	public final static String PROC_NAME_CREDIT_IN = "021";
	public final static String PROC_NAME_CONRAD_EXC_PROC = "022";
	public final static String PROC_NAME_CONRAD_FILE_LOAD = "023";
	public final static String PROC_NAME_CARD_FEES = "024";
	public final static String PROC_NAME_GL_OUT_PH1_SUMM = "025";
	public final static String PROC_NAME_EXCEPTION_PRC = "026";
	public final static String PROC_NAME_INTEREST_ACCRUAL = "027";
	public final static String PROC_NAME_INTEREST_CAPT = "028";
	public final static String PROC_NAME_VISA_OUT = "029";
	public final static String PROC_NAME_CLOSE_CYCLE = "030";
	public final static String PROC_NAME_MERCH_STATEMENT = "031";
	public final static String PROC_NAME_TP2_DOWNLOAD = "032";
	public final static String PROC_NAME_SAFE_FID_INW = "033";
	public final static String PROC_NAME_READ_ORIG_MESSAGE = "033";
	public final static String PROC_NAME_UPDATE_PAYSTAT_HIST = "034";
	public final static String PROC_NAME_CLIENT_INTRA_PAYM2 = "035";
	public final static String PROC_NAME_MERCH_STATISTICS = "036";
	public final static String PROC_NAME_HSBC_BONUS_FILE = "037";
	public final static String PROC_NAME_CSB_GL_FILE = "038";
	public final static String PROC_NAME_CSB_RECLASSIFY = "039";
	public final static String PROC_NAME_STOPLIST_DISTRIB = "040";
	public final static String PROC_NAME_REPROCESS_DEST_SUSP = "041";
	public final static String PROC_NAME_SOURCE_TRANSFER = "042";
	public final static String PROC_NAME_SAFE_INW = "043";
	public final static String PROC_NAME_GL_TRANS_INW = "044";
	public final static String PROC_NAME_PURGE_FILE = "045";
	public final static String PROC_NAME_ECCF_OUT = "046";
	public final static String PROC_NAME_MC_INET_OUT = "047";
	public final static String PROC_NAME_REPROCESS_RETURNS = "048";
	public final static String PROC_NAME_REPROCESS_SRC_SUSP = "049";
	public final static String PROC_NAME_RECONCILE_GL = "050";
	public final static String PROC_NAME_PURGE_PENDINGAUTH = "051";
	public final static String PROC_NAME_SAFE_OUT = "052";
	public final static String PROC_NAME_MOVE_FILE_TO_HISTORY = "053";
	public final static String PROC_NAME_ARCHIVE_FILE = "054";
	public final static String PROC_NAME_PAYMENT_NBK_REQ_OUT = "055";
	public final static String PROC_NAME_MC_INW = "056";
	public final static String PROC_NAME_EPI_ACQ_STAT = "058";
	public final static String PROC_NAME_EPI_ISS_STAT = "059";
	public final static String PROC_NAME_VISA_ACQ_STAT = "060";
	public final static String PROC_NAME_VISA_ISS_STAT = "061";
	public final static String PROC_NAME_EPI_DPR_INW = "062";
	public final static String PROC_NAME_CREDIT_ADV_IN = "063";
	public final static String PROC_NAME_RBS_TRN_OUT = "064";
	public final static String PROC_NAME_ACQUIRE_OUT = "066";
	public final static String PROC_NAME_MERCHANT_OUT = "067";
	public final static String PROC_NAME_CARDSACCTS_OUT = "068";
	public final static String PROC_NAME_CONTRACT_CHARGES = "069";
	public final static String PROC_NAME_RBS_BALANCES_INW = "070";
	public final static String PROC_NAME_DUMMY_OUT_FILE = "071";
	public final static String PROC_NAME_EMBOSS_OUT = "072";
	public final static String PROC_NAME_ENCODING_FILE = "073";
	public final static String PROC_NAME_APPLICATION_LOAD = "074";
	public final static String PROC_NAME_REVERSE_FILE = "075";
	public final static String PROC_NAME_PRC_MERCHANT_EMBOSS = "076";
	public final static String PROC_NAME_PRC_MERCHANT_LOYALTY = "077";
	public final static String PROC_NAME_VISA_ONUS_OUT = "078";
	public final static String PROC_NAME_VISA_EXCEPT_BRIDGE = "079";
	public final static String PROC_NAME_MINB_DAILY_BAL_UPLD = "080";
	public final static String PROC_NAME_MINB_DAILY_BAL_DOWN = "081";
	public final static String PROC_NAME_MINB_APPL_RESP = "082";
	public final static String PROC_NAME_MINB_PAYM_RESPONSE = "083";
	public final static String PROC_NAME_INTEREST_BY_ACCT = "084";
	public final static String PROC_NAME_VARA_ACCT_INFO_OUT = "085";
	public final static String PROC_NAME_BATCH_PROCESS = "086";
	public final static String PROC_NAME_CLIENT_PAYM = "087";
	public final static String PROC_NAME_KNET_BIN = "088";
	public final static String PROC_NAME_NW_SWL = "089";
	public final static String PROC_NAME_INSURANCE_RENEWALS = "090";
	public final static String PROC_NAME_CARD_PRE_PRODUCTION = "091";
	public final static String PROC_NAME_SCHEDULED_RENEWALS = "092";
	public final static String PROC_NAME_BW2_TRANSACTIONS = "093";
	public final static String PROC_NAME_INFOSPAN = "094";
	public final static String PROC_NAME_CLS_OUT = "095";
	public final static String PROC_NAME_CLS_IN = "096";
	public final static String PROC_NAME_OUTW_OCTAGON = "097";
	public final static String PROC_NAME_INW_OCTAGON = "098";
	public final static String PROC_NAME_OCR_PAYMENTS = "099";
	public final static String PROC_NAME_VARAZ_GL = "100";
	public final static String PROC_NAME_CSB_DATA_CONV = "101";
	public final static String PROC_NAME_PRC_INW_LOYALTY = "102";
	public final static String PROC_NAME_CBK_BAS = "104";
	public final static String PROC_NAME_ISS_BATCH_PROCESS = "105";
	public final static String PROC_NAME_PRC_VP_STATEMENT = "106";
	public final static String PROC_NAME_PRC_OCR_INPAY = "107";
	public final static String PROC_NAME_AUTOPAYMENT = "108";
	public final static String PROC_NAME_REGEN_VALUE_BAL = "109";
	public final static String PROC_NAME_PRC_CARD_BLOCK = "110";
	public final static String PROC_NAME_ATC_TRAN_OUT = "111";
	public final static String PROC_NAME_MERCHANT_PAYMENT = "112";
	public final static String PROC_NAME_JNB_POS_BATCH_IN = "113";
	public final static String PROC_NAME_DELTA_TTY_CAF = "115";
	public final static String PROC_NAME_KDB_CTLF_NO_INW = "116";
	public final static String PROC_NAME_KDB_CTLF_ISS_INW = "116";
	public final static String PROC_NAME_INET_ONUS_INW = "118";
	public final static String PROC_NAME_INET_ONUS_OUT = "119";
	public final static String PROC_NAME_ECCF_OUT_DOM = "120";
	public final static String PROC_NAME_BATCH_TRAN_LOADER = "121";
	public final static String PROC_NAME_VP_RPT_SUPP_USAGE = "122";
	public final static String PROC_NAME_VP_RPT_CARDHOLDER = "123";
	public final static String PROC_NAME_VP_RPT_EXCEPTION = "124";
	public final static String PROC_NAME_VP_RPT_COMMODITY = "125";
	public final static String PROC_NAME_FX_RATES_LOADER = "126";
	public final static String PROC_NAME_ACCT_STAT_SYNC = "127";
	public final static String PROC_NAME_CREDITSCORE_OUT = "128";
	public final static String PROC_NAME_EBANK_LIMIT_INW = "129";
	public final static String PROC_NAME_BNKRT_OTH_NEG_LIST = "130";
	public final static String PROC_NAME_PROV_PAYMENT_OUT = "131";
	public final static String PROC_NAME_PROV_BONUS_INW = "132";
	public final static String PROC_NAME_VISA_PURCH_OUT = "133";
	public final static String PROC_NAME_VARA_MERC_PAYMENT = "134";
	public final static String PROC_NAME_EXPN_RBS_INW = "135";
	public final static String PROC_NAME_ATM_SERVICE_IN = "136";
	public final static String PROC_NAME_APACS29_INW = "137";
	public final static String PROC_NAME_ATM_SERVICE_OUT = "138";
	public final static String PROC_NAME_BACS_INW = "139";
	public final static String PROC_NAME_BACS_OUTW_CR = "140";
	public final static String PROC_NAME_POS_HYPERCOM_LOADER = "141";
	public final static String PROC_NAME_AMEX_OUT = "142";
	public final static String PROC_NAME_APPL_INTERFACE = "143";
	public final static String PROC_NAME_FIX_GL_001 = "144";
	public final static String PROC_NAME_GENERIC_STATEMENT = "145";
	public final static String PROC_NAME_PROCARD_POSTEN_INW = "146";
	public final static String PROC_NAME_AUTOGIRO_BGP_CLIENT = "147";
	public final static String PROC_NAME_AUTOGIRO_BGP_PAY_REQ = "148";
	public final static String PROC_NAME_AUTOGIRO_BGP_INC_PAY = "149";
	public final static String PROC_NAME_AUTOGIRO_BGC_CLIENT = "150";
	public final static String PROC_NAME_AUTOGIRO_BGC_PAY_REQ = "151";
	public final static String PROC_NAME_AUTOGIRO_BGC_INC_PAY = "152";
	public final static String PROC_NAME_AUTOGIRO_PG_CLIENT = "153";
	public final static String PROC_NAME_AUTOGIRO_PG_CONFIRM = "154";
	public final static String PROC_NAME_AUTOGIRO_PG_PAY_REQ = "155";
	public final static String PROC_NAME_AUTOGIRO_PG_INC_PAY = "156";
	public final static String PROC_NAME_PROCARD_GENIDATA_INW = "157";
	public final static String PROC_NAME_PROCARD_ISK_OUT = "158";
	public final static String PROC_NAME_EP_STOPLIST = "159";
	public final static String PROC_NAME_BMS_EAGLE_TRAN_OUT = "160";
	public final static String PROC_NAME_BMS_PEGA = "161";
	public final static String PROC_NAME_MASTERCARD_MATCH_OUT = "162";
	public final static String PROC_NAME_BNKRT_HOTLIST_OUT = "163";
	public final static String PROC_NAME_ACCRUALS_REVERSAL = "164";
	public final static String PROC_NAME_BNKRT_STOPCD_PAPER = "165";
	public final static String PROC_NAME_PAYMENT_FP_OUT = "166";
	public final static String PROC_NAME_BNKRT_PAPR_STOP_OUT = "167";
	public final static String PROC_NAME_BNKRT_BANK_INSUR_OUT = "168";
	public final static String PROC_NAME_BMS_OUT_MANUAL = "169";
	public final static String PROC_NAME_BNKRT_B24NEG_REFRESH = "170";
	public final static String PROC_NAME_ECCF_ONUS_OUT = "171";
	public final static String PROC_NAME_ECCF_EXCEPT_BRIDGE = "172";
	public final static String PROC_NAME_ECCF_INET_ONUS_OUT = "173";
	public final static String PROC_NAME_INET_EXCEPT_BRIDGE = "174";
	public final static String PROC_NAME_BNKRT_OPT_ARCHIVING = "175";
	public final static String PROC_NAME_BNKRT_B24PBF_OUT = "176";
	public final static String PROC_NAME_SUSPENSE_PAYM = "177";
	public final static String PROC_NAME_INTRABANK_INWARD = "178";
	public final static String PROC_NAME_MERCHANT_POS_INW = "179";
	public final static String PROC_NAME_BNKRT_SKB_INW = "180";
	public final static String PROC_NAME_BNKRT_MAESTRO_INW = "181";
	public final static String PROC_NAME_BNKRT_MAXI_INW = "182";
	public final static String PROC_NAME_BNKRT_ELEKTRO_INW = "183";
	public final static String PROC_NAME_BNKRT_KOPER_INW = "184";
	public final static String PROC_NAME_BMS_EAGLE_AUTH_OUT = "185";
	public final static String PROC_NAME_BNKRT_DAILY_GROSSAMT = "186";
	public final static String PROC_NAME_VISA_INW_VSS = "187";
	public final static String PROC_NAME_BNKRT_POSTED_BATCHES = "188";
	public final static String PROC_NAME_CIM_INTERFACE = "189";
	public final static String PROC_NAME_ILF_UPLOAD = "190";
	public final static String PROC_NAME_BNKRT_POSTED_POSFEE = "191";
	public final static String PROC_NAME_BKRT_MERCH_PAY_NLB = "192";
	public final static String PROC_NAME_BKRT_MERCH_PAY_OTH = "193";
	public final static String PROC_NAME_BNKRT_DEBIT_POSITION = "194";
	public final static String PROC_NAME_BATCH_TRAN_LDR_MISC = "195";
	public final static String PROC_NAME_BANK_CLEARING = "196";
	public final static String PROC_NAME_MOVE_PAYMENTS = "197";
	public final static String PROC_NAME_BACS_OUTW_DR = "199";
	public final static String PROC_NAME_ACQ_TRAN_INW = "200";
	public final static String PROC_NAME_GEN_ISS_OUT_FILE = "201";
	public final static String PROC_NAME_GEN_ACQ_OUT_FILE = "202";
	public final static String PROC_NAME_CLIENT_AUTH_TRANS = "203";
	public final static String PROC_NAME_CIS_FINANCIAL_FILE = "204";
	public final static String PROC_NAME_RESET_REAPPLY = "205";
	public final static String PROC_NAME_BNKRT_B24CAF_OUT = "206";
	public final static String PROC_NAME_TZAPIS_STAND_ORDER = "207";
	public final static String PROC_NAME_R73TRAPB_STAND_ORDER = "208";
	public final static String PROC_NAME_NLB_DEBIT_CARD_OUT = "209";
	public final static String PROC_NAME_BATCH_TRANSFERS = "210";
	public final static String PROC_NAME_LD_POS_SCHLUMBERGER = "211";
	public final static String PROC_NAME_BNKRT_POS_FEE_SETTL = "212";
	public final static String PROC_NAME_TZAPIS_CONFIRM_PAY = "213";
	public final static String PROC_NAME_R73TRAPB_CONFIRM_PAY = "214";
	public final static String PROC_NAME_END_OF_CYCLE = "215";
	public final static String PROC_NAME_ING_CARD_STATUS_OUT = "216";
	public final static String PROC_NAME_ING_TRAN_DOWNLOAD = "217";
	public final static String PROC_NAME_ING_NORG_TRANSL = "218";
	public final static String PROC_NAME_STATISTIC_GENERATION = "219";
	public final static String PROC_NAME_MKB_CVFEE_UPLOAD = "220";
	public final static String PROC_NAME_BATCH_ACQ_BONUS = "221";
	public final static String PROC_NAME_BATCH_MISC_BONUS = "222";
	public final static String PROC_NAME_SYNC_PAYMENT_VAL_BAL = "223";
	public final static String PROC_NAME_PROCARD_EPOST_FILE = "224";
	public final static String PROC_NAME_ING_DELIVERY_REFRESH = "225";
	public final static String PROC_NAME_MKB_TELEBANK = "226";
	public final static String PROC_NAME_MKB_STOPLIST_VISA = "227";
	public final static String PROC_NAME_MKB_STOPLIST_EURO = "228";
	public final static String PROC_NAME_MKB_CARDDATA_OUT = "229";
	public final static String PROC_NAME_VISA_PLUS_BIN = "230";
	public final static String PROC_NAME_MKB_CBBDATA_OUT = "231";
	public final static String PROC_NAME_MKB_LETTERFILE_OUT = "232";
	public final static String PROC_NAME_MERCH_PAYM_TRAN = "233";
	public final static String PROC_NAME_MERCH_PAYM_BALANCE = "234";
	public final static String PROC_NAME_ADDRESS_VALIDATION = "235";
	public final static String PROC_NAME_MDS_FILE_LOAD = "236";
	public final static String PROC_NAME_CLEANUP_PAYM_HISTORY = "237";
	public final static String PROC_NAME_INFOSPAN_CORP = "238";
	public final static String PROC_NAME_GSS_MERCH_PAYM_OUT = "239";
	public final static String PROC_NAME_IAPA_EMBOSS = "240";
	public final static String PROC_NAME_PG_ISS_STATEMENT = "241";
	public final static String PROC_NAME_APPL_BATCH_PROC_MISC = "242";
	public final static String PROC_NAME_GSS_AGREEMENT_OUT = "244";
	public final static String PROC_NAME_GSS_AGREEMENT_INW = "245";
	public final static String PROC_NAME_GSS_ISS_PAYM_REQUEST = "246";
	public final static String PROC_NAME_GSS_ISS_PAYM_INW = "247";
	public final static String PROC_NAME_INSTALLMENT_GEN = "248";
	public final static String PROC_NAME_GSS_POS_SETTLE = "249";
	public final static String PROC_NAME_GSS_LOAD_PBS_LOGS = "250";
	public final static String PROC_NAME_GSS_MANUAL_PAYM_INW = "251";
	public final static String PROC_NAME_GSS_CPR_IN = "252";
	public final static String PROC_NAME_GSS_CPR_OUT = "253";
	public final static String PROC_NAME_MOBILECOM_IN = "254";
	public final static String PROC_NAME_APPL_BATCH_PROC_ACQ = "255";
	public final static String PROC_NAME_MOBILECOM_OUT = "256";
	public final static String PROC_NAME_MOBILECOM_AUTH = "257";
	public final static String PROC_NAME_GSS_ISS_BS_TOTAL = "258";
	public final static String PROC_NAME_AUTH_BATCH_PROC = "259";
	public final static String PROC_NAME_EXCEPTION_INTERFACE = "260";
	public final static String PROC_NAME_REJECT_TRANSACTIONS = "261";
	public final static String PROC_NAME_ECCF_INTER_OUT = "262";
	public final static String PROC_NAME_MDS_REPORTS = "263";
	public final static String PROC_NAME_COMSERV_ACQ_TRANS = "264";
	public final static String PROC_NAME_COMSERV_INC_TRANS = "265";
	public final static String PROC_NAME_MDS_LOAD_TRANS = "266";
	public final static String PROC_NAME_MDS_CONFIRM_TRANS = "267";
	public final static String PROC_NAME_MIS_INFOSPAN_PURCH = "268";
	public final static String PROC_NAME_MIS_INFOSPAN_CORP = "269";
	public final static String PROC_NAME_MDS_EXCP_TRANS = "270";
	public final static String PROC_NAME_EPI_DOMESTIC_INW = "271";
	public final static String PROC_NAME_GSS_TAX_OUT = "272";
	public final static String PROC_NAME_OMNIPAY_FX_RATES_IN = "273";
	public final static String PROC_NAME_VISA_TC57_INW = "274";
	public final static String PROC_NAME_GSS_DEBIT_TRANS_OUT = "275";
	public final static String PROC_NAME_COMSERVER_TRANS = "276";
	public final static String PROC_NAME_OMNI_AUTH_DATA = "277";
	public final static String PROC_NAME_CCB_CUST_FILE = "278";
	public final static String PROC_NAME_MERCHANT_STATISTICS = "279";
	public final static String PROC_NAME_REGEN_BAL_BY_ACCT = "280";
	public final static String PROC_NAME_REGEN_BAL_INPUT_ACCT = "281";
	public final static String PROC_NAME_MERCHANT_TIER_LEVEL = "282";
	public final static String PROC_NAME_CCB_CARD_FILE = "283";
	public final static String PROC_NAME_COLLECTION = "284";
	public final static String PROC_NAME_ACTIVITY_MONITOR = "285";
	public final static String PROC_NAME_SVC_PROVIDER_FEES = "286";
	public final static String PROC_NAME_HSBC_NON_RESIDENTS = "287";
	public final static String PROC_NAME_VISA_ECCF_CONV_INW = "288";
	public final static String PROC_NAME_HSBC_QCIF = "289";
	public final static String PROC_NAME_PAYMENT_AT_OUT = "290";
	public final static String PROC_NAME_ECCF_VISA_CONV_OUTW = "291";
	public final static String PROC_NAME_HSBC_MERCH_PAY = "292";
	public final static String PROC_NAME_HSBC_MERCH_PAY_OTH = "293";
	public final static String PROC_NAME_MC_MATCH_IN = "294";
	public final static String PROC_NAME_DAILY_MC_STOPLIST = "295";
	public final static String PROC_NAME_REGIONAL_MC_STOPLIST = "296";
	public final static String PROC_NAME_CLIENT_INTRA_PAYM = "297";
	public final static String PROC_NAME_CLIENT_OTHER_PAYM = "298";
	public final static String PROC_NAME_HSBC_PLAZA_BONUS = "299";
	public final static String PROC_NAME_HSBC_BRANCH_DEPOSIT = "300";
	public final static String PROC_NAME_HSBC_ATM_INW = "301";
	public final static String PROC_NAME_PAYMENT_BATCH = "302";
	public final static String PROC_NAME_HSBC_POS_MAINT_OUT = "303";
	public final static String PROC_NAME_REMINDER_PROCS = "304";
	public final static String PROC_NAME_INIT_TIER_STATISTICS = "305";
	public final static String PROC_NAME_HSBC_IMPRINTER_DTLS = "306";
	public final static String PROC_NAME_HSBC_CARDHOLDER_DB = "307";
	public final static String PROC_NAME_REMINDER_CANC = "308";
	public final static String PROC_NAME_REMINDER_COLL = "309";
	public final static String PROC_NAME_DINERS_PURC_OUT = "310";
	public final static String PROC_NAME_OUTW_OCTAGON_SIS = "311";
	public final static String PROC_NAME_VUB_TRAN_INW = "312";
	public final static String PROC_NAME_GSS_REMINDER_FILE = "313";
	public final static String PROC_NAME_FDMS_80BS_INW = "314";
	public final static String PROC_NAME_FDMS_M045 = "315";
	public final static String PROC_NAME_HSBC_CAF = "316";
	public final static String PROC_NAME_VUB_CAF_OUT = "317";
	public final static String PROC_NAME_COS_BATCH = "318";
	public final static String PROC_NAME_HSBC_DB_DEPOSIT = "319";
	public final static String PROC_NAME_HSBC_QUIKCASH_DEBIT = "320";
	public final static String PROC_NAME_HSBC_MIMBOL = "321";
	public final static String PROC_NAME_HSBC_CAF_MIMBOL = "322";
	public final static String PROC_NAME_VUB_TRAN_OUT = "323";
	public final static String PROC_NAME_CZSB_CRU_COMMERCIAL = "324";
	public final static String PROC_NAME_FDMS_E336 = "325";
	public final static String PROC_NAME_HSBC_PBF = "326";
	public final static String PROC_NAME_CBK_EXC_OUT = "327";
	public final static String PROC_NAME_DINERS_CASH_OUT = "328";
	public final static String PROC_NAME_EPI_OER = "329";
	public final static String PROC_NAME_MTS_APPL_INTERFACE = "330";
	public final static String PROC_NAME_HSBC_THF = "331";
	public final static String PROC_NAME_FSPA_NEW_CARDHOLDER = "332";
	public final static String PROC_NAME_FSPA_REF_CARDHOLDER = "333";
	public final static String PROC_NAME_FSPA_DEREG_KUS = "334";
	public final static String PROC_NAME_HSBC_CACT = "335";
	public final static String PROC_NAME_MDC_BATCH_ALLROUND = "336";
	public final static String PROC_NAME_ALLROUND_AUTO_SUSP = "337";
	public final static String PROC_NAME_COS_NT_TRANSACTION = "338";
	public final static String PROC_NAME_COS_NT_BATCH_INPUT = "339";
	public final static String PROC_NAME_GEN_OTHER_OUT_FILE = "340";
	public final static String PROC_NAME_FSPA_BONUS_CHECK = "341";
	public final static String PROC_NAME_FDMS_AUTH_DATA_OUT = "342";
	public final static String PROC_NAME_ALLRND_ACQ_BCH_PROC = "343";
	public final static String PROC_NAME_VUB_GL_INTERFACE = "344";
	public final static String PROC_NAME_HSBC_ISS_STMT_INTER = "345";
	public final static String PROC_NAME_HSBC_MRC_STMT_INTER = "346";
	public final static String PROC_NAME_GFF_PAYM_INTERFACE = "347";
	public final static String PROC_NAME_OMNI_MAPS_OUT_FILE = "348";
	public final static String PROC_NAME_HSBC_DB_OUT_FILES = "349";
	public final static String PROC_NAME_FSPA_SPAR = "350";
	public final static String PROC_NAME_FSPA_DEREG_PRIME = "351";
	public final static String PROC_NAME_FSPA_SPAR_INW = "352";
	public final static String PROC_NAME_HSBC_RESIDUAL_ADVICE = "353";
	public final static String PROC_NAME_ICC_PAYM_REQUEST = "354";
	public final static String PROC_NAME_ICC_PAYM_RESPONSE = "355";
	public final static String PROC_NAME_BACS_OUTW_EUR_DR = "356";
	public final static String PROC_NAME_BACS_OUTW_EUR_CR = "357";
	public final static String PROC_NAME_HSBC_HUB_ACCT = "358";
	public final static String PROC_NAME_HSBC_HUB_CLIENT = "359";
	public final static String PROC_NAME_LIMIT_RENEWALS = "360";
	public final static String PROC_NAME_ING_ATLAS_PENDING = "361";
	public final static String PROC_NAME_IPM_MC_OUT = "362";
	public final static String PROC_NAME_IPM_ONUS_OUT = "363";
	public final static String PROC_NAME_IPM_ONUS_INW = "364";
	public final static String PROC_NAME_IPM_MC_INW = "366";
	public final static String PROC_NAME_DLT_TRAN_DOWNLOAD = "367";
	public final static String PROC_NAME_DELTA_TRAN_INW = "368";
	public final static String PROC_NAME_DELTA_CARDS_OUTW = "369";
	public final static String PROC_NAME_DELTA_MERCHANT_OUT = "370";
	public final static String PROC_NAME_DELTA_ISS_PAYM_INW = "371";
	public final static String PROC_NAME_DELTA_ISS_PAYM_OUTW = "372";
	public final static String PROC_NAME_HSBC_CUSTOMER_INFO = "373";
	public final static String PROC_NAME_WELLS_REJECTS_INW = "374";
	public final static String PROC_NAME_WELLS_PAYMENT_OUT = "375";
	public final static String PROC_NAME_MKB_UPD_TARIFF = "376";
	public final static String PROC_NAME_DELTA_CARD_STAT_OUT = "377";
	public final static String PROC_NAME_ECB_ATM_REQUEST = "378";
	public final static String PROC_NAME_BACS_OUTW_MANDATE = "379";
	public final static String PROC_NAME_STMT_FAX_EMAIL = "380";
	public final static String PROC_NAME_IPM_BIN_LOAD = "381";
	public final static String PROC_NAME_EBC_RECON_REPORT = "382";
	public final static String PROC_NAME_FDMS_CASH_MGT_OUT = "383";
	public final static String PROC_NAME_BOV_INW = "384";
	public final static String PROC_NAME_BOV_OUT = "385";
	public final static String PROC_NAME_FINANCIAL_STATISTICS = "386";
	public final static String PROC_NAME_PAYMENT_TRAN_ADJ = "387";
	public final static String PROC_NAME_TRN_EXTR_BILL_PAYMT = "388";
	public final static String PROC_NAME_DELTA_SPLIT_TRAN_INW = "389";
	public final static String PROC_NAME_DELTA_TRAN_ACQ_INW = "390";
	public final static String PROC_NAME_MTS_DAY_VISA_SMS_PRC = "391";
	public final static String PROC_NAME_DELTA_MRCH_PAY_OUT = "392";
	public final static String PROC_NAME_JCB_XCHNG_OUT = "393";
	public final static String PROC_NAME_JCB_XCHNG_INW = "394";
	public final static String PROC_NAME_FX_RATES_VISA_IN = "395";
	public final static String PROC_NAME_FX_RATES_ECMC_IN = "396";
	public final static String PROC_NAME_CEKAB_OUT = "397";
	public final static String PROC_NAME_FDMS_80BS_DCC_INW = "398";
	public final static String PROC_NAME_CONSOLE = "399";
	public final static String PROC_NAME_CEKAB_HVE_BENCHMARK = "400";
	public final static String PROC_NAME_RECON_BEFORE_STAT = "401";
	public final static String PROC_NAME_RECON_AFTER_STAT = "402";
	public final static String PROC_NAME_HSBC_ATMP_REFRESH = "403";
	public final static String PROC_NAME_HSBC_ATMP_INW = "404";
	public final static String PROC_NAME_MKB_INS_STUDENT_OUT = "405";
	public final static String PROC_NAME_MKB_INS_ELECTRON_OUT = "406";
	public final static String PROC_NAME_MKB_INS_STANDARD_OUT = "407";
	public final static String PROC_NAME_HSBC_ATMP_DEBIT_FILE = "408";
	public final static String PROC_NAME_HSBC_ATMP_LANG_IND = "409";
	public final static String PROC_NAME_HSBC_ATMP_CCP = "410";
	public final static String PROC_NAME_MX2 = "411";
	public final static String PROC_NAME_IPM_UPD_BIN_LOAD = "412";
	public final static String PROC_NAME_MINB_250_REPORT = "413";
	public final static String PROC_NAME_AEGN_INW = "414";
	public final static String PROC_NAME_AEGN_OUT = "415";
	public final static String PROC_NAME_AMEXDC_TRN_ADDENDA = "416";
	public final static String PROC_NAME_AMEXDC_DEMOGRAPHIC = "417";
	public final static String PROC_NAME_AMEXDC_ATM_LOCATION = "418";
	public final static String PROC_NAME_AMEXDC_VOL_SUMMARY = "419";
	public final static String PROC_NAME_AMEX_FRAUD_OUT = "420";
	public final static String PROC_NAME_AMEX_FRAUD_INW = "421";
	public final static String PROC_NAME_CARD_ORG_STATISTICS = "422";
	public final static String PROC_NAME_MARB_RBS_ACCTDOWN = "423";
	public final static String PROC_NAME_MARB_RBS_CARDDOWN = "424";
	public final static String PROC_NAME_MINB_GL_INTERFACE = "425";
	public final static String PROC_NAME_MINB_PAYM_INTERFACE = "426";
	public final static String PROC_NAME_COS_BPR_POS_OWS = "427";
	public final static String PROC_NAME_COS_BPR_ATM = "428";
	public final static String PROC_NAME_COS_BPR_STB = "429";
	public final static String PROC_NAME_END_OF_DAY_GL = "430";
	public final static String PROC_NAME_CZSB_EIGER_DAILY_OUT = "431";
	public final static String PROC_NAME_CZSB_EIGER_MONTH_OUT = "432";
	public final static String PROC_NAME_CARD_FEES_PROJECTION = "433";
	public final static String PROC_NAME_BSC_TRANSMASTER_INW = "434";
	public final static String PROC_NAME_MARB_BALANCE_DOWNL = "435";
	public final static String PROC_NAME_BSC_MONTHLY_CARD_BAL = "436";
	public final static String PROC_NAME_HB_PAYM_INW = "437";
	public final static String PROC_NAME_EP_FX_RATES_IN = "438";
	public final static String PROC_NAME_MC_FX_RATES_IN = "439";
	public final static String PROC_NAME_BSC_CARD_APPL_FILE = "440";
	public final static String PROC_NAME_BSC_CARD_APPL_RESP = "441";
	public final static String PROC_NAME_BSC_CARD_DATA_SYNC = "442";
	public final static String PROC_NAME_BSC_DAILY_BAL_UPLOAD = "443";
	public final static String PROC_NAME_SYS_CONFIG = "444";
	public final static String PROC_NAME_RERUN_STATEMENT = "445";
	public final static String PROC_NAME_HSBC_ISS_STMT_BY_FIL = "446";
	public final static String PROC_NAME_HSBC_MRC_STMT_BY_FIL = "447";
	public final static String PROC_NAME_CSB_STATMENT_PRIVATE = "448";
	public final static String PROC_NAME_CSB_STATMENT_COMMERC = "449";
	public final static String PROC_NAME_CSB_STATMNT_COMMPRIV = "450";
	public final static String PROC_NAME_CZSB_CIM_INTERFACE = "451";
	public final static String PROC_NAME_CSB_LOY_CARD_FEES = "452";
	public final static String PROC_NAME_CSB_LOY_CLIENT_ADDR = "453";
	public final static String PROC_NAME_CSB_LOYALTY_BAL_LOAD = "454";
	public final static String PROC_NAME_POS_NT_LOADER = "455";
	public final static String PROC_NAME_MOBCOM_APPR_REJC_OUT = "456";
	public final static String PROC_NAME_ATM_BATCH = "457";
	public final static String PROC_NAME_POS_BATCH = "458";
	public final static String PROC_NAME_HSBC_DIR_DEB_PAY_OUT = "459";
	public final static String PROC_NAME_GTP_DDI = "460";
	public final static String PROC_NAME_GTP_ADDACS = "461";
	public final static String PROC_NAME_GTP_DDP = "462";
	public final static String PROC_NAME_SUSPENSE_INTRA_PAYM = "463";
	public final static String PROC_NAME_GTP_ARUDD_FILE = "464";
	public final static String PROC_NAME_VISA_NMAS_OUTWARD = "465";
	public final static String PROC_NAME_GTP_ARUDD_MANUAL = "466";
	public final static String PROC_NAME_APPLICATION_PROC = "467";
	public final static String PROC_NAME_DELTA_PBF_OUT = "468";
	public final static String PROC_NAME_DELTA_CARD_HLDR_OUT = "469";
	public final static String PROC_NAME_DELTA_ACQ_IN = "470";
	public final static String PROC_NAME_DELTA_ISS_IN = "471";
	public final static String PROC_NAME_BATCH_PROC_BATCH_NUM = "472";
	public final static String PROC_NAME_SPLIT_MERC_BCH_BY_BR = "473";
	public final static String PROC_NAME_XPON_TRAN_INTERFACE = "474";
	public final static String PROC_NAME_CZSB_EIGER_OUTWARD = "475";
	public final static String PROC_NAME_BSF_LEVY_OUTWARD = "477";
	public final static String PROC_NAME_XPON_DIR_DEBIT_INW = "478";
	public final static String PROC_NAME_BSF_LEVY_INWARD = "479";
	public final static String PROC_NAME_BSF_WINGS_OUTWARD = "480";
	public final static String PROC_NAME_KDB_CTLF_INW = "481";
	public final static String PROC_NAME_KDB_PTLF_INW = "482";
	public final static String PROC_NAME_KDB_RBS_BALANCES = "483";
	public final static String PROC_NAME_KDB_CARD_STATUS_OUT = "484";
	public final static String PROC_NAME_KDB_TRAN_DOWNLOAD = "485";
	public final static String PROC_NAME_ICC_PAYM_LOAD = "499";
	public final static String PROC_NAME_INT_RECLASS_PRC = "500";
	public final static String PROC_NAME_INW_DINACARD = "501";
	public final static String PROC_NAME_OUT_DINACARD = "502";
	public final static String PROC_NAME_BSF_DMS_OUT = "503";
	public final static String PROC_NAME_POSTBK_GEN_ACCT_STAT = "504";
	public final static String PROC_NAME_POSTBK_APPL_CLNT_LD = "505";
	public final static String PROC_NAME_POSTBK_APPL_ACCT_LD = "506";
	public final static String PROC_NAME_ON2_INWARD = "508";
	public final static String PROC_NAME_BMS_CAMS_OUTWARD = "509";
	public final static String PROC_NAME_ACH_INW_RESPONSE = "510";
	public final static String PROC_NAME_SETTLEMENT_REFRESH = "511";
	public final static String PROC_NAME_BSF_INCOMING_PYMTS = "512";
	public final static String PROC_NAME_VIECARD_APPL_LOAD = "513";
	public final static String PROC_NAME_VIEWCRD_MULTIFRMT_FL = "514";
	public final static String PROC_NAME_CANCELLATIONS = "515";
	public final static String PROC_NAME_RETAIL_BAL_REQUEST = "516";
	public final static String PROC_NAME_BSF_VISA_ELECTRON = "517";
	public final static String PROC_NAME_RIYAD_APPR_LNK_ACCTS = "518";
	public final static String PROC_NAME_RIYAD_ADDRESS_LOAD = "519";
	public final static String PROC_NAME_RIYAD_APPL_LOAD = "520";
	public final static String PROC_NAME_RIYAD_APPL_LOAD_P2 = "521";
	public final static String PROC_NAME_BSF_CR_DESK_APPL_LD = "522";
	public final static String PROC_NAME_BSF_IVR_BCH_INTERFAC = "523";
	public final static String PROC_NAME_BSF_CR_DSK_APL_LD_IN = "524";
	public final static String PROC_NAME_BSF_VCARD_VDING_MACH = "525";
	public final static String PROC_NAME_RIYAD_CLNT_SEGMNT_LD = "526";
	public final static String PROC_NAME_RECONCILIATION_LOAD = "527";
	public final static String PROC_NAME_RECON_MATCHING = "528";
	public final static String PROC_NAME_BATCH_INSTALL_ACQ = "529";
	public final static String PROC_NAME_BBS_INC_PYMT_FILE = "530";
	public final static String PROC_NAME_VISA_BIN_MEMBER = "531";
	public final static String PROC_NAME_BBS_OUT_PYMT_FILE = "532";
	public final static String PROC_NAME_BBS_DIRECT_RMIT_FILE = "533";
	public final static String PROC_NAME_BBS_INC_AG_PYTREQ = "534";
	public final static String PROC_NAME_GFF2_PAYM_INTERFACE = "535";
	public final static String PROC_NAME_BBS_GL_VISMA = "536";
	public final static String PROC_NAME_BBS_TELLER_MTHLY_RPT = "537";
	public final static String PROC_NAME_EPI_TELLER_INW = "538";
	public final static String PROC_NAME_BBS_NORW_TAX_AUTH = "539";
	public final static String PROC_NAME_HBF_DIWER = "540";
	public final static String PROC_NAME_KBS_CIM_INTERFACE = "540";
	public final static String PROC_NAME_KBS_CARDS_OUTW = "541";
	public final static String PROC_NAME_KBS_RBS_BALANCES = "542";
	public final static String PROC_NAME_KBS_GL_OUTW = "543";
	public final static String PROC_NAME_KBS_CARD_BALANCE_OUT = "544";
	public final static String PROC_NAME_SAMA_POS02 = "561";
	public final static String PROC_NAME_SAMA_POS12 = "562";
	public final static String PROC_NAME_SAMA_POS35 = "563";
	public final static String PROC_NAME_SAMA_STAT6 = "564";
	public final static String PROC_NAME_SAMA_STAT7 = "565";
	public final static String PROC_NAME_SAMA_GCC_ISS = "566";
	public final static String PROC_NAME_SAMA_GCC_ACQ = "567";
	public final static String PROC_NAME_SHCLOG = "568";
	public final static String PROC_NAME_RB_ISS = "569";
	public final static String PROC_NAME_RB_ACQ = "570";
	public final static String PROC_NAME_SSC_INC_PAYM = "571";
	public final static String PROC_NAME_SSC_PAY_REQ_PRTNRS = "572";
	public final static String PROC_NAME_SSC_PAY_UNION = "573";
	public final static String PROC_NAME_SSC_UNION_BAL = "574";
	public final static String PROC_NAME_SSC_PARTNER_BAL = "575";
	public final static String PROC_NAME_SSC_UNION_LOAD = "576";
	public final static String PROC_NAME_SSC_MEMBER_LOAD = "577";
	public final static String PROC_NAME_PL_SQL_PROC_START = "900";
	public final static String PROC_NAME_PL_SQL_PROC_END = "950";
	public final static int CONV_TRANS_TO_SETTL = 1;
	public final static int CONV_SETTL_TO_TRANS = -1;
	public final static int CONV_SETTL_TO_LOCAL = 2;
	public final static int CONV_LOCAL_TO_SETTL = -2;
	public final static int CONV_LOCAL_TO_ACCT = 3;
	public final static int CONV_ACCT_TO_LOCAL = -3;
	public final static int CONV_TRANS_TO_ACCT = 4;
	public final static int CONV_ACCT_TO_TRANS = -4;
	public final static int RATE_TYPE_NA = 0;
	public final static int RATE_TYPE_MIDDLE = 1;
	public final static int RATE_TYPE_PURCHASE = 2;
	public final static int RATE_TYPE_SALES = 3;
	public final static String TYPE_PRIVATE = "001";
	public final static String TYPE_COMMERCIAL = "002";
	public final static String TYPE_MERCHANT = "003";
	public final static String TYPE_INSTITUTION = "004";
	public final static String TYPE_ALL = "005";
	public final static String CLT_TYPE_PRIVATE = "001";
	public final static String CLT_TYPE_CORP_MERCH = "002";
	public final static String CLT_TYPE_INSTITUTION = "003";
	public final static String CLT_TYPE_GOV_AGENCY = "004";
	public final static String CLT_TYPE_GOV_OWNED = "005";
	public final static String CLT_TYPE_COMMERCIAL = "006";
	public final static String ACCT_TYPE_GL_DESTINATION = "100";
	public final static String ACCT_TYPE_GL_SOURCE = "101";
	public final static String ACCT_TYPE_GL_CHANNEL = "102";
	public final static String ACCT_TYPE_COLLECTION = "061";
	public final static String ACCT_TYPE_BONUS_CHECK = "143";
	public final static String ACCT_TYPE_GROUP = "002";
	public final static String ACCT_TYPE_BASE = "003";
	public final static String LEVL_NA = "000";
	public final static String LEVL_MEMBER = "001";
	public final static String LEVL_GROUP = "002";
	public final static String LEVL_SUB_GROUP = "003";
	public final static String LEVL_MAIN_ACCOUNT = "001";
	public final static String LEVL_SUB_ACCOUNT = "002";
	public final static String POST_METHOD_NET = "001";
	public final static String POST_METHOD_GROSS_CHG = "002";
	public final static String BUS_TYP_ISSUING = "001";
	public final static String BUS_TYP_ACQUIRING = "002";
	public final static String BUS_TYP_CLEARING = "003";
	public final static String BUS_TYP_SUSPENSE = "004";
	public final static String BUS_TYP_GL = "005";
	public final static String SERV_ISSUING = "001";
	public final static String SERV_ACQUIRING = "002";
	public final static String SERV_CLEARING = "003";
	public final static String SERV_SUSPENSE = "004";
	public final static String SERV_INSURANCE = "005";
	public final static String SERV_TELEPHONY = "006";
	public final static String SERV_ACQUIRING_OTHER = "007";
	public final static String SERV_LOAN_INSURANCE = "008";
	public final static String SERV_FRAUD_INSURANCE = "009";
	public final static String SERV_REPORTING_ELECTR = "010";
	public final static String SERV_REPORTING_PAPER = "011";
	public final static String SERV_ALL = "999";
	public final static String SERVICE_INFOSPAN = "900";
	public final static String SERVICE_MIS_INFOSPAN = "909";
	public final static String SERVICE_ALL = "999";
	public final static String SVC_CONTRACT_SUSPENSE = "990";
	public final static String SERV_AVAIL_NA = "000";
	public final static String SERV_AVAIL_MANDATORY = "001";
	public final static String SERV_AVAIL_OPTIONAL = "002";
	public final static String SERV_BENE_MEMBER = "001";
	public final static String SERV_BENE_BILL_LEVEL = "002";
	public final static String SERV_BENE_NA = "003";
	public final static String SERV_BENE_GRP_ALL = "004";
	public final static String SERV_BENE_GRP_OPTION = "005";
	public final static String CYCLE_TYP_MONTHLY = "001";
	public final static String CYCLE_TYP_HALF_MONTHLY = "002";
	public final static String CYCLE_TYP_DAILY = "003";
	public final static String CYCLE_TYP_WEEKLY = "004";
	public final static String CYCLE_TYP_HALF_MONTHLY_DOW = "005";
	public final static String CYCLE_TYP_MONTHLY_DOW = "006";
	public final static String CYCLE_TYP_SERVICE_RELATED = "007";
	public final static String CYCLE_TYP_MONTHLY_PBD = "008";
	public final static String CYCLE_TYP_PER_N_DAYS = "009";
	public final static String CYCLE_TYP_N_BUSINESS_DAY = "010";
	public final static String CYCLE_TYP_USER_DEFINED = "900";
	public final static String CYCLE_TYP_NUMBER_OF_MONTHS = "100";
	public final static String CYCLE_TYP_FIXED_MM_YY = "101";
	public final static String CYCLE_TYP_LOAN_EXPIRY = "110";
	public final static String CYCLE_TYP_CAPT_PER_N_MTHS = "150";
	public final static String CYCLE_TYP_YEARLY_MMDD = "151";
	public final static String CHRG_FEE = "001";
	public final static String CHRG_COMMISSION = "002";
	public final static String CHRG_BONUS = "003";
	public final static String CHRG_REPORT = "004";
	public final static String CHRG_DISCOUNT = "005";
	public final static String CHRG_ASSESSMENT = "010";
	public final static String CHRG_PER_TRAN = "011";
	public final static String CHRG_DELTA_SURCHARGE = "012";
	public final static String CHRG_DCC_ACQ_COMMIS = "013";
	public final static String CHRG_MERCH_COMMISSION = "014";
	public final static String CHRG_DELTA = "015";
	public final static String CHRG_NOTIONAL_MPL = "016";
	public final static String CHRG_CLR_FEE_IN = "900";
	public final static String CHRG_CLR_FEE_OUT = "901";
	public final static String CHRG_CLR_FEE_IN_OUT = "902";
	public final static String CHRG_NA = "999";
	public final static String INTEREST_TIER_DEFAULT = "000";
	public final static String INTR_FORMULA_365_31 = "001";
	public final static String INTR_FORMULA_360_30_DAILY = "002";
	public final static String INTR_FORMULA_360_30_MONTHLY = "003";
	public final static String INTR_FORMULA_COMPOUND_n_365 = "004";
	public final static String INTR_FORMULA_HBF_BASE = "100";
	public final static String INTR_FORMULA_HBF_CAMPAIGN = "101";
	public final static String PROC_PHASE1 = "001";
	public final static String PROC_PHASE2 = "002";
	public final static String PROC_PHASE3 = "003";
	public final static String PROC_PHASE4 = "004";
	public final static String PROC_POSTING = "005";
	public final static String PROC_TRN_CHARGES = "006";
	public final static String PROC_POSTING_SRC = "007";
	public final static String PROC_ACCT_SETTL = "008";
	public final static String PROC_EXCEPTIONS = "009";
	public final static String PROC_END_OF_CYCLE = "010";
	public final static String PROC_RECLASSIFY_PH_1 = "011";
	public final static String PROC_RECLASSIFY_PH_2 = "012";
	public final static String PROC_POST_SETTL_INC = "013";
	public final static String PROC_POST_SETTL_OUT = "014";
	public final static String PROC_GL_OUTPUT = "015";
	public final static String PROC_CLIENT_FEES = "016";
	public final static String PROC_AUTHORISATIONS = "017";
	public final static String PROC_BIN_TABLE_LOAD = "018";
	public final static String PROC_POST_SETTL_FEES = "019";
	public final static String PROC_POST_SETTL_VSS = "020";
	public final static String PROC_POST_SETTL_INC_DPR = "021";
	public final static String PROC_COPY_TRAN = "022";
	public final static String PROC_CARD_PRODUCTION_NEW = "023";
	public final static String PROC_GL = "024";
	public final static String PROC_INTEREST_GENERATION = "025";
	public final static String PROC_MERCH_COMMIS_ADJUST = "026";
	public final static String PROC_REMINDER_PROCESSING = "027";
	public final static String PROC_RECLASSIFY_PH_3 = "028";
	public final static String PROC_RECLASSIFY_PH_4 = "029";
	public final static String PROC_DCC_CONVERSION_VISA = "030";
	public final static String PROC_DCC_CONVERSION_ECMC = "031";
	public final static String PROC_POST_SETTL_IPM = "032";
	public final static String PROC_GL_SOURCE = "040";
	public final static String PROC_GL_DEST_ONUS = "041";
	public final static String PROC_GL_DEST_NOT_ONUS = "042";
	public final static String PROC_BATCH_INTERFACE = "900";
	public final static String PROC_FORM = "901";
	public final static String PROC_PARENT = "902";
	public final static String PROC_CARD_PRODUCTION = "014";
	public final static boolean INWARD_CHARGE = true;
	public final static boolean OUTWARD_CHARGE = false;
	public final static String CURR_TYPE_ACCOUNT = "001";
	public final static String CURR_TYPE_LOCAL = "002";
	public final static String CURR_TYPE_SETTLEMENT = "003";
	public final static String CURR_TYPE_TRANS = "004";
	public final static String AMOUNT_GROSS = "001";
	public final static String AMOUNT_INW_CHARGE = "002";
	public final static String AMOUNT_INW_NET = "003";
	public final static String AMOUNT_OUT_CHARGE = "004";
	public final static String AMOUNT_OUT_NET = "005";
	public final static String AMOUNT_INW_CHARGE_DR = "011";
	public final static String AMOUNT_INW_CHARGE_CR = "012";
	public final static String AMOUNT_OUT_CHARGE_DR = "013";
	public final static String AMOUNT_OUT_CHARGE_CR = "014";
	public final static String CURR_AUD = "036";
	public final static String CURR_CAD = "124";
	public final static String CURR_DEM = "280";
	public final static String CURR_HKD = "344";
	public final static String CURR_JAPAN = "392";
	public final static String CURR_JORDAN = "400";
	public final static String CURR_SAR = "682";
	public final static String CURR_SWEDEN = "752";
	public final static String CURR_RUSSIA = "810";
	public final static String CURR_GBP = "826";
	public final static String CURR_USD = "840";
	public final static String CURR_BOSNIA = "977";
	public final static String CURR_EURO = "978";
	public final static String CURR_UKRAINE = "980";
	public final static String CURR_BONUS_POINTS = "996";
	public final static String CURR_EUROZONE = "997";
	public final static String CURR_NA = "998";
	public final static String CURR_ALL = "999";
	public final static String VISA_BASE_II = "003";
	public final static String CLEARING_SERVICE = "003";
	public final static String INC_CEKAB = "019";
	public final static String FILE_LOG_NO = "001";
	public final static String PRC_IN_PROCESS = "001";
	public final static String PRC_COMPLETED = "002";
	public final static String PRC_ERROR = "003";
	public final static String TRAN_SLIP_NO = "019";
	public final static String ACQURING_CLNT_TYP = "002";
	public final static String ACQURING_SERVICE = "002";
	public final static String INTERCHANGE_FEES = "001";
	public final static String MERCHANT_COMMISSION = "002";
	public final static String FILE_NO = "001";
	public final static String INC_ECCF_20 = "001";
	public final static String INTERNAL_ONUS = "018";
	public final static String SETT_GRP_CTRL = "007";
	public final static String DPR_GRP_CTRL = "006";
	public final static String SETT_TRAN = "005";
	public final static String SETT_GRP_INF = "019";
	public final static String MASTERCARD = "004";
	public final static String EUROPAY = "003";
	public final static String VISA = "002";
	public final static String CRD_EC_MC = "001";
	public final static String CRD_CIRRUS = "002";
	public final static String CRD_EDC_MAESTRO = "003";
	public final static String CRD_EUROCHEQUE = "004";
	public final static String CRD_VISA = "005";
	public final static String CRD_VISA_PLUS = "006";
	public final static String CRD_ECHA_CIRRUS = "008";
	public final static String CRD_VISA_ELECTRON = "007";
	public final static String CRD_CARTE_BANCAIRE = "009";
	public final static String CRD_MASTERCARD = "010";
	public final static String CRD_MAESTRO = "011";
	public final static String CRD_EDC = "012";
	public final static String CRD_AMEX = "015";
	public final static String CRD_DINERS = "016";
	public final static String CRD_VISA_BUSINESS = "028";
	public final static String CRD_VISA_CORPORATE = "029";
	public final static String CRD_VISA_PURCHASING = "030";
	public final static String CRD_VISA_TRAVEL_MONEY = "031";
	public final static String CRD_MC_CORP_BUSS = "032";
	public final static String CRD_MC_CORP_PURCHASE = "033";
	public final static String CRD_MC_CORP_FLEET = "034";
	public final static String CRD_EDCN = "036";
	public final static String CRD_CIRRUS_MAESTRO = "037";
	public final static String CRD_EC_COMMERCIAL = "050";
	public final static String CRD_VISA_DELTA = "051";
	public final static String CRD_VISA_COMMERCE = "052";
	public final static String CRD_MC_EURO_CORPORATE = "060";
	public final static String CRD_MC_WORLD_SIGNIA = "061";
	public final static String CRD_VISA_INFINITY = "062";
	public final static String CRD_MC_ELECTRONIC_CRD = "063";
	public final static String CRD_EP_CLIP = "064";
	public final static String CRD_JCB = "065";
	public final static String CRD_VISA_SIGNATURE = "066";
	public final static String CRD_VISA_GOLD = "067";
	public final static String CRD_VISA_PLATINUM = "068";
	public final static String CRD_MC_ELEC_BUSINESS = "069";
	public final static String CRD_DINA_CARD = "102";
	public final static String CRD_VUB_CREDIT_CARD = "103";
	public final static String CRD_AUSTRALIAN_BC = "104";
	public final static String CRD_AUSTRALIAN_DC = "105";
	public final static String ORG_NA = "000";
	public final static String ORG_EUROPAY = "001";
	public final static String ORG_MASTERCARD = "002";
	public final static String ORG_VISA = "003";
	public final static String ORG_AMEX = "004";
	public final static String ORG_PROPRIETARY = "005";
	public final static String ORG_DINERS = "006";
	public final static String ORG_MDS = "020";
	public final static String ORG_VISA_SMS = "021";
	public final static String ORG_INTRABANK = "100";
	public final static String ORG_BOV = "101";
	public final static String ORG_AUSTRALIA_DEBIT = "102";
	public final static String ORG_AUSTRALIA_CREDIT = "103";
	public final static String ORG_SHETAB = "110";
	public final static String ORG_ONUS = "200";
	public final static String ORG_ALL = "999";
	public final static String ACTION_PENDING = "000";
	public final static String VISA_SETTL_FLAG_INT = "0";
	public final static String VISA_SETTL_FLAG_CLEAR_ONLY = "3";
	public final static String VISA_SETTL_FLAG_DOM = "8";
	public final static String VISA_SETTL_FLAG_DEFAULT = "9";
	public final static String VISA_SETTL_FLAG_ONUS_CSB = "9";
	public final static String VISA_SETTL_FLAG_BW3_INT = "X";
	public final static String VISA_SETTL_FLAG_BW3_DOM = "Y";
	public final static String VISA_SETTL_FLAG_BW3_INTERNAL = "Z";
	public final static String VISA_SETTL_FLAG_ECCF_INT = "E";
	public final static String VISA_SETTL_FLAG_ECCF_DOM = "F";
	public final static String VISA_SETTL_FLAG_ECCF_ONUS = "G";
	public final static String VISA_SETTL_FLAG_ONUS_CSB_PROP = "R";
	public final static String VISA_SETTL_FLAG_DINACARD = "D";
	public final static String COUNTRY_AUSTRALIA = "036";
	public final static String COUNTRY_AUSTRIA = "040";
	public final static String COUNTRY_BELGIUM = "056";
	public final static String COUNTRY_BOSNIA = "070";
	public final static String COUNTRY_CANADA = "124";
	public final static String COUNTRY_CYPRUS = "196";
	public final static String COUNTRY_DENMARK = "208";
	public final static String COUNTRY_FINLAND = "246";
	public final static String COUNTRY_CZECH_REPUBLIC = "203";
	public final static String COUNTRY_ESTONIA = "233";
	public final static String COUNTRY_GERMANY = "280";
	public final static String COUNTRY_GIBRALTAR = "292";
	public final static String COUNTRY_GREECE = "300";
	public final static String COUNTRY_HONG_KONG = "344";
	public final static String COUNTRY_HUNGARY = "348";
	public final static String COUNTRY_IRELAND = "372";
	public final static String COUNTRY_JORDAN = "400";
	public final static String COUNTRY_LATVIA = "428";
	public final static String COUNTRY_LITHUANIA = "440";
	public final static String COUNTRY_NETHERLANDS = "528";
	public final static String COUNTRY_NEW_ZEALAND = "554";
	public final static String COUNTRY_NORWAY = "578";
	public final static String COUNTRY_POLAND = "616";
	public final static String COUNTRY_PORTUGAL = "620";
	public final static String COUNTRY_RUSSIA = "643";
	public final static String COUNTRY_SAUDI_ARABIA = "682";
	public final static String COUNTRY_SLOVAKIA = "703";
	public final static String COUNTRY_SLOVENIA = "705";
	public final static String COUNTRY_SPAIN = "724";
	public final static String COUNTRY_SWEDEN = "752";
	public final static String COUNTRY_SWITZERLAND = "756";
	public final static String COUNTRY_TURKEY = "792";
	public final static String COUNTRY_UKRAINE = "804";
	public final static String COUNTRY_UNITED_KINGDOM = "826";
	public final static String COUNTRY_UNITED_STATES = "840";
	public final static String COUNTRY_NA = "998";
	public final static String COUNTRY_YUGOSLAVIA = "891";
	public final static String COUNTRY_ALL = "999";
	public final static String FX_CATEG_NA = "000";
	public final static String FX_CATEG_OWN = "001";
	public final static String FX_CATEG_VISA = "002";
	public final static String FX_CATEG_EUROPAY = "003";
	public final static String FX_CATEG_MASTER = "004";
	public final static String FX_CATEG_ENECUR = "006";
	public final static String FX_CATEG_EUROPAY_EURO = "800";
	public final static String FX_CATEG_EUROPAY_EURO_BUY = "801";
	public final static String FX_CATEG_EUROPAY_USD = "802";
	public final static String FX_CATEG_EURO = "900";
	public final static String FX_CATEG_PEGGED = "901";
	public final static String FX_CATEG_CARD_SCHEME = "910";
	public final static String TEST_MODE = "Y";
	public final static String PRODUCTION_MODE = "N";
	public final static String INST_BASE_SYSTEM = "0000";
	public final static String INST_PROCARD = "0001";
	public final static String INST_SSC_XPON = "0002";
	public final static String INST_HBF = "0003";
	public final static String INST_CZECHSB = "0004";
	public final static String INST_KBS_MK = "0005";
	public final static String INST_VARAZDINSKA = "0006";
	public final static String INST_LOPLUS = "0007";
	public final static String INST_JONB = "0008";
	public final static String INST_PROCARD_ISSUER = "0010";
	public final static String INST_LCARD_CENTRE = "0011";
	public final static String INST_LCRC = "0012";
	public final static String INST_LUFT = "0013";
	public final static String INST_PERSIAN_ECOMMERCE = "0014";
	public final static String INST_BARCLAYS_MS = "0017";
	public final static String INST_KDB_HUNGARY = "0018";
	public final static String INST_MARKET_BANK = "0019";
	public final static String INST_MAGYAR_KB = "0021";
	public final static String INST_ING = "0022";
	public final static String INST_GSS = "0023";
	public final static String INST_ICC = "0024";
	public final static String INST_ARAB_LAND_BANK = "0025";
	public final static String INST_OMNIPAY = "0026";
	public final static String INST_AXXESS_BAHAMAS = "0027";
	public final static String INST_HSBC_MALTA = "0028";
	public final static String INST_SLOVAKIA_ACS = "0029";
	public final static String INST_NCR_EGYPT = "0030";
	public final static String INST_GTP_UK = "0031";
	public final static String INST_YUGOSLAVIA_MTS = "0032";
	public final static String INST_ALLROUND = "0033";
	public final static String INST_DELTA_YU = "0034";
	public final static String INST_EALB_EGYPT = "0035";
	public final static String INST_JORDAN_PAYM_CNTR = "0036";
	public final static String INST_CFT = "0037";
	public final static String INST_MINB_MOSCOW = "0039";
	public final static String INST_EDBE_EGYPT = "0040";
	public final static String INST_BSC_LATVIA = "0041";
	public final static String INST_XSBV = "0043";
	public final static String INST_BSF_SAUDI = "0044";
	public final static String INST_RIYADH_SAUDI = "0045";
	public final static int GI_PDATE = 1;
	public final static int GI_SDATE = 2;
	public final static int GI_USER_ID = 3;
	public final static int GI_USER_NAME = 4;
	public final static int GI_USER_SHORT_NAME = 5;
	public final static int GI_USER_DEPT = 6;
	public final static int GI_USER_BRANCH = 7;
	public final static int GI_SERVER_DRIVE = 8;
	public final static int GI_PATH_PROGRAM = 9;
	public final static int GI_PATH_IMAGE = 10;
	public final static int GI_PATH_LOCAL_DATA = 11;
	public final static int GI_PATH_SERVER_DATA = 12;
	public final static int GI_PATH_LOCAL = 13;
	public final static int GI_PATH_LOGS = 14;
	public final static int GI_PATH_EXCHANGE = 15;
	public final static int GI_PATH_MISC = 16;
	public final static int GI_PATH_STATEMENT = 18;
	public final static int GI_PATH_SEQUENTIAL = 34;
	public final static int GI_PATH_REPORT = 36;
	public final static int GI_LOCAL_CONFIG = 19;
	public final static int GI_SERVER_CONFIG = 20;
	public final static int GI_LIKE_CHAR = 21;
	public final static int GI_THOUSANDS_SEPARATOR = 22;
	public final static int GI_DECIMAL_POINT = 23;
	public final static int GI_EXPONENT = 24;
	public final static int GI_LICENSEE = 25;
	public final static int GI_STATION_NUMBER = 27;
	public final static int GI_CURRENT_SYSTEM = 28;
	public final static int GI_LANGUAGE = 29;
	public final static int GI_ISSUER_ID = 30;
	public final static int GI_ACQUIRER_ID = 31;
	public final static int GI_LOCAL_CURRENCY = 32;
	public final static int GI_USER_GROUP_ID = 33;
	public final static int GI_TRAN_TYPE = 35;
	public final static int GI_LOCAL_CURRENCY_ISO = 37;
	public final static int GI_JUL_PDATE_4 = 38;
	public final static int GI_JUL_SDATE_4 = 39;
	public final static int GI_JUL_PDATE_5 = 40;
	public final static int GI_JUL_SDATE_5 = 41;
	public final static String MSG_TYPE_ERROR = "Error";
	public final static String MSG_TYPE_DBERROR = "DBError";
	public final static String MSG_TYPE_INFO = "Info";
	public final static String MSG_TYPE_WARNING = "Warning";
	public final static int MSG_FILEONLY = -1;
	public final static int MSG_OK = 0;
	public final static int MSG_YESNO = 1;
	public final static int MSG_YESNOCANCEL = 2;
	public final static int MSG_RET_YES = 6;
	public final static int MSG_RET_NO = 7;
	public final static int MSG_RET_CANCEL = 2;
	public final static int CASE_SENSITIVE = 0;
	public final static int CASE_INSENSITIVE = 1;
	public final static int FMT_FLD_EDIT = -1;
	public final static int FMT_FLD_DISP = 0;
	public final static int FMT_IPM_AMT_DEFAULT = 1;
	public final static int FMT_IPM_AMT_ADDENDUM = 2;
	public final static int VALID_MDE_ALL = 1;
	public final static int VALID_MDE_LJ = 2;
	public final static int VALID_MDE_NO_EMPTY = 3;
	public final static int VALID_MDE_NO_LOW_VALS = 4;
	public final static int VALID_MDE_NO_HIGH_VALS = 5;
	public final static int FMT_NUM_LZ = 1;
	public final static int FMT_NUM_DEC_PT = 2;
	public final static int FMT_NUM_DEC_PT_LS = 3;
	public final static int FMT_NUM_LZ_VAREXP = 4;
	public final static int FMT_NUM_LS = 5;
	public final static int FMT_SGN_MODE_NA = 0;
	public final static int FMT_SGN_MODE_LEAD = 1;
	public final static int FMT_SGN_MODE_TRAIL = 2;
	public final static int FMT_SGN_MODE_ISO = 3;
	public final static int FMT_SGN_MODE_INET = 4;
	public final static int FMT_SGN_MODE_NO_LEAD = 5;
	public final static String FMT_SGN_SYM_DEFAULT = "-+";
	public final static String FMT_SGN_SYM_DR_CR = "DC";
	public final static String FMT_SGN_SYM_MINUS = "-";
	public final static String FMT_SGN_SYM_MINUS_SP = "-";
	public final static int FMT_TIME_LONG = 0;
	public final static int FMT_TIME_SHORT = 1;
	public final static int CMD_LINE_SIGN_ON = 1;
	public final static int CMD_LINE_USER_ID = 2;
	public final static int CMD_LINE_PARAM = 3;
	public final static int CMD_LINE_INSTITUTION = 4;
	public final static int CMD_LINE_LANGUAGE = 5;
	public final static int CMD_LINE_CURRENT_SYSTEM = 6;
	public final static int CMD_LINE_USER_DB_NAME = 7;
	public final static int CMD_LINE_USER_DB_PWRD = 8;
	public final static int CMD_LINE_SCHED_PROC_NAME = 9;
	public final static int CMD_LINE_SCHED_SELECT_ALL = 10;
	public final static int CMD_LINE_SCHED_PARAM_LIST = 11;
	public final static int CMD_LINE_BPI_PROC_NUMBER = 12;
	public final static long DB_DEBUG_CONNECT_DISCONNECT = 1;
	public final static long DB_DEBUG_OPEN_CLOSE = 2;
	public final static long DB_DEBUG_NAVIGATE = 4;
	public final static long DB_DEBUG_TRANSACTIONS = 8;
	public final static long DB_DEBUG_GET_FIELD_VALUE = 16;
	public final static long DB_DEBUG_SET_FIELD_VALUE = 32;
	public final static long DB_DEBUG_TABLE_INFO = 64;
	public final static long DB_DEBUG_UPDATE_INSERT = 128;
	public final static long DB_DEBUG_SQL_FUNCTIONS = 256;
	public final static long DB_DEBUG_SET_RECORD = 512;
	public final static long DB_DEBUG_GET_FIELD_INFO = 1024;
	public final static long DB_DEBUG_MEMO_FIELD = 2048;
	public final static int TRAN_NONE = 0;
	public final static int TRAN_OPEN = 1;
	public final static int TRAN_PENDING = 2;
	public final static int DIFF_DAYS = 0;
	public final static int DIFF_WEEKS = 1;
	public final static int DIFF_MONTHS = 2;
	public final static int DIFF_YEARS = 3;
	public final static int BUS_DAY_COUNT = 4;
	public final static int BUS_DAY_IGNORE = 0;
	public final static int BUS_DAY_PREV = 1;
	public final static int BUS_DAY_NEXT = 2;
	public final static int DB_MODE_INSERT = 0;
	public final static int DB_MODE_UPDATE = 1;
	public final static String ACCOUNT_MAIN = "001";
	public final static String ACCOUNT_SUB = "002";
	public final static String RECORD_PRIVATE = "001";
	public final static String RECORD_COMMERCIAL = "002";
	public final static String RECORD_MERCHANT = "003";
	public final static String RECORD_INSTITUTION = "004";
	public final static String RECORD_ALL = "005";
	public final static String RECORD_PRIVATE_EXT = "051";
	public final static String RECORD_COMMERC_EXT = "052";
	public final static String RECORD_MERCHANT_EXT = "053";
	public final static String RECORD_MERCH_HIER = "073";
	public final static String RECORD_BILL_LEVEL = "080";
	public final static String RECORD_TEMP_CARDS = "090";
	public final static String RECORD_VB_BASE = "100";
	public final static String RECORD_VB_CURRENT = "101";
	public final static String ADR_CATG_STANDARD = "001";
	public final static String ADR_CATG_TRADE = "002";
	public final static String ADR_CATG_LEGAL = "003";
	public final static String ADR_CATG_VISITING = "004";
	public final static String ADR_CATG_STATEMENT = "006";
	public final static String ADR_CATG_ALTERNATE = "007";
	public final static String ADR_CATG_CARD = "008";
	public final static String ADR_CATG_PIN = "009";
	public final static String ADR_CATG_ERROR = "010";
	public final static String ADR_CATG_POSTAL = "011";
	public final static String ADR_CATG_WORK = "012";
	public final static String ADR_CATG_STATEMENT_COPY = "013";
	public final static String ADR_CATG_STMNT_COPY_FROM = "080";
	public final static String ADR_CATG_STMNT_COPY_TO = "099";
	public final static String ADR_CATG_DISPUTE = "200";
	public final static String ADR_CATG_HEAD_OFFICE = "201";
	public final static String SVC_TYPE_CREDIT = "001";
	public final static String SVC_TYPE_DEBIT = "002";
	public final static String SVC_TYPE_CHARGE = "003";
	public final static String SVC_TYPE_INS_BASIC = "100";
	public final static String SVC_TYPE_INS_EXTND = "101";
	public final static String SVC_TYPE_ALL = "999";
	public final static String SYNTH_EXT_NOT_APPLIC = "000";
	public final static String SYNTH_EXT_OUT_CHANNEL = "001";
	public final static String SYNTH_EXT_TRAN_TYPE = "002";
	public final static String SYNTH_EXT_INC_CHANNEL = "003";
	public final static String SYNTH_EXT_USER_DEFINED = "004";
	public final static String SYNTH_EXT_INTRA_INST = "005";
	public final static String REPL_METH_INT_FEE_CASH_PURCH = "001";
	public final static String REPL_METH_CASH_PURCH_FEES = "002";
	public final static String TRN_DATA_LOCATION_NA = "000";
	public final static String TRN_DATA_LOCATION_CURRENT = "001";
	public final static String TRN_DATA_LOCATION_HISTORY = "002";
	public final static String TRN_DATA_LOCATION_ARCHIVE = "003";
	public final static String LIMIT_DIST_TYPE_SHARED = "001";
	public final static String LIMIT_DIST_TYPE_ALLOCATED = "002";
	public final static String LIMIT_DIST_TYPE_INCR = "003";
	public final static String LIMIT_DIST_TYPE_GROUP = "004";
	public final static String PIN_REQUIRED_NO = "001";
	public final static String PIN_REQUIRED_NEW = "002";
	public final static String PIN_REQUIRED_SAME = "003";
	public final static String PIN_REQUIRED_ONLINE = "004";
	public final static String PIN_REQUIRED_ONLINE_YES = "005";
	public final static int TRN_DATA_CURRENT_MONTHS = 1;
	public final static int TRN_DATA_HISTORY_MONTHS = 6;
	public final static long NUMBER_TRANSACTIONS_TO_COMMIT = 2000;
	public final static String ISSUER_BATCH = "001";
	public final static String ACQUIRER_BATCH = "002";
	public final static String OUTPUT_BATCH = "003";
	public final static String TRANSFER_BATCH = "004";
	public final static String APPL_PROC_MISC_BATCH = "005";
	public final static String APPL_PROC_ACQ_BATCH = "006";
	public final static String AUTH_BATCH = "007";
	public final static String PAYMENT_BATCH = "008";
	public final static String ALLROUND_ACQ_BATCH = "009";
	public final static String CS_POS_BATCH = "010";
	public final static String CS_ATM_BATCH = "011";
	public final static String ELECTRONIC_MISC_BATCH = "012";
	public final static String ELECTRONIC_ACQUIRER_BATCH = "013";
	public final static String ACQUIRER_BATCH_LOAN = "014";
	public final static String ACQUIRER_BONUS_BATCH = "015";
	public final static String MISC_BONUS_BATCH = "016";
	public final static String BWNET_MISC_BATCH = "101";
	public final static String BWNET_TRANSFER_BATCH = "104";
	public final static String CLIENT_REF_TYPE_RBS = "101";
	public final static String CLIENT_REF_TYPE_AMEX = "201";
	public final static String CLIENT_REF_TYPE_VISA = "202";
	public final static String CLIENT_REF_TYPE_MASTERCARD = "203";
	public final static String PAYMENT_BY_SUMMARY = "SUMM";
	public final static String PAYMENT_CLOSING_BALANCE = "CBAL";
	public final static String PAYMENT_CHGBK_TRAN = "CHBK";
	public final static String PAYMENT_PRES_TRAN = "PRES";
	public final static String DATA_TYPE_DATE = "001";
	public final static String DATA_TYPE_STRING = "002";
	public final static String DATA_TYPE_NUMERIC = "003";
	public final static String DATA_TYPE_CURRENCY = "004";
	public final static String DATA_TYPE_BWT_TABLE = "005";
	public final static String DATA_TYPE_LIST = "006";
	public final static String DATA_TYPE_ISO_CODE = "007";
	public final static String DATA_TYPE_ALPHANUMERIC = "008";
	public final static String DATA_TYPE_HEX = "009";
	public final static String ACCT_CREATION_MANDATORY = "000";
	public final static String ACCT_CREATION_ON_DEMAND = "001";
	public final static String ACCT_CREATION_BY_SERVICE = "002";
	public final static String ACCT_CREATION_BY_POST_TARIFF = "003";
	public final static String ACCT_CREATION_BY_APPLICATION = "004";
	public final static String DB_CONNECT_USER = "001";
	public final static String DB_CONNECT_INTERNAL = "002";
	public final static String DATE_FRMT_CCYYMMDD = "CCYYMMDD";
	public final static String DATE_FRMT_CCYYMM = "CCYYMM";
	public final static String DATE_FRMT_YYMMDD = "YYMMDD";
	public final static String DATE_FRMT_YYMM = "YYMM";
	public final static String DATE_FRMT_MMYY = "MMYY";
	public final static String DATE_FRMT_MMCCYY = "MMCCYY";
	public final static String DATE_FRMT_MMDDYY = "MMDDYY";
	public final static String DATE_FRMT_MMDDCCYY = "MMDDCCYY";
	public final static String DATE_FRMT_MMDD = "MMDD";
	public final static String DATE_FRMT_DDMM = "DDMM";
	public final static String DATE_FRMT_DDMMYY = "DDMMYY";
	public final static String DATE_FRMT_DDMMCCYY = "DDMMCCYY";
	public final static String DATE_FRMT_DDD = "DDD";
	public final static String DATE_FRMT_YDDD = "YDDD";
	public final static String DATE_FRMT_YYDDD = "YYDDD";
	public final static String DATE_FRMT_CCYYDDD = "CCYYDDD";
	public final static int CREATE_PAYM_HISTORY = 1;
	public final static int UPDATE_PAYM_HISTORY = 2;
	public final static int REVERSE_PAYM_HISTORY = 3;
	public final static int REVERSE_PAYM_HISTORY_MIN_PAYM = 4;
	public final static int REVERSE_PAYM_REQUEST_AMOUNT = 5;
	public final static boolean SET_SAVEPOINT = false;
	public final static boolean ROLLBACK_SAVEPOINT = true;
	public final static boolean READ_ONLY = true;
	public final static boolean READ_WRITE = false;
	public final static String CONTRACT_PRIVATE = "001";
	public final static String CONTRACT_COMMERCIAL = "002";
	public final static String CONTRACT_MERCHANT = "003";
	public final static String CONTRACT_INSTITUTION = "004";
	public final static String CONTRACT_ALL = "999";
	public final static String ACCT_CATEG_DEMAND_DEPOSITS = "001";
	public final static String ACCT_CATEG_DEMAND_LOANS = "002";
	public final static String ACCT_CATEG_TIME_LOANS = "003";
	public final static String ACCT_CATEG_TIME_DEPOSIT = "004";
	public final static String ACCT_CATEG_INTERNAL = "005";
	public final static String ACCT_CATEG_PRODUCT_ACCT = "006";
	public final static String ACCT_CATEG_PL = "007";
	public final static String ACCT_CATEG_INSTALLMENT_LOANS = "008";
	public final static String ACCT_CATEG_TURNOVER = "009";
	public final static String ACCT_CATEG_CAMPAIGN_ACCT = "010";
	public final static String ACCT_CATEG_BONUS = "011";
	public final static String REC_LINK_BCH_CAPTURE = "001";
	public final static String REC_LINK_INT_TRAN = "002";
	public final static String INST_GEN_INDV_SETTL = "001";
	public final static String INST_GEN_INST_GEN_PROC = "002";
	public final static String AGREE_STAT_NA = "000";
	public final static String AGREE_STAT_ACTIVE = "001";
	public final static String AGREE_STAT_NEW = "002";
	public final static String AGREE_STAT_CANC_DEBT_BANK = "003";
	public final static String AGREE_STAT_CANC_CREDITOR = "004";
	public final static String AGREE_STAT_CANC_BS = "005";
	public final static String AGREE_STAT_REJECT = "006";
	public final static String AGREE_STAT_PENDING = "007";
	public final static String AGREE_STAT_UNKNOWN = "008";
	public final static int ADDENDUM_AIRLINE = 1;
	public final static int ADDENDUM_LODGING = 2;
	public final static int ADDENDUM_AUTORENTAL = 3;
	public final static int ADDENDUM_PURCHFORMAT_1 = 4;
	public final static int ADDENDUM_LIMITED_USE = 5;
	public final static int ADDENDUM_PURCHA = 6;
	public final static int ADDENDUM_PURCHL = 7;
	public final static int ADDENDUM_CPS_AUTORENTAL = 8;
	public final static int ADDENDUM_CPS_LODGING = 9;
	public final static int ADDENDUM_CPS = 10;
	public final static int ADDENDUM_CPS_AIRLINE = 11;
	public final static int ADDENDUM_PAYMENT_SERVICE = 12;
	public final static String PAYM_HIST_GENERATE_NO = "000";
	public final static String PAYM_HIST_GENERATE_YES = "001";
	public final static String PAYM_HIST_GEN_CARRY_FORWARD = "002";
	public final static String VALUE_BAL_NONE = "000";
	public final static String VALUE_BAL_STANDARD = "001";
	public final static String VALUE_BAL_IGP_RETAIL = "002";
	public final static int LV_WIDTH_EXACT_NO_HEADER = -1;
	public final static int LV_WIDTH_EXACT_WITH_HEADER = -2;
	public final static int LV_WIDTH_NO_CHANGE = -3;
	public final static int LV_SELECT_ALL = 1;
	public final static int LV_SELECT_NONE = 2;
	public final static int LV_SELECT_NO_CHANGE = 3;
	public final static int LV_SELECT_FIRST = 4;
	public final static String DELIV_METH_NOT_AVAIL = "000";
	public final static String DELIV_METH_REGULAR_MAIL = "001";
	public final static String DELIV_METH_REGISTERED_MAIL = "002";
	public final static String DELIV_METH_EXPRESS_MAIL = "003";
	public final static String DELIV_METH_COURIER_MAIL = "004";
	public final static String DELIV_METH_PICK_UP = "005";
	public final static String DELIV_METH_HAND_DELIVERY = "006";
	public final static String DELIV_METH_HOLD_BRANCH = "013";
	public final static String DELIV_METH_REGISTERED_REGULAR = "021";
	public final static String DELIV_METH_FAST_MAIL = "022";
	public final static String DELIV_METH_INTERNET = "025";
	public final static String DELIV_METH_INTERFACE_FILE = "200";
	public final static String DELIV_METH_E_MAIL = "500";
	public final static int LOG_MODE_START = 1;
	public final static int LOG_MODE_END = 2;
	public final static String LOGMSG_TYPE_ERROR = "001";
	public final static String LOGMSG_TYPE_FATAL_ERROR = "002";
	public final static String LOGMSG_TYPE_WARNING = "003";
	public final static String LOGMSG_TYPE_INFO = "004";
	public final static String LOGMSG_TYPE_ADVICE = "005";
	public final static String LOGMSG_TYPE_REPLY = "006";
	public final static String LOGMSG_TYPE_HELP = "007";
	public final static int RECLASS_MODE_NONE = 0;
	public final static int RECLASS_MODE_CSB = 1;
	public final static int RECLASS_MODE_VUB = 2;
	public final static String PROC_CLASS_INTEREST_DR = "INTD";
	public final static String PROC_CLASS_INTEREST_CR = "INTC";
	public final static String PROC_CLASS_OVERDUE_INTEREST = "INTO";
	public final static String PROC_CLASS_OVERLIMIT_INTEREST = "INTL";
	public final static String PROC_CLASS_TRAN_CHARGES = "CHRG";
	public final static String PROC_CLASS_SERVICE_FEES = "FEES";
	public final static String PROC_CLASS_ACCOUNT_FEES = "FEEA";
	public final static String PROC_CLASS_INSURANCE_FEES = "FEEI";
	public final static String MODE_SVC_EXPIRY_ROUND_NA = "000";
	public final static String MODE_SVC_EXPIRY_ROUND_EOM = "001";
	public final static String LIMIT_CATEG_OVERDRAFT = "001";
	public final static String LIMIT_CATEG_RETAIL_BALANCE = "006";
	public final static String LIMIT_CATEG_SECURITY_DEPOSIT = "012";
	public final static int UCAF_NOT_SUPPORTED = 0;
	public final static int UCAF_MERCHANT = 1;
	public final static int UCAF_FULL = 2;
	public final static String FEE_MODE_SUPPRESS = "000";
	public final static String FEE_MODE_GENERATE = "001";
	public final static String CHARGE_SOURCE_LOAN_AMT = "001";
	public final static String CHARGE_SOURCE_INSTALLMENT_AMT = "002";
	public final static String CHARGE_SOURCE_PURCHASE_AMT = "003";
	public final static String CHARGE_SOURCE_DOWNPAYM_AMT = "004";
	public final static String CHARGE_SOURCE_NO_INSTALLMENT = "005";
	public final static String CHARGE_SOURCE_EXTENSION_AMT = "006";
	public final static String CHARGE_SOURCE_ACCT_BALANCE = "007";
	public final static String CHARGE_SOURCE_CLIENT_LIMIT = "008";
	public final static String CHARGE_SOURCE_SECURITY_AMOUNT = "009";
	public final static String CHARGE_SOURCE_PRECALC_INT = "100";
	public final static String CHARGE_SOURCE_INSURANCE_REIM = "101";
	public final static String CHARGE_SOURCE_USER_DEF_START = "500";
	public final static String CHARGE_SOURCE_USER_DEF_END = "599";
	public final static String CHARGE_SOURCE_NONE = "900";
	public final static String ROUNDING_MODE_NA = "000";
	public final static String ROUNDING_MODE_NEAREST = "001";
	public final static String ROUNDING_MODE_ROUND_UP = "002";
	public final static String ROUNDING_MODE_ROUND_DOWN = "003";
	public final static byte RANGE_TYPE_ALL = 0;
	public final static byte RANGE_TYPE_APP_NUM = 1;
	public final static byte RANGE_TYPE_DATE = 2;
	public final static String CHARGE_MODE_ACQ = "001";
	public final static String CHARGE_MODE_ISS = "002";
	public final static String CHARGE_DEST_NA = "000";
	public final static String CHARGE_DEST_CLIENT = "001";
	public final static String CHARGE_DEST_MERCHANT = "002";
	public final static String CHARGE_DEST_INSTITUTION = "003";
	public final static String GENDER_MALE = "001";
	public final static String GENDER_FEMALE = "002";
	public final static String PRC_MSG_TYP_INFO = "100";
	public final static String PRC_MSG_TYP_WARNING = "200";
	public final static String PRC_MSG_TYP_ERROR = "300";
	public final static String PRC_MSG_TYP_FATAL = "900";
	public final static String PRC_MSG_SRC_BW = "001";
	public final static String PRC_MSG_SRC_ORACLE = "002";
	public final static String PRC_MSG_SRC_VB = "003";
	public final static String PRC_MSG_SRC_RDO = "004";
	public final static String PRC_MSG_SRC_ISOM = "005";
	public final static String PRC_MSG_SRC_SEQL = "006";
	public final static String PRC_MSG_SRC_BPI = "007";
	public final static String PRC_MSG_SRC_PARM = "008";
	public final static int COND_TRUE = -1;
	public final static int COND_FALSE = 0;
	public final static int COND_AUTHORIZED = 100;
	public final static int COND_SECURED = 101;
	public final static int COND_MERCHANT_UCAF = 102;
	public final static int COND_ECOMMERCE = 103;
	public final static int COND_FULL_UCAF = 104;
	public final static int COND_MCE_REGISTERED = 105;
	public final static int COND_ELECTRONIC = 106;
	public final static int COND_SET_MERCHANT = 107;
	public final static int COND_TICKET_NUMBER = 108;
	public final static int COND_MOTO = 109;
	public final static int COND_CAT_2 = 110;
	public final static int COND_CAT_1_AND_2 = 111;
	public final static int COND_CUST_YES_KEY = 112;
	public final static int COND_ECOMMERCE_SECURED = 113;
	public final static int COND_CREDIT_CARD = 114;
	public final static int COND_DEBIT_CARD = 115;
	public final static int COND_CNP = 116;
	public final static int COND_NOT_AUTHORIZED = 117;
	public final static int COND_CPS_QUALIFIED = 118;
	public final static int COND_KEY_ENTRY = 119;
	public final static int COND_CNP_OR_ECOM = 120;
	public final static int COND_CVV2_DATA = 121;
	public final static int COND_VISA_GSA_CARD = 122;
	public final static int COND_CHIP = 123;
	public final static int COND_CHIP_PIN = 124;
	public final static int COND_ISSUER_CHIP = 125;
	public final static int COND_ACQUIRER_CHIP = 126;
	public final static int COND_CHIP_SIGNATURE = 127;
	public final static int COND_CHIP_TERMINAL = 128;
	public final static int COND_CHIP_TERM_PIN = 129;
	public final static int COND_CHIP_TERM_SIGN = 130;
	public final static int COND_CHIP_TERM_SEC = 131;
	public final static int COND_CNP_ECOM_CE = 132;
	public final static int COND_ADDEN_START = 900;
	public final static int ADDEN_VISA_CPS = 901;
	public final static int ADDEN_VISA_PASSENGER = 902;
	public final static int ADDEN_VISA_LODGING = 903;
	public final static int ADDEN_VISA_CAR_RENTAL = 904;
	public final static int ADDEN_ECMC_DATA_RATE_II = 920;
	public final static int ADDEN_ECMC_PASSENGER = 921;
	public final static int ADDEN_ECMC_CAR_RENTAL = 922;
	public final static int ADDEN_ECMC_LODGING = 923;
	public final static int ADDEN_ECMC_PURCHASING = 924;
	public final static int ADDEN_VISA_LIMITED_USE = 925;
	public final static int ADDEN_VISA_LVL2_DATA = 926;
	public final static int ADDEN_VISA_LVL3_DATA = 927;
	public final static int COND_ADDEN_END = 999;
	public final static String DCC_CONV_FLAG_NO = "000";
	public final static String DCC_CONV_FLAG_CONCESION = "001";
	public final static String DCC_CONV_FLAG_FRONT_END = "002";
	public final static int MCC_AIRLINE = 100;
	public final static int MCC_HOTEL = 101;
	public final static int MCC_CAR_RENTAL = 102;
	public final static int MCC_RESTAURANT = 103;
	public final static int MCC_SUPERMARKET = 104;
	public final static int MCC_FOODSTORE = 105;
	public final static int MCC_TRAVEL_AGENCY = 106;
	public final static int MCC_TELECOM_SERVICE = 107;
	public final static int MCC_PETROL = 108;
	public final static int MCC_TOUR_OPERATOR = 109;
	public final static int MCC_QUASI_CASH = 110;
	public final static int MCC_AUTOMATED_FUEL = 111;
	public final static int MCC_RAILWAY = 112;
	public final static int MCC_TELEMARKETING = 114;
	public final static int MCC_CRUISE_LINES = 116;
	public final static int MCC_GOVERNMENT = 117;
	public final static int MCC_SCHOOL = 118;
	public final static int MCC_UTILITY = 119;
	public final static int MCC_CABLE_SAT_TV_RADIO = 120;
	public final static int MCC_INSURANCE = 121;
	public final static int MCC_CPS_SMALL_TICKET = 122;
	public final static int MCC_UK_ELEC_HOT_CARD = 123;
	public final static int MCC_MOTO = 124;
	public final static int MCC_PAYMENT = 125;
	public final static int MCC_T_AND_E = 800;
	public final static int MCC_PASSENGER_TRANSPORT = 801;
	public final static int MCC_DEVELOPING_MARKET = 802;
	public final static int MCC_SPAIN_2_75 = 900;
	public final static int MCC_SPAIN_2_55 = 901;
	public final static int MCC_SPAIN_2_13 = 902;
	public final static int MCC_SPAIN_1_91 = 903;
	public final static int MCC_SPAIN_1_70 = 904;
	public final static int MCC_SPAIN_1_30 = 905;
	public final static int MCC_SPAIN_0_85 = 906;
	public final static String REGION_NA = "000";
	public final static String REGION_BY_CLIENT_COUNTRY = "900";
	public final static String REGION_BY_TRAN_COUNTRY = "901";
	public final static String NEW_INSTRUCTION = "100";
	public final static String CANCELLATION = "101";
	public final static String AMENDMENT_CANCEL = "102";
	public final static String AMENDMENT_NEW = "103";
	public final static String ACTIVATE = "104";
	public final static String DDI_INITIALISED_STATUS = "001";
	public final static String DDI_ACTIVE_STATUS = "002";
	public final static String DDI_CANCEL_STATUS = "009";
	public final static String CYCLE_MODE_CONTRACT = "001";
	public final static String CYCLE_MODE_USER_DEFINED = "002";
	public final static String CYCLE_MODE_OTHER_ACCT = "003";
	public final static String RECLASS_UNPROCESSED = "001";
	public final static String RECLASS_RELEASED = "002";
	public final static String RECLASS_NOT_RELEASED = "003";
	public final static String RECLASS_EXCEPTION = "004";
	public final static String RECLASS_MANUAL = "005";
	public final static String RECLASS_MAN_RELEASED = "006";
	public final static String RECLASS_MAN_NOT_RELEASED = "007";
	public final static String RECLASS_MAN_UNPROCESSED = "008";
	public final static String BILLBACK_NO = "001";
	public final static String BILLBACK_DEBIT_ONLY = "002";
	public final static String BILLBACK_ALL = "003";
	public final static String BILLBACK_MANUAL = "004";
	public final static String STAT_GEN_SUPPRESS = "000";
	public final static String STAT_GEN_IF_ACTIVITY = "001";
	public final static String STAT_GEN_IF_NO_ACTIVITY = "002";
	public final static String PAYM_CATEG_DEFAULT = "001";
	public final static String PAYM_CATEG_LEVEL2 = "002";
	public final static String PAYM_CATEG_LEVEL3 = "003";
	public final static String PAYMENT_MODEL_1 = "001";
	public final static String PAYMENT_MODEL_3 = "003";
	public final static String PAYMENT_MODEL_4 = "004";
	public final static String PROC_TYPE_AUTH = "001";
	public final static String PROC_TYPE_POSTING = "002";
	public final static String PROC_TYPE_INTEREST_CAPT = "003";
	public final static String PROC_TYPE_FEES = "004";
	public final static String PROC_TYPE_STATEMENT = "005";
	public final static String PROC_TYPE_CARD_PRODUCTION = "013";
	public final static String PROC_TYPE_BONUS_GENERATION = "014";
	public final static String ISO_GRP_EPI_STATISTICS = "001";
	public final static String ISO_GRP____ = "002";
	public final static String ISO_GRP_MCC_BLOCKING = "003";
	public final static String ISO_GRP_BONUS_EXCL_NONONUS = "004";
	public final static String ISO_GRP_BONUS_EXCL_ONUS = "005";
	public final static String ISO_GRP_MCC_AUTH_RPT = "010";

}
