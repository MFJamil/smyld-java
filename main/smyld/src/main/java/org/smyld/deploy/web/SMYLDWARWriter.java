package org.smyld.deploy.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.smyld.util.jar.SMYLDJARWriter;

/**
 * This class will create a war file that comply with the web standards for any
 * normal war file and can be deployed on the application as well as web server
 */
public class SMYLDWARWriter extends SMYLDJARWriter {
	protected WebFileWriter webSettingsFile;

	public SMYLDWARWriter(OutputStream out) throws IOException {
		super(out);
		init();
	}

	public SMYLDWARWriter(String outName) throws IOException {
		super(outName);
		init();
	}

	private void init() {
		webSettingsFile = new WebFileWriter();
	}

	public void compose() {
		try {
			addXMLFile(webSettingsFile.getRootElement(), PATH_WEB
					+ webSettingsFile.getFileName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dumpFile() throws IOException {
		compose();
		close();
	}

	public void addRefLibrary(String libraryName) throws Exception {
		// The library must be added to the proper folder
		if (libraryName == null) {
			if (!libraryName.toLowerCase().endsWith("jar"))
				throw new Exception("Wrong library name!");
			File libFile = new File(libraryName);
			if (libFile.exists())
				addFile(libraryName, PATH_LIB + libFile.getName());
		}
	}

	public WebFileWriter getWebSettingsFile() {
		return webSettingsFile;
	}

	public void setWebSettingsFile(WebFileWriter webSettingsFile) {
		this.webSettingsFile = webSettingsFile;
	}

	public static final String PATH_LIB = "WEB-INF/lib/";
	public static final String PATH_WEB = "WEB-INF/";

}
