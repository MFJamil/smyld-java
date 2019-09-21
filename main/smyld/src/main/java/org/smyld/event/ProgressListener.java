package org.smyld.event;

public interface ProgressListener {
	public void maximumMessageNumber(int maxMsgNo);

	public boolean isPercentage();

	public int getProgressValue();

	public void newProgress(int currentValue);

}
