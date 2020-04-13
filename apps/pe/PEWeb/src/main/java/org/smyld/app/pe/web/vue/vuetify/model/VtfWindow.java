/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.web.vue.vuetify.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.smyld.app.pe.model.gui.GUIWindow;


@Slf4j
@Setter
@Getter
public class VtfWindow extends GUIWindow {

    private boolean maximize = true;
    private boolean minimize = true;
    private boolean close    = true;
    private String  barColor = "Primary" ;



}
