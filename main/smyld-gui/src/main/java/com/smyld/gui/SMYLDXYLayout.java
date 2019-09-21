package com.smyld.gui;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.HashMap;

import com.smyld.SMYLDObject;
import com.smyld.gui.layout.SMYLDXYConstraints;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class SMYLDXYLayout extends SMYLDObject implements LayoutManager2 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3321184658198901341L;
	Container parentContainer;
	Insets borders;
	Dimension componentArea;
	HashMap<Component, Object> components;
	ComponentOrientation compOrient;

	/**
	 * 
	 * @see
	 * @since
	 */
	public SMYLDXYLayout() {
		components = new HashMap<Component, Object>();
		compOrient = ComponentOrientation.LEFT_TO_RIGHT;
	}

	public void removeLayoutComponent(Component comp) {
		// System.out.println("removeLayoutComponent");
		components.remove(comp);
	}

	public Dimension preferredLayoutSize(Container parent) {
		// System.out.println("preferredLayoutSize");
		// return maximumLayoutSize(parent);
		return getComponentsArea();
	}

	public void setComponentOrientation(ComponentOrientation orient) {
		compOrient = orient;
	}

	public ComponentOrientation getComponentOrientation() {
		return compOrient;
	}

	public Dimension minimumLayoutSize(Container parent) {
		// System.out.println("minimumLayoutSize = " + this.getClass().getName()
		// );

		return getComponentsArea();
	}

	private Dimension getComponentsArea() {

		int smallWidth = 0;
		int bigWidth = 0;
		int smallHeight = 0;
		int bigHeight = 0;
		for (Object curObject : components.values()) {
			SMYLDXYConstraints currPos = (SMYLDXYConstraints) curObject;
			if (currPos.getX() < smallWidth) {
				smallWidth = currPos.getX();
			}
			if ((currPos.getX() + currPos.getWidth()) > bigWidth) {
				bigWidth = currPos.getX() + currPos.getWidth();
			}
			if (currPos.getY() < smallHeight) {
				smallHeight = currPos.getY();
			}
			if ((currPos.getY() + currPos.getHeight()) > bigHeight) {
				bigHeight = currPos.getY() + currPos.getHeight();
			}
		}
		return new Dimension(bigWidth - smallWidth, bigHeight - smallHeight);

	}

	public void layoutContainer(Container parent) {
		synchronized (parent.getTreeLock()) {
			// System.out.println("layoutContainer");
			parentContainer = parent;
			borders = parentContainer.getInsets();
			for (Component currCmp : components.keySet()) {
				SMYLDXYConstraints currPos = (SMYLDXYConstraints) components
						.get(currCmp);
				allignComponent(currPos);
				currCmp.setBounds(allignComponent(currPos), currPos.getY(),
						currPos.getWidth(), currPos.getHeight());
			}
		}
	}

	public void addLayoutComponent(Component comp, Object constraints) {
		// System.out.println("addLayoutComponent(Component comp, Object
		// constraints)");
		if (constraints instanceof SMYLDXYConstraints) {
			SMYLDXYConstraints xyConst = (SMYLDXYConstraints) constraints;
			components.put(comp, xyConst);
		}
	}

	private int allignComponent(SMYLDXYConstraints constraints) {
		int result = constraints.getX();
		if (!compOrient.isLeftToRight()) {
			result = parentContainer.getWidth() - constraints.getX()
					- constraints.getWidth();
		}
		return result;
	}

	public Dimension maximumLayoutSize(Container target) {
		// System.out.println("maximumLayoutSize(Container target)");
		return getComponentsArea();
	}

	public float getLayoutAlignmentX(Container target) {
		// System.out.println("getLayoutAlignmentX");
		return 0.5f;
	}

	public float getLayoutAlignmentY(Container target) {
		// System.out.println("getLayoutAlignmentY");
		return 0.5f;
	}

	public void invalidateLayout(Container target) {
		// System.out.println("invalidateLayout");
	}

	public void addLayoutComponent(String name, Component comp) {
	}
}
