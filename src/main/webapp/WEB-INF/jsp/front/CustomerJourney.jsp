<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav></nav>
<script>gModelType = "7";</script>
<section id="mainArea">

	<div class="col-md-2 col-sm-2 col-xs-2">
		<div class="panel panel-primary margin-top-lg">
			<div class="panel-heading">
				<h4 class="panel-title">塑模元素</h4>
			</div>

			<div id="component" align="center">
				<img src="<c:url value='/res/images/cjm_service.png' />" onclick="addRect()" /> <br />服務接觸點<br /> 
				<img src="<c:url value='/res/images/cjm_order.png' />" onclick="addConnector()" /> <br />流程方向<br /> 
				<img src="<c:url value='/res/images/cjm_experiencecircle.png' />" onclick="addEllipse('circle')" /> <br />接觸點細節<br />
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
			<rect x="39" y="89" width="100" height="60" rx="5" ry="5"
				id="group_0000_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="135.5" cy="89.5" r="5" id="group_0000_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="77.5" cy="87" r="5" id="group_0000_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="77.5" cy="147" r="5" id="group_0000_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="17" cy="117" r="5" id="group_0000_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="138" cy="117" r="5" id="group_0000_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="50" y="122" id="group_0000_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">詢問適合方案</text></g>
					<g id="group_0001_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="200" y="89" width="100" height="60" rx="5" ry="5"
				id="group_0001_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="317.5" cy="91.5" r="5" id="group_0001_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="260" cy="89" r="5" id="group_0001_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="260" cy="149" r="5" id="group_0001_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="200" cy="119" r="5" id="group_0001_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="320" cy="119" r="5" id="group_0001_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="210" y="124" id="group_0001_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">購買適合方案</text></g>
					<g id="group_0002_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="376" y="89" width="100" height="60" rx="5" ry="5"
				id="group_0002_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="493.5" cy="91.5" r="5" id="group_0002_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="436" cy="89" r="5" id="group_0002_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="436" cy="149" r="5" id="group_0002_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="376" cy="119" r="5" id="group_0002_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="496" cy="119" r="5" id="group_0002_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="400" y="124" id="group_0002_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">確認</text></g>
					<g id="group_0003_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="535" y="89" width="100" height="60" rx="5" ry="5"
				id="group_0003_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="652.5" cy="91.5" r="5" id="group_0003_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="595" cy="89" r="5" id="group_0003_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="595" cy="149" r="5" id="group_0003_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="535" cy="119" r="5" id="group_0003_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="655" cy="119" r="5" id="group_0003_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="555" y="124" id="group_0003_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">開始行程</text></g>
					<g id="group_0004_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 138 120 L 197.84375 120 " id="group_0004_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="157.921875" y="105" id="group_0004_text" class="myLabel"></text>
			<circle cx="138" cy="120" r="5" id="group_0004_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="197.84375" cy="120" r="5" id="group_0004_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="167.921875" cy="120" r="5" id="group_0004_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 192 120 L 192 117 L 197 120 L 197 121 L 192 123 Z"
				id="group_0004_arrow" transform="matrix(1,0,0,1,0,0)"
				class="myConnector"></path></g>
					<g id="group_0005_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 301 120 L 372.84375 119 " id="group_0005_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="326.921875" y="104.5" id="group_0005_text" class="myLabel"></text>
			<circle cx="301" cy="120" r="5" id="group_0005_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="372.84375" cy="119" r="5" id="group_0005_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="336.921875" cy="119.5" r="5" id="group_0005_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 367 119 L 367 116 L 372 119 L 372 120 L 367 122 Z"
				id="group_0005_arrow"
				transform="matrix(0.9999,-0.0141,0.0141,0.9999,-1.639,5.2507)"
				class="myConnector"></path></g>
					<g id="group_0006_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 478 117 L 534.84375 116 " id="group_0006_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="496.421875" y="101.5" id="group_0006_text" class="myLabel"></text>
			<circle cx="478" cy="117" r="5" id="group_0006_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="534.84375" cy="116" r="5" id="group_0006_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="506.421875" cy="116.5" r="5" id="group_0006_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 529 116 L 529 113 L 534 116 L 534 117 L 529 119 Z"
				id="group_0006_arrow"
				transform="matrix(0.9998,-0.0179,0.0179,0.9998,-1.986,9.5527)"
				class="myConnector"></path></g>
					
					<g id="group_0007_g" transform="matrix(1,0,0,1,0,0)">
			<ellipse cx="256" cy="270" rx="40" ry="40" id="group_0007_ellipse"
				class="myEllipse" transform="matrix(1,0,0,1,0,0)"></ellipse>
			<circle cx="293.44032430787433" cy="232.5" r="5"
				id="group_0007_close" class="myClose hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="255.9403243078743" cy="230" r="5" id="group_0007_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="255.9403243078743" cy="310" r="5" id="group_0007_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="215.9403243078743" cy="270" r="5" id="group_0007_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="295.94032430787433" cy="270" r="5"
				id="group_0007_eResize" class="myEResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="225.9403243078743" y="275" id="group_0007_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">詢問方案</text></g>					
					<g id="group_0008_g" transform="matrix(1,0,0,1,0,0)">
			<ellipse cx="156" cy="352" rx="40" ry="40" id="group_0008_ellipse"
				class="myEllipse" transform="matrix(1,0,0,1,0,0)"></ellipse>
			<circle cx="193.44032430787433" cy="314.5" r="5"
				id="group_0008_close" class="myClose hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="155.94032430787433" cy="312" r="5"
				id="group_0008_nResize" class="myNResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="155.94032430787433" cy="392" r="5"
				id="group_0008_sResize" class="mySResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="115.94032430787432" cy="352" r="5"
				id="group_0008_wResize" class="myWResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="195.94032430787433" cy="352" r="5"
				id="group_0008_eResize" class="myEResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="125.94032430787432" y="357" id="group_0008_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">與朋友分享</text></g>
					<g id="group_0009_g" transform="matrix(1,0,0,1,0,0)">
			<ellipse cx="368" cy="341" rx="40" ry="40" id="group_0009_ellipse"
				class="myEllipse" transform="matrix(1,0,0,1,0,0)"></ellipse>
			<circle cx="405.4403243078743" cy="303.5" r="5" id="group_0009_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="367.9403243078743" cy="301" r="5" id="group_0009_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="367.9403243078743" cy="381" r="5" id="group_0009_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="327.9403243078743" cy="341" r="5" id="group_0009_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="407.9403243078743" cy="341" r="5" id="group_0009_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="355" y="346" id="group_0009_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">簽約</text></g>
					<g id="group_0010_g" transform="matrix(1,0,0,1,0,0)">
			<ellipse cx="199" cy="465" rx="40" ry="40" id="group_0010_ellipse"
				class="myEllipse" transform="matrix(1,0,0,1,0,0)"></ellipse>
			<circle cx="236.44032430787433" cy="427.5" r="5"
				id="group_0010_close" class="myClose hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="198.94032430787433" cy="425" r="5"
				id="group_0010_nResize" class="myNResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="198.94032430787433" cy="505" r="5"
				id="group_0010_sResize" class="mySResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="158.94032430787433" cy="465" r="5"
				id="group_0010_wResize" class="myWResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="238.94032430787433" cy="465" r="5"
				id="group_0010_eResize" class="myEResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="165" y="470" id="group_0010_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">支付訂購款項</text></g>
					<g id="group_0011_g" transform="matrix(1,0,0,1,0,0)">
			<ellipse cx="328" cy="474" rx="40" ry="40" id="group_0011_ellipse"
				class="myEllipse" transform="matrix(1,0,0,1,0,0)"></ellipse>
			<circle cx="365.44032430787433" cy="436.5" r="5"
				id="group_0011_close" class="myClose hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="327.94032430787433" cy="434" r="5"
				id="group_0011_nResize" class="myNResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="327.94032430787433" cy="514" r="5"
				id="group_0011_sResize" class="mySResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="287.94032430787433" cy="474" r="5"
				id="group_0011_wResize" class="myWResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="367.94032430787433" cy="474" r="5"
				id="group_0011_eResize" class="myEResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="295" y="479" id="group_0011_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">詢問客服人員</text></g>		
					
					<g id="group_0012_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 253 150 L 252.84375 229 " id="group_0012_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="242.921875" y="174.5" id="group_0012_text" class="myLabel"> </text>
			<circle cx="253" cy="150" r="5" id="group_0012_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="252.84375" cy="229" r="5" id="group_0012_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="252.921875" cy="189.5" r="5" id="group_0012_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 247 229 L 247 226 L 252 229 L 252 230 L 247 232 Z"
				id="group_0012_arrow"
				transform="matrix(-0.0127,0.9999,-0.9999,-0.0127,484.1713,-20.0813)"
				class="myConnector"></path></g>					
					<g id="group_0013_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 224 294 L 185.84375 322 " id="group_0013_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="194.921875" y="293" id="group_0013_text" class="myLabel"> </text>
			<circle cx="224" cy="294" r="5" id="group_0013_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="185.84375" cy="322" r="5" id="group_0013_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="204.921875" cy="308" r="5" id="group_0013_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 180 322 L 180 319 L 185 322 L 185 323 L 180 325 Z"
				id="group_0013_arrow"
				transform="matrix(-0.8123,0.5832,-0.5832,-0.8123,523.0725,475.675)"
				class="myConnector"></path></g>		
					<g id="group_0014_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 170 391 L 179.84375 427 " id="group_0014_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="164.921875" y="394" id="group_0014_text" class="myLabel"> </text>
			<circle cx="170" cy="391" r="5" id="group_0014_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="179.84375" cy="427" r="5" id="group_0014_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="174.921875" cy="409" r="5" id="group_0014_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 174 427 L 174 424 L 179 427 L 179 428 L 174 430 Z"
				id="group_0014_arrow"
				transform="matrix(0.2425,0.9701,-0.9701,0.2425,549.837,149.7818)"
				class="myConnector"></path></g>					
					<g id="group_0015_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 241 472 L 285.84375 472 " id="group_0015_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="253.421875" y="457" id="group_0015_text" class="myLabel"> </text>
			<circle cx="241" cy="472" r="5" id="group_0015_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="285.84375" cy="472" r="5" id="group_0015_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="263.421875" cy="472" r="5" id="group_0015_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 280 472 L 280 469 L 285 472 L 285 473 L 280 475 Z"
				id="group_0015_arrow" transform="matrix(1,0,0,1,0,0)"
				class="myConnector"></path></g>					
					<g id="group_0016_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 342 436 L 364.84375 386 " id="group_0016_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="343.421875" y="396" id="group_0016_text" class="myLabel"> </text>
			<circle cx="342" cy="436" r="5" id="group_0016_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="364.84375" cy="386" r="5" id="group_0016_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="353.421875" cy="411" r="5" id="group_0016_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 359 386 L 359 383 L 364 386 L 364 387 L 359 389 Z"
				id="group_0016_arrow"
				transform="matrix(0.4027,-0.9153,0.9153,0.4027,-135.9085,563.7176)"
				class="myConnector"></path></g>					
					<g id="group_0017_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 348 310 L 295.84375 279 " id="group_0017_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="311.921875" y="279.5" id="group_0017_text" class="myLabel"> </text>
			<circle cx="348" cy="310" r="5" id="group_0017_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="295.84375" cy="279" r="5" id="group_0017_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="321.921875" cy="294.5" r="5" id="group_0017_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 290 279 L 290 276 L 295 279 L 295 280 L 290 282 Z"
				id="group_0017_arrow"
				transform="matrix(-0.8632,-0.5049,0.5049,-0.8632,408.7779,668.7699)"
				class="myConnector"></path></g>					
		
	</div>

</section>
