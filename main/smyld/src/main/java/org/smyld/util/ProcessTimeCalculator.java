package org.smyld.util;

import org.smyld.SMYLDObject;

public class ProcessTimeCalculator extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long minTime = -1, maxTime = -1, totalTime, curTime;
	String processName;

	public ProcessTimeCalculator(String processName) {
		this.processName = processName;
	}

	public void processStart() {
		curTime = System.currentTimeMillis();
	}

	public void processEnd() {
		calculateEndOfTranProcessTime(System.currentTimeMillis() - curTime);
	}

	private void calculateEndOfTranProcessTime(long curPeriod) {
		if (minTime == -1)
			minTime = curPeriod;
		if (maxTime == -1)
			maxTime = curPeriod;
		if (curPeriod > maxTime)
			maxTime = curPeriod;
		if (curPeriod < minTime)
			minTime = curPeriod;
		totalTime += curPeriod;
		curTime = 0;
	}

	public void printTimeResults() {
		System.out.println("Process " + processName + " time results :");
		System.out.println("Total   time " + totalTime);
		System.out.println("Maximum time " + maxTime);
		System.out.println("Minimum time " + minTime);
	}

	public void reset() {
		maxTime = 0;
		minTime = 0;
		totalTime = 0;
		curTime = 0;
	}

	public long getTotalProcessingTime() {
		return totalTime;
	}

	public long getMaximumProcessingTime() {
		return maxTime;
	}

	public long getMinimumProcessingTime() {
		return minTime;
	}

}
