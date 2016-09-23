package org.iii.see.dao.wherecon;

public class OrderBy {
	
	private Property property;
	private boolean ascending;
	
	public OrderBy(Property property, boolean ascending) {
		this.property = property;
		this.ascending = ascending;
	}

	public Property getProperty() {
		return property;
	}

	public boolean isAscending() {
		return ascending;
	}

}
