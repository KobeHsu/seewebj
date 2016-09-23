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
<c:forEach var="caseAttachment" items="${caseAttachmentList}" varStatus="caseAttachmentLoop">
			<tr>
				<td class="text-right">${caseAttachmentLoop.index+1}</td>
				<td>
					<button type="button" class="btn btn-xs btn-warning" onclick="javascript:location.href='CaseFile/attachment?dataUuid=${caseAttachment.dataUuid}&uuid=${caseAttachment.uuid}'"><i class="fa fa-download"></i></button>					
				</td>
				<td>${caseAttachment.fileName}${empty caseAttachment.extName ? '' : '.'}${empty caseAttachment.extName ? '' : caseAttachment.extName}</td>
				<td>${caseAttachment.description}</td>
			</tr>
</c:forEach>
		
		</tbody>	
		</table>
	
	</div>

</div>
</div>