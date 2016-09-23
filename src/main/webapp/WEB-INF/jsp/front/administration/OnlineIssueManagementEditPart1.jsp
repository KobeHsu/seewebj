<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container padding-top-lg">
	 	<form role="form" class="form-horizontal" id="editForm1" name="editForm1">
	 		<input type="hidden" class="form-control" id="functionName" name="functionName" />
	 		<input type="hidden" class="form-control" id="projectUuid" name="projectUuid" value="1" />
	 		<input type="hidden" class="form-control" id="uuid" name="uuid" />

	 		<div class="form-group">
      	<label for="project" class="control-label col-md-2 text-right">專案</label>
      	<div class="col-md-6">
      		<select class="form-control" name="project" id="project" disabled="disabled">
      		</select>
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="submitter" class="control-label col-md-2 text-right">提出者</label>
      	<div class="col-md-3">
					<input type="text" class="form-control" id="submitter" name="submitter" maxlength="64" disabled="disabled" />
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="createTime" class="control-label col-md-2 text-right">提出時間</label>
      	<div class="col-md-3">
      		<input type="text" class="form-control" id="createTime" name="createTime" maxlength="32" disabled="disabled" />
      	</div>
    	</div>

	 		<div class="form-group">
				<label for="title" class="control-label col-md-2 text-right">主旨</label>
				<div class="col-md-10">
						<input type="text" class="form-control" id="title" name="title" maxlength="32" disabled="disabled" />
				</div>
    	</div>

	 		<div class="form-group">
				<label for="title" class="control-label col-md-2 text-right">內容</label>
			  <div class="col-md-10">
			  		<textarea class="form-control" rows="4" id="content" name="content" disabled="disabled"></textarea>
			  </div>
    	</div>

	 		<div class="form-group">
				<label for="title" class="control-label col-md-2 text-right">畫面擷圖</label>
			  <div class="col-md-10">
				  <div class="row" id="figureList">
				  		
				  </div>			  		
			  </div>
    	</div>

	 		<div class="form-group">
				<label for="title" class="control-label col-md-2 text-right">處理說明</label>
			  <div class="col-md-10">
			  		<textarea class="form-control" rows="4" id="replyContent" name="replyContent"></textarea>
			  </div>
    	</div>

	 		<div class="form-group">
      	<label for="processor" class="control-label col-md-2 text-right">處理人員</label>
      	<div class="col-md-3">
					<input type="text" class="form-control" id="processor" name="processor" maxlength="64" disabled="disabled" />
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="updateTime" class="control-label col-md-2 text-right">處理時間</label>
      	<div class="col-md-3">
      		<input type="text" class="form-control" id="updateTime" name="updateTime" maxlength="32" disabled="disabled" />
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="status" class="control-label col-md-2 text-right">狀態</label>
      	<div class="col-md-2">
      		<select class="form-control" name="status" id="status">
      		</select>
      	</div>
    	</div>

		</form>
</div>