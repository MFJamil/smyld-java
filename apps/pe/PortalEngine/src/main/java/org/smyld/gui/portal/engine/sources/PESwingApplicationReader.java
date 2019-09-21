package org.smyld.gui.portal.engine.sources;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Vector;

import org.smyld.app.pe.logging.LogFile;
import org.smyld.app.pe.model.ApplicationReader;
import org.smyld.app.pe.model.gui.GUIToolbar;
import org.smyld.app.pe.projectbuilder.ProjectBuilder;
import org.smyld.app.pe.security.AppSecurity;
import org.jdom2.Element;

import org.smyld.deploy.DeploymentDescriptor;
import org.smyld.app.pe.model.gui.MenuItem;
import org.smyld.app.pe.model.gui.PEAction;
import org.smyld.resources.FileInfo;
import org.smyld.resources.LookAndFeelResource;
import org.smyld.util.multilang.LangSource;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public interface PESwingApplicationReader extends ApplicationReader {
	public HashMap<String, String>              loadLibraries();
	public HashMap<String, Element>             loadWindows();
	public HashMap<String, Element>             loadPanels();
	public HashMap<String, PEAction>            loadActions();
	public HashMap<String, MenuItem>            loadMenus();
	public HashMap<String, GUIToolbar>          loadToolbars();
	public HashMap<String, LangSource>          loadLanguages();
	public HashMap<String, String>              loadImages();
	public HashMap<String, String>              loadSourceImages();
	public HashMap<String, String>              loadResources();
	public HashMap<String, LookAndFeelResource> loadLookAndFeels();
	public Vector<DeploymentDescriptor>         loadDeployments();
	
	public String getAppStartupClass();
	public String getAppName();
	public String getMainClassPackage();
	public String getGroup();
	public String getHomePath();
	public String getSourcePath();
	public InputStream getSourceStream();
	public String getClassPath();
	public String getComponentType();
	public String getLAF();
	public String getTargetJarName();
	public String getTargetJarPath();
	public boolean createJarFile();
	public boolean compileClasses();
	public boolean exportLibraries();
	public boolean containsPanelID(String panelID);
	public String getAppManagerClass();
	public LogFile getLogFile();
	public FileInfo getAppSettingsFile();
	public FileInfo getAppJarFile();
	public String getAppSettingsSourceType();
	public AppSecurity getAppSecurity();
	public String getAppType();
	public Element getSplash();
	public void setVariableMapper(SettingsVariablesMapper mapper);
	public ProjectBuilder getProjectBuilder();
}
