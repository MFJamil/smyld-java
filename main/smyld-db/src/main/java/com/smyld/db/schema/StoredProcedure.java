package com.smyld.db.schema;

import java.util.HashMap;

import com.smyld.SMYLDObject;

public class StoredProcedure extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,ProcedureColumn> params;
	String name;
	String body;
	String pack;

	public StoredProcedure() {
	}

	public HashMap<String,ProcedureColumn> getParams() {
		return params;
	}

	public void setParams(HashMap<String,ProcedureColumn> params) {
		this.params = params;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

}
