<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container padding-top-lg">
	 	<form role="form" class="form-horizontal" id="queryForm" name="queryForm">
	 		<div class="form-group">
      	<label for="queryAccount" class="control-label col-md-2 text-right">帳號</label>
      	<div class="col-md-5">
      		<input type="text" class="form-control" id="queryAccount" name="queryAccount" />
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="queryRealname" class="control-label col-md-2 text-right">姓名</label>
      	<div class="col-md-5">
      		<input type="text" class="form-control" id="queryRealname" name="queryRealname" />
      	</div>
    	</div>
	 		
	 		<div class="form-group">
      	<label for="queryStatus" class="control-label col-md-2 text-right">狀態</label>
      	<div class="col-md-3">
      		<select class="form-control" name="queryStatus" id="queryStatus">
      		</select>
      	</div>
    	</div>
	 		
		</form>			
</div>
<hr>
<div>
		<table id="queryResultList" class="display compact" cellspacing="0">
			<thead>
				<tr>
					<th>UUID</th>
					<th>帳號</th>
					<th>姓名</th>
					<th>狀態</th>
					<th>狀態代碼</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>

</div>