package org.smyld.gui.portal.engine.gui.builders.swing;

import java.util.Vector;

import org.smyld.SMYLDObject;
import org.smyld.app.pe.model.Constants;
import org.smyld.app.pe.model.gui.GUIComponent;
import org.smyld.app.pe.model.gui.GUIWindow;
import org.smyld.app.pe.model.user.UserConstraint;
import org.smyld.gui.GUIAction;
import org.smyld.gui.GUIConstants;
import org.smyld.gui.portal.engine.Application;
import org.smyld.gui.portal.engine.ApplicationGenerator;
import org.smyld.gui.portal.engine.sources.PESwingApplicationReader;
import org.smyld.lang.script.java.JavaClassBody;
import org.smyld.lang.script.java.JavaInterface;
import org.smyld.lang.script.java.JavaMethod;
import org.smyld.lang.script.util.Variable;

import javax.inject.Inject;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class SMYLDSwingGUIBuilder extends SMYLDObject implements GUIConstants,
		Constants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Application activeApplication;

	@Inject
	protected PESwingApplicationReader appReader;

	private boolean doAddMethod = true;

	/**
	 * 
	 * @see
	 * @since
	 */
	public SMYLDSwingGUIBuilder() {


	}

	public void setActiveApplication(Application newApplication) {
		activeApplication = newApplication;
		appReader = activeApplication.getAppReader();
	}

	protected void createListeners(GUIComponent component, Vector<String> listeners,
								   JavaClassBody newClass, JavaInterface newInterface,
								   String interfaceInstance) {
		String compID = component.getID();
		if (compID.equals(newClass.getName())) {
			compID = "this";
		}

		boolean inMultiEventMode = false;
		if ((component.getEvents() != null)
				&& (component.getEvents().size() > 0)) {
			if ((component instanceof GUIWindow)
					&& (component.getEvents().size() > 1)) {
				addWindowImportClassesAndLines(component, listeners, newClass);
				inMultiEventMode = true;
			}
			for (String methodName : component.getEvents().keySet()) {
				doAddMethod = true;
				String eventType = component.getEvents().get(
						methodName);
				JavaMethod newMethod = null;
				// System.out.println("New Event of type " + eventType + " must
				// call " +
				// methodName + " method");
				if (eventType.toLowerCase().equals(TAG_COMP_ATT_ON_CLK)) {
					newClass.addImport(CLASS_NAME_FP_ACT_EVNT);
					newClass.addImport(CLASS_NAME_FP_ACT_LSTN);
					listeners.add(compID
							+ ".addActionListener(new ActionListener(){");
					listeners
							.add("public void actionPerformed(ActionEvent evt){");
					doHandleMethod(newClass, newInterface, listeners,
							interfaceInstance, methodName);
					listeners.add("}");
					listeners.add("})");
				} else if (eventType.toLowerCase().equals(
						TAG_COMP_ATT_ON_CHANGE)) {
					/*
					newClass.addImport(CLASS_NAME_FP_ITM_LISTN);
					newClass.addImport(CLASS_NAME_FP_ITM_EVNT);
					listeners.add(compID
							+ ".addItemListener(new ItemListener(){");
					listeners
							.add("public void itemStateChanged(ItemEvent evt){");
					doHandleMethod(newClass, newInterface, listeners,
							interfaceInstance, methodName);
					listeners.add("}");
					listeners.add("})");
					*/
					newClass.addImport(CLASS_NAME_FP_CHANGE_LIST);
					listeners.add(compID
							+ ".addSMYLDChangeListener(new SMYLDChangeListener(){");
					listeners
							.add("public void newChange(Object evt){");
					doHandleMethod(newClass, newInterface, listeners,
							interfaceInstance, methodName);
					listeners.add("}");
					listeners.add("})");

					
				} else if (eventType.toLowerCase().equals(
						TAG_COMP_ATT_ON_FOC_LOST)) {
					newClass.addImport(CLASS_NAME_FP_FOC_EVNT);
					newClass.addImport(CLASS_NAME_FP_FOC_ADPT);
					listeners.add(compID
							+ ".addFocusListener(new FocusAdapter(){");
					listeners.add("public void focusLost(FocusEvent evt){");
					doHandleMethod(newClass, newInterface, listeners,
							interfaceInstance, methodName);
					listeners.add("}");
					listeners.add("})");
				} else if (eventType.toLowerCase().equals(
						TAG_COMP_ATT_ON_FOC_GOT)) {
					newClass.addImport(CLASS_NAME_FP_FOC_EVNT);
					newClass.addImport(CLASS_NAME_FP_FOC_ADPT);
					listeners.add(compID
							+ ".addFocusListener(new FocusAdapter(){");
					listeners.add("public void focusGained(FocusEvent evt){");
					doHandleMethod(newClass, newInterface, listeners,
							interfaceInstance, methodName);
					listeners.add("}");
					listeners.add("})");
				} else if ((eventType.toLowerCase()
						.equals(TAG_COMP_ATT_ON_MOUS_CLK))
						|| (eventType.toLowerCase()
								.equals(TAG_COMP_ATT_ON_MOUS_DBL_CLK))
						|| (eventType.toLowerCase()
								.equals(TAG_COMP_ATT_ON_MOUS_R_CLK))) {
					String clickCount = "1";
					String buttonClicked = "BUTTON1";
					if (eventType.toLowerCase().equals(
							TAG_COMP_ATT_ON_MOUS_DBL_CLK)) {
						clickCount = "2";
					}
					if (eventType.toLowerCase().equals(
							TAG_COMP_ATT_ON_MOUS_R_CLK)) {
						buttonClicked = "BUTTON3";
					}
					newClass.addImport(CLASS_NAME_FP_MOUS_EVNT);
					newClass.addImport(CLASS_NAME_FP_MOUS_ADPT);
					listeners.add(compID
							+ ".addMouseListener(new MouseAdapter(){");
					listeners.add("public void mouseClicked(MouseEvent evt){");
					listeners.add("if (evt.getClickCount()==" + clickCount
							+ "){");
					listeners.add("if (evt.getButton()==evt." + buttonClicked
							+ "){");
					doHandleMethod(newClass, newInterface, listeners,
							interfaceInstance, methodName);
					listeners.add("}");
					listeners.add("}");
					listeners.add("}");
					listeners.add("})");
				} else if (eventType.toLowerCase()
						.equals(TAG_COMP_ATT_ON_CLOSE)) {
					if (!inMultiEventMode) {
						addWindowImportClassesAndLines(component, listeners,
								newClass);
					}
					addWindowListenerMethodes(component, listeners, methodName,
							interfaceInstance, "Closing");
					if (!inMultiEventMode) {
						listeners.add("})");
					}
				} else if (eventType.toLowerCase().equals(
						TAG_COMP_ATT_ON_WIN_FOC_GOT)) {
					if (!inMultiEventMode) {
						addWindowImportClassesAndLines(component, listeners,
								newClass);
					}
					addWindowListenerMethodes(component, listeners, methodName,
							interfaceInstance, "GainedFocus");
					if (!inMultiEventMode) {
						listeners.add("})");
					}
				} else if (eventType.toLowerCase().equals(
						TAG_COMP_ATT_ON_ACTIVE)) {
					if (!inMultiEventMode) {
						addWindowImportClassesAndLines(component, listeners,
								newClass);
					}
					addWindowListenerMethodes(component, listeners, methodName,
							interfaceInstance, "Activated");
					if (!inMultiEventMode) {
						listeners.add("})");
					}
				} else if (eventType.toLowerCase().equals(
						TAG_COMP_ATT_ON_DEACTIVE)) {
					if (!inMultiEventMode) {
						addWindowImportClassesAndLines(component, listeners,
								newClass);
					}
					addWindowListenerMethodes(component, listeners, methodName,
							interfaceInstance, "Deactivated");
					if (!inMultiEventMode) {
						listeners.add("})");
					}
				} else if (eventType.toLowerCase().equals(
						TAG_COMP_ATT_ON_ICONIFY)) {
					if (!inMultiEventMode) {
						addWindowImportClassesAndLines(component, listeners,
								newClass);
					}
					addWindowListenerMethodes(component, listeners, methodName,
							interfaceInstance, "Iconified");
					if (!inMultiEventMode) {
						listeners.add("})");
					}
				} else if (eventType.toLowerCase().equals(
						TAG_COMP_ATT_ON_DEICONIFY)) {
					if (!inMultiEventMode) {
						addWindowImportClassesAndLines(component, listeners,
								newClass);
					}
					addWindowListenerMethodes(component, listeners, methodName,
							interfaceInstance, "Deiconified");
					if (!inMultiEventMode) {
						listeners.add("})");
					}
				} else if (eventType.toLowerCase().equals(TAG_COMP_ATT_ON_OPEN)) {
					if (!inMultiEventMode) {
						addWindowImportClassesAndLines(component, listeners,
								newClass);
					}
					addWindowListenerMethodes(component, listeners, methodName,
							interfaceInstance, "Opened");
					if (!inMultiEventMode) {
						listeners.add("})");
					}
				}
				if (doAddMethod) {
					newMethod = new JavaMethod(methodName,
							Variable.SCOPE_PUBLIC, null);
					newMethod.addModifier(JavaClassBody.MODIFIER_ABSTRACT);
					if (newInterface.getMethod(newMethod.getName()) == null) {
						newInterface.addMethod(newMethod);
					}
				}
			}
			if (inMultiEventMode) {
				listeners.add("})");
			}
		}
	}

	protected void doHandleMethod(JavaClassBody classBody,
			JavaInterface classInterface, Vector<String> codeLines,
			String interfaceInstance, String methodName) {
		if (isActionCall(methodName)) {
			String actionName = methodName.substring(TAG_ATT_METH_ACTION
					.length());
			if ((actionName != null) && (appReader.loadActions() != null)) {
				GUIAction targetAction = appReader.loadActions()
						.get(actionName);
				if (targetAction != null) {
					if (targetAction.getCommand().equals(
							GUIAction.COM_OPEN_POPUP)) {
						classBody.addImport(CLASS_NAME_FP_SMYLD_POPUP_MENU);
						classBody.addImport("java.awt.Component");
						classInterface
								.addParentClass(CLASS_NAME_ACTION_HANDLER);
						classInterface.addImport(CLASS_NAME_FP_ACTION_HANDLER);
						String menuName = targetAction.getTarget();
						// String mnuFctMeth =
						// AppMenuFactory.createMethodNameAsPopup(menuName);
						codeLines.add(CLASS_NAME_SMYLD_POPUP_MENU
								+ " popMenu = "
								+ SMYLDSwingMenuBuilder.getRefAsPopup(menuName,
										interfaceInstance));
						codeLines
								.add("popMenu.show((Component)evt.getSource(),evt.getX(),evt.getY())");
						doAddMethod = false;
					}
				}
			}
		} else {
			codeLines.add("if (" + interfaceInstance + "!=null){");
			codeLines.add(interfaceInstance + "." + methodName + "()");
			codeLines.add("}");
			codeLines.add("else{");
			codeLines.add(APP_MAIN_CLASS_NAME+ ".instance.handleAction(\"" + classBody.getName() + "\",\""+ methodName +"\",instance)");
			codeLines.add("}");
		}
	}
	protected String createAddConstraintCodeLine(UserConstraint userConst){
		return "addConstraint(new UserConstraint(\"" + userConst.getCompID() +
			"\",\"" + userConst.getAllRoles() + "\",\"" + userConst.getAllShowRoles() +
			"\"))";
	}

	private boolean isActionCall(String methodName) {
		return (methodName.startsWith(TAG_ATT_METH_ACTION));
	}

	private void addWindowImportClassesAndLines(GUIComponent curWindow,
			Vector<String> listeners, JavaClassBody newClass) {
		if (isInternalFrame(curWindow)) {
			// We must create the window
			newClass.addImport(CLASS_NAME_FP_IF_ADPT);
			newClass.addImport(CLASS_NAME_FP_IF_EVNT);
			listeners
					.add("this.addInternalFrameListener(new InternalFrameAdapter(){");
		} else {
			// We must create the window
			newClass.addImport(CLASS_NAME_FP_WIN_EVNT);
			newClass.addImport(CLASS_NAME_FP_WIN_ADPT);
			listeners.add("this.addWindowListener(new WindowAdapter(){");
		}
	}

	private boolean isInternalFrame(GUIComponent component) {
		if (component instanceof GUIWindow) {
			return (((GUIWindow) component).getType()
					.equals(TAG_ATT_WINDOW_TYPE_INTERNAL));
		}
		return false;
	}

	protected void addWindowListenerMethodes(GUIComponent component,
			Vector<String> listeners, String methodName, String interfaceInstance,
			String eventMethodName) {
		String className = "window";
		String eventName = "Window";
		if (isInternalFrame(component)) {
			className = "internalFrame";
			eventName = "InternalFrame";
		}
		listeners.add("public void " + className + eventMethodName + "("
				+ eventName + "Event evt){");
		listeners.add(interfaceInstance + "." + methodName + "()");
		listeners.add("}");
	}

	public static String getSetToolTipText(String module, String id,
			String tooltipValue) {
		return id
				+ ".setToolTipText("
				+ ApplicationGenerator.getTranslateTP(module, "\"" + id + "\"",
						"\"" + tooltipValue + "\"") + ")";
	}

	public String getTextValue(String value) {
		if (value == null) {
			return "null";
		}
		return "\"" + value + "\"";
	}

	public String createApplyCompOrientation(String itemID) {
		return itemID + "." + SWING_METH_APPLY_COMP_ORIENT + "("
				+ APP_MAIN_CLASS_NAME + "." + APP_METH_GET_APP_ORIENT + "())";
	}

	public void addFocusLostEvent(String compID, JavaClassBody newClass,
			Vector<String> codeLines) {
		newClass.addImport(CLASS_NAME_FP_FOC_EVNT);
		newClass.addImport(CLASS_NAME_FP_FOC_ADPT);
		codeLines.add(compID + ".addFocusListener(new FocusAdapter(){");
		codeLines.add("public void focusLost(FocusEvent evt){");
		// doHandleMethod(newClass,newInterface,listeners,interfaceInstance,methodName);
		codeLines.add("}");
		codeLines.add("})");
	}

}
