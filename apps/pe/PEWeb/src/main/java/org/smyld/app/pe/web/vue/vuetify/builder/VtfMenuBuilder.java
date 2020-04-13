/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.web.vue.vuetify.builder;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.smyld.app.pe.model.gui.GUIComponent;
import org.smyld.app.pe.web.gui.builder.WUIBuilderFactory;
import org.smyld.app.pe.web.vue.vuetify.model.VtfMenuItem;
import org.smyld.lang.script.core.CodeFileWriter;
import org.smyld.lang.script.ts.TSClassBody;
import org.smyld.lang.script.ts.TSFileWriter;
import org.smyld.lang.script.ts.TSMethod;
import org.smyld.lang.script.ts.TSVariable;

import java.util.Map;

@Slf4j
@Setter
@Getter
public class VtfMenuBuilder extends VtfUIBuilder {

    @NonNull
    VtfMenuItem vtfMenuItem;

    private String indent;

    public VtfMenuBuilder(GUIComponent guiComponent) {
        super(guiComponent);
        vtfMenuItem = (VtfMenuItem) guiComponent;
    }

    private void init(int tabsNo){
        Map<String, String> valuesMap = VtfBuildUtils.createBuildVars(tabsNo);
        valuesMap.put("transition", "slide-y-transition"); //vtfMenuItem.getTransition());
        valuesMap.put("menuId", vtfMenuItem.getID());
        ss = new StringSubstitutor(valuesMap);
    }

    /*
        <v-menu
          v-for="(menu,id) in menus"
          :key="id"
          bottom
          origin="center center"
          offset-y
          transition="slide-y-transition"
        >
          <template v-slot:activator="{ on }">
            <v-btn
              text small
              v-on="on"
              width="120px"
            >
              {{menu.title}}
            </v-btn>
          </template>
          <v-list dense>
            <v-list-item
              v-for="(item, id) in menu.subMenu"
              :key="id"
              @click="handleAction(item);"
            >
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
    * */
    private String buildGeneralMenu(){
        //ToDo we need to think about a better way to build the toolbar
        String menuText = "${ind}<v-menu\n" +
                            "${ind1}v-for=\"(menu,id) in ${menuId}\"\n" +
                            "${ind1}:key=\"id\"\n" +
                            "${ind1}bottom\n" +
                            "${ind1}origin=\"center center\"\n" +
                            "${ind1}offset-y\n" +
                            "${ind1}transition=\"${transition}\"\n" +
                          "${ind}>\n" +
                            "${ind1}<template v-slot:activator=\"{ on }\">\n" +
                              "${ind2}<v-btn\n" +
                                "${ind3}text small\n" +
                                "${ind3}v-on=\"on\"\n" +
                                "${ind3}width=\"120px\"\n" +
                              "${ind2}>\n" +
                                "${ind3}{{menu.title}}\n" +
                              "${ind2}</v-btn>\n" +
                            "${ind1}</template>\n" +
                            "${ind1}<v-list dense>\n" +
                              "${ind2}<v-list-item\n" +
                                "${ind3}v-for=\"(item, id) in menu.subMenu\"\n" +
                                "${ind3}:key=\"id\"\n" +
                                "${ind3}@click=\"handleAction(item);\"\n" +
                              "${ind2}>\n" +
                                "${ind3}<v-list-item-title>{{ item.title }}</v-list-item-title>\n" +
                              "${ind2}</v-list-item>\n" +
                            "${ind1}</v-list>\n" +
                          "${ind}</v-menu>\n";
            return ss.replace(menuText);

    }

    @Override
    public int build(WUIBuilderFactory wuiBuilderFactory, CodeFileWriter codeFileWriter, StringBuffer sb, int tabsNo){
        if (codeFileWriter instanceof TSFileWriter){
            init(tabsNo);
            TSFileWriter fileWriter  = (TSFileWriter) codeFileWriter;
            TSClassBody  tsClassBody = fileWriter.getDefaultClassBody();
            String varValue = buildMainMenuData();
            tsClassBody.addVariable(new TSVariable().setName(vtfMenuItem.getID()).setScope("public").setDefaultValue(varValue));
            VtfBuildUtils.handleAddingProcessAction(fileWriter);
            sb.append(buildGeneralMenu());
        }

        return tabsNo;
    }


    public String buildMainMenuData(){
        log.info("Main Application menu " + vtfMenuItem.toString());
        return VtfBuildUtils.buildMenuData(vtfMenuItem);
    }




}
