package org.iii.see.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.iii.see.domain.Forum;
import org.iii.see.domain.ForumThread;
import org.iii.see.form.ForumDataFormBean;
import org.iii.see.form.ForumThreadDataFormBean;
import org.iii.see.service.ForumManagementService;
import org.iii.see.utility.DateTimeUtility;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ViewForum")
public class ViewForumController extends BaseController {
	
	@Autowired
	private ForumManagementService forumManagementService;		
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam(value="projectUuid", required=true) String projectUuid,
			                    @RequestParam(value="dataUuid", required=true) String dataUuid,
			                    HttpSession session) {
		ModelAndView mav = new ModelAndView("/front/ViewForum");
		
		// TODO: 檢查是否已經登入系統, 是否為專案成員
		
		mav.addObject("projectUuid", projectUuid);
		mav.addObject("dataUuid", dataUuid);
		
		Forum forum = forumManagementService.queryForum(dataUuid);
		
		ForumDataFormBean forumDataFormBean = new ForumDataFormBean();
		BeanUtils.copyProperties(forum, forumDataFormBean);	
		mav.addObject("forum", forumDataFormBean);
		
    	String[] ignoreProperties = {"createTime", "updateTime"};
    	
    	List<ForumThreadDataFormBean> forumThreadDataFormBeanList = new ArrayList<ForumThreadDataFormBean>();
    			
		List<ForumThread> forumThreadList = forumManagementService.queryForumThreadList(dataUuid);
		for (ForumThread forumThread : forumThreadList) {
			ForumThreadDataFormBean forumThreadDataFormBean = new ForumThreadDataFormBean();
			
			BeanUtils.copyProperties(forumThread, forumThreadDataFormBean, ignoreProperties);
			
			if (forumThread.getCreateTime() != null) {
				forumThreadDataFormBean.setCreateTime(DateTimeUtility.convertTimestampToDateTimeString(forumThread.getCreateTime()));				
			}

			if (forumThread.getUpdateTime() != null) {
				forumThreadDataFormBean.setUpdateTime(DateTimeUtility.convertTimestampToDateTimeString(forumThread.getUpdateTime()));				
			}
			
			forumThreadDataFormBeanList.add(forumThreadDataFormBean);
		}
		
		mav.addObject("forumThreadList", forumThreadDataFormBeanList);
				
		return mav;
	}	
	

}
