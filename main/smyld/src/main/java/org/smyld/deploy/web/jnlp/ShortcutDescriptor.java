package org.smyld.deploy.web.jnlp;

import org.jdom2.Element;

import org.smyld.deploy.web.Descriptor;
import org.smyld.xml.XMLUtil;

public class ShortcutDescriptor extends Descriptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean desktop;
	boolean online;
	String menu;

	public ShortcutDescriptor() {
	}

	public boolean isDesktop() {
		return desktop;
	}

	public void setDesktop(boolean desktop) {
		this.desktop = desktop;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	@Override
	public Element getXMLElement() {
		Element shortEl = new Element(TAG_NAME_SHORTCUT);
		shortEl.setAttribute(TAG_ATT_ONLINE, isOnline() ? ATT_BOOLEAN_TRUE
				: ATT_BOOLEAN_FALSE);
		if (isDesktop())
			XMLUtil.addChildElement(shortEl, TAG_NAME_DESKTOP, "");
		if (getMenu() != null) {
			Element menuEl = new Element(TAG_NAME_MENU);
			menuEl.setAttribute(TAG_ATT_SUBMENU, getMenu());
			shortEl.addContent(menuEl);
		}
		return shortEl;
	}
}
