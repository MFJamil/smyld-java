package org.smyld.app;

import org.smyld.app.annotations.*;
import org.smyld.gui.GUIAction;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class AppAnnotationsHandler {
    PEApplication peAppAnt;
    Class<?>      peAppClass;
    Object        peAppObj;
    Set<Class<?>> actionControllers;
    HashMap<String,ActionMethod> actionMethods = new HashMap<>();
    Reflections refs;


    public  boolean processAnnotations(String packageName){
        refs = new Reflections(packageName);
        // Processing the PE Application class
        Set<Class<?>> mainAppInstances = refs.getTypesAnnotatedWith(PEApplication.class);
        if (mainAppInstances.size()>0){
            System.out.println("Found : " + mainAppInstances.size() + " instances of PE Application");
            if (mainAppInstances.size()>1)
                throw new RuntimeException("PE Application annotation should only used one time per application life cycle...!");
            peAppClass  = (Class<?>)mainAppInstances.toArray()[0];
            for (Annotation curAnn:peAppClass.getAnnotations()){
                if (curAnn instanceof PEApplication){
                    peAppAnt = (PEApplication)curAnn;
                    //System.out.println("Config file is : " + peAppAnt.configFile());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isApplicatinActive(){
        return peAppAnt!=null;
    }

    public PEApplication getPeAppAnt() {
        return peAppAnt;
    }

    protected void doInitiateAnnotations(String[] args){
        // Creating the main Application class object
        try {
            peAppObj = Class.forName(peAppClass.getName()).getConstructor().newInstance();
            Method appInitMethod = getFirstAnnotatedMethod(peAppObj.getClass(), PEInitialized.class);
            if (appInitMethod!=null){
                try {

                    System.out.println("Inovking the method annotated with PEInitialized and holding the name '" + appInitMethod.getName() + "'" );
                    System.out.println("Args : " + Arrays.toString(args));
                    if (appInitMethod.getParameterCount()==0){
                        appInitMethod.invoke(peAppObj);

                    }else if ((appInitMethod.getParameterCount()==1)&&(appInitMethod.getParameters()[0].getType().getSimpleName().equals("String[]"))){
                        appInitMethod.invoke(peAppObj,(Object)args);
                    }else{
                        throw new RuntimeException("Method annotating PE Initialized holds invalid parameters and can not be invoked, either no parameter or single paramter hoding String[] type");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            actionControllers = refs.getTypesAnnotatedWith(PEActionController.class);
            if (actionControllers.size()>0) {
                System.out.println("Found : " + actionControllers.size() + " instances of PEBusinessAction Controllers");
                for (Class<?> curClass :actionControllers){
                    System.out.println("Class :" + curClass.getName());
                    loadActionMethods(curClass);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    private void loadActionMethods(Class<?> actionController){
        try {
            Object hostObject = Class.forName(actionController.getName()).getConstructor().newInstance();
            //refs.getMethodsAnnotatedWith(PEBusinessAction.class);
            for (Method method:actionController.getMethods()){
                fetchActionAnnotations(method,PEBusinessAction.class,hostObject,actionController);
                fetchActionAnnotations(method,PEGUIAction.class,hostObject,actionController);
            }
            System.out.println("Finished Loading action Methods");

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    private <T extends Annotation> void fetchActionAnnotations(Method method,Class anotClass,Object hostObject,Class<?> actionController){
        if (method.isAnnotationPresent(anotClass)) {
            T peAction = (T) getMethodAnnotation(method, anotClass);
            String actionKey = getUniqueActionKey(peAction);
            if (actionMethods.containsKey(actionKey)) {
                System.out.println("Warning :: duplicate handlers for the same action " + actionKey);
            } else {
                System.out.println(String.format("Adding new action handler for key ='%s' in class %s via method %s", actionKey, actionController.getName(), method.getName()));
                actionMethods.put(actionKey, new ActionMethod(actionKey, method, hostObject, actionController));
            }
        }
    }

    private <T extends Annotation> String getUniqueActionKey(T action){
        if (action instanceof PEGUIAction)
            return getActionKey((PEGUIAction)action);
        return getActionKey((PEBusinessAction) action);
    }

    private String getActionKey(PEGUIAction actionAnot){
        return getActionKey(null,actionAnot.actionID());
    }

    private String getActionKey(PEBusinessAction actionAnot){
        return getActionKey(actionAnot.objectID(),actionAnot.actionID());
    }
    private String getActionKey(String objID ,String actionID){
        return (objID==null?GUIACTION_PREFIX:objID) + "_" + actionID;
    }
    protected void notifyApplicationClosed(){
        Method appClosedMethod = getFirstAnnotatedMethod(peAppObj.getClass(), PEClosed.class);
        if (appClosedMethod!=null){
            try {

                System.out.println("Invoking the method annotated with PEClosed and holding the name '" + appClosedMethod.getName() + "'" );
                if (appClosedMethod.getParameterCount()==0){
                    appClosedMethod.invoke(peAppObj);
                }else{
                    throw new RuntimeException("Method annotating PEClosed holds invalid parameters and can not be invoked, should not have any parameter");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private Annotation getMethodAnnotation(Method method,Class antClass){
        if (method.isAnnotationPresent(antClass)) {
            for (Annotation anot : method.getAnnotations()) {
                if (anot.annotationType().getName().equals(antClass.getName())) return anot;
            }
        }
        return null;
    }


    private Annotation getFirstClassAnnotation(Class mainClass, Class antClass){
        for (Annotation ant:mainClass.getAnnotations()){
            if (ant.getClass() == antClass){
                return ant;
            }
        }
        return null;
    }


    private Method getFirstAnnotatedMethod(Class mainClass,Class antClass){
        for (Method meth:mainClass.getMethods()){
            if (meth.isAnnotationPresent(antClass)){
                return meth;
            }
        }
        return null;
    }

    public static void main(String... args)throws Exception {
        //new AppMainClass();
        String packageName = "org.smyld";
        String[] tt = new String[1];

        System.out.println(tt.getClass().getName());
        System.out.println(tt.getClass().getSimpleName());
        System.out.println(tt.getClass().getTypeName());
        System.out.println(packageName.getClass().getName());
        System.out.println(args.getClass().getName());
        //processAnnotations(packageName);

    }

    public void handlePEAction(String objId,String actionId,Object objHandle){
        String key = getActionKey(objId,actionId);
        if (actionMethods.containsKey(key)){
            actionMethods.get(key).execute(objHandle);
        }
    }


    class ActionMethod{
        String key;
        Method method;
        Object hostObject;
        Class<?> hostClass;


        public ActionMethod(String key, Method method, Object hostObject, Class<?> hostClass) {
            this.key = key;
            this.method = method;
            this.hostObject = hostObject;
            this.hostClass = hostClass;
        }

        public void execute(Object objHandle){
            try {
                if ((method.getParameterCount()==1)){
                    method.invoke(hostObject, objHandle);
                }else if (method.getParameterCount()==0){
                    method.invoke(hostObject);
                }else{
                    System.out.println(String.format("Warning: method '%s' to handle GUI action with id %s contains invalid parameter!" +
                            (key.startsWith(GUIACTION_PREFIX)?"GUIAction parameter class is required":"")
                            ,method.getName(),key));
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
    }

    private static final String GUIACTION_PREFIX = "actGui";


}

