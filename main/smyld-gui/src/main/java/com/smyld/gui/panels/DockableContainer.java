package com.smyld.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import com.smyld.gui.SMYLDPanel;
import com.smyld.gui.event.DragableComponentListener;

public class DockableContainer extends SMYLDPanel implements DropTargetListener,
		DockableConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6564194059413534793L;
	private HashMap<JComponent, JComponent> childComponents = new HashMap<JComponent, JComponent>();
	private HashMap<JSplitPane, JSplitPane> childSplitters = new HashMap<JSplitPane, JSplitPane>();
	private HashMap<JComponent, DragableComponentListener> draggedComponents = new HashMap<JComponent, DragableComponentListener>();
	private BorderLayout mainLayout = new BorderLayout();
	private Color dropColor = new Color(0, 0, 255, 3);
	private Color dropTxtColor = Color.YELLOW;
	private int location = 0;
	@SuppressWarnings("unused")
	private int dragNo = 1;
	private int margin = 20;
	@SuppressWarnings("unused")
	private int arrowAlpha = 0;
	@SuppressWarnings("unused")
	private int arrowAlphaSign = 1;
	private int lastDropLoc = -1;
	private String dropText = "Drop Panel";
	private JComponent mainComp;
	@SuppressWarnings("unused")
	private JComponent freeComp;
	private Point curPoint;
	private Dimension lastMainCompSize;

	public DockableContainer() {
		init();
	}

	private void init() {
		setLayout(mainLayout);
		setDropTarget(new DropTarget(this, this));
		UIDefaults lafDef = UIManager.getLookAndFeelDefaults();
		if (lafDef.getColor("ToolTip.background") != null) {
			dropTxtColor = lafDef.getColor("ToolTip.background");
		}
		if (lafDef.getColor("ToolTip.foreground") != null) {
			dropColor = lafDef.getColor("ToolTip.foreground");
			dropColor = new Color(dropColor.getRed(), dropTxtColor.getGreen(),
					dropTxtColor.getBlue(), 4);
		}
	}

	/*
	 * public void registerDragOperation(JComponent draggedComp){
	 * draggedComponents.put(draggedComp,draggedComp); }
	 */
	public void registerDragOperation(
			DragableComponentListener draggedCompListner) {
		draggedComponents.put(draggedCompListner.getComponent(),
				draggedCompListner);
	}

	public void dropComponent(JComponent dropComp) {
		if ((draggedComponents.containsKey(dropComp)) && (location != 0)) {
			DragableComponentListener listener = draggedComponents
					.get(dropComp);
			draggedComponents.remove(dropComp);
			setLastMainComponent(dropComp);
			removeComponent(dropComp);
			revalidate();
			addComponent(dropComp, location, !listener.isInternal());
		} else {
			cancel();
		}
	}

	private void setLastMainComponent(JComponent dropComp) {
		if (mainComp instanceof JSplitPane) {
			JSplitPane split = (JSplitPane) mainComp;
			Component lastMain = null;
			//
			if (dropComp.equals(split.getLeftComponent())) {
				lastMain = split.getRightComponent();
			} else if (dropComp.equals(split.getRightComponent())) {
				lastMain = split.getLeftComponent();
			} else if (dropComp.equals(split.getTopComponent())) {
				lastMain = split.getBottomComponent();
			} else if (dropComp.equals(split.getBottomComponent())) {
				lastMain = split.getTopComponent();
			}
			lastMainCompSize = lastMain.getPreferredSize();
		} else {
			lastMainCompSize = mainComp.getPreferredSize();
		}
	}

	public void setFreeMainComponent(JComponent freeComp) {
		// Need to be recursive because it is only one level navigation
		if (mainComp instanceof JSplitPane) {
			JSplitPane split = (JSplitPane) mainComp;
			@SuppressWarnings("unused")
			Component lastMain = null;
			//
			if (isNotFree(split.getLeftComponent())) {
				split.setLeftComponent(freeComp);
			} else if (isNotFree(split.getRightComponent())) {
				split.setRightComponent(freeComp);
			} else if (isNotFree(split.getTopComponent())) {
				split.setTopComponent(freeComp);
			} else if (isNotFree(split.getBottomComponent())) {
				split.setBottomComponent(freeComp);
			}
		} else {
			// Need to add dockable main component, so that the user can close or minimize it 
			//mainComp = freeComp;
			//mainComp = 
		}
	}

	private boolean isNotFree(Component comp) {
		return (!(comp instanceof JSplitPane) && !(comp instanceof DockablePanel));
	}

	public void dragEnter(DropTargetDragEvent dtde) {
	}

	@SuppressWarnings("unused")
	private boolean isLocalDragable(Object dragObj) {
		if (dragObj instanceof JComponent) {
			return (draggedComponents.containsKey(dragObj));
		}
		return false;
	}

	public void dragOver(DropTargetDragEvent dtde) {
		curPoint = dtde.getLocation();
		// if (!isLocalDragable(dtde.getSource())) return;

		if (curPoint.x <= margin) {
			paintDrop(0, 0, margin, this.getHeight(), DROP_LEFT);
			return;
		} else if (curPoint.y <= margin) {
			paintDrop(0, 0, this.getWidth(), margin, DROP_TOP);
			return;
		} else if (this.getHeight() - curPoint.y <= margin) {
			paintDrop(0, this.getHeight() - margin, this.getWidth(), this
					.getHeight(), DROP_DOWN);
			return;
		} else if (this.getWidth() - curPoint.x <= margin) {
			paintDrop(this.getWidth() - margin, 0, this.getWidth(), this
					.getHeight(), DROP_RIGHT);
			return;
		} else if (location != 0) {
			cancel();
		}
	}

	private void paintDrop(int x, int y, int width, int height, int loc) {
		if (location != loc) {
			cancel();
		}
		location = loc;
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.setColor(dropColor);
		g.fillRect(x, y, width, height);

		// Drawing the arrows
		/*
		 * g.setXORMode(dropColor); arrowAlpha += arrowAlphaSign*3; if
		 * (arrowAlpha >=255){ arrowAlphaSign = -1; arrowAlpha = 255; }else if
		 * (arrowAlpha <= 0){ arrowAlphaSign = 1; arrowAlpha = 0; }
		 * g.setColor(new Color(0,0,0,arrowAlpha));
		 */
		int smargin = margin / 5;
		g.setColor(dropTxtColor);
		switch (location) {
		case DROP_LEFT:
			int fArcX = x + 4 * smargin;
			int fArcY = y + 2 * smargin;
			int[] xarray1 = { fArcX, fArcX, fArcX - 3 * smargin, fArcX };
			int[] yarray1 = { fArcY, fArcY + 3 * smargin,
					(int) (fArcY + 1.5 * smargin), fArcY };
			g.fillPolygon(xarray1, yarray1, 3);
			fArcX = x + 4 * smargin;
			fArcY = height - 2 * smargin;
			int[] xarray2 = { fArcX, fArcX, fArcX - 3 * smargin, fArcX };
			int[] yarray2 = { fArcY, fArcY - 3 * smargin,
					(int) (fArcY - 1.5 * smargin), fArcY };
			g.fillPolygon(xarray2, yarray2, 3);

			break;
		case DROP_TOP:
			fArcX = x + 2 * smargin;
			fArcY = y + 4 * smargin;
			int[] xarray3 = { fArcX, fArcX + 3 * smargin,
					(int) (fArcX + 1.5 * smargin), fArcX };
			int[] yarray3 = { fArcY, fArcY, fArcY - 3 * smargin, fArcY };
			g.fillPolygon(xarray3, yarray3, 3);
			// g.rotate(Math.toRadians(180),fArcX+smargin,fArcY);
			// g.fillPolygon(createTraingle(fArcX,fArcY,3*smargin));

			fArcX = width - 2 * smargin;
			fArcY = y + 4 * smargin;
			int[] xarray4 = { fArcX, fArcX - 3 * smargin,
					(int) (fArcX - 1.5 * smargin), fArcX };
			int[] yarray4 = { fArcY, fArcY, fArcY - 3 * smargin, fArcY };
			g.fillPolygon(xarray4, yarray4, 3);
			// g.rotate(Math.toRadians(180),fArcX-2*smargin,fArcY);
			// g.fillPolygon(createTraingle(fArcX,fArcY,3*smargin));

			break;
		case DROP_DOWN:
			fArcX = x + 2 * smargin;
			fArcY = y + 1 * smargin;
			int[] xarray5 = { fArcX, fArcX + 3 * smargin,
					(int) (fArcX + 1.5 * smargin), fArcX };
			int[] yarray5 = { fArcY, fArcY, fArcY + 3 * smargin, fArcY };
			g.fillPolygon(xarray5, yarray5, 3);
			fArcX = width - 2 * smargin;
			fArcY = y + 1 * smargin;
			int[] xarray6 = { fArcX, fArcX - 3 * smargin,
					(int) (fArcX - 1.5 * smargin), fArcX };
			int[] yarray6 = { fArcY, fArcY, fArcY + 3 * smargin, fArcY };
			g.fillPolygon(xarray6, yarray6, 3);

			break;
		case DROP_RIGHT:
			fArcX = x + 1 * smargin;
			fArcY = y + 1 * smargin;
			int[] xarray7 = { fArcX, fArcX, fArcX + 3 * smargin, fArcX };
			int[] yarray7 = { fArcY, fArcY + 3 * smargin,
					(int) (fArcY + 1.5 * smargin), fArcY };
			g.fillPolygon(xarray7, yarray7, 3);
			fArcX = width - 4 * smargin;
			fArcY = height - 1 * smargin;
			int[] xarray8 = { fArcX, fArcX, fArcX + 3 * smargin, fArcX };
			int[] yarray8 = { fArcY, fArcY - 3 * smargin,
					(int) (fArcY - 1.5 * smargin), fArcY };
			g.fillPolygon(xarray8, yarray8, 3);

			break;

		}
		// Drawing the text
		int rotate = 0;
		int textX = (x + width) / 2;
		int textY = (y + height) / 2;

		if ((loc == DROP_LEFT) || (loc == DROP_RIGHT)) {
			rotate = 270;
			textX += margin / 5;
		} else {
			textY += margin / 5;
		}
		g.setPaintMode();
		Font targetFont = new Font("Arial", Font.BOLD + Font.HANGING_BASELINE,
				12);
		g.getFontRenderContext().getTransform().translate(textX, textY);
		g.rotate(Math.toRadians(rotate), textX, textY);
		g.setFont(targetFont);
		// g.setColor(dropTxtColor);
		/*
		 * try { g.drawString(new
		 * String("tztr".getBytes(),"windows-1256"),textX,textY); } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); }
		 */
		g.drawString(getDropText(), textX, textY);
	}

	private void cancel() {
		location = 0;
		this.repaint();
	}

	@SuppressWarnings("unused")
	private Polygon createTraingle(int x, int y, int length) {
		int[] xarray = { x, x - length, (x - length / 2), x };
		int[] yarray = { y, y, y + length, y };
		return new Polygon(xarray, yarray, 3);

	}

	public void dropActionChanged(DropTargetDragEvent dtde) {
		// System.out.println("dropActionChanged");
	}

	public void dragExit(DropTargetEvent dte) {
		cancel();
		// System.out.println("dragExit");
	}

	public void drop(DropTargetDropEvent dtde) { /*
													 * JComponent newComponent =
													 * null; try { DataFlavor df =
													 * dtde.getCurrentDataFlavors()[0];
													 * Transferable curT =
													 * dtde.getTransferable();
													 * if (curT!=null){
													 * System.out.println("Transferable
													 * class : " +
													 * curT.toString()); if
													 * (curT.getTransferData(df)instanceof
													 * JComponent){ newComponent =
													 * (JComponent)curT.getTransferData(df);
													 * addComponent(newComponent,location);
													 * }else{
													 * addComponent(createBut(),location); }
													 *  } } catch (Exception e) {
													 * e.printStackTrace(); }
													 */

	}

	public void addSideComponent(JComponent newComponent) {
		if (getComponentOrientation().isLeftToRight()) {
			addComponent(newComponent, DROP_LEFT, false);
		} else {
			addComponent(newComponent, DROP_RIGHT, false);
		}
	}

	public void addDownComponent(JComponent newComponent) {
		addComponent(newComponent, DROP_DOWN, false);
	}

	public void setFreeComponent(JComponent newComp) {
		freeComp = newComp;
		revalidate();
		repaint();
	}

	public void addComponent(JComponent newComponent, int toLocation,
			boolean extendSize) {
		if (!childComponents.containsKey(newComponent)) {
			childComponents.put(newComponent, newComponent);
		}
		if (newComponent instanceof DockablePanel) {
			((DockablePanel) newComponent).setDockLocation(toLocation);
		}

		JSplitPane newSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		newSplit.setComponentOrientation(getComponentOrientation());
		Dimension compSize = newComponent.getPreferredSize();
		if (lastMainCompSize == null) {
			setLastMainComponent(newComponent);
		}

		@SuppressWarnings("unused")
		Dimension origSize = mainComp.getPreferredSize();
		// newSplit.setResizeWeight(getResizeWeight(toLocation));
		switch (toLocation) {
		case DROP_LEFT:
			newSplit.setLeftComponent(newComponent);
			newSplit.setRightComponent(mainComp);
			newSplit.setDividerLocation(newComponent.getPreferredSize().width);
			mainComp = newSplit;
			add(mainComp, BorderLayout.CENTER);
			break;
		case DROP_TOP:
			newSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
			if (!extendSize) {
				newSplit.setResizeWeight(0);
			}
			newSplit.setTopComponent(newComponent);
			newSplit.setBottomComponent(mainComp);
			if (extendSize) {
				newSplit
						.setDividerLocation(newComponent.getPreferredSize().height);
			}
			mainComp = newSplit;
			add(mainComp, BorderLayout.CENTER);
			break;
		case DROP_DOWN:
			newSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
			if (!extendSize) {
				newSplit.setResizeWeight(1);
			}
			newSplit.setTopComponent(mainComp);
			newSplit.setBottomComponent(newComponent);
			if (extendSize) {
				newSplit.setDividerLocation(newSplit.getHeight()
						- newComponent.getPreferredSize().height);
			}

			mainComp = newSplit;
			add(mainComp, BorderLayout.CENTER);
			break;
		case DROP_RIGHT:
			newSplit.setLeftComponent(mainComp);
			if (!extendSize) {
				newSplit.setResizeWeight(1);
			}
			newSplit.setRightComponent(newComponent);
			newSplit.setDividerLocation(mainComp.getWidth());
			mainComp = newSplit;
			add(mainComp, BorderLayout.CENTER);
			break;
		}
		if (extendSize) {
			handelSize(newComponent, toLocation, compSize, lastMainCompSize);
			Component window = getPanelWindow();
			if (window != null) {
				window.setSize(this.getSize());

				switch (toLocation) {
				case DROP_RIGHT:
					if (lastDropLoc == DROP_LEFT) {
						window.setLocation(window.getLocation().x
								+ compSize.width, window.getLocation().y);
					}
					break;
				case DROP_DOWN:
					if (lastDropLoc == DROP_LEFT) {
						window.setLocation(window.getLocation().x
								+ compSize.width, window.getLocation().y);
					}
					break;
				case DROP_LEFT:
					window.setLocation(window.getLocation().x - compSize.width,
							window.getLocation().y);
					break;
				case DROP_TOP:
					int newX = window.getLocation().x;
					if (lastDropLoc == DROP_LEFT) {
						newX = window.getLocation().x + compSize.width;
					}
					window.setLocation(newX, window.getLocation().y
							- compSize.height);
					break;
				}

				lastDropLoc = location;

			}
		}

		childSplitters.put(newSplit, newSplit);
		this.validate();
		this.repaint();
		// this.getParent().setSize(this.getSize());
	}

	private void handelSize(JComponent newComp, int location,
			Dimension compSize, Dimension origSize) {
		int newHeight = origSize.height;
		int newWidth = origSize.width;
		System.out.println("Main Component (W:" + origSize.width + " , H:"
				+ origSize.height);
		System.out.println("Drag Component (W:" + compSize.width + " , H:"
				+ compSize.height);
		switch (location) {
		case DROP_RIGHT:
		case DROP_LEFT:
			newWidth = origSize.width + compSize.width;
			newHeight = origSize.height > compSize.height ? origSize.height
					: compSize.height;
			break;
		case DROP_TOP:
		case DROP_DOWN:
			newHeight = origSize.height + compSize.height;
			newWidth = origSize.width > compSize.width ? origSize.width
					: compSize.width;
			break;
		}
		this.setSize(newWidth, newHeight);
	}

	public JComponent getMainComp() {
		return mainComp;
	}

	public void setMainComp(JComponent mainComp) {
		if (!(mainComp instanceof JSplitPane)) {
			freeComp = mainComp;
		}
		this.mainComp = mainComp;
		add(mainComp, BorderLayout.CENTER);
	}

	public void removeComponent(JComponent targetComponent) {
		if (childComponents.containsKey(targetComponent)) {
			JSplitPane targetSplitter = (JSplitPane) targetComponent
					.getParent();
			Container splitCont = targetSplitter.getParent();
			JComponent remainComponent = null;
			if (childSplitters.containsKey(targetSplitter)) {
				int spiltterOrient = targetSplitter.getOrientation();
				if (spiltterOrient == JSplitPane.HORIZONTAL_SPLIT) {
					JComponent rcomp = (JComponent) targetSplitter
							.getRightComponent();
					JComponent lcomp = (JComponent) targetSplitter
							.getLeftComponent();
					splitCont.remove(targetSplitter);
					if (targetComponent.equals(rcomp)) {
						splitCont.add(lcomp);
						remainComponent = lcomp;
					} else {
						splitCont.add(rcomp);
						remainComponent = rcomp;
					}
				} else if (spiltterOrient == JSplitPane.VERTICAL_SPLIT) {
					JComponent tcomp = (JComponent) targetSplitter
							.getTopComponent();
					JComponent bcomp = (JComponent) targetSplitter
							.getBottomComponent();
					splitCont.remove(targetSplitter);
					if (targetComponent.equals(tcomp)) {
						splitCont.add(bcomp);
						remainComponent = bcomp;
					} else {
						splitCont.add(tcomp);
						remainComponent = tcomp;
					}
				}
			}
			if (mainComp.equals(targetSplitter)) {
				mainComp = remainComponent;
			}
			childComponents.remove(targetComponent);
			childSplitters.remove(targetSplitter);
		}

	}

	public String getDropText() {
		return dropText;
	}

	public void setDropText(String dropText) {
		this.dropText = dropText;
	}

}
