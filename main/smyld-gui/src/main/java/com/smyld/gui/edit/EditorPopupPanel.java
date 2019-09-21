package com.smyld.gui.edit;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.smyld.gui.SMYLDTextEditor;

public class EditorPopupPanel extends JPanel{
	
	private SMYLDTextEditor    parentComp;
	private PopList           list ;
	private MyScroll          sp;
	private EditorPopupModel  model;
	
	public EditorPopupPanel(){
		super();
		init();
	}
	public EditorPopupPanel(SMYLDTextEditor comp, EditorPopupModel  model){	
		this.parentComp = comp;
		this.model      = model;
		init();
	}
	
	
	private void init(){
		//setPreferredSize(new Dimension(200,150));
		setLayout(new BorderLayout(0,0));
		sp   = new MyScroll();
		list = new PopList();
		sp.getViewport().add(list);
		sp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	
		add(sp,BorderLayout.CENTER);
		setVisible(false);
	}
	
	public void paintComponent(Graphics gr){
		super.paintComponent(gr);
		/* We can paint some visual effects as a container by spacing the swing component so that we show some of our tricks here */
	}
	class MyScroll extends JScrollPane{
		public MyScroll(){init();}
		/* Doing that will give the required transparency but need first to draw the list correctly (because it should clear the previous paint taking into consideration the underlying text)
		public void paintComponent(Graphics gr){
			Graphics2D g = (Graphics2D)gr;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
			super.paintComponent(gr);
		}
		//*/
		private void init(){
			super.addMouseListener(new MouseAdapter(){
			    public void mouseEntered(MouseEvent e) {
			    	setCursor(new Cursor(Cursor.HAND_CURSOR));
			    }
			});
		}
		

	}
	public void setSelectedIndex(int index){
		list.setSelectedIndex(index);
	} 
	class MyListModel extends DefaultListModel{
		EditorPopupModel  myModel;
		public MyListModel(EditorPopupModel  popModel){
			this.myModel = popModel;
			init();
		}
		private void init(){
			for (int i = 0; i < myModel.getItemsCount(); i++) 
				super.add(i, myModel.getItemAt(i));
		}
		
	}
	class MyListRenderer extends DefaultListCellRenderer{
		public Component getListCellRendererComponent(JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus){
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (value instanceof EditorPopupItem) {
				EditorPopupItem          curItem  = (EditorPopupItem) value;
				EditorPopupItemCategory  curCateg = curItem.getCategory();
				setText(curItem.getName());
				
				// Setting the icon
				if (curItem.getIcon()!=null){
					setIcon((Icon)curItem.getIcon());
				}else if (curCateg.getIcon()!=null){
					setIcon((Icon)curCateg.getIcon());
				}
				setForeground(isSelected?Color.YELLOW:Color.WHITE);
				setBackground(isSelected?new Color(0,0,120):new Color(0,0,50));	
				revalidate();
				//setPreferredSize(preferredSize)
				return this;
			}
			return this;

		}
	}
	public class PopList extends JList{
		Composite     alphComp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f);
		//GradientPaint paint    = new GradientPaint(0,0,new Color(0,0,90),0,getHeight(),new Color(44,173,234));
		GradientPaint paint    = new GradientPaint(0,0,new Color(0,0,0),0,getHeight(),new Color(0,0,120));
		BasicStroke   stroke   = new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		public PopList(){
			init();
		}
		private void init(){
			super.addMouseListener(new MouseAdapter(){
			    public void mouseEntered(MouseEvent e) {
			    	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			    }
			    public void mouseClicked(MouseEvent e) {
			    	if (e.getClickCount()==2) parentComp.itemSelected(list.getSelectedValue());
			    }
			});
			setCellRenderer(new MyListRenderer());
			setModel(new MyListModel(model));
		}
		public void scrollToSearch(String searchKey){
			
			for (int i=0; i<getModel().getSize(); i++) {
				EditorPopupItem curItem =(EditorPopupItem)getModel().getElementAt(i);
				
				if ((curItem!=null)&&(curItem.getName().toLowerCase().matches("^"+searchKey.toLowerCase()+".*"))) {
					setSelectedIndex(i);
					ensureIndexIsVisible(i);
					
					break;
				}
			}
			
		}
		public void paintComponent(Graphics gr){
			Graphics2D g = (Graphics2D)gr;
			Composite old = g.getComposite();
			g.clearRect(0, 0, getWidth(), getHeight());
			g.setComposite(alphComp);
			g.setPaint(paint);
			g.setColor(Color.BLACK);
			g.fillRect(1,1, getWidth()-1,getHeight()-1);
			
			g.setStroke(stroke);
			g.drawRect(1,1, getWidth()-1,getHeight()-1);
			//g.setComposite(old);
			setForeground(Color.WHITE);
			super.paintComponent(gr);
		}
	}
	
	public Object getSelected(){
		return list.getSelectedValue();
	}
	// This code will do the scrolling on the list to show the selected items holding the same name as the given text or at least matching it
	public void scrollOnSearchText(String text){
		list.scrollToSearch(text);
	}
	public void moveDown(){
		if (list.getSelectedIndex()<list.getModel().getSize()-1)
			setSelectedItem(list.getSelectedIndex()+1);
		else
			setSelectedItem(0);
		
	}
	private void setSelectedItem(int id){
		list.setSelectedIndex(id);
		list.ensureIndexIsVisible(id);
		
	}
	
	public void moveUp(){
		if (list.getSelectedIndex()>0)
			setSelectedItem(list.getSelectedIndex()-1);
		else
			setSelectedItem(list.getModel().getSize()-1);
	}
	
	public void movePageDown(){
		int vis = list.getVisibleRowCount();
		if (vis>0){
			if (list.getSelectedIndex()+vis<=list.getModel().getSize()-1)
				setSelectedItem(list.getSelectedIndex()+vis);
			else
				setSelectedItem(list.getModel().getSize()-1);
		}
	}
	
	public void movePageUp(){
		int vis = list.getVisibleRowCount();
		if (vis>0){
			if (list.getSelectedIndex()-vis>=0)
				setSelectedItem(list.getSelectedIndex()-vis);
			else
				setSelectedItem(0);
		}
	}

	
	
}
