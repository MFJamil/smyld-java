/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.web.vue.vuetify.builder;

import org.smyld.app.pe.model.gui.GUIComponent;
import org.smyld.app.pe.model.gui.GUIToolbar;
import org.smyld.app.pe.model.gui.MenuItem;
import org.smyld.app.pe.model.gui.holders.GUIToolbarHolder;
import org.smyld.lang.script.ts.*;
import org.smyld.text.TextUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class VtfBuildUtils {
    public static final String[] imgExts = {"ico","png","jpg","gif","pic"};
    public static String buildMenuData (MenuItem menuItem){
        StringBuffer sb  = new StringBuffer();
        String space = "            ";
        sb.append("[");
        //ToDo the below code should be updated to adopt n-th level menus in the future
        menuItem.getChildren().forEach(comp ->{
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

            //ToDo below should be replaced with a professional JSON writer
            sb.append(space).append("{\n");
            sb.append(space).append(" id: '").append(curMenu.getID()).append("',\n");
            sb.append(space).append(" title: '").append(handleItemLabel(curMenu)).append("',\n");
            //ToDo here we do have only 1 level menu navigation, this needs to be updated later
            if (curMenu.hasChildren()){
                sb.append(space).append(" subMenu: [\n");
                curMenu.getChildren().forEach(sub ->{
                    MenuItem curSub = (MenuItem) sub;
                    sb.append(space).append("  {title: '").append(handleItemLabel(curSub)).append("'");
                    if (curSub.getAction()!=null)
                        sb.append(",action: '").append(curSub.getAction().getID()).append("'");
                    sb.append("},\n");
                });

                sb.append(space).append(" ]\n");
            }
            sb.append(space).append("},\n");
        });
        sb.append(space).append("]");
        return sb.toString();


    }

    public static Map<String,String> createBuildVars(int tabsNo){
        String indent = TextUtil.createWord("\t",tabsNo);
        Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put("ind", indent);
        valuesMap.put("ind1", indent + "\t");
        valuesMap.put("ind2", indent + "\t\t");
        valuesMap.put("ind3", indent + "\t\t\t");
        valuesMap.put("ind4", indent + "\t\t\t\t");
        valuesMap.put("ind5", indent + "\t\t\t\t\t");
        valuesMap.put("ind6", indent + "\t\t\t\t\t\t");
        return valuesMap;

    }

    public static String handleItemLabel(GUIComponent comp){
        //ToDo this should be updated with the multi-lang call in the future

        return comp.getLabel()!=null?comp.getLabel():comp.getID();
    }

    public static String handleIcon(String icon, String curPath){
        if (icon==null) return null;
        if (isCDNIcon(icon)) return icon;
        return getFileLocation(icon,curPath);
    }

    public static String getFileLocation(String fileName,String packagePath){
        int levels = TextUtil.occuranceOf(packagePath, ".");
        String packageLib = TextUtil.createWord("../", levels) + fileName;
        return packageLib;
    }

    public static String resolveImport(String srcPath,String packagePath){
        int levels = TextUtil.occuranceOf(srcPath, ".");
        String packageLib = TextUtil.createWord("../", levels-1) + packagePath.replaceAll("\\.","/") ;
        if (packageLib.endsWith("/vue"))
            packageLib = packageLib.substring(0,packageLib.length()-4) + ".vue";
        return packageLib;
    }
    public static String resolveImport(String srcPath,String packagePath,String fileName){
        int levels = TextUtil.occuranceOf(srcPath, ".");
        String packageLib = TextUtil.createWord("../", levels) + packagePath.replaceAll("\\.","/") + fileName ;
        return packageLib;
    }

    public static String excludeVueFileFromPackage(String fullPath){
        if (!fullPath.endsWith(".vue")) return fullPath;
        fullPath = fullPath.substring(0,fullPath.lastIndexOf("."));
        return fullPath.substring(0,fullPath.lastIndexOf("."));


    }


    public static boolean isCDNIcon(String icon){
        for(String ext: imgExts)
            if (icon.endsWith("." + ext))
                return false;
        return true;
    }

    public static  TSClassBody createVueClassBody(String className){
        // Creating the test class
        TSClassBody tsCB = new TSClassBody();
        tsCB.setName(className);
        tsCB.setParentClassName("Vue");
        // Adding parent class import ( we need to automatically add it during the setting of parent class)
        TSImport tsImport = new TSImport("vue-property-decorator");
        tsImport.addClass("Vue");
        tsCB.getImportsHandler().addImport(tsImport);
        tsCB.addAnnotation(new TSAnnotation("Component","vue-property-decorator"));
        return tsCB;
    }


    public static void handleAddingProcessAction(TSFileWriter fileWriter ){
        TSClassBody  tsClassBody = fileWriter.getDefaultClassBody();
        TSMethod tsMethod = new TSMethod();
        tsMethod.setName("handleAction");
        tsMethod.setReturnType("void");
        tsMethod.addParameter("action","any");
        if (!tsClassBody.containsMethod(tsMethod)){
            tsMethod.addCodeLine("if (action){");
            tsMethod.addCodeLine("this.$emit('runAction',action)");
            tsMethod.addCodeLine("}");
            tsClassBody.addMethod(tsMethod);
        }

    }

    public static String handleAppToolbars(HashMap<String, GUIToolbarHolder> toolbars){
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

        StringBuffer sb  = new StringBuffer();
        sb.append("[");

        toolbars.values().stream()
                .sorted((tlb1,tlb2)->tlb1.getOrder()-tlb2.getOrder()).forEach(toolbarHolder->{
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
        sb.append("]");
        return sb.toString();
    }

}
