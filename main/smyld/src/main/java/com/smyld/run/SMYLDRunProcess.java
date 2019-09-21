package com.smyld.run;

import java.io.IOException;
import java.io.OutputStreamWriter;

import com.smyld.SMYLDObject;
import com.smyld.io.SMYLDInputStream;
import com.smyld.io.StreamAdapter;

public class SMYLDRunProcess extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Process curProcess;
	SMYLDInputStream errStr;
	SMYLDInputStream resStr;
	OutputStreamWriter outStr;
	RunProcessListener activeListener;

	public SMYLDRunProcess() {
	}

	public SMYLDRunProcess(Process runProcess) {
		handleProcess(runProcess);
	}

	public SMYLDRunProcess(Process runProcess, RunProcessListener newListener) {
		activeListener = newListener;
		handleProcess(runProcess);
	}

	public void handleProcess(Process runProcess) {
		curProcess = runProcess;
		errStr = new SMYLDInputStream(curProcess.getErrorStream(), 1024, 500);
		resStr = new SMYLDInputStream(curProcess.getInputStream(), 1024, 500);
		outStr = new OutputStreamWriter(curProcess.getOutputStream());
		errStr.addStreamListener(new StreamAdapter() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void textRecieved(String newText) {
				if (activeListener != null)
					activeListener.onError(newText);
			}
		});
		resStr.addStreamListener(new StreamAdapter() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void textRecieved(String newText) {
				if (activeListener != null)
					activeListener.onResponse(newText);
			}
		});
		new Thread(errStr).start();
		new Thread(resStr).start();

	}

	public void addProcessListener(RunProcessListener newListener) {
		activeListener = newListener;
	}

	public void close() {
		try {
			errStr.close();
			resStr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(String text) throws IOException {
		if (outStr != null) {
			outStr.write(text);
			outStr.flush();
		}
	}

}
