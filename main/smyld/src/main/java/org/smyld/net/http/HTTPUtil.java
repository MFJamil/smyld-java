package org.smyld.net.http;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.smyld.SMYLDObject;

public class HTTPUtil extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HTTPUtil() {
	}

	@SuppressWarnings("unchecked")
	public static String printRequest(HttpServletRequest httpRequest) {
		StringBuffer buffer = new StringBuffer();
		String currentItem = null;
		String itemValue = null;
		buffer.append("Headers : ");
		// TODO need to see the servlet version and whether a new one exists returning a generics enumeration
		Enumeration enums = httpRequest.getHeaderNames();
		while (enums.hasMoreElements()) {
			currentItem = (String) enums.nextElement();
			itemValue = httpRequest.getHeader(currentItem);
			buffer.append("Header ");
			buffer.append(currentItem);
			buffer.append(": ");
			buffer.append(itemValue);
		}
		buffer.append("Parameters : ");
		enums = httpRequest.getParameterNames();
		while (enums.hasMoreElements()) {
			currentItem = (String) enums.nextElement();
			itemValue = httpRequest.getParameter(currentItem);
			buffer.append("Parameter ");
			buffer.append(currentItem);
			buffer.append(": ");
			buffer.append(itemValue);
		}
		return buffer.toString();
	}

}
