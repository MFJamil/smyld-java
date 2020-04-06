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
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.smyld.lang.script.LangAnnotation;
@Slf4j
@Setter
@Getter
@NoArgsConstructor
public class TSAnnotation extends LangAnnotation {
    public TSAnnotation(String name, String library){
        super(name,library,false);
    }
    public TSAnnotation(String name, String library,boolean isFunction){
        super(name,library,isFunction);
    }

    public String print(String indent){
        return indent + "@" + getName() + (isFunction()?"(" + (getBody()!=null?getBody():"") + ")":"");
    }

}
