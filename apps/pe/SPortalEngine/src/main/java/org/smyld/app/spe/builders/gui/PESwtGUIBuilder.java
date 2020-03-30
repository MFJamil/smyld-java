package org.smyld.app.spe.builders.gui;

import org.smyld.app.pe.annotations.PEGUIBuilder;
import org.smyld.app.pe.model.ApplicationType;
import org.smyld.app.pe.model.GUIToolkit;
import org.smyld.app.pe.model.GUIWidget;

@PEGUIBuilder(name = "SwingBuilder", applicationType = ApplicationType.Desktop, guiToolkit = GUIToolkit.swt,guiWidget = GUIWidget.Swt)
public class PESwtGUIBuilder {

}
