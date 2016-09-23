<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<c:url value='/res/js/seeweb/ValidatorAddOn.js' />"></script>
<script type="text/javascript" src="<c:url value='/res/js/seeweb/NewProjectStep1.js' />"></script>

<div class="page-header margin-top-md">
	<h4>
		新增專案 <small>步驟1: 輸入專案名稱</small>
	</h4>
</div>

<form role="form" class="form-horizontal" id="newProjectForm" name="newProjectForm">
<input type="hidden" id="step" name="step" value="1" />
	
<div class="margin-top-lg">
		<div class="form-group">
			<label for="title" class="control-label col-md-2 text-right">專案名稱</label>
			<div class="col-md-8">
					<input type="text" class="form-control" id="name" name="name" maxlength="64" placeholder="請輸入專案名稱" value="${projectData.name}" />
			</div>
		</div>

</div>

<div class="pull-right margin-bottom-lg">
	<button type="button" id="prevBtn" name="prevBtn" class="btn btn-default" onclick="javascript:location.href='Index'">取消</button>
	<button type="button" id="nextBtn" name="nextBtn" class="btn btn-default">下一步</button>
</div>

</form>
