package com.smyld.gui.portal.engine.gui.builders.swing;

import java.util.ArrayList;
import java.util.HashMap;

import com.smyld.app.pe.annotations.PEGUIBuilder;
import com.smyld.app.pe.model.ApplicationType;
import com.smyld.app.pe.model.GUIToolkit;
import com.smyld.app.pe.model.gui.*;
import org.jdom2.Element;

import com.smyld.SMYLDObject;
import com.smyld.gui.portal.engine.Application;
import com.smyld.gui.portal.engine.gui.builders.SMYLDGUIBuilder;
import com.smyld.lang.script.java.JavaClassBody;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */

public class SwingGUIHandler extends SMYLDObject implements SMYLDGUIBuilder {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SMYLDSwingPanelBuilder panelBuilder;
	SMYLDSwingWindowBuilder windowBuilder;
	SMYLDSwingMenuBuilder menuBuilder;
	SMYLDActionsBuilder actionsBuilder;
	SMYLDSwingToolbarBuilder toolbarsBuilder;

	/**
	 * 
	 * @see
	 * @since
	 */
	public SwingGUIHandler() {
	}

	public void init() {
		panelBuilder = new SMYLDSwingPanelBuilder();
		windowBuilder = new SMYLDSwingWindowBuilder();
		actionsBuilder = new SMYLDActionsBuilder();
		menuBuilder = new SMYLDSwingMenuBuilder();
		toolbarsBuilder = new SMYLDSwingToolbarBuilder();
	}

	public void setActiveApplication(Application newApp) {
		panelBuilder.setActiveApplication(newApp);
		windowBuilder.setActiveApplication(newApp);
		menuBuilder.setActiveApplication(newApp);
		actionsBuilder.setActiveApplication(newApp);
		toolbarsBuilder.setActiveApplication(newApp);
	}

	public JavaClassBody[] generatePanel(ArrayList<GUIComponent> classComponents)
			throws Exception {
		return panelBuilder.generatePanel(classComponents);
	}



	public JavaClassBody[] generateWindow(GUIWindow windowComponent)
			throws Exception {
		return windowBuilder.generateWindow(windowComponent);
	}

	public JavaClassBody generateMenuFactory(HashMap<String,MenuItem> menus) throws Exception {
		if ((menus == null) || (menus.size() == 0)) {
			throw new Exception("Application does not contain any menu bar ..!");
		}
		menuBuilder.buildNewClass();
		for (MenuItem curItem : menus.values()) {
			menuBuilder.generatMenu(curItem);
		}
		return menuBuilder.getMenuFactoryClass();
	}

	public JavaClassBody generateActionsFactory(HashMap<String,PEAction> actions)
			throws Exception {
		if ((actions == null) || (actions.size() == 0)) {
			throw new Exception("Application does not contain any Actions ..!");
		}
		actionsBuilder.buildNewClass();
		for (PEAction curItem : actions.values()) {
			actionsBuilder.generateAction(curItem);
		}
		return actionsBuilder.getActionFactoryClass();
	}

	public JavaClassBody generateToolbarsFactory(HashMap<String, GUIToolbar> toolbars)
			throws Exception {
		if ((toolbars == null) || (toolbars.size() == 0)) {
			return null;
		}
		toolbarsBuilder.buildNewClass();
		for (GUIToolbar curToolbar : toolbars.values()) {
			toolbarsBuilder.generateToolbarBar(curToolbar);
		}
		return toolbarsBuilder.getToolbarFactoryClass();
	}

	public JavaClassBody generateWindowFactory() throws Exception {
		return windowBuilder.generateWindowFactory();
	}

	public JavaClassBody generatePanelFactory() throws Exception {
		return panelBuilder.getFactory();
	}

	public JavaClassBody[] generatePanel(ArrayList<GUIComponent> classComponents,
			HashMap<String, Element> panels) throws Exception {
		return panelBuilder.generatePanel(classComponents,panels);
	}

}
