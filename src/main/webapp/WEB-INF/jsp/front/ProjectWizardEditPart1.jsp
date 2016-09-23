<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container padding-top-lg">
	 	<form role="form" class="form-horizontal" id="editForm1" name="editForm1">
	 		<input type="hidden" class="form-control" id="functionName" name="functionName" />
	 		<input type="hidden" class="form-control" id="projectUuid" name="projectUuid" value="1" />
	 		<input type="hidden" class="form-control" id="uuid" name="uuid" />
	 		<input type="hidden" class="form-control" id="toolUuids" name="toolUuids" />
	 		<div class="form-group">
      	<label for="title" class="control-label col-md-2 text-right">階段名稱</label>
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
		</form>
</div>
<hr>
<div class="padding-top-lg">
		<table id="queryToolList" class="display compact" cellspacing="0">
			<thead>
				<tr>
					<th></th>
					<th>UUID</th>
					<th>工具名稱</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>

</div>
