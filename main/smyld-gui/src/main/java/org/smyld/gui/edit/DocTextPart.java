package org.smyld.gui.edit;

import javax.swing.text.Position;

public class DocTextPart {
	
	public String      fieldID;
	public String      fieldValue;
	public Position    fieldPos;
	public Type        type;
	public DocTextPart(){
		
	}
	public DocTextPart(String value){
		fieldID    = value;
		fieldValue = value;
	}
	public DocTextPart(String value,Type partType){
		this(value);
		setType(partType);
	}

	/**
	 * @return the fieldID
	 */
	public String getFieldID() {
		return fieldID;
	}

	/**
	 * @param fieldID the fieldID to set
	 */
	public void setFieldID(String fieldID) {
		this.fieldID = fieldID;
	}

	/**
	 * @return the fieldValue
	 */
	public String getFieldValue() {
		return fieldValue;
	}

	/**
	 * @param fieldValue the fieldValue to set
	 */
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	/**
	 * @return the fieldPos
	 */
	public Position getFieldPos() {
		return fieldPos;
	}

	/**
	 * @param fieldPos the fieldPos to set
	 */
	public void setFieldPos(Position fieldPos) {
		this.fieldPos = fieldPos;
	}
	
	
	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}


	public enum Type{Editor,Custom,User;}
}
