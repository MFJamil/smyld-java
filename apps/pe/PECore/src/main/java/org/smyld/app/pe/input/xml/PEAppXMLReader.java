package org.smyld.app.pe.input.xml;

import org.smyld.app.pe.model.ApplicationReader;
import org.smyld.app.pe.model.ApplicationType;
import org.smyld.app.pe.model.GUIToolkit;
import org.smyld.app.pe.model.LayoutType;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.io.InputStream;

import static org.smyld.app.pe.model.Constants.*;

public class  PEAppXMLReader extends PEXmlFileReader implements ApplicationReader {
    GUIToolkit toolkit;

    public PEAppXMLReader()  {
        super();
    }


    public PEAppXMLReader(String appXMLDoc) throws IOException, JDOMException {
        super(appXMLDoc);
        init();
    }

    @Override
    public LayoutType getLayout() {
        return LayoutType.XML;
    }


    public PEAppXMLReader(InputStream appXMLStream) throws IOException, JDOMException {
        super(appXMLStream);
        init();
    }


    protected void init(){
        // Reading the toolkit type
        readBuildSettings(this.rootNode.getChild(TAG_NAME_BUILD));
        readLogSettings(this.rootNode.getChild(SET_XML_NODE_LOG));


    }

    private void readBuildSettings(Element buildEl){
        if (buildEl==null) return ;
        // Setting the default to swing
        this.toolkit = buildEl.getChildText(TAG_NAME_TOOLkit)!=null? GUIToolkit.valueOf(buildEl.getChildText(TAG_NAME_TOOLkit)):GUIToolkit.swing;

    }

    private void readLogSettings(Element logEl){
        if (logEl==null) return ;
    }


    @Override
    public GUIToolkit getGUIToolkit() {
        return this.toolkit;
    }

    @Override
    public ApplicationType getType() {
        return null;
    }
}
