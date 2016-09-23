<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav></nav>
<script>gModelType = "3";</script>
<section id="mainArea">

	<div class="col-md-2 col-sm-2 col-xs-2">
		<div class="panel panel-primary margin-top-lg">
			<div class="panel-heading">
				<h4 class="panel-title">塑模元素</h4>
			</div>

			<div id="component" align="center">
				<img src="<c:url value='/res/images/sm_trigger.png' />"
					onclick="addRect('light')" /> <br />Trigger<br /> <img
					src="<c:url value='/res/images/sm_step.png' />" onclick="addRect()" />
				<br />Step<br /> <img
					src="<c:url value='/res/images/sm_order.png' />"
					onclick="addConnector()" /> <br />Order<br /> <img
					src="<c:url value='/res/images/sm_breakdown.png' />"
					onclick="addBreak()" /> <br />Breakdown<br />
			</div>
		</div>
	</div>

	<div id="drawArea" style="width: 760px;"
		class="panel panel-primary col-md-10">
		<svg height="600" version="1.1" width="800"
			xmlns="http://www.w3.org/2000/svg" id="snapSvg">
				<desc>Created with Snap</desc>
			<defs></defs>
				<g id="group_0000_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="42" y="28" width="196" height="60" rx="5" ry="5"
				id="group_0000_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="235.5" cy="30.5" r="5" id="group_0000_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="140" cy="28" r="5" id="group_0000_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="140" cy="88" r="5" id="group_0000_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="42" cy="58" r="5" id="group_0000_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="238" cy="58" r="5" id="group_0000_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="52" y="63" id="group_0000_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">T: 已確定訂房，想要租車</text></g>
				<g id="group_0001_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="41" y="122" width="197" height="60" rx="5" ry="5"
				id="group_0001_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="235.5" cy="124.5" r="5" id="group_0001_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="139.5" cy="122" r="5" id="group_0001_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="139.5" cy="182" r="5" id="group_0001_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="41" cy="152" r="5" id="group_0001_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="238" cy="152" r="5" id="group_0001_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="51" y="157" id="group_0001_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">連結租車公司網站</text></g>
				<g id="group_0002_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="41" y="208" width="195" height="60" rx="5" ry="5"
				id="group_0002_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="233.5" cy="210.5" r="5" id="group_0002_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="138.5" cy="208" r="5" id="group_0002_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="138.5" cy="268" r="5" id="group_0002_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="41" cy="238" r="5" id="group_0002_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="236" cy="238" r="5" id="group_0002_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="51" y="243" id="group_0002_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">查詢WISH日租價格</text></g>
				<g id="group_0003_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="36" y="343" width="196" height="60" rx="5" ry="5"
				id="group_0003_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="229.5" cy="345.5" r="5" id="group_0003_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="134" cy="343" r="5" id="group_0003_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="134" cy="403" r="5" id="group_0003_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="36" cy="373" r="5" id="group_0003_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="232" cy="373" r="5" id="group_0003_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="46" y="378" id="group_0003_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">連結拍賣網站，搜尋租車WISH</text></g>			
				<g id="group_0004_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 76 259 L 126 309 L 106 309 L 166 359 L 116 309 Z"
				id="group_0004_break" transform="matrix(1,0,0,1,0,0)"
				class="myBreak"></path></g>
				<g id="group_0005_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="35" y="442" width="196" height="60" rx="5" ry="5"
				id="group_0005_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="228.5" cy="444.5" r="5" id="group_0005_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="133" cy="442" r="5" id="group_0005_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="133" cy="502" r="5" id="group_0005_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="35" cy="472" r="5" id="group_0005_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="231" cy="472" r="5" id="group_0005_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="45" y="477" id="group_0005_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">瀏覽搜尋結果，並留意價格</text></g>				
				<g id="group_0006_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="376" y="70" width="195" height="60" rx="5" ry="5"
				id="group_0006_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="568.5" cy="72.5" r="5" id="group_0006_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="473.5" cy="70" r="5" id="group_0006_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="473.5" cy="130" r="5" id="group_0006_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="376" cy="100" r="5" id="group_0006_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="571" cy="100" r="5" id="group_0006_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="386" y="105" id="group_0006_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">觀看賣家評價</text></g>				
				<g id="group_0007_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="375" y="218" width="196" height="60" rx="5" ry="5"
				id="group_0007_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="568.5" cy="220.5" r="5" id="group_0007_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="473" cy="218" r="5" id="group_0007_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="473" cy="278" r="5" id="group_0007_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="375" cy="248" r="5" id="group_0007_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="571" cy="248" r="5" id="group_0007_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="385" y="253" id="group_0007_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">於問與答提問，確認賣家可租車</text></g>				
				<g id="group_0008_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="375" y="309" width="196" height="60" rx="5" ry="5"
				id="group_0008_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="568.5" cy="311.5" r="5" id="group_0008_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="473" cy="309" r="5" id="group_0008_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="473" cy="369" r="5" id="group_0008_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="375" cy="339" r="5" id="group_0008_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="571" cy="339" r="5" id="group_0008_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="385" y="344" id="group_0008_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">賣家回覆，確認交易</text></g>
				<g id="group_0009_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="376" y="403" width="196" height="60" rx="5" ry="5"
				id="group_0009_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="569.5" cy="405.5" r="5" id="group_0009_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="474" cy="403" r="5" id="group_0009_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="474" cy="463" r="5" id="group_0009_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="376" cy="433" r="5" id="group_0009_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="572" cy="433" r="5" id="group_0009_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="386" y="438" id="group_0009_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">列印匯款所需書面資料，並攜回家</text></g>
				<g id="group_0010_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 138 88 L 138 120 " id="group_0010_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="126.171875" y="89.5" id="group_0010_text" class="myLabel"> </text>
			<circle cx="136.171875" cy="89" r="5" id="group_0010_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="136.171875" cy="120" r="5" id="group_0010_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="136.171875" cy="104.5" r="5" id="group_0010_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 131 120 L 131 117 L 136 120 L 136 121 L 131 123 Z"
				id="group_0010_arrow" transform="matrix(0,1,-1,0,256,-16)"
				class="myConnector"></path></g>
				<g id="group_0011_g">
			<path d="M 138 180 L 138 206 " id="group_0011_connector"
				class="myConnector"></path>
			<text x="125.171875" y="178" id="group_0011_text" class="myLabel"> </text>
			<circle cx="135.171875" cy="180" r="5" id="group_0011_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="135.171875" cy="206" r="5" id="group_0011_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="135.171875" cy="193" r="5" id="group_0011_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 130 206 L 130 203 L 135 206 L 135 207 L 130 209 Z"
				id="group_0011_arrow" transform="matrix(0,1,-1,0,341,71)"
				class="myConnector"></path></g>				
				<g id="group_0012_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 138 268 L 138 343 " id="group_0012_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="128.0859375" y="290.5" id="group_0012_text" class="myLabel">價格太高</text>
			<circle cx="139" cy="268" r="5" id="group_0012_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="137.171875" cy="343" r="5" id="group_0012_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="138.0859375" cy="305.5" r="5" id="group_0012_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 132 343 L 132 340 L 137 343 L 137 344 L 132 346 Z"
				id="group_0012_arrow"
				transform="matrix(-0.0267,0.9996,-0.9996,-0.0267,483.5301,215.1921)"
				class="myConnector"></path></g>
				<g id="group_0013_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 138 403 L 138 440 " id="group_0013_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="127.0859375" y="406.5" id="group_0013_text" class="myLabel"> </text>
			<circle cx="137" cy="403" r="5" id="group_0013_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="137.171875" cy="440" r="5" id="group_0013_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="137.0859375" cy="421.5" r="5" id="group_0013_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 132 440 L 132 437 L 137 440 L 137 441 L 132 443 Z"
				id="group_0013_arrow" transform="matrix(0,1,-1,0,577,303)"
				class="myConnector"></path></g>				
				<g id="group_0014_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 476 130 L 476 215 " id="group_0014_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="466.0859375" y="185.5" id="group_0014_text" class="myLabel">評價太低的賣家可能有風險</text>
			<circle cx="476" cy="186" r="5" id="group_0014_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="476.171875" cy="215" r="5" id="group_0014_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="476.0859375" cy="200.5" r="5" id="group_0014_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 471 215 L 471 212 L 476 215 L 476 216 L 471 218 Z"
				id="group_0014_arrow" transform="matrix(0,1,-1,0,691,-261)"
				class="myConnector"></path></g>			
				<g id="group_0015_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 476 278 L 476 305 " id="group_0015_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="469.171875" y="276.5" id="group_0015_text" class="myLabel"> </text>
			<circle cx="480.171875" cy="278" r="5" id="group_0015_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="478.171875" cy="305" r="5" id="group_0015_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="479.171875" cy="291.5" r="5" id="group_0015_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 473 305 L 473 302 L 478 305 L 478 306 L 473 308 Z"
				id="group_0015_arrow"
				transform="matrix(-0.0739,0.9973,-0.9973,-0.0739,817.4773,-149.1631)"
				class="myConnector"></path></g>				
				<g id="group_0016_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 476 369 L 476 403 " id="group_0016_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="467.171875" y="371" id="group_0016_text" class="myLabel"> </text>
			<circle cx="477.171875" cy="369" r="5" id="group_0016_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="477.171875" cy="403" r="5" id="group_0016_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="477.171875" cy="386" r="5" id="group_0016_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 472 403 L 472 400 L 477 403 L 477 404 L 472 406 Z"
				id="group_0016_arrow" transform="matrix(0,1,-1,0,880,-74)"
				class="myConnector"></path></g>			
				<g id="group_0017_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 434 123 L 484 173 L 464 173 L 524 223 L 474 173 Z"
				id="group_0017_break" transform="matrix(1,0,0,1,0,0)"
				class="myBreak"></path></g>
				<g id="group_0018_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 137 505 L 137 546 L 340 546 L 340 25 L 475 25 L 475 67 "
				id="group_0018_connector" class="myConnector"
				transform="matrix(1,0,0,1,0,0)"></path>
			<text x="325.653499858181" y="302.9862251035843" id="group_0018_text"
				class="myLabel"> </text>
			<circle cx="137" cy="505" r="5" id="group_0018_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="136.171875" cy="546" r="5" id="group_0018_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="136.5859375" cy="525.5" r="5" id="group_0018_point_mid_0"
				class="myMidPoint hide"></circle>
			<circle cx="339.171875" cy="548" r="5" id="group_0018_point_end_2"
				class="myEndPoint hide"></circle>
			<circle cx="237.671875" cy="547" r="5" id="group_0018_point_mid_1"
				class="myMidPoint hide"></circle>
			<circle cx="331.171875" cy="25" r="5" id="group_0018_point_end_3"
				class="myEndPoint hide"></circle>
			<circle cx="335.171875" cy="286.5" r="5" id="group_0018_point_mid_2"
				class="myMidPoint hide"></circle>
			<circle cx="471.171875" cy="26" r="5" id="group_0018_point_end_4"
				class="myEndPoint hide"></circle>
			<circle cx="401.171875" cy="25.5" r="5" id="group_0018_point_mid_3"
				class="myMidPoint hide"></circle>
			<circle cx="473.171875" cy="67" r="5" id="group_0018_point_end_5"
				class="myEndPoint hide"></circle>
			<circle cx="472.171875" cy="46.5" r="5" id="group_0018_point_mid_4"
				class="myMidPoint hide"></circle>
			<path d="M 468 67 L 468 64 L 473 67 L 473 68 L 468 70 Z"
				id="group_0018_arrow"
				transform="matrix(0.0487,0.9988,-0.9988,0.0487,516.8747,-408.7027)"
				class="myConnector"></path></g>				
			</svg>
	</div>

</section>
