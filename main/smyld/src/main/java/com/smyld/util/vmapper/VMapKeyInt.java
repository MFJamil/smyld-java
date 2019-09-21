package com.smyld.util.vmapper;

import com.smyld.SMYLDObject;
import com.smyld.text.TextUtil;

public class VMapKeyInt extends SMYLDObject implements Comparable<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int[][] valueRanges;
	String origKeyValue;
	String values;
	String singleValue;
	int singleIntValue;
	int diff = -1;
	boolean hasRanges;
	boolean hasSingleValue;

	public VMapKeyInt(String values) {
		origKeyValue = values;
		this.values = values;
		parseTextValues();
	}

	public VMapKeyInt() {
	}

	public void setValue(String value) {
		hasRanges = false;
		hasSingleValue = false;
		this.values = value;
		origKeyValue = value;
		parseTextValues();
	}

	public void setSingleValue(int value) {
		hasRanges = false;
		hasSingleValue = true;
		this.singleIntValue = value;
	}

	public int getSingleValue() {
		return singleIntValue;
	}

	public boolean isSingleValue() {
		return hasSingleValue;
	}

	private void parseTextValues() {
		StringBuffer buffer = new StringBuffer();
		String[] valueItems = values.split(",");
		int rangeNumber = TextUtil.occuranceOf(values, "-");
		if (rangeNumber > 0) {
			hasRanges = true;
			valueRanges = new int[rangeNumber][2];
		}
		if ((valueItems.length == 1) && (!hasRanges)) {
			hasSingleValue = true;
			singleValue = values;
			singleIntValue = Integer.parseInt(singleValue);

		} else {
			int rangeIndex = 0;
			for (int i = 0; i < valueItems.length; i++) {
				int sep = valueItems[i].indexOf("-");
				if (sep != -1) {
					String minValue = valueItems[i].substring(0, sep);
					String maxValue = valueItems[i].substring(sep + 1);
					addSingleValue(buffer, minValue);
					addSingleValue(buffer, maxValue);
					int minIntValue = Integer.parseInt(minValue.trim());
					int maxIntValue = Integer.parseInt(maxValue);
					if (minIntValue > maxIntValue) {
						valueRanges[rangeIndex][0] = maxIntValue;
						valueRanges[rangeIndex][1] = minIntValue;
					} else {
						valueRanges[rangeIndex][0] = minIntValue;
						valueRanges[rangeIndex][1] = maxIntValue;
					}
					rangeIndex++;
				} else {
					addSingleValue(buffer, valueItems[i]);
				}
			}
			if (buffer.length() > 0)
				values = buffer.toString().substring(0,
						buffer.toString().length() - 1);
		}
	}

	private void addSingleValue(StringBuffer buffer, String singleValue) {
		buffer.append("'");
		buffer.append(singleValue);
		buffer.append("'");
		buffer.append(",");
	}

	public boolean equals(int intValue) {
		diff = -1;
		if (hasSingleValue) {
			diff = singleIntValue - intValue;
			return (intValue == singleIntValue);
		}
		if (values.indexOf("'" + intValue + "'") != -1)
			return true;
		if (hasRanges) {
			for (int i = 0; i < valueRanges.length; i++) {
				if ((intValue >= valueRanges[i][0])
						&& (intValue <= valueRanges[i][1]))
					return true;
			}
		}
		return false;

	}

	@Override
	public boolean equals(Object obj) {
		VMapKeyInt otherMap = ((VMapKeyInt) obj);
		if ((this.origKeyValue != null) && (otherMap.origKeyValue != null))
			return otherMap.origKeyValue.equals(this.origKeyValue);
		int value = ((VMapKeyInt) obj).getSingleValue();
		return equals(value);
	}

	public boolean equalsOriginalKeyText(String compareKey) {
		return origKeyValue.equals(compareKey);
	}

	public int compareTo(Object o) {
		if ((o instanceof String) && (equalsOriginalKeyText((String) o)))
			return 0;
		if (o.equals(this))
			return 0;
		return diff;
	}

}
