package org.smyld.app;

import static org.smyld.gui.GUIConstants.CLASS_NAME_SMYLD_MNU;
import static org.smyld.gui.GUIConstants.CLASS_NAME_SMYLD_MNUITM;
import static org.smyld.gui.GUIConstants.CLASS_NAME_SMYLD_TREE_NODE;

import static org.smyld.app.pe.model.Constants.*;

import org.smyld.gui.SMYLDMenu;
import org.smyld.gui.SMYLDMenuClass;
import org.smyld.gui.SMYLDMenuItem;
import org.smyld.gui.event.ActionHandler;
import org.smyld.app.pe.model.gui.PEAction;
import org.smyld.gui.tree.SMYLDTreeNode;

public class AppMenuFactory extends AppBaseFactory implements AppConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected AppActionFactory appActionsFactory;

	public AppMenuFactory() {

	}

	public AppMenuFactory(AppActionFactory actionFactory) {
		this.appActionsFactory = actionFactory;
	}

	public Object getMenu(String menuMethName, ActionHandler actionHandler) {
		return null;
	}

	public static String createMethodNameAsBar(String menuID) {
		return "generate" + menuID + "AsBar";
	}

	public static String createMethodNameAsTree(String menuID) {
		return "generate" + menuID + "AsTree";
	}

	public static String createMethodNameAsPopup(String menuID) {
		return "generate" + menuID + "AsPopup";
	}

	public static String createMethodName(String menuID, String menuType) {
		if (menuType.equals(MENU_TYPE_TREE)) {
			return createMethodNameAsTree(menuID);
		}
		return createMethodNameAsBar(menuID);
	}
	/*
	protected SMYLDMenuItem createMenuItem(String actionID,String menutID,String icon,String defaultValue){
		SMYLDMenuItem newMenuItem = new SMYLDMenuItem (appActionsFactory.getAction(actionID),menutID);
		newMenuItem.setText(AppMainClass.translate("menus",menutID,defaultValue));
		//newMenuItem.setActionListener(srcActionHandler);
		if (icon!=null)
			newMenuItem.setIcon(AppMainClass.getImage(icon));
		newMenuItem.applyComponentOrientation(AppMainClass.getOrientation());
		return newMenuItem;
		
	}  
	*/
	protected SMYLDMenuClass createMenuItem(
			String menuClass, 
			String actionID,
			String menuID,
			String icon,
			String defValue,
			ActionHandler srcActionHandler,
			String isEnabled,
			String tooltip,
			String accelerator){
		SMYLDMenuClass newMenuItem = null;
		// Processing the action part
		PEAction cmdAction = null;

		if (actionID!=null){
			cmdAction = appActionsFactory.getAction(actionID);
			if (appActionsFactory.hasConstraint(actionID)){
				if (appActionsFactory.canUse(actionID)){
					newMenuItem = generateMenuItem(menuClass,cmdAction,menuID);
					
				}
				else if (appActionsFactory.canView(actionID)){
					newMenuItem = generateMenuItem(menuClass,cmdAction,menuID);
					newMenuItem.setEnabled(false);
				}
			}
			else {
				newMenuItem = generateMenuItem(menuClass,cmdAction,menuID);
			}
		}else{
			newMenuItem = generateMenuItem(menuClass,null,menuID);
		}
		//Processing the toolbar constraint part
		if (newMenuItem!=null){
			if (hasConstraint(menuID)){
				if (canUse(menuID)){
					// Nothing to do here
				}else if (canView(menuID)){
					newMenuItem.setEnabled(false);
				}else{
					newMenuItem = null;
				}
			}
			
		}
		if (newMenuItem !=null){
			if ((cmdAction!=null)&&(cmdAction.getLabel()!=null)&&(!cmdAction.getLabel().isEmpty())){
				newMenuItem.setText(AppMainClass.translate(MODULE_NAME_ACTIONS,cmdAction.getID(),cmdAction.getLabel()));
				//System.out.println("Translating actions for id (" + menuID + ") with default value of ("+ defValue + ") resulted in ("+ AppMainClass.translate(MODULE_NAME_MENUS,menuID,defValue) + ")");
			}
			if ((newMenuItem.getText()==null)||(newMenuItem.getText().isEmpty()))
				newMenuItem.setText(AppMainClass.translate(MODULE_NAME_MENUS,menuID,defValue));
				//System.out.println("Translating menus for id (" + menuID + ") with default value of ("+ defValue + ") resulted in ("+ AppMainClass.translate(MODULE_NAME_MENUS,menuID,defValue) + ")");
			
			if (cmdAction!=null){
				if (cmdAction.getCommand().equals(ACTION_COM_ASSIGN_APP)){
					newMenuItem.setActionListener(srcActionHandler);
				}else{
					newMenuItem.setActionListener(appActionsFactory.getHandler());
				}
			}
			if (icon!=null)
				newMenuItem.setIcon(AppMainClass.getImage(icon));
			newMenuItem.applyComponentOrientation(AppMainClass.getOrientation());
			if ((isEnabled!=null)&&(isEnabled.equals(TAG_ATT_BOOLEAN_FLASE)))
				newMenuItem.setEnabled(false);
			if (tooltip!=null)
				newMenuItem.setTooltipText(tooltip);
			if (accelerator!=null)
				newMenuItem.setAccelerator(accelerator);
			
			
		}
		return newMenuItem;
	}
	private SMYLDMenuClass generateMenuItem(String className,PEAction action,String id ){
		SMYLDMenuClass result = null;
		if (className!=null){
			if (className.equals(CLASS_NAME_SMYLD_MNUITM)){
				if (action!=null)
					result = new SMYLDMenuItem(action,id);
				else 
					result = new SMYLDMenuItem("",id);
			}else if (className.equals(CLASS_NAME_SMYLD_MNU)){
				if (action!=null)
					result = new SMYLDMenu(action,id);
				else 
					result = new SMYLDMenu("",id);
			}else if (className.equals(CLASS_NAME_SMYLD_TREE_NODE)){
				if (action!=null)
					result = new SMYLDTreeNode(action,id);
				else 
					result = new SMYLDTreeNode(id,"");

			}
		}
		return result;
	}
	
	/*


	 * */
	

}
