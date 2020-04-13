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
import org.smyld.app.pe.model.gui.GUIWindow;
import org.smyld.app.pe.web.gui.builder.WebBuilder;
import org.smyld.app.pe.web.vue.vuetify.VtfXmlReader;
import org.smyld.app.pe.web.vue.vuetify.builder.VtfMenuBuilder;
import org.xml.sax.SAXException;

import static  org.junit.jupiter.api.Assertions.assertThrows;
import static  org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class WebAppTester {

    @Test
    public void testMissingID() throws Exception {
         SAXException expectedException = assertThrows(SAXException.class,() ->{
            String xmlFile = ClassLoader.getSystemClassLoader().getResource("Missing_ID.xml").getPath();
            VtfXmlReader reader = new VtfXmlReader();
            reader.readApplicationFile(xmlFile);

        });
        assertTrue(expectedException.getMessage().contains("Attribute 'id' must appear on element 'menu'"));

    }

    @Test
    public void testMenuBuilder(){
        VtfMenuBuilder menuBuilder = new VtfMenuBuilder(null);
        //log.info(menuBuilder.buildGeneralMenu());

    }

    @Test
    public void testWebBuilder(){
        WebBuilder webBuilder = new WebBuilder();
        GUIWindow guiWindow =  new GUIWindow();
        guiWindow.setLabel("My Test Window");
        guiWindow.setWidth("600px");
        guiWindow.setHeight("400px");
        webBuilder.addComponent(new GUIWindow());

    }


}
