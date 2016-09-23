package org.iii.see.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.iii.see.domain.OnlineIssue;
import org.iii.see.domain.OnlineIssueFigure;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.enumeration.FileFolderEnum;
import org.iii.see.enumeration.OnlineIssueStatusEnum;
import org.iii.see.form.FileUploadReturnBean;
import org.iii.see.form.OnlineIssueDataFormBean;
import org.iii.see.service.OnlineIssueManagementService;
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
@RequestMapping("/NewOnlineIssue")
public class NewOnlineIssueController extends BaseController {
	
	private String FUNCTION_URL = "/front/NewOnlineIssue";	
	
	@Autowired
	private OnlineIssueManagementService onlineIssueManagementService;		

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam(value="projectUuid", required=true) String projectUuid,
			                    HttpSession session) {
		
		return super.getPage(session, FUNCTION_URL, projectUuid);
	}	
	
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    public @ResponseBody
    void doSave(OnlineIssueDataFormBean formBean,
    		    HttpSession session, 
    		    HttpServletResponse response) throws IOException {
    	    	
		// 檢查是否已經登入系統
    	if (!StringUtils.isEmpty(checkAuthorization(session, FUNCTION_URL, formBean.getProjectUuid()))) {    
    		responseNotLoginError(response);
    		return;
    	}
    	
    	JSONObject jsonObject = new JSONObject();
    	
    	User user = SessionUtility.getUser(session); 
    	
    	// 檢查欄位值    	
    	if (StringUtils.isBlank(formBean.getProjectUuid()) || 
    			StringUtils.isBlank(formBean.getTitle()) || 
    			StringUtils.isBlank(formBean.getContent())) {
    		
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DATA_INCORRECT.getDesc());
	    	
			response.setContentType(JKEY_CONTENT_TYPE);
			response.getWriter().write(jsonObject.toString());      	
			return;
    	}
    	
    	OnlineIssue onlineIssue = new OnlineIssue();
		BeanUtils.copyProperties(formBean, onlineIssue);
		
		onlineIssue.setSubmitter(user.getAccount());
		
    	if (StringUtils.equals(FUNCTION_NAME_INSERT, formBean.getFunctionName())) {
    		// 新增資料
    		onlineIssue.setStatus(OnlineIssueStatusEnum.OPEN.getCode());
    		
    		try {
    			onlineIssue = onlineIssueManagementService.createOnlineIssue(onlineIssue);
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
				jsonObject.put(JKEY_UUID, onlineIssue.getUuid());	 
			} catch (Exception e) {
				e.printStackTrace();
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

    @RequestMapping(value = "/doUploadFigure", method = RequestMethod.POST)
    public @ResponseBody
    void doUploadFigure(@RequestParam(value="projectUuid", required=true) String projectUuid, 
    		            @RequestParam(value="uuid", required=true) String uuid, 
    		            @RequestParam(value="figureFile", required=true) MultipartFile figureFile, 
    		            HttpSession session, 
    		            HttpServletResponse response) {
        	    	
		// 檢查是否已經登入系統
    	if (!StringUtils.isEmpty(checkAuthorization(session, FUNCTION_URL, projectUuid))) {    
    		// TODO: throw an exception
    	}
    	
    	// 儲存檔案
    	File storePath = composeFilePath(projectUuid, FileFolderEnum.OnlineIssue, uuid);
    	if (!storePath.exists()) {
    		storePath.mkdirs();
    	}
    	    	
    	// 解決encoding問題
    	String attachmentFileNameUTF8 = StringUtils.EMPTY;
    	try {
    		attachmentFileNameUTF8 = new String(figureFile.getOriginalFilename().getBytes ("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
    	
    	    	
    	// 檔案名稱
    	String figureUuid = String.valueOf(UUID.randomUUID());
    	String extName = extractExtName(figureFile.getOriginalFilename());
    	
    	File uploadedFile = new File(storePath, 
    			                     StringUtils.isEmpty(extName) ? figureUuid : figureUuid.concat(".").concat(extName));
    	
    	OutputStream os = null;
        InputStream is = null;
    	
        try {
    		os = new FileOutputStream(uploadedFile);
            is = figureFile.getInputStream();
            
	        byte[] buf = new byte[1024];
	        int n = 0;
	        while ((n = is.read(buf)) != -1) {
	            os.write(buf, 0, n);
	        }

	        FileUploadReturnBean fileUploadReturnBean = new FileUploadReturnBean();
	        fileUploadReturnBean.addUploadedEntry(figureFile.getName(), figureFile.getSize(), "");
	        
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
    	
        
        OnlineIssueFigure onlineIssueFigure = new OnlineIssueFigure();
        onlineIssueFigure.setUuid(figureUuid);    	
        onlineIssueFigure.setDataUuid(uuid);
    	onlineIssueFigure.setCreator(SessionUtility.getUserAccount(session));
        onlineIssueFigure.setFileName(extractFileName(attachmentFileNameUTF8));
        onlineIssueFigure.setExtName(extName);
    	
        onlineIssueFigure = onlineIssueManagementService.createOnlineIssueFigure(onlineIssueFigure);    	
    }    
    
}
