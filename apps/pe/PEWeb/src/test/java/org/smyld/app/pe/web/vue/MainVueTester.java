/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.web.vue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.smyld.app.pe.model.gui.GUIWindow;
import org.smyld.app.pe.model.gui.MenuItem;
import org.smyld.app.pe.web.vue.builder.VueTsWriter;
import org.smyld.app.pe.web.vue.vuetify.VtfXmlReader;
import org.smyld.app.pe.web.vue.builder.VueFileBuilder;
import org.smyld.app.pe.web.vue.builder.VueWebBuilder;
import org.smyld.app.pe.web.vue.vuetify.builder.VtfUIBuilderFactory;
import org.smyld.lang.script.core.Method;
import org.smyld.lang.script.ts.*;

import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class MainVueTester {

    
    private TSClassBody createTestClassBody1(){
        // Creating the test class
        TSClassBody tsCB = new TSClassBody();
        tsCB.setName("AppFooter");
        tsCB.setParentClassName("Vue");
        tsCB.addInterface("InfoListener","../../KfwStore");
        // Adding parent class import ( we need to automatically add it during the setting of parent class)
        TSImport tsImport = new TSImport("vue-property-decorator");
        tsImport.addClass("Vue");
        tsCB.getImportsHandler().addImport(tsImport);
        tsCB.addAnnotation(new TSAnnotation("Component","vue-property-decorator"));
        // Variable 1
        TSVariable tsVariable1 = new TSVariable();
        tsVariable1.setName("theme").setScope("public").setType("string");
        tsVariable1.addAnnotation(new TSAnnotation("Prop","vue-property-decorator",true));
        tsCB.addVariable(tsVariable1);
        // Variable 2
        TSVariable tsVariable2 = new TSVariable();
        tsVariable2.setName("themeDark").setScope("public").setType("boolean");
        TSAnnotation propAnnot =  new TSAnnotation("Prop","vue-property-decorator",true);
        propAnnot.setBody("{default:true}");
        tsVariable2.addAnnotation(propAnnot);
        tsCB.addVariable(tsVariable2);
        // Variable 3
        TSVariable tsVariable3 = new TSVariable();
        tsVariable3.setName("user").setScope("public").setType("KfwUser").setLibrary("../../model/kfwUser");
        TSAnnotation propAnnot3 =  new TSAnnotation("Prop","vue-property-decorator",true);
        propAnnot3.setBody("{default:new KfwUser(), required:true}");
        tsVariable3.addAnnotation(propAnnot3);
        tsCB.addVariable(tsVariable3);
        tsCB.addVariable(new TSVariable().setName("btnBackActive").setScope("public").setType("boolean").setDefaultValue("false"));
        tsCB.addVariable(new TSVariable().setName("btnNextActive").setScope("public").setType("boolean").setDefaultValue("false"));

        // Interface Method
        TSMethod tsMethod1 = new TSMethod();
        tsMethod1.setName("stateChanged");
        tsMethod1.setScope("public");
        tsMethod1.addParameter("property","string");
        tsMethod1.addParameter("newValue","any");
        tsMethod1.addCodeLine("if (property==KfwStore.PROPERTY_ACTIVATE_NEXT){")
                .addCodeLine("this.btnNextActive = newValue")
                .addCodeLine("}")
                .addCodeLine("if (property==KfwStore.PROPERTY_ACTIVATE_BACK){")
                .addCodeLine( "this.btnBackActive = newValue")
                .addCodeLine("}");

        tsCB.getImportsHandler().addImport(new TSImport().setLibrary("../../KfwStore").setImportAsDefault().addClass("KfwStore"));
        tsCB.addMethod(((Method) new TSMethod().setName("mounted")).addCodeLine("KfwStore.addListener(this)"));
        tsCB.addMethod(((Method) new TSMethod().setName("doHandleNext")).addCodeLine("this.$emit('next')"));
        tsCB.addMethod(((Method) new TSMethod().setName("doHandleBack")).addCodeLine("this.$emit('back')"));
        tsCB.addMethod(tsMethod1);
        return tsCB;
    }

    private TSClassBody createVueClassBody(String className){
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



    private TSFileWriter createTestFileWriter(TSClassBody tsCB){
        TSFileWriter tsFileWriter = new TSFileWriter();
        tsFileWriter.addClass(tsCB,true);
        return tsFileWriter;

    }

    @Test
    public void testVueWriter() throws Exception{
        String outputPath = "/media/mfjamil/Ubuntu_second/work/FullStack/temp/PE_Test/BackupRatingTest/src/components/panels/AppFooter1.vue";
        TSFileWriter tsFileWriter = createTestFileWriter(createTestClassBody1());
        VueFileBuilder vueFileBuilder = new VueFileBuilder();
        vueFileBuilder.setCodeFileWriter(tsFileWriter);
        String vueFileContents = vueFileBuilder.print("\t");
        log.info("\n" + vueFileBuilder.print("\t"));
        Files.write(Paths.get(outputPath), vueFileContents.getBytes());

    }

    @Test
    public void testMenuBuilder() throws Exception{
        String outputPath = "/media/mfjamil/Ubuntu_second/work/FullStack/temp/PE_Test/BackupRatingTest/src/components/test/Menu1.vue";
        TSFileWriter tsFileWriter = createTestFileWriter(createVueClassBody("MenuHandler"));

        VueFileBuilder vueFileBuilder = new VueFileBuilder();
        vueFileBuilder.setCodeFileWriter(tsFileWriter);

        VueWebBuilder vueWebBuilder = new VueWebBuilder();
        vueWebBuilder.setWuiBuilderFactory(new VtfUIBuilderFactory());

        VtfXmlReader peWebXmlReader = new VtfXmlReader();
        peWebXmlReader.readApplicationFile("src/test/resources/app1/WebApplication.xml");
        MenuItem menuItem = peWebXmlReader.getMenus().get("mainMenu");
        vueWebBuilder.addComponent(menuItem);
        vueFileBuilder.setVueWebBuilder(vueWebBuilder);
        String vueFileContents = vueFileBuilder.print("\t");
        log.info("\n" + vueFileBuilder.print("\t"));
        Files.write(Paths.get(outputPath), vueFileContents.getBytes());

    }

    @Test
    public void testWindowBuilder() throws Exception{
        String outputPath = "/media/mfjamil/Ubuntu_second/work/FullStack/temp/PE_Test/BackupRatingTest/src/components/test/window1.vue";
        VueFileBuilder vueFileBuilder = new VueFileBuilder();
        vueFileBuilder.setCodeFileWriter(new VueTsWriter("components.test.Window1.vue"));
        VueWebBuilder vueWebBuilder = new VueWebBuilder();
        vueWebBuilder.setWuiBuilderFactory(new VtfUIBuilderFactory());
        VtfXmlReader peWebXmlReader = new VtfXmlReader();
        peWebXmlReader.readApplicationFile("src/test/resources/app1/WebApplication.xml");
        GUIWindow windowItem = peWebXmlReader.getWindows().get("SettingsWindow");
        vueWebBuilder.addComponent(windowItem);
        vueFileBuilder.setVueWebBuilder(vueWebBuilder);
        String vueFileContents = vueFileBuilder.print("\t");
        log.info("\n" + vueFileBuilder.print("\t"));
        Files.write(Paths.get(outputPath), vueFileContents.getBytes());

    }


}
