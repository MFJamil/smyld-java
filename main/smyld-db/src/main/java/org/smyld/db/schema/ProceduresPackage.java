package org.smyld.db.schema;

import java.util.Vector;
import org.smyld.SMYLDObject;

public class ProceduresPackage extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	Vector<StoredProcedure> procedures;

	public ProceduresPackage() {

	}

	public void addProcedure(StoredProcedure newProcedure) {
		if (procedures == null)
			procedures = new Vector<StoredProcedure>();
		procedures.add(newProcedure);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector<StoredProcedure> getProcedures() {
		return procedures;
	}

	public void setProcedures(Vector<StoredProcedure> procedures) {
		this.procedures = procedures;
	}
}
