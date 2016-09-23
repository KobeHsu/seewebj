package org.iii.see.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.iii.see.domain.UsibilityTestFormTemplate;
import org.iii.see.service.UsibilityTestFormManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ViewUsibilityTestFormTemplate")
public class ViewUsibilityTestFormTemplateController {

	@Autowired
	private UsibilityTestFormManagementService usibilityTestFormManagementService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam(value="projectUuid", required=true) String projectUuid,
			                    HttpSession session) {
		
		// TODO: 檢查是否已經登入系統, 是否為專案成員
		
		ModelAndView mav = new ModelAndView("/front/ViewUsibilityTestFormTemplate");
				
		mav.addObject("projectUuid", projectUuid);
		
		List<UsibilityTestFormTemplate> usibilityTestFormTemplateList = usibilityTestFormManagementService.queryUsibilityTestFormTemplateList(projectUuid);
		mav.addObject("usibilityTestFormTemplateList", usibilityTestFormTemplateList);
		
		return mav;
	}
	
}
