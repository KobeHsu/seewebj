package org.iii.see.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.enumeration.FileFolderEnum;
import org.iii.see.enumeration.MemberAuditLogActionEnum;
import org.iii.see.service.AuthenticationService;
import org.iii.see.utility.SessionUtility;
import org.iii.see.utility.SystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.ModelAndView;

public class GenericController {
	
	protected final String URL_LOGIN = "/front/Login";
	
	protected final String JKEY_CONTENT_TYPE = "application/json;charset=utf-8";
	
	protected final String FUNCTION_NAME_INSERT = "INSERT";
	protected final String FUNCTION_NAME_UPDATE = "UPDATE";
	protected final String FUNCTION_NAME_DELETE = "DELETE";
	protected final String FUNCTION_NAME_QUERY = "QUERY";
	
	protected final String JKEY_FUNCTION_NAME = "functionName";
	protected final String JKEY_FUNCTION_STATUS = "functionStatus";
	protected final String JKEY_ERROR_MESSAGE = "errorMessage";
	protected final String JKEY_UUID = "uuid";
	
	protected final String JVALUE_FUNCTION_SUCCESS = "SUCCESS";
	protected final String JVALUE_FUNCTION_FAILED = "FAILED";
	
	@Autowired
	private AuthenticationService authenticationService;			

    protected String extractExtName(String originalFilename) {
    	String extName = StringUtils.EMPTY;
    	int dotIndex = originalFilename.lastIndexOf(".");
    	if (dotIndex >= 0 && dotIndex < originalFilename.length() - 1) {
    		extName = originalFilename.substring(dotIndex+1);
    	}
    	return extName;
    }

    protected String extractFileName(String originalFilename) {
    	String fileName = StringUtils.EMPTY;
    	int dotIndex = originalFilename.lastIndexOf(".");
    	if (dotIndex >= 0 && dotIndex < originalFilename.length() - 1) {
    		fileName = originalFilename.substring(0, dotIndex);
    	} else {
    		fileName = originalFilename;
    	}
    	return fileName;
    }
    
    protected String composeFileName(String fileName, String extName) {
    	return StringUtils.isEmpty(extName) ? fileName : fileName.concat(".").concat(extName);
    }
    	
	protected File composeFilePath(String projectUuid, FileFolderEnum fileFolderEnum, String dataUuid) {
		// 根目錄			
    	File storePath = new File(SystemProperties.getFileDirectory());	    	
    	// 第一層目錄: Project UUID
    	storePath = new File(storePath, projectUuid); 	    	
    	// 第二層目錄: 功能名稱
    	storePath = new File(storePath, fileFolderEnum.getCode()); 
    	// 第三層目錄: Data UUID
    	storePath = new File(storePath, dataUuid); 		
		
    	return storePath;
	}
    	
	protected ModelAndView getPage(HttpSession session, String url) {
    	
    	// 檢查是否登入
		if (!SessionUtility.isLogon(session)) {
    		return new ModelAndView(URL_LOGIN);
    	}

		// TODO: 檢查授權
		
    	return new ModelAndView(url);
	}
	
	protected void responseNotLoginError(HttpServletResponse response) throws IOException {
    	JSONObject jsonObject = new JSONObject();
		jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
		jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.AUTHENTICATION_NOT_LOGON.getCode());
    	response.setContentType(JKEY_CONTENT_TYPE);
		response.getWriter().write(jsonObject.toString());		
	}
	
	protected boolean isAuthorized(HttpSession session, String url, String functionName) {
		boolean isAuthorized = false;
		
    	// 檢查是否登入
		if (!SessionUtility.isLogon(session)) {
    		return isAuthorized;
    	}

		// TODO: 檢查授權
		
		return true;
	}
	
	protected void addAuditLog(String account, MemberAuditLogActionEnum action, HttpServletRequest request) {
		authenticationService.createMemberAuditLog(account, action.getCode(), null, extractRemoteAddress(request));		
	}

	protected void addAuditLog(String account, MemberAuditLogActionEnum action, String note, HttpServletRequest request) {
		authenticationService.createMemberAuditLog(account, action.getCode(), note, extractRemoteAddress(request));		
	}
	
    protected String extractRemoteAddress(HttpServletRequest request) {
    	String ipAddress = request.getHeader("X-FORWARDED-FOR");  
    	if (ipAddress == null) {  
    		ipAddress = request.getRemoteAddr();  
    	}
 	   return ipAddress;
    }	
    
     protected void generatePdfReport(HttpServletResponse response, 
    		                          String reportName, 
    		                          Map<String, Object> reportParameters, 
    		                          List<Map<String, Object>> reportDataItems, 
    		                          String pdfName) throws JRException, IOException {
    	 
 		response.setContentType("application/pdf");
 		response.addHeader("Content-Disposition", "attachment;filename=\"" + pdfName + "\"");
 		
 		reportParameters.put("SUBREPORT_DIR", "META-INF/jasperreports/");
 		
    	 
 		JRBeanCollectionDataSource reportDataSource = new JRBeanCollectionDataSource(reportDataItems);
    	
 		FileInputStream fis = new FileInputStream(ResourceUtils.getFile("classpath:META-INF/jasperreports/" + reportName + ".jasper"));
 		
 		JasperPrint print = JasperFillManager.fillReport(fis, reportParameters, reportDataSource);
 		
 		JRPdfExporter pdfExporter = new JRPdfExporter();

 		pdfExporter.setExporterInput(new SimpleExporterInput(print));
 		pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
 		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
 		pdfExporter.setConfiguration(configuration);

 		pdfExporter.exportReport();
    	 
     }
}
