package org.iii.see.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.iii.see.dao.CaseExtraDefinitionDao;
import org.iii.see.domain.CaseExtraDefinition;
import org.iii.see.exception.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CaseExtraDefinitionManagementService {

	@Autowired
	private CaseExtraDefinitionDao caseExtraDefinitionDao;
	
	public List<CaseExtraDefinition> queryCaseExtraDefinitionList(String projectUuid) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectUuid", projectUuid);
		
		@SuppressWarnings("unchecked")
		List<CaseExtraDefinition> caseExtraDefinitionList = caseExtraDefinitionDao.queryByNamedQuery("CaseExtraDefinitionManagement.queryCaseExtraDefinition", params);
		
		return caseExtraDefinitionList;
	}	
	
	public CaseExtraDefinition createCaseExtraDefinition(CaseExtraDefinition caseExtraDefinition) {
		
		UUID uuid = UUID.randomUUID();
		caseExtraDefinition.setUuid(String.valueOf(uuid));
		caseExtraDefinition.setCreateTime(new Timestamp((new Date()).getTime()));
		
		return caseExtraDefinitionDao.create(caseExtraDefinition);
	}	

	public CaseExtraDefinition updateCaseExtraDefinition(CaseExtraDefinition caseExtraDefinition) throws NoDataFoundException {
		
		CaseExtraDefinition oCaseExtraDefinition = caseExtraDefinitionDao.query(new CaseExtraDefinition(), caseExtraDefinition.getUuid());
		if (oCaseExtraDefinition == null) {
			throw new NoDataFoundException();
		}
		// 搬部分欄位至原資料, 避免將未提供修改欄位清空
		oCaseExtraDefinition.setName(caseExtraDefinition.getName());
		oCaseExtraDefinition.setSerialNo(caseExtraDefinition.getSerialNo());
		oCaseExtraDefinition.setValueType(caseExtraDefinition.getValueType());
		oCaseExtraDefinition.setValueLength(caseExtraDefinition.getValueLength());
		oCaseExtraDefinition.setValueMeasure(caseExtraDefinition.getValueMeasure());
		
		return caseExtraDefinitionDao.update(caseExtraDefinition);
	}	

	public void deleteCaseExtraDefinition(String uuid) throws NoDataFoundException {
		
		CaseExtraDefinition oCaseExtraDefinition = caseExtraDefinitionDao.query(new CaseExtraDefinition(), uuid);
		if (oCaseExtraDefinition == null) {
			throw new NoDataFoundException();
		}
		caseExtraDefinitionDao.delete(oCaseExtraDefinition, uuid);
	}	
	
}
