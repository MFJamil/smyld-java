package org.smyld.app;

import java.awt.ComponentOrientation;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.UIManager;

import org.smyld.SMYLDObject;
import org.smyld.app.pe.model.Constants;
import org.smyld.gui.GUIAction;
import org.smyld.gui.SMYLDSplashScreen;
import org.smyld.gui.event.ActionHandler;
import org.smyld.gui.portal.engine.AppSettingsWriter;
import org.smyld.app.pe.model.user.PEUser;
import org.smyld.app.pe.model.user.	UserSettings;
import org.smyld.io.FileSystem;
import org.smyld.resources.Resource;
import org.smyld.run.SMYLDProcessUtility;
import org.smyld.util.Translator;
import org.smyld.util.multilang.MultiLangSource;

//import com.sun.java.swing.plaf.windows.
/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class AppMainClass extends SMYLDObject implements ActionHandler,
		AppConstants {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3266833824829881846L;
	/**
	 * 
	 * @see
	 * @since
	 */

	protected        AppWindowsFactory         appWindowsFactory;
	public    static AppManager                mainAppManager;
	public           AppMenuFactory            appMenuFactory;
	public           AppActionFactory          appActionsFactory;
	public           AppToolbarsFactory        appToolbarsFactory;
	public           AppPanelFactory           appPanelsFactory;
	protected        AppSettingsReader         appSettings;
	public    static ActiveApplication         activeApplication;
	protected static Translator                translator;
	protected static Resource                  resource;
	protected static String                    charSetName;
	protected static SMYLDSplashScreen          splashScreen;
	protected static ComponentOrientation      appCompOrientation = ComponentOrientation.LEFT_TO_RIGHT;
	public    static AppMainClass              instance;
	                 HashMap<String, Object>   activeObjects;
	protected        String[]                  args;
	protected        PEUser                    activeUser;
	protected        AppBusinessMode           businessMode = AppBusinessMode.Interfaces; //This is the default mode

	protected        AppAnnotationsHandler     annotationsHandler = new AppAnnotationsHandler();
	public AppMainClass() {
		// initApplication();
	}
	
	public static void closeSplash(){
		if (splashScreen!=null) splashScreen.dispose();
	}

	protected void initApplication() {
		instance = this;
		activeObjects = new HashMap<String, Object>();

	}


	public void handleAction(String objectID,String actionID,Object objHandle){
		System.out.println(String.format("@PEBusinessAction(objectID = \"%s\",actionID = \"%s\")",objectID,actionID));
		annotationsHandler.handlePEAction(objectID,actionID,objHandle);
	}

	protected void postAppManagerInit() {
		if (mainAppManager != null) {
			activeUser = mainAppManager.getActiveUser();
			if (activeUser != null)
				activateApplicationFrmUser();
		}
		System.out.println("Active application is : " + activeApplication);
		if (activeApplication == null)
			activateApplicationFrmXML(getSettingsFile());
		if (activeApplication != null){

			if (activeApplication.getGroup()!=null){
				System.out.println("Processing the annotations from the Group : " + activeApplication.getGroup());
				if (annotationsHandler.processAnnotations(activeApplication.getGroup())) {
					businessMode = AppBusinessMode.Annotations;
					doInitiateAnnotations();
				}
			}

			activateTranslator(activeApplication.getDefaultLanguage().getSourceFileName(), activeApplication.getDefaultLanguage().getName());
			if (activeApplication.getDefaultLanguage().getName().equals(Translator.LANG_ARABIC)) {
				appCompOrientation = ComponentOrientation.RIGHT_TO_LEFT;
			}
			try {
				if (activeApplication.getDefaultLookAndFeel() == null) {
					System.out.println(UIManager.getSystemLookAndFeelClassName());
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} else {
					UIManager.setLookAndFeel(activeApplication.getDefaultLookAndFeel().getClassName());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	protected void activateApplicationFrmUser(){
		System.out.println(" ======================================== activateApplicationFrmUser");
		UserSettings userSett = activeUser.getSettings();
		if (userSett!=null){
			System.out.println(" ======================================== Settings No NUll");
			DefaultLiveApplication defActiveApp = (DefaultLiveApplication)activateApplicationFrmJarXML(getSettingsFile()); 
			if (userSett.getLanguage()!=null){
				System.out.println("Got the settings to be  ==LANGUAGE========== " + userSett.getLanguage());
				defActiveApp.setDefaultLanguage(defActiveApp.getLanguages().get(userSett.getLanguage()));
			}
			if (userSett.getLookAndFeel()!=null){
				System.out.println("Got the settings to be  ==Look And Feels========== " + userSett.getLookAndFeel());
				defActiveApp.setDefaultLookAndFeel(defActiveApp.getLookAndFeels().get(userSett.getLookAndFeel()));
			}
			activeApplication = defActiveApp;
		}
	}

	protected String getSettingsFile() {
		if (System.getProperty(Constants.RUN_SYS_PROPERTY_CONF_FILE)!=null)
			return System.getProperty(Constants.RUN_SYS_PROPERTY_CONF_FILE);
		//TODO Reading configuration location from the annotation might not be a good idea, need to check that further
		//if(annotationsHandler.isApplicatinActive())
		//	return annotationsHandler.getPeAppAnt().configFile();
		return APP_DEF_SETTINGS_FILE;
	}
	
	public String readAppMgrClassName(){
		return System.getProperty(Constants.RUN_SYS_PROPERTY_LINK_CLASS);
	}

	public void setWindowsFactory(AppWindowsFactory newFactory) {
		appWindowsFactory = newFactory;
	}

	public void setApplicationManager(AppManager newAppManager) {
		mainAppManager = newAppManager;
	}

	public static ComponentOrientation getOrientation() {
		return appCompOrientation;
	}

	public static InputStream getResourceInputStream(String resourceName) {
		return resource.getResourceInputStream(resourceName);
	}

	public void activateApplicationFrmXML(String settingsFile) {
		System.out.println("Activating the application from the settings file : "	+ settingsFile);
		activateResource();
		try {
			appSettings = new XMLAppSetReader(settingsFile);
			activeApplication = appSettings.getActiveApplication();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ActiveApplication activateApplicationFrmJarXML(String fileName) {
		ActiveApplication activeApp = null;
		activateResource();
		try {
			appSettings = new XMLAppSetReader(getResourceFileInputStream(fileName));
			activeApp = appSettings.getActiveApplication();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return activeApp;
	}

	public void setAppSettingsReader(AppSettingsReader newApplicationSettReader) {
		appSettings = newApplicationSettReader;
	}

	protected void activateTranslator(String xmlFile, String language) {
		System.out.println("Activating the translator for : " + xmlFile
				+ " file for the languague :" + language);
		if (resource == null) {
			activateResource();
		}
		try {
			// InputStream langFileStream =
			// resource.getResourceInputStream(JAR_RES_LANGS + xmlFile );
			URL langFileURL = resource.getResource(JAR_RES_LANGS + xmlFile);
			// MultiLangSource lanSource = new MultiLangSource(langFileStream);
			MultiLangSource lanSource = new MultiLangSource(langFileURL);
			translator = lanSource.loadLanguage(language);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String translate(String module, String ID) {
		return translate(module, ID, null);
	}

	public static String getActiveLanguage() {
		return translator.getLanguage();
	}

	public static String translate(String module, String ID, String defaultValue) {
		if(translator==null) return defaultValue;
		String result = translator.translateWord(module, ID);
		if ((result == null) && (defaultValue != null)) {
			result = defaultValue;
		}
		//if (result==null) result = "\"\""; 
		return result;
	}

	public static void activateResource() {
		try {
			resource = Resource.getInstance(JAR_RES_CLASS);
			// resource = new Resource();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static File getLangFile(String langFileName) throws Exception {
		return resource.getFile(JAR_RES_LANGS + langFileName);
	}

	public static File getResourceFile(String resFileName) throws Exception {
		return resource.getFile(JAR_RES_FILES + resFileName);
	}

	public static File getFile(String fileName) throws Exception {
		return resource.getFile(fileName);
	}

	public static URL getResource(String resourceName) throws Exception {
		return resource.getResource(JAR_RES_FILES + resourceName);
	}

	public static ImageIcon getImage(String imageName) {
		return resource.getImageIcon(JAR_RES_IMAGES + imageName);
	}

	public static InputStream getResourceFileInputStream(String resourceName) {
		return resource.getResourceInputStream(JAR_RES_FILES + resourceName);
	}

	protected void applicationExit() {
		// can be overwritten by other classes and the default action is exit
		if (businessMode==AppBusinessMode.Annotations){
			annotationsHandler.notifyApplicationClosed();
		}
		System.out.println("AppMainClass::applicationExit() is called.....");
		//TODO we need to update the settings instead of re-writing the hole file, since the developer might use it as well
		//doSaveSettings();
		System.exit(0);
	}

	public void doSaveSettings() {

		if ((activeUser!=null)&&(activeUser.getSettings()!=null)){
			//TODO Here we need to activate the settings save for the user object
		}else if (activeApplication != null) {
			AppSettingsWriter setWriter = new AppSettingsWriter(new File(
					getSettingsFile()));
			setWriter.importSettings(activeApplication);
			setWriter.createFile();
		}

	}

	/*
	 * try { activateResource(); activateTranslator("English.xml","German");
	 * System.out.println(translate("windows","mdiWindowTitle")); } catch
	 * (Exception e) { e.printStackTrace(); }
	 * 
	 */

	/**
	 * 
	 * @param args
	 * @see
	 * @since
	 */
	public static void main(String... args)throws Exception {
		new AppMainClass();
	}


	protected void doInitiateAnnotations(){
		annotationsHandler.doInitiateAnnotations(args);
	}

	public void processGUIAction(GUIAction incomingAction) {

		String incomingCommand = incomingAction.getCommand();
		System.out.println("Incoming command : " + incomingCommand);
		System.out.println("Incoming target  : " + incomingAction.getTarget());
		// Code for opening the window
		if (incomingCommand.equals(GUIAction.COM_OPEN_WINDOW)) {
			informManager(incomingAction);
			openWindow(incomingAction.getTarget(), incomingAction);
			// Code for runing outside application
		} else if (incomingCommand.equals(GUIAction.COM_RUN_APP)) {
			informManager(incomingAction);
			try {
				Process newAppRun = Runtime.getRuntime().exec(
						incomingAction.getParameters());
				String errCode = SMYLDProcessUtility.getErrorCode(newAppRun);
				if (errCode != null) {
					System.out.println(errCode);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Code for transfereing control to the porting application
		} else if (incomingCommand.equals(GUIAction.COM_CLOSE_APP)) {
			// Need to add aditional code to call the application exit
			applicationExit();
		} else if (incomingCommand.equals(GUIAction.COM_ADD_COMP)) {
			informManager(incomingAction);
			String window = incomingAction.getTarget();
			addUserObject(incomingAction);
			sendWindow(window, incomingAction);

		} else {
			informManager(incomingAction);
		}
	}

	private void informManager(GUIAction newAction) {
		if (businessMode==AppBusinessMode.Annotations){
			annotationsHandler.handlePEAction(null,newAction.getID(),newAction);
		}else if (mainAppManager != null) {
			mainAppManager.actionFired(newAction);
		}
	}

	protected void openWindow(String windowName, GUIAction incomingAction) {
	}

	protected void sendWindow(String windowName, GUIAction incomingAction) {
	}

	public void redirectLog(String logFilePath, String logFileName) {
		File targetLogFile = FileSystem.createFileWithPath(logFilePath,
				logFileName);
		if (targetLogFile != null) {
			FileSystem.redirectSysOutput(targetLogFile);
		}
	}

	public void addUserObject(GUIAction incAction) {
		if (incAction != null) {
			Object userObject = incAction.getUserObject();
			if ((userObject != null) && (userObject instanceof String)) {
				String userObjectTxt = (String) userObject;
				incAction.setUserObject(createObject(userObjectTxt));
			}
		}
	}

	private Object createObject(String objectDesc) {
		String[] tockens = objectDesc.split("\\."); 
		Object userObject = null;
		String objectName = null;
		if ((tockens != null) && (tockens.length > 1)) {
			if (tockens[0].equals(OBJ_TYPE_MENU)) {
				userObject = getMenu(tockens[1], tockens[2]);
				objectName = tockens[1];
			} else if (tockens[0].equals(OBJ_TYPE_PANEL)) {
				
				userObject = appPanelsFactory.getPanel(tockens[1],mainAppManager);
				objectName = tockens[1];
			}
		}
		if (userObject != null) {
			activeObjects.put(objectName, userObject);
		}

		return userObject;
	}

	protected Object getMenu(String menuName, String menuType) {
		if (appMenuFactory != null) {
			String methodName = AppMenuFactory.createMethodName(menuName,
					menuType);
			if (methodName != null) {
				return appMenuFactory.getMenu(methodName, this);
			}
		}
		return null;
	}

	public void setAppActionsFactory(AppActionFactory appActionsFactory) {
		this.appActionsFactory = appActionsFactory;
	}

	public void setAppToolbarsFactory(AppToolbarsFactory appToolbarsFactory) {
		this.appToolbarsFactory = appToolbarsFactory;
	}

	public void setAppMenuFactory(AppMenuFactory appMenuFactory) {
		this.appMenuFactory = appMenuFactory;
	}

	public void setAppPanelsFactory(AppPanelFactory appPanelsFactory) {
		this.appPanelsFactory = appPanelsFactory;
	}

	public Object getActiveObject(String objectName) {
		return activeObjects.get(objectName);
	}

	public Object setActiveObject(String objectName, Object object) {
		return activeObjects.put(objectName, object);
	}
	/*
	 * import com.l2fprod.gui.plaf.skin.SkinLookAndFeel; try{
	 * //SkinLookAndFeel.setSkin(SkinLookAndFeel.loadThemePack("D:/downloads/LAF/skin/themepack.zip"));
	 * //SkinLookAndFeel.setSkin(SkinLookAndFeel.loadThemePack("D:/downloads/LAF/skin/aquathemepack.zip"));
	 * //SkinLookAndFeel.setSkin(SkinLookAndFeel.loadThemePack("D:/downloads/LAF/skin/aquathemepack.zip"));
	 * //SkinLookAndFeel.setSkin(SkinLookAndFeel.loadThemePack("D:/downloads/LAF/skin/beosthemepack.zip"));
	 * //SkinLookAndFeel.setSkin(SkinLookAndFeel.loadThemePack("D:/downloads/LAF/skin/chaNinja-Bluethemepack.zip"));
	 * //SkinLookAndFeel.setSkin(SkinLookAndFeel.loadThemePack("D:/downloads/LAF/skin/macosthemepack.zip"));
	 * //SkinLookAndFeel.setSkin(SkinLookAndFeel.loadThemePack("D:/downloads/LAF/skin/quickSilverRthemepack.zip"));
	 * //SkinLookAndFeel.setSkin(SkinLookAndFeel.loadThemePack("D:/downloads/LAF/skin/xplunathemepack.zip"));
	 * 
	 * UIManager.setLookAndFeel(new SkinLookAndFeel()); } catch (Exception ex)
	 * {ex.printStackTrace();}
	 */

}
