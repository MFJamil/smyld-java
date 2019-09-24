package org.smyld.deploy.web.jnlp;

import org.jdom2.Element;

import org.smyld.deploy.web.Descriptor;
import org.smyld.xml.XMLUtil;

public class InfoDescriptor extends Descriptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String title;
	String vendor;
	String homePage;
	String description;
	String tooltip;
	String icon;
	ShortcutDescriptor shortCut;
	boolean offlineAllowed;

	public InfoDescriptor() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Element getXMLElement() {
		Element info = new Element(TAG_NAME_INFO);
		XMLUtil.addChildElement(info, TAG_NAME_TITLE, getTitle());
		XMLUtil.addChildElement(info, TAG_NAME_VENDOR, getVendor());
		XMLUtil.addChildElement(info, TAG_NAME_DESC, getDescription());
		XMLUtil.addChildElement(info, TAG_NAME_HOME_PAGE, getHomePage());
		if (getTooltip() != null) {
			Element tooltipEl = new Element(TAG_NAME_DESC);
			tooltipEl.setAttribute(TAG_ATT_KIND, ATT_KIND_TOOLTIP);
			tooltipEl.setText(getTooltip());
			info.addContent(tooltipEl);
		}
		if (getIcon() != null) {
			Element iconEl = new Element(TAG_NAME_ICON);
			iconEl.setAttribute(TAG_ATT_REF, getIcon());
			info.addContent(iconEl);
		}
		if (isOfflineAllowed())
			XMLUtil.addChildElement(info, TAG_NAME_OFF_ALWD, "");

		if (getShortCut() != null)
			info.addContent(getShortCut().getXMLElement());
		return info;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public ShortcutDescriptor getShortCut() {
		return shortCut;
	}

	public void setShortCut(ShortcutDescriptor shortCut) {
		this.shortCut = shortCut;
	}

	public boolean isOfflineAllowed() {
		return offlineAllowed;
	}

	public void setOfflineAllowed(boolean offlineAllowed) {
		this.offlineAllowed = offlineAllowed;
	}
}
