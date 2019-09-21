package org.smyld.app.pe.input.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.smyld.app.pe.model.ApplicationReader;
import org.smyld.app.pe.model.ApplicationType;
import org.smyld.app.pe.model.GUIToolkit;
import org.smyld.app.pe.model.LayoutType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.smyld.app.pe.model.Constants.*;

public class PEAppJsonReader implements ApplicationReader {
    GUIToolkit toolkit;
    public PEAppJsonReader(String fileName) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get(fileName));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonData);
        readBuildSettings(rootNode);

    }
    private void readBuildSettings(JsonNode rootNode){
        String toolkitValue = rootNode.at(String.format("/%s/%s/%s",TAG_NAME_APPLICATION,TAG_NAME_BUILD,TAG_NAME_TOOLkit)).textValue();
        this.toolkit = GUIToolkit.valueOf(toolkitValue);


    }

    @Override
    public GUIToolkit getGUIToolkit() {
        return this.toolkit;
    }

    @Override
    public ApplicationType getType() {
        return null;
    }

    @Override
    public LayoutType getLayout() {
        return LayoutType.JSon;
    }

}
