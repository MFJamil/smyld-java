package org.smyld;

import java.io.File;
import java.io.FileFilter;

import org.smyld.util.SMYLDDate;
import org.smyld.util.jar.SMYLDJARWriter;

public class Tester04 {
	public Tester04() {
		try {
			// startProjectsExport();
			//testSoundAPI();
			//exportSinglePreprocessProject(
			//		"D:/projects/JDev/clientProjects/Omnipay/OmnipayPreprocess",
			//		"1.4.89", "PP_Omnipay");
			// exportSinglePreprocessProject("D:/projects/JDev/clientProjects/RiyadBank/RBPreprocess","1.4.73","PP_RB");
			// exportSinglePreprocessProject("D:/projects/JDev/clientProjects/EDB/EDBPreprocess","1.1.1","PP_EDB");
			// exportSinglePreprocessCustomerProject("D:/projects/releases/work/1.4.70D","OmnipayPreprocess","1.4.70F","PP_Omnipay");
			// exportSinglePreprocessCustomerProject("D:/projects/releases/work/1.3.61","OmnipayPreprocess","1.3.61B","PP_Omnipay");
			// exportSinglePreprocessCustomerProject("D:/projects/releases/work/1.3.61","OmnipayPreprocess","1.3.61C","PP_Omnipay");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Tester04();
	}
	@SuppressWarnings("unused")
	private void startProjectsExport() throws Exception {
		// Loop through target compressed files
		SMYLDDate today = new SMYLDDate(SMYLDDate.FRM_yyyyMMdd);
		String todayDate = today.toString();
		String targetExportDir = "d:/projects/releases/";
		// String[] prjsShared =
		// {"D:/projects/sharedProjects/BWPreprocess","D:/projects/sharedProjects/SMYLD","D:/projects/sharedProjects/SMYLDTransFormats"};
		// String[] prjsClient =
		// {"D:/projects/clientProjects/Omnipay/OmnipayPreprocess","D:/projects/clientProjects/RiyadBank/RBPreprocess","D:/projects/clientProjects/BSF/BSFPreprocess"};
		String[] prjsShared = new String[3];
		prjsShared[0] = "D:/projects/JDev/sharedProjects/SMYLD";
		prjsShared[1] = "D:/projects/JDev/sharedProjects/SMYLDTransFormats";
		prjsShared[2] = "D:/projects/JDev/sharedProjects/BWPreprocess";
		String[] prjsClient = new String[3];
		prjsClient[0] = "D:/projects/JDev/clientProjects/Omnipay/OmnipayPreprocess";
		prjsClient[1] = "D:/projects/JDev/clientProjects/RiyadBank/RBPreprocess";
		prjsClient[2] = "D:/projects/JDev/clientProjects/BSF/BSFPreprocess";

		createTarget(targetExportDir + todayDate + "_shared_projects.zip",
				prjsShared);
		createTarget(targetExportDir + todayDate + "_client_projects.zip",
				prjsClient);

	}

	private void exportSinglePreprocessProject(String preprocessProjectName,
			String version, String cusName) throws Exception {
		SMYLDDate today = new SMYLDDate(SMYLDDate.FRM_yyyyMMdd);
		String todayDate = today.toString();
		String targetExportDir = "d:/projects/releases/";
		String[] prjsShared = new String[4];
		prjsShared[0] = "D:/projects/JDev/sharedProjects/SMYLD";
		prjsShared[1] = "D:/projects/JDev/sharedProjects/SMYLDTransFormats";
		prjsShared[2] = "D:/projects/JDev/sharedProjects/BWPreprocess";
		prjsShared[3] = preprocessProjectName;
		/*
		 * String[] prjsClient = new String[3]; prjsClient[0] =
		 * "D:/projects/JDev/clientProjects/Omnipay/OmnipayPreprocess";
		 * prjsClient[1] =
		 * "D:/projects/JDev/clientProjects/RiyadBank/RBPreprocess";
		 * prjsClient[2] = "D:/projects/JDev/clientProjects/BSF/BSFPreprocess";
		 */
		createTarget(targetExportDir + cusName + "_vr_" + version + "_dtd_"
				+ todayDate + ".zip", prjsShared);
		// createTarget(targetExportDir + todayDate +
		// "_client_projects.zip",prjsClient);

	}
	@SuppressWarnings("unused")
	private void exportSinglePreprocessCustomerProject(String baseDir,
			String preprocessProjectName, String version, String cusName)
			throws Exception {
		SMYLDDate today = new SMYLDDate(SMYLDDate.FRM_yyyyMMdd);
		String todayDate = today.toString();
		String targetExportDir = "d:/projects/releases/";
		String[] prjsShared = new String[4];
		prjsShared[0] = baseDir + "/SMYLD";
		prjsShared[1] = baseDir + "/SMYLDTransFormats";
		prjsShared[2] = baseDir + "/BWPreprocess";
		prjsShared[3] = baseDir + "/" + preprocessProjectName;
		/*
		 * String[] prjsClient = new String[3]; prjsClient[0] =
		 * "D:/projects/JDev/clientProjects/Omnipay/OmnipayPreprocess";
		 * prjsClient[1] =
		 * "D:/projects/JDev/clientProjects/RiyadBank/RBPreprocess";
		 * prjsClient[2] = "D:/projects/JDev/clientProjects/BSF/BSFPreprocess";
		 */
		createTarget(targetExportDir + cusName + "_vr_" + version + "_dtd_"
				+ todayDate + ".zip", prjsShared);
		// createTarget(targetExportDir + todayDate +
		// "_client_projects.zip",prjsClient);

	}

	private void createTarget(String targetFileName, String[] sourceProjects)
			throws Exception {
		// Create zip file
		SMYLDJARWriter writer = new SMYLDJARWriter(targetFileName);
		// loop through the source projects
		for (String element : sourceProjects) {
			addProject(writer, element);
		}
		writer.close();
	}

	private void addProject(SMYLDJARWriter writer, String sourceProject)
			throws Exception {
		File curProjDir = new File(sourceProject);
		// */
		FileFilter prjFilter = new FileFilter() {
			public boolean accept(File curFile) {
				String fileName = curFile.getName().toLowerCase();
				// if ((curFile.isDirectory())&&(fileName.endsWith("src")))
				// return true;
				if (curFile.isDirectory()) {
					return false;
				}
				return filterProjectFiles(fileName);
			}
		};
		String srcPath = curProjDir.getPath() + "/src";
		String targetSrc = curProjDir.getName() + "/src";

		writer.addFilesInFolder(targetSrc, new File(srcPath), new FileFilter() {
			public boolean accept(File curFile) {
				return true;
			}
		});
		// *
		File[] rootFiles = curProjDir.listFiles(prjFilter);
		for (File element : rootFiles) {
			writer.addFile(element.getPath(), curProjDir.getName() + "/"
					+ element.getName());
		}
		// */

	}
	/*
	private void testSoundAPI(){
		try {
			//Clip soundClip = javax.sound.sampled.AudioSystem.getClip();
			//soundClip.open(AudioSystem.getAudioInputStream(new File("D:/downloads/Quran/Fatiha.mp3")));
			MediaLocator locator = new MediaLocator("file:D:/downloads/Quran/Fatiha.mp3");
			Player player = Manager.createRealizedPlayer(locator);
			
			player.setMediaTime(new javax.media.Time(5d));
			player.start();
			player.setStopTime(new javax.media.Time(9d));
			//javax.sound.sampled.spi.

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//javax.sound.sampled.AudioSystem.getAudioFileFormat(new File("")).getFormat().
	}
	*/

	private boolean filterProjectFiles(String fileName) {
		// *
		return ((fileName.endsWith(".deploy")) || (fileName.endsWith(".java")) || (fileName
				.endsWith(".jpr")));
		// */
		// return (fileName.endsWith(".java"));

	}

}
