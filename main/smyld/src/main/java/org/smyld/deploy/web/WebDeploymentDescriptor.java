package org.smyld.deploy.web;

import java.util.Vector;

import org.smyld.deploy.DeploymentDescriptor;
import org.smyld.security.SMYLDKey;
import org.smyld.web.Server;

public class WebDeploymentDescriptor extends DeploymentDescriptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String codeBase;
	Server targetServer;
	Vector<String> securityPermissions;
	SMYLDKey securityKey;

	public WebDeploymentDescriptor() {
	}

	public String getCodeBase() {
		return codeBase;
	}

	public void setCodeBase(String codeBase) {
		this.codeBase = codeBase;
	}

	public Server getTargetServer() {
		return targetServer;
	}

	public void setTargetServer(Server targetServer) {
		this.targetServer = targetServer;
	}

	public Vector<String> getSecurityPermissions() {
		return securityPermissions;
	}

	public void setSecurityPermissions(Vector<String> securityPermissions) {
		this.securityPermissions = securityPermissions;
	}

	public void addPermission(String permission) {
		if (securityPermissions == null)
			securityPermissions = new Vector<String>();
		securityPermissions.add(permission);
	}

	public SMYLDKey getSecurityKey() {
		return securityKey;
	}

	public void setSecurityKey(SMYLDKey securityKey) {
		this.securityKey = securityKey;
	}
}
