package com.smyld.bw.sys;

import com.smyld.bw.data.structurs.SystemJob;

public interface ProcessEndListener {
	public void processEnd(SystemJob endedJob, int transNumber);

}
