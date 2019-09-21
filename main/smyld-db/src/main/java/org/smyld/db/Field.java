package org.smyld.db;

import org.smyld.SMYLDObject;

public class Field extends SMYLDObject implements Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String Value;
	protected String Name;

	/**
	 * Empty constructor to initialize the object
	 */
	public Field() {
	}

	/**
	 * Taking the required parameters to initialize the object
	 * 
	 * @param FieldName
	 *            the name of the field
	 * @param FieldValue
	 *            the value of the field
	 */
	public Field(String FieldName, String FieldValue) {
		setName(FieldName);
		setValue(FieldValue);
	}

	/**
	 * Getting the value of the field
	 */
	public String getValue() {
		return Value;
	}

	/**
	 * Setting the value of the field
	 * 
	 * @param FieldValue
	 *            the new field value
	 */

	public synchronized void setValue(String FieldValue) {
		Value = FieldValue;
	}

	/**
	 * Getting the name of the field
	 */

	public String getName() {
		return Name;
	}

	/**
	 * Setting the Name of the field
	 * 
	 * @param FieldName
	 *            the new field name
	 */

	public void setName(String FieldName) {
		Name = FieldName;
	}

	/**
	 * Overrides the hash code of the parent object class to indentify the
	 * object according to the field name
	 * 
	 * @retrun the hash code of the field name or the parent object class hash
	 *         code if field name is null
	 */
	@Override
	public int hashCode() {
		if (Name != null)
			return Name.hashCode();
		return super.hashCode();
	}

	/**
	 * Overrides the equals of the parent object class to compare the object
	 * according to the field name
	 * 
	 * @retrun the comparison result and false if the field name is null
	 */
	public boolean equals(Field comparedField) {
		if (Name != null) {
			if (this.hashCode() == comparedField.hashCode())
				return true;
		}
		return false;
	}

	/**
	 * Print the current instance values in order to use it with toString()
	 * method
	 * 
	 * @return The instance vlaues
	 */
	@Override
	public void printInstanceValues() {

		insertInstanceValue("Field Name  : " + Name);
		insertInstanceValue("Field Value : " + Value);
	}

	@Override
	public Object clone() {
		Field newCopy = new Field();
		newCopy.setName(this.Name);
		return newCopy;
	}

	public void reset() {
		setName(null);
		setValue(null);
	}

}
