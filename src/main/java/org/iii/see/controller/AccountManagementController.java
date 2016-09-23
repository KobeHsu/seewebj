package org.iii.see.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.iii.see.domain.Account;
import org.iii.see.enumeration.AccountStatusEnum;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.exception.NoDataFoundException;
import org.iii.see.form.AccountManagementFormBean;
import org.iii.see.form.DatatableReturnBean;
import org.iii.see.service.AccountManagementService;
import org.iii.see.service.PagingResultSet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/AccountManagement")
public class AccountManagementController extends BaseController {

	@Autowired
	private AccountManagementService accountManagementService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(HttpSession session) {
		ModelAndView mav = new ModelAndView("/front/AccountManagement");
		
		// 檢查是否已經登入系統
//		if (!SessionUtils.isLogon(session)) {
//			mav = new ModelAndView("/front/Login");
//		}		
		return mav;
	}
	
    @RequestMapping(value = "/doQuery", method = RequestMethod.POST)
    public @ResponseBody
    void doQuery(AccountManagementFormBean formBean,
		         HttpSession session, HttpServletResponse response) throws IOException {
    	
    	DatatableReturnBean datatableReturnBean = new DatatableReturnBean();
    	datatableReturnBean.setDraw(formBean.getDraw());
    	
		PagingResultSet pagingResultSet = accountManagementService.queryAccount(formBean.getStart(), formBean.getLength(), formBean.getQueryAccount(), formBean.getQueryRealname(), formBean.getQueryStatus());
		List<Object[]> resultSet = pagingResultSet.getResultSet();
		if (resultSet != null) {
    		for (Object[] result : resultSet) {
    			
    			Map<String, Object> record = new HashMap<String, Object>();

    			record.put("uuid", result[0]);
    			record.put("account", result[1]);
    			record.put("realname", result[2]);

    			try {
        			record.put("statusCaption", AccountStatusEnum.valueOf((String)result[3]).getDesc());    				
    			} catch (Exception e) {
    				record.put("statusCaption", StringUtils.EMPTY);
    			}
    			
    			record.put("status", result[3]);
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
    void doSave(AccountManagementFormBean formBean,
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
    	if (StringUtils.isBlank(formBean.getAccount()) || 
    			StringUtils.isBlank(formBean.getPassword()) ||
    			StringUtils.isBlank(formBean.getRealname()) ||
    			StringUtils.isBlank(formBean.getStatus())) {
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DATA_INCORRECT.getDesc());
	    	
			response.setContentType(JKEY_CONTENT_TYPE);
			response.getWriter().write(jsonObject.toString());      	
			return;
    	}
    	
    	String[] ignoreProperties = {"createTime", "updateTime", "verifyTime"};
    	
    	Account account = new Account();
		BeanUtils.copyProperties(formBean, account, ignoreProperties);
		
    	if (StringUtils.equals(FUNCTION_NAME_INSERT, formBean.getFunctionName())) {
    		// 新增資料    		
    		
    		try {
    			account = accountManagementService.createAccount(account);
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
				jsonObject.put(JKEY_UUID, account.getUuid());
			} catch (Exception e) {
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
				jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_INSERT_FAILED.getDesc());				
			}
    		
    	} else if (StringUtils.equals(FUNCTION_NAME_UPDATE, formBean.getFunctionName())) {
    		// 更新資料
			try { 
				account = accountManagementService.updateAccount(account);
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
	
	
    @RequestMapping(value = "/doQueryDetail", method = RequestMethod.POST)
    public @ResponseBody
    void doQueryDetail(AccountManagementFormBean formBean, HttpSession session, HttpServletResponse response) throws IOException {
    	
    	JSONObject jsonObject = new JSONObject();
    	
    	Account account = accountManagementService.queryAccount(formBean.getUuid());
    	if (account == null) {
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_UNKNOWN_FUNCTION.getDesc());    		
    	} else {
    		jsonObject.put("uuid", account.getUuid());
    		jsonObject.put("account", account.getAccount());
    		jsonObject.put("realname", account.getRealname());
    		jsonObject.put("phone", account.getPhone());
    		jsonObject.put("email", account.getEmail());
    		jsonObject.put("status", account.getStatus());
    		
    		jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
    	}
    	
    	response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(jsonObject.toString());    	
    }    
	
}
