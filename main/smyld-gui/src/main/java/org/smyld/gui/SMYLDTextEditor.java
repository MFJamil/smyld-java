package org.smyld.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.Position;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;

import org.smyld.gui.edit.DocTextPart;
import org.smyld.gui.edit.EditorPopupItem;
import org.smyld.gui.edit.EditorPopupModel;
import org.smyld.gui.edit.EditorPopupPanel;
import org.smyld.gui.edit.EditorUserField;
import org.smyld.gui.edit.Formula;
import org.smyld.gui.edit.DocTextPart.Type;
import org.smyld.gui.event.ActionHandler;
import org.smyld.resources.Resource;
//import org.smyld.test.EditPopModel;

public class SMYLDTextEditor extends JTextPane {
	private SMYLDTextEditor     instance;
	private EditorPopupPanel   popPan;
	private Word               curWord;
	private EditorPopupModel   model;
	private int                popWidth     = 150;
	private int                popHeight    = 200;
	private EditMode           emode        = EditMode.Normal;
	Vector<EditorUserField>    userFields   = new Vector<EditorUserField>();
	Vector<DocTextPart>        formHlParts  = new Vector<DocTextPart>();
	private int                curUFieldInd = 0;
	private int                curUFieldPos = 0;
	private Formula            curFormula; 
	private Vector<Formula>    formulaCashe = new Vector<Formula>();
	private SimpleAttributeSet userFieldsStyle;
	private SimpleAttributeSet formulasStyle;
	private SimpleAttributeSet templateStyle;
	private SimpleAttributeSet formulaFieldStyle;
	private SimpleAttributeSet defaultTextStyle;
	
