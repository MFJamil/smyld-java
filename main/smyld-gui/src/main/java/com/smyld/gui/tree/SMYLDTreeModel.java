package com.smyld.gui.tree;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class SMYLDTreeModel extends DefaultTreeModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected boolean filterIsActive;

	public SMYLDTreeModel(TreeNode root) {
		this(root, false);
	}

	public SMYLDTreeModel(TreeNode root, boolean asksAllowsChildren) {
		this(root, false, false);
	}

	public SMYLDTreeModel(TreeNode root, boolean asksAllowsChildren,
			boolean filterIsActive) {
		super(root, asksAllowsChildren);
		((SMYLDTreeNode) root).setModel(this);
		this.filterIsActive = filterIsActive;
	}
    public void insertNodeInto(SMYLDTreeNode newChild,
    		SMYLDTreeNode parent, int index){
    		parent.insertNodeInto(newChild, index);
    			int[]           newIndexs = new int[1];

    			newIndexs[0] = index;
    			nodesWereInserted(parent, newIndexs);
    }

	public void activateFilter(boolean newValue) {
		filterIsActive = newValue;
	}

	@Override
	public boolean isLeaf(Object node) {
		return ((SMYLDTreeNode) node).isLeaf();
	}

	public boolean isActivatedFilter() {
		return filterIsActive;
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		if ((parent == null) || (child == null)) {
			return -1;
		}
		@SuppressWarnings("unused")
		Object target = null;
		TreeNode parentNode = (TreeNode) parent;
		int targetIndex = parentNode.getIndex((TreeNode) child);
		// System.out.println("SMYLDTreeModel.getIndexOfChild(" +
		// ((SMYLDTreeNode)parent).getText() + "," +
		// ((SMYLDTreeNode)child).getText() + ") : int (" + targetIndex + ")");
		return targetIndex;
	}

	@Override
	public Object getChild(Object parent, int index) {

		Object target = null;
		if (filterIsActive) {
			if (parent instanceof SMYLDTreeNode) {
				target = ((SMYLDTreeNode) parent).getChildAt(index,
						filterIsActive);
			}
		}
		target = ((TreeNode) parent).getChildAt(index);
		// System.out.println("SMYLDTreeModel.getChild(" +
		// ((SMYLDTreeNode)parent).getText() + "," + index + ") : Object (" +
		// ((SMYLDTreeNode)target).getText() + ")");
		return target;
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// System.out.println("SMYLDTreeModel.valueForPathChanged(" +
		// path.getPath().length + "," + newValue.toString() + ")");
		SMYLDTreeNode aNode = (SMYLDTreeNode) path.getLastPathComponent();
		aNode.setUserObject(newValue);
		// nodeChanged(aNode);
	}

	public void addChild(SMYLDTreeNode parentNode, SMYLDTreeNode childNode) {
		if (parentNode == null) {
			parentNode = (SMYLDTreeNode) getRoot();
		}
		int insertedIndex = parentNode.add(childNode);
		int[] newIndexs = new int[1];
		newIndexs[0] = insertedIndex;

		nodesWereInserted(parentNode, newIndexs);
		// super.scrollPathToVisible(new TreePath(childNode.getPathToRoot()));

	}

	public void removeNode(SMYLDTreeNode node) {
		SMYLDTreeNode parent = (SMYLDTreeNode) node.getParent();
		if (parent == null) {
			throw new IllegalArgumentException("node does not have a parent.");
		}
		int[] childIndex = new int[1];
		Object[] removedArray = new Object[1];
		childIndex[0] = parent.getIndex(node);
		parent.remove(childIndex[0]);
		removedArray[0] = node;
		nodesWereRemoved(parent, childIndex, removedArray);
	}

	@Override
	public int getChildCount(Object parent) {

		// childCount++;
		// System.out.println("Child count called");
		int count = 0;
		if (filterIsActive) {
			if (parent instanceof SMYLDTreeNode) {
				count = ((SMYLDTreeNode) parent).getChildCount(filterIsActive);
			}
		}
		count = ((TreeNode) parent).getChildCount();
		// System.out.println("SMYLDTreeModel.getChildCount(" +
		// ((SMYLDTreeNode)parent).getText() + ") : int (" + count + ")");
		return count;
	}

}
