package org.smyld.app.spe.factory;

import org.smyld.app.pe.annotations.PELayoutHandler;
import org.smyld.app.pe.annotations.PEGUIBuilder;
import org.smyld.app.pe.model.GUIToolkit;
import org.smyld.app.pe.model.LayoutType;
import org.smyld.reflections.AnnotatedType;

public interface AbstractFactory {

    public AnnotatedType<PEGUIBuilder> getGUIBuilder(GUIToolkit toolkit);
    public AnnotatedType<PELayoutHandler> getLayoutHandler(GUIToolkit toolkit, LayoutType layoutType);
}
