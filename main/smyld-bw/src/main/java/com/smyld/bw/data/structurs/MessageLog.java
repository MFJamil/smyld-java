package com.smyld.bw.data.structurs;

import com.smyld.SMYLDObject;
import com.smyld.bw.db.DBValue;
import com.smyld.db.Field;
import com.smyld.text.TextUtil;

public class MessageLog extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Field[] params = new Field[16];
	public String No;
	public String ProcessNo;
	public String InstitutionNo;
	public String FileNo;
	public String TrnSlip;
	public String SystemDate;
	public String SystemTime;
	public String ProcessMessageType;
	public String Code;
	public String Param1;
	public String Param2;
	public String Param3;
	public String Param4;
	public String Param5;
	public String Param6;
	public String Param7;
	public String Param8;
	public String Param9;
	public String Param10;
	public String Param11;
	public String Param12;
	public String Param13;
	public String Param14;
	public String Param15;
	public String Param16;
	public String Occurance;
	public String ProcessMessageSource;
	public String ExternalCode;
	public String AdditionalText;
	public String fullMessageDescription;
	public String FunctionName;
	private boolean showFullMessageDescription;
	StringBuffer fulMsgBuf;
	int totalParamNo;
	int maxParamNo = 16;

	public MessageLog() {

	}

	@Override
	public void printInstanceValues() {
		insertInstanceValue("No                      =" + getLenName(No));
		insertInstanceValue("ProcessNo               =" + getLenName(ProcessNo));
		insertInstanceValue("InstitutionNo           ="
				+ getLenName(InstitutionNo));
		insertInstanceValue("FileNo                  =" + getLenName(FileNo));
		insertInstanceValue("TrnSlip                 =" + getLenName(TrnSlip));
		insertInstanceValue("SystemDate              ="
				+ getLenName(SystemDate));
		insertInstanceValue("SystemTime              ="
				+ getLenName(SystemTime));
		insertInstanceValue("ProcessMessageType      ="
				+ getLenName(ProcessMessageType));
		insertInstanceValue("Code                    =" + getLenName(Code));
		insertParamValue(0);
		insertParamValue(1);
		insertParamValue(2);
		insertParamValue(3);
		insertParamValue(4);
		insertParamValue(5);
		insertParamValue(6);
		insertParamValue(7);
		insertParamValue(8);
		insertParamValue(9);
		insertParamValue(10);
		insertParamValue(11);
		insertParamValue(12);
		insertParamValue(13);
		insertParamValue(14);
		insertParamValue(15);
		/*
		 * insertInstanceValue( "Param1 =" + getLenName(Param1 ));
		 * insertInstanceValue( "Param2 =" + getLenName(Param2 ));
		 * insertInstanceValue( "Param2 =" + getLenName(Param2 ));
		 * insertInstanceValue( "Param4 =" + getLenName(Param4 ));
		 * insertInstanceValue( "Param5 =" + getLenName(Param5 ));
		 * insertInstanceValue( "Param6 =" + getLenName(Param6 ));
		 * insertInstanceValue( "Param7 =" + getLenName(Param7 ));
		 * insertInstanceValue( "Param8 =" + getLenName(Param8 ));
		 * insertInstanceValue( "Param9 =" + getLenName(Param9 ));
		 * insertInstanceValue( "Param10 =" + getLenName(Param10 ));
		 * insertInstanceValue( "Param11 =" + getLenName(Param11 ));
		 * insertInstanceValue( "Param12 =" + getLenName(Param12 ));
		 * insertInstanceValue( "Param13 =" + getLenName(Param13 ));
		 * insertInstanceValue( "Param14 =" + getLenName(Param14 ));
		 * insertInstanceValue( "Param15 =" + getLenName(Param15 ));
		 * insertInstanceValue( "Param16 =" + getLenName(Param16 ));
		 */
		insertInstanceValue("Occurance               =" + getLenName(Occurance));
		insertInstanceValue("ProcessMessageSource    ="
				+ getLenName(ProcessMessageSource));
		insertInstanceValue("ExternalCode            ="
				+ getLenName(ExternalCode));
		insertInstanceValue("AdditionalText          ="
				+ getLenName(AdditionalText));
		insertInstanceValue("FunctionName            ="
				+ getLenName(FunctionName));
	}

	private void insertParamValue(int index) {
		if (params[index] != null)
			insertInstanceValue(getParamName(index) + "                  ="
					+ getLenName(getParamValue(index)));
	}

	public String getParamValue(int index) {
		if (params[index] != null)
			return params[index].getValue();
		return null;
	}

	public String getParamName(int index) {
		if (params[index] != null)
			return params[index].getName();
		return null;
	}

	public Field getParam(int index) {
		return params[index];
	}

	public void setParam(int index, String paramName, String paramValue) {
		Field targetParam = params[index];
		if (targetParam == null)
			targetParam = new Field();
		targetParam.setName(paramName);
		targetParam.setValue(paramValue);
		updateParamRange(index);
	}

	public void setParamValue(int index, String value) {
		if (params[index] == null)
			params[index] = new Field();
		if (value != null) {
			if (value.length() < DBValue.PRC_MSG_LOG_PARAM_WIDTH)
				params[index].setValue(value);
			else
				params[index].setValue(TextUtil.resize(value,
						DBValue.PRC_MSG_LOG_PARAM_WIDTH));
		}
		updateParamRange(index);
	}

	public void setParamName(int index, String name) {
		if (params[index] == null)
			params[index] = new Field();
		params[index].setName(name);
		updateParamRange(index);
	}

	public void setParam(int index, Field param) {
		params[index] = param;
		updateParamRange(index);
	}

	private void updateParamRange(int newIndex) {
		newIndex++;
		if (totalParamNo < newIndex)
			totalParamNo = newIndex + 1;
	}

	public void constructFullMessageDescription() {
		if (fulMsgBuf == null)
			fulMsgBuf = new StringBuffer();
		fulMsgBuf.setLength(0);
		fulMsgBuf.append(AdditionalText);
		fulMsgBuf.append("\n");
		fulMsgBuf.append("\n");
		for (int i = 0; i < totalParamNo; i++) {
			if (params[i] != null) {
				String paramName = (params[i].getName() == null) ? "Param "
						+ (i + 1) : params[i].getName();
				fulMsgBuf.append(paramName);
				fulMsgBuf.append(":[");
				String value = "(No value)";
				if (params[i].getValue() != null)
					value = params[i].getValue();
				fulMsgBuf.append(value);
				fulMsgBuf.append("]");
				fulMsgBuf.append("\n");
			}
		}
		fullMessageDescription = fulMsgBuf.toString();
	}

	public void reset() {
		No = null;
		ProcessNo = null;
		InstitutionNo = null;
		FileNo = null;
		TrnSlip = null;
		SystemDate = null;
		SystemTime = null;
		ProcessMessageType = null;
		Code = null;
		Occurance = null;
		ProcessMessageSource = null;
		ExternalCode = null;
		AdditionalText = null;
		fullMessageDescription = null;
		FunctionName = null;
		showFullMessageDescription = false;
		fulMsgBuf = null;
		for (int i = 0; i < totalParamNo; i++)
			if (params[i] != null)
				params[i].reset();
		totalParamNo = 0;

	}

	public void showFullMessageDescription(boolean showFullMessageDescription) {
		this.showFullMessageDescription = showFullMessageDescription;
	}

	public boolean isFullMessageDescription() {
		return showFullMessageDescription;
	}

	public int getTotalParamNo() {
		return totalParamNo;
	}
}
