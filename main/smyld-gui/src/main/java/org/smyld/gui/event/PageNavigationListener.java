package org.smyld.gui.event;

import java.net.URL;

public interface PageNavigationListener {
	public void pageNavigated(URL pageURL);

	public void pageForwarded(URL pageURL);

	public void pageBackwarded(URL pageURL);

}
