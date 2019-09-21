package org.smyld.gui.portal.engine;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Vector;

import org.smyld.SMYLDObject;
import org.smyld.db.DBSettings;
import org.smyld.gui.GUIAction;
import org.smyld.gui.portal.engine.sources.PESwingApplicationReader;
import org.smyld.lang.script.java.JavaClassBody;

/**
 * This class will hold the information needed about the application generated,
 * in order to facilitate the work for other generating classes to provide
 * needed references.
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class Application extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String,JavaClassBody> generatedClasses;
	HashMap<String,JavaClassBody> generatedMainClasses;
	HashMap<String,JavaClassBody> generatedWindows;
	HashMap<String,JavaClassBody> generatedPanels;
	HashMap<String,JavaClassBody> generatedInterfaces;
	HashMap<String,JavaClassBody> generatedWindowInterfaces;
	HashMap<String,JavaClassBody> generatedPanelInterfaces;
	HashMap<String,JavaClassBody> generatedMainInterfaces;

	HashMap<String,GUIAction> guiActions;

	String       name;
	String       sourceFile;
	InputStream  sourceStream;
	String       appStartupClass;
	boolean      compileNeeded;
	
	

	PESwingApplicationReader appReader;
	Vector<String>  usedLabiraries;
	DBSettings dbSource;

	/**
	 * 
	 * @see
	 * @since
	 */
	public Application() {
		init();
	}

	private void init() {
		generatedClasses          = new HashMap<String,JavaClassBody>();
		generatedMainClasses      = new HashMap<String,JavaClassBody>();
		generatedInterfaces       = new HashMap<String,JavaClassBody>();
		generatedWindows          = new HashMap<String,JavaClassBody>();
		generatedPanels           = new HashMap<String,JavaClassBody>();
		generatedPanelInterfaces  = new HashMap<String,JavaClassBody>();
		generatedWindowInterfaces = new HashMap<String,JavaClassBody>();
		generatedMainInterfaces   = new HashMap<String,JavaClassBody>();
	}

	public void setUsedLibraries(Vector<String>  newUsedLibraries) {
		usedLabiraries = newUsedLibraries;
	}

	public Vector<String>  getUsedLabraries() {
		return usedLabiraries;
	}

	public void setGUIActions(HashMap<String,GUIAction> newGUIActions) {
		guiActions = newGUIActions;
	}

	public void addClass(String className, JavaClassBody newClass) {
		generatedClasses.put(className, newClass);
	}

	public void addWindow(String windowName, JavaClassBody newWindow) {
		generatedWindows.put(windowName, newWindow);
		addClass(windowName, newWindow);
	}

	public void addPanel(String panelName, JavaClassBody newPanel) {
		generatedPanels.put(panelName, newPanel);
		addClass(panelName, newPanel);
	}

	public void addMainClass(String mainClassName, JavaClassBody newMainClass) {
		generatedMainClasses.put(mainClassName, newMainClass);
		addClass(mainClassName, newMainClass);
	}

	public void addInterface(String interfaceName, JavaClassBody newInterface) {
		generatedInterfaces.put(interfaceName, newInterface);
		addClass(interfaceName, newInterface);
	}

	public void addWindowInterface(String interfaceName,
			JavaClassBody newInterface) {
		generatedWindowInterfaces.put(interfaceName, newInterface);
		addInterface(interfaceName, newInterface);
	}

	public void addPanelInterface(String interfaceName,
			JavaClassBody newInterface) {
		generatedPanelInterfaces.put(interfaceName, newInterface);
		addInterface(interfaceName, newInterface);
	}

	public void addMainInterface(String interfaceName,
			JavaClassBody newInterface) {
		generatedMainInterfaces.put(interfaceName, newInterface);
		addInterface(interfaceName, newInterface);
	}

	public HashMap<String,JavaClassBody> getGeneratedClasses() {
		return generatedClasses;
	}

	public HashMap<String,JavaClassBody> getGeneratedMainClasses() {
		return generatedMainClasses;
	}

	public HashMap<String,JavaClassBody> getGeneratedInterfaces() {
		return generatedInterfaces;
	}

	public HashMap<String,JavaClassBody> getGeneratedMainInterfaces() {
		return generatedMainInterfaces;
	}

	public HashMap<String,JavaClassBody> getGeneratedWindowInterfaces() {
		return generatedWindowInterfaces;
	}

	public HashMap<String,JavaClassBody> getGeneratedPanelInterfaces() {
		return generatedPanelInterfaces;
	}

	public HashMap<String,JavaClassBody> getGeneratedWindows() {
		return generatedWindows;
	}

	public HashMap<String,JavaClassBody> getGeneratedPanels() {
		return generatedPanels;
	}

	public boolean isClassExist(String className) {
		return generatedClasses.containsKey(className);
	}

	public boolean isInterfaceExist(String interfaceName) {
		return generatedInterfaces.containsKey(interfaceName);
	}

	public JavaClassBody getClass(String className) {
		return (JavaClassBody) generatedClasses.get(className);
	}

	public JavaClassBody getInterface(String interfaceName) {
		return (JavaClassBody) generatedInterfaces.get(interfaceName);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	public PESwingApplicationReader getAppReader() {
		return appReader;
	}

	public void setAppReader(PESwingApplicationReader appReader) {
		this.appReader = appReader;
		//TODO below is ugly spaghetti instances, need to be cleaned
		this.setName(appReader.getAppName());
	}

	public String getAppStartupClass() {
		return appStartupClass;
	}

	public void setAppStartupClass(String appStartupClass) {
		this.appStartupClass = appStartupClass;
	}

	public DBSettings getDbSource() {
		return dbSource;
	}

	public void setDbSource(DBSettings dbSource) {
		this.dbSource = dbSource;
	}

	public boolean isCompileNeeded() {
		return compileNeeded;
	}

	public void setCompileNeeded(boolean compileNeeded) {
		this.compileNeeded = compileNeeded;
	}

	public InputStream getSourceStream() {
		return sourceStream;
	}

	public void setSourceStream(InputStream sourceStream) {
		this.sourceStream = sourceStream;
	}
	

}
