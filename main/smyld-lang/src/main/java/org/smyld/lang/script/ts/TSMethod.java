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
import org.smyld.lang.script.core.LangContants;
import org.smyld.lang.script.core.Method;
import org.smyld.lang.script.java.JavaClassBody;
import org.smyld.lang.script.util.CodeUtils;
import org.smyld.text.TextUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Slf4j
@Setter
@Getter
@NoArgsConstructor
public class TSMethod extends Method {
    private Set<TSAnnotation> annotations = new HashSet<>();

    public TSMethod addAnnotation(TSAnnotation newAnnotation) {
        if (annotations.contains(newAnnotation)) return this;
        annotations.add(newAnnotation);
        return this;
    }
    @Override
    public String fillParameter(String name, String type) {
        return name + ":" + type;
    }

    @Override
    public boolean parseMethod(String codeLine) {
        return false;
    }

    @Override
    public String print(int tabsLength) {
        body.setLength(0);
        String margin = CodeUtils.getTabsIndent(tabsLength);
        // Printing the method header
        body.append(margin);
        if (scope!=null)
            body.append(scope + " ");
        fillInModifiers();
        body.append(name);
        body.append("(");
        fillInParameters();
        body.append(")");
        if (!isConstructor()) {
            if (returnType == null) {
                returnType = LangContants.RETURN_TYPE_VOID;
            }
            body.append(":" + returnType);
        }

        // In case the method is abstract then it must be ended with semicolon
        if (modifiers.containsKey(JavaClassBody.MODIFIER_ABSTRACT)) {
            body.append(";");
        } else {
            adjustCodeLines();
            tabsLength++;
            body.append("{\n");
            if (codeLines.size() > 0) {

                for (String currentLineCode : codeLines) {
                    if (isBlockEnd(currentLineCode.trim())) {
                        tabsLength--;
                    }
                    margin = TextUtil.createWord("\t", tabsLength);
                    if (isBlockStart(currentLineCode.trim())) {
                        tabsLength++;
                    }
                    body.append(margin + currentLineCode);
                    if (isLineEnd(currentLineCode.trim())) {
                        body.append(";");
                    }
                    body.append("\n");
                }
            }
            tabsLength--;
            margin = TextUtil.createWord("\t", tabsLength);
            body.append(margin + "}\n");
        }
        return body.toString();
    }


}
