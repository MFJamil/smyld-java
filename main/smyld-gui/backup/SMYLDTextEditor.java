package com.smyld.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import com.smyld.gui.edit.EditorPopupPanel;

public class SMYLDTextEditor extends JTextPane {
	SMYLDTextEditor   instance;
	EditorPopupPanel popPan;
	//JList            list   = new JList();
	//JScrollPane      sp     = new JScrollPane();
	//Window           owner;
	//int              curSelIndex = 0;
	private static final char[] WORD_SEPARATORS =
	{' ', '\n', '\t', '.', ',', ';', '!', '?', '\'', '(', ')', '[', ']', '\"', '{', '}', '/', '\\', '<','>'};

	private Word curWord;
	boolean showPopup;
	public SMYLDTextEditor(){
		init();
	}
	private void init(){
		instance = this;
		//popPan = EditorPopupPanel.getInstance(instance);
		//popPan   = new EditorPopupPanel(this);   
		curWord  = new Word();
		setEventManagement();
		
		String[] words = {"one","two","three","four","five","six","seven","eight","nine","ten"};
		/*
		list.setListData(words);
        list.setOpaque(false);
        list.setAutoscrolls(true);
        list.setBackground(new Color(0,0,0,0));
        list.setForeground(Color.DARK_GRAY);
		sp.getViewport().add(list);
		*/
		
	}
	
	public void paintComponent(Graphics gr){
		Graphics2D g = (Graphics2D)gr;
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		//if (showPopup)
			//processWordListPopup(g);
			//processWordListPopup();
		
	}
	
