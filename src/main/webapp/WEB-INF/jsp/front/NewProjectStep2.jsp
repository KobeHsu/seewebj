<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<c:url value='/res/js/seeweb/ValidatorAddOn.js' />"></script>
<script type="text/javascript" src="<c:url value='/res/js/seeweb/NewProjectStep2.js' />"></script>

<div class="page-header margin-top-md">
	<h4>
		新增專案 <small>步驟2: 選取專案範本</small>
	</h4>
</div>

<form role="form" class="form-horizontal" id="newProjectForm" name="newProjectForm">
<input type="hidden" id="step" name="step" value="2" />
<input type="hidden" id="name" name="name" value="${projectData.name}" />
<input type="hidden" id="next" name="next" />
	
<div class="margin-top-lg">

<c:if test="${not empty projectTemplates}">
	<c:forEach var="projectTemplate" items="${projectTemplates}" varStatus="projectTemplateLoop">

	<div class="radio radio-danger">
		<c:choose>
			<c:when test="${projectData.projectTemplateUuid == projectTemplate.uuid}">
	  		<input type="radio" name="projectTemplateUuid" value="${projectTemplate.uuid}" id="projectTemplateUuid${projectTemplate.uuid}" checked="checked">
			</c:when>
			<c:otherwise>
				<input type="radio" name="projectTemplateUuid" value="${projectTemplate.uuid}" id="projectTemplateUuid${projectTemplate.uuid}">
			</c:otherwise>
		</c:choose>	
	  <label for="projectTemplateUuid${projectTemplate.uuid}">
	  	${projectTemplate.name} <br>
	  	${projectTemplate.description}
	  </label>	  
	</div>
	

	</c:forEach>
</c:if>

</div>

<div class="pull-right margin-bottom-lg">
	<button type="button" id="prevBtn" name="prevBtn" class="btn btn-default">上一步</button>
	<button type="button" id="nextBtn" name="nextBtn" class="btn btn-default">下一步</button>
</div>

</form>
