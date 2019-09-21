package com.smyld.util.alias;

import java.util.HashMap;

import com.smyld.SMYLDObject;
import com.smyld.lang.script.java.JavaConstants;
import com.smyld.lang.script.java.JavaVariable;
import com.smyld.lang.script.util.Variable;
import com.smyld.text.TextTokenizer;

public class AliasGenerator extends SMYLDObject implements XMLAliasConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StringBuffer buffer = new StringBuffer();
	AliasSettings settings;
	AliasSource alSource;
	HashMap<String,AliasClassSettings> classes;

	public AliasGenerator(HashMap<String,AliasClassSettings> classes) {
		this.classes = classes;
	}

	public void processSource(AliasSource alSrc, AliasSettings alSettings)
			throws Exception {
		alSource = alSrc;
		settings = alSettings;
	}

	protected String convertName(String curName) {
		buffer.setLength(0);
		TextTokenizer tockenz = new TextTokenizer(curName, settings
				.getNameSeparator());
		String[] nameParts = tockenz.parseTokens();
		for (int i = 0; i < nameParts.length; i++) {
			if (settings.getShortings().containsKey(nameParts[i]))
				nameParts[i] = (String) settings.getShortings().get(
						nameParts[i]);
			buffer.append(nameParts[i]);
			if (i < nameParts.length - 1)
				buffer.append(settings.getNameSeparator());
		}
		return buffer.toString().toUpperCase();
	}

	protected void addConstant(String constName, String constValue) {
		for (String classID : alSource.classRef.keySet()) {
			AliasClassSettings curClass = classes.get(classID);
			JavaVariable newTableVariable = new JavaVariable(constName,
					Variable.SCOPE_PUBLIC, "String", "\"" + constValue + "\"");
			newTableVariable.addModifier(JavaConstants.MODIFIER_FINAL);
			newTableVariable.addModifier(JavaConstants.MODIFIER_STATIC);
			curClass.getActiveClass().addVariable(newTableVariable);

		}
	}

	protected void addComment(String comment) {
		for (String classID : alSource.classRef.keySet()) {
			AliasClassSettings curClass = classes.get(classID);
			curClass.getActiveClass().addSingleCommentLine(comment);
		}
	}

}
