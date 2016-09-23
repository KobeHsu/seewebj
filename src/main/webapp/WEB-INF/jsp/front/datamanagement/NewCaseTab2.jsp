<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel-default margin-top-lg">

<c:if test="${not empty caseExtraDefinitionList}">
	
<form role="form" class="form-horizontal" id="newCaseTab2Form" name="newCaseTab2Form">

<c:forEach var="caseExtraDefinition" items="${caseExtraDefinitionList}" varStatus="caseExtraDefinitionLoop">			  	
		
		<div class="form-group">
    		<label for="account" class="control-label col-md-2 text-right">${caseExtraDefinition.name}</label>
<c:choose>
	<c:when test="${caseExtraDefinition.valueLength le 10}">
    		<div class="col-md-3">
	</c:when>
	<c:when test="${caseExtraDefinition.valueLength le 32}">
    		<div class="col-md-8">
	</c:when>
	<c:otherwise>
				<div class="col-md-10">
	</c:otherwise>
</c:choose>    		
    				<div class="input-group"> 
    					<span class="input-group-addon"><i class="fa fa-pencil fa-width-sm"></i> </span>
    					<input type="hidden" id="caseExtraDataList[${caseExtraDefinitionLoop.index}].definitionUuid" name="caseExtraDataList[${caseExtraDefinitionLoop.index}].definitionUuid" value="${caseExtraDefinition.uuid}" />
      				<input type="text" class="form-control ${caseExtraDefinition.valueType}" id="caseExtraDataList[${caseExtraDefinitionLoop.index}].content" name="caseExtraDataList[${caseExtraDefinitionLoop.index}].content" maxlength="${caseExtraDefinition.valueLength}" />
<c:if test="${not empty caseExtraDefinition.valueMeasure}">
      				<span class="input-group-addon">${caseExtraDefinition.valueMeasure}</span>
</c:if>
      			</div>
     		</div>
    </div>

</c:forEach>

</form>
</c:if>

</div>