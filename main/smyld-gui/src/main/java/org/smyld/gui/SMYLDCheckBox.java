package org.smyld.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import org.smyld.gui.event.SMYLDChangeListener;

public class SMYLDCheckBox extends JCheckBox {
	public SMYLDCheckBox(String text){
		super(text);
	}
	public SMYLDCheckBox(String text,ImageIcon icon){
		super(text,icon);
	}
	public SMYLDCheckBox(String text,boolean selected){
		super(text,selected);
	}

	public SMYLDCheckBox(String text,ImageIcon icon,boolean selected){
		super(text,icon,selected);
	}
	public SMYLDCheckBox(ImageIcon icon,boolean selected){
		super(icon,selected);
	}
	public SMYLDCheckBox(ImageIcon icon){
		super(icon);
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
