package org.iii.see.controller.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.iii.see.controller.BaseController;
import org.iii.see.domain.CaseAttachment;
import org.iii.see.domain.CaseBasicData;
import org.iii.see.domain.CaseExtraDefinition;
import org.iii.see.domain.CasePortraitFile;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.enumeration.FileFolderEnum;
import org.iii.see.exception.DuplicatedDataException;
import org.iii.see.form.FileUploadReturnBean;
import org.iii.see.form.datamanagement.CaseCompositeDataFormBean;
import org.iii.see.service.CaseExtraDefinitionManagementService;
import org.iii.see.service.CaseManagementService;
import org.iii.see.session.User;
import org.iii.see.utility.SessionUtility;
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
@RequestMapping("/NewCase")
public class NewCaseController extends BaseController {
	
	private String FUNCTION_URL = "/front/NewCase";	
	
	@Autowired
	private CaseManagementService caseManagementService;

	@Autowired
	private CaseExtraDefinitionManagementService caseExtraDefinitionManagementService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam(value="projectUuid", required=true) String projectUuid,
			                    HttpSession session) {
		
		ModelAndView mav = super.getPage(session, FUNCTION_URL, projectUuid);
		if (StringUtils.equals(FUNCTION_URL, mav.getViewName())) {
			// 取得進階欄位
	    	List<CaseExtraDefinition> caseExtraDefinitionList = caseExtraDefinitionManagementService.queryCaseExtraDefinitionList(projectUuid);
	    	mav.addObject("caseExtraDefinitionList", caseExtraDefinitionList);    	
		}
				
		return mav;
	}	
	
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    public @ResponseBody
    void doSave(CaseCompositeDataFormBean formBean,
    		    HttpSession session, HttpServletResponse response) throws IOException {
    	
    	JSONObject jsonObject = new JSONObject();
    	
		// 檢查是否已經登入系統
    	if (!StringUtils.isEmpty(checkAuthorization(session, FUNCTION_URL))) {    
    		responseNotLoginError(response);
    		return;
    	}    	
    	
    	User user = SessionUtility.getUser(session);
    	    	
    	// 檢查欄位值    	
    	if (StringUtils.isBlank(formBean.getProjectUuid()) || 
    			StringUtils.isBlank(formBean.getCaseNo()) || 
    			StringUtils.isBlank(formBean.getRealname()) ||
    			StringUtils.isBlank(formBean.getSex()) ||
    			StringUtils.isBlank(formBean.getBusinessPhone()) ||
    			StringUtils.isBlank(formBean.getMobilePhone()) ||
    			StringUtils.isBlank(formBean.getAddress())) {
    		
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DATA_INCORRECT.getDesc());
	    	
			response.setContentType(JKEY_CONTENT_TYPE);
			response.getWriter().write(jsonObject.toString());      	
			return;
    	}
    	
		CaseBasicData caseBasicData = new CaseBasicData();
		BeanUtils.copyProperties(formBean, caseBasicData);						
    	if (StringUtils.equals(FUNCTION_NAME_INSERT, formBean.getFunctionName())) {
    		// 新增資料
    		caseBasicData.setCreator(user.getAccount());
    		
    		try {
    			caseBasicData = caseManagementService.createCase(caseBasicData, formBean.getCaseExtraDataList());
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
				jsonObject.put(JKEY_UUID, caseBasicData.getUuid());	 
			} catch (DuplicatedDataException e) {
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
				jsonObject.put(JKEY_ERROR_MESSAGE, "個案編號已經存在");				
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
    
    @RequestMapping(value = "/doUploadPortraitFile", method = RequestMethod.POST)
    public @ResponseBody
    void doUploadPortraitFile(@RequestParam(value="projectUuid", required=true) String projectUuid, 
    		                  @RequestParam(value="uuid", required=true) String uuid, 
    		                  @RequestParam(value="portraitFile", required=true) MultipartFile portraitFile, 
    		                  HttpSession session, 
    		                  HttpServletResponse response) throws IOException {
        	    	
		// 檢查是否已經登入系統
    	if (!StringUtils.isEmpty(checkAuthorization(session, FUNCTION_URL))) {    
    		responseNotLoginError(response);
    		return;
    	}    	
    	
    	User user = SessionUtility.getUser(session);
    	
    	// 儲存檔案
    	File storePath = composeFilePath(projectUuid, FileFolderEnum.Case, uuid);
    	if (!storePath.exists()) {
    		storePath.mkdirs();
    	}
    	    	
    	// 檔案名稱
    	String portraitUuid = String.valueOf(UUID.randomUUID());
    	String extName = extractExtName(portraitFile.getOriginalFilename());
    	
    	File uploadedFile = new File(storePath, 
    			                     StringUtils.isEmpty(extName) ? portraitUuid : portraitUuid.concat(".").concat(extName));
    	
    	OutputStream os = null;
        InputStream is = null;
    	
        try {
    		os = new FileOutputStream(uploadedFile);
            is = portraitFile.getInputStream();
            
	        byte[] buf = new byte[1024];
	        int n = 0;
	        while ((n = is.read(buf)) != -1) {
	            os.write(buf, 0, n);
	        }

	        FileUploadReturnBean fileUploadReturnBean = new FileUploadReturnBean();
	        fileUploadReturnBean.addUploadedEntry(portraitFile.getName(), portraitFile.getSize(), "");
	        
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
    	
    	CasePortraitFile casePortraitFile = new CasePortraitFile();
		casePortraitFile.setUuid(portraitUuid);    	
    	casePortraitFile.setDataUuid(uuid);
    	casePortraitFile.setCreator(user.getAccount());
    	casePortraitFile.setExtName(extName);
    	
    	casePortraitFile = caseManagementService.createCasePortraitFile(casePortraitFile);    	
    }    

    @RequestMapping(value = "/doUploadAttachment", method = RequestMethod.POST)
    public @ResponseBody
    void doUploadAttachment(@RequestParam(value="projectUuid", required=true) String projectUuid, 
    		                @RequestParam(value="uuid", required=true) String uuid, 
    		                @RequestParam(value="attachmentDesc", required=false) String attachmentDesc,
    		                @RequestParam(value="serialNo", required=true) short serialNo,
    		                @RequestParam(value="attachmentFile", required=true) MultipartFile attachmentFile, 
    		                HttpSession session, 
    		                HttpServletResponse response) throws IOException {
        	    	
		// 檢查是否已經登入系統
    	if (!StringUtils.isEmpty(checkAuthorization(session, FUNCTION_URL))) {    
    		responseNotLoginError(response);
    		return;
    	}    	
    	
    	User user = SessionUtility.getUser(session);
    	
    	// 儲存檔案
    	File storePath = composeFilePath(projectUuid, FileFolderEnum.Case, uuid);
    	if (!storePath.exists()) {
    		storePath.mkdirs();
    	}
    	    	
    	// 解決encoding問題
    	String attachmentDescUTF8 = StringUtils.EMPTY;
    	String attachmentFileNameUTF8 = StringUtils.EMPTY;
    	try {
			attachmentDescUTF8 = new String(attachmentDesc.getBytes ("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}

    	try {
    		attachmentFileNameUTF8 = new String(attachmentFile.getOriginalFilename().getBytes ("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
    	
    	// 檔案名稱
    	String attachmentUuid = String.valueOf(UUID.randomUUID());
    	String extName = extractExtName(attachmentFileNameUTF8);
    	
    	File uploadedFile = new File(storePath, 
    			                     StringUtils.isEmpty(extName) ? attachmentUuid : attachmentUuid.concat(".").concat(extName));
    	
    	OutputStream os = null;
        InputStream is = null;
    	
        try {
    		os = new FileOutputStream(uploadedFile);
            is = attachmentFile.getInputStream();
            
	        byte[] buf = new byte[1024];
	        int n = 0;
	        while ((n = is.read(buf)) != -1) {
	            os.write(buf, 0, n);
	        }

	        FileUploadReturnBean fileUploadReturnBean = new FileUploadReturnBean();
	        fileUploadReturnBean.addUploadedEntry(attachmentFile.getName(), attachmentFile.getSize(), "");
	        
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
    	
        CaseAttachment caseAttachment = new CaseAttachment();
        caseAttachment.setUuid(attachmentUuid);    	
        caseAttachment.setDataUuid(uuid);
        
        caseAttachment.setSerialNo(serialNo);
        caseAttachment.setFileName(extractFileName(attachmentFileNameUTF8));
        caseAttachment.setExtName(extName);
        caseAttachment.setDescription(attachmentDescUTF8);
    	caseAttachment.setCreator(user.getAccount());
        caseAttachment = caseManagementService.createCaseAttachment(caseAttachment);    	
    }    
    
}
