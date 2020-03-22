package org.smyld.app.pe.model.gui;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.smyld.app.pe.model.user.UserConstraint;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
@Slf4j
@Setter
@Getter
@NoArgsConstructor
public class GUIComponent extends GUIField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ClassName;
	String ClassImportName;
	String Row;
	String Column;
	String Width;
	String fieldWidth;
	String Selected;
	String Scope;
	String ConstructorLine;
	String AddingLine;
	String DefaultValue;
	HashMap<String,String> Events;
	protected ArrayList<GUIComponent> children = new ArrayList<>();
	String layout;
	String Position;
	String Package;
	String Icon;
	String bgColor;
	String labelPosition;
	String type;
	String enabled;
	String scrollable;
	String tooltip;
	String compWidth;
	String align;
	String source;
	String topMargin;
	String titleType;
	String rows;
	String cols;
	String vGap;
	String hGap;
	String dockable;
	String dragable;
	String listenerTarget;
	String borderType;
	String borderWidth;
	String borderTitle;
	String orient;
	String tagName;
	Object[] values;
	String color;
	UserConstraint userConstraint;


	public String getClassImportName() {
		if (ClassImportName == null) {
			ClassImportName = Package + "." + ID;
		}
		return ClassImportName;
	}


	public void addChild(GUIComponent newComponent){
		if (children==null)
			children = new ArrayList<>();
		children.add(newComponent);

	}
	public boolean hasChildren(){ return ((this.children!=null)&&(this.children.size()>0));}


}
