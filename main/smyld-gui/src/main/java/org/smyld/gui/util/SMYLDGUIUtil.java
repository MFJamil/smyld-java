package org.smyld.gui.util;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import org.smyld.SMYLDObject;
import org.smyld.gui.dnd.DragableTransfereHandler;
import org.smyld.gui.event.DragableComponentListener;
import org.smyld.gui.panels.DockableContainer;

public class SMYLDGUIUtil extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMYLDGUIUtil() {
	}

	public static void setDragableComponent(JComponent component,
			DockableContainer container) {
		setDragableComponent(component, container, true);
	}

	public static void setDragableComponent(JComponent component,
			DockableContainer container, boolean isInternal) {
		final JComponent comp = component;
		final DockableContainer cont = container;
		final boolean internal = isInternal;
		comp.setTransferHandler(new DragableTransfereHandler(
				new DragableComponentListener() {
					public void dragStarted() {
						cont.registerDragOperation(this);
						// cont.registerDragOperation(comp);
					}

					public void dragEnded() {
						cont.dropComponent(comp);
					}

					public JComponent getComponent() {
						return comp;
					}

					public boolean isInternal() {
						return internal;
					}
				}));
		comp.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				// System.out.println("Start panel drag");
				comp.getTransferHandler().exportAsDrag(comp, evt,
						TransferHandler.COPY);
			}
		});

	}
	
	public static Color getMiddleColor(Color col1,Color col2){
		int resRed   = (int)(col1.getRed()+col2.getRed())/2;
		int resGreen = (int)(col1.getGreen()+col2.getGreen())/2;
		int resBlue  = (int)(col1.getBlue()+col2.getBlue())/2;
		int resAlpha = (int)(col1.getAlpha()+col2.getAlpha())/2;
		
		return new Color(resRed,resGreen,resBlue,resAlpha);
		
	}

}
