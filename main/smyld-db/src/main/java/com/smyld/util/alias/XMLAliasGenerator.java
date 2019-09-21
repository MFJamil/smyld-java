package com.smyld.util.alias;

import static com.smyld.util.alias.AliasConstants.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Element;

import com.smyld.xml.XMLUtil;

public class XMLAliasGenerator extends AliasGenerator {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String tagPrefix;
	String attPrefix;

	public XMLAliasGenerator(HashMap<String,AliasClassSettings> classes) {
		super(classes);
	}

	@Override
	public void processSource(AliasSource alSrc, AliasSettings alSettings)
			throws Exception {
		super.processSource(alSrc, alSettings);
		//boolean commentAdded = false;
		XMLAliasSource xmlSrc = (XMLAliasSource) alSrc;
		// Code for reading the XML document
		if (xmlSrc.getSchemaType()==XML_SCHEME_TYPE_TAGS){
			Element root = XMLUtil.getRootNode(xmlSrc.getName());
			tagPrefix = (String) alSettings.getPrefixes().get(
					SETT_XML_NODE_AL_PREFIX_TAG);
			attPrefix = (String) alSettings.getPrefixes().get(
					SETT_XML_NODE_AL_PREFIX_ATT);
			addComment(" ******************************** Constants XML source from \""
					+ xmlSrc.getName()
					+ "\" file  ******************************** ");
			processTag(root);
		}
	}

	@SuppressWarnings("unchecked")
	private void processTag(Element newTag) {
		String newTagName = addXMLElement(null, newTag.getName(), tagPrefix);
		List atts = newTag.getAttributes();
		if ((atts != null) && (atts.size() > 0)) {
			Iterator attItr = atts.iterator();
			while (attItr.hasNext())
				addXMLElement(newTagName,
						((Attribute) attItr.next()).getName(), attPrefix);
		}
		if (XMLUtil.hasChildren(newTag)) {
			Iterator childItr = newTag.getChildren().iterator();
			while (childItr.hasNext())
				processTag((Element) childItr.next());
		}
	}

	private String addXMLElement(String parentEl, String newEl, String prefix) {
		String newTagName = convertName(newEl);
		if (parentEl != null) {
			addConstant(prefix + settings.getNameSeparator() + parentEl
					+ settings.getNameSeparator() + newTagName, newEl);
		} else {
			addConstant(prefix + settings.getNameSeparator() + newTagName,
					newEl);
		}
		return newTagName;
	}

}
