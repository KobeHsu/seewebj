package org.iii.see.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.iii.see.dao.UsibilityTestFormTemplateDao;
import org.iii.see.domain.UsibilityTestFormTemplate;
import org.iii.see.exception.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsibilityTestFormManagementService {

	@Autowired
	private UsibilityTestFormTemplateDao usibilityTestFormTemplateDao;

	public List<UsibilityTestFormTemplate> queryUsibilityTestFormTemplateList(String projectUuid) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectUuid", projectUuid);
		
		@SuppressWarnings("unchecked")
		List<UsibilityTestFormTemplate> usibilityTestFormTemplateList = usibilityTestFormTemplateDao.queryByNamedQuery("UsibilityTestFormManagement.queryUsibilityTestFormTemplate", params);
		
		return usibilityTestFormTemplateList;
	}	
	
	public UsibilityTestFormTemplate createUsibilityTestFormTemplate(UsibilityTestFormTemplate usibilityTestFormTemplate) {
		
		UUID uuid = UUID.randomUUID();
		usibilityTestFormTemplate.setUuid(String.valueOf(uuid));
		usibilityTestFormTemplate.setCreateTime(new Timestamp((new Date()).getTime()));
		
		return usibilityTestFormTemplateDao.create(usibilityTestFormTemplate);
	}	
	
	public UsibilityTestFormTemplate updateUsibilityTestFormTemplate(UsibilityTestFormTemplate usibilityTestFormTemplate) throws NoDataFoundException {
		
		UsibilityTestFormTemplate oUsibilityTestFormTemplate = usibilityTestFormTemplateDao.query(new UsibilityTestFormTemplate(), usibilityTestFormTemplate.getUuid());
		if (oUsibilityTestFormTemplate == null) {
			throw new NoDataFoundException();
		}
		// 搬部分欄位至原資料, 避免將未提供修改欄位清空
		oUsibilityTestFormTemplate.setName(usibilityTestFormTemplate.getName());
		oUsibilityTestFormTemplate.setSerialNo(usibilityTestFormTemplate.getSerialNo());
		oUsibilityTestFormTemplate.setDescription(usibilityTestFormTemplate.getDescription());
		oUsibilityTestFormTemplate.setUpdateTime(new Timestamp((new Date()).getTime()));
		
		return usibilityTestFormTemplateDao.update(oUsibilityTestFormTemplate);
	}	

	public UsibilityTestFormTemplate updateUsibilityTestFormTemplateFile(String uuid, String fileName, String extName) throws NoDataFoundException {
		
		UsibilityTestFormTemplate oUsibilityTestFormTemplate = usibilityTestFormTemplateDao.query(new UsibilityTestFormTemplate(), uuid);
		if (oUsibilityTestFormTemplate == null) {
			throw new NoDataFoundException();
		}
		// 搬部分欄位至原資料, 避免將未提供修改欄位清空
		oUsibilityTestFormTemplate.setFileName(fileName);
		oUsibilityTestFormTemplate.setExtName(extName);
		oUsibilityTestFormTemplate.setUpdateTime(new Timestamp((new Date()).getTime()));
		
		return usibilityTestFormTemplateDao.update(oUsibilityTestFormTemplate);
	}	
	
	public UsibilityTestFormTemplate queryUsibilityTestFormTemplate(String uuid) {		
		return usibilityTestFormTemplateDao.query(new UsibilityTestFormTemplate(), uuid);
	}	

	public void deleteUsibilityTestFormTemplate(String uuid) throws NoDataFoundException {
		
		UsibilityTestFormTemplate oUsibilityTestFormTemplate = usibilityTestFormTemplateDao.query(new UsibilityTestFormTemplate(), uuid);
		if (oUsibilityTestFormTemplate == null) {
			throw new NoDataFoundException();
		}
		
		usibilityTestFormTemplateDao.delete(new UsibilityTestFormTemplate(), uuid);
	}	
	
}
