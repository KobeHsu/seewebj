<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel-default margin-top-lg">

<c:if test="${not empty caseExtraItemList}">
	
<form role="form" class="form-horizontal" id="editCaseTab2Form" name="editCaseTab2Form">

<c:forEach var="caseExtraItem" items="${caseExtraItemList}" varStatus="caseExtraItemLoop">			  	
		
		<div class="form-group">
    		<label for="account" class="control-label col-md-2 text-right">${caseExtraItem.definition.name}</label>
<c:choose>
	<c:when test="${caseExtraItem.definition.valueLength le 10}">
    		<div class="col-md-3">
	</c:when>
	<c:when test="${caseExtraItem.definition.valueLength le 32}">
    		<div class="col-md-8">
	</c:when>
	<c:otherwise>
				<div class="col-md-10">
	</c:otherwise>
</c:choose>    		
    				<div class="input-group"> 
    					<span class="input-group-addon"><i class="fa fa-pencil fa-width-sm"></i> </span>
    					<input type="hidden" id="caseExtraDataList[${caseExtraItemLoop.index}].uuid" name="caseExtraDataList[${caseExtraItemLoop.index}].uuid" value="${caseExtraItem.data.uuid}" />
    					<input type="hidden" id="caseExtraDataList[${caseExtraItemLoop.index}].definitionUuid" name="caseExtraDataList[${caseExtraItemLoop.index}].definitionUuid" value="${caseExtraItem.definition.uuid}" />
    					
      				<input type="text" class="form-control ${caseExtraItem.definition.valueType}" id="caseExtraDataList[${caseExtraItemLoop.index}].content" name="caseExtraDataList[${caseExtraItemLoop.index}].content" maxlength="${caseExtraItem.definition.valueLength}" value="${caseExtraItem.data.content}" />
<c:if test="${not empty caseExtraItem.definition.valueMeasure}">
      				<span class="input-group-addon">${caseExtraItem.definition.valueMeasure}</span>
</c:if>
      			</div>
     		</div>
    </div>

</c:forEach>

</form>
</c:if>

</div>