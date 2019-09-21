package org.smyld.gui.portal.engine.gui.builders.swing;

import org.smyld.app.AppMenuFactory;
import org.smyld.app.pe.model.gui.GUIComponent;
import org.smyld.app.pe.model.gui.MenuItem;
import org.smyld.app.pe.model.gui.MenuType;
import org.smyld.gui.portal.engine.ApplicationGenerator;
import org.smyld.lang.script.java.JavaClassBody;
import org.smyld.lang.script.java.JavaMethod;
import org.smyld.lang.script.util.Variable;

import static org.smyld.app.AppConstants.ACTION_COM_ASSIGN_APP;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class SMYLDSwingMenuBuilder extends SMYLDSwingGUIBuilder {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JavaClassBody menuFactoryClass;
	JavaMethod   constructor;
	JavaMethod   menuBuildMethod;
	JavaMethod   treeBuildMethod;
	JavaMethod   popBuildMethod;
	JavaMethod   initMethod;
	JavaMethod   getMenuMethod;
	boolean      asTree;
	boolean      asBar;
	boolean      asPopup;
 
	/**
	 * 
	 * @see
	 * @since
	 */
	public SMYLDSwingMenuBuilder() {

	}

	public void buildNewClass() {
		menuFactoryClass = new JavaClassBody(MENU_FCTR_CLASS_NAME,
				activeApplication.getAppReader().getMainClassPackage(),
				CLASS_NAME_APP_MNU_FCT, true, false);
		constructor = new JavaMethod(MENU_FCTR_CLASS_NAME,
				Variable.SCOPE_PUBLIC, null, true);
		constructor.addParameter(APP_INSTANCE_ACT_FACTORY, ACT_FCTR_CLASS_NAME);
		initMethod = new JavaMethod("init",Variable.SCOPE_PRIVATE, null, false);

		menuFactoryClass.addImport(CLASS_NAME_FP_APP_MNU_FCT);
		menuFactoryClass.addImport(CLASS_NAME_FP_USER_CONSTRAINT);
		menuFactoryClass.addImport(CLASS_NAME_FP_SMYLD_MNUCLS);
		getMenuMethod = new JavaMethod(MENU_FCTR_METH_GET_MENU,
				Variable.SCOPE_PUBLIC, "Object");
		getMenuMethod.addParameter(MENU_FCTR_METH_PRM_MNU_METH, "String");
		getMenuMethod.addParameter(MENU_FCTR_METH_PARM_ACT_HDL,
				CLASS_NAME_ACTION_HANDLER);
		// JavaVariable actionFactory = new
		// JavaVariable(APP_INSTANCE_ACT_FACTORY,JavaMethod.SCOPE_PUBLIC,ACT_FCTR_CLASS_NAME);
		// menuFactoryClass.addVariable(actionFactory);
		constructor.addCodeLine("super(" + APP_INSTANCE_ACT_FACTORY + ")");
		constructor.addCodeLine("init()");
		// menuFactoryClass.addImport(CLASS_NAME_FP_SMYLD_MNUBAR);
	}

	public JavaClassBody getMenuFactoryClass() {
		menuFactoryClass.addConstructors(constructor);
		getMenuMethod.addCodeLine("return null");
		menuFactoryClass.addMethod(getMenuMethod);
		menuFactoryClass.addMethod(initMethod);
		return menuFactoryClass;
	}

	private void detectUsage(MenuItem menuBarItem) {
		int usage = menuBarItem.getUsage();
		asTree = getUsage(usage, MENU_AS_TREE);
		asBar = getUsage(usage, MENU_AS_BAR);
		asPopup = getUsage(usage, MENU_AS_POPUP);
	}

	private boolean getUsage(int usageValue, int usageFlag) {
		return (((usageValue & usageFlag) == usageFlag) || (usageValue == 0));
	}

	public void generatMenu(MenuItem menuBarItem) {
		// Code for generating a class that contains the menu
		detectUsage(menuBarItem);
		menuFactoryClass.addImport(CLASS_NAME_FP_SMYLD_MNUBAR);
		menuFactoryClass.addImport(CLASS_NAME_FP_SMYLD_TREE);
		menuFactoryClass.addImport(CLASS_NAME_FP_SMYLD_TREE_NODE);
		menuFactoryClass.addImport(CLASS_NAME_FP_ACTION_HANDLER);
		menuFactoryClass.addImport(CLASS_NAME_FP_SMYLD_POPUP_MENU);
		menuFactoryClass.addImport(CLASS_NAME_FP_SMYLD_MNU);
		menuFactoryClass.addImport(CLASS_NAME_FP_SMYLD_MNUITM);
		menuBuildMethod = new JavaMethod(AppMenuFactory
				.createMethodNameAsBar(menuBarItem.getID()),
				Variable.SCOPE_PUBLIC, CLASS_NAME_SMYLD_MNUBAR);
		treeBuildMethod = new JavaMethod(AppMenuFactory
				.createMethodNameAsTree(menuBarItem.getID()),
				Variable.SCOPE_PUBLIC, CLASS_NAME_SMYLD_TREE);
		popBuildMethod = new JavaMethod(AppMenuFactory
				.createMethodNameAsPopup(menuBarItem.getID()),
				Variable.SCOPE_PUBLIC, CLASS_NAME_SMYLD_POPUP_MENU);
		menuBuildMethod.addParameter(MENU_FCTR_METH_PARM_ACT_HDL,
				CLASS_NAME_ACTION_HANDLER);
		treeBuildMethod.addParameter(MENU_FCTR_METH_PARM_ACT_HDL,
				CLASS_NAME_ACTION_HANDLER);
		popBuildMethod.addParameter(MENU_FCTR_METH_PARM_ACT_HDL,
				CLASS_NAME_ACTION_HANDLER);
		String menuVariable = "bar" + menuBarItem.getID();
		addActionHandlerNullable(menuBuildMethod);
		addActionHandlerNullable(treeBuildMethod);
		addActionHandlerNullable(popBuildMethod);

		menuBuildMethod.addCodeLine(CLASS_NAME_SMYLD_MNUBAR + " " + menuVariable
				+ " = new " + CLASS_NAME_SMYLD_MNUBAR + "("
				+ MENU_FCTR_INSTANCE_ACTION_HDL + ")");
		popBuildMethod.addCodeLine(CLASS_NAME_SMYLD_POPUP_MENU + " "
				+ menuVariable + " = new " + CLASS_NAME_SMYLD_POPUP_MENU + "()");
		treeBuildMethod.addCodeLine(CLASS_NAME_SMYLD_TREE_NODE + " "
				+ menuVariable + " = new " + CLASS_NAME_SMYLD_TREE_NODE
				+ "(\"rootID\",\"root\")");

		// COMPONENT ORIENTATION
		menuBuildMethod.addCodeLine(createApplyCompOrientation(menuVariable));
		popBuildMethod.addCodeLine(createApplyCompOrientation(menuVariable));
		
		for (GUIComponent curGUIItem : menuBarItem.getChildren()) {
			MenuItem curItem = (MenuItem) curGUIItem;
			buildSingleMenu(menuVariable, curItem,0);
		}
		String targetTree = "targetTree";
		treeBuildMethod.addCodeLine(CLASS_NAME_SMYLD_TREE + " " + targetTree
				+ " = new " + CLASS_NAME_SMYLD_TREE + "(" + menuVariable + ","
				+ MENU_FCTR_METH_PARM_ACT_HDL + ")");
		treeBuildMethod.addCodeLine(createApplyCompOrientation(targetTree));
		menuBuildMethod.addCodeLine("return " + menuVariable);
		popBuildMethod.addCodeLine("return " + menuVariable);
		treeBuildMethod.addCodeLine("return " + targetTree);
		// I have commented this since some menus could be addressed by the
		// processing part and has no usage by other GUI parts
		/*
		 * addMenuMethod(menuBuildMethod,asBar);
		 * addMenuMethod(treeBuildMethod,asTree);
		 * addMenuMethod(popBuildMethod,asPopup);
		 */
		addMenuMethod(menuBuildMethod, true);
		addMenuMethod(treeBuildMethod, true);
		addMenuMethod(popBuildMethod, true);

	}

	private void addMenuMethod(JavaMethod mnuMethod, boolean menuFlag) {
		if (menuFlag) {
			addGetMenuLine(mnuMethod.getName());
			menuFactoryClass.addMethod(mnuMethod);
		}
	}

	private void addActionHandlerNullable(JavaMethod targetMethode) {
		targetMethode.addCodeLine("if (" + MENU_FCTR_METH_PARM_ACT_HDL
				+ "==null){");
		targetMethode.addCodeLine(MENU_FCTR_METH_PARM_ACT_HDL + " = "
				+ MENU_FCTR_INSTANCE_ACTION_HDL);
		targetMethode.addCodeLine("}");
	}

	private void addGetMenuLine(String methodName) {
		getMenuMethod.addCodeLine("if (" + MENU_FCTR_METH_PRM_MNU_METH
				+ ".equals(\"" + methodName + "\")) return " + methodName + "("
				+ MENU_FCTR_METH_PARM_ACT_HDL + ")");
	}

	public static String getRefAsBar(String ID, String actionHandler) {
		return getRef(ID, actionHandler, AppMenuFactory
				.createMethodNameAsBar(ID));
	}

	public static String getRefAsTree(String ID, String actionHandler) {
		return getRef(ID, actionHandler, AppMenuFactory
				.createMethodNameAsTree(ID));
	}

	public static String getRefAsPopup(String ID, String actionHandler) {
		return getRef(ID, actionHandler, AppMenuFactory
				.createMethodNameAsPopup(ID));
	}

	private static String getRef(String ID, String actionHandler,
			String methodName) {
		return APP_MAIN_CLASS_NAME + "." + APP_INSTANCE_MNU_FACTORY + "."
				+ methodName + "(" + actionHandler + ")";
	}

	public static String createMethodName(MenuItem menuItem) {
		StringBuffer methodeName = new StringBuffer();
		methodeName.append("generate");
		methodeName.append(menuItem.getID());
		methodeName.append("As");
		methodeName.append(getMethodeType(menuItem.getType()));
		return methodeName.toString();
	}

	private static String getMethodeType(String type) {
		if ((type != null) && (type.equals(TAG_ATT_MENUBAR_TYPE_TREE))) {
			return MENUBAR_TYPE_METHOD_SUFIX_TREE;
		}
		return MENUBAR_TYPE_METHOD_SUFIX_CLASSIC;
	}

	public static String createMethodNameAsBar(String menuID, String type) {
		return "generate" + menuID + "AsBar";
	}

	@SuppressWarnings("unused")
	private boolean containsSeparator(MenuItem menuItem) {
		for (GUIComponent curGUIItem : menuItem.getChildren()) {
			MenuItem curItem = (MenuItem) curGUIItem;
			if (curItem.getMenuType()== MenuType.SEPARATOR)
				return true;
			
		}
		return false;
	}

	private void buildSingleMenu(String parentMenuItem, MenuItem curItem,int level) {
		String menuClassName = CLASS_NAME_SMYLD_MNUITM;
		if (curItem.hasChildren()) {
			menuClassName = CLASS_NAME_SMYLD_MNU;
		}
		switch(curItem.getMenuType()){
			case SEPARATOR:
				menuBuildMethod.addCodeLine("if(" + parentMenuItem + "!=null){");
				if (level==0){
					menuBuildMethod.addCodeLine("((SMYLDMenuBar)" + parentMenuItem + ").addSeparator()");
				}else{
					menuBuildMethod.addCodeLine("((SMYLDMenu)" + parentMenuItem + ").addSeparator()");
				}
				menuBuildMethod.addCodeLine("}");
				popBuildMethod.addCodeLine("if(" + parentMenuItem + "!=null){");
				popBuildMethod.addCodeLine("((SMYLDPopupMenu)" + parentMenuItem + ").addSeparator()");
				popBuildMethod.addCodeLine("}");
				break;
			default:
				//if (curItem.getAction() != null) {
					if (curItem.getUserConstraint()!=null)
						initMethod.addCodeLine(createAddConstraintCodeLine(curItem.getUserConstraint()));
					
					String initCodeLine =  CLASS_NAME_SMYLD_MNUCLS + " " + curItem.getID()+  " = createMenuItem(\""+ menuClassName +"\"," + (curItem.getAction()!=null?"\"" + curItem.getAction().getID()+"\"":"null") +
					",\"" + curItem.getID() + "\",\"" + curItem.getIcon() + "\"," + getTextValue(curItem.getLabel()) + "," + 
					MENU_FCTR_METH_PARM_ACT_HDL + ","+ getTextValue(curItem.getEnabled()) + "," + getTextValue(curItem.getTooltip()) 
					+ "," + getTextValue(curItem.getAccelerator()) + ")";
					
					menuBuildMethod.addCodeLine(initCodeLine);
					popBuildMethod.addCodeLine(initCodeLine);
					initCodeLine =  CLASS_NAME_SMYLD_MNUCLS + " " + curItem.getID()+  " = createMenuItem(\""+ CLASS_NAME_SMYLD_TREE_NODE +"\"," + (curItem.getAction()!=null?"\"" + curItem.getAction().getID()+"\"":"null") +
					",\"" + curItem.getID() + "\",\"" + curItem.getIcon() + "\"," + getTextValue(curItem.getLabel()) + "," + 
					MENU_FCTR_METH_PARM_ACT_HDL + ","+ getTextValue(curItem.getEnabled()) + "," + getTextValue(curItem.getTooltip()) 
					+ "," + getTextValue(curItem.getAccelerator()) + ")";

					
					treeBuildMethod.addCodeLine(initCodeLine);		
					/*		
					popBuildMethod.addCodeLine( "JComponent " + curItem.getID()+  " = createMenuItem(\""+ menuClassName +"\"," + (curItem.getAction()!=null?"\""+curItem.getAction().getID()+"\"":"null") +
							",\"" + curItem.getID() + "\",\"" + curItem.getIcon() + "\"," + getTextValue(curItem.getLabel()) + "," + 
							MENU_FCTR_METH_PARM_ACT_HDL + ","+ getTextValue(curItem.getEnabled()) + "," + getTextValue(curItem.getTooltip()) 
							+ "," + getTextValue(curItem.getAccelerator()) + ")");

					
					
					
					
					if (curItem.getAction() != null) {
						treeBuildMethod.addCodeLine(newItemCodeLine(curItem,CLASS_NAME_SMYLD_TREE_NODE));
						if ((curItem.getAction().getLabel() == null)) {
							String setLable = curItem.getID()
									+ ".setText("
									+ ApplicationGenerator.getTranslate(
											MODULE_NAME_MENUS, getTextValue(curItem
													.getID()), getTextValue(curItem
													.getLabel())) + ")";
	
							treeBuildMethod.addCodeLine(setLable);
						}
					} else {
						treeBuildMethod.addCodeLine(treeCodeLine(curItem));
					}
					*/
					// Need to eliminate this part
					/*
					menuBuildMethod.addCodeLine(menuCodeLine(menuClassName, curItem
							.getID(), curItem.getID()));
					
					popBuildMethod.addCodeLine(menuCodeLine(menuClassName, curItem
							.getID(), curItem.getID()));
							*/
					
					

				//}
				/*	
				if (curItem.getIcon() != null) {
					treeBuildMethod.addCodeLine(newItemSetIcon(curItem));
				}
				if ((curItem.getEnabled() != null)
						&& (curItem.getEnabled().equals(TAG_ATT_BOOLEAN_FLASE))) {
					treeBuildMethod.addCodeLine(curItem.getID()
							+ ".setEnabled(false)");
				}
				if (curItem.getTooltip() != null) {
					String toolTipCommand = getSetToolTipText(MODULE_NAME_MENUS,
							curItem.getID(), curItem.getTooltip());
					treeBuildMethod.addCodeLine(toolTipCommand);
				}
				if (curItem.getPopupMenu() != null) {
					addPopupMenu(curItem);
				}

				treeBuildMethod.addCodeLine(createApplyCompOrientation(curItem
						.getID()));
						*/
				if (curItem.getPopupMenu() != null) {
					addPopupMenu(curItem);
				}
				if (curItem.hasChildren()) {
					level++;
					for (GUIComponent curGUIComp : curItem.getChildren()) {
						MenuItem childItem = (MenuItem) curGUIComp;
						buildSingleMenu(curItem.getID(), childItem,level);
					}
					level--;
				}

				
			menuBuildMethod.addCodeLine("if (("+ curItem.getID() + "!=null)&&(" +parentMenuItem + "!=null)){");
			popBuildMethod.addCodeLine("if (("+ curItem.getID() + "!=null)&&(" +parentMenuItem + "!=null)){");
			treeBuildMethod.addCodeLine("if (("+ curItem.getID() + "!=null)&&(" +parentMenuItem + "!=null)){");
			
				if (level==0){
					menuBuildMethod.addCodeLine("((SMYLDMenuBar)" + parentMenuItem + ").add((" + menuClassName + ")"+ curItem.getID() + ")");
					popBuildMethod.addCodeLine("((SMYLDPopupMenu)" + parentMenuItem + ").add((" + menuClassName + ")" + curItem.getID() + ",\"" + curItem.getID() + "\")");
				}else{
					menuBuildMethod.addCodeLine("((SMYLDMenu)" + parentMenuItem + ").add((" + menuClassName + ")"+ curItem.getID() + ")");
					popBuildMethod.addCodeLine("((SMYLDMenu)" + parentMenuItem + ").add((" + menuClassName + ")" + curItem.getID() + ",\"" + curItem.getID() + "\")");
				}
				treeBuildMethod.addCodeLine("((" + CLASS_NAME_SMYLD_TREE_NODE + ")" + parentMenuItem + ").add("+ curItem.getID() + ")");

			menuBuildMethod.addCodeLine("}");
			popBuildMethod.addCodeLine("}");
			treeBuildMethod.addCodeLine("}");



				
		}
	}
	private void buildSingleMenu_old(String parentMenuItem, MenuItem curItem) {
		String menuClassName = CLASS_NAME_SMYLD_MNUITM;
		if (curItem.hasChildren()) {
			menuClassName = CLASS_NAME_SMYLD_MNU;
		}
		switch(curItem.getMenuType()){
			case SEPARATOR:
				menuBuildMethod.addCodeLine(parentMenuItem + ".addSeparator()");
				popBuildMethod.addCodeLine(parentMenuItem + ".addSeparator()");
				break;
			default:
				if (curItem.getAction() != null) {
					menuBuildMethod.addCodeLine(newItemCodeLine(curItem,
							menuClassName));
					treeBuildMethod.addCodeLine(newItemCodeLine(curItem,
							CLASS_NAME_SMYLD_TREE_NODE));
					popBuildMethod.addCodeLine(newItemCodeLine(curItem,
							menuClassName));
					if ((curItem.getAction().getLabel() == null)) {
						String setLable = curItem.getID()
								+ ".setText("
								+ ApplicationGenerator.getTranslate(
										MODULE_NAME_MENUS, getTextValue(curItem
												.getID()), getTextValue(curItem
												.getLabel())) + ")";
						menuBuildMethod.addCodeLine(setLable);
						treeBuildMethod.addCodeLine(setLable);
						popBuildMethod.addCodeLine(setLable);

					}
					if (curItem.getAction().getCommand().equals(
							ACTION_COM_ASSIGN_APP)) {
						menuBuildMethod.addCodeLine(curItem.getID()
								+ ".setActionListener("
								+ MENU_FCTR_METH_PARM_ACT_HDL + ")");
						popBuildMethod.addCodeLine(curItem.getID()
								+ ".setActionListener("
								+ MENU_FCTR_METH_PARM_ACT_HDL + ")");
					} else {
						menuBuildMethod.addCodeLine(curItem.getID()
								+ ".setActionListener("
								+ MENU_FCTR_INSTANCE_ACTION_HDL + ")");
						popBuildMethod.addCodeLine(curItem.getID()
								+ ".setActionListener("
								+ MENU_FCTR_INSTANCE_ACTION_HDL + ")");
					}
				} else {
					menuBuildMethod.addCodeLine(menuCodeLine(menuClassName, curItem
							.getID(), curItem.getID()));
					popBuildMethod.addCodeLine(menuCodeLine(menuClassName, curItem
							.getID(), curItem.getID()));
					treeBuildMethod.addCodeLine(treeCodeLine(curItem));

				}
				if (curItem.getIcon() != null) {
					menuBuildMethod.addCodeLine(newItemSetIcon(curItem));
					treeBuildMethod.addCodeLine(newItemSetIcon(curItem));
					popBuildMethod.addCodeLine(newItemSetIcon(curItem));
				}
				if ((curItem.getEnabled() != null)
						&& (curItem.getEnabled().equals(TAG_ATT_BOOLEAN_FLASE))) {
					menuBuildMethod.addCodeLine(curItem.getID()
							+ ".setEnabled(false)");
					treeBuildMethod.addCodeLine(curItem.getID()
							+ ".setEnabled(false)");
					popBuildMethod.addCodeLine(curItem.getID()
							+ ".setEnabled(false)");
				}
				if (curItem.getTooltip() != null) {
					String toolTipCommand = getSetToolTipText(MODULE_NAME_MENUS,
							curItem.getID(), curItem.getTooltip());
					menuBuildMethod.addCodeLine(toolTipCommand);
					treeBuildMethod.addCodeLine(toolTipCommand);
					popBuildMethod.addCodeLine(toolTipCommand);
				}
				if (curItem.getAccelerator() != null) {
					menuBuildMethod.addCodeLine(curItem.getID()
							+ ".setAccelerator(\"" + curItem.getAccelerator()
							+ "\")");
					popBuildMethod.addCodeLine(curItem.getID()
							+ ".setAccelerator(\"" + curItem.getAccelerator()
							+ "\")");
				}
				if (curItem.getPopupMenu() != null) {
					addPopupMenu(curItem);
				}

				menuBuildMethod.addCodeLine(createApplyCompOrientation(curItem
						.getID()));
				treeBuildMethod.addCodeLine(createApplyCompOrientation(curItem
						.getID()));
				popBuildMethod.addCodeLine(createApplyCompOrientation(curItem
						.getID()));
				if (curItem.hasChildren()) {
					
					for (GUIComponent curGUIComp : curItem.getChildren()) {
						MenuItem childItem = (MenuItem) curGUIComp;
						//buildSingleMenu(curItem.getID(), childItem);
					}
				}

				menuBuildMethod.addCodeLine(parentMenuItem + ".add("
						+ curItem.getID() + ")");
				treeBuildMethod.addCodeLine(parentMenuItem + ".add("
						+ curItem.getID() + ")");
				popBuildMethod.addCodeLine(parentMenuItem + ".add("
						+ curItem.getID() + ",\"" + curItem.getID() + "\")");

				
		}
	}

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

	private String treeCodeLine(MenuItem instance) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(CLASS_NAME_SMYLD_TREE_NODE + " " + instance.getID()
				+ " = new " + CLASS_NAME_SMYLD_TREE_NODE + "(");
		buffer.append("\"" + instance.getID() + "\",");
		buffer.append(ApplicationGenerator.getTranslate(MODULE_NAME_MENUS, "\""
				+ instance.getID() + "\"", "\"" + instance.getLabel() + "\""));
		buffer.append(")");
		return buffer.toString();
	}

	private void addPopupMenu(MenuItem curItem) {
		menuFactoryClass.addImport(CLASS_NAME_FP_SMYLD_POPUP_MENU);
		treeBuildMethod.addCodeLine(curItem.getID()
				+ ".setPopupMenu("
				+ getRefAsPopup(curItem.getPopupMenu(),
						MENU_FCTR_METH_PARM_ACT_HDL) + ")");

	}

	private String newItemSetIcon(MenuItem instance) {
		return instance.getID() + ".setIcon("
				+ ApplicationGenerator.getImage(instance.getIcon()) + ")";
	}

	private String newItemCodeLine(MenuItem instance, String className) {
		StringBuilder buffer = new StringBuilder();
		buffer.append(className + " " + instance.getID() + " = new " + className + "(");
		buffer.append(ACT_FCT_INSTANCE + ".getAction(\"" + instance.getAction().getID() + "\"),");
		buffer.append("\"" + instance.getID() + "\")");
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

}
