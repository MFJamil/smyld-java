package org.smyld.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class BlurredButton {
	static JButton button2; 
    public static void main(String[] args) {

        UIManager.put("ButtonUI", "com.three60t.runtime.init.client.CustomButtonUI");

        JFrame frame = new JFrame("Button test");
        frame.getContentPane().setBackground(Color.black);
        
        JButton button = new JButton("Test Enabled");
        button.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		button2.setEnabled(!button2.isEnabled());
        	}
        });
        frame.add(button, BorderLayout.NORTH);

        button2 = new JButton("Test Disabled");
        button2.setEnabled(false);
        frame.add(button2, BorderLayout.SOUTH);
        
        frame.pack();
        frame.setSize(200, 100);
        frame.setVisible(true);
    }
}

