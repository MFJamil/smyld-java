/**
 * 
 */
package com.smyld.test;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import com.smyld.gui.edit.DefaultEditorPopupItem;
import com.smyld.gui.edit.DefaultEditorPopupItemCategory;
import com.smyld.gui.edit.EditorPopupItem;
import com.smyld.gui.edit.EditorPopupItemCategory;
import com.smyld.gui.edit.EditorPopupModel;
import com.smyld.gui.edit.Formula;
import com.smyld.resources.Resource;

/**
 * @author jamil
 *
 */
public class EditPopModel implements EditorPopupModel {
	DefaultEditorPopupItemCategory   categDBField; // One category so far
	DefaultEditorPopupItemCategory   categTemplate; // One category so far
	Vector<EditorPopupItem>          items  = new Vector<EditorPopupItem>();
	Vector<EditorPopupItemCategory>  categs = new Vector<EditorPopupItemCategory>();
	HashMap<String, String> params;
	public EditPopModel(){
		init();
	}
	
	private void init(){
		Resource res = Resource.getInstance();
		categDBField  = new DefaultEditorPopupItemCategory("dbField"    ,"Database fields list"  ,"Field",res.getImageIcon("db_16.png"));
		categTemplate = new DefaultEditorPopupItemCategory("docTemplate","Document Template List","Template",res.getImageIcon("gtk-edit.png"));
		categTemplate.setNeedProcessing(true);
		// Database Items
		DefaultEditorPopupItem item1 = new DefaultEditorPopupItem("Research ID"     ,categDBField,"522348"        ,"Research ID");
		DefaultEditorPopupItem item2 = new DefaultEditorPopupItem("Research Date"   ,categDBField,"01-01-2009"    ,"Research Date");
		DefaultEditorPopupItem item3 = new DefaultEditorPopupItem("FactorX"         ,categDBField,"540"           ,"Research factor X");
		DefaultEditorPopupItem item4 = new DefaultEditorPopupItem("FactorY"         ,categDBField,"160"           ,"Research factor Y");
		
		
		DefaultEditorPopupItem item5  = new DefaultEditorPopupItem("Paitent Name"    ,categDBField,"Müller"          ,"Paitent Name");
		DefaultEditorPopupItem item6  = new DefaultEditorPopupItem("Paitent Birth"   ,categDBField,"24-05-1971"     ,"Research ID");
		DefaultEditorPopupItem item7  = new DefaultEditorPopupItem("Doc Adresse"     ,categDBField,"Dres Klingspor u.Krille\nGemeinschaftpraxis\nPariserstr.103\n55268 Nieder-Olm" ,"Doctor Adresse");
		DefaultEditorPopupItem item8  = new DefaultEditorPopupItem("Lange"           ,categDBField,"5"              ,"Leber lange");
		DefaultEditorPopupItem item9  = new DefaultEditorPopupItem("Breite"          ,categDBField,"4"              ,"Leber breite");
		DefaultEditorPopupItem item10 = new DefaultEditorPopupItem("Tief1"           ,categDBField,"3"              ,"Tief1");
		DefaultEditorPopupItem item11 = new DefaultEditorPopupItem("Tief2"           ,categDBField,"3"              ,"Tief2");
		DefaultEditorPopupItem item12 = new DefaultEditorPopupItem("Doc Anrede"      ,categDBField,"Sehr geehrter Herr Kollege Klingespor,\nSehr geehrter Herr Kollege Krille,"              ,"Doc Anrede");
		DefaultEditorPopupItem item13 = new DefaultEditorPopupItem("Paitent VName"   ,categDBField,"Joerg"          ,"Paitent Name");
		// Template Items
		DefaultEditorPopupItem tmpl1 = new DefaultEditorPopupItem("Paitent Info"    ,categTemplate,"The patent name is (${dbField:Paitent Name}) having birth date \"${dbField:Paitent Birth}\"","Research ID");
		DefaultEditorPopupItem tmpl2 = new DefaultEditorPopupItem("Research Result" ,categTemplate,"The research first factor was (${dbField:FactorX}) while from the check factor y was (${dbField:FactorY}), and by applying the additional case ${user:researchCase}, with the other lab factor ${user:labFactor} the estimation will result in ${formula:calcTissue(FactorX,FactorY,researchCase)}.","Research ID");
		DefaultEditorPopupItem tmpl3 = new DefaultEditorPopupItem("Sehr Ge H"       ,categTemplate,"Sehr geehrter Herr (${dbField:Doc Name}) : \n${edit:cursor}","Sehr geehrter Herrn");
		
		DefaultEditorPopupItem tmpl4 = new DefaultEditorPopupItem("Feeling"         ,categTemplate,"I have to admit that I felt so \"${user:feeling!}\" ","Feeling expressing");
		DefaultEditorPopupItem tmpl5 = new DefaultEditorPopupItem("Ending"          ,categTemplate,"Please Accept my best regards, \n         Dr. Mohammed Jamil ","Report End");
		DefaultEditorPopupItem tmpl6 = new DefaultEditorPopupItem("sysout"          ,categTemplate,"System.out.println(${user:printText});${edit:cursor}","System output println");
		DefaultEditorPopupItem tmpl7 = new DefaultEditorPopupItem("sysoutt"         ,categTemplate,"System.out.println(\"${user:printText}\");${edit:cursor}","System output println");
		DefaultEditorPopupItem tmpl8 = new DefaultEditorPopupItem("Harnwege"        ,categTemplate,"${user:test} Ergebnisse ist ${formula:rechnenLeber(Lange,Breite,Tief1,Tief2,test)}.","Harnwege Formula");
		String template = 
		"Dr.med.Humburg, Dr.med.Homann,\n" +
		"Kinderärzte,55116 Mainz\n" +
		"Herrn\n"+
		"${dbField:Doc Adresse}\n" +                   
		""+                      
		"${dbField:Doc Anrede}\n" +                                                   
        "" +
		"wir berichten über die Untersuchungen Ihres obg Patienten.\n"+
		"Name    : ${dbField:Paitent Name}\n" +
		"Vorname : ${dbField:Paitent VName}\n";
		DefaultEditorPopupItem tmpl9 = new DefaultEditorPopupItem("Berichte"        ,categTemplate,template,"Harnwege Formula");

		
		item3.setIcon(res.getImageIcon("group.png"));
		// Adding database fields
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		items.add(item6);
		items.add(item7);
		items.add(item8);
		items.add(item9);
		items.add(item10);
		items.add(item11);
		items.add(item12);
		items.add(item13);
		
		// Adding templates
		items.add(tmpl1);
		items.add(tmpl2);
		items.add(tmpl3);
		items.add(tmpl4);
		items.add(tmpl5);
		items.add(tmpl6);
		items.add(tmpl7);
		items.add(tmpl8);
		items.add(tmpl9);
		
		
		
		
		Collections.sort(items,new Comparator<EditorPopupItem>(){

			public int compare(EditorPopupItem o1, EditorPopupItem o2) {
				
				return o1.getName().compareTo(o2.getName());
			}
			
		});
		
		categs.add(categDBField);
		categs.add(categTemplate);
		
		
		
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.smyld.gui.edit.EditorPopupModel#getCategoriesCount()
	 */
	public int getCategoriesCount() {
		return categs.size();
	}

	/* (non-Javadoc)
	 * @see com.smyld.gui.edit.EditorPopupModel#getCategoryAt(int)
	 */
	public EditorPopupItemCategory getCategoryAt(int index) {
		return categs.get(index);
	}

	/* (non-Javadoc)
	 * @see com.smyld.gui.edit.EditorPopupModel#getItemAt(int)
	 */
	public EditorPopupItem getItemAt(int index) {
		return items.get(index);
	}

	/* (non-Javadoc)
	 * @see com.smyld.gui.edit.EditorPopupModel#getItemsCount()
	 */
	public int getItemsCount() {
		return items.size();
	}

	public EditorPopupItemCategory getCategory(String categID) {
		if ((categs!=null)&&(categs.size()>0)){
			for(EditorPopupItemCategory curCat:categs){
				if (curCat.getID().equals(categID)) return curCat;
			}
		}
		return null;
	}
	public EditorPopupItem getItem(String itemName){
		if ((items!=null)&&(items.size()>0)){
			for(EditorPopupItem curItem:items){
				if (curItem.getName().equals(itemName)) return curItem;
			}
		}
		return null;
		
	}
	public EditorPopupItem getItemForCategory(String categID,String itemName){
		if ((items!=null)&&(items.size()>0)){
			for(EditorPopupItem curItem:items){
				if ((curItem.getName().equals(itemName))&&(curItem.getCategory().getID().equals(categID))) return curItem;
			}
		}
		return null;
	}
	public String getFormulaParameterValue(String paramName){
		EditorPopupItem formItem = getItem(paramName);
		if (formItem!=null){
			if(formItem.getCategory().getID().equals(categDBField.getID()))
				return formItem.getContents();
		}
			
		return null;
	}

	public String processItem(EditorPopupItem item) {
		
		return null;
	}

	public Formula getFormulaAt(int index) {
		return null;
	}

	public int getFormulasCount() {
		return 0;
	}

	private float getFloat(String param){
		return Float.parseFloat(params.get(param));
	}
	public String processFormula(Formula formula) {
		System.out.println("Formula is : " + formula.getFieldID());
		params = formula.getParams();
		if (formula!=null){
			if (formula.getFieldID().equals("rechnenLeber")){
				
				float result = getFloat("Lange") * getFloat("Breite") * 0.523f * ((getFloat("Tief1")+getFloat("Tief2"))/2);
				return Float.toString(result);
			}else{
				
				
				if ((params!=null)&&(params.size()>0)){
					int result = 0;
					for(String curParam:params.values()){
						if((curParam==null)||(curParam.isEmpty())) return formula.getFieldID(); // Still not complete
						result += Integer.parseInt(curParam);
					}
					
					return Integer.toString(result); // Just dummy value to test the rest of code
					
				}
			}
		}
		return "myFormula";
	}

}
