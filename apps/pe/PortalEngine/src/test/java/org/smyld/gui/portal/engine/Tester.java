package org.smyld.gui.portal.engine;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.smyld.app.pe.logging.LogFile;
import org.smyld.app.pe.model.ApplicationType;
import org.smyld.app.pe.model.GUIToolkit;
import org.smyld.app.pe.model.LayoutType;
import org.smyld.app.pe.model.gui.GUIToolbar;
import org.smyld.app.pe.model.gui.MenuItem;
import org.smyld.app.pe.model.gui.PEAction;
import org.smyld.app.pe.projectbuilder.ProjectBuilder;
import org.smyld.app.pe.security.AppSecurity;
import org.jdom2.Element;

import org.smyld.SMYLDObject;
import org.smyld.deploy.DeploymentDescriptor;
import org.smyld.gui.portal.engine.sources.PESwingApplicationReader;
import org.smyld.gui.portal.engine.sources.PortalAppXMLSetReaderPESwing;
import org.smyld.gui.portal.engine.sources.SettingsVariablesMapper;
import org.smyld.io.FileSystem;
import org.smyld.resources.FileInfo;
import org.smyld.resources.LookAndFeelResource;
import org.smyld.util.multilang.LangSource;

public class Tester extends SMYLDObject implements PESwingApplicationReader {//WindowPanelListener,
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// WindowPanel createdFrame;
	//WindowPanel createdPanel;
	int itemCounter;

	public Tester() throws Exception {
		//testWindowCreation();
		// testWindowCreated();
		// testXML();
		// testJarArchive();
		// testJarUtility();
		//testAppCreated();
		 //testAppCreation();
		//testClassRef();
		// testLibCopy();
		// testJarRecursive();
		// testSiteUpdator();
		//testMenuLoading();
		//systemTray();
	}

	public HashMap<String, LookAndFeelResource> loadLookAndFeels() {
		return null;
	}


	/*
	 * private void testSiteUpdator(){ try { SMYLDJarOutputStream out = new
	 * SMYLDJarOutputStream(new FileOutputStream("d:/temp/xmlEditor.jar"));
	 * //out.setLevel(9); out.addFilesInFolder(new
	 * File("D:/Projects/personal/XMLEditor/classes"),new FileFilter(){ public
	 * boolean accept(File newFile){ return
	 * (newFile.getName().endsWith(".class")
	 * ||newFile.getName().endsWith(".gif")||newFile.getName().endsWith(".jpg")); }
	 * }); Attributes att = new Attributes();
	 * att.putValue(Attributes.Name.MAIN_CLASS.toString(),"com.lym.prj.xml.editor.MainClass");
	 * att.putValue(Attributes.Name.MANIFEST_VERSION.toString(),"1.0");
	 * out.createManifestFromAttribute(att);
	 * /*out.addLibrary("D:/cvs_home/SMYLD/SMYLD/deploy/smyld.jar");
	 * out.addLibrary("D:/Projects/lib/temp/imap.jar");
	 * out.addLibrary("D:/jdev9051/j2ee/home/lib/mail.jar");
	 * out.addLibrary("D:/jdev9051/j2ee/home/lib/activation.jar"); out.close(); }
	 * catch (Exception e){e.printStackTrace();} } private void testLibCopy(){
	 * try { SMYLDJarOutputStream out = new SMYLDJarOutputStream(new
	 * FileOutputStream("d:/temp/mail.jar"));
	 * out.addLibrary("D:/Projects/personal/HouseMail/deploy/HouseMail.jar");
	 * Attributes att = new Attributes();
	 * att.putValue(Attributes.Name.MAIN_CLASS.toString(),"com.lym.mail.mainClass");
	 * out.createManifestFromAttribute(att); out.close(); } catch (Exception e) {
	 * e.printStackTrace(); } } private void testJarRecursive(){ try {
	 * SMYLDJarOutputStream out = new SMYLDJarOutputStream(new
	 * FileOutputStream("d:/temp/mail.jar")); out.addFilesInFolder(new
	 * File("D:/Projects/personal/HouseMail/classes"),new FileFilter(){ public
	 * boolean accept(File newFile){ return
	 * (newFile.getName().endsWith(".class")
	 * ||newFile.getName().endsWith(".gif")||newFile.getName().endsWith(".jpg")); }
	 * }); Attributes att = new Attributes();
	 * att.putValue(Attributes.Name.MAIN_CLASS.toString(),"com.lym.mail.mainClass");
	 * out.createManifestFromAttribute(att);
	 * out.addLibrary("D:/cvs_home/SMYLD/SMYLD/deploy/smyld.jar");
	 * out.addLibrary("D:/Projects/lib/temp/imap.jar");
	 * out.addLibrary("D:/jdev9051/j2ee/home/lib/mail.jar");
	 * out.addLibrary("D:/jdev9051/j2ee/home/lib/activation.jar"); out.close(); }
	 * catch (Exception e){e.printStackTrace();} }
	 */

