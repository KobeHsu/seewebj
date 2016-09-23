<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container padding-top-lg">
	 	<form role="form" class="form-horizontal" id="queryForm" name="queryForm">
	 		<div class="form-group">
      	<label for="queryDate" class="control-label col-md-2 text-right">提出日期</label>
      	<div class="col-md-4">
	        <div class="input-group date-picker input-daterange" data-date-format="yyyy/mm/dd">
	          <input type="text" class="form-control" name="queryBeginDate" id="queryBeginDate">
	          <span class="input-group-addon"> 至 </span>
	          <input type="text" class="form-control" name="queryEndDate" id="queryEndDate">
	        </div>	        
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="queryProject" class="control-label col-md-2 text-right">專案</label>
      	<div class="col-md-6">
      		<select class="form-control" name="queryProject" id="queryProject">
      		</select>
      	</div>
    	</div>
	 		
	 		<div class="form-group">
      	<label for="queryStatus" class="control-label col-md-2 text-right">狀態</label>
      	<div class="col-md-2">
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
					<th>專案</th>
					<th>問題主旨</th>
					<th>狀態</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>

</div>