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

@Slf4j
@Setter
@Getter
public class WindowBarBuilder {
    public String height;
    public String minWidth;
    public String extraProbs;
    public String color;
    public String barClass;
    public String appTitle;
    public String barIcon;
    public String transition;
    public String iconWidth;



    public String buildWindowBar(){


        String appBarText = "<v-app-bar\n" +
                "      extended\n" +
                "      extension-height=\"${height}px\"\n" +
                "      ${extraProps}\n" +
                "      app\n" +
                "      min-width=\"${minWidth}px\"\n" +
                "      color=\"${color}\"\n" +
                "      :class=\"${barClass}\"\n" +
                //"      :dark=\"themeDark\"\n" +
                "      >\n" +
                "\n" +
                "      <v-app-bar-nav-icon> </v-app-bar-nav-icon>\n" +
                "      <v-toolbar-title>${appTitle}</v-toolbar-title>\n" +
                "\n" +
                "      <v-spacer></v-spacer>\n" +
                "      <div class=\"d-flex align-center\">\n" +
                "        <v-img\n" +
                "          alt=\"KFW Logo\"\n" +
                "          class=\"shrink mr-2\"\n" +
                "          contain\n" +
                "          src=\"../../assets/${barIcon}\"\n" +
                "          transition=\"${transition}\"\n" +
                "          width=\"${iconWidth}\"\n" +
                "        />\n" +
                "\n" +
                "      </div>\n" +
                "      ${extension}" +
                "      <template v-slot:extension >\n" +
                "        <v-container  fluid >\n" +
                "        <v-row fluid no-gutters>\n" +
                "          <v-col>\n" +
                "            <v-menu\n" +
                "              v-for=\"(menu,id) in menus\"\n" +
                "              :key=\"id\"\n" +
                "              bottom\n" +
                "              origin=\"center center\"\n" +
                "              offset-y\n" +
                "              transition=\"slide-y-transition\"\n" +
                "            >\n" +
                "              <template v-slot:activator=\"{ on }\">\n" +
                "                <v-btn\n" +
                "                  text small\n" +
                "                  v-on=\"on\"\n" +
                "                  width=\"120px\"\n" +
                "                >\n" +
                "                  {{menu.title}}\n" +
                "                </v-btn>\n" +
                "              </template>\n" +
                "              <v-list dense>\n" +
                "                <v-list-item\n" +
                "                  v-for=\"(item, id) in menu.subMenu\"\n" +
                "                  :key=\"id\"\n" +
                "                  @click=\"handleClick(item.action);\"\n" +
                "                >\n" +
                "                  <v-list-item-title>{{ item.title }}</v-list-item-title>\n" +
                "                </v-list-item>\n" +
                "              </v-list>\n" +
                "            </v-menu>\n" +
                "          </v-col>\n" +
                "        </v-row>\n" +
                "      <v-row  no-gutters>\n" +
                "        <v-col\n" +
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
                "        </v-col>\n" +
                "      </v-row>\n" +
                "        </v-container>\n" +
                "    </template>\n" +

                "  </v-app-bar>";
        return appBarText;


    }
}
