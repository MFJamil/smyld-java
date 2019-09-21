package com.smyld.gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class SMYLDFileChooser extends JFileChooser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMYLDFileChooser() {
	}

	public SMYLDFileChooser(File currentDirectory) {
		super(currentDirectory);
	}

	/**
	 * Constructs a <code>JFileChooser</code> using the given
	 * <code>FileSystemView</code>.
	 */
	public SMYLDFileChooser(FileSystemView fsv) {
		super(fsv);
	}

	/**
	 * Constructs a <code>JFileChooser</code> using the given current
	 * directory and <code>FileSystemView</code>.
	 */
	public SMYLDFileChooser(File currentDirectory, FileSystemView fsv) {
		super(currentDirectory, fsv);
	}

	/**
	 * Constructs a <code>JFileChooser</code> using the given current
	 * directory path and <code>FileSystemView</code>.
	 */
	public SMYLDFileChooser(String currentDirectoryPath, FileSystemView fsv) {
		super(currentDirectoryPath, fsv);
	}

	public SMYLDFileChooser(String currentDirectoryPath) {
		super(currentDirectoryPath);
	}

}
