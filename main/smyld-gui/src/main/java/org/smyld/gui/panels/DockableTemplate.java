package org.smyld.gui.panels;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JSplitPane;

import org.smyld.gui.GUIAction;
import org.smyld.gui.SMYLDDesktopPane;
import org.smyld.gui.SMYLDLabel;
import org.smyld.gui.SMYLDPanel;
import org.smyld.gui.event.ActionHandler;
import org.smyld.gui.event.DockablePanelListener;
import org.smyld.text.TextUtil;

public class DockableTemplate extends SMYLDPanel implements ActionHandler,
		DockableConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private   JSplitPane                    hSplit         = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	private   BorderLayout                  borderLayout1  = new BorderLayout();
	protected JComponent                    mainComponent  = null;
	private   DockableContainer             deskTopCont    = new DockableContainer();
	private   SMYLDPanel                     leftPanel      = new SMYLDPanel();
	private   SMYLDPanel                     topPanel       = new SMYLDPanel();
	private   SMYLDPanel                     downPanel      = new SMYLDPanel();
	private   SMYLDPanel                     rightPanel     = new SMYLDPanel();
	private   FlowLayout                    topLayout      = new FlowLayout(FlowLayout.RIGHT);
	private   FlowLayout                    leftLayout     = new FlowLayout(FlowLayout.RIGHT);
	private   FlowLayout                    rightLayout    = new FlowLayout(FlowLayout.LEFT);
	private   FlowLayout                    downLayout     = new FlowLayout(FlowLayout.LEFT);
	private   HashMap<String,DockablePanel> compContainers = new HashMap<String,DockablePanel>();
	          DockableTemplate              instance;
	          DockablePanel                 sidePanel;
	          SMYLDLabel                     panLable;
	          SMYLDDesktopPane               defaultPanel   = new SMYLDDesktopPane();

	public DockableTemplate(JComponent mainComponent) {
		this.mainComponent = mainComponent;
		init();

		instance = this;
	}

	public DockableTemplate() {
		mainComponent = defaultPanel;
		init();
		instance = this;
	}

	private void init() {
		this.setLayout(borderLayout1);
		topPanel.setLayout(topLayout);
		leftPanel.setLayout(leftLayout);
		rightPanel.setLayout(rightLayout);
		downPanel.setLayout(downLayout);
		topPanel.setSize(0, 0);
		downPanel.setSize(0, 0);
		leftPanel.setSize(0, 0);
		rightPanel.setSize(0, 0);
		deskTopCont.setMainComp(mainComponent);
		this.add(topPanel, BorderLayout.NORTH);
		this.add(leftPanel, BorderLayout.WEST);
		this.add(downPanel, BorderLayout.SOUTH);
		this.add(rightPanel, BorderLayout.EAST);
		this.add(deskTopCont, BorderLayout.CENTER);
	}

	public void addToDown(JComponent newComp, String title, ImageIcon icon,
			boolean showTabTitle, boolean showTabIcon) {
		// need code here
		addComponent(newComp, title, icon, DROP_DOWN, showTabTitle, showTabIcon);
	}

	public void addToSide(JComponent newComp, String title, ImageIcon icon,
			boolean showTabTitle, boolean showTabIcon) {
		// need code here
		addComponent(newComp, title, icon, DROP_SIDE, showTabTitle, showTabIcon);
	}

	public void addComponent(JComponent newComp, String title, ImageIcon icon,int location, boolean showTabTitle, boolean showTabIcon) {
		// need code here
		boolean newCreated = false;
		String panelID = Integer.toString(location);
		if (!compContainers.containsKey(panelID)) {
			compContainers.put(panelID, initCompContainer(panelID));
			newCreated = true;
		}
		DockablePanel targetPanel = (DockablePanel) compContainers.get(panelID);
		if (newCreated) {
			targetPanel.setDockLocation(getComponentLocation(location));
		}
		targetPanel.setContent(newComp, title, icon, showTabTitle, showTabIcon);
		if ((targetPanel.getStatus() == DockablePanel.STATUS_MINIMIZED)|| (targetPanel.getStatus() == DockablePanel.STATUS_CLOSED)) {
			showPanel(targetPanel);
		} else if (newCreated) {
			deskTopCont.addComponent(targetPanel,
					getComponentLocation(location), false);
			targetPanel.setStatus(DockablePanel.STATUS_SHOWN);
		}
		revalidate();
		repaint();
	}

	private int getComponentLocation(int compLocation) {
		if (compLocation == DROP_SIDE) {
			if (getComponentOrientation().isLeftToRight()) {
				return DROP_LEFT;
			}
			return DROP_RIGHT;
		}
		return compLocation;
	}

	private DockablePanel initCompContainer(String dockableID) {
		DockablePanel newPanel = new DockablePanel();
		newPanel.setId(dockableID);
		newPanel.applyComponentOrientation(getComponentOrientation());
		newPanel.addListener(new DockablePanelListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void panelClosed(DockablePanel panel) {
				if (panel.getComponentNumber() == 0) {
					panel.setStatus(DockablePanel.STATUS_CLOSED);
					panel.setDockLocation(getComponentLocation(Integer
							.parseInt(panel.getId())));
					deskTopCont.removeComponent(panel);
					instance.revalidate();
					instance.repaint();
				}
			}

			public void panelHidden(DockablePanel panel) {
				panel.setStatus(DockablePanel.STATUS_MINIMIZED);
				deskTopCont.removeComponent(panel);
				downPanel.add(panel.getMinimizedLabel());
				downPanel.repaint();
				instance.revalidate();
			}

			public void panelShown(DockablePanel panel) {
				showPanel(panel);
			}

			public void panelDragged(DockablePanel panel) {
				deskTopCont.registerDragOperation(panel);
			}

			public void panelDropped(DockablePanel panel) {
				deskTopCont.dropComponent(panel);
			}
		});
		return newPanel;
	}

	@Override
	public void applyComponentOrientation(ComponentOrientation newOrient) {
		super.applyComponentOrientation(newOrient);
		if (newOrient.isLeftToRight()) {
			topLayout.setAlignment(FlowLayout.RIGHT);
			leftLayout.setAlignment(FlowLayout.RIGHT);
			rightLayout.setAlignment(FlowLayout.LEFT);
			downLayout.setAlignment(FlowLayout.LEFT);
		} else {
			topLayout.setAlignment(FlowLayout.LEFT);
			leftLayout.setAlignment(FlowLayout.LEFT);
			rightLayout.setAlignment(FlowLayout.RIGHT);
			downLayout.setAlignment(FlowLayout.RIGHT);
		}

		if ((compContainers != null) && (compContainers.size() > 0)) {
			for (DockablePanel curPanel : compContainers.values()) {
				curPanel.applyComponentOrientation(newOrient);
			}
		}
		deskTopCont.applyComponentOrientation(newOrient);
	}

	private void showPanel(DockablePanel targetPanel) {
		downPanel.remove(targetPanel.getMinimizedLabel());
		targetPanel.refresh();
		deskTopCont.addComponent(targetPanel, targetPanel.getDockLocation(),
				false);
		targetPanel.setStatus(DockablePanel.STATUS_SHOWN);
		instance.revalidate();
		instance.repaint();
	}

	public void processGUIAction(GUIAction incomingAction) {
		if (incomingAction != null) {
			if (TextUtil.doEqual(incomingAction.getCommand(),GUIAction.COM_ADD_COMP)) {
				JComponent newComp = (JComponent) incomingAction.getUserObject();
				if (TextUtil.doEqual(incomingAction.getParameters(),LOCATE_DOWN)) {
					addToDown(newComp, incomingAction.getLabel(),incomingAction.getIcon(), true, true);
				}else if (TextUtil.doEqual(incomingAction.getParameters(),LOCATE_SIDE)) {
					addToSide(newComp, incomingAction.getLabel(),incomingAction.getIcon(), true, true);
				} else {
					deskTopCont.setFreeMainComponent(newComp);
					/*
					DockablePanel targetPanel = initCompContainer("main");
					targetPanel.setContent(newComp, incomingAction.getLabel(),incomingAction.getIcon(), true, true);
					deskTopCont.setMainComp(targetPanel);
					*/
				}
			} else {
				if (mainComponent instanceof ActionHandler) {
					((ActionHandler) mainComponent).processGUIAction(incomingAction);
				} else {
					doProcessGUIAction(incomingAction);
				}
			}
		}
	}

	public void setMainComponent(JComponent newMainComponent) {
		this.mainComponent = newMainComponent;
		deskTopCont.setMainComp(mainComponent);
	}

	public void setFreeComponent(JComponent newMainComponent) {

		deskTopCont.setFreeComponent(mainComponent);
	}

	public JComponent getMainComponent() {
		return this.mainComponent;
	}

	public void doProcessGUIAction(GUIAction incomingAction) {
		// Child classes can use this method to receive the action
	}

	public static final String LOCATE_SIDE = "side";
	public static final String LOCATE_DOWN = "down";
	public static final String LOCATE_MAIN = "main";

}
