package com.smyld.util;

import java.util.TreeMap;

import com.smyld.util.vmapper.IntMapKeyGenerator;
import com.smyld.util.vmapper.VMapIntRangeKey;
import com.smyld.util.vmapper.VMapIntSingleKey;
import com.smyld.util.vmapper.VMapKeyInt;

public class IntValueMapper extends ValueMapper {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TreeMap<VMapIntSingleKey,Object> map = new TreeMap<VMapIntSingleKey,Object>();
	VMapKeyInt searchKeyInt = new VMapKeyInt();
	VMapKeyInt searchKeyTxt = new VMapKeyInt();
	VMapIntSingleKey intSearchKey = new VMapIntSingleKey();

	public IntValueMapper() {
	}

	public void addKey(VMapIntSingleKey newIntKey, Object keyValue) {
		map.put(newIntKey, keyValue);
	}

	public void addKey(VMapIntRangeKey newIntKey, Object keyValue) {
		map.put(newIntKey, keyValue);
	}

	public void addKey(String keyID1, Object keyValue) {
		IntMapKeyGenerator.generateKey(keyID1, keyValue, this);
	}

	@Override
	public void addKey(String keyID1, String keyID2, Object keyValue) {
		IntValueMapper targetMapper = (IntValueMapper) getKeyValue(keyID1);
		if (targetMapper == null) {
			targetMapper = new IntValueMapper();
			targetMapper.addKey(keyID2, keyValue);
			addKey(keyID1, targetMapper);
		} else {
			targetMapper.addKey(keyID2, keyValue);
		}

	}

	public void addKey(String keyID1, String keyID2, String keyID3,
			Object keyValue) {
		IntValueMapper targetMapper = (IntValueMapper) getKeyValue(keyID1,
				keyID2);
		if (targetMapper == null) {
			targetMapper = new IntValueMapper();
			targetMapper.addKey(keyID3, keyValue);
			addKey(keyID1, keyID2, targetMapper);
		} else {
			targetMapper.addKey(keyID3, keyValue);
		}

	}

	public Object getKeyValue(int intValue) {
		// searchKeyInt.setSingleValue(intValue);
		// return map.get(searchKeyInt);
		intSearchKey.setValue(intValue);
		return map.get(intSearchKey);
	}

	public Object getKeyValue(int keyValue1, int keyValue2) {
		Object targetValue = getKeyValue(keyValue1);
		if ((targetValue != null) && (targetValue instanceof IntValueMapper))
			return ((IntValueMapper) targetValue).getKeyValue(keyValue2);
		return targetValue;
	}

	public Object getKeyValue(int keyValue1, int keyValue2, int keyValue3) {
		Object targetValue = getKeyValue(keyValue1, keyValue2);
		if ((targetValue != null) && (targetValue instanceof IntValueMapper))
			return ((IntValueMapper) targetValue).getKeyValue(keyValue3);
		return targetValue;
	}

	@Override
	public Object getKeyValue(String txtValue) {
		searchKeyTxt.setValue(txtValue);
		return map.get(searchKeyTxt);
	}

	@Override
	public Object getKeyValue(String keyValue1, String keyValue2) {
		Object targetValue = getKeyValue(keyValue1);
		if ((targetValue != null) && (targetValue instanceof IntValueMapper))
			return ((IntValueMapper) targetValue).getKeyValue(keyValue2);
		return targetValue;
	}

	@Override
	public Object getKeyValue(String keyValue1, String keyValue2,
			String keyValue3) {
		Object targetValue = getKeyValue(keyValue1, keyValue2);
		if ((targetValue != null) && (targetValue instanceof IntValueMapper))
			return ((IntValueMapper) targetValue).getKeyValue(keyValue3);
		return targetValue;
	}

}
