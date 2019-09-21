package org.smyld.gui.portal.engine;

import org.smyld.SMYLDObject;
import org.smyld.app.pe.annotations.PEApplicationReader;
import org.smyld.app.pe.annotations.PEGUIBuilder;
import org.smyld.app.pe.annotations.PEGenerateApplication;
import org.smyld.app.pe.model.ApplicationReader;
import org.smyld.app.pe.model.ApplicationType;
import org.smyld.app.pe.model.Constants;
import org.smyld.app.pe.model.GUIToolkit;
import org.smyld.app.pe.model.gui.GUISplash;
import org.smyld.app.pe.model.gui.GUIToolbar;
import org.smyld.app.pe.model.gui.GUIWindow;
import org.smyld.app.pe.security.AppSecurity;
import org.smyld.deploy.DeploymentDescriptor;
import org.smyld.deploy.web.jnlp.JNLPDeploymentDescriptor;
import org.smyld.deploy.web.jnlp.SMYLDJNLPWriter;
import org.smyld.gui.GUIConstants;
import org.smyld.gui.portal.engine.gui.builders.SMYLDGUIBuilder;
import org.smyld.gui.portal.engine.gui.builders.swing.SwingGUIHandler;
import org.smyld.app.pe.model.gui.GUIComponent;
import org.smyld.app.pe.projectbuilder.MavenProjectBuilder;
import org.smyld.app.pe.projectbuilder.ProjectBuildSource;
import org.smyld.app.pe.projectbuilder.ProjectBuilder;
import org.smyld.gui.portal.engine.sources.PESwingApplicationReader;
import org.smyld.io.FileSystem;
import org.smyld.lang.script.java.*;
import org.smyld.lang.script.util.Variable;
import org.smyld.resources.FileInfo;
import org.smyld.resources.LookAndFeelResource;
import org.smyld.security.SMYLDKey;
import org.smyld.text.TextUtil;
import org.smyld.util.ProcessTimer;
import org.smyld.util.SMYLDJavaTools;
import org.smyld.util.jar.JarEntryAlreadyExist;
import org.smyld.util.jar.SMYLDJARWriter;
import org.smyld.util.multilang.LangSource;
import org.jdom2.Element;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.jar.Attributes;

import static org.smyld.app.AppConstants.*;

/**
 * This class will create the whole application the launcher application creator
 * will depend on the properities of application XML document
 * 
 * @author
 * @version
 * @see
 * @since
 */

