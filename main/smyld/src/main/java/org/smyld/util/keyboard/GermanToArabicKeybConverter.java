package org.smyld.util.keyboard;

public class GermanToArabicKeybConverter extends BasicKeyboardConverter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GermanToArabicKeybConverter() {
		init();
	}

	@Override
	void init() {
		targetCode = "windows-1256";
		sourceKeyboardName = "German";
		targetKeyboardName = "Arabic";
		doLoadKeySet();
	}

	private void doLoadKeySet() {
		convertKeySet = new byte[][] { { 113, -42 }, { 119, -43 },
				{ 101, -53 }, { 114, -34 }, { 116, -35 }, { 122, -37 },
				{ 117, -38 }, { 105, -27 }, { 111, -50 }, { 112, -51 },
				{ -4, -52 }, { 43, -49 }, { 35, -48 }, { 97, -44 },
				{ 115, -45 }, { 100, -19 }, { 102, -56 }, { 103, -31 },
				{ 104, -57 }, { 106, -54 }, { 107, -28 }, { 108, -29 },
				{ -10, -33 }, { -28, -40 }, { 121, -58 }, { 120, -63 },
				{ 99, -60 }, { 118, -47 }, { 98, -31, -57 }, { 110, -20 },
				{ 109, -55 }, { 44, -26 }, { 46, -46 }, { 45, -39 },
				{ 66, -31, -62 }, { 78, -62 }, { 59, 44 }, { 58, -73 },
				{ 95, -65 }, { 71, -31, -61 }, { 72, -61 }, { 74, -36 },
				{ 75, -70 }, { 90, -59 }, { 80, -70 }, { 39, -8 }, { 76, -47 },
				{ 73, -9 }, { 79, -41 }, { -36, 60 }, { 42, 62 }, { 81, -16 },
				{ 87, -14 }, { 82, -13 }, { 84, -11 }, { 69, -10 } };
	}

}
