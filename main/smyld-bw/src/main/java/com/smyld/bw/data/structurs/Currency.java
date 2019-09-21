package com.smyld.bw.data.structurs;

import com.smyld.SMYLDObject;

public class Currency extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ISOCode;
	String SwiftCode;
	String Name;
	int Exponent;
	String Symbol;
	int CalcBase;
	String SortField;
	String LocalCode;

	public Currency() {
	}

	public String getISOCode() {
		return ISOCode;
	}

	public void setISOCode(String ISOCode) {
		this.ISOCode = ISOCode;
	}

	public String getSwiftCode() {
		return SwiftCode;
	}

	public void setSwiftCode(String SwiftCode) {
		this.SwiftCode = SwiftCode;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public int getExponent() {
		return Exponent;
	}

	public void setExponent(int Exponent) {
		this.Exponent = Exponent;
	}

	public String getSymbol() {
		return Symbol;
	}

	public void setSymbol(String Symbol) {
		this.Symbol = Symbol;
	}

	public int getCalcBase() {
		return CalcBase;
	}

	public void setCalcBase(int CalcBase) {
		this.CalcBase = CalcBase;
	}

	public String getSortField() {
		return SortField;
	}

	public void setSortField(String SortField) {
		this.SortField = SortField;
	}

	public String getLocalCode() {
		return LocalCode;
	}

	public void setLocalCode(String LocalCode) {
		this.LocalCode = LocalCode;
	}
}
