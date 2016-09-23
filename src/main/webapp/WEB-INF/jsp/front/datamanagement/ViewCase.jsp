<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<c:url value='/res/js/seeweb/datamanagement/ViewCase.js' />"></script>

<div class="margin-top-lg">

	<div id="messageBar"></div>
	
	<div role="tabpanel">
	  		<ul class="nav nav-tabs" role="tablist">
	    		<li role="presentation" class="active" id="tabHeader1"><a href="#tab1" aria-controls="ViewCaseTab1" role="tab" data-toggle="tab">基本資訊</a></li>
	    		<li role="presentation" id="tabHeader2"><a href="#tab2" aria-controls="ViewCaseTab2" role="tab" data-toggle="tab">進階資訊</a></li>
	    		<li role="presentation" id="tabHeader3"><a href="#tab3" aria-controls="ViewCaseTab3" role="tab" data-toggle="tab">相關檔案</a></li>
	  		</ul>
	</div>
	
	<div class="tab-content tab-content-with-bottom-border">
	    		<div role="tabpanel" class="tab-pane active" id="tab1">
						<c:import url="/WEB-INF/jsp/front/datamanagement/ViewCaseTab1.jsp" />
	    		</div>
	    		<div role="tabpanel" class="tab-pane" id="tab2">
	    			<c:import url="/WEB-INF/jsp/front/datamanagement/ViewCaseTab2.jsp" />
	    		</div>
	    		<div role="tabpanel" class="tab-pane" id="tab3">
	    			<c:import url="/WEB-INF/jsp/front/datamanagement/ViewCaseTab3.jsp" />
	    		</div>
	</div>
	
	<div class="pull-right margin-bottom-lg">
			<button type="button" id="previousBtn" name="previousBtn" class="btn btn-default" onclick="javascript:location.href='CaseList?projectUuid=${projectUuid}'">返回個案清單</button>
	</div>

</div>