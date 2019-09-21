package org.smyld.util.alias;
import static org.smyld.util.alias.AliasConstants.*;

public class XMLAliasSource extends AliasSource {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String target;
	String name;
	int schemaType = XML_SCHEME_TYPE_TAGS;


	public int getSchemaType() {
		return schemaType;
	}

	public void setSchemaType(int schemaType) {
		this.schemaType = schemaType;
	}

	public XMLAliasSource() {
		super(ALIAS_SRC_TYPE_XML);
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
