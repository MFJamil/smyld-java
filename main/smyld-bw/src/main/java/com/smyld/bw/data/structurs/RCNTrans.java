package com.smyld.bw.data.structurs;

import com.smyld.bw.db.SMYLDTableRecord;

public class RCNTrans extends SMYLDTableRecord {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RCNTrans() {
		recordType = RCN_RECORD_TYPE_TRAN;
	}

	public String INST_NUM;
	public String RECON_SLIP;
	public String FILE_NUM;
	public String RECON_CHNL;
	public String REC_DATE;
	public String AUDIT_TRAIL;
	public String CARD_NUM;
	public String TRN_DATE;
	public String TRAN_CUR;
	public String TRAN_AMT_GR;
	public String SETTL_AMT_GR;
	public String AUTH_CODE;
	public String TRN_CLS;
	public String TRN_CATG;
	public String TRN_TYPE;
	public String REV_FLAG;
	public String TERM_ID;
	public String CAPTURE_METH;
	public String BUSS_CLS;
	public String MERCH_NAME;
	public String MERCH_CITY;
	public String MERCH_CTRY;
	public String MERCH_STATE;
	public String MERCH_POST_CODE;
	public String MERCH_NUM;
	public String MERCH_STREET;
	public String SETL_CUR;
	public String LOCAL_CUR;
	public String TRAN_AMT_CHG;
	public String TRAN_AMT_NET;
	public String SETTL_AMT_CHG;
	public String SETTL_AMT_NET;
	public String LOCAL_AMT_INW_GR;
	public String LOCAL_AMT_INW_CHG;
	public String LOCAL_AMT_INW_NET;
	public String NUM_ORG_SLIP;
	public String ORG_REF_NUM;
	public String RETRIEVAL_REF;
	public String TRACE_ID;
	public String TIME_TRN;
	public String RECON_STA;
	public String MATCH_DATE;
	public String MATCH_RULE;
	public String MATCH_TRN_SLIP;
	public String MATCH_RESULT;
	public String MATCH_DAYS_DIFF;
	public String MATCH_AMT_DIFF;
	public String AREA_OF_EVENT;
	public String CARD_ORGA;

	@Override
	public void reset() {
		INST_NUM = null;
		RECON_SLIP = null;
		FILE_NUM = null;
		RECON_CHNL = null;
		REC_DATE = null;
		AUDIT_TRAIL = null;
		CARD_NUM = null;
		TRN_DATE = null;
		TRAN_CUR = null;
		TRAN_AMT_GR = null;
		SETTL_AMT_GR = null;
		AUTH_CODE = null;
		TRN_CLS = null;
		TRN_CATG = null;
		TRN_TYPE = null;
		REV_FLAG = null;
		TERM_ID = null;
		CAPTURE_METH = null;
		BUSS_CLS = null;
		MERCH_NAME = null;
		MERCH_CITY = null;
		MERCH_CTRY = null;
		MERCH_STATE = null;
		MERCH_POST_CODE = null;
		MERCH_NUM = null;
		MERCH_STREET = null;
		SETL_CUR = null;
		LOCAL_CUR = null;
		TRAN_AMT_CHG = null;
		TRAN_AMT_NET = null;
		SETTL_AMT_CHG = null;
		SETTL_AMT_NET = null;
		LOCAL_AMT_INW_GR = null;
		LOCAL_AMT_INW_CHG = null;
		LOCAL_AMT_INW_NET = null;
		NUM_ORG_SLIP = null;
		ORG_REF_NUM = null;
		RETRIEVAL_REF = null;
		TRACE_ID = null;
		TIME_TRN = null;
		RECON_STA = null;
		MATCH_DATE = null;
		MATCH_RULE = null;
		MATCH_TRN_SLIP = null;
		MATCH_RESULT = null;
		MATCH_DAYS_DIFF = null;
		MATCH_AMT_DIFF = null;
		AREA_OF_EVENT = null;
		CARD_ORGA = null;
	}

