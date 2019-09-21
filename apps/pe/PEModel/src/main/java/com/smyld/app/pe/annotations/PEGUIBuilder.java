package com.smyld.app.pe.annotations;

import com.smyld.app.pe.model.ApplicationType;
import com.smyld.app.pe.model.GUIToolkit;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PEGUIBuilder {
    String name();
    ApplicationType applicationType();
    GUIToolkit guiToolkit();
}
