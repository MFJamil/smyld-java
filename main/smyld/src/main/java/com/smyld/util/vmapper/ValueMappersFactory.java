package com.smyld.util.vmapper;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import com.smyld.xml.XMLUtil;
import org.jdom2.Element;
import org.jdom2.Document;
import org.jdom2.input.DOMBuilder;



import com.smyld.SMYLDObject;
import com.smyld.resources.Resource;
import com.smyld.text.TextUtil;
import com.smyld.util.IntValueMapper;
import com.smyld.util.ValueMapper;
import org.jdom2.input.SAXBuilder;

public class ValueMappersFactory extends SMYLDObject implements VMapperConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Element root;
	String mappName;

	public ValueMappersFactory() {
	}

	public ValueMapper Build(String SourceXMLFileName, String mappingName)
			throws Exception {
		InputStream fileStream = new Resource()
				.getResourceInputStream(SourceXMLFileName);



		root = new SAXBuilder().build(fileStream).getRootElement();
		mappName = mappingName;
		return buildFromXML();
	}

	public ValueMapper Build(Connection conn, String tableName,
			String srcFieldName, String destFieldName) throws Exception {
		//StringBuffer buffer = new StringBuffer();
		String sqlQuery = "Select * from " + tableName;
		PreparedStatement pst = conn.prepareStatement(sqlQuery);
		ResultSet rs = pst.executeQuery();
		ValueMapper mapper = null;
		while (rs.next()) {
			if (mapper == null)
				mapper = new ValueMapper();
			String keyValue = rs.getString(srcFieldName);
			String value = rs.getString(destFieldName);
			if (!TextUtil.isEmpty(keyValue))
				mapper.addKey(null, keyValue, value);
		}
		return mapper;
	}

	public ValueMapper Build(File ExternalXMLFile, String mappingName)
			throws Exception {
		root = new SAXBuilder().build(ExternalXMLFile).getRootElement();
		mappName = mappingName;
		return buildFromXML();
	}

	@SuppressWarnings("unchecked")
	private ValueMapper buildFromXML() {
		List mappingList = root.getChildren(XML_NODE_NAME_MAPPING);
		Element targetMap = getMapper(mappingList);
		if (targetMap != null)
			return buildSingleMapFromNode(targetMap, null);
		return null;

	}

	public IntValueMapper BuildIntMapper(String SourceXMLFileName,
			String mappingName) throws Exception {
		InputStream fileStream = new Resource()
				.getResourceInputStream(SourceXMLFileName);
		root = new SAXBuilder().build(fileStream).getRootElement();
		mappName = mappingName;
		return buildIntFromXML();
	}

	public IntValueMapper BuildIntMapper(File ExternalXMLFile,
			String mappingName) throws Exception {
		root = new SAXBuilder().build(ExternalXMLFile).getRootElement();
		mappName = mappingName;
		return buildIntFromXML();
	}

	@SuppressWarnings("unchecked")
	private IntValueMapper buildIntFromXML() {
		List mappingList = root.getChildren(XML_NODE_NAME_MAPPING);
		Element targetMap = getMapper(mappingList);
		if (targetMap != null)
			return buildSingleIntMapFromNode(targetMap, null);
		return null;

	}

	@SuppressWarnings("unchecked")
	private IntValueMapper buildSingleIntMapFromNode(Element parentNode,
			IntValueMapper mapper) {
		if (mapper == null)
			mapper = new IntValueMapper();
		List children = parentNode.getChildren(XML_NODE_NAME_MAP);
		Iterator itr = children.iterator();
		while (itr.hasNext()) {
			Element curNode = (Element) itr.next();
			String curKey = curNode.getAttributeValue(XML_ATT_KEY);
			if (XMLUtil.hasChildren(curNode)) {
				IntValueMapper childMapper = new IntValueMapper();
				mapper.addKey(curKey, childMapper);
				buildSingleIntMapFromNode(curNode, childMapper);
			} else {
				String curValue = curNode.getAttributeValue(XML_ATT_VALUE);
				mapper.addKey(curKey, curValue);
			}
		}
		return mapper;
	}

	@SuppressWarnings("unchecked")
	private ValueMapper buildSingleMapFromNode(Element parentNode,
			ValueMapper mapper) {
		if (mapper == null)
			mapper = new ValueMapper();
		List children = parentNode.getChildren(XML_NODE_NAME_MAP);
		Iterator itr = children.iterator();
		while (itr.hasNext()) {
			Element curNode = (Element) itr.next();
			String curKey = curNode.getAttributeValue(XML_ATT_KEY);
			if (XMLUtil.hasChildren(curNode)) {
				ValueMapper childMapper = mapper.addMappingKey(null, curKey);
				buildSingleMapFromNode(curNode, childMapper);
			} else {
				String curValue = curNode.getAttributeValue(XML_ATT_VALUE);
				mapper.addKey(null, curKey, curValue);
			}
		}
		return mapper;
	}

	@SuppressWarnings("unchecked")
	private Element getMapper(List nodes) {
		Iterator itr = nodes.iterator();
		while (itr.hasNext()) {
			Element curNode = (Element) itr.next();
			String mapID = curNode.getAttributeValue(XML_ATT_ID);
			if ((mapID != null) && (mapID.equals(mappName)))
				return curNode;
		}
		return null;
	}

}
