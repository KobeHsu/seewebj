package org.iii.see.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.iii.see.constant.UsibilityTestFormManagementConstant;
import org.iii.see.domain.UsibilityTestFormTemplate;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.exception.NoDataFoundException;
import org.iii.see.form.DatatableReturnBean;
import org.iii.see.form.FileUploadReturnBean;
import org.iii.see.form.UsibilityTestFormManagementFormBean;
import org.iii.see.service.UsibilityTestFormManagementService;
import org.iii.see.session.User;
import org.iii.see.utility.SessionUtility;
import org.iii.see.utility.SystemProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/UsibilityTestFormManagement")
public class UsibilityTestFormManagementController extends BaseController {

	@Autowired
	private UsibilityTestFormManagementService usibilityTestFormManagementService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(HttpSession session) {
		ModelAndView mav = new ModelAndView("/front/UsibilityTestFormManagement");
		
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
    	
    	List<UsibilityTestFormTemplate> usibilityTestFormTemplateList = usibilityTestFormManagementService.queryUsibilityTestFormTemplateList(projectUuid);
    	for (UsibilityTestFormTemplate usibilityTestFormTemplate : usibilityTestFormTemplateList) {
			Map<String, Object> record = new HashMap<String, Object>();
			record.put("uuid", usibilityTestFormTemplate.getUuid());
			record.put("name", usibilityTestFormTemplate.getName());
			record.put("serialNo", usibilityTestFormTemplate.getSerialNo());
			record.put("description", usibilityTestFormTemplate.getDescription());
			
			String fileName = composeFileName(usibilityTestFormTemplate.getFileName(), usibilityTestFormTemplate.getExtName()); 
			record.put("fileName", fileName);
			
			datatableReturnBean.addRecord(record);    		
    	}
    	
    	datatableReturnBean.setRecordsTotal(usibilityTestFormTemplateList.size());
    	datatableReturnBean.setRecordsFiltered(usibilityTestFormTemplateList.size());
    	
    	response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(datatableReturnBean.toString());     	
    }    
	
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    public @ResponseBody
    void doSave(UsibilityTestFormManagementFormBean formBean,
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
    	
    	UsibilityTestFormTemplate usibilityTestFormTemplate = new UsibilityTestFormTemplate();
		BeanUtils.copyProperties(formBean, usibilityTestFormTemplate);		
    	if (StringUtils.equals(FUNCTION_NAME_INSERT, formBean.getFunctionName())) {
    		// 新增資料    		
    		usibilityTestFormTemplate.setCreator(SessionUtility.getUserAccount(session));
    		
    		try {
    			usibilityTestFormTemplate = usibilityTestFormManagementService.createUsibilityTestFormTemplate(usibilityTestFormTemplate);
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
				jsonObject.put(JKEY_UUID, usibilityTestFormTemplate.getUuid());	 
			} catch (Exception e) {
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
				jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_INSERT_FAILED.getDesc());				
			}
    		
    	} else if (StringUtils.equals(FUNCTION_NAME_UPDATE, formBean.getFunctionName())) {
    		// 更新資料
			try { 
				usibilityTestFormTemplate = usibilityTestFormManagementService.updateUsibilityTestFormTemplate(usibilityTestFormTemplate);
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

    @RequestMapping(value = "/doUploadFormTemplateFile", method = RequestMethod.POST)
    public @ResponseBody
    void doUploadAttachment(@RequestParam(value="projectUuid", required=true) String projectUuid, 
    		                @RequestParam(value="uuid", required=true) String uuid, 
    		                @RequestParam(value="formFile", required=true) MultipartFile formFile, 
    		                HttpSession session, 
    		                HttpServletResponse response) {
        	    	
		// TODO: 檢查是否已經登入系統, 是否為專案成員
    	User user = (User)session.getAttribute("user");
    	if (user == null) {
    		// TODO: throw an exception
    	}
    	
    	UsibilityTestFormTemplate usibilityTestFormTemplate = usibilityTestFormManagementService.queryUsibilityTestFormTemplate(uuid);
    	if (usibilityTestFormTemplate == null) {
    		// TODO: throw an exception
    	}
    	
    	String existingFileName = StringUtils.EMPTY;
    	if (StringUtils.isNotEmpty(usibilityTestFormTemplate.getFileName()) || StringUtils.isNotEmpty(usibilityTestFormTemplate.getExtName())) {
    		existingFileName = composeFileName(uuid, usibilityTestFormTemplate.getExtName()); 
    	}
    	    	
    	// 儲存檔案
    	File storePath = new File(SystemProperties.getFileDirectory());
    	if (!storePath.exists()) {
    		storePath.mkdirs();
    	}
    	
    	// 第一層目錄: Project UUID
    	storePath = new File(storePath, projectUuid); 
    	if (!storePath.exists()) {
    		storePath.mkdirs();
    	}
    	
    	// 第二層目錄 
    	storePath = new File(storePath, UsibilityTestFormManagementConstant.FORM_TEMPLATE_FILE_DIR); 
    	if (!storePath.exists()) {
    		storePath.mkdirs();
    	}
    	
    	// 刪除舊檔
    	if (StringUtils.isNotEmpty(existingFileName)) {
    		File existingFile = new File(storePath, existingFileName);
    		if (existingFile.exists()) {    			
    			existingFile.delete();
    		}
    	}
    	    	
    	// 解決encoding問題
    	String fileNameUTF8 = StringUtils.EMPTY;
    	try {
    		fileNameUTF8 = new String(formFile.getOriginalFilename().getBytes ("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
    	
    	// 檔案名稱
    	String extName = extractExtName(fileNameUTF8);
    	
    	File uploadedFile = new File(storePath, composeFileName(uuid, extName));
    	
    	OutputStream os = null;
        InputStream is = null;
    	
        try {
    		os = new FileOutputStream(uploadedFile);
            is = formFile.getInputStream();
            
	        byte[] buf = new byte[1024];
	        int n = 0;
	        while ((n = is.read(buf)) != -1) {
	            os.write(buf, 0, n);
	        }

	        FileUploadReturnBean fileUploadReturnBean = new FileUploadReturnBean();
	        fileUploadReturnBean.addUploadedEntry(formFile.getName(), formFile.getSize(), "");
	        
	    	response.setContentType(JKEY_CONTENT_TYPE);
			response.getWriter().write(fileUploadReturnBean.toString());    		        
	        
		} catch (FileNotFoundException e) {
			// TODO: Error Handling
		} catch (IOException e) {
			// TODO: Error Handling
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
			}
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
			}
	    }
    	        
    	// TODO:
    	// casePortraitFile.setCreator(user.getUuid());
        try {
			usibilityTestFormTemplate = usibilityTestFormManagementService.updateUsibilityTestFormTemplateFile(uuid, extractFileName(fileNameUTF8), extName);
		} catch (NoDataFoundException e) {
			e.printStackTrace();
		}    	
    }    

    @RequestMapping(value = "/doDelete", method = RequestMethod.POST)
    public @ResponseBody
    void doDelete(UsibilityTestFormManagementFormBean formBean, 
    		      HttpSession session, 
    		      HttpServletResponse response) throws IOException {

    	JSONObject jsonObject = new JSONObject();

		// TODO: 檢查是否已經登入系統, 是否為專案成員
    	User user = (User)session.getAttribute("user");
    	if (user == null) {
    		// TODO: throw an exception
    	}
    	
    	
    	UsibilityTestFormTemplate usibilityTestFormTemplate = usibilityTestFormManagementService.queryUsibilityTestFormTemplate(formBean.getUuid());
    	if (usibilityTestFormTemplate == null) {
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_NO_DATA_FOUND.getDesc());				
    	} else {
        	String existingFileName = StringUtils.EMPTY;
        	if (StringUtils.isNotEmpty(usibilityTestFormTemplate.getFileName()) || StringUtils.isNotEmpty(usibilityTestFormTemplate.getExtName())) {
        		existingFileName = composeFileName(usibilityTestFormTemplate.getUuid(), usibilityTestFormTemplate.getExtName()); 
        	}
        	
        	// 儲存檔案
        	File storePath = new File(SystemProperties.getFileDirectory());
        	if (!storePath.exists()) {
        		storePath.mkdirs();
        	}
        	
        	// 第一層目錄: Project UUID
        	storePath = new File(storePath, usibilityTestFormTemplate.getProjectUuid()); 
        	if (!storePath.exists()) {
        		storePath.mkdirs();
        	}
        	
        	// 第二層目錄 
        	storePath = new File(storePath, UsibilityTestFormManagementConstant.FORM_TEMPLATE_FILE_DIR); 
        	if (!storePath.exists()) {
        		storePath.mkdirs();
        	}
        	
        	// 刪除舊檔
        	if (StringUtils.isNotEmpty(existingFileName)) {
        		File existingFile = new File(storePath, existingFileName);
        		if (existingFile.exists()) {    			
        			existingFile.delete();
        		}
        	}
        	
    		try { 
    			usibilityTestFormManagementService.deleteUsibilityTestFormTemplate(formBean.getUuid());			
    			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
    		} catch (NoDataFoundException e) {					
    			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
    			jsonObject.put(JKEY_ERROR_MESSAGE, e.getMessage());
    		} catch (Exception e) {
    			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
    			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DELETE_FAILED.getDesc());				
    		}
    	}
    	
    	
    	response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(jsonObject.toString());    	
    }
    
}
