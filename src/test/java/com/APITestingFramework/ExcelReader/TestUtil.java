package com.APITestingFramework.ExcelReader;

import org.json.JSONObject;

public class TestUtil {
	
	public static boolean jsonHaskey(String json, String key) {
		
		JSONObject jsonObject = new JSONObject(json);
		return jsonObject.has(key);
	}
	
	public static String getJsonkeyValue(String json,String key) {
		
		JSONObject jsonObject = new JSONObject(json);
		return jsonObject.get(key).toString();
	}

}
