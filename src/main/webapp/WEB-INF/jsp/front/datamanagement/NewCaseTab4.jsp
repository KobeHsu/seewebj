<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value='/res/kartik-v-bootstrap-fileinput/css/fileinput.min.css' />" rel="stylesheet">		
	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/plugins/canvas-to-blob.min.js' />"></script>	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/fileinput.min.js' />"></script>	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/fileinput_locale_zh-TW.js' />"></script>	
	
<div class="panel-default margin-top-lg">

<div class="row margin-bottom-lg">			
	<div class="col-md-1 col-sm-1 col-xs-1 text-right">
		<button type="button" id="newAttachmentBtn" name="newAttachmentBtn" class="btn btn-success" onclick="addAttachment()"><i class="fa fa-plus"></i></button>
	</div>	
</div>

<div class="row" id="dummyDiv">			
</div>

</div>