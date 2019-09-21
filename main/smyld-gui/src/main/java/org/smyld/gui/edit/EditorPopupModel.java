package org.smyld.gui.edit;

public interface EditorPopupModel {
	public EditorPopupItem getItemAt(int index);
	public int getItemsCount();
	public EditorPopupItemCategory getCategoryAt(int index);
	public int getCategoriesCount();
	public EditorPopupItemCategory getCategory(String categID);
	public EditorPopupItem getItemForCategory(String categID,String itemName);
	public String processFormula(Formula formula);
	public Formula getFormulaAt(int index);
	public int getFormulasCount();
	public String getFormulaParameterValue(String paramName);
	
	
	
}
