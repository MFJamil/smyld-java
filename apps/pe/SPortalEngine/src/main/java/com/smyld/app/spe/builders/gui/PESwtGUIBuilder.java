package com.smyld.app.spe.builders.gui;

import com.smyld.app.pe.annotations.PEGUIBuilder;
import com.smyld.app.pe.model.ApplicationType;
import com.smyld.app.pe.model.GUIToolkit;

@PEGUIBuilder(name = "SwingBuilder", applicationType = ApplicationType.Desktop, guiToolkit = GUIToolkit.swt)
public class PESwtGUIBuilder {

}
