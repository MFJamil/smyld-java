/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.web.vue.builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.smyld.app.pe.model.gui.GUIComponent;
import org.smyld.app.pe.web.gui.builder.WUIBuilder;

@Setter
@Getter
@Slf4j
@NoArgsConstructor
public class VueUIBuilder extends WUIBuilder {

    public VueUIBuilder(GUIComponent guiComponent){super(guiComponent);}


}
