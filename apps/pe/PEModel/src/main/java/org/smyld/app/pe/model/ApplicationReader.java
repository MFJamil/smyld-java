package org.smyld.app.pe.model;

public interface ApplicationReader {

    GUIToolkit getGUIToolkit();
    ApplicationType getType();
    LayoutType getLayout();

    default boolean isValid(){
        return getGUIToolkit()!=null;
    }

}
