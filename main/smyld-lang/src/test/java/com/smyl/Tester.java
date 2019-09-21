package com.smyld;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import com.smyld.lang.script.converter.VBtoJavaConverter;
import com.smyld.lang.script.java.JavaClassBody;
import com.smyld.lang.script.java.JavaInterface;
import com.smyld.lang.script.java.JavaMethod;
import com.smyld.lang.script.java.JavaVariable;
import com.smyld.lang.script.util.Scriptlet;
import com.smyld.lang.script.vb.VBBooleanExp;
import com.smyld.lang.script.vb.VBCodeReader;
import com.smyld.lang.script.vb.VBConstants;
import com.smyld.lang.script.vb.VBScriptlet;
import com.smyld.lang.script.vb.VBVariable;
import com.smyld.text.BracketsParser;
import com.smyld.text.MultiTextTokenizer;

public class Tester {
	// String codeSampleFile = codeSample1;
	String codeSampleFile = codeSample1;
	static FileOutputStream resultFile;

	public Tester() {
		init();
		// testCodeCreation();
		// testBracketsParser();
		// testBooleanExp();
		// testMultiTockenizer();
		// startConvert();
		// testFileCode();
		// testQutations();
		// testInterface();
		//testVBConstantConverter();
		testHashSet();
	}
	private void testHashSet(){
		HashSet<String> t = new HashSet<String>();
		t.add("Test1");
		t.add("Test1");
		t.add("Test2");
		for(String curItem:t){
			System.out.println(curItem);
			
		}
		
	}

