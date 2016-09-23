<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container padding-top-lg">
	 	<form role="form" class="form-horizontal" id="queryForm" name="queryForm">
	 		<div class="form-group">
      	<label for="queryAccount" class="control-label col-md-2 text-right">名稱</label>
      	<div class="col-md-5">
      		<input type="text" class="form-control" id="queryName" name="queryName" />
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
					<th>名稱</th>
					<th>說明</th>
					<th>URL</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>

</div>