package org.smyld.gui.edit;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JWindow;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;

public class EditorPopupPanel extends JPanel{
	JPanel    contentsPan;
	Component parentComp;
	Window    owner;
	Popup     popup;
	boolean   isShown;
	public PopList   list ;
	JWindow   wd;
	JDialog   dlg;
	MyScroll  sp;
	public EditorPopupPanel(){
		super();
		init();
	}
	public EditorPopupPanel(Window owner){	
		this.owner = owner;
		init();
	}
	public EditorPopupPanel(Component comp){	
		this.parentComp = comp;
		init();
	}
	
	private void init(){
		setBackground(Color.BLUE);
		setPreferredSize(new Dimension(200,100));
		setLayout(new BorderLayout());
		sp = new MyScroll();
		list = new PopList();
		String[] words = {"one","two","three","four","five","six","seven","eight","nine","ten"};
		list.setListData(words);
		list.setOpaque(false);
		list.setBackground(new Color(255,255,255,0));
		sp.getViewport().setOpaque(false);
		sp.getViewport().setBackground(new Color(255,255,255,0));
		sp.getViewport().add(list);
		add(sp,BorderLayout.CENTER);
		setVisible(false);
		setFocusable(true);
		
		
	
	}
	/*
	public void setVisible(boolean vis){
		if (popup!=null){
			if (vis){
				popup.show();
			}else{
				popup.hide();
			}
		}
		isShown = vis;
		//super.setVisible(vis);
			
	}
	
	public boolean isVisible(){
		return isShown;
	}
	*/
	public void hideMe(){
		popup.hide();
	}
	
	public void paintComponent(Graphics gr){
		
		super.paintComponent(gr);
		/*
		Graphics2D g = (Graphics2D)gr;
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.2f));
		super.paintComponent(gr);
		
		g.setPaint(new GradientPaint(0,0,Color.BLUE,0,getHeight(),new Color(44,173,234)));
		g.fillRect(1,1, getWidth()-1,getHeight()-1);
		g.setColor(Color.DARK_GRAY);
		//g.setPaint(new GradientPaint(0,0,Color.LIGHT_GRAY,0,getHeight(),Color.DARK_GRAY));
		g.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g.drawRect(1,1, getWidth()-1,getHeight()-1);
		*/
		
	}
    private Popup getPopup(int x,int y) {
        Popup oldPopup = popup;
        if (oldPopup != null) 
            oldPopup.hide();
        PopupFactory popupFactory = PopupFactory.getSharedInstance();
        return popupFactory.getPopup(parentComp, this, x, y);
    }
    public void setParent(Component comp){
    	this.parentComp = comp;
    }

    /**
     * Key used to indicate a light weight popup should be used.
     */
    static final int LIGHT_WEIGHT_POPUP   = 0;

    /**
     * Key used to indicate a medium weight Popup should be used.
     */
    static final int MEDIUM_WEIGHT_POPUP  = 1;

    /*
     * Key used to indicate a heavy weight Popup should be used.
     */
    static final int HEAVY_WEIGHT_POPUP   = 2;

	
	
	public void setContents(JPanel panel){
	}
	
	public void show_(int x,int y){
		//setLocation(x,y);
		//setVisible(true);
		//requestFocus();
		System.out.println("Editor Popup Panel. Show is called ....");
		popup = getPopup(x, y);
		
		System.out.println("Popup class name is  : "  +  popup.getClass().getName());
		setVisible(true);
		
		System.out.println("Requesting the focus ....");
		list.requestFocusInWindow();
		list.setSelectedIndex(0);
	}
	
	/*
	public boolean requestFocusInWindow(){
		sp.requestFocusInWindow();
		return list.requestFocusInWindow();
		
	}*/
	
