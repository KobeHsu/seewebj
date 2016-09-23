package org.iii.see.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.iii.see.enumeration.AccountStatusEnum;
import org.iii.see.enumeration.AnnouncementSerialNoEnum;
import org.iii.see.enumeration.AnnouncementStatusEnum;
import org.iii.see.enumeration.BuiltInCaseExtraDefinitionTypeEnum;
import org.iii.see.enumeration.ForumStatusEnum;
import org.iii.see.enumeration.OnlineIssueStatusEnum;
import org.iii.see.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Common")
public class CommonController extends BaseController {
	
	@Autowired
	private ProjectService projectService;	

	@RequestMapping(value = "/getCaseExtraDefinitionTypeList", method = RequestMethod.POST)
	public @ResponseBody
	void getCaseExtraDefinitionTypeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		JSONArray jsonArray = new JSONArray();
		BuiltInCaseExtraDefinitionTypeEnum[] enumerations = BuiltInCaseExtraDefinitionTypeEnum.values();
		for (BuiltInCaseExtraDefinitionTypeEnum enumeration : enumerations) {
			Map<String, String> item = new HashMap<String, String>();
			
			item.put("code", enumeration.getCode());
			item.put("desc", enumeration.getDesc());
            jsonArray.add(item);
		}
		
		response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(jsonArray.toString());
	}

	@RequestMapping(value = "/getForumStatus", method = RequestMethod.POST)
	public @ResponseBody
	void getForumStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		JSONArray jsonArray = new JSONArray();
		ForumStatusEnum[] enumerations = ForumStatusEnum.values();
		for (ForumStatusEnum enumeration : enumerations) {
			Map<String, String> item = new HashMap<String, String>();
			
			item.put("code", enumeration.getCode());
			item.put("desc", enumeration.getDesc());
            jsonArray.add(item);
		}
		
		response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(jsonArray.toString());
	}

	@RequestMapping(value = "/getAnnouncementStatus", method = RequestMethod.POST)
	public @ResponseBody
	void getAnnouncementStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		JSONArray jsonArray = new JSONArray();
		AnnouncementStatusEnum[] enumerations = AnnouncementStatusEnum.values();
		for (AnnouncementStatusEnum enumeration : enumerations) {
			Map<String, String> item = new HashMap<String, String>();
			
			item.put("code", enumeration.getCode());
			item.put("desc", enumeration.getDesc());
            jsonArray.add(item);
		}
		
		response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(jsonArray.toString());
	}
	
	@RequestMapping(value = "/getAccountStatus", method = RequestMethod.POST)
	public @ResponseBody
	void getAccountStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		JSONArray jsonArray = new JSONArray();
		AccountStatusEnum[] enumerations = AccountStatusEnum.values();
		for (AccountStatusEnum enumeration : enumerations) {
			Map<String, String> item = new HashMap<String, String>();
			
			item.put("code", enumeration.getCode());
			item.put("desc", enumeration.getDesc());
            jsonArray.add(item);
		}
		
		response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(jsonArray.toString());
	}

	@RequestMapping(value = "/getOnlineIssueStatus", method = RequestMethod.POST)
	public @ResponseBody
	void getOnlineIssueStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		JSONArray jsonArray = new JSONArray();
		OnlineIssueStatusEnum[] enumerations = OnlineIssueStatusEnum.values();
		for (OnlineIssueStatusEnum enumeration : enumerations) {
			Map<String, String> item = new HashMap<String, String>();
			
			item.put("code", enumeration.getCode());
			item.put("desc", enumeration.getDesc());
            jsonArray.add(item);
		}
		
		response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(jsonArray.toString());
	}

	@RequestMapping(value = "/getAnnouncementSerialNo", method = RequestMethod.POST)
	public @ResponseBody
	void getAnnouncementSerialNo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		JSONArray jsonArray = new JSONArray();
		AnnouncementSerialNoEnum[] enumerations = AnnouncementSerialNoEnum.values();
		for (AnnouncementSerialNoEnum enumeration : enumerations) {
			Map<String, String> item = new HashMap<String, String>();
			
			item.put("code", enumeration.getCode());
			item.put("desc", enumeration.getDesc());
            jsonArray.add(item);
		}
		
		response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(jsonArray.toString());
	}
	
}
