package org.smyld.net.url;

import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

public class HTTPURLConnector extends URLConnector {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpURLConnection httpConnection;
	HashMap<String,String> requestParams;
	StringBuffer buffer = new StringBuffer();
	String targetAddress;

	public HTTPURLConnector(URL targetURL) {
		super(targetURL);
		targetAddress = targetURL.toExternalForm();
		requestParams = new HashMap<String,String>();
	}

	public boolean sendHttpRequest() {
		try {
			buffer.setLength(0);
			buffer.append(targetAddress);
			if (requestParams.size() > 0)
				addRequestParams(buffer);
			// System.out.println("Resultant url text : " + buffer.toString() );
			serverURL = new URL(buffer.toString());
			// System.out.println("Resultant url text : " +
			// serverURL.toExternalForm() );
			httpConnection = (HttpURLConnection) serverURL.openConnection();
			return httpConnection.getResponseCode() == 200;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	private void addRequestParams(StringBuffer resultBuffer) {
		resultBuffer.append("?");
		Iterator<String> itr = requestParams.keySet().iterator();
		while (itr.hasNext()) {
			String currentKey = itr.next();
			resultBuffer.append(currentKey);
			resultBuffer.append("=");
			resultBuffer.append(requestParams.get(currentKey));
			if (itr.hasNext())
				resultBuffer.append("&");
		}

	}

	public void setRequestMethod(String methodName) throws ProtocolException {
		httpConnection.setRequestMethod(methodName);
	}

	public void setRequestParameter(String paramName, String paramValue) {
		requestParams.put(paramName, paramValue);
	}

}
