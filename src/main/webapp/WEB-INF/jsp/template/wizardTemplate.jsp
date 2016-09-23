<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		
		<title><tiles:getAsString name="title"/></title>
			
		<link rel="shortcut icon" href="<c:url value='/res/images/favicon.ico' />" type="image/x-icon" /> 
		
		<link href="<c:url value='/res/css/bootstrap.min.css' />" rel="stylesheet">
		<link href="<c:url value='/res/bootstrap-datepicker/css/datepicker.css' />" rel="stylesheet">			
		<link href="<c:url value='/res/font-awesome/css/font-awesome.css' />" rel="stylesheet">		
		<link href="<c:url value='/res/formvalidation/css/formValidation.min.css' />" rel="stylesheet">
		<link href="<c:url value='/res/css/bootstrap-tokenfield.min.css' />" rel="stylesheet">
		<link href="<c:url value='/res/css/jquery.dataTables.min.css' />" rel="stylesheet">
		<link href="<c:url value='/res/css/dataTables.bootstrap.css' />" rel="stylesheet">
		<link href="<c:url value='/res/css/awesome-bootstrap-checkbox.css' />" rel="stylesheet">			
		<link href="<c:url value='/res/css/styles.css' />" rel="stylesheet">
		
		<script src="<c:url value='/res/js/jquery-1.11.3.min.js' />"></script>
		<script src="<c:url value='/res/js/bootstrap.min.js' />"></script>
		<script src="<c:url value='/res/formvalidation/js/formValidation.min.js' />"></script>
		<script src="<c:url value='/res/formvalidation/js/framework/bootstrap.min.js' />"></script>	
		<script src="<c:url value='/res/js/jquery.blockUI.js' />"></script>
		<script src="<c:url value='/res/js/form2js.js' />"></script>
		<script src="<c:url value='/res/js/bootstrap-tokenfield.js' />"></script>
		<script src="<c:url value='/res/js/jquery.dataTables.min.js' />"></script>
		<script src="<c:url value='/res/js/dataTables.bootstrap.js' />"></script>			
		<script src="<c:url value='/res/bootstrap-datepicker/js/bootstrap-datepicker.js' />"></script>

		<!--[if lt IE 9]>
    <script src="res/js/html5shiv.min.js"></script>
    <script src="res/js/respond.min.js"></script>
    <![endif]-->
						
	</head>
	
	<body>		
		<div id="mainDiv">
			<tiles:insertAttribute name="header" />
			<div class="container-fluid">
			  <div class="row">
			  	<div class="col-md-offset-2 col-md-8 col-sm-12 col-xs-12">
		        <tiles:insertAttribute name="content" />
			  	</div>
		    </div>
		  </div>
		  <div id="pushDiv"></div>
	  </div>
  	<div id="footer">
  		<tiles:insertAttribute name="footer" />
  	</div>
		      		        
	</body>
	
</html>