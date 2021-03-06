package org.smyld.lang.script.vb;

import org.smyld.lang.script.core.Langs;
import org.smyld.lang.script.core.Scriptlet;
import org.smyld.lang.script.core.Variable;

public class VBVariable extends Variable implements VBConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VBVariable() {
		super(Langs.VisualBasic6);
	}

	public VBVariable(String codeLine) {
		this();
		parseVariable(codeLine);
	}

	public void parseVariable(String codeLine) {

		String[] elements = Scriptlet.getCodeLineElements(codeLine);
		if (elements[0].toLowerCase().equals(VB_CODE_DIM)) {
			addModifier(VB_CODE_DIM);
		} else if (elements[0].toLowerCase().equals(SCOPE_PUBLIC)) {
			scope = SCOPE_PUBLIC;
			// Declaration of a constant
			if (elements[1].toLowerCase().equals(VB_CODE_CONSTANT)) {
				addModifier(VB_CODE_CONSTANT);
				name = elements[2];
				type = elements[4];
				setDefaultValue(elements[6]);
			}
		} else {
			name = elements[0];
			type = elements[2];
		}
	}

	public static String constructVariable(String name, String type) {
		return name + " " + VBConstants.VB_CODE_AS + " " + type;
	}

}
