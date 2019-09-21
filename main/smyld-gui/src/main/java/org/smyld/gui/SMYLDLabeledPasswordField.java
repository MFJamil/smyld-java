package org.smyld.gui;

import javax.swing.JComponent;

public class SMYLDLabeledPasswordField extends SMYLDLabeledComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SMYLDPasswordField passField;
	String text;
	int cols;

	public SMYLDLabeledPasswordField() {
		super();
	}

	public SMYLDLabeledPasswordField(String Label, int Cols) {
		super(Label);
		setCols(Cols);
		init();
	}

	public SMYLDLabeledPasswordField(String Label, int Cols,int mainComponentWidth) {
		this(Label, Cols);
		this.mainComponentWidth = mainComponentWidth;
		init();
	}

	@Override
	protected JComponent createMainComponent() {
		passField = new SMYLDPasswordField(getCols());

		return passField;
	}

	public String getText() {
		if (passField != null) {
			return passField.getText();
		}
		return text;
	}

	public void setText(String Text) {
		this.text = Text;
		if (passField != null) {
			passField.setText(text);
		}

	}

	public int getCols() {
		if (passField != null) {
			return passField.getColumns();
		}

		return cols;
	}

	public void setCols(int Cols) {
		this.cols = Cols;
		if (passField != null) {
			passField.setColumns(cols);
		}
	}

	public SMYLDPasswordField getSMYLDPasswordField() {
		return passField;
	}

}
