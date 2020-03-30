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

import javax.inject.Inject;
import java.util.List;

@Slf4j @Getter @Setter
public class WebBuilder {
    List<WUIBuilder> wuiBuilders;
    @Inject
    WUIBuilderFactory wuiBuilderFactory;

    public void addComponent(GUIComponent guiComponent){
        wuiBuilders.add(wuiBuilderFactory.getBuilder(guiComponent));
    }

    public String build(){
        int tabsNo = 1;
        StringBuffer sb = new StringBuffer();
        for (WUIBuilder wuiBuilder:wuiBuilders){
            tabsNo = wuiBuilder.build(sb,tabsNo);
        }
        return sb.toString();
    }
}
