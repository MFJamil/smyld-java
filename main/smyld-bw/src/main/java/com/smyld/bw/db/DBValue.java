package com.smyld.bw.db;

/**
 * This class will hold all the preset table values for bank works, those values
 * are important for processing
 */
public interface DBValue {
	public static final String TRN_CLASS_NOT_AVAILABLE = "000"; // bwt_transaction_class
	public static final String TRN_CLASS_BATCH_CONTROL = "001"; // bwt_transaction_class
	public static final String TRN_CLASS_CLEARING_TRANS = "002";
	public static final String TRN_CLASS_GL_TRANS = "003";
	public static final String TRN_CLASS_INTERNAL_OWN = "004";
	public static final String TRN_CLASS_SETTL_TRANS = "005";
	public static final String TRN_CLASS_DETAIL_GROUP = "006";
	public static final String TRN_CLASS_SETTL_GROUP = "007";
	public static final String TRN_CLASS_CLEARING_ADV = "009";
	public static final String TRN_CLASS_AUTHORIZATION = "010";
	public static final String TRN_CLASS_NON_FINANCIAL = "011";
	public static final String TRN_CLASS_SOURCE_CTRL = "012";
	public static final String TRN_CLASS_DESTINATION_CTRL = "013";
	public static final String TRN_CLASS_ACCUMULATE_TRANS = "014";
	public static final String TRN_CLASS_CLR_SOURCE_CTRL = "015";
	public static final String TRN_CLASS_CLR_DEST_CTRL = "016";
	public static final String TRN_CLASS_ALL = "999";

	//
	public static final String TRN_CATEG_PRESENTMENTS = "001"; // bwt_transaction_category
	public static final String TRN_CATEG_CHARGEBACKS = "002";
	public static final String TRN_CATEG_REPRESENTMENTS = "003";
	public static final String TRN_CATEG_RETRIEVALS = "004";
	public static final String TRN_CATEG_INT_FEES = "005";
	public static final String TRN_CATEG_INTEREST = "006";
	public static final String TRN_CATEG_CHARGES_FEES = "007";
	public static final String TRN_CATEG_PAYMENTS = "008";
	public static final String TRN_CATEG_BONUS = "009";
	public static final String TRN_CATEG_DETAIL_INFO = "011";
	public static final String TRN_CATEG_INVALID_RESP = "012";
	public static final String TRN_CATEG_ADVICE = "014";
	public static final String TRN_CATEG_SUMMARY_CONTROL = "015";
	public static final String TRN_CATEG_SOURCE_CONTROL = "016";
	public static final String TRN_CATEG_DEST_CONTROL = "017";
	public static final String TRN_CATEG_GL_CONTROL = "018";
	public static final String TRN_CATEG_SETTL_INFO = "019";
	public static final String TRN_CATEG_RETRV_FULLF = "020";
	public static final String TRN_CATEG_SOURCE_FEES = "021";
	public static final String TRN_CATEG_FEE_COLLECT = "022";
	public static final String TRN_CATEG_FULLF_FEE = "023";
	public static final String TRN_CATEG_REJECT_FINANCIAL = "024";
	public static final String TRN_CATEG_REJECT_NON_FINAN = "025";
	public static final String TRN_CATEG_REJECT_BATCH = "026";
	public static final String TRN_CATEG_OLD_REJECT_REVERSAL = "027"; // Conflict
																		// with
																		// comserver
																		// - see
																		// new
																		// reject
																		// reversal
																		// categ
																		// 036
	public static final String TRN_CATEG_AUTHORISATIONS = "027";
	public static final String TRN_CATEG_INT_CHARGES = "028";
	public static final String TRN_CATEG_BALANCE_ADJ = "030";
	public static final String TRN_CATEG_FRAUD_DETAIL = "031";
	public static final String TRN_CATEG_FRAUD_REJECT = "032";
	public static final String TRN_CATEG_FRAUD_WARNING = "033";
	public static final String TRN_CATEG_CHECK = "034"; // Used for Procard
														// Loyalty System
	public static final String TRN_CATEG_PAYMENT_REQUEST = "035";
	public static final String TRN_CATEG_REJECT_REVERSAL = "036"; // reserved
																	// as a
																	// replacement
																	// for 027
	public static final String TRN_CATEG_ACCRUED_INTEREST = "037"; // Used by
																	// accruals
																	// process
																	// for GL
																	// purposes
	public static final String TRN_CATEG_INT_BELOW_THRESHOLD = "038"; // Used
																		// by
																		// capitalization
																		// for
																		// GL
																		// purposes
	public static final String TRN_CATEG_PAYMENT_ADJUSTMENT = "039";
	public static final String TRN_CATEG_DECLINES = "040";
	public static final String TRN_CATEG_REFERRALS = "041";
	public static final String TRN_CATEG_FAILURES = "042";
	public static final String TRN_CATEG_PAYMENT_REJECTS = "043";
	public static final String TRN_CATEG_FEE_CHARGEBACK = "044"; // Fee
																	// collection
																	// return
	public static final String TRN_CATEG_FEE_RESUBMISSION = "045"; // Fee
																	// collection
																	// resubmission
																	// - for IPM
																	// use
	public static final String TRN_CATEG_FEE_SECOND_CHARGE = "046"; // Fee
																	// collection
																	// arbitration
																	// return -
																	// for IPM
																	// use
	public static final String TRN_CATEG_ADJUSTMENT = "050";
	public static final String TRN_CATEG_ALL = "999";
	//
	public static final String TRN_STATE_ENTERED = "001"; // bwt_transaction_status
	public static final String TRN_STATE_POSTED = "002";
	public static final String TRN_STATE_ERROR = "003";
	public static final String TRN_STATE_PAID = "004";
	public static final String TRN_STATE_PENDING = "005";
	public static final String TRN_STATE_MATCHED = "006";
	public static final String TRN_STATE_PROCESSED = "007";
	public static final String TRN_STATE_RELEASED = "008";
	public static final String TRN_STATE_CLEARED = "009";
	public static final String TRN_STATE_UNPOSTED = "010";
	public static final String TRN_STATE_REPROCESSED = "011";
	public static final String TRN_STATE_SUPPRESSED = "012";
	public static final String TRN_STATE_PURGED = "013";
	public static final String TRN_STATE_CHARGEBACKED = "050";
	//
	public static final String TRN_STATE_NO_BIN_TAB = "200"; // bwt_transaction_status
	public static final String TRN_STATE_NO_SERVICE = "201";
	public static final String TRN_STATE_NO_ASSIGNED_SVC = "202";
	public static final String TRN_STATE_NO_INW_FEES = "203";
	public static final String TRN_STATE_NO_OUT_FEES = "204";
	public static final String TRN_STATE_NO_FEES = "205";
	public static final String TRN_STATE_LUHN_FAILED = "206";
	public static final String TRN_STATE_NO_DESTINATION = "207";
	public static final String TRN_STATE_INVALID_POS_CD = "208";
	public static final String TRN_STATE_NO_SVC_CONTRACT = "209";
	public static final String TRN_STATE_NOT_RECONCILED = "210";
	public static final String TRN_STATE_NO_CLIENT = "211";
	public static final String TRN_STATE_NO_POST_INST = "212";
	public static final String TRN_STATE_NO_ACCT_REC = "213";
	public static final String TRN_STATE_GL_FAILED = "214";
	public static final String TRN_STATE_NO_TERM_CATEGORY = "215";
	public static final String TRN_STATE_INV_CARDNO_LENGTH = "216";
	public static final String TRN_STATE_ZERO_VALUE = "217";
	public static final String TRN_STATE_CONTRACT_CLOSED = "218";
	public static final String TRN_STATE_DCC_MISMATCH = "219"; // Barclays DCC
																// only
	public static final String TRN_STATE_NO_PRESENTMENT = "220";
	public static final String TRN_STATE_DEVICE_NOT_FOUND = "221";
	public static final String TRN_STATE_DCC_INV_FORMAT = "222"; // Barclays
																	// DCC only
	public static final String TRN_STATE_NO_PAYMENT = "223"; // CZSB
																// (Octagon)
	public static final String TRN_STATE_INVALID_TRAN_DATE = "224";
	public static final String TRN_STATE_CONTRACT_INVALID = "225";
	public static final String TRN_STATE_CYCLE_CLOSED = "226"; // internal use
																// only
	public static final String TRN_STATE_FUTURE_TRAN_DATE = "227";
	public static final String TRN_STATE_LARGE_VALUE = "228";
	public static final String TRN_STATE_LATE_PRESENTMENT = "229";
	public static final String TRN_STATE_AUTH_CODE_REQUIRED = "230";
	public static final String TRN_STATE_WRONG_CARD_STATUS = "231"; // Invalid
																	// card
																	// status -
																	// these
																	// statuses
																	// are used
																	// during
																	// posting
																	// when
																	// checking
																	// against
																	// CBR_PROCESS_ACTION
																	// (conditionally)
	public static final String TRN_STATE_WRONG_ACCT_STATUS = "232"; // Invalid
																	// acct
																	// status -
																	// ";
	public static final String TRN_STATE_WRONG_CNTRCT_STATUS = "233"; // Invalid
																		// contract
																		// status
																		// - ";
	public static final String TRN_STATE_PH1_NO_POST_INST = "234"; // Source
																	// transaction-level
																	// reject
																	// (used
																	// only when
																	// ValidatePh1PostInst
																	// is
																	// enabled)

	public static final String TRN_STATE_NO_ACQUIRING_BIN = "235"; // Interchange
																	// member
																	// details
																	// not found
																	// in
																	// GetAcquiringBin
																	// function
	public static final String TRN_STATE_EMPTY_FEE_RULE = "236"; // Used when
																	// Fee Rule
																	// (IRD)
																	// cannot be
																	// determined.
	public static final String TRN_STATE_INVALID_BUSS_CLASS = "237"; // Used
																		// for
																		// Invalid
																		// Business
																		// Class
																		// value
																		// that
																		// cannot
																		// be
																		// mapped.

	public static final String TRN_STATE_INVALID_AMEX_SE_NO = "238"; // Used
																		// by
																		// the
																		// FDMS
																		// 80
																		// byte
																		// file
																		// -
																		// Invalid
																		// AMEX
																		// SE
																		// Number
	public static final String TRN_STATE_INV_CARD_PREFIX = "239";
	public static final String TRN_STATE_INTER_NOT_ALLOWED = "240"; // Interchange
																	// not
																	// allowed
	public static final String TRN_STATE_NO_REFERENCE_FOUND = "241"; // Previously
																		// was
																		// '351'
																		// but
																		// now
																		// placed
																		// in
																		// proper
																		// sequence
																		// of
																		// constants.
																		// This
																		// is
																		// used
																		// for
																		// PEC
																		// incoming
																		// payments
	public static final String TRN_STATE_INVAL_MERCH_DETAIL = "242"; // Front
																		// end
																		// reject
																		// code
																		// used
																		// for
																		// FDMS
																		// 80
																		// byte
																		// file.
																		// Invalid
																		// Merchant
																		// Detail
	public static final String TRN_STATE_INVAL_ADDENDUM_DATA = "243"; // Reject
																		// code
																		// used
																		// for
																		// rejection
																		// of
																		// CPS
																		// transactions
																		// with
																		// invalid
																		// Addendum
																		// Data.
																		// OMNI
																		// Issue
																		// 468.
	public static final String TRN_STATE_MISSING_SERVICE_TEL = "244";

	//
	public static final String TRN_STATE_SUSPECTED_FRAUD = "300"; // bwt_transaction_status
	public static final String TRN_STATE_CONFIRMED_FRAUD = "301";
	public static final String TRN_STATE_NO_FRAUD = "302";
	public static final String TRN_STATE_FRAUD_BANK_LOSS = "303";
	public static final String TRN_STATE_FRAUD_MERCH_LOSS = "304";
	//
	public static final String TRN_STATE_DISCARD_VALUE = "310"; // Procard only
																// - COD (see
																// CEKAB)
	public static final String TRN_STATE_RK_CREDIT_NA = "311"; // Procard only
																// - RK (see
																// CEKAB)
	public static final String TRN_STATE_NO_RK_FEES = "312"; // Procard only
																// - RK (see
																// CEKAB)
	public static final String TRN_STATE_NO_COD_FEES = "313"; // Procard only
																// - COD (see
																// CEKAB)
	//
	public static final String TRN_STATE_PAYM_REJECTED = "400"; // For rejected
																// payments of
																// GSS from BS
	public static final String TRN_STATE_PAYM_CANCELLED = "401";
	public static final String TRN_STATE_PAYM_TRANSFER_BACK = "402";
	//
	public static final String TRN_STATE_ITEMTYP_MISC = "800"; // For CZSB only
																// 8xx
	public static final String TRN_STATE_ITEMTYP_32 = "801";
	public static final String TRN_STATE_ITEMTYP_33 = "802";
	public static final String TRN_STATE_ITEMTYP_42 = "803";
	public static final String TRN_STATE_ITEMTYP_43 = "804";
	public static final String TRN_STATE_ITEMTYP_55 = "805";
	public static final String TRN_STATE_ITEMTYP_81 = "806";
	public static final String TRN_STATE_ITEMTYP_82 = "807";
	public static final String TRN_STATE_ITEMTYP_83 = "808";
	public static final String TRN_STATE_ITEMTYP_85 = "809";
	//
	public static final String TRN_TYPES_NA = "000"; // bwt_transaction_type
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
	public final static String TRN_TYPES_SETTLEMENT_CR = "475";
	public final static String TRN_TYPES_SETTLEMENT_DR = "476";
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

	//
	public static final String TRN_TERMC_MANUAL = "001"; // bwt_terminal_capability
	public static final String TRN_TERMC_POS = "002";
	public static final String TRN_TERMC_POS_PIN = "003";
	public static final String TRN_TERMC_POS_ICC = "004"; // will be used for
															// chip processing
	public static final String TRN_TERMC_ATM = "005";
	public static final String TRN_TERMC_ATM_ICC = "006";
	public static final String TRN_TERMC_CAT1_AUTO_DISP = "007";
	public static final String TRN_TERMC_CAT2_SELF_SERV = "008";
	public static final String TRN_TERMC_CAT3_LIMITED_AMT = "009";
	public static final String TRN_TERMC_CAT4_IN_FLIGHT = "010";
	public static final String TRN_TERMC_ALL = "999";

	public static final String TRN_CAPTM_SWIPED_SIGNED = "001"; // bwt_capture_method
	public static final String TRN_CAPTM_SWIPED_PIN = "002";
	public static final String TRN_CAPTM_CUST_YES_KEY = "003";
	public static final String TRN_CAPTM_CUST_NO_KEY = "004";
	public static final String TRN_CAPTM_MANUAL = "010";
	public static final String TRN_CAPTM_ATM = "020";
	public static final String TRN_CAPTM_ATM_CHIP = "021";
	public static final String TRN_CAPTM_MAIL = "030";
	public static final String TRN_CAPTM_TEL = "040";
	public static final String TRN_CAPTM_MASTERPHONE = "041";
	public static final String TRN_CAPTM_CHIP_FULL_GRD = "043";
	public static final String TRN_CAPTM_CHIP_PARTIAL_GRD = "044";
	public static final String TRN_CAPTM_CNP_RECURRING = "045";

	// --- e-commerce START //if e-commerce capture methods are added, then
	// modify function IsEcommerceCaptM()
	public static final String TRN_CAPTM_SET_YES_CERT = "050"; // VISA Secure
																// Electronic
																// Transaction
																// with
																// cardholder
																// certificate
																// (Visa
																// mail/tel
																// indicator 5)
	public static final String TRN_CAPTM_SET_NO_CERT = "051"; // VISA Secure
																// Electronic
																// Transaction
																// without
																// cardholder
																// certificate
																// (Visa
																// mail/tel
																// indicator 6)
	public static final String TRN_CAPTM_ELEC_COMMERCE = "052"; // EP Electronic
																// Commerce
																// (obsolete)
	public static final String TRN_CAPTM_NON_SET = "053"; // Non-Secure
															// Electronic
															// Commerce (Visa
															// mail/tel
															// indicator 8)
	public static final String TRN_CAPTM_CHANNEL_ENCRYPT = "054"; // Channel
																	// Encrypted
																	// Electronic
																	// Commerce
																	// (Visa
																	// mail/tel
																	// indicator
																	// 7)
	public static final String TRN_CAPTM_SET_MERCHANT = "055"; // VISA
																// Non-authenticated
																// security
																// transaction
																// that does not
																// comply with
																// SET and the
																// merchant is
																// SET
																// compatible
																// (Visa
																// mail/tel
																// indicator 9)
	public static final String TRN_CAPTM_FULL_SET = "056"; // Europay Full SET
	// --- e-commerce END
	public static final String TRN_CAPTM_CHIP_INIT = "060"; // The Chip
															// initiated the
															// transaction -
															// Inserted for
															// Smart Card Chip
															// data processing
															// Pierre 07/02/2000
	public static final String TRN_CAPTM_CHIP_NOT_REL = "061"; // Chip Card was
																// read at
																// terminal but
																// data
																// transmitted
																// may not have
																// been reliable
																// - Inserted
																// for Smart
																// Card Chip
																// data
																// processing
																// Pierre
																// 07/02/2000
	public static final String TRN_CAPTM_CHIP_READ_SIGN = "062"; // Chip card
																	// read,
																	// signature
																	// (Europay)
	public static final String TRN_CAPTM_CHIP_READ_PIN = "063"; // Chip card
																// read, PIN
																// (Europay)
	public static final String TRN_CAPTM_CHIP_OFFLINE = "064"; // Chip card
																// read, Offline
	public static final String TRN_CAPTM_CHIP_CNTCTLES = "065"; // Chip card
																// auto-entry
																// via
																// contactless
	public static final String TRN_CAPTM_CHIP_CNTLES_SIGN = "066"; // Chip card
																	// auto-entry
																	// via
																	// contactless
																	// signature
	public static final String TRN_CAPTM_CAT1_PIN = "070";
	public static final String TRN_CAPTM_CAT2_AUTHORIZED = "071";
	public static final String TRN_CAPTM_CAT3_OFFLINE = "072";
	public static final String TRN_CAPTM_CAT4_IN_FLIGHT = "073";
	public static final String TRN_CAPTM_CAT_REMOTE = "074";
	public static final String TRN_CAPTM_CAT6_ELEC_COMM = "075";
	public static final String TRN_CAPTM_CAT7_TRANSP = "076";
	public static final String TRN_CAPTM_ELEC = "100";
	public static final String TRN_CAPTM_PAPER = "101";
	public static final String TRN_CAPTM_ALL = "999";

	public static final String TRN_BUSSC_MISCELLANEOUS = "504"; // MCC Codes
																// (bwt_iso_buss_class)
	public static final String TRN_BUSSC_TELLER = "505";
	public static final String TRN_BUSSC_ATM = "506";
	public static final String TRN_BUSSC_CASINO = "583";
	public static final String TRN_BUSSCISO_MISC = "5999";
	public static final String TRN_BUSSCISO_TELLER = "6010";
	public static final String TRN_BUSSCISO_ATM = "6011";
	public static final String TRN_BUSSCISO_CASINO = "7995";

