package org.smyld.gui.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

import org.smyld.gui.event.DragableComponentListener;
import org.smyld.resources.Resource;

public class DragableTransfereHandler extends TransferHandler implements
		Transferable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DragableComponentListener dragListener;
	DataFlavor panelDF;// =new DataFlavor(org.smyld.gui.panels.DockablePanel,"SMYLD
						// Dockable Panel");

	public DragableTransfereHandler() {
	}

	public DragableTransfereHandler(DragableComponentListener dragListener) {
		this.dragListener = dragListener;
		panelDF = new DataFlavor(dragListener.getComponent().getClass(),
				"SMYLD Dockable Panel");
	}

	@Override
	public Transferable createTransferable(JComponent comp) {
		// System.out.println("Create transferable called for component : " +
		// comp.toString());
		// System.out.println(draggedComponent.toString());
		return this;
	}

	@Override
	public boolean importData(JComponent comp, Transferable transferable) {
		// System.out.println("IMPORT DATA CALLED ... ");
		return true;
	}

	@Override
	public int getSourceActions(JComponent comp) {
		// System.out.println("Get source actions called .. ");
		return COPY_OR_MOVE;
	}

	@Override
	public void exportDone(JComponent comp, Transferable transfer, int action) {
		// System.out.println("Export Done Called");
		dragListener.dragEnded();
	}

	@Override
	public Icon getVisualRepresentation(Transferable t) {
		// System.out.println("getVisualRepresentation called ... ");
		return new Resource().getImageIcon("parser_sm.gif");
	}

	@Override
	public void exportAsDrag(JComponent comp, InputEvent evt, int action) {
		dragListener.dragStarted();
		super.exportAsDrag(dragListener.getComponent(), evt, action);
	}

	@Override
	public boolean canImport(JComponent comp, DataFlavor[] dfs) {
		// System.out.println("canImport is called ..");
		return true;
	}

	// Transferable Interface methodes .....

	public DataFlavor[] getTransferDataFlavors() {
		// System.out.println("getTransferDataFlavors called");
		DataFlavor[] response = new DataFlavor[1];
		response[0] = panelDF;
		return response;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		// System.out.println("isDataFlavorSupported called");
		return panelDF.equals(flavor);
	}

	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		// System.out.println("getTransferData called");
		return dragListener.getComponent();
	}

}
