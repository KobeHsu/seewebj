<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<c:url value='/res/js/seeweb/ValidatorAddOn.js' />"></script>
<script type="text/javascript" src="<c:url value='/res/js/seeweb/NewOnlineIssue.js' />"></script>

<div class="margin-top-lg">

	<div id="messageBar"></div>
	
	<div role="tabpanel">
	  <ul class="nav nav-tabs" role="tablist">
	  	<li role="presentation" class="active" id="tabHeader1"><a href="#tab1" aria-controls="NewCaseTab1" role="tab" data-toggle="tab">回報問題</a></li>
	    <li role="presentation" id="tabHeader2"><a href="#tab2" aria-controls="NewCaseTab4" role="tab" data-toggle="tab">畫面擷圖</a></li>
		</ul>
	</div>
	
	<div class="tab-content tab-content-with-bottom-border">
		<div role="tabpanel" class="tab-pane active" id="tab1">
			<c:import url="/WEB-INF/jsp/front/NewOnlineIssueTab1.jsp" />
	  </div>
	  <div role="tabpanel" class="tab-pane" id="tab2">
	  	<c:import url="/WEB-INF/jsp/front/NewOnlineIssueTab2.jsp" />
	  </div>	  
	</div>
	
	<div class="pull-right margin-bottom-lg">
		<!--
			<button type="button" id="resetBtn" name="resetBtn" class="btn btn-default" onclick="javascript:location.href='ForumList?projectUuid=${projectUuid}'">清除</button>
			-->
			<button type="button" id="confirmBtn" name="confirmBtn" class="btn btn-primary">確認送出</button>
	</div>

</div>