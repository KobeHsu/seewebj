package org.iii.see.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.iii.see.form.ForumDataFormBean;
import org.iii.see.service.ForumManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ForumList")
public class ForumListController {
	
	@Autowired
	private ForumManagementService forumManagementService;	

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam(value="projectUuid", required=true) String projectUuid,
			                    HttpSession session) {
		ModelAndView mav = new ModelAndView("/front/ForumList");
		
		// TODO: 檢查是否已經登入系統, 是否為專案成員
		
		mav.addObject("projectUuid", projectUuid);
		
		List<ForumDataFormBean> forumDataFormBeanList = new ArrayList<ForumDataFormBean>();
		
		List<Object[]> forumList = forumManagementService.queryForumList(projectUuid);
		for (Object[] forumFields : forumList) {
			ForumDataFormBean forumDataFormBean = new ForumDataFormBean();
			
			// 查詢結果欄位順序
		    // 0: uuid
		    // 1: projectUuid
		    // 2: title
		    // 3: summary
		    // 4: status
			forumDataFormBean.setUuid((String)forumFields[0]);
			forumDataFormBean.setProjectUuid((String)forumFields[1]);
			forumDataFormBean.setTitle((String)forumFields[2]);
			forumDataFormBean.setSummary((String)forumFields[3]);
			forumDataFormBean.setStatus((String)forumFields[4]);

			forumDataFormBeanList.add(forumDataFormBean);
		}
		
		mav.addObject("forumList", forumDataFormBeanList);
		
		return mav;
	}	
	
}
