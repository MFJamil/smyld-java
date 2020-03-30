package org.smyld.app.pe.annotations;

import org.smyld.app.pe.model.ApplicationType;
import org.smyld.app.pe.model.GUIToolkit;
import org.smyld.app.pe.model.GUIWidget;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PEGUIBuilder {
    String name();
    ApplicationType applicationType();
    GUIToolkit guiToolkit();
    GUIWidget guiWidget();
}
