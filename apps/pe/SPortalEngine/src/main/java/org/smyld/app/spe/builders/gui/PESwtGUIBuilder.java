package org.smyld.app.spe.builders.gui;

import org.smyld.app.pe.annotations.PEGUIBuilder;
import org.smyld.app.pe.model.ApplicationType;
import org.smyld.app.pe.model.GUIToolkit;

@PEGUIBuilder(name = "SwingBuilder", applicationType = ApplicationType.Desktop, guiToolkit = GUIToolkit.swt)
public class PESwtGUIBuilder {

}
