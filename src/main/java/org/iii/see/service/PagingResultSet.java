package org.iii.see.service;

import java.util.List;

public class PagingResultSet {
	
	private List<Object[]> resultSet = null;	
	private int recordsStart = 0;
	private int pageSize = 0;
	private int recordsTotal = 0;
	
	public List<Object[]> getResultSet() {
		return resultSet;
	}
	
	public void setResultSet(List<Object[]> resultSet) {
		this.resultSet = resultSet;
	}
	
	public int getRecordsStart() {
		return recordsStart;
	}
	
	public void setRecordsStart(int recordsStart) {
		this.recordsStart = recordsStart;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	
}
