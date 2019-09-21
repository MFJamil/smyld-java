package com.smyld.app.spe.builders.layout;

import com.smyld.app.pe.annotations.PELayoutHandler;
import com.smyld.app.pe.model.ApplicationType;
import com.smyld.app.pe.model.GUIToolkit;
import com.smyld.app.pe.model.LayoutType;

@PELayoutHandler(name = "Local Swing XML Reader", applicationType = ApplicationType.Desktop, guiToolkit = GUIToolkit.swt, layoutType = LayoutType.XML)
public class PEXMLLayoutHandler {

}
