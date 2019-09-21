package org.smyld.gui;

import java.awt.Font;

import javax.swing.JComponent;

public class SMYLDLabeledTextField extends SMYLDLabeledComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SMYLDTextField txtField;
	String text;
	int cols;

	public SMYLDLabeledTextField() {
		super();
	}

	public SMYLDLabeledTextField(String Label, int Cols) {
		super(Label);
		setCols(Cols);
		init();
	}

	public SMYLDLabeledTextField(String Label, String Text, int Cols,int mainComponentWidth) {
		this(Label, Cols);
		this.mainComponentWidth = mainComponentWidth;
		setText(Text);
		init();
	}

	@Override
	protected JComponent createMainComponent() {
		txtField = new SMYLDTextField(getText(), getCols());

		return txtField;
	}

	public String getText() {
		if (txtField != null) {
			return txtField.getText();
		}
		return text;
	}

	public void setText(String Text) {
		this.text = Text;
		if (txtField != null) {
			txtField.setText(text);
		}

	}

	public int getCols() {
		if (txtField != null) {
			return txtField.getColumns();
		}

		return cols;
	}

	public void setCols(int Cols) {
		this.cols = Cols;
		if (txtField != null) {
			txtField.setColumns(cols);
		}
	}

	public SMYLDTextField getSMYLDTextField() {
		return txtField;
	}
    public void setFont(Font font) {
    	if (txtField!=null)
    		txtField.setFont(font);
    	super.setFont(font);
    }
}
