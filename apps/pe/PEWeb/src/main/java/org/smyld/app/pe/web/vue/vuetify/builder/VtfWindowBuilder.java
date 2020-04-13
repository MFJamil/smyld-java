/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.web.vue.vuetify.builder;

import org.apache.commons.text.StringSubstitutor;
import org.smyld.app.pe.model.gui.GUIComponent;
import org.smyld.app.pe.web.gui.builder.WUIBuilderFactory;
import org.smyld.app.pe.web.vue.builder.VueTsWriter;
import org.smyld.app.pe.web.vue.vuetify.model.VtfWindow;
import org.smyld.lang.script.core.CodeFileWriter;
import org.smyld.lang.script.ts.TSClassBody;
import org.smyld.lang.script.ts.TSVariable;

import java.util.Map;

public class VtfWindowBuilder extends VtfUIBuilder {



    VtfWindow vtfWindow;

    public VtfWindowBuilder(GUIComponent guiComponent) {
        super(guiComponent);
        vtfWindow = (VtfWindow)guiComponent;
    }

    private void init(int tabsNo){
        Map<String, String> valuesMap = VtfBuildUtils.createBuildVars(tabsNo);
        valuesMap.put("transition", "slide-y-transition"); //vtfMenuItem.getTransition());
        valuesMap.put("windowId", vtfWindow.getID());
        //ToDo need to handle the image
        valuesMap.put("windowIcon",VtfBuildUtils.handleIcon(vtfWindow.getIcon(),vtfWindow.getPackage()));
        valuesMap.put("windowWidth",vtfWindow.getWidth());
        valuesMap.put("barColor",vtfWindow.getBarColor());
        valuesMap.put("extension",(extendToolbar()?"extended extension-height=\"80px\"":""));
        ss = new StringSubstitutor(valuesMap);
    }


    private String buildPart1(){
        boolean max = vtfWindow.isMaximize();
        boolean min = vtfWindow.isMinimize();
        boolean cls = vtfWindow.isClose();
        String contents = "${ind}<v-container class=\"fill-height\" fluid>\n" +
                "${ind1}<v-card class=\"mx-auto\"  max-width=\"${windowWidth}\">\n" +
                    "${ind2}<v-system-bar color=\"${barColor} darken-1\" dark>\n" +
                        "${ind3}<v-spacer></v-spacer>\n" +
                   (min?"${ind3}<v-icon>mdi-window-minimize</v-icon>\n":"") +
                   (max?"${ind3}<v-icon>mdi-window-maximize</v-icon>\n":"   ") +
                   (cls?"${ind3}<v-icon>mdi-close</v-icon>\n":"") +
                    "${ind2}</v-system-bar>\n" +
                    "${ind2}<v-toolbar color=\"${barColor}\" dark ${extension}>\n" +
                        "${ind3}<v-app-bar-nav-icon></v-app-bar-nav-icon>\n" +
                        "${ind3}<v-toolbar-title>{{windowTitle}}</v-toolbar-title>\n" +
                        "${ind3}<v-spacer></v-spacer>\n" +
                        "${ind3}<v-btn icon><v-icon>${windowIcon}</v-icon></v-btn>\n";

            return ss.replace(contents);
    }

    private void processExtension(boolean menu,boolean toolbars){
        if ((!menu)&&(!toolbars)) return;
        getSb().append(ss.replace("${ind3}<template v-slot:extension>\n" +
                                                    "${ind4}<v-container  fluid >\n"));
        if (menu){
            getSb().append(ss.replace("${ind5}<v-row fluid no-gutters>\n" +
                                                                "${ind6}<v-col>\n"));
            getWuiBuilderFactory().getBuilder(vtfWindow.getMainMenu()).build(getWuiBuilderFactory(),getCodeFileWriter(),getSb(),getTabsNo()+7);
            getSb().append(ss.replace("${ind6}</v-col>\n" +
                    "${ind5}</v-row>\n"));

        }
        if (toolbars){
            getSb().append(ss.replace("${ind5}<v-row fluid no-gutters>\n"+
                                                "${ind6}<v-col v-for=\"(toolbar,id) in toolbar\"  :key=\"id\" :align=toolbar.align>\n"));
            VtfToolbarBuilder vtfToolbarBuilder = new VtfToolbarBuilder(vtfWindow.getToolbars());
            vtfToolbarBuilder.build(getWuiBuilderFactory(),getCodeFileWriter(),getSb(),getTabsNo()+7);

            getSb().append(ss.replace("${ind6}</v-col>\n"+
                                            "${ind5}</v-row>\n"));

        }
        getSb().append(ss.replace("${ind3}</v-container>\n" +
                            "${ind2}</template>\n")) ;
    }

    private String buildPart2(){
        String contents = "${ind2}</v-toolbar>\n" +
                "${ind2}<v-container fluid>\n";
        return ss.replace(contents);
    }

    private String buildPart3(){
        String contents =
                "${ind2}</v-container>\n" +
             "${ind1}</v-card>\n" +
           "${ind}</v-container>\n";
        return ss.replace(contents);
    }



    private TSClassBody createWindowTSClass(){
        TSClassBody windowClass = VtfBuildUtils.createVueClassBody(vtfWindow.getID());
        TSVariable varWindowTitle = new TSVariable();
        varWindowTitle.setName("windowTitle").setType("string").setScope("public").setDefaultValue("'" + vtfWindow.getLabel() + "'");
        windowClass.addVariable(varWindowTitle);
        return windowClass;
    }

    private boolean extendToolbar(){
        return ((vtfWindow.getMainMenu()!=null)&&(vtfWindow.getToolbars().size()>0));
    }

    @Override
    public int build(WUIBuilderFactory wuiBuilderFactory, CodeFileWriter codeFileWriter, StringBuffer sb, int tabsNo){
        super.build(wuiBuilderFactory,codeFileWriter,sb,tabsNo);
        if (codeFileWriter instanceof VueTsWriter) {
            init(tabsNo);
            VueTsWriter tsFileWriter = (VueTsWriter) codeFileWriter;
            tsFileWriter.addClass(createWindowTSClass(),true);
            sb.append(buildPart1());
            processExtension(vtfWindow.getMainMenu()!=null,vtfWindow.getToolbars().size()>0);
            sb.append(buildPart2());
            sb.append(handleWindowBody(tsFileWriter));
            sb.append(buildPart3());
        }
        return tabsNo;
    }

    private String handleWindowBody(VueTsWriter tsFileWriter ){
        if (vtfWindow.getBodySrc()==null) return "";
        tsFileWriter.addComponentToClass(tsFileWriter.getDefaultClassBody(),vtfWindow.getBodyID(),vtfWindow.getBodySrc());
        return(ss.replace("${ind3}<" + vtfWindow.getBodyID() + " />\n"));
    }



}
