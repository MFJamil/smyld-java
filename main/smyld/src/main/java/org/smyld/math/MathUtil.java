package org.smyld.math;

import org.smyld.SMYLDObject;

public class MathUtil extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MathUtil() {
	}

	public static int[] getFieldsSet(String fieldsBitmap) {
		int[] primResult = new int[fieldsBitmap.length()];
		int setFieldsCounter = 0;
		for (int i = 0; i < fieldsBitmap.length(); i++) {
			if (fieldsBitmap.charAt(i) == '1') {
				primResult[setFieldsCounter] = i + 1;
				setFieldsCounter++;
			}
		}
		int[] finalResult = new int[setFieldsCounter];
		System.arraycopy(primResult, 0, finalResult, 0, setFieldsCounter);
		return finalResult;
	}
}
