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
import org.smyld.app.pe.model.gui.GUIToolbar;
import org.smyld.app.pe.model.gui.MenuItem;
import org.smyld.app.pe.model.gui.holders.GUIToolbarHolder;

import java.util.HashMap;
@Slf4j @Setter @Getter
public class ToolbarBuilder extends VuetifyBuilder {


    public String handleAppToolbars(HashMap<String, GUIToolbarHolder> toolbars){
        log.info("Main Application toolbars " + toolbars.toString());
        StringBuffer sb  = new StringBuffer();
        toolbars.values().stream()
                .sorted((tlb1,tlb2)->tlb1.getOrder()-tlb2.getOrder()).forEach(toolbarHolder->{
            /*
        { id: 'toolbar1',
            align:'left',
            items: [
                {icon: 'mdi-close-circle-outline'},
                {icon: 'mdi-undo-variant'},
                {icon: 'mdi-pause', action:'editDocument'},
                {icon: 'mdi-file-document-edit-outline'},
                    ]
        },
           */
            GUIToolbar toolbar = toolbarHolder.getToolbar();
            String space = "            ";
            //ToDo below should be replaced with a professional JSON writer
            sb.append(space);sb.append("{\n");
            sb.append(space); sb.append(" id: '"); sb.append(toolbar.getID());sb.append("',\n");
            if (toolbarHolder.getAlign()!=null) {
                sb.append(space);
                sb.append(" align: '");
                sb.append(toolbarHolder.getAlign());
                sb.append("',\n");
            }
            //ToDo here we do have only 1 level menu navigation, this needs to be updated later
            if (toolbar.hasChildren()){
                sb.append(space);sb.append(" items: [\n");
                toolbar.getChildren().forEach(sub ->{
                    MenuItem curSub = (MenuItem) sub;
                    sb.append(space);sb.append("  {icon: '"); sb.append(curSub.getIcon());sb.append("'");
                    if (curSub.getAction()!=null) {
                        sb.append(",action: '");
                        sb.append(curSub.getAction().getID());
                        sb.append("'");
                    }
                    sb.append("},\n");
                });

                sb.append(space);sb.append(" ]\n");
            }
            sb.append(space);sb.append("},\n");
        });

        return sb.toString();
    }

}
