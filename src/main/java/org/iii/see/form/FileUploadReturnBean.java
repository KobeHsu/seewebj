package org.iii.see.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FileUploadReturnBean implements Serializable {
	
	private static final long serialVersionUID = -949823563882663730L;
	
	private JSONArray jsonArray = new JSONArray();
	
	public void addUploadedEntry(String name, long size, String url) {
		
		Map<String, Object> entry = new HashMap<String, Object>();
		
		entry.put("name", name);
		entry.put("size", size);
		entry.put("url", url);
		
    	jsonArray.add(entry);		
	}

	public String toString() {
		JSONObject jsonObject = new JSONObject();		
    	jsonObject.put("files", jsonArray);
    	return jsonObject.toString();
	}
	
}
