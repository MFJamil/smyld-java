package org.smyld.gui.event;

import java.util.HashMap;

public class SMYLDChangesMonitor extends Thread {
	HashMap<String, Object> targets = new HashMap<String, Object>();

	public SMYLDChangesMonitor() {

	}

	public void addNewChangeTarget(SMYLDChangeTarget newTarget) {
		// targets.put(newTarget.);

	}

}
