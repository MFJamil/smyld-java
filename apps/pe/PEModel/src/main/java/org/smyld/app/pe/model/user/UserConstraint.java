package org.smyld.app.pe.model.user;

import org.smyld.text.TextUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UserConstraint {
	HashSet<String> roles;
	HashSet<String> showRoles;
	String          compID;
	
	public UserConstraint(){
		
	}
	public UserConstraint(String compID){
		this.compID = compID;
		
	}
			
	public UserConstraint(String compID,String roles,String showRoles){
		this(compID);
		init(roles,showRoles);
		
	}
	public UserConstraint(String roles,String showRoles){
		init(roles,showRoles);
		
	}
	
	private void init(String roles,String showRoles){
		if (!TextUtil.isEmpty(roles)){
			if (roles.indexOf(',')==-1){
				addRole(roles);
			}else{
				for(String curRole:roles.split(",")) addRole(curRole);
			}
		}
		if (!TextUtil.isEmpty(showRoles)){
			if (showRoles.indexOf(',')==-1){
				addShowRole(showRoles);
			}else{
				for(String curRole:showRoles.split(",")) addShowRole(curRole);
			}
		}

	}
	
	public void addShowRole(String newRole){
		if (showRoles == null) showRoles = new HashSet<String>();
		showRoles.add(newRole);
		
	}
	
	private boolean checkRights(Set<String> src, PEUser checkUser){
		if (src!=null){
			if (checkUser==null) return false;
			if ((checkUser.getRoles()==null)||(checkUser.getRoles().size()==0)) return false; // in this case the user is not allowed to view that object
			for(String curRole:src){
				if (checkUser.getRoles().contains(curRole)) return true;
			}
			return false;
		}
		return true;
		
	}
	public boolean canUse(PEUser checkUser){
		return checkRights(roles, checkUser);
	}
	public boolean canView(PEUser checkUser){
		return checkRights(showRoles, checkUser);
	}
	

	public void addRole(String newRole){
		if (roles == null) roles = new HashSet<String>();
		roles.add(newRole);
		
	}
	
	
	private String composeValues(Set<String> src){
		if ((src!=null)&&(src.size()>0)){
			StringBuilder sb = new StringBuilder();
			Iterator<String> itr =  src.iterator();
			while(itr.hasNext()){
				sb.append(itr.next());
				if (itr.hasNext()) sb.append(',');
			}
			return sb.toString();
		}
		return null;

	}
	
	public String getAllRoles(){
		return composeValues(roles);
	}
	public String getAllShowRoles(){
		return composeValues(showRoles);
	}
	
	/**
	 * @return the roles
	 */
	public HashSet<String> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(HashSet<String> roles) {
		this.roles = roles;
	}
	/**
	 * @return the showRoles
	 */
	public HashSet<String> getShowRoles() {
		return showRoles;
	}
	/**
	 * @param showRoles the showRoles to set
	 */
	public void setShowRoles(HashSet<String> showRoles) {
		this.showRoles = showRoles;
	}

	/**
	 * @return the compID
	 */
	public String getCompID() {
		return compID;
	}

	/**
	 * @param compID the compID to set
	 */
	public void setCompID(String compID) {
		this.compID = compID;
	}
	
	
	
}
