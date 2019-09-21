package com.smyld.bw.util;

import java.util.Date;
import java.util.HashMap;

import com.smyld.SMYLDObject;
import com.smyld.text.TextUtil;

public class ProcessTimer extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,String> pendingTimeTests;

	public ProcessTimer() {
	}

	public void startTimeTest(String TestTitle) {
		if (pendingTimeTests == null)
			pendingTimeTests = new HashMap<String,String>();
		pendingTimeTests.put(TestTitle, Long.toString(System
				.currentTimeMillis()));
	}

	public String endTimeTest(String TestTitle) {
		if (pendingTimeTests.get(TestTitle) != null) {
			String timeResult = Long.toString(System.currentTimeMillis()
					- Long
							.parseLong(pendingTimeTests.get(TestTitle)
									.toString()));
			String timeText = TestTitle + " time ";
			return TextUtil.fillRightSide(timeText, 25, ' ') + ":" + timeResult;
		}
		return null;
	}

	public String endTimeTest(String TestTitle, int messageNo) {
		long endTime = new Date().getTime();
		if (pendingTimeTests.get(TestTitle) != null) {
			float timeDiff = endTime
					- Long
							.parseLong(pendingTimeTests.get(TestTitle)
									.toString());
			String timeText = TestTitle + " time ";
			String messageTime = timeDiff / messageNo + " ms ";
			return TextUtil.fillRightSide(timeText, 25, ' ') + ":" + timeDiff
					+ " for " + messageNo + " (i.e. " + messageTime
					+ " per Message)";
		}
		return null;
	}

}
