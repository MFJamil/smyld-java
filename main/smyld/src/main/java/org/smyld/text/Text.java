/*
 * @(#)Text.java	1.1 24/01/03
 *
 * Copyright 2000-2003 SMYLD Software, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of SMYLD Software, Inc.  
 * Use is subject to license terms.
 * 
 */

package org.smyld.text;

/**
 * This class will extend the String class methodes to facilitate the text
 * processing, although that String and StringBuffer classes are final, they
 * will be used as instances to get used from the already provided
 * functionality.
 */
public class Text {
	/**
	 * The text to be processed
	 */
	private String text;
	/**
	 * The text buffer to be processed
	 */

	private StringBuffer textBuffer;

	/**
	 * Constructs the class with the given String
	 */
	public Text(String newText) {
		text = newText;
		textBuffer = new StringBuffer(newText);
	}

	public Text() {
		textBuffer = new StringBuffer();
	}

	public void setText(String Text) {
		text = Text;
		textBuffer.setLength(0);
		textBuffer.append(Text);
	}

	/**
	 * Returns the index within this string of the first occurrence of the
	 * specified substring. The integer returned is the smallest value <i>k</i>
	 * such that: <blockquote>
	 * 
	 * <pre>
	 * this.startsWith(str, &lt;i&gt;k&lt;/i&gt;)
	 * </pre>
	 * 
	 * </blockquote> is <code>true</code>.
	 * 
	 * @param str
	 *            any string.
	 * @return if the string argument occurs as a substring within this object,
	 *         then the index of the first character of the first such substring
	 *         is returned; if it does not occur as a substring, <code>-1</code>
	 *         is returned.
	 * @exception java.lang.NullPointerException
	 *                if <code>str</code> is <code>null</code>.
	 */
	public int indexOf(String searchText) {
		return text.indexOf(searchText);
	}

	/**
	 * Returns the index within this string of the first occurrence of the
	 * specified substring, starting at the specified index. The integer
	 * returned is the smallest value <i>k</i> such that: <blockquote>
	 * 
	 * <pre>
	 * this.startsWith(str, &lt;i&gt;k&lt;/i&gt;) &amp;&amp; (&lt;i&gt;k&lt;/i&gt; &gt;= fromIndex)
	 * </pre>
	 * 
	 * </blockquote> is <code>true</code>.
	 * <p>
	 * There is no restriction on the value of <code>fromIndex</code>. If it
	 * is negative, it has the same effect as if it were zero: this entire
	 * string may be searched. If it is greater than the length of this string,
	 * it has the same effect as if it were equal to the length of this string:
	 * <code>-1</code> is returned.
	 * 
	 * @param str
	 *            the substring to search for.
	 * @param fromIndex
	 *            the index to start the search from.
	 * @return If the string argument occurs as a substring within this object
	 *         at a starting index no smaller than <code>fromIndex</code>,
	 *         then the index of the first character of the first such substring
	 *         is returned. If it does not occur as a substring starting at
	 *         <code>fromIndex</code> or beyond, <code>-1</code> is
	 *         returned.
	 * @exception java.lang.NullPointerException
	 *                if <code>str</code> is <code>null</code>
	 */
	public int indexOf(String searchText, int fromIndex) {
		return text.indexOf(searchText, fromIndex);
	}

	/**
	 * replaces the provided new text with the old text, the operation will take
	 * place on the first occurance for the old text after the given index
	 * 
	 * @param oldText
	 *            The old text to be replaced
	 * @param newText
	 *            The new text
	 * @param fromIndex
	 *            The starting position
	 * @exception java.lang.NullPointerException
	 *                if either texts are <code>null</code>
	 */
	public void replace(String oldText, String newText, int fromIndex) {
		int startPosition = indexOf(oldText, fromIndex);
		if (startPosition != -1) {
			if (newText == null)
				throw new NullPointerException("New String is null");
			textBuffer.replace(startPosition, startPosition + oldText.length(),
					newText);
			text = textBuffer.toString();
		}
	}

	/**
	 * replaces the provided new text with the old text, the operation will take
	 * place on the first occurance for the old text
	 * 
	 * @param oldText
	 *            The old text to be replaced
	 * @param newText
	 *            The new text
	 * @exception java.lang.NullPointerException
	 *                if either texts are <code>null</code>
	 */
	public void replace(String oldText, String newText) {
		replace(oldText, newText, 0);
	}

	/**
	 * replaces the provided new text with every occurance for the old text
	 * 
	 * @param oldText
	 *            The old text to be replaced
	 * @param newText
	 *            The new text
	 * @return the number of the occured replace times, -1 in when no replace
	 *         took place
	 * @exception java.lang.NullPointerException
	 *                if either texts are <code>null</code>
	 */

	public int replaceAll(String oldText, String newText) {
		int lastIndex = 0;
		int replaceNo = -1;
		while ((lastIndex = indexOf(oldText, lastIndex)) != -1) {
			replaceNo++;
			replace(oldText, newText, lastIndex);
		}
		return replaceNo;
	}

	/**
	 * searches the gievn text and returns the number of occurances
	 * 
	 * @param searchText
	 *            The text to search for
	 * @return the number of the occurance , -1 in when no occurance exist
	 * @exception java.lang.NullPointerException
	 *                if searchTexts is <code>null</code>
	 */

	public int occuranceNo(String searchText) {
		int lastIndex = 0;
		int occurNo = 0;
		while ((lastIndex = indexOf(searchText, lastIndex)) != -1) {
			occurNo++;
			lastIndex++;
		}
		if (occurNo > 0)
			return occurNo;
		return -1;
	}

	/**
	 * returns the text after processing
	 * 
	 * @return resultant text
	 */

	@Override
	public String toString() {
		return text;
	}

}
