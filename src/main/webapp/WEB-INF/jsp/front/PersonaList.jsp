<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="<c:url value='/res/js/jQuery.download.js' />"></script>
<script type="text/javascript" src="<c:url value='/res/js/bootstrap-confirmation.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/res/js/seeweb/PersonaList.js' />"></script>

<c:set var="newLine" value="<%= \"\n\" %>" />
	
<input type="hidden" id="projectUuid" name="projectUuid" value="${projectUuid}" />

<div class="margin-top-lg">
	
<c:if test="${not empty personaList}">
<c:forEach var="personaData" items="${personaList}" varStatus="caseLoop">			  	
	
<div class="callout callout-persona">
<div class="row">
		
		<div class="col-md-6 col-sm-6 col-xs-6 right-border">
			<div class="list-order">
				<h4><strong>編號</strong>: ${personaData.personaNo} </h4>
				<h4><strong>姓名</strong>: ${personaData.realname} </h4>			
				<h4><strong>類型</strong>: ${fn:replace(fn:escapeXml(personaData.category), newLine, "<br/>")}</h4>		
				<hr />
				<h5><strong>背景及態度</strong>: ${fn:replace(fn:escapeXml(personaData.background), newLine, "<br/>")}</h5>
				<h5><strong>行為特質</strong>: ${fn:replace(fn:escapeXml(personaData.behavior), newLine, "<br/>")}</h5>
					
			</div>
			<div class="pull-right margin-top-lg"">
				<button type="button" class="btn btn-round btn-sm btn-warning" id="exportBtn${personaData.uuid}" name="exportBtn${personaData.uuid}" onclick="javascript:exportPersona('${projectUuid}', '${personaData.uuid}')"><i class="fa fa-file-pdf-o"></i></button>
				<button type="button" class="btn btn-round btn-sm btn-warning" id="modifyBtn${personaData.uuid}" name="modifyBtn${personaData.uuid}" onclick="javascript:location.href='EditPersona?projectUuid=${projectUuid}&dataUuid=${personaData.uuid}'"><i class="fa fa-pencil"></i></button>
				<button type="button" class="btn btn-round btn-sm btn-warning btn-delete" id="deleteBtn${personaData.uuid}" name="deleteBtn${personaData.uuid}" 
					                    data-container="body"
					                    data-toggle="confirmation" data-placement="bottom" data-title="確認刪除人物角色原型資料"  
					                    data-btn-ok-label="是" data-btn-cancel-label="否">
					<i class="fa fa-times"></i>
				</button>
			</div>
			
		</div>

		<div class="col-md-6 col-sm-6 col-xs-6">

<c:choose>
	<c:when test="${not empty personaData.portraitFileName}">
			<img class="img-responsive" src="<c:url value='/PersonaFile/portrait?dataUuid=${personaData.uuid}&uuid=${personaData.portraitFileUuid}&fileName=${personaData.portraitFileName}' />" />
	</c:when>
	<c:otherwise>
			<img class="img-responsive" src="<c:url value='/res/images/male.png' />" />
	</c:otherwise>
</c:choose>
			<h4><strong>欲達成目標</strong>:</h4>
			<h5>${fn:replace(fn:escapeXml(personaData.target), newLine, "<br/>")}</h5>
		</div>
	
</div>	
</div>

</c:forEach>
</c:if>
	
	
</div>