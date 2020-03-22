/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.model.gui.holders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.smyld.app.pe.model.gui.GUIToolbar;

@Slf4j
@RequiredArgsConstructor
public class GUIToolbarHolder {

    @Getter
    @Setter
    private String align;

    @Getter
    @Setter
    private GUIToolbar toolbar;



}
