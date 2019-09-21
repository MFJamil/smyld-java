package com.smyld;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import com.smyld.gui.DateControl;
import com.smyld.gui.GUIAction;
import com.smyld.gui.SMYLDButton;
import com.smyld.gui.SMYLDFrame;
import com.smyld.gui.SMYLDLabel;
import com.smyld.gui.SMYLDLabeledTextField;
import com.smyld.gui.SMYLDMenu;
import com.smyld.gui.SMYLDMenuItem;
import com.smyld.gui.SMYLDPanel;
import com.smyld.gui.SMYLDPopupMenu;
import com.smyld.gui.SMYLDProgressBar;
import com.smyld.gui.SMYLDRCLayout;
import com.smyld.gui.SMYLDTextArea;
import com.smyld.gui.SMYLDTree;
import com.smyld.gui.event.DockablePanelListener;
import com.smyld.gui.layout.SMYLDRCConstraints;
import com.smyld.gui.panels.DockableContainer;
import com.smyld.gui.panels.DockableDesktop;
import com.smyld.gui.panels.DockablePanel;
import com.smyld.gui.panels.InternetPanel;
import com.smyld.gui.tree.SMYLDTreeModel;
import com.smyld.gui.tree.SMYLDTreeNode;
import com.smyld.gui.util.SMYLDGUIUtil;
import com.smyld.gui.window.SMYLDProgressWindow;
import com.smyld.io.ComponentPrintStream;
import com.smyld.resources.Resource;
import com.smyld.test.DynamicTree;

