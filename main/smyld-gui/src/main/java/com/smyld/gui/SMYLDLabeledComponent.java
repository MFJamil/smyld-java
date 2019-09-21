package com.smyld.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.smyld.gui.event.SMYLDChangeListener;

public abstract class SMYLDLabeledComponent extends JPanel implements
		GUIConstants {
	JLabel       lblField;
	JComponent   mainComponent;
	//BorderLayout layout;
	String       label;
	JScrollPane  scrollPane;
	int          labelPosition;
	protected int          mainComponentWidth;

	public SMYLDLabeledComponent() {
	}

	public SMYLDLabeledComponent(String Label) {
		setLabel(Label);
	}

	public void setScrollable(boolean scrol) {

	}

	protected void init() {
		labelPosition = SIDE;
		//BoxLayout layout = new BoxLayout(this,BoxLayout.LINE_AXIS);
		//BorderLayout layout = new BorderLayout();
		//SMYLDXYLayout xyLayout = new SMYLDXYLayout();
		//setLayout(layout);
		setLayout(null);
		mainComponent = createMainComponent();
		add(mainComponent);
		//mainComponent.setLocation(super.getSize().width - mainComponentWidth, mainComponent.getHeight());
		//add(mainComponent);
		lblField = new JLabel(getLabel());
		lblField.setVerticalAlignment(TOP);
		lblField.setHorizontalAlignment(LEFT);
		addLabelField();
		// setFocusable(true);
		lblField.setLabelFor(mainComponent);
		addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent evt) {
				mainComponent.requestFocusInWindow();
			}
			public void focusLost(FocusEvent evt) {
			}
		});
	}
	
	public JComponent getMainComponent(){return mainComponent;}
	public JComponent getLabelComponent(){return lblField;}
	protected abstract JComponent createMainComponent();
	public String getLabel() {return label;}
	public void setVerticalLabelAlignment(int position) {
		lblField.setVerticalAlignment(position);
	}
	public void addSMYLDChangeListener(final SMYLDChangeListener listener){
		if ((mainComponent ==null)||(listener==null)) return;
		if (mainComponent instanceof SMYLDComboBox){
			//.addItemListener(new ItemListener(){");
			SMYLDComboBox combo =  (SMYLDComboBox)mainComponent;
			combo.addSMYLDChangeListener(listener);
		}else if (mainComponent instanceof SMYLDTextField){
			SMYLDTextField text =  (SMYLDTextField)mainComponent;
			text.addSMYLDChangeListener(listener);
		}else if (mainComponent instanceof SMYLDTextArea){
			SMYLDTextArea texta =  (SMYLDTextArea)mainComponent;
			texta.addSMYLDChangeListener(listener);

		}
	}
	public void setHorizintalLabelAlignment(int position) {
		lblField.setHorizontalAlignment(position);
	}

	public void setLabel(String label) {
		this.label = label;
		if (lblField != null) {
			lblField.setText(label);
		}
	}

	@Override
	public void setEnabled(boolean enable) {
		lblField.setEnabled(enable);
		mainComponent.setEnabled(enable);
		if (scrollPane != null) {
			scrollPane.setEnabled(enable);
		}
	}
	public void setIcon(ImageIcon newIcon){
		lblField.setIcon(newIcon);
		
	}
	@Override
	public void setComponentOrientation(ComponentOrientation orient) {
		super.setComponentOrientation(orient);
		if (lblField!=null)
			lblField.setComponentOrientation(orient);
		if (mainComponent!=null)
			mainComponent.setComponentOrientation(orient);
		// init();
		/*
		if ((lblField != null) && (mainComponent != null)) {
			remove(lblField);
			addLabelField();
		}
		*/
	}

	private void addLabelField() {
		/*
		String dir = getComponentOrientation().isLeftToRight() ? BorderLayout.WEST
				: BorderLayout.EAST;
		add(lblField, dir);
		//*/
		//lblField.setLocation(super.getLocation().x , super.getLocation().y);
		//lblField.setLocation(0, lblField.getHeight());
		
		add(lblField);
		

	}

	@Override
	public void setAutoscrolls(boolean scrollable) {
		mainComponent.setAutoscrolls(true);
		if (scrollable) {
			scrollPane = new JScrollPane(mainComponent);
			remove(mainComponent);
			add(scrollPane, BorderLayout.CENTER);
		}
	}

	public void setBounds(int x,int y,int width,int height){
		// Aligning the component according to the layout manager changes

		int realMainCompWidth = mainComponentWidth;
		LayoutManager ly = super.getParent().getLayout();
		
		if ((ly!=null)&&(ly instanceof SMYLDRCLayout))
			realMainCompWidth = mainComponentWidth * SMYLDRCLayout.x_ratio;
		if (mainComponentWidth==0)
			realMainCompWidth = (int)(width*0.66f);
		if ((label==null)||(label.isEmpty()))
			realMainCompWidth = width;
		boolean ltr = getComponentOrientation().isLeftToRight();
		lblField.setComponentOrientation(getComponentOrientation());
		mainComponent.setComponentOrientation(getComponentOrientation());
		int lblH = (int)lblField.getPreferredSize().getHeight();
		switch(labelPosition){
			case TOP:
				lblField.setLocation(0, 0);
				lblField.setSize(width,lblH);
				mainComponent.setLocation(0, lblH);
				mainComponent.setSize(width,height-lblH);
				break;
			case BOTTOM:
				lblField.setLocation(0, height-lblH);
				lblField.setSize(width,lblH);
				mainComponent.setLocation(0, 0);
				mainComponent.setSize(width,height-lblH);
				break;
			default:
				if (ltr){
					lblField.setLocation(0, 0);
					lblField.setSize(width-realMainCompWidth,height);
					mainComponent.setLocation(width-realMainCompWidth, 0);
					mainComponent.setSize(realMainCompWidth,height);
				}else{
					lblField.setLocation(width-realMainCompWidth, 0);
					lblField.setSize(width-realMainCompWidth,height);
					mainComponent.setLocation(0,0);
					mainComponent.setSize(realMainCompWidth,height);
				}
		}
		
		//*/
		//lblField.setLocation(super.getLocation().x , super.getLocation().y);
		//lblField.setLocation(0, lblField.getHeight());
		
		
		super.setBounds(x, y, width, height);

	}

	
	
	public void setLabelPosition(String newPosition) {
		if (newPosition.equals(COMP_TEXT_POSITION_TOP)) {
			setLabelPosition(TOP);
		} else if (newPosition.equals(COMP_TEXT_POSITION_BOTTOM)) {
			setLabelPosition(BOTTOM);
		} else if (newPosition.equals(COMP_TEXT_POSITION_SIDE)) {
			setLabelPosition(SIDE);
		}

	}

	public void setLabelPosition(int newPosition) {
		if (labelPosition != newPosition) {
			remove(lblField);
			switch (newPosition) {
			case TOP:
				add(lblField, BorderLayout.NORTH);
				setHorizintalLabelAlignment(CENTER);
				break;
			case BOTTOM:
				add(lblField, BorderLayout.SOUTH);
				setHorizintalLabelAlignment(CENTER);
				break;
			default:
				addLabelField();
			}
			labelPosition = newPosition;
		}
	}

	@Override
	public void addMouseListener(MouseListener mouseListener) {
		if (mainComponent != null) {
			mainComponent.addMouseListener(mouseListener);
		}
	}

	@Override
	public void addFocusListener(FocusListener focusListener) {
		if (mainComponent != null) {
			mainComponent.addFocusListener(focusListener);
		}
	}

	public int getMainComponentWidth() {
		return mainComponentWidth;
	}

	public void setMainComponentWidth(int mainComponentWidth) {
		this.mainComponentWidth = mainComponentWidth;
	
	}
	

	public void setBGColor(String newColor) {
		try {
			Color targetColor = Color.decode(newColor);
			setBackground(targetColor);
			mainComponent.setBackground(targetColor);
			lblField.setBackground(targetColor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
