package org.smyld.reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public  class AnnotatedType <T extends Annotation>{
    Class<?> annotatedClass;
    T annotation;
    Object hostObject;

    public AnnotatedType(Class<?> annotatedClass, T annotation) {
        this.annotatedClass = annotatedClass;
        this.annotation = annotation;
    }

    public void instantiate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        this.hostObject = annotatedClass.getConstructor().newInstance();

    }

    public Class<?> getAnnotatedClass() {
        return annotatedClass;
    }

    public T getAnnotation() {
        return annotation;
    }

    public Object getHostObject() {
        return hostObject;
    }
}
