package org.iii.see.controller.datamanagement;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.iii.see.controller.BaseController;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.enumeration.FileFolderEnum;
import org.iii.see.exception.NoDataFoundException;
import org.iii.see.form.datamanagement.CaseSimpleDataFormBean;
import org.iii.see.service.CaseManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/CaseList")
public class CaseListController extends BaseController {
	
	private String FUNCTION_URL = "/front/CaseList";
	
	@Autowired
	private CaseManagementService caseManagementService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam(value="projectUuid", required=true) String projectUuid,
			                    HttpSession session) {
				
		ModelAndView mav = super.getPage(session, FUNCTION_URL, projectUuid);		
		if (StringUtils.equals(FUNCTION_URL, mav.getViewName())) {
			List<CaseSimpleDataFormBean> caseSimpleDataFormBeanList = new ArrayList<CaseSimpleDataFormBean>();
			
			List<Object[]> caseList = caseManagementService.queryCaseList(projectUuid);
			for (Object[] caseFields : caseList) {
				CaseSimpleDataFormBean caseSimpleDataFormBean = new CaseSimpleDataFormBean();
				
				// 查詢結果欄位順序
			    // 0: uuid
			    // 1: projectUuid
			    // 2: caseNo
			    // 3: realname
			    // 4: sex
			    // 5: age
			    // 6: businessPhone
			    // 7: mobilePhone
			    // 8: createTime
				// 9: portrait uuid
				// 10: portrait file name
				caseSimpleDataFormBean.setUuid((String)caseFields[0]);
				caseSimpleDataFormBean.setProjectUuid((String)caseFields[1]);
				caseSimpleDataFormBean.setCaseNo((String)caseFields[2]);
				caseSimpleDataFormBean.setRealname((String)caseFields[3]);
				caseSimpleDataFormBean.setSex((String)caseFields[4]);
				caseSimpleDataFormBean.setAge((short)caseFields[5]);
				caseSimpleDataFormBean.setBusinessPhone((String)caseFields[6]);
				caseSimpleDataFormBean.setMobilePhone((String)caseFields[7]);
				caseSimpleDataFormBean.setCreateTime((Timestamp)caseFields[8]);
				caseSimpleDataFormBean.setPortraitFileUuid((String)caseFields[9]);	
				caseSimpleDataFormBean.setPortraitFileName((String)caseFields[10]);			
				caseSimpleDataFormBeanList.add(caseSimpleDataFormBean);
			}
			
			mav.addObject("caseList", caseSimpleDataFormBeanList);
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
    	
		// 檢查是否已經登入系統
    	if (!StringUtils.isEmpty(checkAuthorization(session, FUNCTION_URL))) {    
    		responseNotLoginError(response);
    		return;
    	}    	
    	
    	try {
			caseManagementService.deleteCase(dataUuid);
			
	    	File storePath = composeFilePath(projectUuid, FileFolderEnum.Case, dataUuid);
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

}
