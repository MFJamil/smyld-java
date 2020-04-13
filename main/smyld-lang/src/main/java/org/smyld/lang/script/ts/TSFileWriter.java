/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.lang.script.ts;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.smyld.lang.script.core.CodeFileWriter;
import org.smyld.lang.script.core.Langs;

import java.util.*;

@Slf4j
@Setter
@Getter
public class TSFileWriter extends CodeFileWriter {
    private String fileName;
    private String filePath;
    private String defaultClass;
    List<String> codeLines = new ArrayList<>();
    List<Object> sequence = new ArrayList<>();

    public TSFileWriter(){super(Langs.TypeScript);}

    Map<String,TSClassBody> classes = new HashMap<>();

    public TSClassBody getDefaultClassBody(){
        if (defaultClass==null) return null;
        return classes.get(defaultClass);
    }
    public TSFileWriter addClass(TSClassBody newClass) {
        if (classes.containsKey(newClass.getName())) {
            log.warn("Class is already available, we can not add it");
            return this;
        }
        classes.put(newClass.getName(), newClass);
        sequence.add(newClass);
        return this;
    }

    public void addCodeLine(String codeLine){
        sequence.add(codeLine);
    }



    /*
    * Overloading the function to handle the default class
    * */
    public TSFileWriter addClass(TSClassBody newClass,boolean asDefault){
        if (asDefault) {
            if ((defaultClass != null) && (!defaultClass.equals(newClass.getName()))) {
                log.warn(String.format("Default class is already set with the name '%s', will only add the class ", defaultClass));
            } else {
                defaultClass = newClass.getName();
            }
        }
        return addClass(newClass);
    }


    @Override
    public String print(String indent){
        StringBuffer sb = new StringBuffer();
        // Printing Imports
        TSImportsHandler importsHandler = new TSImportsHandler();
        getClasses().forEach((className,classBody) ->{
            classBody.getImportsHandler().getImports().forEach(imp ->{
                importsHandler.addImport(imp);
            } );

        });
        sb.append("\n");
        importsHandler.getImports().forEach(imp->{sb.append(imp.print(indent)).append("\n");});
        sb.append("\n\n\n");
        sequence.forEach(curObj -> {
            if (curObj instanceof TSClassBody){
                TSClassBody tsClass = (TSClassBody)curObj;
               sb.append(tsClass.print(indent,tsClass.getName().equals(defaultClass))).append("\n");
            }else if (curObj instanceof String){
               sb.append(indent).append(curObj).append("\n");
            }
        });

        // Printing classes
        //getClasses().forEach((className,classBody)->sb.append(classBody.print(indent,className.equals(defaultClass))));
        return sb.toString();
    }






}
