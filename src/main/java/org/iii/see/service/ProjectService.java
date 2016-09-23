package org.iii.see.service;

import java.util.ArrayList;
import java.util.List;

import org.iii.see.dao.ProjectDao;
import org.iii.see.dao.ProjectMemberDao;
import org.iii.see.dao.ProjectPhaseDao;
import org.iii.see.dao.wherecon.Criteria;
import org.iii.see.domain.Project;
import org.iii.see.domain.ProjectMember;
import org.iii.see.domain.ProjectPhase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectService {

	@Autowired
	private ProjectMemberDao projectMemberDao;
	
	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private ProjectPhaseDao projectPhaseDao;
	
	public List<Project> queryProjectListForMember(String memberAccount) {
		
		List<Project> projectListForMember =  new ArrayList<Project>();
		
		Criteria criteria = new Criteria();
		criteria.eq("id.memberAccount", memberAccount);
				
		criteria.orderBy("createTime", false);
		
		List<ProjectMember> projectMemberList = projectMemberDao.query(new ProjectMember(), criteria);
		for (ProjectMember projectMember : projectMemberList) {
			if (projectMember.getProject() != null) {
				projectListForMember.add(projectMember.getProject());				
			}
		}
		
		return projectListForMember;
	}
	
	public List<ProjectPhase> queryProjectPhaseList(String projectUuid) {
		Criteria criteria = new Criteria();
		criteria.eq("projectUuid", projectUuid);
		
		criteria.orderBy("serialNo", true);
		
		return projectPhaseDao.query(new ProjectPhase(), criteria);
		
	}	
	
	
}
