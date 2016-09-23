package org.iii.see.controller.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.iii.see.controller.BaseController;
import org.iii.see.domain.CaseAttachment;
import org.iii.see.domain.CaseBasicData;
import org.iii.see.domain.CaseExtraData;
import org.iii.see.domain.CaseExtraDefinition;
import org.iii.see.domain.CasePortraitFile;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.enumeration.FileFolderEnum;
import org.iii.see.exception.DuplicatedDataException;
import org.iii.see.exception.NoDataFoundException;
import org.iii.see.form.FileUploadReturnBean;
import org.iii.see.form.datamanagement.CaseCompositeDataFormBean;
import org.iii.see.form.datamanagement.CaseExtraDataFormBean;
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
@RequestMapping("/EditCase")
public class EditCaseController extends BaseController {
	
	private String FUNCTION_URL = "/front/EditCase";		

	@Autowired
	private CaseManagementService caseManagementService;

	@Autowired
	private CaseExtraDefinitionManagementService caseExtraDefinitionManagementService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam(value="projectUuid", required=true) String projectUuid,
			                    @RequestParam(value="dataUuid", required=true) String dataUuid,
			                    HttpSession session) {
		
		ModelAndView mav = super.getPage(session, FUNCTION_URL, projectUuid);
		if (StringUtils.equals(FUNCTION_URL, mav.getViewName())) {
			mav.addObject("dataUuid", dataUuid);

	    	Object[] caseDataSet = caseManagementService.queryCase(projectUuid, dataUuid);
	    	// 基本欄位
	    	CaseBasicData caseBasicData = (CaseBasicData)caseDataSet[0];
	    	// 圖像
	    	CasePortraitFile casePortraitFile = (CasePortraitFile)caseDataSet[2];
	    	// 相關檔案
	    	List<CaseAttachment> caseAttachmentList = (List<CaseAttachment>)caseDataSet[3];
	    	
	    	// 進階欄位
	    	@SuppressWarnings("unchecked")
			List<CaseExtraData> caseExtraDataList = (List<CaseExtraData>)caseDataSet[1];		
			// 進階欄位定義
	    	List<CaseExtraDefinition> caseExtraDefinitionList = caseExtraDefinitionManagementService.queryCaseExtraDefinitionList(projectUuid);
	    	
	    	// 以欄位定義為基準, 進行彙整
	    	if (caseBasicData != null) {
	        	mav.addObject("caseBasicData", caseBasicData);
	        	
	        	if (casePortraitFile != null) {
	            	mav.addObject("casePortraitFile", casePortraitFile);        		
	        	}        	
	        	
	        	if (caseAttachmentList != null) {
	            	mav.addObject("caseAttachmentList", caseAttachmentList);        		
	        	}
	        	
	        	Map<String, CaseExtraData> caseExtraDataIndexes = new HashMap<String, CaseExtraData>();
	        	if (caseExtraDataList != null) {
	        		for (CaseExtraData caseExtraData : caseExtraDataList) {
	        			caseExtraDataIndexes.put(caseExtraData.getDefinitionUuid(), caseExtraData);
	        		}
	        	}
	        	
	        	List<CaseExtraDataFormBean> caseExtraItemList = new ArrayList<CaseExtraDataFormBean>();
	        	if (caseExtraDefinitionList != null) {
	        		for (CaseExtraDefinition caseExtraDefinition : caseExtraDefinitionList) {
	        			
	        			CaseExtraData caseExtraData = caseExtraDataIndexes.get(caseExtraDefinition.getUuid());
	        			
	        			CaseExtraDataFormBean caseExtraDataFormBean = new CaseExtraDataFormBean();
	        			caseExtraDataFormBean.setDefinition(caseExtraDefinition);
	        			caseExtraDataFormBean.setData(caseExtraData);

	        			// 移除已有對應資料
	        			if (caseExtraData != null) {
	        				caseExtraDataIndexes.remove(caseExtraDefinition.getUuid());
	        			}

	        			caseExtraItemList.add(caseExtraDataFormBean);
	        		}
	        	}        	
	        	
	        	// 將無對應欄位定義的資料加入
	        	if (caseExtraDataIndexes.size() > 0) {
	        		for (Entry<String, CaseExtraData> entry : caseExtraDataIndexes.entrySet()) {
	        			CaseExtraDataFormBean caseExtraDataFormBean = new CaseExtraDataFormBean();
	        			caseExtraDataFormBean.setData(entry.getValue());
	        			
	        			caseExtraItemList.add(caseExtraDataFormBean);
	        		}
	        	}
	        	
	        	mav.addObject("caseExtraItemList", caseExtraItemList);
	    	}
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
		caseBasicData.setUuid(formBean.getUuid());
		
    	if (StringUtils.equals(FUNCTION_NAME_UPDATE, formBean.getFunctionName())) {
    		// 更新資料
    		caseBasicData.setUpdator(user.getAccount());
    		
    		try {
    			caseBasicData = caseManagementService.updateCase(caseBasicData, formBean.getCaseExtraDataList());
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);	 
			} catch (DuplicatedDataException e) {
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
				jsonObject.put(JKEY_ERROR_MESSAGE, "個案編號已經存在");				
			} catch (NoDataFoundException e) {
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
				jsonObject.put(JKEY_ERROR_MESSAGE, e.getMessage());				
			} catch (Exception e) {
				e.printStackTrace();
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
	
    
    @RequestMapping(value = "/doUploadPortraitFile", method = RequestMethod.POST)
    public @ResponseBody
    void doUploadPortraitFile(@RequestParam(value="projectUuid", required=true) String projectUuid, 
    		                  @RequestParam(value="uuid", required=true) String uuid, 
    		                  @RequestParam(value="portraitFile",required=true) MultipartFile portraitFile, 
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
    	    	
    	CasePortraitFile oCasePortraitFile = caseManagementService.queryCasePortraitFile(uuid);    	
    	if (oCasePortraitFile == null) {
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
        	
            // 新增資料
        	CasePortraitFile casePortraitFile = new CasePortraitFile();
    		casePortraitFile.setUuid(portraitUuid);    	
        	casePortraitFile.setDataUuid(uuid);
        	casePortraitFile.setCreator(user.getAccount());
        	casePortraitFile.setExtName(extName);
        	
        	casePortraitFile = caseManagementService.createCasePortraitFile(casePortraitFile);    	
    	} else {
    		// 刪除已上傳檔案
        	File oUploadedFile = new File(storePath, 
        			                      StringUtils.isEmpty(oCasePortraitFile.getExtName()) ? oCasePortraitFile.getUuid() : oCasePortraitFile.getUuid().concat(".").concat(oCasePortraitFile.getExtName()));
        	if (oUploadedFile.exists()) {
        		oUploadedFile.delete();
        	}
    		
        	String nExtName = extractExtName(portraitFile.getOriginalFilename());        	
        	File nUploadedFile = new File(storePath, 
        			                      StringUtils.isEmpty(nExtName) ? oCasePortraitFile.getUuid() : oCasePortraitFile.getUuid().concat(".").concat(nExtName));
        	
        	OutputStream os = null;
            InputStream is = null;
        	
            try {
        		os = new FileOutputStream(nUploadedFile);
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

            // 更新
            oCasePortraitFile.setUpdator(user.getAccount());
            oCasePortraitFile.setExtName(nExtName);
        	
            oCasePortraitFile = caseManagementService.updateCasePortraitFile(oCasePortraitFile);     	
    	}    	
    }    
    
    @RequestMapping(value = "/doDeleteAttachment", method = RequestMethod.POST)
    public @ResponseBody
    void doDeleteAttachment(@RequestParam(value="projectUuid", required=true) String projectUuid,
    		                @RequestParam(value="dataUuid", required=true) String dataUuid,
    		                @RequestParam(value="attachmentUuid", required=true) String attachmentUuid,
    		                HttpSession session, 
    		                HttpServletResponse response) throws IOException {
    	JSONObject jsonObject = new JSONObject();
    	
		// 檢查是否已經登入系統
    	if (!StringUtils.isEmpty(checkAuthorization(session, FUNCTION_URL))) {    
    		responseNotLoginError(response);
    		return;
    	}    	
    	
    	File storePath = composeFilePath(projectUuid, FileFolderEnum.Case, dataUuid);
    	if (!storePath.exists()) {
    		// TODO: throw an exception
    	}
    	    	
    	CaseAttachment caseAttachment = caseManagementService.queryCaseAttachment(attachmentUuid);
    	// 刪除檔案
    	File oUploadedFile = new File(storePath, 
    			                      StringUtils.isEmpty(caseAttachment.getExtName()) ? caseAttachment.getUuid() : caseAttachment.getUuid().concat(".").concat(caseAttachment.getExtName()));
    	if (oUploadedFile.exists()) {
    		oUploadedFile.delete();
    	}
    	    	
    	try {
			caseManagementService.deleteCaseAttachment(attachmentUuid);
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
    
    @RequestMapping(value = "/doUpdateAttachment", method = RequestMethod.POST)
    public @ResponseBody
    void doUpdateAttachment(@RequestParam(value="projectUuid", required=true) String projectUuid, 
    		                @RequestParam(value="uuid", required=true) String uuid, 
    		                @RequestParam(value="attachmentUuid", required=false) String attachmentUuid,
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
    	
    	// 查詢原資料
    	CaseAttachment caseAttachment = caseManagementService.queryCaseAttachment(attachmentUuid);
    	if (caseAttachment == null) {
    		// TODO: throw an exception
    	}    	
    	    	    	
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
    	
    	// 刪除原檔案
    	File oUploadedFile = new File(storePath, 
                                      StringUtils.isEmpty(caseAttachment.getExtName()) ? attachmentUuid : attachmentUuid.concat(".").concat(caseAttachment.getExtName()));
    	if (oUploadedFile.exists()) {
    		oUploadedFile.delete();
    	}    	
    	
    	// 檔案名稱
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
    	
        caseAttachment.setFileName(extractFileName(attachmentFileNameUTF8));
        caseAttachment.setExtName(extName);
        caseAttachment.setDescription(attachmentDescUTF8);
    	caseAttachment.setUpdator(user.getAccount());
        caseAttachment = caseManagementService.updateCaseAttachment(caseAttachment);    	
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
    
    @RequestMapping(value = "/doUpdateAttachmentDescription", method = RequestMethod.POST)
    public @ResponseBody
    void doUpdateAttachmentDescription(@RequestParam(value="projectUuid", required=true) String projectUuid, 
    		                           @RequestParam(value="dataUuid", required=true) String dataUuid, 
    		                           @RequestParam(value="attachmentUuid", required=false) String attachmentUuid,
    		                           @RequestParam(value="attachmentDesc", required=false) String attachmentDesc,
    		                           HttpSession session, 
    		                           HttpServletResponse response) throws IOException {

    	JSONObject jsonObject = new JSONObject();
    	
		// 檢查是否已經登入系統
    	if (!StringUtils.isEmpty(checkAuthorization(session, FUNCTION_URL))) {    
    		responseNotLoginError(response);
    		return;
    	}
    	
    	User user = SessionUtility.getUser(session);
    	
    	// 查詢原資料
    	CaseAttachment caseAttachment = caseManagementService.queryCaseAttachment(attachmentUuid);
    	if (caseAttachment == null) {
    		// TODO: throw an exception
    	}    	
    	
        caseAttachment.setDescription(attachmentDesc);        
    	caseAttachment.setUpdator(user.getAccount());
        caseAttachment = caseManagementService.updateCaseAttachment(caseAttachment);    	

        jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
        
    	response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(jsonObject.toString());    
    }    
    
}
