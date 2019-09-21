package com.smyld.gui;

import java.awt.Font;

import javax.swing.JComponent;

public class SMYLDLabeledTextArea extends SMYLDLabeledComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SMYLDTextArea txtAreaField;

	public SMYLDLabeledTextArea() {
		init();
	}

	public SMYLDLabeledTextArea(String Label) {
		super(Label);
		init();
		preset();
	}

	public SMYLDLabeledTextArea(String Label, int rows, int cols) {
		super(Label);
		init();
		txtAreaField.setRows(rows);
		txtAreaField.setColumns(cols);
		txtAreaField.setAutoscrolls(true);
		txtAreaField.setLineWrap(true);
		txtAreaField.setWrapStyleWord(true);
		preset();
	}
	public SMYLDLabeledTextArea(String Label, int rows, int cols,int mainComponentWidth) {
		this(Label,rows,cols);
		this.mainComponentWidth = mainComponentWidth;
	}
	public SMYLDLabeledTextArea(String Label, String text, int rows, int cols,int mainComponentWidth) {
		this(Label,text,rows,cols);
		this.mainComponentWidth = mainComponentWidth;
	}
	public SMYLDLabeledTextArea(String Label, String text, int rows, int cols) {
		this(Label, rows, cols);
		txtAreaField.setText(text);
	}

	@Override
	public JComponent createMainComponent() {
		txtAreaField = new SMYLDTextArea();
		return txtAreaField;
	}

	public SMYLDTextArea getTextArea() {
		return txtAreaField;
	}

	private void preset() {
		setAutoscrolls(false);
	}

	public void setText(String newText) {
		txtAreaField.setText(newText);
	}

	public String getText() {
		return txtAreaField.getText();
	}
    public void setFont(Font font) {
    	if (txtAreaField!=null)
    		txtAreaField.setFont(font);
    	super.setFont(font);
     }
	

}
