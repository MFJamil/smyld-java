package org.smyld.app.spe.factory;

import org.smyld.app.pe.annotations.PELayoutHandler;
import org.smyld.app.pe.annotations.PEGUIBuilder;
import org.smyld.app.pe.model.GUIToolkit;
import org.smyld.app.pe.model.LayoutType;
import org.smyld.reflections.AnnotatedType;
import org.smyld.reflections.SMYLDReflections;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

import java.util.Optional;
import java.util.Set;

@Component
public class SPEFactory implements AbstractFactory {
    Logger logger = LoggerFactory.getLogger(SPEFactory.class);



    private  Set<AnnotatedType<PELayoutHandler>> layoutHandlers;

    private  Set<AnnotatedType<PEGUIBuilder>>  guiBuilders;


    public SPEFactory(){
        this.init();
    }

    private void init(){
        //TODO We need to read the default builders package from the configuration,
        // to adopt multiple packages, allowing plugins in the future to be easily engaged
        String buildersPackage = "org.smyld";

        //TODO  Long Term solution would be to dynamically loading the Application file reader and inject it in the GUI Builder
        SMYLDReflections smyldReflections = new SMYLDReflections(buildersPackage);
        guiBuilders = smyldReflections.loadTypesAnnotatedWith(PEGUIBuilder.class);
        layoutHandlers = smyldReflections.loadTypesAnnotatedWith(PELayoutHandler.class);



    }


   private <T extends Annotation> void printDetectedObjects(Class<?> objClass,ArrayList<T> collection){
        if (collection.size()==0) return;
        StringBuilder sb = new StringBuilder();
        sb.append("Found : " + collection.size() + " instances of \n" + objClass.getSimpleName() + "\n");
        collection.forEach(curObj -> sb.append("\t" + curObj + "  => "  + curObj.annotationType().getName() + "\n"));
        logger.info(sb.toString());
    }





    @Override
    public AnnotatedType<PEGUIBuilder> getGUIBuilder(GUIToolkit toolkit) {
        Optional<AnnotatedType<PEGUIBuilder>> result = this.guiBuilders.stream().filter(curBuilder ->curBuilder.getAnnotation().guiToolkit() == toolkit).findFirst();
        return result.get();
    }


    @Override
    public AnnotatedType<PELayoutHandler> getLayoutHandler(GUIToolkit toolkit, LayoutType layoutType) {
        Optional<AnnotatedType<PELayoutHandler>> result = this.layoutHandlers.stream().filter(curBuilder ->
                (curBuilder.getAnnotation().guiToolkit() == toolkit)&&
                (curBuilder.getAnnotation().layoutType() == layoutType)).findFirst();
        return result.get();
    }



    public Set<AnnotatedType<PEGUIBuilder>> getGuiBuilders() {
        return guiBuilders;
    }

    public Set<AnnotatedType<PELayoutHandler>> getLayoutHandlers() {
        return layoutHandlers;
    }



}
