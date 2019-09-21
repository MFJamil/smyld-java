package org.smyld.gui;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.JToolBar;

import org.smyld.gui.event.ActionHandler;

public class SMYLDToolbar extends JToolBar {
	HashMap<String, SMYLDButton>   buttons = new HashMap<String, SMYLDButton>();
	HashMap<String, SMYLDComboBox> combos  = new HashMap<String, SMYLDComboBox>();
	HashMap<String, SMYLDCheckBox> checks  = new HashMap<String, SMYLDCheckBox>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMYLDToolbar() {

	}
	
	public void setActionListener(ActionHandler actionHanler) {
		for (SMYLDButton curButton : buttons.values()) {
			curButton.setActionListener(actionHanler);
		}
	}
	
	public void add(Component comp, Object constraints) {
		if ((comp instanceof SMYLDButton)&&(constraints instanceof String)){
			buttons.put((String)constraints, (SMYLDButton)comp);
		}else if ((comp instanceof SMYLDComboBox)&&(constraints instanceof String)){
			combos.put((String)constraints, (SMYLDComboBox)comp);
		}else if ((comp instanceof SMYLDCheckBox)&&(constraints instanceof String)){
			checks.put((String)constraints, (SMYLDCheckBox)comp);

		}
		super.add(comp, constraints);
	}
	public SMYLDButton getButton(String id){
		return buttons.get(id);
	}
	public SMYLDComboBox getComboBox(String id){
		return combos.get(id);
		
	}
	public SMYLDCheckBox getCheckBox(String id){
		return checks.get(id);
		
	}

	
}
