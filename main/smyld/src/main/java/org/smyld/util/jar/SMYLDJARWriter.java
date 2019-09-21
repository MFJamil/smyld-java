package org.smyld.util.jar;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarException;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.CRC32;

import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import org.smyld.io.FileNavigationListener;
import org.smyld.io.FilesNavigator;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class SMYLDJARWriter extends JarOutputStream {
	HashMap<String,JarEntry> entries;
	boolean printDuplicateEntries;
	String fileName;

	/**
	 * 
	 * @see
	 * @since
	 */
	public SMYLDJARWriter(OutputStream out) throws IOException {
		super(out);
		entries = new HashMap<String,JarEntry>();
		setComment("Created by SMYLD JAR API's utilities \n" + " at "
				+ new Date().toString());
	}

	public SMYLDJARWriter(String outName) throws IOException {
		super(new FileOutputStream(outName));
		fileName = outName;
		entries = new HashMap<String,JarEntry>();
		setComment("Created by SMYLD JAR API's utilities \n" + " at "
				+ new Date().toString());
	}

	// public void addFileToStream(String entryName,

	/**
	 * This method will copy all the contents of the given library into the
	 * current jar stream object
	 */
	public void addLibrary(String jarLibPath) throws JarException,
			FileNotFoundException, IOException {
		CRC32 crc = new CRC32();
		byte[] data = null;
		JarInputStream jin = new JarInputStream(new FileInputStream(jarLibPath));
		JarEntry curInt = null;
		loop: while ((curInt = jin.getNextJarEntry()) != null) {
			crc.reset();
			long activeSize = 0;
			try {
				//System.out.println("++ Adding Entry +++++++++++ " + curInt.getName());
				
				if ((curInt.getName()!=null)&&(curInt.getName().equals("META-INF/MANIFEST.MF"))) {
					// Do not allow to copy the manifest from the library, so that we keep the main jar file with its manifest file
				}else{
					
					putNextEntry(curInt);
					int curSize = 0;
					data = new byte[1024];
					while ((curSize = jin.read(data)) != -1) {
						activeSize += curSize;
						crc.update(data, 0, curSize);
						curInt.setSize(activeSize);
						curInt.setCrc(crc.getValue());
						write(data, 0, curSize);
					}
				}
				closeEntry();

			} catch (JarEntryAlreadyExist e) {
				if (isPrintDuplicateEntries())
					System.out.println("Duplicate Entry : "
							+ e.getJarEntry().getName());
			}
		}
	}

	public void putNextEntry(JarEntry newEntry) throws IOException,
			JarEntryAlreadyExist {
		if (entries.containsKey(newEntry.getName()))
			throw new JarEntryAlreadyExist(newEntry);
		else
			entries.put(newEntry.getName(), newEntry);
		super.putNextEntry(newEntry);
	}

	public void addFile(String sourceFile, String targetFileName)
			throws JarException, FileNotFoundException, IOException,
			JarEntryAlreadyExist {
		FileInputStream fin = new FileInputStream(sourceFile);
		addStreamFile(fin, targetFileName);
	}

	public void addXMLFile(Element rootNode, String targetFileName)
			throws JarException, FileNotFoundException, IOException,
			JarEntryAlreadyExist {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		new XMLOutputter().output(rootNode, outStream);
		ByteArrayInputStream in = new ByteArrayInputStream(outStream
				.toByteArray());
		addStreamFile(in, targetFileName);
	}

	public void addStreamFile(InputStream in, String targetFileName)
			throws JarException, FileNotFoundException, IOException,
			JarEntryAlreadyExist {
		long entrySize = 0;
		targetFileName = correctTargetEntryName(targetFileName);
		JarEntry newFile = new JarEntry(targetFileName);

		putNextEntry(newFile);
		byte[] data = new byte[1024];
		int size = -1;
		while ((size = in.read(data)) != -1) {
			entrySize += size;
			write(data, 0, size);
		}
		in.close();

		closeEntry();
	}

	private String correctTargetEntryName(String entryName) {
		StringBuffer curBuf = new StringBuffer(entryName.length());
		for (int i = 0; i < entryName.length(); i++) {
			if (entryName.charAt(i) == '\\')
				curBuf.append('/');
			else
				curBuf.append(entryName.charAt(i));
		}
		return curBuf.toString();

	}

	public void createManifestFromAttribute(Attributes manAttributes)
			throws IOException, JarEntryAlreadyExist {
		createManifestFromAttribute(null, manAttributes);
	}

	public void createManifestFromAttribute(String manitFestFileName,
			Attributes manAttributes) throws IOException, JarEntryAlreadyExist {
		if (manitFestFileName == null)
			manitFestFileName = "MANIFEST";
		manitFestFileName += ".MF";
		JarEntry manEntr = new JarEntry("META-INF/" + manitFestFileName);
		putNextEntry(manEntr);
		String lineSep = System.getProperty("line.separator");
		Iterator<Object> itr = manAttributes.keySet().iterator();
		while (itr.hasNext()) {
			// System.out.println(itr.next().getClass().getName());
			String attKey = ((Attributes.Name) itr.next()).toString();
			String attValue = manAttributes.getValue(attKey);
			String entry = attKey + ": " + attValue + lineSep;
			write(entry.getBytes());
		}
		closeEntry();
	}

	public void addFilesInFolder(final String targetBaseDir,
			final File folderPath, FileFilter filesFilter) throws IOException,
			FileNotFoundException {
		FilesNavigator navigator = new FilesNavigator();
		navigator.addNavigationListener(new FileNavigationListener() {
			public void fileFound(File newFile) {
				// System.out.println("Extract Result : " + );
				String targetFileName = extractPackagePath(newFile.getPath(),
						folderPath.getPath());
				if (targetBaseDir != null)
					targetFileName = targetBaseDir + "/" + targetFileName;
				try {
					// if (targetFileName!=null)
					// addFile(newFile.getPath(),targetFileName);
					if ((!newFile.isDirectory()) && (targetFileName != null))
						addFile(newFile.getPath(), targetFileName);
					else if (newFile.isDirectory()) {
						// JarEntry dirEntry = new JarEntry(targetFileName);
						// putNextEntry(dirEntry);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		navigator.navigate(folderPath, filesFilter);

	}

	public void addFilesInFolder(final File folderPath, FileFilter filesFilter)
			throws IOException, FileNotFoundException {
		addFilesInFolder(null, folderPath, filesFilter);
	}

	protected String extractPackagePath(String filePath, String homePath) {
		String targetPath = null;
		int startIndex = filePath.indexOf(homePath);
		if (startIndex != -1)
			targetPath = filePath.substring(startIndex + homePath.length() + 1);
		return targetPath;
	}

	public boolean isPrintDuplicateEntries() {
		return printDuplicateEntries;
	}

	public void setPrintDuplicateEntries(boolean printDuplicateEntries) {
		this.printDuplicateEntries = printDuplicateEntries;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
