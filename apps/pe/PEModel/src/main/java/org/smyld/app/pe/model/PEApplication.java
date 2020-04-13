/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.model;

import org.smyld.app.pe.model.gui.GUIToolbar;
import org.smyld.app.pe.model.gui.GUIWindow;
import org.smyld.app.pe.model.gui.MenuItem;
import org.smyld.app.pe.model.gui.PEAction;

import java.util.HashMap;

public interface PEApplication {
    // General application settings
    // For the window
    public String          getLogo();
    public String          getIcon();
    public String          getName();
    public String          getTitle();
    public String          getHomePath();
    public String          getMainClass();

    public GUIToolkit      getGUIToolkit();
    public ApplicationType getType();
    public LayoutType      getLayout();

    // Other application elements
    public HashMap<String, String>      getImages();
    public HashMap<String, PEAction>    getActions();
    public HashMap<String, GUIToolbar>  getToolbars();
    public HashMap<String, MenuItem>    getMenus();
    public HashMap<String, GUIWindow>   getWindows();


}
