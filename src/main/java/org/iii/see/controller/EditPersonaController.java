package org.iii.see.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.iii.see.domain.PersonaBasicData;
import org.iii.see.domain.PersonaPortraitFile;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.enumeration.FileFolderEnum;
import org.iii.see.exception.NoDataFoundException;
import org.iii.see.form.FileUploadReturnBean;
import org.iii.see.form.PersonaCompositeDataFormBean;
import org.iii.see.service.PersonaManagementService;
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
@RequestMapping("/EditPersona")
public class EditPersonaController extends BaseController {
	
	@Autowired
	private PersonaManagementService personaManagementService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam(value="projectUuid", required=true) String projectUuid,
			                    @RequestParam(value="dataUuid", required=true) String dataUuid,
			                    HttpSession session) {
		ModelAndView mav = new ModelAndView("/front/EditPersona");
		
		// TODO: 檢查是否已經登入系統, 是否為專案成員
		
		mav.addObject("projectUuid", projectUuid);
		mav.addObject("dataUuid", dataUuid);
		
    	Object[] personaDataSet = personaManagementService.queryPersona(projectUuid, dataUuid);

    	// 基本欄位
    	PersonaBasicData personaBasicData = (PersonaBasicData)personaDataSet[0];
    	// 圖像
    	PersonaPortraitFile personaPortraitFile = (PersonaPortraitFile)personaDataSet[1];
    	
    	mav.addObject("personaBasicData", personaBasicData);
    	mav.addObject("personaPortraitFile", personaPortraitFile);
    	
		return mav;
	}	
	
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    public @ResponseBody
    void doSave(PersonaCompositeDataFormBean formBean,
    		    HttpSession session, HttpServletResponse response) throws IOException {
    	
    	JSONObject jsonObject = new JSONObject();
    	
		// TODO: 檢查是否已經登入系統
    	
    	// 檢查欄位值    	
    	if (StringUtils.isBlank(formBean.getProjectUuid()) || 
    			StringUtils.isBlank(formBean.getPersonaNo()) || 
    			StringUtils.isBlank(formBean.getRealname()) ||
    			StringUtils.isBlank(formBean.getBackground()) ||
    			StringUtils.isBlank(formBean.getCategory()) ||
    			StringUtils.isBlank(formBean.getBehavior()) ||
    			StringUtils.isBlank(formBean.getTarget())) {
    		    		
			jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
			jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DATA_INCORRECT.getDesc());
	    	
			response.setContentType(JKEY_CONTENT_TYPE);
			response.getWriter().write(jsonObject.toString());      	
			return;
    	}
    	
		PersonaBasicData personaBasicData = new PersonaBasicData();
		BeanUtils.copyProperties(formBean, personaBasicData);						
		
    	if (StringUtils.equals(FUNCTION_NAME_UPDATE, formBean.getFunctionName())) {
    		// 更新資料
    		personaBasicData.setUpdator(SessionUtility.getUserAccount(session));
    		
    		try {
    			personaBasicData = personaManagementService.updatePersona(personaBasicData);
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);	 
//			} catch (DuplicatedDataException e) {
//				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
//				jsonObject.put(JKEY_ERROR_MESSAGE, "個案編號已經存在");				
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
    		                  HttpServletResponse response) {
        	    	
		// TODO: 檢查是否已經登入系統, 是否為專案成員
    	User user = (User)session.getAttribute("user");
    	if (user == null) {
    		// TODO: throw an exception
    	}
    	
    	// 儲存檔案
    	File storePath = composeFilePath(projectUuid, FileFolderEnum.Persona, uuid);
    	if (!storePath.exists()) {
    		storePath.mkdirs();
    	}
    	    	    	
    	PersonaPortraitFile oPersonaPortraitFile = personaManagementService.queryPersonaPortraitFile(uuid);
    	
    	if (oPersonaPortraitFile == null) {
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
            PersonaPortraitFile personaPortraitFile = new PersonaPortraitFile();
            personaPortraitFile.setUuid(portraitUuid);    	
            personaPortraitFile.setDataUuid(uuid);
        	// TODO:
        	// casePortraitFile.setCreator(user.getUuid());
            personaPortraitFile.setExtName(extName);
        	
            personaPortraitFile = personaManagementService.createPersonaPortraitFile(personaPortraitFile);    	
    	} else {
    		// 刪除已上傳檔案
        	File oUploadedFile = new File(storePath, 
        			                      StringUtils.isEmpty(oPersonaPortraitFile.getExtName()) ? oPersonaPortraitFile.getUuid() : oPersonaPortraitFile.getUuid().concat(".").concat(oPersonaPortraitFile.getExtName()));
        	if (oUploadedFile.exists()) {
        		oUploadedFile.delete();
        	}
    		
        	String nExtName = extractExtName(portraitFile.getOriginalFilename());        	
        	File nUploadedFile = new File(storePath, 
        			                      StringUtils.isEmpty(nExtName) ? oPersonaPortraitFile.getUuid() : oPersonaPortraitFile.getUuid().concat(".").concat(nExtName));
        	
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
        	// TODO:
            // oCasePortraitFile.setUpdator(user.getUuid());
            oPersonaPortraitFile.setExtName(nExtName);
        	
            oPersonaPortraitFile = personaManagementService.updatePersonaPortraitFile(oPersonaPortraitFile);     	
    	}    	
    }    

}
