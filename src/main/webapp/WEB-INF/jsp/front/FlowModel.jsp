<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav></nav>
<script>gModelType = "1";</script>
<section id="mainArea">

	<div class="col-md-2 col-sm-2 col-xs-2">
		<div class="panel panel-primary margin-top-lg">
			<div class="panel-heading">
				<h4 class="panel-title">塑模元素</h4>
			</div>

			<div id="component" align="center">
				<img src="<c:url value='/res/images/fm_individual.png' />"
					onclick="addEllipse()" /> <br />Individual/Group<br /> <img
					src="<c:url value='/res/images/fm_place.png' />"
					onclick="addRect()" /> <br />Place<br /> <img
					src="<c:url value='/res/images/fm_artifact.png' />"
					onclick="addRect('small')" /> <br />Artifact<br /> <img
					src="<c:url value='/res/images/fm_communication.png' />"
					onclick="addConnector()" /> <br />Communication<br /> <img
					src="<c:url value='/res/images/fm_breakdown.png' />"
					onclick="addBreak()" /> <br />Breakdown<br />
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
			<ellipse cx="362" cy="267" rx="89.5" ry="39.5"
				id="group_0000_ellipse" class="myEllipse"
				transform="matrix(1,0,0,1,0,0)"></ellipse>
			<circle cx="447.8664756388688" cy="230" r="5" id="group_0000_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="361.3664756388688" cy="227.5" r="5"
				id="group_0000_nResize" class="myNResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="361.3664756388688" cy="305.5" r="5"
				id="group_0000_sResize" class="mySResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="272.3664756388688" cy="266.5" r="5"
				id="group_0000_wResize" class="myWResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="450.3664756388688" cy="266.5" r="5"
				id="group_0000_eResize" class="myEResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<rect x="272.3664756388688" y="227.5" width="179.26704872226247"
				height="79" id="group_0000_selected" class="mySelected hide"
				transform="matrix(1,0,0,1,0,0)"></rect>
			<foreignObject width="40.05967569212567" x="272.3664756388688"
				y="234" id="group_0000_label" transform="matrix(1,0,0,1,0,0)">
			<div>
				<div contenteditable="true"
					style="width: 169.267px; text-align: center;" placeholder="label">陳先生(旅遊方案訂購)</div>
				<ul placeholder="label">
					<li contenteditable="true">與朋友討論</li>
					<li contenteditable="true">查詢適合方案</li>
				</ul>
			</div></foreignObject></g>
			<g id="group_0001_g" type="null" transform="matrix(1,0,0,1,0,0)">
			<ellipse cx="184" cy="406" rx="89.5" ry="39.5"
				id="group_0001_ellipse" class="myEllipse"
				transform="matrix(1,0,0,1,0,0)"></ellipse>
			<circle cx="269.8664756388688" cy="369" r="5" id="group_0001_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="183.3664756388688" cy="366.5" r="5"
				id="group_0001_nResize" class="myNResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="183.3664756388688" cy="444.5" r="5"
				id="group_0001_sResize" class="mySResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="94.36647563886879" cy="405.5" r="5"
				id="group_0001_wResize" class="myWResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="272.3664756388688" cy="405.5" r="5"
				id="group_0001_eResize" class="myEResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<rect x="94.36647563886879" y="366.5" width="179.26704872226247"
				height="79" id="group_0001_selected" class="mySelected hide"
				transform="matrix(1,0,0,1,0,0)"></rect>
			<foreignObject width="40.05967569212567" x="94.36647563886879"
				y="399" id="group_0001_label" transform="matrix(1,0,0,1,0,0)">
			<div>
				<div contenteditable="true"
					style="width: 169.267px; text-align: center;">李先生</div>
			</div></foreignObject></g>
			<g id="group_0002_g" type="null" transform="matrix(1,0,0,1,0,0)">
			<ellipse cx="599" cy="121" rx="89.5" ry="39.5"
				id="group_0002_ellipse" class="myEllipse"
				transform="matrix(1,0,0,1,0,0)"></ellipse>
			<circle cx="684.8664756388688" cy="84" r="5" id="group_0002_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="598.3664756388688" cy="81.5" r="5"
				id="group_0002_nResize" class="myNResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="598.3664756388688" cy="159.5" r="5"
				id="group_0002_sResize" class="mySResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="509.3664756388688" cy="120.5" r="5"
				id="group_0002_wResize" class="myWResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="687.3664756388688" cy="120.5" r="5"
				id="group_0002_eResize" class="myEResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<rect x="509.3664756388688" y="81.5" width="179.26704872226247"
				height="79" id="group_0002_selected" class="mySelected hide"
				transform="matrix(1,0,0,1,0,0)"></rect>
			<foreignObject width="40.05967569212567" x="509.3664756388688" y="88"
				id="group_0002_label" transform="matrix(1,0,0,1,0,0)">
			<div>
				<div contenteditable="true"
					style="width: 169.267px; text-align: center;" placeholder="label">同事A</div>
				<ul placeholder="label">
					<li contenteditable="true">提供過去經驗</li>
					<li contenteditable="true">參與討論</li>
				</ul>
			</div></foreignObject></g>
			<g id="group_0003_g" type="null" transform="matrix(1,0,0,1,0,0)">
			<ellipse cx="601" cy="367" rx="89.5" ry="39.5"
				id="group_0003_ellipse" class="myEllipse"
				transform="matrix(1,0,0,1,0,0)"></ellipse>
			<circle cx="686.8664756388688" cy="330" r="5" id="group_0003_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="600.3664756388688" cy="327.5" r="5"
				id="group_0003_nResize" class="myNResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="600.3664756388688" cy="405.5" r="5"
				id="group_0003_sResize" class="mySResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="511.3664756388688" cy="366.5" r="5"
				id="group_0003_wResize" class="myWResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="689.3664756388688" cy="366.5" r="5"
				id="group_0003_eResize" class="myEResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<rect x="511.3664756388688" y="327.5" width="179.26704872226247"
				height="79" id="group_0003_selected" class="mySelected hide"
				transform="matrix(1,0,0,1,0,0)"></rect>
			<foreignObject width="40.05967569212567" x="511.3664756388688"
				y="334" id="group_0003_label" transform="matrix(1,0,0,1,0,0)">
			<div>
				<div contenteditable="true"
					style="width: 169.267px; text-align: center;">家人</div>
				<ul>
					<li contenteditable="true" style="text-align: left;">提供訂購經驗</li>
					<li contenteditable="true">提供使用經驗</li>
				</ul>
			</div></foreignObject></g>
			<g id="group_0004_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="58" y="109" width="141" height="70" rx="5" ry="5"
				id="group_0004_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="196.5" cy="111.5" r="5" id="group_0004_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="128.5" cy="109" r="5" id="group_0004_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="128.5" cy="179" r="5" id="group_0004_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="58" cy="144" r="5" id="group_0004_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="199" cy="144" r="5" id="group_0004_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<rect x="58" y="109" width="141" height="70" id="group_0004_selected"
				class="mySelected hide" transform="matrix(1,0,0,1,0,0)"></rect>
			<foreignObject width="60" x="58" y="111" id="group_0004_label"
				transform="matrix(1,0,0,1,0,0)">
			<div>
				<div contenteditable="true"
					style="width: 131px; text-align: center;">官方網站</div>
				<ul>
					<li contenteditable="true">提供適合方案</li>
					<li contenteditable="true">提供贈品</li>
				</ul>
			</div></foreignObject></g>
			<g id="group_0005_g" type="undefined" transform="matrix(1,0,0,1,0,0)">
			<path d="M 174 195 L 269 239 " id="group_0005_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<circle cx="276.5" cy="187.5" r="5" id="group_0005_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<rect x="164" y="185" width="115" height="64"
				id="group_0005_selected" class="mySelected hide"
				transform="matrix(1,0,0,1,0,0)"></rect>
			<foreignObject width="100" x="211.5" y="202" id="group_0005_label">
			<div>
				<div contenteditable="true" style="width: 90px; text-align: center"></div>
			</div></foreignObject>
			<circle cx="174" cy="195" r="5" id="group_0005_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="269" cy="239" r="5" id="group_0005_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="221.5" cy="217" r="5" id="group_0005_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 264 239 L 264 236 L 269 239 L 269 240 L 264 242 Z"
				id="group_0005_arrow"
				transform="matrix(0.9074,0.4203,-0.4203,0.9074,125.3539,-90.9209)"
				class="myConnector"></path></g>
			<g id="group_0006_g" type="undefined">
			<path d="M 257 257 L 155 211 " id="group_0006_connector"
				class="myConnector"></path>
			<circle cx="264.5" cy="203.5" r="5" id="group_0006_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<rect x="145" y="201" width="122" height="66"
				id="group_0006_selected" class="mySelected hide"
				transform="matrix(1,0,0,1,0,0)"></rect>
			<foreignObject width="100" x="196" y="219" id="group_0006_label">
			<div>
				<div contenteditable="true" style="width: 90px; text-align: center"></div>
			</div></foreignObject>
			<circle cx="257" cy="257" r="5" id="group_0006_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="155" cy="211" r="5" id="group_0006_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="206" cy="234" r="5" id="group_0006_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 150 211 L 150 208 L 155 211 L 155 212 L 150 214 Z"
				id="group_0006_arrow"
				transform="matrix(-0.9116,-0.4111,0.4111,-0.9116,209.5522,467.0665)"
				class="myConnector"></path></g>
			<g id="group_0007_g" type="undefined" transform="matrix(1,0,0,1,0,0)">
			<path d="M 279 305 L 225 355 " id="group_0007_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<circle cx="286.5" cy="297.5" r="5" id="group_0007_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<rect x="215" y="295" width="74" height="70" id="group_0007_selected"
				class="mySelected hide" transform="matrix(1,0,0,1,0,0)"></rect>
			<foreignObject width="100" x="242" y="315" id="group_0007_label">
			<div>
				<div contenteditable="true" style="width: 90px; text-align: center"></div>
			</div></foreignObject>
			<circle cx="279" cy="305" r="5" id="group_0007_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="225" cy="355" r="5" id="group_0007_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="252" cy="330" r="5" id="group_0007_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 220 355 L 220 352 L 225 355 L 225 356 L 220 358 Z"
				id="group_0007_arrow"
				transform="matrix(-0.7338,0.6794,-0.6794,-0.7338,631.286,462.6182)"
				class="myConnector"></path></g>
			<g id="group_0008_g" type="undefined" transform="matrix(1,0,0,1,0,0)">
			<path d="M 256 363 L 307 316 " id="group_0008_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<circle cx="314.5" cy="308.5" r="5" id="group_0008_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<rect x="246" y="306" width="71" height="67" id="group_0008_selected"
				class="mySelected hide" transform="matrix(1,0,0,1,0,0)"></rect>
			<foreignObject width="100" x="271.5" y="324.5" id="group_0008_label">
			<div>
				<div contenteditable="true" style="width: 90px; text-align: center"></div>
			</div></foreignObject>
			<circle cx="256" cy="363" r="5" id="group_0008_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="307" cy="316" r="5" id="group_0008_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="281.5" cy="339.5" r="5" id="group_0008_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 302 316 L 302 313 L 307 316 L 307 317 L 302 319 Z"
				id="group_0008_arrow"
				transform="matrix(0.7354,-0.6777,0.6777,0.7354,-132.9015,291.6756)"
				class="myConnector"></path></g>
			<g id="group_0009_g" type="undefined" transform="matrix(1,0,0,1,0,0)">
			<path d="M 434 310 L 507 346 " id="group_0009_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<circle cx="514.5" cy="302.5" r="5" id="group_0009_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<rect x="424" y="300" width="93" height="56" id="group_0009_selected"
				class="mySelected hide" transform="matrix(1,0,0,1,0,0)"></rect>
			<foreignObject width="100" x="460.5" y="313" id="group_0009_label"
				transform="matrix(1,0,0,1,0,0)">
			<div>
				<div contenteditable="true" style="width: 90px; text-align: center"></div>
			</div></foreignObject>
			<circle cx="434" cy="310" r="5" id="group_0009_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="507" cy="346" r="5" id="group_0009_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="470.5" cy="328" r="5" id="group_0009_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 502 346 L 502 343 L 507 346 L 507 347 L 502 349 Z"
				id="group_0009_arrow"
				transform="matrix(0.8969,0.4423,-0.4423,0.8969,205.3197,-188.5596)"
				class="myConnector"></path></g>
			<g id="group_0010_g" type="undefined" transform="matrix(1,0,0,1,0,0)">
			<path d="M 521 325 L 457 292 " id="group_0010_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<circle cx="528.5" cy="284.5" r="5" id="group_0010_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<rect x="447" y="282" width="84" height="53" id="group_0010_selected"
				class="mySelected hide" transform="matrix(1,0,0,1,0,0)"></rect>
			<foreignObject width="100" x="479" y="293.5" id="group_0010_label"
				transform="matrix(1,0,0,1,0,0)">
			<div>
				<div contenteditable="true" style="width: 90px; text-align: center"></div>
			</div></foreignObject>
			<circle cx="521" cy="325" r="5" id="group_0010_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="457" cy="292" r="5" id="group_0010_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="489" cy="308.5" r="5" id="group_0010_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 452 292 L 452 289 L 457 292 L 457 293 L 452 295 Z"
				id="group_0010_arrow"
				transform="matrix(-0.8888,-0.4583,0.4583,-0.8888,729.3626,760.9687)"
				class="myConnector"></path></g>
			<g id="group_0011_g" type="undefined" transform="matrix(1,0,0,1,0,0)">
			<path d="M 515 149 L 414 217 " id="group_0011_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<circle cx="522.5" cy="141.5" r="5" id="group_0011_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<rect x="404" y="139" width="121" height="88"
				id="group_0011_selected" class="mySelected hide"
				transform="matrix(1,0,0,1,0,0)"></rect>
			<foreignObject width="100" x="454.5" y="168" id="group_0011_label"
				transform="matrix(1,0,0,1,0,0)">
			<div>
				<div contenteditable="true" style="width: 90px; text-align: center"
					placeholder="label">無法讀取即時訊息</div>
			</div></foreignObject>
			<circle cx="515" cy="149" r="5" id="group_0011_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="414" cy="217" r="5" id="group_0011_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="464.5" cy="183" r="5" id="group_0011_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 409 217 L 409 214 L 414 217 L 414 218 L 409 220 Z"
				id="group_0011_arrow"
				transform="matrix(-0.8295,0.5585,-0.5585,-0.8295,878.6103,165.7918)"
				class="myConnector"></path></g>
			<g id="group_0013_g" type="undefined" transform="matrix(1,0,0,1,0,0)">
			<path d="M 445 228 L 536 167 " id="group_0013_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<circle cx="543.5" cy="159.5" r="5" id="group_0013_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<rect x="435" y="157" width="111" height="81"
				id="group_0013_selected" class="mySelected hide"
				transform="matrix(1,0,0,1,0,0)"></rect>
			<foreignObject width="100" x="480.5" y="182.5" id="group_0013_label">
			<div>
				<div contenteditable="true" style="width: 90px; text-align: center"></div>
			</div></foreignObject>
			<circle cx="445" cy="228" r="5" id="group_0013_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="536" cy="167" r="5" id="group_0013_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="490.5" cy="197.5" r="5" id="group_0013_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 531 167 L 531 164 L 536 167 L 536 168 L 531 170 Z"
				id="group_0013_arrow"
				transform="matrix(0.8306,-0.5568,0.5568,0.8306,-2.2112,326.73)"
				class="myConnector"></path></g>
			<g id="group_0014_g" transform="matrix(1,0,0,1,0,0)">
			<path
				d="M 461 153 L 501.32 193.5 L 484.76 193.5 L 533 234 L 492.68 193.5 Z"
				id="group_0014_break" _ratio="0,0|0.56,0.5|0.33,0.5|1,1|0.44,0.5"
				transform="matrix(1,0,0,1,0,0)" class="myBreak"></path>
			<circle cx="538.5" cy="133.5" r="5" id="group_0014_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="496" cy="131" r="5" id="group_0014_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,1,19)"></circle>
			<circle cx="496" cy="231" r="5" id="group_0014_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="451" cy="181" r="5" id="group_0014_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,9,0)"></circle>
			<circle cx="541" cy="181" r="5" id="group_0014_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,-9,2)"></circle>
			<rect x="461" y="153" width="72" height="81" id="group_0014_selected"
				class="mySelected hide" transform="matrix(1,0,0,1,0,0)"></rect></g>
			<g id="group_0015_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="369" y="124" width="80" height="40" rx="5" ry="5"
				id="group_0015_rect" class="myRect2" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="446.5" cy="126.5" r="5" id="group_0015_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="409" cy="124" r="5" id="group_0015_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="409" cy="164" r="5" id="group_0015_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="369" cy="144" r="5" id="group_0015_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="449" cy="144" r="5" id="group_0015_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<rect x="369" y="124" width="80" height="40" id="group_0015_selected"
				class="mySelected hide" transform="matrix(1,0,0,1,0,0)"></rect>
			<foreignObject width="80" x="369" y="137" id="group_0015_label"
				transform="matrix(1,0,0,1,0,0)">
			<div>
				<div contenteditable="true" style="width: 70px; text-align: center">FB</div>
			</div></foreignObject></g>
			<g id="group_0016_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="548" y="193" width="80" height="40" rx="5" ry="5"
				id="group_0016_rect" class="myRect2" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="625.5" cy="195.5" r="5" id="group_0016_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="588" cy="193" r="5" id="group_0016_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="588" cy="233" r="5" id="group_0016_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="548" cy="213" r="5" id="group_0016_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="628" cy="213" r="5" id="group_0016_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<rect x="548" y="193" width="80" height="40" id="group_0016_selected"
				class="mySelected hide" transform="matrix(1,0,0,1,0,0)"></rect>
			<foreignObject width="80" x="548" y="206" id="group_0016_label"
				transform="matrix(1,0,0,1,0,0)">
			<div>
				<div contenteditable="true" style="width: 70px; text-align: center"
					placeholder="label">email</div>
			</div></foreignObject></g>
			<g id="group_0017_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="505" y="268" width="80" height="40" rx="5" ry="5"
				id="group_0017_rect" class="myRect2" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="582.5" cy="270.5" r="5" id="group_0017_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="545" cy="268" r="5" id="group_0017_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="545" cy="308" r="5" id="group_0017_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="505" cy="288" r="5" id="group_0017_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="585" cy="288" r="5" id="group_0017_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<rect x="505" y="268" width="80" height="40" id="group_0017_selected"
				class="mySelected hide" transform="matrix(1,0,0,1,0,0)"></rect>
			<foreignObject width="80" x="505" y="281" id="group_0017_label"
				transform="matrix(1,0,0,1,0,0)">
			<div>
				<div contenteditable="true" style="width: 70px; text-align: center"
					placeholder="label">LINE</div>
			</div></foreignObject></g>
			<g id="group_0018_g" transform="matrix(1,0,0,1,0,0)">
			<rect x="404" y="343" width="80" height="40" rx="5" ry="5"
				id="group_0018_rect" class="myRect2" transform="matrix(1,0,0,1,0,0)"></rect>
			<circle cx="481.5" cy="345.5" r="5" id="group_0018_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="444" cy="343" r="5" id="group_0018_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="444" cy="383" r="5" id="group_0018_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="404" cy="363" r="5" id="group_0018_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="484" cy="363" r="5" id="group_0018_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<rect x="404" y="343" width="80" height="40" id="group_0018_selected"
				class="mySelected hide" transform="matrix(1,0,0,1,0,0)"></rect>
			<foreignObject width="80" x="404" y="356" id="group_0018_label"
				transform="matrix(1,0,0,1,0,0)">
			<div>
				<div contenteditable="true" style="width: 70px; text-align: center">電話</div>
			</div></foreignObject></g></svg>
	</div>

</section>
