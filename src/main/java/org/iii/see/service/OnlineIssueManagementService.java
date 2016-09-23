package org.iii.see.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.iii.see.dao.OnlineIssueDao;
import org.iii.see.dao.OnlineIssueFigureDao;
import org.iii.see.domain.OnlineIssue;
import org.iii.see.domain.OnlineIssueFigure;
import org.iii.see.exception.NoDataFoundException;
import org.iii.see.utility.DateTimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class OnlineIssueManagementService {
	
	@Autowired
	private OnlineIssueDao onlineIssueDao;

	@Autowired
	private OnlineIssueFigureDao onlineIssueFigureDao;
	
	public OnlineIssue createOnlineIssue(OnlineIssue onlineIssue) {

		UUID uuid = UUID.randomUUID();
		onlineIssue.setUuid(String.valueOf(uuid));
		onlineIssue.setCreateTime(DateTimeUtility.getCurrentTimestamp());
		 
		return onlineIssueDao.create(onlineIssue);
	}
	
	public OnlineIssueFigure createOnlineIssueFigure(OnlineIssueFigure onlineIssueFigure) {	
		onlineIssueFigure.setCreateTime(DateTimeUtility.getCurrentTimestamp());
		return onlineIssueFigureDao.create(onlineIssueFigure);		
	}
	
	public PagingResultSet queryOnlineIssueList(int recordStart, 
			                                    int recordLength, 
			                                    String queryBeginDate, 
			                                    String queryEndDate,
			                                    String queryProject,
			                                    String queryStatus) {
		
		PagingResultSet pagingResultSet = new PagingResultSet();
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (StringUtils.isNotBlank(queryProject)) {
			params.put("by_project", 1);
			params.put("project", queryProject);			
		} else {
			params.put("by_project", 0);
			params.put("project", StringUtils.EMPTY);			
		}
		
		if (StringUtils.isNotBlank(queryBeginDate)) {
			Timestamp createDateBegin = DateTimeUtility.convertDateStringToSqlTimestamp(queryBeginDate, "yyyy/MM/dd", DateTimeUtility.DATE_BEGIN);
			params.put("by_createTimeBegin", 1);
			params.put("createTimeBegin", createDateBegin);			
		} else {
			params.put("by_createTimeBegin", 0);
			params.put("createTimeBegin", null);
		}
		
		if (StringUtils.isNotBlank(queryEndDate)) {
			Timestamp createDateEnd = DateTimeUtility.convertDateStringToSqlTimestamp(queryEndDate, "yyyy/MM/dd", DateTimeUtility.DATE_END);
			params.put("by_createTimeEnd", 1);
			params.put("createTimeEnd", createDateEnd);			
		} else {
			params.put("by_createTimeEnd", 0);
			params.put("createTimeEnd", null);
		}
		
		if (StringUtils.isNotBlank(queryStatus)) {
			params.put("by_status", 1);
			params.put("status", queryStatus);
		} else {
			params.put("by_status", 0);
			params.put("status", StringUtils.EMPTY);
		}

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = (List<Object[]>)onlineIssueDao.queryByNamedQuery("OnlineIssueManagementService.queryOnlineIssueList", 
                                                                                    params, 
                                                                                    recordStart, 
                                                                                    recordLength);
		
		List countSet = onlineIssueDao.queryByNamedQuery("OnlineIssueManagementService.queryOnlineIssueListCount", params);
		if (countSet != null && countSet.size() == 1) {
			if (countSet.get(0) instanceof Long) {
				Long count = (Long)countSet.get(0);
				pagingResultSet.setRecordsTotal(count.intValue());
			}
		}

		pagingResultSet.setRecordsStart(recordStart);
		pagingResultSet.setPageSize(recordLength);
		pagingResultSet.setResultSet(resultSet);

		return pagingResultSet;
		
	}
		
	public OnlineIssue queryOnlineIssue(String uuid) {
		return onlineIssueDao.query(new OnlineIssue(), uuid);
	}	

	public OnlineIssue updateOnlineIssue(OnlineIssue onlineIssue) throws NoDataFoundException {
		
		OnlineIssue oOnlineIssue = onlineIssueDao.query(new OnlineIssue(), onlineIssue.getUuid());
		if (oOnlineIssue == null) {
			throw new NoDataFoundException();
		}
		
		oOnlineIssue.setReplyContent(onlineIssue.getReplyContent());
		oOnlineIssue.setProcessor(onlineIssue.getProcessor());
		oOnlineIssue.setStatus(onlineIssue.getStatus());
		oOnlineIssue.setUpdateTime(DateTimeUtility.getCurrentTimestamp());
		 
		return onlineIssueDao.update(oOnlineIssue);
	}

	public void deleteOnlineIssue(String uuid) throws NoDataFoundException {
		
		OnlineIssue oOnlineIssue = onlineIssueDao.query(new OnlineIssue(), uuid);
		if (oOnlineIssue == null) {
			throw new NoDataFoundException();
		}
		
		onlineIssueDao.delete(new OnlineIssue(), uuid);
	}
	
	public List<OnlineIssueFigure> queryOnlineIssueFigureList(String uuid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dataUuid", uuid);
		
		List<OnlineIssueFigure> resultSet = (List<OnlineIssueFigure>)onlineIssueDao.queryByNamedQuery("OnlineIssueManagementService.queryOnlineIssueFigureList", params); 
		return resultSet;
	}	

	public OnlineIssueFigure queryOnlineIssueFigure(String uuid) {
		return onlineIssueFigureDao.query(new OnlineIssueFigure(), uuid);
	}	
	
}
