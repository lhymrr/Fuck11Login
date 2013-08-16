package com.l.h.y.fucklogin.Http;

import java.util.HashMap;
import java.util.Set;

public class Bundle {
	
	private HashMap<String, String> map = new HashMap<String, String>();

	public Bundle() {
	}
	
	public void putString(String key, String value){
		map.put(key, value);
	}
	
	public String getString(String key){
		
		return map.get(key);
	}
	
	public Set<String> keySet(){
		
		return map.keySet();
	}
	
	public int size(){
		return map.size();
	}
}
