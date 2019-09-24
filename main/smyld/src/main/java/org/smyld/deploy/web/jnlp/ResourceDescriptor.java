package org.smyld.deploy.web.jnlp;

import java.util.Vector;

import org.jdom2.Element;

import org.smyld.deploy.web.Descriptor;

public class ResourceDescriptor extends Descriptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String j2seVersion;
	Vector<JARDescriptor> jars;

	public ResourceDescriptor() {
	}

	public void addJAR(JARDescriptor newJar) {
		if (jars == null)
			jars = new Vector<JARDescriptor>();
		jars.add(newJar);
	}

	public String getJ2seVersion() {
		if (j2seVersion == null)
			return ATT_J2SE_DEFAULT;
		return j2seVersion;
	}

	public void setJ2seVersion(String j2seVersion) {
		this.j2seVersion = j2seVersion;
	}

	public Vector<JARDescriptor> getJars() {
		return jars;
	}

	public void setJars(Vector<JARDescriptor> jars) {
		this.jars = jars;
	}

	@Override
	public Element getXMLElement() {
		Element res = new Element(TAG_NAME_RES);
		if (getJ2seVersion() != null) {
			Element j2se = new Element(TAG_NAME_J2SE);
			j2se.setAttribute(TAG_ATT_VER, getJ2seVersion());
			res.addContent(j2se);
		}
		// adding the jar references
		if (jars != null) {
			for (JARDescriptor curJar : jars) {
				res.addContent(curJar.getXMLElement());
			}
		}
		return res;
	}
}
