<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container padding-top-lg">
	 	<form role="form" class="form-horizontal" id="editForm1" name="editForm1">
	 		<input type="hidden" class="form-control" id="functionName" name="functionName" />
	 		<input type="hidden" class="form-control" id="projectUuid" name="projectUuid" value="1" />
	 		<input type="hidden" class="form-control" id="uuid" name="uuid" />
	 		<div class="form-group">
      	<label for="title" class="control-label col-md-2 text-right">欄位名稱</label>
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
      	<label for="serveMeal" class="control-label col-md-2 text-right">值型別</label>
      	<div class="col-md-3">
      		<select class="form-control" name="valueType" id="valueType">
      		</select>
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="listOrder" class="control-label col-md-2 text-right">長度</label>
      	<div class="col-md-2">
      		<input type="text" class="form-control" id="valueLength" name="valueLength" />
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="listOrder" class="control-label col-md-2 text-right">顯示單位</label>
      	<div class="col-md-2">
      		<input type="text" class="form-control" id="valueMeasure" name="valueMeasure" />
      	</div>
    	</div>

		</form>
</div>