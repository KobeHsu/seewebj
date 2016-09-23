package org.iii.see.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.iii.see.enumeration.MemberAuditLogActionEnum;
import org.iii.see.utility.SessionUtility;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/Logout")
public class LogoutController extends GenericController {

    @RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(HttpSession session, HttpServletRequest request) {
    	
    	addAuditLog(SessionUtility.getUserAccount(session), MemberAuditLogActionEnum.LOGOUT, request);

    	SessionUtility.clearSession(session);
    	
    	ModelAndView mav = new ModelAndView("/front/Login");
    	    	    	
    	return mav;
	}
	
}
