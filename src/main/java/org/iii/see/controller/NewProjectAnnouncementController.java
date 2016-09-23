package org.iii.see.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.iii.see.domain.ProjectAnnouncement;
import org.iii.see.enumeration.AnnouncementStatusEnum;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.form.AnnouncementDataFormBean;
import org.iii.see.service.AnnouncementManagementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/NewProjectAnnouncement")
public class NewProjectAnnouncementController extends BaseController {
	
	private String FUNCTION_URL = "/front/NewProjectAnnouncement";
	
	@Autowired
	private AnnouncementManagementService announcementManagementService;		

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam(value="projectUuid", required=true) String projectUuid,
			                    HttpSession session) {
		
		return super.getPage(session, FUNCTION_URL, projectUuid);
	}	

    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    public @ResponseBody
    void doSave(AnnouncementDataFormBean formBean,
    		    HttpSession session, 
    		    HttpServletResponse response) throws IOException {
    	
    	JSONObject jsonObject = new JSONObject();
    	
		// TODO: 檢查是否已經登入系統
    	
    	// 檢查欄位值    	
    	if (StringUtils.isBlank(formBean.getProjectUuid()) || 
    			StringUtils.isBlank(formBean.getTitle()) || 
    			StringUtils.isBlank(formBean.getSummary()) ||
    			StringUtils.isBlank(formBean.getContent())) {
    		
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DATA_INCORRECT.getDesc());
	    	
			response.setContentType(JKEY_CONTENT_TYPE);
			response.getWriter().write(jsonObject.toString());      	
			return;
    	}
    	
		ProjectAnnouncement announcement = new ProjectAnnouncement();
		BeanUtils.copyProperties(formBean, announcement);	
		announcement.setStatus(AnnouncementStatusEnum.ACTIVE.getCode());
		
    	if (StringUtils.equals(FUNCTION_NAME_INSERT, formBean.getFunctionName())) {
    		// 新增資料
//    		personaBasicData.setCreator(SessionUtils.getUserUuid(session));
    		
    		try {
    			announcement = announcementManagementService.createProjectAnnouncement(announcement);
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
				jsonObject.put(JKEY_UUID, announcement.getUuid());	 
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
