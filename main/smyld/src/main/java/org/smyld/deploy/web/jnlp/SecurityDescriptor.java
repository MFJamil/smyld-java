package org.smyld.deploy.web.jnlp;

import java.util.Vector;

import org.jdom2.Element;

import org.smyld.deploy.web.Descriptor;
import org.smyld.xml.XMLUtil;

public class SecurityDescriptor extends Descriptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector<String> permissions;
	boolean grantAllPermissions;

	public SecurityDescriptor() {
	}

	public Vector<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Vector<String> permissions) {
		this.permissions = permissions;
	}

	public void addAllPermissions() {
		grantAllPermissions = true;
	}

	@Override
	public Element getXMLElement() {
		Element sec = new Element(TAG_NAME_SECURITY);
		/*
		 * Iterator itr = permissions.iterator(); while(itr.hasNext()){
		 * (String)itr.next(); }
		 */
		if (grantAllPermissions)
			XMLUtil.addChildElement(sec, TAG_NAME_ALL_PERMISSIONS, "");
		return sec;
	}

}
