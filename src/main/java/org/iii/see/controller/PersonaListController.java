package org.iii.see.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.iii.see.domain.PersonaBasicData;
import org.iii.see.domain.PersonaPortraitFile;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.enumeration.FileFolderEnum;
import org.iii.see.exception.NoDataFoundException;
import org.iii.see.form.PersonaCompositeDataFormBean;
import org.iii.see.service.PersonaManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/PersonaList")
public class PersonaListController extends GenericController {
	
	private final String PROCESS_URL = "/front/PersonaList";
	
	@Autowired
	private PersonaManagementService personaManagementService;	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam(value="projectUuid", required=true) String projectUuid,
			                    HttpSession session) {
		
		ModelAndView mav = super.getPage(session, PROCESS_URL);
		if (StringUtils.equals(mav.getViewName(), PROCESS_URL)) {
			mav.addObject("projectUuid", projectUuid);
			
			List<PersonaCompositeDataFormBean> personaDataFormBeanList = new ArrayList<PersonaCompositeDataFormBean>();
			
			List<Object[]> personaList = personaManagementService.queryPersonaList(projectUuid);
			for (Object[] personaFields : personaList) {
				PersonaCompositeDataFormBean personaCompositeDataFormBean = new PersonaCompositeDataFormBean();
				
				// 查詢結果欄位順序
			    // 0: uuid
			    // 1: projectUuid
			    // 2: personaNo
			    // 3: realname
			    // 4: category
			    // 5: background
			    // 6: behavior
			    // 7: target
			    // 8: createTime					
				// 9: portrait uuid
				// 10: portrait file name
				personaCompositeDataFormBean.setUuid((String)personaFields[0]);
				personaCompositeDataFormBean.setProjectUuid((String)personaFields[1]);
				personaCompositeDataFormBean.setPersonaNo((String)personaFields[2]);
				personaCompositeDataFormBean.setRealname((String)personaFields[3]);
				personaCompositeDataFormBean.setCategory((String)personaFields[4]);
				personaCompositeDataFormBean.setBackground((String)personaFields[5]);
				personaCompositeDataFormBean.setBehavior((String)personaFields[6]);
				personaCompositeDataFormBean.setTarget((String)personaFields[7]);
				personaCompositeDataFormBean.setPortraitFileUuid((String)personaFields[9]);	
				personaCompositeDataFormBean.setPortraitFileName((String)personaFields[10]);			
				personaDataFormBeanList.add(personaCompositeDataFormBean);
			}
			
			mav.addObject("personaList", personaDataFormBeanList);
		}		
		
		return mav;
	}	
	
    @RequestMapping(value = "/doDelete", method = RequestMethod.POST)
    public @ResponseBody
    void doDelete(@RequestParam(value="projectUuid", required=true) String projectUuid,
    		      @RequestParam(value="dataUuid", required=true) String dataUuid,
    		      HttpSession session, 
    		      HttpServletResponse response) throws IOException {
    	JSONObject jsonObject = new JSONObject();
    	
		// TODO: 檢查是否已經登入系統, 是否為專案成員
    	
    	try {
    		personaManagementService.deletePersona(dataUuid);
			
			// 刪除檔案			
        	File storePath = composeFilePath(projectUuid, FileFolderEnum.Persona, dataUuid);
        	if (storePath.exists()) {
				FileUtils.deleteDirectory(storePath);
        	}
    		
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
	
    @RequestMapping(value = "/doExport", method = RequestMethod.POST)
    public @ResponseBody
    void doExport(@RequestParam(value="projectUuid", required=true) String projectUuid,
    		      @RequestParam(value="dataUuid", required=true) String dataUuid,
    		      HttpSession session, 
    		      HttpServletResponse response) throws IOException, JRException {
    	
		// TODO: 檢查是否已經登入系統, 是否為專案成員

    	
		PersonaBasicData persona = personaManagementService.queryPersonaBasicData(dataUuid);
		
		PersonaPortraitFile personaPortraitFile = personaManagementService.queryPersonaPortraitFile(dataUuid);
    	File storePath = composeFilePath(projectUuid, FileFolderEnum.Persona, dataUuid);
    	File portraitFile = new File(storePath, personaPortraitFile.getUuid().concat(".").concat(personaPortraitFile.getExtName()));
		
    	Map<String, Object> reportParameters = new HashMap<String, Object>();
		
    	List<Map<String, Object>> dataList1 = new ArrayList<Map<String, Object>>();
    	Map<String, Object> data1 = new HashMap<String, Object>();
    	data1.put("personaNo", persona.getPersonaNo());
    	data1.put("realname", persona.getRealname());
    	data1.put("category", persona.getCategory());
    	data1.put("background", persona.getBackground());
    	data1.put("behavior", persona.getBehavior());
    	dataList1.add(data1);
    	
    	List<Map<String, Object>> dataList2 = new ArrayList<Map<String, Object>>();
    	Map<String, Object> data2 = new HashMap<String, Object>();
    	data2.put("target", persona.getTarget());
    	data2.put("portraitFile", portraitFile);
    	dataList2.add(data2);
    			
		List<Map<String, Object>> personaDataList = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> personaData = new HashMap<String, Object>();
		personaData.put("SUBREPORT_DATA1", new JRBeanCollectionDataSource(dataList1));
		personaData.put("SUBREPORT_DATA2", new JRBeanCollectionDataSource(dataList2));
		
		personaDataList.add(personaData);
		
		generatePdfReport(response, "persona", reportParameters, personaDataList, "persona-".concat(persona.getPersonaNo()).concat(".pdf"));
    }	
    
}
