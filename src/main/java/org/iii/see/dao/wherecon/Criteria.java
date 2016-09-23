package org.iii.see.dao.wherecon;

import java.util.ArrayList;
import java.util.List;

public class Criteria {

	public static final int EQ = 1;
	public static final int NE = 2;
	public static final int LIKE = 3;
	public static final int GE = 4;
	public static final int GT = 5;
	public static final int LE = 6;
	public static final int LT = 7;
		
	private List<Criterion> criterionList = new ArrayList();
	private List<OrderBy> orderByList = new ArrayList();
		
	public Criteria eq(Property property, Object value) {
		criterionList.add(new Criterion(property, EQ, value));
		return this;
	}

	public Criteria eq(String property, Object value) {
		return eq(new Property(property), value);
	}

	public Criteria ne(Property property, Object value) {
		criterionList.add(new Criterion(property, NE, value));
		return this;
	}

	public Criteria ne(String property, Object value) {
		return ne(new Property(property), value);
	}

	public Criteria like(Property property, Object value) {
		criterionList.add(new Criterion(property, LIKE, value));
		return this;
	}

	public Criteria like(String property, Object value) {
		return like(new Property(property), value);
	}
	
	public Criteria orderBy(Property property, boolean ascending) {
		this.orderByList.add(new OrderBy(property, ascending));
		return this;
	}	

	public Criteria orderBy(String property, boolean ascending) {
		this.orderByList.add(new OrderBy(new Property(property), ascending));
		return this;
	}	

	public Criteria ge(Property property, Object value) {
		criterionList.add(new Criterion(property, GE, value));
		return this;
	}

	public Criteria ge(String property, Object value) {
		return ge(new Property(property), value);
	}

	public Criteria gt(Property property, Object value) {
		criterionList.add(new Criterion(property, GT, value));
		return this;
	}

	public Criteria gt(String property, Object value) {
		return gt(new Property(property), value);
	}

	public Criteria le(Property property, Object value) {
		criterionList.add(new Criterion(property, LE, value));
		return this;
	}

	public Criteria le(String property, Object value) {
		return le(new Property(property), value);
	}

	public Criteria lt(Property property, Object value) {
		criterionList.add(new Criterion(property, LT, value));
		return this;
	}

	public Criteria lt(String property, Object value) {
		return lt(new Property(property), value);
	}
	
	public List<Criterion> getCriterionList() {
		return criterionList;
	}

	public List<OrderBy> getOrderByList() {
		return orderByList;
	}
	
}
