package org.smyld.gui.portal.engine;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.smyld.app.UserManager;
import org.smyld.app.pe.model.user.UserConstraint;
import org.smyld.gui.SMYLDLabeledTextArea;
import org.smyld.gui.SMYLDLabeledTextField;
import org.smyld.gui.SMYLDPanel;
import org.smyld.gui.SMYLDTabbedPane;
import org.smyld.gui.SMYLDTextArea;
import org.smyld.gui.SMYLDTextField;
import org.smyld.gui.layout.SMYLDRCConstraints;

public class PEPanel extends SMYLDPanel {
	// SMYLDRCLayout layoutManager = new SMYLDRCLayout();
	protected UserManager userManager = new UserManager();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PEPanel() {
	}
	
	public PEPanel(UserManager userManager) {
		this.userManager = userManager;
	}
	
	
	protected void addConstraint(UserConstraint newConst){
		userManager.addConstraint(newConst);
	}
	public void add(String compID,Component comp, Object constraints){
		addComponent(compID, comp, constraints);
	}
	public Component add(String compID,Component comp){
		if (doAdd(compID, comp))
			return super.add(compID,comp);
		return comp;
	}

	public void addComponent(String compID,Component comp, Object constraints){
		if (doAdd(compID, comp)){
			super.add(comp,constraints);
		}
	}
	protected boolean doAdd(String compID,Component comp){
		if (userManager.hasConstraint(compID)){
			if (userManager.canUse(compID)){
				return true;
			}else if (userManager.canView(compID)){
				setComponentToView(comp);
				return true;
			}
		}else{
			return true;
		}
		return false;
	}
	private void setComponentToView(Component comp){
		if (comp instanceof SMYLDLabeledTextField){
			((SMYLDLabeledTextField)comp).getSMYLDTextField().setEditable(false);
		}else if (comp instanceof SMYLDLabeledTextArea){
			((SMYLDLabeledTextArea)comp).getTextArea().setEditable(false);
		}else if (comp instanceof SMYLDTextField){
			((SMYLDTextField)comp).setEditable(false);
		}else if (comp instanceof SMYLDTextArea){
			((SMYLDTextArea)comp).setEditable(false);
		}else if (comp instanceof JTextArea){
			((JTextArea)comp).setEditable(false);
		}else if (comp instanceof JTextField){
			((JTextField)comp).setEditable(false);

		}else{
			comp.setEnabled(false);
		}
			
		
		
	}
	protected boolean doDisable(String compID,Component comp){
		return ((userManager.hasConstraint(compID))&&(userManager.canView(compID))&&(!userManager.canUse(compID)));
	}

	public void addComponent(Component comp, int column, int row, int width,
			int height) {
			super.add(comp, new SMYLDRCConstraints(column, row, width, height));
	}
	
	public void addComponent(String compID,Component comp, int column, int row, int width,
			int height) {
		if (doAdd(compID, comp)){
			addComponent(comp,column, row, width, height);
		}
	}
	
	protected void addTab(SMYLDTabbedPane tabbedPane,String title, Icon icon, Component comp,String compID){
		if (doAdd(compID, comp)){
			int tcount = tabbedPane.getTabCount();
			tabbedPane.addTab(title, icon,comp);
			if (doDisable(compID, comp)) tabbedPane.setEnabledAt(tcount, false); 			
		}
	}
	protected void addTab(SMYLDTabbedPane tabbedPane,String title, Component comp,String compID){
		if (doAdd(compID, comp)){
			int tcount = tabbedPane.getTabCount();
			tabbedPane.addTab(title, comp);
			if (doDisable(compID, comp)) tabbedPane.setEnabledAt(tcount, false);
		}
	}
	
	
	
	

}
