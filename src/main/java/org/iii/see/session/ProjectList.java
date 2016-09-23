package org.iii.see.session;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

public class ProjectList {
	
	private ArrayList<Map.Entry<String, String>> items = new ArrayList<Map.Entry<String, String>>();

	public void add(String projectUuid, String projectName) {
		Map.Entry<String, String> item = new AbstractMap.SimpleEntry(projectUuid, projectName);
		items.add(item);
	}
	
	public void add(Map.Entry<String, String> item) {
		items.add(item);
	}

	public void clear() {
		items.clear();
	}
	
	public ArrayList<Map.Entry<String, String>> getItems() {
		return items;
	}

	public void setItems(ArrayList<Map.Entry<String, String>> items) {
		this.items = items;
	}

}
