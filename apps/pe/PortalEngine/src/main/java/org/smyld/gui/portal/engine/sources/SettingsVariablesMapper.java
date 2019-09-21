package org.smyld.gui.portal.engine.sources;

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
