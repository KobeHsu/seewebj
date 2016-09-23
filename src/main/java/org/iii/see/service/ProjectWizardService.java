package org.iii.see.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.iii.see.dao.ProjectPhaseDao;
import org.iii.see.dao.ProjectPhaseToolDao;
import org.iii.see.domain.ProjectPhase;
import org.iii.see.domain.ProjectPhaseTool;
import org.iii.see.domain.Tool;
import org.iii.see.excecption.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectWizardService {
	
	@Autowired
	private ProjectPhaseDao projectPhaseDao;
	
	@Autowired
	private ProjectPhaseToolDao projectPhaseToolDao;
	
	public ProjectPhase createProjectPhase(ProjectPhase projectPhase) {
		
		UUID uuid = UUID.randomUUID();
		projectPhase.setUuid(String.valueOf(uuid));
		projectPhase.setCreateTime(new Timestamp((new Date()).getTime()));
		
		return projectPhaseDao.create(projectPhase);
	}	

	public ProjectPhase createProjectPhase(ProjectPhase projectPhase, String[] toolUuids) {
		
		Timestamp now = new Timestamp((new Date()).getTime());
		
		UUID uuid = UUID.randomUUID();
		projectPhase.setUuid(String.valueOf(uuid));
		projectPhase.setCreateTime(now);
		
		if (toolUuids != null) {
			for (String toolUuid : toolUuids) {
				
				ProjectPhaseTool projectPhaseTool = new ProjectPhaseTool(); 
				
				projectPhaseTool.setUuid(String.valueOf(UUID.randomUUID()));
				projectPhaseTool.setProjectUuid(projectPhase.getProjectUuid());
				projectPhaseTool.setPhaseUuid(projectPhase.getUuid());
				projectPhaseTool.setToolUuid(toolUuid);
				
				projectPhaseTool.setCreateTime(now);
				
				projectPhaseToolDao.create(projectPhaseTool);
			}
		}
		
		return projectPhaseDao.create(projectPhase);
	}	
	
	public ProjectPhase updateProjectPhase(ProjectPhase projectPhase, String[] toolUuids) throws NoDataFoundException {
		
		Timestamp now = new Timestamp((new Date()).getTime());
		
		ProjectPhase oProjectPhase = projectPhaseDao.query(new ProjectPhase(), projectPhase.getUuid());
		if (oProjectPhase == null) {
			throw new NoDataFoundException();
		}
		// 搬部分欄位至原資料, 避免將未提供修改欄位清空
		oProjectPhase.setName(projectPhase.getName());
		oProjectPhase.setSerialNo(projectPhase.getSerialNo());
		oProjectPhase.setUpdateTime(now);
		
		// 刪除
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("phaseUuid", oProjectPhase.getUuid());		
		projectPhaseToolDao.executeByNamedQuery("ProjectWizard.deleteProjectPhaseTool", params); 						
		
		
		if (toolUuids != null) {
			for (String toolUuid : toolUuids) {
				
				ProjectPhaseTool projectPhaseTool = new ProjectPhaseTool(); 
				
				projectPhaseTool.setUuid(String.valueOf(UUID.randomUUID()));
				projectPhaseTool.setProjectUuid(projectPhase.getProjectUuid());
				projectPhaseTool.setPhaseUuid(projectPhase.getUuid());
				projectPhaseTool.setToolUuid(toolUuid);
				
				projectPhaseTool.setCreateTime(now);
				
				projectPhaseToolDao.create(projectPhaseTool);
			}
		}		
		
		return projectPhaseDao.update(oProjectPhase);
	}	

	public ProjectPhase updateProjectPhase(ProjectPhase projectPhase) throws NoDataFoundException {
		
		ProjectPhase oProjectPhase = projectPhaseDao.query(new ProjectPhase(), projectPhase.getUuid());
		if (oProjectPhase == null) {
			throw new NoDataFoundException();
		}
		// 搬部分欄位至原資料, 避免將未提供修改欄位清空
		oProjectPhase.setName(projectPhase.getName());
		oProjectPhase.setSerialNo(projectPhase.getSerialNo());
		oProjectPhase.setUpdateTime(new Timestamp((new Date()).getTime()));
		
		return projectPhaseDao.update(oProjectPhase);
	}	
	
	public void deleteProjectPhase(String uuid) throws NoDataFoundException {
		
		ProjectPhase oProjectPhase = projectPhaseDao.query(new ProjectPhase(), uuid);
		if (oProjectPhase == null) {
			throw new NoDataFoundException();
		}
		projectPhaseDao.delete(new ProjectPhase(), uuid);
	}	
	
	public List<ProjectPhase> queryProjectPhaseList(String projectUuid) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectUuid", projectUuid);
		
		@SuppressWarnings("unchecked")
		List<ProjectPhase> projectPhaseList = projectPhaseDao.queryByNamedQuery("ProjectWizard.queryProjectPhaseList", params);
		
		return projectPhaseList;
	}		

	public List<Tool> queryToolList() {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		@SuppressWarnings("unchecked")
		List<Tool> toolList = projectPhaseDao.queryByNamedQuery("ProjectWizard.queryToolList", params);
		
		return toolList;
	}		

	public List<String> queryPhaseToolUuidList(String phaseUuid) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("phaseUuid", phaseUuid);
		
		@SuppressWarnings("unchecked")
		List<String> toolUuidList = projectPhaseDao.queryByNamedQuery("ProjectWizard.queryPhaseToolUuidList", params);
		
		return toolUuidList;
	}		

	public List<Object[]> queryProjectToolUuidList(String projectUuid) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectUuid", projectUuid);
		
		@SuppressWarnings("unchecked")
		List<Object[]> toolList = projectPhaseDao.queryByNamedQuery("ProjectWizard.queryProjectToolUuidList", params);
		
		return toolList;
	}		
	
}