	@SuppressWarnings("unused")
	private void init() {
		try {
			resultFile = new FileOutputStream(codeResultFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//TODO we need to replace it with JUnit test cases 
	}

	@SuppressWarnings("unused")
	private void testInterface() {
		JavaInterface tt = new JavaInterface("MyInterface", "com.test", null,
				true);
		tt.addParentClass("MyParent");
		try {
			tt.exportFileToPath("d:");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startConvert() {
		VBCodeReader codeReader = null;
		VBScriptlet scriptLet = new VBScriptlet();
		try {
			FileOutputStream fout = new FileOutputStream(codeResultFile);
			codeReader = new VBCodeReader(new File(codeSampleFile));
			String currentCodeLine = null;
			while ((currentCodeLine = codeReader.getNextCodeLine()) != null) {
				int lineType = scriptLet.getCodeLineType(currentCodeLine);
				// if (lineType==scriptLet.CODE_TYPE_UNKNOWN)
				writeln(fout, Integer.toString(lineType) + " - "
						+ currentCodeLine);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void testVBConstantConverter() {
		JavaInterface constantsInterface = new JavaInterface("BWDBValue",
				"com.smyld.bw.db", null, true);
		VBCodeReader codeReader = null;
		VBScriptlet scriptLet = new VBScriptlet();

		try {
			@SuppressWarnings("unused")
			FileOutputStream fout = new FileOutputStream(javaDB);
			codeReader = new VBCodeReader(new File(vbConstant));
			String currentCodeLine = null;
			while ((currentCodeLine = codeReader.getNextCodeLine()) != null) {
				int lineType = scriptLet.getCodeLineType(currentCodeLine);
				switch (lineType) {
				case VBConstants.CODE_TYPE_CONSTANT:
					VBVariable vbVar = new VBVariable(currentCodeLine);
					JavaVariable javaVar = new JavaVariable(vbVar.getName());
					javaVar.importVariable(vbVar);
					constantsInterface.addVariable(javaVar);
					// writeln(fout,javaVar.print());
					break;
				case VBConstants.CODE_TYPE_COMMENT:
					constantsInterface.addSingleCommentLine(currentCodeLine);
					break;
				}
				// if (lineType==scriptLet.CODE_TYPE_UNKNOWN)
				// writeln(fout,Integer.toString(lineType)+ " - " +
				// currentCodeLine);
			}
			constantsInterface.setAllignVariableCount(6);
			constantsInterface
					.exportFileToPath("d:/Projects/sharedProjects/SMYLD/src");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	private ResultSet getProcess() {

		// BWDataBase bwData = new BWDataBase(this);
		return null;
	}

	public boolean addError(Exception exc, Connection conn) {
		exc.printStackTrace();
		return true;
	}

	private void writeln(FileOutputStream fout, String data) throws IOException {
		fout.write((data + "\n").getBytes());
		fout.flush();
	}

	public static void writeln(String data) {
		try {
			resultFile.write((data + "\n").getBytes());
			resultFile.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void testFileCode() {
		try {
			init();
			VBtoJavaConverter testVbCoder = new VBtoJavaConverter();
			testVbCoder.loadCodeFile(codeSampleFile);
			testVbCoder.startConversion();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void testQutations() {
		System.out.println(test_vb_qute_2);
		VBScriptlet test = new VBScriptlet();
		HashMap<String, String> results = test
				.filterOutQutations(test_vb_qute_2);
		String filteredCode = results.get(Scriptlet.FILTER_CODE_ID);
		filteredCode = test.doFilterComment(filteredCode);
		printIterator(results.values().iterator());
		System.out.println(filteredCode);
	}

	public void testCodeCreation() {
		JavaVariable testCodeLine = new JavaVariable("saver",
				JavaClassBody.SCOPE_PUBLIC);
		testCodeLine.addModifier(JavaClassBody.MODIFIER_STATIC);
		testCodeLine.addModifier(JavaClassBody.MODIFIER_FINAL);
		// System.out.println(test.print());*/
		JavaMethod testJavaMethod = new JavaMethod("saver",
				JavaClassBody.SCOPE_PUBLIC, null);
		testJavaMethod.addModifier(JavaClassBody.MODIFIER_ABSTRACT);
		testJavaMethod.addModifier(JavaClassBody.MODIFIER_FINAL);
		testJavaMethod.addParameter("FileName", "String");
		testJavaMethod.addParameter("active", "boolean");
		JavaMethod testJavaMethod2 = new JavaMethod("delete",
				JavaClassBody.SCOPE_PUBLIC, null);
		testJavaMethod2.addModifier(JavaClassBody.MODIFIER_FINAL);
		testJavaMethod2.addParameter("FileName", "String");
		testJavaMethod2.addParameter("includeDirectory", "boolean");
		// System.out.println(test.print());*/
		/*
		 * VBMethod test = new VBMethod("saver","Public", "String");
		 * test.addParameter("FileName","String");
		 * test.addParameter("active","boolean");
		 * System.out.println(test.print());
		 */
		JavaClassBody test = new JavaClassBody("Tester", "com.smyld",
				"SMYLDObject", true, false);
		test.addImport("com.smyld.util");
		test.addImport("java.io.File");
		test.addInterface("Runnable");
		test.addInterface("Thread");
		test.addMethod(testJavaMethod);
		test.addMethod(testJavaMethod2);
		test.addVariable(testCodeLine);
		test.createEmptyConstructor();
		System.out.println(test.print());
	}

	public void testBracketsParser() {
		String[] ifStatements = { test_vb_if_1, test_vb_if_2, test_vb_if_3 };
		testBracketsParser(ifStatements);
	}

	public void testBracketsParser(String[] ifStatements) {
		Object[] result = null;
		BracketsParser test = new BracketsParser('(', ')', true);
		for (String element : ifStatements) {
			test.setText(element);
			result = test.parseBracket();
			System.out.println("Parsing : " + element);
			printArray(result);
			test.reset();
		}
	}

	public void testBooleanExp() {
		// String[] ifStatements = {test_if_1,test_if_2,test_if_3};
		VBBooleanExp test = new VBBooleanExp(test_vb_if_6);
		// JavaBooleanExp test = new JavaBooleanExp(test_java_if_5);
		test.parseText();
	}

	public void testMultiTockenizer() {
		String[] sep = { "Or", "And" };
		MultiTextTokenizer test = new MultiTextTokenizer(test_vb_if_4, sep,
				false);
		test.parseTokens();
	}

	private void printArray(Object[] array) {
		for (Object element : array) {
			System.out.println(element.toString());
		}
	}

	@SuppressWarnings( { "unchecked", "unused" })
	private void printEnum(Enumeration enums) {
		while (enums.hasMoreElements()) {
			System.out.println((String) enums.nextElement());
		}
	}

	@SuppressWarnings( { "unchecked", "unused" })
	private void printIterator(Iterator itr) {
		while (itr.hasNext()) {
			System.out.println((String) itr.next());
		}
	}

	public static final String codeSample1 = "src/test/resources/sample1.txt";
	public static final String codeSample2 = "src/test/resources/sample2.txt";
	public static final String codeSample3 = "src/test/resources/sample3.txt";

	public static final String vbConstant = "d:/Projects/sharedProjects/SMYLD/sources/LIB_CONS.txt";
	public static final String javaDB = "src/test/resources/DBValue.java";

	public static final String codeResultFile = "src/test/resources/sampleResult.txt";

	public static final String test_vb_if_1 = "(Left(strFldValue22, 1) = \"2\") And (Mid(strFldValue22, 5, 1) = \"0\") And (Mid(strFldValue22, 6, 1) = \"1\") And (Mid(strFldValue22, 4, 1) = \"9\")";
	public static final String test_vb_if_2 = "((Left(strFldValue22, 1) = \"2\") And ((Mid(strFldValue22, 8, 1) = \"1\") Or (Mid(strFldValue22, 8, 1) = \"9\")))";
	public static final String test_vb_if_3 = "((Left(strFldValue22, 3) = \"211\") And (Right(strFldValue22, 8) = \"01213046\"))";
	public static final String test_vb_if_4 = "Left(strFldValue22, 3) = \"211\" And Right(strFldValue22, 8) = \"01213046\"";
	public static final String test_vb_if_5 = "Mid(strFldValue22, 5, 5) = \"01213\" And (Mid(strFldValue63, 1, 2) = \"02\" Or Mid(strFldValue63, 1, 2) = \"90\") And Mid(strFldValue63, 6, 1) = \"1\"";
	public static final String test_vb_if_6 = "Mid(strFldValue22, 5, 5) = \"01213\" And (Mid(strFldValue63, 1, 2) = \"02\" Or Mid(strFldValue63, 1, 2) = \"90\") And Mid(strFldValue63, 6, 1) = \"1\"";

	public static final String test_java_if_1 = "((Left(strFldValue22, 1) == \"2\") && (Mid(strFldValue22, 5, 1) == \"0\") && (Mid(strFldValue22, 6, 1) == \"1\") && (Mid(strFldValue22, 4, 1) == \"9\"))";
	public static final String test_java_if_2 = "((Left(strFldValue22, 1) == \"2\") && ((Mid(strFldValue22, 8, 1) == \"1\") || (Mid(strFldValue22, 8, 1) == \"9\")))";
	public static final String test_java_if_3 = "((Left(strFldValue22, 3) == \"211\") && (Right(strFldValue22, 8) == \"01213046\"))";
	public static final String test_java_if_4 = "(Left(strFldValue22, 3) == \"211\") && (Right(strFldValue22, 8) == \"01213046\")";
	public static final String test_java_if_5 = "(Mid(strFldValue22, 5, 5) == \"01213\") && ((Mid(strFldValue63, 1, 2) == \"02\") || (Mid(strFldValue63, 1, 2) == \"90\")) && (Mid(strFldValue63, 6, 1) == \"1\"))";

	public static final String test_vb_qute_1 = "if istableempty(\"select account_type_id from cbr_contract_acct_types where service_contract_id = '\"&strservcon & \"' and account_type_id = '\" & stracctype & \"' and acct_currency = '\" &stracctcurr &\"' and institution_number ='\" &	strinstitution & \"'\", 0) = 1 then";
	public static final String test_vb_qute_2 = "if istableempty(\"select account_type_id from cbr_contract_acct_types where service_contract_id = '\"&strservcon & \"' and account_type_id = '\" & stracctype & \"' and acct_currency = '\" &stracctcurr &\"' and institution_number ='\" &	strinstitution & \"'\", 0) = 1 then ' \"this is to test the comment\"";

}
