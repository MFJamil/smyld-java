package com.smyld.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.smyld.gui.event.PageNavigationListener;

public class SMYLDEditorPane extends JEditorPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PageNavigationListener listener;
	Vector<URL> pages = new Vector<URL>();
	int curPos = 0;
	boolean activateNavigation;
	boolean hyperListenerActivated;
	boolean supportExternalLinks = false;

	public SMYLDEditorPane() {
		addHyperlinkSupport();
	}

	public SMYLDEditorPane(String url) throws IOException {
		super(url);
		setEditable(false);
		addHyperlinkSupport();
	}

	public SMYLDEditorPane(String type, String text) throws IOException {
		super(type, text);
		addHyperlinkSupport();
	}

	public SMYLDEditorPane(URL page) throws IOException {
		super(page);
		setEditable(false);
		pages.add(page);
		addHyperlinkSupport();
	}

	public void addPageListener(PageNavigationListener newListener) {
		listener = newListener;
	}

	public void goBack() {
		if ((isActivateNavigation()) && (curPos > 0) && (pages.size() > 0)) {
			curPos -= 1;
			if ((goToPage((URL) pages.get(curPos))) && (listener != null)) {
				listener.pageBackwarded((URL) pages.get(curPos));
			}
		}
	}

	private boolean goToPage(URL pageURL) {
		return openPage(pageURL);
	}

	private void setHTMLText(String message) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html>");
		buffer.append("<body>");
		buffer.append("<p>");
		buffer.append("<b>");
		buffer.append(message);
		buffer.append("</b>");
		buffer.append("</p>");
		buffer.append("</body>");
		buffer.append("</html>");
		setContentType("text/html");
		setText(buffer.toString());

	}

	public void goForward() {
		if ((isActivateNavigation()) && (curPos >= 0) && (pages.size() > 1)
				&& (curPos != pages.size() - 1)) {
			curPos += 1;
			if (goToPage((URL) pages.get(curPos)) && (listener != null)) {
				listener.pageForwarded((URL) pages.get(curPos));
			}
		}

	}

	private void addHyperlinkSupport() {
		if (!hyperListenerActivated) {
			try {
				addHyperlinkListener(new HyperlinkListener() {
					public void hyperlinkUpdate(HyperlinkEvent evt) {
						if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
							//System.out.println("----{{{ Hyper link event received }}}----");
							if ((isExternalLink(evt.getURL()))&&(!supportExternalLinks)){
								//System.out.println("----{{{ Launching The desktop browser }}}----");
								// Code for opening the machine default browser
								try {
									Desktop.getDesktop().browse(evt.getURL().toURI());
								} catch (Exception e) {
									e.printStackTrace();
								}
								
							}else{
								if (goToPage(evt.getURL())) {
									doAddNewPage(evt.getURL());
									if (listener != null) {
										listener.pageNavigated(evt.getURL());
									}
								}

							}
							
						}
					}
				});
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			hyperListenerActivated = true;
		}
	}
	private boolean isExternalLink(URL link){
		String lnkText = link.toExternalForm();
		//System.out.println("----{{{ Checking the link " + lnkText + " }}}----");
		if ((lnkText!=null)&&(!lnkText.isEmpty())){
			return ((lnkText.indexOf("http")!=-1)||(lnkText.indexOf("https")!=-1)||(lnkText.indexOf("ftp")!=-1));
		}
		return false;
	}

	private void doAddNewPage(URL newPage) {
		if (curPos < pages.size() - 1) {
			// removing all the later pages in order to maintain the sequence of
		// pages
			int delPages = pages.size() - curPos - 1;
			for (int i = 0; i < delPages; i++) {
				pages.remove(curPos + 1);
			}
		}
		pages.add(newPage);
		curPos = pages.size() - 1;
	}

	public boolean isActivateNavigation() {
		return activateNavigation;
	}

	public void setActivateNavigation(boolean activateNavigation) {
		this.activateNavigation = activateNavigation;
	}

	public boolean openPage(URL page) {
		if (isEditable()) {
			setEditable(false);
		}
		try {
			setContentType("text/html");
			addHyperlinkSupport();
			super.setPage(page);
			return true;
		} catch (Exception e) {
			setHTMLText(e.toString());
		}
		return false;

	}

	public boolean openPage(String page) {
		setContentType("text/html");
		if (isEditable()) {
			setEditable(false);
		}
		try {
			addHyperlinkSupport();
			super.setPage(page);
			return true;
		} catch (Exception e) {
			setHTMLText(e.toString());
		}
		return false;

	}

	public int getHistorySize() {
		return pages.size();
	}

	public int getCurrentPageIndexInHistory() {
		return curPos;
	}

}