	// Clearing channels declaration
	public static final String TRN_CLEAR_NA = "000";
	public static final String TRN_CLEAR_INW_ECCF = "001";
	public static final String TRN_CLEAR_OUT_ECCF = "002";
	public static final String TRN_CLEAR_INW_VISA_II = "003";
	public static final String TRN_CLEAR_OUT_VISA_II = "004";
	public static final String TRN_CLEAR_BATCH_INPUT = "005";
	public static final String TRN_CLEAR_BATCH_INPUT_ISS = "006";
	public static final String TRN_CLEAR_EPS_NET = "007";
	public static final String TRN_CLEAR_VISA_I = "009";
	public static final String TRN_CLEAR_INW_MC_INET = "011";
	public static final String TRN_CLEAR_OUT_MC_INET = "012";
	public static final String TRN_CLEAR_OUT_AMEX = "014";
	public static final String TRN_CLEAR_INW_MERCHANT = "015";
	public static final String TRN_CLEAR_OUT_MERCHANT = "016";
	public static final String TRN_CLEAR_INW_ON_US = "017";
	public static final String TRN_CLEAR_OUT_ON_US = "018";
	public static final String TRN_CLEAR_INW_CEKAB = "019";
	public static final String TRN_CLEAR_INW_LOYALTY = "020";
	public static final String TRN_CLEAR_INW_AURIGA = "021";
	public static final String TRN_CLEAR_OUT_BW3_ISSUER = "024";
	public static final String TRN_CLEAR_INW_VISA_NNSS = "025";
	public static final String TRN_CLEAR_OUT_VISA_NNSS = "026";
	public static final String TRN_CLEAR_OUT_BW3_FANTOM = "028";
	public static final String TRN_CLEAR_OUT_PAYM_PGIRO = "029";
	public static final String TRN_CLEAR_OUT_PAYM_BGIRO = "030";
	public static final String TRN_CLEAR_INW_ON_US_FEES = "031";
	public static final String TRN_CLEAR_INW_ON_US_PAYM = "032";
	public static final String TRN_CLEAR_INW_CR_PAYM = "034";
	public static final String TRN_CLEAR_OUT_CR_PAYM = "035";
	public static final String TRN_CLEAR_INW_CONRAD = "036";
	public static final String TRN_CLEAR_INW_EXCEPTION = "037";
	public static final String TRN_CLEAR_BAL_ADJUST = "038";
	public static final String TRN_CLEAR_INW_VISA_ON_US = "039";
	public static final String TRN_CLEAR_INW_INT_ON_US = "040";
	public static final String TRN_CLEAR_RECLASSIFICATION = "041";
	public static final String TRN_CLEAR_OUT_SVC_PROV = "042";
	public static final String TRN_CLEAR_INW_EXCP_EPS = "043";
	public static final String TRN_CLEAR_INW_EXCP_UBS = "044";
	public static final String TRN_CLEAR_INW_EXCP_CSG = "045";
	public static final String TRN_CLEAR_INW_MC_SAFE = "046"; // changed from
																// 033
																// (25/11/98)
	public static final String TRN_CLEAR_OUT_MC_SAFE = "047";
	public static final String TRN_CLEAR_INW_VISA_USD = "048";
	public static final String TRN_CLEAR_OUT_PAYM_REQUEST = "049";
	public static final String TRN_CLEAR_OUT_PAYORD = "050";
	public static final String TRN_CLEAR_OUT_ACQUIRER = "051";
	public static final String TRN_CLEAR_OUT_MERCHANT_ZIB = "052";
	public static final String TRN_CLEAR_OUT_CARDSACCTS = "053";
	public static final String TRN_CLEAR_OUT_CARDFEES = "054";
	public static final String TRN_CLEAR_OUT_EMBOSS = "055";
	public static final String TRN_CLEAR_INW_ENCODING = "056";
	public static final String TRN_CLEAR_INW_GL_TRANS = "057";
	public static final String TRN_CLEAR_OUT_CSB_GL = "058";
	public static final String TRN_CLEAR_INW_REJ_DIRECT_DEBITS = "060";
	public static final String TRN_CLEAR_OUT_BKR = "061";
	public static final String TRN_CLEAR_OUTW_OCTAGON = "065";
	public static final String TRN_CLEAR_INW_OCTAGON = "066";
	public static final String TRN_CLEAR_INW_BW2 = "067";
	public static final String TRN_CLEAR_CSB_DATA_CONV = "068";
	public static final String TRN_CLEAR_OUT_PAYM_HANDEL = "069";
	public static final String TRN_CLEAR_OUT_VISA_ON_US = "071";
	public static final String TRN_CLEAR_INW_ECCF_DOM = "072";
	public static final String TRN_CLEAR_OUT_ECCF_DOM = "073";
	public static final String TRN_CLEAR_OUT_TRN_EXPORT = "074";
	public static final String TRN_CLEAR_INW_JNB_POS = "075";
	public static final String TRN_CLEAR_NWB_AIRTIME_TXN = "076";
	public static final String TRN_CLEAR_INW_INET_ON_US = "077";
	public static final String TRN_CLEAR_OUT_INET_ON_US = "078";
	public static final String TRN_CLEAR_BAS_OUTW = "079";
	public static final String TRN_CLEAR_MIS_OUTW = "080";
	public static final String TRN_CLEAR_PROV_INW_BONUS = "081";
	public static final String TRN_CLEAR_INW_EXPN_AFFI = "082"; // =trn_clear_inw_expn_ink1
	public static final String TRN_CLEAR_INW_APACS29 = "083";
	public static final String TRN_CLEAR_INW_APACS40 = "084";
	public static final String TRN_CLEAR_INW_BACS = "085";
	public static final String TRN_CLEAR_OUT_BACS_CR = "086";
	public static final String TRN_CLEAR_OUT_BMS_MANUAL = "087";
	public static final String TRN_CLEAR_OUT_AUTOGIRO_BG_COMM = "088";
	public static final String TRN_CLEAR_OUT_AUTOGIRO_PG = "089";
	public static final String TRN_CLEAR_OUT_OCR_GIRO = "090";
	public static final String TRN_CLEAR_EP_STOPLIST = "091"; // please note
																// that this
																// channel is
																// also used for
																// the
																// MasterCard
																// StopList
																// process
	public static final String TRN_CLEAR_VISA_STOPLIST = "092";
	public static final String TRN_CLEAR_OUT_MATERCARD_MATCH = "093";
	public static final String TRN_CLEAR_OUT_PAYM_FRISPAR = "094";
	public static final String TRN_CLEAR_OUT_AUTOGIRO_BG_PRIV = "095";
	public static final String TRN_CLEAR_INW_ECCF_ON_US = "096";
	public static final String TRN_CLEAR_OUT_ECCF_ON_US = "097";
	public static final String TRN_CLEAR_INW_AUTOGIRO_BGP = "098";
	public static final String TRN_CLEAR_INW_AUTOGIRO_BGC = "099";
	public static final String TRN_CLEAR_INW_AUTOGIRO_PG = "100";
	public static final String TRN_CLEAR_INW_INTRABANK = "101"; // used for
																// service
																// provider
																// functions -
																// triggers
																// special GL
																// handling
	public static final String TRN_CLEAR_OUT_INTRABANK = "102"; // used for
																// service
																// provider
																// functions -
																// triggers
																// special GL
																// handling
	public static final String TRN_CLEAR_OUT_BKRT_MERCH_PAY_NLB = "103";
	public static final String TRN_CLEAR_OUT_BKRT_MERCH_PAY_OTH = "104";
	public static final String TRN_CLEAR_OUT_NLB_PAYMENT = "105";
	public static final String TRN_CLEAR_OUT_NLB_AUTO_PAY_TZAPIS = "106";
	public static final String TRN_CLEAR_OUT_BKRT_MERCH_PAY_DR = "107";
	public static final String TRN_CLEAR_OUT_NLB_AUTO_PAY_R73TRAPB = "108";
	public static final String TRN_CLEAR_OUT_NLB_DEBIT_CARD = "109";
	public static final String TRN_CLEAR_INW_NLB_AUTO_PAY_TZAPIS = "110";
	public static final String TRN_CLEAR_INW_NLB_AUTO_PAY_R73TRAPB = "111";
	public static final String TRN_CLEAR_INW_ILF = "112";
	public static final String TRN_CLEAR_MKB_RBS_CHANNEL = "113";
	public static final String TRN_CLEAR_OUT_GSS_MERCH_PAYMENT = "114";
	public static final String TRN_CLEAR_INW_APPL_PROC_MISC = "115";
	public static final String TRN_CLEAR_OUT_BETALINGSSERVICE = "116";
	public static final String TRN_CLEAR_INW_BETALINGSSERVICE = "117";
	public static final String TRN_CLEAR_INW_ON_US_INSTALLMENTS = "118";
	public static final String TRN_CLEAR_OUT_BS_TOTAL = "119"; // reserved for
																// GSS BS Total
	public static final String TRN_CLEAR_INW_APPL_PROC_ACQ = "120";
	public static final String TRN_CLEAR_INW_AUTH_ACQ = "121";
	public static final String TRN_CLEAR_INW_VISA_TC57 = "122";
	public static final String TRN_CLEAR_INW_MDS = "123";
	public static final String TRN_CLEAR_OUT_MDS = "124";
	public static final String TRN_CLEAR_OUT_DOM_JONB_SPARROW = "125";
	public static final String TRN_CLEAR_INW_SPARROW = "126";
	public static final String TRN_CLEAR_OUT_PAYM_ATLAS = "127";
	public static final String TRN_CLEAR_OUT_DINERS = "128";
	public static final String TRN_CLEAR_INW_VUB_DOMESTIC_ONUS = "129";
	public static final String TRN_CLEAR_INW_VUB_DOMESTIC_NON_ONUS = "130";
	public static final String TRN_CLEAR_INW_VUB_DOMESTIC_EXTERNAL = "131";
	public static final String TRN_CLEAR_OUT_VUB_DOMESTIC_ONUS = "132";
	public static final String TRN_CLEAR_OUT_VUB_DOMESTIC_NON_ONUS = "133";
	public static final String TRN_CLEAR_OUT_VUB_DOMESTIC_EXTERNAL = "134";
	public static final String TRN_CLEAR_INW_HSBC_BRANCH_DEPOSIT = "135";
	public static final String TRN_CLEAR_INW_HSBC_DB_DEPOSIT = "136";
	public static final String TRN_CLEAR_INW_COMSERVER_POS = "137";
	public static final String TRN_CLEAR_OUT_HSBC_PLAZA_BONUS = "138";
	public static final String TRN_CLEAR_OUT_HSBC_QUIKCASH = "139";
	public static final String TRN_CLEAR_OUT_HSBC_MIMBOL = "140";
	public static final String TRN_CLEAR_INW_FDMS_TRANS = "141";
	public static final String TRN_CLEAR_OUT_HSBC_MERCHANT = "142";
	public static final String TRN_CLEAR_OUT_HSBC_MERCH_OTHERS = "143";
	public static final String TRN_CLEAR_OUT_HSBC_MERCH_DRAFT = "144";
	public static final String TRN_CLEAR_OUT_GSS_BONUS_CHECK_ISS = "145";
	public static final String TRN_CLEAR_OUT_GFF_MERCH_PAY_OUT = "146";
	public static final String TRN_CLEAR_OUT_ICC_PAY_REQST_BANKA = "147";
	public static final String TRN_CLEAR_INW_ICC_PAY_RESP_BANKA = "148";
	public static final String TRN_CLEAR_OUT_BACS_EUR_CR = "149";
	public static final String TRN_CLEAR_OUT_BACS_EUR_DR = "150";
	public static final String TRN_CLEAR_OUT_WELLS_PAYMENT = "151";
	public static final String TRN_CLEAR_INW_WELLS_PAYM_RESPONSE = "152";
	public static final String TRN_CLEAR_INW_MC_IPM = "153";
	public static final String TRN_CLEAR_OUT_MC_IPM = "154";
	public static final String TRN_CLEAR_INW_MC_IPM_DOM = "155";
	public static final String TRN_CLEAR_OUT_MC_IPM_DOM = "156";
	public static final String TRN_CLEAR_INW_BOV = "157";
	public static final String TRN_CLEAR_OUT_BOV = "158";
	public static final String TRN_CLEAR_INW_JCB_XCHNG = "159";
	public static final String TRN_CLEAR_OUT_JCB_XCHNG = "160";
	public static final String TRN_CLEAR_INW_FDMS_DCC_TRANS = "161";
	public static final String TRN_CLEAR_INW_IPM_ON_US = "162";
	public static final String TRN_CLEAR_OUT_IPM_ON_US = "163";
	public static final String TRN_CLEAR_OUT_BMS_LASER = "164";
	public static final String TRN_CLEAR_OUT_PAYM_PAYCE = "165"; // BMS
																	// outward
																	// merchant
																	// payment
																	// channel
	public static final String TRN_CLEAR_OUT_ONUS_ATMP = "166";
	public static final String TRN_CLEAR_INW_HSBC_ATMP_CCP = "167";
	public static final String TRN_CLEAR_INW_AEGN = "168";
	public static final String TRN_CLEAR_OUT_AEGN = "169";
	public static final String TRN_CLEAR_OUT_AMEXDC = "170";
	public static final String TRN_CLEAR_OUT_AMEX_FRAUD = "171";
	public static final String TRN_CLEAR_INW_AMEX_FRAUD = "172";
	public static final String TRN_CLEAR_OUT_VISA_SMS = "173";
	public static final String TRN_CLEAR_OUT_VISA_SMS_NNSS = "174";
	public static final String TRN_CLEAR_OUT_BACS_DR = "199";

	public static final String TRN_CLEAR_INW_ATM_FILE_RB = "213";
	public static final String TRN_CLEAR_INW_POS_FILE_RB = "215";
	public static final String TRN_CLEAR_INW_SAMA_DOM = "216";
	public static final String TRN_CLEAR_OUT_SAMA_DOM = "217";
	public static final String TRN_CLEAR_INW_SAMA_GCC = "218";
	public static final String TRN_CLEAR_OUT_SAMA_GCC = "219";

	//
	public static final String TRN_CLEAR_INW_EXCP_TKRS16 = "401";

	public static final String TRN_CLEAR_INW_ISS_RECON = "507";
	public static final String TRN_CLEAR_INW_ACQ_RECON = "508";

	//
	// Range 800 through 899 is used for domestic clearing channels
	public static final String TRN_CLEAR_DOMESTIC_START = "800";
	public static final String TRN_CLEAR_DOMESTIC_END = "899";
	// In the first phase this channels will be hard-coded, but later on this
	// will be defined using
	// an oracle table (SYS_PROCESS_CHANNELS)
	public static final String TRN_CLEAR_OUT_MC_IPM_DOM_CA = "800";
	public static final String TRN_CLEAR_INW_MC_IPM_DOM_CA = "801";
	public static final String TRN_CLEAR_INW_VISA_NNSS_CA = "802";
	public static final String TRN_CLEAR_OUT_VISA_NNSS_CA = "803";
	public static final String TRN_CLEAR_INW_VISA_NNSS_UK = "804";
	public static final String TRN_CLEAR_OUT_VISA_NNSS_UK = "805";
	public static final String TRN_CLEAR_INW_VISA_NNSS_DE = "806";
	public static final String TRN_CLEAR_OUT_VISA_NNSS_DE = "807";
	public static final String TRN_CLEAR_INW_ECCF_DOM_DE = "808";
	public static final String TRN_CLEAR_OUT_ECCF_DOM_DE = "809";
	public static final String TRN_CLEAR_INW_ECCF_DOM_UK = "810";
	public static final String TRN_CLEAR_OUT_ECCF_DOM_UK = "811";
	public static final String TRN_CLEAR_INW_MC_IPM_DOM_US = "812";
	public static final String TRN_CLEAR_OUT_MC_IPM_DOM_US = "813";
	public static final String TRN_CLEAR_INW_VISA_NNSS_US = "814";
	public static final String TRN_CLEAR_OUT_VISA_NNSS_US = "815";
	//
	public static final String TRN_CLEAR_OUT_AUTHORIZATION = "998";
	public static final String TRN_CLEAR_UNMATCHED = "999";
	//
	// The following is a list of all merchant inward channels (can be used
	// directly in sql statements)
	public static final String TRN_CLEAR_MERCHANT_SOURCES = TRN_CLEAR_INW_CEKAB
			+ "//,//" + TRN_CLEAR_BATCH_INPUT + "//,//"
			+ TRN_CLEAR_INW_APPL_PROC_ACQ + "//,//" + TRN_CLEAR_INW_APACS29
			+ "//,//" + TRN_CLEAR_INW_MERCHANT + "//,//"
			+ TRN_CLEAR_INW_VISA_TC57 + "//,//" + TRN_CLEAR_INW_COMSERVER_POS
			+ "//,//" + TRN_CLEAR_INW_FDMS_TRANS + "//,//"
			+ TRN_CLEAR_INW_FDMS_DCC_TRANS;
	//
	public static final String SUN_TYPES_FIRST_CHARGE = "001"; // bwt_sundry_type
	public static final String SUN_TYPES_SECOND_CHARGE = "002";
	public static final String SUN_TYPES_REPRESENTMENTS = "003";
	public static final String SUN_TYPES_FULFILLMENT = "004";
	public static final String SUN_TYPES_INVALID_RESPONSE = "005";
	public static final String SUN_TYPES_FEE_CHARGEBACK = "006";
	public static final String SUN_TYPES_FEE_COLLECTION = "007";
	public static final String SUN_TYPES_RET_REQUEST = "008";
	public static final String SUN_TYPES_REVERSAL = "009";
	public static final String SUN_TYPES_FULF_FEE = "010";
	public static final String SUN_TYPES_FUNDS_DISB = "011";
	public static final String SUN_TYPES_TRANSFER = "012";
	public static final String SUN_TYPES_REJECTS = "013";
	public static final String SUN_TYPES_RE_PRC_BATCH = "014";
	public static final String SUN_TYPES_FRAUD_ADD = "015";
	public static final String SUN_TYPES_FRAUD_CHANGE = "016";
	public static final String SUN_TYPES_FRAUD_DELETE = "017";
	public static final String SUN_TYPES_FRAUD_CONFIRM = "018";
	public static final String SUN_TYPES_FRAUD_DUPLICATE = "019";
	public static final String SUN_TYPES_FRAUD_PRESTATUS = "020";
	public static final String SUN_TYPES_FRAUD_REACTIVATE = "021";
	public static final String SUN_TYPES_FRAUD_ADD_DUPLICATE = "022";
	public static final String SUN_TYPES_FRAUD_WARNING = "023";
	public static final String SUN_TYPES_FRAUD_CHGBCK_RIGHT = "024";
	public static final String SUN_TYPES_FRAUD_AMOUNT_LT_1USD = "025"; // Fraud
																		// Amount
																		// < 1
																		// $US//
	public static final String SUN_TYPES_FEE_RESUBMISSION = "026"; // Fee
																	// collection
																	// resubmission
																	// - for IPM
																	// use
	public static final String SUN_TYPES_FEE_SECOND_CHARGE = "027"; // Fee
																	// collection
																	// arbitration
																	// return -
																	// for IPM
																	// use
	public static final String SUN_TYPES_SOURCE_TRANSFER = "030"; // Source
																	// summary
																	// transfer
	public static final String SUN_TYPES_AMEND_CLIENT_NO = "031"; // used by
																	// Suspense
																	// Reprocessing
	public static final String SUN_TYPES_AMEND_CARD_NO = "032"; // used by
																// Suspense
																// Reprocessing
	public static final String SUN_TYPES_MERCHANT_RETURN = "033"; // used by
																	// Suspense
																	// Reprocessing
	public static final String SUN_TYPES_REVERSE_FILE = "034";
	public static final String SUN_TYPES_NEW_PRESENTMENT = "035";
	public static final String SUN_TYPES_MC_MATCH_ADD = "036";
	public static final String SUN_TYPES_MC_MATCH_INQUIRY = "037";
	public static final String SUN_TYPES_BALANCE_TRANSFER = "038";
	public static final String SUN_TYPES_PAYMENT_HISTORY_ADJ = "039";
	public static final String SUN_TYPES_MERCHANT_CHGBK_TRANSFER = "040";
	public static final String SUN_TYPES_AMEND_TRAN_DATE = "041"; // used by
																	// source
																	// Suspense
																	// Reprocessing
																	// (group U)
	public static final String SUN_TYPES_AMEND_AUTH_CODE = "042"; // used by
																	// source
																	// Suspense
																	// Reprocessing
																	// (group U)
	public static final String SUN_TYPES_PAYMENT_REJECTS = "043";
	public static final String SUN_TYPES_PAYMENT_TRAN_ADJ = "044";
	//
	public static final String SUN_TYPES_INTR_FREE_CREDIT = "200"; // Used by
																	// Cekab -
																	// Interest
																	// Free
																	// Credit
																	// Function
	public static final String SUN_TYPES_CASH_ON_DELIVERY = "201"; // Used by
																	// Cekab -
																	// Cash on
																	// Delivery
																	// Function

	public static final String SUN_DOC_HARDCOPY = "010";
	public static final String SUN_DOC_MICORFILM = "011";
	public static final String SUN_DOC_FAX = "012";
	public static final String SUN_DOC_ORIG_PAPER = "013";
	public static final String SUN_DOC_RECEIPT_COPY = "014";
	public static final String SUN_DOC_NOT_APPLIC = "999";

	public static final String REF_OBJ_SERVICE_NEW = "002";
	public static final String REF_OBJ_SERVICE_EMERGENCY = "003";
	public static final String REF_OBJ_SERVICE_RENEWAL = "004";
	public static final String REF_OBJ_SERVICE_REPLACE = "005";
	public static final String REF_OBJ_PAYMENT_STATUS = "006";
	public static final String REF_OBJ_SERVICE_ACTIVE = "007";
	public static final String REF_OBJ_SERVICE_PIN_REISSUE = "008";
	public static final String REF_OBJ_SERVICE_REISSUE = "009";
	public static final String REF_OBJ_CARD_DELIVERY_EXPRESS = "010";
	public static final String REF_OBJ_FIRST_SERVICE = "011";
	public static final String REF_OBJ_ADDITIONAL_SERVICES = "012";