	private void setEventManagement() {
		addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				requestFocus();
			}
		});
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE , InputEvent.CTRL_MASK), "controlEspace");
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_HOME  , InputEvent.CTRL_MASK), "home");
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_END   , InputEvent.CTRL_MASK), "end");
		getActionMap().put("controlEspace", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("I must be popped ..!");
				processWordListPopup();
				instance.repaint();
				showPopup = true;
				//instance.repaint();
			}
		});
		getActionMap().put("home", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				//theWordMenu.moveStart();
			}
		});
		getActionMap().put("end", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				//theWordMenu.moveEnd();
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				super.mouseClicked(e);
			    if((popPan!=null)&& (popPan.isVisible())){
					popPan.setVisible(false);
				}
			}
		});
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.isConsumed()) return;
				if ((popPan!=null)&&(popPan.isVisible())){
					popPan.list.dispatchEvent(e);
				}
				/*
				if (showPopup){
					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						curSelIndex++;
						repaint();
					}else if (e.getKeyCode() == KeyEvent.VK_UP) {
						curSelIndex--;
						repaint();
					}else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						String insertValue = (String)popPan.list.getSelectedValue();
						try {
							Word curWrd = getCurrentTypedWord();
							getDocument().remove(curWrd.getStart(), curWrd.getLength());
							getDocument().insertString(curWrd.getStart(), insertValue, null);
							//getDocument().
							
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						showPopup = false;
						popPan.setVisible(false);
						remove(popPan);
						repaint();

					}
				}else{
					showPopup = false;
					repaint();
				}
				*/
				//if (popPan.isVisible()) {
					/*
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						popPan.onSelected();
						e.consume();
					} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						popPan.moveDown();
						e.consume();
					} else if (e.getKeyCode() == KeyEvent.VK_UP) {
						popPan.moveUp();
						e.consume();
					} else if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
						popPan.movePageDown();
						e.consume();
					} else if (e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
						popPan.movePageUp();
						e.consume();
					}
					*/
				//}
			}
		});
		getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if((popPan!=null)&& (popPan.isVisible())) {
					int beginIndex = e.getOffset();
					int endIndex = beginIndex + e.getLength();
					String newCharacters = getText().substring(beginIndex, endIndex);
					for (int i = 0; i < WORD_SEPARATORS.length; i++) {
						if (newCharacters.indexOf(WORD_SEPARATORS[i]) != -1) {
							curWord.setBounds(-1, 0);
							//popPan.setWords(null);
							//popPan.setVisible(false);
							//showPopup = false;
							repaint();
							return;
						}
					}
					curWord.increaseLength(e.getLength());
					//updateMenu();
				}
			}
 
			public void removeUpdate(DocumentEvent e) {
				showPopup = false;
				if((popPan!=null)&& (popPan.isVisible())){
					curWord.decreaseLength(e.getLength());
					if (curWord.getLength() == 0) {
						//popPan.setWords(null);
						popPan.setVisible(false);
						//showPopup = false;
						repaint();
						return;
					}
					//updateMenu();
				}
			}
 
			public void changedUpdate(DocumentEvent e) {
			}
		});
	}
	private void processWordListPopup_self(Graphics2D g){
		/*
		JPanel component = new JPanel();
		component.setPreferredSize(new Dimension(100,200));
		component.setOpaque(true);
		component.setBackground(Color.BLACK);
		*/
		
		//list.setOpaque(true);
		//list.setBackground(new Color(255,255,255,0));
		
		Rectangle rect = null;
		curWord = getCurrentTypedWord();
		if (curWord.getLength() == 0) return;
		int index = curWord.getStart();
		try {
			rect = getUI().modelToView(this, index);
			
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		if (showPopup){

			//*
			Point p = convertScreenLocationToParent(this,rect.x, rect.y);
			//g.setPaint(new GradientPaint(50,50,new Color(100,100,100,80),50,250,new Color(150,150,150,80)));
	        int wd = 100;
	        int hi = 120;
			Point crPos = getCaret().getMagicCaretPosition();
			g.setPaint(new GradientPaint(crPos.x, crPos.y,new Color(0,0,0,150),crPos.x,crPos.y+hi,new Color(150,150,150,150)));
			g.fillRect(crPos.x, crPos.y,wd,hi);
	        //g.setColor(new Color(200,200,200,80));
			g.setColor(Color.LIGHT_GRAY);
	        g.drawRect(crPos.x, crPos.y,wd,hi);
	        //*/
	        //list.setLocation(54,54);
	        //list.setSize(wd-8,hi-8);
			/*
	        list.setVisible(true);
			BufferedImage bm = new BufferedImage(wd,hi,BufferedImage.TYPE_INT_ARGB);
			list.setSelectedIndex(curSelIndex);
			//list.paint(bm.createGraphics());
			
			sp.getViewport().setOpaque(false);	
			sp.getViewport().setSize(wd-8,hi-8);
			sp.getViewport().setVisible(true);
			sp.getViewport().paint(bm.createGraphics());
			*/
			//Image compImage = list.createImage(92, 192);
			//g.drawImage(bm, crPos.x+4, crPos.y+4, null);
		}
		
        
		//*/
        
        
        //component.setVisible(false);
        
        
        //component.setVisible(true);
		
	}

	private void processWordListPopup_(Graphics2D g){
		
		JPanel component = new JPanel();
		component.setPreferredSize(new Dimension(100,200));
		component.setOpaque(true);
		component.setBackground(Color.BLACK);
		Rectangle rect = null;
		curWord = getCurrentTypedWord();
		
		if (curWord.getLength() == 0) return;

		int index = curWord.getStart();
		try {
			rect = getUI().modelToView(this, index);
			
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		Point p = convertScreenLocationToParent(this,rect.x, rect.y);
        component.setLocation(p.x, p.y);
        //component.setVisible(false);
        add(component);
        
        //component.setVisible(true);
		
	}
    static Point convertScreenLocationToParent(Container parent,int x, int y) {
        for (Container p = parent; p != null; p = p.getParent()) {
            if (p instanceof Window) {
                Point point = new Point(x, y);

                SwingUtilities.convertPointFromScreen(point, parent);
                return point;
            }
        }
        throw new Error("convertScreenLocationToParent: no window ancestor");
    }
	
	private void processWordListPopup(){
		curWord = getCurrentTypedWord();
		if (curWord.getLength() == 0) return;
		if (popPan==null)
			popPan = new EditorPopupPanel(this);
		if(!popPan.isVisible()){
			showPopup = true;
			int index = curWord.getStart();
			Rectangle rect = null;
			try {
				rect = getUI().modelToView(this, index);
				
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			if (rect == null){
				System.out.println("The rect is null !! :(");
				return;
			}else{
				System.out.println("The rect position is : " + rect.toString());
			}
			popPan.setLocation(rect.x + rect.width,rect.y+rect.height);
			popPan.setSize(100,120);
			popPan.setVisible(true);
			//popPan.paintImmediately(new Rectangle2D.Float(rect.x,rect.y,100,120).getBounds());
			add(popPan);
			System.out.println("Finished creating the popup ...!!!!");
			//KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
			//popPan.list.requestFocusInWindow();
		}
		
		/*
		popPan.show(this.getLocationOnScreen().x + rect.x,this.getLocationOnScreen().y+ rect.y + rect.height);
		
		popPan.requestFocusInWindow();
		popPan.list.requestFocusInWindow();
		//transferFocus();
		//KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent(popPan.list);
		
		
		System.out.println("Focus Owner : " + KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner().toString());
		
		System.out.println("Focus Window Owner : " + KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow().toString());
		//KeyboardFocusManager.getCurrentKeyboardFocusManager().enqueueKeyEvents(time.get(), toFocus);
		//FocusManager.getCurrentKeyboardFocusManager(). addKeyEventDispatcher(dispatcher)upFocusCycle(popPan.getWindow());
		//popPan.requestFocusInWindow();
			*/
	}

    private void processWordListPopup_(){
		curWord = getCurrentTypedWord();
		if (curWord.getLength() == 0) return;
		if (!popPan.isVisible()){
			int index = curWord.getStart();
			Rectangle rect = null;
			try {
				rect = getUI().modelToView(this, index);
				
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			if (rect == null){
				System.out.println("The rect is null !! :(");
				return;
			}else{
				System.out.println("The rect position is : " + rect.toString());
			}
			
			popPan.show(this.getLocationOnScreen().x + rect.x,this.getLocationOnScreen().y+ rect.y + rect.height);
			
			popPan.requestFocusInWindow();
			popPan.list.requestFocusInWindow();
			//transferFocus();
			//KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent(popPan.list);
			
			
			System.out.println("Focus Owner : " + KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner().toString());
			
			System.out.println("Focus Window Owner : " + KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow().toString());
			//KeyboardFocusManager.getCurrentKeyboardFocusManager().enqueueKeyEvents(time.get(), toFocus);
			//FocusManager.getCurrentKeyboardFocusManager(). addKeyEventDispatcher(dispatcher)upFocusCycle(popPan.getWindow());
			//popPan.requestFocusInWindow();
			
			
		}
		//transferFocus();
		//updateMenu();
		
		//requestFocus();
		
	}
	public void setOwner(Window owner){
		owner.addComponentListener(new ComponentAdapter() {
			public void componentHidden(ComponentEvent e) {
				popPan.setVisible(false);
			}
 
			public void componentMoved(ComponentEvent e) {
				if (popPan.isVisible()) {
					//popPan.move();
				}
			}
		});

	}
	private static boolean isWordSeparator(char aChar) {
		for (int i = 0; i < WORD_SEPARATORS.length; i++) {
			if (aChar == WORD_SEPARATORS[i]) return true;
		}
		return false;
	}
	
	private Word getCurrentTypedWord() {
		Word word = new Word();
		int position = getCaretPosition();
		if (position == 0) return word;
		int index = position - 1;
		boolean found = false;
		while ((index > 0) && (!found)) {
			char current = getText().charAt(index);
			if (isWordSeparator(current)) {
				found = true;
				index++;
			} else {
				index--;
			}
		}
		word.setBounds(index, position - index);
		return word;
	}
	
	
	
	
	
	public static void main(String[] args){
		JFrame testFrame = new JFrame("SMYLD Text Editor");
		testFrame.setSize(800,600);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SMYLDTextEditor editor = new SMYLDTextEditor();
		testFrame.getContentPane().add(editor);
		testFrame.setLocationRelativeTo(null);
		//editor.setOwner(testFrame);
		testFrame.setVisible(true);
		
	}
	private class Word {
		private int theWordStart;
		private int theWordLength;
 
		public Word() {
			theWordStart = -1;
			theWordLength = 0;
		}
 
		public void setBounds(int aStart, int aLength) {
			theWordStart = Math.max(-1, aStart);
			theWordLength = Math.max(0, aLength);
			if (theWordStart == -1) theWordLength = 0;
			if (theWordLength == 0) theWordStart = -1;
		}
 
		public void increaseLength(int newCharLength) {
			int max = instance.getText().length() - theWordStart;
			theWordLength = Math.min(max, theWordLength + newCharLength);
			if (theWordLength == 0) theWordStart = -1;
		}
 
		public void decreaseLength(int removedCharLength) {
			theWordLength = Math.max(0, theWordLength - removedCharLength);
			if (theWordLength == 0) theWordStart = -1;
		}
 
		public int getStart() {
			return theWordStart;
		}
 
		public int getLength() {
			return theWordLength;
		}
 
		public int getEnd() {
			return theWordStart + theWordLength;
		}
 
		public String toString() {
			String toReturn = null;
			try {
				toReturn = instance.getText(theWordStart, theWordLength);
			} catch (BadLocationException e) {
			}
			if (toReturn == null) toReturn = "";
			return toReturn;
		}
	}
 	
}

