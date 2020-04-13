/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.lang.script.ts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.smyld.lang.script.core.ClassBody;
import org.smyld.lang.script.core.Variable;

import java.util.*;

@Setter @Getter
@RequiredArgsConstructor
public class TSClassBody extends ClassBody {

    private TSClassType type;
    private String parentClassName;
    private TSClassBody parentClass;
    private Set<TSClassBody> interfaces = new HashSet<>();
    private Set<String> interfaceNames = new HashSet<>();
    private TSImportsHandler importsHandler = new TSImportsHandler();
    private Set<TSAnnotation> annotations = new HashSet<>();



    public TSClassBody addInterface(TSClassBody newInterface){
        interfaces.add(newInterface);
        return this;
    }

    public TSClassBody addInterface(String interfaceName){
        interfaceNames.add(interfaceName);
        return this;
    }

    public TSClassBody addInterface(String interfaceName,String library){
        interfaceNames.add(interfaceName);
        importsHandler.addImport(library,interfaceName);
        return this;
    }

    public TSClassBody addAnnotation(TSAnnotation newAnnotation){
        if (annotations.contains(newAnnotation)) return this;
        annotations.add(newAnnotation);
        handleAnnotationLibrary(newAnnotation);
        return this;
    }
    private void handleAnnotationLibrary(TSAnnotation newAnnotation){
        importsHandler.addImport(newAnnotation.getLibrary(),newAnnotation.getName());
    }

    private Set<String> getInterfaceNamesList(){
        if ((interfaceNames.size()==0)&&(interfaces.size()==0)) return null;
        Set<String> result = new HashSet<>();
        interfaces.forEach(curInt -> result.add(curInt.getName()));
        interfaceNames.forEach(curInt -> result.add(curInt));
        return result;
    }

    @Override
    public void addVariable(Variable newVariable) {
        super.addVariable(newVariable);
        if (newVariable instanceof TSVariable){
            TSVariable tsVariable = (TSVariable) newVariable;
            if (tsVariable.getAnnotations().size()>0){
                tsVariable.getAnnotations().forEach(curAnnot -> handleAnnotationLibrary(curAnnot));
            }
            if (tsVariable.getLibrary()!=null){
                importsHandler.addImport(tsVariable.getLibrary(),tsVariable.getType());
            }
        }
    }

    public String print(String indent,boolean asDefault){
        mainBody.setLength(0);
        if (getAnnotations().size()>0){
            getAnnotations().forEach(curAnot -> {
                mainBody.append(curAnot.print(indent)).append("\n");
            });
        }

        mainBody.append(indent).append("export ");
        if (asDefault) mainBody.append("default ");
        mainBody.append("class ");
        mainBody.append(getName()).append(" ");
        String parentClassText = getParentClass()!=null?getParentClass().getName():getParentClassName()!=null?getParentClassName():null;
        if (parentClassText != null)
            mainBody.append("extends ").append(parentClassText).append(" ");
        Set<String> interfaceList =  getInterfaceNamesList();
        if (interfaceList!=null)
            mainBody.append("implements ").append(String.join(", ",interfaceList));
        mainBody.append(" {\n");
        fillInVariables();
        fillInMethods(2);
        mainBody.append(indent).append("}");
        return mainBody.toString();

    }
}
