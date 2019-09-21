package org.smyld.gui.edit;

import javax.swing.ImageIcon;

public class DefaultEditorPopupItem implements EditorPopupItem {
	String                  contents;
	String                  description;
	String                  name;
	ImageIcon               icon;
	EditorPopupItemCategory category;
	
	public DefaultEditorPopupItem(){
		
	}
	public DefaultEditorPopupItem(String name,EditorPopupItemCategory category,String contents,String descrip){
		setName(name);setCategory(category);setContents(contents);setDescription(descrip);
	}

	public EditorPopupItemCategory getCategory() {
		return category;
	}

	public String getContents() {
		return contents;
	}

	public String getDescription() {
		return description;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public String getName() {
		return name;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(EditorPopupItemCategory category) {
		this.category = category;
	}
	
	
	

}
