package org.iii.see.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.iii.see.domain.PersonaBasicData;
import org.iii.see.enumeration.FileFolderEnum;
import org.iii.see.service.PersonaManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/PersonaFile")
public class PersonaFileReferController extends BaseController {

	@Autowired
	private PersonaManagementService personaManagementService;
	
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
    	
    	PersonaBasicData personaBasicData = personaManagementService.queryPersonaBasicData(dataUuid);
    	if (personaBasicData == null) {
    		// TODO: ERROR Handling
    		return;
    	}

    	String projectUuid = personaBasicData.getProjectUuid();
    	
    	File storePath = composeFilePath(projectUuid, FileFolderEnum.Persona, dataUuid);
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
	
}
