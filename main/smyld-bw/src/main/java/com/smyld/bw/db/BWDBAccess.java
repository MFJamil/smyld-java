package com.smyld.bw.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.smyld.bw.data.structurs.Channel;
import com.smyld.bw.data.structurs.ClientLink;
import com.smyld.bw.data.structurs.Country;
import com.smyld.bw.data.structurs.Currency;
import com.smyld.bw.data.structurs.ProcessSetup;
import com.smyld.bw.data.structurs.ServiceContract;
import com.smyld.bw.data.structurs.SystemJob;
import com.smyld.bw.data.structurs.TranType;
import com.smyld.db.DBConnection;
import com.smyld.db.DBErrorHandler;
import com.smyld.db.SQL;
import com.smyld.err.CardConflictException;
import com.smyld.text.TextUtil;

public class BWDBAccess extends BWDataBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static HashMap<String,String> countryCodes2;
	public static HashMap<String,String> countryCodes3;
	public static HashMap<String,Country> countrys;

	public static HashMap<String,Channel> channels;
	public static HashMap<String,TranType> transTypes;
	public static HashMap<String,String> isoBussClass;
	public static HashMap<String,String> idxBussClass;
	public static HashMap<String,String> extraChargesCarRental;
	public static HashMap<String,String> extraChargesLodging;
	public static HashMap<String,String> countryStateCodes;
	public static HashMap<String,String> systemCountryRegion;
	public static HashMap<String,String> regions;
	public static HashMap<String,Currency> currencies;
	public static HashMap<String,String> interchangeDetails;

	public BWDBAccess(int driver, DBErrorHandler e, String userName,
			String userPassword, String host, String port, String dbname) {
		super(driver, e, userName, userPassword, host, port, dbname);
	}

	public BWDBAccess(DBErrorHandler e, DBConnection connection) {
		super(e, connection);
		init();
	}

	public BWDBAccess(DBErrorHandler e) {
		super(e);
		init();
	}

	public void init() {
		loadChannels();
		loadTransactionTypes();
		loadISOBussClasses();
		loadExtraChargesCarRental();
		loadExtraChargesLodging();
		loadCountryStateCodes();
		loadCountryCodes();
		loadSystemCountryRegion();
		loadRegions();
		loadCurrencies();
		loadInterchangeDetails();
	}

	public synchronized void deleteFile(String institutionNo, String fileNo) {
		dbUtility.doDoubleParamSQL(institutionNo, fileNo,
				SQLStatements.DEL_int_file_log_details_SINGLE_RECORD);
		dbUtility.doDoubleParamSQL(institutionNo, fileNo,
				SQLStatements.DEL_int_process_file_log_SINGLE_RECORD);
	}

	public synchronized ProcessSetup loadProcessSetup(String processName,
			String institutionNum) {
		ProcessSetup newProcessSetup = null;
		ResultSet rs = null;
		try {
			rs = dbUtility.getDoubleParamSQL(processName, institutionNum,
					SQLStatements.SEL_sys_process_setup_SINGLE_RECORD);
			if (rs.next()) {
				newProcessSetup = new ProcessSetup();
				newProcessSetup.setFileMask(rs
						.getString(COL_SYS_PRC_USR_SETUP_FILE_MASK));
				newProcessSetup.setFilePath(rs
						.getString(COL_SYS_PRC_USR_SETUP_FILE_PATH));
				newProcessSetup.setFileMovePath(rs
						.getString(COL_SYS_PRC_USR_SETUP_FILE_MOVE_PATH));
				newProcessSetup.setFileRenameMask(rs
						.getString(COL_SYS_PRC_USR_SETUP_FILE_RENAME_MASK));
			}
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rs);
		}
		return newProcessSetup;
	}

	public void changeComponentVersion(String newVersion, String productID,
			String compID) {
		PreparedStatement st = null;
		try {
			String sql = "update BW_COMPONENT_VERSION set COMP_VERSION = ? where COMP_PRODUCT = ? and COMP_ID = ?";
			st = dbConnection.prepareStatement(sql);
			st.setString(1, newVersion);
			st.setString(2, productID);
			st.setString(3, compID);
			st.executeUpdate();
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(st);
		}
	}

	public static Currency getCurrency(String currCode) {
		return (Currency) currencies.get(currCode);
	}

	public static int getCurrencyExponent(String currCode) {
		Currency targetCurrency = getCurrency(currCode);
		if (targetCurrency != null)
			return targetCurrency.getExponent();
		return -1;
	}

	/**
	 * This methode creates a system job object out of the given process number
	 * 
	 * @param processNumber
	 * @return System Job
	 * @see
	 * @since
	 */
	public synchronized SystemJob createJob(String processNumber) {
		SystemJob requestedJob = null;
		ResultSet rsProcessLog = null;
		ResultSet rsProcessLogParams = null;
		try {
			rsProcessLog = dbUtility.getSingleParamSQL(processNumber,
					SQLStatements.SEL_int_proc_log_SINGLE_RECORDs);
			if (rsProcessLog.next()) {
				String no = rsProcessLog.getString(COL_INT_PROC_LOG_PRC_NUM);
				String id = rsProcessLog.getString(COL_INT_PROC_LOG_PRC_ID);
				String name = rsProcessLog.getString(COL_INT_PROC_LOG_PRC_NAME);
				String institutionNo = rsProcessLog
						.getString(COL_INT_PROC_LOG_INST_NUM);
				String stationNo = rsProcessLog
						.getString(COL_INT_PROC_LOG_STATION_NUM);
				String userID = rsProcessLog
						.getString(COL_INT_PROC_LOG_USER_ID);
				String postingDate = rsProcessLog
						.getString(COL_INT_PROC_LOG_POSTING_DATE);
				String parentPrcNo = rsProcessLog
						.getString(COL_INT_PROC_LOG_PARENT_PRC_NUM);
				// String paramsOut =
				// rsProcessLog.getString(COL_INT_PROC_LOG_PARM_OUT );
				requestedJob = new SystemJob(no, id, name, institutionNo,
						stationNo, userID, postingDate);
				rsProcessLogParams = dbUtility.getSingleParamSQL(processNumber,
						SQLStatements.SEL_int_process_params_MULTI_RECORD);
				while (rsProcessLogParams.next()) {
					String paramValue = rsProcessLogParams
							.getString(DBName.COL_INT_PRC_PARMS_PARM_VALUE);
					requestedJob.setParametersIn(paramValue);
					requestedJob.setParentProcessNo(parentPrcNo);
				}
			}
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsProcessLog);
			dbUtility.closeCursor(rsProcessLogParams);
		}
		return requestedJob;
	}

	public synchronized void updateJobParameters(SystemJob targetJob) {
		if (targetJob.getParamOutSetsCount() > 0)
			dbUtility.executeDoubleParamProcedure(
					LIB_PRC_CONTROL_SAVE_PARM_OUT, targetJob.getNo(), targetJob
							.getParametersOutValue());
	}

	public synchronized boolean updateFilePrcStatus(String institutionNo,
			String fileNumber, String prcStatus) {
		int result = dbUtility.doTrippleParamUpdateSQL(prcStatus, fileNumber,
				institutionNo,
				SQLStatements.UPD_int_file_log_details_PRC_STATUS);
		return (result > 0);
	}

	public synchronized boolean updateIntFilePrcStatus(String institutionNo,
			String fileNumber, String prcStatus) {
		int result = dbUtility.doTrippleParamUpdateSQL(prcStatus, fileNumber,
				institutionNo,
				SQLStatements.UPD_int_file_log_details_PRC_STATUS);
		return (result > 0);
	}

	public synchronized ResultSet getClientDetailsForExRef(
			String institutionNo, String clientExRef) {
		// To solve the problem of replacing the clinet number with null as a
		// text value
		return dbUtility.getDoubleParamSQL(institutionNo, clientExRef,
				SQLStatements.SEL_cis_client_details_OUT_REF_CHECK);
	}

	public synchronized ResultSet getClientRefDetailsForExRef(
			String institutionNo, String clientExRef, String clientRefType) {
		// To solve the problem of replacing the clinet number with null as a
		// text value
		return dbUtility.getQuadParamSQL(institutionNo, clientExRef,
				clientExRef, clientRefType,
				SQLStatements.SEL_cis_client_details_ref_OUR_REF_CHECK);

	}

	// OM 1.4.89
	public synchronized ResultSet getClientRefDetailsForExRef(
			String institutionNo, String clientExRef, String clientRefType,
			boolean byRegistrationNo) {
		// To solve the problem of replacing the clinet number with null as a
		// text value
		if (byRegistrationNo)
			return dbUtility.getTrippleParamSQL(institutionNo, clientExRef,
					clientRefType,
					SQLStatements.SEL_cis_client_details_ref_REG_NO_CHECK);

		return dbUtility.getTrippleParamSQL(institutionNo, clientExRef,
				clientRefType,
				SQLStatements.SEL_cis_client_details_ref_OUR_REF_CHECK);

	}

	public synchronized boolean doINTFileExist(String fileID,
			SystemJob systemJob) {
		return doFileExist(fileID, systemJob,
				SQLStatements.SEL_int_file_log_details_FILE_CHECK);
	}

	public synchronized boolean doFileExist(String fileID, SystemJob systemJob,
			String sql) {
		boolean fileExist = false;
		PreparedStatement pstFileCheck = null;
		ResultSet rsFileCheck = null;
		try {
			pstFileCheck = dbConnection.prepareStatement(sql);
			// Taking the first 4 characters from the file name have to be
			// removed
			// String fileName =
			// systemJob.getParameterIn(systemJob.PARAM_NAME_FILE_NAME).substring(0,4);
			String fileName = systemJob
					.getParameterIn(SystemJob.PARAM_NAME_FILE_NAME);
			pstFileCheck.setString(1, systemJob.getInstitutionNo());
			pstFileCheck.setString(2, fileID);
			pstFileCheck.setString(3, fileName + SQL.PR);
			pstFileCheck.setString(4, systemJob.getName());
			System.out.println("FILE ID:" + fileID);
			rsFileCheck = pstFileCheck.executeQuery();
			fileExist = rsFileCheck.next();
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(pstFileCheck);
		}
		return fileExist;
	}

	/*
	 * public synchronized boolean doFileExist(String fileID, SystemJob
	 * systemJob, String fileTable){ boolean fileExist = false;
	 * PreparedStatement pstFileCheck = null; ResultSet rsFileCheck = null; try {
	 * pstFileCheck =
	 * dbConnection.prepareStatement(DynamicSQLStatements.getCheckFile(fileTable)); //
	 * Taking the first 4 characters from the file name String fileName =
	 * systemJob.getParameterIn(systemJob.PARAM_NAME_FILE_NAME).substring(0,4);
	 * pstFileCheck.setString(1,systemJob.getInstitutionNo());
	 * pstFileCheck.setString(2,fileID ); pstFileCheck.setString(3,fileName +
	 * SQL.PR ); pstFileCheck.setString(4,systemJob.getName() );
	 * System.out.println("FILE ID:" + fileID ); rsFileCheck =
	 * pstFileCheck.executeQuery(); fileExist = rsFileCheck.next(); } catch
	 * (Exception ex){ handleDBError(ex); } finally
	 * {dbUtility.closeCursor(pstFileCheck);} return fileExist; }
	 */

	public synchronized boolean doFileExist(String fileID, String processName) {
		boolean fileExist = false;
		PreparedStatement pstFileCheck = null;
		ResultSet rsFileCheck = null;
		try {
			pstFileCheck = dbConnection
					.prepareStatement(SQLStatements.SEL_int_file_log_details_CHECK_FILE_ID_PROCESS_NO);
			// Taking the first 4 characters from the file name
			pstFileCheck.setString(1, fileID);
			pstFileCheck.setString(2, processName);
			rsFileCheck = pstFileCheck.executeQuery();
			fileExist = rsFileCheck.next();
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(pstFileCheck);
		}
		return fileExist;
	}

	public synchronized HashMap<String,Channel> loadChannels() {
		channels = new HashMap<String,Channel>();
		ResultSet rsChannels = null;
		try {
			rsChannels = dbUtility
					.executeQuery(SQLStatements.SEL_cbr_channel_def_ALL_RECORDs);
			while (rsChannels.next()) {
				// Specifying the channel id through testing which value
				String trnSource = rsChannels
						.getString(COL_CBR_CHANNEL_DEF_TRN_SRC);
				String trnDestination = rsChannels
						.getString(COL_CBR_CHANNEL_DEF_TRN_DEST);
				String InstitutionNo = rsChannels
						.getString(COL_CBR_CHANNEL_DEF_INST_NUM);
				String TranCurrency = rsChannels
						.getString(COL_CBR_CHANNEL_DEF_TRN_CUR);
				String SettlementCurrency = rsChannels
						.getString(COL_CBR_CHANNEL_DEF_SETL_CUR);
				String ClientNo = rsChannels
						.getString(COL_CBR_CHANNEL_DEF_CLNT_NUM);
				String ClientBussType = rsChannels
						.getString(COL_CBR_CHANNEL_DEF_CLNT_BUSS_TYPE);
				String InterchangeInstitution = rsChannels
						.getString(COL_CBR_CHANNEL_DEF_INTR_INST);
				String AuditTrail = rsChannels
						.getString(COL_CBR_CHANNEL_DEF_AUDIT_TRAIL);
				String RecordDate = rsChannels
						.getString(COL_CBR_CHANNEL_DEF_REC_DATE);
				String RecordType = rsChannels
						.getString(COL_CBR_CHANNEL_DEF_REC_TYPE);
				String CardOrganization = rsChannels
						.getString(COL_CBR_CHANNEL_DEF_CARD_ORGA);
				String AreaOfEvent = rsChannels
						.getString(COL_CBR_CHANNEL_DEF_AREA_OF_EVENT);
				String ServiceType = rsChannels
						.getString(COL_CBR_CHANNEL_DEF_SRV_TYPE);
				String ChargeType = rsChannels
						.getString(COL_CBR_CHANNEL_DEF_CHG_TYPE);
				// String TrnFormat =
				// rsChannels.getString(COL_CBR_CHANNEL_DEF_TRN_FRMT );
				String channelID = trnSource;
				if (trnSource.equals(CHANNEL_ID_NOT_EXIST))
					channelID = trnDestination;
				// Channel newChannel = new Channel( InstitutionNo, channelID,
				// TranCurrency, SettlementCurrency,
				// ClientNo,ClientBussType,InterchangeInstitution, AuditTrail,
				// RecordDate, RecordType,CardOrganization,
				// AreaOfEvent,ServiceType,ChargeType,TrnFormat);
				Channel newChannel = new Channel(InstitutionNo, channelID,
						TranCurrency, SettlementCurrency, ClientNo,
						ClientBussType, InterchangeInstitution, AuditTrail,
						RecordDate, RecordType, CardOrganization, AreaOfEvent,
						ServiceType, ChargeType);
				channels.put(newChannel.getKey(), newChannel);
			}
		} catch (Exception ex) {
			handleDBError(ex);
			channels = null;
		} finally {
			dbUtility.closeCursor(rsChannels);
		}
		return channels;
	}

	public synchronized void loadInterchangeDetails() {
		interchangeDetails = new HashMap<String,String>();
		String lastKey = null;
		int keyCount = 0;
		ResultSet rsDet = dbUtility
				.executeQuery(SQLStatements.SEL_cis_interchange_details_ALL_RECORDS);
		try {
			while (rsDet.next()) {
				String detInst = rsDet.getString(COL_CIS_INTR_DTL_INST_NUM);
				String detCardOrg = rsDet.getString(COL_CIS_INTR_DTL_CARD_ORGA);
				String detClientRegion = rsDet
						.getString(COL_CIS_INTR_DTL_CLNT_RGN);
				String detailKey = detInst + detCardOrg + detClientRegion;
				if (lastKey == null) {
					lastKey = detailKey;
					keyCount = 1;
				} else if (lastKey.equals(detailKey)) {
					keyCount++;
				} else {
					interchangeDetails.put(lastKey, Integer.toString(keyCount));
					lastKey = detailKey;
					keyCount = 1;
				}
				if (!interchangeDetails.containsKey(lastKey))
					interchangeDetails.put(lastKey, Integer.toString(keyCount));
			}

		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsDet);
		}

	}

	public synchronized void loadTransactionTypes() {
		transTypes = new HashMap<String,TranType>();
		ResultSet rsTans = dbUtility
				.executeQuery(SQLStatements.SEL_bwt_transaction_types_ALL_RECORDS);
		try {
			while (rsTans.next()) {
				String tranIndex = rsTans
						.getString(COL_BWT_TRANSACTION_TYPE_INX_FLD);
				String tranSrcSign = rsTans
						.getString(COL_BWT_TRANSACTION_TYPE_SRC_SIGN);
				String tranDestSign = rsTans
						.getString(COL_BWT_TRANSACTION_TYPE_DEST_SIGN);
				TranType newTransType = new TranType(tranIndex, tranSrcSign,
						tranDestSign);
				transTypes.put(newTransType.getIndex(), newTransType);
			}
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsTans);
		}
	}

	public synchronized void loadISOBussClasses() {
		isoBussClass = new HashMap<String,String>();
		idxBussClass = new HashMap<String,String>();
		ResultSet rsBuss = dbUtility
				.executeQuery(SQLStatements.SEL_bwt_iso_bus_cls_ALL_RECORDS);
		try {
			while (rsBuss.next()) {
				String indexField = rsBuss
						.getString(COL_BWT_ISO_BUS_CLS_IDX_FLD);
				String isoCode = rsBuss.getString(COL_BWT_ISO_BUS_CLS_ISO_CODE);
				isoBussClass.put(indexField, isoCode);
				idxBussClass.put(isoCode, indexField);
			}
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsBuss);
		}
		// System.out.println("ISO BUSINESS CLASSES :" + isoBussClasses.size());
	}

	public synchronized String getFileNo(String institutionNo) {
		String newFileNo = dbUtility.getSingleParamFunctionString(
				LIB_SEQ_GENERATEFILENUMBER, institutionNo);
		return TextUtil.fillLeftSide(newFileNo, DB_SEQUENTIAL_NO_FILE_WIDTH,
				'0');
	}

	public synchronized String getRcnSlipNo(String institutionNo) {
		String newSlipNo = dbUtility.getSingleParamFunctionStringReturnString(
				LIB_SEQ_GENERATERCNSLIPNUMBER, institutionNo);
		return newSlipNo;
	}

	public synchronized String getRcnSlipNo(String institutionNo,
			String postingDate) {
		String newSlipNo = dbUtility.getDoubleParamFunctionStringReturnString(
				LIB_SEQ_GENERATERCNSLIPNUMBER, institutionNo, postingDate);
		return newSlipNo;
	}

	public synchronized String getFileNo(String procedure, String institutionNo) {
		String newFileNo = dbUtility.getSingleParamFunctionString(procedure,
				institutionNo);
		return TextUtil.fillLeftSide(newFileNo, DB_SEQUENTIAL_NO_FILE_WIDTH,
				'0');
	}

	public synchronized HashMap<String,String> getExtraCharges(String sqlText) {
		HashMap<String,String> extraCharges = new HashMap<String,String>();
		ResultSet rsExtra = dbUtility.executeQuery(sqlText);
		try {
			while (rsExtra.next()) {
				String indexField = rsExtra
						.getString(COL_BWT_EXR_CHG_AUTO_RENTAL_IDX_FLD);
				String visaCode = rsExtra
						.getString(COL_BWT_EXR_CHG_AUTO_RENTAL_VISA_CODE);
				String inetCode = rsExtra
						.getString(COL_BWT_EXR_CHG_AUTO_RENTAL_INET_CODE);
				if (visaCode == null)
					visaCode = inetCode;
				if (visaCode != null)
					extraCharges.put(visaCode, indexField);
			}
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsExtra);
		}
		// System.out.println("Extra Charges : " + extraCharges.size());
		return extraCharges;
	}

	public String getCountryRegion(String institutionNo, String Country) {
		return (String) systemCountryRegion.get(institutionNo + Country);
	}

	public String getRegion(String regionIndex, String Language) {
		if (Language == null)
			Language = LANG_USA;
		return (String) regions.get(regionIndex + Language);
	}

	public synchronized void loadCountryCodes() {
		countryCodes2 = new HashMap<String,String>();
		countryCodes3 = new HashMap<String,String>();
		countrys = new HashMap<String,Country>();
		ResultSet rsCodes = dbUtility
				.executeQuery(SQLStatements.SEL_bwt_country_ALL_RECORDS);
		try {
			while (rsCodes.next()) {
				Country newCountry = new Country();
				String indexField = rsCodes
						.getString(DBName.COL_BWT_CTRY_IDX_FLD);
				String countryCode2 = rsCodes
						.getString(DBName.COL_BWT_CTRY_CTRY_CODE_2);
				String countryCode3 = rsCodes
						.getString(DBName.COL_BWT_CTRY_CTRY_CODE_3);
				newCountry.setIsoValue(indexField);
				newCountry.setCountryCodeOf2(countryCode2);
				newCountry.setCountryCodeOf3(countryCode3);
				newCountry.setName(rsCodes
						.getString(DBName.COL_BWT_CTRY_CLNT_CTRY));
				newCountry.setNationality(rsCodes
						.getString(DBName.COL_BWT_CTRY_NATIONALITY));
				newCountry.setTelCode(rsCodes
						.getString(DBName.COL_BWT_CTRY_TEL_CODE));

				if (countryCode2 != null)
					countryCodes2.put(countryCode2, indexField);
				if (countryCode3 != null)
					countryCodes3.put(countryCode3, indexField);
				countrys.put(indexField, newCountry);
			}
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsCodes);
		}
	}

	public synchronized void loadCountryStateCodes() {
		countryStateCodes = new HashMap<String,String>();
		ResultSet rsCntryStates = dbUtility
				.executeQuery(SQLStatements.SEL_bwt_country_state_ALL_RECORDS);
		try {
			while (rsCntryStates.next()) {
				// String indexField =
				// rsCntryStates.getString(COL_BWT_CTRY_IDX_FLD );
				String stateCode = rsCntryStates
						.getString(COL_BWT_CTRY_STATE_STATE_CODE);
				String countryCode = rsCntryStates
						.getString(COL_BWT_CTRY_STATE_CTRY_CODE);
				countryStateCodes.put(stateCode, countryCode);
			}
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsCntryStates);
		}
	}

	public synchronized void loadSystemCountryRegion() {
		systemCountryRegion = new HashMap<String,String>();
		ResultSet rsCntryRegion = dbUtility
				.executeQuery(SQLStatements.SEL_sys_country_region_ALL_RECORDS);
		try {
			while (rsCntryRegion.next()) {
				String region = rsCntryRegion.getString(COL_SYS_CTRY_RGN_RGN);
				String countryCode = rsCntryRegion
						.getString(COL_SYS_CTRY_RGN_CTRY);
				String instNum = rsCntryRegion
						.getString(COL_SYS_CTRY_RGN_INST_NUM);
				systemCountryRegion.put(instNum + countryCode, region);
			}
			// System.out.println("Generated region codes number (" +
			// systemCountryRegion.size() + ")");
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsCntryRegion);
		}
	}

	public synchronized void loadRegions() {
		regions = new HashMap<String,String>();
		ResultSet rsRegion = dbUtility
				.executeQuery(SQLStatements.SEL_bwt_regions_ALL_RECORDS);
		try {
			while (rsRegion.next()) {
				String indexField = rsRegion.getString(COL_BWT_RGN_IDX_FLD);
				String lang = rsRegion.getString(COL_BWT_RGN_LANG);
				String region = rsRegion.getString(COL_BWT_RGN_RGN);
				regions.put(indexField + lang, region);
			}
			// System.out.println("Generated regions number (" + regions.size()
			// + ")");
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsRegion);
		}
	}

	public synchronized void loadCurrencies() {
		currencies = new HashMap<String,Currency>();
		ResultSet rsCurrency = dbUtility
				.executeQuery(SQLStatements.SEL_bwt_currency_MULTI_RECORD);
		// System.out.println(SQLStatements.SEL_bwt_currency_MULTI_RECORD);
		try {
			while (rsCurrency.next()) {
				Currency newCurrency = new Currency();
				newCurrency.setISOCode(rsCurrency
						.getString(COL_BWT_CUR_ISO_CODE));
				newCurrency.setSwiftCode(rsCurrency
						.getString(COL_BWT_CUR_SWIFT_CODE));
				newCurrency.setLocalCode(rsCurrency
						.getString(COL_BWT_CUR_LOCAL_CODE));
				newCurrency.setName(rsCurrency.getString(COL_BWT_CUR_NAME));
				newCurrency.setExponent(rsCurrency.getInt(COL_BWT_CUR_EXPN));
				newCurrency.setCalcBase(rsCurrency
						.getInt(COL_BWT_CUR_CALC_BASE));
				newCurrency.setSymbol(rsCurrency.getString(COL_BWT_CUR_SYMBOL));
				newCurrency.setSortField(rsCurrency
						.getString(COL_BWT_CUR_SORT_FLD));

				currencies.put(newCurrency.getISOCode(), newCurrency);
			}
			// System.out.println("Generated regions number (" + regions.size()
			// + ")");
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsCurrency);
		}
	}

	/*
	 * public synchronized boolean checkInterchangeDetails(String
	 * InstitutionNo,String CardOrganization,String ClientRegion) throws
	 * CardConflictException{ boolean result = false; boolean cardOrgError =
	 * false; if
	 * ((InstitutionNo==null)||(CardOrganization==null)||(ClientRegion==null))
	 * return false; int recordCount =
	 * dbUtility.getCount(dbUtility.getTrippleParamSQL(InstitutionNo,CardOrganization,ClientRegion,SQLStatements.SEL_cis_interchange_details_COUNT_RECORDS));
	 * result = recordCount>0; if (result){ if
	 * (CardOrganization.equals(ORG_MASTERCARD)){ if
	 * (checkInterchangeDetails(InstitutionNo,ORG_EUROPAY,ClientRegion))
	 * cardOrgError = true; }else if (CardOrganization.equals(ORG_EUROPAY)){ if
	 * (checkInterchangeDetails(InstitutionNo,ORG_MASTERCARD,ClientRegion))
	 * cardOrgError = true; } if (cardOrgError) throw new
	 * CardConflictException(InstitutionNo,getRegion(ClientRegion,null)); }
	 * return result; }
	 */

	public synchronized boolean checkInterchangeDetails(String InstitutionNo,
			String CardOrganization, String ClientRegion)
			throws CardConflictException {
		boolean result = false;
		boolean cardOrgError = false;
		if ((InstitutionNo == null) || (CardOrganization == null)
				|| (ClientRegion == null))
			return false;
		String key = InstitutionNo + CardOrganization + ClientRegion;
		if (interchangeDetails.containsKey(key)) {
			int recCount = Integer.parseInt((String) interchangeDetails
					.get(key));
			result = recCount > 0;
			if (result) {
				if (CardOrganization.equals(ORG_MASTERCARD)) {
					if (checkInterchangeDetails(InstitutionNo, ORG_EUROPAY,
							ClientRegion))
						cardOrgError = true;
				} else if (CardOrganization.equals(ORG_EUROPAY)) {
					if (checkInterchangeDetails(InstitutionNo, ORG_MASTERCARD,
							ClientRegion))
						cardOrgError = true;
				}
				if (cardOrgError)
					throw new CardConflictException(InstitutionNo, getRegion(
							ClientRegion, null));
			}
		}
		return result;
	}

	public synchronized void loadExtraChargesCarRental() {
		extraChargesCarRental = getExtraCharges(SQLStatements.SEL_bwt_exr_chg_car_rental_ALL_RECORDS);
	}

	public synchronized void loadExtraChargesLodging() {
		extraChargesLodging = getExtraCharges(SQLStatements.SEL_bwt_exr_chg_lodging_ALL_RECORDS);
	}

	public boolean isContractDetailsExist(boolean useGroup,
			boolean getGroupClt, String contractType, String clientNumber,
			String effectiveDate, String institutionNumber, String groupNumber) {
		boolean result = false;
		ResultSet rsCntr = null;
		try {
			rsCntr = getContractDetails(useGroup, getGroupClt, contractType,
					clientNumber, effectiveDate, institutionNumber, groupNumber);
			result = dbUtility.isExist(rsCntr);
			return dbUtility.isExist(rsCntr);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			dbUtility.closeCursor(rsCntr);
		}
		return result;
	}

	public ResultSet getContractDetails(boolean useGroup, boolean getGroupClt,
			String contractType, String clientNumber, String effectiveDate,
			String institutionNumber, String groupNumber) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rsCntr = null;
		String sqlStatement = DynamicSQLStatements.getContractDetails(useGroup,
				getGroupClt);
		System.out.println(sqlStatement);
		stmt = dbConnection.prepareStatement(sqlStatement);
		stmt.setString(1, contractType);
		stmt.setString(2, clientNumber);
		stmt.setString(3, institutionNumber);
		stmt.setString(4, contractType);
		stmt.setString(5, clientNumber);
		stmt.setString(6, institutionNumber);
		stmt.setString(7, effectiveDate);
		if (useGroup) {
			stmt.setString(8, groupNumber);
			stmt.setString(9, groupNumber);
			if (getGroupClt)
				stmt.setString(10, effectiveDate);
		} else if (getGroupClt) {
			stmt.setString(8, effectiveDate);
		}
		rsCntr = stmt.executeQuery();
		return rsCntr;
	}

	public ServiceContract getServiceContract(String institutionNumber,
			String contractID) {
		ResultSet rsSvrCntr = null;
		ServiceContract newContract = null;
		try {
			rsSvrCntr = dbUtility.getDoubleParamSQL(institutionNumber,
					contractID,
					SQLStatements.SEL_cbr_service_contract_SINGLE_RECORD);
			if (rsSvrCntr.next()) {
				newContract = new ServiceContract();
				newContract.setInstitutionNumber(institutionNumber);
				newContract.setServiceContractID(contractID);
				newContract.setContractType(rsSvrCntr
						.getString(COL_CBR_SVR_CNTR_TYPE));
				newContract.setNoteText(rsSvrCntr
						.getString(COL_CBR_SVR_CNTR_NOTE_TXT));
				newContract.setRecordDate(rsSvrCntr
						.getString(COL_CBR_SVR_CNTR_REC_DATE));
				newContract.setReviewPeriod(rsSvrCntr
						.getString(COL_CBR_SVR_CNTR_REVIEW_PERIOD));
			}
		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rsSvrCntr);
		}

		return newContract;
	}

	public ClientLink loadClientLink(boolean useGroup, boolean getGroupClt,
			String contractType, String clientNumber, String effectiveDate,
			String institutionNumber, String groupNumber) {
		ResultSet rs = null;
		ClientLink newClient = null;
		try {

			rs = getContractDetails(useGroup, getGroupClt, contractType,
					clientNumber, effectiveDate, institutionNumber, groupNumber);
			if (rs.next()) {
				String contractID = rs.getString(COL_CIS_CLNT_LNK_CNTR_REF);
				newClient.setInstitutionNumber(rs
						.getString(COL_CIS_CLNT_LNK_INST_NUM));
				newClient.setBankReference(rs
						.getString(COL_CIS_CLNT_LNK_BANK_REF));
				newClient.setClientNumber(rs
						.getString(COL_CIS_CLNT_LNK_CLNT_NUM));
				newClient.setClientBranch(rs
						.getString(COL_CIS_CLNT_LNK_CLNT_BRANCH));
				newClient.setClientLevel(rs
						.getString(COL_CIS_CLNT_LNK_CLNT_LEVEL));
				newClient.setClientTariff(rs
						.getString(COL_CIS_CLNT_LNK_CLNT_TARIFF));
				newClient.setChargeTierLevel(rs
						.getString(COL_CIS_CLNT_LNK_CHG_TIER_LEVEL));
				newClient.setContractCategory(rs
						.getString(COL_CIS_CLNT_LNK_CNTR_CATG));
				newClient.setContractStatus(rs
						.getString(COL_CIS_CLNT_LNK_CNTR_STATUS));
				newClient.setCostCenter(rs
						.getString(COL_CIS_CLNT_LNK_COST_CENTER));
				newClient.setEffectiveDate(rs
						.getString(COL_CIS_CLNT_LNK_EFFECTIVE_DATE));
				newClient.setExpiryDate(rs
						.getString(COL_CIS_CLNT_LNK_EXPIRY_DATE));
				newClient
						.setGroupNumber(rs.getString(COL_CIS_CLNT_LNK_GRP_NUM));
				newClient.setInstitutionAccountOfficer(rs
						.getString(COL_CIS_CLNT_LNK_INST_ACCT_OFC));
				newClient.setProviderAccountOfficer(rs
						.getString(COL_CIS_CLNT_LNK_PROVIDER_ACCT_OFC));
				newClient.setParentClientNumber(rs
						.getString(COL_CIS_CLNT_LNK_PARENT_CLNT_NUM));
				newClient.setPostingMethod(rs
						.getString(COL_CIS_CLNT_LNK_POSTING_METH));
				newClient
						.setRecordDate(rs.getString(COL_CIS_CLNT_LNK_REC_DATE));
				newClient.setSettlementMethod(rs
						.getString(COL_CIS_CLNT_LNK_SETT_METH));
				newClient.setServiceContract(getServiceContract(
						institutionNumber, contractID));
			}

		} catch (Exception ex) {
			handleDBError(ex);
		} finally {
			dbUtility.closeCursor(rs);
		}
		return newClient;
	}

	public static final String CHANNEL_ID_NOT_EXIST = "999";
}
