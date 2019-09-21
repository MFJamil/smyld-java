package org.smyld.app.pe.input.yaml;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import org.smyld.app.pe.model.ApplicationReader;
import org.smyld.app.pe.model.ApplicationType;
import org.smyld.app.pe.model.GUIToolkit;
import org.smyld.app.pe.model.LayoutType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.smyld.app.pe.model.Constants.*;

public class PEAppYamlReader implements ApplicationReader {
    YamlMapping rootNode;
    GUIToolkit toolkit;
    public PEAppYamlReader(String fileName)throws FileNotFoundException,IOException {
        rootNode = Yaml.createYamlInput(new File(fileName)).readYamlMapping();
        init();
    }

    void init() {
        YamlMapping appNode = rootNode.yamlMapping(TAG_NAME_APPLICATION);
        readBuildSettings(appNode.yamlMapping(TAG_NAME_BUILD));
    }

    private void readBuildSettings(YamlMapping buildEl){
        if (buildEl==null) return ;
        System.out.println(buildEl.string(TAG_NAME_TOOLkit));
        this.toolkit =  GUIToolkit.valueOf(buildEl.string(TAG_NAME_TOOLkit));
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
        return LayoutType.YAML;
    }

}

