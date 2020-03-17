/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.model;

import org.smyld.app.pe.model.gui.PEAction;

import java.util.HashMap;

public interface PEApplication {
    public String getIcon();
    public String getName();
    public String getTitle();
    public String getHomePath();
    GUIToolkit getGUIToolkit();
    ApplicationType getType();
    LayoutType getLayout();
    public HashMap<String, String> getImages();
    public HashMap<String, PEAction> getActions();

}
