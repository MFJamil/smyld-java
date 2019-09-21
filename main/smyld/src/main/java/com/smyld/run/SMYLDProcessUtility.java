package com.smyld.run;

import java.io.IOException;

import com.smyld.io.SMYLDIOUtil;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class SMYLDProcessUtility {
	/**
	 * 
	 * @see
	 * @since
	 */
	public SMYLDProcessUtility() {

	}

	public static String getErrorCode(Process runningProcess) {
		return SMYLDIOUtil.readStream(runningProcess.getErrorStream());
	}

	public static String getResponse(Process runningProcess) {
		return SMYLDIOUtil.readStream(runningProcess.getInputStream());
	}

	public static void writeToProcess(Process runningProcess, String command)
			throws IOException {
		runningProcess.getOutputStream().write(command.getBytes());
	}

}
