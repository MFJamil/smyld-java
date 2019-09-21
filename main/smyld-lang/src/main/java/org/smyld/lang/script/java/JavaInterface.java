package org.smyld.lang.script.java;


import java.util.HashSet;


/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
public class JavaInterface extends JavaClassBody {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HashSet<String> parents = new HashSet<String>();

	/**
	 * 
	 * @see
	 * @since
	 */
	public JavaInterface(String Name, String PackageName, String ParentClass,
			boolean Public) {
		super(Name, PackageName, ParentClass, Public, false);
	}

	@Override
	protected String getObjectType() {
		return KEYWORD_INTERFACE;
	}

	@Override
	protected String getParentClass() {
		StringBuffer buffer = new StringBuffer(parentClass);
		for (String curParent : parents) {
			if (!curParent.equals(parentClass)) {
				buffer.append(",");
				buffer.append(curParent);
			}
		}

		return buffer.toString();
	}

	public void addParentClass(String newParentClass) {
		if (parentClass == null) {
			setParentClassName(newParentClass);
		} else {
			parents.add(newParentClass);
		}
	}

	@Override
	public void fillInInterfaces() {
		// we do not do anything here because the interface could not implements
		// others
	}
}