	private StyledDocument     stDoc;
	public SMYLDTextEditor(EditorPopupModel  model){
		this.model = model;
		init();
	}
	private void init(){
		instance = this;
		curWord  = new Word();
		stDoc    = (StyledDocument)getDocument();
		setEventManagement();
		activateAttributes();
	}
	private void activateAttributes(){
		defaultTextStyle = new SimpleAttributeSet();
		StyleConstants.setFontFamily(defaultTextStyle, "Monospaced");
		StyleConstants.setFontSize(defaultTextStyle, 12);
		StyleConstants.setBackground(defaultTextStyle, Color.WHITE);
		StyleConstants.setForeground(defaultTextStyle, Color.BLACK);
		StyleConstants.setBold(defaultTextStyle, false);
		StyleConstants.setItalic(defaultTextStyle, false);

		userFieldsStyle = new SimpleAttributeSet();
		StyleConstants.setFontFamily(userFieldsStyle, "Monospaced");
		StyleConstants.setFontSize(userFieldsStyle, 12);
		StyleConstants.setBackground(userFieldsStyle, new Color(232,242,254));
		StyleConstants.setForeground(userFieldsStyle, Color.BLUE);
		StyleConstants.setBold(userFieldsStyle, false);
		StyleConstants.setItalic(userFieldsStyle, false);

		formulaFieldStyle = new SimpleAttributeSet();
		StyleConstants.setFontFamily(formulaFieldStyle, "Monospaced");
		StyleConstants.setFontSize(formulaFieldStyle, 12);
		StyleConstants.setBackground(formulaFieldStyle, Color.WHITE);
		StyleConstants.setForeground(formulaFieldStyle, Color.BLUE);
		StyleConstants.setBold(formulaFieldStyle, true);
		StyleConstants.setItalic(formulaFieldStyle, false);
		
		formulasStyle = new SimpleAttributeSet();
		StyleConstants.setFontFamily(formulasStyle, "Monospaced");
		StyleConstants.setFontSize(formulasStyle, 12);
		StyleConstants.setBackground(formulasStyle, Color.WHITE);
		StyleConstants.setForeground(formulasStyle, Color.RED);
		StyleConstants.setBold(formulasStyle, true);
		StyleConstants.setItalic(formulasStyle, false);

		templateStyle = new SimpleAttributeSet();
		StyleConstants.setFontFamily(templateStyle, "Monospaced");
		StyleConstants.setFontSize(templateStyle, 12);
		StyleConstants.setAlignment(templateStyle, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setBackground(templateStyle, Color.WHITE);
		StyleConstants.setForeground(templateStyle, Color.BLACK);
		StyleConstants.setBold(templateStyle, false);
		StyleConstants.setItalic(templateStyle, false);
	}
	

	public void paintComponent(Graphics gr){
		Graphics2D g = (Graphics2D)gr;
		super.paintComponent(g);
		if (userFields.size()>0){
			paintEditorFields(g); 
		}
		if((popPan!=null)&&(popPan.isVisible())){
			paintShadow(g);
		}
		// Code be some code for doing visual affects like dropping the shadow of the dialog
		/*
		 if (${name:var} instanceof ${type}) {
			${type} ${new_name} = (${type})${name};
			${cursor}
		}
		*/
	}
	
	private void paintShadow(Graphics2D g){
		Rectangle rect = popPan.getBounds();
		int steps = 5;
		int trans = 30;
		int block = trans/steps;
		for (int i = 0; i < steps; i++) {
			rect.setLocation(rect.x+1, rect.y+1);
			g.setColor(new Color(0,0,0,(trans-(i*block))));
			g.fill(rect);
		}
	}
	
	private void setEventManagement() {
		/*
		addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				requestFocus();
			}
		});
		//*/
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE , InputEvent.CTRL_MASK), "controlEspace");
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_HOME  , InputEvent.CTRL_MASK), "home");
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_END   , InputEvent.CTRL_MASK), "end");
		getActionMap().put("controlEspace", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("I must be popped ..!");
				processWordListPopup();
				instance.repaint();
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
					hidePopup();
				}
			}
		});
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				
				if (e.isConsumed()) return;
				// Special handling for the formula filling
				switch(emode){
					case Formula:case Template:
						if (e.getKeyCode() == KeyEvent.VK_TAB) {
							moveToNextUserField(true);
							e.consume();
						}else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							if (emode==EditMode.Formula){ 
								closeFormula();
								e.consume();
							}
							if (emode==EditMode.Template) closeTemplate();
							
						}
						
						break;
					default:
						if (e.getKeyCode() == KeyEvent.VK_TAB) {
							if (checkFormulaActivation(getCaretPosition())) e.consume();
						}
						
				}
				if ((popPan!=null)&&(popPan.isVisible())){
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						itemSelected(popPan.getSelected());
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
					} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						if (popPan.isVisible())
							hidePopup();
					} else if (e.getKeyCode() == KeyEvent.VK_TAB) {
						if (checkFormulaActivation(getCaretPosition())) e.consume();
						
					}else{
						scrollPopupToTypedWord();
					}
				}
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
							repaint();
							return;
						}
					}
					//doFilterExtraChars();
					curWord.increaseLength(e.getLength());
					//updateMenu();
				}
			}
			public void removeUpdate(DocumentEvent e) {
				if((popPan!=null)&& (popPan.isVisible())){
					curWord.decreaseLength(e.getLength());
					if (curWord.getLength() == 0) {
						//popPan.setWords(null);
						popPan.setVisible(false);
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
	
	private void scrollPopupToTypedWord(){
		
		Word curWord = getCurrentTypedWord();
		if ((curWord!=null)&&(curWord.getLength()>0))
			popPan.scrollOnSearchText(curWord.toString());
		else
			popPan.setSelectedIndex(0);
	}
	public void itemSelected(Object selectedItem){
		if (selectedItem instanceof String) {
			String newValue = (String) selectedItem;
			processWordChange(newValue);
		}else if (selectedItem instanceof EditorPopupItem) {
			EditorPopupItem curItem = (EditorPopupItem) selectedItem;
			if ((curItem.getCategory()!=null)&&(curItem.getCategory().doNeedProcessing())){
				processPopupContents(curItem);
			}else{
				processWordChange(curItem.getContents());
			}
		}
	}

	// We should not close the formula by setting it to null, we should cash the formula to enable the user to change the value later on and the formula should be able to calculate once again
	private void closeFormula(){
		closeTemplate();
		//highlightFormulaFields(curFormula.getFieldPos().getOffset());
		formHlParts.removeAllElements();
		formulaCashe.add(curFormula);
		curFormula = null;
	}
	private void closeTemplate(){
		emode = EditMode.Normal;
		deHightlightUserFields();
		userFields.removeAllElements();
	}
	
	// This code will check if the user wants to recalculate the formula or not
	private boolean  checkFormulaActivation(int curPos){
		if (formulaCashe.size()>0){
			// we start the check in reverse order because the user might deleted any of the cashed formulas so it is better to start from the last entered formula upwards
			for (int i = 0; i < formulaCashe.size(); i++) {
				Formula formula = formulaCashe.get(formulaCashe.size()-i-1);
				DocTextPart formParam = formula.getParamAtPos(curPos); 
				if (formParam!=null){
					checkFormulaFieldValueChange(formParam,formula);
					updateFormula(formula);
					return true;
				}
			}
		}
		return false;
	}
	private void hidePopup(){
		popPan.setVisible(false);
		remove(popPan);
		repaint();
	}
	private void processPopupContents(EditorPopupItem item){
		System.out.println("Should be processing the template now ... ");
		// will call the adding for the normal contents for now, this should be replaced with the processed contents
		int crtPos =  getCaretPosition();
		processWordChange(processEL(item.getContents()));
		// Need to set the position value for the formula in this case
		if (curFormula!=null){
			int formIndex = getText().indexOf(curFormula.getFieldValue(),crtPos);
			if (formIndex!=-1) {
				stDoc.setCharacterAttributes(formIndex, curFormula.getFieldValue().length(), formulasStyle, true);
				curFormula.setFieldPos(doCreatePosition(formIndex));
			}
			
		}
	}
	private Position doCreatePosition(int index){
		try {
			return getDocument().createPosition(index);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String processEL(String value){
		curFormula = null;
		userFields.removeAllElements();
		formHlParts.removeAllElements();
		StringBuilder sb = new StringBuilder(value);
		int start = 0;
		int end   = 0;
		while((start = value.indexOf("${"))!=-1){
			end = value.indexOf("}",start+2);
			if (end!=-1){
				String elVariable = value.substring(start+2,end);
				String varValue =  getELValue(elVariable);
				if (varValue!=null){
					sb.replace(start, end+1, varValue);
					value = sb.toString();
				}
			}
		}
		if (curFormula!=null){
			
		loop:	for(DocTextPart curPart:formHlParts){
				if (!curFormula.containsParameter(curPart.getFieldID())){
					formHlParts.remove(curPart);
					break loop;
				}
		
			}
			curFormula.importDocParams(formHlParts);
		}
		
		return sb.toString();
	}
	
	private String getELValue(String src){
		int sepPos = src.indexOf(':'); 
		if (sepPos!=-1){
			String categID  = src.substring(0,sepPos);
			String itemName = src.substring(sepPos+1);
			EditorPopupItem item =  model.getItemForCategory(categID, itemName);
			if (item!=null) {
				formHlParts.add(new DocTextPart(item.getContents()));
				return item.getContents();
			}
			switch(PopupCategory.evaluate(categID)){
				case User:
					EditorUserField newFld  = new EditorUserField(itemName );
					newFld.setFieldIndex(userFields.size());
					userFields.add(newFld);
					formHlParts.add(newFld);
					return itemName;
				case Formula:
					parseFormula(itemName);
					return curFormula.getFieldID();
				case Editor:
					return processEditorCommands(itemName);
			}
		}
		return src;
	}
	private String processEditorCommands(String commandValue){
		if ((commandValue!=null)&&(!commandValue.isEmpty())){
			EditorCommand command = EditorCommand.evaluate(commandValue);
			switch(command){
				case Cursor:
					EditorUserField newFld  = new EditorUserField("|",DocTextPart.Type.Editor);
					newFld.setFieldIndex(userFields.size());
					userFields.add(newFld);
					return "|"; // Will need to deal with this differently in the editor
			}
				
			 
		}
		return null;
	}
	
	private void parseFormula(String formText){
		String formName    = formText.substring(0,formText.indexOf('('));
		String allParamTxt = formText.substring(formText.indexOf('(')+1,formText.indexOf(')'));
		curFormula = new Formula(formName,"");
		curFormula.setFieldValue(formName);
		for(String curParam:allParamTxt.split(",")){
			String paramValue = model.getFormulaParameterValue(curParam);
			curFormula.addParameter(curParam, paramValue);
			
			
		}
		
	}
	
	private void processWordChange(String newValue){
		try {
			Word curWrd = getCurrentTypedWord();
			int  insertPos = getCaretPosition();
			if (curWord.getLength() > 0){
				getDocument().remove(curWrd.getStart(), curWrd.getLength());
				insertPos = curWrd.getStart();
			}
			getDocument().insertString(insertPos, newValue, templateStyle);
			if ((curFormula!=null)&&(formHlParts.size()>0)){
				highlightFormulaFields(insertPos);
				emode = EditMode.Formula;
			}
			if (userFields.size()>0){
				highlightUserFields(insertPos);
				filterEditorFields();
				if (emode==EditMode.Normal) emode = EditMode.Template; // The case where a template where added, containing user fields to be given but no formula included
				
			}
			
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		popPan.setVisible(false);
		remove(popPan);
		repaint();
	}
	private void filterEditorFields() throws BadLocationException{
		for(DocTextPart curField:userFields){
			if (curField.getType()== DocTextPart.Type.Editor){
				if (curField.getFieldValue().equals("|")){
					getDocument().remove(curField.getFieldPos().getOffset(), 1);
				}
			}
		}
		
	}
	
	private void highlightFormulaFields(int start){
		for(DocTextPart curField:formHlParts){
			int userFieldPos = getText().indexOf(curField.fieldValue,start);
			if (userFieldPos!=-1){
				stDoc.setCharacterAttributes(userFieldPos, curField.fieldValue.length(), formulaFieldStyle, true);
				try {
					curField.fieldPos = getDocument().createPosition(userFieldPos);
				} catch (BadLocationException e) {e.printStackTrace();}
			}
		}
	}
	public String getText(){
		return doFilterExtraChars(super.getText());
	}
	private String doFilterExtraChars(String curValue){
		System.out.println("Filtering");
		StringBuilder sb = new StringBuilder();
		for(char curChar: curValue.toCharArray()){
			if(curChar!=13)sb.append(curChar);
		}
		return sb.toString();
		//getText().replaceAll(String.valueOf(13), "");
		
	}
	
	private void paintEditorFields(Graphics2D g){
		if (userFields.size()>0){
			for(EditorUserField curField:userFields){
				if (curField.getType()==Type.Editor){
					
					if (curField.getFieldValue().equals("|")){
						try {
							Rectangle rect = getUI().modelToView(this, curField.getFieldPos().getOffset());
							g.setColor(new Color(0,0,250,80));
							g.draw(rect);
						} catch (BadLocationException e) {
							e.printStackTrace();
						}
						
					}
				}
			}
		}
	}
	
	
	private void highlightUserFields(int start){
		boolean firstLocate = true;
		int     firstPos    = 0;
		int     firstLength = 0;
		for(EditorUserField curField:userFields){
			int userFieldPos = getText().indexOf(curField.fieldValue,start);
			if (userFieldPos!=-1){
				if (curField.getType()!=DocTextPart.Type.Editor)
					stDoc.setCharacterAttributes(userFieldPos, curField.fieldValue.length(), userFieldsStyle, true);
				try {
					curField.fieldPos = getDocument().createPosition(userFieldPos);
					//if (curField.getType()==DocTextPart.Type.Editor)
					//	getDocument().remove(userFieldPos, 1); // When going for a more forms of editor variables this should be a wrong approach, we might not need to remove the text
				} catch (BadLocationException e) {e.printStackTrace();}
				if (firstLocate){
					firstPos    =  userFieldPos;
					firstLength = curField.fieldValue.length();
					firstLocate = false;
				}
			}
		}
		curUFieldInd = 0;
		curUFieldPos = firstPos;
		
		setSelectedText(firstPos, firstLength);
	}
	private void deHightlightUserFields(){
		for(EditorUserField curField:userFields){
			stDoc.setCharacterAttributes(curField.getFieldPos().getOffset(), curField.fieldValue.length(), defaultTextStyle, true);
		}
	}
	
	
	private void setSelectedText(int firstPos,int length){
		setCaretPosition(firstPos);
		setSelectionStart(firstPos);
		setSelectionEnd(firstPos + length);
		
	}
	private void setSelectedUserField(EditorUserField usrFld){
		switch(usrFld.getType()){
			case Editor:
				if (usrFld.fieldValue.equals("|"))
					setCaretPosition(usrFld.fieldPos.getOffset());
				break;
			default:
				setSelectedText(usrFld.fieldPos.getOffset(),usrFld.fieldValue.length());
		}	
		
		
	}
	
	private void moveToNextUserField(boolean forward){
		EditorUserField prevField = userFields.get(curUFieldInd);
		if (emode==EditMode.Formula)
			checkFormulaFieldValueChange(prevField,curFormula);
		if (emode==EditMode.Template)
			updateFieldValue(prevField);
		if (curUFieldInd+1<userFields.size()){
			curUFieldInd++;
		}else if (curUFieldInd+1==userFields.size()){
			curUFieldInd=0;
		}

		EditorUserField nxtField = userFields.get(curUFieldInd);
		setSelectedUserField(nxtField);
	}
	private void checkFormulaFieldValueChange(DocTextPart frmFld,Formula targeFormula) {
		if (updateFieldValue(frmFld)){
			if ((targeFormula!=null)&&(targeFormula.containsParameter(frmFld.fieldID))) {
				targeFormula.addParameter(frmFld.fieldID, frmFld.fieldValue);
				updateFormula(targeFormula);
			}
		}
	}
	private boolean updateFieldValue(DocTextPart part){
		try {
			String curDocVal = null;
			if (part.fieldPos.getOffset()+part.fieldValue.length()>getText().length())
				curDocVal = getText(part.fieldPos.getOffset(),getText().length() - part.fieldPos.getOffset());
			else
				curDocVal = getText(part.fieldPos.getOffset(),part.fieldValue.length());
			if ((curDocVal==null)||(!curDocVal.equals(part.fieldValue))){
				// This means that the user changed the value to something else, we should update the field value
				StringBuilder sb      = new StringBuilder();
				char          curChar = ' ';
				int           index   = part.fieldPos.getOffset();
				while((curChar=getText().charAt(--index))!=' ') {
					if (isWordSeparator(curChar)) break;
					sb.insert(0, curChar);
					
				}
				System.out.println("New Value is : " + sb.toString());
				part.fieldValue = sb.toString();
				part.fieldPos   = getDocument().createPosition(index+1);
				return true;

			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	private void updateFormula(Formula targetFormula) {
		try {
			int oldOffset = targetFormula.getFieldPos().getOffset();
			getDocument().remove(oldOffset, targetFormula.getFieldValue().length());
			targetFormula.setFieldValue(model.processFormula(targetFormula));
			getDocument().insertString(targetFormula.getFieldPos().getOffset(),targetFormula.getFieldValue(),formulasStyle);
			targetFormula.setFieldPos(doCreatePosition(oldOffset));
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

	}
	
	private void processWordListPopup(){
		curWord = getCurrentTypedWord();
		//if (curWord.getLength() == 0) return;
		if (popPan==null)
			popPan = new EditorPopupPanel(this,model);
		if(!popPan.isVisible()){
			int index = getCaretPosition();
			if (curWord.getLength() > 0)
				index = curWord.getStart();
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
			popPan.setSize(popWidth,popHeight);
			scrollPopupToTypedWord();
			add(popPan);
			popPan.setVisible(true);
			
			//System.out.println("Finished creating the popup ...!!!!");
		}
		
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
	private static void testChar(){
		String chk = "\f";
		for(char curChar: chk.toCharArray()) System.out.println("Cur Char Value : " + (int)curChar);
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
	
	
	/*
	 * Setters and Getters mothods
	 * */
	/**
	 * @return the model
	 */
	public EditorPopupModel getModel() {return model;}
	/**
	 * @param model the model to set
	 */
	public void setModel(EditorPopupModel model) {this.model = model;}


	
	/* Common utility method - might need to remove it later on*/
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
    
    public void serviceCommand(FormatCommand command){
    	switch(command){
    		case Bold:
    			 //StyledEditorKit.BoldAction;
    			
    			break;
    	}
    }
    
    enum PopupCategory{
    	User,Formula,Editor,Others;
    	public static PopupCategory evaluate(String value){
    		if (value!=null){
    			if(value.equals("user")) return User;
    			if(value.equals("formula")) return Formula;
    			if(value.equals("edit")) return Editor;
    		}
    		return Others;
    	}
    	
    }
    enum FormatCommand{
    	AlignLeft,AlignCenter,AlignRight,Fill,FontSize,FontColor,FontFace,Bold,Italic;
    	
    }
    enum EditMode{Formula,Normal,Popup,Template;}
    enum EditorCommand{Cursor,Others;
		public static EditorCommand evaluate(String value){
			if (value!=null){
				if(value.equals("cursor")) return Cursor;
			}
			return Others;
		}

    }
    static SMYLDTextEditor editor;
	private static final char[] WORD_SEPARATORS = {' ', '\n', '\t', '.', ',', ';', '!', '?', '\'', '(', ')', '[', ']', '\"', '{', '}', '/', '\\', '<','>'};

	
	
	/*
	 * This section contains the external testing environment, including the toolbar, it is also possible to include the toolbar in a panel, so that 
	 * the editor is a ready component that can be used directly, just an idea
	 * 
	 * */
	private static void testComponent(){
		
		SMYLDFrame testFrame = new SMYLDFrame("SMYLD Text Editor");
		testFrame.setSize(800,600);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//editor = new SMYLDTextEditor(new EditPopModel());
		addToolBar(testFrame);
		testFrame.add(editor,BorderLayout.CENTER);
		testFrame.setLocationRelativeTo(null);
		testFrame.setVisible(true);
	}
	
	private static void addToolBar(final SMYLDFrame frame){
		Resource res = Resource.getInstance();
		
		SMYLDToolbar toolbar = new SMYLDToolbar();
		// Adding the entries to the toolbar
		toolbar.add(createButton(FormatCommand.AlignLeft.name(), "format-justify-left.png",new StyledEditorKit.AlignmentAction("left-justify",StyleConstants.ALIGN_LEFT)),"cmdAlignLeft");
		toolbar.add(createButton(FormatCommand.AlignCenter.name(), "format-justify-center-horizontal.png",new StyledEditorKit.AlignmentAction("center-justify",StyleConstants.ALIGN_CENTER)),"cmdAlignCenter");
		toolbar.add(createButton(FormatCommand.AlignRight.name(), "format-justify-right.png",new StyledEditorKit.AlignmentAction("right-justify",StyleConstants.ALIGN_RIGHT)),"cmdAlignRight");
		//toolbar.add(createButton(FormatCommand.Fill.name(), "format-justify-fill.png"),"cmdFill");
		toolbar.addSeparator();
		toolbar.add(createButton(FormatCommand.Bold.name(), "format-text-bold.png",new StyledEditorKit.BoldAction()),"cmdBold");
		toolbar.add(createButton(FormatCommand.Italic.name(), "format-text-italic.png",new StyledEditorKit.ItalicAction()),"cmdItalic");
		
		String[] fontSizes = {"8","10","12","14","16","18","24","36","48"};
		final SMYLDComboBox size = new SMYLDComboBox(fontSizes); 
		toolbar.add(size,"fontSizeCombo");
		String[] fonts = {"Times New Roman","Arial","Arial Black"};
		final SMYLDComboBox fontsCombo = new SMYLDComboBox(fonts);
		size.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
			    MutableAttributeSet attr = new SimpleAttributeSet();
			    StyleConstants.setFontSize(attr, Integer.parseInt((String)size.getSelectedItem()));
			    editor.setCharacterAttributes(attr, false);
			}
		});
		fontsCombo.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
			    MutableAttributeSet attr = new SimpleAttributeSet();
			    StyleConstants.setFontFamily(attr, (String)fontsCombo.getSelectedItem());
			    editor.setCharacterAttributes(attr, false);
			}
		});

		toolbar.add(fontsCombo,"fontsCombo");
		
		toolbar.addSeparator();
		
		toolbar.addSeparator();
		SMYLDButton fntColorBut = createButton(FormatCommand.FontColor.name(), "format-text-color.png");
		fntColorBut.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Color result = JColorChooser.showDialog(editor, "Font Color", Color.BLACK);
			    MutableAttributeSet attr = new SimpleAttributeSet();
			    StyleConstants.setForeground(attr, result);
			    editor.setCharacterAttributes(attr, false);

			}
		});
		toolbar.add(fntColorBut,"cmdFontColor");
		toolbar.setActionListener(new ActionHandler(){
			@Override
			public void processGUIAction(GUIAction incomingAction) {
				System.out.println("Hi There " + incomingAction.getCommand());
				editor.serviceCommand(FormatCommand.valueOf(incomingAction.getCommand()));
			}
		});
		frame.setToolbar(toolbar);
		
	}  
	
	private static SMYLDButton createButton(String actionCommand,String iconName){
		return createButton(actionCommand, iconName,null);
	}

	private static SMYLDButton createButton(String actionCommand,String iconName,Action butAction){
		GUIAction action = new GUIAction();
		action.setCommand(actionCommand);
		SMYLDButton newBut = new SMYLDButton(action,"",Resource.getInstance().getImageIcon(iconName));
		if ( butAction!=null){
			newBut.setActionCommand(butAction.NAME);
			newBut.addActionListener(butAction);
		}
		return newBut;
	}
	
	public static void main(String[] args){
		//testChar();
		testComponent();
	}

	
}
