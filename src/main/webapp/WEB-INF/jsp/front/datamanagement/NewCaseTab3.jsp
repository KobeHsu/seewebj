<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value='/res/kartik-v-bootstrap-fileinput/css/fileinput.min.css' />" rel="stylesheet">		
	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/plugins/canvas-to-blob.min.js' />"></script>	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/fileinput.min.js' />"></script>	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/fileinput_locale_zh-TW.js' />"></script>	
	
<div class="panel-default margin-top-lg">

<form role="form" class="form-horizontal" id="newCaseTab3Form" name="newCaseTab3Form">
	<div class="form-group">
		<label for="portrait" class="control-label col-md-2 text-right">個案影像</label>
    <div class="col-md-5">
    	<input type="file" class="file" id="portraitFile" name="portraitFile" />
    </div>
	</div>

</form>

</div>