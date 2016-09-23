<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<c:url value='/res/js/seeweb/ViewCase.js' />"></script>

<div class="margin-top-lg">

	<div id="messageBar"></div>
	
	<div role="tabpanel">
	  		<ul class="nav nav-tabs" role="tablist">
	    		<li role="presentation" class="active" id="tabHeader1"><a href="#tab1" aria-controls="ViewCaseTab1" role="tab" data-toggle="tab">易用性測試共用表單範本</a></li>
	  		</ul>
	</div>
	
	<div class="tab-content tab-content-with-bottom-border">
	    		<div role="tabpanel" class="tab-pane active" id="tab1">
						<c:import url="/WEB-INF/jsp/front/ViewUsibilityTestFormTemplateTab1.jsp" />
	    		</div>
	</div>
	
</div>