package com.smyld.app.pe.annotations;

import com.smyld.app.pe.model.ApplicationType;
import com.smyld.app.pe.model.GUIToolkit;
import com.smyld.app.pe.model.LayoutType;

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