@PEGUIBuilder(name = "PE Swing Builder",applicationType = ApplicationType.Desktop,guiToolkit = GUIToolkit.swing)
public class ApplicationGenerator extends SMYLDObject implements Constants,
		GUIConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JavaClassBody                   mainClass;
	GUIPanelGenerator               panelGenerator;
	GUIWindowGenerator              windowGenerator;
	GUIWindow mainWindow;
	AppSettingsWriter               appSetWriter;
	SMYLDGUIBuilder                  componentsBuilder;
	SwingGUIHandler                 swingGUIBuilder;
	Vector<String>                  usedLibs;
	AppCompiler                     appCompiler;
	Vector<String>                  warnings;
	public static Application       newApplication;
	public static PESwingApplicationReader appReader;

	/**
	 * 
	 * @see
	 * @since
	 */
	public ApplicationGenerator() {
		init();
	}

	private void init() {
		// swingGUIBuilder = new SMYLDGUIBuilder();
		panelGenerator    = new GUIPanelGenerator();
		windowGenerator   = new GUIWindowGenerator();
		swingGUIBuilder   = new SwingGUIHandler();
		usedLibs          = new Vector<String>();
		appCompiler       = new AppCompiler();
		warnings          = new Vector<String>();
		// newApplication = new Application();
		swingGUIBuilder.init();

	}


	@PEGenerateApplication
	public void generateApplication(@PEApplicationReader ApplicationReader newAppReader) {
		Application newApp = new Application();
		//TODO the type check below should be replaced with a more generic way
		newApp.setAppReader((PESwingApplicationReader) newAppReader);
		this.generateApplication(newApp);
	}


	void generateApplication(Application newApp) {
		try {

			swingGUIBuilder.setActiveApplication(newApp);
			newApplication = newApp;
			appReader = newApp.getAppReader();
			// Creating the application settings file
			FileInfo setInfo = appReader.getAppSettingsFile();
			if ((setInfo != null)&&(setInfo.getFileName()!=null)) {
				String filePath = setInfo.getFilePath();
				if (TextUtil.isEmpty(filePath)) 
					filePath = appReader.getTargetJarPath();
				if (TextUtil.isEmpty(filePath)) 
					filePath = System.getProperty("user.dir" + File.separator + "deploy");
				
				File setFile = FileSystem.createFileWithPath(filePath, setInfo.getFileName()
						+ ".xml");
				if (setFile != null) {
					appSetWriter = new AppSettingsWriter(setFile);
				}
			}
			if((getPrjType()!=null)&&(getPrjType() == ProjectBuildSource.Maven)){
				MavenProjectBuilder mavenBuilder = (MavenProjectBuilder)PortalManager.getInstance().projectBuilder;
				String settingsFile = mavenBuilder.getProjectPath() + "/" + APP_DEF_SETTINGS_FILE;
				appSetWriter = new AppSettingsWriter(settingsFile);
				//System.out.println("TARGET PROJECT PATH existence check : " +  new File(mavenBuilder.getProjectPath()).exists());


			}

			System.out.println("Generating " + newApp.getName()
					+ " application ...");
			ProcessTimer timer = new ProcessTimer();
			timer.startTimeTest("Generation");
			generateClasses();
			System.out.println(timer.endTimeTest("Generation"));
			
			if (newApp.appReader.compileClasses()){
				System.out.println("Compiling " + newApp.getName()
						+ " application ...");

				timer.startTimeTest("Compilation");
				appCompiler.setActiveApplication(newApplication);
				appCompiler.compileApplication();
				System.out.println(timer.endTimeTest("Compilation"));
			}
			
			if (newApp.appReader.createJarFile()){
				System.out.println("Creating " + newApp.getName()
						+ " application in one JAR file ...");

				timer.startTimeTest("Jar Creation");
				createJarFile();
				handelAppSecurity();
				createDeployments();
				System.out.println(timer.endTimeTest("Jar Creation"));
			}
			if (appSetWriter!=null){
				populateProjectSettings();
				appSetWriter.createFile();
			}
			System.out.println("Application " + newApp.getName()
					+ " Generation is finished ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private ProjectBuildSource getPrjType(){
		return (PortalManager.getInstance()!=null)?PortalManager.getInstance().projectBuilder.getSource():null;
	}
	private ProjectBuilder getPrjBuilder(){
		return PortalManager.getInstance().projectBuilder;
	}


	private void handelAppSecurity() {
		AppSecurity appSecurity = appReader.getAppSecurity();
		// Generating the keys required for the application
		if ((appSecurity != null) && (appSecurity.getKeys() != null)) {
			SMYLDJavaTools tools = new SMYLDJavaTools();
			for (SMYLDKey curKey : appSecurity.getKeys().values()) {
				try {
					tools.createKey(curKey);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}


	void generateClasses() {
		try {
			// Creating the main class of the application
			mainClass = new JavaClassBody(APP_MAIN_CLASS_NAME, appReader.getMainClassPackage(), true, false);
			JavaMethod mainMethod = mainClass.addMainMethod();
			mainMethod
					.addCodeLine("System.out.println(\"This is the starting class for the application ....\")");

			// mainClass.exportFileToPath(appReader.getSourcePath());
			// Adding all main classes to one package
			if (appReader.getComponentType().equals(TAG_COMP_TYPE_SWING)) {
				componentsBuilder = swingGUIBuilder;
			}
			// Code for loading the panels
			if (containsActions()) {
				buildActions();
			}
			if (containsMenus()) {
				buildMenus();
			}
			if (containsToolbars()) {
				buildToolbars();
			}
			buildPanels();
			buildWindows();

			handleLaunchingApp(mainMethod);
			newApplication.addMainClass(APP_MAIN_CLASS_NAME, mainClass);
			createAppManagerInterface();
			exportClasses();
			handleLibraries();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getTranslate(String module, String id,
			String defaultValue) {
		String codeLine = APP_MAIN_CLASS_NAME + "." + APP_METH_TRANSLATE
				+ "(\"" + module + "\"," + id + ",";
		String rest = defaultValue + ")";
		if (defaultValue == null) {
			rest = "null)";
		}
		return codeLine + rest;
	}

	public static String getTranslateTP(String module, String id,
			String defaultValue) {
		return getTranslate(module + "_tp", id, defaultValue);
	}

	public static String getImage(String iconName) {
		String codeLine = APP_MAIN_CLASS_NAME + "." + APP_METH_GET_IMAGE
				+ "(\"" + iconName + "\")";
		return codeLine;
	}

	private void handleLaunchingApp(JavaMethod mainMethod) {
		if (mainWindow != null) {
			// Adding imports
			mainClass.addImport(CLASS_NAME_FP_GUI_ACTION);
			mainClass.addImport(CLASS_NAME_FP_ACTION_HANDLER);

			mainClass.addImport(mainWindow.getPackage() + "."
					+ mainWindow.getID());
			mainClass.setParentClassName("AppMainClass");
			mainClass.addImport("org.smyld.app.AppMainClass");
			mainClass.addImport("org.smyld.app.AppBusinessMode");
			JavaVariable appManager = new JavaVariable(
					APP_INSTANCE_APP_MANAGER, Variable.SCOPE_PUBLIC,
					APP_MANAGER_INTERFACE_NAME);
			mainClass.addInterface(GUIConstants.CLASS_NAME_ACTION_HANDLER);
			JavaVariable windowsFactory = new JavaVariable(
					APP_INSTANCE_WIN_FACTORY, Variable.SCOPE_PUBLIC,
					WINDS_FCTR_CLASS_NAME);
			windowsFactory.addModifier(JavaConstants.MODIFIER_STATIC);
			mainClass.addVariable(windowsFactory);
			JavaMethod actionHandler = new JavaMethod(APP_METH_APP_GUI_ACTION,
					Variable.SCOPE_PUBLIC, "void");
			actionHandler.addParameter(APP_METH_PARM_ACTION,
					GUIConstants.CLASS_NAME_GUI_ACTION);
			actionHandler
					.addCodeLine("System.out.println(\"Action received :\" + "
							+ APP_METH_PARM_ACTION + ".getID())");
			// mainClass.addMethod(actionHandler);
			mainClass.addVariable(appManager);
			JavaVariable mainWindowVar = new JavaVariable(
					APP_INSTANCE_MAIN_WINDOW, Variable.SCOPE_PUBLIC, mainWindow
							.getID());
			mainClass.addVariable(mainWindowVar);
			if (containsMenus()) {
				JavaVariable menuFactoryVar = new JavaVariable(
						APP_INSTANCE_MNU_FACTORY, Variable.SCOPE_PUBLIC,
						MENU_FCTR_CLASS_NAME);
				menuFactoryVar.addModifier(JavaConstants.MODIFIER_STATIC);
				mainClass.addVariable(menuFactoryVar);
			}
			if (containsActions()) {
				JavaVariable actFactoryVar = new JavaVariable(
						APP_INSTANCE_ACT_FACTORY, Variable.SCOPE_PUBLIC,
						ACT_FCTR_CLASS_NAME);
				actFactoryVar.addModifier(JavaConstants.MODIFIER_STATIC);
				mainClass.addVariable(actFactoryVar);
			}
			if (containsToolbars()) {
				JavaVariable tlbFactoryVar = new JavaVariable(
						APP_INSTANCE_TLB_FACTORY, Variable.SCOPE_PUBLIC,
						TLB_FCTR_CLASS_NAME);
				tlbFactoryVar.addModifier(JavaConstants.MODIFIER_STATIC);
				mainClass.addVariable(tlbFactoryVar);
			}
			if (containsPanels()) {
				JavaVariable pnlFactoryVar = new JavaVariable(
						APP_INSTANCE_PNL_FACTORY, Variable.SCOPE_PUBLIC,
						PNL_FCTR_CLASS_NAME);
				pnlFactoryVar.addModifier(JavaConstants.MODIFIER_STATIC);
				mainClass.addVariable(pnlFactoryVar);
			}
			//instance
			JavaVariable staticInstance = new JavaVariable(
					"instance", Variable.SCOPE_PUBLIC,
					APP_MAIN_CLASS_NAME);
			staticInstance.addModifier(JavaConstants.MODIFIER_STATIC);
			mainClass.addVariable(staticInstance);

			JavaMethod initMethod = new JavaMethod("init",
					Variable.SCOPE_PRIVATE, "void");
			JavaMethod exitMethod = new JavaMethod(APP_METH_APP_EXIT,
					Variable.SCOPE_PUBLIC, "void");
			exitMethod.addCodeLine("if (" + APP_INSTANCE_APP_MANAGER
					+ "!=null){");
			exitMethod.addCodeLine(APP_INSTANCE_APP_MANAGER + "."
					+ APP_METH_APP_EXIT + "()");
			exitMethod.addCodeLine("}");
			exitMethod.addCodeLine("else{");
			exitMethod.addCodeLine("super." + APP_METH_APP_EXIT + "()");
			exitMethod.addCodeLine("}");
			handleLAF(initMethod);
			if ((appReader.getAppSettingsSourceType()!=null)&&(appReader.getAppSettingsSourceType().equals(SETT_TYPE_XML))) {
				String filePath = appReader.getAppSettingsFile().getFilePath();
				if (TextUtil.isEmpty(filePath)) {
					filePath = "";
				} else {
					filePath += "/";
				}
				String settingsFile = filePath
						+ appReader.getAppSettingsFile().getFileName() + ".xml";
				JavaMethod getSettings = new JavaMethod("getSettingsFile",
						Variable.SCOPE_PUBLIC, "String");
				getSettings.addCodeLine("return \"" + settingsFile + "\"");
				mainClass.addMethod(getSettings);
			}
			initMethod.addCodeLine("super.initApplication()");
			initMethod.addCodeLine(APP_INSTANCE_WIN_FACTORY + " = new "
					+ WINDS_FCTR_CLASS_NAME + "()");
			initMethod.addCodeLine(APP_METH_SET_WIN_FACTORY + "("
					+ APP_INSTANCE_WIN_FACTORY + ")");

			initMethod.addCodeLine("if (businessMode == AppBusinessMode.Interfaces){");
				initMethod.addCodeLine("String appMgrClsName = readAppMgrClassName()");
				initMethod.addCodeLine("if (appMgrClsName != null){");
				initMethod.addCodeLine("try {");
				initMethod.addCodeLine(APP_INSTANCE_APP_MANAGER + " = (ApplicationManager)Class.forName(appMgrClsName).newInstance()");
				initMethod.addCodeLine(APP_METH_SET_APP_MANAGER + "( "+ APP_INSTANCE_APP_MANAGER + ")");
				initMethod.addCodeLine(APP_INSTANCE_APP_MANAGER + "."+ APP_METH_APP_INIT + "(args)");

				initMethod.addCodeLine("}");
				initMethod.addCodeLine("catch (Exception e) {");
				initMethod.addPrintingLineCode("Error instantiating the application manager class (\" + appMgrClsName + \")");
				initMethod.addCodeLine("e.printStackTrace()");
				initMethod.addCodeLine("}");
				initMethod.addCodeLine("}");
			initMethod.addCodeLine("}");
			initMethod.addCodeLine("else if (businessMode == AppBusinessMode.Annotations){");
			initMethod.addCodeLine("doInitiateAnnotations();");
			initMethod.addCodeLine("}");
			initMethod.addCodeLine("super.postAppManagerInit()");

			/*
			 * if (appReader.getLogFile()!= null){
			 * initMethod.addCodeLine("redirectLog(\"" +
			 * appReader.getLogFile().getFilePath() + "\",\"" +
			 * appReader.getLogFile().getFileName() + "\")"); }
			 */
			if (containsActions()) {
				initMethod.addCodeLine(APP_INSTANCE_ACT_FACTORY + " = new "
						+ ACT_FCTR_CLASS_NAME + "(this)");
				initMethod.addCodeLine(APP_METH_SET_APP_ACT_FCT + "("
						+ APP_INSTANCE_ACT_FACTORY + ")");
			}
			if (containsToolbars()) {
				initMethod.addCodeLine(APP_INSTANCE_TLB_FACTORY + " = new "
						+ TLB_FCTR_CLASS_NAME + "(" + APP_INSTANCE_ACT_FACTORY
						+ ")");
				initMethod.addCodeLine(APP_METH_SET_APP_TLB_FCT + "("
						+ APP_INSTANCE_TLB_FACTORY + ")");
			}
			if (containsMenus()) {
				initMethod.addCodeLine(APP_INSTANCE_MNU_FACTORY + " = new "
						+ MENU_FCTR_CLASS_NAME + "(" + APP_INSTANCE_ACT_FACTORY
						+ ")");
				initMethod.addCodeLine(APP_METH_SET_APP_MNU_FCT + "("
						+ APP_INSTANCE_MNU_FACTORY + ")");
			}
			if (containsPanels()) {
				initMethod.addCodeLine(APP_INSTANCE_PNL_FACTORY + " = new "
						+ PNL_FCTR_CLASS_NAME + "()");
				initMethod.addCodeLine(APP_METH_SET_APP_PNL_FCT + "("
						+ APP_INSTANCE_PNL_FACTORY + ")");
			}


			// initMethod.addCodeLine(APP_INSTANCE_MAIN_WINDOW +
			// SWING_METH_SET_DEF_LAF); // Setting the default LAF decoration
			if (appReader.getAppManagerClass() != null) {
				JavaMethod readAppMgrMethod = new JavaMethod("readAppMgrClassName",JavaMethod.SCOPE_PUBLIC,"String",false);
				readAppMgrMethod.addCodeLine("return \"" +appReader.getAppManagerClass() + "\"" );
				mainClass.addMethod(readAppMgrMethod);
			}	
			
			initMethod.addCodeLine("if ("+APP_INSTANCE_APP_MANAGER+" != null){");
			initMethod.addCodeLine(APP_INSTANCE_MAIN_WINDOW + " = new "	+ mainWindow.getID() + "(" + APP_INSTANCE_APP_MANAGER	+ ".get" + mainWindow.getID() + "Listener(),null)");
			initMethod.addCodeLine("}");
			initMethod.addCodeLine("else{");
			initMethod.addPrintingLineCode("Warning : The porting application manager class is not available");
			initMethod.addCodeLine(APP_INSTANCE_MAIN_WINDOW + " = new "+ mainWindow.getID() + "(null,null)");
			initMethod.addCodeLine("}");

			/*
			if (appReader.getAppManagerClass() == null) {
				
				initMethod.addCodeLine("String appManagerClassName = System.getProperty(\"" + RUN_SYS_PROPERTY_LINK_CLASS + "\")");
				initMethod.addCodeLine("if (appManagerClassName==null){");
				initMethod.addPrintingLineCode("Warning : The porting application manager class is not available");
				initMethod.addCodeLine(APP_INSTANCE_MAIN_WINDOW + " = new "+ mainWindow.getID() + "(null,null)");
				initMethod.addCodeLine("}else{");
				initMethod.addCodeLine("try {");
				initMethod.addCodeLine(APP_INSTANCE_APP_MANAGER + " = (ApplicationManager)Class.forName(appManagerClassName).newInstance()");
				initMethod.addCodeLine(APP_METH_SET_APP_MANAGER + "( "+ APP_INSTANCE_APP_MANAGER + ")");
				initMethod.addCodeLine(APP_INSTANCE_APP_MANAGER + "."+ APP_METH_APP_INIT + "(args)");
				initMethod.addCodeLine(APP_INSTANCE_MAIN_WINDOW + " = new "	+ mainWindow.getID() + "(" + APP_INSTANCE_APP_MANAGER	+ ".get" + mainWindow.getID() + "Listener(),null)");
				initMethod.addCodeLine("} catch (Exception e) {");
				initMethod.addPrintingLineCode("Error instantiating the application manager class (\" + appManagerClassName + \")");
				initMethod.addCodeLine("e.printStackTrace()");
				initMethod.addCodeLine("}");
				initMethod.addCodeLine("}");
			} else {
				mainClass.addImport(appReader.getAppManagerClass());
				initMethod.addCodeLine(APP_INSTANCE_APP_MANAGER + " = new "	+ appReader.getAppManagerClass() + "()");
				initMethod.addCodeLine(APP_METH_SET_APP_MANAGER + "( "+ APP_INSTANCE_APP_MANAGER + ")");
				initMethod.addCodeLine(APP_INSTANCE_APP_MANAGER + "."+ APP_METH_APP_INIT + "(args)");
				initMethod.addCodeLine(APP_INSTANCE_MAIN_WINDOW + " = new "	+ mainWindow.getID() + "(" + APP_INSTANCE_APP_MANAGER	+ ".get" + mainWindow.getID() + "Listener(),null)");
			}
			*/
			// COMPONENT ORIENTATION
			initMethod.addCodeLine(APP_INSTANCE_MAIN_WINDOW + "."
					+ SWING_METH_SET_COMP_ORIENT + "(" + APP_MAIN_CLASS_NAME
					+ "." + APP_METH_GET_APP_ORIENT + "())");

			if (TAG_ATT_WINDOW_TYPE_MDI.equals(mainWindow.getType())) {
				initMethod.addCodeLine(APP_INSTANCE_WIN_FACTORY + "."
						+ WINDS_FCTR_METH_SET_MDI + "("
						+ APP_INSTANCE_MAIN_WINDOW + ")");
			}
			initMethod.addCodeLine(APP_INSTANCE_WIN_FACTORY + "."
					+ WINDS_FCTR_METH_ADD_WINDOW + "(\"" + mainWindow.getID()
					+ "\"," + APP_INSTANCE_MAIN_WINDOW + ")");

			initMethod.addCodeLine(APP_INSTANCE_MAIN_WINDOW + ".setVisible(true)");
			initMethod.addCodeLine("if("+APP_INSTANCE_APP_MANAGER + "!=null){");
			initMethod.addCodeLine("\t" +APP_INSTANCE_APP_MANAGER + "."
					+ APP_METH_INTERFACE_CREATED + "()");
			initMethod.addCodeLine("}");
			// initMethod.addCodeLine("System.out.println(\"This is the end of
			// the application ....\")");
			// initMethod.addCodeLine(APP_INSTANCE_APP_MANAGER + "." +
			// APP_METH_APP_EXIT + "()");

			JavaMethod constructMethod = new JavaMethod(APP_MAIN_CLASS_NAME,
					Variable.SCOPE_PUBLIC, null, true);
			constructMethod.addParameter("args", "String[]");
			constructMethod.addCodeLine("this.args = args");
			if (appReader.getSplash() != null) {
				addSplash(constructMethod, appReader.getSplash());
			} else{
				constructMethod.addCodeLine("SwingUtilities.invokeLater(new Runnable(){public void run(){");
				constructMethod.addCodeLine("init()");
				constructMethod.addCodeLine("}});");
			}
			

			mainClass.addConstructors(constructMethod);
			mainMethod.addCodeLine("final String[] fargs = args");
			mainMethod.addCodeLine(" instance = new "	+ APP_MAIN_CLASS_NAME + "(fargs)");
			mainClass.addImport("javax.swing.SwingUtilities");
			mainClass.addMethod(initMethod);
			JavaMethod openWindowMethod = new JavaMethod("openWindow",
					Variable.SCOPE_PUBLIC, "void");
			openWindowMethod.addParameter("windowName", "String");
			openWindowMethod.addParameter("incomingAction",
					GUIConstants.CLASS_NAME_GUI_ACTION);
			mainClass.addImport(GUIConstants.CLASS_NAME_FP_GUI_ACTION);
			openWindowMethod.addCodeLine(APP_INSTANCE_WIN_FACTORY
					+ ".openWindow(windowName," + APP_INSTANCE_APP_MANAGER
					+ ",incomingAction)");
			mainClass.addMethod(openWindowMethod);
			JavaMethod sendWindowMethod = new JavaMethod("sendWindow",
					Variable.SCOPE_PUBLIC, "void");
			sendWindowMethod.addParameter("windowName", "String");
			sendWindowMethod.addParameter("incomingAction",
					GUIConstants.CLASS_NAME_GUI_ACTION);
			sendWindowMethod.addCodeLine(APP_INSTANCE_WIN_FACTORY
					+ ".sendWindow(windowName,incomingAction)");
			mainClass.addMethod(sendWindowMethod);

			mainClass.addMethod(exitMethod);
		}
	}

	private void addSplash(JavaMethod constructMethod, Element splashNode) {
		GUISplash curSplash = windowGenerator.buildSplash(splashNode);
		constructMethod.addCodeLine("activateResource()");
		if (curSplash.getIcon() != null) {
			if (!TextUtil.isEmpty(curSplash.getTime())) {
				constructMethod
						.addCodeLine("splashScreen = new SMYLDSplashScreen(getImage(\""
								+ curSplash.getIcon()
								+ "\"),"
								+ curSplash.getTime() + ")");
				mainClass.addImport("org.smyld.gui.SMYLDSplashScreen");
				constructMethod.addCodeLine("SwingUtilities.invokeLater(new Runnable(){public void run(){");
				constructMethod.addCodeLine("init()");
				constructMethod.addCodeLine("}});");
				
			} else {
				constructMethod
						.addCodeLine("splashScreen = new SMYLDSplashScreen(getImage(\""
								+ curSplash.getIcon() + "\"))");
				constructMethod.addCodeLine("SwingUtilities.invokeLater(new Runnable(){public void run(){");
				constructMethod.addCodeLine("init()");
				constructMethod.addCodeLine("}});");
				mainClass.addImport("org.smyld.gui.SMYLDSplashScreen");
				constructMethod.addCodeLine("splashScreen.dispose()");
			}
			// This line take care of the panel creation step
		} else if (curSplash.getBody() != null) {

		}

	}

	private boolean containsPanels() {
		return containsItems(appReader.loadPanels());
	}

	private boolean containsMenus() {
		return containsItems(appReader.loadMenus());
	}

	private boolean containsActions() {
		return containsItems(appReader.loadActions());
	}

	private boolean containsToolbars() {
		return containsItems(appReader.loadToolbars());
	}

	private boolean containsItems(HashMap<?,?> collection) {
		return ((collection != null) && (collection.size() > 0));
	}

	private void handleLAF(JavaMethod initMethod) {
		if (appReader.getLAF() != null) {
			mainClass.addImport("javax.swing.UIManager");
			initMethod.addCodeLine("try{");
			initMethod.addCodeLine("UIManager.setLookAndFeel(\""
					+ appReader.getLAF() + "\")");
			initMethod
					.addCodeLine("} catch (Exception ex) {ex.printStackTrace();}");
		}
	}

	private void handleLibraries() {
		try {
			if (appReader.exportLibraries()){
				HashMap<String,String> libs = new HashMap<String,String>();
				// TODO in case of the single application generation, we need to handle that differently, 
				//this will not work for now but we can avoid that by setting the export attribute to false 
				HashMap<String,String> mainLibs = PortalManager.loadLibraries();
				if ((mainLibs!=null)&&(mainLibs.size()>0))
					libs.putAll(mainLibs);
				HashMap<String,String> appLibs = appReader.loadLibraries();
				if ((appLibs != null) && (appLibs.size() > 0)) {
					System.out.println("\n Libraries referenced : ");
					libs.putAll(appLibs);
				}
				for (String libID : libs.keySet()) {
					String libFile = libs.get(libID);
					System.out.println(libID + " : " + libFile);
					String targetPath = appReader.getHomePath() + File.separator
							+ "lib";
					File targetLibFile = FileSystem.createFileWithPath(targetPath,
							libID);
					// FileSystem.confirmFoldersExistence(targetPath);
					// String targetLib = targetPath + File.separator + libID;
					FileSystem.copyFile(new File(libFile), targetLibFile);
					usedLibs.add(targetLibFile.getPath());
				}

				System.out.println("\n");
				newApplication.setUsedLibraries(usedLibs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void buildWindows() throws Exception {
		// Code for building the windows
		GUIWindow startupWindow = null;
		GUIWindow mdiWindow = null;
		HashMap<String,Element> windows = appReader.loadWindows();
		for (Element currentWindow : windows.values()) {
			GUIWindow newWindow = windowGenerator.buildWindow(currentWindow);
			// Specifying the MDI window
			if (TAG_ATT_BOOLEAN_TRUE.equals(newWindow.getStartup())) {
				mdiWindow = newWindow;
			}
			if (TAG_ATT_WINDOW_TYPE_MDI.equals(newWindow.getType())) {
				startupWindow = newWindow;
			}
			JavaClassBody[] classes = componentsBuilder
					.generateWindow(newWindow);
			if (classes != null) {
				if (classes.length > 0) {
					currentWindow.getAttributeValue(TAG_COMP_ATT_ID);
					// generatedClasses.put(panelID,classes);
					newApplication.addWindow(classes[0].getName(), classes[0]);
					newApplication.addWindowInterface(classes[1].getName(),
							classes[1]);
				}
			}
		}
		if (startupWindow != null) {
			mainWindow = startupWindow;
		} else if (mdiWindow != null) {
			mainWindow = mdiWindow;
		}
		JavaClassBody windowFactory = componentsBuilder.generateWindowFactory();
		newApplication.addMainClass(windowFactory.getName(), windowFactory);
	}

	private void buildMenus() {
		// Code for building the menus
		try {
			JavaClassBody menuFactory = componentsBuilder
					.generateMenuFactory(appReader.loadMenus());
			newApplication.addMainClass(MENU_FCTR_CLASS_NAME, menuFactory);
		} catch (Exception e) {
			System.out.println("Exception in building menus : ");
			e.printStackTrace();
		}
	}

	private void buildToolbars() {
		// Code for creating the toolbars here
		try {
			HashMap<String, GUIToolbar> toolbars = appReader.loadToolbars();
			if ((toolbars != null) && (toolbars.size() > 0)) {
				JavaClassBody tlbFactory = componentsBuilder
						.generateToolbarsFactory(appReader.loadToolbars());
				newApplication.addMainClass(TLB_FCTR_CLASS_NAME, tlbFactory);
			}
		} catch (Exception e) {
			System.out.println("Exception in building menus : ");
			e.printStackTrace();
		}

	}

	private void buildActions() {
		try {
			JavaClassBody actionsFactory = componentsBuilder
					.generateActionsFactory(appReader.loadActions());
			newApplication.addMainClass(ACT_FCTR_CLASS_NAME, actionsFactory);

		} catch (Exception e) {
			System.out.println("Exception in building Actions : ");
			e.printStackTrace();
		}
	}

	private void createAppManagerInterface() {
		// Code for creating the main application interface which will port with
		// the
		// interfacing application
		JavaInterface mainAppManager = new JavaInterface(
				APP_MANAGER_INTERFACE_NAME, appReader.getMainClassPackage(),
				null, true);
		mainAppManager.addImport(APP_MANAGER_FP_CLASS_NAME);
		mainAppManager.setParentClassName(APP_MANAGER_CLASS_NAME);
		for (JavaClassBody curClassBody : newApplication.getGeneratedInterfaces().values()) {
			JavaInterface curInterface = (JavaInterface) curClassBody;
			JavaMethod callInterface = new JavaMethod("get"
					+ curInterface.getName(), Variable.SCOPE_PUBLIC,
					curInterface.getName());
			callInterface.addModifier(JavaClassBody.MODIFIER_ABSTRACT);
			mainAppManager.addMethod(callInterface);
			mainAppManager.addImport(curInterface.getFullClassName());
		}
		// Adding application init method
		JavaMethod callInterface = new JavaMethod(APP_METH_APP_INIT,
				Variable.SCOPE_PUBLIC, "void");
		callInterface.addParameter("args", "String[]");
		callInterface.addModifier(JavaClassBody.MODIFIER_ABSTRACT);
		JavaMethod appExit = new JavaMethod(APP_METH_APP_EXIT,
				Variable.SCOPE_PUBLIC, "void");
		appExit.addModifier(JavaClassBody.MODIFIER_ABSTRACT);
		mainAppManager.addMethod(appExit);

		newApplication.addMainInterface(APP_MANAGER_INTERFACE_NAME,
				mainAppManager);
	}

	
	private void buildPanels() throws Exception {
		HashMap<String,Element> panels = appReader.loadPanels();
		if ((panels == null) || (panels.size() == 0)) {
			warnings.add("No single panel specified!");
			return;
		}
		for (Element currentPanel : panels.values()) {
			ArrayList<GUIComponent> components = panelGenerator.buildPanelComponents(currentPanel);
			JavaClassBody[]      classes    = componentsBuilder.generatePanel(components,panels);

			if (classes != null) {
				if (classes.length > 0) {
					currentPanel.getAttributeValue(TAG_COMP_ATT_ID);
					newApplication.addPanel(classes[0].getName(), classes[0]);
					newApplication.addPanelInterface(classes[1].getName(),
							classes[1]);
				}
			}
		}
		newApplication.addClass(PNL_FCTR_CLASS_NAME, componentsBuilder
				.generatePanelFactory());
	}

	private void exportClasses() throws Exception {
		exportClasses(newApplication.getGeneratedClasses());
	}

	private void exportClasses(HashMap<String,JavaClassBody> classesCollection) throws Exception {
		for (JavaClassBody curClass : classesCollection.values()) {
			if (curClass != null) {
				curClass.exportFileToPath(appReader.getSourcePath());
			}
		}

	}

	private File getJarFile() {
		return FileSystem.createFileWithPath(appReader.getTargetJarPath(),
				appReader.getTargetJarName() + ".jar");
	}

	public void createJarFile() {
		String targetPath = appReader.getHomePath() + File.separator
				+ "lib" + File.separator;

		createJarFile(getJarFile(),appReader.compileClasses(),targetPath);
	}

	public void createJarFile(File jarFile,boolean addCompiledClasses,String librariesPath) {
		try {
			// FileSystem.confirmFoldersExistence(appReader.getTargetJarPath());
			// String jarFile = appReader.getTargetJarPath() + File.separator +
			// appReader.getTargetJarName() + ".jar";

			SMYLDJARWriter jarStream = new SMYLDJARWriter(new FileOutputStream(
					jarFile));
			if (PortalManager.logFile!=null)
				jarStream.setPrintDuplicateEntries(PortalManager.logFile.isShowJarDuplicates());

			// Adding the class files
			if (addCompiledClasses){
				jarStream.addFilesInFolder(new File(appReader.getClassPath()),
						new FileFilter() {
							public boolean accept(File targetFile) {
								return targetFile.getName().endsWith(".class");
							}
						});
			}

			// Copying all reference libraries

			if (appReader.exportLibraries()){
				HashMap<String,String> libs = appReader.loadLibraries();
				// Adding the main libraries
				addLibraries(PortalManager.loadLibraries(),
						jarStream, librariesPath);
				// Adding the application libraries
				if (appReader.loadLibraries() != null) {
					addLibraries(appReader.loadLibraries(), jarStream, librariesPath);
				}
			}
			// Adding language files
			// Utility for getting the languages out of the data base must be
			// added here
			HashMap<String,LangSource> langs = appReader.loadLanguages();
			if ((langs != null) && (langs.size() > 0)) {
				for (String langName : langs.keySet()) {
					LangSource newLang = langs.get(langName);
					try {
						jarStream.addFile(newLang.getSourceFileName(),
								JAR_FOLDER_LANGS + newLang.getTargetFileName());
					} catch (JarEntryAlreadyExist je) {
						// we do not do any thing becaus of duplicate
					}
				}
			}
			// Adding images to the jar
			HashMap<String,String> images = appReader.loadImages();
			if ((images != null) && (images.size() > 0)) {
				for (String imageName : images.keySet()) {
					String imagePath = images.get(imageName);

					try {
						jarStream.addFile(imagePath, JAR_FOLDER_IMAGES
								+ imageName);
					} catch (JarEntryAlreadyExist je) {
						// we do not do any thing becaus of duplicate
					}
				}
			}
			// Adding resources to the jar
			HashMap<String,String> resources = appReader.loadResources();
			if ((resources != null) && (resources.size() > 0)) {
				for (String resName : resources.keySet()) {
					String resPath = resources.get(resName);

					try {
						jarStream.addFile(resPath, JAR_FOLDER_RESS + resName);
					} catch (JarEntryAlreadyExist je) {
						// we do not do any thing becaus of duplicate
					}
				}
			}

			// Creating the manifest file
			Attributes att = new Attributes();
			att.putValue(Attributes.Name.MAIN_CLASS.toString(), appReader
					.getMainClassPackage()
					+ ".MainClass");
			jarStream.createManifestFromAttribute(att);
			jarStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void populateProjectSettings(){
		HashMap<String,LangSource> langs = appReader.loadLanguages();
		if ((langs != null) && (langs.size() > 0)) {
			for (String langName : langs.keySet()) {
				LangSource newLang = langs.get(langName);
				appSetWriter.addLanguage(newLang.getName(), newLang
						.getTargetFileName());
			}
		}
		HashMap<String,LookAndFeelResource> lafs = appReader.loadLookAndFeels();
		if ((lafs != null) && (lafs.size() > 0)) {
			for (String lafName : lafs.keySet()) {
				LookAndFeelResource newLaf = lafs.get(lafName);
				appSetWriter.addLookAndFeel(newLaf);
			}
		}
		if (appReader.getLogFile() != null) {
			appSetWriter.addLogFile(appReader.getLogFile());
		}
		if(getPrjType() == ProjectBuildSource.Maven) {
			appSetWriter.addGroup(((MavenProjectBuilder)PortalManager.getInstance().projectBuilder).getGroup());
		}else if (appReader.getGroup()!=null){
			appSetWriter.addGroup(appReader.getGroup());
		}
	}
	private void createDeployments() throws IOException {
		if ((appReader.loadDeployments() != null)
				&& (appReader.loadDeployments().size() > 0)) {
			for (DeploymentDescriptor curDeployment  : appReader.loadDeployments()) {
				if (curDeployment instanceof JNLPDeploymentDescriptor) {
					handelJNLPDeployer((JNLPDeploymentDescriptor) curDeployment);
				}
			}
		}
	}

	private void handelJNLPDeployer(JNLPDeploymentDescriptor jnlpDeployDesc)
			throws IOException {
		if ((jnlpDeployDesc.getTargetServer() != null)
				&& (jnlpDeployDesc.getTargetServer().getPath() != null)) {
			String targetWarFileName = jnlpDeployDesc.getTargetServer()
					.getPath()
					+ jnlpDeployDesc.getName() + ".war";
			SMYLDJNLPWriter newDeployer = new SMYLDJNLPWriter(targetWarFileName);
			jnlpDeployDesc.setMainClass(mainClass.getFullClassName());
			newDeployer.importDescriptor(jnlpDeployDesc);
			File jarFile = getJarFile();
			String targetJarName = JNLP_DEFAULT_APP_FOLDER + jarFile.getName();
			try {
				if (jnlpDeployDesc.getIcon() != null) {
					File iconFile = new File(jnlpDeployDesc.getIcon());
					if (iconFile.exists()) {
						newDeployer.getJNLPFile().setIcon(iconFile.getName());
						newDeployer.addFile(iconFile.getPath(), iconFile
								.getName());
					}
				}

				newDeployer.getJNLPFile().addJarFile(targetJarName, true);
				if (jnlpDeployDesc.getSecurityKey() != null) {
					newDeployer.addMainJarFile(jarFile.getPath(),
							jnlpDeployDesc.getSecurityKey());
				} else {
					newDeployer.addMainJarFile(jarFile.getPath());
				}
				if (jnlpDeployDesc.getSecurityPermissions() != null) {
					for (String curPermission : jnlpDeployDesc.getSecurityPermissions()) {
						if (curPermission.equals("all")) {
							newDeployer.getJNLPFile().grantAllPermissions();
						}
					}

				}
				newDeployer.dumpFile();
				System.out.println("Deploying JNLP WAR file to "
						+ newDeployer.getFileName() + " ....");
			} catch (JarEntryAlreadyExist e) {
				e.printStackTrace();
			}
		}
	}

	private void addLibraries(HashMap<String,String> libs, SMYLDJARWriter jarStream,
			String targetPath) throws Exception {
		if ((libs!=null)&&(libs.size()>0)) {
			for (String libID : libs.keySet()) {
				jarStream.addLibrary(targetPath + libID);

			}
		}

	}

	void runApplication() {
		try {
			Runtime rt = Runtime.getRuntime();
			// String sourceClassPath = appReader.getSourcePath() +
			// File.separator +
			// JavaLangUtility.convertPackageToPath(appReader.getMainClassPackage())
			// + File.separator+ mainClass.getName() + ".java";
			String targetClassPath = appReader.getClassPath()
					+ File.separator
					+ JavaLangUtility.convertPackageToPath(appReader
							.getMainClassPackage()) + File.separator
					+ "MainClass";
			String command = "java " + targetClassPath;
			// System.out.println("javac",params);
			rt.exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PESwingApplicationReader getAppReader() {
		return appReader;
	}

}

