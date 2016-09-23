<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel-default margin-top-lg">

<div class="row">
	
	<div class="col-md-12 col-sm-12 col-xs-12">
		<table class="table table-striped">
		<thead>
			<tr class="info">
				<td width="7%" class="text-right">項次</td>
				<td width="3%"></td>
				<td width="45%">檔名</td>
				<td width="45%">說明</td>
			</tr>
		</thead>
		<tbody>
<c:forEach var="usibilityTestFormTemplate" items="${usibilityTestFormTemplateList}" varStatus="usibilityTestFormTemplateLoop">
			<tr>
				<td class="text-right">${usibilityTestFormTemplateLoop.index+1}</td>
				<td>
					<button type="button" class="btn btn-xs btn-warning" onclick="javascript:location.href='UsibilityTestFormFile/formTemplate?projectUuid=${projectUuid}&uuid=${usibilityTestFormTemplate.uuid}'"><i class="fa fa-download"></i></button>					
				</td>
				<td>${usibilityTestFormTemplate.fileName}${empty usibilityTestFormTemplate.extName ? '' : '.'}${empty usibilityTestFormTemplate.extName ? '' : usibilityTestFormTemplate.extName}</td>
				<td>${usibilityTestFormTemplate.description}</td>
			</tr>
</c:forEach>
		
		</tbody>	
		</table>
	
	</div>

</div>
</div>