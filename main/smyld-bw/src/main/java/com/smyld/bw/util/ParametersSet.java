package com.smyld.bw.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.smyld.text.EnvelopTockenizer;
import com.smyld.text.TextTokenizer;

/**
 * This class will perform all the operations needed for processing the
 * parameters which are arranged according to SMYLD conventions
 */
public class ParametersSet implements Serializable {
	ArrayList<HashMap<String, String>> parameterSets = new ArrayList<HashMap<String, String>>();
	int paramSetsRealCount;
	static final long serialVersionUID = 4223910564156435384L;

	public ParametersSet(String parameters) {
		if (parameters != null) {
			EnvelopTockenizer paramsTockenizer = new EnvelopTockenizer(
					parameters, PARAM_ENV_START, PARAM_ENV_END);
			parseParameters(paramsTockenizer.parseEnvelops());
		}
	}

	public ParametersSet() {
		parameterSets.add(new HashMap<String,String>());
	}

	// Parsing parameter sets
	private void parseParameters(String[] tockenizedParameterSets) {
		HashMap<String,String> currentParameterSet;
		for (int i = 0; i < tockenizedParameterSets.length; i++)
			if ((currentParameterSet = parseParametersSet(tockenizedParameterSets[i])) != null)
				parameterSets.add(i, currentParameterSet);
	}

	private HashMap<String,String> parseParametersSet(String parametersSet) {
		HashMap<String,String> resultantParameterSet = null;
		TextTokenizer currentParamterSet = new TextTokenizer(parametersSet,
				PARAM_SEP);
		String[] parameters = currentParamterSet.parseTokens();
		for (int i = 0; i < parameters.length; i++) {
			if (resultantParameterSet == null)
				resultantParameterSet = new HashMap<String,String>(parameters.length);
			String paramName = parameters[i].substring(0, parameters[i]
					.indexOf(PARAM_NAME_SEP));
			String paramValue = parameters[i].substring(parameters[i]
					.indexOf(PARAM_VALUE_START)
					+ PARAM_VALUE_START.length(), parameters[i]
					.lastIndexOf(PARAM_VALUE_END));
			resultantParameterSet.put(paramName.toLowerCase(), paramValue);
		}
		return resultantParameterSet;
	}

	public int addParametersSet() {
		HashMap<String,String> newSet = new HashMap<String,String>();
		parameterSets.add(newSet);
		return parameterSets.size();
	}

	// adding new Parameters
	public void addParameter(int paramSet, String newParmName,
			String newParamValue) {

		HashMap<String,String> targetSet;
		if ((targetSet = getParameterSet(paramSet)) != null) {
			if (paramSet > paramSetsRealCount)
				paramSetsRealCount = paramSet;
			targetSet.put(newParmName.toLowerCase(), newParamValue);
		}
	}

	public void addParameter(String newParmName, String newParamValue) {
		HashMap<String,String> targetSet;
		if ((targetSet = getParameterSet(0)) != null)
			targetSet.put(newParmName.toLowerCase(), newParamValue);
	}

	// Fetching parameter values
	public HashMap<String,String> getParameterSet(int paramSet) {
		if (parameterSets.size() >= paramSet) {
			HashMap<String,String> targetSet = parameterSets.get(paramSet - 1);
			return targetSet;
		}
		return null;
	}

	public String getParameterValue(int parameterSet, String parameterName) {
		HashMap<String,String> targetSet;
		if ((targetSet = getParameterSet(parameterSet)) != null) {
			Object targetObject = targetSet.get(parameterName.toLowerCase());
			if (targetObject != null)
				return targetObject.toString();
		}
		return null;
	}

	public String getParameterValue(String parameterName) {
		return getParameterValue(1, parameterName);
	}

	// Printing parameter sets values
	public String printParameters() {
		StringBuffer value = new StringBuffer();
		for (HashMap<String, String> currentSet : parameterSets) {
			if ((currentSet != null) && (currentSet.size() > 0)) {
				value.append(PARAM_ENV_START + printParameterSet(currentSet)
						+ PARAM_ENV_END);
			}
		}
		return value.toString();
	}

	private String printParameterSet(HashMap<String, String> currentParameterSet) {
		StringBuffer value = new StringBuffer();
		if (currentParameterSet.size() > 0) {
			Iterator<String> items = currentParameterSet.keySet().iterator();
			while (items.hasNext()) {
				String parameterName = items.next();
				String parameterValue = currentParameterSet.get(parameterName)
						.toString();
				value.append(parameterName + PARAM_NAME_SEP + PARAM_VALUE_START
						+ parameterValue + PARAM_VALUE_END);
				if (items.hasNext())
					value.append(PARAM_SEP);
			}
			return value.toString();
		}
		return "ParameterSetEmpty";
	}

	public int getSetsCount() {
		return paramSetsRealCount;
	}

	public static final String PARAM_ENV_START = "{";
	public static final String PARAM_ENV_END = "}";
	public static final String PARAM_SEP = "|";
	public static final String PARAM_NAME_SEP = "=>";
	public static final String PARAM_VALUE_START = "'";
	public static final String PARAM_VALUE_END = "'";
}
