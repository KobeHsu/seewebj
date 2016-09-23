package org.iii.see.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.iii.see.dao.CaseAttachmentDao;
import org.iii.see.dao.CaseBasicDataDao;
import org.iii.see.dao.CaseExtraDataDao;
import org.iii.see.dao.CasePortraitFileDao;
import org.iii.see.domain.CaseAttachment;
import org.iii.see.domain.CaseBasicData;
import org.iii.see.domain.CaseExtraData;
import org.iii.see.domain.CasePortraitFile;
import org.iii.see.exception.DuplicatedDataException;
import org.iii.see.exception.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CaseManagementService {

	@Autowired
	private CaseBasicDataDao caseBasicDataDao;

	@Autowired
	private CaseExtraDataDao caseExtraDataDao;
	
	@Autowired
	private CasePortraitFileDao casePortraitFileDao;

	@Autowired
	private CaseAttachmentDao caseAttachmentDao;
	
	public CaseBasicData createCase(CaseBasicData caseBasicData) {

		// TODO: 檢查個案編號是否已經存在
		
		UUID uuid = UUID.randomUUID();
		caseBasicData.setUuid(String.valueOf(uuid));
		caseBasicData.setCreateTime(new Timestamp((new Date()).getTime()));
		 
		CaseBasicData createdCaseBasicData = caseBasicDataDao.create(caseBasicData);
		return createdCaseBasicData;
	}
	
	public List<Object[]> queryCaseList(String projectUuid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectUuid", projectUuid);
		
		// 查詢結果欄位順序
	    // 0: uuid
	    // 1: projectUuid
	    // 2: caseNo
	    // 3: realname
	    // 4: sex
	    // 5: age
	    // 6: businessPhone
	    // 7: mobilePhone
	    // 8: createTime		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = (List<Object[]>)caseBasicDataDao.queryByNamedQuery("CaseManagement.queryCaseList", params); 

		return resultSet;
	}

	public CaseBasicData createCase(CaseBasicData caseBasicData, List<Map<String, String>> caseExtraDataList) throws DuplicatedDataException {
		// 檢查個案編號是否已經存在
		if (isCaseNoDuplicated(caseBasicData.getProjectUuid(), caseBasicData.getCaseNo())) {
			throw new DuplicatedDataException();
		}
		
		UUID dataUuid = UUID.randomUUID();
		caseBasicData.setUuid(String.valueOf(dataUuid));
		caseBasicData.setCreateTime(new Timestamp((new Date()).getTime()));
		 
		CaseBasicData createdCaseBasicData = caseBasicDataDao.create(caseBasicData);
		
		// 新增進階欄位
		if (caseExtraDataList != null) {
			for (Map<String, String> caseExtraDataMap: caseExtraDataList) {
				String definitionUuid = caseExtraDataMap.get("definitionUuid");
				String content = caseExtraDataMap.get("content");
				
				CaseExtraData caseExtraData = new CaseExtraData();
				
				caseExtraData.setDataUuid(String.valueOf(dataUuid));
				caseExtraData.setProjectUuid(caseBasicData.getProjectUuid());
				caseExtraData.setDefinitionUuid(definitionUuid);
				caseExtraData.setContent(content);				
				caseExtraData.setUuid(String.valueOf(UUID.randomUUID()));
				
				caseExtraDataDao.create(caseExtraData);
			}
		}
		
		return createdCaseBasicData;
	}

	public CaseBasicData updateCase(CaseBasicData caseBasicData, List<Map<String, String>> caseExtraDataList) throws NoDataFoundException, DuplicatedDataException {

		CaseBasicData oCaseBasicData = caseBasicDataDao.query(new CaseBasicData(), caseBasicData.getUuid());
		if (oCaseBasicData == null) {
			throw new NoDataFoundException();
		}

		// 若個案編號有修改, 檢查新個案編號是否已經存在
		if (!StringUtils.equals(oCaseBasicData.getCaseNo(), caseBasicData.getCaseNo()) && 
				isCaseNoDuplicated(caseBasicData.getProjectUuid(), caseBasicData.getCaseNo())) {
			throw new DuplicatedDataException();
		}		
		
		// 自行複製屬性, 避免蓋到不可複製的屬性
		oCaseBasicData.setCaseNo(caseBasicData.getCaseNo());
		oCaseBasicData.setRealname(caseBasicData.getRealname());
		oCaseBasicData.setSex(caseBasicData.getSex());
		oCaseBasicData.setAge(caseBasicData.getAge());
		oCaseBasicData.setHeight(caseBasicData.getHeight());
		oCaseBasicData.setWeight(caseBasicData.getWeight());
		oCaseBasicData.setOccupation(caseBasicData.getOccupation());
		oCaseBasicData.setIncome(caseBasicData.getIncome());
		oCaseBasicData.setBusinessPhone(caseBasicData.getBusinessPhone());
		oCaseBasicData.setMobilePhone(caseBasicData.getMobilePhone());
		oCaseBasicData.setAddress(caseBasicData.getAddress());
		oCaseBasicData.setUpdator(caseBasicData.getUpdator());
		oCaseBasicData.setUpdateTime(new Timestamp((new Date()).getTime()));
		
		oCaseBasicData = caseBasicDataDao.update(oCaseBasicData);
		
		// 新增進階欄位
		if (caseExtraDataList != null) {
			for (Map<String, String> caseExtraDataMap: caseExtraDataList) {
				String uuid = caseExtraDataMap.get("uuid");
				String content = caseExtraDataMap.get("content");
				
				if (StringUtils.isEmpty(uuid)) {
					
					String definitionUuid = caseExtraDataMap.get("definitionUuid");
					
					CaseExtraData caseExtraData = new CaseExtraData();
					
					caseExtraData.setDataUuid(caseBasicData.getUuid());
					caseExtraData.setProjectUuid(caseBasicData.getProjectUuid());
					caseExtraData.setDefinitionUuid(definitionUuid);
					caseExtraData.setContent(content);				
					caseExtraData.setUuid(String.valueOf(UUID.randomUUID()));
					
					caseExtraDataDao.create(caseExtraData);
				} else {
					CaseExtraData oCaseExtraData = caseExtraDataDao.query(new CaseExtraData(), uuid);
					oCaseExtraData.setContent(content);
					
					caseExtraDataDao.update(oCaseExtraData);					
				}
			}
		}
		
		return oCaseBasicData;
	}
	
	public Object[] queryCase(String projectUuid, String dataUuid) {
		
		Object[] resultSet = new Object[4];
		
		// 0: CaseBasicData
		CaseBasicData caseBasicData = caseBasicDataDao.query(new CaseBasicData(), dataUuid);
		if (caseBasicData == null) {
			return null;
		}
		
		resultSet[0] = caseBasicData;
		
		// 1: caseExtraDataList
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectUuid", projectUuid);
		params.put("dataUuid", dataUuid);
		
		@SuppressWarnings("unchecked")
		List<CaseExtraData> caseExtraDataList = caseBasicDataDao.queryByNamedQuery("CaseManagement.queryCaseExtraData", params);		
		resultSet[1] = caseExtraDataList;		
		
		params.clear();
		params.put("dataUuid", dataUuid);

		@SuppressWarnings("unchecked")
		List<CasePortraitFile> casePortraitFileList = casePortraitFileDao.queryByNamedQuery("CaseManagement.queryCasePortraitFile", params);
		if (casePortraitFileList != null && casePortraitFileList.size() > 0) {
			resultSet[2] = casePortraitFileList.get(0);
		}

		List<CaseAttachment> caseAttachmentList = caseAttachmentDao.queryByNamedQuery("CaseManagement.queryCaseAttachment", params);
		if (casePortraitFileList != null && casePortraitFileList.size() > 0) {
			resultSet[3] = caseAttachmentList;
		}
		
		return resultSet;
	}

	public boolean isCaseNoDuplicated(String projectUuid, String caseNo) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectUuid", projectUuid);
		params.put("caseNo", caseNo);
		
		List countSet = caseBasicDataDao.queryByNamedQuery("CaseManagement.checkDuplicateCaseNo", params);
		if (countSet != null && countSet.size() == 1) {
			if (countSet.get(0) instanceof Long) {
				Long count = (Long)countSet.get(0);
				if (count.intValue() > 0) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void deleteCase(String dataUuid) throws NoDataFoundException {
		CaseBasicData oCaseBasicData = caseBasicDataDao.query(new CaseBasicData(), dataUuid);
		if (oCaseBasicData == null) {
			throw new NoDataFoundException();
		}

		// 刪除進階資料
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectUuid", oCaseBasicData.getProjectUuid());
		params.put("dataUuid", dataUuid);
		
		caseExtraDataDao.executeByNamedQuery("CaseManagement.deleteCaseExtraData", params);
		
		// 刪除基本資料
		caseBasicDataDao.delete(new CaseBasicData(), dataUuid);
		
		// 刪除圖檔資料
		params.clear();
		params.put("dataUuid", dataUuid);
		
		casePortraitFileDao.executeByNamedQuery("CaseManagement.deleteCasePortraitFile", params);
		
		// 刪除附件資料		
		caseAttachmentDao.executeByNamedQuery("CaseManagement.deleteCaseAttachment", params);
		
	}
	
	public CasePortraitFile createCasePortraitFile(CasePortraitFile casePortraitFile) {	
		casePortraitFile.setCreateTime(new Timestamp((new Date()).getTime()));
		return casePortraitFileDao.create(casePortraitFile);		
	}

	public CasePortraitFile updateCasePortraitFile(CasePortraitFile casePortraitFile) {	
		casePortraitFile.setUpdateTime(new Timestamp((new Date()).getTime()));
		return casePortraitFileDao.update(casePortraitFile);		
	}

	public CaseBasicData queryCaseBasicData(String dataUuid) {
		return caseBasicDataDao.query(new CaseBasicData(), dataUuid);
	}

	public CasePortraitFile queryCasePortraitFile(String dataUuid) {
		CasePortraitFile casePortraitFile = null;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dataUuid", dataUuid);
		
		@SuppressWarnings("unchecked")
		List<CasePortraitFile> casePortraitFileList = casePortraitFileDao.queryByNamedQuery("CaseManagement.queryCasePortraitFile", params);
		if (casePortraitFileList != null && casePortraitFileList.size() > 0) {
			casePortraitFile = casePortraitFileList.get(0);
		}
		
		return casePortraitFile;		
	}

	public CaseAttachment createCaseAttachment(CaseAttachment caseAttachment) {	
		caseAttachment.setCreateTime(new Timestamp((new Date()).getTime()));
		return caseAttachmentDao.create(caseAttachment);		
	}

	public CaseAttachment queryCaseAttachment(String attachmentUuid) {
		return caseAttachmentDao.query(new CaseAttachment(), attachmentUuid);		
	}
	
	public void deleteCaseAttachment(String attachmentUuid) throws NoDataFoundException {
		caseAttachmentDao.delete(new CaseAttachment(), attachmentUuid);		
	}

	public CaseAttachment updateCaseAttachment(CaseAttachment caseAttachment) {	
		caseAttachment.setUpdateTime(new Timestamp((new Date()).getTime()));
		return caseAttachmentDao.update(caseAttachment);		
	}
	
}
