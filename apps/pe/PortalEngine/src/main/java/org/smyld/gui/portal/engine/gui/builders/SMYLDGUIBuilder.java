package org.smyld.gui.portal.engine.gui.builders;

import java.util.ArrayList;
import java.util.HashMap;

import org.smyld.app.pe.model.gui.*;
import org.jdom2.Element;

import org.smyld.lang.script.java.JavaClassBody;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public interface SMYLDGUIBuilder {
	public JavaClassBody[] generatePanel(ArrayList<GUIComponent> classComponents)throws Exception;
	public JavaClassBody[] generatePanel(ArrayList<GUIComponent> classComponents, HashMap<String,Element> panels)throws Exception;

	public JavaClassBody[] generateWindow(GUIWindow windowComponent)
			throws Exception;

	public JavaClassBody generateMenuFactory(HashMap<String,MenuItem> menus) throws Exception;

	public JavaClassBody generateActionsFactory(HashMap<String,PEAction> actions)
			throws Exception;

	public JavaClassBody generateToolbarsFactory(HashMap<String, GUIToolbar> toolbars)
			throws Exception;

	public JavaClassBody generateWindowFactory() throws Exception;

	public JavaClassBody generatePanelFactory() throws Exception;
}
