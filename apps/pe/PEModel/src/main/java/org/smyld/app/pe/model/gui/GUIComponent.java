package org.smyld.app.pe.model.gui;

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

	/**
	 * 
	 * @see
	 * @since
	 */
	public GUIComponent() {
	}

	public String getClassName() {
		return ClassName;
	}

	public void setClassName(String ClassName) {
		this.ClassName = ClassName;
	}

	public String getClassImportName() {
		if (ClassImportName == null) {
			ClassImportName = Package + "." + ID;
		}
		return ClassImportName;
	}

	public void setClassImportName(String ClassImportName) {
		this.ClassImportName = ClassImportName;
	}

	public String getRow() {
		return Row;
	}

	public void setRow(String Row) {
		this.Row = Row;
	}

	public String getColumn() {
		return Column;
	}

	public void setColumn(String Column) {
		this.Column = Column;
	}

	@Override
	public String getWidth() {
		return Width;
	}

	@Override
	public void setWidth(String Width) {
		if (Width != null) {
			this.Width = Width;
		}
	}

	public String getSelected() {
		return Selected;
	}

	public void setSelected(String Selected) {
		this.Selected = Selected;
	}

	public String getScope() {
		return Scope;
	}

	public void setScope(String Scope) {
		this.Scope = Scope;
	}

	public String getConstructorLine() {
		return ConstructorLine;
	}

	public void setConstructorLine(String ConstructorLine) {
		this.ConstructorLine = ConstructorLine;
	}

	public String getAddingLine() {
		return AddingLine;
	}

	public void setAddingLine(String AddingLine) {
		this.AddingLine = AddingLine;
	}

	public String getDefaultValue() {
		return DefaultValue;
	}

	public void setDefaultValue(String DefaultValue) {
		this.DefaultValue = DefaultValue;
	}

	public HashMap<String,String> getEvents() {
		return Events;
	}

	public void setEvents(HashMap<String,String> Events) {
		this.Events = Events;
	}

	public ArrayList<GUIComponent> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<GUIComponent> children) {
		this.children = children;
	}

	public void addChild(GUIComponent newComponent){
		if (children==null)
			children = new ArrayList<>();
		children.add(newComponent);

	}
	public boolean hasChildren(){ return ((this.children!=null)&&(this.children.size()>0));}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String Position) {
		this.Position = Position;
	}

	public String getPackage() {
		return Package;
	}

	public void setPackage(String Package) {
		this.Package = Package;
	}

	public String getIcon() {
		return Icon;
	}

	public void setIcon(String Icon) {
		this.Icon = Icon;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public String getLabelPosition() {
		return labelPosition;
	}

	public void setLabelPosition(String labelPosition) {
		this.labelPosition = labelPosition;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getCompWidth() {
		return compWidth;
	}

	public void setCompWidth(String compWidth) {
		this.compWidth = compWidth;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTopMargin() {
		return topMargin;
	}

	public void setTopMargin(String topMargin) {
		this.topMargin = topMargin;
	}

	public String getTitleType() {
		return titleType;
	}

	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

	public String getVGap() {
		return vGap;
	}

	public void setVGap(String vGap) {
		this.vGap = vGap;
	}

	public String getHGap() {
		return hGap;
	}

	public void setHGap(String hGap) {
		this.hGap = hGap;
	}

	public String getDockable() {
		return dockable;
	}

	public void setDockable(String dockable) {
		this.dockable = dockable;
	}

	public String getDragable() {
		return dragable;
	}

	public void setDragable(String dragable) {
		this.dragable = dragable;
	}

	public String getListenerTarget() {
		return listenerTarget;
	}

	public void setListenerTarget(String listenerTarget) {
		this.listenerTarget = listenerTarget;
	}

	public String getBorderType() {
		return borderType;
	}

	public void setBorderType(String borderType) {
		this.borderType = borderType;
	}

	public String getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(String borderWidth) {
		this.borderWidth = borderWidth;
	}

	public String getOrient() {
		return orient;
	}

	public void setOrient(String orient) {
		this.orient = orient;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getScrollable() {
		return scrollable;
	}

	public void setScrollable(String scrollable) {
		this.scrollable = scrollable;
	}

	/**
	 * @return the fieldWidth
	 */
	public String getFieldWidth() {
		return fieldWidth;
	}

	/**
	 * @param fieldWidth the fieldWidth to set
	 */
	public void setFieldWidth(String fieldWidth) {
		this.fieldWidth = fieldWidth;
	}

	/**
	 * @return the userConstraint
	 */
	public UserConstraint getUserConstraint() {
		return userConstraint;
	}

	/**
	 * @param userConstraint the userConstraint to set
	 */
	public void setUserConstraint(UserConstraint userConstraint) {
		this.userConstraint = userConstraint;
	}

	/**
	 * @return the borderTitle
	 */
	public String getBorderTitle() {
		return borderTitle;
	}

	/**
	 * @param borderTitle the borderTitle to set
	 */
	public void setBorderTitle(String borderTitle) {
		this.borderTitle = borderTitle;
	}
	
	

}
