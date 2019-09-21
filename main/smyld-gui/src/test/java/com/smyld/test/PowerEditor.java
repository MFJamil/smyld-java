package com.smyld.test;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
 
/**
 * <dl>
 * <dt><b>Creation date :</b></dt>
 * <dd> 8 oct. 2003 </dd>
 * </dl>
 *
 * @author Pierre LE LANNIC
 */
public class PowerEditor extends JPanel {
	private Set theSet;
	private WordMenuWindow theWordMenu;
	private JTextComponent theTextComponent;
	private Window theOwner;
 
	private static final char[] WORD_SEPARATORS =
			{' ', '\n', '\t', '.', ',', ';', '!', '?', '\'', '(', ')', '[', ']', '\"', '{', '}', '/', '\\', '<','>'};
 
	private Word theCurrentWord;
 
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
			int max = theTextComponent.getText().length() - theWordStart;
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
				toReturn = theTextComponent.getText(theWordStart, theWordLength);
			} catch (BadLocationException e) {
			}
			if (toReturn == null) toReturn = "";
			return toReturn;
		}
	}
 
	private class WordMenuWindow extends JWindow {
		private JList             theList;
		private DefaultListModel  theModel;
		private Point             theRelativePosition;

		public WordMenuWindow() {
			super(theOwner);
			theModel            = new DefaultListModel();
			theRelativePosition = new Point(0, 0);
			loadUIElements();
			setEventManagement();
			toFront();
		}

		private class WordMenuKeyListener extends KeyAdapter {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					onSelected();
				}
			}
		}
 
		private class WordMenuMouseListener extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				if ((e.getButton() == MouseEvent.BUTTON1) && (e.getClickCount() == 2)) {
					onSelected();
				}
			}
		}
 
 
		private void loadUIElements() {
			theList = new JList(theModel) {
				public int getVisibleRowCount() {
					return Math.min(theModel.getSize(), 10);
				}
			};
			theList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			theList.setBackground(new Color(235, 244, 254));
			JScrollPane scrollPane = new JScrollPane(theList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			setContentPane(scrollPane);
		}
 
		private void setEventManagement() {
			theList.addKeyListener(new WordMenuKeyListener());
			theList.addMouseListener(new WordMenuMouseListener());
		}
 
		private void onSelected() {
			String word = (String)theList.getSelectedValue();
			setCurrentTypedWord(word);
		}
 
		public void display(Point aPoint) {
			theRelativePosition = aPoint;
			Point p = theTextComponent.getLocationOnScreen();
			//setLocation(new Point(p.x + aPoint.x, p.y + aPoint.y));
			System.out.println("I must be displayed !!!!");
			setAlwaysOnTop(true);
			toFront();
			
			setVisible(true);
			toFront();
		}
 
		public void move() {
			if (theRelativePosition != null) {
				Point p = theTextComponent.getLocationOnScreen();
				setLocation(new Point(p.x + theRelativePosition.x, p.y + theRelativePosition.y));
			}
		}
 
		public void setWords(String[] someWords) {
			theModel.clear();
			if ((someWords == null) || (someWords.length == 0)) {
				setVisible(false);
				return;
			}
			for (int i = 0; i < someWords.length; i++) {
				theModel.addElement(someWords[i]);
			}
			pack();
			pack();
		}
 
		public void moveDown() {
			if (theModel.getSize() < 1) return;
			int current = theList.getSelectedIndex();
			int newIndex = Math.min(theModel.getSize() - 1, current + 1);
			theList.setSelectionInterval(newIndex, newIndex);
			theList.scrollRectToVisible(theList.getCellBounds(newIndex, newIndex));
		}
 
		public void moveUp() {
			if (theModel.getSize() < 1) return;
			int current = theList.getSelectedIndex();
			int newIndex = Math.max(0, current - 1);
			theList.setSelectionInterval(newIndex, newIndex);
			theList.scrollRectToVisible(theList.getCellBounds(newIndex, newIndex));
		}
 
		public void moveStart() {
			if (theModel.getSize() < 1) return;
			theList.setSelectionInterval(0, 0);
			theList.scrollRectToVisible(theList.getCellBounds(0, 0));
		}
 
		public void moveEnd() {
			if (theModel.getSize() < 1) return;
			int endIndex = theModel.getSize() - 1;
			theList.setSelectionInterval(endIndex, endIndex);
			theList.scrollRectToVisible(theList.getCellBounds(endIndex, endIndex));
		}
 
		public void movePageUp() {
			if (theModel.getSize() < 1) return;
			int current = theList.getSelectedIndex();
			int newIndex = Math.max(0, current - Math.max(0, theList.getVisibleRowCount() - 1));
			theList.setSelectionInterval(newIndex, newIndex);
			theList.scrollRectToVisible(theList.getCellBounds(newIndex, newIndex));
		}
 
		public void movePageDown() {
			if (theModel.getSize() < 1) return;
			int current = theList.getSelectedIndex();
			int newIndex = Math.min(theModel.getSize() - 1, current + Math.max(0, theList.getVisibleRowCount() - 1));
			theList.setSelectionInterval(newIndex, newIndex);
			theList.scrollRectToVisible(theList.getCellBounds(newIndex, newIndex));
		}
	}
 
	public PowerEditor(Set aLexiconSet, JFrame anOwner, JTextComponent aTextComponent) {
		super(new BorderLayout());
		theOwner         = anOwner;
		theTextComponent = aTextComponent;
		theWordMenu      = new WordMenuWindow();
		theSet           = aLexiconSet;
		theCurrentWord   = new Word();
		loadUIElements();
		setEventManagement();
	}
 
	public JTextComponent getTextComponent() {
		return theTextComponent;
	}
 
	private void loadUIElements() {
		add(theTextComponent, BorderLayout.CENTER);
	}
 
	private void setEventManagement() {
		theTextComponent.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				theTextComponent.requestFocus();
			}
		});
		theTextComponent.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, InputEvent.CTRL_MASK), "controlEspace");
		theTextComponent.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_HOME, InputEvent.CTRL_MASK), "home");
		theTextComponent.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_END, InputEvent.CTRL_MASK), "end");
		theTextComponent.getActionMap().put("controlEspace", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("I must be popped ..!");
				//*
				//EditorPopupPanel pnl = EditorPopupPanel.getInstance(theTextComponent);
				//if (pnl!=null)
					//pnl.show(200, 100);
					//*/
				//onControlSpace();

//				   popup.hide();

			}
		});
		theTextComponent.getActionMap().put("home", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				theWordMenu.moveStart();
			}
		});
		theTextComponent.getActionMap().put("end", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				theWordMenu.moveEnd();
			}
		});
		theTextComponent.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (theWordMenu.isVisible()) {
					theWordMenu.setVisible(false);
				}
			}
		});
		theTextComponent.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.isConsumed()) return;
				if (theWordMenu.isVisible()) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						theWordMenu.onSelected();
						e.consume();
					} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						theWordMenu.moveDown();
						e.consume();
					} else if (e.getKeyCode() == KeyEvent.VK_UP) {
						theWordMenu.moveUp();
						e.consume();
					} else if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
						theWordMenu.movePageDown();
						e.consume();
					} else if (e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
						theWordMenu.movePageUp();
						e.consume();
					}
				}
			}
		});
		theOwner.addComponentListener(new ComponentAdapter() {
			public void componentHidden(ComponentEvent e) {
				theWordMenu.setVisible(false);
			}
 
			public void componentMoved(ComponentEvent e) {
				if (theWordMenu.isVisible()) {
					theWordMenu.move();
				}
			}
		});
		theTextComponent.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if (theWordMenu.isVisible()) {
					int beginIndex = e.getOffset();
					int endIndex = beginIndex + e.getLength();
					String newCharacters = theTextComponent.getText().substring(beginIndex, endIndex);
					for (int i = 0; i < WORD_SEPARATORS.length; i++) {
						if (newCharacters.indexOf(WORD_SEPARATORS[i]) != -1) {
							theCurrentWord.setBounds(-1, 0);
							theWordMenu.setWords(null);
							theWordMenu.setVisible(false);
							return;
						}
					}
					theCurrentWord.increaseLength(e.getLength());
					updateMenu();
				}
			}
 
			public void removeUpdate(DocumentEvent e) {
				if (theWordMenu.isVisible()) {
					theCurrentWord.decreaseLength(e.getLength());
					if (theCurrentWord.getLength() == 0) {
						theWordMenu.setWords(null);
						theWordMenu.setVisible(false);
						return;
					}
					updateMenu();
				}
			}
 
			public void changedUpdate(DocumentEvent e) {
			}
		});
	}
 
	private String[] getWords(String aWord) {
		aWord = aWord.trim().toLowerCase();
		Set returnSet = new TreeSet();
		for (Iterator iterator = theSet.iterator(); iterator.hasNext();) {
			String string = (String)iterator.next();
			if (string.startsWith(aWord)) {
				returnSet.add(string);
			}
		}
		return (String[])returnSet.toArray(new String[0]);
	}
 
	private static boolean isWordSeparator(char aChar) {
		for (int i = 0; i < WORD_SEPARATORS.length; i++) {
			if (aChar == WORD_SEPARATORS[i]) return true;
		}
		return false;
	}
 
	private void onControlSpace() {
		JPopupMenu men = null;
		theCurrentWord = getCurrentTypedWord();
		System.out.println("The current word is : " + theCurrentWord);
		if (theCurrentWord.getLength() == 0) return;
		int index = theCurrentWord.getStart();
		Rectangle rect = null;
		try {
			rect = theTextComponent.getUI().modelToView(theTextComponent, index);
			
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		if (rect == null){
			System.out.println("The rect is null !! :(");
			return;
		}else{
			System.out.println("The rect position is : " + rect.toString());
		}
		theWordMenu.display(new Point(rect.x, rect.y + rect.height));
		updateMenu();
		theTextComponent.requestFocus();
	}
 
	private void updateMenu() {
		if (theCurrentWord.getLength() == 0) return;
		String[] words = getWords(theCurrentWord.toString());
		theWordMenu.setWords(words);
	}
 
	private Word getCurrentTypedWord() {
		Word word = new Word();
		int position = theTextComponent.getCaretPosition();
		if (position == 0) return word;
		int index = position - 1;
		boolean found = false;
		while ((index > 0) && (!found)) {
			char current = theTextComponent.getText().charAt(index);
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
 
	private void setCurrentTypedWord(String aWord) {
		theWordMenu.setVisible(false);
		if (aWord != null) {
			if (aWord.length() > theCurrentWord.getLength()) {
				String newLetters = aWord.substring(theCurrentWord.getLength());
				try {
					theTextComponent.getDocument().insertString(theCurrentWord.getEnd(), newLetters, null);
				} catch (BadLocationException e) {
				}
				theCurrentWord.increaseLength(newLetters.length());
			}
		}
		theTextComponent.requestFocus();
		theTextComponent.setCaretPosition(theCurrentWord.getEnd());
		theCurrentWord.setBounds(-1, 0);
	}
 
	private static Set loadLexiconFromFile(File aFile) throws IOException {
		Set returnSet = new TreeSet();
		BufferedReader reader = new BufferedReader(new FileReader(aFile));
		String line = reader.readLine();
		while (line != null) {
			returnSet.add(line);
			line = reader.readLine();
		}
		reader.close();
		return returnSet;
	}
	private static Set loadLexiconRunTime(){
		Set returnSet = new TreeSet();
		String[] words = {"One","Two","Three","Four"};
		for(String curWord:words)returnSet.add(curWord);
		return returnSet;
	}
 
	public static void main(String[] args) {
		try {
			//File lexiconFile = new File("./lexicon.txt");
			//Set lexicon =  loadLexiconFromFile(lexiconFile);
			Set lexicon =  loadLexiconRunTime();
			final JFrame frame = new JFrame("Test");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JTextPane textArea = new JTextPane();
			PowerEditor powerEditor = new PowerEditor(lexicon, frame, textArea);
			JScrollPane scrollpane = new JScrollPane(powerEditor);
			scrollpane.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
													BorderFactory.createBevelBorder(BevelBorder.LOWERED)));
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			frame.setContentPane(scrollpane);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					frame.setSize(500, 500);
					frame.setVisible(true);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
