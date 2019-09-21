package com.smyld.security;

import com.smyld.SMYLDObject;

public class SMYLDKey extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String password;
	String ownerCompleteName;
	String organizationUnitName;
	String organizationName;
	String locality;
	String state;
	String countryCode;
	String keyStore;

	public SMYLDKey() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOwnerCompleteName() {
		return ownerCompleteName;
	}

	public void setOwnerCompleteName(String ownerCompleteName) {
		this.ownerCompleteName = ownerCompleteName;
	}

	public String getOrganizationUnitName() {
		return organizationUnitName;
	}

	public void setOrganizationUnitName(String organizationUnitName) {
		this.organizationUnitName = organizationUnitName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getKeyStore() {
		return keyStore;
	}

	public void setKeyStore(String keyStore) {
		this.keyStore = keyStore;
	}

	public String getDistinguishName() {
		StringBuffer buffer = new StringBuffer();
		if (getOwnerCompleteName() != null)
			buffer.append(KEY_ATT_COMPLETE_NAME + "=" + getOwnerCompleteName()
					+ ", ");
		if (getOrganizationUnitName() != null)
			buffer.append(KEY_ATT_ORG_UNIT_NAME + "="
					+ getOrganizationUnitName() + ", ");
		if (getOrganizationName() != null)
			buffer
					.append(KEY_ATT_ORG_NAME + "=" + getOrganizationName()
							+ ", ");
		if (getLocality() != null)
			buffer.append(KEY_ATT_LOCALITY_CITY + "=" + getLocality() + ", ");
		if (getState() != null)
			buffer.append(KEY_ATT_STATE + "=" + getState() + ", ");
		if (getCountryCode() != null)
			buffer.append(KEY_ATT_COUNTRY + "=" + getCountryCode());

		return buffer.toString();
	}

	public static final String KEY_ATT_COMPLETE_NAME = "CN";
	public static final String KEY_ATT_ORG_UNIT_NAME = "OU";
	public static final String KEY_ATT_ORG_NAME = "O";
	public static final String KEY_ATT_LOCALITY_CITY = "L";
	public static final String KEY_ATT_STATE = "S";
	public static final String KEY_ATT_COUNTRY = "C";

}
