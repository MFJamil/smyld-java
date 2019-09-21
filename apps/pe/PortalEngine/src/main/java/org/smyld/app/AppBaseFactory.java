package org.smyld.app;

import java.util.HashMap;

import org.smyld.SMYLDObject;
import org.smyld.app.pe.model.user.UserConstraint;

public class AppBaseFactory extends SMYLDObject{
	protected UserManager userManager = new UserManager();
	protected HashMap<String,UserConstraint> constraints = new HashMap<String, UserConstraint>();

	public void addConstraint(UserConstraint newConstraint){
		userManager.addConstraint(newConstraint);
	}
	
	public boolean hasConstraint(String compID){
		return userManager.hasConstraint(compID);
	}

	public boolean canUse(String compID){
		return userManager.canUse(compID);	
	}
	public boolean canView(String compID){
		return userManager.canView(compID);
	}

		
}
