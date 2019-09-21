package org.smyld.gui.portal.engine;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.smyld.SMYLDObject;
import org.smyld.gui.portal.engine.sources.PESwingApplicationReader;
import org.smyld.io.FileSystem;
import org.smyld.lang.script.java.JavaClassBody;
import org.smyld.lang.script.java.JavaLangUtility;
import org.smyld.run.SMYLDProcessUtility;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class AppCompiler extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Application activeApplication;
	PESwingApplicationReader appReader;

	/**
	 * 
	 * @see
	 * @since
	 */
	public AppCompiler() {
	}

	public void setActiveApplication(Application newApplication) {
		activeApplication = newApplication;
		appReader = newApplication.getAppReader();
	}

	public void compileApplication() throws Exception {
		String mainClassTarget = appReader.getClassPath();
		// Creating the classes folder in case it does not exist
		if (FileSystem.confirmFoldersExistence(mainClassTarget)) {
			Runtime rt = Runtime.getRuntime();
			String sourcePath = " -sourcepath " + appReader.getSourcePath();
			String targetClassPath = " -d " + mainClassTarget;
			//String targetClassPath = "";
			String compileClasses = getCompileClassesCommandParameter();
			String command = "javac" + sourcePath + getLibraryRefCommand()
					+ targetClassPath + compileClasses;
			System.out.println("Executing Command : " + command + "\n\n\n");
			Process result = rt.exec(command);
			//result.getOutputStream().write("jamil".getBytes());
			//result.getOutputStream().flush();
			String errCode = SMYLDProcessUtility.getErrorCode(result);
			if (errCode != null) {
				System.out.println(errCode);
			}

		}
	}

	private String getLibraryRefCommand() {
		Vector<String> libs = activeApplication.getUsedLabraries();
		String pathSep = System.getProperty("path.separator");
		//System.out.println("Path Separator : " + pathSep);
		if ((libs != null) && (libs.size() > 0)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(" -classpath ");
			
			buffer.append(appReader.getClassPath() + pathSep);
			//*
			Iterator<String> itr = activeApplication.getUsedLabraries().iterator();
			while (itr.hasNext()) {
				String currentLib = itr.next();
				buffer.append(currentLib);
				if (itr.hasNext()) {
					buffer.append(pathSep);
				}
			}
			//*/
			//buffer.append("/home/jamil/workspace/SMYLDInterfaceSimulator/lib/*.jar");	
			buffer.append(" ");
			return buffer.toString();
		}
		return "";
	}

	private String getCompileClassesCommandParameter() throws Exception {
		// Creating a single line of classes that need to be compiled
		StringBuffer compileClasses = new StringBuffer(" ");
		for (JavaClassBody curClass : activeApplication.getGeneratedClasses().values()) {
			String sourceClassPath = appReader.getSourcePath() + File.separator
					 +JavaLangUtility.convertPackageToPath(curClass
							.getPackageName()) + File.separator
					+ curClass.getName() + ".java";
			compileClasses.append(sourceClassPath);
			compileClasses.append(" ");
		}
		return compileClasses.toString();
	}

	public PESwingApplicationReader getAppReader() {
		return appReader;
	}

	void compileApplication(HashMap<String,JavaClassBody> classesCollection) throws Exception {
		String mainClassTarget = appReader.getClassPath();
		if (FileSystem.confirmFoldersExistence(mainClassTarget)) {
			Runtime rt = Runtime.getRuntime();
			for (JavaClassBody curClass  : classesCollection.values()) {
				String sourceClassPath = appReader.getSourcePath()
						+ File.separator
						+ JavaLangUtility.convertPackageToPath(curClass
								.getPackageName()) + File.separator
						+ curClass.getName() + ".java";
				String targetClassPath = " -d " + mainClassTarget;
				String command = "javac " + getLibraryRefCommand()
						+ sourceClassPath + targetClassPath;
				// System.out.println("javac",params);
				Process result = rt.exec(command);
				String errCode = SMYLDProcessUtility.getErrorCode(result);
				if (errCode != null) {
					System.out.println(errCode);
				}
			}
		}
	}

}
