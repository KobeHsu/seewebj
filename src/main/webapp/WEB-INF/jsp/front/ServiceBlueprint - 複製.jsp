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

	<div id="drawArea" style="width: 760px;"
		class="panel panel-primary col-md-10">
		<svg height="600" version="1.1" width="800"
			xmlns="http://www.w3.org/2000/svg" id="snapSvg">
			<desc>Created with Snap</desc>
			<defs></defs>
			<text x="0" y="130" class="myLabel">外部互動界線</text>
			<g transform="matrix(1,0,0,1,0,0)">
			<path d="M 20 150 L 750 150 " class="myConnector-dash"
				transform="matrix(1,0,0,1,0,0)"></path>
			<text x="362.421875" y="193" id="group_0000_text" class="myLabel"> </text>
			<circle cx="26" cy="208" r="5" id="group_0000_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="718.84375" cy="208" r="5" id="group_0000_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="372.421875" cy="208" r="5" id="group_0000_point_mid_0"
				class="myMidPoint hide"></circle></g>		
			<text x="0" y="280" class="myLabel">可視界線</text>
			<g transform="matrix(1,0,0,1,0,0)">
			<path d="M 20 300 L 750 300 " class="myConnector-dash"
				transform="matrix(1,0,0,1,0,0)"></path>
			<text x="362.421875" y="193" id="group_0000_text" class="myLabel"> </text>
			<circle cx="26" cy="208" r="5" id="group_0000_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="718.84375" cy="208" r="5" id="group_0000_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="372.421875" cy="208" r="5" id="group_0000_point_mid_0"
				class="myMidPoint hide"></circle></g>
			<text x="0" y="430" class="myLabel">內部互動界線</text>
			<g transform="matrix(1,0,0,1,0,0)">
			<path d="M 20 450 L 750 450 " class="myConnector-dash"
				transform="matrix(1,0,0,1,0,0)"></path>
			<text x="362.421875" y="193" id="group_0000_text" class="myLabel"> </text>
			<circle cx="26" cy="208" r="5" id="group_0000_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="718.84375" cy="208" r="5" id="group_0000_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="372.421875" cy="208" r="5" id="group_0000_point_mid_0"
				class="myMidPoint hide"></circle></g>
		
			<g id="group_0001_g">
			<path d="M 143 47 L 243 47 L 243 107 L 123 107 L 123 67 Z"
				id="group_0001_break" transform="matrix(1,0,0,1,0,0)"
				class="myClippingSquare"></path>
			<text x="160" y="87" id="group_0001_text" class="myLabel">選擇方案</text></g>					
			<g id="group_0002_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 303 49 L 403 49 L 403 109 L 283 109 L 283 69 Z"
				id="group_0002_break" transform="matrix(1,0,0,1,0,0)"
				class="myClippingSquare"></path>
			<text x="330" y="87" id="group_0002_text" class="myLabel">訂購</text></g>			
			<g id="group_0003_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 459 49 L 559 49 L 559 109 L 439 109 L 439 69 Z"
				id="group_0003_break" transform="matrix(1,0,0,1,0,0)"
				class="myClippingSquare"></path>
			<text x="480" y="87" id="group_0003_text" class="myLabel">取得權限</text></g>			
			
			<g id="group_0004_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="121" y="198" width="120" height="60" rx="5" ry="5"
				id="group_0004_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="238.5" cy="200.5" r="5" id="group_0004_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="181" cy="198" r="5" id="group_0004_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="181" cy="258" r="5" id="group_0004_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="121" cy="228" r="5" id="group_0004_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="241" cy="228" r="5" id="group_0004_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="131" y="233" id="group_0004_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">提供適合方案</text></g>			
			<g id="group_0005_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="280" y="198" width="120" height="60" rx="5" ry="5"
				id="group_0005_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="397.5" cy="200.5" r="5" id="group_0005_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="340" cy="198" r="5" id="group_0005_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="340" cy="258" r="5" id="group_0005_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="280" cy="228" r="5" id="group_0005_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="400" cy="228" r="5" id="group_0005_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="290" y="233" id="group_0005_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">訂購服務</text></g>
			<g id="group_0006_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="441" y="198" width="120" height="60" rx="5" ry="5"
				id="group_0006_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="558.5" cy="200.5" r="5" id="group_0006_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="501" cy="198" r="5" id="group_0006_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="501" cy="258" r="5" id="group_0006_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="441" cy="228" r="5" id="group_0006_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="561" cy="228" r="5" id="group_0006_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="451" y="233" id="group_0006_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">提供權限服務</text></g>
			
			<g id="group_0007_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="118" y="348" width="120" height="60" rx="5" ry="5"
				id="group_0007_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="235.5" cy="350.5" r="5" id="group_0007_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="178" cy="348" r="5" id="group_0007_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="178" cy="408" r="5" id="group_0007_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="118" cy="378" r="5" id="group_0007_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="238" cy="378" r="5" id="group_0007_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="128" y="383" id="group_0007_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">存取方案設定</text></g>
			<g id="group_0008_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="275" y="482" width="120" height="60" rx="5" ry="5"
				id="group_0008_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="392.5" cy="484.5" r="5" id="group_0008_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="335" cy="482" r="5" id="group_0008_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="335" cy="542" r="5" id="group_0008_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="275" cy="512" r="5" id="group_0008_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="395" cy="512" r="5" id="group_0008_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="285" y="517" id="group_0008_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">線上刷卡</text></g>
			<g id="group_0009_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="277" y="346" width="120" height="60" rx="5" ry="5"
				id="group_0009_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="394.5" cy="348.5" r="5" id="group_0009_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="337" cy="346" r="5" id="group_0009_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="337" cy="406" r="5" id="group_0009_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="277" cy="376" r="5" id="group_0009_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="397" cy="376" r="5" id="group_0009_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="287" y="381" id="group_0009_text" class="myLabel"
				transform="matrix(1,0,0,1,0,0)">進行訂購處理</text></g>

			<g id="group_0010_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 245 85 L 280 85 " id="group_0010_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="254.421875" y="69.5" id="group_0010_text" class="myLabel"> </text>
			<circle cx="247" cy="85" r="5" id="group_0010_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="281.84375" cy="84" r="5" id="group_0010_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="264.421875" cy="84.5" r="5" id="group_0010_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 276 84 L 276 81 L 281 84 L 281 85 L 276 87 Z"
				id="group_0010_arrow"
				transform="matrix(0.9996,-0.0294,0.0294,0.9996,-2.3481,8.2974)"
				class="myConnector"></path></g>
			<g id="group_0011_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 405 85 L 435 85 " id="group_0011_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="410.421875" y="67.5" id="group_0011_text" class="myLabel"> </text>
			<circle cx="405" cy="82" r="5" id="group_0011_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="435.84375" cy="83" r="5" id="group_0011_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="420.421875" cy="82.5" r="5" id="group_0011_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 430 83 L 430 80 L 435 83 L 435 84 L 430 86 Z"
				id="group_0011_arrow"
				transform="matrix(0.9994,0.0333,-0.0333,0.9994,3.0066,-14.4459)"
				class="myConnector"></path></g>

			<g id="group_0012_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 180 109 L 180 192 " id="group_0012_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="171.34375" y="135.5" id="group_0012_text" class="myLabel"> </text>
			<circle cx="181.84375" cy="109" r="5" id="group_0012_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="180.84375" cy="192" r="5" id="group_0012_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="181.34375" cy="150.5" r="5" id="group_0012_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 175 192 L 175 189 L 180 192 L 180 193 L 175 195 Z"
				id="group_0012_arrow"
				transform="matrix(-0.012,0.9999,-0.9999,-0.012,374.1546,14.3261)"
				class="myConnector"></path></g>
			<g id="group_0013_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 180 257 L 180 345 " id="group_0013_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="170.421875" y="286" id="group_0013_text" class="myLabel"> </text>
			<circle cx="181" cy="257" r="5" id="group_0013_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="179.84375" cy="345" r="5" id="group_0013_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="180.421875" cy="301" r="5" id="group_0013_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 174 345 L 174 342 L 179 345 L 179 346 L 174 348 Z"
				id="group_0013_arrow"
				transform="matrix(-0.0227,0.9997,-0.9997,-0.0227,527.9781,173.8851)"
				class="myConnector"></path></g>			
			
			<g id="group_0014_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 344 110 L 344 195 " id="group_0014_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="333.421875" y="138" id="group_0014_text" class="myLabel"> </text>
			<circle cx="344" cy="110" r="5" id="group_0014_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="342.84375" cy="196" r="5" id="group_0014_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="343.421875" cy="153" r="5" id="group_0014_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 337 196 L 337 193 L 342 196 L 342 197 L 337 199 Z"
				id="group_0014_arrow"
				transform="matrix(-0.0232,0.9997,-0.9997,-0.0232,545.8984,-141.3506)"
				class="myConnector"></path></g>
			<g id="group_0015_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 344 260 L 344 345 " id="group_0015_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="332.421875" y="288" id="group_0015_text" class="myLabel"> </text>
			<circle cx="344" cy="260" r="5" id="group_0015_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="340.84375" cy="346" r="5" id="group_0015_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="342.421875" cy="303" r="5" id="group_0015_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 335 346 L 335 343 L 340 346 L 340 347 L 335 349 Z"
				id="group_0015_arrow"
				transform="matrix(-0.0465,0.9989,-0.9989,-0.0465,701.4232,22.4428)"
				class="myConnector"></path></g>
			<g id="group_0016_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 344 409 L 344 478 " id="group_0016_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="332.921875" y="429" id="group_0016_text" class="myLabel"> </text>
			<circle cx="344" cy="409" r="5" id="group_0016_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="341.84375" cy="479" r="5" id="group_0016_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="342.921875" cy="444" r="5" id="group_0016_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 336 479 L 336 476 L 341 479 L 341 480 L 336 482 Z"
				id="group_0016_arrow"
				transform="matrix(-0.0428,0.9991,-0.9991,-0.0428,834.1616,158.8225)"
				class="myConnector"></path></g>
			
			<g id="group_0017_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 505 109 L 505 197 " id="group_0017_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="495.921875" y="138" id="group_0017_text" class="myLabel"> </text>
			<circle cx="505" cy="109" r="5" id="group_0017_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="506.84375" cy="197" r="5" id="group_0017_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="505.921875" cy="153" r="5" id="group_0017_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 501 197 L 501 194 L 506 197 L 506 198 L 501 200 Z"
				id="group_0017_arrow"
				transform="matrix(0.0114,0.9999,-0.9999,0.0114,697.2377,-311.2058)"
				class="myConnector"></path></g>

		</svg>

	</div>

</section>
