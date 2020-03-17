/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.ep;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.smyld.app.pe.EntityPlotterImpl;
import org.smyld.app.pe.TextProps;
import org.smyld.app.pe.util.Props;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.smyld.app.pe.TextProps.*;

@Slf4j
public class EPUtilsTester {

    @Test
    public void testProps(){
        Props<TextProps> testProps = new Props<>(TextProps.class);
        testProps.addProperty(lineMargin,200);
        Assert.assertEquals(200,testProps.getInt(lineMargin));
        testProps.addProperty(text,"Testing String");
        Assert.assertThat(testProps.getString(text),containsString("Testing"));
    }

    @Test
    public void testEntityPlotterClass(){
        Map<String,Field> fields = new HashMap<>();
        Stream.of(EntityPlotterImpl.class.getDeclaredFields()).forEach(curField ->fields.put(curField.getName(),curField));
        System.out.println(String.format("We do have %d fields",fields.size()));
        fields.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Field>comparingByKey())
                .map(curField -> (curField.getKey() + "::" + curField.getValue().getType().getName()) )
                .forEach(System.out::println);
    }
}
