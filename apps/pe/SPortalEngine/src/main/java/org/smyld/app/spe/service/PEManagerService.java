package org.smyld.app.spe.service;


import org.smyld.app.pe.annotations.*;
import org.smyld.app.pe.input.json.PEAppJsonReader;
import org.smyld.app.pe.input.xml.PEAppXMLReader;
import org.smyld.app.pe.input.yaml.PEAppYamlReader;
import org.smyld.app.pe.model.ApplicationReader;
import org.smyld.app.spe.factory.SPEFactory;
import org.smyld.app.spe.util.CommandLineHandler;
import org.smyld.reflections.AnnotatedType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;


/**
 * Java class PEManagerService
 *
 * Main portal engine service which represents the main entry point
 *
 * @author Mohammed Jamil
 * @version 1.0, 02 Jun 2019
 */

@Service
public class PEManagerService {
    //Creating default logger
    Logger logger = LoggerFactory.getLogger(PEManagerService.class);

    @Autowired
    private CommandLineHandler commandLineHandler;
    @Autowired
    private SPEFactory factory;

    private AnnotatedType<PEGUIBuilder> peGUIBuilder;


    private AnnotatedType<PELayoutHandler> peLayoutHandler;

    public void processCommandLine(ApplicationArguments args){
        commandLineHandler.processCommandLine(args);
    }

    public void processSingleApplication(String appFile,ApplicationArguments args){
        logger.info(LOG_APP_RECIEVE_MESSAGE + appFile);
        try {
            ApplicationReader reader = getProperApplicationReader(appFile);
            logger.info("Application Toolkit : " +  reader.getGUIToolkit());
            peGUIBuilder =  factory.getGUIBuilder(reader.getGUIToolkit());
            logger.info("Application reader found for the given toolkit : " + peGUIBuilder.getAnnotation().guiToolkit() + " ==> " + peGUIBuilder.getAnnotatedClass().getName());
            peLayoutHandler =  factory.getLayoutHandler(reader.getGUIToolkit(),reader.getLayout());
            logger.info("Layout Handler found for the given toolkit : " + peLayoutHandler);
            ApplicationReader applicationReader = handleApplicationFile(appFile);
            if (applicationReader!=null){
                logger.info("Application reader was successfully created, invoking the Application generator now");
                handleApplicationGeneration(applicationReader);

            }
        }catch(Exception ex){
            ex.printStackTrace();

        }
    }

    private ApplicationReader handleApplicationFile(String appFile) throws Exception{
        // Handling the layout manager
        System.out.println(" ================ " + peLayoutHandler.getAnnotatedClass());
        peLayoutHandler.instantiate();
        Object hostObj = peLayoutHandler.getHostObject();
        for (Method curMethod:hostObj.getClass().getMethods())
            if (curMethod.isAnnotationPresent(PEReadApplication.class)){
                if (!curMethod.getReturnType().isAssignableFrom(ApplicationReader.class))  throw new RuntimeException("Returned object should be an Application Reader instance ");
                for (Parameter curParam : curMethod.getParameters())
                    if (curParam.isAnnotationPresent(PEApplicationSource.class)){
                        Object result = curMethod.invoke(hostObj,appFile);
                        return (ApplicationReader) result;
                    }

            }
        return null;
    }

    private void handleApplicationGeneration(ApplicationReader applicationReader) throws Exception{
        // Handling the GUI Builder

        peGUIBuilder.instantiate();
        Object hostObj = peGUIBuilder.getHostObject();
        for (Method curMethod:hostObj.getClass().getMethods())
            if (curMethod.isAnnotationPresent(PEGenerateApplication.class)){
                for (Parameter curParam : curMethod.getParameters())
                    if (curParam.isAnnotationPresent(PEApplicationReader.class)){
                        Object result = curMethod.invoke(hostObj,applicationReader);

                    }

            }
    }

    private ApplicationReader getProperApplicationReader(String appFile) throws Exception{
        if (appFile.endsWith("xml")) {
            return new PEAppXMLReader(appFile);
        }else if (appFile.endsWith("yaml")) {
            return new PEAppYamlReader(appFile);
        }else if (appFile.endsWith("json")) {
            return new PEAppJsonReader(appFile);
        }
        throw new RuntimeException("Format not supported ... ");


    }

    public AnnotatedType<PEGUIBuilder> getPeGUIBuilder() {
        return peGUIBuilder;
    }

    public  AnnotatedType<PELayoutHandler> getPeLayoutHandler() {
        return peLayoutHandler;
    }



    public static final String LOG_APP_RECIEVE_MESSAGE = "Portal Engine should now process single application file ";

}
