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
import org.apache.commons.text.StringSubstitutor;
import org.smyld.app.pe.model.gui.GUIComponent;
import org.smyld.app.pe.model.gui.holders.GUIToolbarHolder;
import org.smyld.app.pe.web.gui.builder.WUIBuilderFactory;
import org.smyld.lang.script.core.CodeFileWriter;
import org.smyld.lang.script.ts.TSClassBody;
import org.smyld.lang.script.ts.TSFileWriter;
import org.smyld.lang.script.ts.TSVariable;

import java.util.HashMap;
@Slf4j @Setter @Getter
public class VtfToolbarBuilder extends VtfUIBuilder {

    HashMap<String, GUIToolbarHolder> toolbars;
    public VtfToolbarBuilder(GUIComponent guiComponent) {
        super(guiComponent);
    }
    public VtfToolbarBuilder(HashMap<String, GUIToolbarHolder> toolbars){
        this.toolbars = toolbars;

    }

    private String buildVueComp(){
        String toolbarsText = "${ind}<v-btn-toggle  v-model=\"toggle_exclusive\" background-color=\"transparent\" dark>\n" +
                                "${ind1}<v-btn text fab small v-for=\"(item,id) in toolbar.items\" :key=\"id\" @click=\"handleAction(item);\">\n" +
                                    "${ind2}<v-icon small>{{item.icon}}</v-icon></v-btn>\n" +
                                "${ind1}</v-btn-toggle>\n";
        return ss.replace(toolbarsText);
    }

    @Override
    public int build(WUIBuilderFactory wuiBuilderFactory, CodeFileWriter codeFileWriter, StringBuffer sb, int tabsNo){
        if (codeFileWriter instanceof TSFileWriter){
            ss = new StringSubstitutor(VtfBuildUtils.createBuildVars(tabsNo));
            TSFileWriter fileWriter  = (TSFileWriter) codeFileWriter;
            TSClassBody tsClassBody = fileWriter.getDefaultClassBody();
            String varValue = VtfBuildUtils.handleAppToolbars(this.toolbars);
            tsClassBody.addVariable(new TSVariable().setName("toolbar").setScope("public").setDefaultValue(varValue));
            VtfBuildUtils.handleAddingProcessAction(fileWriter);
            sb.append(buildVueComp());
        }

        return tabsNo;
    }


}
