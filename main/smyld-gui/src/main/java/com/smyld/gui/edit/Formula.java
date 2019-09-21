package com.smyld.gui.edit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class Formula extends DocTextPart {
	String  equation;
	HashMap<String,String> params    = new HashMap<String, String>();
	HashSet<DocTextPart>   docParams = new HashSet<DocTextPart>();
	
	public Formula(){
		
	}
	public Formula(String name,String equation){
		setFieldID(name);setEquation(equation);
	}
	
	public void addParameter(String paramName,String paramValue){
		params.put(paramName, paramValue);
		
	}
	
	public boolean containsParameter(String paramName){return params.containsKey(paramName);}
	
	/**
	 * @return the equation
	 */
	public String getEquation() {
		return equation;
	}
	/**
	 * @param equation the equation to set
	 */
	public void setEquation(String equation) {
		this.equation = equation;
	}
	/**
	 * @return the params
	 */
	public HashMap<String, String> getParams() {
		return params;
	}
	/**
	 * @param params the params to set
	 */
	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}
	
	public void addDocParam(DocTextPart newDocParam){
		docParams.add(newDocParam);
	}
	/**
	 * @return the docParams
	 */
	public HashSet<DocTextPart> getDocParams() {
		return docParams;
	}
	/**
	 * @param docParams the docParams to set
	 */
	public void setDocParams(HashSet<DocTextPart> docParams) {
		this.docParams = docParams;
	}
	
	public void importDocParams(Vector<DocTextPart> extInfo){
		docParams.addAll(extInfo);
	}
	
	public boolean containsParamAtPos(int pos){
		return (getParamAtPos(pos)!=null);
	}
	
	public DocTextPart getParamAtPos(int pos){
		if ((docParams!=null)&&(docParams.size()>0)){
			for(DocTextPart curPart:docParams){
				if ((pos >=curPart.getFieldPos().getOffset())&&(pos<=curPart.getFieldPos().getOffset()+curPart.getFieldValue().length())){
					return curPart;
				}
			}
		}
		return null;
	}
	
}
