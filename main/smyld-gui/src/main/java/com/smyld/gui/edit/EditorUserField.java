package com.smyld.gui.edit;


public class EditorUserField extends DocTextPart {
	int fieldIndex;
	
	public EditorUserField(String value) {
		super(value);
		setType(Type.User);
		
	}
	public EditorUserField(String value,DocTextPart.Type partType){
		super(value,partType);
	}

	/**
	 * @return the fieldIndex
	 */
	public int getFieldIndex() {
		return fieldIndex;
	}

	/**
	 * @param fieldIndex the fieldIndex to set
	 */
	public void setFieldIndex(int fieldIndex) {
		this.fieldIndex = fieldIndex;
	}
	

}
