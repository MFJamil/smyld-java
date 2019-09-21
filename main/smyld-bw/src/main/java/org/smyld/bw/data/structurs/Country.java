package org.smyld.bw.data.structurs;

import org.smyld.SMYLDObject;

public class Country extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String isoValue;
	String countryCodeOf2;
	String countryCodeOf3;
	String telCode;
	String name;
	String nationality;

	public Country() {
	}

	public String getIsoValue() {
		return isoValue;
	}

	public void setIsoValue(String isoValue) {
		this.isoValue = isoValue;
	}

	public String getCountryCodeOf2() {
		return countryCodeOf2;
	}

	public void setCountryCodeOf2(String countryCodeOf2) {
		this.countryCodeOf2 = countryCodeOf2;
	}

	public String getCountryCodeOf3() {
		return countryCodeOf3;
	}

	public void setCountryCodeOf3(String countryCodeOf3) {
		this.countryCodeOf3 = countryCodeOf3;
	}

	public String getTelCode() {
		return telCode;
	}

	public void setTelCode(String telCode) {
		this.telCode = telCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
}
