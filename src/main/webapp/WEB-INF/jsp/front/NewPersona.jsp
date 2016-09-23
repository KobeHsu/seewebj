<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<c:url value='/res/js/seeweb/ValidatorAddOn.js' />"></script>
<script type="text/javascript" src="<c:url value='/res/js/seeweb/NewPersona.js' />"></script>

<div class="margin-top-lg">

	<div id="messageBar"></div>
	
	<c:import url="/WEB-INF/jsp/front/NewPersonaTab1.jsp" />
	
	
	<div class="pull-right margin-bottom-lg">
			<button type="button" id="previousBtn" name="previousBtn" class="btn btn-default" onclick="javascript:location.href='PersonaList?projectUuid=${projectUuid}'">返回人物角色原型清單</button>
			<button type="button" id="confirmBtn" name="confirmBtn" class="btn btn-primary">確認送出</button>
	</div>

</div>