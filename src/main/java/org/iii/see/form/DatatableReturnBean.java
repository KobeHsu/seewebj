package org.iii.see.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DatatableReturnBean implements Serializable {

	private static final long serialVersionUID = -5846628493012093925L;
	
	private int draw;	
	private int recordsTotal;
	private int recordsFiltered;	
	private List<Map<String, Object>> recordSet = new ArrayList<Map<String, Object>>();

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}
	
	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	
	public void addRecord(Map<String, Object> record) {
		if (record != null) {
			recordSet.add(record);
		}
	}
	
	public String toString() {
    	JSONObject jsonObject = new JSONObject();

    	jsonObject.put("draw", draw);
    	jsonObject.put("recordsTotal", recordsTotal);
    	jsonObject.put("recordsFiltered", recordsTotal);
    	
    	JSONArray jsonArray = new JSONArray();
    	for (Map<String, Object> record : recordSet) {
    		jsonArray.add(record);
    	}
    
    	jsonObject.put("data", jsonArray);
    	return jsonObject.toString();
	}
	
}
