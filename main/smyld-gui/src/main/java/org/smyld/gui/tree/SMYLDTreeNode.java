package org.smyld.gui.tree;

import java.awt.ComponentOrientation;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.tree.TreeNode;

import org.smyld.SMYLDObject;
import org.smyld.gui.GUIAction;
import org.smyld.gui.SMYLDButton;
import org.smyld.gui.SMYLDLabel;
import org.smyld.gui.SMYLDMenuClass;
import org.smyld.gui.SMYLDPopupMenu;
import org.smyld.gui.event.ActionHandler;

public class SMYLDTreeNode extends SMYLDObject implements TreeNode,SMYLDMenuClass
// public class SMYLDTreeNode extends DefaultMutableTreeNode implements TreeNode
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2187133357488282035L;
	Vector<SMYLDTreeNode> items;
	String               nodeLable;
	String               nodeID;
	String               ActionCommand;
	String               toolTipText;
	ActionHandler actHandler;  
	GUIAction     guiAction;
	ImageIcon     nodeIcon;
	SMYLDLabel     guiItem;
	Object        userObject;
	SMYLDPopupMenu popUp;
	private int[] visibles;
	
	// static final long serialVersionUID = 8616898850580367665L;
	boolean visible       = true;
	boolean enabled       = true;
	boolean allowChildren = true;
	public boolean debug  = false;

	ComponentOrientation cmpOrient;
	SMYLDTreeNode         parent;
	SMYLDTreeModel        model;
	int                  level;
	
	// Constructors
	public SMYLDTreeNode() {
	}

	public SMYLDTreeNode(String ID, String NodeLable) {
		this();
		nodeID = ID;
		nodeLable = NodeLable;
	}

	public SMYLDTreeNode(String ID, String NodeLable, SMYLDObject UserObject) {
		this(ID, NodeLable);
		userObject = UserObject;
	}

	public SMYLDTreeNode(String ID, boolean AllowChildren, String NodeLable) {
		this(ID, NodeLable);
		allowChildren = AllowChildren;
	}

	public SMYLDTreeNode(String ID, String NodeLable, ImageIcon NodeIcon) {
		this(ID, NodeLable);
		nodeIcon = NodeIcon;
	}

	public SMYLDTreeNode(String ID, String NodeLable, String tooltipText,
			ImageIcon NodeIcon) {
		this(ID, NodeLable);
		this.toolTipText = tooltipText;
		nodeIcon = NodeIcon;
	}

	public SMYLDTreeNode(String ID, String NodeLable, ImageIcon NodeIcon,
			String Action) {
		this(ID, NodeLable, NodeIcon);
		setActionCommand(Action);
	}

	public SMYLDTreeNode(GUIAction itemAction) {
		this(itemAction.getID(), itemAction.getLabel());
		guiAction = itemAction;
	}

	public SMYLDTreeNode(GUIAction itemAction, String ID, String NodeLabel,
			ImageIcon NodeIcon) {
		this(ID, NodeLabel, NodeIcon);
		guiAction = itemAction;
	}

	public SMYLDTreeNode(GUIAction itemAction, String ID, String NodeLabel) {
		this(ID, NodeLabel);
		guiAction = itemAction;
	}

	public SMYLDTreeNode(GUIAction itemAction, String ID) {
		this(ID, itemAction.getLabel());
		guiAction = itemAction;
	}

	public SMYLDTreeNode(String ID, ImageIcon NodeIcon, GUIAction itemAction) {
		this(ID, itemAction.getLabel(), NodeIcon);
		guiAction = itemAction;
	}

	public void add(SMYLDMenuClass menuClass) {
		if (menuClass==null) return;
		if (menuClass instanceof SMYLDTreeNode){
			add((SMYLDTreeNode)menuClass);
		}
	}
	public int add(SMYLDTreeNode newChildNode) {
		setChildInfo(newChildNode);
		if (items.add(newChildNode)) {
			return items.indexOf(newChildNode);
		}
		return -1;
		// newChildNode.get
	}
	public void insertNodeInto(SMYLDTreeNode newChildNode,int index){
		setChildInfo(newChildNode);
		items.add(index, newChildNode);

	}
	private void setChildInfo(SMYLDTreeNode newChildNode){
		if (items == null) {
			items = new Vector<SMYLDTreeNode>();
		}
		newChildNode.setParent(this);
		newChildNode.setLevel(getLevel() + 1);
		if (getModel() != null) {
			newChildNode.setModel(getModel());
		}
		
	}

	public TreeNode[] getPathToRoot() {
		if (getLevel() < 0) {
			return null;
		}
		int pathLength = getLevel() + 1;
		TreeNode[] path = new TreeNode[pathLength];
		SMYLDTreeNode curNode = this;
		for (int i = 0; i < pathLength; i++) {
			path[pathLength - 1 - i] = curNode;
			curNode = (SMYLDTreeNode) curNode.getParent();

		}
		return path;
	}

	public void setPopupMenu(SMYLDPopupMenu menu) {
		popUp = menu;
		
		if (guiItem == null) {
			createGUI();
		}
	}
	
	public TreeNode getChildAt(int childIndex) {

		SMYLDTreeNode target = null;
		if (items != null) {
			if (items.size() > childIndex) {
				target = items.get(childIndex);
			}
		}
		// doPrint(getText() + ".getChildAt("+ childIndex +") : Object(" +
		// target.getText() + ")");
		return target;
	}

	public TreeNode getChild(String nodeID) {
		for (Enumeration<SMYLDTreeNode> enums = children(); enums
				.hasMoreElements();) {
			SMYLDTreeNode curNode = enums.nextElement();
			if ((curNode.getNodeID() != null)
					&& (curNode.getNodeID().equals(nodeID))) {
				return curNode;
			}
		}
		return null;
	}

	public int getChildCount() {

		int count = 0;
		if (items != null) {
			count = items.size();
		}
		// doPrint(getText() + ".getChildCount() : int (" + count + ")");
		return count;
	}

	/* @TODO: Needs review so that the parent node can be returned */
	public TreeNode getParent() {
		@SuppressWarnings("unused")
		String parentText = "null";
		if (parent != null) {
			parentText = (parent).getText();
		}
		// doPrint(getText() + ".getParent() : TreeNode (" + parentText + ")");
		return parent;
		// System.out.println("TreeNode.getParent() called for node (" +
		// getText() +")");
	}

	public int getIndex(TreeNode node) {
		int index = items.indexOf(node);
		// doPrint(getText()+ ".getIndex(" + ((SMYLDTreeNode)node).getText() + ")
		// : int (" + index +") ");
		return index;
	}

	public boolean getAllowsChildren() {
		return allowChildren;
	}

	public ImageIcon getNodeImage() {
		return nodeIcon;
	}

	public void setNodeImage(ImageIcon NodeIcon) {
		nodeIcon = NodeIcon;
	}

	public String getText() {

		return getGuiItem().getText();
	}

	public void setText(String newLabel) {
		nodeLable = newLabel;
		if (guiItem != null) {
			guiItem.setText(newLabel);
			refreshGUI();
		}
	}

	private void refreshGUI() {
		guiItem.refresh();
		if (model!=null) model.nodeChanged(this);

	}

	public void remove(int childIndex) {
		SMYLDTreeNode child = (SMYLDTreeNode) getChildAt(childIndex);
		items.removeElementAt(childIndex);
		child.setParent(null);
	}

	public void removeChildren() {
		if (items != null) {
			
			items.removeAllElements();
			int[] indices = new int[items.size()];
			for(int i=0;i<items.size();i++) indices[i] =i;
			model.nodesWereRemoved(this, indices, items.toArray());
		}
	}

	public boolean isLeaf() {
		return (getChildCount() == 0);
	}

	public Enumeration<SMYLDTreeNode> children() {
		// doPrint(getText() + ".children() : Enum(" + items.size() + ")");
		if (items != null) {
			return items.elements();
		}
		return null;
	}

	public String getNodeID() {
		return nodeID;
	}

	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
		if (guiItem != null) {
			guiItem.setText(nodeID);
			refreshGUI();
		} else {
			createGUI();
		}
	}

	public SMYLDLabel getGuiItem() {
		if (guiItem == null) {
			createGUI();
		}
		return guiItem;
	}

	private void createGUI() {
		guiItem = new SMYLDLabel(nodeLable, getNodeImage());
		guiItem.setOpaque(true);
		guiItem.setEnabled(enabled);
		guiItem.setToolTipText(getToolTipText());
		if (cmpOrient != null) {
			guiItem.applyComponentOrientation(cmpOrient);
		}

	}

	public String getActionCommand() {
		return ActionCommand;
	}

	public SMYLDPopupMenu getPopupMenu() {
		return popUp;
	}

	public void setActionCommand(String newActionCommand) {
		ActionCommand = newActionCommand;
	}

	/*
	 * public SMYLDObject getUserObject() { return userObject; }
	 */

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean newVisible) {
		visible = newVisible;
		this.getGuiItem().setVisible(visible);
	}

	public void applyComponentOrientation(ComponentOrientation o) {
		cmpOrient = o;
		if (guiItem != null) {
			guiItem.applyComponentOrientation(cmpOrient);
		}
	}

	/**
	 * REturns a hashmap containing the children of this node with label as key
	 */
	public HashMap<String, SMYLDTreeNode> getChildrenByLabel() {
		if (!allowChildren) {
			return null;
		}
		HashMap<String, SMYLDTreeNode> retval = new HashMap<String, SMYLDTreeNode>();
		for (int i = 0; i < items.size(); i++) {
			SMYLDTreeNode node = items.get(i);
			retval.put(node.getText(), node);
		}
		return retval;
	}

	public TreeNode getChildAt(int index, boolean filterIsActive) {

		if (!filterIsActive) {
			return getChildAt(index);
		}
		if (items == null) {
			throw new ArrayIndexOutOfBoundsException("node has no children");
		}
		int realindex = visibles[index];
		return getChildAt(realindex);
	}

	public int getChildCount(boolean filterIsActive) {
		if (!filterIsActive) {
			return getChildCount();
		}
		if (items == null) {
			return 0;
		}
		int count = 0;
		visibles = new int[getChildCount()];
		for (SMYLDTreeNode node : items) {
			if (node.isVisible()) {
				visibles[count] = this.getIndex(node);
				count++;
			}
		}
		return count;
	}

	public GUIAction getGUIAction() {
		return guiAction;
	}

	public void setIcon(ImageIcon nodeIcon) {
		this.nodeIcon = nodeIcon;
		if (guiItem != null) {
			guiItem.setIcon(nodeIcon);
			refreshGUI();
		} else {
			createGUI();
		}
	}

	public ImageIcon getIcon() {
		return nodeIcon;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;

	}

	public String getToolTipText() {
		return toolTipText;
	}

	public void setTooltipText(String toolTipText) {

		this.toolTipText = toolTipText;
		if (guiItem != null) {
			guiItem.setToolTipText(toolTipText);
			refreshGUI();
		} else {
			createGUI();
		}

	}

	public void setParent(SMYLDTreeNode parent) {
		this.parent = parent;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@SuppressWarnings("unused")
	private void doPrint(String text) {
		if (debug) {
			System.out.println(text);
		}
	}

	public Object getUserObject() {
		return userObject;
	}

	public void setUserObject(Object userObject) {
		this.userObject = userObject;
	}

	public SMYLDTreeModel getModel() {
		return model;
	}

	public void setModel(SMYLDTreeModel model) {
		this.model = model;
		if (getChildCount() > 0) {
			for (SMYLDTreeNode curNode : items) {
				curNode.setModel(model);
			}
		}
	}

	public void setGUIAction(GUIAction newAction) {
		this.guiAction = newAction;
	}

	
	
	
	public JMenuItem getParentClass() {
		// TODO Auto-generated method stub
		return null;
	}

	public SMYLDButton getToolbarButton() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAccelerator(String newAccelerator) {
		// TODO Auto-generated method stub
		
	}

	public void setActionListener(ActionHandler Handler) {
		
		
	}


	public void setToolbarButton(String lable, ImageIcon toolbarIcon) {
		// TODO Auto-generated method stub
		
	}

	public void setToolbarIcon(ImageIcon toolbarIcon) {
		// TODO Auto-generated method stub
		
	}


}
