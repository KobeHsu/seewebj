package org.iii.see.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.iii.see.domain.Project;
import org.iii.see.domain.ProjectTemplate;
import org.iii.see.domain.ProjectTemplatePhase;
import org.iii.see.domain.ProjectTemplatePhaseToolPK;
import org.iii.see.enumeration.MemberOwnPrjoectAuthorityEnum;
import org.iii.see.enumeration.ProjectStatusEnum;
import org.iii.see.form.NewProjectFormBean;
import org.iii.see.service.NewProjectService;
import org.iii.see.utility.SessionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/NewProject")
public class NewProjectController extends GenericController {
	
	private final String PROCESS_STEP1_URL = "/front/NewProjectStep1";
	private final String PROCESS_STEP2_URL = "/front/NewProjectStep2";
	private final String PROCESS_STEP3_URL = "/front/NewProjectStep3";
	
	@Autowired
	private NewProjectService newProjectService;	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(HttpSession session) {
		
		ModelAndView mav = super.getPage(session, PROCESS_STEP1_URL);
		
		// 進一步檢查是否允許建立專案
		if (StringUtils.equals(mav.getViewName(), PROCESS_STEP1_URL)) {
			if (!StringUtils.equals(SessionUtility.getUser(session).getOwnPrjoectAuthority(), MemberOwnPrjoectAuthorityEnum.Y.getCode())) {
				mav = new ModelAndView(URL_LOGIN);
			}
			
			mav.addObject("projectData", new NewProjectFormBean());
		}
			
		return mav;
	}
	
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView goStep(NewProjectFormBean formBean,
    		                   HttpSession session, 
    		                   HttpServletRequest request) throws IOException {
    	
		String account = SessionUtility.getUserAccount(session);
		if (account == null) {
			// 尚未登入
			return new ModelAndView(new RedirectView(request.getContextPath()));
		}
    	
		ModelAndView mav = null;
		if (StringUtils.equals(formBean.getStep(), "1")) {
			mav = generateMavStep2(formBean);
		
		} else if (StringUtils.equals(formBean.getStep(), "2")) {
			if (StringUtils.equals(formBean.getNext(), "Y")) {
				// 下一步
				mav = generateMavStep3(formBean); 				
			} else {
				// 上一步
				mav = generateMavStep1(formBean);
			}			
		} else if (StringUtils.equals(formBean.getStep(), "3")) {
			if (StringUtils.equals(formBean.getNext(), "Y")) {
				// 完成
				mav = createProject(formBean, session, request);								
			} else {
				// 上一步
				mav = generateMavStep2(formBean);
			}			
		}
		
		mav.addObject("projectData", formBean);
		
    	return mav;
    }
    
    private ModelAndView generateMavStep1(NewProjectFormBean formBean) {
    	return new ModelAndView(PROCESS_STEP1_URL);
    }

    private ModelAndView generateMavStep2(NewProjectFormBean formBean) {    	
    	ModelAndView mav = new ModelAndView(PROCESS_STEP2_URL);
		// 取得專案範本清單
		List<ProjectTemplate> projectTemplateList = newProjectService.queryProjectTemplateList();
		mav.addObject("projectTemplates", projectTemplateList);  
		return mav;
    }

    private ModelAndView generateMavStep3(NewProjectFormBean formBean) {    	

    	ModelAndView mav = new ModelAndView(PROCESS_STEP3_URL);
		
		// 查出專案範本的階段資料
		List<ProjectTemplatePhase> projectTemplatePhaseList = newProjectService.queryProjectTemplatePhaseList(formBean.getProjectTemplateUuid());
		mav.addObject("projectTemplatePhases", projectTemplatePhaseList);
				
		return mav;
    }

    private ModelAndView createProject(NewProjectFormBean formBean, HttpSession session, HttpServletRequest request) {    	
		
		// 查出專案範本的階段資料
    	Project project = new Project();
    	
    	project.setName(formBean.getName());
    	project.setDescription(formBean.getDescription());
    	project.setProjectTemplateUuid(formBean.getProjectTemplateUuid());
    	project.setStatus(ProjectStatusEnum.ACTIVE.getCode());
    	
		// 查出專案範本的階段資料
		List<ProjectTemplatePhase> projectTemplatePhaseList = newProjectService.queryProjectTemplatePhaseList(formBean.getProjectTemplateUuid());

		// 取得預設選取的工具
		List<ProjectTemplatePhaseToolPK> selectedToolList = new ArrayList<ProjectTemplatePhaseToolPK>();
		if (formBean.getProjectTemplatePhaseTool() != null) {
			for (String projectTemplatePhaseToolStr : formBean.getProjectTemplatePhaseTool()) {
				String[] values = projectTemplatePhaseToolStr.split(":");
				
				if (values != null && values.length == 2) {
					ProjectTemplatePhaseToolPK selectedTool = new ProjectTemplatePhaseToolPK();
					selectedTool.setProjectTemplatePhaseUuid(values[0]);
					selectedTool.setToolUuid(values[1]);

					selectedToolList.add(selectedTool);
				}
			}
		}
				
		project = newProjectService.createProject(project, projectTemplatePhaseList, selectedToolList, SessionUtility.getUserAccount(session));
		
    	// 新增成功, 回到起始頁
    	ModelAndView mav = new ModelAndView(new RedirectView(request.getContextPath() + "/Index?projectUuid=" + project.getUuid()));
		
		return mav;
    }
    
}
