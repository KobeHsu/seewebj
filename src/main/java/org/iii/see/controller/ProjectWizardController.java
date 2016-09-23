package org.iii.see.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.iii.see.domain.ProjectPhase;
import org.iii.see.domain.Tool;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.excecption.NoDataFoundException;
import org.iii.see.form.DatatableReturnBean;
import org.iii.see.form.ProjectWizardFormBean;
import org.iii.see.service.ProjectWizardService;
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
@RequestMapping("/ProjectWizard")
public class ProjectWizardController extends BaseController {

	@Autowired
	private ProjectWizardService projectWizardService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(HttpSession session) {
		ModelAndView mav = new ModelAndView("/front/ProjectWizard");
		
		// 檢查是否已經登入系統
//		if (!SessionUtils.isLogon(session)) {
//			mav = new ModelAndView("/front/Login");
//		}		
		return mav;
	}
	
    @RequestMapping(value = "/doQuery", method = RequestMethod.POST)
    public @ResponseBody
    void doQuery(@RequestParam(value="projectUuid", required=true) String projectUuid, 
    		     @RequestParam(value="draw", required=true) int draw,
    		     @RequestParam(value="start", required=true) int start,
    		     @RequestParam(value="length", required=true) int length,
		         HttpSession session, HttpServletResponse response) throws IOException {
    	
    	DatatableReturnBean datatableReturnBean = new DatatableReturnBean();
    	datatableReturnBean.setDraw(draw);
    	
    	List<ProjectPhase> projectPhaseList = projectWizardService.queryProjectPhaseList(projectUuid);
    	for (ProjectPhase projectPhase : projectPhaseList) {
			Map<String, Object> record = new HashMap<String, Object>();
			record.put("uuid", projectPhase.getUuid());
			record.put("name", projectPhase.getName());
			record.put("serialNo", projectPhase.getSerialNo());
			
			datatableReturnBean.addRecord(record);    		
    	}
    	
    	datatableReturnBean.setRecordsTotal(projectPhaseList.size());
    	datatableReturnBean.setRecordsFiltered(projectPhaseList.size());
    	
    	response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(datatableReturnBean.toString());     	
    }    

    @RequestMapping(value = "/doQueryTool", method = RequestMethod.POST)
    public @ResponseBody
    void doQueryTool(@RequestParam(value="phaseUuid", required=false) String phaseUuid, 
    		         @RequestParam(value="draw", required=true) int draw,
    		         @RequestParam(value="start", required=true) int start,
    		         @RequestParam(value="length", required=true) int length,
		             HttpSession session, HttpServletResponse response) throws IOException {
    	
    	DatatableReturnBean datatableReturnBean = new DatatableReturnBean();
    	datatableReturnBean.setDraw(draw);
    	
    	List<Tool> toolList = projectWizardService.queryToolList();
    	
    	List<String> phaseToolUuidList = null;
    	
    	if (StringUtils.isNotEmpty(phaseUuid)) {
    		phaseToolUuidList = projectWizardService.queryPhaseToolUuidList(phaseUuid);
    	} 
    	
    	for (Tool tool : toolList) {
			Map<String, Object> record = new HashMap<String, Object>();			
			record.put("uuid", tool.getUuid());
			record.put("name", tool.getName());
			
			if (phaseToolUuidList != null && phaseToolUuidList.size() > 0 && phaseToolUuidList.contains(tool.getUuid())) {
				record.put("isSelected", "Y");
			} else {
				record.put("isSelected", "N");				
			}
			
			datatableReturnBean.addRecord(record);    		
    	}
    	
    	datatableReturnBean.setRecordsTotal(toolList.size());
    	datatableReturnBean.setRecordsFiltered(toolList.size());
    	
    	response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(datatableReturnBean.toString());     	
    }    
    
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    public @ResponseBody
    void doSave(ProjectWizardFormBean formBean,
    		    HttpSession session, 
    		    HttpServletResponse response) throws IOException {    	
    	JSONObject jsonObject = new JSONObject();
    	
		// 檢查是否已經登入系統
//		if (!SessionUtils.isLogon(session)) {
//			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
//			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.AUTHENTICATION_NOT_LOGON.getDesc());
//	    	
//			response.setContentType(JKEY_CONTENT_TYPE);
//			response.getWriter().write(jsonObject.toString());      	
//			return;
//		}
    	
    	// 檢查欄位值
    	if (StringUtils.isBlank(formBean.getProjectUuid()) || 
    			StringUtils.isBlank(formBean.getName())) {
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DATA_INCORRECT.getDesc());
	    	
			response.setContentType(JKEY_CONTENT_TYPE);
			response.getWriter().write(jsonObject.toString());      	
			return;
    	}
    	
		ProjectPhase projectPhase = new ProjectPhase();
		BeanUtils.copyProperties(formBean, projectPhase);
		
		String[] toolUuids = StringUtils.split(formBean.getToolUuids(), ",");

    	if (StringUtils.equals(FUNCTION_NAME_INSERT, formBean.getFunctionName())) {
    		// 新增資料    		
    		projectPhase.setCreator(SessionUtility.getUserUuid(session));
    		
    		try {
    			projectPhase = projectWizardService.createProjectPhase(projectPhase, toolUuids);
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
				jsonObject.put(JKEY_UUID, projectPhase.getUuid());	 
			} catch (Exception e) {
				e.printStackTrace();
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
				jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_INSERT_FAILED.getDesc());				
			}
    		
    	} else if (StringUtils.equals(FUNCTION_NAME_UPDATE, formBean.getFunctionName())) {
    		// 更新資料
			try { 
				projectPhase = projectWizardService.updateProjectPhase(projectPhase, toolUuids);
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);				
			} catch (NoDataFoundException e) {					
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
				jsonObject.put(JKEY_ERROR_MESSAGE, e.getMessage());
			} catch (Exception e) {
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
				jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_UPDATE_FAILED.getDesc());				
			}
    		
    	} else {
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_UNKNOWN_FUNCTION.getDesc());				
    	}
    	
    	response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(jsonObject.toString());      	
    }	
	
}
