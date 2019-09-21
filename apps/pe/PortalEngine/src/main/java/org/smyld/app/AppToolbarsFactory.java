package org.smyld.app;

import java.util.HashMap;

import org.smyld.gui.SMYLDButton;
import org.smyld.gui.SMYLDCheckBox;
import org.smyld.gui.SMYLDComboBox;
import org.smyld.gui.SMYLDLabel;
import org.smyld.gui.SMYLDToolbar;
import org.smyld.app.pe.model.gui.PEAction;

public class AppToolbarsFactory extends AppBaseFactory {
	/**
	 * 
	 */
	private static final long serialVersionUID = -195820656279125837L;
	protected AppActionFactory appActionsFactory;
	HashMap<String, SMYLDToolbar> toolbars;

	public AppToolbarsFactory() {
	}

	public AppToolbarsFactory(AppActionFactory actionFactory) {
		this.appActionsFactory = actionFactory;
	}

	public SMYLDToolbar getToolbar(String toolbarID) {
		if ((toolbars != null) && (toolbars.size() > 0)) {
			return toolbars.get(toolbarID);
		}
		return null;
	}
	protected SMYLDComboBox createCombo(String combID,Object[] vals){
		SMYLDComboBox comb  = null;
		if (hasConstraint(combID)){
			if (canView(combID)){
				comb  = new SMYLDComboBox ();
				comb.setEnabled(false);
			}else if (canUse(combID)){
				comb  = new SMYLDComboBox ();
			}
		}else{
			comb  = new SMYLDComboBox ();
		}
		if ((comb!=null)&&(vals!=null)){
			for(Object curVal:vals)comb.addItem(curVal);
		}
		return comb;
	}

	
	protected SMYLDLabel createLabel(String lblID,String icon,String defString){
		SMYLDLabel lblProd  = null;
		if (hasConstraint(lblID)){
			if (canView(lblID)){
				lblProd  = new SMYLDLabel (" ");
				lblProd.setEnabled(false);
			}else if (canUse(lblID)){
				lblProd  = new SMYLDLabel (" ");
			}
		}else{
			lblProd  = new SMYLDLabel (" ");
		}
		if (lblProd!=null){
			if (icon!=null)
				lblProd.setIcon(AppMainClass.getImage(icon));
			lblProd.setText(AppMainClass.translate("menus",lblID,defString));
		}
		return lblProd;
	}
	protected SMYLDCheckBox createCheckBox(String lblID,String icon,String defString){
		SMYLDCheckBox chkProd  = null;
		if (hasConstraint(lblID)){
			if (canView(lblID)){
				chkProd  = new SMYLDCheckBox (" ");
				chkProd.setEnabled(false);
			}else if (canUse(lblID)){
				chkProd  = new SMYLDCheckBox (" ");
			}
		}else{
			chkProd  = new SMYLDCheckBox (" ");
		}
		if (chkProd!=null){
			if (icon!=null)
				chkProd.setIcon(AppMainClass.getImage(icon));
			chkProd.setText(AppMainClass.translate("menus",lblID,defString));
		}
		// TODO we need to revise the structure in order to allow the user to add the actions he needs based on the state change
		/*
		if (chkProd!=null){
			chkProd.addChangeListener(new ChangeListener(){

				public void stateChanged(ChangeEvent e) {
					appActionsFactory.getHandler().
					
				}
				
			});
		}
		*/
		return chkProd;
	}

	protected SMYLDButton createButton(String actionID,String buttonID,String icon,String defString){
		SMYLDButton newButton = null;
		// Processing the action part
		if (actionID!=null){
			PEAction cmdAction = appActionsFactory.getAction(actionID);
			if (appActionsFactory.hasConstraint(actionID)){
				if (appActionsFactory.canUse(actionID)){
					newButton = new SMYLDButton(cmdAction,buttonID);
				}
				else if (appActionsFactory.canView(actionID)){
					newButton = new SMYLDButton(cmdAction,buttonID);
					newButton.setEnabled(false);
				}
			}
			else {
				newButton = new SMYLDButton(cmdAction,buttonID);
			}
		}else{
			newButton = new SMYLDButton (buttonID);
		}
		//Processing the toolbar constraint part
		if (newButton!=null){
			if (hasConstraint(buttonID)){
				if (canUse(buttonID)){
					// Nothing to do here
				}else if (canView(buttonID)){
					newButton.setEnabled(false);
				}else{
					newButton = null;
				}
			}
			
		}
		if (newButton !=null){
			newButton.setText(AppMainClass.translate("menus",buttonID,defString));
			newButton.setActionListener(appActionsFactory.getHandler());
			if (icon!=null)
				newButton.setIcon(AppMainClass.getImage(icon));
			newButton.setTextDown();
		}
		return newButton;
	}
	
}
