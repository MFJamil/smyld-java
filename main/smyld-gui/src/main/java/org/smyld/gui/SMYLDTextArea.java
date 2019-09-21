package org.smyld.gui;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.smyld.gui.event.SMYLDChangeListener;

public class SMYLDTextArea extends JTextArea {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JScrollPane scrollPane;

	public SMYLDTextArea() {
		super();
		init();

	}

	public SMYLDTextArea(int rows, int cols) {
		super(rows, cols);
		init();
	}

	public SMYLDTextArea(String text, int rows, int cols) {
		this(rows, cols);
		setText(text);
	}

	private void init() {
		setBorder(BorderFactory.createEtchedBorder());
		setAutoscrolls(true);
		setLineWrap(true);
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
	}

}
