package org.iii.see.controller.administration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.iii.see.controller.BaseController;
import org.iii.see.domain.OnlineIssueFigure;
import org.iii.see.enumeration.FileFolderEnum;
import org.iii.see.service.OnlineIssueManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/OnlineIssueFile")
public class OnlineIssueFileReferController extends BaseController {
	
	@Autowired
	private OnlineIssueManagementService onlineIssueManagementService;	

    @RequestMapping(value = "/figure", method = RequestMethod.GET)
    public @ResponseBody
    void getFigureFile(@RequestParam(value="projectUuid", required=true) String projectUuid,
    		           @RequestParam(value="dataUuid", required=true) String dataUuid,
    		           @RequestParam(value="uuid", required=true) String uuid,
    		           HttpSession session,
    		           HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
    	
    	// TODO: 檢查是否已經登入系統
    	
    	OnlineIssueFigure figure = onlineIssueManagementService.queryOnlineIssueFigure(uuid);
    	if (figure == null) {
    		// TODO: ERROR Handling
    		return;
    	}

    	File storePath = composeFilePath(projectUuid, FileFolderEnum.OnlineIssue, dataUuid);
    	if (!storePath.exists()) {
    		// TODO: ERROR Handling
    		return;    		
    	}
    	
    	String fileName = StringUtils.isEmpty(figure.getExtName()) ? figure.getUuid() : figure.getUuid().concat(".").concat(figure.getExtName());

    	File figureFile = new File(storePath, fileName);
    	if (!figureFile.exists()) {
    		// TODO: ERROR Handling
    		return;    		
    	}
    	
        response.setHeader("Content-Type", request.getServletContext().getMimeType(fileName));
        response.setHeader("Content-Length", String.valueOf(figureFile.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + figureFile.getName() + "\"");
        Files.copy(figureFile.toPath(), response.getOutputStream());    	    	
    }	

    @RequestMapping(value = "/downloadFigure", method = RequestMethod.GET)
    public @ResponseBody
    void downloadFigureFile(@RequestParam(value="projectUuid", required=true) String projectUuid,
    		                @RequestParam(value="dataUuid", required=true) String dataUuid,
    		                @RequestParam(value="uuid", required=true) String uuid,
    		                HttpSession session,
    		                HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
    	
    	// TODO: 檢查是否已經登入系統
    	
    	OnlineIssueFigure figure = onlineIssueManagementService.queryOnlineIssueFigure(uuid);
    	if (figure == null) {
    		// TODO: ERROR Handling
    		return;
    	}

    	File storePath = composeFilePath(projectUuid, FileFolderEnum.OnlineIssue, dataUuid);
    	if (!storePath.exists()) {
    		// TODO: ERROR Handling
    		return;    		
    	}
    	
    	String fileName = StringUtils.isEmpty(figure.getExtName()) ? figure.getUuid() : figure.getUuid().concat(".").concat(figure.getExtName());

    	File figureFile = new File(storePath, fileName);
    	if (!figureFile.exists()) {
    		// TODO: ERROR Handling
    		return;    		
    	}

    	String downloadfileName = StringUtils.isEmpty(figure.getExtName()) ? figure.getFileName() : figure.getFileName().concat(".").concat(figure.getExtName());
    	
        response.setHeader("Content-Type", request.getServletContext().getMimeType(fileName));
        response.setHeader("Content-Length", String.valueOf(figureFile.length()));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + downloadfileName + "\"");
        Files.copy(figureFile.toPath(), response.getOutputStream());    	    	
    }	
    
}
