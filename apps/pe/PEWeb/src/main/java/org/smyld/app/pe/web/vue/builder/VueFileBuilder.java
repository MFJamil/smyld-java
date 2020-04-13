/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.web.vue.builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.smyld.lang.script.core.CodeFileWriter;
import org.smyld.lang.script.ts.TSAnnotation;
import org.smyld.lang.script.ts.TSClassBody;
import org.smyld.lang.script.ts.TSFileWriter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Setter @Getter @Slf4j
@NoArgsConstructor

public class VueFileBuilder {

    private String templateContents = "";
    private String scriptContents = "";
    private String scriptLang= "ts";
    private VueWebBuilder vueWebBuilder;
    private CodeFileWriter codeFileWriter;
    private HashMap<TSClassBody, Set<String>> classComps = new HashMap<>();
    public String print(String indent){
        StringBuffer sb  = new StringBuffer();
        sb.append("<template>\n");
        sb.append(vueWebBuilder!=null?vueWebBuilder.build(codeFileWriter):templateContents).append("\n");
        sb.append("</template>\n");
        sb.append("<script lang='").append(codeFileWriter!=null?codeFileWriter.getFileLang().getShortName():scriptLang).append("'>\n");
        sb.append(codeFileWriter!=null?codeFileWriter.print(indent):scriptContents).append("\n");
        sb.append("</script>\n");
        return sb.toString();
    }










}
