<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value='/res/kartik-v-bootstrap-fileinput/css/fileinput.min.css' />" rel="stylesheet">		
	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/plugins/canvas-to-blob.min.js' />"></script>	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/fileinput.min.js' />"></script>	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/fileinput_locale_zh-TW.js' />"></script>	
	
<div class="panel-default margin-top-lg">
		<form role="form" class="form-horizontal" id="editCaseTab3Form" name="editCaseTab3Form">
			<div class="form-group">
				<div class="col-md-6">
<c:choose>
	<c:when test="${not empty casePortraitFile}">
				<img class="img-responsive" src="<c:url value='/CaseFile/portrait?dataUuid=${caseBasicData.uuid}&uuid=${casePortraitFile.uuid}&fileName=${casePortraitFile.uuid.concat(".").concat(casePortraitFile.extName)}' />" />
	</c:when>	
	<c:when test="${caseBasicData.sex eq 'M'}">			
				<img class="img-responsive" src="<c:url value='/res/images/male.png' />" />
	</c:when>
	<c:otherwise>
				<img class="img-responsive" src="<c:url value='/res/images/female.png' />" />
	</c:otherwise>
</c:choose>		
					
				</div>
		    <div class="col-md-6">
		    	<input type="file" class="file" id="portraitFile" name="portraitFile" />
		    </div>
			</div>		
		</form>		
</div>