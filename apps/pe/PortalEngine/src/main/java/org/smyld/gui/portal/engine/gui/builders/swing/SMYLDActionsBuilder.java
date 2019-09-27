package org.smyld.gui.portal.engine.gui.builders.swing;

import org.smyld.app.pe.model.gui.PEAction;
import org.smyld.gui.Actions;
import org.smyld.gui.portal.engine.ApplicationGenerator;
import org.smyld.lang.script.java.JavaClassBody;
import org.smyld.lang.script.java.JavaMethod;
import org.smyld.lang.script.util.Variable;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class SMYLDActionsBuilder extends SMYLDSwingGUIBuilder {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JavaClassBody actionsFactoryClass;
	JavaMethod actionsCreateMethod;
	JavaMethod constructor;
	Actions addedActions;

	/**
	 * 
	 * @see
	 * @since
	 */
	public SMYLDActionsBuilder() {

	}

	public void buildNewClass() {
		addedActions = null;
		actionsFactoryClass = new JavaClassBody(ACT_FCTR_CLASS_NAME,
				activeApplication.getAppReader().getMainClassPackage(),
				CLASS_NAME_APP_ACT_FCT, true, false);
		constructor = new JavaMethod(ACT_FCTR_CLASS_NAME,
				Variable.SCOPE_PUBLIC, null, true);
		constructor.addParameter(ACT_FCTR_METH_PARM_ACT_HDL,
				CLASS_NAME_ACTION_HANDLER);
		actionsFactoryClass.addImport(CLASS_NAME_FP_ACTION_HANDLER);
		constructor.addCodeLine("super(" + ACT_FCTR_METH_PARM_ACT_HDL + ")");
		// menuFactoryClass.addImport(GUIConstants.CLASS_NAME_FP_SMYLD_MNUBAR);
	}

	public JavaClassBody getActionFactoryClass() {
		if ((addedActions != null) && (addedActions.size() > 0)) {
			actionsFactoryClass.addMethod(actionsCreateMethod);
			// constructor.addCodeLine(MENU_FCTR_METH_ACTIONS_CREATE +"()");
		}
		actionsFactoryClass.addConstructors(constructor);
		return actionsFactoryClass;
	}

	public void generateAction(PEAction newAction) {

		// First time creation
		if (addedActions == null) {
			addedActions = new Actions();
			actionsCreateMethod = new JavaMethod(ACT_FCTR_METH_ACTIONS_CREATE,
					Variable.SCOPE_PUBLIC, "void");
			//actionsCreateMethod.addCodeLine(ACT_FCTR_INSTANCE_ACTIONS
			//		+ " = new " + CLASS_NAME_ACTIONS + "()");
			// JavaVariable actionsVariable = new
			// JavaVariable(ACT_FCTR_INSTANCE_ACTIONS,JavaVariable.SCOPE_PUBLIC,CLASS_NAME_ACTIONS);
			// actionsFactoryClass.addVariable(actionsVariable);
			//actionsFactoryClass.addImport(CLASS_NAME_FP_ACTIONS);
			//actionsFactoryClass.addImport(CLASS_NAME_FP_GUI_ACTION);
			actionsFactoryClass.addImport(CLASS_NAME_FP_PE_ACTION);
			actionsFactoryClass.addImport(CLASS_NAME_FP_USER_CONSTRAINT);
			actionsFactoryClass.addImport(CLASS_NAME_FP_APP_ACT_FCT);

		}
		// Adding the action
		if (!addedActions.isActionExist(newAction)) {
			addedActions.addAction(newAction);
			actionsCreateMethod.addCodeLine(CLASS_NAME_PE_ACTION + " " + newAction.getID() + " = new "+ CLASS_NAME_PE_ACTION + "()");
			actionsCreateMethod.addCodeLine(newAction.getID() + ".setID(\""	+ newAction.getID() + "\")");
			actionsCreateMethod.addCodeLine(newAction.getID() + ".setLabel("	+ ApplicationGenerator.getTranslate(MODULE_NAME_ACTIONS,
							"\"" + newAction.getID() + "\"",getTextValue(newAction.getLabel())) + ")");
			if (newAction.getParameters() != null) 
				actionsCreateMethod.addCodeLine(newAction.getID()+ ".setParameters(\"" + newAction.getParameters()+ "\")");
			if (newAction.getTarget() != null) 
				actionsCreateMethod.addCodeLine(newAction.getID()+ ".setTarget(\"" + newAction.getTarget() + "\")");
			if (newAction.getCommand() != null) 
				actionsCreateMethod.addCodeLine(newAction.getID()+ ".setCommand(\"" + newAction.getCommand() + "\")");
			if (newAction.getUserObject() != null) 
				actionsCreateMethod.addCodeLine(newAction.getID()+ ".setUserObject(\"" + newAction.getUserObject()+ "\")");
			if (newAction.getUserConstraint()!=null)
				actionsCreateMethod.addCodeLine(createAddConstraintCodeLine(newAction.getUserConstraint()));
			if (newAction.getIconText() != null) 
				actionsCreateMethod.addCodeLine(newAction.getID()+ ".setIcon( " + APP_MAIN_CLASS_NAME + "." + APP_METH_GET_IMAGE + "(\"" + newAction.getIconText()+ "\"))");

			actionsCreateMethod.addCodeLine(ACT_FCTR_INSTANCE_ACTIONS + ".put(\"" + newAction.getID()  + "\"," + newAction.getID() + ")");
							
		}
	}

}
