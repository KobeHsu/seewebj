package org.iii.see.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.iii.see.domain.ProjectPhase;
import org.iii.see.excecption.NoDataFoundException;
import org.iii.see.service.AccountManagementService;
import org.iii.see.service.ProjectWizardService;
import org.iii.see.utility.SessionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ActivateAccount")
public class ActivateAccountController extends BaseController {
	
	@Autowired
	private AccountManagementService accountManagementService;
	
	@Autowired
	private ProjectWizardService projectWizardService;	

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(HttpSession session) {
		ModelAndView mav = new ModelAndView("/front/ActivateAccount");
		
		String sid = session.getId();
		mav.addObject("sid", sid);		
		
		// 檢查是否已經登入系統
//		if (!SessionUtils.isLogon(session)) {
//			mav = new ModelAndView("/front/Login");
//		}		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView activateAccount(HttpSession session,
			                            @RequestParam(value="sid", required=true) String sid,
			                            @RequestParam(value="sign", required=true) String sign,
			                            @RequestParam(value="agreementVersion", required=true) String agreementVersion)
			throws org.iii.see.exception.NoDataFoundException {
		
		ModelAndView mav = new ModelAndView("/front/ActivateAccount");

		if (!StringUtils.equals(sid, session.getId())) {
			mav.addObject("sid", session.getId());
			return mav;
		}
		
		if (StringUtils.equals("Y", sign)) {
			String userUuid = SessionUtility.getUserUuid(session);
			if (userUuid != null) {
                accountManagementService.activateAccount(userUuid);
                mav = new ModelAndView("/front/Index");

                List<ProjectPhase> projectPhaseList =
                        projectWizardService.queryProjectPhaseList("1");
                mav.addObject("projectPhaseList", projectPhaseList);

                List<Object[]> projectPhaseToolList =
                        projectWizardService.queryProjectToolUuidList("1");
                mav.addObject("projectPhaseToolList", projectPhaseToolList);

            }
		} else {
			return mav;
		}		
		
		return mav;		
	}
	
}
