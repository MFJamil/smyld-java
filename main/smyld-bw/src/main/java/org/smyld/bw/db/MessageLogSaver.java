package org.smyld.bw.db;

import org.smyld.bw.data.structurs.MessageLog;
import org.smyld.bw.data.structurs.SystemJob;
import org.smyld.db.DBConnection;
import org.smyld.db.DBErrorHandler;
import org.smyld.text.TextUtil;
import org.smyld.util.SMYLDDate;
import org.smyld.util.Util;

public class MessageLogSaver extends BWDataBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessageLogSaver(DBErrorHandler e, DBConnection connection) {
		super(e, connection);
	}

	public MessageLogSaver(DBErrorHandler e) {
		super(e);
	}

	protected synchronized void setMessageAdditionalText(MessageLog newMessage,
			Exception exp) {
		setMessageAdditionalText(newMessage, Util
				.extractExceptionStackTrace(exp));
	}

	protected synchronized void setMessageAdditionalText(MessageLog newMessage,
			String expText) {
		if ((expText != null)
				&& (expText.length() > PRC_MSG_LOG_ADD_TEXT_WIDTH))
			expText = TextUtil.resize(expText, PRC_MSG_LOG_ADD_TEXT_WIDTH);
		newMessage.AdditionalText = expText;
	}

	public synchronized void addProgramException(SystemJob systemJob,
			Exception exp, String errorSource, String functionName) {
		try {
			addProgramException(systemJob,
					Util.extractExceptionStackTrace(exp), errorSource,
					functionName);
		} catch (Exception e) {
			System.out
					.println("Can not log messages into the system due to the below reason :");
			e.printStackTrace();
		}

	}

	public synchronized void addProgramException(SystemJob systemJob,
			String exceptionText, String errorSource, String functionName) {
		MessageLog newMessage = fillOneParamMessage(systemJob,
				MSG_CODE_JAVA_EXCEPTION, PRC_MSG_TYP_FATAL, errorSource, null,
				null, functionName);
		setMessageAdditionalText(newMessage,
				"Unknown exception ! please contact SMYLD and send this technical details ('"
						+ exceptionText + "')");
		insertMessageLog(newMessage);
	}

	// General Methodes
	protected synchronized String getProcessFileName(SystemJob systemJob) {
		return systemJob.getParameterIn(SystemJob.PARAM_NAME_FILE_NAME);
	}

	protected synchronized String getProcessFilePath(SystemJob systemJob) {
		return systemJob.getParameterIn(SystemJob.PARAM_NAME_FILE_PATH);
	}

	public synchronized void addOneParamMessage(String msgText,
			SystemJob systemJob, String msgCode, String msgType, String param1,
			String fileNo, String trnSlip, String functionName) {
		MessageLog newMessage = fillOneParamMessage(systemJob, msgCode,
				msgType, param1, fileNo, trnSlip, functionName);
		if (msgText != null)
			setMessageAdditionalText(newMessage, msgText);
		insertMessageLog(newMessage);
	}

	public synchronized void addTwoParamMessage(String msgText,
			SystemJob systemJob, String msgCode, String msgType, String param1,
			String param2, String fileNo, String trnSlip, String functionName) {
		MessageLog newMessage = fillTwoParamMessage(systemJob, msgCode,
				msgType, param1, param2, fileNo, trnSlip, functionName);
		if (msgText != null)
			setMessageAdditionalText(newMessage, msgText);
		insertMessageLog(newMessage);
	}

	public synchronized void addThreeParamMessage(String msgText,
			SystemJob systemJob, String msgCode, String msgType, String param1,
			String param2, String param3, String fileNo, String trnSlip,
			String functionName) {
		MessageLog newMessage = fillThreeParamMessage(systemJob, msgCode,
				msgType, param1, param2, param3, fileNo, trnSlip, functionName);
		if (msgText != null)
			setMessageAdditionalText(newMessage, msgText);
		insertMessageLog(newMessage);
	}

	public synchronized void addFourParamMessage(String msgText,
			SystemJob systemJob, String msgCode, String msgType, String param1,
			String param2, String param3, String param4, String fileNo,
			String trnSlip, String functionName) {
		MessageLog newMessage = fillFourParamMessage(systemJob, msgCode,
				msgType, param1, param2, param3, param4, fileNo, trnSlip,
				functionName);
		if (msgText != null)
			setMessageAdditionalText(newMessage, msgText);
		insertMessageLog(newMessage);
	}

	public synchronized void addFiveParamMessage(String msgText,
			SystemJob systemJob, String msgCode, String msgType, String param1,
			String param2, String param3, String param4, String param5,
			String fileNo, String trnSlip, String functionName) {
		MessageLog newMessage = fillFiveParamMessage(systemJob, msgCode,
				msgType, param1, param2, param3, param4, param5, fileNo,
				trnSlip, functionName);
		if (msgText != null)
			setMessageAdditionalText(newMessage, msgText);
		insertMessageLog(newMessage);
	}

	public synchronized MessageLog fillOneParamMessage(SystemJob systemJob,
			String msgCode, String msgType, String param1, String fileNo,
			String trnSlip, String functionName) {
		SMYLDDate currentDate = new SMYLDDate(SMYLDDate.FRM_yyyyMMdd);
		MessageLog newMessageLog = new MessageLog();
		// newMessageLog.InstitutionNo = BWSystem.defaultInstitutionNo;
		if (systemJob != null) {
			newMessageLog.InstitutionNo = systemJob.getInstitutionNo();
			newMessageLog.ProcessNo = systemJob.getNo();
		}
		// newMessageLog.No =
		// createSequentialNumber(TABLE_INT_PROC_MSG_LOG,COL_INT_PROC_MSG_LOG_MSG_NUM);
		// newMessageLog.No = getAbsoluteSequenceValue(SEQ_MSG_NUMBER);
		newMessageLog.FileNo = fileNo;
		newMessageLog.TrnSlip = trnSlip;
		newMessageLog.SystemDate = currentDate.toString();
		newMessageLog.SystemTime = currentDate
				.toString(SMYLDDate.FRM_HHmmss_SEP_2points);
		newMessageLog.ProcessMessageType = msgType;
		newMessageLog.Code = msgCode;
		// if ((param1!=null)&&(param1.length()>PRC_MSG_LOG_PARAM_WIDTH)) param1
		// = TextUtil.resize(param1,PRC_MSG_LOG_PARAM_WIDTH);
		// newMessageLog.Param1 = param1;
		newMessageLog.setParamValue(0, param1);
		newMessageLog.FunctionName = functionName;
		newMessageLog.ProcessMessageSource = PRC_MSG_SRC_JAVA;
		return newMessageLog;
	}

	public synchronized MessageLog fillTwoParamMessage(SystemJob systemJob,
			String msgCode, String msgType, String param1, String param2,
			String fileNo, String trnSlip, String functionName) {
		MessageLog newMessageLog = fillOneParamMessage(systemJob, msgCode,
				msgType, param1, fileNo, trnSlip, functionName);
		// newMessageLog.Param2 = param2;
		newMessageLog.setParamValue(1, param2);
		return newMessageLog;
	}

	public synchronized MessageLog fillThreeParamMessage(SystemJob systemJob,
			String msgCode, String msgType, String param1, String param2,
			String param3, String fileNo, String trnSlip, String functionName) {
		MessageLog newMessageLog = fillTwoParamMessage(systemJob, msgCode,
				msgType, param1, param2, fileNo, trnSlip, functionName);
		// newMessageLog.Param3 = param3;
		newMessageLog.setParamValue(2, param3);
		return newMessageLog;
	}

	public synchronized MessageLog fillFourParamMessage(SystemJob systemJob,
			String msgCode, String msgType, String param1, String param2,
			String param3, String param4, String fileNo, String trnSlip,
			String functionName) {
		MessageLog newMessageLog = fillThreeParamMessage(systemJob, msgCode,
				msgType, param1, param2, param3, fileNo, trnSlip, functionName);
		// newMessageLog.Param4 = param4;
		newMessageLog.setParamValue(3, param4);
		return newMessageLog;
	}

	public synchronized MessageLog fillFiveParamMessage(SystemJob systemJob,
			String msgCode, String msgType, String param1, String param2,
			String param3, String param4, String param5, String fileNo,
			String trnSlip, String functionName) {
		MessageLog newMessageLog = fillFourParamMessage(systemJob, msgCode,
				msgType, param1, param2, param3, param4, fileNo, trnSlip,
				functionName);
		// newMessageLog.Param5 = param5;
		newMessageLog.setParamValue(4, param5);
		return newMessageLog;
	}

	public synchronized MessageLog fillSixParamMessage(SystemJob systemJob,
			String msgCode, String msgType, String param1, String param2,
			String param3, String param4, String param5, String param6,
			String fileNo, String trnSlip, String functionName) {
		MessageLog newMessageLog = fillFiveParamMessage(systemJob, msgCode,
				msgType, param1, param2, param3, param4, param5, fileNo,
				trnSlip, functionName);
		// newMessageLog.Param6 = param6;
		newMessageLog.setParamValue(5, param6);
		return newMessageLog;
	}

	// To be later developed
	public synchronized MessageLog fillMultiParamMessage(SystemJob systemJob,
			String msgCode, String msgType, String[] params, String fileNo,
			String trnSlip, String functionName) {
		return null;
	}

	public synchronized boolean insertMessageLog(MessageLog newMessage) {
		// if (newMessage.Code.equals())
		// Tester.addFileLine(newMessage.toString());
		// Constructing the parameters for the message
		String[] params = new String[27];
		params[0] = newMessage.ProcessNo;
		params[1] = newMessage.ProcessMessageType;
		params[2] = newMessage.ProcessMessageSource;
		params[3] = newMessage.Code;
		params[4] = newMessage.ExternalCode;
		if (newMessage.isFullMessageDescription()) {
			newMessage.constructFullMessageDescription();
			params[5] = newMessage.fullMessageDescription;
		} else {
			params[5] = newMessage.AdditionalText;
		}

		params[6] = newMessage.FunctionName;
		params[7] = newMessage.InstitutionNo;
		params[8] = newMessage.FileNo;
		params[9] = newMessage.TrnSlip;
		params[10] = newMessage.getParamValue(0);
		params[11] = newMessage.getParamValue(1);
		params[12] = newMessage.getParamValue(2);
		params[13] = newMessage.getParamValue(3);
		params[14] = newMessage.getParamValue(4);
		params[15] = newMessage.getParamValue(5);
		params[16] = newMessage.getParamValue(6);
		params[17] = newMessage.getParamValue(7);
		params[18] = newMessage.getParamValue(8);
		params[19] = newMessage.getParamValue(9);
		params[20] = newMessage.getParamValue(10);
		params[21] = newMessage.getParamValue(11);
		params[22] = newMessage.getParamValue(12);
		params[23] = newMessage.getParamValue(13);
		params[24] = newMessage.getParamValue(14);
		params[25] = newMessage.getParamValue(15);

		/*
		 * params[10] = newMessage.Param1; params[11] = newMessage.Param2;
		 * params[12] = newMessage.Param3; params[13] = newMessage.Param4;
		 * params[14] = newMessage.Param5; params[15] = newMessage.Param6;
		 * params[16] = newMessage.Param7; params[17] = newMessage.Param8;
		 * params[18] = newMessage.Param9; params[19] = newMessage.Param10;
		 * params[20] = newMessage.Param11; params[21] = newMessage.Param12;
		 * params[22] = newMessage.Param13; params[23] = newMessage.Param14;
		 * params[24] = newMessage.Param15; params[25] = newMessage.Param16;
		 */
		params[26] = " ";
		executeMultiParamProcedure(LIB_MESSAGES_INSERTPROCESSMESSAGE, params);

		/*
		 * PreparedStatement pstMsg = null; try { pstMsg =
		 * dbConnection.prepareStatement(SQLStatements.INS_int_proc_msg_log_NEW_RECORD);
		 * pstMsg.setString(1,newMessage.No ;
		 * pstMsg.setString(2,newMessage.ProcessNo );
		 * pstMsg.setString(3,newMessage.InstitutionNo );
		 * pstMsg.setString(4,newMessage.FileNo );
		 * pstMsg.setString(5,newMessage.TrnSlip );
		 * pstMsg.setString(6,newMessage.SystemDate );
		 * pstMsg.setString(7,newMessage.SystemTime );
		 * pstMsg.setString(8,newMessage.ProcessMessageType );
		 * pstMsg.setString(9,newMessage.Code );
		 * pstMsg.setString(10,newMessage.Param1 );
		 * pstMsg.setString(11,newMessage.Param2 );
		 * pstMsg.setString(12,newMessage.Param3 );
		 * pstMsg.setString(13,newMessage.Param4 );
		 * pstMsg.setString(14,newMessage.Param5 );
		 * pstMsg.setString(15,newMessage.Param6 );
		 * pstMsg.setString(16,newMessage.Param7 );
		 * pstMsg.setString(17,newMessage.Param8 );
		 * pstMsg.setString(18,newMessage.Param9 );
		 * pstMsg.setString(19,newMessage.Param10 );
		 * pstMsg.setString(20,newMessage.Param11 );
		 * pstMsg.setString(21,newMessage.Param12 );
		 * pstMsg.setString(22,newMessage.Param13 );
		 * pstMsg.setString(23,newMessage.Param14 );
		 * pstMsg.setString(24,newMessage.Param15 );
		 * pstMsg.setString(25,newMessage.Param16 );
		 * pstMsg.setString(26,newMessage.Occurance );
		 * pstMsg.setString(27,newMessage.ProcessMessageSource);
		 * pstMsg.setString(28,newMessage.ExternalCode );
		 * pstMsg.setString(29,newMessage.AdditionalText );
		 * pstMsg.setString(30,newMessage.FunctionName ); if
		 * (pstMsg.executeUpdate()>0) return true;
		 *  } catch (Exception ex) { handleDBError(ex); } finally
		 * {dbUtility.closeCursor(pstMsg); }
		 */
		return false;
	}

	/*
	 * public boolean insertMessageLog(MessageLog newMessage){ PreparedStatement
	 * pstMsg = null; //System.out.println(newMessage.toString()); try { pstMsg =
	 * dbConnection.prepareStatement(SQLStatements.INS_int_proc_msg_log_NEW_RECORD);
	 * pstMsg.setString(1,newMessage.No );
	 * pstMsg.setString(2,newMessage.ProcessNo );
	 * pstMsg.setString(3,newMessage.InstitutionNo );
	 * pstMsg.setString(4,newMessage.FileNo );
	 * pstMsg.setString(5,newMessage.TrnSlip );
	 * pstMsg.setString(6,newMessage.SystemDate );
	 * pstMsg.setString(7,newMessage.SystemTime );
	 * pstMsg.setString(8,newMessage.ProcessMessageType );
	 * pstMsg.setString(9,newMessage.Code );
	 * pstMsg.setString(10,newMessage.Param1 );
	 * pstMsg.setString(11,newMessage.Param2 );
	 * pstMsg.setString(12,newMessage.Param3 );
	 * pstMsg.setString(13,newMessage.Param4 );
	 * pstMsg.setString(14,newMessage.Param5 );
	 * pstMsg.setString(15,newMessage.Param6 );
	 * pstMsg.setString(16,newMessage.Param7 );
	 * pstMsg.setString(17,newMessage.Param8 );
	 * pstMsg.setString(18,newMessage.Param9 );
	 * pstMsg.setString(19,newMessage.Param10 );
	 * pstMsg.setString(20,newMessage.Param11 );
	 * pstMsg.setString(21,newMessage.Param12 );
	 * pstMsg.setString(22,newMessage.Param13 );
	 * pstMsg.setString(23,newMessage.Param14 );
	 * pstMsg.setString(24,newMessage.Param15 );
	 * pstMsg.setString(25,newMessage.Param16 );
	 * pstMsg.setString(26,newMessage.Occurance );
	 * pstMsg.setString(27,newMessage.ProcessMessageSource);
	 * pstMsg.setString(28,newMessage.ExternalCode );
	 * pstMsg.setString(29,newMessage.AdditionalText );
	 * pstMsg.setString(30,newMessage.FunctionName ); if
	 * (pstMsg.executeUpdate()>0) return true;
	 *  } catch (Exception ex) { handleDBError(ex); } finally
	 * {dbUtility.closeCursor(pstMsg); } return false; }
	 */
	/*
	 * // SMYLD Ref "10002002" public synchronized void
	 * addInvalidMsgType(SystemJob systemJob, String functionCode,String
	 * messageType,String functionName){ MessageLog newMessage =
	 * fillThreeParamMessage(systemJob,MSG_CODE_BATCH_RECON_WITHOUT_BATCH_HEADER
	 * ,PRC_MSG_TYP_FATAL,messageType,functionCode,getProcessFileName(systemJob),
	 * null,null,functionName); insertMessageLog(newMessage); } // SMYLD Ref
	 * "10002032" public synchronized void
	 * addBatchReconWithoutBatchHeader(SystemJob systemJob,String functionName){
	 * MessageLog newMessage =
	 * fillOneParamMessage(systemJob,MSG_CODE_BATCH_RECON_WITHOUT_BATCH_HEADER
	 * ,PRC_MSG_TYP_FATAL,getProcessFileName(systemJob),
	 * null,null,functionName); insertMessageLog(newMessage); } // SMYLD Ref
	 * "10002027" public synchronized void addBatchHeaderNotPresent(SystemJob
	 * systemJob,String functionName){ MessageLog newMessage =
	 * fillOneParamMessage(systemJob,MSG_CODE_BATCH_HEADER_NOT_PRESENT
	 * ,PRC_MSG_TYP_FATAL,getProcessFileName(systemJob),
	 * null,null,functionName); insertMessageLog(newMessage); } // SMYLD Ref
	 * "10001014" public synchronized void addBatchReconSumsError(SystemJob
	 * systemJob,String functionName){ MessageLog newMessage =
	 * fillOneParamMessage(systemJob,MSG_CODE_BATCH_RECON_SUMS_ERROR
	 * ,PRC_MSG_TYP_FATAL,getProcessFileName(systemJob),
	 * null,null,functionName); insertMessageLog(newMessage); }
	 *  // SMYLD Ref "10002014" public synchronized void addMsgOutOfSeq(SystemJob
	 * systemJob,String functionName){ MessageLog newMessage =
	 * fillOneParamMessage(systemJob,MSG_CODE_MESSAGES_OUT_OF_SEQ
	 * ,PRC_MSG_TYP_FATAL,getProcessFileName(systemJob),
	 * null,null,functionName); insertMessageLog(newMessage); }
	 *  // SMYLD Ref "90030078" public synchronized void
	 * addFileOpenError(SystemJob systemJob,String functionName){ MessageLog
	 * newMessage = fillTwoParamMessage(systemJob,MSG_CODE_GL_FILE_OPEN_ERROR
	 * ,PRC_MSG_TYP_FATAL,getProcessFileName(systemJob),getProcessFilePath(systemJob),
	 * null,null,functionName); insertMessageLog(newMessage); } // SMYLD Ref
	 * "99999999" public synchronized void addProcessInformingMessage(SystemJob
	 * systemJob){ MessageLog newMessage =
	 * fillTwoParamMessage(systemJob,"99999999"
	 * ,PRC_MSG_TYP_INFO,"p_Filename=>'" + getProcessFileName(systemJob) +
	 * "'","p_Filepath=>'" + getProcessFilePath(systemJob)+ "'",
	 * null,null,"Preprocess"); insertMessageLog(newMessage); }
	 * 
	 * public synchronized void addFileOpenError(SystemJob systemJob,String
	 * functionName, Exception ex){ MessageLog newMessage =
	 * fillTwoParamMessage(systemJob,MSG_CODE_GL_FILE_OPEN_ERROR
	 * ,PRC_MSG_TYP_FATAL,getProcessFileName(systemJob),getProcessFilePath(systemJob),
	 * null,null,functionName); setMessageAdditionalText(newMessage,ex);
	 * insertMessageLog(newMessage); } public synchronized void
	 * addMasterCardEuropayConflict(SystemJob systemJob,String functionName,
	 * Exception ex){ MessageLog newMessage =
	 * fillTwoParamMessage(systemJob,MSG_CODE_MASTERCARD_EUROPAY_CONFLICT
	 * ,PRC_MSG_TYP_ERROR,getProcessFileName(systemJob),getProcessFilePath(systemJob),
	 * null,null,functionName); setMessageAdditionalText(newMessage,ex);
	 * insertMessageLog(newMessage); }
	 *  // Not mapped error numbers //FILE_EXP_FILE_ALREADY_PROCESSED public
	 * synchronized void addFileAlreadyProcessedError(SystemJob systemJob,String
	 * fileID, String functionName){ MessageLog newMessage =
	 * fillTwoParamMessage(systemJob,MSG_CODE_GL_FILE_ALREADY_PROCESSED_ERROR
	 * ,PRC_MSG_TYP_FATAL,getProcessFileName(systemJob),fileID,
	 * null,null,functionName); insertMessageLog(newMessage); }
	 * 
	 * //MSG_CODE_CLEARING_CHANNEL_NOT_EXIST public synchronized void
	 * addClearingChannelNotExistError(SystemJob systemJob,String functionName){
	 * MessageLog newMessage =
	 * fillOneParamMessage(systemJob,MSG_CODE_CLEARING_CHANNEL_NOT_EXIST
	 * ,PRC_MSG_TYP_FATAL,getProcessFileName(systemJob),
	 * null,null,functionName); insertMessageLog(newMessage); } //
	 * MSG_CODE_BATCH_NOT_RECONCILED = "20000003"; public synchronized void
	 * addBatchNotReconciledError(SystemJob systemJob,String functionName){
	 * MessageLog newMessage =
	 * fillOneParamMessage(systemJob,MSG_CODE_BATCH_NOT_RECONCILED
	 * ,PRC_MSG_TYP_FATAL,getProcessFileName(systemJob),
	 * null,null,functionName); insertMessageLog(newMessage); } //
	 * MSG_CODE_MANDATORY_FIELDS_MISSED = "20000004"; public synchronized void
	 * addMandatoryFieldsMissedError(SystemJob systemJob,String functionName){
	 * MessageLog newMessage =
	 * fillOneParamMessage(systemJob,MSG_CODE_MANDATORY_FIELDS_MISSED
	 * ,PRC_MSG_TYP_FATAL,getProcessFileName(systemJob),
	 * null,null,functionName); insertMessageLog(newMessage); } //
	 * MSG_CODE_DATE_AFTER_POSTING_DATE = "20000005"; public synchronized void
	 * addDateAfterPostingDateError(SystemJob systemJob,String functionName){
	 * MessageLog newMessage =
	 * fillOneParamMessage(systemJob,MSG_CODE_DATE_AFTER_POSTING_DATE
	 * ,PRC_MSG_TYP_FATAL,getProcessFileName(systemJob),
	 * null,null,functionName); insertMessageLog(newMessage); } //
	 * MSG_CODE_PROCESS_NUMBER_DO_NOT_EXIST = "20000006"; public synchronized
	 * void addProcessNumberDoNotExistError(String processNo,String
	 * functionName){ MessageLog newMessage =
	 * fillOneParamMessage(null,MSG_CODE_PROCESS_NUMBER_DO_NOT_EXIST
	 * ,PRC_MSG_TYP_FATAL,processNo, null,null,functionName);
	 * insertMessageLog(newMessage); }
	 */

}
