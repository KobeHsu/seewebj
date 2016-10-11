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
		<link href="<c:url value='/res/css/BootSideMenu.css' />" rel="stylesheet">
		<link href="<c:url value='/res/font-awesome/css/font-awesome.css' />" rel="stylesheet">		
		<link href="<c:url value='/res/formvalidation/css/formValidation.min.css' />" rel="stylesheet">
		<link href="<c:url value='/res/css/bootstrap-tokenfield.min.css' />" rel="stylesheet">
		<link href="<c:url value='/res/css/jquery.dataTables.min.css' />" rel="stylesheet">
		<link href="<c:url value='/res/css/dataTables.bootstrap.css' />" rel="stylesheet">
		<link href="<c:url value='/res/css/bootstrap-colorpicker.css' />" rel="stylesheet">
		<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
		<link href="<c:url value='/res/css/styles.css' />" rel="stylesheet">			
		<link href="<c:url value='/res/css/see_draw.css' />" rel="stylesheet">	
		<link href="<c:url value='/res/css/ModelingExt.css' />" rel="stylesheet">
			
		<script src="<c:url value='/res/js/jquery-1.11.3.min.js' />"></script>
		<script src="<c:url value='/res/js/bootstrap.min.js' />"></script>
		<script src="<c:url value='/res/formvalidation/js/formValidation.min.js' />"></script>
		<script src="<c:url value='/res/formvalidation/js/framework/bootstrap.min.js' />"></script>	
		<script src="<c:url value='/res/js/jquery.blockUI.js' />"></script>
		<script src="<c:url value='/res/js/form2js.js' />"></script>
		<script src="<c:url value='/res/js/bootstrap-tokenfield.js' />"></script>
		<script src="<c:url value='/res/js/jquery.dataTables.min.js' />"></script>
		<script src="<c:url value='/res/js/dataTables.bootstrap.js' />"></script>
		<script src="<c:url value='/res/js/BootSideMenu.js' />"></script>
		<script src="<c:url value='/res/js/seeweb/ModelTutorial.js' />"></script>	
		<script src="<c:url value='/res/js/bootstrap-colorpicker.min.js' />"></script>
		
		<script type="text/javascript" src="<c:url value='/res/js/seeweb/snap.svg-min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/res/js/seeweb/saveSvgAsPng.js' />"></script>
		<script type="text/javascript" src="<c:url value='/res/js/seeweb/see_draw.js' />"></script>
		<script type="text/javascript" src="<c:url value='/res/js/seeweb/see_draw_path.js' />"></script>
		<script type="text/javascript" src="<c:url value='/res/js/seeweb/ModelingExt.js' />"></script>

		<!--[if lt IE 9]>
    <script src="res/js/html5shiv.min.js"></script>
    <script src="res/js/respond.min.js"></script>
    <![endif]-->
						
	</head>
	
	<body>		
		<div id="mainDiv">
			<c:import url="/WEB-INF/jsp/common/menuHeader.jsp" />
			<c:import url="/WEB-INF/jsp/common/modelHeader.jsp" />
												
			<div class="container-fluid">

	  	<div class="col-md-12 col-sm-12 col-xs-12">
        <tiles:insertAttribute name="content" />
	  	</div>	

		  </div>
		  <div id="pushDiv"></div>
	  </div>
	  
		<nav id="context-menu" class="context-menu">
			<ul class="context-menu__items">
				<li class="context-menu__item"><a href="#" class="context-menu__link" onmousedown="svgElRemove()">刪除元素</a></li>
				<li class="context-menu__item"><a href="#" class="context-menu__link" onmousedown="svgElDuplicate()">複製元素</a></li>
				<li class="context-menu__item"><a href="#" class="context-menu__link" onmousedown="svgElToFront()">移至上層</a></li>
				<li class="context-menu__item"><a href="#" class="context-menu__link" onmousedown="svgElToBack()">移至下層</a></li>
				<li class="context-menu__item"><a href="#" class="context-menu__link" id="borderColorPicker">設定框線色彩<input id="borderColorHex" type="hidden" /></a>
					<div class="sub-context-menu">
						<div class="color-picker-flat color-picker-red" onclick="borderColorChanged(this)">紅</div>
						<div class="color-picker-flat color-picker-orange" onclick="borderColorChanged(this)">橙</div>
						<div class="color-picker-flat color-picker-yellow" onclick="borderColorChanged(this)">黃</div>
						<div class="color-picker-flat color-picker-green" onclick="borderColorChanged(this)">綠</div>
						<div class="color-picker-flat color-picker-blue" onclick="borderColorChanged(this)">藍</div>
						<div class="color-picker-flat color-picker-indigo" onclick="borderColorChanged(this)">靛</div>
						<div class="color-picker-flat color-picker-violet" onclick="borderColorChanged(this)">紫</div>
						<div class="color-picker-flat color-picker-black" onclick="borderColorChanged(this)">黑</div>
						<div class="color-picker-flat color-picker-white" onclick="borderColorChanged(this)">白</div>
					</div>
				</li>
				<li class="context-menu__item"><a href="#" class="context-menu__link" id="fillColorPicker">設定填滿色彩<input id="fillColorHex" type="hidden" /></a>
					<div class="sub-context-menu">
						<div class="color-picker-flat color-picker-red" onclick="fillColorChanged(this)">紅</div>
						<div class="color-picker-flat color-picker-orange" onclick="fillColorChanged(this)">橙</div>
						<div class="color-picker-flat color-picker-yellow" onclick="fillColorChanged(this)">黃</div>
						<div class="color-picker-flat color-picker-green" onclick="fillColorChanged(this)">綠</div>
						<div class="color-picker-flat color-picker-blue" onclick="fillColorChanged(this)">藍</div>
						<div class="color-picker-flat color-picker-indigo" onclick="fillColorChanged(this)">靛</div>
						<div class="color-picker-flat color-picker-violet" onclick="fillColorChanged(this)">紫</div>
						<div class="color-picker-flat color-picker-black" onclick="fillColorChanged(this)">黑</div>
						<div class="color-picker-flat color-picker-white" onclick="fillColorChanged(this)">白</div>
					</div>
				</li>
			</ul>
		</nav>	
				
		<nav id="context-menu-connector" class="context-menu">
			<ul class="context-menu__items">
				<li class="context-menu__item"><a href="#" class="context-menu__link" onmousedown="endPointRemove()">刪除連接線</a></li>
			</ul>
		</nav>
		
		<nav id="context-menu-label" class="context-menu">
			<ul class="context-menu__items">
				<li class="context-menu__item"><a href="#" class="context-menu__link" onmousedown="labelRemove()">刪除標籤</a></li>
			</ul>
		</nav>	  

		<nav>
			<div class="btn-toolbar" id="context-menu-textedit">
			</div>
		</nav>

		<div id="tutorialDiv">
	  	<tiles:insertAttribute name="tutorial" />
		</div>
	  
    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog modal-sm">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" id="myModalHeader"></h4>
                </div>
                <div class="modal-body" id="myModalBody"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="myModalButton">Save</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <input type="hidden" id="loadedModel"><input type="hidden" id="uuid">
                </div>
            </div>

        </div>
    </div>	  
	  
  	<div id="footer">
  		<c:import url="/WEB-INF/jsp/common/footer.jsp" />
  	</div>
		      		        
	</body>
	
</html>