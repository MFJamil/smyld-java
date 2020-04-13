/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.web.vue.vuetify.builder;

import lombok.NoArgsConstructor;
import org.apache.commons.text.StringSubstitutor;
import org.smyld.app.pe.model.gui.GUIComponent;
import org.smyld.app.pe.web.vue.builder.VueUIBuilder;


public class VtfUIBuilder extends VueUIBuilder {
    protected StringSubstitutor ss;
    public VtfUIBuilder(){}
    public VtfUIBuilder(GUIComponent guiComponent) {
        super(guiComponent);
    }



}
