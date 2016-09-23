package org.iii.see.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.iii.see.domain.Project;
import org.iii.see.domain.ProjectPhase;
import org.iii.see.service.ProjectService;
import org.iii.see.session.ProjectList;
import org.iii.see.utility.SessionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Index")
public class IndexController extends GenericController {
	
	private final String PROCESS_URL = "/front/Index";
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam(value="projectUuid", required=false) String projectUuid, HttpSession session) {

		ModelAndView mav = super.getPage(session, PROCESS_URL);
		if (StringUtils.equals(PROCESS_URL, mav.getViewName())) {
			
			// 取得專案清單		
			String account = SessionUtility.getUserAccount(session);
			
			ProjectList projectList = (session.getAttribute("projectList") != null) ? (ProjectList)session.getAttribute("projectList") : new ProjectList();
			
			projectList.clear();
			
			String activeProjectUuid = StringUtils.EMPTY;
			String activeProjectName = StringUtils.EMPTY;
			
			List<Project> projects = projectService.queryProjectListForMember(account);
			for (Project project : projects) {				
				projectList.add(project.getUuid(), project.getName());	
				
				if (StringUtils.isEmpty(activeProjectUuid)) {
					if (StringUtils.isEmpty(projectUuid)) {
						activeProjectUuid = project.getUuid();
						activeProjectName = project.getName();											
					} else {
						if (StringUtils.equals(projectUuid, project.getUuid())) {
							activeProjectUuid = project.getUuid();
							activeProjectName = project.getName();											
						}
					}
				}
			}
			
			if (session.getAttribute("projectList") == null) {
				session.setAttribute("projectList", projectList);				
			}
			
			session.setAttribute("activeProjectUuid", activeProjectUuid);
			session.setAttribute("activeProjectName", activeProjectName);
			
			// 查出專案的階段資料			
			List<ProjectPhase> projectPhaseList = projectService.queryProjectPhaseList(activeProjectUuid);
			mav.addObject("projectPhases", projectPhaseList);
			
		}
		
		
						
		return mav;
	}	

}
