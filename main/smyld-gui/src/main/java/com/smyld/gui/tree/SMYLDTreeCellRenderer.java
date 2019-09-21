package com.smyld.gui.tree;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

import com.smyld.SMYLDObject;
import com.smyld.gui.SMYLDLabel;

public class SMYLDTreeCellRenderer extends SMYLDObject implements TreeCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Color selBGColor;
	Color selFGColor;
	Color norBGColor;
	Color norFGColor;

	SMYLDTreeNode currentNode;

	public SMYLDTreeCellRenderer() {
		init();
	}

	private void init() {
		norBGColor = Color.white;
		norFGColor = Color.black;
		selBGColor = new Color(0, 0, 150, 20);
		selFGColor = Color.blue;
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		SMYLDLabel currentNode = ((SMYLDTreeNode) value).getGuiItem();
		if (selected) {
			currentNode.setBackground(getSelBGColor());
			currentNode.setForeground(getSelFGColor());
		} else {
			currentNode.setBackground(getNorBGColor());
			currentNode.setForeground(getNorFGColor());
		}
		currentNode.setSize(0, 0);
		currentNode.repaint();
		// System.out.println("CellRender.getTree = " + ((SMYLDTreeNode)
		// value).getText());
		// Integer.parseInt("2ewe");
		return currentNode;
	}

	public Color getNorBGColor() {
		return norBGColor;
	}

	public void setNorBGColor(Color newNorBGColor) {
		norBGColor = newNorBGColor;
	}

	public Color getNorFGColor() {
		return norFGColor;
	}

	public void setNorFGColor(Color newNorFGColor) {
		norFGColor = newNorFGColor;
	}

	public Color getSelBGColor() {
		return selBGColor;
	}

	public void setSelBGColor(Color newSelBGColor) {
		selBGColor = newSelBGColor;
	}

	public Color getSelFGColor() {
		return selFGColor;
	}

	public void setSelFGColor(Color newSelFGColor) {
		selFGColor = newSelFGColor;
	}

}
