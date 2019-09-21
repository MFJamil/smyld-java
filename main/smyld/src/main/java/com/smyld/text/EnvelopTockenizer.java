package com.smyld.text;

public class EnvelopTockenizer extends TextTokenizer {
	private String envelopeStart;
	@SuppressWarnings("unused")
	private String envelopeEnd;

	public EnvelopTockenizer(String envelopList, String envStart, String envEnd) {
		super(envelopList, envEnd);
		envelopeStart = envStart;
		envelopeEnd = envEnd;
	}

	public String[] parseEnvelops() {
		String[] reultant = super.parseTokens();
		for (int i = 0; i < reultant.length; i++)
			reultant[i] = reultant[i].substring(reultant[i]
					.indexOf(envelopeStart)
					+ envelopeStart.length());
		return reultant;
	}

}
