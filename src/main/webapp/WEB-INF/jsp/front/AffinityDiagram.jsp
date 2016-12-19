<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav></nav>
<script>gModelType = "6";</script>
<section id="mainArea">

	<div class="col-md-2 col-sm-2 col-xs-2">
		<div class="panel panel-primary margin-top-lg">
			<div class="panel-heading">
				<h4 class="panel-title">塑模元素</h4>
			</div>

			<div id="component" align="center">
				<img src="<c:url value='/res/images/af_category.png' />"
					onclick="addRect()" /> <br />Category<br /> <img
					src="<c:url value='/res/images/af_idea.png' />"
					onclick="addRect('small')" /> <br />Idea<br />
			</div>

		</div>
	</div>

	<div id="drawArea"
		class="panel panel-primary col-md-10">
		<svg height="600" version="1.1" width="800"
			xmlns="http://www.w3.org/2000/svg" id="snapSvg">
			<desc>Created with Snap</desc>
			<defs></defs>
			<g id="group_0000_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="17" y="64" width="163" height="256" rx="5" ry="5"
				id="group_0000_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="177.5" cy="66.5" r="5" id="group_0000_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="98.5" cy="64" r="5" id="group_0000_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="98.5" cy="320" r="5" id="group_0000_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="17" cy="192" r="5" id="group_0000_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="180" cy="192" r="5" id="group_0000_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="60" y="90" id="group_0000_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">延伸行程建議</text>
			<rect x="17" y="64" width="163" height="256" rx="5" ry="5"
				id="group_0000_selected" class="mySelected hide"
				transform="matrix(1,0,0,1,0,0)"></rect></g>
			<g id="group_0001_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="39" y="143" width="100" height="40" rx="5" ry="5"
				id="group_0001_rect" class="myRect2" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="131.5" cy="145.5" r="5" id="group_0001_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="94" cy="143" r="5" id="group_0001_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="94" cy="183" r="5" id="group_0001_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="54" cy="163" r="5" id="group_0001_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="134" cy="163" r="5" id="group_0001_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="64" y="168" id="group_0001_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">加購行程</text>
			<rect x="39" y="143" width="100" height="40" rx="5" ry="5"
				id="group_0001_selected" class="mySelected hide"
				transform="matrix(1,0,0,1,0,0)"></rect></g>
			<g id="group_0002_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="37" y="225" width="100" height="40" rx="5" ry="5"
				id="group_0002_rect" class="myRect2" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="129.5" cy="227.5" r="5" id="group_0002_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="92" cy="225" r="5" id="group_0002_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="92" cy="265" r="5" id="group_0002_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="52" cy="245" r="5" id="group_0002_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="132" cy="245" r="5" id="group_0002_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="62" y="250" id="group_0002_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">購物行程</text>
			<rect x="37" y="225" width="100" height="40" rx="5" ry="5"
				id="group_0002_selected" class="mySelected hide"
				transform="matrix(1,0,0,1,0,0)"></rect></g>
			<g id="group_0003_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="213" y="67" width="163" height="257" rx="5" ry="5"
				id="group_0003_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="373.5" cy="69.5" r="5" id="group_0003_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="294.5" cy="67" r="5" id="group_0003_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="294.5" cy="324" r="5" id="group_0003_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="213" cy="195.5" r="5" id="group_0003_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="376" cy="195.5" r="5" id="group_0003_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="253" y="92" id="group_0003_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">顧客遇到之問題</text>
			<rect x="213" y="67" width="163" height="257" rx="5" ry="5"
				id="group_0003_selected" class="mySelected hide"
				transform="matrix(1,0,0,1,0,0)"></rect></g>
			<g id="group_0004_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="240" y="142" width="100" height="40" rx="5" ry="5"
				id="group_0004_rect" class="myRect2" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="332.5" cy="144.5" r="5" id="group_0004_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="295" cy="142" r="5" id="group_0004_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="295" cy="182" r="5" id="group_0004_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="255" cy="162" r="5" id="group_0004_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="335" cy="162" r="5" id="group_0004_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="255" y="167" id="group_0004_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">旅客住宿問題</text>
			<rect x="240" y="142" width="100" height="40" rx="5" ry="5"
				id="group_0004_selected" class="mySelected hide"
				transform="matrix(1,0,0,1,0,0)"></g>
			<g id="group_0005_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="238" y="220" width="100" height="40" rx="5" ry="5"
				id="group_0005_rect" class="myRect2" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="330.5" cy="222.5" r="5" id="group_0005_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="293" cy="220" r="5" id="group_0005_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="293" cy="260" r="5" id="group_0005_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="253" cy="240" r="5" id="group_0005_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="333" cy="240" r="5" id="group_0005_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="263" y="245" id="group_0005_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">行程更動</text>
			<rect x="238" y="220" width="100" height="40" rx="5" ry="5"
				id="group_0005_selected" class="mySelected hide"
				transform="matrix(1,0,0,1,0,0)"></rect></g></svg>
	</div>

</section>
