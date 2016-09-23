package org.iii.see.controller.administration;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.iii.see.controller.BaseController;
import org.iii.see.domain.Account;
import org.iii.see.domain.OnlineIssue;
import org.iii.see.domain.OnlineIssueFigure;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.enumeration.OnlineIssueStatusEnum;
import org.iii.see.exception.NoDataFoundException;
import org.iii.see.form.DatatableReturnBean;
import org.iii.see.form.administration.OnlineIssueManagementFormBean;
import org.iii.see.service.AccountManagementService;
import org.iii.see.service.OnlineIssueManagementService;
import org.iii.see.service.PagingResultSet;
import org.iii.see.session.User;
import org.iii.see.utility.DateTimeUtility;
import org.iii.see.utility.SessionUtility;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/OnlineIssueManagement")
public class OnlineIssueManagementController extends BaseController {
	
	private String FUNCTION_URL = "/front/OnlineIssueManagement";		

	@Autowired
	private AccountManagementService accountManagementService;

	@Autowired
	private OnlineIssueManagementService onlineIssueManagementService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(HttpSession session) {
		
		return super.getPage(session, FUNCTION_URL);
	}	
	
    @RequestMapping(value = "/doQuery", method = RequestMethod.POST)
    public @ResponseBody
    void doQuery(OnlineIssueManagementFormBean formBean,
		         HttpSession session, 
		         HttpServletResponse response) throws IOException {
    	
		// 檢查是否已經登入系統
    	if (!StringUtils.isEmpty(checkAuthorization(session, FUNCTION_URL))) {    
    		responseNotLoginError(response);
    		return;
    	}    	
    	
    	DatatableReturnBean datatableReturnBean = new DatatableReturnBean();
    	datatableReturnBean.setDraw(formBean.getDraw());
    	
		PagingResultSet pagingResultSet = onlineIssueManagementService.queryOnlineIssueList(formBean.getStart(), 
				                                                                            formBean.getLength(), 
				                                                                            formBean.getQueryBeginDate(), 
				                                                                            formBean.getQueryEndDate(),
				                                                                            formBean.getQueryProject(),
				                                                                            formBean.getQueryStatus());
		List<Object[]> resultSet = pagingResultSet.getResultSet();
		if (resultSet != null) {
    		for (Object[] result : resultSet) {
    			
    			Map<String, Object> record = new HashMap<String, Object>();

    			record.put("uuid", result[0]);
    			record.put("projectName", result[1]);
    			record.put("title", result[2]);

    			try {
        			record.put("status", OnlineIssueStatusEnum.valueOf((String)result[3]).getDesc());    				
    			} catch (Exception e) {
    				record.put("status", StringUtils.EMPTY);
    			}
    			
    			datatableReturnBean.addRecord(record);
    		}
		}
    	    	
    	datatableReturnBean.setRecordsTotal(pagingResultSet.getRecordsTotal());
    	datatableReturnBean.setRecordsFiltered(resultSet.size());
		
    	response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(datatableReturnBean.toString());    	    	
    }    
	
    @RequestMapping(value = "/doQueryDetail", method = RequestMethod.POST)
    public @ResponseBody
    void doQueryDetail(OnlineIssueManagementFormBean formBean, HttpSession session, HttpServletResponse response) throws IOException {
    	
    	JSONObject jsonObject = new JSONObject();
    	
    	OnlineIssue onlineIssue = onlineIssueManagementService.queryOnlineIssue(formBean.getUuid());
    	if (onlineIssue == null) {
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_UNKNOWN_FUNCTION.getDesc());    		
    	} else {
    		
    		jsonObject.put("uuid", onlineIssue.getUuid());
    		jsonObject.put("project", onlineIssue.getProjectUuid());
    		jsonObject.put("title", onlineIssue.getTitle());
    		jsonObject.put("content", onlineIssue.getContent());
    		jsonObject.put("replyContent", onlineIssue.getReplyContent());
    		jsonObject.put("status", onlineIssue.getStatus());
    		
    		jsonObject.put("createTime", DateTimeUtility.convertTimestampToDateTimeString(onlineIssue.getCreateTime()));
    		jsonObject.put("updateTime", DateTimeUtility.convertTimestampToDateTimeString(onlineIssue.getUpdateTime()));

    		Account submitter = accountManagementService.queryAccount(onlineIssue.getSubmitter());
    		Account processor = accountManagementService.queryAccount(onlineIssue.getProcessor());
    	
    		if (submitter != null && submitter.getAccount() != null) {
        		jsonObject.put("submitter", submitter.getAccount());    			
    		} else {
    			jsonObject.put("submitter", StringUtils.EMPTY);
    		}

    		if (processor != null && processor.getAccount() != null) {
    			jsonObject.put("processor", processor.getAccount());    			
    		} else {
    			jsonObject.put("processor", StringUtils.EMPTY);
    		}
    		
    		// 畫面擷圖清單
        	JSONArray jsonArray = new JSONArray();
    		List<OnlineIssueFigure> figureList = onlineIssueManagementService.queryOnlineIssueFigureList(formBean.getUuid());
    		for (OnlineIssueFigure figure : figureList) {
    			Map<String, Object> figureData = new HashMap<String, Object>();
    			figureData.put("uuid", figure.getUuid());
    			figureData.put("fileName", figure.getFileName());
    			figureData.put("extName", figure.getExtName());
    			
    			jsonArray.add(figureData);
    		}
    		jsonObject.put("figures", jsonArray);
    		    		
    		jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
    	}
    	
    	response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(jsonObject.toString());    	
    }    

    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    public @ResponseBody
    void doSave(OnlineIssueManagementFormBean formBean,
    		    HttpSession session, 
    		    HttpServletResponse response) throws IOException {
    	
		// 檢查是否已經登入系統
    	if (!StringUtils.isEmpty(checkAuthorization(session, FUNCTION_URL))) {    
    		responseNotLoginError(response);
    		return;
    	}
    	
    	JSONObject jsonObject = new JSONObject();
    	
    	User user = SessionUtility.getUser(session);     	
    	   	
    	// 檢查欄位值
    	if (StringUtils.isBlank(formBean.getStatus())) {
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DATA_INCORRECT.getDesc());
	    	
			response.setContentType(JKEY_CONTENT_TYPE);
			response.getWriter().write(jsonObject.toString());      	
			return;
    	}
    	    	
    	OnlineIssue onlineIssue = new OnlineIssue();
		BeanUtils.copyProperties(formBean, onlineIssue);
		
		onlineIssue.setProcessor(user.getAccount());
		
    	if (StringUtils.equals(FUNCTION_NAME_UPDATE, formBean.getFunctionName())) {
    		// 更新資料
			try { 
				onlineIssue = onlineIssueManagementService.updateOnlineIssue(onlineIssue);
				// 回傳處理時間及處理人員
	    		jsonObject.put("updateTime", DateTimeUtility.convertTimestampToDateTimeString(onlineIssue.getUpdateTime()));
    			jsonObject.put("processor", user.getAccount());    			
				
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
    void doDelete(OnlineIssueManagementFormBean formBean,
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
			onlineIssueManagementService.deleteOnlineIssue(formBean.getUuid());
			
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