	public static final String REF_OBJ_CLOSING_BALANCE = "101";
	public static final String REF_OBJ_BATCH_PER_VALUE_DATE = "102";
	public static final String REF_OBJ_CLIENT_PER_VALUE_DATE = "103";
	public static final String REF_OBJ_FILE_PER_VALUE_DATE = "104";
	public static final String REF_OBJ_TRAN_PER_VALUE_DATE = "105";
	public static final String REF_OBJ_INC_AMT_ARREARS = "106";
	public static final String REF_OBJ_TOT_AMT_ARREARS = "107";
	public static final String REF_OBJ_CHGBCK_VALUE = "109";
	public static final String REF_OBJ_PRESENTMENT_VALUE = "111";
	public static final String REF_OBJ_INT_CHRGS_VALUE = "112"; // CONFLICT!!!
																// (used only by
																// Procard -
																// should be
																// changed)
	public static final String REF_OBJ_PRINCIPAL_AMOUNT = "112"; // CONFLICT!!!
	public static final String REF_OBJ_SOURCE_SUMM_VALUE = "113";
	public static final String REF_OBJ_PAYMENT_VALUE = "114";
	public static final String REF_OBJ_START_UP_CLOSING_BAL = "115";
	public static final String REF_OBJ_FINAL_BALANCE = "116"; // closing
																// balance of an
																// account for a
																// deceased
																// client
																// (status
																// STA_CUST_CLOSED_DECEASED)
	public static final String REF_OBJ_PAYMENT_DUE_DAYS = "117";
	public static final String REF_OBJ_ACCT_LIMIT_SETTL = "118"; // same as
																	// closing
																	// balance,
																	// but
																	// settlement
																	// is a
																	// percentage
																	// of limit
	public static final String REF_OBJ_SUB_BAL_CHARGES = "119";
	public static final String REF_OBJ_ALL_TRANSACTIONS = "120";
	public static final String REF_OBJ_SUB_BAL_CR_PAYMENTS = "121";
	public static final String REF_OBJ_REJECT_PAYMENT = "122";
	public static final String REF_OBJ_MERCH_COMMISION_TRANS = "123";
	public static final String REF_OBJ_OVERDUE_DAYS = "130";
	public static final String REF_OBJ_PREV_AMT_ARREARS = "131"; // account
																	// fees
																	// based on
																	// begin
																	// balance
																	// (prev
																	// paym) of
																	// payment
																	// history
	public static final String REF_OBJ_OVER_30_CLOSING_BAL = "132"; // trigger
																	// by
																	// closing
																	// balance;
																	// overdue
																	// by 30-59
																	// days
																	// (from
																	// cycle
																	// end)
	public static final String REF_OBJ_OVER_60_CLOSING_BAL = "133"; // trigger
																	// by
																	// closing
																	// balance;
																	// overdue
																	// by 60-89
																	// days
																	// (from
																	// cycle
																	// end)
	public static final String REF_OBJ_OVER_90_CLOSING_BAL = "134"; // trigger
																	// by
																	// closing
																	// balance;
																	// overdue
																	// by 90-119
																	// days
																	// (from
																	// cycle
																	// end)
	public static final String REF_OBJ_OVER_120_CLOSING_BAL = "135"; // trigger
																		// by
																		// closing
																		// balance;
																		// overdue
																		// by
																		// 120-149
																		// days
																		// (from
																		// cycle
																		// end)
	public static final String REF_OBJ_OVER_150_CLOSING_BAL = "136"; // trigger
																		// by
																		// closing
																		// balance;
																		// overdue
																		// by
																		// 150+
																		// days
																		// (from
																		// cycle
																		// end)
	public static final String REF_OBJ_CYCLE_BALANCE = "137"; // account fee
																// based on
																// (closing
																// balance -
																// begin
																// balance) of
																// cycle book
																// balance
	public static final String REF_OBJ_ACCOUNT_ACTIVITY = "138";
	public static final String REF_OBJ_ALWAYS = "140";
	public static final String REF_OBJ_REMINDER_LETTER = "141";
	public static final String REF_OBJ_BONUS_RULE = "150"; // Allround
															// Settlement (intra
															// acct): used with
															// cis_bonus_club_details
															// (AllRound) to
															// transfer from
															// bonus to
															// bonus-check acct
	public static final String REF_OBJ_TURNOVER_BALANCE = "151"; // Allround
																	// specific,
																	// used in
																	// settlement
																	// & fees.
																	// Closing-balance
																	// based,
	// fee applied in tiers and copied to bonus club. Settlement to bonus
	// club (intra-acct). Both triggered by bonus club//s settlement months.
	public static final String REF_OBJ_EXPIRED_BONUS_CHECKS = "152"; // Allround
																		// trigger
																		// to
																		// clear
																		// expired
																		// bonus
																		// checks
																		// to
																		// merchant
																		// acct
	public static final String REF_OBJ_CLOSED_MEMBER_BALANCE = "153"; // Allround
																		// trigger
																		// to
																		// clear
																		// the
																		// bonus
																		// balance
																		// of
																		// closed
																		// members
	//
	public static final String REF_OBJ_CLOSING_BAL_INTRA = "160"; // Normal
																	// closing
																	// balance
																	// trigger
																	// for use
																	// in
																	// intra-acct
																	// settlemnet
	// NOTE: the above do not make use of LAST_SETTLEMENT_DATE
	public static final String REF_OBJ_REPLENISH_FROM_DEBIT = "161"; // MINB
																		// intra-acct
																		// settlement
																		// trigger
																		// -
																		// replenish
																		// from
																		// debit
																		// acct
	// (having acct category: 001)
	public static final String REF_OBJ_REPLENISH_FROM_SEC_DEP = "162"; // MINB
																		// intra-acct
																		// settlement
																		// trigger
																		// -
																		// replenish
																		// from
																		// security
																		// dep.
																		// acct
	// (having acct category: 004)
	//
	public static final String REF_OBJ_ACCOUNT_NEW = "200";
	public static final String REF_OBJ_ACCOUNT_ANNIVERSARY = "201";
	public static final String REF_OBJ_OVERLIMIT_AMOUNT = "202";
	public static final String REF_OBJ_CLIENT_NEW = "203";
	public static final String REF_OBJ_CLIENT_ANNIVERSARY = "204";
	public static final String REF_OBJ_ACCOUNT_ANNUAL_FEE = "205";
	public static final String REF_OBJ_OVERLIMIT_PERCENT = "206";
	public static final String REF_OBJ_COMB_OVERLIMIT_AMOUNT = "207"; // Special
																		// overlimit
																		// amount
																		// calculation
																		// at
																		// Delta
																		// Bank
	public static final String REF_OBJ_EACH_POS_DEVICE = "210";
	public static final String REF_OBJ_NUMBER_OF_POS_DEVICE = "211";
	public static final String REF_OBJ_ACCT_LIMIT = "220"; // used for account
															// fee
	public static final String REF_OBJ_ACCOUNT = "221"; // added by Budapest
														// Office
	//
	public static final String REF_OBJ_INSTALLMENT_LOANS = "230";
	public static final String REF_OBJ_PERIODIC_REVENUE = "231";
	//
	public static final String REF_OBJ_ACCOUNT_TURNOVER = "250";
	public static final String REF_OBJ_COMMISSION_CYCLE = "251";
	public static final String REF_OBJ_DCC_TRANSACTIONS = "260"; // used for
																	// DCC
																	// Rebate
																	// account
																	// fee
																	// (Barclays
																	// only)
	//
	public static final String REF_OBJ_INTERCHANGE_FEE = "270";
	//
	public static final String REF_OBJ_CRD_SC_VISA_DOM_CPT = "272";
	public static final String REF_OBJ_CRD_SC_VISA_DOM_PER = "273";
	public static final String REF_OBJ_CRD_SC_ECMC_DOM_CPT = "274";
	public static final String REF_OBJ_CRD_SC_ECMC_DOM_PER = "275";
	public static final String REF_OBJ_CRD_SC_VISA_INTRA_CPT = "276";
	public static final String REF_OBJ_CRD_SC_VISA_INTRA_PER = "277";
	public static final String REF_OBJ_CRD_SC_ECMC_INTRA_CPT = "278";
	public static final String REF_OBJ_CRD_SC_ECMC_INTRA_PER = "279";
	public static final String REF_OBJ_CRD_SC_VISA_INTER_CPT = "280";
	public static final String REF_OBJ_CRD_SC_VISA_INTER_PER = "281";
	public static final String REF_OBJ_CRD_SC_ECMC_INTER_CPT = "282";
	public static final String REF_OBJ_CRD_SC_ECMC_INTER_PER = "283";
	//
	public static final String REF_OBJ_MERCH_VOLUME_FEE_DR = "284";
	public static final String REF_OBJ_MERCH_VOLUME_FEE_CR = "285";
	//
	public static final String REF_OBJ_STATEMENT_GENERATION = "300";
	public static final String REF_OBJ_STMNT_MAIN_PRINT_FILE = "301";
	public static final String REF_OBJ_STMNT_COPY_PRINT_FILE = "302";
	public static final String REF_OBJ_STATEMENT_EMAIL = "303";
	//
	public static final String REF_OBJ_TIER_NO_TRAN_YEAR = "400"; // used for
																	// tiered
																	// pricing
	public static final String REF_OBJ_TIER_VAL_LOC_TRAN_YEAR = "401";
	//
	public static final String REF_OBJ_SP_NEW_MERCHANTS = "450"; // range 450
																	// - 500
																	// reserved
																	// for
																	// Service
																	// Provide
																	// fee
																	// trigger
																	// sources
	public static final String REF_OBJ_SP_NUM_TRAN_YEAR = "451";
	public static final String REF_OBJ_SP_NUM_ELECT_APPR = "452";
	public static final String REF_OBJ_SP_NUM_ELECT_DECL = "453";
	public static final String REF_OBJ_SP_NUM_ELECT_REFR = "454";
	public static final String REF_OBJ_SP_NUM_ELECT_CANC = "455";
	public static final String REF_OBJ_SP_NUM_RETRIEVALS = "456";
	public static final String REF_OBJ_SP_NUM_CHARGEBACKS = "457";
	public static final String REF_OBJ_SP_NUM_VOICE_APPR = "458";
	public static final String REF_OBJ_SP_NUM_VOICE_DECL = "459";
	public static final String REF_OBJ_SP_NUM_VOICE_REFR = "460";
	public static final String REF_OBJ_SP_NUM_ARU_APPR = "461";
	public static final String REF_OBJ_SP_NUM_ARU_DECL = "462";
	public static final String REF_OBJ_SP_NUM_ARU_REFR = "463";
	public static final String REF_OBJ_MERCHANT_AUTH = "464";
	public static final String REF_OBJ_NUM_CHARGEBACKS = "465";
	public static final String REF_OBJ_SP_ADDITIONAL_ACCT = "466";
	public static final String REF_OBJ_SP_NUM_TRAN_CURR_CONV = "467";
	public static final String REF_OBJ_SP_NUM_BATCHES = "468";
	//
	public static final String REF_OBJ_NUM_ELECT_APPR = "469";
	public static final String REF_OBJ_NUM_ELECT_DECL = "470";
	public static final String REF_OBJ_NUM_ELECT_REFR = "471";
	public static final String REF_OBJ_NUM_ELECT_CANC = "472";
	public static final String REF_OBJ_NUM_VOICE_APPR = "473";
	public static final String REF_OBJ_NUM_VOICE_DECL = "474";
	public static final String REF_OBJ_NUM_VOICE_REFR = "475";
	public static final String REF_OBJ_NUM_ARU_APPR = "476";
	public static final String REF_OBJ_NUM_ARU_DECL = "477";
	public static final String REF_OBJ_NUM_ARU_REFR = "478";
	public static final String REF_OBJ_NUM_CHARGEBACKS_REV = "479";
	public static final String REF_OBJ_NUM_BATCHES = "480";
	public static final String REF_OBJ_MERCH_PROC_FEE_CYCLE = "481";
	public static final String REF_OBJ_NUM_AVS = "482";
	public static final String REF_OBJ_SP_NUM_AVS = "485";
	//
	public static final String REF_OBJ_SP_RESIDENCY = "499"; // last fee to
																// be generated
	//
	public static final String REF_PER_POSTING_DATE = "001"; // bwt_periodic_reference
	public static final String REF_PER_END_OF_CYCLE = "002";
	public static final String REF_PER_START_OF_CYCLE = "003";
	public static final String REF_PER_END_PREV_CYCLE = "004";
	public static final String REF_PER_TRANSACTION_DATE = "005";
	public static final String REF_PER_DAY_OF_MONTH = "006";
	public static final String REF_PER_DAY_OF_WEEK = "007";
	public static final String REF_PER_HALF_MONTHLY = "008";
	// ";009"; BiMonthly (1/3) ?? (check LUBJ) not implemented
	// ";010"; BiMonthly (1/4) ?? (check LUBJ) not implemented
	public static final String REF_PER_END_OF_MONTH = "011";
	public static final String REF_PER_END_OF_NEXT_CYCLE = "012";
	public static final String REF_PER_END_OF_NEXT_MONTH = "013";
	//
	public static final String OBJ_LEVEL_MAIN_ACCOUNT = "001";
	public static final String OBJ_LEVEL_SUB_ACCOUNT = "002";
	public static final String OBJ_LEVEL_BILLING_ACCOUNT = "003";
	public static final String OBJ_LEVEL_FIRST_SERVICE = "101";
	public static final String OBJ_LEVEL_SUPPL_SERVICE = "102";
	public static final String OBJ_LEVEL_GROUP_CLT = "201";
	public static final String OBJ_LEVEL_SUBGROUP_CLT = "202";
	public static final String OBJ_LEVEL_MEMBER_CLT = "203";
	public static final String OBJ_LEVEL_ALL = "999";

	public static final String SETT_CATG_PAYABLE = "001"; // bwt_settlement_category
	public static final String SETT_CATG_RECEIVABLE = "002";
	//
	public static final String SETTLM_CAT_PAYABLE = "001"; // bwt_settlement_category
	public static final String SETTLM_CAT_RECIEVABLE = "002";
	public static final String SETTLM_CAT_INTRA_ACCT = "003";
	public static final String SETTLM_CAT_REQUEST = "004";
	public static final String SETTLM_CAT_TRANSFER = "005";
	public static final String SETTLM_CAT_INSTALLMENTS = "006";
	public static final String SETTLM_CAT_INTRA_ACCT_STRT = "300"; // Used for
																	// additional
																	// Intra
																	// account
																	// settlement
	public static final String SETTLM_CAT_INTRA_ACCT_END = "399";

	public static final String FEE_CAT_ACCOUNT = "001"; // bwt_fee_category
	public static final String FEE_CAT_SERVICE = "002";
	public static final String FEE_CAT_TRANSACTION = "003";
	public static final String FEE_CAT_INTERCHANGE_PLUS = "004";
	public static final String FEE_CAT_INTERCHANGE_PASSTRU = "005";
	public static final String FEE_CAT_SOURCE_FEE_TO_DEST = "006";
	public static final String FEE_CAT_DCC_INTERCHANGE_PLUS = "007";
	//
	// ----Range 200-299 of bwt_fee_category reserved for multiple transaction
	// charge functionality----
	//
	public static final String AREA_FOREIGN = "001"; // bwt_area_of_action
	public static final String AREA_DOMESTIC = "002";
	public static final String AREA_ONUS = "003";
	public static final String AREA_INTRABANK = "004";
	//
	public static final String AREA_DOMESTIC_START = "100"; // Range 100 through
															// 199 is used for
	public static final String AREA_DOMESTIC_END = "199"; // detailed domestic
															// area of events
															// (user defined)
	//
	public static final String AREA_FOREIGN_START = "200"; // Range 200 through
															// 299 is used for
	public static final String AREA_FOREIGN_EMEA = "201"; // detailed foreign
															// area of events
															// (reserved)
	public static final String AREA_FOREIGN_CEMEA = "205";
	public static final String AREA_FOREIGN_REST = "202";
	public static final String AREA_FOREIGN_EURO = "203";
	public static final String AREA_FOREIGN_MASTER = "204";
	public static final String AREA_FOREIGN_EU = "210";
	public static final String AREA_FOREIGN_END = "299";
	//
	public static final String AREA_BW3_EXTERNAL = "300"; // Used for ONUS
															// which go to
															// another BW3
															// System (Bridge)
	//
	public static final String AREA_ONUS_EXTERNAL_START = "400"; // Range 400
																	// through
																	// 499 is
																	// reserved
																	// for ONUS
																	// cards
																	// which go
	public static final String AREA_ONUS_EXTERNAL_END = "499"; // to external
																// systems (user
																// defined)
	//
	// Range: 500-599 reserved for Merchant-specific ONUS area of events (used
	// to differentiate issuing charges)
	// Range: 600-699 reserved for Client tariff based ONUS area of events (used
	// to differentiate acquiring charges)
	//
	public static final String AREA_DOMESTIC_COUNTRY_START = "700"; // Range 700
																	// through
																	// 799 is
																	// used for
																	// country-based
																	// domestic
	public static final String AREA_DOMESTIC_COUNTRY_END = "799"; // area of
																	// events
																	// (cross
																	// border
																	// acquiring)
	//
	public static final String TARIFF_NA = "000"; // bwt_account_condition_set
	public static final String TARIFF_GENERAL = "908";
	public static final String TARIFF_GENERAL_LT_4 = "909";
	public static final String TARIFF_AUTHORIZED_LT_4 = "910";
	public static final String TARIFF_AUTHORIZED_LT_30 = "913";
	public static final String TARIFF_SECURED_LT_4 = "915";
	public static final String TARIFF_SECURED = "927";
	public static final String TARIFF_ELECTRONIC_LT_4 = "923";
	public static final String TARIFF_ELECTRONIC = "928";
	public static final String TARIFF_GENERAL_LT_5 = "905";
	public static final String TARIFF_AUTHORIZED_LT_5 = "911";
	public static final String TARIFF_GENERAL_LT_8 = "904";
	public static final String TARIFF_GENERAL_LT_20 = "903";
	public static final String TARIFF_PETROL = "906";
	public static final String TARIFF_AIRLINE = "907";
	public static final String TARIFF_AIRLINE_LT_5 = "902";
	public static final String TARIFF_AIRLINE_LT_8 = "901";
	public static final String TARIFF_AIRLINE_LT_20 = "900";
	public static final String TARIFF_GROCERY = "922";
	public static final String TARIFF_LIQOUR = "921";
	public static final String TARIFF_RATEII_5_NO_FUEL = "924"; // No used
	public static final String TARIFF_SECURED_LT_2 = "926"; // recycled from not
															// used
															// TARIFF_RATEII_5
	public static final String TARIFF_LARGE_30_NO_TE = "925";
	public static final String TARIFF_AUTHORIZED_LT_2 = "970";
	//
	public static final String TARIFF_AUTHORIZED = "971";
	public static final String TARIFF_SET_MERCHANT = "978";
	public static final String TARIFF_SEC_LT_4_PETROL = "966";
	public static final String TARIFF_SEC_LT_4_TRAVEL = "969";
	public static final String TARIFF_SEC_LT_4_SUPERMKT = "981";
	public static final String TARIFF_ELEC_LT_4_PETROL = "965";
	public static final String TARIFF_ELEC_LT_4_TRAVEL = "968";
	public static final String TARIFF_ELEC_LT_4_SUPERMKT = "980";
	public static final String TARIFF_GENERAL_PETROL = "964";
	public static final String TARIFF_GENERAL_TRAVEL = "967";
	public static final String TARIFF_GENERAL_SUPERMKT = "979";

	// New Tariff added 26/03/01 after reviewing EPI//s Interchange fee
	// calculation
	public static final String TARIFF_LARGE_TICKET = "930"; // instead of 925
	public static final String TARIFF_DATA_RATE_II = "931"; // instead of 924,
															// 926
	public static final String TARIFF_SECURED_LT_4_I = "932"; // Incentive
																// rate
	public static final String TARIFF_ELECTRONIC_LT_4_I = "933"; // Incentive
																	// rate
	public static final String TARIFF_GENERAL_I = "934"; // Incentive rate
	public static final String TARIFF_LATE_GT_46 = "935"; // ATM Late
															// presentments

	// New tariffs added for domestic UK and VISA Int 12/10/2001
	public static final String TARIFF_SECURED_LT_5 = "936"; // Secured Visa REST
															// rate/MC Inter
	public static final String TARIFF_AIRLINE_LT_10 = "937";
	public static final String TARIFF_UK_INTERNET_LT_3 = "938"; // internet EPI
																// rate
	public static final String TARIFF_UK_CNP_LT_2 = "939"; // Card not Present
															// VISA rate
	public static final String TARIFF_COMMERCIAL_VAT = "940";
	public static final String TARIFF_COMMERCIAL_SUM = "941";
	public static final String TARIFF_COMMERCIAL_LID = "942";
	public static final String TARIFF_UK_CAT_LT_2 = "944";
	public static final String TARIFF_UK_EHC_LT_2 = "945";
	public static final String TARIFF_SECURED_LT_3 = "946"; // Secured EPI/Visa
															// rate
	public static final String TARIFF_UK_CNP_LT_3 = "947"; // Card not Present
															// EPI rate
	public static final String TARIFF_UK_INTERNET = "949"; // internet VISA
															// rate

	// New tariffs added 02/07/2002 for US domestic car rental transactions
	public static final String TARIFF_CPS_HOTEL_AUTO_CP = "950";
	public static final String TARIFF_CPS_HOTEL_AUTO_CNP = "951";
	public static final String TARIFF_US_STANDARD = "952";
	public static final String TARIFF_US_EIRF = "953";
	public static final String TARIFF_CPS_RETAIL_CR = "972";
	public static final String TARIFF_CPS_ELECTRONIC = "992";
	//
	// UCAF Tariffs added 07/08/2002
	public static final String TARIFF_MERCHANT_UCAF = "960";
	public static final String TARIFF_FULL_UCAF = "961";
	public static final String TARIFF_MERCHANT_UCAF_I = "962";
	public static final String TARIFF_FULL_UCAF_I = "963";
	//
	// New tariff added for the MC Electronic Interchange Program ECCSS 02.1
	public static final String TARIFF_MC_ELECTRONIC_CRD = "948";
	//
	public static final String TARIFF_ALL = "999";
	//
	public static final String STA_SRC_CLIENT = "001"; //
	public static final String STA_SRC_CONTRACT = "002";
	public static final String STA_SRC_ACCOUNT = "003";
	public static final String STA_SRC_LIMIT = "004";
	public static final String STA_SRC_AGING = "005";
	public static final String STA_SRC_SERVICE = "006";
	//
	public static final String STA_CARD_INACTIVE = "000"; // bwt_card_status
	public static final String STA_CARD_ACTIVE = "001";
	public static final String STA_CARD_PU_LOST = "002";
	public static final String STA_CARD_PU_STOLEN = "003";
	public static final String STA_CARD_BLOCKED = "004";
	public static final String STA_CARD_ACTIVE_DEBLOCK = "005"; // for delta
																// bank
																// yugoslavia
	public static final String STA_CARD_BLOCKED_NEG_BAL = "006"; // for delta
																	// bank
																	// yugoslavia
	public static final String STA_CARD_BLOC_BANK_ORDR = "007"; // for delta
																// bank
																// yugoslavia
	public static final String STA_CARD_BLOC_DUPL_PAN = "008"; // for delta
																// bank
																// yugoslavia
	public static final String STA_CARD_TEMP_SUSPENDED = "009";
	public static final String STA_CARD_CLOSED = "010";
	public static final String STA_CARD_NOT_ACTIVE = "011";
	public static final String STA_CARD_GIFT_NOT_LOADED = "012";
	public static final String STA_CARD_CLOSED_SEND = "017";
	public static final String STA_CARD_CLOSED_REFRESH = "018";
	public static final String STA_CARD_HOT = "020";
	public static final String STA_CARD_WARM = "021";
	//
	public static final String STA_SERVICE_NA = "000"; // bwt_service_status
	public static final String STA_SERVICE_ACTIVE = "001";
	public static final String STA_SERVICE_CLOSED = "002";
	//
	public static final String STA_CARD_FOR_PRODUCTION = "001"; // bwt_card_prod_status
	public static final String STA_CARD_IN_PRODUCTION = "002";
	public static final String STA_CARD_PRODUCED = "003";
	public static final String STA_CARD_FEE_GENERATED = "004";
	public static final String STA_CARD_PROD_COMPLETED = "005";
	public static final String STA_CARD_FEES_ERROR = "006";
	public static final String STA_CARD_DECLINED = "007";
	public static final String STA_CARD_PRODUCTION_ERROR = "008";
	public static final String STA_CARD_PRE_PRODUCTION = "010";
	public static final String STA_CARD_ONLINE = "011";
	//
	//
	public static final String STA_APPL_CAPTURED = "001"; // bwt_application_status
	public static final String STA_APPL_APPROVED = "002";
	public static final String STA_APPL_DECLINED = "003";
	public static final String STA_APPL_PROCESSED = "004";
	public static final String STA_APPL_CANCELLED = "005";
	public static final String STA_APPL_ENTERED = "006";
	public static final String STA_APPL_FOR_AMEND = "007";
	public static final String STA_APPL_ERROR = "008";
	//
	public static final String STA_APPL_RESTRICTED_ID = "101"; // These four
																// application
	public static final String STA_APPL_RESTRICTED_PC = "102"; // statuses are
																// currently
	public static final String STA_APPL_RESTR_ID_CLOSED = "103"; // only used
																	// for
																	// ALLROUND
	public static final String STA_APPL_RESTR_PC_CLOSED = "104"; // by Appl
																	// Proc and
																	// De-Reg
	//
	public static final String STA_ACCT_FULLY_PAID = "000"; // used for accrual
															// (interest tier by
															// payment status)
	public static final String STA_ACCT_ACTIVE = "001"; // bwt_account_status
	public static final String STA_ACCT_CLOSED = "002";
	public static final String STA_ACCT_SUSPENDED = "003";
	// public static final String STA_ACCT_COLLECTION = "004";
	public static final String STA_ACCT_ACTIVE_BANKRUPT = "005"; // Used for
																	// acquiring
																	// Procard
	public static final String STA_ACCT_DORMANT = "005";
	public static final String STA_ACCT_OVER_30 = "006";
	public static final String STA_ACCT_OVER_60 = "007";
	public static final String STA_ACCT_OVER_90 = "008";
	public static final String STA_ACCT_OVER_120 = "009";
	public static final String STA_ACCT_CLOSED_BANKRUPT = "011"; // Used for
																	// acquiring
																	// Procard
	public static final String STA_ACCT_COLLECTION = "016";
	public static final String STA_ACCT_COLL_STOPPED = "017";
	public static final String STA_ACCT_FEE_EXEMPTED = "025";

