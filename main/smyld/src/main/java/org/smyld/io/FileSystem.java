package org.smyld.io;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.HashMap;

import org.smyld.resources.FileInfo;
import org.smyld.run.SMYLDProcessUtility;
import org.smyld.sys.SMYLDSystem;
import org.smyld.sys.SystemConstants;
import org.smyld.util.jar.SMYLDJARWriter;

public class FileSystem {
	private File currentFile;
	static HashMap<String,String> images; // For listener access purpose
	static HashMap<String,String> envs;

	public FileSystem() {
	}

	// Process drives
	/**
	 * Searches the hard drive recursively for the given file name, and returns
	 * the first file found with the given name
	 * 
	 * @param fileName
	 *            the name of the file to be found
	 */
	public File findFileOnHardDrive(String fileName) {
		File[] f = File.listRoots();
		for (int i = 0; i < f.length; i++) {
			File drive = f[i];
			System.out.println("Currently searching path:" + drive.getPath());
			searchAllDirs(drive, fileName);
		}
		return currentFile;
	}

	// Process directories in a drive
	private void searchAllDirs(File dir, String fileName) {
		if (currentFile != null)
			return;
		if (dir.isDirectory()) {
			searchAllFiles(dir, fileName);
			String[] children = dir.list();
			if (children != null) {
				for (int i = 0; i < children.length; i++) {
					searchAllDirs(new File(dir, children[i]), fileName);
				}
			}
		}
	}

	/*
	 * Joining the path name with the file name taking into consideration the
	 * possibility that the path could end with slash for any platform
	 */
	public static String joinFilePath(String path, String fileName) {
		if ((path.endsWith("\\")) || (path.endsWith("/")))
			return path + fileName;
		return path + File.separator + fileName;
	}

