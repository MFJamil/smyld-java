package com.smyld.util.vmapper;

import com.smyld.SMYLDObject;
import com.smyld.util.IntValueMapper;

public class IntMapKeyGenerator extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IntMapKeyGenerator() {
	}

	public static void generateKey(String keyValue, Object value,
			IntValueMapper mapper) {
		//StringBuffer buffer = new StringBuffer();
		String[] valueItems = keyValue.split(",");
		for (int i = 0; i < valueItems.length; i++) {
			VMapIntSingleKey newKey = null;
			int sep = valueItems[i].indexOf("-");
			if (sep != -1) {
				String minValue = valueItems[i].substring(0, sep);
				String maxValue = valueItems[i].substring(sep + 1);
				int minIntValue = Integer.parseInt(minValue.trim());
				int maxIntValue = Integer.parseInt(maxValue);
				if (minIntValue > maxIntValue) {
					newKey = new VMapIntRangeKey(maxIntValue, minIntValue);
				} else {
					newKey = new VMapIntRangeKey(minIntValue, maxIntValue);
				}
			} else {
				newKey = new VMapIntSingleKey(Integer.parseInt(valueItems[i]
						.trim()));
			}
			if (newKey != null)
				mapper.addKey(newKey, value);
		}
	}
}