	public static final String STA_CUST_NOT_ACTIVE = "000";
	public static final String STA_CUST_ACTIVE = "001"; // bwt_client_status
	public static final String STA_CUST_DORMANT = "002";
	public static final String STA_CUST_SUSPENDED = "003";
	public static final String STA_CUST_CLOSED = "004";
	public static final String STA_CUST_ACTIVE_BANKRUPT = "005";
	public static final String STA_CUST_ACTIVE_HOLD_PAY = "006";
	public static final String STA_CUST_CLOSED_NEW_OWNER = "010";
	public static final String STA_CUST_CLOSED_BANKRUPT = "011";
	public static final String STA_CUST_CLOSED_EMERGENCY = "012";
	public static final String STA_CUST_CLOSED_DECEASED = "013";
	public static final String STA_CUST_ACTIVE_CANCELLED = "014";
	public static final String STA_CUST_CANCELLED_OVERDUE = "015";
	public static final String STA_CUST_CLOSED_COLLECTION = "016";
	public static final String STA_CUST_CLOSED_SEND = "017";
	public static final String STA_CUST_CLOSED_REFRESH = "018";
	public static final String STA_CUST_RESTRICTED_POSTCODE = "019";
	//
	public static final String STA_PROC_IN_PROCESS = "001"; // bwt_processing_status
	public static final String STA_PROC_COMPLETED = "002";
	public static final String STA_PROC_ERROR = "003";
	public static final String STA_PROC_CURRENT = "004";
	public static final String STA_PROC_LOADED = "005";
	public static final String STA_PROC_UNMATCHED = "006";
	public static final String STA_PROC_ENTERED = "007";
	public static final String STA_PROC_APPROVED = "008";
	public static final String STA_PROC_ACCRUED = "009";
	public static final String STA_PROC_CAPITALIZED = "010";
	public static final String STA_PROC_ARCHIVED = "011";
	public static final String STA_PROC_CORRECTION = "012";
	public static final String STA_PROC_CLOSED = "015";
	public static final String STA_PROC_PURGED = "016";
	public static final String STA_PROC_CANCELLED = "017";
	public static final String STA_PROC_CLEARED = "018";
	public static final String STA_PROC_REJECTED = "019";
	public static final String STA_PROC_PENDING = "020";
	public static final String STA_PROC_AUTHORIZED = "021";
	public static final String STA_PROC_TIMEOUT = "022";
	public static final String STA_PROC_RESPONDED = "023";
	public static final String STA_PROC_DUPLICATE = "024";
	public static final String STA_PROC_DUPL_UNMATCHED = "025";
	public static final String STA_PROC_DUPL_UNMATCH_TOT = "026";
	public static final String STA_PROC_DUPL_UNMATCH_COMPL = "027";
	public static final String STA_PROC_DUPL_UNMATCH_TOT_COMPL = "028";
	public static final String STA_PROC_TRANSFERRED = "029";
	public static final String STA_PROC_REVERSED = "030";
	public static final String STA_PROC_FULFILLED = "031"; // Status used when
															// retrieval
															// requests are
															// fulfilled and
															// sent to scheme
	// by MasterCom or VIEW external systems. Fulfillment messages still need to
	// be loaded
	// in BW3 but suppressed and not picked by the exception processing.
	public static final String STA_PROC_VALIDATING = "201"; // Status used when
															// retrieval
															// requests are
															// fulfilled and
															// sent to scheme
	//
	public static final String STA_VAL_VALID = "001"; // bwt_validation_status
	public static final String STA_VAL_DELAYED = "030";
	public static final String STA_VAL_OUT_OF_TIMEFRAME = "031";
	public static final String STA_VAL_XML_ERROR = "050";
	public static final String STA_VAL_NOT_EXPECTED = "051";
	public static final String STA_VAL_STRUCTURAL_ERROR = "052";
	public static final String STA_VAL_FAILED_ARITHM_CHECKS = "053";
	public static final String STA_VAL_SOURCE_AMT_MISMATCH = "070";
	public static final String STA_VAL_ELECTRONIC_NOT_TAX_EVIDENCE = "071";
	public static final String STA_VAL_NOT_TAX_INVOICE = "072";
	public static final String STA_VAL_NOT_ORIGINAL = "073";
	public static final String STA_VAL_INVALID_SUPPLIER_VAT_NUMBER = "074";
	public static final String STA_VAL_MISSING_CUSTOMER_REF = "075";
	//
	public static final String STA_BIN_RANGE_ACTIVE = "001"; // bwt_bin_range_status
	//
	public static final String PROC_NAME_EPI_INW = "001"; // bwt_process_name
	public static final String PROC_NAME_CEKAB = "002";
	public static final String PROC_NAME_VISA_INW = "003";
	public static final String PROC_NAME_VISA_INT_OUT = "004";
	public static final String PROC_NAME_VISA_DOM_OUT = "005";
	public static final String PROC_NAME_EPI_OUT = "006";
	public static final String PROC_NAME_CONRAD_OUT = "007";
	public static final String PROC_NAME_FANTOM_OUT = "008";
	public static final String PROC_NAME_PAYMENT_PG_OUT = "009";
	public static final String PROC_NAME_PAYMENT_BG_OUT = "010";
	public static final String PROC_NAME_GL_OUT = "011";
	public static final String PROC_NAME_MERCH_PAYM = "013";
	public static final String PROC_NAME_ACCT_RESET = "014";
	public static final String PROC_NAME_FEES_INW = "015";
	public static final String PROC_NAME_VISA_BIN = "016";
	public static final String PROC_NAME_RB_POS_RECON_LOAD = "017";
	public static final String PROC_NAME_ENECUR = "018";
	public static final String PROC_NAME_SWE_DOM = "019";
	public static final String PROC_NAME_TKS_ONUS = "020";
	public static final String PROC_NAME_CREDIT_IN = "021";
	public static final String PROC_NAME_CONRAD_EXC_PROC = "022";
	public static final String PROC_NAME_CONRAD_FILE_LOAD = "023";
	public static final String PROC_NAME_CARD_FEES = "024";
	public static final String PROC_NAME_GL_OUT_PH1_SUMM = "025";
	public static final String PROC_NAME_EXCEPTION_PRC = "026";
	public static final String PROC_NAME_INTEREST_ACCRUAL = "027";
	public static final String PROC_NAME_INTEREST_CAPITALISATION = "028";
	public static final String PROC_NAME_VISA_OUT = "029"; // recycled process
															// number
															// PROC_NAME_ACCT_AGEING
															// that was no
															// longer in use
	public static final String PROC_NAME_CLOSE_CYCLE = "030";
	public static final String PROC_NAME_MERCH_STATEMENT = "031";
	public static final String PROC_NAME_TP2_DOWNLOAD = "032";
	// public static final String PROC_NAME_INTEREST_TKS_ACCRUAL = "033";
	// //process no longer in use
	public static final String PROC_NAME_READ_ORIG_MESSAGE = "033"; // a
																	// case-specific
																	// cleanup
																	// process
	public static final String PROC_NAME_BAL_ADJUST = "034";
	public static final String PROC_NAME_SAP_OUT = "035";
	public static final String PROC_NAME_MERCH_STATISTICS = "036";
	public static final String PROC_NAME_COPY_INT_OUT = "037";
	public static final String PROC_NAME_CSB_GL_FILE = "038";
	public static final String PROC_NAME_CSB_RECLASSIFY = "039";
	public static final String PROC_NAME_STOPLIST_DISTRIB = "040";
	public static final String PROC_NAME_REPROCESS_DEST_SUSP = "041";
	public static final String PROC_NAME_SOURCE_TRANSFER = "042";
	public static final String PROC_NAME_SAFE_INW = "043";
	public static final String PROC_NAME_GL_TRANS_INW = "044";
	public static final String PROC_NAME_PURGE_FILE = "045";
	public static final String PROC_NAME_ECCF_OUT = "046";
	public static final String PROC_NAME_MC_INET_OUT = "047";
	public static final String PROC_NAME_REPROCESS_RETURNS = "048";
	public static final String PROC_NAME_REPROCESS_SRC_SUSP = "049";
	public static final String PROC_NAME_RECONCILE_GL = "050";
	public static final String PROC_NAME_PURGE_PENDINGAUTH = "051";
	public static final String PROC_NAME_SAFE_OUT = "052";
	public static final String PROC_NAME_MOVE_FILE_TO_HISTORY = "053";
	public static final String PROC_NAME_ARCHIVE_FILE = "054";
	public static final String PROC_NAME_PAYMENT_NBK_REQ_OUT = "055";
	public static final String PROC_NAME_MC_INW = "056";
	public static final String PROC_NAME_STAT_OUT = "057"; // to be phased out
															// and replaced by
															// 365
	public static final String PROC_NAME_EPI_ACQ_STAT = "058";
	public static final String PROC_NAME_EPI_ISS_STAT = "059";
	public static final String PROC_NAME_VISA_ACQ_STAT = "060";
	public static final String PROC_NAME_VISA_ISS_STAT = "061";
	public static final String PROC_NAME_EPI_DPR_INW = "062"; // recycled
																// process
																// number
																// PROC_NAME_SVC_PROD
																// that was no
																// longer in use
	public static final String PROC_NAME_CREDIT_ADV_IN = "063";
	public static final String PROC_NAME_RBS_TRN_OUT = "064";
	public static final String PROC_NAME_PAYORD_OUT = "065";
	public static final String PROC_NAME_ACQUIRE_OUT = "066";
	public static final String PROC_NAME_MERCHANT_OUT = "067";
	public static final String PROC_NAME_CARDSACCTS_OUT = "068";
	public static final String PROC_NAME_CARD_FEES_OUT = "069";
	public static final String PROC_NAME_RBS_BALANCES_INW = "070";
	public static final String PROC_NAME_NATSTAT_OUT = "071";
	public static final String PROC_NAME_EMBOSS_OUT = "072";
	public static final String PROC_NAME_ENCODING_FILE = "073";
	public static final String PROC_NAME_APPLICATION_LOAD = "074";
	public static final String PROC_NAME_REVERSE_FILE = "075";
	public static final String PROC_NAME_PRC_MERCHANT_EMBOSS = "076";
	public static final String PROC_NAME_PRC_MERCHANT_LOYALTY = "077";
	public static final String PROC_NAME_VISA_ONUS_OUT = "078";
	public static final String PROC_NAME_VISA_EXCEPT_BRIDGE = "079";
	// 080 - see later (PROC_NAME_VP_RPT_COMPANY)
	public static final String PROC_NAME_NW_REJECT_DIR_DEBITS = "081";
	public static final String PROC_NAME_NW_GIRO_PAYM_RECVD = "082";
	public static final String PROC_NAME_NW_BKR = "083";
	public static final String PROC_NAME_NBK_GL = "084";
	public static final String PROC_NAME_VARA_ACCT_INFO_OUT = "085";
	public static final String PROC_NAME_BATCH_PROCESS = "086";
	public static final String PROC_NAME_CLIENT_PAYM = "087";
	public static final String PROC_NAME_KNET_BIN = "088";
	public static final String PROC_NAME_NW_SWL = "089";
	public static final String PROC_NAME_INSURANCE_RENEWALS = "090";
	public static final String PROC_NAME_CARD_PRE_PRODUCTION = "091";
	public static final String PROC_NAME_SCHEDULED_RENEWALS = "092";
	public static final String PROC_NAME_BW2_TRANSACTIONS = "093";
	public static final String PROC_NAME_INFOSPAN = "094";
	public static final String PROC_NAME_CLS_OUT = "095";
	public static final String PROC_NAME_CLS_IN = "096";
	public static final String PROC_NAME_OUTW_OCTAGON = "097";
	public static final String PROC_NAME_INW_OCTAGON = "098";
	public static final String PROC_NAME_OCR_PAYMENTS = "099";
	public static final String PROC_NAME_VARAZ_GL = "100";
	public static final String PROC_NAME_CSB_DATA_CONV = "101";
	public static final String PROC_NAME_PRC_INW_LOYALTY = "102";
	public static final String PROC_NAME_NATW_DD_OUT = "103";
	public static final String PROC_NAME_CBK_BAS = "104";
	public static final String PROC_NAME_ISS_BATCH_PROCESS = "105";
	public static final String PROC_NAME_PRC_VP_STATEMENT = "106";
	public static final String PROC_NAME_PRC_OCR_INPAY = "107";
	public static final String PROC_NAME_AUTOPAYMENT = "108";
	public static final String PROC_NAME_REGEN_VALUE_BAL = "109";
	public static final String PROC_NAME_PRC_CARD_BLOCK = "110";
	public static final String PROC_NAME_ATC_TRAN_OUT = "111";
	public static final String PROC_NAME_MERCHANT_PAYMENT = "112";
	public static final String PROC_NAME_JNB_POS_BATCH_IN = "113";
	public static final String PROC_NAME_NWB_AIRTIME_CH = "114";
	public static final String PROC_NAME_NWB_AIRTIME_TXN = "115";
	public static final String PROC_NAME_BAS_REPORT = "116";
	public static final String PROC_NAME_MIS_REPORT = "117";
	public static final String PROC_NAME_INET_ONUS_INW = "118";
	public static final String PROC_NAME_INET_ONUS_OUT = "119";
	public static final String PROC_NAME_ECCF_OUT_DOM = "120";
	public static final String PROC_NAME_BATCH_TRAN_LOADER = "121";
	public static final String PROC_NAME_VP_RPT_COMPANY = "080";
	public static final String PROC_NAME_VP_RPT_SUPP_USAGE = "122";
	public static final String PROC_NAME_VP_RPT_CARDHOLDER = "123";
	public static final String PROC_NAME_VP_RPT_EXCEPTION = "124";
	public static final String PROC_NAME_VP_RPT_COMMODITY = "125";
	public static final String PROC_NAME_FX_RATES_LOADER = "126";
	public static final String PROC_NAME_ACCT_STAT_SYNC = "127";
	public static final String PROC_NAME_CREDITSCORE_OUT = "128";
	public static final String PROC_NAME_EBANK_LIMIT_INW = "129";
	public static final String PROC_NAME_BNKRT_OTH_NEG_LIST = "130";
	public static final String PROC_NAME_PROV_PAYMENT_OUT = "131";
	public static final String PROC_NAME_PROV_BONUS_INW = "132";
	public static final String PROC_NAME_VISA_PURCH_OUT = "133";
	public static final String PROC_NAME_VARA_MERC_PAYMENT = "134";
	public static final String PROC_NAME_EXPN_RBS_INW = "135";
	public static final String PROC_NAME_EXPN_INK1_INW = "136";
	public static final String PROC_NAME_APACS29_INW = "137";
	public static final String PROC_NAME_APACS40_INW = "138";
	public static final String PROC_NAME_BACS_INW = "139";
	public static final String PROC_NAME_BACS_OUTW_CR = "140";
	public static final String PROC_NAME_POS_HYPERCOM_LOADER = "141";
	public static final String PROC_NAME_AMEX_OUT = "142";
	public static final String PROC_NAME_APPL_INTERFACE = "143";
	public static final String PROC_NAME_FIX_GL_001 = "144";
	public static final String PROC_NAME_GENERIC_STATEMENT = "145";
	public static final String PROC_NAME_PROCARD_POSTEN_INW = "146"; // same
																		// interface
																		// as
																		// CEKAB
	public static final String PROC_NAME_AUTOGIRO_BGP_CLIENT = "147";
	public static final String PROC_NAME_AUTOGIRO_BGP_PAY_REQ = "148";
	public static final String PROC_NAME_AUTOGIRO_BGP_INC_PAY = "149";
	public static final String PROC_NAME_AUTOGIRO_BGC_CLIENT = "150";
	public static final String PROC_NAME_AUTOGIRO_BGC_PAY_REQ = "151";
	public static final String PROC_NAME_AUTOGIRO_BGC_INC_PAY = "152";
	public static final String PROC_NAME_AUTOGIRO_PG_CLIENT = "153";
	public static final String PROC_NAME_AUTOGIRO_PG_CONFIRM = "154";
	public static final String PROC_NAME_AUTOGIRO_PG_PAY_REQ = "155";
	public static final String PROC_NAME_AUTOGIRO_PG_INC_PAY = "156";
	public static final String PROC_NAME_PROCARD_GENIDATA_INW = "157"; // same
																		// interface
																		// as
																		// CEKAB
	public static final String PROC_NAME_PROCARD_ISK_OUT = "158";
	public static final String PROC_NAME_EP_STOPLIST = "159";
	public static final String PROC_NAME_BMS_EAGLE_TRAN_OUT = "160";
	public static final String PROC_NAME_BMS_PEGA = "161";
	public static final String PROC_NAME_MASTERCARD_MATCH_OUT = "162";
	public static final String PROC_NAME_BNKRT_HOTLIST_OUT = "163";
	public static final String PROC_NAME_ACCRUALS_REVERSAL = "164";
	public static final String PROC_NAME_BNKRT_STOPCD_PAPER = "165";
	public static final String PROC_NAME_PAYMENT_FP_OUT = "166";
	public static final String PROC_NAME_BNKRT_PAPR_STOP_OUT = "167";
	public static final String PROC_NAME_BNKRT_BANK_INSUR_OUT = "168";
	public static final String PROC_NAME_BMS_OUT_MANUAL = "169";
	public static final String PROC_NAME_BNKRT_B24NEG_REFRESH = "170";
	public static final String PROC_NAME_ECCF_ONUS_OUT = "171";
	public static final String PROC_NAME_ECCF_EXCEPT_BRIDGE = "172";
	public static final String PROC_NAME_ECCF_INET_ONUS_OUT = "173";
	public static final String PROC_NAME_INET_EXCEPT_BRIDGE = "174";
	public static final String PROC_NAME_BNKRT_OPT_ARCHIVING = "175";
	public static final String PROC_NAME_BNKRT_B24PBF_OUT = "176";
	public static final String PROC_NAME_SUSPENSE_PAYM = "177";
	public static final String PROC_NAME_INTRABANK_INWARD = "178";
	public static final String PROC_NAME_MERCHANT_POS_INW = "179"; // Bankart
																	// Pos
	public static final String PROC_NAME_BNKRT_SKB_INW = "180"; // Bankart Pos
																// SKB
	public static final String PROC_NAME_BNKRT_MAESTRO_INW = "181"; // Bankart
																	// Pos
																	// Maestro
	public static final String PROC_NAME_BNKRT_MAXI_INW = "182"; // Bankart
																	// Pos Maxi
	public static final String PROC_NAME_BNKRT_ELEKTRO_INW = "183"; // Bankart
																	// Pos
																	// Elektrotehna
	public static final String PROC_NAME_BNKRT_KOPER_INW = "184"; // Bankart
																	// Pos Koper
	public static final String PROC_NAME_BMS_EAGLE_AUTH_OUT = "185";
	public static final String PROC_NAME_BNKRT_DAILY_GROSSAMT = "186";
	public static final String PROC_NAME_VISA_INW_VSS = "187";
	public static final String PROC_NAME_BNKRT_POSTED_BATCHES = "188";
	public static final String PROC_NAME_CIM_INTERFACE = "189";
	public static final String PROC_NAME_ILF_UPLOAD = "190";
	public static final String PROC_NAME_BNKRT_POSTED_POSFEE = "191";
	public static final String PROC_NAME_BKRT_MERCH_PAY_NLB = "192";
	public static final String PROC_NAME_BKRT_MERCH_PAY_OTH = "193";
	public static final String PROC_NAME_BNKRT_DEBIT_POSITION = "194";
	public static final String PROC_NAME_BATCH_TRAN_LOADER_MISC = "195";
	public static final String PROC_NAME_BANK_CLEARING = "196";
	public static final String PROC_NAME_MOVE_PAYMENTS = "197";
	public static final String PROC_NAME_BACS_OUTW_DR = "199";
	public static final String PROC_NAME_ACQ_TRAN_INW = "200"; // BW3 format
																// Acquirer
																// Inward Trans
	public static final String PROC_NAME_GEN_ISS_OUT_FILE = "201";
	public static final String PROC_NAME_GEN_ACQ_OUT_FILE = "202";
	public static final String PROC_NAME_CLIENT_AUTH_TRANS = "203";
	public static final String PROC_NAME_CIS_FINANCIAL_FILE = "204";
	public static final String PROC_NAME_RESET_REAPPLY = "205";
	public static final String PROC_NAME_BNKRT_B24CAF_OUT = "206";
	public static final String PROC_NAME_TZAPIS_STAND_ORDER = "207";
	public static final String PROC_NAME_R73TRAPB_STAND_ORDER = "208";
	public static final String PROC_NAME_NLB_DEBIT_CARD_OUT = "209";
	public static final String PROC_NAME_BATCH_TRANSFERS = "210";
	public static final String PROC_NAME_LOAD_POS_SCHLUMBERGER = "211";
	public static final String PROC_NAME_BNKRT_POS_FEE_SETTL = "212";
	public static final String PROC_NAME_TZAPIS_CONFIRM_PAY = "213";
	public static final String PROC_NAME_R73TRAPB_CONFIRM_PAY = "214";
	public static final String PROC_NAME_END_OF_CYCLE = "215";
	public static final String PROC_NAME_ING_CARD_STATUS_OUT = "216";
	public static final String PROC_NAME_ING_TRAN_DOWNLOAD = "217";
	public static final String PROC_NAME_ING_NORG_TRANSL = "218";
	public static final String PROC_NAME_STATISTIC_GENERATION = "219";
	public static final String PROC_NAME_MKB_CVFEE_UPLOAD = "220";
	public static final String PROC_NAME_TKRS_FULL_DWH = "221";
	public static final String PROC_NAME_TKRS_PARTIAL_DWH = "222";
	public static final String PROC_NAME_SYNC_PAYMENT_VAL_BAL = "223";
	public static final String PROC_NAME_PROCARD_EPOST_FILE = "224";
	public static final String PROC_NAME_ING_DELIVERY_REFRESH = "225";
	public static final String PROC_NAME_MKB_TELEBANK = "226";
	public static final String PROC_NAME_MKB_STOPLIST_VISA = "227";
	public static final String PROC_NAME_MKB_STOPLIST_EURO = "228";
	public static final String PROC_NAME_MKB_CARDDATA_OUT = "229";
	public static final String PROC_NAME_VISA_PLUS_BIN = "230";
	public static final String PROC_NAME_MKB_CBBDATA_OUT = "231";
	public static final String PROC_NAME_MKB_LETTERFILE_OUT = "232";
	// Boris: conflict - change to 240
	// public static final String PROC_NAME_IAPA_EMBOSS = "233";
	public static final String PROC_NAME_MERCH_PAYM_TRAN = "233";
	public static final String PROC_NAME_MERCH_PAYM_BALANCE = "234";
	public static final String PROC_NAME_ADDRESS_VALIDATION = "235";
	public static final String PROC_NAME_MDS_FILE_LOAD = "236";
	public static final String PROC_NAME_CLEANUP_PAYM_HISTORY = "237";
	public static final String PROC_NAME_INFOSPAN_CORP = "238";
	public static final String PROC_NAME_GSS_MERCH_PAYM_OUT = "239";
	public static final String PROC_NAME_IAPA_EMBOSS = "240";
	public static final String PROC_NAME_PG_ISS_STATEMENT = "241"; // reserved
																	// for
																	// future
																	// use -
																	// currently
																	// Provida
																	// statement
	public static final String PROC_NAME_APPL_BATCH_PROC_MISC = "242";
	public static final String PROC_NAME_CURR_RATES_UPDATE = "243";
	public static final String PROC_NAME_GSS_AGREEMENT_OUT = "244";
	public static final String PROC_NAME_GSS_AGREEMENT_INW = "245";
	public static final String PROC_NAME_GSS_ISS_PAYM_REQUEST = "246";
	public static final String PROC_NAME_GSS_ISS_PAYM_INW = "247";
	public static final String PROC_NAME_INSTALLMENT_GEN = "248";
	public static final String PROC_NAME_GSS_POS_SETTLE = "249";
	public static final String PROC_NAME_GSS_LOAD_PBS_LOGS = "250";
	public static final String PROC_NAME_GSS_MANUAL_PAYM_INW = "251";
	public static final String PROC_NAME_GSS_CPR_IN = "252";
	public static final String PROC_NAME_GSS_CPR_OUT = "253";
	public static final String PROC_NAME_MOBILECOM_IN = "254";
	public static final String PROC_NAME_APPL_BATCH_PROC_ACQ = "255";
	public static final String PROC_NAME_MOBILECOM_OUT = "256";
	public static final String PROC_NAME_MOBILECOM_AUTH = "257";
	public static final String PROC_NAME_GSS_ISS_BS_TOTAL = "258";
	public static final String PROC_NAME_AUTH_BATCH_PROC = "259";
	public static final String PROC_NAME_EXCEPTION_INTERFACE = "260";
	public static final String PROC_NAME_REJECT_TRANSACTIONS = "261";
	public static final String PROC_NAME_ECCF_INTER_OUT = "262";
	public static final String PROC_NAME_MDS_REPORTS = "263";
	public static final String PROC_NAME_COMSERV_ACQ_TRANS = "264";
	public static final String PROC_NAME_COMSERV_INC_TRANS = "265";
	public static final String PROC_NAME_MDS_LOAD_TRANS = "266";
	public static final String PROC_NAME_MDS_CONFIRM_TRANS = "267";
	public static final String PROC_NAME_MIS_INFOSPAN_PURCH = "268";
	public static final String PROC_NAME_MIS_INFOSPAN_CORP = "269";
	public static final String PROC_NAME_MDS_EXCP_TRANS = "270";
	public static final String PROC_NAME_EPI_DOMESTIC_INW = "271";
	public static final String PROC_NAME_GSS_TAX_OUT = "272";
	public static final String PROC_NAME_OMNIPAY_FX_RATES_IN = "273";
	public static final String PROC_NAME_VISA_TC57_INW = "274";
	public static final String PROC_NAME_GSS_DEBIT_TRANS_OUT = "275";
	public static final String PROC_NAME_COMSERVER_TRANS = "276";
	public static final String PROC_NAME_OMNI_AUTH_DATA = "277";
	public static final String PROC_NAME_CCB_CUST_FILE = "278";
	public static final String PROC_NAME_MERCHANT_STATISTICS = "279";
	public static final String PROC_NAME_REGEN_BAL_BY_ACCT = "280";
	public static final String PROC_NAME_REGEN_BAL_INPUT_ACCT = "281";
	public static final String PROC_NAME_MERCHANT_TIER_LEVEL = "282";
	public static final String PROC_NAME_CCB_CARD_FILE = "283";
	public static final String PROC_NAME_COLLECTION = "284";
	public static final String PROC_NAME_ACTIVITY_MONITOR = "285";
	public static final String PROC_NAME_SVC_PROVIDER_FEES = "286";
	public static final String PROC_NAME_HSBC_NON_RESIDENTS = "287";
	public static final String PROC_NAME_VISA_ECCF_CONV_INW = "288";
	public static final String PROC_NAME_HSBC_QCIF = "289";
	public static final String PROC_NAME_PAYMENT_AT_OUT = "290";
	public static final String PROC_NAME_ECCF_VISA_CONV_OUTW = "291";
	public static final String PROC_NAME_HSBC_MERCH_PAY = "292";
	public static final String PROC_NAME_HSBC_MERCH_PAY_OTH = "293";
	public static final String PROC_NAME_MC_MATCH_IN = "294";
	public static final String PROC_NAME_DAILY_MC_STOPLIST = "295";
	public static final String PROC_NAME_REGIONAL_MC_STOPLIST = "296";
	public static final String PROC_NAME_CLIENT_INTRA_PAYM = "297";
	public static final String PROC_NAME_CLIENT_OTHER_PAYM = "298";
	public static final String PROC_NAME_HSBC_PLAZA_BONUS = "299";
	public static final String PROC_NAME_HSBC_BRANCH_DEPOSIT = "300";
	public static final String PROC_NAME_HSBC_ATM_INW = "301";
	public static final String PROC_NAME_PAYMENT_BATCH = "302"; // Payment
																// batches with
																// ref 008 (used
																// by Axxess)
	public static final String PROC_NAME_HSBC_POS_MAINT_OUT = "303";
	public static final String PROC_NAME_REMINDER_PROCS = "304";
	public static final String PROC_NAME_INIT_TIER_STATISTICS = "305";
	public static final String PROC_NAME_HSBC_IMPRINTER_DTLS = "306";
	public static final String PROC_NAME_HSBC_CARDHOLDER_DB = "307"; // Initially
																		// ";Marsa
																		// Sports
																		// Club";
	public static final String PROC_NAME_REMINDER_CANC = "308";
	public static final String PROC_NAME_REMINDER_COLL = "309";
	public static final String PROC_NAME_DINERS_PURC_OUT = "310";
	public static final String PROC_NAME_OUTW_OCTAGON_SIS = "311";
	public static final String PROC_NAME_VUB_TRAN_INW = "312";
	public static final String PROC_NAME_GSS_REMINDER_FILE = "313";
	public static final String PROC_NAME_FDMS_80BS_INW = "314";
	public static final String PROC_NAME_FDMS_M045 = "315";
	public static final String PROC_NAME_HSBC_CAF = "316";
	public static final String PROC_NAME_VUB_CAF_OUT = "317";
	public static final String PROC_NAME_COS_BATCH = "318";
	public static final String PROC_NAME_HSBC_DB_DEPOSIT = "319";
	public static final String PROC_NAME_HSBC_QUIKCASH_DEBIT = "320";
	public static final String PROC_NAME_HSBC_MIMBOL = "321";
	public static final String PROC_NAME_HSBC_CAF_MIMBOL = "322";
	public static final String PROC_NAME_VUB_TRAN_OUT = "323";
	public static final String PROC_NAME_CZSB_CRU_COMMERCIAL = "324";
	public static final String PROC_NAME_FDMS_E336 = "325";
	public static final String PROC_NAME_HSBC_PBF = "326";
	public static final String PROC_NAME_CBK_EXC_OUT = "327";
	public static final String PROC_NAME_DINERS_CASH_OUT = "328";
	public static final String PROC_NAME_EPI_OER = "329";
	public static final String PROC_NAME_MTS_APPL_INTERFACE = "330";
	public static final String PROC_NAME_HSBC_THF = "331";
	public static final String PROC_NAME_FSPA_NEW_CARDHOLDER = "332";
	public static final String PROC_NAME_FSPA_REF_CARDHOLDER = "333";
	public static final String PROC_NAME_FSPA_DEREG_KUS = "334";
	public static final String PROC_NAME_HSBC_CACT = "335";
	public static final String PROC_NAME_MDC_BATCH_ALLROUND = "336";
	public static final String PROC_NAME_ALLROUND_AUTO_SUSP = "337"; // Close/Suspend
																		// memberships
	public static final String PROC_NAME_COS_NT_TRANSACTION = "338";
	public static final String PROC_NAME_COS_NT_BATCH_INPUT = "339";
	public static final String PROC_NAME_GEN_OTHER_OUT_FILE = "340";
	public static final String PROC_NAME_FSPA_BONUS_CHECK = "341";
	public static final String PROC_NAME_FDMS_AUTH_DATA_OUT = "342";
	public static final String PROC_NAME_ALLRND_ACQ_BCH_PROC = "343";
	public static final String PROC_NAME_VUB_GL_INTERFACE = "344";
	public static final String PROC_NAME_HSBC_ISS_STMT_INTER = "345";
	public static final String PROC_NAME_HSBC_MRC_STMT_INTER = "346";
	public static final String PROC_NAME_GFF_PAYM_INTERFACE = "347";
	public static final String PROC_NAME_OMNI_MAPS_OUT_FILE = "348";
	public static final String PROC_NAME_HSBC_DB_OUT_FILES = "349";
	public static final String PROC_NAME_FSPA_SPAR = "350";
	public static final String PROC_NAME_FSPA_DEREG_PRIME = "351";
	public static final String PROC_NAME_FSPA_SPAR_INW = "352";
	public static final String PROC_NAME_HSBC_RESIDUAL_ADVICE = "353";
	public static final String PROC_NAME_ICC_PAYM_REQUEST = "354";
	public static final String PROC_NAME_ICC_PAYM_RESPONSE = "355";
	public static final String PROC_NAME_BACS_OUTW_EUR_DR = "356";
	public static final String PROC_NAME_BACS_OUTW_EUR_CR = "357";
	public static final String PROC_NAME_HSBC_HUB_ACCT = "358";
	public static final String PROC_NAME_HSBC_HUB_CLIENT = "359";
	public static final String PROC_NAME_LIMIT_RENEWALS = "360";
	public static final String PROC_NAME_ING_ATLAS_PENDING = "361";
	public static final String PROC_NAME_IPM_MC_OUT = "362";
	public static final String PROC_NAME_IPM_ONUS_OUT = "363";
	public static final String PROC_NAME_IPM_ONUS_INW = "364";
	public static final String PROC_NAME_STAT_OUT_TKS_NEW = "365";
	public static final String PROC_NAME_IPM_MC_INW = "366";
	public static final String PROC_NAME_DLT_TRAN_DOWNLOAD = "367";
	public static final String PROC_NAME_DELTA_TRAN_INW = "368";
	public static final String PROC_NAME_DELTA_CARDS_OUTW = "369";
	public static final String PROC_NAME_DELTA_MERCHANT_OUT = "370";
	public static final String PROC_NAME_DELTA_ISS_PAYM_INW = "371";
	public static final String PROC_NAME_DELTA_ISS_PAYM_OUTW = "372";
	public static final String PROC_NAME_HSBC_CUSTOMER_INFO = "373";
	public static final String PROC_NAME_WELLS_REJECTS_INW = "374";
	public static final String PROC_NAME_WELLS_PAYMENT_OUT = "375";
	public static final String PROC_NAME_MKB_UPD_TARIFF = "376";
	public static final String PROC_NAME_DELTA_CARD_STAT_OUT = "377";
	public static final String PROC_NAME_ECB_ATM_REQUEST = "378";
	public static final String PROC_NAME_BACS_OUTW_MANDATE = "379";
	public static final String PROC_NAME_STMT_FAX_EMAIL = "380";
	public static final String PROC_NAME_IPM_BIN_LOAD = "381";
	public static final String PROC_NAME_EBC_RECON_REPORT = "382";
	public static final String PROC_NAME_FDMS_CASH_MGT_OUT = "383";
	public static final String PROC_NAME_BOV_INW = "384";
	public static final String PROC_NAME_BOV_OUT = "385";
	public static final String PROC_NAME_FINANCIAL_STATISTICS = "386";
	public static final String PROC_NAME_PAYMENT_TRAN_ADJ = "387";
	public static final String PROC_NAME_TRN_EXTR_BILL_PAYMT = "388";
	public static final String PROC_NAME_DELTA_SPLIT_TRAN_INW = "389";
	public static final String PROC_NAME_DELTA_TRAN_ACQ_INW = "390";
	public static final String PROC_NAME_MTS_DAY_VISA_SMS_PRC = "391";
	public static final String PROC_NAME_DELTA_MRCH_PAY_OUT = "392";
	public static final String PROC_NAME_JCB_XCHNG_OUT = "393";
	public static final String PROC_NAME_JCB_XCHNG_INW = "394";
	public static final String PROC_NAME_FX_RATES_VISA_IN = "395";
	public static final String PROC_NAME_FX_RATES_ECMC_IN = "396";
	public static final String PROC_NAME_CEKAB_OUT = "397";
	public static final String PROC_NAME_FDMS_80BS_DCC_INW = "398";
	public static final String PROC_NAME_CONSOLE = "399"; // used as a generic
															// process name for
															// any console
	public static final String PROC_NAME_CEKAB_HVE_BENCHMARK = "400";
	public static final String PROC_NAME_RECON_BEFORE_STAT = "401";
	public static final String PROC_NAME_RECON_AFTER_STAT = "402";
	public static final String PROC_NAME_HSBC_ATMP_REFRESH = "403";
	public static final String PROC_NAME_HSBC_ATMP_INW = "404"; // Note:
																// PROC_NAME_HSBC_ATM_INW
																// = "301"; has
																// to eventually
																// be deleted
																// when this one
																// will go life
	public static final String PROC_NAME_MKB_INS_STUDENT_OUT = "405";
	public static final String PROC_NAME_MKB_INS_ELECTRON_OUT = "406";
	public static final String PROC_NAME_MKB_INS_STANDARD_OUT = "407";
	public static final String PROC_NAME_HSBC_ATMP_DEBIT_FILE = "408";
	public static final String PROC_NAME_HSBC_ATMP_LANG_IND = "409";
	public static final String PROC_NAME_HSBC_ATMP_CCP = "410";
	public static final String PROC_NAME_MX2 = "411";
	public static final String PROC_NAME_IPM_UPD_BIN_LOAD = "412";
	public static final String PROC_NAME_MINB_250_REPORT = "413";
	public static final String PROC_NAME_AEGN_INW = "414";
	public static final String PROC_NAME_AEGN_OUT = "415";
	public static final String PROC_NAME_AMEXDC_TRN_ADDENDA = "416";
	public static final String PROC_NAME_AMEXDC_DEMOGRAPHIC = "417";
	public static final String PROC_NAME_AMEXDC_ATM_LOCATION = "418";
	public static final String PROC_NAME_AMEXDC_VOL_SUMMARY = "419";
	public static final String PROC_NAME_AMEX_FRAUD_OUT = "420";
	public static final String PROC_NAME_AMEX_FRAUD_INW = "421";
	public static final String PROC_NAME_VISA_STATISTICS = "422";
	public static final String PROC_NAME_MARB_RBS_ACCTDOWN = "423";
	public static final String PROC_NAME_MARB_RBS_CARDDOWN = "424";
	// public static final String PROC_NAME_RBBH_3 = "425"; //reserved for
	// market bank static data export 3
	public static final String PROC_NAME_RB_ISSUING = "569";
	public static final String PROC_NAME_RB_ACQUIRING = "570";
	public static final String PROC_NAME_RB_SHCLOG_LOAD = "578";
	public static final String PROC_NAME_FDMS_80BS_INW_2ND = "632";

