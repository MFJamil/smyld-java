package com.smyld.app.pe.model.gui;

import com.smyld.gui.GUIAction;
import com.smyld.app.pe.model.user.UserConstraint;

public class PEAction extends GUIAction {
	UserConstraint userConstraint;
	public PEAction(){
		
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
	
}
