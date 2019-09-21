package com.smyld.err;

import com.smyld.SMYLDException;

public class CardConflictException extends SMYLDException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String institutionNo, clientRegion;

	public CardConflictException(String InstitutionNo, String ClientRegion) {
		institutionNo = InstitutionNo;
		clientRegion = ClientRegion;
	}

}
