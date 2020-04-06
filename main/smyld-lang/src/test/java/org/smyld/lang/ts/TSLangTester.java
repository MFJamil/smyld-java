/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.lang.ts;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.smyld.lang.script.core.Method;
import org.smyld.lang.script.core.SMYLDLangException;
import org.smyld.lang.script.ts.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Slf4j
public class TSLangTester {


    @Test
    public void testTSClassBody()throws Exception{
        TSClassBody tsCB = new TSClassBody();
        tsCB.setName("AppHeader");
        tsCB.setParentClassName("Vue");
        log.info("\n" + tsCB.print("    ", true));
        tsCB = new TSClassBody();

    }

    @Test
    public void testVueWriter() throws Exception{
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
        tsCB.addMethod(tsMethod1);
        tsCB.getImportsHandler().addImport(new TSImport().setLibrary("../../KfwStore").setImportAsDefault().addClass("KfwStore"));
        tsCB.addMethod(((Method) new TSMethod().setName("mounted")).addCodeLine("KfwStore.addListener(this)"));
        tsCB.addMethod(((Method) new TSMethod().setName("doHandleNext")).addCodeLine("this.$emit('next')"));
        tsCB.addMethod(((Method) new TSMethod().setName("doHandleBack")).addCodeLine("this.$emit('back')"));
        // Outputting the file
        String outputPath = "/media/mfjamil/Ubuntu_second/work/FullStack/temp/PE_Test/BackupRatingTest/src/components/panels/AppFooter1.vue";
        TSFileWriter tsFileWriter = new TSFileWriter();
        tsFileWriter.addClass(tsCB);
        String vueFileContents = String.format("<template>\n</template>\n<script lang=\"ts\">\n%s\n</script>",tsFileWriter.print("\t"));
        log.info("\n" + vueFileContents);
        Files.write(Paths.get(outputPath), vueFileContents.getBytes());

    }


    @Test
    public void testInvalidTSImport1()throws Exception{
        TSImport tsImport = new TSImport();
        SMYLDLangException exception =  assertThrows(SMYLDLangException.class,()->tsImport.validate());
        assertTrue(exception.getMessage().equals("Library is missing"));
    }

    @Test
    public void testInvalidTSImport2()throws Exception{
        TSImport tsImport = new TSImport();
        tsImport.setLibrary("vue-property-decorator");
        SMYLDLangException exception =  assertThrows(SMYLDLangException.class,()->tsImport.validate());
        assertTrue(exception.getMessage().equals("No classes to import"));
    }

    @Test
    public void testTSImport()throws Exception{
        TSImport tsImport = new TSImport("vue-property-decorator");
        tsImport.addClass("Component").addClass("Vue").addClass("Prop");

        log.info(tsImport.print("    "));
        assertTrue(tsImport.print("    ").contains("} from 'vue-property-decorator'"));
    }

}
