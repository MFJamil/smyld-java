/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.web;

import lombok.extern.slf4j.Slf4j;
import org.smyld.app.pe.annotations.PEApplicationSource;
import org.smyld.app.pe.annotations.PELayoutHandler;
import org.smyld.app.pe.annotations.PEReadApplication;
import org.smyld.app.pe.input.xml.PEAppV1XmlReader;
import org.smyld.app.pe.input.xml.PEXmlValidator;
import org.smyld.app.pe.model.ApplicationReader;
import org.smyld.app.pe.model.ApplicationType;
import org.smyld.app.pe.model.GUIToolkit;
import org.smyld.app.pe.model.LayoutType;
import org.smyld.xml.XMLUtil;
@Slf4j
@PELayoutHandler(name = "PE Web XML Application Reader", applicationType = ApplicationType.Web, guiToolkit = GUIToolkit.vue, layoutType = LayoutType.XML)
public class PEWebXmlReader extends PEAppV1XmlReader {


    @PEReadApplication
    public ApplicationReader readApplicationFile(@PEApplicationSource String fileName) throws Exception{
        rootNode = XMLUtil.getRootNode(fileName);
        String xsdFile  = ClassLoader.getSystemClassLoader().getResource("WebApplication.xsd").getPath();
        log.info("Validating the XML file against  WebApplication.xsd");
        PEXmlValidator.validateXMLSchema(xsdFile,fileName);
        doInit();

        return this;

    }

}
