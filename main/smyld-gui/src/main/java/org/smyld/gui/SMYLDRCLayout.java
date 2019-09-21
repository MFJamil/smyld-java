package org.smyld.gui;

import java.awt.Component;

import javax.swing.UIManager;

import org.smyld.gui.layout.SMYLDRCConstraints;
import org.smyld.gui.layout.SMYLDXYConstraints;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class SMYLDRCLayout extends SMYLDXYLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see
	 * @since
	 */
	public SMYLDRCLayout() {
		topMargin = y_margin;
		specifyHeight();
	}
	private void specifyHeight(){
		//if (UIManager.getLookAndFeel()!=null) System.out.println("SYSTEM L&F IS (" + UIManager.getLookAndFeel().getName() +")");
		if ((UIManager.getLookAndFeel()!=null)&&(UIManager.getLookAndFeel().getName().toLowerCase().equals("nimbus")))
			y_ratio = 25;
		else
			y_ratio = 20;
		
	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		synchronized (comp.getTreeLock()) {
			specifyHeight();
			SMYLDRCConstraints rcConst = (SMYLDRCConstraints) constraints;
			if (rcConst != null) {
				int newX = rcConst.getColumn() * x_ratio;
				int newY = ((rcConst.getRow() - 1) * y_gap)
						+ ((rcConst.getRow() - 1) * y_ratio) + topMargin;
				int newW = rcConst.getWidth() * x_ratio;
				int newH = rcConst.getHeight() * y_ratio;
				super.addLayoutComponent(comp, new SMYLDXYConstraints(newX, newY,
						newW, newH));
			}
		}
	}

	public void setYRatio(int newValue) {
		y_ratio = newValue;
	}

	public void setYGap(int newValue) {
		y_gap = newValue;
	}

	// Constants definition
	public static final int x_ratio = 12;
	public static int y_ratio = 20;
	public static int y_gap = 5;
	public static final int y_margin = 35;
	int topMargin;

	public int getTopMargin() {
		return topMargin;
	}

	public void setTopMargin(int topMargin) {
		this.topMargin = topMargin;
	}

}
