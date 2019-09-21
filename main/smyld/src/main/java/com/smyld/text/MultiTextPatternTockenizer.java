package com.smyld.text;

import java.util.Vector;

public class MultiTextPatternTockenizer extends MultiTextTokenizer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String patternStart;
	String patternEnd;
	boolean activatePattern;
	Text contentText;
	Vector<String> tockenz;

	public MultiTextPatternTockenizer(String[] Separators,
			boolean WithSeparators) {
		super(Separators, WithSeparators);
	}

	public MultiTextPatternTockenizer(String Text, String[] Separators,
			boolean WithSeparators) {
		super(Text, Separators, WithSeparators);
	}

	public void setPattern(String startPatternText, String endPatternText) {
		patternStart = startPatternText;
		patternEnd = endPatternText;
		if ((patternStart != null) && (patternEnd != null))
			activatePattern = true;

	}

	@Override
	public String[] parseTokens() {
		String[] results = super.parseTokens();
		boolean detectingEnd = false;
		int diff = 0;
		StringBuffer condsBuffer = new StringBuffer();
		if (results.length > 0) {
			tockenz = new Vector<String>();
			contentText = new Text();
			for (int i = 0; i < results.length; i += 2) {
				String currentCond = (String) results[i];
				if (detectingEnd) {
					diff = isConditionCompelete(currentCond.trim(), diff);
					if (i != 0)
						condsBuffer.append(results[i - 1]);
					condsBuffer.append(currentCond.trim());
					if (diff == 0) {
						detectingEnd = false;
						tockenz.add(condsBuffer.deleteCharAt(
								condsBuffer.length() - 1).toString());
						condsBuffer.setLength(0);
					}
				} else if (currentCond.trim().startsWith(patternStart)) {
					diff = isConditionCompelete(currentCond.trim(), diff);
					if (i != 0)
						tockenz.add(results[i - 1]);
					if (diff != 0) {
						detectingEnd = true;
						condsBuffer.append(currentCond.trim().substring(1));
					} else {
						tockenz.add(currentCond.trim().substring(1,
								currentCond.length() - 1));
					}
				} else {
					if (i != 0)
						tockenz.add(results[i - 1]);
					tockenz.add(currentCond);
				}
			}
			String[] result = new String[tockenz.size()];
			tockenz.copyInto(result);
			return result;
		}
		return results;
	}

	private int isConditionCompelete(String cond, int lastDiff) {
		contentText.setText(cond);
		int openingBracketNo = contentText.occuranceNo(patternStart);
		int closingBracketNo = contentText.occuranceNo(patternEnd);
		int diff = lastDiff + openingBracketNo - closingBracketNo;
		return diff;
	}

}
