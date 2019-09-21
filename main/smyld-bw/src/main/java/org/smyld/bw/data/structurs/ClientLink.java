package org.smyld.bw.data.structurs;

import org.smyld.SMYLDObject;

public class ClientLink extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String bankReference;
	String clientTariff;
	String contractReference;
	String clientBranch;
	String clientNumber;
	String contractCategory;
	String costCenter;
	String chargeTierLevel;
	String clientLevel;
	String effectiveDate;
	String expiryDate;
	String institutionNumber;
	String institutionAccountOfficer;
	String settlementMethod;
	String postingMethod;
	String providerAccountOfficer;
	String groupNumber;
	String recordDate;
	String contractStatus;
	String parentClientNumber;

	ServiceContract serviceContract;

	public ClientLink() {
	}

	public String getInstitutionNumber() {
		return institutionNumber;
	}

	public void setInstitutionNumber(String institutionNumber) {
		this.institutionNumber = institutionNumber;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public String getParentClientNumber() {
		return parentClientNumber;
	}

	public void setParentClientNumber(String parentClientNumber) {
		this.parentClientNumber = parentClientNumber;
	}

	public String getClientLevel() {
		return clientLevel;
	}

	public void setClientLevel(String clientLevel) {
		this.clientLevel = clientLevel;
	}

	public ServiceContract getServiceContract() {
		return serviceContract;
	}

	public void setServiceContract(ServiceContract serviceContract) {
		this.serviceContract = serviceContract;
	}

	public String getClientTariff() {
		return clientTariff;
	}

	public void setClientTariff(String clientTariff) {
		this.clientTariff = clientTariff;
	}

	public String getBankReference() {
		return bankReference;
	}

	public void setBankReference(String bankReference) {
		this.bankReference = bankReference;
	}

	public String getContractReference() {
		return contractReference;
	}

	public void setContractReference(String contractReference) {
		this.contractReference = contractReference;
	}

	public String getSettlementMethod() {
		return settlementMethod;
	}

	public void setSettlementMethod(String settlementMethod) {
		this.settlementMethod = settlementMethod;
	}

	public String getPostingMethod() {
		return postingMethod;
	}

	public void setPostingMethod(String postingMethod) {
		this.postingMethod = postingMethod;
	}

	public String getClientBranch() {
		return clientBranch;
	}

	public void setClientBranch(String clientBranch) {
		this.clientBranch = clientBranch;
	}

	public String getInstitutionAccountOfficer() {
		return institutionAccountOfficer;
	}

	public void setInstitutionAccountOfficer(String institutionAccountOfficer) {
		this.institutionAccountOfficer = institutionAccountOfficer;
	}

	public String getProviderAccountOfficer() {
		return providerAccountOfficer;
	}

	public void setProviderAccountOfficer(String providerAccountOfficer) {
		this.providerAccountOfficer = providerAccountOfficer;
	}

	public String getContractCategory() {
		return contractCategory;
	}

	public void setContractCategory(String contractCategory) {
		this.contractCategory = contractCategory;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getChargeTierLevel() {
		return chargeTierLevel;
	}

	public void setChargeTierLevel(String chargeTierLevel) {
		this.chargeTierLevel = chargeTierLevel;
	}

}
