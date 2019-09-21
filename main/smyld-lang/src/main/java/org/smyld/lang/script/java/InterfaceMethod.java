package org.smyld.lang.script.java;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class InterfaceMethod extends JavaMethod {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see
	 * @since
	 */
	public InterfaceMethod(String Name, String Scope, String ReturnType) {
		super(Name, Scope, ReturnType);
		addModifier(JavaClassBody.MODIFIER_ABSTRACT);
	}

}
