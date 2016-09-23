package org.iii.see.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/NewOnlineIssueSuccess")
public class NewOnlineIssueSuccessController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam(value="projectUuid", required=true) String projectUuid,
			                    HttpSession session) {
		ModelAndView mav = new ModelAndView("/front/NewOnlineIssueSuccess");
		
		// TODO: 檢查是否已經登入系統, 是否為專案成員		
		mav.addObject("projectUuid", projectUuid);
				
		return mav;
	}	
	
}
