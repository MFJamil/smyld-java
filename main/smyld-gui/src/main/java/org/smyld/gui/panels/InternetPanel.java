package org.smyld.gui.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JScrollPane;

import org.smyld.gui.SMYLDButton;
import org.smyld.gui.SMYLDEditorPane;
import org.smyld.gui.SMYLDPanel;
import org.smyld.gui.event.PageNavigationListener;
import org.smyld.resources.Resource;

public class InternetPanel extends SMYLDPanel implements PageNavigationListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private URL homePage = null;
	private BorderLayout borderLayout1 = new BorderLayout();
	private FlowLayout flowLayout1 = new FlowLayout();
	private SMYLDPanel navigation = new SMYLDPanel();
	private SMYLDButton forwardBut = new SMYLDButton();
	private SMYLDButton backwardBut = new SMYLDButton();
	private SMYLDButton homeBut = new SMYLDButton();
	private JScrollPane container = new JScrollPane();

	private SMYLDEditorPane pane = new SMYLDEditorPane();

	public InternetPanel() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InternetPanel(URL page) {

		try {
			pane = new SMYLDEditorPane(page);
			setHomePage(page);
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		Resource res = new Resource();
		pane.setActivateNavigation(true);
		pane.addPageListener(this);
		flowLayout1.setAlignment(FlowLayout.LEFT);
		backwardBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				pane.goBack();
			}
		});
		forwardBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				pane.goForward();
			}
		});
		homeBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				pane.openPage(getHomePage());
			}
		});

		forwardBut.setIcon(res.getImageIcon("arrow_right.gif"));
		forwardBut.setEnabled(false);
		backwardBut.setIcon(res.getImageIcon("arrow_left.gif"));
		homeBut.setIcon(res.getImageIcon("home.gif"));
		backwardBut.setEnabled(false);
		navigation.add(backwardBut);
		navigation.add(forwardBut);
		navigation.add(homeBut);
		navigation.setLayout(flowLayout1);

		this.setLayout(borderLayout1);
		container.setViewportView(pane);
		this.add(container, BorderLayout.CENTER);
		this.add(navigation, BorderLayout.NORTH);

	}

	public void openPage(URL page) {
		pane.openPage(page);
		if (getHomePage() == null) {
			setHomePage(page);
		}
	}

	public void openPage(String page) {
		pane.openPage(page);
	}

	public void pageNavigated(URL pageURL) {
		updateGUIStatus();
	}

	public void pageForwarded(URL pageURL) {
		updateGUIStatus();

	}

	public void pageBackwarded(URL pageURL) {
		updateGUIStatus();
	}

	private void updateGUIStatus() {
		if (pane.getHistorySize() > 0) {
			if (pane.getCurrentPageIndexInHistory() == 0) {
				forwardBut.setEnabled(true);
				backwardBut.setEnabled(false);
			} else if (pane.getCurrentPageIndexInHistory() == pane
					.getHistorySize() - 1) {
				forwardBut.setEnabled(false);
				backwardBut.setEnabled(true);
			} else {
				forwardBut.setEnabled(true);
				backwardBut.setEnabled(true);
			}
		} else {
			forwardBut.setEnabled(false);
			backwardBut.setEnabled(false);
		}

	}

	public URL getHomePage() {
		return homePage;
	}

	public void setHomePage(URL homePage) {
		this.homePage = homePage;
	}

}
