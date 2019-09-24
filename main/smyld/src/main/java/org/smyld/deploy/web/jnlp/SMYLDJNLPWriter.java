package org.smyld.deploy.web.jnlp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.smyld.deploy.web.SMYLDWARWriter;
import org.smyld.security.SMYLDKey;
import org.smyld.util.SMYLDJavaTools;
import org.smyld.util.jar.SMYLDJARWriter;

public class SMYLDJNLPWriter extends SMYLDWARWriter implements JNLPConstants {
	SMYLDKey signKey;
	String mainJarFile;
	JNLPFileWriter jnlpFile;
	JNLPHTMLDocument htmlDoc;
	boolean generateLaunchPage;

	public SMYLDJNLPWriter(OutputStream out) throws IOException {
		super(out);
		init();
	}

	public SMYLDJNLPWriter(String outName) throws IOException {
		super(outName);
		init();
	}

	private void init() {
		jnlpFile = new JNLPFileWriter();
		webSettingsFile.addJNLPMappings();

	}

	public void signJarFile(SMYLDKey signingKey) {
		signKey = signingKey;
	}

	public void addMainJarFile(String MainJarFile, SMYLDKey signingKey) {
		signKey = signingKey;
		addMainJarFile(MainJarFile);
	}

	public void addMainJarFile(String MainJarFile) {
		mainJarFile = MainJarFile;
	}

	private void handelSecuritySigniture() throws Exception {
		if (mainJarFile != null) {
			/*
			 * Code for copying the file to the system because it will never
			 * recognize it as the copy in JNLP-INF folder
			 */
			File tempFile = new File(mainJarFile + ".tmp");
			String jnlpTempFilePath = tempFile.getParent() + File.separator;
			jnlpFile.setFileName(jnlpFile.getName());
			jnlpFile.writeFileToPath(jnlpTempFilePath);
			jnlpTempFilePath += jnlpFile.getName();
			// Creating a temp jar file that holds the jnlp copy inside
			SMYLDJARWriter writer = new SMYLDJARWriter(new FileOutputStream(
					tempFile));
			writer.addLibrary(mainJarFile);
			writer.addFile(jnlpTempFilePath, JNLP_SIGN_DEF_NAME);
			writer.close();
			addFile(jnlpTempFilePath, jnlpFile.getName());
			// Sigining the file with the provided key
			SMYLDJavaTools tools = new SMYLDJavaTools();
			boolean result = tools.signJarFile(tempFile.getPath(), signKey);
			if (result) {
				System.out.println("Finished siging the file .... ");
				addFile(tempFile.getPath(), FOLDER_APPS
						+ new File(mainJarFile).getName());
				// Deleting the temp files
				tempFile.delete();
				// boolean delresult = FileSystem.deleteFile(jnlpTempFilePath);
				// System.out.println(delresult);
			}

		}
	}

	private void addJarFileToPackage(String jarFileSource) throws Exception {
		File testJar = new File(jarFileSource);
		if (testJar.exists()) {
			addFile(testJar.getPath(), FOLDER_APPS + testJar.getName());
		}
	}

	@Override
	public void compose() {
		try {
			if (signKey != null) {
				handelSecuritySigniture();
			} else {
				addJarFileToPackage(mainJarFile);
				addXMLFile(jnlpFile.getRootElement(), jnlpFile.getName());
			}
			if (isGenerateLaunchPage()) {
				if (htmlDoc == null)
					htmlDoc = new JNLPHTMLDocument(jnlpFile);
				addXMLFile(htmlDoc.getRootElement(), HTML_FILE_NAME);
			}
			super.compose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JNLPFileWriter getJNLPFile() {
		return jnlpFile;
	}

	public boolean isGenerateLaunchPage() {
		return generateLaunchPage;
	}

	public void setGenerateLaunchPage(boolean generateLaunchPage) {
		this.generateLaunchPage = generateLaunchPage;
	}

	public void importDescriptor(JNLPDeploymentDescriptor desc) {
		jnlpFile.importDescriptor(desc);
		if (desc.getHtmlDocument() != null) {
			try {
				htmlDoc = new JNLPHTMLDocument(jnlpFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			htmlDoc.setTitle(desc.getHtmlDocument().getTitle());
			htmlDoc.setHeadline(desc.getHtmlDocument().getHeadline());
			setGenerateLaunchPage(true);
		}

	}

}
