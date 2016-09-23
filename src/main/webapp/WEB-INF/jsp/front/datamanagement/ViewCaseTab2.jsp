<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel-default margin-top-lg">

<div class="row">
	<div class="col-md-3 col-sm-3 hidden-xs margin-bottom-lg">
<c:choose>
	<c:when test="${not empty casePortraitFile}">
		<img class="img-responsive" src="<c:url value='/CaseFile/portrait?dataUuid=${caseBasicData.uuid}&uuid=${casePortraitFile.uuid}&fileName=${casePortraitFile.uuid.concat(".").concat(casePortraitFile.extName)}' />" />
	</c:when>		
	<c:when test="${caseBasicData.sex eq 'M'}">			
			<img class="img-responsive" src="<c:url value='/res/images/male.png' />" />
	</c:when>
	<c:otherwise>
			<img class="img-responsive" src="<c:url value='/res/images/female.png' />" />
	</c:otherwise>
</c:choose>		
	</div>
	
	<div class="col-md-9 col-sm-9 col-xs-9">
		<table class="table table-striped">
		<tbody>
<c:forEach var="caseExtraItem" items="${caseExtraItemList}" varStatus="caseExtraItemLoop">
			<tr>
				<td width="25%" class="text-right">${caseExtraItem.definition.name} <i class="fa fa-angle-right fa-width-sm"></i></td>
				<td width="75%">${caseExtraItem.data.content} ${caseExtraItem.definition.valueMeasure}</td>
			</tr>
</c:forEach>
		
		</tbody>	
		</table>
	
	</div>

</div>
</div>