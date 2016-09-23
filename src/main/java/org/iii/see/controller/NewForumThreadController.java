package org.iii.see.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.iii.see.domain.ForumThread;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.enumeration.ForumThreadStatusEnum;
import org.iii.see.form.ForumThreadDataFormBean;
import org.iii.see.service.ForumManagementService;
import org.iii.see.utility.SessionUtility;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/NewForumThread")
public class NewForumThreadController extends BaseController {

	@Autowired
	private ForumManagementService forumManagementService;	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam(value="projectUuid", required=true) String projectUuid,
			                    @RequestParam(value="forumUuid", required=true) String forumUuid,
			                    HttpSession session) {
		ModelAndView mav = new ModelAndView("/front/NewForumThread");
		
		// TODO: 檢查是否已經登入系統, 是否為專案成員
		
		mav.addObject("projectUuid", projectUuid);
		mav.addObject("forumUuid", forumUuid);				
				
		return mav;
	}	
	
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    public @ResponseBody
    void doSave(ForumThreadDataFormBean formBean,
    		    HttpSession session, 
    		    HttpServletResponse response) throws IOException {
    	
    	JSONObject jsonObject = new JSONObject();
    	
		// TODO: 檢查是否已經登入系統
    	
    	// 檢查欄位值    	
    	if (StringUtils.isBlank(formBean.getForumUuid()) || 
    			StringUtils.isBlank(formBean.getTitle()) || 
    			StringUtils.isBlank(formBean.getContent())) {
    		
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DATA_INCORRECT.getDesc());
	    	
			response.setContentType(JKEY_CONTENT_TYPE);
			response.getWriter().write(jsonObject.toString());      	
			return;
    	}
    	
		ForumThread forumThread = new ForumThread();
		BeanUtils.copyProperties(formBean, forumThread);
		
    	if (StringUtils.equals(FUNCTION_NAME_INSERT, formBean.getFunctionName())) {
    		// 新增資料
    		forumThread.setAuthorUuid(SessionUtility.getUserAccount(session));
    		forumThread.setAuthorNickname(SessionUtility.getUserNickname(session));
    		forumThread.setStatus(ForumThreadStatusEnum.ACTIVE.getCode());
    		
    		try {
    			forumThread = forumManagementService.createForumThread(forumThread);
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
				jsonObject.put(JKEY_UUID, forumThread.getUuid());	 
			} catch (Exception e) {
				e.printStackTrace();
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
				jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_INSERT_FAILED.getDesc());				
			}
    	} else {
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_UNKNOWN_FUNCTION.getDesc());				
    	}
    	
    	response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(jsonObject.toString());      	
    }	 
	
}
