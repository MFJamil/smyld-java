package org.smyld.deploy.web.jnlp;

import org.smyld.text.HTMLFileWriter;

public class JNLPHTMLDocument extends HTMLFileWriter implements JNLPConstants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JNLPFileWriter sourceFileWriter;

	public JNLPHTMLDocument() {
		super();
	}

	public JNLPHTMLDocument(JNLPFileWriter SourceFileWriter) throws Exception {
		super();
		sourceFileWriter = SourceFileWriter;
		// compose();
	}

	@Override
	public void compose() {
		setFileName(HTML_FILE_NAME);
		if (sourceFileWriter.getTitle() != null)
			setTitle(sourceFileWriter.getTitle());
		else
			setTitle(HTML_TITLE_DEFAULT);
		setHeadline(HTML_HEADLINE_DEFAULT);
		if (sourceFileWriter.getIcon() != null)
			addImage(sourceFileWriter.getIcon());
		String linkText = sourceFileWriter.getName();
		if (linkText != null)
			addLink(sourceFileWriter.getName(), sourceFileWriter.getTitle());
		super.compose();
	}

}
