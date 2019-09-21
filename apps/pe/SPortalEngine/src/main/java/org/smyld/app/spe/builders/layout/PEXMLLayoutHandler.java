package org.smyld.app.spe.builders.layout;

import org.smyld.app.pe.annotations.PELayoutHandler;
import org.smyld.app.pe.model.ApplicationType;
import org.smyld.app.pe.model.GUIToolkit;
import org.smyld.app.pe.model.LayoutType;

@PELayoutHandler(name = "Local Swing XML Reader", applicationType = ApplicationType.Desktop, guiToolkit = GUIToolkit.swt, layoutType = LayoutType.XML)
public class PEXMLLayoutHandler {

}
