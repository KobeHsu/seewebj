<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container padding-top-lg">
	 	<form role="form" class="form-horizontal" id="editForm1" name="editForm1">
	 		<input type="hidden" class="form-control" id="functionName" name="functionName" />
	 		<input type="hidden" class="form-control" id="projectUuid" name="projectUuid" value="1" />
	 		<input type="hidden" class="form-control" id="uuid" name="uuid" />
	 		<div class="form-group">
      	<label for="title" class="control-label col-md-2 text-right">表單名稱</label>
      	<div class="col-md-5">
      		<input type="text" class="form-control" id="name" name="name" maxlength="32" />
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="listOrder" class="control-label col-md-2 text-right">顯示順序</label>
      	<div class="col-md-2">
      		<input type="text" class="form-control" id="serialNo" name="serialNo" />
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="serveMeal" class="control-label col-md-2 text-right">說明</label>
      	<div class="col-md-8">
					<textarea class="form-control" rows="3" id="description" name="description" maxlength="512"></textarea>
      	</div>
    	</div>


	 		<div class="form-group">
      	<label for="formFile" class="control-label col-md-2 text-right">檔案</label>
      	<div class="col-md-8">      		      		
					<button type="button" class="btn btn-default" id="btnViewFile" name="btnViewFile"><i class="fa fa-eye"></i></button>
					<span id="viewFormFileDiv" name="viewFormFileDiv">&nbsp;&nbsp;</span>
      	</div>
    	</div>
	 		<div class="form-group">
      	<label for="formFile" class="control-label col-md-2 text-right"></label>
      	<div class="col-md-8">      		      		
      		<input class="form-control" type="file" class="file" id="formFile" name="formFile" />
      	</div>
    	</div>

		</form>
</div>