	// Process files in a directory
	private void searchAllFiles(File dir, String fileName) {
		if (currentFile != null)
			return;
		if (dir.isDirectory() && !dir.isHidden()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				searchAllFiles(new File(dir, children[i]), fileName);
			}
		} else if (dir.getName().equals(fileName)) {
			currentFile = dir;
		}
	}

	public static void writeFile(String filename, String content, boolean append) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filename,
					append));
			out.write(content);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File createFileWithPath(String filePath, String fileName) {
		if (confirmFoldersExistence(filePath)) {
			return new File(filePath + File.separator + fileName);
		}
		return null;
	}

	public static void redirectSysOutput(File f) {
		try {
			PrintStream printStream = new PrintStream(new BufferedOutputStream(
					new FileOutputStream(f, true)), true);
			redirectSysOutput(printStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void redirectSysOutput(PrintStream printStr) {
		try {
			// PrintStream printStream = new PrintStream(new
			// BufferedOutputStream(new FileOutputStream(f,true)), true);
			System.setErr(printStr);
			System.setOut(printStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean deleteFile(String filePath) {
		return (new File(filePath)).delete();
	}

	// Copying the given destination file to the given target file
	public static void copyFile(File originalFile, File tartgetFile)
			throws IOException {
		if (originalFile.isDirectory()) {
			new File(tartgetFile.getPath() + File.separator
					+ originalFile.getName()).mkdir();
		} else {
			FileInputStream fin = new FileInputStream(originalFile);
			copyFile(fin, tartgetFile);
		}
	}

	public static void copyFile(InputStream originalFile, File tartgetFile)
			throws IOException {
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(tartgetFile);
			byte[] data = new byte[256];
			int dataLength = 0;
			while ((dataLength = originalFile.read(data)) != -1) {
				if (dataLength < 256)
					fout.write(data, 0, dataLength);
				else
					fout.write(data);
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			originalFile.close();
			fout.close();
		}
	}

	public static String readStringFile(String filepath) {
		return readStringFile(new File(filepath)).toString();
	}

	// Reads the given file as string and return string buffer with the contents
	public static StringBuffer readStringFile(File targetFile) {
		StringBuffer resultData = new StringBuffer();
		try {
			LineNumberReader bin = new LineNumberReader(new FileReader(
					targetFile));
			String curLine = bin.readLine();
			while (curLine != null) {
				resultData.append(curLine + NEW_LINE);
				curLine = bin.readLine();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (resultData.length() > 0)
			return resultData;
		return null;
	}

	public static boolean moveFile(String sourceFileName,
			String destFullFileName) {
		try {
			File sourceFile = new File(sourceFileName);
			if (sourceFile.exists()) {
				if (sourceFile.isFile()) {
					return sourceFile.renameTo(new File(destFullFileName));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	// Counting the folders exist under the given path
	public static int getFoldersCount(File parentFile) {
		int countResult = 0;
		if (parentFile.isDirectory()) {
			File[] childFiles = parentFile.listFiles();
			if (childFiles.length > 0)
				for (int count = 0; count < childFiles.length; count++)
					if (childFiles[count].isDirectory())
						countResult++;
		}
		return countResult;
	}

	public static boolean confirmFoldersExistence(String path) {
		File testFile = new File(path);
		if (!testFile.exists())
			return testFile.mkdirs();
		return true;
	}

	public static FileInfo parseFileInfo(String path) {
		FileInfo newFile = new FileInfo();

		File test = new File(path);
		if (test.exists()) {
			if (test.isFile()) {
				newFile.setFileName(test.getName());
				newFile.setFilePath(test.getParent());
			} else if (test.isDirectory()) {
				newFile.setFilePath(test.getPath());
			}
		} else {
			int posSL = path.lastIndexOf("/");
			int posBL = path.lastIndexOf("\\");
			int pos = posSL;
			if (pos == -1)
				pos = posBL;
			if (pos != -1) {
				newFile.setFilePath(path.substring(0, pos));
				newFile.setFileName(path.substring(pos + 1));
			} else {
				newFile = null;
			}
		}
		return newFile;
	}

	public static HashMap<String,String> loadImagesInFolder(String dir, HashMap<String,String> source) {
		if (source != null)
			images = source;
		else
			images = new HashMap<String,String>();
		FilesNavigator navigator = new FilesNavigator();
		navigator.addNavigationListener(new FileNavigationListener() {
			public void fileFound(File newImage) {
				images.put(newImage.getName(), newImage.getPath());
			}
		});
		File imageDir = new File(dir);
		if ((imageDir != null) && (imageDir.isDirectory()))
			try {
				navigator.navigate(imageDir, new FileFilter() {
					public boolean accept(File testFile) {
						String fileExt = testFile
								.getName()
								.toLowerCase()
								.substring(
										testFile.getName().lastIndexOf(".") + 1);
						return (IMG_TYPES.indexOf(fileExt) != -1);
					}
				});
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		return images;
	}

	public static HashMap<String,String> getEnvironmentVariables() throws Exception {
		int os = SMYLDSystem.getOperatingSystem();
		String envCommand = null;
		switch (os) {
		case SystemConstants.OS_WINDOWS_NT:
		case SystemConstants.OS_WINDOWS_2000:
		case SystemConstants.OS_WINDOWS_XP:
			envCommand = "cmd.exe /c set";
			break;
		case SystemConstants.OS_WINDOWS_95:
		case SystemConstants.OS_WINDOWS_98:
			envCommand = "command.com /c set";
			break;
		default:
			envCommand = "env";
			break;
		}
		Process p = Runtime.getRuntime().exec(envCommand);
		parseEnv(SMYLDProcessUtility.getResponse(p));
		return envs;
	}

	public static String getEnvVariable(String variableName) {
		String envValue = null;
		try {
			HashMap<String,String> envs = getEnvironmentVariables();
			if (envs != null) {
				envValue =  envs.get(variableName);
				// oraclePath += File.separator + "network" + File.separator +
				// "admin" + File.separator + "tnsnames.ora";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return envValue;
	}

	private static void parseEnv(String envValue) {
		envs = new HashMap<String,String>();
		LineNumberReader reader = new LineNumberReader(new StringReader(
				envValue));
		String curLine = null;
		try {
			while ((curLine = reader.readLine()) != null) {
				String varName = curLine.substring(0, curLine.indexOf("="));
				String varValue = curLine.substring(curLine.indexOf("=") + 1);
				envs.put(varName, varValue);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void copyDirectory(String dirName, File targetDirectory)
			throws Exception {
		File dir = new File(dirName);
		if (!dir.isDirectory())
			throw new Exception("No Directory supplied!");
		File[] list = dir.listFiles();
		for (int i = 0; i < list.length; i++) {
			if (list[i].isDirectory()) {
				copyFile(list[i], targetDirectory);
				copyDirectory(list[i].getPath(), new File(targetDirectory
						.getPath()
						+ File.separator + list[i].getName()));
			} else {
				copyFile(list[i], new File(targetDirectory + File.separator
						+ list[i].getName()));
			}
		}

	}

	public static String getExtention(String fileName) {
		int extPos = fileName.lastIndexOf('.');
		if (extPos != -1) {
			String ext = fileName.substring(extPos + 1);
			return ext;
		}
		return null;
	}

	public static String getExtention(File file) {
		return getExtention(file.getName());
	}

	public static void compressDirectory(String dirName,
			String targetCompressName) throws Exception {
		File dirFile = new File(dirName);
		if (dirFile.isDirectory()) {
			File[] list = dirFile.listFiles();
			if ((list != null) && (list.length > 0)) {
				SMYLDJARWriter writer = new SMYLDJARWriter(targetCompressName);
				writer.addFilesInFolder(dirFile, null);
				writer.close();
			}
		}
	}

	public static final String NEW_LINE = System.getProperty("line.separator");
	private static final String IMG_TYPES = "jpg,gif,png,bmp";
}
