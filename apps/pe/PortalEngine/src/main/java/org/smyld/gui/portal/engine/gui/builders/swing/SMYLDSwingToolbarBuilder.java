package org.smyld.gui.portal.engine.gui.builders.swing;

import org.smyld.app.pe.model.gui.*;
import org.smyld.gui.GUIConstants;
import org.smyld.gui.portal.engine.ApplicationGenerator;
import org.smyld.lang.script.java.JavaClassBody;
import org.smyld.lang.script.java.JavaMethod;
import org.smyld.lang.script.util.Variable;
import org.smyld.text.TextUtil;

import static org.smyld.app.AppConstants.ACTION_COM_ASSIGN_APP;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class SMYLDSwingToolbarBuilder extends SMYLDSwingGUIBuilder {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JavaClassBody tlbFactoryClass;
	JavaMethod constructor;
	JavaMethod buildToolbars;
	String tlbsVariable;
	String toolBarVariable;

	/**
	 * 
	 * @see
	 * @since
	 */
	public SMYLDSwingToolbarBuilder() {

	}

	public void buildNewClass() {
		tlbFactoryClass = new JavaClassBody(TLB_FCTR_CLASS_NAME,
				activeApplication.getAppReader().getMainClassPackage(),
				CLASS_NAME_APP_TLB_FCT, true, false);
		constructor = new JavaMethod(TLB_FCTR_CLASS_NAME,
				Variable.SCOPE_PUBLIC, null, true);
		constructor.addParameter(APP_INSTANCE_ACT_FACTORY,
				CLASS_NAME_APP_ACT_FCT);
		tlbFactoryClass.addImport(CLASS_NAME_FP_APP_ACT_FCT);
		tlbFactoryClass.addImport(CLASS_NAME_FP_APP_TLB_FCT);
		tlbFactoryClass.addImport(CLASS_NAME_FP_USER_CONSTRAINT);
		tlbFactoryClass.addImport(CLASS_NAME_FP_SMYLD_TOOLBAR);
		tlbFactoryClass.addImport(CLASS_NAME_FP_SMYLD_BUTN);
		tlbFactoryClass.addImport(CLASS_NAME_FP_ACTION_HANDLER);
		tlbFactoryClass.addImport(CLASS_NAME_FP_PE_ACTION);

		constructor.addCodeLine("super(" + APP_INSTANCE_ACT_FACTORY + ")");
		// buildToolbars.addParameter(tlbsVariable,"");
	}

	public JavaClassBody getToolbarFactoryClass() {
		tlbFactoryClass.addConstructors(constructor);
		return tlbFactoryClass;
	}

	public void generateToolbarBar(GUIToolbar toolBarItem) {
		// Code for generating a class that contains the menu
		JavaMethod tlbMeth = new JavaMethod(createMethodNameToolBar(toolBarItem
				.getID()), Variable.SCOPE_PUBLIC, CLASS_NAME_SMYLD_TOOLBAR);
		tlbMeth.addParameter(TLB_FCTR_METH_PARM_ACT_HDL,
				CLASS_NAME_ACTION_HANDLER);
		toolBarVariable = "tlb" + toolBarItem.getID();
		tlbMeth.addCodeLine("if (" + TLB_FCTR_METH_PARM_ACT_HDL + "==null){");
		tlbMeth.addCodeLine(TLB_FCTR_METH_PARM_ACT_HDL + " = "
				+ TLB_FCTR_METH_PARM_ACT_HDL);
		tlbMeth.addCodeLine("}");
		tlbMeth.addCodeLine(CLASS_NAME_SMYLD_TOOLBAR + " " + toolBarVariable
				+ " = new " + GUIConstants.CLASS_NAME_SMYLD_TOOLBAR + "()");
		// COMPONENT ORIENTATION
		tlbMeth.addCodeLine(toolBarVariable + "."
				+ SWING_METH_APPLY_COMP_ORIENT + "(" + APP_MAIN_CLASS_NAME
				+ "." + APP_METH_GET_APP_ORIENT + "())");

		for (GUIComponent curGUIComp : toolBarItem.getChildren()) {
			if (curGUIComp.getUserConstraint()!=null) 
				tlbMeth.addCodeLine(createAddConstraintCodeLine(curGUIComp.getUserConstraint()));
			ActionHolderItem curItem = (ActionHolderItem) curGUIComp;
			// buildSingleMenuBar(curItem,newMenuMethod);
			tlbFactoryClass.addImport(GUIConstants.CLASS_NAME_FP_SMYLD_BUTN);
			MenuType curMType = MenuType.parse(curItem.getType());
			switch(curMType){
				case ITEM:
					buildToolbarButton(curItem.getID(), curItem, tlbMeth);
					break;
				case SEPARATOR:
					tlbMeth.addCodeLine(toolBarVariable + ".addSeparator()");
					break;
				case COMBO:
					//System.out.println("Hi there this is the combo supplied");
					buildToolbarCombo(curItem.getID(), curItem, tlbMeth);
					break;
				case LABEL:
					//System.out.println("Hi there this is the combo supplied");
					buildToolbarLabel(curItem.getID(), curItem, tlbMeth);
					break;
				case CHECK:
					buildToolbarCheckBox(curItem.getID(), curItem, tlbMeth);
					break;
					
		
					
			}
			// COMPONENT ORIENTATION
			//System.out.println("Working on item : " + curItem.getID());
			if (curMType!=MenuType.SEPARATOR){
				tlbMeth.addCodeLine("if (" + curItem.getID() + "!=null){");
				tlbMeth.addCodeLine(curItem.getID() + "."
						+ SWING_METH_APPLY_COMP_ORIENT + "("
						+ APP_MAIN_CLASS_NAME + "." + APP_METH_GET_APP_ORIENT
						+ "())");
				tlbMeth.addCodeLine(toolBarVariable + ".add(" + curItem.getID()
						+ ",\"" + curItem.getID() + "\")");
				tlbMeth.addCodeLine("}");
			}
			

			// tlbMeth.addCodeLine(menuCodeLine(GUIConstants.CLASS_NAME_SMYLD_MNU,curItem.getID(),curItem.getID()));
			/*
			if ((curItem.getType() != null)
					&& (curItem.getType().equals(Integer
							.toString(MENU_TYPE_ITEM)))) {
				// COMPONENT ORIENTATION
				tlbMeth.addCodeLine(curItem.getID() + "."
						+ SWING_METH_APPLY_COMP_ORIENT + "("
						+ APP_MAIN_CLASS_NAME + "." + APP_METH_GET_APP_ORIENT
						+ "())");
				tlbMeth.addCodeLine(toolBarVariable + ".add(" + curItem.getID()
						+ ",\"" + curItem.getID() + "\")");
			}*/

		}
		tlbMeth.addCodeLine("return " + toolBarVariable);
		tlbFactoryClass.addMethod(tlbMeth);
	}

	public static String createMethodNameToolBar(String toolbarID) {
		return "generate" + toolbarID + "ToolBar";
	}
	
	private void buildToolbarCombo(String parentMenuItem, ActionHolderItem curItem, JavaMethod buildMethod) {
		tlbFactoryClass.addImport(CLASS_NAME_FP_SMYLD_COMBO);
		StringBuilder sb      = null;
		String        valsRef = "null";
		if ((curItem.getValues()!=null)&&(curItem.getValues().length>0)){
			sb = new StringBuilder();
			sb.append("Object[] ");
			valsRef = curItem.getID() + "_vals";
			sb.append(valsRef);
			sb.append(" = {");
			for(Object curValue:curItem.getValues()){
				sb.append("\"" +curValue.toString() + "\"");
				sb.append(',');
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("};");
		}
		if (sb!=null) buildMethod.addCodeLine(sb.toString()); 
		buildMethod.addCodeLine(CLASS_NAME_SMYLD_COMBO + " " + curItem.getID() + " = createCombo(\"" + curItem.getID() + "\"," + valsRef + ")");
				
		
	}


	private void buildToolbarCombo_old(String parentMenuItem, ActionHolderItem curItem, JavaMethod buildMethod) {
		tlbFactoryClass.addImport(CLASS_NAME_FP_SMYLD_COMBO);
		buildMethod.addCodeLine(CLASS_NAME_SMYLD_COMBO + " " + curItem.getID() + " = new "
				+ CLASS_NAME_SMYLD_COMBO + "()");
		if ((curItem.getValues()!=null)&&(curItem.getValues().length>0)){
			for(Object curValue:curItem.getValues()){
				buildMethod.addCodeLine(curItem.getID() + ".addItem(\"" +curValue.toString() + "\")");
			}
		}
		
	}
	private void buildToolbarLabel(String parentMenuItem, ActionHolderItem curItem, JavaMethod buildMethod) {
		tlbFactoryClass.addImport(CLASS_NAME_FP_SMYLD_LBL);
		buildMethod.addCodeLine(CLASS_NAME_SMYLD_LBL + " " + curItem.getID() + " = createLabel(\"" +curItem.getID() + "\",\"" + curItem.getIcon() + "\"," + (curItem.getLabel()!=null?"\"" + curItem.getLabel() + "\"":"\"\"") + ")");
		
	}
	private void buildToolbarCheckBox(String parentMenuItem, ActionHolderItem curItem, JavaMethod buildMethod) {
		tlbFactoryClass.addImport(CLASS_NAME_FP_SMYLD_CHKB);
		buildMethod.addCodeLine(CLASS_NAME_SMYLD_CHKB + " " + curItem.getID() + " = createCheckBox(\"" +curItem.getID() + "\",\"" + curItem.getIcon() + "\"," + (curItem.getLabel()!=null?"\"" + curItem.getLabel() + "\"":"\"\"") + ")");
		
	}

	private void buildToolbarLabel_old(String parentMenuItem, ActionHolderItem curItem, JavaMethod buildMethod) {
		tlbFactoryClass.addImport(CLASS_NAME_FP_SMYLD_LBL);
		buildMethod.addCodeLine(CLASS_NAME_SMYLD_LBL + " " + curItem.getID() + " = new "
				+ CLASS_NAME_SMYLD_LBL + "(\" \")");
		if (curItem.getIcon() != null) {
			buildMethod.addCodeLine(curItem.getID() + ".setIcon("
					+ ApplicationGenerator.getImage(curItem.getIcon())
					+ ")");
		}
		if (curItem.getLabel()!=null){
			buildMethod.addCodeLine(curItem.getID() + ".setText(" +ApplicationGenerator.getTranslate(MODULE_NAME_MENUS, "\""
					+ curItem.getID() + "\"", "\"" + curItem.getLabel() + "\"")+ ")");
 
		}
		
	}

	private void buildToolbarButton(String parentMenuItem, ActionHolderItem curItem, JavaMethod buildMethod) {
		
		buildMethod.addCodeLine(CLASS_NAME_SMYLD_BUTN + " " + curItem.getID() + " =  createButton(" + (curItem.getAction()!=null?"\"" + curItem.getAction().getID()+ "\"":"null") + 
				",\"" +curItem.getID() + "\",\"" + curItem.getIcon() + "\"," + (curItem.getLabel()!=null?"\"" + curItem.getLabel() + "\"":"\"\"") + ")" );
		if (curItem.getTooltip() != null) {
			buildMethod.addCodeLine("// Here must be a tooltip setter method !!!");
			String toolTipCommand = getSetToolTipText(MODULE_NAME_TLBS,	curItem.getID(), curItem.getTooltip());
			buildMethod.addCodeLine(toolTipCommand);
		}

	}
	
	private void buildToolbarButton_old(String parentMenuItem, ActionHolderItem curItem, JavaMethod buildMethod) {
		if (curItem.getAction() != null) {
			//buildMethod.addCodeLine(createToolbarButton(curItem,buildMethod));
			createToolbarButton(curItem,buildMethod);
			buildMethod.addCodeLine("if (" + curItem.getID() + " !=null){");
			if (TextUtil.isEmpty(curItem.getAction().getLabel())) {
				buildMethod.addCodeLine(curItem.getID() + ".setText("
						+ readLabel(curItem) + ")");
			}
			if (curItem.getAction().getCommand().equals(
					ACTION_COM_ASSIGN_APP)) {
				buildMethod.addCodeLine(curItem.getID()
						+ ".setActionListener("
						+ MENU_FCTR_METH_PARM_ACT_HDL + ")");
			} else {
				buildMethod.addCodeLine(curItem.getID()
						+ ".setActionListener("
						+ MENU_FCTR_INSTANCE_ACTION_HDL + ")");
			}
		} else {
			createToolbarButton(curItem,buildMethod);
			buildMethod.addCodeLine("if (" + curItem.getID() + " !=null){");
		}
		
			if (curItem.getIcon() != null) {
				buildMethod.addCodeLine(curItem.getID() + ".setIcon("
						+ ApplicationGenerator.getImage(curItem.getIcon())
						+ ")");
				buildMethod.addCodeLine(curItem.getID() + ".setTextDown()");
			}
			if ((curItem.getEnabled() != null)
					&& (curItem.getEnabled().equals(TAG_ATT_BOOLEAN_FLASE))) {
				buildMethod.addCodeLine(curItem.getID() + ".setEnabled(false)");
			}
			if (curItem.getTooltip() != null) {
				String toolTipCommand = getSetToolTipText(MODULE_NAME_TLBS,
						curItem.getID(), curItem.getTooltip());
				buildMethod.addCodeLine(toolTipCommand);
			}
			buildMethod.addCodeLine("}");
	}

	private void createToolbarButton(ActionHolderItem curItem, JavaMethod buildMethod) {
		String command = null;
		if (curItem.getAction() != null) {
			buildMethod.addCodeLine(CLASS_NAME_SMYLD_BUTN + " " + curItem.getID() + " = null"); 
			String actionRef = curItem.getID() + "_act"; 
			buildMethod.addCodeLine("PEAction "+ actionRef + " = " + ACT_FCT_INSTANCE + ".getAction(\"" + curItem.getAction().getID() + "\")");
			buildMethod.addCodeLine("if (" + ACT_FCT_INSTANCE + ".hasConstraint(\"" + curItem.getAction().getID() + "\")){");
				buildMethod.addCodeLine("if (" + ACT_FCT_INSTANCE + ".canView(\"" + curItem.getAction().getID() + "\")){");
					buildMethod.addCodeLine(curItem.getID() + " = new " + CLASS_NAME_SMYLD_BUTN + "(" + actionRef +  ",\""	+ curItem.getID() + "\")");
					buildMethod.addCodeLine(curItem.getID() + ".setEnabled(false)");

				buildMethod.addCodeLine("}");
				buildMethod.addCodeLine("else if (" + ACT_FCT_INSTANCE + ".canUse(\"" + curItem.getAction().getID() + "\")){");
					buildMethod.addCodeLine(curItem.getID() + " = new " + CLASS_NAME_SMYLD_BUTN + "(" + actionRef +  ",\""	+ curItem.getID() + "\")");
				buildMethod.addCodeLine("}");
			buildMethod.addCodeLine("}");	
			buildMethod.addCodeLine("else {"); 
				buildMethod.addCodeLine(curItem.getID() + " = new " + CLASS_NAME_SMYLD_BUTN + "(" + actionRef +",\""	+ curItem.getID() + "\")");
			buildMethod.addCodeLine("}");
			if (curItem.getTooltip() != null) {
				String toolTipCommand = getSetToolTipText(MODULE_NAME_TLBS,	curItem.getID(), curItem.getTooltip());
				buildMethod.addCodeLine(toolTipCommand);
			}
			

		} else if (curItem.getID() != null) {
			buildMethod.addCodeLine(CLASS_NAME_SMYLD_BUTN + " " + curItem.getID() + " = new "+ CLASS_NAME_SMYLD_BUTN + "(" + getTextValue(curItem.getID())
					+ "," + readLabel(curItem) + ")");
		}
		
	}

	private String readLabel(ActionHolderItem item) {
		String label = item.getLabel();
		if ((label == null) || (label.length() == 0)) {
			label = "";
		}
		return "\"" + label + "\"";

	}

	@SuppressWarnings("unused")
	private String menuCodeLine(String className, String instanceName,
			String instanceLable) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(className + " " + instanceName + " = new " + className
				+ "(");
		buffer.append(ApplicationGenerator.getTranslate(MODULE_NAME_MENUS, "\""
				+ instanceName + "\"", "\"" + instanceLable + "\""));
		buffer.append(",\"" + instanceName + "\")");
		return buffer.toString();
	}

	public void generateTreeMenu(MenuItem treeMenuItem) throws Exception {
		// Code for generating the menu as a tree menu style
		throw new Exception("Not implemented yet");
	}

	public void generateButtonsMenu(MenuItem treeMenuItem) throws Exception {
		// Code for generating the menu as a buttons menu style
		throw new Exception("Not implemented yet");
	}

	public static String getRef(String compID) {
		// TODO ( need to make the reference taking a value instead of null
		return APP_MAIN_CLASS_NAME + "." + APP_INSTANCE_TLB_FACTORY + "."
				+ createMethodNameToolBar(compID) + "(null)";
	}
	public static String getRef(String compID,String actionHandlerClass) {
		// TODO ( need to make the reference taking a value instead of null
		return APP_MAIN_CLASS_NAME + "." + APP_INSTANCE_TLB_FACTORY + "."
				+ createMethodNameToolBar(compID) + "(" + actionHandlerClass + ")";
	}

}
