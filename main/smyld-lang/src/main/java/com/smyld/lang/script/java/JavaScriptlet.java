package com.smyld.lang.script.java;

import com.smyld.lang.script.util.Scriptlet;

public class JavaScriptlet extends Scriptlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JavaClassBody javaActiveClass;

	public JavaScriptlet() {
	}

	public void setActiveClass(JavaClassBody newJavaClass) {
		super.setActiveClass(newJavaClass);
		javaActiveClass = newJavaClass;
	}

	@Override
	public int getCodeLineType(String codeLine) {
		return 0;
	}

	@Override
	public String getCodeComment() {
		return JAVA_CODE_COM;
	}

	public void addListener(String methodName, String eventClass,
			String listenerClass) {
		JavaMethod targetMethod = (JavaMethod) javaActiveClass
				.getMethod(methodName);
		if (targetMethod != null) {
			javaActiveClass.addImport(eventClass);
			javaActiveClass.addImport(listenerClass);

			/*
			 * initMethod.addCodeLine(component.getID() +
			 * ".addActionListener(new ActionListener(){");
			 * initMethod.addCodeLine("public void actionPerformed(ActionEvent
			 * evt){"); initMethod.addCodeLine(CLASS_INTERFACE + "." +
			 * methodName + "()"); JavaMethod newMethod = new
			 * JavaMethod(methodName,JavaVariable.SCOPE_PUBLIC,null);
			 * newMethod.addModifier(JavaClassBody.MODIFIER_ABSTRACT);
			 * interfaceClass.addMethod(newMethod); initMethod.addCodeLine("}");
			 * initMethod.addCodeLine("})"); }else if
			 * (eventType.toLowerCase().equals(TAG_COMP_ATT_ON_FOC_LOST)){
			 * windowClass.addImport(CLASS_NAME_FP_FOC_EVNT);
			 * windowClass.addImport(CLASS_NAME_FP_FOC_ADPT);
			 * initMethod.addCodeLine(component.getID() + ".addFocusListener(new
			 * FocusAdapter(){"); initMethod.addCodeLine("public void
			 * focusLost(FocusEvent evt){");
			 * initMethod.addCodeLine(CLASS_INTERFACE + "." + methodName +
			 * "()"); JavaMethod newMethod = new
			 * JavaMethod(methodName,JavaVariable.SCOPE_PUBLIC,null);
			 * newMethod.addModifier(JavaClassBody.MODIFIER_ABSTRACT);
			 * interfaceClass.addMethod(newMethod); initMethod.addCodeLine("}");
			 * initMethod.addCodeLine("})"); }else if
			 * (eventType.toLowerCase().equals(TAG_COMP_ATT_ON_FOC_GOT)){
			 * windowClass.addImport(CLASS_NAME_FP_FOC_EVNT);
			 * windowClass.addImport(CLASS_NAME_FP_FOC_ADPT);
			 * initMethod.addCodeLine(component.getID() + ".addFocusListener(new
			 * FocusAdapter(){"); initMethod.addCodeLine("public void
			 * focusGained(FocusEvent evt){");
			 * initMethod.addCodeLine(CLASS_INTERFACE + "." + methodName +
			 * "()"); JavaMethod newMethod = new
			 * JavaMethod(methodName,JavaVariable.SCOPE_PUBLIC,null);
			 * newMethod.addModifier(JavaClassBody.MODIFIER_ABSTRACT);
			 * interfaceClass.addMethod(newMethod); initMethod.addCodeLine("}");
			 * initMethod.addCodeLine("})"); }
			 */

		}
	}

	public static final String JAVA_CODE_COM = "//";
}
