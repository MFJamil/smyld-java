package org.smyld.gui.portal.engine;

import java.io.File;
import java.io.StringBufferInputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import org.smyld.SMYLDObject;
import org.smyld.app.pe.logging.LogFile;
import org.smyld.app.pe.model.Constants;
import org.smyld.db.DBErrorHandler;
import org.smyld.db.oracle.SMYLDOracleConnection;
import org.smyld.app.pe.projectbuilder.MavenProjectBuilder;
import org.smyld.app.pe.projectbuilder.ProjectBuildSource;
import org.smyld.app.pe.projectbuilder.ProjectBuilder;
import org.smyld.gui.portal.engine.sources.PESwingApplicationReader;
import org.smyld.gui.portal.engine.sources.PortalAppXMLSetReaderPESwing;
import org.smyld.gui.portal.engine.sources.PortalDBReaderPESwing;
import org.smyld.gui.portal.engine.sources.SettingsVariablesMapper;
import org.smyld.io.FileSystem;
import org.smyld.util.ProcessTimer;
import org.smyld.version.RunAppVersion;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class PortalManager extends SMYLDObject implements Constants,
		DBErrorHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static SettingsReader settingsReader;
	public static LogFile logFile;
	private static PortalManager instance;
	ApplicationGenerator applicationGenerator;
	ProjectBuilder projectBuilder = new ProjectBuilder(ProjectBuildSource.PortalEngine); // Default to portal engine native builder
	String[] args;

	/**
	 * 
	 * @see
	 * @since
	 */
	public PortalManager(SettingsReader setReader) {
		instance = this;
		settingsReader = setReader;
		init();
	}

	public PortalManager(ProjectBuilder projectBuilder, String singleAppFile, String[] args) throws Exception {
		instance=this;
		this.projectBuilder = projectBuilder;
		this.args = args;
		initSingleApp(singleAppFile);

	}


	public PortalManager(String singleAppFile,String[] args) throws Exception {
		instance=this;
		this.args = args;
		initSingleApp(singleAppFile);
		
	}

	public static PortalManager getInstance(){
		return instance;

	}
	public static HashMap<String,String> loadLibraries(){
		if (instance!=null){
			if (instance.settingsReader!=null){
				return instance.settingsReader.loadLibraries();
			}
			return instance.loadCommandLibraries();
		}
		return null;
	}



	private void initSingleApp(String singleAppFile) throws Exception{
		printHeader();
		applicationGenerator = new ApplicationGenerator();
		Application newApplication = new Application();
		newApplication.setName("UNKnown");
		
		SettingsVariablesMapper mapper = loadMapper();
		if ((mapper!=null)&&(mapper.getVariablesNumber()>0)){
			String xmlDocText = org.smyld.io.FileSystem.readStringFile(singleAppFile);
			String result = processEL(xmlDocText, mapper);
			//System.out.println("Processing result \n" + result);
			newApplication.setSourceStream(new StringBufferInputStream(result));
		}else{
			newApplication.setSourceFile(singleAppFile);
		}

		//TODO injection of the application reader should take place to update the below
		
		PESwingApplicationReader reader =  getApplicationReader(newApplication);
		/*
		SettingsVariablesMapper mapper = loadMapper();
		if ((mapper!=null)&&(mapper.getVariablesNumber()>0)){
			reader.setVariableMapper(mapper); 
		}
		*/
		newApplication.setAppReader(reader);
		applicationGenerator.generateApplication(newApplication);
	}


	private SettingsVariablesMapper loadMapper(){
		// Handling the command line variables
		SettingsVariablesMapper mapper = new SettingsVariablesMapper();
		if (args.length>2){
			boolean loadVarValue = false;
			String  varName = null;
			for(String curArg:args){
				
				if (curArg.toLowerCase().startsWith(ARG_PREFIX_VAR)){
					varName = curArg.substring(ARG_PREFIX_VAR.length());
					loadVarValue = true;
				}else if (loadVarValue){
					mapper.addVariable(varName, curArg);
					System.out.println("Adding the variable : " + varName + " , holding the value : " + curArg);
					loadVarValue = false;
				}
			}
		}
		// Handling additional builders variables
		if(projectBuilder.getSource()==ProjectBuildSource.Maven){

			mapper.addVariable("groupId", ((MavenProjectBuilder)projectBuilder).getGroup());
			//mapper.addVariable("artifactId", ((MavenProjectBuilder)projectBuilder).get);
		}
		return mapper;
	} 
	private HashMap<String,String> loadCommandLibraries(){
		if (args.length>2){
			HashMap<String,String> libs = new HashMap<String,String>();
			String  libPath = null;
			int libCounter = 1;
			for(String curArg:args){
				
				if (curArg.toLowerCase().startsWith(ARG_PREFIX_LIB)){
					libPath = curArg.substring(ARG_PREFIX_LIB.length());
					String libName = "lib_" + libCounter++; 
					int pathIndex = libPath.lastIndexOf(File.separator);
					if (pathIndex!=-1){
						libName = libPath.substring(pathIndex+1);
					}
					System.out.println("Adding library (" + libName + ") holding the location (" + libPath + ")");
					libs.put(libName, libPath);
					
				}
			}
			return libs;
		}
		return null;
	} 
	
	private void init() {
		try {
			logFile = settingsReader.getLogFile();
			// FileSystem.confirmFoldersExistence(logFile.getFilePath());
			
			if (logFile!=null){
				String fileLog = logFile.getFilePath() + File.separator
						+ logFile.getFileName();
				if ((logFile.getFilePath() != null)
						&& (logFile.getFileName() != null)) {
					File targetLogFile = FileSystem.createFileWithPath(logFile
							.getFilePath(), logFile.getFileName());
					FileSystem.redirectSysOutput(targetLogFile);
				}
			}
			printHeader();
			Vector<Application> apps = settingsReader.loadApplications();
			if (apps != null) {
				ApplicationGenerator appGenerator = new ApplicationGenerator();
				for (Application curApp : apps) {
					curApp.setAppReader(getApplicationReader(curApp));
					appGenerator.generateApplication(curApp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void printHeader(){
		System.out.println("*************************************************************");
		System.out.println(RunAppVersion.getVersionText(
				PORTAL_ENIGINE_NAME, PORTAL_ENIGINE_VERSION));
		System.out.println(new Date().toString());
		System.out.println(System.getProperty("java.version"));
		System.out.println("*************************************************************\n");
	}

	private PESwingApplicationReader getApplicationReader(Application targetApplication)
			throws Exception {
		PESwingApplicationReader appReader = null;
		// The code for determining whether to load the application from an XML
		// file
		// or from the data base
		if (targetApplication.getDbSource() != null) {
			SMYLDOracleConnection oraConn = new SMYLDOracleConnection(
					SMYLDOracleConnection.DRIVER_THIN, targetApplication
							.getDbSource());
			appReader = new PortalDBReaderPESwing(this, oraConn);
			((PortalDBReaderPESwing) appReader).readApplication(targetApplication
					.getName());
		} else {
			if (targetApplication.getSourceFile()!=null)
				appReader = new PortalAppXMLSetReaderPESwing(targetApplication.getSourceFile(),projectBuilder);
			else if (targetApplication.getSourceStream()!=null)
				appReader = new PortalAppXMLSetReaderPESwing(targetApplication.getSourceStream(),projectBuilder);
		}
		return appReader;
	}

	public static void main(String[] args) {
		RunAppVersion appVersion = new RunAppVersion(PORTAL_ENIGINE_NAME,
				PORTAL_ENIGINE_VERSION);
		if (!appVersion.processVersion(args)) {
			// Code for starting the portal engine since the call not for the
			// version
			if (args[0].equals(RUN_ARG_SETTING_FILE)) {
				String settingsFile = args[1];
				if (settingsFile != null) {
					try {
						org.smyld.util.ProcessTimer timer = new ProcessTimer();
						timer.startTimeTest("checkTime");
						new PortalManager(new PotalEngineXMLSetReader(
								settingsFile));
						System.out.println(timer.endTimeTest("checkTime"));
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}else if (args[0].equals(RUN_ARG_APP_FILE)) {
				
				String appFile = args[1];
				if (appFile != null) {
					try {
						org.smyld.util.ProcessTimer timer = new ProcessTimer();
						timer.startTimeTest("Process Time");
						new PortalManager(appFile,args);
						System.out.println(timer.endTimeTest("Process Time"));
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
	
			}
		}
	}
	public String processEL(String value,SettingsVariablesMapper elMapper){
		
		if ((elMapper!=null)&&(value!=null)){
			StringBuilder sb = new StringBuilder(value);
			int start = 0;
			int end   = 0;
			while((start = value.indexOf("${",end+1))!=-1){
				end = value.indexOf("}",start+2);
				if (end!=-1){
					String elVariable = value.substring(start+2,end);
					String varValue =  elMapper.map(elVariable);
					if (varValue!=null){
						sb.replace(start, end+1, varValue);
						value = sb.toString();
						//System.out.println(" --- document variable (" + elVariable + ") found");
						//System.out.println(" --- document variable (" + elVariable + "), resultant value : " + sb.toString());
					}
				}
			}
			return value;
		}
		return null;
	}
	public ApplicationGenerator getApplicationGenerator(){return this.applicationGenerator;}

	public boolean addError(Exception e, Connection c) {
		return false;
	}
	
	// -setting /home/jamil/workspace/PortalEngine/docs/portal_settings.xml
	private static final String ARG_PREFIX_VAR = "-var:";
	private static final String ARG_PREFIX_LIB = "-lib:";
}
