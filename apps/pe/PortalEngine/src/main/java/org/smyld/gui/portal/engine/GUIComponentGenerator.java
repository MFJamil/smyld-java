package org.smyld.gui.portal.engine;

import java.util.*;

import org.smyld.app.pe.model.Constants;
import org.smyld.app.pe.model.gui.GUIComponent;
import org.jdom2.Attribute;
import org.jdom2.Element;

import org.smyld.SMYLDObject;
import org.smyld.gui.GUIConstants;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class GUIComponentGenerator extends SMYLDObject implements GUIConstants,
		Constants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see
	 * @since
	 */
	public GUIComponentGenerator() {
	}

	@SuppressWarnings("unchecked")
	protected void navigate(Element parentElement, ArrayList<GUIComponent> createdComponents) {
		List nodesList = parentElement.getChildren();
		Iterator itr = nodesList.iterator();
		while (itr.hasNext()) {
			Element currentElement = (Element) itr.next();
			GUIComponent newComponent = generateComponent(currentElement);
			if (newComponent != null) {
				createdComponents.add(newComponent);
				// System.out.println(currentElement.getName());
				if (hasChildren(currentElement)) {
					navigate(currentElement, newComponent.getChildren());
				}
			}
		}
	}
	private boolean hasChildren(Element curEl){
		return ((curEl.getChildren()!=null)&&(curEl.getChildren().size()>0));
	}

	protected GUIComponent generateComponent(Element currentElement) {
		return null;
	}

	protected String getStringValue(Element targetElement, String tagName) {
		Attribute targetAttribute = targetElement.getAttribute(tagName);
		if (targetAttribute != null) {
			return targetAttribute.getValue();
		}
		return null;
	}

	protected String getClassName(String fullClassName) {
		return fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
	}

	protected void detectEvent(Element currentElement, String tagName,
			HashMap<String,String> events) {
		String event = getStringValue(currentElement, tagName);
		if (event != null) {
			events.put(event, tagName);
		}

	}

}
