package com.smyld.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.smyld.gui.event.ActionHandler;
import com.smyld.gui.event.SMYLDActionListener;

public class SMYLDButton extends JButton implements ActionHandle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String        ID;
	GUIAction     curAction;
	ActionHandler actionHanler;

	public SMYLDButton() {
	}

	public SMYLDButton(GUIAction action) {
		curAction = action;
		initFromAction();
	}

	public SMYLDButton(GUIAction action, String ID) {
		this.ID = ID;
		curAction = action;
		initFromAction();
	}

	public SMYLDButton(GUIAction action, String label, ImageIcon icon) {
		this(label, icon);
		curAction = action;
	}

	public String getID() {
		return this.ID;
	}

	public SMYLDButton(String lable) {
		super(lable);
	}

	public SMYLDButton(String lable, ImageIcon icon) {
		super(lable, icon);
	}

	public SMYLDButton(String ID, String label) {
		super(label);
		this.ID = ID;
	}

	public SMYLDButton(ImageIcon icon) {
		super(icon);
	}

	private void initFromAction() {
		if (curAction.getLabel() != null) {
			super.setText(curAction.getLabel());
		}
	}

	public void setActionListener(ActionHandler actionHanler) {
		this.actionHanler = actionHanler;
		SMYLDActionListener smyldAction = new SMYLDActionListener(actionHanler,
				curAction);
		addActionListener(smyldAction);
		// setToolTipText();
	}

	public GUIAction getGUIAction() {
		return curAction;
	}

	@Override
	public void setText(String Lable) {
		super.setText(Lable);
		setTextDown();
	}

	public void setIcon(ImageIcon icon) {
		super.setIcon(icon);
		setTextLeft(); // temporary, should be controlled via the portal engine
	}
	public void setTextDown() {
		setVerticalTextPosition(BOTTOM);
		setHorizontalTextPosition(CENTER);
	}
	public void setTextLeft() {
		setVerticalTextPosition(CENTER);
		setHorizontalTextPosition(LEFT);
	}
	public void setTextRight() {
		setVerticalTextPosition(CENTER);
		setHorizontalTextPosition(RIGHT);
	}
	public void setTextTop() {
		setVerticalTextPosition(TOP);
		setHorizontalTextPosition(CENTER);
	}

}
