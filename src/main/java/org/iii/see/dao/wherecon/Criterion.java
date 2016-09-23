package org.iii.see.dao.wherecon;

public class Criterion {

	private Property property;
	private int operation;
	private Object value1;
	private Object value2;
	
	public Criterion(Property property, int operation, Object value1, Object value2) {
		this.property = property;
		this.operation = operation;
		this.value1 = value1;
		this.value2 = value2;
	}
	
	public Criterion(Property property, int operation, Object value1) {
		this.property = property;
		this.operation = operation;
		this.value1 = value1;
	}

	public Criterion(Property property, int operation) {
		this.property = property;
		this.operation = operation;
	}

	public Property getProperty() {
		return property;
	}

	public int getOperation() {
		return operation;
	}

	public Object getValue1() {
		return value1;
	}

	public Object getValue2() {
		return value2;
	}

}
