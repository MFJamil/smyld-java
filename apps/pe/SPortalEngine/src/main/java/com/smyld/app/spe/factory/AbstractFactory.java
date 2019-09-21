package com.smyld.app.spe.factory;

import com.smyld.app.pe.annotations.PELayoutHandler;
import com.smyld.app.pe.annotations.PEGUIBuilder;
import com.smyld.app.pe.model.GUIToolkit;
import com.smyld.app.pe.model.LayoutType;
import com.smyld.reflections.AnnotatedType;

public interface AbstractFactory {

    public AnnotatedType<PEGUIBuilder> getGUIBuilder(GUIToolkit toolkit);
    public AnnotatedType<PELayoutHandler> getLayoutHandler(GUIToolkit toolkit, LayoutType layoutType);
}
