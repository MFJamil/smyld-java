package org.smyld.db.schema;

public class Sequence extends SchemaObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String minValue;
	String maxValue;
	String incrementValue;
	int cashSize;
	boolean order;
	boolean cycle;

	public Sequence() {
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getIncrementValue() {
		return incrementValue;
	}

	public void setIncrementValue(String incrementValue) {
		this.incrementValue = incrementValue;
	}

	public int getCashSize() {
		return cashSize;
	}

	public void setCashSize(int cashSize) {
		this.cashSize = cashSize;
	}

	public boolean isOrder() {
		return order;
	}

	public void setOrder(boolean order) {
		this.order = order;
	}

	public boolean isCycle() {
		return cycle;
	}

	public void setCycle(boolean cycle) {
		this.cycle = cycle;
	}
}
