package org.smyld.bw.data.structurs;

import org.smyld.SMYLDObject;
import org.smyld.bw.util.ParametersSet;
import org.smyld.log.SMYLDDebugHandler;
import org.smyld.util.SMYLDDate;

/**
 * This class will hold the information about a certain job wich exists in the
 * job Que (int_process_log) table.
 */
public class SystemJob extends SMYLDObject {
	private String No;
	private String ID;
	private String Name;
	private String InstitutionNo;
	private String UserID;
	private String StationNo;
	private String status;
	private String ParentProcessNo;
	private SMYLDDate PostingDate;
	private ParametersSet ParametersIn;
	private ParametersSet ParametersOut;
	private SMYLDDebugHandler ProcessLog;

	static final long serialVersionUID = 1412819887941196619L;

	public SystemJob(String no, String id, String name, String institutionNo,
			String stationNo, String userID, String postingDate) {
		this.No = no;
		this.ID = id;
		this.Name = name;
		this.InstitutionNo = institutionNo;
		this.StationNo = stationNo;
		this.UserID = userID;
		try {
			this.PostingDate = new SMYLDDate(SMYLDDate.FRM_yyyyMMdd, postingDate);
			if (PostingDate != null)
				postingDateText = PostingDate.toString();
		} catch (Exception ex) {
			this.PostingDate = null;
		}

	}

	public void setParametersIn(String parametersValue) {
		if (ParametersIn == null)
			ParametersIn = new ParametersSet(parametersValue);
	}

	public String getID() {
		return this.ID;
	}

	public String getName() {
		return this.Name;
	}

	public void addOutPutParameter(String parameterName, String parameterValue) {
		if (ParametersOut == null)
			ParametersOut = new ParametersSet();
		ParametersOut.addParameter(parameterName, parameterValue);
	}

	public void addOutPutParameter(int parameterSetIndex, String parameterName,
			String parameterValue) {
		if (ParametersOut == null)
			ParametersOut = new ParametersSet();
		ParametersOut.addParameter(parameterSetIndex, parameterName,
				parameterValue);
	}

	public int createNewOutputEnvelope() {
		if (ParametersOut == null)
			ParametersOut = new ParametersSet();
		return ParametersOut.addParametersSet();
	}

	public String getInstitutionNo() {
		return this.InstitutionNo;
	}

	public String getUserID() {
		return this.UserID;
	}

	public String getStationNo() {
		return this.StationNo;
	}

	public SMYLDDate getPostingDate() {
		return this.PostingDate;
	}

	public String getParameterOut(int parameterSet, String parameterName) {
		return ParametersOut.getParameterValue(parameterSet, parameterName);
	}

	public String getParameterOut(String parameterName) {
		return ParametersOut.getParameterValue(1, parameterName);
	}

	public String getParameterIn(String parameterName) {
		return ParametersIn.getParameterValue(parameterName);
	}

	public String getParametersOutValue() {
		return ParametersOut.printParameters();
	}

	public int getParamOutSetsCount() {
		if (ParametersOut != null)
			return ParametersOut.getSetsCount();
		return -1;
	}

	public String getNo() {
		return this.No;
	}

	@Override
	public boolean equals(Object compareObject) {
		if (compareObject instanceof SystemJob) {
			SystemJob comparedProcess = (SystemJob) compareObject;
			return comparedProcess.getNo().equals(this.No);
		}
		return false;
	}

	public void setStatus(String newValue) {
		status = newValue;
	}

	public String getStatus() {
		return status;
	}

	public static final String PARAM_NAME_FILE_NAME = "p_filename";
	public static final String PARAM_NAME_FILE_PATH = "p_filepath";
	public static final String PARAM_NAME_FILE_NUM = "p_fileno";
	public static final String PARAM_NAME_INST_NUM = "p_instno";
	public static final String PARAM_NAME_PROC_NAME = "p_process_name";

	// Process status values
	public static final String PROCESS_COMPLETED = "0";
	public static final String PROCESS_TERMINATED = "1";
	public static final String PROCESS_CANCELED = "3";
	public static final String PROCESS_ALREADY_RUN = "2";
	public static final String PROCESS_In_Process = "4";

	String postingDateText;

	public String getParentProcessNo() {
		return ParentProcessNo;
	}

	public void setParentProcessNo(String newParentProcessNo) {
		ParentProcessNo = newParentProcessNo;
	}

	public SMYLDDebugHandler getProcessLog() {
		return ProcessLog;
	}

	public void setProcessLog(SMYLDDebugHandler ProcessLog) {
		this.ProcessLog = ProcessLog;
	}

	public void setName(String newName) {
		Name = newName;
	}

	public String getPostingDateText() {
		return postingDateText;
	}

}
