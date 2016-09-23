<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel panel-tool">
	<div class="panel-heading">
		<h3 class="panel-title">
			${sessionScope.activeProjectName} - 專案流程
		</h3>
	</div>

	<div class="panel-body">
		<div class="row">
		
<c:forEach var="projectPhase" items="${projectPhases}" varStatus="projectPhaseLoop">			  	
			<div class="col-md-3 col-sm-6 col-xs-6">
		    <div class="panel panel-primary">
		      <div class="panel-heading">階段#${projectPhaseLoop.index+1}<br>${projectPhase.name}</div>
		      <div class="panel-body">
	      	
		<c:if test="${not empty projectPhase.projectPhaseTools}">
			<c:set var="toolCount" value="0" />
			<c:forEach var="projectPhaseTool" items="${projectPhase.projectPhaseTools}" varStatus="projectPhaseToolLoop">	
						<c:if test="${projectPhaseTool.status eq 'ACTIVE'}">
							<c:if test="${toolCount > 0}">
								<br>
							</c:if>
							<a href="${projectPhaseTool.tool.url}?projectUuid=${sessionScope.activeProjectUuid}" target="_blank" class="label label-tool">${projectPhaseTool.tool.name}</a>
							<c:set var="toolCount" value="${toolCount + 1}"/>
						</c:if>
			</c:forEach>
		</c:if>
		      	
		      </div>
		    </div>
			</div>
</c:forEach>
		
		</div>
	</div>
	
</div>

