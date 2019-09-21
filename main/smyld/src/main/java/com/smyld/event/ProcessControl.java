package com.smyld.event;

public interface ProcessControl {
	public void startProcess();

	public void stopProcess();

	public void endProcess();

	public int getStatus();

	public static final int PROCESS_RUNNING = 1;
	public static final int PROCESS_STOPPED = 2;
	public static final int PROCESS_ENDED = 3;

}