	// CHG need to take the real value
	// public static final String PROC_NAME_BSF_DEI = "800";
	//
	public static final String PROD_TYP_NO_PRODUCTION = "000"; // bwt_production_types
																// (card event
																// statuses)
	public static final String PROD_TYP_DO_NOT_RENEW = "998";
	public static final String PROD_TYP_NO_PROD_OVERDUE = "999";
	// bwt_production_types
	public static final String PROD_TYP_NEW_CARD = "100";
	public static final String PROD_TYP_MANUAL_RENEWAL = "101";
	public static final String PROD_TYP_REPLACEMENT = "102";
	public static final String PROD_TYP_EMERGENCY_REPLACEMENT = "107";
	public static final String PROD_TYP_PIN_RE_ISSUE = "108";
	public static final String PROD_TYP_CARD_RE_ISSUE = "109";
	public static final String PROD_TYP_SCHEDULED_RENEWAL = "110";
	public static final String PROD_TYP_EXPRESS_CARD = "111";
	public static final String PROD_TYP_EMERGENCY_CARD = "112";
	public static final String PROD_TYP_OTHER_REPLACEMENT = "113";
	public static final String PROD_TYP_URGENT_NEW_CARD = "114";
	public static final String PROD_TYP_PIN_CHANGE = "115";
	public static final String PROD_TYP_PIN_DROP_OR_STOP = "116";
	//
	public static final String FIL_UNKNOWN = "001"; // bwt_file_type
	public static final String FIL_ACQ_POS = "002";
	public static final String FIL_ACQ_ATM = "003";
	public static final String FIL_ACQ_POS_ACK = "004";
	public static final String FIL_TEST = "005";
	public static final String FIL_ISS_PRESENTMENTS = "006";
	public static final String FIL_DETAILED_POSITIONS = "007";
	public static final String FIL_INCOMING_TRANSACTIONS = "008";
	public static final String FIL_OUTGOING_TRANSACTIONS = "009";
	public static final String FIL_GL_TRANSACTIONS = "010";
	public static final String FIL_STATEMENT = "011";
	public static final String FIL_DATA = "012";
	public static final String FIL_SETTLEMENT = "013";
	public static final String FIL_EXCEPTIONS = "014";
	public static final String FIL_BATCH_INPUT = "015";
	//
	public static final String SEQ_FILE_NUMBER = "001"; // bwt_sequence_number_ID
	public static final String SEQ_OUR_UNIQUE_REF_NO = "002";
	public static final String SEQ_REPORT_ID = "003";
	public static final String SEQ_GROUP_ID = "004";
	public static final String SEQ_DETAIL_ID = "005";
	public static final String SEQ_GL_FILE_NUMBER = "006";
	public static final String SEQ_BANK_SERIAL_NO = "007";
	public static final String SEQ_POSTGIRO_NO = "008";
	public static final String SEQ_EP_IRN_RETRVL = "009";
	public static final String SEQ_CLIENT_NUMBER = "010";
	public static final String SEQ_LAST_TEST_NO = "013";
	public static final String SEQ_RECORD_ID = "015";
	public static final String SEQ_APPLICATION_NO = "017";
	public static final String SEQ_GROUP_NUMBER = "018";
	public static final String SEQ_TRANSACTION_NO = "019";
	public static final String SEQ_CENTER_BATCH_ID = "020";
	public static final String SEQ_STATEMENT_NUMBER = "021";
	public static final String SEQ_EUROPAY_STAN = "022";
	public static final String SEQ_EP_IRN_CHGBACK = "023";
	public static final String SEQ_VISA_IRN_CHGBACK = "024";
	public static final String SEQ_VISA_IRN_RETRVL = "025";
	public static final String SEQ_ISS_FAN_FILE_NO = "026";
	public static final String SEQ_ISS_CON_FILE_NO = "027";
	public static final String SEQ_PRAEGE_FILE_NO = "028";
	public static final String SEQ_ISS_STATEMENT_NO = "029";
	public static final String SEQ_RBS_BASE_ACCT = "032";
	public static final String SEQ_COLLECTION = "035";
	public static final String SEQ_GL_BATCH_NO = "037";
	public static final String SEQ_GL_TRANSACTION_NO = "038";
	public static final String SEQ_OCTAGON_RECORD_ID = "039";
	public static final String SEQ_CSB_DAY_PARAMETER = "040";
	// public static final String SEQ_BATCH_INP_BATCH_NO = "041"; //obsolete...
	// may be reused
	public static final String SEQ_BATCH_INP_TRAN_NO = "042";
	public static final String SEQ_CLIENT_NUMBER_ISS = "043"; // HSBC require
																// separate
																// sequence id
																// for issuing
																// clients
	public static final String SEQ_ECB_ATM_REQUEST = "044"; // Egypt ECB for ATM
															// Request
	public static final String SEQ_MKB_PAYM_REQ = "050"; // MKB Payment
															// Request
	public static final String SEQ_FX_RATE_SEQ_NO = "051";
	public static final String SEQ_MATCH_REFERENCE = "065";
	public static final String SEQ_VOICE_AUTH_COUNTER = "066";
	public static final String SEQ_PGATLAS_NO = "067";
	public static final String SEQ_PROCESS_NO = "068";
	public static final String SEQ_OCTA_INPUT_SEQ_NO = "069";
	public static final String SEQ_BONUS_CHECK_NO = "070";
	public static final String SEQ_TEST = "999";
	//
	public static final String CONF_NO = "000"; // bwt_confirmation
	public static final String CONF_YES = "001";
	public static final String CONF_PENDING = "002";
	public static final String CONF_CONDITIONAL = "003";
	//
	public static final String SIGN_NA = "000";
	public static final String SIGN_CREDIT = "001"; // bwt_sign_operators
	public static final String SIGN_PLUS = "001";
	public static final String SIGN_DEBIT = "002";
	public static final String SIGN_MINUS = "002";
	public static final String SIGN_EQ = "003";
	public static final String SIGN_LT = "004";
	public static final String SIGN_GT = "005";
	public static final String SIGN_NE = "006";
	public static final String SIGN_LE = "007";
	public static final String SIGN_GE = "008";
	public static final String SIGN_BE = "009";
	public static final String SIGN_MULTIPLY = "010";
	public static final String SIGN_CONSTANT = "011";
	//
	public static final String PER_NA = "000"; // bwt_periodic_cycles
	public static final String PER_DAILY = "001";
	public static final String PER_WEEKLY = "002";
	public static final String PER_FORTNIGHT = "003";
	public static final String PER_WEEKLY_AVERAGE = "004";
	public static final String PER_MONTHLY = "005";
	public static final String PER_QUARTERLY = "006";
	public static final String PER_YEARLY = "007";
	// range 001 - 099 is user-definable and should not be hardcoded (except for
	// range 020-029)
	//
	public static final String PER_START_OF_CYCLE = "020";
	public static final String PER_END_OF_CYCLE = "021";
	public static final String PER_CONTRACT_INIT = "022"; // reserved for new
															// card fees. do not
															// use !!! [s.v.
															// 24/10/2000]
	public static final String PER_CONTRACT_ANNIVERSARY_2 = "023"; // same as
																	// 025, but
																	// excludes
																	// the first
																	// occurrence
																	// (0th.
																	// anniversary)
	public static final String PER_PAYM_DUE_DATE = "024";
	public static final String PER_SERVICE_ANNIVERSARY = "025";
	public static final String PER_CONTRACT_ANNIVERSARY = "026";
	public static final String PER_ACCOUNT_ANNIVERSARY = "027";
	public static final String PER_NEW_ACCOUNT = "028"; // added by Budapest
														// Office
	//
	public static final String MONTHLY_CYCLE_1 = "301";
	// range 301-399 is user-definable and should not be hardcoded
	//
	public static final String REPL_THRESH_1_MTH = "400"; // bwt_periodic_cycles
	public static final String REPL_THRESH_2_MTH = "401";
	public static final String REPL_THRESH_3_MTH = "402";
	public static final String REPL_THRESH_4_MTH = "403";
	//
	public static final String PER_BILLING_CYCLE_DATE = "501";
	//
	// range 600-630: reserved for system use (accrual / capitalization
	// frequencies)
	//
	public static final String PER_MONTH_END = "600"; // bwt_periodic_cycles
														// GROUP ";I";
	public static final String PER_CYCLE_END = "601";
	public static final String PER_MONTH_AND_CYCLE_END = "602";
	//
	public static final String TYPE_PRIVATE = "001"; // bwt_record_type (to
														// phase out.. see
														// RECORD_...)
	public static final String TYPE_COMMERCIAL = "002";
	public static final String TYPE_MERCHANT = "003";
	public static final String TYPE_INSTITUTION = "004";
	public static final String TYPE_ALL = "005";
	//
	public static final String CLT_TYPE_PRIVATE = "001"; // bwt_client_type
	public static final String CLT_TYPE_CORP_MERCH = "002";
	public static final String CLT_TYPE_INSTITUTION = "003";
	public static final String CLT_TYPE_GOV_AGENCY = "004";
	public static final String CLT_TYPE_GOV_OWNED = "005";
	public static final String CLT_TYPE_COMMERCIAL = "006";
	//
	public static final String ACCT_TYPE_GL_DESTINATION = "100"; // bwt_account_type_id
	public static final String ACCT_TYPE_GL_SOURCE = "101";
	public static final String ACCT_TYPE_GL_CHANNEL = "102";
	public static final String ACCT_TYPE_COLLECTION = "061";
	public static final String ACCT_TYPE_BONUS_CHECK = "143";
	//
	public static final String LEVL_NA = "000"; // bwt_client_level
	public static final String LEVL_MEMBER = "001";
	public static final String LEVL_GROUP = "002";
	public static final String LEVL_SUB_GROUP = "003";
	//
	public static final String LEVL_MAIN_ACCOUNT = "001"; // bwt_account_level
	public static final String LEVL_SUB_ACCOUNT = "002";
	//
	public static final String POST_METHOD_NET = "001"; // bwt_posting_method";
	public static final String POST_METHOD_GROSS_CHG = "002";
	//
	public static final String BUS_TYP_ISSUING = "001"; // bwt_client_business_type
	public static final String BUS_TYP_ACQUIRING = "002";
	public static final String BUS_TYP_CLEARING = "003";
	public static final String BUS_TYP_SUSPENSE = "004";
	public static final String BUS_TYP_GL = "005";
	//
	public static final String SERV_ISSUING = "001"; // bwt_service_category
	public static final String SERV_ACQUIRING = "002";
	public static final String SERV_CLEARING = "003";
	public static final String SERV_SUSPENSE = "004";
	public static final String SERV_INSURANCE = "005";
	public static final String SERV_TELEPHONY = "006";
	public static final String SERV_ACQUIRING_OTHER = "007";
	public static final String SERV_LOAN_INSURANCE = "008";
	public static final String SERV_FRAUD_INSURANCE = "009";
	public static final String SERV_REPORTING_ELECTR = "010";
	public static final String SERV_REPORTING_PAPER = "011";
	public static final String SERV_ALL = "999";
	//
	public static final String SERVICE_INFOSPAN = "900"; // bwt_service_id
	public static final String SERVICE_MIS_INFOSPAN = "909";
	public static final String SERVICE_ALL = "999"; // 800...899 reserved for
													// Procard (visa purch/corp
													// reports)
	// 900...999 reserved for internal use
	//
	public static final String SVC_CONTRACT_SUSPENSE = "990"; // bwt_service_contract_id
	//
	public static final String SERV_AVAIL_NA = "000"; // bwt_service_availability
	public static final String SERV_AVAIL_MANDATORY = "001";
	public static final String SERV_AVAIL_OPTIONAL = "002";
	//
	public static final String SERV_BENE_MEMBER = "001"; // bwt_service_beneficiary
	public static final String SERV_BENE_BILL_LEVEL = "002";
	public static final String SERV_BENE_NA = "003";
	public static final String SERV_BENE_GRP_ALL = "004";
	public static final String SERV_BENE_GRP_OPTION = "005";
	//
	// range 001-099 to be used for account billing cycles
	public static final String CYCLE_TYP_MONTHLY = "001"; // bwt_periodic_cycle_type
	public static final String CYCLE_TYP_HALF_MONTHLY = "002";
	public static final String CYCLE_TYP_DAILY = "003";
	public static final String CYCLE_TYP_WEEKLY = "004";
	public static final String CYCLE_TYP_HALF_MONTHLY_DOW = "005";
	public static final String CYCLE_TYP_MONTHLY_DOW = "006";
	public static final String CYCLE_TYP_SERVICE_RELATED = "007"; // used to
																	// make the
																	// account
																	// cycle
																	// coincide
																	// with the
																	// service
	public static final String CYCLE_TYP_MONTHLY_PBD = "008"; // Cycle date
																// must be a
																// businees
																// date,
																// otherwise use
																// Previous
																// Business Day
																// (PBD)
	// creation and expiry dates.
	public static final String CYCLE_TYP_PER_N_DAYS = "009"; // cycle end
																// occurring on
																// a base date +
																// every N days
																// thereon
	//
	// range 100-149 to be used for service expiry dates
	public static final String CYCLE_TYP_NUMBER_OF_MONTHS = "100"; // used for
																	// card
																	// expiry
																	// date
	public static final String CYCLE_TYP_FIXED_MM_YY = "101"; // used for
																// fixed card
																// expiry date
	//
	// range 150-199 to be used for interest capitalisation cycles
	public static final String CYCLE_TYP_CAPT_PER_N_MTHS = "150";
	//
	public static final String CHRG_FEE = "001"; // bwt_charge_type
	public static final String CHRG_COMMISSION = "002";
	public static final String CHRG_BONUS = "003";
	public static final String CHRG_REPORT = "004";
	public static final String CHRG_DISCOUNT = "005";
	//
	public static final String CHRG_ASSESSMENT = "010";
	public static final String CHRG_PER_TRAN = "011";
	public static final String CHRG_DELTA_SURCHARGE = "012";
	public static final String CHRG_DCC_ACQ_COMMIS = "013";
	public static final String CHRG_MERCH_COMMISSION = "014";
	//
	// Range 100 - 199 reserved for Procard RK functionality (refer CEKAB,
	// MERCSTAT)
	// public static final String CHRG_INTEREST_3_MONTH = "103"; //no longer
	// hardcoded - range 100-199 is reserved
	// public static final String CHRG_INTEREST_6_MONTH = "106";
	// public static final String CHRG_INTEREST_12_MONTH = "112";
	//
	// Range 200 - 299 reserved for multiple charge functionality (in
	// conjunction with cht_fee_category)
	//
	public static final String CHRG_CLR_FEE_IN = "900";
	public static final String CHRG_CLR_FEE_OUT = "901";
	public static final String CHRG_CLR_FEE_IN_OUT = "902";
	public static final String CHRG_NA = "999";
	//
	public static final String INTEREST_TIER_DEFAULT = "000"; // bwt_interest_tier
	//
	public static final String INTR_FORMULA_365_31 = "001"; // bwt_interest_formula
	public static final String INTR_FORMULA_360_30 = "002"; // 360/30 and 360/30
															// Daily are the
															// same
	public static final String INTR_FORMULA_360_30_DAILY = "002"; // both
																	// constants
																	// are being
																	// kept for
																	// backward
																	// compatibility
	public static final String INTR_FORMULA_360_30_MONTHLY = "003";
	//
	public static final String PROC_PHASE1 = "001"; // bwt_process_id
	public static final String PROC_PHASE2 = "002";
	public static final String PROC_PHASE3 = "003";
	public static final String PROC_PHASE4 = "004";
	public static final String PROC_POSTING = "005";
	public static final String PROC_TRN_CHARGES = "006";
	public static final String PROC_POSTING_SRC = "007";
	public static final String PROC_ACCT_SETTL = "008";
	public static final String PROC_EXCEPTIONS = "009";
	public static final String PROC_END_OF_CYCLE = "010";
	public static final String PROC_RECLASSIFY_PH_1 = "011"; // CSB/VUB GL
																// only
	public static final String PROC_RECLASSIFY_PH_2 = "012"; // CSB/VUB GL
																// only
	public static final String PROC_POST_SETTL_INC = "013"; // Posting ECCF
															// Incoming DPRs
															// (using incoming
															// batch file)
	public static final String PROC_POST_SETTL_OUT = "014"; // Posting ECCF
															// Outgoing DPRs
	public static final String PROC_GL_OUTPUT = "015";
	public static final String PROC_CLIENT_FEES = "016";
	public static final String PROC_AUTHORISATIONS = "017"; // Used by comserver
	public static final String PROC_BIN_TABLE_LOAD = "018";
	public static final String PROC_POST_SETTL_FEES = "019"; // Posting ECCF
																// Settlement
																// Fees (Fax,
																// Settl, Trnsf)
	public static final String PROC_POST_SETTL_VSS = "020"; // Posting VISA
															// Settlement
	public static final String PROC_POST_SETTL_INC_DPR = "021"; // Posting ECCF
																// Incoming DPRs
																// (using DPR
																// file)
	public static final String PROC_COPY_TRAN = "022";
	public static final String PROC_CARD_PRODUCTION_NEW = "023"; // This to
																	// resolve
																	// the
																	// conflict
																	// between
																	// Post ECCf
																	// Out and
																	// Card
																	// Production
	public static final String PROC_GL = "024"; // Used where it is necessary to
												// have separate fx rules for GL
	public static final String PROC_INTEREST_GENERATION = "025"; // Used to
																	// define GL
																	// rules for
																	// accrued
																	// interest
																	// & int.
																	// below
																	// threshold
	public static final String PROC_MERCH_COMMIS_ADJUST = "026";
	public static final String PROC_REMINDER_PROCESSING = "027";
	public static final String PROC_RECLASSIFY_PH_3 = "028"; // CSB/VUB GL
																// only
	public static final String PROC_RECLASSIFY_PH_4 = "029"; // CSB/VUB GL
																// only
	public static final String PROC_DCC_CONVERSION_VISA = "030"; // DCC
																	// Currency
																	// Conversion
																	// for Visa
	public static final String PROC_DCC_CONVERSION_ECMC = "031"; // DCC
																	// Currency
																	// Conversion
																	// for ECMC
	public static final String PROC_BATCH_INTERFACE = "900"; // Batch process
																// interface -
																// Process
																// Message
																// Logging
	public static final String PROC_FORM = "901"; // Form - Process Message
													// Logging
	public static final String PROC_PARENT = "902"; // Parent process - Process
													// Message Logging
	//
	public static final String PROC_CARD_PRODUCTION = "014"; // Conflict
	//
	//
	public static final String CURR_TYPE_ACCOUNT = "001"; // bwt_currency_type
	public static final String CURR_TYPE_LOCAL = "002";
	public static final String CURR_TYPE_SETTLEMENT = "003";
	public static final String CURR_TYPE_TRANS = "004";
	//
	public static final String AMOUNT_GROSS = "001"; // bwt_amount_type
	public static final String AMOUNT_INW_CHARGE = "002";
	public static final String AMOUNT_INW_NET = "003";
	public static final String AMOUNT_OUT_CHARGE = "004";
	public static final String AMOUNT_OUT_NET = "005";
	//
	public static final String CURR_CAD = "124";
	public static final String CURR_JAPAN = "392";
	public static final String CURR_JORDAN = "400";
	public static final String CURR_SAR = "682";
	public static final String CURR_SWEDEN = "752"; // bwt_currency
	public static final String CURR_USD = "840";
	public static final String CURR_EURO = "978";
	public static final String CURR_UKRAINE = "980";
	public static final String CURR_EUROZONE = "997";
	public static final String CURR_NA = "998";
	public static final String CURR_ALL = "999";
	//
	public static final String VISA_BASE_II = "003";
	public static final String CLEARING_SERVICE = "003";
	//
	public static final String INC_CEKAB = "019";
	public static final String FILE_LOG_NO = "001";
	//
	public static final String PRC_IN_PROCESS = "001";
	public static final String PRC_COMPLETED = "002";
	public static final String PRC_ERROR = "003";
	public static final String PRC_LOADED = "005";
	public static final String PRC_CANCELLED = "017";
	public static final String PRC_VALIDATING = "201";
	//
	public static final String TRAN_SLIP_NO = "019";
	public static final String ACQURING_CLNT_TYP = "002";
	public static final String ACQURING_SERVICE = "002";
	public static final String INTERCHANGE_FEES = "001";
	public static final String MERCHANT_COMMISSION = "002";
	//
	public static final String FILE_NO = "001";
	public static final String INC_ECCF_20 = "001";
	public static final String INTERNAL_ONUS = "018";
	public static final String SETT_GRP_CTRL = "007";
	public static final String DPR_GRP_CTRL = "006";
	public static final String SETT_TRAN = "005";
	public static final String SETT_GRP_INF = "019";
	public static final String MASTERCARD = "004";
	public static final String EUROPAY = "003";
	public static final String VISA = "002";
	//
	public static final String CRD_EC_MC = "001"; // bwt_card_brand
	public static final String CRD_CIRRUS = "002";
	public static final String CRD_EDC_MAESTRO = "003";
	public static final String CRD_EUROCHEQUE = "004";
	public static final String CRD_VISA = "005";
	public static final String CRD_VISA_PLUS = "006";
	public static final String CRD_ECHA_CIRRUS = "008";
	public static final String CRD_VISA_ELECTRON = "007";
	public static final String CRD_CARTE_BANCAIRE = "009";
	public static final String CRD_MASTERCARD = "010";
	public static final String CRD_MAESTRO = "011";
	public static final String CRD_EDC = "012";
	public static final String CRD_DINA_CARD = "102"; // Serbia&Montenegro
														// Proprietary card
	public static final String CRD_VUB_CREDIT_CARD = "103"; // VUB proprietary
															// card
	public static final String CRD_AUSTRALIAN_BC = "104"; // Australian
															// BankCard
															// processing
	public static final String CRD_AUSTRALIAN_DC = "105"; // Australian
															// DebitCard
															// processing

