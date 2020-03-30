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
import org.smyld.app.pe.model.gui.MenuItem;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Setter
@Getter
public class MenuBuilder extends VuetifyBuilder {

    String transition = "slide-y-transition";
    private StringSubstitutor stringSubstitutor;

    public MenuBuilder(){init();}


    private void init(){
        Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put("transition", getTransition());
        stringSubstitutor = new StringSubstitutor(valuesMap);

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
                  @click="handleClick(item.action);"
                >
                  <v-list-item-title>{{ item.title }}</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
    * */
    public String buildGeneralMenu(){
        //ToDo we need to think about a better way to build the toolbar
        String toolbarsText = "        <v-col\n" +
                "          v-for=\"(toolbar,id) in toolbar\"\n" +
                "          :key=\"id\"\n" +
                "          :align=toolbar.align\n" +
                "        >\n" +
                "          <v-btn\n" +
                "          text fab small\n" +
                "          v-for=\"(item,id) in toolbar.items\"\n" +
                "          :key=\"id\"\n" +
                "           @click=\"handleToolbar(item.action);\"\n" +
                "            ><v-icon small>{{item.icon}}</v-icon></v-btn>\n" +
                "        </v-col>";
        return toolbarsText;
    }

    public String buildMainMenuData(MenuItem mainMenu){
        log.info("Main Application menu " + mainMenu.toString());
        StringBuffer sb  = new StringBuffer();
        //ToDo the below code should be updated to adopt n-th level menus in the future
        mainMenu.getChildren().forEach(comp ->{
            /*
          { id: 'menu1',
            title: 'Dateie',
            subMenu: [
              {title: 'Öffnen'},
              {title: 'Speicher'},
              {title: 'Theme', action:'changeTheme'},
              {title: 'Beenden'},
            ]
          },
           */
            MenuItem curMenu = (MenuItem) comp;
            String space = "            ";
            //ToDo below should be replaced with a professional JSON writer
            sb.append(space);sb.append("{\n");
            sb.append(space); sb.append(" id: '"); sb.append(curMenu.getID());sb.append("',\n");
            sb.append(space); sb.append(" title: '"); sb.append(handleItemLabel(curMenu));sb.append("',\n");
            //ToDo here we do have only 1 level menu navigation, this needs to be updated later
            if (curMenu.hasChildren()){
                sb.append(space);sb.append(" subMenu: [\n");
                curMenu.getChildren().forEach(sub ->{
                    MenuItem curSub = (MenuItem) sub;
                    sb.append(space);sb.append("  {title: '"); sb.append(handleItemLabel(curSub));sb.append("'");
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
