package com.smyld.lang.script.java;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import com.smyld.lang.script.util.ClassBody;
import com.smyld.lang.script.util.Variable;
import com.smyld.text.TextUtil;

public class JavaClassBody extends ClassBody {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Vector<JavaMethod> constructors = new Vector<JavaMethod>();
	protected HashMap<String,String> imports = new HashMap<String,String>();
	protected HashSet<String> interfaces = new HashSet<String>();

	protected String parentClass;
	protected boolean isPublic;
	boolean isAbstract;

	// Constructors
	public JavaClassBody() {
		extension = "java";
	}

	public JavaClassBody(String Name) {
		super(Name);
		extension = "java";
	}

	public JavaClassBody(String Name, boolean Public, boolean Abstract) {
		this(Name);
		isPublic = Public;
		isAbstract = Abstract;
	}

	public JavaClassBody(String Name, String PackageName, boolean Public,
			boolean Abstract) {
		this(Name, Public, Abstract);
		packageName = PackageName;
	}

	public JavaClassBody(String Name, String PackageName, String ParentClass,
			boolean Public, boolean Abstract) {
		this(Name, PackageName, Public, Abstract);
		parentClass = ParentClass;
	}

	public void addImport(String className) {
		imports.put(className, className);
	}

	public void addInterface(String interfaceName) {
		interfaces.add(interfaceName);
	}

	public void addConstructors(JavaMethod newConstructor) {
		constructors.add(newConstructor);
	}

	public void createEmptyConstructor() {
		JavaMethod constructor = new JavaMethod(name, SCOPE_PUBLIC, null, true);
		addConstructors(constructor);
	}

	public void addClassParameter(String parameterName, String parameterType,
			String defaultValue) {
		if (!variables.containsKey(parameterName)) {
			JavaMethod setterMethod = new JavaMethod("set"
					+ TextUtil.toTitleCase(parameterName), SCOPE_PUBLIC, null);
			setterMethod.addParameter("\t" + parameterName.toUpperCase(),
					parameterType);
			setterMethod.addCodeLine("\t" + parameterName + "="
					+ parameterName.toUpperCase());
			JavaMethod getterMethod = new JavaMethod("get"
					+ TextUtil.toTitleCase(parameterName), SCOPE_PUBLIC,
					parameterType);
			getterMethod.addCodeLine("\t return " + parameterName);
			JavaVariable variable = new JavaVariable(parameterName,
					SCOPE_PRIVATE, parameterType, defaultValue);

			addMethod(setterMethod);
			addMethod(getterMethod);
			addVariable(variable);
		}
	}

	public void fillInConstructors(int tabSeq) {
		if (constructors.size() > 0) {
			for (JavaMethod currentConstructor : constructors) {
				mainBody.append(currentConstructor.print(tabSeq));
				mainBody.append("\n");
			}
			mainBody.append("\n");
		}
	}

	private void printMethod(JavaMethod newMethod, int tabSeq) {
		mainBody.append(newMethod.print(tabSeq));
		mainBody.append("\n");
	}

	public void testRunning() {
		System.out.println("Here is the test from the java class body");
	}

	public void fillInImports() {
		if (imports.size() > 0) {
			Vector<String> sortingImports = new Vector<String>(imports.size());
			sortingImports.addAll(imports.values());
			Collections.sort(sortingImports);
			for (String curImport : sortingImports) {
				mainBody.append(KEYWORD_IMPORT);
				mainBody.append(" ");
				mainBody.append(curImport);
				mainBody.append(";\n");
			}
			mainBody.append("\n");
		}
	}

	protected void fillInInterfaces() {
		if (interfaces.size() > 0) {
			mainBody.append(KEYWORD_IMPLEMENTS + " ");
			for (String curInterface : interfaces) {
				mainBody.append(curInterface);
				mainBody.append(",");
			}
			mainBody.deleteCharAt(mainBody.length() - 1);
		}
	}

	protected String getObjectType() {
		return KEYWORD_CLASS;
	}

	protected String getParentClass() {
		return parentClass;
	}

	@Override
	public String print() {
		mainBody.append(KEYWORD_PACKAGE + " ");
		mainBody.append(packageName + ";\n\n");
		fillInImports();
		if (isPublic) {
			mainBody.append(SCOPE_PUBLIC + " ");
		}
		if (isAbstract) {
			mainBody.append(MODIFIER_ABSTRACT + " ");
		}
		mainBody.append(getObjectType() + " ");
		mainBody.append(name + " ");
		if (parentClass != null) {
			mainBody.append(KEYWORD_EXTENDS + " " + getParentClass() + " ");
		}
		fillInInterfaces();
		mainBody.append("{\n");
		mainBody.append("\n");
		// Printing the contents of the class here
		if (isCreateOnSequence()) {
			int[] allignValues = calculateAllignVariables(alignCounter);
			for (Object curObj : sequence) {
				if (curObj instanceof String) {
					printComment((String) curObj);
				} else if (curObj instanceof Variable) {
					printVariable((Variable) curObj, allignValues);
				} else if (curObj instanceof JavaMethod) {
					JavaMethod jMeth = (JavaMethod) curObj;
					printMethod(jMeth, 1);
				}
			}
		} else {
			addComments();
			fillInVariables();
			mainBody.append("\n");
			fillInConstructors(1);
			fillInMethods(1);
		}

		mainBody.append("}");
		return mainBody.toString();

	}

	private void printComment(String comment) {
		mainBody.append("//" + comment + "\n");
	}

	private void addComments() {
		for (String currentComment : commentLines) {
			printComment(currentComment);
		}

	}

	public JavaMethod addMainMethod() {
		JavaMethod mainMethod = new JavaMethod("main", SCOPE_PUBLIC, null);
		mainMethod.addModifier(MODIFIER_STATIC);
		mainMethod.addParameter("args", "String[]");
		addMethod(mainMethod);
		return mainMethod;
	}

	@Override
	public void exportFileToPath(String path) throws Exception {
		String targetPath = getClassFilePath(path);
		super.exportFileToPath(targetPath);
	}

	public String getClassFilePath(String path) throws Exception {
		return path + File.separator
				+ JavaLangUtility.convertPackageToPath(packageName);
	}

	public String getClassFilePathWithName(String path) throws Exception {
		return path + File.separator + getClassFullNameAsSystemFolders();
	}

	public String getClassFullNameAsSystemFolders() throws Exception {
		return JavaLangUtility.convertPackageToPath(packageName)
				+ File.separator + getName();
	}

	public String getPackageName() {
		return packageName;
	}

	public String getFullPackageName() {
		return packageName + "." + getName();
	}

	public String getParentClassName() {
		return parentClass;
	}

	public void setParentClassName(String newParentClassName) {
		parentClass = newParentClassName;
	}

	public String getFullClassName() {
		return packageName + "." + getName();
	}

	public static final String SCOPE_PUBLIC = "public";
	public static final String SCOPE_PRIVATE = "private";
	public static final String SCOPE_PROTECTED = "protected";

	public static final String MODIFIER_STATIC = "static";
	public static final String MODIFIER_SYNCHRONIZED = "synchronized";
	public static final String MODIFIER_FINAL = "final";
	public static final String MODIFIER_ABSTRACT = "abstract";

	public static final String KEYWORD_PACKAGE = "package";
	public static final String KEYWORD_CLASS = "class";
	public static final String KEYWORD_INTERFACE = "interface";
	public static final String KEYWORD_EXTENDS = "extends";
	public static final String KEYWORD_IMPLEMENTS = "implements";
	public static final String KEYWORD_IMPORT = "import";

}
