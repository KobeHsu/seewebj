package org.iii.see.controller.datamanagement;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.iii.see.controller.BaseController;
import org.iii.see.domain.CaseAttachment;
import org.iii.see.domain.CaseBasicData;
import org.iii.see.enumeration.FileFolderEnum;
import org.iii.see.service.CaseManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/CaseFile")
public class CaseFileReferController extends BaseController {

	@Autowired
	private CaseManagementService caseManagementService;
	
    @RequestMapping(value = "/portrait", method = RequestMethod.GET)
    public @ResponseBody
    void getPortraitFile(@RequestParam(value="dataUuid", required=true) String dataUuid,
    		             @RequestParam(value="uuid", required=true) String uuid,
    		             @RequestParam(value="fileName", required=true) String fileName,
    		             HttpSession session,
    		             HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
    	
    	// TODO: 檢查是否已經登入系統, 是否為專案成員
    	if (StringUtils.isBlank(dataUuid) || StringUtils.isBlank(uuid)) {
    		// TODO: ERROR Handling
    		return;
    	}
    	
    	CaseBasicData caseBasicData = caseManagementService.queryCaseBasicData(dataUuid);
    	if (caseBasicData == null) {
    		// TODO: ERROR Handling
    		return;
    	}

    	String projectUuid = caseBasicData.getProjectUuid();
    	
    	File storePath = composeFilePath(projectUuid, FileFolderEnum.Case, dataUuid);    	
    	if (!storePath.exists()) {
    		// TODO: ERROR Handling
    		return;    		
    	}

    	File portraitFile = new File(storePath, fileName);
    	if (!portraitFile.exists()) {
    		// TODO: ERROR Handling
    		return;    		
    	}
    	
        response.setHeader("Content-Type", request.getServletContext().getMimeType(fileName));
        response.setHeader("Content-Length", String.valueOf(portraitFile.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + portraitFile.getName() + "\"");
        Files.copy(portraitFile.toPath(), response.getOutputStream());    	    	
    }	

    @RequestMapping(value = "/attachment", method = RequestMethod.GET)
    public @ResponseBody
    void getAttachment(@RequestParam(value="dataUuid", required=true) String dataUuid,
    		           @RequestParam(value="uuid", required=true) String uuid,
    		           HttpSession session,
    		           HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
    	
    	// TODO: 檢查是否已經登入系統, 是否為專案成員    	    	
    	if (StringUtils.isBlank(dataUuid) || StringUtils.isBlank(uuid)) {
    		// TODO: ERROR Handling
    		return;
    	}
    	
    	CaseBasicData caseBasicData = caseManagementService.queryCaseBasicData(dataUuid);
    	if (caseBasicData == null) {
    		// TODO: ERROR Handling
    		return;
    	}

    	String projectUuid = caseBasicData.getProjectUuid();
    	
    	File storePath = composeFilePath(projectUuid, FileFolderEnum.Case, dataUuid);
    	if (!storePath.exists()) {
    		// TODO: ERROR Handling
    		return;    		
    	}

    	CaseAttachment caseAttachment = caseManagementService.queryCaseAttachment(uuid);
    	
    	if (!StringUtils.equals(dataUuid, caseAttachment.getDataUuid())) {
    		// TODO: ERROR Handling
    		return;
    	}
    	
    	String storedFileName = caseAttachment.getUuid() + 
    			               ((StringUtils.isEmpty(caseAttachment.getExtName())) ? "" : ".".concat(caseAttachment.getExtName()));
    	
    	String returnFileName = caseAttachment.getFileName() + 
	                            ((StringUtils.isEmpty(caseAttachment.getExtName())) ? "" : ".".concat(caseAttachment.getExtName()));

    	File attachmentFile = new File(storePath, storedFileName);
    	if (!attachmentFile.exists()) {
    		// TODO: ERROR Handling
    		return;    		
    	}
    	
    	response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", request.getServletContext().getMimeType(storedFileName));
        response.setHeader("Content-Length", String.valueOf(attachmentFile.length()));
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + URLEncoder.encode(returnFileName, "UTF-8") + "");
        Files.copy(attachmentFile.toPath(), response.getOutputStream());    	    	
    }	

}