	@Override
	public void printInstanceValues() {
		super.printInstanceValues();
		insertInstanceValue(" INST_NUM          : " + getLenName(INST_NUM));
		insertInstanceValue(" RECON_SLIP        : " + getLenName(RECON_SLIP));
		insertInstanceValue(" FILE_NUM          : " + getLenName(FILE_NUM));
		insertInstanceValue(" RECON_CHNL        : " + getLenName(RECON_CHNL));
		insertInstanceValue(" REC_DATE          : " + getLenName(REC_DATE));
		insertInstanceValue(" AUDIT_TRAIL       : " + getLenName(AUDIT_TRAIL));
		insertInstanceValue(" CARD_NUM          : " + getLenName(CARD_NUM));
		insertInstanceValue(" TRN_DATE          : " + getLenName(TRN_DATE));
		insertInstanceValue(" TRAN_CUR          : " + getLenName(TRAN_CUR));
		insertInstanceValue(" TRAN_AMT_GR       : " + getLenName(TRAN_AMT_GR));
		insertInstanceValue(" SETTL_AMT_GR      : " + getLenName(SETTL_AMT_GR));
		insertInstanceValue(" AUTH_CODE         : " + getLenName(AUTH_CODE));
		insertInstanceValue(" TRN_CLS           : " + getLenName(TRN_CLS));
		insertInstanceValue(" TRN_CATG          : " + getLenName(TRN_CATG));
		insertInstanceValue(" TRN_TYPE          : " + getLenName(TRN_TYPE));
		insertInstanceValue(" REV_FLAG          : " + getLenName(REV_FLAG));
		insertInstanceValue(" TERM_ID           : " + getLenName(TERM_ID));
		insertInstanceValue(" CAPTURE_METH      : " + getLenName(CAPTURE_METH));
		insertInstanceValue(" BUSS_CLS          : " + getLenName(BUSS_CLS));
		insertInstanceValue(" MERCH_NAME        : " + getLenName(MERCH_NAME));
		insertInstanceValue(" MERCH_CITY        : " + getLenName(MERCH_CITY));
		insertInstanceValue(" MERCH_CTRY        : " + getLenName(MERCH_CTRY));
		insertInstanceValue(" MERCH_STATE       : " + getLenName(MERCH_STATE));
		insertInstanceValue(" MERCH_POST_CODE   : "
				+ getLenName(MERCH_POST_CODE));
		insertInstanceValue(" MERCH_NUM         : " + getLenName(MERCH_NUM));
		insertInstanceValue(" MERCH_STREET      : " + getLenName(MERCH_STREET));
		insertInstanceValue(" SETL_CUR          : " + getLenName(SETL_CUR));
		insertInstanceValue(" LOCAL_CUR         : " + getLenName(LOCAL_CUR));
		insertInstanceValue(" TRAN_AMT_CHG      : " + getLenName(TRAN_AMT_CHG));
		insertInstanceValue(" TRAN_AMT_NET      : " + getLenName(TRAN_AMT_NET));
		insertInstanceValue(" SETTL_AMT_CHG     : " + getLenName(SETTL_AMT_CHG));
		insertInstanceValue(" SETTL_AMT_NET     : " + getLenName(SETTL_AMT_NET));
		insertInstanceValue(" LOCAL_AMT_INW_GR  : "
				+ getLenName(LOCAL_AMT_INW_GR));
		insertInstanceValue(" LOCAL_AMT_INW_CHG : "
				+ getLenName(LOCAL_AMT_INW_CHG));
		insertInstanceValue(" LOCAL_AMT_INW_NET : "
				+ getLenName(LOCAL_AMT_INW_NET));
		insertInstanceValue(" NUM_ORG_SLIP      : " + getLenName(NUM_ORG_SLIP));
		insertInstanceValue(" ORG_REF_NUM       : " + getLenName(ORG_REF_NUM));
		insertInstanceValue(" RETRIEVAL_REF     : " + getLenName(RETRIEVAL_REF));
		insertInstanceValue(" TRACE_ID          : " + getLenName(TRACE_ID));
		insertInstanceValue(" TIME_TRN          : " + getLenName(TIME_TRN));
		insertInstanceValue(" RECON_STA         : " + getLenName(RECON_STA));
		insertInstanceValue(" MATCH_DATE        : " + getLenName(MATCH_DATE));
		insertInstanceValue(" MATCH_RULE        : " + getLenName(MATCH_RULE));
		insertInstanceValue(" MATCH_TRN_SLIP    : "
				+ getLenName(MATCH_TRN_SLIP));
		insertInstanceValue(" MATCH_RESULT      : " + getLenName(MATCH_RESULT));
		insertInstanceValue(" MATCH_DAYS_DIFF   : "
				+ getLenName(MATCH_DAYS_DIFF));
		insertInstanceValue(" MATCH_AMT_DIFF    : "
				+ getLenName(MATCH_AMT_DIFF));
		insertInstanceValue(" CARD_ORGA         : " + getLenName(CARD_ORGA));
		insertInstanceValue(" AREA_OF_EVENT     : " + getLenName(AREA_OF_EVENT));

	}

	@Override
	public void addFlatValues() {
		addFlatValue(INST_NUM);
		addFlatValue(RECON_SLIP);
		addFlatValue(FILE_NUM);
		addFlatValue(RECON_CHNL);
		addFlatValue(REC_DATE);
		addFlatValue(AUDIT_TRAIL);
		addFlatValue(CARD_NUM);
		addFlatValue(TRN_DATE);
		addFlatValue(TRAN_CUR);
		addFlatValue(TRAN_AMT_GR);
		addFlatValue(SETTL_AMT_GR);
		addFlatValue(AUTH_CODE);
		addFlatValue(TRN_CLS);
		addFlatValue(TRN_CATG);
		addFlatValue(TRN_TYPE);
		addFlatValue(REV_FLAG);
		addFlatValue(TERM_ID);
		addFlatValue(CAPTURE_METH);
		addFlatValue(BUSS_CLS);
		addFlatValue(MERCH_NAME);
		addFlatValue(MERCH_CITY);
		addFlatValue(MERCH_CTRY);
		addFlatValue(MERCH_STATE);
		addFlatValue(MERCH_POST_CODE);
		addFlatValue(MERCH_NUM);
		addFlatValue(MERCH_STREET);
		addFlatValue(SETL_CUR);
		addFlatValue(LOCAL_CUR);
		addFlatValue(TRAN_AMT_CHG);
		addFlatValue(TRAN_AMT_NET);
		addFlatValue(SETTL_AMT_CHG);
		addFlatValue(SETTL_AMT_NET);
		addFlatValue(LOCAL_AMT_INW_GR);
		addFlatValue(LOCAL_AMT_INW_CHG);
		addFlatValue(LOCAL_AMT_INW_NET);
		addFlatValue(NUM_ORG_SLIP);
		addFlatValue(ORG_REF_NUM);
		addFlatValue(RETRIEVAL_REF);
		addFlatValue(TRACE_ID);
		addFlatValue(TIME_TRN);
		addFlatValue(RECON_STA);
		addFlatValue(MATCH_DATE);
		addFlatValue(MATCH_RULE);
		addFlatValue(MATCH_TRN_SLIP);
		addFlatValue(MATCH_RESULT);
		addFlatValue(MATCH_DAYS_DIFF);
		addFlatValue(MATCH_AMT_DIFF);
		addFlatValue(CARD_ORGA);
		addFlatValue(AREA_OF_EVENT);

	}

	public static final int RCN_RECORD_TYPE_TRAN = 200;
}
