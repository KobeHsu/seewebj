<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav></nav>
<script>gModelType = "8";</script>

<section id="mainArea">
	<div class="col-md-2 col-sm-2 col-xs-2">
		<div class="panel panel-primary margin-top-lg">
			<div class="panel-heading">
				<h4 class="panel-title">塑模元素</h4>
			</div>

			<div id="component" align="center">
				<img src="<c:url value='/res/images/sb_customeractivity.png' />" onclick="addCustom('ClippingSquare')" /> <br />顧客活動<br /> 
				<img src="<c:url value='/res/images/sb_serviceactivity.png' />" onclick="addRect()" /> <br />服務活動<br /> 
				<img src="<c:url value='/res/images/sb_interaction.png' />" onclick="addConnector()" /> <br />流程方向<br /> 
				<img src="<c:url value='/res/images/sb_condition.png' />" onclick="addCustom('Diamond')" /> <br />決策分歧點<br /> 
				<img src="<c:url value='/res/images/sb_failpoint.png' />" onclick="addEllipse('xs-circle')" /> <br />服務失誤可能點<br />
			</div>

		</div>
	</div>

	<div id="drawArea" style="width: 760px;" class="panel panel-primary col-md-10">
		
		<svg height="600" version="1.1" width="800" xmlns="http://www.w3.org/2000/svg" id="snapSvg">						
			
		</svg>

	</div>

</section>
