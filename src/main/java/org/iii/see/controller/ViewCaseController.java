package org.iii.see.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.iii.see.domain.CaseAttachment;
import org.iii.see.domain.CaseBasicData;
import org.iii.see.domain.CaseExtraData;
import org.iii.see.domain.CaseExtraDefinition;
import org.iii.see.domain.CasePortraitFile;
import org.iii.see.form.datamanagement.CaseExtraDataFormBean;
import org.iii.see.service.CaseExtraDefinitionManagementService;
import org.iii.see.service.CaseManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ViewCase")
public class ViewCaseController extends BaseController {

	@Autowired
	private CaseManagementService caseManagementService;

	@Autowired
	private CaseExtraDefinitionManagementService caseExtraDefinitionManagementService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam(value="projectUuid", required=true) String projectUuid,
			                    @RequestParam(value="dataUuid", required=true) String dataUuid,
			                    HttpSession session) {
		ModelAndView mav = new ModelAndView("/front/ViewCase");
		
		// TODO: 檢查是否已經登入系統, 是否為專案成員

		mav.addObject("projectUuid", projectUuid);
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
    		
    	} else {
    		// TODO: 轉至錯誤畫面
    	}
    	        	
		return mav;
	}	
	

}