	public void showDlg(int x,int y){
		if (dlg==null){
			dlg = new JDialog(SwingUtilities.getWindowAncestor(parentComp));
			//wd.getContentPane().setLayout(new BorderLayout());
			setVisible(true);
			//wd.getContentPane().add(this);
			dlg.setLayout(new BorderLayout());
			dlg.add(this,"Center");
			dlg.setAlwaysOnTop(true);
			dlg.setFocusable(true);
			dlg.addFocusListener(new FocusListener(){

				public void focusGained(FocusEvent e) {
					System.out.println("Popped Window Focus gained");
					
				}

				public void focusLost(FocusEvent e) {
					System.out.println("Popped Window Focus Lost");
					
				}
				
			});
		}
		dlg.setSize(100,150);
		dlg.setLocation(x,y);
		//Thread.dumpStack();
		dlg.requestFocusInWindow();
		dlg.setVisible(true);
		
		
	}
	
	public void show(int x,int y){
		
		if (wd==null){
			Window pareWd = SwingUtilities.getWindowAncestor(parentComp);
			
			wd = new JWindow(pareWd);
			//wd.getContentPane().setLayout(new BorderLayout());
			setVisible(true);
			//wd.getContentPane().add(this);
			wd.setLayout(new BorderLayout());
			wd.add(this,"Center");
			wd.setAlwaysOnTop(true);
			wd.setFocusable(true);
			wd.addFocusListener(new FocusListener(){

				public void focusGained(FocusEvent e) {
					System.out.println("Popped Window Focus gained");
					
				}

				public void focusLost(FocusEvent e) {
					System.out.println("Popped Window Focus Lost");
					
				}
				
			});
			wd.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		}
		wd.setSize(100,150);
		wd.setLocation(x,y);
		//Thread.dumpStack();
		wd.requestFocusInWindow();
		wd.setVisible(true);
		
		
	}
	public Window getWindow(){
		return wd;
	}
	
	public static EditorPopupPanel getInstance(Component comp){
		
		Container cont = comp.getParent();
		if (cont != null){
			if (!(cont instanceof Window))
				while(!((cont=cont.getParent()) instanceof Window));
			return new EditorPopupPanel((Window)cont);
		}
		return null;
	}
	class MyScroll extends JScrollPane{
		/*
		public void paintComponent(Graphics gr){
			Graphics2D g = (Graphics2D)gr;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
			super.paintComponent(gr);
		}
		*/

	}
	public class PopList extends JList{
		
		public PopList(){
			init();
		}
		private void init(){
			super.addMouseListener(new MouseAdapter(){
				public void mouseReleased(MouseEvent e){
					processMouseReleasedEvent(e);
					requestFocus();
				}
				public void mousePressed(MouseEvent e){
					processMousePressedEvent(e);
					
				}
			});
		}
		private void processMouseReleasedEvent(MouseEvent e){
			
		}
		private void processMousePressedEvent(MouseEvent e){
			if(e.getButton()==MouseEvent.BUTTON1){
				if (e.getClickCount()==2){
					//hideMe();
				}
			}
			
		}
		
		public void paintComponent(Graphics gr){
			
			setForeground(Color.WHITE);
			Graphics2D g = (Graphics2D)gr;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
			g.setPaint(new GradientPaint(0,0,Color.BLUE,0,getHeight(),new Color(44,173,234)));
			g.fillRect(1,1, getWidth()-1,getHeight()-1);
			g.setColor(Color.DARK_GRAY);
			//g.setPaint(new GradientPaint(0,0,Color.LIGHT_GRAY,0,getHeight(),Color.DARK_GRAY));
			g.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
			g.drawRect(1,1, getWidth()-1,getHeight()-1);
			super.paintComponent(gr);
			
		}
	}
	
	public Object onSelected(){
		return list.getSelectedValue();
	}
	
	public void moveDown(){
		if (list.getSelectedIndex()<list.getModel().getSize())
			setSelectedItem(list.getSelectedIndex()+1);
		
	}
	private void setSelectedItem(int id){
		list.setSelectedIndex(id);
		list.ensureIndexIsVisible(id);
		
	}
	
	public void moveUp(){
		if (list.getSelectedIndex()>0)
			setSelectedItem(list.getSelectedIndex()-1);
	}
	
	public void movePageDown(){
		// To be implemented
	}
	
	public void movePageUp(){
		// To be implemented
	}

	
	
}

