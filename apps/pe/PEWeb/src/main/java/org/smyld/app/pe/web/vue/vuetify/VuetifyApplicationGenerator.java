/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.web.vue.vuetify;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.smyld.app.pe.annotations.PEApplicationReader;
import org.smyld.app.pe.annotations.PEGUIBuilder;
import org.smyld.app.pe.annotations.PEGenerateApplication;
import org.smyld.app.pe.annotations.PEPreCompiledApplication;
import org.smyld.app.pe.model.*;
import org.smyld.app.pe.model.gui.*;
import org.smyld.app.pe.model.gui.holders.GUIToolbarHolder;
import org.smyld.app.pe.web.vue.vuetify.builder.VtfBuildUtils;
import org.smyld.lang.script.ts.TSAnnotation;
import org.smyld.lang.script.ts.TSClassBody;
import org.smyld.lang.script.ts.TSFileWriter;
import org.smyld.text.TextUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.apache.commons.io.FileUtils.*;


@Slf4j
@PEGUIBuilder(name = "PE WEB Builder",applicationType = ApplicationType.Web,guiToolkit = GUIToolkit.vue,guiWidget = GUIWidget.Vuetify)
public class VuetifyApplicationGenerator {
    //String templateSource = "vue/frontend_2020_01_06";
    String templateSource = "vue/vuetify/frontend_v1";

    Map<String,String> vars = new HashMap();
    String[] updateFiles = {
            "/vue.config.js",
            "/src/App.vue",
            "/src/router/index.ts",
            "/public/index.html",
            "/src/components/panels/AppHeader.vue"
    };


    @PEGenerateApplication
    public void generateFromApplicationReader(@PEApplicationReader ApplicationReader newAppReader) {
        //TODO here the Vue Generation should start
        log.info("Vue Application out of PE Application Reader should be generated now .......");


    }


    @PEGenerateApplication
    public void generateFromApplication(@PEPreCompiledApplication PEApplication newApp) {
        log.info("Vue Application out of PE Application should be generated now .......");
        // The below Casting should be removed later, we need to nominate a list of functions to migrate them to the PE Application Interface
        if (newApp instanceof PEApplication){
            PEApplication peApp = (PEApplication) newApp;
            log.info("--Home Path : " +  peApp.getHomePath());
            log.info("--App Name  : " +  peApp.getName());
            String webAppTemplate = ClassLoader.getSystemClassLoader().getResource(templateSource).getPath();
            String targetApp =  peApp.getHomePath() + File.separator + peApp.getName();
            copyTemplate(webAppTemplate,targetApp);
            handleImages(peApp,targetApp);
            handlePageIcon(peApp,targetApp);
            handleActions(peApp);
            addParams(peApp,targetApp);
            handleWindows(peApp);
            Stream.of(updateFiles).forEach(file -> updateFile(new File(targetApp + file)));
        }
    }

    private void handleWindows(PEApplication peApp){
        if (peApp.getWindows().values().stream().filter(w -> w.getWindowType()== WindowType.mdi).count()>1)
            throw new RuntimeException("Application can only have one MDI window !!");
        peApp.getWindows().forEach((id,win) ->{
            if (win.getWindowType()== WindowType.mdi){
                log.info("Detected Main Window : " + win.toString());
                processMainWindow(peApp,win);
            }

        });
    }

    private void processMainWindow(PEApplication peApp,GUIWindow mainWindow){
        // Processing the main menu
        if (mainWindow.getMenuBarID()!=null){
            MenuItem mainMenubar = peApp.getMenus().get(mainWindow.getMenuBarID());
            if (mainMenubar==null)
                log.warn("Main menu %s that is referenced in main window %s is not defined ",mainWindow.getMenuBarID(),mainWindow.getID());
            if ((mainMenubar.getChildren()!=null)||(mainMenubar.getChildren().size()>0))
                handleMainMenu(mainMenubar);
       }
        if ((mainWindow.getToolbars()!=null) && (mainWindow.getToolbars().size()>0)) {
            handleAppToolbars(mainWindow.getToolbars());
        }

    }

    private void handleMainMenu(MenuItem mainMenu){
        log.info("Main Application menu " + mainMenu.toString());
        vars.put("main_menu", VtfBuildUtils.buildMenuData(mainMenu));
    }

    private void handleAppToolbars(HashMap<String, GUIToolbarHolder> toolbars){
        log.info("Main Application toolbars " + toolbars.toString());
        vars.put("app_toolbars", VtfBuildUtils.handleAppToolbars(toolbars));

    }


    private String handleItemLabel(GUIComponent comp){
        //ToDo this should be updated with the multi-lang call in the future

        return comp.getLabel()!=null?comp.getLabel():comp.getID();
    }

    private void addParams(PEApplication peApp, String targetPath){
        handleMainClass(peApp,targetPath );
        vars.put("app_name"    ,peApp.getName());
        vars.put("app_title"   ,peApp.getTitle());
        vars.put("app_icon"    ,"facicon.ico");
        if (peApp.getLogo()!=null)
            vars.put("app_logo", peApp.getLogo());

    }

