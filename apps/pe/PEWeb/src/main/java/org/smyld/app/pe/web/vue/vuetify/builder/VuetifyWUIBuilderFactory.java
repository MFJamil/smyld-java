/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.web.vue.vuetify.builder;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.smyld.app.pe.model.gui.GUIComponent;
import org.smyld.app.pe.web.gui.builder.WUIBuilder;
import org.smyld.app.pe.web.gui.builder.WUIBuilderFactory;

@Slf4j @Setter @Getter
public class VuetifyWUIBuilderFactory extends WUIBuilderFactory {

    @Override
    public WUIBuilder getBuilder(GUIComponent guiComponent) {
        return null;
    }
}
