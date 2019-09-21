package org.smyld.util.vmapper;

public class VMapIntSingleKey extends VMapKey {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int value;

	public VMapIntSingleKey() {
	}

	public VMapIntSingleKey(int keyValue) {
		setValue(keyValue);
	}

	public void setValue(int newValue) {
		value = newValue;
	}

	public int getValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof VMapIntRangeKey) {
			VMapIntRangeKey otherObj = (VMapIntRangeKey) obj;
			return ((value >= otherObj.getMinValue()) && (value <= otherObj
					.getMaxValue()));
		}
		if (obj instanceof VMapIntSingleKey)
			return (value == ((VMapIntSingleKey) obj).getValue());
		return false;
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof VMapIntRangeKey) {
			VMapIntRangeKey otherObj = (VMapIntRangeKey) obj;
			if (value < otherObj.getMinValue())
				return value - otherObj.getMinValue();
			if (value > otherObj.getMaxValue())
				return value - otherObj.getMaxValue();
		} else if (obj instanceof VMapIntSingleKey) {
			return value - ((VMapIntSingleKey) obj).getValue();
		}
		return 0;
	}

}
