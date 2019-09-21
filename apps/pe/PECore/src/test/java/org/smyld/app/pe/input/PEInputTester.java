package org.smyld.app.pe.input;

import org.smyld.app.pe.input.json.PEAppJsonReader;
import org.smyld.app.pe.input.xml.PEAppXMLReader;
import org.smyld.app.pe.input.yaml.PEAppYamlReader;
import org.smyld.app.pe.model.GUIToolkit;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PEInputTester {

    @Test
    public void testPEXmlReader() throws Exception{
        PEAppXMLReader xmlReader = new PEAppXMLReader("src/test/resources/app_files/Sample_1.xml");
        Assumptions.assumingThat(xmlReader.isValid(),() -> assertTrue(GUIToolkit.swing == xmlReader.getGUIToolkit()));
    }

    @Test
    public void testPEYamlReader() throws Exception{
        PEAppYamlReader yamlReader = new PEAppYamlReader("src/test/resources/app_files/Sample_1.yaml");
        Assumptions.assumingThat(yamlReader.isValid(),() -> assertTrue(GUIToolkit.swing == yamlReader.getGUIToolkit()));
    }

    @Test
    public void testPEJsonReader() throws Exception{
        PEAppJsonReader jsonReader = new PEAppJsonReader("src/test/resources/app_files/Sample_1.json");
        Assumptions.assumingThat(jsonReader.isValid(),() -> assertTrue(GUIToolkit.swing == jsonReader.getGUIToolkit()));

    }


}
