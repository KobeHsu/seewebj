<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="<c:url value='/res/js/bootstrap-confirmation.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/res/js/seeweb/PersonaList.js' />"></script>

<c:set var="newLine" value="<%= \"\n\" %>" />
	
<input type="hidden" id="projectUuid" name="projectUuid" value="${projectUuid}" />

<div class="margin-top-lg">
	
<c:if test="${not empty announcementList}">
<c:forEach var="announcementData" items="${announcementList}" varStatus="caseLoop">			  	
	
<div class="callout callout-announcement">
<div class="row">
		
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="list-order">
				<h4>${announcementData.title} </h4>
				<hr />
				<h5>${fn:replace(fn:escapeXml(announcementData.summary), newLine, "<br/>")}</h5>				
			</div>
			<div class="pull-right">
				<button type="button" class="btn btn-round btn-sm btn-warning" id="detailBtn${announcementData.uuid}" name="detailBtn${announcementData.uuid}" onclick="javascript:location.href='ViewProjectAnnouncement?projectUuid=${projectUuid}&dataUuid=${announcementData.uuid}'"><i class="fa fa-list-ul"></i></button>
				<button type="button" class="btn btn-round btn-sm btn-warning" id="modifyBtn${announcementData.uuid}" name="modifyBtn${announcementData.uuid}" onclick="javascript:location.href='EditProjectAnnouncement?projectUuid=${projectUuid}&dataUuid=${announcementData.uuid}'"><i class="fa fa-pencil"></i></button>
				<button type="button" class="btn btn-round btn-sm btn-warning btn-delete" id="deleteBtn${announcementData.uuid}" name="deleteBtn${announcementData.uuid}" 
					                    data-container="body"
					                    data-toggle="confirmation" data-placement="bottom" data-title="確認刪除公告"  
					                    data-btn-ok-label="是" data-btn-cancel-label="否">
					<i class="fa fa-times"></i>
				</button>						
			</div>
		</div>	
</div>	
</div>

</c:forEach>
</c:if>
	
	
</div>