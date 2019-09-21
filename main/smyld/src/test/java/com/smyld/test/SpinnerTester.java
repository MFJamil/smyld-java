package com.smyld.test;

import java.awt.BorderLayout;
import java.math.BigDecimal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class SpinnerTester {

	
	public SpinnerTester(){
		testSpinner();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		new SpinnerTester();
	}
	
	private void testSpinner(){
		JFrame frame = new JFrame("Testing Spinner");
		frame.setSize(200,80);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel(new BorderLayout());
		int precision = 4;
		MySpinnerModel model = new MySpinnerModel(
				/*
				new BigDecimal("60.0000",new MathContext(precision) ),
				new BigDecimal("20.0000",new MathContext(precision)),
				new BigDecimal("100.0000",new MathContext(precision)),
				new BigDecimal("0.0001",new MathContext(precision)));
				//*/
				//*
				new BigDecimal("60.0000"),
				new BigDecimal("20.0000"),
				new BigDecimal("100.0000"),
				new BigDecimal(".0001"));
				//*/
		
		JSpinner spin = new JSpinner(model);
		((JSpinner.NumberEditor)spin.getEditor()).getFormat().setMaximumFractionDigits(4);
		mainPanel.add(spin,BorderLayout.CENTER);
		frame.add(mainPanel);
		frame.setVisible(true);
	}
	
	class MySpinnerModel extends SpinnerNumberModel{
		BigDecimal value;
		BigDecimal min;
		BigDecimal max;
		BigDecimal step;
	    public MySpinnerModel(BigDecimal value,BigDecimal min,BigDecimal max,BigDecimal step){
	    	this.value = value;
	    	this.max   = max;
	    	this.min   = min;
	    	this.step  = step;
	    }
		public Object getNextValue() {
        	BigDecimal newValue = (value).add(step);
        	if (newValue.compareTo(max)>0) return max;
        	return newValue;
	    }
        public Object getPreviousValue() {
        	BigDecimal newValue = (value).subtract(step);
        	if (newValue.compareTo(min)<=0) return min;
        	return newValue;
        }
        public Object getValue(){return value;}
        public void setValue(Object newValue){
        	System.out.println(newValue.toString());
        	if ((newValue == null) || !(newValue instanceof Number)) {
        	    throw new IllegalArgumentException("illegal value");
        	}
        	if (!newValue.equals(this.value)) {
        	    this.value = (BigDecimal)newValue;
        	    fireStateChanged();
        	}
        	
        }
        public Number getNumber() {
        	return value;
        }

	}

}
