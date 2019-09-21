package org.smyld.gui.portal.engine.gui.builders.swing;

import java.util.Vector;

import org.smyld.app.pe.model.Constants;
import org.smyld.app.pe.model.gui.GUIWindow;
import org.smyld.gui.GUIConstants;
import org.smyld.gui.portal.engine.ApplicationGenerator;
import org.smyld.lang.script.java.InterfaceMethod;
import org.smyld.lang.script.java.JavaClassBody;
import org.smyld.lang.script.java.JavaConstants;
import org.smyld.lang.script.java.JavaInterface;
import org.smyld.lang.script.java.JavaMethod;
import org.smyld.lang.script.java.JavaVariable;
import org.smyld.lang.script.util.Variable;
import org.smyld.text.TextUtil;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class SMYLDSwingWindowBuilder extends SMYLDSwingGUIBuilder implements
		Constants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JavaClassBody   windowsFactoryClass;
	JavaMethod      openWindow;
	Vector<JavaMethod> specificWindowMethods = new Vector<JavaMethod>();
	JavaClassBody   windowFrame;
	JavaInterface   windowListener;
	JavaMethod      initMethod;
	JavaMethod      constructMethod;
	GUIWindow windowObject;
	String          currentAppName;
	String          windowNameParm     = "windowName";
	String          appManagerParm     = "appManager";
	String          incomingActionParm = "incomingAction";
	Vector<String>  listenerCodeLines;
	boolean         firstIf;

	/**
	 * 
	 * @see
	 * @since
	 */
	public SMYLDSwingWindowBuilder() {
	}

	private void init() {
		currentAppName = activeApplication.getName();
		windowsFactoryClass = new JavaClassBody(WINDS_FCTR_CLASS_NAME,
				appReader.getMainClassPackage(), APP_WINDS_FCTR_CLASS_NAME,
				true, false);
		windowsFactoryClass.addImport(APP_WINDS_FCTR_FP_CLASS_NAME);
		windowsFactoryClass.addImport(GUIConstants.CLASS_NAME_FP_GUI_ACTION);
		windowsFactoryClass.addImport(CLASS_NAME_FP_WIND_LIST);
		windowsFactoryClass.addImport("java.awt.Window");
		openWindow = new JavaMethod(WINDS_FCTR_METH_OPEN_WIN,Variable.SCOPE_PUBLIC, "void");
		openWindow.addParameter(windowNameParm, "String");
		openWindow.addParameter(appManagerParm, APP_MANAGER_INTERFACE_NAME);
		openWindow.addParameter(incomingActionParm,	GUIConstants.CLASS_NAME_GUI_ACTION);

	}

	public JavaClassBody[] generateWindow(GUIWindow WindowObject)throws Exception {
		if (windowsFactoryClass == null) init();
		if (!currentAppName.equals(activeApplication.getName())) init();
		windowObject = WindowObject;
		listenerCodeLines = new Vector<String>();
		windowFrame = getProperWindowClass();
		windowFrame.addImport(GUIConstants.CLASS_NAME_FP_GUI_ACTION);
		handleWindowListener();
		windowFrame.addImport(appReader.getMainClassPackage() + "." + APP_MAIN_CLASS_NAME);
		windowFrame.addImport(CLASS_NAME_FP_WIND_LIST);
		initMethod = new JavaMethod("init", Variable.SCOPE_PRIVATE, "void");
		constructMethod = new JavaMethod(windowObject.getID(),Variable.SCOPE_PUBLIC, null, true);
		String listenerParam = "active" + windowObject.getID() + "Listener";
		if (windowFrame.getParentClassName().equals(CLASS_NAME_SMYLD_DIALOG)) {
			constructMethod.addParameter("owner", "Window");
			//constructMethod.addParameter("owner", CLASS_NAME_SMYLD_FRAME);
			constructMethod.addCodeLine("super(owner)");
			//windowFrame.addImport(CLASS_NAME_FP_SMYLD_FRAME);
			windowFrame.addImport("java.awt.Window");
			
		}
		// COMPONENT ORIENTATION
		constructMethod.addCodeLine(SWING_METH_APPLY_COMP_ORIENT + "("+ APP_MAIN_CLASS_NAME + "." + APP_METH_GET_APP_ORIENT + "())");
		constructMethod.addParameter(listenerParam, windowObject.getID()+ "Listener");
		constructMethod.addParameter("incomingAction",GUIConstants.CLASS_NAME_GUI_ACTION);
		constructMethod.addCodeLine("if(" + listenerParam + " == null){");
		constructMethod.addPrintingLineCode("Warning : " + windowObject.getID()	+ "Listener is not available ");
		constructMethod.addCodeLine("}");
		constructMethod.addCodeLine("else{");
		constructMethod.addCodeLine(listenerParam+ ".openAction(incomingAction)");
		constructMethod.addCodeLine("}");
		// constructMethod.addCodeLine(WIN_INSTANCE_LISTENER + " = (" +
		// windowObject.getID() + "Listener" + ")" + listenerParam );
		constructMethod.addCodeLine(WIN_INSTANCE_LISTENER + " = "+ listenerParam);
		constructMethod.addCodeLine("init()");
		// constructMethod.addCodeLine("pack()");
		if (windowObject.getStartUpMethod() != null) {
			initMethod.addCodeLine("if(" + WIN_INSTANCE_LISTENER + " != null){");
			initMethod.addCodeLine(WIN_INSTANCE_LISTENER + "."+ windowObject.getStartUpMethod() + "()");
			initMethod.addCodeLine("}");
		}

		if (windowObject.getMenuBarID() != null) {
			processMenuBar();
		}
		if (windowObject.getToolbarID() != null) {
			processToolBar();
		}
		if (windowObject.getStatusBar() != null) {
			processStatusBar();
		}

		if (windowObject.getBgColor() != null) {
			initMethod.addCodeLine(SMYLD_MDI_METH_SET_BG_COLOR + "(\"0x"
					+ windowObject.getBgColor() + "\")");
		}
		if (windowObject.getLabel() != null) {
			initMethod.addCodeLine("setTitle("
					+ ApplicationGenerator.getTranslate(MODULE_NAME_WINDOWS,
							"\"" + windowObject.getID() + "\"", "\""
									+ windowObject.getLabel() + "\"") + ")");
		}
		if (windowObject.getIcon() != null) {
			initMethod.addCodeLine("setIcon("
					+ ApplicationGenerator.getImage(windowObject.getIcon())
					+ ")");
		}
		if ((windowObject.getWidth() != null) && (windowObject.getHeight() != null)) {
			if ((TextUtil.isNumeric(windowObject.getWidth()))&&(TextUtil.isNumeric(windowObject.getHeight()))){
				initMethod.addCodeLine("setSize(" + windowObject.getWidth() + ","+ windowObject.getHeight() + ")");
			}else if (TextUtil.isNumeric(windowObject.getWidth())){
				initMethod.addCodeLine("maximizeHeight(" + windowObject.getWidth() + ")");
			}else if (TextUtil.isNumeric(windowObject.getHeight())){	
				initMethod.addCodeLine("maximizeWidth(" + windowObject.getHeight() + ")");
			}else{ // if ((windowObject.getHeight().equals("max"))&&(windowObject.getWidth().equals("max"))){{
				initMethod.addCodeLine("maximizeFrame()");
			}
		}
		if (!windowFrame.getParentClassName().equals(
				GUIConstants.CLASS_NAME_SMYLD_IFRAME)) {
			initMethod.addCodeLine("centerWindowInScreen()");
		}
		if (windowObject.getBody() != null) {
			processWindowBody();
		}
		initMethod.addCodeLine("if(" + WIN_INSTANCE_LISTENER + " != null){");
		initMethod.addCodeLine(WIN_INSTANCE_LISTENER + "."
				+ WIN_METH_ACTIVE_WIN_HDL + "(this)");
		initMethod.addCodeLine("}");
		if ((windowObject.getResizable() != null)
				&& (windowObject.getResizable().equals(TAG_ATT_BOOLEAN_FLASE))) {
			initMethod.addCodeLine("setResizable(false)");
		}
		if (windowFrame.getParentClassName().equals(CLASS_NAME_SMYLD_DIALOG)) {
			initMethod.addCodeLine("setModal(true)");
		}

		createListeners(WindowObject, listenerCodeLines, windowFrame,
				windowListener, WIN_INSTANCE_LISTENER);
		if (listenerCodeLines.size() > 0) {
			initMethod.addCodeLines(listenerCodeLines);
		}
		windowFrame.addConstructors(constructMethod);

		windowFrame.addMethod(initMethod);
		addWindowOpen();
		JavaVariable listenerClassVariable = new JavaVariable(
				WIN_INSTANCE_LISTENER, JavaConstants.SCOPE_PROTECTED,
				windowObject.getID() + "Listener");
		windowFrame.addVariable(listenerClassVariable);

		JavaClassBody[] result = { windowFrame, windowListener };
		return result;
	}

	private void processMenuBar() {
		String windowActionHander = "null";
		// Checking if the action handler should be the very same window
		if ((windowObject.getMenuHandler() != null)
				&& (windowObject.getMenuHandler()
						.equals(TAG_ATT_LINK_HANDLER_SELF))) {
			windowListener.addImport(CLASS_NAME_FP_ACTION_HANDLER);
			windowListener.addParentClass(CLASS_NAME_ACTION_HANDLER);
			// windowFrame.addInterface(GUIConstants.CLASS_NAME_ACTION_HANDLER);
			// InterfaceMethod processActionMethod = new
			// InterfaceMethod(APP_METH_APP_GUI_ACTION,JavaMethod.SCOPE_PUBLIC,null);
			// processActionMethod.addParameter(APP_METH_PARM_ACTION,GUIConstants.CLASS_NAME_GUI_ACTION);
			// windowListener.addMethod(processActionMethod);
			windowActionHander = WIN_INSTANCE_LISTENER;

		}
		String refMenuBar = SMYLDSwingMenuBuilder.getRefAsBar(windowObject
				.getMenuBarID(), windowActionHander);
		initMethod.addCodeLine("setJMenuBar(" + refMenuBar + ")");

	}

	private void processToolBar() {
		String createCodeLine = SMYLDSwingToolbarBuilder.getRef(windowObject
				.getToolbarID());
		initMethod.addCodeLine("setToolbar(" + createCodeLine + ")");
		windowFrame.addImport(appReader.getMainClassPackage() + "."
				+ APP_MAIN_CLASS_NAME);
	}

	private void processStatusBar() {
		String createCodeLine = SMYLDSwingPanelBuilder.getRef(windowObject
				.getStatusBar());
		initMethod.addCodeLine("setStatusBar(" + createCodeLine + ")");
		windowFrame.addImport(appReader.getMainClassPackage() + "."
				+ APP_MAIN_CLASS_NAME);
	}

	private void addWindowOpen() {
		String objectInstance = windowObject.getID() + "Instance";
		windowsFactoryClass.addImport(windowObject.getClassImportName());
		windowsFactoryClass.addImport(windowObject.getClassImportName() + "Listener");
		openWindow.addCodeLine("if (" + windowNameParm + ".equals(\""+ windowObject.getID() + "\")){");
			openWindow.addCodeLine(windowObject.getID() + " " + objectInstance	+ " = null ");
			openWindow.addCodeLine("if (" +appManagerParm + "!=null){");
				String listenerClass = appManagerParm + ".get" + windowObject.getID()	+ "Listener()";
				if (windowFrame.getParentClassName().equals(CLASS_NAME_SMYLD_DIALOG)) {
					openWindow.addCodeLine(objectInstance + " = new " + windowObject.getID() + "("+ WINDS_FCTR_INST_ACTV_MDI + "," + listenerClass + ","+ incomingActionParm + ")");
				} else {
					openWindow.addCodeLine(objectInstance + " = new " + windowObject.getID() + "(" + listenerClass	+ "," + incomingActionParm + ")");
				}
			openWindow.addCodeLine("}");
			openWindow.addCodeLine("else{");
				if (windowFrame.getParentClassName().equals(CLASS_NAME_SMYLD_DIALOG)) {
					openWindow.addCodeLine(objectInstance + " = new " + windowObject.getID() + "("	+ WINDS_FCTR_INST_ACTV_MDI + ",null,"+ incomingActionParm + ")");
				} else {
					openWindow.addCodeLine(objectInstance + " = new " + windowObject.getID() + "(null," + incomingActionParm + ")");
				}
			openWindow.addCodeLine("}");
		
			// openWindow.addCodeLine(incomingActionParm + ".setUserObject(" +
			// objectInstance + ")");
			Vector<String> commonCode = new Vector<String>();
			commonCode.add(WINDS_FCTR_METH_ADD_WINDOW + "(\""+ windowObject.getID() + "\"," + objectInstance + ")");
			if (TAG_ATT_WINDOW_TYPE_MDI.equals(windowObject.getType())) {
				commonCode.add(WINDS_FCTR_INST_ACTV_MDI + "="+ objectInstance);
				commonCode.add(objectInstance + ".setVisible(true)");
			} else if (TAG_ATT_WINDOW_TYPE_NORMAL.equals(windowObject.getType())) {
				commonCode.add(objectInstance + ".setVisible(true)");
			} else if (TAG_ATT_WINDOW_TYPE_DIALOG.equals(windowObject.getType())) {
				// We will not set it to be visible until the porting application decides to because the code control will be lost in this case
				//commonCode.add(objectInstance + ".setAlwaysOnTop(true)");
				//commonCode.add(objectInstance + ".toFront()");
				//commonCode.add(objectInstance + ".setVisible(true)");
				
				
				
			} else {
				commonCode.add(WINDS_FCTR_INST_ACTV_MDI + "."+ SMYLD_MDI_METH_ADD_INT_FRM + "(" + objectInstance + ")");
			}
			openWindow.addCodeLines(commonCode);
			if (TAG_ATT_WINDOW_TYPE_DIALOG.equals(windowObject.getType())) {
				openWindow.addCodeLine(objectInstance + ".setVisible(true)");
			}
			/*
			openWindow.addCodeLine(WINDS_FCTR_METH_ADD_WINDOW + "(\""+ windowObject.getID() + "\"," + objectInstance + ")");
			if (TAG_ATT_WINDOW_TYPE_MDI.equals(windowObject.getType())) {
				openWindow.addCodeLine(WINDS_FCTR_INST_ACTV_MDI + "="+ objectInstance);
				openWindow.addCodeLine(objectInstance + ".setVisible(true)");
			} else if (TAG_ATT_WINDOW_TYPE_NORMAL.equals(windowObject.getType())) {
				openWindow.addCodeLine(objectInstance + ".setVisible(true)");
			} else if (TAG_ATT_WINDOW_TYPE_DIALOG.equals(windowObject.getType())) {
				openWindow.addCodeLine(objectInstance + ".setVisible(true)");
			} else {
				openWindow.addCodeLine(WINDS_FCTR_INST_ACTV_MDI + "."+ SMYLD_MDI_METH_ADD_INT_FRM + "(" + objectInstance + ")");
			}
			*/
			
	
		openWindow.addCodeLine("}");
		//*
		JavaMethod specificWindow = new JavaMethod("open" + windowObject.getID()+"Window" ,Variable.SCOPE_PUBLIC, windowObject.getID());
		specificWindow.addParameter("listener", windowObject.getID()	+ "Listener");
		specificWindow.addParameter("owner"   , "Window");
		
		specificWindow.addCodeLine(windowObject.getID() + " " + objectInstance	+ " = null ");
		if (windowFrame.getParentClassName().equals(CLASS_NAME_SMYLD_DIALOG)) {
			specificWindow.addCodeLine("if (owner!=null){");
			specificWindow.addCodeLine(objectInstance + " = new " + windowObject.getID() + "(owner,listener,null)");
			specificWindow.addCodeLine("}");
			specificWindow.addCodeLine("else{");
			specificWindow.addCodeLine(objectInstance + " = new " + windowObject.getID() + "("+ WINDS_FCTR_INST_ACTV_MDI + ",listener,null)");
			specificWindow.addCodeLine("}");
		} else {
			specificWindow.addCodeLine(objectInstance + " = new " + windowObject.getID() + "(listener,null)");
		}
		specificWindow.addCodeLines(commonCode);
		specificWindow.addCodeLine("return " + objectInstance);
		specificWindowMethods.add(specificWindow);
		// */
		// openWindow.addCodeLine("if (" + windowNameParm + ".equals(\"" +
		// windowObject.getID() + "\")){");
	}

	public JavaClassBody generateWindowFactory() throws Exception {
		windowsFactoryClass.addMethod(openWindow);
		for(JavaMethod curMeth:specificWindowMethods){
			windowsFactoryClass.addMethod(curMeth);
		}
		
		return windowsFactoryClass;

	}

	/**
	 * Handeling the creation of window class listener
	 */
	private void handleWindowListener() {
		windowListener = new JavaInterface(windowObject.getID() + "Listener",
				windowObject.getPackage(), CLASS_NAME_WIND_LIST, true);
		windowListener.addImport(CLASS_NAME_FP_WIND_LIST);
		windowListener.addImport(GUIConstants.CLASS_NAME_FP_GUI_ACTION);
		if (windowObject.getStartUpMethod() != null) {
			InterfaceMethod startUpmethod = new InterfaceMethod(windowObject
					.getStartUpMethod(), Variable.SCOPE_PUBLIC, "void");
			windowListener.addMethod(startUpmethod);
		}
		// adding the method for handing the current active winodw object
		// reference
		JavaMethod activeObjectHandle = new JavaMethod(WIN_METH_ACTIVE_WIN_HDL,
				Variable.SCOPE_PUBLIC, "void");
		activeObjectHandle.addModifier(JavaClassBody.MODIFIER_ABSTRACT);
		activeObjectHandle.addParameter("activeWindow", windowObject.getID());
		windowListener.addMethod(activeObjectHandle);
		// JavaMethod openActionMethod = new
		// JavaMethod(WIN_METH_OPEN_ACTION,JavaMethod.SCOPE_PUBLIC,"void");
		// openActionMethod.addModifier(JavaClassBody.MODIFIER_ABSTRACT);
		// openActionMethod.addParameter("incomingAction",GUIConstants.CLASS_NAME_GUI_ACTION);
		// windowListener.addMethod(openActionMethod);

		// public void openAction(GUIAction incomingAction);

	}

	private JavaClassBody getProperWindowClass() {
		String properClassName = CLASS_NAME_SMYLD_IFRAME;
		String properPackName = CLASS_NAME_FP_SMYLD_IFRAME;
		if (windowObject.getType() != null) {
			if (windowObject.getType().equals(TAG_ATT_WINDOW_TYPE_MDI)) {
				properClassName = CLASS_NAME_SMYLD_MDI;
				properPackName = CLASS_NAME_FP_SMYLD_MDI;
			} else if (windowObject.getType()
					.equals(TAG_ATT_WINDOW_TYPE_NORMAL)) {
				properClassName = CLASS_NAME_SMYLD_FRAME;
				properPackName = CLASS_NAME_FP_SMYLD_FRAME;
			} else if (windowObject.getType()
					.equals(TAG_ATT_WINDOW_TYPE_DIALOG)) {
				properClassName = CLASS_NAME_SMYLD_DIALOG;
				properPackName = CLASS_NAME_FP_SMYLD_DIALOG;
			}
		}
		JavaClassBody properClass = new JavaClassBody(windowObject.getID(),
				windowObject.getPackage(), properClassName, true, false);
		properClass.addImport(properPackName);
		return properClass;
	}

	private void processWindowBody() {
		// Checking for the exsitence of the class
		if ((windowObject.getBodyType() != null)
				& ((windowObject.getBodyType()
						.equals(TAG_COMP_CONT_DOCKABLE_DESKTOP)))) {
			// Code for adding the dockable desktop
			createWindowBody(CLASS_NAME_DOCKABLE_DESKTOP,
					CLASS_NAME_FP_DOCKABLE_DESKTOP);
			windowListener.addParentClass(CLASS_NAME_FP_DOCKABLE_DESKTOP_LST);
		} else if (activeApplication.isClassExist(windowObject.getBody())) {
			JavaClassBody targetClass = activeApplication.getClass(windowObject
					.getBody());
			createWindowBody(targetClass.getName(), targetClass
					.getFullPackageName());
		}
	}

	private void createWindowBody(String className, String classPackage) {
		JavaVariable bodyClassVar = new JavaVariable(windowObject.getBodyID(),
				Variable.SCOPE_PUBLIC, windowObject.getBody());
		windowFrame.addImport(classPackage);
		windowFrame.addVariable(bodyClassVar);
		initMethod.addCodeLine(windowObject.getBodyID() + " = new " + className
				+ "(" + WIN_INSTANCE_LISTENER + ")");
		if (!className.equals(CLASS_NAME_DOCKABLE_DESKTOP)) {
			initMethod.addCodeLine(windowObject.getBodyID() + ".init()");
			// initMethod.addCodeLine("this.getContentPane().add(" +
			// windowObject.getBodyID() + "." + PNL_CLASS_METH_GET_MAIN_COMP +
			// "())");
			initMethod.addCodeLine("addComponent(\"" + windowObject.getBodyID()
					+ "\"," + windowObject.getBodyID() + "."
					+ PNL_CLASS_METH_GET_MAIN_COMP + "())");
		} else {
			initMethod.addCodeLine("this.setContentPane("
					+ windowObject.getBodyID() + "," + windowObject.getBodyID()
					+ ")");
		}
		if ((windowObject.getBodyListenerTarget() != null)
				&& (windowObject.getBodyListenerTarget()
						.equals(TAG_ATT_LINK_LISTNR_WINDOW))) {
			JavaClassBody listenerInterface = activeApplication
					.getInterface(windowObject.getBody() + "Listener");
			if (listenerInterface != null) {
				windowListener.addParentClass(listenerInterface.getName());
				windowListener
						.addImport(listenerInterface.getFullPackageName());
			}
		}
		initMethod.addCodeLine(createApplyCompOrientation(windowObject
				.getBodyID()));

	}

}
