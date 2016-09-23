package org.iii.see.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.iii.see.domain.Forum;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.form.ForumDataFormBean;
import org.iii.see.service.ForumManagementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/NewForum")
public class NewForumController extends BaseController {
	
	@Autowired
	private ForumManagementService forumManagementService;	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam(value="projectUuid", required=true) String projectUuid,
			                    HttpSession session) {
		ModelAndView mav = new ModelAndView("/front/NewForum");
		
		// TODO: 檢查是否已經登入系統, 是否為專案成員
		
		mav.addObject("projectUuid", projectUuid);
				
		return mav;
	}	
	
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    public @ResponseBody
    void doSave(ForumDataFormBean formBean,
    		    HttpSession session, 
    		    HttpServletResponse response) throws IOException {
    	
    	JSONObject jsonObject = new JSONObject();
    	
		// TODO: 檢查是否已經登入系統
    	
    	// 檢查欄位值    	
    	if (StringUtils.isBlank(formBean.getProjectUuid()) || 
    			StringUtils.isBlank(formBean.getTitle()) || 
    			StringUtils.isBlank(formBean.getSummary()) ||
    			StringUtils.isBlank(formBean.getStatus())) {
    		
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DATA_INCORRECT.getDesc());
	    	
			response.setContentType(JKEY_CONTENT_TYPE);
			response.getWriter().write(jsonObject.toString());      	
			return;
    	}
    	
		Forum forum = new Forum();
		BeanUtils.copyProperties(formBean, forum);						
    	if (StringUtils.equals(FUNCTION_NAME_INSERT, formBean.getFunctionName())) {
    		// 新增資料
//    		personaBasicData.setCreator(SessionUtils.getUserUuid(session));
    		
    		try {
    			forum = forumManagementService.createForum(forum);
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
				jsonObject.put(JKEY_UUID, forum.getUuid());	 
//			} catch (DuplicatedDataException e) {
//				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
//				jsonObject.put(JKEY_ERROR_MESSAGE, "個案編號已經存在");				
			} catch (Exception e) {
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