    private void handleMainClass(PEApplication peApp, String targetPath){
        String mainAppClass = peApp.getMainClass();
        String className = mainAppClass.substring(mainAppClass.lastIndexOf(".")+1);
        String classPackage =  mainAppClass.substring(0,mainAppClass.lastIndexOf(".")).replaceAll("\\.","/");
        vars.put("custAppFile" ,"./" + classPackage + "/" + className);
        vars.put("custAppClass",mainAppClass.substring(mainAppClass.lastIndexOf(".")+1));
        try {
            // We should avoid overwriting existing application class in case they already exist
            File baseDir = new File(targetPath + "/src/" + classPackage);
            if (!baseDir.exists())
                forceMkdir(baseDir);
            File mainClassFile =  new File(baseDir + "/" + className + ".ts");
            if (!mainClassFile.exists()) {
                TSFileWriter tsFileWriter = new TSFileWriter();
                TSClassBody tsClass = new TSClassBody();
                tsClass.setName(className);
                String packageLib = VtfBuildUtils.getFileLocation("PEAnnotations",mainAppClass);
                //int levels = TextUtil.occuranceOf(mainAppClass, ".");
                //String packageLib = TextUtil.createWord("../", levels) + "PEAnnotations";
                tsClass.addAnnotation(new TSAnnotation("PEApplication", packageLib));
                tsFileWriter.addClass(tsClass);
                tsFileWriter.addCodeLine("export default new " + className + "();");
                FileUtils.writeStringToFile(mainClassFile, tsFileWriter.print(""));
            }
        } catch (IOException e) {
            log.error("Problem by creating package folders " ,e);
        }


    }

    private void copyTemplate(String webAppTemplate,String targetApp){
        try {
            copyDirectory(new File(webAppTemplate),new File(targetApp));
        } catch (IOException e) {
            log.error("Error copying the Vue Template", e);
        }

    }

    private void handleImages(PEApplication peApp,String targetApp){
        HashMap<String,String> images =  peApp.getImages();

        // Handling the images
        images.forEach((k,v) -> {
            try {
                copyFile(new File (v),new File(targetApp + "/src/assets/" + k ));
            } catch (IOException e) {
                log.error("Can not copy image " + e);
            }
            //System.out.println(k + " :: " + v);
        });


    }
    private void handlePageIcon(PEApplication peApp,String targetApp){
        if (peApp.getIcon()!=null){
            if (peApp.getImages().containsKey(peApp.getIcon())){
                try {
                    copyFile(new File (peApp.getImages().get(peApp.getIcon())),new File(targetApp + "/public/" + peApp.getIcon()));
                    vars.put("app_icon",peApp.getIcon());
                } catch (IOException e) {
                    log.error("Error by copying over application icon " ,e);
                }
            }else{
                log.warn("Unable to allocate the application icon file provided : " + peApp.getIcon());
            }
        }

    }

    private void handleActions(PEApplication peApp){
        List<PEAction> proxyActions = new ArrayList<>();
        List<PEAction> routerActions = new ArrayList<>();
        peApp.getActions().forEach((k,v) -> {
            /*
            System.out.println(k + " : \n"
                    + " -- target : " + v.getTarget()
                    + ((v.getUserObject()!=null)?" -- userObject : " + v.getUserObject():"")
            );*/
            if (v.getUserObject()!=null){
                routerActions.add(v);
            }else{
                proxyActions.add(v);
            }
        });

        vars.put("proxy_settings",createProxyActions(proxyActions));
        vars.put("routes",createRouterActions(routerActions));


    }

    private String createProxyActions(List<PEAction> actions){
        StringBuffer sb = new StringBuffer();
        /*
          '^/authenticate': {
          target: 'http://localhost:8099',
          ws: true,
          changeOrigin: true
          },
        */
        actions.forEach(act -> {
            sb.append("        '");sb.append(act.getID());sb.append("':{\n");
            sb.append("          target: '");sb.append(act.getTarget());sb.append("',\n");
            sb.append("          ws: true,\n");
            sb.append("          changeOrigin: true\n");
            sb.append("        },\n");
        });
        return sb.toString();
    }

    private String createRouterActions(List<PEAction> actions){
        StringBuffer sb = new StringBuffer();
        /*
          {
            path: '/',
            name: 'mainApp',
            component: KfwApp,
            default: true,
          },
        */
        actions.forEach(act -> {
            sb.append("{\n");
            sb.append("   path: '"); sb.append(act.getTarget());sb.append("',\n");
            sb.append("   name: '"); sb.append(act.getID()); sb.append("',\n");
            sb.append("   component: ");sb.append(act.getUserObject());sb.append(",\n");
            sb.append("   default: true\n");
            sb.append("},\n");

        });
        return sb.toString();
    }


    private void updateFile(File fileName){
        try {
            String fileContents = readFileToString(fileName);
            for (String var : vars.keySet())
                fileContents =  fileContents.replaceAll("\\$\\{" + var + "\\}",vars.get(var));
            writeStringToFile( fileName,fileContents);
        } catch (IOException e) {
            log.error("Error updating Vue Variables ", e);
        }


    }


}
