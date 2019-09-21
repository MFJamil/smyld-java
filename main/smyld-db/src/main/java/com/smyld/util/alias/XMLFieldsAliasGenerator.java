package com.smyld.util.alias;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Element;

import com.smyld.xml.XMLUtil;
import static com.smyld.util.alias.AliasConstants.*;

public class XMLFieldsAliasGenerator extends AliasGenerator {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String tagPrefix;
	String attPrefix;

	public XMLFieldsAliasGenerator(HashMap<String,AliasClassSettings> classes) {
		super(classes);
	}

	@Override
	public void processSource(AliasSource alSrc, AliasSettings alSettings)
			throws Exception {
		super.processSource(alSrc, alSettings);
		//boolean commentAdded = false;
		XMLAliasSource xmlSrc = (XMLAliasSource) alSrc;
		// Code for reading the XML document
		if (xmlSrc.getSchemaType()==XML_SCHEME_TYPE_FIELDS){
			Element root = XMLUtil.getRootNode(xmlSrc.getName());
			addComment(" ******************************** Constants XML source from \""
					+ xmlSrc.getName()
					+ "\" file  ******************************** ");
			processTag(root);
		}
	}

	@SuppressWarnings("unchecked")
	private void processTag(Element newTag) {
		String tagName = newTag.getName();
		if ((tagName!=null)&&(tagName.toLowerCase().equals("field"))){
			String idValue = newTag.getAttributeValue("id");
			if(idValue!=null){
				addConstant(convertName(idValue), idValue);
			}
		}
		if (XMLUtil.hasChildren(newTag)) {
			Iterator childItr = newTag.getChildren().iterator();
			while (childItr.hasNext())
				processTag((Element) childItr.next());
		}
	}

}
