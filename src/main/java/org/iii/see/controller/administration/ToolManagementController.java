package org.iii.see.controller.administration;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.iii.see.controller.BaseController;
import org.iii.see.domain.Tool;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.exception.DuplicatedDataException;
import org.iii.see.exception.NoDataFoundException;
import org.iii.see.form.DatatableReturnBean;
import org.iii.see.form.administration.ToolManagementFormBean;
import org.iii.see.service.PagingResultSet;
import org.iii.see.service.ToolManagementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ToolManagement")
public class ToolManagementController extends BaseController {

	private String FUNCTION_URL = "/front/ToolManagement";		
	
	@Autowired
	private ToolManagementService toolManagementService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(HttpSession session) {
		
		return super.getPage(session, FUNCTION_URL);
	}	

    @RequestMapping(value = "/doQuery", method = RequestMethod.POST)
    public @ResponseBody
    void doQuery(ToolManagementFormBean formBean,
		         HttpSession session, 
		         HttpServletResponse response) throws IOException {
    	
		// 檢查是否已經登入系統
    	if (!StringUtils.isEmpty(checkAuthorization(session, FUNCTION_URL))) {    
    		responseNotLoginError(response);
    		return;
    	}    	
    	
    	DatatableReturnBean datatableReturnBean = new DatatableReturnBean();
    	datatableReturnBean.setDraw(formBean.getDraw());
    	
		PagingResultSet pagingResultSet = toolManagementService.queryToolList(formBean.getStart(), 
				                                                              formBean.getLength(), 
				                                                              formBean.getQueryName());
		List<Object[]> resultSet = pagingResultSet.getResultSet();
		if (resultSet != null) {
    		for (Object[] result : resultSet) {
    			
    			Map<String, Object> record = new HashMap<String, Object>();

    			record.put("uuid", result[0]);
    			record.put("name", result[1]);
    			record.put("description", result[2]);
    			record.put("url", result[3]);
    			
    			datatableReturnBean.addRecord(record);
    		}
		}
    	    	
    	datatableReturnBean.setRecordsTotal(pagingResultSet.getRecordsTotal());
    	datatableReturnBean.setRecordsFiltered(resultSet.size());
		
    	response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(datatableReturnBean.toString());    	    	
    }    
	
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    public @ResponseBody
    void doSave(ToolManagementFormBean formBean,
    		    HttpSession session, 
    		    HttpServletResponse response) throws IOException {
    	
    	JSONObject jsonObject = new JSONObject();
    	
		// 檢查是否已經登入系統
    	if (!StringUtils.isEmpty(checkAuthorization(session, FUNCTION_URL))) {    
    		responseNotLoginError(response);
    		return;
    	}
    	
    	// 檢查欄位值
    	if (StringUtils.isBlank(formBean.getName()) || StringUtils.isBlank(formBean.getUrl())) {
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DATA_INCORRECT.getDesc());
	    	
			response.setContentType(JKEY_CONTENT_TYPE);
			response.getWriter().write(jsonObject.toString());      	
			return;
    	}
    	
    	
    	Tool tool = new Tool();
		BeanUtils.copyProperties(formBean, tool);
		
    	if (StringUtils.equals(FUNCTION_NAME_INSERT, formBean.getFunctionName())) {
    		// 新增資料    		    		
    		try {
    			tool = toolManagementService.createTool(tool);
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
				jsonObject.put(JKEY_UUID, tool.getUuid());
			} catch (DuplicatedDataException e) {
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
				jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DUPLICATED_DATA.getDesc());				
			} catch (Exception e) {
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
				jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_INSERT_FAILED.getDesc());				
			}
    		
    	} else if (StringUtils.equals(FUNCTION_NAME_UPDATE, formBean.getFunctionName())) {
    		// 更新資料
			try { 
				tool = toolManagementService.updateTool(tool);
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);				
			} catch (DuplicatedDataException e) {
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
				jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DUPLICATED_DATA.getDesc());				
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
    void doDelete(ToolManagementFormBean formBean,
    		      HttpSession session, 
    		      HttpServletResponse response) throws IOException {
    	
		// 檢查是否已經登入系統
    	if (!StringUtils.isEmpty(checkAuthorization(session, FUNCTION_URL))) {    
    		responseNotLoginError(response);
    		return;
    	}
    	
    	JSONObject jsonObject = new JSONObject();
    	   	    	    	
		// 刪除資料
		try { 
			toolManagementService.deleteTool(formBean.getUuid());
			
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
