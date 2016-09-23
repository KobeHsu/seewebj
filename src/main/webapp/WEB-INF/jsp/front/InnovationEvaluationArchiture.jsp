<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value='/res/css/see_draw.css' />" rel="stylesheet">		

<script type="text/javascript" src="<c:url value='/res/js/seeweb/snap.svg-min.js' />"></script>
<script type="text/javascript" src="<c:url value='/res/js/seeweb/see_draw.js' />"></script>
<script type="text/javascript" src="<c:url value='/res/js/seeweb/see_draw_path.js' />"></script>


<header>創新商業評估架構</header>

<nav></nav>
<section id="mainArea">
    <div id="component" style="width:150px;">
        <svg width="150" height="40" style="margin: 10px 10px 10px 10px" onclick="addRect()">
            <rect x="0" y="0" rx="5" ry="5" width="80" height="40" class="myRect"/>
            <text x="15" y="25" class="myLabel">NEW</text>
        </svg>
        <svg width="150" height="40" style="margin: 10px 10px 10px 10px" onclick="addConnector()">
            <path d="M0 20 L80 20" class="myConnector"/>
            <path d="M80 20 L75 17 L75 23 Z" class="myConnector"/>
        </svg>
    </div>
<div id="drawArea" style="width:800px;"><input type="text" id="rectText" style="left: 579px; top: 205px; display: none;">
        <svg height="600" version="1.1" width="800" xmlns="http://www.w3.org/2000/svg" id="snapSvg">
            <desc>Created with Snap</desc>
            <defs></defs>
            <g id="group_0000_g" transform="matrix(1,0,0,1,0,0)">
                <rect x="46" y="51" width="120" height="60" rx="5" ry="5" id="group_0000_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
                <circle cx="163.5" cy="53.5" r="5" id="group_0000_close" class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
                <text x="56" y="86" id="group_0000_text" transform="matrix(1,0,0,1,0,0)" class="myLabel">硬體廠商</text>
            </g>
            <g id="group_0001_g" transform="matrix(1,0,0,1,0,0)">
                <rect x="45" y="124" width="120" height="60" rx="5" ry="5" id="group_0001_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
                <circle cx="162.5" cy="126.5" r="5" id="group_0001_close" class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
                <text x="55" y="159" id="group_0001_text" transform="matrix(1,0,0,1,0,0)" class="myLabel">軟體廠商</text>
            </g>
            <g id="group_0003_g" transform="matrix(1,0,0,1,0,0)">
                <rect x="280" y="72" width="120" height="60" rx="5" ry="5" id="group_0003_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
                <circle cx="397.5" cy="74.5" r="5" id="group_0003_close" class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
                <text x="290" y="107" id="group_0003_text" transform="matrix(1,0,0,1,0,0)" class="myLabel">零組件供應商</text>
            </g>
            <g id="group_0004_g" transform="matrix(1,0,0,1,0,0)">
                <path d="M 167 82 L 226 82 L 226 97 L 277 97 " id="group_0004_connector" class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
                <text x="216" y="100.49790272302926" id="group_0004_text" class="myLabel">Not Entered</text>
                
                
                
                
                
                
                
                
            <circle cx="167" cy="82" r="5" id="group_0004_point_end_0" class="myEndPoint hide"></circle><circle cx="226" cy="82" r="5" id="group_0004_point_end_1" class="myEndPoint hide"></circle><circle cx="196.5" cy="82" r="5" id="group_0004_point_mid_0" class="myMidPoint hide"></circle><circle cx="226" cy="97" r="5" id="group_0004_point_end_2" class="myEndPoint hide"></circle><circle cx="226" cy="89.5" r="5" id="group_0004_point_mid_1" class="myMidPoint hide"></circle><circle cx="277" cy="97" r="5" id="group_0004_point_end_3" class="myEndPoint hide"></circle><circle cx="251.5" cy="97" r="5" id="group_0004_point_mid_2" class="myMidPoint hide"></circle><path d="M 272 97 L 272 94 L 277 97 L 277 98 L 272 100 Z" id="group_0004_arrow" transform="matrix(1,0,0,1,0,0)" class="myConnector"></path></g>
            <g id="group_0005_g" transform="matrix(1,0,0,1,0,0)">
                <rect x="17" y="258" width="120" height="60" rx="5" ry="5" id="group_0005_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
                <circle cx="134.5" cy="260.5" r="5" id="group_0005_close" class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
                <text x="27" y="293" id="group_0005_text" transform="matrix(1,0,0,1,0,0)" class="myLabel">系統整合者</text>
            </g>
            <g id="group_0006_g" transform="matrix(1,0,0,1,0,0)">
                <path d="M 70 256 L 70 224 L 107 223 L 108 185 " id="group_0006_connector" class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
                <text x="81.4962748636026" y="238.419019598281" id="group_0006_text" class="myLabel">100元/台</text>
                
                
                
                
                
                
                
                
            <circle cx="70" cy="256" r="5" id="group_0006_point_end_0" class="myEndPoint hide"></circle><circle cx="70" cy="224" r="5" id="group_0006_point_end_1" class="myEndPoint hide"></circle><circle cx="70" cy="240" r="5" id="group_0006_point_mid_0" class="myMidPoint hide"></circle><circle cx="107" cy="223" r="5" id="group_0006_point_end_2" class="myEndPoint hide"></circle><circle cx="88.5" cy="223.5" r="5" id="group_0006_point_mid_1" class="myMidPoint hide"></circle><circle cx="108" cy="185" r="5" id="group_0006_point_end_3" class="myEndPoint hide"></circle><circle cx="107.5" cy="204" r="5" id="group_0006_point_mid_2" class="myMidPoint hide"></circle><path d="M 103 185 L 103 182 L 108 185 L 108 186 L 103 188 Z" id="group_0006_arrow" transform="matrix(0.0263,-0.9997,0.9997,0.0263,-79.7771,288.0959)" class="myConnector"></path></g>
            <g id="group_0007_g" transform="matrix(1,0,0,1,0,0)">
                <rect x="251" y="201" width="120" height="60" rx="5" ry="5" id="group_0007_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
                <circle cx="368.5" cy="203.5" r="5" id="group_0007_close" class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
                <text x="261" y="236" id="group_0007_text" class="myLabel" transform="matrix(1,0,0,1,0,0)">汽車製造商</text>
            </g>
            <g id="group_0008_g" transform="matrix(1,0,0,1,0,0)">
                <path d="M 249 229 L 199 229 L 200 285 L 138 286 " id="group_0008_connector" class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
                <text x="189.60728506211308" y="278.0079634783324" id="group_0008_text" class="myLabel">500元/台</text>
                
                
                
                
                
                
                
                
            <circle cx="249" cy="229" r="5" id="group_0008_point_end_0" class="myEndPoint hide"></circle><circle cx="199" cy="229" r="5" id="group_0008_point_end_1" class="myEndPoint hide"></circle><circle cx="224" cy="229" r="5" id="group_0008_point_mid_0" class="myMidPoint hide"></circle><circle cx="200" cy="285" r="5" id="group_0008_point_end_2" class="myEndPoint hide"></circle><circle cx="199.5" cy="257" r="5" id="group_0008_point_mid_1" class="myMidPoint hide"></circle><circle cx="138" cy="286" r="5" id="group_0008_point_end_3" class="myEndPoint hide"></circle><circle cx="169" cy="285.5" r="5" id="group_0008_point_mid_2" class="myMidPoint hide"></circle><path d="M 133 286 L 133 283 L 138 286 L 138 287 L 133 289 Z" id="group_0008_arrow" transform="matrix(-0.9999,0.0161,-0.0161,-0.9999,280.5944,569.7373)" class="myConnector"></path></g>
            <g id="group_0009_g" transform="matrix(1,0,0,1,0,0)">
                <rect x="438" y="161" width="120" height="60" rx="5" ry="5" id="group_0009_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
                <circle cx="555.5" cy="163.5" r="5" id="group_0009_close" class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
                <text x="448" y="196" id="group_0009_text" transform="matrix(1,0,0,1,0,0)" class="myLabel">使用者</text>
            </g>
            <g id="group_0010_g" transform="matrix(1,0,0,1,0,0)">
                <path d="M 492 162 L 492 147 L 312 148 L 312 200 " id="group_0010_connector" class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
                <text x="373.49628147402836" y="162.6027984362554" id="group_0010_text" class="myLabel">800元/用戶</text>
                
                
                
                
                
                
                
                
            <circle cx="492" cy="162" r="5" id="group_0010_point_end_0" class="myEndPoint hide"></circle><circle cx="492" cy="147" r="5" id="group_0010_point_end_1" class="myEndPoint hide"></circle><circle cx="492" cy="154.5" r="5" id="group_0010_point_mid_0" class="myMidPoint hide"></circle><circle cx="312" cy="148" r="5" id="group_0010_point_end_2" class="myEndPoint hide"></circle><circle cx="402" cy="147.5" r="5" id="group_0010_point_mid_1" class="myMidPoint hide"></circle><circle cx="312" cy="200" r="5" id="group_0010_point_end_3" class="myEndPoint hide"></circle><circle cx="312" cy="174" r="5" id="group_0010_point_mid_2" class="myMidPoint hide"></circle><path d="M 307 200 L 307 197 L 312 200 L 312 201 L 307 203 Z" id="group_0010_arrow" transform="matrix(0,1,-1,0,512,-112)" class="myConnector"></path></g>
            <g id="group_0011_g" transform="matrix(1,0,0,1,0,0)">
                <rect x="424" y="338" width="120" height="60" rx="5" ry="5" id="group_0011_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
                <circle cx="541.5" cy="340.5" r="5" id="group_0011_close" class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
                <text x="434" y="373" id="group_0011_text" transform="matrix(1,0,0,1,0,0)" class="myLabel">通訊網路提供者
                </text>
            </g>
            <g id="group_0012_g" transform="matrix(1,0,0,1,0,0)">
                <rect x="252" y="337" width="120" height="60" rx="5" ry="5" id="group_0012_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
                <circle cx="369.5" cy="339.5" r="5" id="group_0012_close" class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
                <text x="262" y="372" id="group_0012_text" transform="matrix(1,0,0,1,0,0)" class="myLabel">電信服務提供者
                </text>
            </g>
            <g id="group_0013_g" transform="matrix(1,0,0,1,0,0)">
                <rect x="81" y="336" width="120" height="60" rx="5" ry="5" id="group_0013_rect" class="myRect" transform="matrix(1,0,0,1,0,0)"></rect>
                <circle cx="198.5" cy="338.5" r="5" id="group_0013_close" class="myClose hide" transform="matrix(1,0,0,1,0,0)"></circle>
                <text x="91" y="371" id="group_0013_text" transform="matrix(1,0,0,1,0,0)" class="myLabel">內容提供者</text>
            </g>
            <g id="group_0014_g" transform="matrix(1,0,0,1,0,0)">
                <path d="M 559 191 L 588 191 L 591 368 L 546 367 " id="group_0014_connector" class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
                <text x="579.635516687802" y="302.4954845803186" id="group_0014_text" class="myLabel">Not Entered</text>
                
                
                
                
                
                
                
                
            <circle cx="559" cy="191" r="5" id="group_0014_point_end_0" class="myEndPoint hide"></circle><circle cx="588" cy="191" r="5" id="group_0014_point_end_1" class="myEndPoint hide"></circle><circle cx="573.5" cy="191" r="5" id="group_0014_point_mid_0" class="myMidPoint hide"></circle><circle cx="591" cy="368" r="5" id="group_0014_point_end_2" class="myEndPoint hide"></circle><circle cx="589.5" cy="279.5" r="5" id="group_0014_point_mid_1" class="myMidPoint hide"></circle><circle cx="546" cy="367" r="5" id="group_0014_point_end_3" class="myEndPoint hide"></circle><circle cx="568.5" cy="367.5" r="5" id="group_0014_point_mid_2" class="myMidPoint hide"></circle><path d="M 541 367 L 541 364 L 546 367 L 546 368 L 541 370 Z" id="group_0014_arrow" transform="matrix(-0.9998,-0.0222,0.0222,-0.9998,1083.7117,746.0398)" class="myConnector"></path></g>
            <g id="group_0015_g" transform="matrix(1,0,0,1,0,0)">
                <path d="M 369 230 L 486 230 L 487 339 " id="group_0015_connector" class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
                <text x="472.00644209771417" y="245" id="group_0015_text" class="myLabel">Not Entered</text>
                
                
                
                
                
                
            <circle cx="369" cy="230" r="5" id="group_0015_point_end_0" class="myEndPoint hide"></circle><circle cx="486" cy="230" r="5" id="group_0015_point_end_1" class="myEndPoint hide"></circle><circle cx="427.5" cy="230" r="5" id="group_0015_point_mid_0" class="myMidPoint hide"></circle><circle cx="487" cy="339" r="5" id="group_0015_point_end_2" class="myEndPoint hide"></circle><circle cx="486.5" cy="284.5" r="5" id="group_0015_point_mid_1" class="myMidPoint hide"></circle><path d="M 482 339 L 482 336 L 487 339 L 487 340 L 482 342 Z" id="group_0015_arrow" transform="matrix(0.0092,1,-1,0.0092,821.518,-151.0895)" class="myConnector"></path></g>
            <g id="group_0016_g">
                <path d="M 311 262 L 312 338 " id="group_0016_connector" class="myConnector"></path>
                <text x="301.5" y="315" id="group_0016_text" class="myLabel">100元/用戶</text>
                
                
                
                
            <circle cx="311" cy="262" r="5" id="group_0016_point_end_0" class="myEndPoint hide"></circle><circle cx="312" cy="338" r="5" id="group_0016_point_end_1" class="myEndPoint hide"></circle><circle cx="311.5" cy="300" r="5" id="group_0016_point_mid_0" class="myMidPoint hide"></circle><path d="M 307 338 L 307 335 L 312 338 L 312 339 L 307 341 Z" id="group_0016_arrow" transform="matrix(0.0132,0.9999,-0.9999,0.0132,645.8658,21.58)" class="myConnector"></path></g>
            <g id="group_0017_g" transform="matrix(1,0,0,1,0,0)">
                <path d="M 368 369 L 401 370 L 401 431 L 488 433 L 488 397 " id="group_0017_connector" class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
                <text x="405.50062724614327" y="446.3333477527849" id="group_0017_text" class="myLabel">Not Entered</text>
                
                
                
                
                
                
                
                
                
                
            <circle cx="368" cy="369" r="5" id="group_0017_point_end_0" class="myEndPoint hide"></circle><circle cx="401" cy="370" r="5" id="group_0017_point_end_1" class="myEndPoint hide"></circle><circle cx="384.5" cy="369.5" r="5" id="group_0017_point_mid_0" class="myMidPoint hide"></circle><circle cx="401" cy="431" r="5" id="group_0017_point_end_2" class="myEndPoint hide"></circle><circle cx="401" cy="400.5" r="5" id="group_0017_point_mid_1" class="myMidPoint hide"></circle><circle cx="488" cy="433" r="5" id="group_0017_point_end_3" class="myEndPoint hide"></circle><circle cx="444.5" cy="432" r="5" id="group_0017_point_mid_2" class="myMidPoint hide"></circle><circle cx="488" cy="397" r="5" id="group_0017_point_end_4" class="myEndPoint hide"></circle><circle cx="488" cy="415" r="5" id="group_0017_point_mid_3" class="myMidPoint hide"></circle><path d="M 483 397 L 483 394 L 488 397 L 488 398 L 483 400 Z" id="group_0017_arrow" transform="matrix(0,-1,1,0,91,885)" class="myConnector"></path></g>
            <g id="group_0018_g" transform="matrix(1,0,0,1,0,0)">
                <path d="M 312 398 L 313 429 L 141 430 L 140 396 " id="group_0018_connector" class="myConnector" transform="matrix(1,0,0,1,0,0)"></path>
                <text x="215.5040953812222" y="444.5086971198766" id="group_0018_text" class="myLabel">10元/每部電影</text>
                
                
                
                
                
                
                
                
            <circle cx="312" cy="398" r="5" id="group_0018_point_end_0" class="myEndPoint hide"></circle><circle cx="313" cy="429" r="5" id="group_0018_point_end_1" class="myEndPoint hide"></circle><circle cx="312.5" cy="413.5" r="5" id="group_0018_point_mid_0" class="myMidPoint hide"></circle><circle cx="141" cy="430" r="5" id="group_0018_point_end_2" class="myEndPoint hide"></circle><circle cx="227" cy="429.5" r="5" id="group_0018_point_mid_1" class="myMidPoint hide"></circle><circle cx="140" cy="396" r="5" id="group_0018_point_end_3" class="myEndPoint hide"></circle><circle cx="140.5" cy="413" r="5" id="group_0018_point_mid_2" class="myMidPoint hide"></circle><path d="M 135 396 L 135 393 L 140 396 L 140 397 L 135 399 Z" id="group_0018_arrow" transform="matrix(-0.0294,-0.9996,0.9996,-0.0294,-251.713,547.5815)" class="myConnector"></path></g>
            <path d="M 32 26 L 182 26 L 184 204 L 33 205 L 32 26 " id="group_9999_group" class="myGroup" transform="matrix(1,0,0,1,0,0)"></path>
        </svg>

    </div>
</section>
