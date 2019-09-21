package org.smyld.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.smyld.gui.event.ActionHandler;
import org.smyld.gui.tree.SMYLDTreeCellRenderer;
import org.smyld.gui.tree.SMYLDTreeModel;
import org.smyld.gui.tree.SMYLDTreeNode;

public class SMYLDTree extends JTree {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5886670601013061210L;
	SMYLDTreeCellRenderer smyldCellRenderer;
	SMYLDTreeModel        smyldModel;
	Color                selectionColor;
	ActionHandler        actionHandler;
	SMYLDTree             instance;
	SMYLDPopupMenu        popupMenu;
	int                  actionClicksNo =2;
	public SMYLDTree() {
		super();
		init();
	}

	public SMYLDTree(SMYLDTreeModel model) {
		// super(model);
		init(model);
	}

	public SMYLDTree(SMYLDTreeModel model, ActionHandler handler) {
		actionHandler = handler;
		
		// SMYLDTreeNode root = (SMYLDTreeNode)model.getRoot();
		// init(root);
		init(model);
	}

	public SMYLDTree(SMYLDTreeNode treeNode, ActionHandler treeActionHandler) {
		actionHandler = treeActionHandler;
		init(treeNode);
	}

	public SMYLDTree(SMYLDTreeNode treeNode, ActionHandler treeActionHandler,
			SMYLDPopupMenu treePopupMenu) {
		popupMenu = treePopupMenu;
		actionHandler = treeActionHandler;
		init(treeNode);
	}

	public void expandAll() {
		for (int i = 0; i < getRowCount(); i++) {
			expandRow(i);
		}
	}

	public void collapsAll() {
		for (int i = 0; i < getRowCount(); i++) {
			collapseRow(i);
		}

	}

	/*
	 * private void init(DefaultTreeModel model) { setModel(model); //smyldModel =
	 * model; //setModel(new DefaultTreeModel(treeNode));
	 * DefaultTreeSelectionModel treeSelector = new DefaultTreeSelectionModel();
	 * treeSelector.setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
	 * this.setSelectionModel(treeSelector); smyldCellRenderer = new
	 * SMYLDTreeCellRenderer(); setCellRenderer(smyldCellRenderer);
	 * setShowsRootHandles(true); setRootVisible(false); if
	 * (actionHandler!=null){ this.addMouseListener(new MouseAdapter(){ public
	 * void mouseClicked(MouseEvent evt){ if(evt.getClickCount()==2){ TreePath
	 * selectionPath = instance.getSelectionPath(); if (selectionPath!=null){
	 * SMYLDTreeNode current = (SMYLDTreeNode)selectionPath.getLastPathComponent();
	 * if ((actionHandler!=null)&&(current!=null)) if
	 * (current.getGUIAction()!=null)
	 * actionHandler.processGUIAction(current.getGUIAction()); } } } public void
	 * mousePressed(MouseEvent evt){ if(evt.getButton()==evt.BUTTON3){
	 * 
	 * //System.out.println("Tree mouse pressed received .. "); SMYLDTreeNode
	 * selectedNode = getSelectedItem(); if
	 * ((selectedNode!=null)&&(selectedNode.getPopupMenu()!=null)){
	 * selectedNode.getPopupMenu().show((Component)evt.getSource(),evt.getX(),evt.getY());
	 * //System.out.println(selectedNode.toString()); } if(popupMenu!=null){
	 * popupMenu.show(instance,evt.getX(),evt.getY()); } } } }); } }
	 */
	private void init(SMYLDTreeModel model) {
		setModel(model);
		smyldModel = model;
		// setModel(new DefaultTreeModel(treeNode));
		DefaultTreeSelectionModel treeSelector = new DefaultTreeSelectionModel();
		treeSelector
				.setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
		this.setSelectionModel(treeSelector);
		smyldCellRenderer = new SMYLDTreeCellRenderer();
		setCellRenderer(smyldCellRenderer);
		setShowsRootHandles(true);
		setRootVisible(false);
		instance = this;
		if (actionHandler != null) 
			activateActionHandler();
	
	}
	
