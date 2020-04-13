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
import org.smyld.app.pe.model.gui.MenuItem;
import org.smyld.app.pe.web.gui.builder.WUIBuilder;
import org.smyld.app.pe.web.gui.builder.WUIBuilderFactory;
import org.smyld.app.pe.web.vue.vuetify.model.VtfMenuItem;
import org.smyld.app.pe.web.vue.vuetify.model.VtfWindow;

@Slf4j @Setter @Getter
public class VtfUIBuilderFactory extends WUIBuilderFactory {

    @Override
    public WUIBuilder getBuilder(GUIComponent guiComponent) {
        if (guiComponent instanceof VtfMenuItem){
            return new VtfMenuBuilder((VtfMenuItem) guiComponent);
        }else if (guiComponent instanceof VtfWindow) {
            return new VtfWindowBuilder((VtfWindow) guiComponent);
        }
        return null;
    }
}
