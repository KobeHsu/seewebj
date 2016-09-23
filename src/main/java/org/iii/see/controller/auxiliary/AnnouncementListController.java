package org.iii.see.controller.auxiliary;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.iii.see.controller.BaseController;
import org.iii.see.form.AnnouncementDataFormBean;
import org.iii.see.service.AnnouncementManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/AnnouncementList")
public class AnnouncementListController extends BaseController {
	
	private String FUNCTION_URL = "/front/AnnouncementList";			
	
	@Autowired
	private AnnouncementManagementService announcementManagementService;			

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam(value="projectUuid", required=true) String projectUuid,
			                    HttpSession session) {
		
		ModelAndView mav = super.getPage(session, FUNCTION_URL, projectUuid);
		
		if (StringUtils.equals(FUNCTION_URL, mav.getViewName())) {
			List<AnnouncementDataFormBean> announcementDataFormBeanList = new ArrayList<AnnouncementDataFormBean>();
			
			List<Object[]> announcementList = announcementManagementService.queryAnnouncementList(projectUuid);
			for (Object[] announcementFields : announcementList) {
				AnnouncementDataFormBean announcementDataFormBean = new AnnouncementDataFormBean();
				
				// 查詢結果欄位順序
			    // 0: uuid
			    // 1: projectUuid
			    // 2: title
			    // 3: summary
			    // 4: status
				announcementDataFormBean.setUuid((String)announcementFields[0]);
				announcementDataFormBean.setProjectUuid((String)announcementFields[1]);
				announcementDataFormBean.setTitle((String)announcementFields[2]);
				announcementDataFormBean.setSummary((String)announcementFields[3]);
				announcementDataFormBean.setStatus((String)announcementFields[4]);

				announcementDataFormBeanList.add(announcementDataFormBean);
			}
			
			mav.addObject("announcementList", announcementDataFormBeanList);
		}
		
		return mav;		
	}	
	
}
