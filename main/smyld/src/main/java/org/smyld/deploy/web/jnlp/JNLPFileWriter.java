package org.smyld.deploy.web.jnlp;

import java.util.Vector;

import org.jdom2.Element;

import org.smyld.xml.XMLFileWriter;

public class JNLPFileWriter extends XMLFileWriter implements JNLPConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String codeBase;
	String name;
	String mainJarFile;

	Vector<ResourceDescriptor> resources;
	Vector<AppDescriptor> applications;
	Vector<InfoDescriptor> infoDescriptors;

	InfoDescriptor defInfo;
	AppDescriptor defApp;
	ResourceDescriptor defRes;
	SecurityDescriptor defSec;

	public JNLPFileWriter() {
		init();

	}

	private void init() {
		rootElement = new Element(TAG_NAME_ROOT);
		rootElement.setAttribute(TAG_ATT_SPEC, ATT_SPEC_DEFAULT);
		defInfo = new InfoDescriptor();
		defApp = new AppDescriptor();
		defRes = new ResourceDescriptor();
		defSec = new SecurityDescriptor();

	}

	@Override
	protected void compose() {
		// Writing the root element
		rootElement.setAttribute(TAG_ATT_CODE_BASE, getCodeBase());
		rootElement.setAttribute(TAG_ATT_REF, getName());
		// Adding the main information section
		rootElement.addContent(defInfo.getXMLElement());
		rootElement.addContent(defRes.getXMLElement());
		rootElement.addContent(defApp.getXMLElement());
		rootElement.addContent(defSec.getXMLElement());

	}

	public String getCodeBase() {
		if (codeBase == null)
			return ATT_CODEBASE_DEFAULT;
		return codeBase;
	}

	public void setCodeBase(String codeBase) {
		this.codeBase = codeBase;
	}

	public String getMainJarFile() {
		return mainJarFile;
	}

	public void setMainJarFile(String MainJarFile) {
		mainJarFile = MainJarFile;
	}

	public String getName() {
		if (name == null)
			return ATT_NAME_DEFAULT;
		if (!name.endsWith(".jnlp"))
			return name + ".jnlp";
		return name;
	}

	public void grantAllPermissions() {
		defSec.addAllPermissions();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return defInfo.getTitle();
	}

	public void setTitle(String title) {
		defInfo.setTitle(title);
	}

	public String getVendor() {
		return defInfo.getVendor();
	}

	public void setVendor(String vendor) {
		defInfo.setVendor(vendor);
	}

	public String getHomePage() {
		return defInfo.getHomePage();
	}

	public void setHomePage(String homePage) {
		defInfo.setHomePage(homePage);
	}

	public String getDescription() {
		return defInfo.getDescription();
	}

	public void setDescription(String description) {
		defInfo.setDescription(description);
	}

	public String getJ2seVersion() {
		return defRes.getJ2seVersion();
	}

	public void setJ2seVersion(String j2seVersion) {
		defRes.setJ2seVersion(j2seVersion);
	}

	public Vector<ResourceDescriptor> getResources() {
		return resources;
	}

	public Vector<AppDescriptor> getApplications() {
		return applications;
	}

	public void addInfoDescriptor(InfoDescriptor newInfo) {
		if (infoDescriptors==null) infoDescriptors= new Vector<InfoDescriptor>();
		infoDescriptors.add(newInfo);
		
	}

	public void addAppDescriptor(AppDescriptor newApp) {
		if (applications==null) applications = new Vector<AppDescriptor>();
		applications.add(newApp);
	}

	public void addInfoDescriptor(ResourceDescriptor newRes) {
		if(resources==null) resources = new Vector<ResourceDescriptor>();
		resources.add(newRes);
	}

	public void addJarFile(String JarName) {
		addJarFile(JarName, false, ATT_DOWNLOAD_EAGER);
	}

	public void addJarFile(String JarName, boolean withMainClass) {
		addJarFile(JarName, withMainClass, ATT_DOWNLOAD_EAGER);
		if (withMainClass)
			setMainJarFile(JarName);
	}

	public void addJarFile(String JarName, String downloadType) {
		addJarFile(JarName, false, downloadType);
	}

	public void addJarFile(String JarName, boolean withMainClass,
			String downloadType) {
		JARDescriptor newJar = new JARDescriptor();
		newJar.setContainsMain(withMainClass);
		newJar.setRef(JarName);
		newJar.setDownloadType(downloadType);
		defRes.addJAR(newJar);
	}

	public Vector<InfoDescriptor> getInfoDescriptors() {
		return infoDescriptors;
	}

	public String getMainClass() {
		return defApp.getMainClass();
	}

	public void setMainClass(String mainClass) {
		defApp.setMainClass(mainClass);
	}

	public String getTooltip() {
		return defInfo.getTooltip();
	}

	public void setTooltip(String tooltip) {
		defInfo.setTooltip(tooltip);
	}

	public void addShortCut(boolean firesApplicationOnline,
			boolean creatInDesktop, String menuTitle) {
		ShortcutDescriptor newShort = new ShortcutDescriptor();
		newShort.setDesktop(creatInDesktop);
		newShort.setOnline(firesApplicationOnline);
		newShort.setMenu(menuTitle);
		defInfo.setShortCut(newShort);
	}

	public String getIcon() {
		return defInfo.getIcon();
	}

	public void setIcon(String icon) {
		defInfo.setIcon(icon);
	}

	public boolean isOfflineAllowed() {
		return defInfo.isOfflineAllowed();
	}

	public void setOfflineAllowed(boolean offlineAllowed) {
		defInfo.setOfflineAllowed(offlineAllowed);
	}

	public void importDescriptor(JNLPDeploymentDescriptor desc) {
		setCodeBase(desc.getCodeBase());
		setDescription(desc.getDescription());
		setIcon(desc.getIcon());
		// setFileName(desc.getName());
		setName(desc.getName());
		setOfflineAllowed(desc.isOfflineAllowed());
		setTitle(desc.getTitle());
		setTooltip(desc.getTooltip());
		setVendor(desc.getVendor());
		setMainClass(desc.getMainClass());
		if (desc.getShortcut() != null)
			defInfo.setShortCut(desc.getShortcut());

	}

}
