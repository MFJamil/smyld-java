package org.smyld.gui.portal.engine;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.*;

import org.smyld.app.pe.model.Constants;
import org.smyld.app.pe.model.gui.GUIComponent;
import org.smyld.app.pe.model.gui.GUITable;
import org.smyld.app.pe.model.gui.GUITableColumn;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import org.smyld.app.pe.model.user.UserConstraint;
import org.smyld.text.TextUtil;

/**
 * This class represents the main engine class for generating the GUI components
 * in a single panel that will be referenced inside other window component
 * 
 * @author
 * @version
 * @see
 * @since
 */
import static org.smyld.app.AppConstants.*;
public class GUIPanelGenerator extends GUIComponentGenerator implements Constants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HashMap<String,String> noLabelCompMapper = new HashMap<String,String>();
	HashMap<String,String> classMapper       = new HashMap<String,String>();

	ArrayList<GUIComponent> classComponents = new ArrayList<>();

	/**
	 * 
	 * @see
	 * @since
	 */
	public GUIPanelGenerator() {
		init();
	}

	private void init() {
		classMapper.put(TAG_COMP_CONT_TAB          , CLASS_NAME_FP_PE_PANEL);
		classMapper.put(TAG_COMP_CONT_PANEL        , CLASS_NAME_FP_PE_PANEL);
		classMapper.put(TAG_COMP_CONT_SPLIT        , CLASS_NAME_FP_SMYLD_SPLIT);
		classMapper.put(TAG_COMP_CONT_SPLIT_SIDE   , CLASS_NAME_FP_SMYLD_SP_SIDE);
		classMapper.put(TAG_COMP_CONT_SCRLP        , CLASS_NAME_FP_SMYLD_SCRLP);
		classMapper.put(TAG_COMP_BUTTON            , CLASS_NAME_FP_SMYLD_BUTN);
		classMapper.put(TAG_COMP_CHECKBOX          , CLASS_NAME_FP_SMYLD_CHKB);
		classMapper.put(TAG_COMP_RADIOBUTTON       , CLASS_NAME_FP_SMYLD_RADB);
		classMapper.put(TAG_COMP_LIST_FIELD        , CLASS_NAME_FP_SMYLD_LIST);
		/* We need to add new check mechanism as follows 
		 *  If component without label = we create the pure component
		 *  If component with label = we create the labeled component
		 *  If component with label but with spring layout = we create the pure and label component separately
		 * 
		 * */
		classMapper.put(TAG_COMP_COMBO             , CLASS_NAME_FP_SMYLD_LCOMBO);
		classMapper.put(TAG_COMP_TEXT_FIELD        , CLASS_NAME_FP_SMYLD_LTXT);
		classMapper.put(TAG_COMP_PASSWORD          , CLASS_NAME_FP_SMYLD_LPASS);
		classMapper.put(TAG_COMP_LABEL             , CLASS_NAME_FP_SMYLD_LBL);
		classMapper.put(TAG_COMP_TABLE             , CLASS_NAME_FP_SMYLD_LTABLE);
		classMapper.put(TAG_COMP_TEXT_AREA         , CLASS_NAME_FP_SMYLD_TXT_LAREA);
		classMapper.put(TAG_COMP_TREE              , CLASS_NAME_FP_SMYLD_TREE);
		classMapper.put(TAG_COMP_CONT_DESKTOP_PANE , CLASS_NAME_FP_SMYLD_DESKTOP_PANE);
		classMapper.put(TAG_NAME_TLB               , CLASS_NAME_FP_SMYLD_TOOLBAR);
		classMapper.put(TAG_NAME_MENUBAR           , CLASS_NAME_FP_SMYLD_MNUBAR);
		classMapper.put(TAG_COMP_PROGRESS_BAR      , CLASS_NAME_FP_SMYLD_PRG);
		classMapper.put(TAG_COMP_INTERNET_PANEL    , CLASS_NAME_FP_INTERNET_PANEL);
		classMapper.put(TAG_COMP_CONT_TABBED_PANEL , CLASS_NAME_FP_SMYLD_TABBEDPANE);
		classMapper.put(TAG_COMP_DATE              , CLASS_NAME_FP_DATE_PANEL);
		
		noLabelCompMapper.put(TAG_COMP_COMBO             , CLASS_NAME_FP_SMYLD_COMBO);
		noLabelCompMapper.put(TAG_COMP_TEXT_FIELD        , CLASS_NAME_FP_SMYLD_LTXT);
		//noLabelCompMapper.put(TAG_COMP_PASSWORD          , CLASS_NAME_FP_SMYLD_PASS);
		
		noLabelCompMapper.put(TAG_COMP_TABLE             , CLASS_NAME_FP_SMYLD_LTABLE);
		noLabelCompMapper.put(TAG_COMP_TEXT_AREA         , CLASS_NAME_FP_SMYLD_TXT_LAREA);

	}

	private void reset() {
		classComponents = new ArrayList<>();
	}

	public ArrayList<GUIComponent> buildPanelComponents(Element panelNode) throws Exception {
		reset();
		GUIComponent newComponent = generateComponent(panelNode);
		classComponents.add(newComponent);
		newComponent.setChildren(new ArrayList<>());
		navigate(panelNode, newComponent.getChildren());
		return classComponents;
	}

	/*
	 * private void navigate(lement parentElement,Vector createdComponents){
	 * List nodesList = parentElement.getChildren(); Iterator itr =
	 * nodesList.iterator(); while (itr.hasNext()){ Element currentElement =
	 * (Element)itr.next(); GUIComponent newComponent =
	 * generateComponent(currentElement); if (newComponent!=null){
	 * createdComponents.add(newComponent);
	 * System.out.println(currentElement.getName()); if
	 * (currentElement.hasChildren()){ newComponent.Children = new Vector();
	 * navigate(currentElement,newComponent.getChildren()); } } } }
	 * 
	 * 
	 * 
	 * private void createComponents(Vector components, Element rootElement){ //
	 * Reading list field components List fldList = rootElement.getChildren();
	 * Iterator itr = fldList.iterator(); while(itr.hasNext()){ Element
	 * currentElement = (Element)itr.next(); GUIComponent newComponent =
	 * generateComponent(currentElement); if (newComponent!=null)
	 * components.add(newComponent); } }
	 */
	@Override
	protected GUIComponent generateComponent(Element currentElement) {
		GUIComponent newComponent = null;
		String compName = getStringValue(currentElement, TAG_COMP_ATT_ID);
		String compClassImportName = getClassImportName(currentElement);
		if ((compName != null) && (compClassImportName != null)) {
			// Initializing the class which could be GUIComponent or any child
			// class
			// from it
			String compClassName = getClassName(compClassImportName);
			if (isTableComponent(compClassName)) {
				newComponent = new GUITable();
			} else {
				newComponent = new GUIComponent();
			}
			HashMap<String,String> events = new HashMap<String,String>();
			newComponent.setTagName(currentElement.getName());
			newComponent.setEvents(events);
			newComponent.setID(compName);
			newComponent.setClassName(compClassName);
			newComponent.setClassImportName(compClassImportName);
			newComponent.setPackage(getStringValue(currentElement,TAG_COMP_ATT_PACKAGE));
			newComponent.setColumn(getStringValue(currentElement,TAG_COMP_ATT_COL));
			newComponent.setRow(getStringValue(currentElement, TAG_COMP_ATT_ROW));
			newComponent.setWidth(getStringValue(currentElement,TAG_COMP_ATT_WIDTH));
			newComponent.setHeight(getStringValue(currentElement,TAG_COMP_ATT_HEIGHT));
			newComponent.setScope(getStringValue(currentElement,TAG_COMP_ATT_SCOPE));
			handleLabel(currentElement, newComponent);
			newComponent.setSelected(getStringValue(currentElement,	TAG_COMP_ATT_SELECT));
			newComponent.setDefaultValue(getStringValue(currentElement,	TAG_COMP_ATT_DEFAULT));
			newComponent.setLayout(getStringValue(currentElement,TAG_COMP_ATT_LAYOUT));
			newComponent.setPosition(getStringValue(currentElement,	TAG_COMP_ATT_POSITION));
			newComponent.setType(getStringValue(currentElement,	TAG_COMP_ATT_TYPE));
			newComponent.setLabelPosition(getStringValue(currentElement,TAG_COMP_ATT_LBL_POSITION));
			newComponent.setEnabled(getStringValue(currentElement,TAG_COMP_ATT_ENABLE));
			newComponent.setScrollable(getStringValue(currentElement,TAG_COMP_ATT_SCROLLABLE));
			newComponent.setTooltip(getStringValue(currentElement,TAG_COMP_ATT_TOOLTIP));
			newComponent.setIcon(getStringValue(currentElement,	TAG_COMP_ATT_ICON));
			newComponent.setBgColor(getStringValue(currentElement,TAG_COMP_ATT_BG_COLOR));
			newComponent.setColor(getStringValue(currentElement,TAG_COMP_ATT_COLOR));
			newComponent.setCompWidth(getStringValue(currentElement,TAG_COMP_ATT_COMP_WIDTH));
			newComponent.setAlign(getStringValue(currentElement,TAG_COMP_ATT_ALIGN));
			newComponent.setSource(getStringValue(currentElement        ,TAG_COMP_ATT_SOURCE));
			newComponent.setTopMargin(getStringValue(currentElement     ,TAG_COMP_ATT_TOP_MARGIN));
			newComponent.setTitleType(getStringValue(currentElement     ,TAG_COMP_ATT_TITLE_TYPE));
			newComponent.setRows(getStringValue(currentElement          ,TAG_COMP_ATT_ROWS));
			newComponent.setCols(getStringValue(currentElement          ,TAG_COMP_ATT_COLS));
			newComponent.setVGap(getStringValue(currentElement          ,TAG_COMP_ATT_VERTICAL_GAP));
			newComponent.setHGap(getStringValue(currentElement          ,TAG_COMP_ATT_HORIZONTAL_GAP));
			newComponent.setDockable(getStringValue(currentElement      ,TAG_ATT_DOCKABLE));
			newComponent.setDragable(getStringValue(currentElement      ,TAG_ATT_DRAGABLE));
			newComponent.setListenerTarget(getStringValue(currentElement,TAG_COMP_ATT_LINK_LISTNR));
			newComponent.setBorderType(getStringValue(currentElement    ,TAG_COMP_ATT_BORDER_TYPE));
			newComponent.setBorderWidth(getStringValue(currentElement   ,TAG_COMP_ATT_BORDER_WIDTH));
			newComponent.setOrient(getStringValue(currentElement        ,TAG_COMP_ATT_ORIENT));
			newComponent.setFieldWidth(getStringValue(currentElement    ,TAG_COMP_ATT_FIELD_WIDTH));
			newComponent.setBorderTitle(getStringValue(currentElement   ,TAG_COMP_ATT_BORDER_TITLE));
			UserConstraint userConst = readConstraint(currentElement);
			if (userConst!=null)
				newComponent.setUserConstraint(userConst);
			detectValues(currentElement, newComponent);
			if (isTableComponent(compClassName)) {
				addTableAdditionalInfo((GUITable) newComponent, currentElement);
			}
			// if (compClassName.equals(CLASS_NAME_SMYLD_TABLE))
			// Events Detecting
			detectEvent(currentElement, TAG_COMP_ATT_ON_CLK, events);
			detectEvent(currentElement, TAG_COMP_ATT_ON_FOC_GOT, events);
			detectEvent(currentElement, TAG_COMP_ATT_ON_FOC_LOST, events);
			detectEvent(currentElement, TAG_COMP_ATT_ON_MOUS_CLK, events);
			detectEvent(currentElement, TAG_COMP_ATT_ON_MOUS_DBL_CLK, events);
			detectEvent(currentElement, TAG_COMP_ATT_ON_MOUS_R_CLK, events);
			detectEvent(currentElement, TAG_COMP_ATT_ON_CHANGE, events);

		}
		return newComponent;
	}

	private boolean isTableComponent(String className) {
		return ((className.equals(CLASS_NAME_SMYLD_TABLE)) || (className
				.equals(CLASS_NAME_SMYLD_LTABLE)));
	}

	@SuppressWarnings("unchecked")
	private void detectValues(Element currentElement, GUIComponent curComp) {
		Element values = currentElement.getChild(TAG_NAME_VALUES);
		if ((values != null) && (hasChildren(values))) {
			List valueList = values.getChildren(TAG_NAME_VALUE);
			Object[] comValues = new Object[valueList.size()];
			Iterator itr = valueList.iterator();
			int count = 0;
			while (itr.hasNext()) {
				Element curValue = (Element) itr.next();
				comValues[count] = readComponentValues(curValue, curComp);
				count++;
			}
			curComp.setValues(comValues);
		}
	}
	private boolean hasChildren(Element curEl){
		return ((curEl.getChildren()!=null)&&(curEl.getChildren().size()>0));
	}


	private Object readComponentValues(Element curValue, GUIComponent curComp) {
		// Table special values handling
		if (curComp.getTagName().equals(TAG_COMP_TABLE)) {
			String value = curValue.getText();
			if (!TextUtil.isEmpty(value)) {
				return value.split("\\|");
			}
		}
		return curValue.getText();
	}

	@SuppressWarnings("unchecked")
	private void handleLabel(Element currentElement, GUIComponent curComp) {
		List content = currentElement.getContent();
		if ((currentElement.getName().equals(TAG_COMP_LABEL))
				&& (content != null) && (content.size() > 0)) {
			StringBuffer buffer = new StringBuffer();
			Iterator itr = content.iterator();
			while (itr.hasNext()) {
				Object curCont = itr.next();
				if ((curCont instanceof String)
						&& (!TextUtil.isEmpty((String) curCont))) {
					buffer.append((String) curCont);
				} else if (curCont instanceof Element) {
					String contents = new XMLOutputter()
							.outputString((Element) curCont);
					contents = convertToLiteral(contents);

					buffer.append(contents);
				}
			}
			curComp.setLabel(buffer.toString());
		} else {
			curComp
					.setLabel(getStringValue(currentElement, TAG_COMP_ATT_LABEL));
		}
	}

	private String convertToLiteral(String htmlText) {
		// contents = contents.replaceAll("\"","'");
		htmlText = htmlText.replaceAll("\"", "'");
		StringBuffer store = new StringBuffer();
		LineNumberReader reader = new LineNumberReader(new StringReader(
				htmlText));
		String curLine = null;
		try {
			while ((curLine = reader.readLine()) != null) {
				store.append("\"");
				store.append(curLine);
				store.append("\"");
				store.append("+");
			}
			store.deleteCharAt(0);
			store.delete(store.length() - 2, store.length());
			// store.deleteCharAt(store.length()-1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return store.toString();
	}

	private String getClassImportName(Element curEl) {
		String className = (String) classMapper.get(curEl.getName());
		if (curEl.getName().equals(TAG_NAME_MENUBAR)) {
			String itemType = curEl.getAttributeValue(TAG_ATT_TYPE);
			if ((itemType != null)
					&& (itemType.equals(TAG_ATT_MENUBAR_TYPE_TREE))) {
				className = CLASS_NAME_FP_SMYLD_TREE;
			} else {
				className = CLASS_NAME_FP_SMYLD_MNUBAR;
			}
		} else if (curEl.getName().equals(TAG_COMP_CONT_PANEL)) {
			String itemType = curEl.getAttributeValue(TAG_ATT_TYPE);
			if (TAG_ATT_PANEL_TEMP_DOCKABLE.equals(itemType)) {
				className = CLASS_NAME_FP_DOCKABLE_TEMPLATE;
			} else {
				className = CLASS_NAME_FP_PE_PANEL;
			}
		} else if ((className != null)
				&& (className.equals(CLASS_NAME_FP_SMYLD_LTABLE))
				&& (TextUtil.isEmpty(curEl
						.getAttributeValue(TAG_COMP_ATT_LABEL)))) {
			// The labeled table component behaviour is strange therefore I
			// prefer
			// to use the direct table in case the lable is not available
			className = CLASS_NAME_FP_SMYLD_TABLE;
		}
		return className;
	}

	@SuppressWarnings("unchecked")
	private void addTableAdditionalInfo(GUITable newComponent,
			Element sourceElement) {
		Element header = sourceElement.getChild(TAG_NAME_HEADER);
		if (header != null) {
			List headerColumns = header.getChildren();
			int colOrder = 0;
			Iterator itr = headerColumns.iterator();
			while (itr.hasNext()) {
				Element curCol = (Element) itr.next();
				GUITableColumn newColumn = new GUITableColumn();
				newColumn.setID(getStringValue(curCol, TAG_COMP_ATT_ID));
				newColumn.setLabel(getStringValue(curCol, TAG_COMP_ATT_LABEL));
				newColumn.setWidth(getStringValue(curCol, TAG_COMP_ATT_WIDTH));
				newColumn.setOrderInTable(colOrder);
				colOrder++;
				newComponent.addColumn(newColumn);
			}
		}
	}

	@SuppressWarnings("unused")
	private boolean isComponentNode(Element curElement) {
		return classMapper.containsKey(curElement.getName());
		// boolean result = true;
		// if (curElement.get
	}
	public UserConstraint readConstraint(Element el){
		if ((el.getAttributeValue(TAG_COMP_ATT_ROLE)!=null)||
				(el.getAttributeValue(TAG_COMP_ATT_SHOW_ROLE)!=null)){
			UserConstraint userConst = new UserConstraint(el.getAttributeValue(TAG_COMP_ATT_ID),el.getAttributeValue(TAG_COMP_ATT_ROLE),el.getAttributeValue(TAG_COMP_ATT_SHOW_ROLE));
			return userConst;
		}
		return null;
	}


}