	// Card brands 013 to 014 reserved for Procard Proprietary
	public static final String CRD_AMEX = "015";
	public static final String CRD_DINERS = "016";
	// Card brands 017 to 027 reserved for Procard Proprietary
	public static final String CRD_VISA_BUSINESS = "028";
	public static final String CRD_VISA_CORPORATE = "029";
	public static final String CRD_VISA_PURCHASING = "030";
	public static final String CRD_VISA_TRAVEL_MONEY = "031";
	public static final String CRD_MC_CORP_BUSS = "032";
	public static final String CRD_MC_CORP_PURCHASE = "033";
	public static final String CRD_MC_CORP_FLEET = "034";
	// Card brand 035 reserved for Procard Proprietary
	public static final String CRD_EDCN = "036";
	public static final String CRD_CIRRUS_MAESTRO = "037";
	public static final String CRD_EC_COMMERCIAL = "050";
	public static final String CRD_VISA_DELTA = "051";
	public static final String CRD_VISA_COMMERCE = "052";
	public static final String CRD_MC_EURO_CORPORATE = "060";
	public static final String CRD_MC_WORLD_SIGNIA = "061";
	public static final String CRD_VISA_INFINITY = "062";
	public static final String CRD_MC_ELECTRONIC_CRD = "063";
	public static final String CRD_EP_CLIP = "064";
	public static final String CRD_JCB = "065";
	//
	// Range 000-099 is reserved for BW3 standard card brands
	// Range 100-199 may be used for any installation specific proprietary
	// brands
	//
	public static final String ORG_NA = "000"; // bwt_card_organization
	public static final String ORG_EUROPAY = "001";
	public static final String ORG_MASTERCARD = "002";
	public static final String ORG_VISA = "003";
	public static final String ORG_AMEX = "004";
	public static final String ORG_PROPRIETARY = "005";
	public static final String ORG_DINERS = "006";
	// 007-to-019 reserved for Procard Proprietary organizations
	// use 100-to-199 for proprietary (may overlap between various
	// installations)
	public static final String ORG_MDS = "020";
	public static final String ORG_VISA_SMS = "021";
	public static final String ORG_INTRABANK = "100";
	public static final String ORG_BOV = "101";
	public static final String ORG_AUSTRALIA_DEBIT = "102";
	public static final String ORG_AUSTRALIA_CREDIT = "103";

	public static final String ORG_ONUS = "200";
	public static final String ORG_ALL = "999";
	//
	public static final String VISA_SETTL_FLAG_INT = "0";
	public static final String VISA_SETTL_FLAG_CLEAR_ONLY = "3";
	public static final String VISA_SETTL_FLAG_DOM = "8";
	public static final String VISA_SETTL_FLAG_DEFAULT = "9";
	public static final String VISA_SETTL_FLAG_BW3_INT = "X"; // Bridge -
																// transactions
																// to be sent to
																// Visa Domestic
	public static final String VISA_SETTL_FLAG_BW3_DOM = "Y"; // Bridge -
																// transactions
																// to be sent to
																// Visa
	public static final String VISA_SETTL_FLAG_BW3_INTERNAL = "Z"; // Bridge -
																	// transactions
																	// to be
																	// sent to /
																	// received
																	// from Onus
																	// channels
																	// (e.g.
																	// Cekab)
	//
	public static final String COUNTRY_CANADA = "124"; // bwt_country
	public static final String COUNTRY_AUSTRALIA = "036";
	public static final String COUNTRY_GERMANY = "280";

	public static final String COUNTRY_GCC_BH = "048";
	public static final String COUNTRY_GCC_KW = "414";
	public static final String COUNTRY_GCC_OM = "512";
	public static final String COUNTRY_GCC_QA = "634";
	public static final String COUNTRY_GCC_AE = "784";

	public static final String COUNTRY_SWEDEN = "752";
	public static final String COUNTRY_SWITZERLAND = "756";
	public static final String COUNTRY_UKRAINE = "804";
	public static final String COUNTRY_UNITED_KINGDOM = "826";
	public static final String COUNTRY_UNITED_STATES = "840";
	public static final String COUNTRY_NA = "998";
	public static final String COUNTRY_ALL = "999";
	//
	public static final String FX_CATEG_NA = "000"; // bwt_fx_rate_category
	public static final String FX_CATEG_OWN = "001";
	public static final String FX_CATEG_VISA = "002";
	public static final String FX_CATEG_EUROPAY = "003";
	public static final String FX_CATEG_MASTER = "004";
	public static final String FX_CATEG_ENECUR = "006";
	public static final String FX_CATEG_EUROPAY_EURO = "800";
	public static final String FX_CATEG_EURO = "900"; // fixed eurozone
														// currency rates
	public static final String FX_CATEG_PEGGED = "901"; // pegged currencies
														// (e.g. to USD, GBP)
	public static final String FX_CATEG_CARD_SCHEME = "910"; // special
																// category
																// (refer code)
	//
	public static final String TEST_MODE = "Y";
	public static final String PRODUCTION_MODE = "N";
	//
	public static final String INST_BASE_SYSTEM = "0000"; // Installation
															// Numbers -
															// sys_institution_licence
	public static final String INST_PROCARD = "0001";
	public static final String INST_TELEKURS = "0002";
	public static final String INST_NATWEST = "0003";
	public static final String INST_CZECHSB = "0004";
	public static final String INST_ZIVNOSTKA = "0005";
	public static final String INST_VARAZDINSKA = "0006";
	public static final String INST_NBK = "0007";
	public static final String INST_JONB = "0008";
	public static final String INST_CRISTIANA = "0009"; // project discontinued
	public static final String INST_PROCARD_ISSUER = "0010";
	public static final String INST_LCARD_CENTRE = "0011"; // Almawarid
	public static final String INST_LCRC = "0012"; // Lebanon & Gulf Bank
	public static final String INST_LUFT = "0013"; // Lufthansa
	public static final String INST_EBANK = "0014";
	public static final String INST_VARBANK = "0015"; // project discontinued
	public static final String INST_EXPANDIA = "0016";
	public static final String INST_BARCLAYS_MS = "0017";
	public static final String INST_LJUBLJANSKA = "0018";
	public static final String INST_MARKET_BANK = "0019";
	public static final String INST_BOLIVIA_ATC = "0020"; // project
															// discontinued
															// (BankworksX)
	public static final String INST_MAGYAR_KB = "0021";
	public static final String INST_ING = "0022";
	public static final String INST_GSS = "0023"; // Denmark - Grafium
	public static final String INST_ICC = "0024"; // International Card Centre
	public static final String INST_ARAB_LAND_BANK = "0025"; // Palestine -
																// Arab Land
																// Bank
	public static final String INST_OMNIPAY = "0026"; // Omnipay
	public static final String INST_AXXESS_BAHAMAS = "0027"; // Axxess
																// Bahamas
	public static final String INST_HSBC_MALTA = "0028"; // Hsbc (Malta)
	public static final String INST_SLOVAKIA_ACS = "0029"; // Slovakia ACS
	public static final String INST_NCR_EGYPT = "0030"; // Egypt
	public static final String INST_GTP_UK = "0031"; // GTP - service
														// provider, UK
	public static final String INST_YUGOSLAVIA_MTS = "0032"; // MTS - service
																// provider,
																// Belgrade,
																// Yugoslavia
	public static final String INST_ALLROUND = "0033"; // GSS client (separate
														// instance)
	public static final String INST_DELTA_YU = "0034"; // Yugoslavia Delta Bank
	public static final String INST_EALB_EGYPT = "0035"; // Egypt - EALB
	public static final String INST_JORDAN_PAYM_CNTR = "0036"; // Jordan -
																// Payment
																// Centric
	public static final String INST_CFT = "0037"; // CFT
	public static final String INST_ECC_EGYPT = "0038"; // Egypt - ECC
	public static final String INST_MINB_MOSCOW = "0039"; // Moscow - MINB
	public static final String INST_EDBE_EGYPT = "0040"; // Egypt - EDBE

