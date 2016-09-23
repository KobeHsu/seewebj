package org.iii.see.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.iii.see.dao.PersonaBasicDataDao;
import org.iii.see.dao.PersonaPortraitFileDao;
import org.iii.see.domain.PersonaBasicData;
import org.iii.see.domain.PersonaPortraitFile;
import org.iii.see.exception.DuplicatedDataException;
import org.iii.see.exception.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonaManagementService {
	
	@Autowired
	private PersonaBasicDataDao personaBasicDataDao;

	@Autowired
	private PersonaPortraitFileDao personaPortraitFileDao;
	
	public List<Object[]> queryPersonaList(String projectUuid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectUuid", projectUuid);
		
		// 查詢結果欄位順序
	    // 0: uuid
	    // 1: projectUuid
	    // 2: personaNo
	    // 3: realname
	    // 4: category
	    // 5: background
	    // 6: behavior
	    // 7: target
	    // 8: createTime				
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = (List<Object[]>)personaBasicDataDao.queryByNamedQuery("PersonaManagement.queryPersonaList", params); 

		return resultSet;
	}
	
	public PersonaBasicData createPersona(PersonaBasicData personaBasicData) {

		// TODO: 檢查個案編號是否已經存在
		
		UUID uuid = UUID.randomUUID();
		personaBasicData.setUuid(String.valueOf(uuid));
		personaBasicData.setCreateTime(new Timestamp((new Date()).getTime()));
		 
		PersonaBasicData createdPersonaBasicData = personaBasicDataDao.create(personaBasicData);
		return createdPersonaBasicData;
	}

	public PersonaPortraitFile createPersonaPortraitFile(PersonaPortraitFile personaPortraitFile) {	
		personaPortraitFile.setCreateTime(new Timestamp((new Date()).getTime()));
		return personaPortraitFileDao.create(personaPortraitFile);		
	}
	
	public PersonaBasicData queryPersonaBasicData(String dataUuid) {
		return personaBasicDataDao.query(new PersonaBasicData(), dataUuid);
	}

	public void deletePersona(String dataUuid) throws NoDataFoundException {
		PersonaBasicData oPersonaBasicData = personaBasicDataDao.query(new PersonaBasicData(), dataUuid);
		if (oPersonaBasicData == null) {
			throw new NoDataFoundException();
		}

		// 刪除基本資料
		personaBasicDataDao.delete(new PersonaBasicData(), dataUuid);
		
		// 刪除圖檔資料
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dataUuid", dataUuid);
		
		personaBasicDataDao.executeByNamedQuery("PersonaManagement.deletePersonaPortraitFile", params);
		
	}
	
	public Object[] queryPersona(String projectUuid, String dataUuid) {
		
		Object[] resultSet = new Object[4];
		
		PersonaBasicData personaBasicData = personaBasicDataDao.query(new PersonaBasicData(), dataUuid);
		if (personaBasicData == null) {
			return null;
		}
		
		resultSet[0] = personaBasicData;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dataUuid", dataUuid);

		@SuppressWarnings("unchecked")
		List<PersonaPortraitFile> personaPortraitFileList = personaPortraitFileDao.queryByNamedQuery("PersonaManagement.queryPersonaPortraitFile", params);
		if (personaPortraitFileList != null && personaPortraitFileList.size() > 0) {
			resultSet[1] = personaPortraitFileList.get(0);
		}

		return resultSet;
	}

	public PersonaBasicData updatePersona(PersonaBasicData personaBasicData) throws NoDataFoundException, DuplicatedDataException {

		PersonaBasicData oPersonaBasicData = personaBasicDataDao.query(new PersonaBasicData(), personaBasicData.getUuid());
		if (oPersonaBasicData == null) {
			throw new NoDataFoundException();
		}

		// 若人物角色原型編號有修改, 檢查新人物角色原型編號是否已經存在
		if (!StringUtils.equals(oPersonaBasicData.getPersonaNo(), personaBasicData.getPersonaNo()) && 
				isPersonaNoDuplicated(personaBasicData.getProjectUuid(), personaBasicData.getPersonaNo())) {
			throw new DuplicatedDataException();
		}		
		
		// 自行複製屬性, 避免蓋到不可複製的屬性
		oPersonaBasicData.setPersonaNo(personaBasicData.getPersonaNo());
		oPersonaBasicData.setRealname(personaBasicData.getRealname());
		oPersonaBasicData.setCategory(personaBasicData.getCategory());
		oPersonaBasicData.setBackground(personaBasicData.getBackground());
		oPersonaBasicData.setBehavior(personaBasicData.getBehavior());
		oPersonaBasicData.setTarget(personaBasicData.getTarget());
		oPersonaBasicData.setUpdator(personaBasicData.getUpdator());
		oPersonaBasicData.setUpdateTime(new Timestamp((new Date()).getTime()));

		oPersonaBasicData = personaBasicDataDao.update(oPersonaBasicData);		
		
		return oPersonaBasicData;
	}

	public boolean isPersonaNoDuplicated(String projectUuid, String caseNo) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectUuid", projectUuid);
		params.put("caseNo", caseNo);
		
		List countSet = personaBasicDataDao.queryByNamedQuery("PersonaManagement.checkDuplicatePersonaNo", params);
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

	public PersonaPortraitFile queryPersonaPortraitFile(String dataUuid) {
		PersonaPortraitFile personaPortraitFile = null;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dataUuid", dataUuid);
		
		@SuppressWarnings("unchecked")
		List<PersonaPortraitFile> personaPortraitFileList = personaPortraitFileDao.queryByNamedQuery("PersonaManagement.queryPersonaPortraitFile", params);
		if (personaPortraitFileList != null && personaPortraitFileList.size() > 0) {
			personaPortraitFile = personaPortraitFileList.get(0);
		}
		
		return personaPortraitFile;		
	}
	
	public PersonaPortraitFile updatePersonaPortraitFile(PersonaPortraitFile personaPortraitFile) {	
		personaPortraitFile.setUpdateTime(new Timestamp((new Date()).getTime()));
		return personaPortraitFileDao.update(personaPortraitFile);		
	}
	
}