	@SuppressWarnings("unused")
	private void testClassRef() {
		try {
			Class<?> target = ClassLoader.getSystemClassLoader().loadClass(
					"org.smyld.lang.script.java.JavaClassBody");
			Method[] methods = target.getMethods();
			for (Method element : methods) {
				System.out.println(element.getName());
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * private void testJarUtility(){ try { SMYLDJarOutputStream outStream = new
	 * SMYLDJarOutputStream(new FileOutputStream("d:/temp/app.jar"));
	 * outStream.addLibrary("D:/cvs_home/SMYLD/SMYLD/deploy/smyld.jar"); Attributes
	 * att = new Attributes();
	 * att.putValue(Attributes.Name.MAIN_CLASS.toString(),"org.smyld.com.Tester");
	 * outStream.createManifestFromAttribute(att); /*JarEntry manEntr = new
	 * JarEntry("META-INFO/MANIFEST.MF"); outStream.putNextEntry(manEntr);
	 * outStream.write((("Main-Class:
	 * org.smyld.app.bw.mgui.MainClass")+System.getProperty("line.separator")).getBytes());
	 * outStream.closeEntry(); outStream.close(); } catch (Exception
	 * e){e.printStackTrace();} }
	 */


	@SuppressWarnings("unused")
	private void testJarArchive() {
		try {
			FileOutputStream fout = new FileOutputStream("d:/temp/app.jar");
			FileInputStream fin = new FileInputStream(
					"d:/temp/projects/BW/classes/org.smyld/app/bw/mgui/MainClass.class");

			JarOutputStream jout = new JarOutputStream(fout);

			JarEntry newFile = new JarEntry(
					"org.smyld/app/bw/mgui/MainClass.class");
			jout.putNextEntry(newFile);
			int size = fin.available();
			byte[] data = new byte[size];
			fin.read(data);
			jout.write(data);
			jout.flush();
			jout.closeEntry();
			// JarFile jf = new JarFile("d:/temp/projects/BW/lib/SMYLD.jar");
			JarInputStream jin = new JarInputStream(new FileInputStream(
					"D:/cvs_home/SMYLD/SMYLD/deploy/smyld.jar"));
			JarEntry curInt = null;
			while ((curInt = jin.getNextJarEntry()) != null) {
				jout.putNextEntry(curInt);
				int curSize = (int) curInt.getSize();
				if (curSize != -1) {
					data = new byte[curSize];
					jin.read(data);
					jout.write(data);
				}
				jout.closeEntry();
			}
			// JarOutputStream jout = new JarOutputStream(fout);
			/*
			 * Enumeration enum = jf.entries();
			 * 
			 * int count = 0; while (enum.hasMoreElements()){ JarEntry cur =
			 * (JarEntry) enum.nextElement(); System.out.println("Entry : " +
			 * cur); try { jout.putNextEntry(cur); JarInputStream jin =
			 * (JarInputStream)jf.getInputStream(cur); //InputStream in =
			 * jf.getInputStream(cur); //jin. size = jin.available(); data = new
			 * byte[size]; jin.read(data); jout.write(data); jout.closeEntry(); }
			 * catch (Exception e) { e.printStackTrace(); } count++; }
			 * System.out.println(count + " found");
			 */
			jout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// JarOutputStream outjar = new JarOutputStream(
	}

	@SuppressWarnings("unused")
	private void testJarArchiveRead() {
		try {
			// FileInputStream fin = new
			// FileInputStream("d:/temp/projects/BW/lib/SMYLD.jar");
			JarFile jf = new JarFile("d:/temp/projects/BW/lib/SMYLD.jar");
			// JarOutputStream jout = new JarOutputStream(fout);

			int count = 0;
			for (Enumeration<JarEntry> enums = jf.entries(); enums
					.hasMoreElements();) {
				System.out.println("Entry : " + enums.nextElement());
				count++;
			}
			System.out.println(count + " found");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// JarOutputStream outjar = new JarOutputStream(
	}

	/*
	 * private void testXML() throws Exception{ GUIPanelGenerator generator =
	 * new GUIPanelGenerator(); generator.testXML(new
	 * File(Constants.FILE_TEST_WINDOW)); }
	 */
	@SuppressWarnings("unused")
	private void testXML() throws Exception {
		@SuppressWarnings("unused")
		ApplicationGenerator appGenerator = new ApplicationGenerator();
		@SuppressWarnings("unused")
        PortalAppXMLSetReaderPESwing xmlAppReader = new PortalAppXMLSetReaderPESwing("Application.xml",null);
		// appGenerator.setAppReader(xmlAppReader);
		// appGenerator.generateApplication();
		FileSystem.redirectSysOutput(new File("portal.log"));
	}
	/*
	private void testWindowCreated() {
		try {
			UIManager
					.setLookAndFeel("com.jgoodies.plaf.plastic.PlasticXPLookAndFeel");
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JFrame testingFrame = new JFrame("Testing Window");
		testingFrame.setSize(400, 250);
		/*
		 * JSplitPane split = new JSplitPane(); createdFrame = new
		 * AccountFrame(this); createdFrame.init();
		 * split.setRightComponent(createdFrame);
		 * testingFrame.getContentPane().add(split); //
		 
		// testingFrame.getContentPane().add(createdFrame);
		createdPanel = new WindowPanel(this);
		createdPanel.init();
		testingFrame.getContentPane().add(createdPanel);
		testingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testingFrame.show();

	}*/

	public static void main(String[] args) {
		try {
			new Tester();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	public void cancelPressed() {
		createdPanel.AccountNo.setText("Cancel Pressed");
	}

	public void okPressed() {
		createdPanel.AccountName.setText("Ok Pressed");
	}
*/
	public void radioButton() {
		System.out.println("Radio button activated");
	}

	public void checkBox() {
		System.out.println("Check Box clicked");
	}
	/*
	public void accountNameFocus() {
		createdPanel.AccountName.setText("I Got Focus");
	}

	public void accountNoLostFocus() {
		createdPanel.AccountNo.setText("I Lost Focus");
	}

	public void addItem() {
		itemCounter++;
		createdPanel.accountCategory.addItem("Item " + itemCounter);

	}
*/
	// Application reader interface methodes

	public HashMap<String,Element> loadWindows() {
		return null;
	}

	public HashMap<String,Element> loadPanels() {
		return null;
	}

	public HashMap<String, MenuItem> loadMenus() {
		return null;
	}

	public HashMap<String, PEAction> loadActions() {
		return null;
	}

	public String getMainClassPackage() {
		return "org.smyld.app.mgui";
	}

	@Override
	public String getGroup() {
		return null;
	}

	public String getHomePath() {
		return "d:\\temp\\projects\\bankworks";
	}

	public String getSourcePath() {
		return "d:\\temp\\projects\\bankworks\\src";
	}

	public String getClassPath() {
		return "d:\\temp\\projects\\bankworks\\classes";
	}

	public String getComponentType() {
		return "swing";
	}

	public HashMap<String,String> loadLibraries() {
		return null;
	}

	public String getLAF() {
		return null;
	}

	public String getTargetJarName() {
		return null;
	}

	public String getTargetJarPath() {
		return null;
	}

	public String getAppStartupClass() {
		return null;
	}

	public String getAppManagerClass() {
		return null;
	}

	public LogFile getLogFile() {
		return null;
	}

	public HashMap<String,LangSource> loadLanguages() {
		return null;
	}

	public FileInfo getAppSettingsFile() {
		return null;
	}

	public HashMap<String,String> loadImages() {
		return null;
	}

	public HashMap<String,String> loadSourceImages() {
		return null;
	}

	public String getAppSettingsSourceType() {
		return null;
	}

	public HashMap<String, GUIToolbar> loadToolbars() {
		return null;
	}

	public HashMap<String,String> loadResources() {
		return null;
	}

	public Vector<DeploymentDescriptor> loadDeployments() {
		return null;
	}

	public AppSecurity getAppSecurity() {
		return null;
	}

	public FileInfo getAppJarFile() {
		return null;
	}

	public String getAppName() {
		return null;

	}

	public String getAppType() {
		return null;
	} // */

	public Element getSplash() {
		return null;
	}
	

	  public void systemTray() {
	    Runnable runner = new Runnable() {
	      public void run() {
	        if (SystemTray.isSupported()) {
	          SystemTray tray = SystemTray.getSystemTray();
	          Image image = Toolkit.getDefaultToolkit().getImage("D:/projects/JDev/TestApplications/SMYLDFormatsConsole/sources/images/console_16.png");
	          /*
	          PopupMenu popup = new PopupMenu();
	          MenuItem item     = new MenuItem("Show Console");
	          MenuItem exitItem = new MenuItem("Exit..");
	          exitItem.addActionListener(new ActionListener(){
	        	  public void actionPerformed(ActionEvent evt){
	        		  System.exit(0);
	        	  }
	        	  
	          });
	          popup.add(item);
	          popup.addSeparator();
	          popup.add(exitItem);
	          
	          TrayIcon trayIcon = new TrayIcon(image, "SMYLD Formats Console", popup);
	          */
	          trayIcon = new TrayIcon(image, "SMYLD Formats Console", null);
	          final JPopupMenu jpopup = new JPopupMenu();

	          JMenuItem javaCupMI = new JMenuItem("Show Console", new ImageIcon("D:/projects/JDev/TestApplications/SMYLDFormatsConsole/sources/images/formatsConsole_16.png"));
	          javaCupMI.addActionListener(new ActionListener(){
	        	  public void actionPerformed(ActionEvent evt){
	        		  showMainWindow();
	        	  }
	          });
	          jpopup.add(javaCupMI);

	          JMenuItem smyldSite = new JMenuItem("Our Site", new ImageIcon("D:/projects/JDev/TestApplications/SMYLDFormatsConsole/sources/images/smyldhve_16.png"));
	          javaCupMI.addActionListener(new ActionListener(){
	        	  public void actionPerformed(ActionEvent evt){
	        		  try {
						java.awt.Desktop.getDesktop().browse(new URI("http://www.smyld.com"));
					} catch (IOException e) {
						e.printStackTrace();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
	        	  }
	          });
	          jpopup.add(javaCupMI);
	          jpopup.add(smyldSite);
	          jpopup.addSeparator();
	          JMenuItem exitMI = new JMenuItem("Exit", new ImageIcon("D:/projects/JDev/TestApplications/SMYLDFormatsConsole/sources/images/close_16.png"));
	          exitMI.addActionListener(new ActionListener(){
	        	  public void actionPerformed(ActionEvent evt){
	        		  System.exit(0);
	        	  }
	        	  
	          });
	          jpopup.add(exitMI);
	          
	          trayIcon.addMouseListener(new MouseAdapter() {
	              public void mouseReleased(MouseEvent e) {
	                  if (e.isPopupTrigger()) {
	                      jpopup.setLocation(e.getX(), e.getY());
	                      jpopup.setInvoker(jpopup);
	                      jpopup.setVisible(true);
	                  }
	              }
	          });


	          try {
	            tray.add(trayIcon);
	          } catch (AWTException e) {
	            System.err.println("Can't add to tray");
	          }
	        } else {
	          System.err.println("Tray unavailable");
	        }
	      }
	    };
	    EventQueue.invokeLater(runner);
	  }
	  private void showMainWindow(){
		  if (mdiWindow==null){
			  mdiWindow = new JFrame("Application Main Window");
			  mdiWindow.setIconImage(new ImageIcon("D:/projects/JDev/TestApplications/SMYLDFormatsConsole/sources/images/formatsConsole_16.png").getImage());
			  mdiWindow.setSize(new Dimension(800,600));
			  mdiWindow.setLocationByPlatform(true);
			  mdiWindow.addWindowListener(new WindowAdapter(){
				  public void windowIconified(WindowEvent e){
					  mdiWindow.setVisible(false);
				  }
			  });
		  //*
		  }else{
			  if (mdiWindow.getState()==JFrame.ICONIFIED){
				  mdiWindow.setState(JFrame.NORMAL);
				  mdiWindow.setAlwaysOnTop(true);
				  trayIcon.setImage(new ImageIcon("D:/projects/JDev/TestApplications/SMYLDFormatsConsole/sources/images/monitor_16.png").getImage());
				  
			  }
			//*/  
		  }
		  mdiWindow.setVisible(true);
	  }
	  JFrame   mdiWindow ;
	  TrayIcon trayIcon;

	public boolean containsPanelID(String panelID) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean compileClasses() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean createJarFile() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean exportLibraries(){return false;}
	public void setVariableMapper(SettingsVariablesMapper mapper){
		
	}

	@Override
	public ProjectBuilder getProjectBuilder() {
		return null;
	}

	public InputStream getSourceStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GUIToolkit getGUIToolkit() {
		return GUIToolkit.swing;
	}

	@Override
	public ApplicationType getType() {
		return ApplicationType.Desktop;
	}

	@Override
	public LayoutType getLayout() {
		return LayoutType.XML;
	}
}
