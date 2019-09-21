package com.smyld.gui.event;

public interface SMYLDChangeTarget {

	public String getSqlText();

	public void newValue(String NewValue);

	public String getID();
}
