<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="<c:url value='/res/js/bootstrap-confirmation.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/res/js/seeweb/PersonaList.js' />"></script>

<c:set var="newLine" value="<%= \"\n\" %>" />
	
<input type="hidden" id="projectUuid" name="projectUuid" value="${projectUuid}" />
<input type="hidden" id="projectUuid" name="dataUuid" value="${forum.uuid}" />

<div class="margin-top-lg">

<div class="callout callout-forum">
<div class="row">
		
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="list-order">
				<h4>${forum.title} </h4>
				<hr />
				<h5>${fn:replace(fn:escapeXml(forum.summary), newLine, "<br/>")}</h5>				
			</div>
		</div>	
</div>	
</div>
	
<c:if test="${not empty forumThreadList}">
<c:forEach var="forumThreadData" items="${forumThreadList}" varStatus="caseLoop">			  	
	
<div class="callout callout-thread">
<div class="row">
		
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="list-order">
				<h4>${forumThreadData.title} </h4>
				<hr />
				<h5>${fn:replace(fn:escapeXml(forumThreadData.content), newLine, "<br/>")}</h5>				
			</div>
			<div class="pull-right">
				<button type="button" class="btn btn-round btn-sm btn-warning" id="detailBtn${forumThreadData.uuid}" name="detailBtn${forumThreadData.uuid}" onclick="javascript:location.href='ViewForumThread?projectUuid=${projectUuid}&dataUuid=${forumThreadData.uuid}'"><i class="fa fa-list-ul"></i></button>
				<button type="button" class="btn btn-round btn-sm btn-warning" id="modifyBtn${forumThreadData.uuid}" name="modifyBtn${forumThreadData.uuid}" onclick="javascript:location.href='EditForumhread?projectUuid=${projectUuid}&dataUuid=${forumThreadData.uuid}'"><i class="fa fa-pencil"></i></button>
				<button type="button" class="btn btn-round btn-sm btn-warning btn-delete" id="deleteBtn${forumThreadData.uuid}" name="deleteBtn${forumThreadData.uuid}" 
					                    data-container="body"
					                    data-toggle="confirmation" data-placement="bottom" data-title="確認刪除討論主題"  
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