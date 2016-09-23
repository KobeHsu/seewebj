<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value='/res/kartik-v-bootstrap-fileinput/css/fileinput.min.css' />" rel="stylesheet">		
	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/plugins/canvas-to-blob.min.js' />"></script>	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/fileinput.min.js' />"></script>	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/fileinput_locale_zh-TW.js' />"></script>	
<script src="<c:url value='/res/js/bootstrap-confirmation.min.js' />"></script>
		
<div class="panel-default margin-top-lg">

<div class="row margin-bottom-lg">			
	<div class="col-md-1 col-sm-1 col-xs-1 text-right">
		<button type="button" id="newAttachmentBtn" name="newAttachmentBtn" class="btn btn-success" onclick="addAttachment()"><i class="fa fa-plus"></i></button>
	</div>	
</div>

<c:forEach var="caseAttachment" items="${caseAttachmentList}" varStatus="caseAttachmentLoop">
<div class="row margin-bottom-lg">
	<div class="col-md-1 col-sm-1 col-xs-1 text-right">
		<button type="button" id="removeAttachmentBtn${caseAttachment.serialNo}" name="removeAttachmentBtn${caseAttachment.serialNo}" class="btn btn-default btn-delete"
						data-container="body"
						data-toggle="confirmation" data-placement="bottom" data-title="確認刪除已上傳檔案"
						data-btn-ok-label="是" data-btn-cancel-label="否">  
			<i class="fa fa-times"></i>
		</button>		
	</div>
	<div class="col-md-11 col-sm-11 col-xs-11">
		<input type="hidden" id="attachmentUuid${caseAttachment.serialNo}" name="attachmentUuid${caseAttachment.serialNo}" value="${caseAttachment.uuid}" />
		<button type="button" class="btn btn-default" onclick="javascript:location.href='CaseFile/attachment?dataUuid=${caseAttachment.dataUuid}&uuid=${caseAttachment.uuid}'"><i class="fa fa-download"></i></button>
		&nbsp;<button type="button" class="btn btn-default" disabled="disabled"> ${caseAttachment.fileName}${empty caseAttachment.extName ? '' : '.'}${empty caseAttachment.extName ? '' : caseAttachment.extName} </button>
	</div>
</div>
<div class="row margin-bottom-lg">
	<div class="col-md-1 col-sm-1 col-xs-1 text-right">
	</div>
	<div class="col-md-11 col-sm-11 col-xs-11">
		<input type="text" class="form-control" id="attachmentDesc${caseAttachment.serialNo}" name="attachmentDesc${caseAttachment.serialNo}" maxlength="128" placeholder="請輸入檔案說明" value="${caseAttachment.description}" />
	</div>
</div>
<div class="row margin-bottom-lg">
	<div class="col-md-1 col-sm-1 col-xs-1 text-right">
	</div>
	<div class="col-md-11 col-sm-11 col-xs-11">
		<input type="file" class="file" id="attachmentFile${caseAttachment.serialNo}" name="attachmentFile" />
	</div>
</div>
</c:forEach>


<div class="row" id="dummyDiv">			
</div>


</div>