package com.smyld.test;

import java.awt.EventQueue;
import java.awt.Window;
import java.lang.reflect.Method;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class NonOpaqueWindowTest3 extends JFrame {
	  public NonOpaqueWindowTest3()
	  {
	    super();
	    setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
	    JLabel before = new JLabel();
	    add(before);
	    JLabel after = new JLabel();
	    add(after);
	    JLabel before2 = new JLabel();
	    add(before2);
	    JLabel after2 = new JLabel();
	    add(after2);
	    
	    before.setText("before #setWindowNonOpaque - isOpaque: " + getContentPane().isOpaque());
	    setWindowNonOpaque(this);    
	    after.setText("after #setWindowNonOpaque - isOpaque: " + getContentPane().isOpaque());
	    //is ingored because #setVisible also modifies content pane opacity
	    ((JComponent)getContentPane()).setOpaque(true);
	    
	    setTitle(getClass().getSimpleName());
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(400, 300);
	    setLocationRelativeTo(null);
	 
	    before2.setText("before #setVisible - isOpaque: " + getContentPane().isOpaque());
	    setVisible(true);
	    after2.setText("before #setVisible - isOpaque: " + getContentPane().isOpaque());
	 
	    ((JComponent)getContentPane()).setOpaque(true);
	  }
	  
	  private void setWindowNonOpaque(Window w)
	  {
	    try 
	    {
	      Class<?> c = Class.forName("com.sun.awt.AWTUtilities");
	      Method m = c.getMethod("setWindowOpaque", Window.class, boolean.class);
	      m.invoke(null, w, false);
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    }    
	  }
	 
	  public static void main(String[] args) throws Exception
	  {    
	    EventQueue.invokeLater(new Runnable(){
	      public void run()
	      {
	        JFrame.setDefaultLookAndFeelDecorated(false);
	        new NonOpaqueWindowTest3();
	      }      
	    });
	  }  

}
