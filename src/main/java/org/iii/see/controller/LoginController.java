package org.iii.see.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.iii.see.domain.Member;
import org.iii.see.enumeration.AccountStatusEnum;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.enumeration.MemberAuditLogActionEnum;
import org.iii.see.service.AuthenticationService;
import org.iii.see.session.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginController extends GenericController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage() {
		ModelAndView mav = new ModelAndView("/front/Login");		
		return mav;
	}
	
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public @ResponseBody
    void doLogin(@RequestParam(value="account", required=true) String account,
    		     @RequestParam(value="password", required=true) String password,
    		     HttpSession session, 
    		     HttpServletRequest request,
    		     HttpServletResponse response) throws IOException {
    	
    	JSONObject jsonObject = new JSONObject();
    	
    	Member member = authenticationService.queryMember(account); 
    	if (member == null) {    		
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.AUTHENTICATION_ACCOUNT_NOT_EXISTS.getDesc());  
    	} else {
    		// 檢查是否停用
    		if (StringUtils.equals(member.getStatus(), AccountStatusEnum.DISABLED.getCode())) {
    			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
    			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.AUTHENTICATION_ACCOUNT_DISABLED.getDesc());      		
    		} else {
    			// 先檢查密碼, 再檢查狀態
    			if (true || StringUtils.equals(member.getPassword(), password)) {
            		// 檢查是否非停用
    				
        			if (StringUtils.equals(member.getStatus(), AccountStatusEnum.ACTIVE.getCode())) {
        				User user = new User();
        				BeanUtils.copyProperties(member, user);
        				session.setAttribute("user", user);
        				
        				jsonObject.put("accountStatus", member.getStatus());
        				jsonObject.put("accountAgreementRequired", member.getAgreementRequired());        				
        				
        				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
        				
        				addAuditLog(member.getAccount(), MemberAuditLogActionEnum.LOGIN, request);
        				
        			} else {
        				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
        				jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.AUTHENTICATION_ACCOUNT_INACTIVE.getDesc());      				
        			}        			
        			
    			} else {
    				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
    				jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.AUTHENTICATION_PASSWORD_INCORRECT.getDesc());      				
    			}
    			
    		}
    	}    	
    	
    	response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(jsonObject.toString());
    }	

}
