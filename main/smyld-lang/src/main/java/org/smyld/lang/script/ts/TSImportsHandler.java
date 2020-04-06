/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.lang.script.ts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Getter
@NoArgsConstructor
public class TSImportsHandler {

    List<TSImport> imports = new ArrayList<>();

    public TSImportsHandler addImport(String library, String className){
        TSImport newImport = new TSImport(library);
        newImport.addClass(className);
        addImport(newImport);
        return this;

    }

    private boolean containsImport(String library){
        return getImport(library)!=null;
    }

    private boolean containsImport(TSImport newImport){
        return getImport(newImport.getLibrary())!=null;
    }
    private TSImport getImport(String library){
        for (TSImport curImp:imports)
            if (curImp.getLibrary().equals(library)) return curImp;
        return null;
    }

    public TSImportsHandler addImport(TSImport newImport){
        TSImport existingImp = getImport(newImport.getLibrary());
        if (existingImp!=null)
            if (existingImp.addImport(newImport))
                return this;
        imports.add(newImport);
        return this;
    }



}
