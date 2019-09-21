package com.smyld.bw.data.structurs;

import java.util.HashMap;

import com.smyld.SMYLDObject;
import com.smyld.util.SMYLDDate;

public class BWUser extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ID;
	String name;
	String shortName;
	String bankCode;
	String branchCode;
	String deptCode;
	String language;
	boolean developer;
	boolean admin;
	boolean smyldInternal;
	int status;
	SMYLDDate passwordDate;
	SMYLDDate lastLogOn;
	String defInst;
	String accessGroup;
	HashMap<String,String> validInsts;
	String accessModule;
	String password;

	public BWUser() {
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public boolean isDeveloper() {
		return developer;
	}

	public void setDeveloper(boolean developer) {
		this.developer = developer;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isRs2Internal() {
		return smyldInternal;
	}

	public void setRs2Internal(boolean smyldInternal) {
		this.smyldInternal = smyldInternal;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public SMYLDDate getPasswordDate() {
		return passwordDate;
	}

	public void setPasswordDate(SMYLDDate passwordDate) {
		this.passwordDate = passwordDate;
	}

	public SMYLDDate getLastLogOn() {
		return lastLogOn;
	}

	public void setLastLogOn(SMYLDDate lastLogOn) {
		this.lastLogOn = lastLogOn;
	}

	public String getDefInst() {
		return defInst;
	}

	public void setDefInst(String defInst) {
		this.defInst = defInst;
	}

	public String getAccessGroup() {
		return accessGroup;
	}

	public void setAccessGroup(String accessGroup) {
		this.accessGroup = accessGroup;
	}

	public HashMap<String,String> getValidInsts() {
		return validInsts;
	}

	public void setValidInsts(HashMap<String,String> validInsts) {
		this.validInsts = validInsts;
	}

	public String getAccessModule() {
		return accessModule;
	}

	public void setAccessModule(String accessModule) {
		this.accessModule = accessModule;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
