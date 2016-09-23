package org.iii.see.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.iii.see.domain.Member;
import org.iii.see.enumeration.AccountStatusEnum;
import org.iii.see.enumeration.MemberAuditLogActionEnum;
import org.iii.see.service.AuthenticationService;
import org.iii.see.utility.SessionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/SignAgreement")
public class SignAgreementController extends GenericController {
	
	private final String PROCESS_URL = "/front/SignAgreement";
	
	@Autowired
	private AuthenticationService authenticationService;	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(HttpSession session) {
		
		ModelAndView mav = super.getPage(session, PROCESS_URL);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView signAgreement(@RequestParam(value="agreementVersion", required=true) String agreementVersion, 
			                          HttpSession session,
			                          HttpServletRequest request) {
		
		String account = SessionUtility.getUserAccount(session);
		if (account == null) {
			// 尚未登入
			return new ModelAndView(new RedirectView(request.getContextPath()));
		}
				
		Member member = authenticationService.queryMember(account);
		if (member == null || !StringUtils.equals(member.getStatus(), AccountStatusEnum.ACTIVE.getCode())) {
			// 使用者不存在或帳號非啟用狀態
			return new ModelAndView(new RedirectView(request.getContextPath()));			
		}

		authenticationService.signAgreement(member, agreementVersion);
		
		addAuditLog(member.getAccount(), MemberAuditLogActionEnum.SGNAGMT, agreementVersion, request);
		
		ModelAndView mav = new ModelAndView(new RedirectView(request.getContextPath() + "/Index"));
		
		return mav;
	}
	
}
