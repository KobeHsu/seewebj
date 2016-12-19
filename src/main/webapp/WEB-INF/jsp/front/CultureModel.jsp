<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav></nav>
<script>gModelType = "2";</script>
<section id="mainArea">

	<div class="col-md-2 col-sm-2 col-xs-2">
		<div class="panel panel-primary margin-top-lg">
			<div class="panel-heading">
				<h4 class="panel-title">塑模元素</h4>
			</div>

			<div id="component" align="center">
				<img src="<c:url value='/res/images/cm_influencer.png' />"
					onclick="addEllipse()" /> <br />Influencer<br /> <img
					src="<c:url value='/res/images/cm_influence.png' />"
					onclick="addConnector()" /> <br />Influence<br /> <img
					src="<c:url value='/res/images/cm_breakdown.png' />"
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
			<ellipse cx="212" cy="146" rx="121" ry="87.5" id="group_0000_ellipse"
				class="myEllipse" transform="matrix(1,0,0,1,0,0)"></ellipse>
			<circle cx="330.3194810313198" cy="61" r="5" id="group_0000_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="211.8194810313198" cy="58.5" r="5"
				id="group_0000_nResize" class="myNResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="211.8194810313198" cy="232.5" r="5"
				id="group_0000_sResize" class="mySResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="90.81948103131981" cy="145.5" r="5"
				id="group_0000_wResize" class="myWResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="332.8194810313198" cy="145.5" r="5"
				id="group_0000_eResize" class="myEResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="170" y="100" id="group_0000_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">太太的情緒</text> <text
				x="150" y="130" id="group_0000_text_1"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">● 我們家很久沒有一起出去玩</text></g>
		<g id="group_0001_g" transform="matrix(1,0,0,1,0,0)">
			<ellipse cx="222" cy="326" rx="125.5" ry="93.5"
				id="group_0001_ellipse" class="myEllipse"
				transform="matrix(1,0,0,1,0,0)"></ellipse>
			<circle cx="343.81276751595567" cy="235" r="5" id="group_0001_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="221.31276751595567" cy="232.5" r="5"
				id="group_0001_nResize" class="myNResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="221.31276751595567" cy="418.5" r="5"
				id="group_0001_sResize" class="mySResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="96.31276751595568" cy="325.5" r="5"
				id="group_0001_wResize" class="myWResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="346.31276751595567" cy="325.5" r="5"
				id="group_0001_eResize" class="myEResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="140" y="330.5" id="group_0001_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">孝順文化</text>
			<text x="120" y="360" id="group_0001_text_01"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">● 帶父母一起出門玩，需考量他們的需求</text></g>
		<g id="group_0002_g" transform="matrix(1,0,0,1,0,0)">
			<ellipse cx="459" cy="307" rx="119" ry="91.5" id="group_0002_ellipse"
				class="myEllipse" transform="matrix(1,0,0,1,0,0)"></ellipse>
			<circle cx="575.3224648159261" cy="218" r="5" id="group_0002_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="458.8224648159261" cy="215.5" r="5"
				id="group_0002_nResize" class="myNResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="458.8224648159261" cy="397.5" r="5"
				id="group_0002_sResize" class="mySResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="339.8224648159261" cy="306.5" r="5"
				id="group_0002_wResize" class="myWResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="577.8224648159261" cy="306.5" r="5"
				id="group_0002_eResize" class="myEResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="400" y="340" id="group_0002_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">出遊達人哥哥的推薦</text>
			<text x="380" y="370" id="group_0002_text_01"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">● 那一帶我去過，我推薦你去...</text></g>
		<g id="group_0003_g" transform="matrix(1,0,0,1,0,0)">
			<ellipse cx="450" cy="143" rx="121.5" ry="80" id="group_0003_ellipse"
				class="myEllipse" transform="matrix(1,0,0,1,0,0)"></ellipse>
			<circle cx="567.8187350851682" cy="65.5" r="5" id="group_0003_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="449.3187350851682" cy="63" r="5" id="group_0003_nResize"
				class="myNResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="449.3187350851682" cy="223" r="5" id="group_0003_sResize"
				class="mySResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="328.3187350851682" cy="143" r="5" id="group_0003_wResize"
				class="myWResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="570.3187350851682" cy="143" r="5" id="group_0003_eResize"
				class="myEResize hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="440" y="95" id="group_0003_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">小孩的行程</text>
			<text x="420" y="125" id="group_0003_text_01"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">● 要考試，沒空出去玩</text></g>
		<g id="group_0004_g" transform="matrix(1,0,0,1,0,0)">
			<ellipse cx="339" cy="234" rx="135" ry="88.5" id="group_0004_ellipse"
				class="myEllipse" transform="matrix(1,0,0,1,0,0)"></ellipse>
			<circle cx="471.29859453907585" cy="148" r="5" id="group_0004_close"
				class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="338.79859453907585" cy="145.5" r="5"
				id="group_0004_nResize" class="myNResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="338.79859453907585" cy="321.5" r="5"
				id="group_0004_sResize" class="mySResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="203.79859453907585" cy="233.5" r="5"
				id="group_0004_wResize" class="myWResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<circle cx="473.79859453907585" cy="233.5" r="5"
				id="group_0004_eResize" class="myEResize hide"
				transform="matrix(1,0,0,1,0,0)"></circle>
			<text x="330" y="238.5" id="group_0004_text"
				transform="matrix(1,0,0,1,0,0)" class="myLabel">U1</text></g>
		<g id="group_0005_g">
			<path d="M 206.84375 136 L 296.84375 206 " id="group_0005_connector"
				class="myConnector"></path>
			<text x="241.84375" y="156" id="group_0005_text" class="myLabel"> </text>
			<circle cx="206.84375" cy="136" r="5" id="group_0005_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="296.84375" cy="206" r="5" id="group_0005_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="251.84375" cy="171" r="5" id="group_0005_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 291 206 L 291 203 L 296 206 L 296 207 L 291 209 Z"
				id="group_0005_arrow"
				transform="matrix(0.7894,0.6139,-0.6139,0.7894,188.8235,-138.333)"
				class="myConnector"></path></g>
		<g id="group_0006_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 473 139 L 267.84375 115 " id="group_0006_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="360.421875" y="112" id="group_0006_text" class="myLabel"> </text>
			<circle cx="473" cy="139" r="5" id="group_0006_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="267.84375" cy="115" r="5" id="group_0006_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="370.421875" cy="127" r="5" id="group_0006_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 262 115 L 262 112 L 267 115 L 267 116 L 262 118 Z"
				id="group_0006_arrow"
				transform="matrix(-0.9933,-0.1157,0.1157,-0.9933,518.8981,260.1252)"
				class="myConnector"></path></g>
		<g id="group_0007_g" transform="matrix(1,0,0,1,0,0)">
			<path d="M 477 142 L 389.84375 212 " id="group_0007_connector"
				class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
			<text x="423.421875" y="162" id="group_0007_text" class="myLabel"> </text>
			<circle cx="477" cy="142" r="5" id="group_0007_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="389.84375" cy="212" r="5" id="group_0007_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="433.421875" cy="177" r="5" id="group_0007_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 384 212 L 384 209 L 389 212 L 389 213 L 384 215 Z"
				id="group_0007_arrow"
				transform="matrix(-0.7826,0.6225,-0.6225,-0.7826,825.4068,135.7498)"
				class="myConnector"></path></g>		
		<g id="group_0008_g">
			<path d="M 184.84375 292 L 308.84375 284 " id="group_0008_connector"
				class="myConnector"></path>
			<text x="236.84375" y="273" id="group_0008_text" class="myLabel"> </text>
			<circle cx="184.84375" cy="292" r="5" id="group_0008_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="308.84375" cy="284" r="5" id="group_0008_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="246.84375" cy="288" r="5" id="group_0008_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 303 284 L 303 281 L 308 284 L 308 285 L 303 287 Z"
				id="group_0008_arrow"
				transform="matrix(0.9979,-0.0644,0.0644,0.9979,-17.6456,20.419)"
				class="myConnector"></path></g>		
		<g id="group_0009_g">
			<path d="M 180.84375 293 L 188.84375 187 " id="group_0009_connector"
				class="myConnector"></path>
			<text x="174.84375" y="225" id="group_0009_text" class="myLabel"> </text>
			<circle cx="180.84375" cy="293" r="5" id="group_0009_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="188.84375" cy="187" r="5" id="group_0009_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="184.84375" cy="240" r="5" id="group_0009_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 183 187 L 183 184 L 188 187 L 188 188 L 183 190 Z"
				id="group_0009_arrow"
				transform="matrix(0.0753,-0.9972,0.9972,0.0753,-12.6181,360.3937)"
				class="myConnector"></path></g>		
		<g id="group_0010_g">
			<path d="M 447.84375 317 L 393.84375 274 " id="group_0010_connector"
				class="myConnector"></path>
			<text x="410.84375" y="280.5" id="group_0010_text" class="myLabel"> </text>
			<circle cx="447.84375" cy="317" r="5" id="group_0010_point_end_0"
				class="myEndPoint hide"></circle>
			<circle cx="393.84375" cy="274" r="5" id="group_0010_point_end_1"
				class="myEndPoint hide"></circle>
			<circle cx="420.84375" cy="295.5" r="5" id="group_0010_point_mid_0"
				class="myMidPoint hide"></circle>
			<path d="M 388 274 L 388 271 L 393 274 L 393 275 L 388 277 Z"
				id="group_0010_arrow"
				transform="matrix(-0.7823,-0.6229,0.6229,-0.7823,529.7542,733.155)"
				class="myConnector"></path></g>		
	</svg>
	</div>

</section>
