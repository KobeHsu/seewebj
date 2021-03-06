<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value='/res/bootstrap-datepicker/css/datepicker.css' />" rel="stylesheet">
	
<script type="text/javascript" src="<c:url value='/res/js/seeweb/ValidatorAddOn.js' />"></script>
<script type="text/javascript" src="<c:url value='/res/bootstrap-datepicker/js/bootstrap-datepicker.js' />"></script>
<script type="text/javascript" src="<c:url value='/res/js/seeweb/FormControlUtil.js' />"></script>	
<script type="text/javascript" src="<c:url value='/res/js/seeweb/NewProjectAnnouncement.js' />"></script>

<div class="margin-top-lg">

	<div id="messageBar"></div>
	
	<div role="tabpanel">
	  <ul class="nav nav-tabs" role="tablist">
	  	<li role="presentation" class="active" id="tabHeader1"><a href="#tab1" aria-controls="NewAnnouncementTab1" role="tab" data-toggle="tab">公告設定</a></li>
	  	<li role="presentation" id="tabHeader2"><a href="#tab2" aria-controls="NewAnnouncementTab2" role="tab" data-toggle="tab">附件</a></li>
		</ul>
	</div>
	
	<div class="tab-content tab-content-with-bottom-border">
		<div role="tabpanel" class="tab-pane active" id="tab1">
			<c:import url="/WEB-INF/jsp/front/auxiliary/NewProjectAnnouncementTab1.jsp" />
	  </div>
		<div role="tabpanel" class="tab-pane" id="tab2">
			<c:import url="/WEB-INF/jsp/front/auxiliary/NewProjectAnnouncementTab2.jsp" />
	  </div>
	</div>
	
	<div class="pull-right margin-bottom-lg">
			<button type="button" id="previousBtn" name="previousBtn" class="btn btn-default" onclick="javascript:location.href='AnnouncementList?projectUuid=${projectUuid}'">返回公佈欄</button>
			<button type="button" id="confirmBtn" name="confirmBtn" class="btn btn-primary">確認送出</button>
	</div>

</div>