	//
	public static final String MSG_TYPE_ERROR = "Error";
	public static final String MSG_TYPE_DBERROR = "DBError";
	public static final String MSG_TYPE_INFO = "Info";
	public static final String MSG_TYPE_WARNING = "Warning";
	public static final String MSG_TYPE_FATAL = "Fatal Error";
	//
	public static final String FMT_SGN_SYM_DEFAULT = "-+";
	public static final String FMT_SGN_SYM_DR_CR = "DC";
	public static final String FMT_SGN_SYM_MINUS = "-";
	//
	public static final String ACCOUNT_MAIN = "001"; // bwt_account_level
	public static final String ACCOUNT_SUB = "002";
	//
	public static final String RECORD_PRIVATE = "001"; // bwt_record_type
	public static final String RECORD_COMMERCIAL = "002";
	public static final String RECORD_MERCHANT = "003";
	public static final String RECORD_INSTITUTION = "004";
	public static final String RECORD_ALL = "005";
	public static final String RECORD_PRIVATE_EXT = "051";
	public static final String RECORD_COMMERC_EXT = "052";
	public static final String RECORD_MERCHANT_EXT = "053";
	public static final String RECORD_MERCH_HIER = "073";
	public static final String RECORD_BILL_LEVEL = "080";
	public static final String RECORD_TEMP_CARDS = "090";
	public static final String RECORD_VB_BASE = "100"; // value balance record
														// type (group V)
	public static final String RECORD_VB_CURRENT = "101";
	//
	//
	public static final String ADR_CATG_STANDARD = "001"; // bwt_address_category
	public static final String ADR_CATG_TRADE = "002";
	public static final String ADR_CATG_LEGAL = "003";
	public static final String ADR_CATG_VISITING = "004";
	public static final String ADR_CATG_STATEMENT = "006";
	public static final String ADR_CATG_ALTERNATE = "007";
	public static final String ADR_CATG_CARD = "008"; // added by Budapest
														// Office
	public static final String ADR_CATG_PIN = "009"; // added by Budapest
														// Office
	public static final String ADR_CATG_ERROR = "010";
	public static final String ADR_CATG_POSTAL = "011";
	public static final String ADR_CATG_STATEMENT_COPY = "013";
	public static final String ADR_CATG_DISPUTE = "200";
	//
	public static final String SVC_TYPE_CREDIT = "001"; // bwt_service_type
	public static final String SVC_TYPE_DEBIT = "002";
	public static final String SVC_TYPE_CHARGE = "003";
	public static final String SVC_TYPE_ALL = "999";
	//
	public static final String SYNTH_EXT_NOT_APPLIC = "000"; // bwt_synth_ext_source
	public static final String SYNTH_EXT_OUT_CHANNEL = "001";
	public static final String SYNTH_EXT_TRAN_TYPE = "002";
	public static final String SYNTH_EXT_INC_CHANNEL = "003";
	public static final String SYNTH_EXT_USER_DEFINED = "004";
	public static final String SYNTH_EXT_INTRA_INST = "005";
	//
	public static final String REPL_METH_INT_FEE_CASH_PURCH = "001";
	public static final String REPL_METH_CASH_PURCH_FEES = "002";
	//
	public static final String ACCT_CATG_INTERNAL_ACCT = "005";
	public static final String ACCT_CATG_PRODUCT_ACCT = "006";
	public static final String ACCT_CATG_P_AND_L_ACCT = "007";
	//
	public static final String TRN_DATA_LOCATION_NA = "000";
	public static final String TRN_DATA_LOCATION_CURRENT = "001";
	public static final String TRN_DATA_LOCATION_HISTORY = "002";
	public static final String TRN_DATA_LOCATION_ARCHIVE = "003";
	//
	public static final String LIMIT_DIST_TYPE_SHARED = "001"; // bwt_limit_distribution_types
	public static final String LIMIT_DIST_TYPE_ALLOCATED = "002";
	public static final String LIMIT_DIST_TYPE_INCR = "003";
	//
	// PIN required
	public static final String PIN_REQUIRED_NO = "001";
	public static final String PIN_REQUIRED_NEW = "002";
	public static final String PIN_REQUIRED_SAME = "003";
	public static final String PIN_REQUIRED_ONLINE = "004";
	public static final String PIN_REQUIRED_ONLINE_YES = "005";
	//
	public static final String ISSUER_BATCH = "001";
	public static final String ACQUIRER_BATCH = "002";
	public static final String OUTPUT_BATCH = "003";
	public static final String TRANSFER_BATCH = "004";
	public static final String APPL_PROC_MISC_BATCH = "005";
	public static final String APPL_PROC_ACQ_BATCH = "006";
	public static final String AUTH_BATCH = "007";
	public static final String PAYMENT_BATCH = "008"; // AXXESS payment batch
														// input (including
														// value date)
	public static final String ALLROUND_ACQ_BATCH = "009";
	//
	public static final String CLIENT_REF_TYPE_RBS = "101"; // bwt_client_reference_type
	public static final String CLIENT_REF_TYPE_AMEX = "201";
	public static final String CLIENT_REF_TYPE_VISA = "202";
	public static final String CLIENT_REF_TYPE_MASTERCARD = "203";
	public static final String CLIENT_REF_MERCH_REF_UKDM = "204";
	public static final String CLIENT_REF_CASH_MAN_FILE = "205";
	public static final String CLIENT_REF_DINERS = "206";
	public static final String CLIENT_REF_ASIC = "207";
	public static final String CLIENT_REF_REFUND_PASSWORD = "208";

	//
	public static final String PAYMENT_BY_SUMMARY = "SUMM"; // Payment method:
															// by summary (gross
															// payment)
	public static final String PAYMENT_CLOSING_BALANCE = "CBAL"; // Payment
																	// method:
																	// by
																	// closing
																	// balance
																	// (fee
																	// collection)
	public static final String PAYMENT_CHGBK_TRAN = "CHBK"; // Payment method:
															// by individual
															// transaction
															// (chargebacks)
	public static final String PAYMENT_PRES_TRAN = "PRES"; // Payment method:
															// by individual
															// transaction
															// (presentments)
	//
	// Constants used by function ValidateData in Lib_Misc2
	public static final String DATA_TYPE_DATE = "001";
	public static final String DATA_TYPE_STRING = "002";
	public static final String DATA_TYPE_NUMERIC = "003";
	public static final String DATA_TYPE_CURRENCY = "004";
	public static final String DATA_TYPE_BWT_TABLE = "005";
	public static final String DATA_TYPE_LIST = "006";
	public static final String DATA_TYPE_ISO_CODE = "007";
	public static final String DATA_TYPE_ALPHANUMERIC = "008";
	public static final String DATA_TYPE_HEX = "009";
	//
	// The following are used for the CREATE_ON_DEMAND field.
	// This field previously took its values from bwt_confirmation.
	// The meaning of values ";000"; (CONF_NO), ";001"; (CONF_YES) and
	// ";003"; (CONF_CONDITIONAL) has been retained for backward
	// compatibility.
	public static final String ACCT_CREATION_MANDATORY = "000"; // bwt_account_creation
	public static final String ACCT_CREATION_ON_DEMAND = "001";
	public static final String ACCT_CREATION_BY_SERVICE = "002";
	public static final String ACCT_CREATION_BY_POST_TARIFF = "003";
	public static final String ACCT_CREATION_BY_APPLICATION = "004";
	//
	public static final String DB_CONNECT_USER = "001";
	public static final String DB_CONNECT_INTERNAL = "002";
	//
	// Constants used by function ConvertDate
	public static final String DATE_FRMT_CCYYMMDD = "CCYYMMDD";
	public static final String DATE_FRMT_CCYYMM = "CCYYMM";
	public static final String DATE_FRMT_YYMMDD = "YYMMDD";
	public static final String DATE_FRMT_YYMM = "YYMM";
	public static final String DATE_FRMT_MMYY = "MMYY";
	public static final String DATE_FRMT_MMCCYY = "MMCCYY";
	public static final String DATE_FRMT_MMDDYY = "MMDDYY";
	public static final String DATE_FRMT_MMDDCCYY = "MMDDCCYY";
	public static final String DATE_FRMT_MMDD = "MMDD";
	public static final String DATE_FRMT_DDMM = "DDMM";
	public static final String DATE_FRMT_DDMMYY = "DDMMYY";
	public static final String DATE_FRMT_DDMMCCYY = "DDMMCCYY";
	public static final String DATE_FRMT_DDD = "DDD";
	public static final String DATE_FRMT_YDDD = "YDDD";
	public static final String DATE_FRMT_YYDDD = "YYDDD";
	public static final String DATE_FRMT_CCYYDDD = "CCYYDDD";
	//
	public static final String CONTRACT_PRIVATE = "001"; // bwt_contract_category
	public static final String CONTRACT_COMMERCIAL = "002";
	public static final String CONTRACT_MERCHANT = "003";
	public static final String CONTRACT_INSTITUTION = "004";
	public static final String CONTRACT_ALL = "999";
	//
	public static final String ACCT_CATEG_DEMAND_DEPOSITS = "001"; // bwt_account_category
	public static final String ACCT_CATEG_DEMAND_LOANS = "002";
	public static final String ACCT_CATEG_TIME_LOANS = "003";
	public static final String ACCT_CATEG_TIME_DEPOSIT = "004";
	public static final String ACCT_CATEG_INTERNAL = "005";
	public static final String ACCT_CATEG_PRODUCT_ACCT = "006";
	public static final String ACCT_CATEG_PL = "007";
	public static final String ACCT_CATEG_INSTALLMENT_LOANS = "008";
	public static final String ACCT_CATEG_TURNOVER = "009";
	//
	public static final String REC_LINK_BCH_CAPTURE = "001"; // bwt_record_link_type
	public static final String REC_LINK_INT_TRAN = "002";
	//
	public static final String AGREE_STAT_NA = "000"; // bwt_agreement_status
	public static final String AGREE_STAT_ACTIVE = "001";
	public static final String AGREE_STAT_NEW = "002";
	public static final String AGREE_STAT_CANC_DEBT_BANK = "003";
	public static final String AGREE_STAT_CANC_CREDITOR = "004";
	public static final String AGREE_STAT_CANC_BS = "005";
	public static final String AGREE_STAT_REJECT = "006";
	public static final String AGREE_STAT_PENDING = "007";
	public static final String AGREE_STAT_UNKNOWN = "008";
	//
	public static final String PAYM_HIST_GENERATE_NO = "000"; // bwt_generate_payment_history
																// - do not
																// generate
	public static final String PAYM_HIST_GENERATE_YES = "001"; // generate
																// payment
																// history
	public static final String PAYM_HIST_GENERATE_CARRY_FORWARD = "002"; // generate
																			// payment
																			// history
																			// and
																			// carry
																			// forward
																			// extra
																			// payments
																			// (pre-payment)
	//
	public static final String VALUE_BAL_NONE = "000"; // cht_value_balance_mode
	public static final String VALUE_BAL_STANDARD = "001"; // 000 = No, 001 =
															// Yes (backward
															// compatible)
	public static final String VALUE_BAL_IGP_RETAIL = "002"; // Interest
																// grace period
																// on Retail
																// trans.
	//
	public static final String DELIV_METH_NOT_AVAIL = "000";
	public static final String DELIV_METH_REGULAR_MAIL = "001";
	public static final String DELIV_METH_REGISTERED_MAIL = "002";
	public static final String DELIV_METH_EXPRESS_MAIL = "003";
	public static final String DELIV_METH_COURIER_MAIL = "004";
	public static final String DELIV_METH_PICK_UP = "005";
	public static final String DELIV_METH_HAND_DELIVERY = "006";
	public static final String DELIV_METH_REGISTERED_REGULAR = "021";
	public static final String DELIV_METH_FAST_MAIL = "022";
	public static final String DELIV_METH_INTERNET = "025";
	public static final String DELIV_METH_INTERFACE_FILE = "200";
	public static final String DELIV_METH_E_MAIL = "500";
	//
	public static final String LOGMSG_TYPE_ERROR = "001"; // bwt_log_message_type
	public static final String LOGMSG_TYPE_FATAL_ERROR = "002";
	public static final String LOGMSG_TYPE_WARNING = "003";
	public static final String LOGMSG_TYPE_INFO = "004";
	public static final String LOGMSG_TYPE_ADVICE = "005";
	public static final String LOGMSG_TYPE_REPLY = "006";
	public static final String LOGMSG_TYPE_HELP = "007";
	//
	public static final String PROC_CLASS_INTEREST_DR = "INTD"; // these values
																// are used by
																// interest /
																// fee processes
	public static final String PROC_CLASS_INTEREST_CR = "INTC"; // to allow
																// other
																// processes to
																// identify the
																// type of
	public static final String PROC_CLASS_OVERDUE_INTEREST = "INTO"; // interest
																		// / fee
																		// without
																		// hardcoding
																		// transaction
																		// types
	public static final String PROC_CLASS_TRAN_CHARGES = "CHRG";
	public static final String PROC_CLASS_SERVICE_FEES = "FEES";
	public static final String PROC_CLASS_ACCOUNT_FEES = "FEEA";
	public static final String PROC_CLASS_ECRD = "ECRD";
	//
	public static final String MODE_SVC_EXPIRY_ROUND_NA = "000"; // constants
																	// used in
																	// GetServiceExpiryDate()
	public static final String MODE_SVC_EXPIRY_ROUND_EOM = "001";
	//
	public static final String LIMIT_CATEG_OVERDRAFT = "001"; // bwt_account_limit_categories
	public static final String LIMIT_CATEG_RETAIL_BALANCE = "006";

	// Process Message Logging Library
	//
	public static final String PRC_MSG_TYP_INFO = "100"; // bwt_process_message_type
	public static final String PRC_MSG_TYP_WARNING = "200";
	public static final String PRC_MSG_TYP_ERROR = "300";
	public static final String PRC_MSG_TYP_FATAL = "900";
	//
	public static final String PRC_MSG_SRC_BW = "001"; // bwt_process_message_source
	public static final String PRC_MSG_SRC_ORACLE = "002";
	public static final String PRC_MSG_SRC_VB = "003";
	public static final String PRC_MSG_SRC_RDO = "004";
	public static final String PRC_MSG_SRC_ISOM = "005";
	public static final String PRC_MSG_SRC_SEQL = "006";
	public static final String PRC_MSG_SRC_BPI = "007";
	public static final String PRC_MSG_SRC_JAVA = "008";
	//

	//
	public static final boolean SET_SAVEPOINT = false; // Used by
														// SetOrRollbackSavepoint()
														// [module inw-func.bas]
	public static final boolean ROLLBACK_SAVEPOINT = true;
	//
	public static final boolean READ_ONLY = true; // Used by DBOpen()
	public static final boolean READ_WRITE = false;
	//
	public static final boolean INWARD_CHARGE = true;
	public static final boolean OUTWARD_CHARGE = false;

	// Standard Message Codes
	//
	public static final String MSG_CODE_ORACLE = "90000000";
	public static final String MSG_CODE_VB = "90000001";

	//
	public static final long NUMBER_TRANSACTIONS_TO_COMMIT = 2000; // Controls
																	// number of
																	// transactions
																	// in each
																	// commit
																	// when
																	// moving
																	// transactions
																	// between
																	// tables
	//
	public static final long DB_DEBUG_CONNECT_DISCONNECT = 1;
	public static final long DB_DEBUG_OPEN_CLOSE = 2;
	public static final long DB_DEBUG_NAVIGATE = 4;
	public static final long DB_DEBUG_TRANSACTIONS = 8;
	public static final long DB_DEBUG_GET_FIELD_VALUE = 16;
	public static final long DB_DEBUG_SET_FIELD_VALUE = 32;
	public static final long DB_DEBUG_TABLE_INFO = 64;
	public static final long DB_DEBUG_UPDATE_INSERT = 128;
	public static final long DB_DEBUG_SQL_FUNCTIONS = 256;
	public static final long DB_DEBUG_SET_RECORD = 512;
	public static final long DB_DEBUG_GET_FIELD_INFO = 1024;
	public static final long DB_DEBUG_MEMO_FIELD = 2048;

	//
	public static final int RECLASS_MODE_NONE = 0;
	public static final int RECLASS_MODE_CSB = 1;
	public static final int RECLASS_MODE_VUB = 2;

	//
	public static final int CONV_TRANS_TO_SETTL = 1; // FX conversion types
	public static final int CONV_SETTL_TO_TRANS = -1;
	public static final int CONV_SETTL_TO_LOCAL = 2;
	public static final int CONV_LOCAL_TO_SETTL = -2;
	public static final int CONV_LOCAL_TO_ACCT = 3;
	public static final int CONV_ACCT_TO_LOCAL = -3;
	public static final int CONV_TRANS_TO_ACCT = 4;
	public static final int CONV_ACCT_TO_TRANS = -4;
	//
	public static final int RATE_TYPE_NA = 0; // bwt_fx_rate_type
	public static final int RATE_TYPE_MIDDLE = 1; // FX rate types
	public static final int RATE_TYPE_PURCHASE = 2;
	public static final int RATE_TYPE_SALES = 3;
	//
	public static final int GI_PDATE = 1; // For use with GetGlobalInfo()
	public static final int GI_SDATE = 2;
	//
	public static final int GI_USER_ID = 3;
	public static final int GI_USER_NAME = 4;
	public static final int GI_USER_SHORT_NAME = 5;
	public static final int GI_USER_DEPT = 6;
	public static final int GI_USER_BRANCH = 7;
	//
	public static final int GI_SERVER_DRIVE = 8;
	//
	public static final int GI_PATH_PROGRAM = 9;
	public static final int GI_PATH_IMAGE = 10;
	public static final int GI_PATH_LOCAL_DATA = 11;
	public static final int GI_PATH_SERVER_DATA = 12;
	public static final int GI_PATH_LOCAL = 13;
	public static final int GI_PATH_LOGS = 14;
	public static final int GI_PATH_EXCHANGE = 15;
	public static final int GI_PATH_MISC = 16;
	public static final int GI_PATH_SPOOLER = 17;
	public static final int GI_PATH_STATEMENT = 18;
	public static final int GI_PATH_SEQUENTIAL = 34;
	public static final int GI_PATH_REPORT = 36;
	//
	public static final int GI_LOCAL_CONFIG = 19;
	public static final int GI_SERVER_CONFIG = 20;
	//
	public static final int GI_LIKE_CHAR = 21;
	//
	public static final int GI_THOUSANDS_SEPARATOR = 22;
	public static final int GI_DECIMAL_POINT = 23;
	public static final int GI_EXPONENT = 24;
	//
	public static final int GI_LICENSEE = 25;
	public static final int GI_SERIAL_NUMBER = 26;
	public static final int GI_STATION_NUMBER = 27;
	public static final int GI_CURRENT_SYSTEM = 28;
	public static final int GI_LANGUAGE = 29;
	public static final int GI_ISSUER_ID = 30;
	public static final int GI_ACQUIRER_ID = 31;
	public static final int GI_LOCAL_CURRENCY = 32;
	public static final int GI_USER_GROUP_ID = 33;
	public static final int GI_TRAN_TYPE = 35;
	public static final int GI_LOCAL_CURRENCY_ISO = 37;
	public static final int GI_JUL_PDATE_4 = 38;
	public static final int GI_JUL_SDATE_4 = 39;
	public static final int GI_JUL_PDATE_5 = 40;
	public static final int GI_JUL_SDATE_5 = 41;
	//
	// Add any new constants below to class ProcMsgLog in procedure
	// //ValidateMsgMode()//
	public static final int MSG_FILEONLY = -1;
	public static final int MSG_OK = 0;
	public static final int MSG_YESNO = 1;
	public static final int MSG_YESNOCANCEL = 2;
	public static final int MSG_RET_YES = 6;
	public static final int MSG_RET_NO = 7;
	public static final int MSG_RET_CANCEL = 2;
	//
	// use in HashInit & Replaceint
	//
	public static final int CASE_SENSITIVE = 0; // vbBinaryCompare
	public static final int CASE_INSENSITIVE = 1; // vbTextCompare
	//
	public static final int FMT_FLD_EDIT = -1;
	public static final int FMT_FLD_DISP = 0;
	// public static final int FMT_NUM_LZ = 1
	//
	// Use with FormatAmountOut
	public static final int FMT_NUM_LZ = 1;
	public static final int FMT_NUM_DEC_PT = 2;
	//
	public static final int FMT_SGN_MODE_NA = 0;
	public static final int FMT_SGN_MODE_LEAD = 1;
	public static final int FMT_SGN_MODE_TRAIL = 2;
	public static final int FMT_SGN_MODE_ISO = 3;
	public static final int FMT_SGN_MODE_INET = 4;
	//
	public static final int FMT_TIME_LONG = 0;
	public static final int FMT_TIME_SHORT = 1;
	//
	public static final int CMD_LINE_SIGN_ON = 1; // used as indexes for
													// command line params array
													// gstrCmdLineParams()
	public static final int CMD_LINE_USER_ID = 2;
	public static final int CMD_LINE_PARAM = 3;
	public static final int CMD_LINE_INSTITUTION = 4;
	public static final int CMD_LINE_LANGUAGE = 5;
	public static final int CMD_LINE_CURRENT_SYSTEM = 6;
	public static final int CMD_LINE_USER_DB_NAME = 7;
	public static final int CMD_LINE_USER_DB_PWRD = 8;
	public static final int CMD_LINE_SCHED_PROC_NAME = 9;
	public static final int CMD_LINE_SCHED_SELECT_ALL = 10;
	public static final int CMD_LINE_SCHED_PARAM_LIST = 11;
	public static final int CMD_LINE_BPI_PROC_NUMBER = 12;
	//
	public static final int TRAN_NONE = 0;
	public static final int TRAN_OPEN = 1;
	public static final int TRAN_PENDING = 2;
	//
	// use in DateGetDiff()
	//
	public static final int DIFF_DAYS = 0;
	public static final int DIFF_WEEKS = 1;
	public static final int DIFF_MONTHS = 2;
	public static final int DIFF_YEARS = 3;
	public static final int BUS_DAY_COUNT = 4; // Oppossite from
												// BUS_DAY_IGNORE, required in
												// DateDiff. This belongs
	// to this set as it is used for DateGetDiff() only
	//
	// use in DateAddDays(), DateAddMonths(), DateLastInMonth()
	//
	public static final int BUS_DAY_IGNORE = 0;
	public static final int BUS_DAY_PREV = 1;
	public static final int BUS_DAY_NEXT = 2;
	//
	public static final int DB_MODE_INSERT = 0;
	public static final int DB_MODE_UPDATE = 1;
	//
	public static final int TRN_DATA_CURRENT_MONTHS = 1; // Controls how many
															// months data kept
															// in current tran
															// table (excluding
															// current month)
	public static final int TRN_DATA_HISTORY_MONTHS = 6; // Controls how many
															// months data kept
															// in history tran
															// table (including
															// data in current
															// table and
															// excluding current
															// month)

