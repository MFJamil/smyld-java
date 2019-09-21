package com.smyld.net;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Vector;

import com.smyld.SMYLDObject;
import com.smyld.io.FileSystem;
import com.smyld.net.web.ItemLink;

public class WebCopier extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String siteURL;
	String baseURL;
	String targetPath;
	boolean withImages;
	boolean allResources;
	Vector<ItemLink> pageReferences;
	Vector<ItemLink> items;
	String currentPage;
	StringBuffer buffer = new StringBuffer();
	int indexMove = 0;

	public WebCopier() {

	}

	public void copyWebSite(String siteURL, String targetPath,
			boolean withImages, boolean allResources) {
		this.siteURL = siteURL;
		this.targetPath = targetPath;
		this.withImages = withImages;
		this.allResources = allResources;
		pageReferences = new Vector<ItemLink>();
		items = new Vector<ItemLink>();
		setBaseURL();
		startProcess();
	}

	private void startProcess() {
		currentPage = InternetUtil.getPageContents(siteURL);
		buffer.setLength(0);
		indexMove = 0;
		buffer.append(currentPage);
		resolveItems(items, "src=\"");
		resolveItems(pageReferences, "href=\"");
		processPageItems();
		processPageRef();
		try {
			FileOutputStream out = new FileOutputStream(targetPath
					+ "index.html");
			out.write(buffer.toString().getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("hi");
	}

	private void setBaseURL() {
		// String lastElement = siteURL.substring(siteURL.lastIndexOf("/",10));
		baseURL = siteURL;
		String lastElement = siteURL;
		if (siteURL.startsWith("http://"))
			lastElement = siteURL.substring(7);
		if (lastElement.lastIndexOf("/") != -1) {
			lastElement = lastElement.substring(lastElement.indexOf("/"));
			if (lastElement.indexOf(".") != -1)
				baseURL = siteURL.substring(0, siteURL.lastIndexOf("/"));
		}
	}

	private void resolveItems(Vector<ItemLink> collection, String key) {
		int newKeyIndex = 0;
		while ((newKeyIndex = currentPage.indexOf(key, newKeyIndex + 1)) != -1) {
			int keyEndIndx = currentPage.indexOf("\"", newKeyIndex
					+ key.length() + 1);
			if (keyEndIndx != -1) {
				String value = currentPage.substring(
						newKeyIndex + key.length(), keyEndIndx);
				ItemLink newItem = new ItemLink();
				newItem.setName(value);
				newItem.setStartIndex(newKeyIndex + key.length());
				newItem.setEndIndex(keyEndIndx);
				collection.add(newItem);
			}
		}
	}

	private void processPageItems() {
		for (ItemLink item : items) {
			if (item != null)
				processItem(item);
		}

	}

	private void processPageRef() {
		for (ItemLink item : pageReferences) {
			if (item != null)
				processItem(item);
		}

	}

	private void processItem(ItemLink item) {
		if (item.getName().indexOf("/") != -1) {
			String folder = targetPath
					+ File.separator
					+ item.getName().substring(0,
							item.getName().lastIndexOf("/"));
			if (FileSystem.confirmFoldersExistence(folder)) {
				String realSource = getRealSource(item);
				InternetUtil.savePageItem(realSource, targetPath
						+ item.getName());

			}
		}
	}

	private String getRealSource(ItemLink item) {
		if (item.getName().startsWith("/")) {
			buffer = buffer.replace(item.getStartIndex() - indexMove, item
					.getEndIndex()
					- indexMove, item.getName().substring(1));
			indexMove++;
			if (baseURL.indexOf("/", 8) != -1)
				return baseURL.substring(0, baseURL.indexOf("/", 8))
						+ item.getName();
			else
				return baseURL + item.getName();
		}
		return baseURL + "/" + item.getName();
	}

}
