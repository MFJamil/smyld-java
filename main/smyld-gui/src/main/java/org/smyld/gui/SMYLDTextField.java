package org.smyld.gui;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.smyld.gui.event.SMYLDChangeListener;

public class SMYLDTextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMYLDTextField() {
		super();
	}

	public SMYLDTextField(int cols) {
		super(cols);
	}

	public SMYLDTextField(String text, int cols) {
		super(text, cols);
	}

	public void reset() {
		super.setText("");
	}
	public void addSMYLDChangeListener(final SMYLDChangeListener listener){
		if (listener==null) return;
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
		/*
		addInputMethodListener(new InputMethodListener(){
			public void caretPositionChanged(InputMethodEvent event){
				listener.newChange(event);
			}
			public void inputMethodTextChanged(InputMethodEvent event){
				listener.newChange(event);
			}
			
		});
		*/
	}
	
}
