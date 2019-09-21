package org.smyld.bw.sys;

import org.smyld.bw.data.structurs.SystemJob;

public interface ProcessEndListener {
	public void processEnd(SystemJob endedJob, int transNumber);

}
