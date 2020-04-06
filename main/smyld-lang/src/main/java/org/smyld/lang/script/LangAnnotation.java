/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.lang.script;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j @Setter @Getter @RequiredArgsConstructor @NoArgsConstructor
public class LangAnnotation {
    @NonNull
    private String name;
    @NonNull
    private String library;
    @NonNull
    private boolean function;
    private String body;

}
