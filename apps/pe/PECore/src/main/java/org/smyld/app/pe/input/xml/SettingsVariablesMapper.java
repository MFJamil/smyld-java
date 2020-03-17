/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.input.xml;

import java.util.HashMap;

public class SettingsVariablesMapper {
	
	HashMap<String,String> mapper =  new HashMap<String,String>();
	public SettingsVariablesMapper(){
		
	}
	public void addVariable(String name,String value){
		mapper.put(name, value);
	}
	public String map(String name){
		return mapper.get(name);
	}
	public int getVariablesNumber(){
		return mapper.size();
	}
}
