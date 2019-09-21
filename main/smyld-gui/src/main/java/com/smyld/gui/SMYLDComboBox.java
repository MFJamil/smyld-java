package com.smyld.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import com.smyld.gui.event.SMYLDChangeListener;

public class SMYLDComboBox extends JComboBox {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMYLDComboBox() {
		super();
	}

	public SMYLDComboBox(Object[] values) {
		super(values);
		
	}
	public void addSMYLDChangeListener(final SMYLDChangeListener listener){
		if (listener==null) return;
		addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent evt){
				listener.newChange(evt);
			}
		});
	}
	

}
