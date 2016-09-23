package org.iii.see.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.iii.see.constant.UsibilityTestFormManagementConstant;
import org.iii.see.domain.UsibilityTestFormTemplate;
import org.iii.see.service.UsibilityTestFormManagementService;
import org.iii.see.utility.SystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/UsibilityTestFormFile")
public class UsibilityTestFormFileReferController extends BaseController {

	@Autowired
	private UsibilityTestFormManagementService usibilityTestFormManagementService;
	
    @RequestMapping(value = "/formTemplate", method = RequestMethod.GET)
    public @ResponseBody
    void getAttachment(@RequestParam(value="projectUuid", required=true) String projectUuid,
    		           @RequestParam(value="uuid", required=true) String uuid,
    		           HttpSession session,
    		           HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
    	
    	// TODO: 檢查是否已經登入系統, 是否為專案成員    	    	
    	if (StringUtils.isBlank(projectUuid) || StringUtils.isBlank(uuid)) {
    		// TODO: ERROR Handling
    		return;
    	}
    	
    	UsibilityTestFormTemplate template = usibilityTestFormManagementService.queryUsibilityTestFormTemplate(uuid);
    	if (template == null) {
    		// TODO: ERROR Handling
    		return;
    	}

    	// 第一層目錄: Project UUID
    	File storePath = new File(SystemProperties.getFileDirectory(), projectUuid);    	
    	// 第二層目錄 
    	storePath = new File(storePath, UsibilityTestFormManagementConstant.FORM_TEMPLATE_FILE_DIR);
    	if (!storePath.exists()) {
    		// TODO: ERROR Handling
    		return;    		
    	}

    	String storedFileName = composeFileName(template.getUuid(), template.getExtName());    
    	String returnFileName = composeFileName(template.getFileName(), template.getExtName());

    	File templateFile = new File(storePath, storedFileName);
    	if (!templateFile.exists()) {
    		// TODO: ERROR Handling
    		return;    		
    	}
    	
    	response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", request.getServletContext().getMimeType(storedFileName));
        response.setHeader("Content-Length", String.valueOf(templateFile.length()));
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + URLEncoder.encode(returnFileName, "UTF-8") + "");
        Files.copy(templateFile.toPath(), response.getOutputStream());    	    	
    }	
	
}
