package org.iii.see.controller.datamanagement;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.iii.see.controller.BaseController;
import org.iii.see.domain.CaseExtraDefinition;
import org.iii.see.enumeration.BuiltInCaseExtraDefinitionTypeEnum;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.exception.NoDataFoundException;
import org.iii.see.form.DatatableReturnBean;
import org.iii.see.form.datamanagement.CaseExtraDefinitionManagementFormBean;
import org.iii.see.service.CaseExtraDefinitionManagementService;
import org.iii.see.session.User;
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
@RequestMapping("/CaseExtraDefinitionManagement")
public class CaseExtraDefinitionManagementController extends BaseController {
	
	@Autowired
	private CaseExtraDefinitionManagementService caseExtraDefinitionManagementService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(HttpSession session) {
		ModelAndView mav = new ModelAndView("/front/CaseExtraDefinitionManagement");
		
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
    	
    	List<CaseExtraDefinition> caseExtraDefinitionList = caseExtraDefinitionManagementService.queryCaseExtraDefinitionList(projectUuid);
    	for (CaseExtraDefinition caseExtraDefinition : caseExtraDefinitionList) {
			Map<String, Object> record = new HashMap<String, Object>();
			record.put("uuid", caseExtraDefinition.getUuid());
			record.put("name", caseExtraDefinition.getName());
			record.put("serialNo", caseExtraDefinition.getSerialNo());
			record.put("valueType", caseExtraDefinition.getValueType());
			record.put("valueLength", caseExtraDefinition.getValueLength());
			record.put("valueMeasure", caseExtraDefinition.getValueMeasure());
			
			if (!StringUtils.isBlank(caseExtraDefinition.getValueType())) {
				record.put("valueTypeCaption", BuiltInCaseExtraDefinitionTypeEnum.valueOf(caseExtraDefinition.getValueType()).getDesc());				
			} else {
				record.put("valueTypeCaption", StringUtils.EMPTY);
			}
			
			datatableReturnBean.addRecord(record);    		
    	}
    	
    	datatableReturnBean.setRecordsTotal(caseExtraDefinitionList.size());
    	datatableReturnBean.setRecordsFiltered(caseExtraDefinitionList.size());
    	
    	response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(datatableReturnBean.toString());     	
    }    
	
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    public @ResponseBody
    void doSave(CaseExtraDefinitionManagementFormBean formBean,
    		    HttpSession session, HttpServletResponse response) throws IOException {
    	
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
    	
		CaseExtraDefinition caseExtraDefinition = new CaseExtraDefinition();
		BeanUtils.copyProperties(formBean, caseExtraDefinition);		
    	if (StringUtils.equals(FUNCTION_NAME_INSERT, formBean.getFunctionName())) {
    		// 新增資料    		
    		caseExtraDefinition.setCreator(SessionUtility.getUserAccount(session));
    		
    		try {
    			caseExtraDefinition = caseExtraDefinitionManagementService.createCaseExtraDefinition(caseExtraDefinition);
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
				jsonObject.put(JKEY_UUID, caseExtraDefinition.getUuid());	 
			} catch (Exception e) {
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
				jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_INSERT_FAILED.getDesc());				
			}
    		
    	} else if (StringUtils.equals(FUNCTION_NAME_UPDATE, formBean.getFunctionName())) {
    		// 更新資料
			try { 
				caseExtraDefinition = caseExtraDefinitionManagementService.updateCaseExtraDefinition(caseExtraDefinition);
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
    
    @RequestMapping(value = "/doDelete", method = RequestMethod.POST)
    public @ResponseBody
    void doDelete(CaseExtraDefinitionManagementFormBean formBean, 
    		      HttpSession session, HttpServletResponse response) throws IOException {

    	JSONObject jsonObject = new JSONObject();

		// TODO: 檢查是否已經登入系統, 是否為專案成員
    	User user = (User)session.getAttribute("user");
    	if (user == null) {
    		// TODO: throw an exception
    	}
    	
		try { 
			caseExtraDefinitionManagementService.deleteCaseExtraDefinition(formBean.getUuid());			
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
		} catch (NoDataFoundException e) {					
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, e.getMessage());
		} catch (Exception e) {
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DELETE_FAILED.getDesc());				
		}
    	
    	response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(jsonObject.toString());    	
    }
         
    
}
