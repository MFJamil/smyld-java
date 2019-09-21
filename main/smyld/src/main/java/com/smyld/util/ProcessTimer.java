package com.smyld.util;

import java.util.Date;
import java.util.HashMap;

import com.smyld.SMYLDObject;
import com.smyld.text.TextUtil;

public class ProcessTimer extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,String> pendingTimeTests = new HashMap<String,String>();

	public ProcessTimer() {
	}

	public synchronized void startTimeTest(String TestTitle) {
		pendingTimeTests.put(TestTitle, Long.toString(System
				.currentTimeMillis()));
	}

	public synchronized String endTimeTest(String TestTitle) {
		if (pendingTimeTests.containsKey(TestTitle)) {
			String timeResult = Long.toString(System.currentTimeMillis()
					- Long
							.parseLong(pendingTimeTests.get(TestTitle)
									.toString()));
			String timeText = TestTitle + " time ";
			return TextUtil.fillRightSide(timeText, TEST_TITLE_WIDTH, ' ')
					+ ":" + timeResult;
		}
		return null;
	}

	public synchronized String endTimeTest(String TestTitle, int messageNo) {
		long endTime = new Date().getTime();
		if (pendingTimeTests.containsKey(TestTitle)) {
			float timeDiff = endTime
					- Long
							.parseLong(pendingTimeTests.get(TestTitle)
									.toString());
			String timeText = TestTitle + " time ";
			String messageTime = timeDiff / messageNo + " ms ";
			return TextUtil.fillRightSide(timeText, TEST_TITLE_WIDTH, ' ')
					+ ":" + timeDiff + " for " + messageNo + " (i.e. "
					+ messageTime + " per Message)";
		}
		return null;
	}

	public static final int TEST_TITLE_WIDTH = 50;

}
