/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.web.gui.builder;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.smyld.app.pe.model.gui.GUIComponent;
import org.smyld.lang.script.core.CodeFileWriter;

@Slf4j
@Setter @Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class WUIBuilder {

    @NonNull GUIComponent component;
    CodeFileWriter codeFileWriter;
    WUIBuilderFactory wuiBuilderFactory;
    StringBuffer sb;
    int tabsNo;

    public int build(StringBuffer sb,int tabsNo){
        return tabsNo+1;
    }
    public int build(CodeFileWriter codeFileWriter,StringBuffer sb, int tabsNo){
        return tabsNo+1;
    }
    public int build(WUIBuilderFactory wuiBuilderFactory, CodeFileWriter codeFileWriter,StringBuffer sb, int tabsNo){
        this.codeFileWriter = codeFileWriter;
        this.wuiBuilderFactory = wuiBuilderFactory;
        this.sb = sb;
        this.tabsNo = tabsNo;
        return tabsNo+1;
    }

}
