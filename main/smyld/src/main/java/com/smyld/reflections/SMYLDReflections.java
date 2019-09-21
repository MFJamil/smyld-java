package com.smyld.reflections;


import org.reflections.Reflections;
import org.reflections.scanners.Scanner;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class SMYLDReflections extends Reflections {

    public SMYLDReflections(String prefix, @Nullable Scanner... scanners) {
        super(prefix, scanners);

    }

    public <T extends Annotation> Set<AnnotatedType<T>> loadTypesAnnotatedWith(Class<? extends Annotation> annotation) {
        Set<Class<?>> typeClasses = super.getTypesAnnotatedWith(annotation);
        Set<AnnotatedType<T>> result = new HashSet<>();
        if (typeClasses.size()>0)
            for (Class<?> curClass:typeClasses)
                for (Annotation curAnn:curClass.getAnnotations())
                    if (annotation.isAssignableFrom(curAnn.getClass()))
                        result.add(new AnnotatedType (curClass,(T)curAnn));

        return result;
    }

}
