package org.smyld.util.vmapper;

public class VMapIntRangeKey extends VMapIntSingleKey {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int minValue;
	int maxValue;

	public VMapIntRangeKey() {
	}

	public VMapIntRangeKey(int minValue, int maxValue) {
		setMinValue(minValue);
		setMaxValue(maxValue);
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof VMapIntRangeKey) {
			VMapIntRangeKey otherObj = (VMapIntRangeKey) obj;
			return ((getMinValue() == otherObj.getMinValue()) && (getMaxValue() == otherObj
					.getMaxValue()));
		} else if (obj instanceof VMapIntSingleKey) {
			VMapIntSingleKey otherObj = (VMapIntSingleKey) obj;
			return ((otherObj.getValue() >= getMinValue()) && (otherObj
					.getValue() <= getMaxValue()));
		}
		return false;
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof VMapIntRangeKey) {
			VMapIntRangeKey otherObj = (VMapIntRangeKey) obj;
			if (getMaxValue() != otherObj.getMaxValue())
				return getMaxValue() - otherObj.getMaxValue();
			if (getMinValue() != otherObj.getMinValue())
				return getMinValue() - otherObj.getMinValue();
		} else if (obj instanceof VMapIntSingleKey) {
			VMapIntSingleKey otherObj = (VMapIntSingleKey) obj;
			if (otherObj.getValue() < getMinValue())
				return getMinValue() - otherObj.getValue();
			if (otherObj.getValue() > getMaxValue())
				return getMaxValue() - otherObj.getValue();
		}
		return 0;
	}

}
