package org.smyld.bw.data.structurs;

import org.smyld.SMYLDObject;

public class ServiceContract extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String institutionNumber;
	String serviceContractID;
	String contractType;
	String reviewPeriod;
	String recordDate;
	String noteText;

	public ServiceContract() {
	}

	public String getInstitutionNumber() {
		return institutionNumber;
	}

	public void setInstitutionNumber(String institutionNumber) {
		this.institutionNumber = institutionNumber;
	}

	public String getServiceContractID() {
		return serviceContractID;
	}

	public void setServiceContractID(String serviceContractID) {
		this.serviceContractID = serviceContractID;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getReviewPeriod() {
		return reviewPeriod;
	}

	public void setReviewPeriod(String reviewPeriod) {
		this.reviewPeriod = reviewPeriod;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getNoteText() {
		return noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
}
