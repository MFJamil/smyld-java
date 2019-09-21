package com.smyld.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.smyld.gui.SMYLDLabel;
import com.smyld.gui.SMYLDPanel;
import com.smyld.gui.SMYLDTabbedPane;
import com.smyld.gui.dnd.DragableTransfereHandler;
import com.smyld.gui.event.DockablePanelListener;
import com.smyld.gui.event.DragableComponentListener;
import com.smyld.gui.util.SMYLDGUIUtil;
import com.smyld.resources.Resource;

public class DockablePanel extends SMYLDPanel implements Serializable,
		DockableConstants, DragableComponentListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pnlRSColor = "wt";
	private DockablePanelListener listener;
	private BorderLayout borderLayout1 = new BorderLayout();
	private SMYLDPanel contentPanel = new SMYLDPanel();
	private SMYLDPanel topPanel = new SMYLDPanel(){
		// Border colors are (59,108,156)
		Color lightSide       = new Color(120,179,236);
		Color darkSide        = new Color(88,154,219);
		Color midColor        = SMYLDGUIUtil.getMiddleColor(darkSide, lightSide);
		Color lightMiddleSide = new Color(105,167,228);
		
		Color darkMiddleSide  = new Color(102,164,227);
		
		//7Color darkSide  = new Color(28,112,198);
		
	    public void setBackground(Color bg) {
	    	// Disable the background change now
	    }	
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			//Color titleColor = (Color)UIManager.getLookAndFeelDefaults().get("List.background");
			Graphics2D gd = (Graphics2D)g;
			gd.setPaint(new GradientPaint(getWidth()/2,0,lightSide, getWidth()/2, getHeight()/2,midColor));
			gd.fillRect(0, 0, getWidth(), getHeight()/2);
			gd.setPaint(new GradientPaint(getWidth()/2,getHeight()/2,darkSide, getWidth()/2, getHeight(),midColor));
			gd.fillRect(0,getHeight()/2 , getWidth(), getHeight()/2);

			gd.drawString("This is my test", 0, 20);
			
			//super.paintComponent(g);
		}
	};
	private SMYLDPanel titleRPanel = new SMYLDPanel();
	private SMYLDPanel titleLPanel = new SMYLDPanel();
	private SMYLDLabel minimizedLabel;
	private String label;
	private Resource res;
	JLabel title = new JLabel("Title");
	JLabel panelIcon;
	SMYLDLabel hideLabel;
	SMYLDLabel closeLabel;
	private SMYLDTabbedPane tabPane;
	private DockableComponent firstComponent;
	// private String firstTitle;
	// private ImageIcon firstIcon;
	private HashMap<JComponent,DockableComponent> childComponents;
	private DockablePanel instance;
	private int status;
	private int dockLocation;

	public DockablePanel() {
		instance = this;
		init();

	}

	public static void main(String[] args){
		TreeSet<String> sort = new TreeSet<String>();
		for(Object curKey:UIManager.getLookAndFeelDefaults().keySet()){
			sort.add(curKey.toString());
		}
		//sort.addAll(UIManager.getLookAndFeelDefaults().keySet());
		for(Object curKey:sort){
			
			System.out.println(curKey.toString());
		}
		 
	}
	private void init() {
		res = new Resource();
		childComponents = new HashMap<JComponent,DockableComponent>();
		this.setLayout(borderLayout1);

		panelIcon = new JLabel(res.getImageIcon("parser_sm.gif"));
		initTitle();
		this.add(topPanel, BorderLayout.NORTH);
		this.add(contentPanel, BorderLayout.CENTER);
		minimizedLabel = createLabel();
	}

	public void addListener(DockablePanelListener dockabelListener) {
		listener = dockabelListener;
	}

	public void setContent(JComponent newComponent, String title,
			ImageIcon icon, boolean showTabTitle, boolean showTabIcon) {
		DockableComponent newDockComp = null;
		if (!childComponents.containsKey(newComponent)) {
			newDockComp = new DockableComponent(newComponent, title, icon,
					showTabTitle, showTabIcon);
			if (childComponents.size() == 0) {
				setSingleComponent(newDockComp);
			} else {
				// The situation that the same object have been opened again
				if ((firstComponent != null)
						&& (newComponent.equals(firstComponent.getComponent()))) {
					// Code for checking the current status of the object
					// whether it is minimized or not
					return;
				}
				if ((tabPane == null) || (childComponents.size() == 1)) {
					initTab();
				}
				setIcon(icon);
				setLabel(title);
				addTab(newDockComp);
				// tabPane.addTab(title,icon,holder);
				tabPane.setSelectedComponent(newDockComp.getScroller());
				this.add(tabPane, BorderLayout.CENTER);
			}
		}
		revalidate();
		childComponents.put(newComponent, newDockComp);
	}

	private void addTab(DockableComponent newComp) {
		if ((newComp.isShowTabIcon()) && (newComp.isShowTabTitle())) {
			tabPane.addTab(newComp.getTitle(), newComp.getIcon(), newComp
					.getScroller());
		} else if (newComp.isShowTabTitle()) {
			tabPane.addTab(newComp.getTitle(), newComp.getScroller());
		} else if (newComp.isShowTabIcon()) {
			tabPane.addTab("", newComp.getIcon(), newComp.getScroller());
		} else {
			tabPane.addTab("", newComp.getScroller());
		}
	}

	private void setSingleComponent(DockableComponent newComp) {
		setIcon(newComp.getIcon());
		setLabel(newComp.getTitle());
		this.add(newComp.getScroller(), BorderLayout.CENTER);
		firstComponent = newComp;
		setLabelInfo();
	}

	private void initTab() {
		if (tabPane == null) {
			tabPane = new SMYLDTabbedPane();
			tabPane.applyComponentOrientation(getComponentOrientation());
			tabPane.setBorder(BorderFactory.createEmptyBorder());
			tabPane.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent evt) {
					int sel = tabPane.getSelectedIndex();
					if (sel >= 0) {
						JComponent selectedComp = extractComponent((JScrollPane) tabPane
								.getComponentAt(sel));
						DockableComponent selDock = (DockableComponent) childComponents
								.get(selectedComp);
						// setIcon((ImageIcon)tabPane.getIconAt(sel));
						// setLabel(tabPane.getTitleAt(sel));
						if (selDock != null) {
							setIcon(selDock.getIcon());
							setLabel(selDock.getTitle());
							setLabelInfo();
						}
					}
				}
			});
			tabPane.setTabPlacement(SwingConstants.BOTTOM);
		}
		addTab(firstComponent);
		// tabPane.addTab(firstComponent.getTitle(),firstComponent.getIcon(),
		// firstComponent.getScroller());
	}

	private void initTitle() {
		topPanel.setLayout(new BorderLayout(2, 1));
		titleRPanel.setLayout(new FlowLayout());
		titleLPanel.setLayout(new FlowLayout());
		titleLPanel.setOpaque(false);
		titleRPanel.setOpaque(false);
		closeLabel = new SMYLDLabel(null, res.getImageIcon("close_" + pnlRSColor + ".gif"));
		closeLabel.setBorder(null);
		closeLabel.setRolloverIcon(res.getImageIcon("close_" + pnlRSColor + "_o.gif"));
		closeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				handelCloseAction();
			}
		});

		hideLabel = new SMYLDLabel(null, res.getImageIcon("hide_off_" + pnlRSColor + ".gif"));
		hideLabel.setBorder(null);
		hideLabel.setRolloverIcon(res.getImageIcon("hide_off_" + pnlRSColor + "_o.gif"));
		hideLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				if (listener != null) {
					status = STATUS_MINIMIZED;
					listener.panelHidden(instance);

				}
				// System.out.println("Hidden");
			}
		});
		setTitleBackground(Color.BLUE);
		title.setForeground(Color.WHITE);
		titleRPanel.add(hideLabel);
		titleRPanel.add(closeLabel);
		titleLPanel.add(panelIcon);
		titleLPanel.add(title);
		setPositions();
		// topPanel.setTransferHandler(new DockablePanelTransferHandler(this));
		// SMYLDGUIUtil.setDragableComponent(instance,);
		instance.setTransferHandler(new DragableTransfereHandler(this));
		topPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				// System.out.println("Start panel drag");
				instance.getTransferHandler().exportAsDrag(instance, evt,
						TransferHandler.COPY);
			}
		});
		topPanel.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent evt) {
				System.out.println("Gained");
				setTitleBackground(Color.BLUE);
			}

			public void focusLost(FocusEvent evt) {
				System.out.println("Lost");
				setTitleBackground(Color.GRAY);
			}
		});

	}

	public void refresh() {
		hideLabel.refresh();
		closeLabel.refresh();
	}

	private void setPositions() {
		if (tabPane != null) {
			tabPane.applyComponentOrientation(getComponentOrientation());
		}
		titleRPanel.applyComponentOrientation(getComponentOrientation());
		titleLPanel.applyComponentOrientation(getComponentOrientation());
		topPanel.applyComponentOrientation(getComponentOrientation());
		topPanel.add(titleRPanel, BorderLayout.WEST);
		topPanel.add(titleLPanel, BorderLayout.EAST);
		if (getComponentOrientation().isLeftToRight()) {
			topPanel.add(titleRPanel, BorderLayout.EAST);
			topPanel.add(titleLPanel, BorderLayout.WEST);
		} else {
			topPanel.add(titleRPanel, BorderLayout.WEST);
			topPanel.add(titleLPanel, BorderLayout.EAST);
		}

	}

	@Override
	public void applyComponentOrientation(ComponentOrientation orient) {
		super.applyComponentOrientation(orient);
		setPositions();
	}

	public void resetSize() {
		setSize(0, 0);
	}

	private void handelCloseAction() {
		if ((tabPane != null) && (tabPane.getComponentCount() > 1)) {
			childComponents.remove(extractComponent((JScrollPane) tabPane
					.getSelectedComponent()));
			tabPane.remove(tabPane.getSelectedIndex());
			if (tabPane.getComponentCount() == 1) {
				JScrollPane targetScroll = (JScrollPane) tabPane
						.getComponentAt(0);
				firstComponent = (DockableComponent) childComponents
						.get(extractComponent(targetScroll));
				// firstTitle = tabPane.getTitleAt(0);
				// firstIcon = (ImageIcon)tabPane.getIconAt(0);
				tabPane.remove(targetScroll);
				instance.remove(tabPane);
				setSingleComponent(firstComponent);
				instance.revalidate();
			}
		} else if (childComponents.size() == 1) {
			childComponents.remove(firstComponent.getComponent());
			// System.out.println(firstComponent.getParent().toString());
			instance.remove(firstComponent.getScroller());
		}
		if (listener != null) {
			listener.panelClosed(instance);
			// System.out.println("Closed");
		}

	}

	private JComponent extractComponent(JScrollPane targetScroll) {
		return (JComponent) targetScroll.getViewport().getView();
	}

	@SuppressWarnings("unused")
	private JScrollPane extractScroll(JComponent targetComp) {
		return (JScrollPane) targetComp.getParent().getParent();
	}

	public int getComponentNumber() {
		return childComponents.size();
	}

	private void setTitleBackground(Color backColor) {
		topPanel.setBackground(backColor);
		//titleRPanel.setBackground(backColor);
		//titleLPanel.setBackground(backColor);
	}

	public String getLabel() {
		return label;
	}

	public void setIcon(ImageIcon windowIcon) {
		if (windowIcon != null) {
			panelIcon.setIcon(windowIcon);
		} else {
			panelIcon.setIcon(res.getImageIcon("object.gif"));
		}
	}

	public ImageIcon getIcon() {
		return (ImageIcon) panelIcon.getIcon();
	}

	public void setLabel(String label) {
		this.label = label;
		title.setText(label);
	}

	public void doClose() {
		status = STATUS_CLOSED;
		if (listener != null) {
			listener.panelClosed(instance);
		}
	}

	public void dragStarted() {
		status = STATUS_DRAGGED;
		if (listener != null) {
			listener.panelDragged(instance);
		}
	}

	public void dragEnded() {
		status = STATUS_DROPPED;
		/*
		 * if (tabPane!=null){ if
		 * ((dockLocation==DockableContainer.DROP_DOWN)||(dockLocation==DockableContainer.DROP_TOP)){
		 * tabPane.setTabPlacement(tabPane.TOP); }else{
		 * tabPane.setTabPlacement(tabPane.BOTTOM); } revalidate(); repaint(); }
		 */
		if (listener != null) {
			listener.panelDropped(instance);
		}
	}

	public void setStatus(int newStatus) {
		status = newStatus;
	}

	public int getStatus() {
		return status;
	}

	private SMYLDLabel createLabel() {
		SMYLDLabel panLable = new SMYLDLabel(getLabel(), getIcon());
		panLable.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		panLable.applyComponentOrientation(getComponentOrientation());
		panLable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				if (listener != null) {
					listener.panelShown(instance);
				}
			}

			@Override
			public void mouseEntered(MouseEvent evt) {
				((SMYLDLabel) evt.getSource()).setBorder(BorderFactory
						.createLineBorder(Color.DARK_GRAY));
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				((SMYLDLabel) evt.getSource()).setBorder(BorderFactory
						.createEmptyBorder(1, 1, 1, 1));
			}
		});

		return panLable;
	}

	public SMYLDLabel getMinimizedLabel() {

		return minimizedLabel;
	}

	private void setLabelInfo() {
		minimizedLabel.setText(getLabel());
		minimizedLabel.setIcon(getIcon());
		minimizedLabel.applyComponentOrientation(getComponentOrientation());
	}

	public int getDockLocation() {
		return dockLocation;
	}

	public void setDockLocation(int dockLocation) {
		this.dockLocation = dockLocation;
	}

	public static final int STATUS_SHOWN = 1;
	public static final int STATUS_MINIMIZED = 2;
	public static final int STATUS_DRAGGED = 3;
	public static final int STATUS_DROPPED = 4;
	public static final int STATUS_CLOSED = 5;
	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JComponent getComponent() {
		return instance;
	}

	public boolean isInternal() {
		return true;
	}

}
