<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<c:url value='/res/js/seeweb/ValidatorAddOn.js' />"></script>
<script type="text/javascript" src="<c:url value='/res/js/seeweb/NewProjectStep3.js' />"></script>

<div class="page-header margin-top-md">
	<h4>
		新增專案 <small>步驟3: 調整各階段使用工具</small>
	</h4>
</div>

<form role="form" class="form-horizontal" id="newProjectForm" name="newProjectForm">
<input type="hidden" id="step" name="step" value="3" />
<input type="hidden" id="name" name="name" value="${projectData.name}" />
<input type="hidden" id="projectTemplateUuid" name="projectTemplateUuid" value="${projectData.projectTemplateUuid}" />
<input type="hidden" id="next" name="next" />
	
<div class="row margin-top-lg">

<c:forEach var="projectTemplatePhase" items="${projectTemplatePhases}" varStatus="projectTemplatePhaseLoop">			  	
		<div class="col-md-3 col-sm-6 col-xs-6">
	    <div class="panel panel-primary">
	      <div class="panel-heading">階段#${projectTemplatePhaseLoop.index+1}<br>${projectTemplatePhase.name}</div>
	      <div class="panel-body">
	      	
		<c:if test="${not empty projectTemplatePhase.projectTemplatePhaseTools}">
			<c:forEach var="projectTemplatePhaseTool" items="${projectTemplatePhase.projectTemplatePhaseTools}" varStatus="projectTemplatePhaseToolLoop">			  	
					<div class="checkbox checkbox-danger">
						<input type="checkbox" id="projectTemplatePhaseTool${projectTemplatePhaseTool.id.projectTemplatePhaseUuid}:${projectTemplatePhaseTool.id.toolUuid}" name="projectTemplatePhaseTool" value="${projectTemplatePhaseTool.id.projectTemplatePhaseUuid}:${projectTemplatePhaseTool.id.toolUuid}" checked="checked" />
						<label class="text-primary" for="projectTemplatePhaseTool${projectTemplatePhaseTool.id.projectTemplatePhaseUuid}:${projectTemplatePhaseTool.id.toolUuid}">${projectTemplatePhaseTool.tool.name}</label>
					</div>
			</c:forEach>
		</c:if>
		      	
	      </div>
	    </div>
		</div>
</c:forEach>

</div>

<div class="pull-right margin-bottom-lg">
	<button type="button" id="prevBtn" name="prevBtn" class="btn btn-default">上一步</button>
	<button type="button" id="nextBtn" name="nextBtn" class="btn btn-default">完成</button>
</div>

</form>
