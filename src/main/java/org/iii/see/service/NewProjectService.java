package org.iii.see.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.iii.see.dao.ProjectDao;
import org.iii.see.dao.ProjectMemberDao;
import org.iii.see.dao.ProjectPhaseDao;
import org.iii.see.dao.ProjectPhaseToolDao;
import org.iii.see.dao.ProjectTemplateDao;
import org.iii.see.dao.ProjectTemplatePhaseDao;
import org.iii.see.dao.ProjectTemplatePhaseToolDao;
import org.iii.see.dao.ToolDao;
import org.iii.see.dao.wherecon.Criteria;
import org.iii.see.domain.Project;
import org.iii.see.domain.ProjectMember;
import org.iii.see.domain.ProjectMemberPK;
import org.iii.see.domain.ProjectPhase;
import org.iii.see.domain.ProjectPhaseTool;
import org.iii.see.domain.ProjectPhaseToolPK;
import org.iii.see.domain.ProjectTemplate;
import org.iii.see.domain.ProjectTemplatePhase;
import org.iii.see.domain.ProjectTemplatePhaseTool;
import org.iii.see.domain.ProjectTemplatePhaseToolPK;
import org.iii.see.domain.Tool;
import org.iii.see.enumeration.ProjectMemberRoleEnum;
import org.iii.see.enumeration.ProjectPhaseToolStatusEnum;
import org.iii.see.enumeration.ProjectTemplateStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NewProjectService {
	
	@Autowired
	private ProjectTemplateDao projectTemplateDao;

	@Autowired
	private ProjectTemplatePhaseDao projectTemplatePhaseDao;

	@Autowired
	private ProjectTemplatePhaseToolDao projectTemplatePhaseToolDao;

	@Autowired
	private ToolDao toolDao;

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private ProjectPhaseDao projectPhaseDao;

	@Autowired
	private ProjectPhaseToolDao projectPhaseToolDao;

	@Autowired
	private ProjectMemberDao projectMemberDao;
	
	public List<ProjectTemplate> queryProjectTemplateList() {
		
		Criteria criteria = new Criteria();
		criteria.eq("status", ProjectTemplateStatusEnum.ACTIVE.getCode());
		
		criteria.orderBy("name", true);
		
		return projectTemplateDao.query(new ProjectTemplate(), criteria);
	}
	
	public List<ProjectTemplatePhase> queryProjectTemplatePhaseList(String projectTemplateUuid) {
		Criteria criteria = new Criteria();
		criteria.eq("projectTemplateUuid", projectTemplateUuid);
		
		criteria.orderBy("serialNo", true);
		
		return projectTemplatePhaseDao.query(new ProjectTemplatePhase(), criteria);
		
	}	

	public List<ProjectTemplatePhaseTool> queryProjectTemplatePhaseToolList(String projectTemplatePhaseUuid) {
		Criteria criteria = new Criteria();
		criteria.eq("id.projectTemplatePhaseUuid", projectTemplatePhaseUuid);
				
		criteria.orderBy("serialNo", true);
		
		return projectTemplatePhaseToolDao.query(new ProjectTemplatePhaseTool(), criteria);
		
	}	

	public Tool queryTool(String toolUuid) {
		
		return toolDao.query(new Tool(), toolUuid);
		
	}	
	
	public Project createProject(Project project, List<ProjectTemplatePhase> projectTemplatePhaseList, List<ProjectTemplatePhaseToolPK> selectedToolList, String creator) {
	
		// TODO: 檢查專案建立是否已經超過上限
		
		Timestamp now = new Timestamp((new Date()).getTime());
		
		String projectUuid = String.valueOf(UUID.randomUUID());
		
		project.setUuid(projectUuid);		
		project.setCreator(creator);
		project.setCreateTime(now);
		projectDao.create(project);
		
		ProjectMemberPK projectMemberPK = new ProjectMemberPK();
		projectMemberPK.setProjectUuid(projectUuid);
		projectMemberPK.setMemberAccount(creator);

		ProjectMember projectMember = new ProjectMember();
		projectMember.setId(projectMemberPK);
		projectMember.setCreator(creator);
		projectMember.setCreateTime(now);
		projectMember.setRole(ProjectMemberRoleEnum.OWNER.getCode());
		
		projectMemberDao.create(projectMember);
		
		for (ProjectTemplatePhase projectTemplatePhase : projectTemplatePhaseList) {
			ProjectPhase projectPhase = new ProjectPhase();
			
			String projectPhaseUuid = String.valueOf(UUID.randomUUID());
			
			projectPhase.setUuid(projectPhaseUuid);
			projectPhase.setName(projectTemplatePhase.getName());
			projectPhase.setProjectUuid(projectUuid);
			projectPhase.setProjectTemplatePhaseUuid(projectTemplatePhase.getUuid());
			projectPhase.setSerialNo(projectTemplatePhase.getSerialNo());
			projectPhase.setCreator(creator);
			projectPhase.setCreateTime(now);
			
			projectPhaseDao.create(projectPhase);

			if (projectTemplatePhase.getProjectTemplatePhaseTools() != null) {
				for (ProjectTemplatePhaseTool projectTemplatePhaseTool : projectTemplatePhase.getProjectTemplatePhaseTools()) {
					
					ProjectPhaseToolPK projectPhaseToolPK = new ProjectPhaseToolPK();					
					projectPhaseToolPK.setProjectPhaseUuid(projectPhaseUuid);
					projectPhaseToolPK.setToolUuid(projectTemplatePhaseTool.getId().getToolUuid());
					
					ProjectPhaseTool projectPhaseTool = new ProjectPhaseTool();
					projectPhaseTool.setId(projectPhaseToolPK);
					projectPhaseTool.setSerialNo(projectTemplatePhaseTool.getSerialNo());
										
					if (selectedToolList.contains(projectTemplatePhaseTool.getId())) {
						projectPhaseTool.setStatus(ProjectPhaseToolStatusEnum.ACTIVE.getCode());						
					} else {
						projectPhaseTool.setStatus(ProjectPhaseToolStatusEnum.INACTIVE.getCode());						
					}
					
					projectPhaseToolDao.create(projectPhaseTool);
				}
			}
		}				
				
		return project;
	}
	
}
