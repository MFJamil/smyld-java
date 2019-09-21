package org.smyld.gui.panels;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JScrollPane;

import org.smyld.SMYLDObject;

public class DockableComponent extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JComponent component;
	String title;
	ImageIcon icon;
	boolean showTabIcon;
	boolean showTabTitle;
	JScrollPane scroller;

	public DockableComponent() {
	}

	public DockableComponent(JComponent newComponent, String title,
			ImageIcon icon, boolean showTabText, boolean showTabIcon) {
		this(newComponent, title, icon);
		setShowTabIcon(showTabIcon);
		setShowTabTitle(showTabText);

	}

	public DockableComponent(JComponent newComponent, String title,
			ImageIcon icon) {
		setComponent(newComponent);
		setTitle(title);
		setIcon(icon);
		init();

	}

	public void init() {
		component.setBorder(BorderFactory.createEmptyBorder());
		scroller = new JScrollPane(component);
		scroller.setBorder(BorderFactory.createEmptyBorder());
	}

	public JComponent getComponent() {
		return component;
	}

	public void setComponent(JComponent component) {
		this.component = component;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public boolean isShowTabIcon() {
		return showTabIcon;
	}

	public void setShowTabIcon(boolean showIcon) {
		this.showTabIcon = showIcon;
	}

	public boolean isShowTabTitle() {
		return showTabTitle;
	}

	public void setShowTabTitle(boolean showTabTitle) {
		this.showTabTitle = showTabTitle;
	}

	@Override
	public boolean equals(Object compareObject) {
		if (compareObject instanceof DockableComponent) {
			return ((DockableComponent) compareObject).getComponent().equals(
					getComponent());
		}
		if (compareObject instanceof JComponent) {
			return ((JComponent) compareObject).equals(getComponent());
		}

		return false;
	}

	public JScrollPane getScroller() {
		return scroller;
	}

	public void setScroller(JScrollPane scroller) {
		this.scroller = scroller;
	}

}
