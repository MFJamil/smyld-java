package org.smyld.lang.script.converter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


//import org.smyld.Tester;
import org.smyld.lang.script.java.JavaConstants;
import org.smyld.lang.script.vb.VBCodeReader;
import org.smyld.lang.script.vb.VBConstants;
import org.smyld.lang.script.vb.VBScriptlet;

public class VBtoJavaConverter extends Converter implements JavaConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5779548698205738999L;
	VBScriptlet vbSrcScript;
	boolean insideFunction = false;
	HashMap<String, String> reservedKeywords = new HashMap<String, String>();
	StringBuffer buffer = new StringBuffer();

	public VBtoJavaConverter() {
		vbSrcScript = new VBScriptlet();
		reservedKeywords.put("<>", "!=");
		reservedKeywords.put("=", "==");
		reservedKeywords.put("not", "!");
		reservedKeywords.put("and", "&&");
		reservedKeywords.put("or", "||");
	}

	@Override
	public void loadCodeFile(String fileName) throws IOException {
		currentCodeReader = new VBCodeReader(new File(fileName));
	}

	@Override
	public void loadCodeText(String codeText) throws IOException {
		currentCodeReader = new VBCodeReader(codeText);
	}

	@Override
	public void doConvert(String currentLine) {
		System.out.println(currentLine);
		System.out
				.println("Type : " + vbSrcScript.getCodeLineType(currentLine));
		switch (vbSrcScript.getCodeLineType(currentLine)) {
		case VBConstants.CODE_TYPE_IF:
			//Tester.writeln(currentLine);
			// System.out.println(currentLine);

			// VBBooleanExp booleanResult =
			// (VBBooleanExp)vbSrcScript.doParseIfStatement(currentLine);
			// transferToJava(ifCondition);
			break;
		case VBConstants.CODE_TYPE_FUNCTION:
		case VBConstants.CODE_TYPE_PROCEDURE:
			vbSrcScript.doParseFunction(currentLine);
			insideFunction = true;
			break;
		// TODO
		case VBConstants.CODE_TYPE_CONSTANT:
			// vbSrcScript.doParseFunction(currentLine);
			// insideFunction = true;
			// break;

		}
	}

	@SuppressWarnings("unused")
	private void transferToJava(String[] elements) {
		buffer.setLength(0);
		int bracketsNO = 1;
		buffer.append("(");
		for (int i = 0; i < elements.length; i++) {
			if (reservedKeywords.containsKey(elements[i])) {
				elements[i] = reservedKeywords.get(elements[i]);
			}
			if ((elements[i].equals("&&")) || (elements[i].equals("||"))) {
				buffer.append(")");
				buffer = buffer.insert(0, "(");
				buffer.append(elements[i]);
				buffer.append("(");
				bracketsNO++;
			} else {
				buffer.append(elements[i]);
			}
		}
		for (int j = 0; j < bracketsNO; j++) {
			buffer.append(")");
		}
		//Tester.writeln("Java Equivalent : " + buffer.toString());
		//Tester.writeln("---------------------------------------------------------\n");
	}

	public static HashMap<String,String> convertModifiers(HashMap<String,String> modifiers) {
		HashMap<String,String> javaModifiers = new HashMap<String,String>();
		for (String curModifier : modifiers.values()) {
			if (curModifier.equals(VBConstants.VB_CODE_CONSTANT)) {
				javaModifiers.put(MODIFIER_STATIC, MODIFIER_STATIC);
				javaModifiers.put(MODIFIER_FINAL, MODIFIER_FINAL);
			}
		}
		return javaModifiers;
	}

	public static String convertType(String vbType) {
		if (vbType.toLowerCase().equals(VBConstants.VB_TYPE_INTEGER)) {
			return JAVA_PRIMITIVE_INTEGER;
		}
		if (vbType.toLowerCase().equals(VBConstants.VB_TYPE_LONG)) {
			return JAVA_PRIMITIVE_LONG;
		}
		if (vbType.toLowerCase().equals(VBConstants.VB_TYPE_BOOLEAN)) {
			return JAVA_PRIMITIVE_BOOLEAN;
		}
		if (vbType.toLowerCase().equals(VBConstants.VB_TYPE_BYTE)) {
			return JAVA_PRIMITIVE_BYTE;
		}

		return vbType;
	}

	@Override
	public String getSourceLangDelimiter() {
		return null;
	}

}
