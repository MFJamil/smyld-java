/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.lang.script.core;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import lombok.*;
import org.smyld.SMYLDObject;
import org.smyld.io.FileSystem;
import org.smyld.text.TextUtil;

@Setter @Getter
@RequiredArgsConstructor
@NoArgsConstructor
public abstract class ClassBody extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected StringBuffer 	mainBody = new StringBuffer();
	@NonNull
	protected String name;
	protected String packageName;
	protected List<Method> methods = new ArrayList<>();
	protected HashMap<String,Variable> variables = new HashMap<String,Variable>();
	protected List<Variable> sortVariables = new ArrayList<Variable>();

	protected List<String> commentLines = new ArrayList<String>();
	protected List<Object> sequence = new ArrayList<Object>();
	protected HashMap<String,String> sequenceTest = new HashMap<String,String>();

	protected String extension;
	boolean createNeededFolders;
	boolean sortVariablesAlphabet = false;
	boolean alignVariables = false;
	protected int alignCounter = DEFAULT_ALLIGN_COUNTER;



	public List<Method> listMethods() {
		return methods;
	}

	public boolean containsMethod(Method method){
		for (Method curMethod:methods){
			if (curMethod.equals(method)) return true;
		}
		return false;
	}

	public HashMap<String,Variable> listVariables() {
		return variables;
	}

	public void sortFieldsAlphabetically(boolean sort) {
		sortVariablesAlphabet = sort;
	}

	/*
	 * TODO : Adding the method search based on the method name return type and
	 * parameters
	 */
	public Method getMethod(String methodName) {
		for (Method currentMethod : methods) {
			if (currentMethod.getName().equals(methodName)) {
				return currentMethod;
			}
		}
		return null;
	}

	/*
	 * TODO : Adding the variable search based on the variable name return type
	 * and parameters
	 */
	public Variable getVariable(String variableName) {
		return (Variable) variables.get(variableName);
	}

	public void setAllignVariableCount(int newAlignCount) {
		alignCounter = newAlignCount;
	}

	public void addSingleCommentLine(String commentLine) {
		commentLines.add(commentLine);
		addToSequence(commentLine);
	}

	public void fillInVariables() {
		if (variables.size() > 0) {
			// Vector sortingVariables = new Vector(variables.size());
			// sortingVariables.addAll(variables.values());
			if (sortVariablesAlphabet) {
				Collections.sort(sortVariables, (obj1,obj2) ->{
						Variable var1 = (Variable) obj1;
						Variable var2 = (Variable) obj2;
						int typeCompare = var1.getType().compareToIgnoreCase(
								var2.getType());
						if (typeCompare > 0) {
							return typeCompare;
						} else if (typeCompare == 0) {
							return var1.getName().compareToIgnoreCase(
									var2.getName());
						}
						return typeCompare;
				});
			}
			if (alignVariables) {
				int[] allignValues = calculateAllignVariables(alignCounter);
				for (Variable currentLine : sortVariables) {
					printVariable(currentLine, allignValues);
				}
			} else {
				for (Variable curVar : sortVariables) {
					//ToDo: handling the indent
					mainBody.append(curVar.print("\t\t")).append("\n");
				}
			}
			//printVariable(currentLine, allignValues);
			mainBody.append("\n");
		}
	}

	protected void printVariable(Variable newVar, int[] allignValues) {
		mainBody.append("\t" + allignVariable(allignValues, newVar));
		mainBody.append("\n");

	}

	protected int[] calculateAllignVariables(int alignCount) {
		int[] longTexts = new int[alignCount];
		for (Variable currentVariable : variables.values()) {
			String curVariableText = currentVariable.print();
			if (curVariableText.startsWith("@")) continue;
			StringTokenizer tocken = new StringTokenizer(curVariableText);
			String currentTocken = null;
			int max = alignCount;
			if (alignCount > tocken.countTokens()) {
				max = tocken.countTokens();
			}
			for (int i = 0; i < max; i++) {
				currentTocken = tocken.nextToken();
				if (currentTocken != null) {
					if (longTexts[i] < currentTocken.length()) {
						longTexts[i] = currentTocken.length();
					}
				}

			}
		}
		return longTexts;
	}

	private String allignVariable(int[] lengths, Variable currentVariable) {
		String currentTocken = null;
		StringBuffer buffer = new StringBuffer();
		String variableText = currentVariable.print();
		String[] codeLines = variableText.split("\n");
		if (codeLines.length>1){
			for (String curLine:codeLines){
				if (curLine.indexOf("@")!=-1){
					buffer.append(curLine).append("\n");
				}else{
					variableText = curLine;
					break;
				}
			}
		}
		StringTokenizer tocken = new StringTokenizer(variableText);

		int max = lengths.length;
		if (lengths.length > tocken.countTokens()) {
			max = tocken.countTokens();
		}

		for (int i = 0; i < max; i++) {
			currentTocken = tocken.nextToken();
			if (currentTocken != null) {
				if (lengths[i] > currentTocken.length()) {
					buffer.append(currentTocken
							+ TextUtil.createWord(' ', lengths[i]
									- currentTocken.length() + 1));
				} else {
					buffer.append(currentTocken + " ");
				}
			}
		}
		if (!variableText.endsWith(currentTocken)) {
			buffer.append(variableText.substring(variableText
					.indexOf(currentTocken)
					+ currentTocken.length()));
		}
		return buffer.toString();
	}

	public void fillInMethods(int tabSeq) {

		if (methods.size() > 0) {
			for (Method currentMethode : methods) {
				mainBody.append(currentMethode.print(tabSeq));
				mainBody.append("\n");
			}
			mainBody.append("\n");
		}
	}

	public void addMethod(Method newMethod) {
		methods.add(newMethod);
		addToSequence(newMethod);
	}

	public void addMethodes(Vector<Method> newMethodes) {
		methods.addAll(newMethodes);
		// CHG:sequence.addAll(newMethodes);
	}

	public void addVariables(HashMap<String,Variable> newVariables) {
		variables.putAll(newVariables);
		// CHG:sequence.addAll(newVariables.values());
	}

	public void addVariable(Variable newVariable) {
		boolean exists = variables.containsKey(newVariable.getName());
		variables.put(newVariable.getName(), newVariable);
		addToSequence(newVariable);
		if (!exists) {
			sortVariables.add(newVariable);
		}
	}

	public String print() {
		return null;
	}

	private void addToSequence(Object incObject) {
		if (incObject instanceof Variable) {
			String variableName = ((Variable) incObject).getName();
			if (!sequenceTest.containsKey(variableName)) {
				sequenceTest.put(variableName, variableName);
				sequence.add(incObject);
			}
		} else {
			sequence.add(incObject);
		}
	}

	public void exportFileToPath(String path) throws Exception {
		if (FileSystem.confirmFoldersExistence(path)) {
			FileOutputStream fout = new FileOutputStream(path + File.separator
					+ name + "." + extension);
			fout.write(print().getBytes());
			fout.flush();
			fout.close();
		}
	}

	public String getName() {
		return name;
	}

	public boolean isCreateNeededFolders() {
		return createNeededFolders;
	}

	public void setCreateNeededFolders(boolean createNeededFolders) {
		this.createNeededFolders = createNeededFolders;
	}

	public static final int DEFAULT_ALLIGN_COUNTER = 3;
	boolean createOnSequence;

	public boolean isCreateOnSequence() {
		return createOnSequence;
	}

	public void setCreateOnSequence(boolean createOnSequence) {
		this.createOnSequence = createOnSequence;
	}

}
