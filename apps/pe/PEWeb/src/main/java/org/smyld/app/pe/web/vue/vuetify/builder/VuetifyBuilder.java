/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.web.vue.vuetify.builder;

import org.smyld.app.pe.model.gui.GUIComponent;
import org.smyld.app.pe.web.gui.builder.WUIBuilder;

public class VuetifyBuilder extends WUIBuilder {

    protected String handleItemLabel(GUIComponent comp){
        //ToDo this should be updated with the multi-lang call in the future

        return comp.getLabel()!=null?comp.getLabel():comp.getID();
    }


}
