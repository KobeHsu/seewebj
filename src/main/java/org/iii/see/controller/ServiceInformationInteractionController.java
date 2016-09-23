package org.iii.see.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ServiceInformationInteraction")
public class ServiceInformationInteractionController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(HttpSession session) {
		ModelAndView mav = new ModelAndView("/front/ServiceInformationInteraction");		
		return mav;
	}	
}
