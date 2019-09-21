package com.smyld.bw.util;

import java.io.File;

import com.smyld.io.SMYLDFileFilter;

public class BWFileFilter extends SMYLDFileFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String filePatternMask;
	String[] filePatternMasks;
	StringBuffer buffer;

	public BWFileFilter(String BWFilePattern, boolean InformOnce) {
		super(BWFilePattern, InformOnce);
		buffer = new StringBuffer();
		createMask();
	}

	public BWFileFilter(String[] BWFilePatterns, boolean InformOnce) {
		super(BWFilePatterns, InformOnce);
		buffer = new StringBuffer();
		createMasks();
	}

	private void createMasks() {
		filePatternMasks = new String[getFilePatterns().length];
		for (int i = 0; i < getFilePatterns().length; i++)
			filePatternMasks[i] = convertToMask(getFilePatterns()[i]);
	}

	private void createMask() {
		filePatternMask = convertToMask(getFilePattern());
	}

	private String convertToMask(String pattern) {
		buffer.setLength(0);
		boolean insidePattern = false;
		for (int i = 0; i < pattern.length(); i++) {
			char currentChar = pattern.charAt(i);
			if (currentChar == '<')
				insidePattern = true;
			else if (currentChar == '>')
				insidePattern = false;
			else if (insidePattern)
				buffer.append('|');
			else
				buffer.append(currentChar);
		}
		return buffer.toString();

	}

	@Override
	public boolean accept(File dir, String name) {
		if (filePatternMask != null) {
			return match(name, filePatternMask);
		} else if (filePatternMasks != null) {
			for (int i = 0; i < filePatternMasks.length; i++)
				if (match(name, filePatternMasks[i])) {
					addAcceptedFile(name, getFilePatterns()[i]);
					return true;
				}
		}
		return false;
	}

	private boolean match(String name, String mask) {
		if (name.length() == mask.length()) {
			for (int i = 0; i < name.length(); i++) {
				char maskChar = mask.charAt(i);
				if (maskChar != '|')
					if (maskChar != name.charAt(i))
						return false;
			}
			return true;
		}
		return false;
	}

}
