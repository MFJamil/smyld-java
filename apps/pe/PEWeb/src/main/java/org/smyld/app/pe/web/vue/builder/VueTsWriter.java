/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.web.vue.builder;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.smyld.app.pe.web.vue.vuetify.builder.VtfBuildUtils;
import org.smyld.lang.script.ts.TSAnnotation;
import org.smyld.lang.script.ts.TSClassBody;
import org.smyld.lang.script.ts.TSFileWriter;
import org.smyld.lang.script.ts.TSImport;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Setter
@Getter
@Slf4j
@NoArgsConstructor
@RequiredArgsConstructor
public class VueTsWriter extends TSFileWriter {
    private HashMap<TSClassBody, Set<String>> classComps = new HashMap<>();
    @NonNull
    private String filePath;

    public void addComponentToClass(TSClassBody tsClassBody, String instanceId, String library){
        Set<String> comps = classComps.get(tsClassBody);
        if (comps==null){
            comps = new HashSet<>();
            classComps.put(tsClassBody,comps);
        }
        comps.add(instanceId);
        Optional<TSAnnotation> search =  tsClassBody.getAnnotations().stream().filter(curAnn -> curAnn.getName().equals("Component")).findFirst();
        if(search.isPresent()){
            // We can only add the component in case the current class is also defined with a component annotation
            TSAnnotation compAnn = search.get();
            compAnn.setFunction(true);
            compAnn.setBody("{components:{" + String.join(",",comps) + "}}");
            TSImport tsImport = new TSImport(VtfBuildUtils.resolveImport(filePath,library));
            tsImport.addClass(instanceId);
            tsImport.setImportAsDefault();
            tsClassBody.getImportsHandler().addImport(tsImport);
        }
    }

}
