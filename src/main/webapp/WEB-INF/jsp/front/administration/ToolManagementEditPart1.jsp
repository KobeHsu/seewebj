<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container padding-top-lg">
	 	<form role="form" class="form-horizontal" id="editForm1" name="editForm1">
	 		<input type="hidden" class="form-control" id="functionName" name="functionName" />
	 		<input type="hidden" class="form-control" id="uuid" name="uuid" />

	 		<div class="form-group">
      	<label for="name" class="control-label col-md-2 text-right">名稱</label>
      	<div class="col-md-6">
					<input type="text" class="form-control" id="name" name="name" maxlength="64" />
      	</div>
    	</div>

	 		<div class="form-group">
				<label for="description" class="control-label col-md-2 text-right">說明</label>
			  <div class="col-md-10">
			  		<textarea class="form-control" rows="4" id="description" name="description"></textarea>
			  </div>
    	</div>

	 		<div class="form-group">
      	<label for="url" class="control-label col-md-2 text-right">URL</label>
      	<div class="col-md-10">
					<input type="text" class="form-control" id="url" name="url" maxlength="128" />
      	</div>
    	</div>

		</form>
</div>