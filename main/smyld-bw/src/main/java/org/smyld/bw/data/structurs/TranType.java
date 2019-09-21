package org.smyld.bw.data.structurs;

import org.smyld.SMYLDObject;

public class TranType extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String index;
	private String sourceSign;
	private String destinationSign;

	public TranType() {
	}

	public TranType(String Index, String SourceSign, String DestinationSign) {
		index = Index;
		sourceSign = SourceSign;
		destinationSign = DestinationSign;
	}

	public String getIndex() {
		return index;
	}

	public String getSourceSign() {
		return sourceSign;
	}

	public String getDestinationSign() {
		return destinationSign;
	}

}
