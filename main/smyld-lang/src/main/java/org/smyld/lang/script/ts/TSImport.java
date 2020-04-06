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
import org.smyld.SMYLDObject;
import org.smyld.lang.script.core.SMYLDLangException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter @Getter @Slf4j @RequiredArgsConstructor @NoArgsConstructor
public class TSImport extends SMYLDObject {

    @NonNull  private String library;
    private Set<String> classes = new HashSet<>();
    private boolean importAsDefault = false;

    public TSImport setLibrary(String library){
        this.library = library;
        return this;
    }

    //ToDo: more sophisticated handling of imports
    public TSImport addClass(String className){
        classes.add(className);
        return this;
    }


    public boolean validate(){
        if (library==null) throw new SMYLDLangException("Library is missing");
        if (classes.size()==0) throw new SMYLDLangException("No classes to import");
        return true;
    }

    public boolean addImport(TSImport newImport){
        if (newImport.getLibrary()==null) {
            log.warn("Received a request to add import without a library!");
            return false;
        }
        if (!newImport.getLibrary().equals(this.library)){
            log.warn("Received a request to add import with a different library!");
            return false;
        }

        if (!newImport.importAsDefault) {
            this.classes.addAll(newImport.getClasses());
            return true;
        }
        return false;
    }

    public TSImport setImportAsDefault(){
        importAsDefault = true;
        return this;
    }

    public String print(String indent){
        validate();
        StringBuffer sb  = new StringBuffer();
        sb.append(indent).append("import ");
        //ToDo: handling default in a library needs to be checked later
        if ((importAsDefault)&&(classes.size()>1))
            throw new RuntimeException("Can not import more than one default object from a library, following classes were given " + String.join(" ,",classes));
        if (importAsDefault){
            sb.append(classes.toArray()[0]);
        }else {
            sb.append("{");
            sb.append(String.join(", ", classes.stream().sorted().collect(Collectors.toList())));
            sb.append("}");
        }
        sb.append(" from '").append(library).append("';");
        return sb.toString();


    }
}
