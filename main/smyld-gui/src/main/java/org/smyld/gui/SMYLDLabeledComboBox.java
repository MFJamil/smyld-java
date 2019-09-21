package org.smyld.gui;

import java.awt.event.ItemListener;

import javax.swing.JComponent;

public class SMYLDLabeledComboBox extends SMYLDLabeledComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SMYLDComboBox comboField;

	public SMYLDLabeledComboBox(String Label) {
		super(Label);
		init();
	}
	public SMYLDLabeledComboBox(String Label, int mainComponentWidth) {
		this(Label);
		this.mainComponentWidth = mainComponentWidth;
		init();
	}

	@Override
	protected JComponent createMainComponent() {
		comboField = new SMYLDComboBox();
		return comboField;
	}

	public void addItem(Object newItem) {
		comboField.addItem(newItem);
	}

	public SMYLDComboBox getSMYLDCombo() {
		return comboField;
	}

	public void addItemListener(ItemListener listener) {
		comboField.addItemListener(listener);
	}

}
