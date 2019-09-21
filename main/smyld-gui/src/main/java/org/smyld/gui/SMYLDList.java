package org.smyld.gui;

import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.smyld.gui.event.SMYLDChangeListener;

public class SMYLDList extends JList {
	public SMYLDList(){
		super();
	}
	public SMYLDList(Object[] data){super(data);}
	public SMYLDList(ListModel model){super(model);}
	public SMYLDList(Vector<?> items){super(items);}
	public void addSMYLDChangeListener(final SMYLDChangeListener listener){
		if (listener==null) return;
		addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				listener.newChange(e);
			}
		});
			
	
			
		
		/*
		getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e){
				listener.newChange(e);
			}
			public void insertUpdate(DocumentEvent e){
				listener.newChange(e);
				
			}
			public void removeUpdate(DocumentEvent e){
				listener.newChange(e);
				
			}
		});
		*/
	}
	
}
