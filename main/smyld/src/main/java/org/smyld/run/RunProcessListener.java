package org.smyld.run;

public interface RunProcessListener {
	public void onResponse(String incomingResponse);

	public void onError(String incomingError);
}
