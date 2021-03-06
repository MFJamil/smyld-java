/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.web.gui.builder;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.smyld.app.pe.model.gui.GUIComponent;

@Slf4j
@Getter
@Setter
public abstract class WUIBuilderFactory {

    public abstract  WUIBuilder getBuilder(GUIComponent guiComponent);

}
