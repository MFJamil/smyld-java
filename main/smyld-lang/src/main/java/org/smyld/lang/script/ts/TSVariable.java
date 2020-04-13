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
import org.smyld.lang.script.core.Variable;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Setter
@Getter
@NoArgsConstructor
public class TSVariable extends Variable {

    private Set<TSAnnotation> annotations = new HashSet<>();

    public TSVariable addAnnotation(TSAnnotation newAnnotation) {
        if (annotations.contains(newAnnotation)) return this;
        annotations.add(newAnnotation);
        return this;
    }

    @Override
    public String print(String indent) {
        body.setLength(0);
        //ToDo: handling the indent
        if (getAnnotations().size()>0){
            getAnnotations().forEach(curAnot -> {
                body.append(curAnot.print(indent)).append("\n");
            });
        }
        body.append(indent);
        if (scope!=null)
            body.append(scope).append(" ");
        fillInModifiers();
        body.append(name);
        if (getDefaultValue()==null) body.append("!");
        if (type!=null)
            body.append(" : ").append(type);
        if (getDefaultValue()!=null){
            body.append(" = " + getDefaultValue());
        }
        body.append(";");
        return body.toString();
    }
}
