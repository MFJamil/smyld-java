package org.smyld.deploy;

import org.smyld.SMYLDObject;

public class DeploymentDescriptor extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String title;
	String name;
	String description;
	String icon;
	String tooltip;
	String vendor;

	public DeploymentDescriptor() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
}
