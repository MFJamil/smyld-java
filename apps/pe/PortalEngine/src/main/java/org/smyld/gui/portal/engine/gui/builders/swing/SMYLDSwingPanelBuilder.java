package org.smyld.gui.portal.engine.gui.builders.swing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.smyld.app.pe.model.gui.GUITable;
import org.smyld.app.pe.model.gui.GUITableColumn;
import org.jdom2.Element;

import org.smyld.gui.portal.engine.Application;
import org.smyld.gui.portal.engine.ApplicationGenerator;
import org.smyld.app.pe.model.gui.GUIComponent;
import org.smyld.lang.script.java.JavaClassBody;
import org.smyld.lang.script.java.JavaInterface;
import org.smyld.lang.script.java.JavaMethod;
import org.smyld.lang.script.java.JavaVariable;
import org.smyld.lang.script.util.Variable;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class SMYLDSwingPanelBuilder extends SMYLDSwingGUIBuilder {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector<String>           userConstLines;
	Vector<String>           initLines;
	Vector<String>           addLines; 
	Vector<String>           extraLines;
	Vector<String>           layouts;
	Vector<String>           listeners;
	ArrayList<GUIComponent>     components;
	Vector<JavaVariable>     dockables;
	Vector<GUIComponent>     dragables;
	HashMap<String, Element> panels;
	JavaClassBody        newPanelFactory   = null;
	JavaClassBody        newClass          = null;
	JavaClassBody        newInterface      = null;
	JavaMethod           initMethod        = null;
	JavaMethod           contructMethod    = null;
	JavaMethod           getPanelMethod    = null;
	JavaMethod           getMainComponent  = null;
	String               panelNameInstance = "targetPanel";
	String               mainComponent     = "this";
	String               appManagerParm    = "appManager";
	String               mainPanelID       = null; 
	int                  tableCount;
	boolean              containsConstraint = false;
	/**
	 * 
	 * @see
	 * @since
	 */
	public SMYLDSwingPanelBuilder() {
	}

	@Override
	public void setActiveApplication(Application newApplication) {
		super.setActiveApplication(newApplication);
		doInit();
	}

	private void doInit() {
		newPanelFactory = new JavaClassBody(PNL_FCTR_CLASS_NAME,
				activeApplication.getAppReader().getMainClassPackage(),
				CLASS_NAME_APP_PNL_FCT, true, false);
		getPanelMethod = new JavaMethod(PNL_FCTR_METH_GET_PANEL,
				Variable.SCOPE_PUBLIC, CLASS_NAME_SMYLD_PANEL);
		getPanelMethod.addParameter(panelNameInstance, "String");
		getPanelMethod.addParameter("manager", "Object");
		newPanelFactory.addImport(APP_MANAGER_FP_CLASS_NAME);
		newPanelFactory.addImport(CLASS_NAME_FP_SMYLD_PANEL);
		newPanelFactory.addImport(CLASS_NAME_FP_APP_PNL_FCT);
		getPanelMethod.addCodeLine("ApplicationManager " +appManagerParm + " = (ApplicationManager)manager"); 
	}

	public JavaClassBody getFactory() {
		getPanelMethod.addCodeLine("return null");
		newPanelFactory.addMethod(getPanelMethod);
		return newPanelFactory;
	}

	public void reset() {
		initLines          = null;
		addLines           = null;
		listeners          = null;
		components         = null;
		layouts            = null;
		extraLines         = null;
		newClass           = null;
		initMethod         = null;
		contructMethod     = null;
		newInterface       = null;
		dockables          = null;
		dragables          = null;
		mainComponent      = "this";
		containsConstraint = false;

	}

	public JavaClassBody[] generatePanel(ArrayList<GUIComponent> classComponents, HashMap<String, Element> panels)throws Exception {
		this.panels = panels;
		return generatePanel(classComponents);
	}
	
	public JavaClassBody[] generatePanel(ArrayList<GUIComponent> classComponents)
			throws Exception {
		reset();
		if (classComponents != null) {
			GUIComponent rootComponent = (GUIComponent) classComponents.get(0);
			String       className     = rootComponent.getID();
			String       targetPackge  = rootComponent.getPackage();
			             mainPanelID   = className;
			// Code lines for the panel factory reflection
			getPanelMethod.addCodeLine("if (" + panelNameInstance
					+ ".equals(\"" + className + "\")){");
			String instance = "item" + className;
			// Here we need to pass the listener that is provided by the business project
			getPanelMethod.addCodeLine(className + " " + instance + " = null");
			getPanelMethod.addCodeLine("if("+ appManagerParm +"!=null){");
			getPanelMethod.addCodeLine( instance + " = new "
					+ className + "(" + appManagerParm + ".get"+className+ "Listener())");
			getPanelMethod.addCodeLine("}");
			getPanelMethod.addCodeLine("else{");

			getPanelMethod.addCodeLine(instance + " = new " + className + "(null)");
			getPanelMethod.addCodeLine("}");
			getPanelMethod.addCodeLine(instance + ".init()");
			getPanelMethod.addCodeLine("return " + instance);
			getPanelMethod.addCodeLine("}");
			newPanelFactory.addImport(targetPackge + "." + className);

			return generatePanel(rootComponent, classComponents, targetPackge);
		}
		return null;
	}

	public JavaClassBody[] generatePanel(GUIComponent rootComp,	ArrayList<GUIComponent> classComponents, String targetPackge) throws Exception {
		if (rootComp != null) {
			components = classComponents;
			// System.out.println("Creating class Named : " + className);
			newClass         = new JavaClassBody(rootComp.getID(), targetPackge,rootComp.getClassName(), true, false);
			newInterface     = new JavaInterface(rootComp.getID()+ CLASS_INTERFACE_SUFFIX, targetPackge, null, true);
			contructMethod   = new JavaMethod(rootComp.getID(),Variable.SCOPE_PUBLIC, null, true);
			initMethod       = new JavaMethod(CLASS_METHOD_INIT,Variable.SCOPE_PUBLIC, null);
			getMainComponent = new JavaMethod(PNL_CLASS_METH_GET_MAIN_COMP,	Variable.SCOPE_PUBLIC, CLASS_NAME_SWING_COMP);
			JavaVariable interfaceInstance = new JavaVariable(CLASS_INTERFACE,Variable.SCOPE_PUBLIC, newInterface.getName());
			newClass.addImport(rootComp.getClassImportName());
			newClass.addImport(CLASS_NAME_FP_SWING_COMP);
			newClass.addImport(appReader.getMainClassPackage() + "."
					+ APP_MAIN_CLASS_NAME);
			newClass.addMethod(initMethod);
			newClass.addVariable(interfaceInstance);
			contructMethod.addParameter("windowListener", newInterface
					.getName());
			contructMethod.addCodeLine(interfaceInstance.getName()
					+ "= windowListener");
			// COMPONENT ORIENTATION
			contructMethod.addCodeLine(SWING_METH_APPLY_COMP_ORIENT + "("
					+ APP_MAIN_CLASS_NAME + "." + APP_METH_GET_APP_ORIENT
					+ "())");

			newClass.addMethod(contructMethod);
			// Reading list field components

			buildComponents(newClass);
			JavaMethod pnlHandle = new JavaMethod(PNL_METH_ACTIVE_PNL_HDL,
					Variable.SCOPE_PUBLIC, "void");
			pnlHandle.addModifier(JavaClassBody.MODIFIER_ABSTRACT);
			pnlHandle.addParameter("panelHandle", rootComp.getID());
			newInterface.addMethod(pnlHandle);
		} else {
			throw new Exception("Class name attribute is missed ... ");
		}
		getMainComponent.addCodeLine("return " + mainComponent);
		newClass.addMethod(getMainComponent);
		JavaClassBody[] result = { newClass, newInterface };
		return result;
	}

	private void buildComponents(JavaClassBody newClass) {
		initLines      = new Vector<String>();
		addLines       = new Vector<String>();
		extraLines     = new Vector<String>();
		listeners      = new Vector<String>();
		layouts        = new Vector<String>();
		userConstLines = new Vector<String>();
		dockables      = new Vector<JavaVariable>();
		dragables      = new Vector<GUIComponent>();
		
		if (initMethod != null) {
			// GUIComponent parentComponent = (GUIComponent)components.get(0);
			addCodeLines(components, null);
			if (userConstLines.size()>0){
				initMethod.addCodeLine("// User Constraints definition");
				initMethod.addCodeLines(userConstLines);
				newClass.addImport(CLASS_NAME_FP_USER_CONSTRAINT);
			}

			initMethod.addCodeLine("// Initialization Lines");
			initMethod.addCodeLine("final " + newClass.getName() + " instance = this;");
			initMethod.addCodeLines(initLines);

			if (dockables.size() > 0) {
				// initMethod.addCodeLine("// Dockable ");
				handelDockableMainComponent();
			}
			initMethod.addCodeLine("// Extra Lines");
			initMethod.addCodeLines(extraLines);

			initMethod.addCodeLine("// Layout Lines");
			initMethod.addCodeLines(layouts);
			initMethod.addCodeLine("// Structure Lines");
			initMethod.addCodeLines(addLines);
			handelDragables();
			initMethod.addCodeLine("// Listeners Lines");
			initMethod.addCodeLines(listeners);
			initMethod.addCodeLine("if (" + CLASS_INTERFACE + "!=null){");
			initMethod.addCodeLine(CLASS_INTERFACE + "."
					+ PNL_METH_ACTIVE_PNL_HDL + "(this)");
			initMethod.addCodeLine("}");

		}
	}

	private void addCodeLines(ArrayList<GUIComponent> componentList, GUIComponent parentComponent) {
		Iterator<GUIComponent> itr = null;
		if (parentComponent == null) {
			itr = componentList.iterator();
		} else {
			itr = parentComponent.getChildren().iterator();
		}
		while (itr.hasNext()) {
			GUIComponent currentComp = (GUIComponent) itr.next();
			if(currentComp.getUserConstraint()!=null){
				userConstLines.add(createAddConstraintCodeLine(currentComp.getUserConstraint()));
				containsConstraint = true;
			}
			addLines.add(createAddLine(currentComp, parentComponent));

			createListeners(currentComp, listeners, newClass,
					(JavaInterface) newInterface, CLASS_INTERFACE);
			// if the current component is the same parent class name do not do
			// any thing
			if (!currentComp.getID().equals(newClass.getName())) {
				processChildComponent(currentComp);
				/*
				 * String initCodeLine = createInitLine(currentComp); if
				 * (initCodeLine!=null){ String compScope =
				 * JavaVariable.SCOPE_PUBLIC; // Default value if
				 * (currentComp.getScope()!=null) compScope =
				 * currentComp.getScope(); JavaVariable newVariable = null; if
				 * (currentComp.getSource()!=null){ newVariable = new
				 * JavaVariable(currentComp.getID(),compScope,currentComp.getSource()); //
				 * TODO Adding the import of the class from the package
				 * information }else{ newVariable = new
				 * JavaVariable(currentComp.getID(),compScope,currentComp.getClassName());
				 * newClass.addImport(currentComp.getClassImportName()); }
				 * newClass.addVariable(newVariable); if
				 * (currentComp.getClassName().equals(CLASS_NAME_SMYLD_TABLE))
				 * addTableInitLines((GUITable)currentComp); else
				 * initLines.add(initCodeLine); // COMPONENT ORIENTATION String
				 * compOrient = currentComp.getID() + "." +
				 * SWING_METH_SET_COMP_ORIENT + "(" + APP_MAIN_CLASS_NAME + "." +
				 * APP_METH_GET_APP_ORIENT + "())"; initLines.add(compOrient); }
				 */
			}
			handleDockable(currentComp);
			if (currentComp.getDragable() != null) {
				dragables.add(currentComp);
			}

			createExtraLines(currentComp);
			processLayout(currentComp);
			if (currentComp.getChildren() != null) {
				addCodeLines(null, currentComp);
			}

		}
	}

	private void processChildComponent(GUIComponent currentComp) {
		String initCodeLine = createInitLine(currentComp);
		if (initCodeLine != null) {
			String compScope = Variable.SCOPE_PUBLIC; // Default value
			if (currentComp.getScope() != null) {
				compScope = currentComp.getScope();
			}
			JavaVariable newVariable = null;
			if (currentComp.getSource() != null) {
				newVariable = new JavaVariable(currentComp.getID(), compScope,currentComp.getSource());
				// TODO Adding the import of the class from the package
				String packageImport = getPanelImportPackage(currentComp.getSource());
				if (packageImport!=null)
						newClass.addImport(packageImport);
				
				// information
			} else {
				newVariable = new JavaVariable(currentComp.getID(), compScope,currentComp.getClassName());
				newClass.addImport(currentComp.getClassImportName());
			}
			newClass.addVariable(newVariable);
			if (isTableComponent(currentComp.getClassName())) {
				addTableInitLines((GUITable) currentComp);
			} else {
				initLines.add(initCodeLine);
			}
			// COMPONENT ORIENTATION
			String compOrient = currentComp.getID() + "."
					+ SWING_METH_SET_COMP_ORIENT + "(" + APP_MAIN_CLASS_NAME
					+ "." + APP_METH_GET_APP_ORIENT + "())";
			initLines.add(compOrient);

		}

	}
	private String getPanelImportPackage(String panelName){
		Element panelEl = panels.get(panelName);
		if (panelEl!= null){
			String panelPackage = panelEl.getAttributeValue(TAG_COMP_ATT_PACKAGE);
			if ((panelPackage!=null)&&(!panelPackage.equals(newClass.getPackageName())))
				return panelPackage + '.' + panelName;
		}
		return null;
		
	}

	private boolean isTableComponent(String className) {
		return ((className.equals(CLASS_NAME_SMYLD_TABLE)) || (className
				.equals(CLASS_NAME_SMYLD_LTABLE)));
	}

	private String createInitLine(GUIComponent component) {
		String initCodeLine = null;
		String defaultValue = component.getDefaultValue();
		String compLable = component.getLabel();
		if (defaultValue == null) {
			defaultValue = "";
		}
		compLable = getTranslate(component.getID(), compLable);
		
		// Detecting the reference of panel
		if (component.getSource() != null) {
			// In case the referenced panel is external, no need to pass the interface instance to it
			// windowObject.getBodyID() + " = new " + className + "(" +
			// WIN_INSTANCE_LISTENER + ")" );
			
			if (component.getSource().indexOf('.')==-1){
				initCodeLine = component.getID() + " = new " + component.getSource() + "(" + CLASS_INTERFACE + ")";
				String packageImport = getPanelImportPackage(component.getSource());
				if (packageImport!=null)
						newInterface.addImport(packageImport + CLASS_INTERFACE_SUFFIX);

				// because it could be somewhere else
				((JavaInterface) newInterface).addParentClass(component.getSource()	+ CLASS_INTERFACE_SUFFIX);
				
			}else{
				initCodeLine = component.getID() + " = new "+ component.getSource() + "()";

			}
		} else if (component.getClassName().equals(CLASS_NAME_SMYLD_LPASS)) {
			initCodeLine = component.getID() + " = new "
					+ component.getClassName() + "(" + compLable + "," + component.getHeight() + "," + getProperLabeledCompMainWidth(component) + ")";

		} else if (component.getClassName().equals(CLASS_NAME_SMYLD_LTXT)) {
			initCodeLine = component.getID() + " = new "+ component.getClassName() + "(" + compLable + ",\""+ defaultValue + "\"," + (component.getHeight()==null?"0":component.getHeight()) + "," + getProperLabeledCompMainWidth(component) + ")";
		} else if (component.getClassName().equals(CLASS_NAME_SMYLD_LCOMBO)) {
			initCodeLine = component.getID() + " = new "
					+ component.getClassName() + "(" + compLable +"," + getProperLabeledCompMainWidth(component) +  ")";
		} else if (component.getClassName().equals(CLASS_NAME_SMYLD_BUTN)) {
			initCodeLine = component.getID() + " = new "
					+ component.getClassName() + "(" + compLable + ")";
		} else if (component.getClassName().equals(CLASS_NAME_SMYLD_CHKB)) {
			initCodeLine = component.getID() + " = new "
					+ component.getClassName() + "(" + compLable + ","
					+ component.getSelected() + ")";
		} else if (component.getClassName().equals(CLASS_NAME_SMYLD_RADB)) {
			initCodeLine = component.getID() + " = new "
					+ component.getClassName() + "(" + compLable + ","
					+ component.getSelected() + ")";
		} else if (component.getClassName().equals(CLASS_NAME_SMYLD_LBL)) {
			initCodeLine = component.getID() + " = new "
					+ component.getClassName() + "("
					+ getLabelConstructor(component) + ")";
			/*
			 * }else if (component.getClassName().equals(CLASS_NAME_SMYLD_PANEL)){
			 * initCodeLine = component.getID() + " = new " +
			 * component.getClassName() + "()"; }else if
			 * (component.getClassName().equals(CLASS_NAME_DOCKABLE_TEMPLATE)){
			 * initCodeLine = component.getID() + " = new " +
			 * component.getClassName() + "()"; }else if
			 * (component.getClassName().equals(CLASS_NAME_SMYLD_SPLIT)){
			 * initCodeLine = component.getID() + " = new " +
			 * component.getClassName() + "()"; }else if
			 * (component.getClassName().equals(CLASS_NAME_SMYLD_SP_SCRLP)){
			 * initCodeLine = component.getID() + " = new " +
			 * component.getClassName() + "()"; }else if
			 * (component.getClassName().equals( CLASS_NAME_SMYLD_DESKTOP_PANE)){
			 * initCodeLine = component.getID() + " = new " +
			 * component.getClassName() + "()";
			 */
		} else if (component.getClassName().equals(CLASS_NAME_SMYLD_TXT_LAREA)) {
			initCodeLine = component.getID() + " = new "
					+ component.getClassName() + "(" + compLable + ",\""
					+ defaultValue + "\"," + component.getHeight() + ","
					+ component.getWidth() +"," + getProperLabeledCompMainWidth(component) +  ")";
		} else if (component.getClassName().equals(CLASS_NAME_SMYLD_LTABLE)) {
			initCodeLine = component.getID() + " = new "
					+ component.getClassName() + "(" + compLable + ","
					+ component.getHeight() + "," + component.getWidth() + ")";
		} else if (component.getClassName().equals(CLASS_NAME_SMYLD_TABLE)) {
			initCodeLine = component.getID() + " = new "
					+ component.getClassName() + "(" + component.getHeight()
					+ "," + component.getWidth() + ")";
		} else if (component.getClassName().equals(CLASS_NAME_SMYLD_TREE)) {
			if (TAG_ATT_MENUBAR_TYPE_TREE.equals(component.getType())) {
				String menuListener = null;
				if (isSelfListener(component)) {
					menuListener = CLASS_INTERFACE;
				}
				String refMenuTree = SMYLDSwingMenuBuilder.getRefAsTree(component
						.getID(), menuListener);
				initCodeLine = component.getID() + "=" + refMenuTree;
				newClass.addImport(appReader.getMainClassPackage() + "."
						+ APP_MAIN_CLASS_NAME);
			} else {
				// We need to add constructor that includes the action handler because the tree will not be active
				initCodeLine = component.getID() + " = new " + component.getClassName() + "()";
				
			}
		} else if (component.getClassName().equals(CLASS_NAME_SMYLD_TOOLBAR)) {
			// Need to revise the code so that it hands over the panel listener to handle the actions fired by the toolbar
			
			initCodeLine = component.getID() + "="
					+ SMYLDSwingToolbarBuilder.getRef(component.getID(),CLASS_INTERFACE);
			newClass.addImport(appReader.getMainClassPackage() + "."
					+ APP_MAIN_CLASS_NAME);
			newInterface.addImport(CLASS_NAME_FP_ACTION_HANDLER);
			((JavaInterface) newInterface).addParentClass(CLASS_NAME_ACTION_HANDLER);

		} else if (component.getClassName().equals(CLASS_NAME_PE_PANEL)) {
			//System.out.println("HERE IS THE PE PANELLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL   (" + containsConstraint + ") valule for Comp ID  - " + component.getID());
			//initCodeLine = component.getID() + " = new " + component.getClassName() + "("+(containsConstraint?"userManager":"") +")";
			initCodeLine = component.getID() + " = new " + component.getClassName() + "(userManager)";
			
		} else {
			initCodeLine = component.getID() + " = new "
					+ component.getClassName() + "()";
		}
		return initCodeLine;
	}
	
	private String getProperLabeledCompMainWidth(GUIComponent cmp){
		if (cmp.getFieldWidth()!=null)
			return cmp.getFieldWidth();
		return "0";
	}
	/* This might not be needed because the labeled component should adjust this ratio in case the width was not provided
	private String getProperLabeledCompMainWidth(GUIComponent cmp){
		if ((cmp.getFieldWidth()==null)||(cmp.getFieldWidth().isEmpty())||(cmp.getFieldWidth().equals("0"))){
			int totalWidth = Integer.parseInt(cmp.getWidth());
			if (totalWidth>0){
				int fieldWidth = (int)(totalWidth*0.66f); // we assume logical size
				return Integer.toString(fieldWidth);
			}
		}else{
			return cmp.getFieldWidth();
		}
		return "";
	}*/

	// Handeling the dockable component
	private void handleDockable(GUIComponent component) {
		if ((TAG_ATT_DOCKABLE_YES.equals(component.getDockable()))) {
			newClass.addImport(CLASS_NAME_FP_DOCKABLE_CONTAINER);
			String contID = createDockabelID(component.getID());
			String contInitCodeLine = contID + " = new "
					+ CLASS_NAME_DOCKABLE_CONTAINER + "()";
			initLines.add(contInitCodeLine);
			JavaVariable newVariable = new JavaVariable(contID,
					Variable.SCOPE_PUBLIC, CLASS_NAME_DOCKABLE_CONTAINER);
			newClass.addVariable(newVariable);
			dockables.add(newVariable);
		}
	}

	private String getLabelConstructor(GUIComponent component) {
		//System.out.println("Received Request to construct : " + component.getID() + " for class name " + newClass.getName());
		String labelText = getTranslate(component.getID(),component.getLabel());
		if (component.getIcon() != null) {
			return  labelText + "," + ApplicationGenerator.getImage(component.getIcon());
		}
		return labelText;
	}

	private String getTranslate(String id, String defValue) {
		return ApplicationGenerator.getTranslate(newClass.getName(), "\"" + id
				+ "\"", "\"" + (defValue!=null?defValue:"") + "\"");
	}

	private void createExtraLines(GUIComponent component) {
		String curID = component.getID();
		if (curID.equals(newClass.getName())) {
			curID = "this";
		}
		if ((component.getSource() != null)&&(component.getSource().indexOf(".")==-1)) {
			extraLines.add(curID + "." + "init()");
		}
		if (component.getBgColor() != null) {
			extraLines.add(curID + "." + SMYLD_MDI_METH_SET_BG_COLOR + "(\"0x"
					+ component.getBgColor() + "\")");
		}
		// Need to check the reason for this condition
		if ((component.getIcon() != null)&&((isSMYLDLabeledComponent(component))||supportsSetIcon(component))) {
			extraLines.add(curID + ".setIcon(" + ApplicationGenerator.getImage(component.getIcon()) + ")");
		}

		if (component.getColor() != null) {
			newClass.addImport("java.awt.Color");
			extraLines.add(curID + "." + SMYLD_MDI_METH_SET_COLOR
					+ "(Color.decode(\"0x" + component.getColor() + "\"))");
		}
		if (component.getAlign() != null) {
			if (is(component, CLASS_NAME_SMYLD_LBL)) {
				// this is no more supported (i.e. calling constants from the instances
				//extraLines.add(curID + ".setHorizontalAlignment(" + curID + "."
				//		+ component.getAlign().toUpperCase() + ")");
				
				extraLines.add(curID + ".setHorizontalAlignment(javax.swing.SwingConstants."
						+ component.getAlign().toUpperCase() + ")");
				
			}
		}
		// Excuting the border and reflecting it on the created component
		if ((component.getBorderType() != null)	|| (component.getBorderWidth() != null)) {
			handleComponentBorder(component);
		}

		// If
		if ((component.getClassName().equals(CLASS_NAME_SMYLD_SPLIT))
				&& (component.getOrient() != null)) {
			if (component.getOrient().equals(TAG_ATT_ORIENT_HORIZONTAL)) {
				extraLines.add(curID
						+ ".setOrientation(JSplitPane.HORIZONTAL_SPLIT)");
			} else {
				extraLines.add(curID
						+ ".setOrientation(JSplitPane.VERTICAL_SPLIT)");
			}
		}
		if ((component.getClassName().equals(CLASS_NAME_SMYLD_LCOMBO))
				&& (component.getValues() != null)) {
			for (int i = 0; i < component.getValues().length; i++) {
				extraLines.add(curID + ".addItem(\"" + component.getValues()[i]
						+ "\")");
			}
		}
		String scrollableAtt = component.getScrollable();
		if ((scrollableAtt!=null)&&((scrollableAtt.toLowerCase().equals("true"))||(scrollableAtt.toLowerCase().equals("false")))
				&& (isSMYLDLabeledComponent(component))) {
			extraLines.add(curID + ".setAutoscrolls(" + scrollableAtt.toLowerCase() + ")");
		}

	}
	private boolean supportsSetIcon(GUIComponent comp){
		return ((comp.getClassName().equals(CLASS_NAME_SMYLD_BUTN))||
				(comp.getClassName().equals(CLASS_NAME_SMYLD_CHKB))||
				(comp.getClassName().equals(CLASS_NAME_SMYLD_LTABLE)));				
	}
	private boolean isSMYLDLabeledComponent(GUIComponent comp){
		return ((comp.getClassName().equals(CLASS_NAME_SMYLD_LTXT))||
				(comp.getClassName().equals(CLASS_NAME_SMYLD_TXT_LAREA))||
				(comp.getClassName().equals(CLASS_NAME_SMYLD_LTABLE))||
				(comp.getClassName().equals(CLASS_NAME_SMYLD_LPASS))||
				(comp.getClassName().equals(CLASS_NAME_SMYLD_LCOMBO)));				
	}

	private void handleComponentBorder(GUIComponent component) {
		// Excuting the border and reflecting it on the created component
		// BorderFactory.createLoweredBevelBorder()
		// BorderFactory.createRaisedBevelBorder()
		// BorderFactory.createEtchedBorder()
		// BorderFactory.createEmptyBorder()
		// BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)
		// BorderFactory.createEtchedBorder(EtchedBorder.RAISED)

		boolean addImport = false;

		if (TAG_ATT_BORDER_TYPE_RAISED.equals(component.getBorderType())) {
			extraLines.add(component.getID()+ ".setBorder(BorderFactory.createRaisedBevelBorder())");
			addImport = true;
		} else if (TAG_ATT_BORDER_TYPE_LINE.equals(component.getBorderType())) {
			extraLines.add(component.getID()+ ".setBorder(BorderFactory.createLineBorder(Color.BLACK))");
			newClass.addImport("java.awt.Color");
			addImport = true;
		} else if (TAG_ATT_BORDER_TYPE_LOWERED.equals(component.getBorderType())) {
			extraLines.add(component.getID()+ ".setBorder(BorderFactory.createLoweredBevelBorder())");
			addImport = true;
		} else if (TAG_ATT_BORDER_TYPE_ETCHED.equals(component.getBorderType())) {
			extraLines.add(component.getID()+ ".setBorder(BorderFactory.createEtchedBorder())");
			addImport = true;
		} else if (TAG_ATT_BORDER_TYPE_ETCH_LOW.equals(component.getBorderType())) {
			extraLines.add(component.getID()+ ".setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED))");
			addImport = true;
		} else if (TAG_ATT_BORDER_TYPE_ETCH_RAS.equals(component.getBorderType())) {
			extraLines.add(component.getID() + ".setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED))");
			addImport = true;
		} else if (TAG_ATT_BORDER_TYPE_EMPTY.equals(component.getBorderType())) {
			extraLines.add(component.getID()+ ".setBorder(BorderFactory.createEmptyBorder())");
			addImport = true;

		}
		if (component.getBorderTitle()!=null){
			extraLines.add(component.getID()+ ".setBorder(BorderFactory.createTitledBorder(" + component.getID()+ ".getBorder(),\"" + component.getBorderTitle() + "\"))");
			addImport = true;
		}

		if (addImport) {
			newClass.addImport("javax.swing.BorderFactory");
			newClass.addImport("javax.swing.border.EtchedBorder");
		}
	}

	private boolean is(GUIComponent component, String className) {
		return component.getClassName().equals(className);
	}

	private void addTableInitLines(GUITable tableComponent) {
		// initCodeLine = component.getID() + " = new " +
		// component.getClassName() + "(" + component.getHeight() + "," +
		// component.getWidth() + ")";
		if ((tableComponent.getColumns() != null)
				&& (tableComponent.getColumns().size() > 0)) {
			String colsInstance = "cols" + tableCount;
			String dataInstance = "data" + tableCount;
			tableCount++;
			newClass.addImport(CLASS_NAME_FP_GUI_TABLE_COL);
			initLines.add("String[] " + colsInstance + " = new String["
					+ tableComponent.getColumns().size() + "]");
			for (GUITableColumn curCol : tableComponent.getColumns().values()) {
				String colLable = getTranslate(curCol.getID(), curCol
						.getLabel());
				initLines.add(colsInstance + "[" + curCol.getOrderInTable()
						+ "] = " + colLable);
				// initLines.add(colsInstance + "[" + curCol.getOrderInTable()
				// +"] = new " + CLASS_NAME_GUI_TABLE_COL + "(" +
				// curCol.getOrderInTable() + ",\"" + curCol.getID() + "\"," +
				// colLable + ",\"" + curCol.getWidth() + "\")");
			}
			if (tableComponent.getValues() != null) {
				Object[] values = tableComponent.getValues();
				initLines.add("Object[][] " + dataInstance + " = new Object["
						+ values.length + "]["
						+ tableComponent.getColumns().size() + "]");
				for (int rw = 0; rw < values.length; rw++) {
					String[] curRow = (String[]) values[rw];
					for (int cl = 0; cl < tableComponent.getColumns().size(); cl++) {
						initLines.add(dataInstance + "[" + rw + "][" + cl
								+ "]  = \"" + curRow[cl] + "\"");
					}
				}
			} else {
				initLines.add("Object[][] " + dataInstance
						+ " = new Object[2]["
						+ tableComponent.getColumns().size() + "]");
			}

			String compLable = getTranslate(tableComponent.getID(),
					tableComponent.getLabel());

			// The line where the initilaization of the table using the data and
			// cols givin
			if (tableComponent.getClassName().equals(CLASS_NAME_SMYLD_TABLE)) {
				initLines.add(tableComponent.getID() + " = new "
						+ tableComponent.getClassName() + "(" + dataInstance
						+ "," + colsInstance + ")");
			} else {
				initLines.add(tableComponent.getID() + " = new "
						+ tableComponent.getClassName() + "(" + compLable + ","
						+ dataInstance + "," + colsInstance + ")");
			}
		}
	}

	@SuppressWarnings("unused")
	private void processComponents(GUIComponent currentComp) {
		if (currentComp.getClassName().equals(CLASS_NAME_SMYLD_PANEL)) {

		} else if (currentComp.getClassName().equals(CLASS_NAME_SMYLD_SP_SCRLP)) {

		} else if (currentComp.getClassName().equals(CLASS_NAME_SMYLD_SPLIT)) {

		} else if (currentComp.getClassName().equals(CLASS_NAME_SMYLD_SP_SIDE)) {

		} else {

		}
	}

	private void processLayout(GUIComponent currentComp) {
		if (currentComp.getLayout() != null) {
			if (currentComp.getLayout().equals(TAG_ATT_LAYOUT_BORDER)) {
				createLayoutLines(currentComp, CLASS_NAME_FP_LAYOUT_BORDER,
						CLASS_NAME_LAYOUT_BORDER, "_BR");
			} else if (currentComp.getLayout().equals(TAG_ATT_LAYOUT_RC)) {
				createLayoutLines(currentComp, CLASS_NAME_FP_LAYOUT_RC,
						CLASS_NAME_LAYOUT_RC, "_RC");
			} else if (currentComp.getLayout().equals(TAG_ATT_LAYOUT_GRID)) {
				createLayoutLines(currentComp, CLASS_NAME_FP_LAYOUT_GRID,
						CLASS_NAME_LAYOUT_GRID, "_GRD");
			} else if (currentComp.getLayout().equals(TAG_ATT_LAYOUT_FLOW)) {
				createLayoutLines(currentComp, CLASS_NAME_FP_LAYOUT_FLOW,
						CLASS_NAME_LAYOUT_FLOW, "_FL");
			} else if (currentComp.getLayout().equals(TAG_ATT_LAYOUT_SPRING)) {
				createLayoutLines(currentComp, CLASS_NAME_FP_LAYOUT_SPRING,
						CLASS_NAME_LAYOUT_SPRING, "_SP");

			}
		}
	}

	private void createLayoutLines(GUIComponent currentComp,
			String layoutImportClass, String layoutClass, String layoutClassSuff) {
		newClass.addImport(layoutImportClass);
		JavaVariable classLayout = new JavaVariable(currentComp.getID()
				+ layoutClassSuff, Variable.SCOPE_PRIVATE, layoutClass);
		newClass.addVariable(classLayout);
		StringBuffer layoutBuf = new StringBuffer(classLayout.getName());
		layoutBuf.append("= new ");
		layoutBuf.append(layoutClass);
		layoutBuf.append("(");
		if ((currentComp.getRows() != null) && (currentComp.getCols() != null)
				&& (layoutClass.equals(CLASS_NAME_LAYOUT_GRID))) {
			layoutBuf.append(currentComp.getRows());
			layoutBuf.append(",");
			layoutBuf.append(currentComp.getCols());
			layoutBuf.append(",");
			addGapConstructorParameters(layoutBuf, currentComp);
		} else if (layoutClass.equals(CLASS_NAME_LAYOUT_FLOW)) {
			String defAlign = getFlowAlignment(currentComp.getAlign());
			layoutBuf.append(defAlign);
			if (currentComp.getHGap() != null) {
				layoutBuf.append(",");
				addGapConstructorParameters(layoutBuf, currentComp);
			}
		} else if ((currentComp.getHGap() != null)
				&& ((LAYOUTS_SUPPORT_GAP.indexOf(layoutClass) != -1))) {
			addGapConstructorParameters(layoutBuf, currentComp);
		}
		layoutBuf.append(")");
		initLines.add(layoutBuf.toString());
		if ((currentComp.getTopMargin() != null)
				&& (layoutClass.equals(CLASS_NAME_LAYOUT_RC))) {
			initLines.add(classLayout.getName() + ".setTopMargin("
					+ currentComp.getTopMargin() + ")");
		}
		// COMPONENT ORIENTATION
		if (layoutClass.equals(CLASS_NAME_LAYOUT_RC)) {
			initLines.add(classLayout.getName() + "."
					+ SWING_METH_SET_COMP_ORIENT + "(" + APP_MAIN_CLASS_NAME
					+ "." + APP_METH_GET_APP_ORIENT + "())");
		}

		if (currentComp.getID().equals(newClass.getName())) {
			layouts.add("this.setLayout(" + classLayout.getName() + ")");
		} else {
			layouts.add(currentComp.getID() + ".setLayout("
					+ classLayout.getName() + ")");
		}

	}

	private String getFlowAlignment(String alignValue) {
		if (alignValue != null) {
			if (alignValue.equals(TAG_ATT_POS_RIGHT)) {
				return LAYOUT_FLOW_ALIGN_RIGHT;
			}
			if (alignValue.equals(TAG_ATT_POS_LEFT)) {
				return LAYOUT_FLOW_ALIGN_LEFT;
			}
		}
		return LAYOUT_FLOW_ALIGN_CENTER;
	}

	private void addGapConstructorParameters(StringBuffer buf,
			GUIComponent targetComp) {
		if (targetComp.getHGap() != null) {
			buf.append(targetComp.getHGap());
			buf.append(",");
			if (targetComp.getVGap() != null) {
				buf.append(targetComp.getVGap());
			} else {
				buf.append("0");
			}
		}

	}

	private String createAddLine(GUIComponent component,GUIComponent parentComponent) {
		if (component.getID().equals("fontFamilyScr")){
			System.out.println("Got it");
		}
		String addCodeLine = null;
		String parentRef = "this";
		String parentClass = CLASS_NAME_PE_PANEL;
		if (component.getListenerTarget() != null) {
			processListenerLinkage(component);
		}

		if (parentComponent != null) {
			parentRef = parentComponent.getID();
			parentClass = parentComponent.getClassName();
			if (parentRef.equals(newClass.getName())) {
				parentRef = "this";
			}
		}
		if (component.getLabelPosition() != null) {
			handleLabelPosition(component);
		}
		if (parentClass.equals(CLASS_NAME_SMYLD_SPLIT)) {
			if (component.getPosition() != null) {
				if (component.getPosition().equals(TAG_ATT_POS_LEFT)) {
					addCodeLine = parentRef + ".setLeftComponent("
							+ component.getID() + ")";
				} else if (component.getPosition().equals(TAG_ATT_POS_RIGHT)) {
					addCodeLine = parentRef + ".setRightComponent("
							+ component.getID() + ")";
				} else if (component.getPosition().equals(TAG_ATT_POS_UP)) {
					addCodeLine = parentRef + ".setTopComponent("
							+ component.getID() + ")";
				} else if (component.getPosition().equals(TAG_ATT_POS_DOWN)) {
					addCodeLine = parentRef + ".setBottomComponent("
							+ component.getID() + ")";
				}
			}
		} else if (parentClass.equals(CLASS_NAME_SMYLD_SP_SCRLP)) {
			addCodeLine = parentRef + ".getViewport().add(" + component.getID()
					+ ")";
		} else if (parentClass.equals(CLASS_NAME_SMYLD_TABBEDPANE)) {
			if (component.getIcon() != null) {
				addCodeLine = "addTab(" +parentRef + "," + getTranslate(component.getID(), component.getLabel())
				+ ","
				+ ApplicationGenerator.getImage(component.getIcon())
				+ "," + component.getID() + ",\"" + component.getID() + "\")";

				/*
				addCodeLine = parentRef + ".addTab(" + getTranslate(component.getID(), component.getLabel())
						+ ","
						+ ApplicationGenerator.getImage(component.getIcon())
						+ "," + component.getID() + ")";
				*/
			} else {
				/*
				addCodeLine = parentRef + ".addTab(" + getTranslate(component.getID(), component.getLabel())
						+ "," + component.getID() + ")";
				*/
				addCodeLine = "addTab(" +parentRef + "," + getTranslate(component.getID(), component.getLabel())
					+ "," + component.getID() + ",\"" + component.getID() + "\")";

			}

		} else if (component.getPosition() != null) {
			if ((parentComponent != null)&& (parentComponent.getLayout() != null)) {
				if (parentComponent.getLayout().equals(TAG_ATT_LAYOUT_BORDER)) {
					//addCodeLine = parentRef + ".add(" + component.getID()+ ",\"" + component.getPosition() + "\")";
					addCodeLine = parentRef + ".add(\"" + component.getID() + "\"," + component.getID()+ ",\"" + component.getPosition() + "\")";
				}
			} else if ((parentComponent != null)&& (parentComponent.getClassName().equals(CLASS_NAME_DOCKABLE_TEMPLATE))) {
				if (component.getPosition().equals(TAG_ATT_POS_DOCK_MAIN)) {
					// The main component must be coming at the first (need to
					// fix that)
					addCodeLine = parentRef + ".setMainComponent("
							+ component.getID() + ")";
				} else {
					String place = "Side";
					if (component.getPosition().equals(TAG_ATT_POS_DOCK_DOWN)) {
						place = "Down";
					}
					String showTitle = "true";
					String showIcon = "true";
					if (component.getTitleType() != null) {
						if (component.getTitleType().equals(
								TAG_ATT_TITLE_TYPE_ICON)) {
							showTitle = "false";
						} else if (component.getTitleType().equals(
								TAG_ATT_TITLE_TYPE_TEXT)) {
							showIcon = "false";
						}
					}
					addCodeLine = parentRef
							+ ".addTo"
							+ place
							+ "("
							+ component.getID()
							+ ","
							+ getTranslate(component.getID(), component
									.getLabel())
							+ ","
							+ ApplicationGenerator
									.getImage(component.getIcon()) + ","
							+ showTitle + "," + showIcon + ")";
				}
			}
		} else if ((parentComponent != null)&& (parentComponent.getLayout() != null)&& (LAYOUT_ATT_SUPPORT_ADD.indexOf(parentComponent.getLayout()) != -1)) {
			//addCodeLine = parentRef + ".add(" + component.getID() + ")";
			addCodeLine = parentRef + ".add(\"" + component.getID() + "\"," + component.getID() + ")";
			
			
		} else {
			// Need to consider the frame addition
			if (!component.getID().equals(newClass.getName())) {
				/*
				addCodeLine = parentRef
						+ ".addComponent("
						+ component.getID()
						+ ","
						+ component.getColumn()
						+ ","
						+ component.getRow()
						+ ","
						+ (component.getWidth() == null ? "1" : component
								.getWidth())
						+ ","
						+ (component.getHeight() == null ? "1" : component
								.getHeight()) + ")";
								*/
				addCodeLine = parentRef	+ ".addComponent("+"\"" + component.getID() + "\""	+ ","	+ component.getID()	+ ","	+ component.getColumn()	+ ","+ component.getRow()+ ","+ (component.getWidth() == null ? "1" : component.getWidth())
				+ ","+ (component.getHeight() == null ? "1" : component.getHeight()) + ")";
				
			}
		}
		return addCodeLine;
	}

	private void processListenerLinkage(GUIComponent component) {
		if (isMenuObject(component)) {
			if (isSelfListener(component)) {
				newInterface.addImport(CLASS_NAME_FP_ACTION_HANDLER);
				((JavaInterface) newInterface)
						.addParentClass(CLASS_NAME_ACTION_HANDLER);
			}
		}
	}

	private boolean isSelfListener(GUIComponent component) {
		return ((TAG_ATT_LINK_LISTNR_WINDOW.equals(component
				.getListenerTarget())) || (TAG_ATT_LINK_LISTNR_PANEL
				.equals(component.getListenerTarget())));
	}

	private boolean isMenuObject(GUIComponent component) {
		return ((component.getClass().equals(CLASS_NAME_SMYLD_MNUBAR)) || (TAG_ATT_MENUBAR_TYPE_TREE
				.equals(component.getType())));
	}

	private void handelDockableMainComponent() {
		for (JavaVariable curDockable : dockables) {
			String dockabelComp = extractDockabelCompID(curDockable.getName());
			if (dockabelComp.equals(newClass.getName())) {
				dockabelComp = "this";
				mainComponent = curDockable.getName();
			}

			addLines.add(curDockable.getName() + ".setMainComp(" + dockabelComp
					+ ")");
		}
	}

	private String createDockabelID(String compID) {
		return compID + "_dock";
	}

	private String extractDockabelCompID(String compID) {
		return compID.substring(0, compID.indexOf("_dock"));
	}

	private void handleLabelPosition(GUIComponent curComponent) {
		if (isSMYLDLabeledComponent(curComponent)){
			String pos = curComponent.getLabelPosition();
			addLines.add(curComponent.getID() + ".setLabelPosition(\"" + pos
					+ "\")");
		}
	}

	private void handelDragables() {
		if ((dragables.size() > 0) && (dockables.size() > 0)) {
			newClass.addImport(CLASS_NAME_FP_GUI_UTIL);
			for (JavaVariable curDockable : dockables) {
				for (GUIComponent curDragable : dragables) {
					String dragStyle = "true";
					if (curDragable.getDragable().equals(
							TAG_ATT_DRAGABLE_INTERNAL)) {
						dragStyle = "false";
					}
					listeners.add(CLASS_NAME_GUI_UTIL + "."
							+ CLASS_GUIUtil_METH_SET_DRAGABLE_COMP + "("
							+ curDragable.getID() + "," + curDockable.getName()
							+ "," + dragStyle + ")");
				}
			}
		}
	}

	public static String getRef(String compID) {
		return APP_MAIN_CLASS_NAME + "." + APP_INSTANCE_PNL_FACTORY
				+ ".getPanel" + "(\"" + compID + "\"," + APP_MAIN_CLASS_NAME +".mainAppManager)";
	}

}