	//
	public static final int CREATE_PAYM_HISTORY = 1; // Used by
														// ProcessPaymentHistory()
														// to indicate the mode
	public static final int UPDATE_PAYM_HISTORY = 2;
	public static final int REVERSE_PAYM_HISTORY = 3;
	public static final int REVERSE_PAYM_HISTORY_MIN_PAYM = 4;
	//
	// Constants used by LV (list view) functions
	public static final int LV_WIDTH_EXACT_NO_HEADER = -1;
	public static final int LV_WIDTH_EXACT_WITH_HEADER = -2;
	public static final int LV_WIDTH_NO_CHANGE = -3;
	//
	public static final int LV_SELECT_ALL = 1;
	public static final int LV_SELECT_NONE = 2;
	public static final int LV_SELECT_NO_CHANGE = 3;
	public static final int LV_SELECT_FIRST = 4;
	//
	public static final int ADDENDUM_AIRLINE = 1;
	public static final int ADDENDUM_LODGING = 2;
	public static final int ADDENDUM_AUTORENTAL = 3;
	public static final int ADDENDUM_PURCHFORMAT_1 = 4; // Purchasing Addedum
														// Format 1
	public static final int ADDENDUM_LIMITED_USE = 5; // Limited Use data
														// TCR-6
	public static final int ADDENDUM_PURCHA = 6; // Summary data TC-50
	public static final int ADDENDUM_PURCHL = 7; // Line Item TC-50
	public static final int ADDENDUM_CPS_AUTORENTAL = 8;
	public static final int ADDENDUM_CPS_LODGING = 9;
	public static final int ADDENDUM_CPS = 10;
	public static final int ADDENDUM_CPS_AIRLINE = 11;

	public static final int COND_ADDEN_START = 900;
	public static final int ADDEN_VISA_CPS = 901;
	public static final int ADDEN_VISA_PASSENGER = 902;
	public static final int ADDEN_VISA_LODGING = 903;
	public static final int ADDEN_VISA_CAR_RENTAL = 904;
	public static final int ADDEN_ECMC_DATA_RATE_II = 920;
	public static final int ADDEN_ECMC_PASSENGER = 921;
	public static final int ADDEN_ECMC_CAR_RENTAL = 922;
	public static final int ADDEN_ECMC_LODGING = 923;
	public static final int ADDEN_ECMC_PURCHASING = 924;
	public static final int ADDEN_VISA_LIMITED_USE = 925;
	public static final int ADDEN_VISA_LVL2_DATA = 926;
	public static final int ADDEN_VISA_LVL3_DATA = 927;
	public static final int COND_ADDEN_END = 999;

	//
	// Amt_Round() constants
	public static final int ROUND_NEAREST = 0;
	public static final int ROUND_UP = 1;
	public static final int ROUND_DOWN = -1;
	//
	public static final int LOG_MODE_START = 1; // Used to call
												// Log_Process()/Log_IntProcess
												// in consoles
	public static final int LOG_MODE_END = 2;
	//
	public static final int UCAF_NOT_SUPPORTED = 0;
	public static final int UCAF_MERCHANT = 1;
	public static final int UCAF_FULL = 2;
	//
	public static final int COND_TRUE = -1;
	public static final int COND_FALSE = 0;
	public static final int COND_AUTHORIZED = 100;
	public static final int COND_SECURED = 101;
	public static final int COND_MERCHANT_UCAF = 102;
	public static final int COND_ECOMMERCE = 103;
	public static final int COND_FULL_UCAF = 104;
	public static final int COND_MCE_REGISTERED = 105;
	public static final int COND_ELECTRONIC = 106;
	public static final int COND_SET_MERCHANT = 107;
	//
	public static final int SEQ_NO_ALLOC = 50;

	// LOCALLY GENERATED CONSTANTS

	// Smart card integer tags declaration
	public static final int SCT_ID_Cryptg = 1;
	public static final int SCT_ID_TranAmount = 2;
	public static final int SCT_ID_CryptgVer = 3;
	public static final int SCT_ID_IssDisData = 4;
	public static final int SCT_ID_UnPredictNo = 5;
	public static final int SCT_ID_ApplTranCount = 6;
	public static final int SCT_ID_TermVerifRes = 7;
	public static final int SCT_ID_ApplInterProf = 8;
	public static final int SCT_ID_TermTranDate = 9;
	public static final int SCT_ID_CryptgTranType = 10;
	public static final int SCT_ID_TranCur = 11;
	public static final int SCT_ID_CardVerifRes = 12;
	public static final int SCT_ID_TermCapProf = 13;
	public static final int SCT_ID_TermCountryCode = 14;
	public static final int SCT_ID_TermType = 15;
	public static final int SCT_ID_TranCategCode = 16;
	public static final int SCT_ID_DedicFileName = 17;
	public static final int SCT_ID_TermApplVerNo = 18;
	public static final int SCT_ID_CryptgCashBack = 19;
	public static final int SCT_ID_TermSerialNo = 20;
	public static final int SCT_ID_TranSeqNo = 21;

	// Smart card tags declaration
	public static final String SCT_IDT_Cryptg = "9f26";
	public static final String SCT_IDT_TranAmount = "9f02";
	public static final String SCT_IDT_CryptgVer = "9f27";
	public static final String SCT_IDT_IssDisData = "9f10";
	public static final String SCT_IDT_UnPredictNo = "9f37";
	public static final String SCT_IDT_ApplTranCount = "9f36";
	public static final String SCT_IDT_TermVerifRes = "95";
	public static final String SCT_IDT_ApplIntProf = "82";
	public static final String SCT_IDT_TermTranDate = "9a";
	public static final String SCT_IDT_CryptgTranType = "9c";
	public static final String SCT_IDT_TranCur = "5f2a";
	public static final String SCT_IDT_CardVerifRes = "9f34";
	public static final String SCT_IDT_TermCapProf = "9f33";
	public static final String SCT_IDT_TermCountryCode = "9f1a";
	public static final String SCT_IDT_TermType = "9f35";
	public static final String SCT_IDT_TranCategCode = "9f53";
	public static final String SCT_IDT_DedicFileName = "84";
	public static final String SCT_IDT_TermApplVerNo = "9f09";
	public static final String SCT_IDT_CryptgCashBack = "9f03";
	public static final String SCT_IDT_TermSerialNo = "9f1e";
	public static final String SCT_IDT_TranSeqNo = "9f41";

	public static final String SCT_DEF_VAL_DerivationKeyInd = "00";
	public static final String SCT_DEF_VAL_IssScript1Res = "0000000000";

	// According to Stanly FDMS latest Version
	public static final String DCC_CONV_FLAG_NO = "000";
	public static final String DCC_CONV_FLAG_CONCESION = "001";
	public static final String DCC_CONV_FLAG_FRONT_END = "002";

	public static final String REGION_BY_CLIENT_COUNTRY = "900";
	public static final String REGION_BY_TRAN_COUNTRY = "901";
	public static final String REGION_BY_TRAN_REJECTS = "902";
	public static final String REGION_NA = "000";

	// Package and procdure names
	public static final String PKG_NAME_EXT_PRC = "EXTERNAL_PROCESS";
	public static final String PRO_NAME_RUN_EXT = "RUN_EXTERNAL";
	public static final String PRC_PARM_TYPE_IN = "IN";
	public static final String PRC_PARM_TYPE_OUT = "OUT";

	public static final int MCC_AIRLINE = 100;
	public static final int MCC_HOTEL = 101;
	public static final int MCC_CAR_RENTAL = 102;
	public static final int MCC_RESTAURANT = 103;
	public static final int MCC_SUPERMARKET = 104;
	public static final int MCC_FOODSTORE = 105;
	public static final int MCC_TRAVEL_AGENCY = 106;
	public static final int MCC_TELECOM_SERVICE = 107;
	public static final int MCC_PETROL = 108;
	public static final int MCC_TOUR_OPERATOR = 109;
	public static final int MCC_QUASI_CASH = 110;

	public static final int MCC_AUTOMATED_FUEL = 111;
	public static final int MCC_RAILWAY = 112;
	public static final int MCC_TELEMARKETING = 114;
	public static final int MCC_CRUISE_LINES = 116;
	public static final int MCC_GOVERNMENT = 117;
	public static final int MCC_SCHOOL = 118;
	public static final int MCC_UTILITY = 119;
	public static final int MCC_CABLE_SAT_TV_RADIO = 120;
	public static final int MCC_INSURANCE = 121;
	public static final int MCC_CPS_SMALL_TICKET = 122;
	public static final int MCC_UK_ELEC_HOT_CARD = 123;
	public static final int MCC_MOTO = 124;
	public static final int MCC_PAYMENT = 125;
	public static final int MCC_FUEL_LOCATIONS = 126;
	public static final int MCC_UTILITIES = 127;
	public static final int MCC_PROFESSIONAL_SERVICES = 128;
	public static final int MCC_DRUG_STORES = 129;
	public static final int MCC_RECREATION = 130;
	public static final int MCC_EDUCATION = 131;
	public static final int MCC_REPAIR_SHOPS = 132;
	public static final int MCC_OTHER_SERVICES = 133;
	public static final int MCC_OTHER_RETAIL = 134;
	public static final int MCC_GAS_STATIONS = 135;
	public static final int MCC_HARDWARE = 136;
	public static final int MCC_HEALTH_CARE = 137;
	public static final int MCC_SPORT_TOY_STORES = 138;
	public static final int MCC_DISCOUNT_STORES = 139;
	public static final int MCC_CLOTHING_STORES = 140;
	public static final int MCC_OTHER_TRANSPORT = 141;
	public static final int MCC_VEHICLES = 142;
	public static final int MCC_FOOD_STORES = 143;
	public static final int MCC_UNIQUE = 144;
	public static final int MCC_ATM = 145; // '[EJ 2006-05-22] OMP-GEN-161 -
											// 18736
	public static final int MCC_AUS_RECURRING = 146; // '[EJ 2006-11-21]
														// OMP-GEN-196
	public static final int MCC_CPSRETAIL2_TIPPING = 147; // '[RobAbd
															// 2007-02-09] 7.1
															// mandates
	public static final int MCC_EMERGING_MARKET = 148; // '[EJ 2007-03-01] 07.1
	public static final int MCC_AUS_GOV_AND_UTIL = 149; // '[EJ 2007-04-10]
														// OMP-GEN-230
	public static final int MCC_TELEPHONE = 150; // '[EJ 2007-04-10]
													// OMP-GEN-230
	public static final int MCC_CHARITY = 151; // '[EJ 2007-04-10] OMP-GEN-230
	public static final int MCC_BEAUTY_SALONS = 152; // '[EJ 2007-04-10]
														// OMP-GEN-230
	public static final int MCC_WAREHOUSE = 153; // '[EJ 2007-04-10]
													// OMP-GEN-230
	public static final int MCC_AUS_OTHER = 154; // '[EJ 2007-04-10]
													// OMP-GEN-230
	public static final int MCC_MC_OTHER = 155; // '[EJ 2007-04-10] OMP-GEN-230
	public static final int MCC_GAMBLING = 156; // '[EJ 2007-06-04] OMP-GEN-235
	// 'Groups that use other groups
	public static final int MCC_T_AND_E = 800;
	public static final int MCC_PASSENGER_TRANSPORT = 801;
	public static final int MCC_DEVELOPING_MARKET = 802;

	public static final int MCC_SPAIN_2_75 = 900;
	public static final int MCC_SPAIN_2_55 = 901;
	public static final int MCC_SPAIN_2_13 = 902;
	public static final int MCC_SPAIN_1_91 = 903;
	public static final int MCC_SPAIN_1_70 = 904;
	public static final int MCC_SPAIN_1_30 = 905;
	public static final int MCC_SPAIN_0_85 = 906;
	// Java Added values
	// *
	// public static final int MCC_MOTO = 111;
	// public static final int MCC_PAYMENT = 112;
	public static final int MCC_AUTOPARKING = 113;
	public static final int MCC_POS = 114;
	// public static final int MCC_ATM = 115;
	public static final int MCC_CPSRETAILS = 116;
	public static final int MCC_TANDE = 117;
	public static final int MCC_TELEMARKET = 118;
	// */
	// Java Added values
	public static final int CLIENT_NO_WIDTH = 8;
	public static final int TRAN_SLIP_WIDTH = 11;
	public static final int FOLIO_NO_WIDTH = 11;
	public static final int PRC_MSG_LOG_PARAM_WIDTH = 60;
	public static final int PRC_MSG_LOG_ADD_TEXT_WIDTH = 2000;

	public static final String DB_TRUE = "1";
	public static final String DB_FALSE = "0";
	public static final String DB_VALUE_NOT_EXIST = "999";
	public static final int DB_SEQUENTIAL_NO_FILE_WIDTH = 8;

	public static final String MSG_CODE_BATCH_RECON_SUMS_ERROR = "10001014";
	public static final String MSG_CODE_BATCH_HEADER_NOT_PRESENT = "10002027";
	public static final String MSG_CODE_BATCH_RECON_WITHOUT_BATCH_HEADER = "10002032";
	public static final String MSG_CODE_MESSAGES_OUT_OF_SEQ = "10002014";
	// public static final String MSG_CODE_INVALID_MESSAGE_TYPE = "10002002";
	public static final String MSG_CODE_NULL_MDS_BIN_RECORD = "10236003";
	public static final String MSG_CODE_INVALID_MDS_BIN_MAINT_IND = "10236004";
	public static final String MSG_CODE_INC_FILE_NOT_BELONG_TO_INST = "10364027";
	public static final String MSG_CODE_REJECT_FILE_NOT_IN_DB = "10364028";
	public static final String MSG_CODE_REJECT_FILE_RECEIVED = "10364029";
	public static final String MSG_CODE_GL_FILE_OPEN_ERROR = "90013037";
	public static final String MSG_CODE_FILE_HEADER_IS_MISSING = "90013039";
	public static final String MSG_CODE_FILE_TRAILER_IS_MISSING = "90013040";
	public static final String MSG_CODE_UNKNOWN_TC_IN_FILE_ENCOUNTERED = "90013095";
	public static final String MSG_CODE_DATE_AFTER_POSTING_DATE = "90013105";
	public static final String MSG_CODE_FILE_AMOUNT_INCORRECT = "90013130";
	public static final String MSG_CODE_FILE_MSG_NUM_INCORRECT = "90013134";

	public static final String MSG_CODE_UNKNOWN_MSG_TYPE_RECEIVED = "90015002";
	public static final String MSG_CODE_FORMAT_CODE_NOT_AVAIL = "90015005";

	// Not standard
	public static final String MSG_CODE_BATCH_AMOUNT_INCORRECT = "20013130";
	public static final String MSG_CODE_BATCH_MSG_NUM_INCORRECT = "20013134";

	public static final String MSG_CODE_GL_FILE_ALREADY_PROCESSED_ERROR = "99999999"; // File
																						// already
																						// processed,File
																						// Name
	public static final String MSG_CODE_GL_FILE_CORRUPTED_OR_INVALID = "99999999";
	public static final String MSG_CODE_JAVA_EXCEPTION = "99999999"; // ("File
																		// already
																		// processed","File
																		// Name")
																		// fkjsgdfgk
	public static final String MSG_CODE_CLEARING_CHANNEL_NOT_EXIST = "99999999";
	public static final String MSG_CODE_BATCH_NOT_RECONCILED = "99999999";
	public static final String MSG_CODE_MANDATORY_FIELDS_MISSED = "99999999";

	public static final String MSG_CODE_PROCESS_NUMBER_DO_NOT_EXIST = "99999999";
	public static final String MSG_CODE_MASTERCARD_EUROPAY_CONFLICT = "99999999";

	public static final String MSG_CODE_USER_DIRECT_ABORTION = "99999999";
	public static final String MSG_CODE_UNKNOWN = "99999999";

	public static final String ADDENDUM_PURCHA_TEXT = "PURCHA";
	public static final String ADDENDUM_PURCHL_TEXT = "PURCHL";
	public static final String ADDENDUM_A4_TEXT = "A4";
	public static final String ADDENDUM_A5_TEXT = "A5";

	// Exception sources
	public static final String EXCEPTION_SOURCE_JAVA = "Java_Code";
	public static final String EXCEPTION_SOURCE_ORACLE = "Oracle_Engine";

	// Data base Functions
	public static final String LIB_TEMPTABLE_CREATEXFCTABLES = "Bw_lib_temptables.CreateXFCTempTables";
	public static final String LIB_TEMPTABLE_DROPXFCTABLES = "Bw_lib_temptables.DropXFCTempTables";
	// public static final String LIB_TEMPTABLE_CREATEXFCEXTTABLES =
	// "Bw_lib_temptables.CreateXFCExtTempTables";
	public static final String LIB_TEMPTABLE_CREATEXFCEXTTABLES = "Bw_lib_temptables.InsertUsingExternalTables";
	public static final String LIB_SEQ_GENERATEFILENUMBER = "Bw_Lib_Seq.GenerateFileNumber";
	public static final String LIB_SEQ_GENERATERCNFILENUMBER = "Bw_Lib_Seq.GenerateReconFileNumber";
	public static final String LIB_SEQ_GENERATERCNSLIPNUMBER = "Bw_Lib_Seq.GenerateReconSlipNo";

	// Procedures
	public static final String LIB_MESSAGES_INSERTPROCESSMESSAGE = "Bw_Lib_Messages.Insertprocessmessage";
	public static final String LIB_PRC_MON_SET_CLI_MOD = "bw_pmon.SetClientAndModule";
	public static final String LIB_PRC_MON_SET_PROGRESS = "bw_pmon.SetProgress";
	public static final String LIB_PRC_CONTROL_SAVE_PARM_OUT = "Bw_Process_Control.Saveparameterout";

	// Language Modules Definition
	public static final String LANG_MODULE_MDI_MENU = "MDI_MENU";
	// Language definition
	public static final String LANG_USA = "USA";

	// Param Files types
	public static final int PARM_FILE_TYPE_MENU = 3;

	public static final String SEQ_MSG_NUMBER = "seq_message_number";

	// Parameter file types prefixes
	/*
	 * public static final String PARAM_FILE_PREFIX_DIC = "DIC"; public static
	 * final String PARAM_FILE_PREFIX_MSK = "MSK"; public static final String
	 * PARAM_FILE_PREFIX_SCR = "SCR"; public static final String
	 * PARAM_FILE_PREFIX_MNU = "MNU"; public static final String
	 * PARAM_FILE_PREFIX_PRC = "PRC"; public static final String
	 * PARAM_FILE_PREFIX_BCH = "BCH"; public static final String
	 * PARAM_FILE_PREFIX_DSP = "DSP"; public static final String
	 * PARAM_FILE_PREFIX_LST = "LST"; // Parameter file types public static
	 * final int PARAM_FILE_PREFIX_DIC_IDX = 0; public static final int
	 * PARAM_FILE_PREFIX_MSK_IDX = 1; public static final int
	 * PARAM_FILE_PREFIX_SCR_IDX = 2; public static final int
	 * PARAM_FILE_PREFIX_MNU_IDX = 3; public static final int
	 * PARAM_FILE_PREFIX_PRC_IDX = 4; public static final int
	 * PARAM_FILE_PREFIX_BCH_IDX = 5; public static final int
	 * PARAM_FILE_PREFIX_DSP_IDX = 7; public static final int
	 * PARAM_FILE_PREFIX_LST_IDX = 8;
	 *  // menu id prefixes stored in the MDI menu table public static final
	 * String GUI_MENU_PREFIX_MENU = "mnu"; public static final String
	 * GUI_MENU_PREFIX_MENU_ITEM = "mni"; public static final String
	 * GUI_MENU_PREFIX_SEPARATOR = "sep"; public static final String
	 * GUI_MENU_PREFIX_CHECKBOX = "chk";
	 *  // POPUP menu IDS used in the table public static final String
	 * GUI_POPUP_MENU_ID_TREE_MENU = "tree_menu_edit";
	 * 
	 * 
	 * //Constant string values used in the class "MskSQLExtractor" for
	 * replacing the // sql select query strings public static final String
	 * SQL_EXTRACTOR_SQLSELECT = "SQLSelect"; public static final String
	 * SQL_EXTRACTOR_SQLREF = "SQLRef"; public static final String
	 * GUI_ROOT_MNU_NAME = "sys_main_system.mnu"; public static final String
	 * GUI_LANG_ID_MENU_CAPTION = "MENU_CAPTION";
	 * 
	 * 
	 * //Constants used in proces console public static final String
	 * GUI_LANG_ID_COMBOITEM_ALL = "combo_itemAll"; public static final String
	 * GUI_LANG_ID_MNU_OPTIONS = "mnu_optiontitle"; public static final String
	 * GUI_LANG_ID_MNUITEM_EXIT = "mnu_optionitem_exit";
	 * 
	 * public static final String GUI_LANG_ID_BTN_RUN = "btn_run"; public static
	 * final String GUI_LANG_ID_BTN_RUNUNTIL = "btn_rununtil";
	 * 
	 * public static final String GUI_LANG_ID_LBL_PREPROCESS = "lbl_preprocess";
	 * public static final String GUI_LANG_ID_LBL_POSTPROCESS =
	 * "lbl_postprocess"; public static final String GUI_LANG_ID_LBL_PHASE_TWO =
	 * "lbl_phase_two"; public static final String GUI_LANG_ID_LBL_PHASE_THREE =
	 * "lbl_phase_three"; public static final String GUI_LANG_ID_LBL_PHASE_FOUR =
	 * "lbl_phase_four";
	 * 
	 * public static final String GUI_LANG_ID_LBL_SEND = "lbl_send"; public
	 * static final String GUI_LANG_ID_LBL_SAVE_AS = "lbl_save_as"; public
	 * static final String GUI_LANG_ID_LBL_STATUS = "lbl_status"; public static
	 * final String GUI_LANG_ID_LBL_WIN_TITLE = "lbl_win_title";
	 *  // sys_configuration public static final String
	 * SYS_CONF_SECTION_PREPROCESS = "HighVolumeEngine"; public static final
	 * String SYS_CONF_CONF_KEY_ALLOWED_IPS = "AllowedIps"; public static final
	 * String SYS_CONF_CONF_KEY_LOG_FILE_PATH = "LogFileName"; public static
	 * final String SYS_CONF_CONF_KEY_JAVA_OUT_PATH = "JavaOutputFileName";
	 * public static final String SYS_CONF_CONF_KEY_JAR_FILE_PATH =
	 * "JarFilePath";
	 */
}
