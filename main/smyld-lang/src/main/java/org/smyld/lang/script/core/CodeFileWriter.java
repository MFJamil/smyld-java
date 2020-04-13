/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.lang.script.core;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Slf4j
@NoArgsConstructor
@RequiredArgsConstructor
public class CodeFileWriter {
    @NonNull
    private Langs fileLang;
    public String print(String indent){
        return "";
    }


}