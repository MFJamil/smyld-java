/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.lang.script.util;

import org.smyld.text.TextUtil;

public class CodeUtils {

    public static String getTabsIndent(int tabsLength){
        String margin = null;
        if (tabsLength > 0) {
            margin = TextUtil.createWord("\t", tabsLength);
        }
        return margin;

    }
}
