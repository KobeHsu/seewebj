<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<c:url value='/res/js/bootstrap-confirmation.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/res/js/seeweb/datamanagement/CaseList.js' />"></script>

<input type="hidden" id="projectUuid" name="projectUuid" value="${projectUuid}" />

<div class="margin-top-lg">

<div id="messageBar"></div>
	
<c:if test="${not empty caseList}">
<c:forEach var="caseData" items="${caseList}" varStatus="caseLoop">			  	
	
<div class="callout callout-case">
<div class="row">

		<div class="col-md-3 col-sm-3 hidden-xs">

<c:choose>
	<c:when test="${not empty caseData.portraitFileName}">
			<img class="img-responsive" src="<c:url value='/CaseFile/portrait?dataUuid=${caseData.uuid}&uuid=${caseData.portraitFileUuid}&fileName=${caseData.portraitFileName}' />" />
	</c:when>
	<c:when test="${caseData.sex eq 'M'}">			
			<img class="img-responsive" src="<c:url value='/res/images/male.png' />" />
	</c:when>
	<c:otherwise>
			<img class="img-responsive" src="<c:url value='/res/images/female.png' />" />
	</c:otherwise>
</c:choose>
		</div>
		
		<div class="col-md-4 col-sm-4 right-border">
			<div class="list-order ">
				<h4>個案編號: ${caseData.caseNo} </h4>
				<h4>　　姓名: ${caseData.realname} </h4>			
			</div>
			<div class="pull-right margin-top-lg">
				<button type="button" class="btn btn-round btn-sm btn-warning" id="detailBtn${caseData.uuid}" name="detailBtn${caseData.uuid}" onclick="javascript:location.href='ViewCase?projectUuid=${projectUuid}&dataUuid=${caseData.uuid}'"><i class="fa fa-list-ul"></i></button>
				<button type="button" class="btn btn-round btn-sm btn-warning" id="modifyBtn${caseData.uuid}" name="modifyBtn${caseData.uuid}" onclick="javascript:location.href='EditCase?projectUuid=${projectUuid}&dataUuid=${caseData.uuid}'"><i class="fa fa-pencil"></i></button>
				<button type="button" class="btn btn-round btn-sm btn-warning btn-delete" id="deleteBtn${caseData.uuid}" name="deleteBtn${caseData.uuid}" 
					                    data-container="body"
					                    data-toggle="confirmation" data-placement="bottom" data-title="確認刪除個案資料"  
					                    data-btn-ok-label="是" data-btn-cancel-label="否">
					<i class="fa fa-times"></i>
				</button>
			</div>
			
		</div>

		<div class="col-md-5 col-sm-5">
<c:choose>
	<c:when test="${caseData.sex eq 'M'}">			
			<i class="fa fa-transgender fa-width-sm"></i> 性別：男<br/>
	</c:when>
	<c:otherwise>
			<i class="fa fa-transgender fa-width-sm"></i> 性別：女<br/>
	</c:otherwise>
</c:choose>				
			
			<i class="fa fa-paw fa-width-sm"></i> 年齡：${caseData.age == 0 ? '' : caseData.age}<br/>
			<i class="fa fa-phone fa-width-sm"></i> 公司電話：${caseData.businessPhone}<br/>
			<i class="fa fa-mobile fa-width-sm"></i> 行動電話：${caseData.mobilePhone}<br/>
			<i class="fa fa-clock-o fa-width-sm"></i> 建立時間：${caseData.createTime}<br/>
			
		</div>	

	
</div>	
</div>

</c:forEach>
</c:if>
	
	
</div>