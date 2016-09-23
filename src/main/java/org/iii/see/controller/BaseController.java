package org.iii.see.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.enumeration.FileFolderEnum;
import org.iii.see.utility.SessionUtility;
import org.iii.see.utility.SystemProperties;
import org.springframework.web.servlet.ModelAndView;

public class BaseController {
	
	protected final String JKEY_CONTENT_TYPE = "application/json;charset=utf-8";
	
	protected final String FUNCTION_NAME_INSERT = "INSERT";
	protected final String FUNCTION_NAME_UPDATE = "UPDATE";
	protected final String FUNCTION_NAME_DELETE = "DELETE";
	protected final String FUNCTION_NAME_QUERY = "QUERY";
	
	protected final String JKEY_FUNCTION_NAME = "functionName";
	protected final String JKEY_FUNCTION_STATUS = "functionStatus";
	protected final String JKEY_ERROR_MESSAGE = "errorMessage";
	protected final String JKEY_UUID = "uuid";
	
	protected final String JVALUE_FUNCTION_SUCCESS = "SUCCESS";
	protected final String JVALUE_FUNCTION_FAILED = "FAILED";

    protected String extractExtName(String originalFilename) {
    	String extName = StringUtils.EMPTY;
    	int dotIndex = originalFilename.lastIndexOf(".");
    	if (dotIndex >= 0 && dotIndex < originalFilename.length() - 1) {
    		extName = originalFilename.substring(dotIndex+1);
    	}
    	return extName;
    }

    protected String extractFileName(String originalFilename) {
    	String fileName = StringUtils.EMPTY;
    	int dotIndex = originalFilename.lastIndexOf(".");
    	if (dotIndex >= 0 && dotIndex < originalFilename.length() - 1) {
    		fileName = originalFilename.substring(0, dotIndex);
    	} else {
    		fileName = originalFilename;
    	}
    	return fileName;
    }
    
    protected String composeFileName(String fileName, String extName) {
    	return StringUtils.isEmpty(extName) ? fileName : fileName.concat(".").concat(extName);
    }
    	
	protected File composeFilePath(String projectUuid, FileFolderEnum fileFolderEnum, String dataUuid) {
		// 根目錄			
    	File storePath = new File(SystemProperties.getFileDirectory());	    	
    	// 第一層目錄: Project UUID
    	storePath = new File(storePath, projectUuid); 	    	
    	// 第二層目錄: 功能名稱
    	storePath = new File(storePath, fileFolderEnum.getCode()); 
    	// 第三層目錄: Data UUID
    	storePath = new File(storePath, dataUuid); 		
		
    	return storePath;
	}
    
	protected String checkAuthorization(HttpSession session, String url, String projectUuid) {
    	// 檢查是否登入
		if (!SessionUtility.isLogon(session)) {
    		return "/front/Login";
    	}
		
    	// TODO: 檢查是否有授權
		/*
		@SuppressWarnings("unchecked")
		Map<String, String> authorizationMap = (Map<String, String>)session.getAttribute("authorizationMap");
		if (authorizationMap == null || authorizationMap.get(url) == null) {
			return "/front/UnauthorizedAccess";
		} 
		*/    	
    	
    	return StringUtils.EMPTY;
	}

	protected String checkAuthorization(HttpSession session, String url) {
    	// 檢查是否登入
		if (!SessionUtility.isLogon(session)) {
    		return "/front/Login";
    	}
		
    	// TODO: 檢查專案及是否有授權
		/*
		@SuppressWarnings("unchecked")
		Map<String, String> authorizationMap = (Map<String, String>)session.getAttribute("authorizationMap");
		if (authorizationMap == null || authorizationMap.get(url) == null) {
			return "/front/UnauthorizedAccess";
		} 
		*/    	
    	
    	return StringUtils.EMPTY;
	}
	
	protected ModelAndView getPage(HttpSession session, String url, String projectUuid) {
    	
    	String unauthorizedPath = (StringUtils.isEmpty(projectUuid)) ? checkAuthorization(session, url) : checkAuthorization(session, url, projectUuid);
    	if (StringUtils.isNotEmpty(unauthorizedPath)) {
    		return new ModelAndView(unauthorizedPath);
    	}
    	
    	ModelAndView mav = new ModelAndView(url);    	
    	if (!StringUtils.isEmpty(projectUuid)) {
    		mav.addObject("projectUuid", projectUuid);    		
    	}

    	return mav;
	}

	protected ModelAndView getPage(HttpSession session, String url) {    	
    	return getPage(session, url, null);
	}
	
	protected void responseNotLoginError(HttpServletResponse response) throws IOException {
    	JSONObject jsonObject = new JSONObject();
		jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
		jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.AUTHENTICATION_NOT_LOGON.getCode());
    	response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(jsonObject.toString());		
	}
}
