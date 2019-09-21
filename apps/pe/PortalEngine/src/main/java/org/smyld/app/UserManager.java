package org.smyld.app;

import org.smyld.app.pe.model.user.UserConstraint;

import java.util.HashMap;


public class UserManager {
	protected HashMap<String, UserConstraint> constraints = new HashMap<String, UserConstraint>();

	public void addConstraint(UserConstraint newConstraint){
		constraints.put(newConstraint.getCompID(), newConstraint);
	}
	
	public boolean hasConstraint(String compID){
		if ((constraints!=null)&&(constraints.size()>0))
			return (constraints.containsKey(compID));		
		return false;
	}

	public boolean canUse(String compID){
		if ((constraints!=null)&&(constraints.size()>0)){
			if (constraints.containsKey(compID)){
				return constraints.get(compID).canUse(AppMainClass.instance.activeUser);
			}
		}
		return false;
	}
	public boolean canView(String compID){
		if ((constraints!=null)&&(constraints.size()>0)){
			if (constraints.containsKey(compID)){
				return constraints.get(compID).canView(AppMainClass.instance.activeUser);
			}
		}
		return false;
	}

}
