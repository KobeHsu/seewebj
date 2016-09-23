<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value='/res/kartik-v-bootstrap-fileinput/css/fileinput.min.css' />" rel="stylesheet">		
	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/plugins/canvas-to-blob.min.js' />"></script>	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/fileinput.min.js' />"></script>	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/fileinput_locale_zh-TW.js' />"></script>	
	
<div class="panel-default margin-top-lg">
		<form role="form" class="form-horizontal" id="editwPersonaTab2Form" name="editwPersonaTab2Form">
			<div class="form-group">
				<div class="col-md-6">
<c:choose>
	<c:when test="${not empty personaPortraitFile}">
				<img class="img-responsive" src="<c:url value='/PersonaFile/portrait?dataUuid=${personaBasicData.uuid}&uuid=${personaPortraitFile.uuid}&fileName=${personaPortraitFile.uuid.concat(".").concat(personaPortraitFile.extName)}' />" />
	</c:when>	
</c:choose>		
					
				</div>
		    <div class="col-md-6">
		    	<input type="file" class="file" id="portraitFile" name="portraitFile" />
		    </div>
			</div>		
		</form>		
</div>