package org.smyld.resources;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;

import org.smyld.SMYLDObject;
import org.smyld.io.FileSystem;

public class Resource extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unchecked")
	Class resourceClass;
	
	private static Resource instance;

	public Resource() {
		resourceClass = this.getClass();
	}

	public Resource(String ClassPath) throws ClassNotFoundException {
		resourceClass = Class.forName(ClassPath);
	}
	
	public static Resource getInstance(String ClassPath) throws ClassNotFoundException {
		if (instance==null)
			instance = new Resource(ClassPath);
		return instance;
		
	}
	public static Resource getInstance() {
		if (instance==null)
			instance = new Resource();
		return instance;
		
	}

	/* Returning the resource needed */
	public URL getResource(String resourceName) {
		return resourceClass.getResource(resourceName);
	}

	public InputStream getResourceInputStream(String resourceName) {
		return resourceClass.getResourceAsStream(resourceName);
	}

	public File getFile(String resourceName) throws IOException,
			URISyntaxException {
		URL resURL = getResource(resourceName);
		StringBuffer buffer = new StringBuffer(resURL.getFile());
		/*
		 * System.out.println("Before : " + buffer.toString());
		 * 
		 * buffer = buffer.deleteCharAt(resURL.getFile().indexOf("!"));
		 * System.out.println("After : " + buffer.toString());
		 */

		return new File(new URI(buffer.toString()));
	}

	/*
	 * Creating an image out of the provided image name (if it exists in the
	 * package)
	 */
	public ImageIcon getImageIcon(String imageName) {
		URL imageURL = getResource(imageName);
		if (imageURL != null)
			return new ImageIcon(imageURL);
		return null;
	}

	public Image getImage(String imageName) {
		return getImageIcon(imageName).getImage();
	}

	public void exportResourceFile(String fileName, String targetPath)
			throws Exception {
		String targetFile = targetPath + File.separator + fileName;
		File newFile = new File(targetFile);
		FileSystem.copyFile(getResourceInputStream(fileName), newFile);
	}

}
