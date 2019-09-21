package org.smyld.app.pe.annotations;

import org.smyld.app.pe.model.ApplicationType;
import org.smyld.app.pe.model.GUIToolkit;
import org.smyld.app.pe.model.LayoutType;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)

public @interface PELayoutHandler {
    LayoutType layoutType();
    ApplicationType applicationType();
    GUIToolkit guiToolkit();
    String name();

}