	public void activateActionHandler(){
		if (actionHandler != null) {
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent evt) {
					if (evt.getClickCount() == actionClicksNo) {
						TreePath selectionPath = instance.getSelectionPath();
						if (selectionPath != null) {
							SMYLDTreeNode current = (SMYLDTreeNode) selectionPath
									.getLastPathComponent();
							if ((actionHandler != null) && (current != null)) {
								if (current.getGUIAction() != null) {
									actionHandler.processGUIAction(current
											.getGUIAction());
								}
							}
						}
					}
				}

				@Override
				public void mousePressed(MouseEvent evt) {
					if (evt.getButton() == MouseEvent.BUTTON3) {

						// System.out.println("Tree mouse pressed received ..
						// ");
						SMYLDTreeNode selectedNode = getSelectedItem();
						if ((selectedNode != null)
								&& (selectedNode.getPopupMenu() != null)) {
							selectedNode.getPopupMenu().show(
									(Component) evt.getSource(), evt.getX(),
									evt.getY());
							// System.out.println(selectedNode.toString());
						}
						if (popupMenu != null) {
							popupMenu.show(instance, evt.getX(), evt.getY());
						}
					}
				}
			});
		}

	}
	public void setActionHandler(ActionHandler newActionHandler){
		this.actionHandler = newActionHandler;
		activateActionHandler();
	}
	public void setActionHandler(ActionHandler newActionHandler,int clicksNo){
		actionClicksNo = clicksNo;
		setActionHandler(newActionHandler);
	}
	public void setActionFiringClicks(int clicksNo){
		actionClicksNo = clicksNo;
	}

	
	public ActionHandler getActionHandler() {
		return actionHandler;
	}

	public SMYLDTreeNode getSelectedItem() {
		TreePath selectionPath = instance.getSelectionPath();
		SMYLDTreeNode current = null;
		if (selectionPath != null) {
			current = (SMYLDTreeNode) selectionPath.getLastPathComponent();
		}
		return current;
	}

	private void init() {
		// Initializing dummy node
		SMYLDTreeNode rootNode = new SMYLDTreeNode("root","Root");
		init(rootNode);
	}
	private void init(SMYLDTreeNode treeNode) {
		SMYLDTreeModel model = new SMYLDTreeModel(treeNode);
		// DefaultTreeModel model = new DefaultTreeModel(treeNode);
		init(model);
	}

	public void setRootNode(SMYLDTreeNode treeNode) {
		smyldModel.setRoot(treeNode);
	}

	public SMYLDTreeNode getRootNode() {
		// return (SMYLDTreeNode)smyldModel.getRoot();
		return (SMYLDTreeNode) getModel().getRoot();
	}

	public void setSelectionBackGroundColor(Color color) {
		smyldCellRenderer.setSelBGColor(color);
	}

	public void setSelectionForGroundColor(Color color) {
		smyldCellRenderer.setSelFGColor(color);
	}

	/*
	 * public void expandRow(int rowIndex){
	 * System.out.println("SMYLDTree.expandRow(" + rowIndex + ") is called ... ");
	 * super.expandRow(rowIndex); }
	 */
	/*
	 * public void expandPath(TreePath tPath){
	 * //System.out.println("SMYLDTree.expandPath(" + tPath.toString() + ") is
	 * called ... "); Object[] obs = tPath.getPath(); for (int i = 0; i <
	 * obs.length; i++) { System.out.println("Object (" + i + ") is (" +
	 * ((SMYLDTreeNode)obs[i]).getText() + ")");
	 *  }
	 * 
	 * super.expandPath(tPath); }
	 */

	public void addChild(SMYLDTreeNode parentNode, SMYLDTreeNode childNode) {
		childNode.applyComponentOrientation(getComponentOrientation());
		smyldModel.addChild(parentNode, childNode);
		super.scrollPathToVisible(new TreePath(childNode.getPathToRoot()));
	}

	public void removeChild(SMYLDTreeNode childNode) {
		smyldModel.removeNode(childNode);
	}

	public void setNormalBackGroundColor(Color color) {
		smyldCellRenderer.setNorBGColor(color);
	}

	public void setNormalForGroundColor(Color color) {
		smyldCellRenderer.setNorFGColor(color);
	}

	private void jbInit() throws Exception {
		this.setSize(new Dimension(157, 300));
	}

	public SMYLDTreeNode getNode(String nodeID) {
		SMYLDTreeNode targetNode = null;
		if (getRootNode() != null) {
			if (getRootNode().getNodeID().equals(nodeID)) {
				return getRootNode();
			}
			targetNode = search(nodeID, getRootNode());
		}
		return targetNode;
	}

	private SMYLDTreeNode search(String nodeID, SMYLDTreeNode node) {
		for (Enumeration<SMYLDTreeNode> enums = node.children(); enums
				.hasMoreElements();) {
			SMYLDTreeNode curNode = enums.nextElement();
			if (curNode.getNodeID().equals(nodeID)) {
				return curNode;
			}
			if (curNode.getChildCount() > 0) {
				SMYLDTreeNode result = search(nodeID, curNode);
				if(result!=null) return result;
			}
		}
		return null;
	}
}
