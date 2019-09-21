package org.smyld.gui.portal.engine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.smyld.app.pe.model.gui.GUIComponent;
import org.smyld.app.pe.model.gui.GUISplash;
import org.smyld.app.pe.model.gui.GUIWindow;
import org.jdom2.Element;
import static org.smyld.app.pe.model.Constants.*;
/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class GUIWindowGenerator extends GUIComponentGenerator {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see
	 * @since
	 */
	public GUIWindowGenerator() {

	}

	private void fillWindowAttribute(GUIWindow newWindow, Element windowNode) {
		newWindow.setID(windowNode.getAttributeValue(TAG_COMP_ATT_ID));
		newWindow.setType(windowNode.getAttributeValue(TAG_COMP_ATT_TYPE));
		newWindow
				.setPackage(windowNode.getAttributeValue(TAG_COMP_ATT_PACKAGE));
		newWindow.setLabel(windowNode.getAttributeValue(TAG_COMP_ATT_LABEL));
		newWindow.setIcon(windowNode.getAttributeValue(TAG_COMP_ATT_ICON));
		newWindow.setResizable(windowNode
				.getAttributeValue(TAG_COMP_ATT_RESIZABLE));
		newWindow.setWidth(windowNode.getAttributeValue(TAG_COMP_ATT_WIDTH));
		newWindow.setHeight(windowNode.getAttributeValue(TAG_COMP_ATT_HEIGHT));
		newWindow.setBgColor(windowNode
				.getAttributeValue(TAG_COMP_ATT_BG_COLOR));
		newWindow.setStartUpMethod(windowNode
				.getAttributeValue(TAG_COMP_ATT_ON_STARTUP));
		newWindow
				.setStartup(windowNode.getAttributeValue(TAG_COMP_ATT_STARTUP));
	}

	public GUIWindow buildWindow(Element windowNode) {
		// Building the window out of the XML element, this need to be reviewed
		// more in the future
		GUIWindow newWindow = new GUIWindow();
		fillWindowAttribute(newWindow, windowNode);
		HashMap<String,String> events = new HashMap<String,String>();

		detectEvent(windowNode, TAG_COMP_ATT_ON_CLOSE, events);
		detectEvent(windowNode, TAG_COMP_ATT_ON_WIN_FOC_GOT, events);
		detectEvent(windowNode, TAG_COMP_ATT_ON_ACTIVE, events);
		if (events.size() > 0) {
			newWindow.setEvents(events);
		}
		processWindowNode(windowNode, newWindow);
		// to be reviewed : navigate(windowNode,null);
		return newWindow;
	}

	public GUISplash buildSplash(Element windowNode) {
		// Building the window out of the XML element, this need to be reviewed
		// more in the future
		GUISplash newSplash = new GUISplash();
		fillWindowAttribute(newSplash, windowNode);
		newSplash.setTime(windowNode.getAttributeValue(TAG_COMP_ATT_TIME));
		newSplash.setBody(windowNode.getAttributeValue(TAG_COMP_ATT_BODY));
		return newSplash;
	}

	public Vector<GUIComponent> buildWindowComponents(Element windowNode) throws Exception {
		generateComponent(windowNode);

		return null;
	}

	@SuppressWarnings("unchecked")
	private void processWindowNode(Element windowNode, GUIWindow newWindow) {
		List nodesList = windowNode.getChildren();
		Iterator itr = nodesList.iterator();
		while (itr.hasNext()) {
			Element currentElement = (Element) itr.next();
			if (currentElement.getName().equals(TAG_NAME_BODY)) {
				processBodyTag(currentElement, newWindow);
			} else if (currentElement.getName().equals(TAG_NAME_MENUBAR)) {
				processMenuBarTag(currentElement, newWindow);
			} else if (currentElement.getName().equals(TAG_NAME_TLB)) {
				processToolbarTag(currentElement, newWindow);
			} else if (currentElement.getName().equals(TAG_NAME_STATUS_BAR)) {
				processStatusbarTag(currentElement, newWindow);
			}
		}
	}

	private void processBodyTag(Element bodyNode, GUIWindow newWindow) {
		newWindow.setBodyType(bodyNode.getAttributeValue(TAG_COMP_ATT_TYPE));
		newWindow.setBodyID(bodyNode.getAttributeValue(TAG_COMP_ATT_ID));
		if ((newWindow.getBodyType() != null)
				&& (newWindow.getBodyType()
						.equals(TAG_COMP_CONT_DOCKABLE_DESKTOP))) {
			newWindow.setBody(CLASS_NAME_DOCKABLE_DESKTOP);
		} else {
			newWindow.setBody(bodyNode.getText());
		}
		newWindow.setBodyListenerTarget(bodyNode
				.getAttributeValue(TAG_COMP_ATT_LINK_LISTNR));
	}

	private void processMenuBarTag(Element menuNode, GUIWindow newWindow) {
		newWindow.setMenuBarID(menuNode.getAttributeValue(TAG_COMP_ATT_ID));
		newWindow.setMenuHandler(menuNode
				.getAttributeValue(TAG_COMP_ATT_LINK_HANDLER));
	}

	private void processToolbarTag(Element tlbNode, GUIWindow newWindow) {
		newWindow.setToolbarID(tlbNode.getAttributeValue(TAG_COMP_ATT_ID));
		// Adding code for linking the listener to the window and the related
		// object
	}

	private void processStatusbarTag(Element tlbNode, GUIWindow newWindow) {
		newWindow.setStatusBar(tlbNode.getAttributeValue(TAG_COMP_ATT_ID));
		// Adding code for linking the listener to the window and the related
		// object
	}

}
