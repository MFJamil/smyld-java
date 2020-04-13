/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.web.vue.vuetify;

import lombok.extern.slf4j.Slf4j;
import org.jdom2.Element;
import org.smyld.app.pe.annotations.PEApplicationSource;
import org.smyld.app.pe.annotations.PELayoutHandler;
import org.smyld.app.pe.annotations.PEReadApplication;
import org.smyld.app.pe.input.xml.PEAppV1XmlReader;
import org.smyld.app.pe.input.xml.PEXmlValidator;
import org.smyld.app.pe.model.*;
import org.smyld.app.pe.model.gui.GUIWindow;
import org.smyld.app.pe.model.gui.MenuItem;
import org.smyld.app.pe.web.vue.vuetify.model.VtfMenuItem;
import org.smyld.app.pe.web.vue.vuetify.model.VtfWindow;
import org.smyld.xml.XMLUtil;

import java.util.stream.Stream;

import static org.smyld.app.pe.model.Constants.TAG_ATT_STYLE;

@Slf4j
@PELayoutHandler(name = "PE Web XML Application Reader", applicationType = ApplicationType.Web, guiToolkit = GUIToolkit.vue, layoutType = LayoutType.XML)
public class VtfXmlReader extends PEAppV1XmlReader {


    @PEReadApplication
    public ApplicationReader readApplicationFile(@PEApplicationSource String fileName) throws Exception{
        rootNode = XMLUtil.getRootNode(fileName);
        String xsdFile  = ClassLoader.getSystemClassLoader().getResource("WebApplication.xsd").getPath();
        log.info("Validating the XML file against  WebApplication.xsd");
        PEXmlValidator.validateXMLSchema(xsdFile,fileName);
        doInit();

        return this;

    }
    @Override
    protected GUIWindow handleSingleWindow(String id, Element el){
        GUIWindow guiWindow = super.handleSingleWindow(id,el);
        if (guiWindow instanceof VtfWindow){
            VtfWindow vtfWindow = (VtfWindow)guiWindow;
            String styleText = el.getAttributeValue(TAG_ATT_STYLE);
            if (styleText!=null)
                Stream.of(styleText.split(";")).forEach(curProp ->{
                    String propName = curProp.substring(0,curProp.indexOf(":"));
                    String propValue = curProp.substring(curProp.indexOf(":")+1);
                    switch(Style.valueOf(propName)){
                        case max:
                            vtfWindow.setMaximize(Boolean.parseBoolean(propValue));
                            break;
                        case min:
                            vtfWindow.setMinimize(Boolean.parseBoolean(propValue));
                            break;
                        case close:
                            vtfWindow.setClose(Boolean.parseBoolean(propValue));
                            break;
                        case barColor:
                            vtfWindow.setBarColor(propValue);
                            break;
                    }
                });
        }
        return guiWindow;
    }

    @Override
    protected MenuItem createMenuItem(){
        VtfMenuItem vtfMenuItem  = new VtfMenuItem();
        return vtfMenuItem;
    }

    protected GUIWindow createWindow(){return new VtfWindow();}

}
