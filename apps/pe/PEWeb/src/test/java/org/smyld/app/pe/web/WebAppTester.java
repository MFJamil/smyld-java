/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.xml.sax.SAXException;

import static  org.junit.jupiter.api.Assertions.assertThrows;
import static  org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class WebAppTester {

    @Test
    public void testMissingID() throws Exception {
         SAXException expectedException = assertThrows(SAXException.class,() ->{
            String xmlFile = ClassLoader.getSystemClassLoader().getResource("Missing_ID.xml").getPath();
            PEWebXmlReader reader = new PEWebXmlReader();
            reader.readApplicationFile(xmlFile);

        });
        assertTrue(expectedException.getMessage().contains("Attribute 'id' must appear on element 'menu'"));

    }
}