public class TesterGui implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SMYLDPanel testPanel;
	DockableDesktop testDock;
	int tabCounter = 0;
	SMYLDFrame window;
	JScrollPane testTab;
	DockableContainer container;
	DockablePanel newPanel;
	SMYLDTree testTree;
	SMYLDTree ourTree;
	SMYLDTreeNode rootNode;
	int idCounter = 5;
	DynamicTree newDTree;

	public TesterGui() {
		init();
		// testProgressWindow();
	}

	public static void main(String[] args) {
		// SMYLDTree atree = new SMYLDTree();
		new TesterGui();
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			// UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			//UIManager
			//		.setLookAndFeel("com.borland.plaf.borland.BorlandLookAndFeel");
			// UIManager.setLookAndFeel("com.jgoodies.plaf.plastic.PlasticXPLookAndFeel");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		//SMYLDLoginDialog dlg = new SMYLDLoginDialog(null);
		//dlg.setVisible(true);
	}

	private void init() {
		// window.setDefaultLookAndFeelDecorated(true);
		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			// UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			//UIManager
			//		.setLookAndFeel("com.borland.plaf.borland.BorlandLookAndFeel");
			// UIManager.setLookAndFeel("com.jgoodies.plaf.plastic.PlasticXPLookAndFeel");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		window = new SMYLDFrame("SMYLD GUI Project Tester window");
		window.setSize(233, 290);
		window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		window.centerWindowInScreen();
		window.maximizeFrame();
		window.getContentPane().setLayout(new BorderLayout());
		testPanel = new SMYLDPanel();
		testPanel.setLayout(new BorderLayout());
		doTest();

		// testPanel.revalidate();
		// window.pack();
		// window.setSize(testPanel.getSize());
		window.getContentPane().add(testPanel, BorderLayout.CENTER);

		window.setVisible(true);

	}

	private void doTest() {
		//doTestPopup();
		// doTestProgressBar();
		// doTestTree();
		//doTestTree1();
		// doTestButton();
		//doTestTextFeild();
		//doTestDockable();
		//doTestDockableDeskTop();
		// doTestDockableDeskTop1();
		// testHTMLViewer();
		// testNewDragTool();
		testDatePanel();
		// doTestUIDefaults();
		// doTestPrintStreamComp();
		//doTestLabeledComponent();

	}
	private void doTestPopup(){
		final SMYLDPopupMenu jp = new SMYLDPopupMenu();
		SMYLDMenu m1 = new SMYLDMenu("Menu1","m1");
		SMYLDMenuItem i1 = new SMYLDMenuItem("Item1","i1");
		m1.add(i1);
		final JLabel lb =  new JLabel("Click me");
		testPanel.add(lb);
		jp.add(m1,"m1");
		testPanel.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				jp.show(lb, e.getX(), e.getY());
			}
		});
		
	}

	private void testDatePanel() {
		DateControl datePanel = new DateControl();
		datePanel.setSize(200, 20);
		/*
		 * testPanel.setLayout(new FlowLayout()); testPanel.add(datePanel); //
		 */
		/*
		 * testPanel.setLayout(new SMYLDRCLayout()); testPanel.add(datePanel,new
		 * SMYLDRCConstraints(1,1,10,1)); //
		 */
		// *
		// testPanel.add(datePanel,"Center");
		// SMYLDPanel dpanel = new SMYLDPanel();
		// dpanel.setBackground(Color.BLUE);
		// dpanel.setToolTip(datePanel);
		testPanel.add(datePanel, "Center");
		//testPanel.add(new JLabel("Testing transperancy"), "South");
		// testPanel = new DatePanel();
		// */
	}

	@SuppressWarnings("unused")
	private void testNewDragTool() {
		// SMYLDGUIUtil
		JLabel tlbl = new JLabel("My Drag Label");
		JLabel clbl = new JLabel("My Dockable Label");
		tlbl.setSize(400, 200);
		DockableContainer cont = new DockableContainer();
		SMYLDGUIUtil.setDragableComponent(tlbl, cont, false);
		cont.setMainComp(clbl);
		testPanel.add(cont, BorderLayout.CENTER);
		testPanel.add(tlbl, BorderLayout.SOUTH);

	}

	@SuppressWarnings("unused")
	private void testHTMLViewer() {
		try {
			// String contents = FileSystem.readStringFile("D:/downloads/Java
			// Tools/JBrowser/manual.html");

			// SMYLDEditorPane pane = new SMYLDEditorPane("text/html",contents);
			// File pageFile = new File("D:/downloads/Java
			// Tools/JBrowser/manual.html");
			File pageFile = new File(
					"D:/Projects/TestApplications/SMYLDFormatsConsole/sources/help/introduction_en.htm");
			// SMYLDEditorPane pane = new SMYLDEditorPane(pageFile.toURL());
			// InternetPanel ip = new InternetPanel(pageFile.toURL());
			InternetPanel ip = new InternetPanel();
			//ip.openPage(pageFile.toURL());
			ip.openPage(pageFile.toURI().toURL());
			// SMYLDEditorPane pane = new SMYLDEditorPane("http://www.yahoo.com");
			// ip.openPage();
			// ip.openPage("http://www.yahoo.com");
			// JScrollPane container = new JScrollPane(ip);

			testPanel.add(ip, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * SMYLDEditorPane pane = new SMYLDEditorPane();
		 * pane.setContentType("text/html");
		 * pane.setText(FileSystem.readStringFile("D:/downloads/Java
		 * Tools/JBrowser/manual.html")); pane.setEditable(false);
		 * pane.revalidate();
		 */

	}
	@SuppressWarnings("unused")
	private void doTestLabeledComponent() {
		SMYLDLabeledTextField txt = new SMYLDLabeledTextField("Name", "Mohammed ", 20,20);
		SMYLDPanel newPanel = new SMYLDPanel();
		newPanel.setLayout(new SMYLDRCLayout());
		newPanel.add(txt,new SMYLDRCConstraints(1,1,30,1));
		newPanel.add(new SMYLDLabeledTextField("Password", "****", 20,20),new SMYLDRCConstraints(1,2,30,1));
		newPanel.add(new SMYLDLabeledTextField("Birth Date", "24-05-1971", 20,20),new SMYLDRCConstraints(1,3,30,1));
		newPanel.add(new SMYLDLabeledTextField("ID Number", "554421112", 20,20),new SMYLDRCConstraints(1,4,30,1));
		testPanel = newPanel;
	}
	@SuppressWarnings("unused")
	private void doTestPrintStreamComp() {
		SMYLDTextArea ta = new SMYLDTextArea(12, 40);
		testPanel.add(ta, BorderLayout.CENTER);
		ComponentPrintStream test = new ComponentPrintStream(ta);
		System.setOut(test);

		testPanel.repaint();
		System.out.println("Hello this is the test .. ");
	}

	@SuppressWarnings("unused")
	private void doTestUIDefaults() {
		UIDefaults test = UIManager.getLookAndFeelDefaults();
		for (Object curObj : test.keySet()) {
			String currentObj = curObj.toString();
			if ((currentObj.indexOf("Color") != -1)
					|| (currentObj.indexOf("foreground") != -1)
					|| (currentObj.indexOf("background") != -1)) {
				System.out.println(currentObj);
			}
		}
	}

	@SuppressWarnings("unused")
	private void doTestDockable() {
		window.setSize(400, 200);
		container = new DockableContainer();
		container.setSize(200, 100);
		container.setBackground(Color.BLUE);

		newPanel = new DockablePanel();
		newPanel.addListener(new DockablePanelListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void panelClosed(DockablePanel panel) {
				container.removeComponent(newPanel);
				container.revalidate();
				testPanel.repaint();
			}

			public void panelHidden(DockablePanel panel) {
			}

			public void panelShown(DockablePanel panel) {

			}

			public void panelDragged(DockablePanel panel) {
			}

			public void panelDropped(DockablePanel panel) {
			}
		});
		newPanel.setContent(createTestTree(), "my test", null, true, false);

		SMYLDLabel testLbl = new SMYLDLabel("Drag Me!!");
		// testLbl.setDragEnabled(true);
		testLbl.setTransferHandler(new TransferHandler("text"));
		testLbl.setBorder(BorderFactory.createEtchedBorder());
		testLbl.setBackground(Color.WHITE);
		testLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				// System.out.println("Hello there ");
				JComponent c = (JComponent) evt.getSource();
				TransferHandler th = c.getTransferHandler();

				th.exportAsDrag(c, evt, TransferHandler.COPY);

			}
		});
		newPanel = new DockablePanel();
		newPanel.addListener(new DockablePanelListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void panelClosed(DockablePanel panel) {
				container.removeComponent(newPanel);
				container.revalidate();
				testPanel.repaint();
			}

			public void panelHidden(DockablePanel panel) {
			}

			public void panelShown(DockablePanel panel) {

			}

			public void panelDragged(DockablePanel panel) {
			}

			public void panelDropped(DockablePanel panel) {
			}
		});
		newPanel.setContent(createTestTree(), "my test", null, true, false);
		container.setMainComp(createTestTree());
		container.addSideComponent(newPanel);
		testPanel.add(testLbl, BorderLayout.WEST);
		testPanel.add(container, BorderLayout.CENTER);

		window.repaint();
		// window.pack();
	}

	@SuppressWarnings("unused")
	private void doTestDockableDeskTop1() {
		ComponentOrientation curOrient = ComponentOrientation.RIGHT_TO_LEFT;
		testDock = new DockableDesktop();
		testDock.applyComponentOrientation(curOrient);
		// container = new DockableContainer();
		// container.applyComponentOrientation(curOrient);
		// container.setMainComp(testDock);

		JMenuBar bar = new JMenuBar();
		JMenu testMenu = new JMenu("Testing");
		JMenuItem addScambler = new JMenuItem("add Scrambler");
		JMenuItem changeOrient = new JMenuItem("Change Orientation");
		// testTree = createTestTree();
		changeOrient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (container.getComponentOrientation().isLeftToRight()) {
					testDock
							.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				} else {
					testDock
							.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				}
			}
		});
		addScambler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// testDock.addToSide(createTestTree(),"Scrambler(" +
				// Integer.toString(++tabCounter) + ")",new
				// Resource().getImageIcon("scrambler_sm.gif"));
				testDock.addToSide(createTestTree(), "Scrambler("
						+ Integer.toString(++tabCounter) + ")", new Resource()
						.getImageIcon("scrambler_sm.gif"), true, false);
			}
		});
		JMenuItem addParser = new JMenuItem("add Parser");
		addParser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				testDock.addToDown(createTestTree(), "Parser("
						+ Integer.toString(++tabCounter) + ")", new Resource()
						.getImageIcon("parser_sm.gif"), true, true);
			}
		});
		// testDock.addToSide(test,"Scrambler(" + Integer.toString(++tabCounter)
		// + ")",new Resource().getImageIcon("scrambler_sm.gif"));
		// testDock.addToSide(test,"Scrambler(" + Integer.toString(++tabCounter)
		// + ")",new Resource().getImageIcon("scrambler_sm.gif"));
		testMenu.add(addScambler);
		testMenu.add(addParser);
		testMenu.add(changeOrient);
		bar.add(testMenu);
		window.setJMenuBar(bar);
		// testDock.addToSide(createTestTree(),"First",new
		// Resource().getImageIcon("scrambler_sm.gif"));
		// testDock.addToSide(createTestTree(),"Second",new
		// Resource().getImageIcon("parser_sm.gif"));
		// testDock.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		testPanel = testDock;

		// window.pack();
	}

	@SuppressWarnings("unused")
	private void doTestDockableDeskTop() {
		ComponentOrientation curOrient = ComponentOrientation.RIGHT_TO_LEFT;
		// testTab = createTestTree();
		testTab.applyComponentOrientation(curOrient);
		testDock = new DockableDesktop();
		testDock.applyComponentOrientation(curOrient);
		JMenuBar bar = new JMenuBar();
		JMenu testMenu = new JMenu("Testing");
		JMenuItem addScambler = new JMenuItem("add Scrambler");
		JMenuItem changeOrient = new JMenuItem("Change Orientation");

		changeOrient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (testDock.getComponentOrientation().isLeftToRight()) {
					testDock
							.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				} else {
					testDock
							.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				}
			}
		});
		addScambler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				testDock.addToSide(createTestTree(), "Scrambler("
						+ Integer.toString(++tabCounter) + ")", new Resource()
						.getImageIcon("scrambler_sm.gif"), false, true);
			}
		});
		JMenuItem addParser = new JMenuItem("add Parser");
		addParser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				testDock.addToSide(testTab, "Parser("
						+ Integer.toString(++tabCounter) + ")", new Resource()
						.getImageIcon("parser_sm.gif"), false, true);
			}
		});

		// testDock.addToSide(test,"Scrambler(" + Integer.toString(++tabCounter)
		// + ")",new Resource().getImageIcon("scrambler_sm.gif"));
		// testDock.addToSide(test,"Scrambler(" + Integer.toString(++tabCounter)
		// + ")",new Resource().getImageIcon("scrambler_sm.gif"));
		testMenu.add(addScambler);
		testMenu.add(addParser);
		testMenu.add(changeOrient);
		bar.add(testMenu);
		window.setJMenuBar(bar);
		// testDock.addToSide(createTestTree(),"First",new
		// Resource().getImageIcon("scrambler_sm.gif"));
		// testDock.addToSide(createTestTree(),"Second",new
		// Resource().getImageIcon("parser_sm.gif"));
		// testDock.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		testPanel = testDock;

		// window.pack();
	}

	@SuppressWarnings("unused")
	private void doTestButton() {
		GUIAction act = new GUIAction();
		act.setLabel("my button");
		SMYLDButton but = new SMYLDButton(act, "ID");
		but
				.setIcon(new ImageIcon(
						"D:/Projects/TestApplications/SMYLDMGUI/sources/images/exit.gif"));
		// but.setText("hi");
		but.setTextDown();
		testPanel.add(but);
	}

	@SuppressWarnings("unused")
	private void testProgressWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		SMYLDProgressWindow testWindow = new SMYLDProgressWindow(null,
				"This is a test");
		testWindow.centerWindow();
		// testWindow.setDefaultCloseOperation(testWindow.DISPOSE_ON_CLOSE);
		testWindow.setAllItemsRange(512);
		testWindow.setCurrentItemRange(100);
		testWindow.setCurrentItem(20);
		testWindow.setAllItems(50);
		testWindow.setCurrentItemStatement("downloading mail title etc");
		testWindow.setAllItemsStatement("From total mails");
		testWindow.show();
	}
	@SuppressWarnings("unused")
	private void doTestProgressBar() {
		// Code here for starting test
		// Adding the progress bar
		SMYLDProgressBar testProgress = new SMYLDProgressBar();
		testProgress.setValue(10);
		testProgress.setValue(testProgress.getValue() + 10);
		testProgress.setBackground(Color.WHITE);
		testProgress.setForeground(Color.RED);
		testProgress.setBorder(new BevelBorder(BevelBorder.LOWERED));
		// testProgress.setVisible(true);

		// testPanel.addComponent(testProgress,50,120,250,18);
	}

	@SuppressWarnings("unused")
	private void doTestTree() {
		// Code here for starting test
		// Adding the progress bar
		JScrollPane pane = createTestTree();
		testPanel.add(pane, BorderLayout.CENTER);

		JButton addNode = new JButton("add");
		addNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ourTree.addChild((SMYLDTreeNode) rootNode.getChild("2"),
						new SMYLDTreeNode(Integer.toString(idCounter++), "Node"
								+ Integer.toString(idCounter - 5)));
				// ((SMYLDTreeNode)rootNode.getChild("3")).add(new
				// SMYLDTreeNode(Integer.toString(idCounter++),"newOne"));

				ourTree.revalidate();
				ourTree.repaint();
			}
		});
		testPanel.add(addNode, BorderLayout.NORTH);
		// testPanel.add(createStandardTree());
	}
	@SuppressWarnings("unused")
	private void doTestTree1() {
		newDTree = new DynamicTree();
		// rootNode = newDTree.addObject()
		JButton addNode = new JButton("add");
		addNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				newDTree.addObject(new SMYLDTreeNode(Integer
						.toString(idCounter++), "newOne"));
				// ((SMYLDTreeNode)rootNode.getChild("3")).add(new
				// SMYLDTreeNode(Integer.toString(idCounter++),"newOne"));
			}
		});
		JButton newRootNode = new JButton("new Root");
		newRootNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				newDTree.addObject(new SMYLDTreeNode(Integer
						.toString(idCounter++), "newOne"));
				// ((SMYLDTreeNode)rootNode.getChild("3")).add(new
				// SMYLDTreeNode(Integer.toString(idCounter++),"newOne"));
			}
		});

		testPanel.add(addNode, BorderLayout.NORTH);
		// Code here for starting test
		// Adding the progress bar
		testPanel.add(newDTree, BorderLayout.CENTER);
		// testPanel.add(createStandardTree());
	}

	private JScrollPane createTestTree() {
		rootNode = new SMYLDTreeNode("1", "root");
		SMYLDTreeNode node1 = new SMYLDTreeNode("2", "one");
		rootNode.add(node1);
		SMYLDTreeNode node2 = new SMYLDTreeNode("3", "two");
		rootNode.add(node2);
		SMYLDTreeNode node3 = new SMYLDTreeNode("31", "two1");
		node2.add(node3);
		SMYLDTreeNode node4 = new SMYLDTreeNode("32", "two2");
		node2.add(node4);
		SMYLDTreeNode node5 = new SMYLDTreeNode("33", "two3");
		node2.add(node5);

		SMYLDTreeModel model = new SMYLDTreeModel(rootNode);
		SMYLDTree tree = new SMYLDTree(model);
		tree.setVisible(true);
		tree.setShowsRootHandles(true);
		tree.setRootVisible(false);
		ourTree = tree;
		ourTree.setEditable(true);
		JScrollPane container = new JScrollPane(tree);
		return container;
	}

	@SuppressWarnings("unused")
	private JTree createStandardTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
		DefaultMutableTreeNode one = new DefaultMutableTreeNode("one");
		root.add(one);
		JTree newTree = new JTree(root);
		newTree.setShowsRootHandles(true);

		return newTree;
	}

	@SuppressWarnings("unused")
	private void doTestTextFeild() {
		// Code here for starting test
		// Adding the progress bar

		SMYLDLabeledTextField textField = new SMYLDLabeledTextField("My Test ",
				"this is a text", 1,200);
		//textField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		textField.setSize(400,20);
		testPanel.add(textField,"Center");
	}

}
