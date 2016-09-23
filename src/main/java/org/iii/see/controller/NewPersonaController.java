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
import org.iii.see.form.FileUploadReturnBean;
import org.iii.see.form.PersonaCompositeDataFormBean;
import org.iii.see.service.PersonaManagementService;
import org.iii.see.session.User;
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
@RequestMapping("/NewPersona")
public class NewPersonaController extends BaseController {
	
	@Autowired
	private PersonaManagementService personaManagementService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam(value="projectUuid", required=true) String projectUuid,
			                    HttpSession session) {
		ModelAndView mav = new ModelAndView("/front/NewPersona");
		
		// TODO: 檢查是否已經登入系統, 是否為專案成員
		
		mav.addObject("projectUuid", projectUuid);
				
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
    	if (StringUtils.equals(FUNCTION_NAME_INSERT, formBean.getFunctionName())) {
    		// 新增資料
//    		personaBasicData.setCreator(SessionUtils.getUserUuid(session));
    		
    		try {
    			personaBasicData = personaManagementService.createPersona(personaBasicData);
				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
				jsonObject.put(JKEY_UUID, personaBasicData.getUuid());	 
//			} catch (DuplicatedDataException e) {
//				jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
//				jsonObject.put(JKEY_ERROR_MESSAGE, "個案編號已經存在");				
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
    		                  HttpServletResponse response) {
        	    	
		// TODO: 檢查是否已經登入系統, 是否為專案成員
    	User user = (User)session.getAttribute("user");
    	if (user == null) {
    		// TODO: throw an exception
    	}
    	
    	File storePath = composeFilePath(projectUuid, FileFolderEnum.Persona, uuid);
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
    	
        PersonaPortraitFile personaPortraitFile = new PersonaPortraitFile();
        personaPortraitFile.setUuid(portraitUuid);    	
        personaPortraitFile.setDataUuid(uuid);
    	// TODO:
    	// casePortraitFile.setCreator(user.getUuid());
        personaPortraitFile.setExtName(extName);
    	
        personaPortraitFile = personaManagementService.createPersonaPortraitFile(personaPortraitFile);    	
    }    
	

}
