//var SVG_NAME_SPACE = "http://www.w3.org/2000/svg";
//var XML_NAME_SPACE = "http://www.w3.org/1999/xhtml";
var __DEBUG_OUTPUT = false;

var CANVAS_WIDTH = 800;
var CANVAS_HEIGHT = 600;

var SEPARATOR = "_";
var GROUP_PREFIX = "group";

var RECT_WIDTH = 60;
//var RECT_WIDTH_HALF = RECT_WIDTH / 2;
var RECT_HEIGHT = 30;
//var RECT_HEIGHT_HALF = RECT_HEIGHT / 2;

var RECT_WIDTH_SM = 80;
var RECT_HEIGHT_SM = 40;

var CIRCLE_R = 5;
var CIRCLE_R_HALF = CIRCLE_R / 2;

var ELLIPSE_RX = 20;
var ELLIPSE_RY = 20;

var CIRCLE_RX = 40;
var CIRCLE_RY = 40;

var XS_CIRCLE_RX = 20;
var XS_CIRCLE_RY = 20;

var BREAK_WIDTH = 27;
var BREAK_HEIGHT = 30;
//var LINE_WIDTH = 80;

var BRACE_WIDTH = 20;
var BRACE_Q = 0.6;

var IMAGE_WIDTH = 200;

var PATH_BBOX_ADD = 10;

var REMOVE_CONFIRM_MSG = "Are you sure to remove this?";

//var REMOVE_CONNECTOR_MSG = "Remove this connection ?";
var REMOVE_ENDOPOINT_MSG = "Remove this node ?";

var REMOVE_LINE_MSG = "Remove this boundary ?";
//var REMOVE_ELLIPSE_MSG = "Remove this ellipse ?";
//var REMOVE_BREAK_MSG = "Remove this breakdown ?";
//var REMOVE_BRACE_MSG = "Remove this brace ?";
//var REMOVE_IMAGE_MSG = "Remove this image ?";
var REMOVE_MODEL_MSG = "Remove this model ?";

var TEXT_EDIT_MENU_LEFT = 180;
var TEXT_EDIT_MENU_TOP = 0;
var DIAGRAM_NAME = "";

var EL_TYPES = ["rect", "connector", "ellipse", "line", "break", "brace", "image", "custom"];

var SUPPORTED_FONTS = [["", ""], ["Microsoft JhengHei", "微軟正黑體"], ["Arial", "Arial"], ["Arial Black", "Arial Black"], ["Comic Sans MS", "Comic Sans MS"], ["Courier New", "Courier New"], ["Helvetica", "Helvetica"], ["Impact", "Impact"], ["serif", "serif"], ["sans-serif", "sans-serif"], ["Tahoma", "Tahoma"], ["Times New Roman", "Times New Roman"], ["Verdana", "Verdana"], ["PMingLiU", "新細明體"], ["DFKai-sb", "標楷體"]];
var SUPPORTED_FONT_SIZES = [12, 14, 18, 22];

var CONTEXT_MENU_SHIFT_X = -5;
var CONTEXT_MENU_SHIFT_Y = -5;

var RESIZE_BY_RATIO = false;

var gSerialNo = 0;

var gDrawArea;
var gSvg;
var gStartX;
var gStartY;
var gCurrent;
var gDragAnchor;
var gDragType;
var gDragAnchorPos;
var gEditingItem;
var gGrpTmp;

var gMenuWidth;
var gMenuHeight;

var gRatioAry = [];

var gContextMenu;
var gConnectorContextMenu;
var gLabelContextMenu;
var gTextEditContextMenu;

var gModelType = "-1";
var gGridEnable = false;

var svgElMoveFunc = {
    "rect": rectMove,
    "ellipse": ellipseMove,
    "brace": braceMove,
    "break": breakMove,
    "image": imageMove,
    "custom": customMove
};

"use strict";

function CustomData(x, y, svgEl, overwrite) {

    this.x = toInteger(x, 0);
    this.y = toInteger(y, 0);
    this.h = toInteger(svgEl.attr("height"), 0);
    this.w = toInteger(svgEl.attr("width"), 0);

    this.cx = toInteger(svgEl.attr("cx"), 0);
    this.cy = toInteger(svgEl.attr("cy"), 0);
    this.rx = toInteger(svgEl.attr("rx"), 0);
    this.ry = toInteger(svgEl.attr("ry"), 0);

    this.x1 = toInteger(svgEl.attr("x1"), 0);
    this.y1 = toInteger(svgEl.attr("y2"), 0);
    this.x2 = toInteger(svgEl.attr("x2"), 0);
    this.y2 = toInteger(svgEl.attr("y2"), 0);

    var bBox = svgEl.getBBox();
    if (!svgEl.attr("height")) {
        this.h = toInteger(bBox.height);
    }

    if (!svgEl.attr("width")) {
        this.w = toInteger(bBox.width);
    }

    if (!svgEl.attr("x1")) {
        this.x1 = toInteger(bBox.x);
    }

    if (!svgEl.attr("y1")) {
        this.y1 = toInteger(bBox.y);
    }

    if (!svgEl.attr("x2")) {
        this.x2 = toInteger(bBox.x);
    }

    if (!svgEl.attr("y2")) {
        this.y2 = toInteger(bBox.y) + toInteger(bBox.height);
    }

    if (overwrite !== undefined && typeof(overwrite) == "object") {
        for (var key in overwrite) {
            this[key] = overwrite[key];
        }
    }

}

//region Rect
function getElementXYofRect(bBoxX, bBoxY, elName, rectId) {

    var xy = [];
    var rect = gSvg.select("#" + rectId);

    var width = (rect == null) ? 0 : rect.getBBox().width;// parseInt(rect.attr("width"), 10);
    var height = (rect == null) ? 0 : rect.getBBox().height;//parseInt(rect.attr("height"), 10);

    if ("close" == elName) {

        xy.push(bBoxX + width - CIRCLE_R_HALF);
        xy.push(bBoxY + CIRCLE_R_HALF);

    } else if ("text" == elName) {

        xy.push(bBoxX + 10);
        xy.push(bBoxY + height / 2 + 5);

    } else if ("nResize" == elName) {

        xy.push(bBoxX + width / 2);
        xy.push(bBoxY);

    } else if ("sResize" == elName) {

        xy.push(bBoxX + width / 2);
        xy.push(bBoxY + height);

    } else if ("wResize" == elName) {

        xy.push(bBoxX);
        xy.push(bBoxY + height / 2);

    } else if ("eResize" == elName) {

        xy.push(bBoxX + width);
        xy.push(bBoxY + height / 2);

    } else if ("selected" == elName) {

        xy.push(bBoxX);
        xy.push(bBoxY);

    } else if ("rResize" == elName) {

        xy.push(bBoxX + width);
        xy.push(bBoxY + height);

    }

    return xy;

}

function addRect(type) {

    var grp = getGroupPrefix(gSerialNo);
    var grpId = grp + "g";
    var rectId = grp + "rect";
    var newRect;

    var initX = 10;
    var initY = 10;
    var allRect = gSvg.selectAll("rect");
    for (var i = 0; i < allRect.length; i++) {
        if (initX == allRect[i].attr("x") && initY == allRect[i].attr("y")) {
            initX += 10;
            initY += 10;
        }
    }

    if ('small' == type) {
        newRect = gSvg.rect(initX, initY, RECT_WIDTH_SM, RECT_HEIGHT_SM, 5, 5);
        newRect.addClass("myRect2");
    } else if ('noLabel' == type) {
        newRect = gSvg.rect(initX, initY, RECT_WIDTH, RECT_HEIGHT, 5, 5);
        newRect.addClass("myRect3");
    } else {
        newRect = gSvg.rect(initX, initY, RECT_WIDTH, RECT_HEIGHT, 5, 5);
        newRect.addClass("myRect");
    }
    if ("dash" == type) {
        newRect.addClass("myRectDash");
    } else if ("light" == type) {
        newRect.addClass("myRectLight");
    }
    newRect.attr("id", rectId);

    var bBoxRect = newRect.getBBox();
    var selected = generateSelectedMark(bBoxRect, grp);

    var closeId = grp + "close";
    var closeXY = getElementXYofRect(bBoxRect.x, bBoxRect.y, "close", rectId);
    var close = gSvg.circle(closeXY[0], closeXY[1], CIRCLE_R);
    close.addClass("myClose");
    close.addClass("hide");
    close.attr("id", closeId);

    var nResizeId = grp + "nResize";
    var nResizeXY = getElementXYofRect(bBoxRect.x, bBoxRect.y, "nResize", rectId);
    var nResize = gSvg.circle(nResizeXY[0], nResizeXY[1], CIRCLE_R);
    nResize.addClass("myNResize");
    nResize.addClass("hide");
    nResize.attr("id", nResizeId);

    var sResizeId = grp + "sResize";
    var sResizeXY = getElementXYofRect(bBoxRect.x, bBoxRect.y, "sResize", rectId);
    var sResize = gSvg.circle(sResizeXY[0], sResizeXY[1], CIRCLE_R);
    sResize.addClass("mySResize");
    sResize.addClass("hide");
    sResize.attr("id", sResizeId);

    var wResizeId = grp + "wResize";
    var wResizeXY = getElementXYofRect(bBoxRect.x, bBoxRect.y, "wResize", rectId);
    var wResize = gSvg.circle(wResizeXY[0], wResizeXY[1], CIRCLE_R);
    wResize.addClass("myWResize");
    wResize.addClass("hide");
    wResize.attr("id", wResizeId);

    var eResizeId = grp + "eResize";
    var eResizeXY = getElementXYofRect(bBoxRect.x, bBoxRect.y, "eResize", rectId);
    var eResize = gSvg.circle(eResizeXY[0], eResizeXY[1], CIRCLE_R);
    eResize.addClass("myEResize");
    eResize.addClass("hide");
    eResize.attr("id", eResizeId);

    var rResizeId = grp + "rResize";
    var rResizeXY = getElementXYofRect(bBoxRect.x, bBoxRect.y, "rResize", rectId);
    var rResize = gSvg.circle(rResizeXY[0], rResizeXY[1], CIRCLE_R);
    rResize.addClass("myRResize");
    rResize.addClass("hide");
    rResize.attr("id", rResizeId);

    var label = initLabelForElement(bBoxRect, grp);

    var g = gSvg.g(newRect, close, nResize, sResize, wResize, eResize, rResize, selected, label);
    g.attr("id", grpId);

    registerListener(rectId);

    gSerialNo++;

    setSelected(grp);
    gCurrent = grp;

}

function rectMouseOver() {

    var grp = getGroupPrefix(this.attr("id"));

    showElementById(grp + "close");
    showElementById(grp + "nResize");
    showElementById(grp + "sResize");
    showElementById(grp + "wResize");
    showElementById(grp + "eResize");

}

function rectMouseOut() {

    var grp = getGroupPrefix(this.attr("id"));

    hideElementById(grp + "close");
    hideElementById(grp + "nResize");
    hideElementById(grp + "sResize");
    hideElementById(grp + "wResize");
    hideElementById(grp + "eResize");

}

function correctRectXY(grp, rect) {

    var g = gSvg.select("#" + grp + "g");

    var tStrAry = Snap.parseTransformString(g.attr("transform"));

    var rectId = grp + "rect";

    var nowX = parseInt(rect.attr("x"), 10);
    var nowY = parseInt(rect.attr("y"), 10);

    if (tStrAry.length != 0) {

        var x = parseInt(tStrAry[0][1], 10);
        var y = parseInt(tStrAry[0][2], 10);

        x += nowX;
        y += nowY;

        g.transform("translate(0 0)");

        rect.transform("translate(0 0)");
        rect.attr("x", x);
        rect.attr("y", y);

    }

    var bBox = rect.getBBox();
    var bBoxX = bBox.x;
    var bBoxY = bBox.y;

    var close = gSvg.select("#" + grp + "close");
    var closeXY = getElementXYofRect(bBoxX, bBoxY, "close", rectId);

    close.transform("translate(0 0)");
    close.attr("cx", closeXY[0]);
    close.attr("cy", closeXY[1]);

    var nResize = gSvg.select("#" + grp + "nResize");
    var nResizeXY = getElementXYofRect(bBoxX, bBoxY, "nResize", rectId);

    nResize.transform("translate(0 0)");
    nResize.attr("cx", nResizeXY[0]);
    nResize.attr("cy", nResizeXY[1]);

    var sResize = gSvg.select("#" + grp + "sResize");
    var sResizeXY = getElementXYofRect(bBoxX, bBoxY, "sResize", rectId);

    sResize.transform("translate(0 0)");
    sResize.attr("cx", sResizeXY[0]);
    sResize.attr("cy", sResizeXY[1]);

    var wResize = gSvg.select("#" + grp + "wResize");
    var wResizeXY = getElementXYofRect(bBoxX, bBoxY, "wResize", rectId);

    wResize.transform("translate(0 0)");
    wResize.attr("cx", wResizeXY[0]);
    wResize.attr("cy", wResizeXY[1]);

    var eResize = gSvg.select("#" + grp + "eResize");
    var eResizeXY = getElementXYofRect(bBoxX, bBoxY, "eResize", rectId);

    eResize.transform("translate(0 0)");
    eResize.attr("cx", eResizeXY[0]);
    eResize.attr("cy", eResizeXY[1]);

    var rResize = gSvg.select("#" + grp + "rResize");
    var rResizeXY = getElementXYofRect(bBoxX, bBoxY, "rResize", rectId);

    rResize.transform("translate(0 0)");
    rResize.attr("cx", rResizeXY[0]);
    rResize.attr("cy", rResizeXY[1]);

    //var text = gSvg.select("#" + grp + "text");
    //var textXY = getElementXYofRect(bBoxX, bBoxY, "text", rectId);
    //
    //text.transform("translate(0 0)");
    //text.attr("x", textXY[0]);
    //text.attr("y", textXY[1]);

    var label = g.select("#" + grp + "label");
    var labelXY = getElementXYofBBox(bBox, "label");
    if (label) {
        label.transform("translate(0 0)");
        label.attr("x", labelXY[0]);
        label.attr("y", labelXY[1]);
        label.selectAll("div>div").forEach(function (item) {
            item.node.style.width = labelXY[2] - 10 + "px";
        });
    }

    var selected = gSvg.select("#" + grp + "selected");
    var selectedXY = getElementXYofRect(bBoxX, bBoxY, "selected", rectId);
    if(selected) {
        selected.transform("translate(0 0)");
        selected.attr("x", selectedXY[0]);
        selected.attr("y", selectedXY[1]);
    }

}

function rectMove(myData, eventTarget, e) {

    if ("n" == gDragAnchorPos) {

        var dx = 0; // e.clientX - myData.x;
        var dy = e.clientY - myData.y;

        var newHeight = myData.h - dy;
        if (newHeight < RECT_HEIGHT) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        svgEl.attr("y", e.clientY - gStartY);
        svgEl.attr("height", newHeight);

        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("y", e.clientY - gStartY);
        selected.attr("height", newHeight);

        if (RESIZE_BY_RATIO) {

            var newWidth = myData.w * newHeight / myData.h;
            var newX = myData.x1 - (newWidth - myData.w) / 2;

            svgEl.attr("x", newX);
            svgEl.attr("width", newWidth);

            selected.attr("x", newX);
            selected.attr("width", newWidth);

        }


    } else if ("s" == gDragAnchorPos) {

        var dx = 0; // e.clientX - myData.x;
        var dy = e.clientY - myData.y;

        var newHeight = myData.h + dy;
        if (newHeight < RECT_HEIGHT) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        svgEl.attr("height", newHeight);

        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("height", newHeight);

        if (RESIZE_BY_RATIO) {

            var newWidth = myData.w * newHeight / myData.h;
            var newX = myData.x1 - (newWidth - myData.w) / 2;

            svgEl.attr("x", newX);
            svgEl.attr("width", newWidth);

            selected.attr("x", newX);
            selected.attr("width", newWidth);

        }

    } else if ("w" == gDragAnchorPos) {

        var dx = e.clientX - myData.x;
        var dy = 0; // e.clientY - myData.y;

        var newWidth = myData.w - dx;
        if (newWidth < RECT_WIDTH) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        svgEl.attr("x", e.clientX - gStartX);
        svgEl.attr("width", newWidth);

        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("x", e.clientX - gStartX);
        selected.attr("width", newWidth);

        if (RESIZE_BY_RATIO) {

            var newHeight = myData.h * newWidth / myData.w;
            var newY = myData.y1 - (newHeight - myData.h) / 2;

            svgEl.attr("y", newY);
            svgEl.attr("height", newHeight);

            selected.attr("y", newY);
            selected.attr("height", newHeight);

        }

    } else if ("e" == gDragAnchorPos) {

        var dx = e.clientX - myData.x;
        var dy = 0; // e.clientY - myData.y;

        var newWidth = myData.w + dx;
        if (newWidth < RECT_WIDTH) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        svgEl.attr("width", newWidth);

        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("width", newWidth);

        if (RESIZE_BY_RATIO) {

            var newHeight = myData.h * newWidth / myData.w;
            var newY = myData.y1 - (newHeight - myData.h) / 2;

            svgEl.attr("y", newY);
            svgEl.attr("height", newHeight);

            selected.attr("y", newY);
            selected.attr("height", newHeight);

        }

    } else if ("r" == gDragAnchorPos) {

        var dx = e.clientX - myData.x;
        var dy = e.clientY - myData.y;

        var newWidth = myData.w + dx;
        if (newWidth < RECT_WIDTH) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        svgEl.attr("width", newWidth);

        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("width", newWidth);

        var newHeight = myData.h * newWidth / myData.w;
        var newY = myData.y1 - (newHeight - myData.h) / 2;

        svgEl.attr("y", newY);
        svgEl.attr("height", newHeight);

        selected.attr("y", newY);
        selected.attr("height", newHeight);

    }

}

//endregion

//region Ellipse
function addEllipse(type) {

    var grp = getGroupPrefix(gSerialNo);
    var grpId = grp + "g";
    var ellipseId = grp + "ellipse";
    var newEllipse;

    var initCx = ELLIPSE_RX + 10;
    var initCy = ELLIPSE_RY + 10;
    if (type === 'circle') {
        initCx = CIRCLE_RX + 10;
        initCy = CIRCLE_RY + 10;
    } else if (type === 'xs-circle') {
        initCx = XS_CIRCLE_RX + 10;
        initCy = XS_CIRCLE_RY + 10;
    }

    var allEllipse = gSvg.selectAll("ellipse");
    for (var i = 0; i < allEllipse.length; i++) {
        if (initCx == allEllipse[i].attr("cx") && initCy == allEllipse[i].attr("cy")) {
            initCx += 10;
            initCy += 10;
        }
    }

    if (type === 'circle') {
        newEllipse = gSvg.ellipse(initCx, initCy, CIRCLE_RX, CIRCLE_RY);
    } else if (type === 'xs-circle') {
        newEllipse = gSvg.ellipse(initCx, initCy, XS_CIRCLE_RX, XS_CIRCLE_RY);
    } else {
        newEllipse = gSvg.ellipse(initCx, initCy, ELLIPSE_RX, ELLIPSE_RY);
    }
    newEllipse.addClass("myEllipse");
    newEllipse.attr("id", ellipseId);

    var bBoxEllipse = newEllipse.getBBox();
    var selected = generateSelectedMark(bBoxEllipse, grp);

    var closeId = grp + "close";
    var closeXY = getElementXYofEllipse(bBoxEllipse.x, bBoxEllipse.y, "close", ellipseId);
    var close = gSvg.circle(closeXY[0], closeXY[1], CIRCLE_R);
    close.addClass("myClose");
    close.addClass("hide");
    close.attr("id", closeId);

    var nResizeId = grp + "nResize";
    var nResizeXY = getElementXYofEllipse(bBoxEllipse.x, bBoxEllipse.y, "nResize", ellipseId);
    var nResize = gSvg.circle(nResizeXY[0], nResizeXY[1], CIRCLE_R);
    nResize.addClass("myNResize");
    nResize.addClass("hide");
    nResize.attr("id", nResizeId);

    var sResizeId = grp + "sResize";
    var sResizeXY = getElementXYofEllipse(bBoxEllipse.x, bBoxEllipse.y, "sResize", ellipseId);
    var sResize = gSvg.circle(sResizeXY[0], sResizeXY[1], CIRCLE_R);
    sResize.addClass("mySResize");
    sResize.addClass("hide");
    sResize.attr("id", sResizeId);

    var wResizeId = grp + "wResize";
    var wResizeXY = getElementXYofEllipse(bBoxEllipse.x, bBoxEllipse.y, "wResize", ellipseId);
    var wResize = gSvg.circle(wResizeXY[0], wResizeXY[1], CIRCLE_R);
    wResize.addClass("myWResize");
    wResize.addClass("hide");
    wResize.attr("id", wResizeId);

    var eResizeId = grp + "eResize";
    var eResizeXY = getElementXYofEllipse(bBoxEllipse.x, bBoxEllipse.y, "eResize", ellipseId);
    var eResize = gSvg.circle(eResizeXY[0], eResizeXY[1], CIRCLE_R);
    eResize.addClass("myEResize");
    eResize.addClass("hide");
    eResize.attr("id", eResizeId);

    var rResizeId = grp + "rResize";
    var rResizeXY = getElementXYofEllipse(bBoxEllipse.x, bBoxEllipse.y, "rResize", ellipseId);
    var rResize = gSvg.circle(rResizeXY[0], rResizeXY[1], CIRCLE_R);
    rResize.addClass("myRResize");
    rResize.addClass("hide");
    rResize.attr("id", rResizeId);

    var label = initLabelForElement(bBoxEllipse, grp);

    var g = gSvg.g(newEllipse, close, nResize, sResize, wResize, eResize, rResize, selected, label);
    g.attr("id", grpId);

    registerListener(ellipseId);

    gSerialNo++;

    setSelected(grp);
    gCurrent = grp;

}

function getElementXYofEllipse(bBoxX, bBoxY, elName, ellipseId) {

    var xy = [];
    var ellipse = gSvg.select("#" + ellipseId);

    var rx = (ellipse == null) ? 0 : parseInt(ellipse.attr("rx"), 10);
    var ry = (ellipse == null) ? 0 : parseInt(ellipse.attr("ry"), 10);

    if ("close" == elName) {

        xy.push(bBoxX + rx * 2 - CIRCLE_R_HALF);
        xy.push(bBoxY + CIRCLE_R_HALF);

    } else if ("text" == elName) {

        xy.push(bBoxX + 10);
        xy.push(bBoxY + ry + 5);

    } else if ("nResize" == elName) {

        xy.push(bBoxX + rx);
        xy.push(bBoxY);

    } else if ("sResize" == elName) {

        xy.push(bBoxX + rx);
        xy.push(bBoxY + ry * 2);

    } else if ("wResize" == elName) {

        xy.push(bBoxX);
        xy.push(bBoxY + ry);

    } else if ("eResize" == elName) {

        xy.push(bBoxX + rx * 2);
        xy.push(bBoxY + ry);

    } else if ("selected" == elName) {

        xy.push(bBoxX);
        xy.push(bBoxY);

    } else if ("rResize" == elName) {

        xy.push(bBoxX + rx * 2);
        xy.push(bBoxY + ry * 2);

    }

    return xy;

}

function correctEllipseXY(grp, ellipse) {

    var g = gSvg.select("#" + grp + "g");

    var tStrAry = Snap.parseTransformString(g.attr("transform"));

    var ellipseId = grp + "ellipse";
    if (tStrAry.length != 0) {

        var cx = parseInt(tStrAry[0][1], 10);
        var cy = parseInt(tStrAry[0][2], 10);

        var nowX = parseInt(ellipse.attr("cx"), 10);
        var nowY = parseInt(ellipse.attr("cy"), 10);

        cx += nowX;
        cy += nowY;

        g.transform("translate(0 0)");

        ellipse.transform("translate(0 0)");
        ellipse.attr("cx", cx);
        ellipse.attr("cy", cy);

    }

    var bBoxEllipse = ellipse.getBBox();

    var close = gSvg.select("#" + grp + "close");
    var closeXY = getElementXYofEllipse(bBoxEllipse.x, bBoxEllipse.y, "close", ellipseId);

    close.transform("translate(0 0)");
    close.attr("cx", closeXY[0]);
    close.attr("cy", closeXY[1]);

    var nResize = gSvg.select("#" + grp + "nResize");
    var nResizeXY = getElementXYofEllipse(bBoxEllipse.x, bBoxEllipse.y, "nResize", ellipseId);

    nResize.transform("translate(0 0)");
    nResize.attr("cx", nResizeXY[0]);
    nResize.attr("cy", nResizeXY[1]);

    var sResize = gSvg.select("#" + grp + "sResize");
    var sResizeXY = getElementXYofEllipse(bBoxEllipse.x, bBoxEllipse.y, "sResize", ellipseId);

    sResize.transform("translate(0 0)");
    sResize.attr("cx", sResizeXY[0]);
    sResize.attr("cy", sResizeXY[1]);

    var wResize = gSvg.select("#" + grp + "wResize");
    var wResizeXY = getElementXYofEllipse(bBoxEllipse.x, bBoxEllipse.y, "wResize", ellipseId);

    wResize.transform("translate(0 0)");
    wResize.attr("cx", wResizeXY[0]);
    wResize.attr("cy", wResizeXY[1]);

    var eResize = gSvg.select("#" + grp + "eResize");
    var eResizeXY = getElementXYofEllipse(bBoxEllipse.x, bBoxEllipse.y, "eResize", ellipseId);

    eResize.transform("translate(0 0)");
    eResize.attr("cx", eResizeXY[0]);
    eResize.attr("cy", eResizeXY[1]);

    var rResize = gSvg.select("#" + grp + "rResize");
    var rResizeXY = getElementXYofEllipse(bBoxEllipse.x, bBoxEllipse.y, "rResize", ellipseId);

    rResize.transform("translate(0 0)");
    rResize.attr("cx", rResizeXY[0]);
    rResize.attr("cy", rResizeXY[1]);

    //var text = gSvg.select("#" + grp + "text");
    //var textXY = getElementXYofEllipse(bBoxEllipse.x, bBoxEllipse.y, "text", ellipseId);
    //
    //text.transform("translate(0 0)");
    //text.attr("x", textXY[0]);
    //text.attr("y", textXY[1]);
    var label = g.select("#" + grp + "label");
    var labelXY = getElementXYofBBox(bBoxEllipse, "label");
    label.transform("translate(0 0)");
    label.attr("x", labelXY[0]);
    label.attr("y", labelXY[1]);
    label.selectAll("div>div").forEach(function (item) {
        item.node.style.width = labelXY[2] - 10 + "px";
    });

    var selected = gSvg.select("#" + grp + "selected");
    var selectedXY = getElementXYofEllipse(bBoxEllipse.x, bBoxEllipse.y, "selected", ellipseId);
    selected.transform("translate(0 0)");
    selected.attr("x", selectedXY[0]);
    selected.attr("y", selectedXY[1]);

}

function ellipseMove(myData, eventTarget, e) {

    if ("n" == gDragAnchorPos) {

        var dx = 0; // e.clientX - x;
        var dy = e.clientY - myData.y;

        var newRy = myData.ry - dy / 2;
        if (newRy < ELLIPSE_RY) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        dy /= 2;
        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        svgEl.attr("cy", myData.cy + dy);
        svgEl.attr("ry", newRy);

        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("y", svgEl.getBBox().y);
        selected.attr("height", svgEl.getBBox().height);

        if (RESIZE_BY_RATIO) {

            var newRx = myData.rx * newRy / myData.ry;

            svgEl.attr("rx", newRx);

            selected.attr("x", svgEl.getBBox().x);
            selected.attr("width", svgEl.getBBox().width);

        }

    } else if ("s" == gDragAnchorPos) {

        var dx = 0; // e.clientX - x;
        var dy = e.clientY - myData.y;

        var newRy = myData.ry + dy / 2;
        if (newRy < ELLIPSE_RY) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        dy /= 2;
        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        svgEl.attr("cy", myData.cy + dy);
        svgEl.attr("ry", newRy);

        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("height", svgEl.getBBox().height);

        if (RESIZE_BY_RATIO) {

            var newRx = myData.rx * newRy / myData.ry;

            svgEl.attr("rx", newRx);

            selected.attr("x", svgEl.getBBox().x);
            selected.attr("width", svgEl.getBBox().width);

        }

    } else if ("w" == gDragAnchorPos) {

        var dx = e.clientX - myData.x;
        var dy = 0; // e.clientY - y;

        var newRx = myData.rx - dx / 2;
        if (newRx < ELLIPSE_RX) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        dx /= 2;
        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        svgEl.attr("cx", myData.cx + dx);
        svgEl.attr("rx", newRx);

        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("x", svgEl.getBBox().x);
        selected.attr("width", svgEl.getBBox().width);

        if (RESIZE_BY_RATIO) {

            var newRy = myData.ry * newRx / myData.rx;

            svgEl.attr("ry", newRy);

            selected.attr("y", svgEl.getBBox().y);
            selected.attr("height", svgEl.getBBox().height);

        }

    } else if ("e" == gDragAnchorPos) {

        var dx = e.clientX - myData.x;
        var dy = 0; // e.clientY - y;

        var newRx = myData.rx + dx / 2;
        if (newRx < ELLIPSE_RX) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        dx /= 2;
        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        svgEl.attr("cx", myData.cx + dx);
        svgEl.attr("rx", newRx);

        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("width", svgEl.getBBox().width);

        if (RESIZE_BY_RATIO) {

            var newRy = myData.ry * newRx / myData.rx;

            svgEl.attr("ry", newRy);

            selected.attr("y", svgEl.getBBox().y);
            selected.attr("height", svgEl.getBBox().height);

        }

    } else if ("r" == gDragAnchorPos) {

        var dx = e.clientX - myData.x;
        var dy = e.clientY - myData.y;

        var newRx = myData.rx + dx / 2;
        if (newRx < ELLIPSE_RX) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        dx /= 2;
        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        svgEl.attr("cx", myData.cx + dx);
        svgEl.attr("rx", newRx);

        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("width", svgEl.getBBox().width);

        var newRy = myData.ry * newRx / myData.rx;

        svgEl.attr("ry", newRy);

        selected.attr("y", svgEl.getBBox().y);
        selected.attr("height", svgEl.getBBox().height);

    }

}

//endregion

//region Boundary
function getElementXYofLine(bBoxX, bBoxY, elName, lineId) {

    var xy = [];
    var line = gSvg.select("#" + lineId);

    var width = (line == null) ? 0 : parseInt(line.attr("x1"), 10) - parseInt(line.attr("x2"), 10);
    width = Math.abs(width);

    if ("text" == elName) {

        var grp = getGroupPrefix(lineId);
        var text = gSvg.select("#" + grp + "text");
        var textWidth = 70;
        if (text) {
            var textBBox = text.getBBox();
            textWidth = textBBox.width;
        }

        xy.push(bBoxX - textWidth - 10);
        xy.push(bBoxY);

    } else if ("wResize" == elName) {

        xy.push(bBoxX - CIRCLE_R);
        xy.push(bBoxY);

    } else if ("eResize" == elName) {

        xy.push(bBoxX + width - CIRCLE_R);
        xy.push(bBoxY);

    }

    return xy;

}

function addLine() {

    var grp = getGroupPrefix(gSerialNo);
    var grpId = grp + "g";
    var lineId = grp + "line";
    var newLine = gSvg.line(10, 60, 110, 60);
    newLine.addClass("myLine");
    newLine.attr("id", lineId);

    newLine.mouseover(rectMouseOver);
    newLine.mouseout(rectMouseOut);
    newLine.mousedown(svgElMouseDown);

    newLine.node.addEventListener("contextmenu", lineContextMenu);

    var bBoxLine = newLine.getBBox();

    var wResizeId = grp + "wResize";
    var wResizeXY = getElementXYofLine(bBoxLine.x, bBoxLine.y, "wResize", lineId);
    var wResize = gSvg.circle(wResizeXY[0], wResizeXY[1], CIRCLE_R);
    wResize.addClass("myWResize");
    wResize.addClass("hide");
    wResize.attr("id", wResizeId);

    wResize.mouseover(rectMouseOver);
    wResize.mouseout(rectMouseOut);
    wResize.mousedown(wResizeLineMouseDown);

    var eResizeId = grp + "eResize";
    var eResizeXY = getElementXYofLine(bBoxLine.x, bBoxLine.y, "eResize", lineId);
    var eResize = gSvg.circle(eResizeXY[0], eResizeXY[1], CIRCLE_R);
    eResize.addClass("myEResize");
    eResize.addClass("hide");
    eResize.attr("id", eResizeId);

    eResize.mouseover(rectMouseOver);
    eResize.mouseout(rectMouseOut);
    eResize.mousedown(eResizeLineMouseDown);


    var textId = grp + "text";
    var textXY = getElementXYofLine(bBoxLine.x, bBoxLine.y, "text", lineId);
    var text = gSvg.text(textXY[0], textXY[1], "Label");
    text.attr("id", textId);
    text.addClass("myLabel");

    text.dblclick(textDblClick);

    var g = gSvg.g(newLine, wResize, eResize, text);
    g.attr("id", grpId);

    gSerialNo++;

}

function lineContextMenu(e) {

    e.preventDefault();

    var r = confirm(REMOVE_LINE_MSG);
    if (!r) {
        return;
    }

    var grp = getGroupPrefix(this.id);
    var grpId = grp + "g";
    gSvg.select("#" + grpId).remove();

    return false;

}

function correctLineXY(grp, line) {

    var g = gSvg.select("#" + grp + "g");

    var tStrAry = Snap.parseTransformString(g.attr("transform"));

    // FIXME: re-init bug
    if (tStrAry.length == 0) {
        return;
    }

    var x = parseInt(tStrAry[0][1], 10);
    var y = parseInt(tStrAry[0][2], 10);

    var nowX1 = parseInt(line.attr("x1"), 10);
    var nowY1 = parseInt(line.attr("y1"), 10);
    var nowX2 = parseInt(line.attr("x2"), 10);
    var nowY2 = parseInt(line.attr("y2"), 10);

    var x1 = x + nowX1;
    var y1 = y + nowY1;
    var x2 = x + nowX2;
    var y2 = y + nowY2;

    //g.attr("transform", "");
    g.transform("translate(0 0)");

    //rect.attr("transform", "");
    var lineId = grp + "line";
    line.transform("translate(0 0)");
    line.attr("x1", x1);
    line.attr("y1", y1);
    line.attr("x2", x2);
    line.attr("y2", y2);

    var wResize = gSvg.select("#" + grp + "wResize");
    var wResizeXY = getElementXYofLine(x1, y1, "wResize", lineId);

    wResize.transform("translate(0 0)");
    wResize.attr("cx", wResizeXY[0]);
    wResize.attr("cy", wResizeXY[1]);

    var eResize = gSvg.select("#" + grp + "eResize");
    var eResizeXY = getElementXYofLine(x1, y1, "eResize", lineId);

    eResize.transform("translate(0 0)");
    eResize.attr("cx", eResizeXY[0]);
    eResize.attr("cy", eResizeXY[1]);

    var text = gSvg.select("#" + grp + "text");
    var textXY = getElementXYofLine(x1, y1, "text", lineId);

    //text.attr("transform", "");
    text.transform("translate(0 0)");
    text.attr("x", textXY[0]);
    text.attr("y", textXY[1]);

}

function wResizeLineMouseDown() {

    var id = this.attr("id");
    var grp = getGroupPrefix(id);
    gCurrent = grp;

    gDragAnchor = "wResize";
    var svgEl = gSvg.select("#" + grp + gDragAnchor);

    var line = gSvg.select("#" + grp + "line");

    svgEl.data("mousedown-x", event.clientX);
    svgEl.data("mousedown-y", event.clientY);
    svgEl.data("mousedown-x1", parseInt(line.attr("x1"), 10));
    svgEl.data("mousedown-y1", parseInt(line.attr("y1"), 10));
    svgEl.data("mousedown-x2", parseInt(line.attr("x2"), 10));
    svgEl.data("mousedown-y2", parseInt(line.attr("y2"), 10));

    gDrawArea.onmousemove = wResizeLineMouseMove;
    gDrawArea.onmouseup = wResizeLineMouseUp;
}

function wResizeLineMouseMove(event) {

    var grp;
    if ("" != gCurrent) {
        grp = gCurrent;
    } else {
        return;
    }

    var svgEl = gSvg.select("#" + grp + gDragAnchor);

    var x = (parseInt(svgEl.data('mousedown-x')) || 0);
    var y = (parseInt(svgEl.data('mousedown-y')) || 0);
    var x1 = (parseInt(svgEl.data('mousedown-x1')) || 0);
    var y1 = (parseInt(svgEl.data('mousedown-y1')) || 0);
    var x2 = (parseInt(svgEl.data('mousedown-x2')) || 0);
    var y2 = (parseInt(svgEl.data('mousedown-y2')) || 0);

    var dx = event.clientX - x;
    var dy = event.clientY - y;

    var newX1 = x1 + dx;
    //if (Math.abs(newX1 - x1) < LINE_WIDTH) {
    //    return;
    //}

    var myMatrix = new Snap.Matrix();
    myMatrix.translate(dx, dy);

    svgEl.transform(myMatrix);

    var line = gSvg.select("#" + gCurrent + "line");
    line.attr("x1", newX1);

}

function wResizeLineMouseUp() {

    if ("" != gCurrent) {

        var grp = getGroupPrefix(gCurrent);
        //var svgEl = gSvg.select("#" + gCurrent + gDragAnchor);

        var line = gSvg.select("#" + gCurrent + "line");
        correctXY(grp, line, "line");

    }

    gDrawArea.onmousemove = null;
    gDrawArea.onmouseup = null;

    gCurrent = "";
    gDragAnchor = "";
}

function eResizeLineMouseDown() {

    var id = this.attr("id");
    var grp = getGroupPrefix(id);
    gCurrent = grp;

    gDragAnchor = "eResize";
    var svgEl = gSvg.select("#" + grp + gDragAnchor);

    var line = gSvg.select("#" + grp + "line");

    svgEl.data("mousedown-x", event.clientX);
    svgEl.data("mousedown-y", event.clientY);
    svgEl.data("mousedown-x1", parseInt(line.attr("x1"), 10));
    svgEl.data("mousedown-y1", parseInt(line.attr("y1"), 10));
    svgEl.data("mousedown-x2", parseInt(line.attr("x2"), 10));
    svgEl.data("mousedown-y2", parseInt(line.attr("y2"), 10));

    gDrawArea.onmousemove = eResizeLineMouseMove;
    gDrawArea.onmouseup = eResizeLineMouseUp;
}

function eResizeLineMouseMove(event) {

    var grp;
    if ("" != gCurrent) {
        grp = gCurrent;
    } else {
        return;
    }

    var svgEl = gSvg.select("#" + grp + gDragAnchor);

    var x = (parseInt(svgEl.data('mousedown-x')) || 0);
    var y = (parseInt(svgEl.data('mousedown-y')) || 0);
    var x1 = (parseInt(svgEl.data('mousedown-x1')) || 0);
    var y1 = (parseInt(svgEl.data('mousedown-y1')) || 0);
    var x2 = (parseInt(svgEl.data('mousedown-x2')) || 0);
    var y2 = (parseInt(svgEl.data('mousedown-y2')) || 0);

    var dx = event.clientX - x;
    var dy = event.clientY - y;

    var newX2 = x2 + dx;
    //if (Math.abs(newX2 - x2) < LINE_WIDTH) {
    //    return;
    //}

    var myMatrix = new Snap.Matrix();
    myMatrix.translate(dx, dy);

    svgEl.transform(myMatrix);

    var line = gSvg.select("#" + gCurrent + "line");
    line.attr("x2", newX2);

}

function eResizeLineMouseUp() {

    if ("" != gCurrent) {

        var grp = getGroupPrefix(gCurrent);
        //var svgEl = gSvg.select("#" + gCurrent + gDragAnchor);

        var line = gSvg.select("#" + gCurrent + "line");
        correctXY(grp, line, "line");

    }

    gDrawArea.onmousemove = null;
    gDrawArea.onmouseup = null;

    gCurrent = "";
    gDragAnchor = "";
}
//endregion

//region Brace
// refer: http://bl.ocks.org/alexhornbake/6005176
//returns path string d for <path d="This string">
//a curly brace between x1,y1 and x2,y2, w pixels wide
//and q factor, .5 is normal, higher q = more expressive bracket
function makeCurlyBrace(x1, y1, x2, y2, w, q) {

    //Calculate unit vector
    var dx = x1 - x2;
    var dy = y1 - y2;
    var len = Math.sqrt(dx * dx + dy * dy);
    dx = dx / len;
    dy = dy / len;

    //Calculate Control Points of path,
    var qx1 = x1 + q * w * dy;
    var qy1 = y1 - q * w * dx;
    var qx2 = (x1 - .25 * len * dx) + (1 - q) * w * dy;
    var qy2 = (y1 - .25 * len * dy) - (1 - q) * w * dx;
    var tx1 = (x1 - .5 * len * dx) + w * dy;
    var ty1 = (y1 - .5 * len * dy) - w * dx;
    var qx3 = x2 + q * w * dy;
    var qy3 = y2 - q * w * dx;
    var qx4 = (x1 - .75 * len * dx) + (1 - q) * w * dy;
    var qy4 = (y1 - .75 * len * dy) - (1 - q) * w * dx;

    return ( "M " + x1 + " " + y1 +
    " Q " + qx1 + " " + qy1 + " " + qx2 + " " + qy2 +
    " T " + tx1 + " " + ty1 +
    " M " + x2 + " " + y2 +
    " Q " + qx3 + " " + qy3 + " " + qx4 + " " + qy4 +
    " T " + tx1 + " " + ty1 );
}

function addBrace(dir) {

    var grp = getGroupPrefix(gSerialNo);
    var grpId = grp + "g";
    var braceId = grp + "brace";

    var pathStr = "";
    if ("left" == dir) {
        pathStr = makeCurlyBrace(20, 20, 20, 60, BRACE_WIDTH, BRACE_Q);
    } else if ("right" == dir) {
        pathStr = makeCurlyBrace(20, 60, 20, 20, BRACE_WIDTH, BRACE_Q);
    } else {
        return;
    }

    var newBrace = gSvg.path(pathStr);
    newBrace.addClass("myBrace");
    newBrace.attr("id", braceId);
    newBrace.attr("dir", dir);

    //newBrace.mouseover(rectMouseOver);
    //newBrace.mouseout(rectMouseOut);
    //newBrace.mousedown(svgElMouseDown);

    //newBrace.node.addEventListener("contextmenu", showContextMenu);

    var bBoxBrace = newBrace.getBBox();
    var selected = generateSelectedMark(bBoxBrace, grp);

    var closeId = grp + "close";
    var closeXY = getElementXYofBrace(bBoxBrace.x, bBoxBrace.y, "close", braceId);
    var close = gSvg.circle(closeXY[0], closeXY[1], CIRCLE_R);
    close.addClass("myClose");
    close.addClass("hide");
    close.attr("id", closeId);

    //close.mousedown(closeClick);

    var nResizeId = grp + "nResize";
    var nResizeXY = getElementXYofBrace(bBoxBrace.x, bBoxBrace.y, "nResize", braceId);
    var nResize = gSvg.circle(nResizeXY[0], nResizeXY[1], CIRCLE_R);
    nResize.addClass("myNResize");
    nResize.addClass("hide");
    nResize.attr("id", nResizeId);

    //nResize.mouseover(rectMouseOver);
    //nResize.mouseout(rectMouseOut);
    //nResize.mousedown(nResizeBraceMouseDown);

    var sResizeId = grp + "sResize";
    var sResizeXY = getElementXYofBrace(bBoxBrace.x, bBoxBrace.y, "sResize", braceId);
    var sResize = gSvg.circle(sResizeXY[0], sResizeXY[1], CIRCLE_R);
    sResize.addClass("mySResize");
    sResize.addClass("hide");
    sResize.attr("id", sResizeId);

    //sResize.mouseover(rectMouseOver);
    //sResize.mouseout(rectMouseOut);
    //sResize.mousedown(sResizeBraceMouseDown);
    registerListener(braceId);

    var g = gSvg.g(newBrace, nResize, sResize, selected, close);
    g.attr("id", grpId);

    gSerialNo++;

    setSelected(grp);
    gCurrent = grp;

}

function getElementXYofBrace(bBoxX, bBoxY, elName, braceId) {

    var xy = [];
    var brace = gSvg.select("#" + braceId);
    var bBox = brace.getBBox();

    if ("nResize" == elName) {

        xy.push(bBoxX + (bBox.width ) / 2);
        xy.push(bBoxY);

    } else if ("sResize" == elName) {

        xy.push(bBoxX + (bBox.width ) / 2);
        xy.push(bBoxY + bBox.height);

    } else if ("selected" == elName) {

        xy.push(bBoxX);
        xy.push(bBoxY);

    } else if ("close" == elName) {

        xy.push(bBoxX + bBox.width - CIRCLE_R_HALF);
        xy.push(bBoxY + bBox.height / 2);

    }

    return xy;

}

function correctBraceXY(grp, brace) {

    var g = gSvg.select("#" + grp + "g");

    var tStrAry = Snap.parseTransformString(g.attr("transform"));

    var braceId = grp + "brace";
    if (tStrAry.length != 0) {

        var dx = parseInt(tStrAry[0][1], 10);
        var dy = parseInt(tStrAry[0][2], 10);

        var pathStr = brace.attr("d");
        var pathAry = Snap.parsePathString(pathStr);

        var nowX = parseInt(pathAry[0][1], 10);
        var nowY = parseInt(pathAry[0][2], 10);

        var x = dx + nowX;
        var y = dy + nowY;

        var dir = brace.attr("dir");
        var newPath = "";
        if ("left" == dir) {
            newPath = makeCurlyBrace(x, y, x, y + brace.getBBox().height, BRACE_WIDTH, BRACE_Q);
        } else if ("right" == dir) {
            newPath = makeCurlyBrace(x, y, x, y - brace.getBBox().height, BRACE_WIDTH, BRACE_Q);
        } else {
            return;
        }

        g.transform("translate(0 0)");

        brace.transform("translate(0 0)");
        brace.attr("d", newPath);

    }

    var bBox = brace.getBBox();

    var close = gSvg.select("#" + grp + "close");
    var closeXY = getElementXYofBrace(bBox.x, bBox.y, "close", braceId);

    close.transform("translate(0 0)");
    close.attr("cx", closeXY[0]);
    close.attr("cy", closeXY[1]);

    var nResize = gSvg.select("#" + grp + "nResize");
    var nResizeXY = getElementXYofBrace(bBox.x, bBox.y, "nResize", braceId);

    nResize.transform("translate(0 0)");
    nResize.attr("cx", nResizeXY[0]);
    nResize.attr("cy", nResizeXY[1]);

    var sResize = gSvg.select("#" + grp + "sResize");
    var sResizeXY = getElementXYofBrace(bBox.x, bBox.y, "sResize", braceId);

    sResize.transform("translate(0 0)");
    sResize.attr("cx", sResizeXY[0]);
    sResize.attr("cy", sResizeXY[1]);

    var selected = gSvg.select("#" + grp + "selected");
    var selectedXY = getElementXYofRect(bBox.x, bBox.y, "selected", braceId);
    selected.transform("translate(0 0)");
    selected.attr("x", selectedXY[0]);
    selected.attr("y", selectedXY[1]);

}

function braceMove(myData, eventTarget, e) {

    if ("n" == gDragAnchorPos) {

        //var dx = e.clientX - myData.x;
        var dy = e.clientY - myData.y;

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        var dir = svgEl.attr("dir");
        //
        var newPath = "";
        if ("left" == dir) {
            newPath = makeCurlyBrace(myData.x1 + BRACE_WIDTH, myData.y1 + dy, myData.x2 + BRACE_WIDTH, myData.y2, BRACE_WIDTH, BRACE_Q);
        } else if ("right" == dir) {
            newPath = makeCurlyBrace(myData.x1, myData.y2, myData.x2, myData.y1 + dy, BRACE_WIDTH, BRACE_Q);
        } else {
            return;
        }

        svgEl.attr("d", newPath);

        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("y", e.clientY - gStartY);
        selected.attr("height", svgEl.getBBox().height);

    } else if ("s" == gDragAnchorPos) {

        //var dx = e.clientX - x;
        var dy = e.clientY - myData.y;

        var svgEl = gSvg.select("#" + gCurrent + gDragType);

        var dir = svgEl.attr("dir");
        var newPath = "";
        if ("left" == dir) {
            newPath = makeCurlyBrace(myData.x1 + BRACE_WIDTH, myData.y1, myData.x2 + BRACE_WIDTH, myData.y2 + dy, BRACE_WIDTH, BRACE_Q);
        } else if ("right" == dir) {
            newPath = makeCurlyBrace(myData.x1, myData.y2 + dy, myData.x2, myData.y1, BRACE_WIDTH, BRACE_Q);
        } else {
            return;
        }

        svgEl.attr("d", newPath);

        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("height", svgEl.getBBox().height);

    }

}

//endregion

//region BreakDown
function addBreak() {

    var grp = getGroupPrefix(gSerialNo);
    var breakId = grp + "break";

    var pathStr = "M0 0 L50 50 L30 50 L90 100 L40 50 Z";
    var newBreak = gSvg.path(pathStr);
    newBreak.addClass("myBreak");
    newBreak.attr("id", breakId);

    //newBreak.mouseover(connectorMouseOver);
    //newBreak.mouseout(connectorMouseOut);
    //newBreak.mousedown(svgElMouseDown);

    //newBreak.node.addEventListener("contextmenu", showContextMenu);

    var pathAry = Snap.parsePathString(pathStr);
    var pathLen = pathAry.length;
    var ratioStr = "0,0";

    var bBoxBreak = newBreak.getBBox();

    for (var i = 1; i < pathLen; i++) {

        var action = pathAry[i][0];

        if ("Z" == action.toUpperCase()) {
            break;
        }

        var lineToX = pathAry[i][1];
        var lineToY = pathAry[i][2];

        var ratioX = (lineToX - bBoxBreak.x) / bBoxBreak.width;
        var ratioY = (lineToY - bBoxBreak.y) / bBoxBreak.height;

        ratioStr += "|" + roundByDigits(ratioX, 2) + "," + roundByDigits(ratioY, 2);

    }
    newBreak.attr("_ratio", ratioStr);

    var selected = generateSelectedMark(bBoxBreak, grp);

    var closeId = grp + "close";
    var closeXY = getElementXYofBBox(bBoxBreak, "close");
    var close = gSvg.circle(closeXY[0], closeXY[1], CIRCLE_R);
    close.addClass("myClose");
    close.addClass("hide");
    close.attr("id", closeId);

    //close.mousedown(closeClick);

    var nResizeId = grp + "nResize";
    var nResizeXY = getElementXYofBBox(bBoxBreak, "nResize");
    var nResize = gSvg.circle(nResizeXY[0], nResizeXY[1], CIRCLE_R);
    nResize.addClass("myNResize");
    nResize.addClass("hide");
    nResize.attr("id", nResizeId);

    //nResize.mousedown(nResizeBreakMouseDown);

    var sResizeId = grp + "sResize";
    var sResizeXY = getElementXYofBBox(bBoxBreak, "sResize");
    var sResize = gSvg.circle(sResizeXY[0], sResizeXY[1], CIRCLE_R);
    sResize.addClass("mySResize");
    sResize.addClass("hide");
    sResize.attr("id", sResizeId);

    //sResize.mousedown(sResizeBreakMouseDown);

    var wResizeId = grp + "wResize";
    var wResizeXY = getElementXYofBBox(bBoxBreak, "wResize");
    var wResize = gSvg.circle(wResizeXY[0], wResizeXY[1], CIRCLE_R);
    wResize.addClass("myWResize");
    wResize.addClass("hide");
    wResize.attr("id", wResizeId);

    //wResize.mousedown(wResizeBreakMouseDown);

    var eResizeId = grp + "eResize";
    var eResizeXY = getElementXYofBBox(bBoxBreak, "eResize");
    var eResize = gSvg.circle(eResizeXY[0], eResizeXY[1], CIRCLE_R);
    eResize.addClass("myEResize");
    eResize.addClass("hide");
    eResize.attr("id", eResizeId);

    //eResize.mousedown(eResizeBreakMouseDown);
    registerListener(breakId);

    var g = gSvg.g(newBreak, close, nResize, sResize, wResize, eResize, selected);
    var grpId = grp + "g";
    g.attr("id", grpId);

    gSerialNo++;

    setSelected(grp);
    gCurrent = grp;

}

function correctBreakXY(grp, conn) {

    var g = gSvg.select("#" + grp + "g");

    var tStrAry = Snap.parseTransformString(g.attr("transform"));

    if (tStrAry.length != 0) {

        var x = parseInt(tStrAry[0][1], 10);
        var y = parseInt(tStrAry[0][2], 10);

        //g.attr("transform", "");
        g.transform("translate(0 0)");

        var pathStr = conn.attr("d");
        pathStr = pathStr.substring(0, pathStr.length - 1);
        var pathAry = Snap.parsePathString(pathStr);
        var pathLen = pathAry.length;

        pathAry.forEach(function (p) {
            p[1] = parseInt(p[1]) + x;
            p[2] = parseInt(p[2]) + y;
        });

        var newPath = "";
        var lastSubPath = [];
        for (var i = 0; i < pathLen; i++) {

            var act = pathAry[i][0];
            var cx = pathAry[i][1];
            var cy = pathAry[i][2];

            newPath += act + " ";
            newPath += cx + " ";
            newPath += cy + " ";

            if (i >= pathLen - 2) {
                lastSubPath.push(cx);
                lastSubPath.push(cy);
            }
        }

        newPath = newPath + "Z";
        //conn.attr("transform", "");
        conn.transform("translate(0 0)");
        conn.attr("d", newPath);

        var bBoxConn = conn.getBBox();
        var close = gSvg.select("#" + grp + "close");
        var closeXY = getElementXYofBBox(bBoxConn, "close");

        close.transform("translate(0 0)");
        close.attr("cx", closeXY[0]);
        close.attr("cy", closeXY[1]);

        var nResize = gSvg.select("#" + grp + "nResize");
        var nResizeXY = getElementXYofBBox(bBoxConn, "nResize");

        nResize.transform("translate(0 0)");
        nResize.attr("cx", nResizeXY[0]);
        nResize.attr("cy", nResizeXY[1]);

        var sResize = gSvg.select("#" + grp + "sResize");
        var sResizeXY = getElementXYofBBox(bBoxConn, "sResize");

        sResize.transform("translate(0 0)");
        sResize.attr("cx", sResizeXY[0]);
        sResize.attr("cy", sResizeXY[1]);

        var wResize = gSvg.select("#" + grp + "wResize");
        var wResizeXY = getElementXYofBBox(bBoxConn, "wResize");

        wResize.transform("translate(0 0)");
        wResize.attr("cx", wResizeXY[0]);
        wResize.attr("cy", wResizeXY[1]);

        var eResize = gSvg.select("#" + grp + "eResize");
        var eResizeXY = getElementXYofBBox(bBoxConn, "eResize");

        eResize.transform("translate(0 0)");
        eResize.attr("cx", eResizeXY[0]);
        eResize.attr("cy", eResizeXY[1]);

        var selected = gSvg.select("#" + grp + "selected");
        var selectedXY = getElementXYofBBox(bBoxConn, "selected");
        selected.transform("translate(0 0)");
        selected.attr("x", selectedXY[0]);
        selected.attr("y", selectedXY[1]);

    }

}

function breakMove(myData, eventTarget, e) {

    if ("n" == gDragAnchorPos) {

        var dx = 0;//e.clientX - myData.x;
        var dy = e.clientY - myData.y;

        var newHeight = myData.h - dy;
        if (newHeight < BREAK_HEIGHT) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var newY = event.clientY - gStartY;
        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("y", newY);
        selected.attr("height", newHeight);

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        var pathStr = svgEl.attr("d");
        var pathAry = Snap.parsePathString(pathStr);
        var pathLen = pathAry.length;

        var newPath = "";
        for (var i = 0; i < pathLen; i++) {

            var act = pathAry[i][0];

            if ("Z" != act.toUpperCase()) {
                var lineToX = pathAry[i][1];
                //var lineToY = pathAry[i][2];

                newPath += act + " ";
                newPath += lineToX + " ";
                newPath += newY + (gRatioAry[i][1] * newHeight) + " ";
            } else {
                newPath += act;
            }

        }

        svgEl.attr("d", newPath);

    } else if ("s" == gDragAnchorPos) {

        var dx = 0;//e.clientX - myData.x;
        var dy = e.clientY - myData.y;

        var newHeight = myData.h + dy;
        if (newHeight < BREAK_HEIGHT) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var newY;
        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("height", newHeight);
        newY = selected.getBBox().y;

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        var pathStr = svgEl.attr("d");
        var pathAry = Snap.parsePathString(pathStr);
        var pathLen = pathAry.length;

        var newPath = "";
        for (var i = 0; i < pathLen; i++) {

            var act = pathAry[i][0];

            if ("Z" != act.toUpperCase()) {
                var lineToX = pathAry[i][1];
                //var lineToY = pathAry[i][2];

                newPath += act + " ";
                newPath += lineToX + " ";
                newPath += newY + (gRatioAry[i][1] * newHeight) + " ";
            } else {
                newPath += act;
            }

        }

        svgEl.attr("d", newPath);

    } else if ("w" == gDragAnchorPos) {

        var dx = e.clientX - myData.x;
        var dy = 0;//e.clientY - myData.y;

        var newWidth = myData.w - dx;
        if (newWidth < BREAK_WIDTH) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var newX = event.clientX - gStartX;
        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("x", newX);
        selected.attr("width", newWidth);

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        var pathStr = svgEl.attr("d");
        var pathAry = Snap.parsePathString(pathStr);
        var pathLen = pathAry.length;

        var newPath = "";
        for (var i = 0; i < pathLen; i++) {

            var act = pathAry[i][0];

            if ("Z" != act.toUpperCase()) {
                //var lineToX = pathAry[i][1];
                var lineToY = pathAry[i][2];

                newPath += act + " ";
                newPath += newX + (gRatioAry[i][0] * newWidth) + " ";
                newPath += lineToY + " ";
            } else {
                newPath += act;
            }

        }

        svgEl.attr("d", newPath);

    } else if ("e" == gDragAnchorPos) {

        var dx = e.clientX - myData.x;
        var dy = 0;//e.clientY - myData.y;

        var newWidth = myData.w + dx;
        if (newWidth < BREAK_WIDTH) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var newX;
        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("width", newWidth);
        newX = selected.getBBox().x;

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        var pathStr = svgEl.attr("d");
        var pathAry = Snap.parsePathString(pathStr);
        var pathLen = pathAry.length;

        var newPath = "";
        for (var i = 0; i < pathLen; i++) {

            var act = pathAry[i][0];

            if ("Z" != act.toUpperCase()) {
                //var lineToX = pathAry[i][1];
                var lineToY = pathAry[i][2];

                newPath += act + " ";
                newPath += newX + (gRatioAry[i][0] * newWidth) + " ";
                newPath += lineToY + " ";
            } else {
                newPath += act;
            }

        }

        svgEl.attr("d", newPath);

    }

}

//endregion

//region Image
function insertImage() {
    document.getElementById("insertImg").click();
}

function addImage() {

    var file = document.getElementById("insertImg").files[0];

    if (!file) {
        return;
    }

    var textType = /image.*/;
    if (!file.type.match(textType)) {
        alert("File not supported!");
        document.getElementById("insertImg").value = "";
        return;
    }

    var fReader = new FileReader();
    fReader.onload = function (event) {

        var grp = getGroupPrefix(gSerialNo);
        //var grpId = grp + "g";
        var imageId = grp + "image";

        var newImage = gSvg.image(event.target.result);
        newImage.addClass("myImage");
        newImage.attr("id", imageId);
        newImage.attr("x", 10);
        newImage.attr("y", 10);

        //newImage.mousedown(svgElMouseDown);

        //newImage.node.addEventListener("contextmenu", showContextMenu);
        newImage.node.addEventListener("load",

            function () {

                var width = toInteger(newImage.attr("width"));
                var height = toInteger(newImage.attr("height"));
                var ratio = toInteger(width / IMAGE_WIDTH);
                var newHeight = toInteger(height / ratio);

                newImage.attr("width", IMAGE_WIDTH);
                newImage.attr("height", newHeight);

                var bBoxImage = newImage.getBBox();
                var selected = generateSelectedMark(bBoxImage, grp);

                var closeId = grp + "close";
                var closeXY = getElementXYofImage("close", imageId);
                var close = gSvg.circle(closeXY[0], closeXY[1], CIRCLE_R);
                close.addClass("myClose");
                close.addClass("hide");
                close.attr("id", closeId);

                //close.mousedown(closeClick);

                var nResizeId = grp + "nResize";
                var nResizeXY = getElementXYofImage("nResize", imageId);
                var nResize = gSvg.circle(nResizeXY[0], nResizeXY[1], CIRCLE_R);
                nResize.addClass("myNResize");
                nResize.addClass("hide");
                nResize.attr("id", nResizeId);

                //nResize.mousedown(nResizeImageMouseDown);

                var sResizeId = grp + "sResize";
                var sResizeXY = getElementXYofImage("sResize", imageId);
                var sResize = gSvg.circle(sResizeXY[0], sResizeXY[1], CIRCLE_R);
                sResize.addClass("mySResize");
                sResize.addClass("hide");
                sResize.attr("id", sResizeId);

                //sResize.mousedown(sResizeImageMouseDown);

                var wResizeId = grp + "wResize";
                var wResizeXY = getElementXYofImage("wResize", imageId);
                var wResize = gSvg.circle(wResizeXY[0], wResizeXY[1], CIRCLE_R);
                wResize.addClass("myWResize");
                wResize.addClass("hide");
                wResize.attr("id", wResizeId);

                //wResize.mousedown(wResizeImageMouseDown);

                var eResizeId = grp + "eResize";
                var eResizeXY = getElementXYofImage("eResize", imageId);
                var eResize = gSvg.circle(eResizeXY[0], eResizeXY[1], CIRCLE_R);
                eResize.addClass("myEResize");
                eResize.addClass("hide");
                eResize.attr("id", eResizeId);

                //eResize.mousedown(eResizeImageMouseDown);
                registerListener(imageId);

                var g = gSvg.g(newImage, nResize, sResize, wResize, eResize, close, selected);
                var grpId = grp + "g";
                g.attr("id", grpId);

                correctImageXY(grp, newImage);

                setSelected(grp);
                gCurrent = grp;

            });

        gSerialNo++;

    };
    fReader.readAsDataURL(file);

}

function getElementXYofImage(elName, imageId) {

    var xy = [];
    var image = gSvg.select("#" + imageId);

    var bBox = image.getBBox();

    if ("nResize" == elName) {

        xy.push(bBox.x + bBox.width / 2);
        xy.push(bBox.y);

    } else if ("sResize" == elName) {

        xy.push(bBox.x + bBox.width / 2);
        xy.push(bBox.y + bBox.height);

    } else if ("wResize" == elName) {

        xy.push(bBox.x);
        xy.push(bBox.y + bBox.height / 2);

    } else if ("eResize" == elName) {

        xy.push(bBox.x + bBox.width);
        xy.push(bBox.y + bBox.height / 2);

    } else if ("close" == elName) {

        xy.push(bBox.x + bBox.width - CIRCLE_R_HALF);
        xy.push(bBox.y + CIRCLE_R_HALF);

    } else if ("selected" == elName) {

        xy.push(bBox.x);
        xy.push(bBox.y);

    }

    return xy;

}

function correctImageXY(grp, image) {

    var g = gSvg.select("#" + grp + "g");

    var tStrAry = Snap.parseTransformString(g.attr("transform"));

    var imageId = grp + "image";
    if (tStrAry.length != 0) {

        var x = parseInt(tStrAry[0][1], 10);
        var y = parseInt(tStrAry[0][2], 10);

        var nowX = parseInt(image.attr("x"), 10);
        var nowY = parseInt(image.attr("y"), 10);

        x += nowX;
        y += nowY;

        g.transform("translate(0 0)");

        image.transform("translate(0 0)");
        image.attr("x", x);
        image.attr("y", y);

    }

    var close = gSvg.select("#" + grp + "close");
    var closeXY = getElementXYofImage("close", imageId);

    close.transform("translate(0 0)");
    close.attr("cx", closeXY[0]);
    close.attr("cy", closeXY[1]);

    var nResize = gSvg.select("#" + grp + "nResize");
    var nResizeXY = getElementXYofImage("nResize", imageId);

    nResize.transform("translate(0 0)");
    nResize.attr("cx", nResizeXY[0]);
    nResize.attr("cy", nResizeXY[1]);

    var sResize = gSvg.select("#" + grp + "sResize");
    var sResizeXY = getElementXYofImage("sResize", imageId);

    sResize.transform("translate(0 0)");
    sResize.attr("cx", sResizeXY[0]);
    sResize.attr("cy", sResizeXY[1]);

    var wResize = gSvg.select("#" + grp + "wResize");
    var wResizeXY = getElementXYofImage("wResize", imageId);

    wResize.transform("translate(0 0)");
    wResize.attr("cx", wResizeXY[0]);
    wResize.attr("cy", wResizeXY[1]);

    var eResize = gSvg.select("#" + grp + "eResize");
    var eResizeXY = getElementXYofImage("eResize", imageId);

    eResize.transform("translate(0 0)");
    eResize.attr("cx", eResizeXY[0]);
    eResize.attr("cy", eResizeXY[1]);

    var selected = gSvg.select("#" + grp + "selected");
    var selectedXY = getElementXYofImage("selected", imageId);
    selected.transform("translate(0 0)");
    selected.attr("x", selectedXY[0]);
    selected.attr("y", selectedXY[1]);

}

function imageMove(myData, eventTarget, e) {

    if ("n" == gDragAnchorPos) {

        var dx = 0;//e.clientX - myData.x;
        var dy = e.clientY - myData.y;

        var newHeight = myData.h - dy;
        if (newHeight < RECT_HEIGHT) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        svgEl.attr("y", e.clientY - gStartY);
        svgEl.attr("height", newHeight);

        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("y", e.clientY - gStartY);
        selected.attr("height", svgEl.getBBox().height);

    } else if ("s" == gDragAnchorPos) {

        var dx = 0; //e.clientX -myData.x;
        var dy = e.clientY - myData.y;

        var newHeight = myData.h + dy;
        if (newHeight < RECT_HEIGHT) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        svgEl.attr("height", newHeight);

        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("height", svgEl.getBBox().height);

    } else if ("w" == gDragAnchorPos) {

        var dx = e.clientX - myData.x;
        var dy = 0;//e.clientY - myData.y;

        var newWidth = myData.w - dx;
        if (newWidth < RECT_WIDTH) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        svgEl.attr("x", e.clientX - gStartX);
        svgEl.attr("width", newWidth);

        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("x", e.clientX - gStartX);
        selected.attr("width", svgEl.getBBox().width);

    } else if ("e" == gDragAnchorPos) {

        var dx = e.clientX - myData.x;
        var dy = 0;//e.clientY - myData.y;

        var newWidth = myData.w + dx;
        if (newWidth < RECT_WIDTH) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        svgEl.attr("width", newWidth);

        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("width", svgEl.getBBox().width);

    }

}

// endregion

//region Custom
var CUSTOM_DEF = {
    "ClippingSquare": {path: "M 30 50 L 130 50 L 130 110 L 10 107 L 10 70 Z", clsName: "myClippingSquare"},
    "Diamond": {path: "M30 50 L10 70 L30 90 L50 70 Z", clsName: "myDiamond"}    
};

function addCustom(customDef) {

    var allRect = gSvg.selectAll("rect");

    var grp = getGroupPrefix(gSerialNo);
    var customId = grp + "custom";

    var pathStr = CUSTOM_DEF[customDef].path;
    var newCustom = gSvg.path(pathStr);
    newCustom.addClass(CUSTOM_DEF[customDef].clsName);
    newCustom.attr("id", customId);

    var pathAry = Snap.parsePathString(pathStr);
    var pathLen = pathAry.length;
    var ratioStr = "";

    var bBoxCustom = newCustom.getBBox();

    for (var i = 0; i < pathLen; i++) {

        var action = pathAry[i][0];

        if ("Z" == action.toUpperCase()) {
            break;
        }

        var lineToX = pathAry[i][1];
        var lineToY = pathAry[i][2];

        var ratioX = (lineToX - bBoxCustom.x) / bBoxCustom.width;
        var ratioY = (lineToY - bBoxCustom.y) / bBoxCustom.height;

        ratioStr += ((i > 0) ? "|" : "") + roundByDigits(ratioX, 2) + "," + roundByDigits(ratioY, 2);

    }
    newCustom.attr("_ratio", ratioStr);

    var selected = generateSelectedMark(bBoxCustom, grp);

    var closeId = grp + "close";
    var closeXY = getElementXYofBBox(bBoxCustom, "close");
    var close = gSvg.circle(closeXY[0], closeXY[1], CIRCLE_R);
    close.addClass("myClose");
    close.addClass("hide");
    close.attr("id", closeId);

    var nResizeId = grp + "nResize";
    var nResizeXY = getElementXYofBBox(bBoxCustom, "nResize");
    var nResize = gSvg.circle(nResizeXY[0], nResizeXY[1], CIRCLE_R);
    nResize.addClass("myNResize");
    nResize.addClass("hide");
    nResize.attr("id", nResizeId);

    var sResizeId = grp + "sResize";
    var sResizeXY = getElementXYofBBox(bBoxCustom, "sResize");
    var sResize = gSvg.circle(sResizeXY[0], sResizeXY[1], CIRCLE_R);
    sResize.addClass("mySResize");
    sResize.addClass("hide");
    sResize.attr("id", sResizeId);

    var wResizeId = grp + "wResize";
    var wResizeXY = getElementXYofBBox(bBoxCustom, "wResize");
    var wResize = gSvg.circle(wResizeXY[0], wResizeXY[1], CIRCLE_R);
    wResize.addClass("myWResize");
    wResize.addClass("hide");
    wResize.attr("id", wResizeId);

    var eResizeId = grp + "eResize";
    var eResizeXY = getElementXYofBBox(bBoxCustom, "eResize");
    var eResize = gSvg.circle(eResizeXY[0], eResizeXY[1], CIRCLE_R);
    eResize.addClass("myEResize");
    eResize.addClass("hide");
    eResize.attr("id", eResizeId);

    var rResizeId = grp + "rResize";
    var rResizeXY = getElementXYofBBox(bBoxCustom, "rResize");
    var rResize = gSvg.circle(rResizeXY[0], rResizeXY[1], CIRCLE_R);
    rResize.addClass("myRResize");
    rResize.addClass("hide");
    rResize.attr("id", rResizeId);

    var label = initLabelForElement(bBoxCustom, grp);

    var g = gSvg.g(newCustom, close, nResize, sResize, wResize, eResize, rResize, selected, label);
    var grpId = grp + "g";
    g.attr("id", grpId);

    registerListener(customId);

    gSerialNo++;

    setSelected(grp);
    gCurrent = grp;

    var boxX = selected.getBBox().x;
    var boxY = selected.getBBox().y;
    for (var i = 0; i < allRect.length; i++) {
        if (boxX == allRect[i].attr("x") && boxY == allRect[i].attr("y")) {

            var myMatrix = new Snap.Matrix();
            myMatrix.translate(10, 10);
            g.transform(myMatrix);
            gDragAnchor = "custom";
            svgElMouseUp();

            boxX = selected.getBBox().x;
            boxY = selected.getBBox().y;

        }
    }


}

function correctCustomXY(grp, conn) {

    var g = gSvg.select("#" + grp + "g");

    var tStrAry = Snap.parseTransformString(g.attr("transform"));

    if (tStrAry.length != 0) {

        var x = parseInt(tStrAry[0][1], 10);
        var y = parseInt(tStrAry[0][2], 10);

        g.transform("translate(0 0)");

        var pathStr = conn.attr("d");
        pathStr = pathStr.substring(0, pathStr.length - 1);
        var pathAry = Snap.parsePathString(pathStr);
        var pathLen = pathAry.length;

        pathAry.forEach(function (p) {
            p[1] = parseInt(p[1]) + x;
            p[2] = parseInt(p[2]) + y;
        });

        var newPath = "";
        for (var i = 0; i < pathLen; i++) {

            var act = pathAry[i][0];
            var cx = pathAry[i][1];
            var cy = pathAry[i][2];

            newPath += act + " ";
            newPath += cx + " ";
            newPath += cy + " ";

        }

        newPath = newPath + "Z";

        conn.transform("translate(0 0)");
        conn.attr("d", newPath);

    }

    var bBoxConn = conn.getBBox();
    var close = gSvg.select("#" + grp + "close");
    var closeXY = getElementXYofBBox(bBoxConn, "close");

    close.transform("translate(0 0)");
    close.attr("cx", closeXY[0]);
    close.attr("cy", closeXY[1]);

    var nResize = gSvg.select("#" + grp + "nResize");
    var nResizeXY = getElementXYofBBox(bBoxConn, "nResize");

    nResize.transform("translate(0 0)");
    nResize.attr("cx", nResizeXY[0]);
    nResize.attr("cy", nResizeXY[1]);

    var sResize = gSvg.select("#" + grp + "sResize");
    var sResizeXY = getElementXYofBBox(bBoxConn, "sResize");

    sResize.transform("translate(0 0)");
    sResize.attr("cx", sResizeXY[0]);
    sResize.attr("cy", sResizeXY[1]);

    var wResize = gSvg.select("#" + grp + "wResize");
    var wResizeXY = getElementXYofBBox(bBoxConn, "wResize");

    wResize.transform("translate(0 0)");
    wResize.attr("cx", wResizeXY[0]);
    wResize.attr("cy", wResizeXY[1]);

    var eResize = gSvg.select("#" + grp + "eResize");
    var eResizeXY = getElementXYofBBox(bBoxConn, "eResize");

    eResize.transform("translate(0 0)");
    eResize.attr("cx", eResizeXY[0]);
    eResize.attr("cy", eResizeXY[1]);

    var rResize = gSvg.select("#" + grp + "rResize");
    var rResizeXY = getElementXYofBBox(bBoxConn, "rResize");

    rResize.transform("translate(0 0)");
    rResize.attr("cx", rResizeXY[0]);
    rResize.attr("cy", rResizeXY[1]);

    //var text = gSvg.select("#" + grp + "text");
    //var textXY = getElementXYofBBox(bBoxConn, "text");
    //
    //text.transform("translate(0 0)");
    //text.attr("x", textXY[0]);
    //text.attr("y", textXY[1]);
    var label = g.select("#" + grp + "label");
    var labelXY = getElementXYofBBox(bBoxConn, "label");
    label.transform("translate(0 0)");
    label.attr("x", labelXY[0]);
    label.attr("y", labelXY[1]);
    label.selectAll("div>div").forEach(function (item) {
        item.node.style.width = labelXY[2] - 10 + "px";
    });

    var selected = gSvg.select("#" + grp + "selected");
    var selectedXY = getElementXYofBBox(bBoxConn, "selected");
    selected.transform("translate(0 0)");
    selected.attr("x", selectedXY[0]);
    selected.attr("y", selectedXY[1]);

}

function setRatioAry(grp) {

    var svgEl = gSvg.select("#" + grp + "custom,#" + grp + "break");
    if (svgEl) {

        var ratio = svgEl.attr("_ratio");
        var ratios = ratio.split("|");
        gRatioAry = [];
        ratios.forEach(function (r) {
            gRatioAry.push(r.split(","));
        });

    }

}

function customMove(myData, eventTarget, e) {

    if ("n" == gDragAnchorPos) {

        var dx = 0;//e.clientX - myData.x;
        var dy = e.clientY - myData.y;

        var newHeight = myData.h - dy;
        if (newHeight < RECT_HEIGHT) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var newY = e.clientY - gStartY;
        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("y", newY);
        selected.attr("height", newHeight);

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        var pathStr = svgEl.attr("d");
        var pathAry = Snap.parsePathString(pathStr);
        var pathLen = pathAry.length;

        var newPath = "";

        var newWidth;
        var newX;
        if (RESIZE_BY_RATIO) {

            newWidth = newHeight * myData.w / myData.h;
            newX = (e.clientX - gStartX) - (newWidth / 2);

            selected.attr("x", newX);
            selected.attr("width", newWidth);

        }

        for (var i = 0; i < pathLen; i++) {

            var act = pathAry[i][0];

            if ("Z" != act.toUpperCase()) {
                var lineToX = pathAry[i][1];
                //var lineToY = pathAry[i][2];

                newPath += act + " ";

                if (RESIZE_BY_RATIO) {
                    newPath += newX + (gRatioAry[i][0] * newWidth) + " ";
                } else {
                    newPath += lineToX + " ";
                }

                newPath += newY + (gRatioAry[i][1] * newHeight) + " ";

            } else {
                newPath += act;
            }

        }

        svgEl.attr("d", newPath);

    } else if ("s" == gDragAnchorPos) {

        var dx = 0;//e.clientX - myData.x;
        var dy = e.clientY - myData.y;

        var newHeight = myData.h + dy;
        if (newHeight < RECT_HEIGHT) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var newY;
        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("height", newHeight);
        newY = selected.getBBox().y;

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        var pathStr = svgEl.attr("d");
        var pathAry = Snap.parsePathString(pathStr);
        var pathLen = pathAry.length;

        var newPath = "";

        var newWidth;
        var newX;
        if (RESIZE_BY_RATIO) {

            newWidth = newHeight * myData.w / myData.h;
            newX = (e.clientX - gStartX) - (newWidth / 2);

            selected.attr("x", newX);
            selected.attr("width", newWidth);

        }

        for (var i = 0; i < pathLen; i++) {

            var act = pathAry[i][0];

            if ("Z" != act.toUpperCase()) {
                var lineToX = pathAry[i][1];
                //var lineToY = pathAry[i][2];

                newPath += act + " ";

                if (RESIZE_BY_RATIO) {
                    newPath += newX + (gRatioAry[i][0] * newWidth) + " ";
                } else {
                    newPath += lineToX + " ";
                }

                newPath += newY + (gRatioAry[i][1] * newHeight) + " ";

            } else {
                newPath += act;
            }

        }

        svgEl.attr("d", newPath);

    } else if ("w" == gDragAnchorPos) {

        var dx = e.clientX - myData.x;
        var dy = 0;//e.clientY - myData.y;

        var newWidth = myData.w - dx;
        if (newWidth < RECT_WIDTH) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var newX = e.clientX - gStartX;
        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("x", newX);
        selected.attr("width", newWidth);

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        var pathStr = svgEl.attr("d");
        var pathAry = Snap.parsePathString(pathStr);
        var pathLen = pathAry.length;

        var newPath = "";

        var newHeight;
        var newY;
        if (RESIZE_BY_RATIO) {

            newHeight = newWidth * myData.h / myData.w;
            newY = (e.clientY - gStartY) - (newHeight / 2);

            selected.attr("y", newY);
            selected.attr("height", newHeight);

        }

        for (var i = 0; i < pathLen; i++) {

            var act = pathAry[i][0];

            if ("Z" != act.toUpperCase()) {
                //var lineToX = pathAry[i][1];
                var lineToY = pathAry[i][2];

                newPath += act + " ";
                newPath += newX + (gRatioAry[i][0] * newWidth) + " ";

                if (RESIZE_BY_RATIO) {
                    newPath += newY + (gRatioAry[i][1] * newHeight) + " ";
                } else {
                    newPath += lineToY + " ";
                }

            } else {
                newPath += act;
            }

        }

        svgEl.attr("d", newPath);

    } else if ("e" == gDragAnchorPos) {

        var dx = e.clientX - myData.x;
        var dy = 0;//e.clientY - myData.y;

        var newWidth = myData.w + dx;
        if (newWidth < RECT_WIDTH) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var newX;
        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("width", newWidth);
        newX = selected.getBBox().x;

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        var pathStr = svgEl.attr("d");
        var pathAry = Snap.parsePathString(pathStr);
        var pathLen = pathAry.length;

        var newPath = "";

        var newHeight;
        var newY;
        if (RESIZE_BY_RATIO) {

            newHeight = newWidth * myData.h / myData.w;
            newY = (e.clientY - gStartY) - (newHeight / 2);

            selected.attr("y", newY);
            selected.attr("height", newHeight);

        }

        for (var i = 0; i < pathLen; i++) {

            var act = pathAry[i][0];

            if ("Z" != act.toUpperCase()) {
                //var lineToX = pathAry[i][1];
                var lineToY = pathAry[i][2];

                newPath += act + " ";
                newPath += newX + (gRatioAry[i][0] * newWidth) + " ";

                if (RESIZE_BY_RATIO) {
                    newPath += newY + (gRatioAry[i][1] * newHeight) + " ";
                } else {
                    newPath += lineToY + " ";
                }

            } else {
                newPath += act;
            }

        }

        svgEl.attr("d", newPath);

    } else if ("r" == gDragAnchorPos) {

        var dx = e.clientX - myData.x;
        var dy = e.clientY - myData.y;

        var newWidth = myData.w + dx;
        if (newWidth < RECT_WIDTH) {
            return;
        }

        var myMatrix = new Snap.Matrix();
        myMatrix.translate(dx, dy);

        eventTarget.transform(myMatrix);

        var newX;
        var selected = gSvg.select("#" + gCurrent + "selected");
        selected.attr("width", newWidth);
        newX = selected.getBBox().x;

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        var pathStr = svgEl.attr("d");
        var pathAry = Snap.parsePathString(pathStr);
        var pathLen = pathAry.length;

        var newPath = "";

        var newHeight;
        var newY;

        newHeight = newWidth * myData.h / myData.w;
        newY = (e.clientY - gStartY) - newHeight;

        selected.attr("y", newY);
        selected.attr("height", newHeight);

        for (var i = 0; i < pathLen; i++) {

            var act = pathAry[i][0];

            if ("Z" != act.toUpperCase()) {
                //var lineToX = pathAry[i][1];
                var lineToY = pathAry[i][2];

                newPath += act + " ";
                newPath += newX + (gRatioAry[i][0] * newWidth) + " ";
                newPath += newY + (gRatioAry[i][1] * newHeight) + " ";

            } else {
                newPath += act;
            }

        }

        svgEl.attr("d", newPath);

    }

}

//endregion

// region Common

function resizeMouseDown(event) {

    event.stopPropagation();

    var id = this.attr("id");
    var grp = getGroupPrefix(id);
    gCurrent = grp;

    gDragAnchor = id.split("_")[2];//"nResize";
    gDragAnchorPos = gDragAnchor.substring(0, 1);
    var eventTarget = gSvg.select("#" + grp + gDragAnchor);

    var grpEl = gSvg.select("#" + grp + "g").select(":first-child");
    gDragType = getTypeById(grpEl.attr("id"));

    var svgEl = gSvg.select("#" + grp + gDragType);

    var myData = new CustomData(event.clientX, event.clientY, svgEl);
    eventTarget.data("myData", myData);

    setRatioAry(grp);

    gDrawArea.onmousemove = resizeMouseMove;
    gDrawArea.onmouseup = resizeMouseUp;
}

function resizeMouseMove(event) {

    var grp;
    if ("" != gCurrent) {
        grp = gCurrent;
    } else {
        return;
    }

    var eventTarget = gSvg.select("#" + grp + gDragAnchor);
    var myData = eventTarget.data("myData");

    svgElMoveFunc[gDragType](myData, eventTarget, event);

}

function resizeMouseUp() {

    if ("" != gCurrent) {

        var grp = getGroupPrefix(gCurrent);

        var svgEl = gSvg.select("#" + gCurrent + gDragType);
        correctXY(grp, svgEl, gDragType);

    }

    gDrawArea.onmousemove = null;
    gDrawArea.onmouseup = null;

    gDragAnchor = "";
    gDragType = "";
    gDragAnchorPos = "";
}

function correctXY(grp, svgEl, dragType) {
    if ("rect" == dragType) {
        correctRectXY(grp, svgEl);
    } else if ("connector" == dragType) {
        correctConnectorXY(grp, svgEl);
    } else if ("ellipse" == dragType) {
        correctEllipseXY(grp, svgEl);
    } else if ("line" == dragType) {
        correctLineXY(grp, svgEl);
    } else if ("break" == dragType) {
        correctBreakXY(grp, svgEl);
    } else if ("brace" == dragType) {
        correctBraceXY(grp, svgEl);
    } else if ("image" == dragType) {
        correctImageXY(grp, svgEl);
    } else if ("custom" == dragType) {
        correctCustomXY(grp, svgEl);
    }

}

function getGroupPrefix(id) {

    if (isNaN(id) && id.indexOf(SEPARATOR) > 0) {
        var idAry = id.split(SEPARATOR);
        return idAry[0] + SEPARATOR + idAry[1] + SEPARATOR;
    }

    var pad = "0000";
    var newSn = (pad + id).slice(-pad.length);
    return GROUP_PREFIX + SEPARATOR + newSn + SEPARATOR;

}

function hideElementById(elId) {

    var el = gSvg.select("#" + elId);
    hideElement(el);

}

function hideElement(el) {
    if (el) {
        el.addClass("hide");
    }
}

function showElementById(elId) {

    var el = gSvg.select("#" + elId);
    showElement(el);
}

function showElement(el) {
    if (el) {
        el.removeClass("hide");
    }
}

function reloadSvg() {
    var initGrp = "group_0000_";
    // dispatch all events
    gSvg.selectAll("rect").forEach(function (newRect) {

        var id = newRect.attr("id");
        registerListener(id);
        var grp = getGroupPrefix(id);

        if (grp > initGrp) {
            initGrp = grp;
        }

    });
    //
    //gSvg.selectAll(".myClose").forEach(function (close) {
    //
    //    //close.mouseover(rectMouseOver);
    //    //close.mouseout(rectMouseOut);
    //    close.click(closeClick);
    //
    //});
    //
    //gSvg.selectAll("text").forEach(function (text) {
    //
    //    text.dblclick(textDblClick);
    //
    //});

    gSvg.selectAll("[id$='connector']").forEach(function (newConn) {

        var id = newConn.attr("id");
        registerListener(id);
        var grp = getGroupPrefix(id);

        if (grp > initGrp) {
            initGrp = grp;
        }

        if (newConn.attr('class') == 'myConnector2') {
            reDrawPointByPath(grp, newConn, null, 'route');
        } else {
            reDrawPointByPath(grp, newConn);
        }

    });

    gSvg.selectAll("ellipse").forEach(function (newEllipse) {

        var id = newEllipse.attr("id");
        registerListener(id);
        var grp = getGroupPrefix(id);

        if (grp > initGrp) {
            initGrp = grp;
        }

    });

    gSvg.selectAll("[id$='_brace']").forEach(function (newBrace) {

        var id = newBrace.attr("id");
        registerListener(id);
        var grp = getGroupPrefix(id);

        if (grp > initGrp) {
            initGrp = grp;
        }

    });

    gSvg.selectAll("[id$='_break']").forEach(function (newBreak) {

        var id = newBreak.attr("id");
        registerListener(id);
        var grp = getGroupPrefix(id);

        if (grp > initGrp) {
            initGrp = grp;
        }

    });

    gSvg.selectAll("image").forEach(function (newImage) {

        var id = newImage.attr("id");
        registerListener(id);
        var grp = getGroupPrefix(id);

        if (grp > initGrp) {
            initGrp = grp;
        }

    });

    gSvg.selectAll("[id$='_custom']").forEach(function (newCustom) {

        var id = newCustom.attr("id");
        registerListener(id);
        var grp = getGroupPrefix(id);

        if (grp > initGrp) {
            initGrp = grp;
        }

    });

    var grpAry = initGrp.split(SEPARATOR);
    var sn = parseInt(grpAry[1], 10);
    gSerialNo = sn + 1;
}

function getTypeById(id) {

    if ("" == id) {
        return "";
    }

    var idSplit = id.split("_");
    if (idSplit.length < 3) {
        return "";
    }

    return idSplit[2];
}

function toggleGrid() {

    var snapSvg = document.getElementById("snapSvg");

    if (!snapSvg) {
        return;
    }

    if (snapSvg.classList.contains("gridBackground")) {
        snapSvg.classList.remove("gridBackground");
        gGridEnable = false;
    } else {
        snapSvg.classList.add("gridBackground");
        gGridEnable = true;
    }

}
// document ready
document.addEventListener("DOMContentLoaded", function () {

    var reload = false;
    gSvg = Snap.select("#snapSvg");
    gDrawArea = document.getElementById("drawArea");

    if (!gSvg) {
        gSvg = Snap(CANVAS_WIDTH, CANVAS_HEIGHT);
        gSvg.attr("id", "snapSvg");
        gSvg.appendTo(gDrawArea);
    } else {
        reload = true;
    }

    gDrawArea.onmousedown = function () {
        log("gDrawArea onmousedown");
        if (gContextMenu.classList.contains("context-menu--active")) {
            return;
        }

        if (gCurrent != "") {
            clearSelected(gCurrent);
            gCurrent = "";
        }
    };

    gDrawArea.addEventListener("contextmenu", function (e) {
        e.preventDefault();
        e.stopPropagation();
    });

    var pathArray = window.location.pathname.split('/');
    DIAGRAM_NAME = pathArray[pathArray.length - 1];
    localStorage.removeItem(DIAGRAM_NAME + "_TMP");

    var mainAreaBound;
    if (DIAGRAM_NAME.match(/\.html?$/i)) {
        mainAreaBound = document.getElementById("mainArea").parentNode.getBoundingClientRect();
        CONTEXT_MENU_SHIFT_X = 0;
        CONTEXT_MENU_SHIFT_Y = 0;
        gMenuWidth = mainAreaBound.left;
        gMenuHeight = mainAreaBound.top;
    } else {
        mainAreaBound = document.getElementById("drawArea").getBoundingClientRect(); //   For remote site
        TEXT_EDIT_MENU_LEFT = mainAreaBound.left + 200;
        TEXT_EDIT_MENU_TOP = mainAreaBound.top + 10;
        gMenuWidth = 0;
        gMenuHeight = 0;
    }

    //$(gSvg.node).position().top;

    // context menu
    gContextMenu = document.getElementById("context-menu");
    gContextMenu.addEventListener("mouseover", function (e) {
        e.stopPropagation();
        gContextMenu.classList.add("context-menu--active");
    });

    gContextMenu.addEventListener("mouseout", function () {
        gContextMenu.classList.remove("context-menu--active");
    });

    gConnectorContextMenu = document.getElementById("context-menu-connector");
    gConnectorContextMenu.addEventListener("mouseover", function (e) {
        e.stopPropagation();
        gConnectorContextMenu.classList.add("context-menu--active");
    });

    gConnectorContextMenu.addEventListener("mouseout", function () {
        gConnectorContextMenu.classList.remove("context-menu--active");
    });

    gLabelContextMenu = document.getElementById("context-menu-label");
    gLabelContextMenu.addEventListener("mouseover", function (e) {
        e.stopPropagation();
        gLabelContextMenu.classList.add("context-menu--active");
    });

    gLabelContextMenu.addEventListener("mouseout", function () {
        gLabelContextMenu.classList.remove("context-menu--active");
    });

    gTextEditContextMenu = document.getElementById("context-menu-textedit");
    gTextEditContextMenu.classList.add("context-menu--active");
    gTextEditContextMenu.style["position"] = "absolute";
    gTextEditContextMenu.style["left"] = TEXT_EDIT_MENU_LEFT + "px";
    gTextEditContextMenu.style["top"] = TEXT_EDIT_MENU_TOP + "px";

    //gTextEditContextMenu.addEventListener("mouseover", function () {
    //    gTextEditContextMenu.classList.add("context-menu--active");
    //});
    //gTextEditContextMenu.addEventListener("mouseout", function () {
    //    gTextEditContextMenu.classList.remove("context-menu--active");
    //});

    initSelectionFonts();
    initSelectionFontSizes();

    if (reload) {
        reloadSvg();

    }

    var bound = gSvg.node.getBoundingClientRect();
    gStartX = bound.left;//gSvg.node).position().left;
    gStartY = bound.top;

    $('[data-toggle="tooltip"]').tooltip({placement: "bottom"});

    $('#borderColorPicker').colorpicker({
        input: $('#borderColorHex'),
        container: true
    }).on('showPicker', borderColorShowPicker).on('changeColor', borderColorChanged);
    $('#fillColorPicker').colorpicker({
        input: $('#fillColorHex'),
        container: true
    }).on('showPicker', fillColorShowPicker).on('changeColor', fillColorChanged);

		initModeling(gModelType);
});

function initSelectionFonts() {

    var textEditFontFamily = document.querySelector("#textEditFontFamily");
    textEditFontFamily.innerHTML = "";

    if (textEditFontFamily) {

        //var opt = document.createElement("option");
        //opt.value = "";
        //opt.innerHTML = "&nbsp;";
        //textEditFontFamily.appendChild(opt);

        SUPPORTED_FONTS.forEach(function (fontAry) {
            var opt = document.createElement("option");
            opt.value = fontAry[0];
            opt.innerHTML = "<span style='font-family:" + fontAry[0] + "'>" + fontAry[1] + "</span>";
            textEditFontFamily.appendChild(opt);
        });
    }
}

function initSelectionFontSizes() {
    var textEditFontSize = document.querySelector("#textEditFontSize");
    textEditFontSize.innerHTML = "";
    if (textEditFontSize) {
        SUPPORTED_FONT_SIZES.forEach(function (sizeAry) {
            var opt = document.createElement("option");
            opt.value = sizeAry;
            opt.innerHTML = sizeAry;
            textEditFontSize.appendChild(opt);
        });
    }
}

function setSelected(grp, grpOld) {

    clearSelected(grpOld);
    showElementById(grp + "selected");
    showElementById(grp + "close");
    showElementById(grp + "nResize");
    showElementById(grp + "sResize");
    showElementById(grp + "wResize");
    showElementById(grp + "eResize");
    showElementById(grp + "rResize");

    gSvg.selectAll("[id^='" + grp + "point']").forEach(function (element) {
        showElement(element);
    });

    gSvg.selectAll("[id^='" + grp + "arrow']").forEach(function (element) {
        hideElement(element);
    });

    var parentDiv = gSvg.select("[id^='" + grp + "label']>div");
    if (parentDiv) {

        var labelItems = parentDiv.selectAll("div,li");
        if (labelItems && labelItems.length > 0) {
            labelItems.forEach(function (item) {
                item.node.setAttribute("placeholder", "label");
            });
        }
    }

}

function clearSelected(grp) {

    gSvg.selectAll("[id$='selected'").forEach(function (element) {
        hideElement(element);
    });

    gSvg.selectAll("[id$='close'").forEach(function (element) {
        hideElement(element);
    });

    gSvg.selectAll("[id$='Resize'").forEach(function (element) {
        hideElement(element);
    });

    gSvg.selectAll("[id^='" + grp + "point']").forEach(function (element) {
        hideElement(element);
    });

    gSvg.selectAll("[id^='" + grp + "arrow']").forEach(function (element) {
        showElement(element);
    });

    if (grp) {
        var parentDiv = gSvg.select("[id^='" + grp + "label']>div");
        if (parentDiv) {

            var labelItems = parentDiv.selectAll("div,li");
            if (labelItems && labelItems.length > 0) {
                labelItems.forEach(function (item) {
                    item.node.removeAttribute("placeholder");
                });
            }

        }

    }

}

function svgElRemove() {

    var r = confirm(REMOVE_CONFIRM_MSG);
    if (!r) {
        return;
    }

    if ("" != gGrpTmp) {
        var grpId = gGrpTmp + "g";
        gSvg.select("#" + grpId).remove();
        gContextMenu.classList.remove("context-menu--active");
        gGrpTmp = "";
        gCurrent = "";
    }

}

String.prototype.replaceAll = function (target, replacement) {
    return this.split(target).join(replacement);
};

function svgElDuplicate() {

    if ("" != gGrpTmp) {

        var grpId = gGrpTmp + "g";
        var svgEl = gSvg.select("#" + grpId);

        var newGrp = getGroupPrefix(gSerialNo);
        var newGrpId = newGrp + "g";

        var innerHtml = svgEl.node.innerHTML.replaceAll(gGrpTmp, newGrp);
        var g = gSvg.g();
        g.attr("id", newGrpId);
        if ("" != svgEl.attr("type")) {
            g.attr("type", svgEl.attr("type"));
        }
        g.node.innerHTML = innerHtml;

        var firstChild = g.select(":first-child");
        var type = registerListener(firstChild.attr("id"));
        var myMatrix;

        if ("rect" == type) {

            myMatrix = new Snap.Matrix();
            myMatrix.translate(30, 30);
            g.transform(myMatrix);
            correctXY(newGrp, firstChild, type);

        } else if ("ellipse" == type) {

            myMatrix = new Snap.Matrix();
            myMatrix.translate(30, 30);
            g.transform(myMatrix);
            correctXY(newGrp, firstChild, type);

        } else if ("brace" == type) {

            myMatrix = new Snap.Matrix();
            myMatrix.translate(30, 30);
            g.transform(myMatrix);
            correctXY(newGrp, firstChild, type);

        } else if ("break" == type) {

            myMatrix = new Snap.Matrix();
            myMatrix.translate(30, 30);
            g.transform(myMatrix);
            correctXY(newGrp, firstChild, type);

        } else if ("image" == type) {

            myMatrix = new Snap.Matrix();
            myMatrix.translate(30, 30);
            g.transform(myMatrix);
            correctXY(newGrp, firstChild, type);

        } else if ("custom" == type) {

            myMatrix = new Snap.Matrix();
            myMatrix.translate(30, 30);
            g.transform(myMatrix);
            correctXY(newGrp, firstChild, type);

        } else if ("connector" == type) {

            myMatrix = new Snap.Matrix();
            myMatrix.translate(30, 30);
            g.transform(myMatrix);
            correctXY(newGrp, firstChild, type);

            reDrawPointByPath(newGrp, firstChild, g, g.attr("type"));

        }

        gSerialNo++;
        //gSvg.append(svgElClone);
        gContextMenu.classList.remove("context-menu--active");
    }

}

function svgElToFront() {

    if ("" != gGrpTmp) {

        var grpId = gGrpTmp + "g";
        var svgEl = gSvg.select("#" + grpId);
        gSvg.append(svgEl);
        gContextMenu.classList.remove("context-menu--active");

    }

}

function svgElToBack() {

    if ("" != gGrpTmp) {

        var grpId = gGrpTmp + "g";
        var svgEl = gSvg.select("#" + grpId);
        var firstChild = gSvg.select(":first-child");
        svgEl.insertBefore(firstChild);
        gContextMenu.classList.remove("context-menu--active");

    }

}

function newDraw() {
    gSvg.node.innerHTML = "";
    document.getElementById("loadedModel").value = "";
    document.getElementById("uuid").value = "";
    gCurrent = "";
    initModeling(gModelType);
}

function loadDraw() {

    var myModal = $("#myModal");
    if (myModal) {

        var formData = {"type": gModelType};
        $.blockUI({message: '<h4> 模型清單讀取中, 請稍候</h4>'});

        $.ajax({
            url: 'ModelIO/doGetList',
            type: 'POST',
            dataType: 'json',
            data: formData,
            success: function (jsonResult) {
                $.unblockUI();

                if (jsonResult.functionStatus == "SUCCESS") {

                    if (jsonResult.uuids && jsonResult.names) {

                        var uuidAry = jsonResult.uuids.split(",");
                        var nameAry = jsonResult.names.split(",");

                        var html = "<ul>";

                        for (var i = 0; i < uuidAry.length; i++) {
                            html += "<li class=\"modalNameList\" style=\"cursor:pointer;\" onclick=\"performLoad('" + uuidAry[i] + "')\">" + nameAry[i] + "</li>";
                        }

                        html += "</ul>";

                        var myModalHeader = document.getElementById("myModalHeader");
                        var myModalBody = document.getElementById("myModalBody");
                        var myModalButton = document.getElementById("myModalButton");

                        myModalHeader.innerHTML = "Select name to load";
                        myModalBody.innerHTML = html;
                        myModalButton.style.display = "none";
                        myModal.modal("show");

                        //modelMessage.innerHTML = html;
                        //location.href = "#openModal";
                    } else {
                        alert("no saved model");
                    }

                } else if (jsonResult.functionStatus == "FAILED") {
                    alert(jsonResult.errorMessage);
                }

            },
            error: function () {
                $.unblockUI();
            }
        });


    }

//    var html = localStorage.getItem(DIAGRAM_NAME);
//    if (!html) {
//        alert("No data!")
//        return;
//    }
//    gSvg.node.innerHTML = html;
//    reloadSvg();
    //document.getElementById("loadSvg").value = "";
    //document.getElementById("loadSvg").click();
}

function performLoad(uuid) {

    var formData = {"uuid": uuid};
    $.blockUI({message: '<h4> 模型讀取中, 請稍候</h4>'});
    $("#myModal").modal("hide");

    $.ajax({
        url: 'ModelIO/doLoad',
        type: 'POST',
        dataType: 'json',
        data: formData,
        success: function (jsonResult) {
            $.unblockUI();

            if (jsonResult.functionStatus == "SUCCESS") {

                if (jsonResult.content && jsonResult.name) {

                    gSvg.node.innerHTML = jsonResult.content;

                    document.getElementById("loadedModel").value = jsonResult.name;
                    document.getElementById("uuid").value = uuid;

                    reloadSvg();
                }

            } else if (jsonResult.functionStatus == "FAILED") {
                alert(jsonResult.errorMessage);
            }

        },
        error: function () {
            $.unblockUI();
        }
    });

}

//function performLoadSvg() {

//    var file = document.getElementById("loadSvg").files[0];
//
//    if (!file) {
//        return;
//    }
//
//    var url = window.location.href;
//var textType = /image.*/;
//if (!file.type.match(textType)) {
//    alert("File not supported!");
//    document.getElementById("insertImg").value = "";
//    return;
//}

//    var fReader = new FileReader();
//    fReader.onload = function (event) {
//
//        gSvg.node.innerHTML = event.target.result;
//        reloadSvg();
//
//    };
//    fReader.readAsText(file);

//}

function saveDraw() {

    var myModal = $("#myModal");
    if (myModal) {

        var loadedModel = document.getElementById("loadedModel").value;
        if (!loadedModel) {
            loadedModel = "";
        }

        var myModalHeader = document.getElementById("myModalHeader");
        var myModalBody = document.getElementById("myModalBody");
        var myModalButton = document.getElementById("myModalButton");

        myModalHeader.innerHTML = "Enter name to save";
        myModalBody.innerHTML = "<input type='text' id='modelName' value='" + loadedModel + "'>";
        myModalButton.innerHTML = "Save";
        myModalButton.style.display = "";
        myModalButton.onclick = performSave;

        myModal.modal("show");

        //modelMessage.innerHTML = "&nbsp;<input type='text' id='modelName' value='" + loadedModel + "'><button style='float: right' onclick='performSave()'>SAVE</button>";
        //location.href = "#openModal";
    }
//    localStorage.setItem(DIAGRAM_NAME, gSvg.node.innerHTML);
}

function performSave() {

    var svgHtml = gSvg.node.innerHTML;
    if (svgHtml && svgHtml.length > 0) {
        svgHtml = svgHtml.replace(/\r?\n|\r/g, "");
    } else {
        alert("no data!");
        return;
    }

    var modelName = document.getElementById("modelName").value;
    if (!modelName) {
        alert("no name!");
        return;
    }

    var doSaveOrUpdate = true;
    if (modelName == document.getElementById("loadedModel").value) {
        doSaveOrUpdate = confirm("Overwrite?");
    }

    if (!doSaveOrUpdate) {
        return;
    }

    var formData = {"content": svgHtml, "type": gModelType, "name": modelName};

    $("#myModal").modal("hide");
    $.blockUI({message: '<h4> 模型儲存中, 請稍候</h4>'});

    $.ajax({
        url: 'ModelIO/doSave',
        type: 'POST',
        dataType: 'json',
        data: formData,
        success: function (jsonResult) {
            $.unblockUI();

            if (jsonResult.functionStatus == "SUCCESS") {
                document.getElementById("loadedModel").value = modelName;
                document.getElementById("uuid").value = jsonResult.uuid;
            } else if (jsonResult.functionStatus == "FAILED") {
                alert(jsonResult.errorMessage);
            }

        },
        error: function () {
            $.unblockUI();
        }
    });

}

function deleteDraw() {

    var myModal = $("#myModal");
    if (myModal) {

        var loadedModel = document.getElementById("loadedModel").value;
        if (!loadedModel) {
            loadedModel = "";
        }

        var myModalHeader = document.getElementById("myModalHeader");
        var myModalBody = document.getElementById("myModalBody");
        var myModalButton = document.getElementById("myModalButton");

        myModalHeader.innerHTML = "The model to delete";
        myModalBody.innerHTML = "[" + loadedModel + "]";
        myModalButton.innerHTML = "Save";
        myModalButton.style.display = "";
        myModalButton.onclick = performDelete;
        myModal.modal("show");
        //modelMessage.innerHTML = "&nbsp;" + "[" + loadedModel + "]" + "<button style='float: right' onclick='performDelete()'>DELETE</button>";
        //location.href = "#openModal";
    }

//    localStorage.removeItem(DIAGRAM_NAME);
//    newDraw();
}

function performDelete() {


    var uuid = document.getElementById("uuid").value;

    if (!uuid) {
        alert("no data!");
        return;
    }

    var doDelete = confirm(REMOVE_MODEL_MSG);
    if (!doDelete) {
        return;
    }

    var formData = {"uuid": uuid};

    $("#myModal").modal("hide");
    $.blockUI({message: '<h4> 模型刪除中, 請稍候</h4>'});

    $.ajax({
        url: 'ModelIO/doDelete',
        type: 'POST',
        dataType: 'json',
        data: formData,
        success: function (jsonResult) {
            $.unblockUI();

            if (jsonResult.functionStatus == "SUCCESS") {
//				document.getElementById("loadedModel").value = "";
//				document.getElementById("uuid").value = "";
                newDraw();
            } else if (jsonResult.functionStatus == "FAILED") {
                alert(jsonResult.errorMessage);
            }

        },
        error: function () {
            $.unblockUI();
        }
    });

}

function exportDraw() {
    transformHtmlToSvgText();
    saveSvgAsPng(document.getElementById("snapSvg"), "diagram.png");
    gSvg.selectAll("[id^=tmp_text_]").remove();
}

function generateElementOfSvg(tagName) {
    var el = document.createElementNS("http://www.w3.org/2000/svg", tagName);
    el.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xlink", "http://www.w3.org/1999/xlink");
    el.setAttribute("id", "tmp_text_" + gSerialNo++);
    return el;
}

function transformHtmlToSvgText() {

    var xShift = gSvg.node.getBoundingClientRect().left;
    var yShift = gSvg.node.getBoundingClientRect().top;

    gSvg.selectAll("foreignobject > div").forEach(function (parentDiv) {

        //var foreignObj = parentDiv.parent();
        var lastListParent = null;
        var listItemOrder = 1;

        var textItems = parentDiv.selectAll("div,li");
        if (textItems.length > 0 && textItems[0].node.innerHTML != "") {

            textItems.forEach(function (textItem) {

                var textItemEl = textItem.node;
                var computedStyle = window.getComputedStyle(textItemEl, null);

                var textAlign = computedStyle.getPropertyValue('text-align'); // text-anchor: start | middle | end
                var fontSize = computedStyle.getPropertyValue('font-size'); // font-size
                var fontStyle = computedStyle.getPropertyValue('font-style'); // font-style
                var fontFamily = computedStyle.getPropertyValue('font-family'); // font-family
                var fontWeight = computedStyle.getPropertyValue('font-weight'); // font-weight

                var svgText = generateElementOfSvg("text");
                var fontSizeInt = 12;
                if ("" != fontSize) {
                    fontSizeInt = parseInt(fontSize.replace("px", ""), 10);
                }

                svgText.setAttribute("font-size", fontSize);
                svgText.setAttribute("font-style", fontStyle);
                svgText.setAttribute("font-family", fontFamily);
                svgText.setAttribute("font-weight", fontWeight);

                var bounding = textItemEl.getBoundingClientRect();

                var left = bounding.left - xShift; // x
                var top = bounding.top - yShift + fontSizeInt; // y
                var width = bounding.width;

                if ("left" == textAlign) {

                    svgText.setAttribute("x", left);
                    svgText.setAttribute("y", top);
                    svgText.setAttribute("text-anchor", "start");

                } else if ("right" == textAlign) {

                    svgText.setAttribute("x", left + width);
                    svgText.setAttribute("y", top);
                    svgText.setAttribute("text-anchor", "end");

                } else {

                    svgText.setAttribute("x", left + width / 2);
                    svgText.setAttribute("y", top);
                    svgText.setAttribute("text-anchor", "middle");

                }

                var tagName = textItemEl.tagName;

                if ("div" == tagName.toLowerCase()) {
                    svgText.innerHTML = textItem.innerSVG();
                } else {

                    var listParent = textItemEl.parentNode;
                    if (lastListParent != listParent) {
                        listItemOrder = 1;
                        lastListParent = listParent;
                    }

                    var listStyle = "&#x25CF;";
                    if ("ol" == listParent.tagName.toLowerCase()) {
                        listStyle = listItemOrder + ".";
                        listItemOrder++;
                    }

                    svgText.innerHTML = listStyle + " " + textItem.innerSVG();

                }

                gSvg.append(svgText);


            });

        }

    });

}

//function backupSvgCurrent(svgEl) {
//    localStorage.setItem(DIAGRAM_NAME + "_TMP", svgEl.node.outerHTML);
//}

// function undo() {
//     console.log("UNDO");
// }

function log(msg) {
    if (__DEBUG_OUTPUT) {
        console.log(msg);
    }
}

function roundByDigits(num, digits) {
    var d = Math.pow(10, digits);
    return Math.round(num * d) / d;
}

function toInteger(str, def) {

    if (str != undefined && !isNaN(str)) {
        return parseInt(str, 10);
    }

    return (def !== undefined) ? def : null;
}

function generateSelectedMark(bBox, grp, isPath) {

    var selectedId = grp + "selected";

    //var pathStr = "M " + bBox.x + " " + bBox.+ " L " + bBox.x + " " + bBox.y2 + " L " + bBox.x2 + " " + bBox.y2 + " L " + bBox.x2 + " " + bBox.y + " Z";
    //var selected = gSvg.path(pathStr);
    var add = 0;
    if (isPath) {
        add = PATH_BBOX_ADD;
    }

    var selected = gSvg.rect(bBox.x - add, bBox.y - add, bBox.width + add * 2, bBox.height + add * 2);

    selected.attr("id", selectedId);
    selected.addClass("mySelected");
    hideElementById(selectedId);

    return selected;

}

function getElementXYofBBox(bBox, elName) {

    var xy = [];

    var width = 0;
    var height = 0;
    var bBoxX = 0;
    var bBoxY = 0;

    if (bBox != null) {
        width = bBox.width;
        height = bBox.height;
        bBoxX = bBox.x;
        bBoxY = bBox.y;
    }

    if ("close" == elName) {

        xy.push(bBoxX + width - CIRCLE_R_HALF);
        xy.push(bBoxY + CIRCLE_R_HALF);

    } else if ("text" == elName) {

        xy.push(bBoxX + 10);
        xy.push(bBoxY + height / 2 + 5);

    } else if ("nResize" == elName) {

        xy.push(bBoxX + width / 2);
        xy.push(bBoxY);

    } else if ("sResize" == elName) {

        xy.push(bBoxX + width / 2);
        xy.push(bBoxY + height);

    } else if ("wResize" == elName) {

        xy.push(bBoxX);
        xy.push(bBoxY + height / 2);

    } else if ("eResize" == elName) {

        xy.push(bBoxX + width);
        xy.push(bBoxY + height / 2);

    } else if ("rResize" == elName) {

        xy.push(bBoxX + width);
        xy.push(bBoxY + height);

    } else if ("selected" == elName) {

        xy.push(bBoxX);
        xy.push(bBoxY);

    } else if ("text" == elName) {

        xy.push(bBoxX + 10);
        xy.push(bBoxY + height / 2 + 5);

    } else if ("label" == elName) {

        xy.push(bBoxX);

        var newY = bBoxY + height / 2;
        if (gCurrent) {
            var findDiv = gSvg.select("#" + gCurrent + "label>div");
            if (findDiv) {
                var itemsHeight = gSvg.select("#" + gCurrent + "label>div").node.scrollHeight;
                newY = newY - itemsHeight / 2;
            }
        }

        xy.push(newY);

        xy.push(width);
        xy.push(height);
        //myDiv.clientHeight;
        //myDiv.scrollHeight; *
        //myDiv.offsetHeight;

    }

    return xy;

}

function initLabelForElement(bBox, grp, initX, initY) {

    var w = bBox.width;
    //var h = bBox.height;
    var x = bBox.x;
    var y = bBox.y + bBox.height / 2 - 6; //  default size of font: 12px
    var labelId = grp + "label";

    if (initX) {
        x = initX;
    }
    if (initY) {
        y = initY;
    }

    var fragmentStr = "<foreignObject width='" + w + "' x='" + x + "' y='" + y + "' id='" + labelId + "'>";
    fragmentStr += "<div>";
    fragmentStr += "<div contenteditable='true' style='width:" + (w - 10) + "px; text-align:center' placeholder='label'></div>";
    fragmentStr += "</div></foreignObject>";

    return Snap.fragment(fragmentStr);

}

function setTextEditMenuState(labelItem) {

    if (!labelItem) {
        return;
    }

    var textEditFontFamily = document.getElementById("textEditFontFamily");
    var textEditFontSize = document.getElementById("textEditFontSize");

    var textEditFontBold = document.getElementById("textEditFontBold");
    var textEditFontItalic = document.getElementById("textEditFontItalic");

    var textEditAlignLeft = document.getElementById("textEditAlignLeft");
    var textEditAlignCenter = document.getElementById("textEditAlignCenter");
    var textEditAlignRight = document.getElementById("textEditAlignRight");

    var textEditBulleted = document.getElementById("textEditBulleted");
    var textEditNumbered = document.getElementById("textEditNumbered");

    if ("" != labelItem.style["font-family"]) {
        textEditFontFamily.value = labelItem.style["font-family"];
    }
    if ("" != labelItem.style["font-size"]) {
        textEditFontSize.value = labelItem.style["font-size"].replace("px", "");
    }

    if ("bold" == labelItem.style["font-weight"]) {
        textEditFontBold.classList.add("text-edit-icon-active");
    } else {
        textEditFontBold.classList.remove("text-edit-icon-active");
    }

    if ("italic" == labelItem.style["font-style"]) {
        textEditFontItalic.classList.add("text-edit-icon-active");
    } else {
        textEditFontItalic.classList.remove("text-edit-icon-active");
    }

    if ("left" == labelItem.style["text-align"]) {
        textEditAlignLeft.classList.add("text-edit-icon-active");
    } else {
        textEditAlignLeft.classList.remove("text-edit-icon-active");
    }

    if ("center" == labelItem.style["text-align"]) {
        textEditAlignCenter.classList.add("text-edit-icon-active");
    } else {
        textEditAlignCenter.classList.remove("text-edit-icon-active");
    }

    if ("right" == labelItem.style["text-align"]) {
        textEditAlignRight.classList.add("text-edit-icon-active");
    } else {
        textEditAlignRight.classList.remove("text-edit-icon-active");
    }

    var parentNode = labelItem.parentNode;
    if (parentNode && "ul" == parentNode.tagName.toLowerCase()) {
        textEditBulleted.classList.add("text-edit-icon-active");
        textEditNumbered.classList.remove("text-edit-icon-active");
    } else if (parentNode && "ol" == parentNode.tagName.toLowerCase()) {
        textEditBulleted.classList.remove("text-edit-icon-active");
        textEditNumbered.classList.add("text-edit-icon-active");
    } else {
        textEditBulleted.classList.remove("text-edit-icon-active");
        textEditNumbered.classList.remove("text-edit-icon-active");
    }

}

function getParentByTag(el, tagName) {

    if (el) {
        var parent = el.parentNode;
        if (parent && parent.tagName && tagName.toLowerCase() == parent.tagName.toLowerCase()) {
            return parent;
        } else {
            return getParentByTag(parent, tagName);
        }
    } else {
        return null;
    }

}

function adjustLabelItemPosition(labelItem) {

    var label = getParentByTag(labelItem, "foreignobject");
    //if ("DIV" == labelItem.tagName.toUpperCase()) {
    //    label = labelItem.parentNode.parentNode;
    //} else {
    //    label = labelItem.parentNode.parentNode.parentNode;
    //}

    if (label) {
        var grp = getGroupPrefix(label.id);
        var svgEl = gSvg.select("#" + grp + "g").select(":first-child");
        var type = getTypeById(svgEl.attr("id"));
        gCurrent = grp; // fix for empty
        correctXY(grp, svgEl, type);
        setSelected(gCurrent);
        //gEditingItem = "";
    }

}

function labelRemove() {

    var labelItem = gGrpTmp;
    if (labelItem) {

        var parentNode = labelItem.parentNode;
        if (parentNode) {
            var childCount = parentNode.querySelectorAll("div,li").length;
            if (childCount > 1) {
                parentNode.removeChild(labelItem);
                adjustLabelItemPosition(parentNode);
                setSelected(gCurrent);
                parentNode.querySelectorAll("div,li")[0].focus();
            }
        }

        //var childCount = labelItem.parentNode.childNodes.length;
        //if (childCount > 1) {
        //    var parentDiv = labelItem.parentNode;
        //    parentDiv.removeChild(labelItem);
        //    adjustLabelItemPosition(parentDiv.childNodes[0]);
        //    setSelected(gCurrent);
        //    parentDiv.querySelectorAll("div,li")[0].focus();
        //}

    }
    gLabelContextMenu.classList.remove("context-menu--active");
    gGrpTmp = "";
}

function textEdit(func, value) {

    log(gEditingItem);
    if (!gEditingItem) {
        return;
    }

    if ("size" == func) {
        gEditingItem.style["font-size"] = document.getElementById("textEditFontSize").value + "px";
    } else if ("family" == func) {
        gEditingItem.style["font-family"] = document.getElementById("textEditFontFamily").value;
    } else if ("weight" == func) {
        if ("bold" == gEditingItem.style["font-weight"]) {
            gEditingItem.style["font-weight"] = "normal";
        } else {
            gEditingItem.style["font-weight"] = "bold";
        }
    } else if ("style" == func) {
        if ("italic" == gEditingItem.style["font-style"]) {
            gEditingItem.style["font-style"] = "normal";
        } else {
            gEditingItem.style["font-style"] = "italic";
        }
    } else if ("align" == func) {
        gEditingItem.style["text-align"] = value;
    } else if ("list" == func) {

        var listType = "";
        if ("bulleted" == value) {
            listType = "ul";
        } else if ("numbered" == value) {
            listType = "ol";
        }

        var list;
        var parentDiv;

        if ("div" == gEditingItem.tagName.toLowerCase()) {

            list = document.createElement(listType);
            var content = gEditingItem.innerHTML;

            if ("" != content) {
                content.split(/\<br\/?\>/i).forEach(function (itemStr) {
                    var listItem = document.createElement("li");
                    listItem.innerHTML = itemStr;
                    listItem.setAttribute("contenteditable", "true");

                    list.appendChild(listItem);

                });

                parentDiv = gEditingItem.parentNode;
                if (gEditingItem.nextSibling) {
                    parentDiv.insertBefore(list, gEditingItem.nextSibling);
                } else {
                    parentDiv.appendChild(list);
                }

                registerListenerToLabel(list);
                parentDiv.removeChild(gEditingItem);

                gEditingItem = list.querySelectorAll("li")[0];

            }

        } else {

            var html = "";
            list = gEditingItem.parentNode;

            var tagName = list.tagName.toLowerCase();
            //if ("li"==tagName.toLocaleLowerCase()) {
            //    tagName = gEditingItem.parentNode.tagName;
            //}

            if (listType != tagName) {

                var newList = document.createElement(listType);
                parentDiv = list.parentNode;
                newList.innerHTML = list.innerHTML;
                parentDiv.insertBefore(newList, list);
                parentDiv.removeChild(list);

                gEditingItem = newList.querySelectorAll("li")[0];

                registerListenerToLabel(parentDiv);

            } else {

                if (list) {

                    var itemAry = [];
                    var listItems = list.querySelectorAll("li");
                    [].forEach.call(listItems, function (item) {
                        if (item) {
                            itemAry.push(item.innerHTML);
                        }
                    });
                    html = itemAry.join("<br>");

                    var div = document.createElement("div");
                    div.innerHTML = html;
                    div.setAttribute("contenteditable", "true");

                    parentDiv = list.parentNode;
                    if (list.nextSibling) {
                        parentDiv.insertBefore(div, list.nextSibling);
                    } else {
                        parentDiv.appendChild(div);
                    }
                    parentDiv.removeChild(list);

                    registerListenerToLabel(parentDiv);

                }

            }


        }

    }

    adjustLabelItemPosition(gEditingItem);
    if (gEditingItem) {
        gEditingItem.focus();
    }
    event.stopPropagation();
}

//function getCaretCharacterOffsetWithin(element) {
//    var caretOffset = 0;
//    if (typeof window.getSelection != "undefined") {
//        var range = window.getSelection().getRangeAt(0);
//        var preCaretRange = range.cloneRange();
//        preCaretRange.selectNodeContents(element);
//        preCaretRange.setEnd(range.endContainer, range.endOffset);
//        caretOffset = preCaretRange.toString().length;
//    } else if (typeof document.selection != "undefined" && document.selection.type != "Control") {
//        var textRange = document.selection.createRange();
//        var preCaretTextRange = document.body.createTextRange();
//        preCaretTextRange.moveToElementText(element);
//        preCaretTextRange.setEndPoint("EndToEnd", textRange);
//        caretOffset = preCaretTextRange.text.length;
//    }
//    return caretOffset;
//}

function registerListenerToLabel(parentDiv, svgEl) {


    if (parentDiv) {

        var listItems = parentDiv.querySelectorAll("div, li");
        if (listItems && listItems.length > 0) {

            [].forEach.call(listItems, function (listItem) {

                    if (listItem) {

                        listItem.addEventListener("contextmenu", showLabelContextMenu);
                        listItem.addEventListener("keydown", labelItemKeyDown);
                        listItem.addEventListener("mousedown", function () {

                                var mouseDownEvent = document.createEvent("MouseEvent");
                                mouseDownEvent.initMouseEvent("mousedown", true, true, window, 0,
                                    event.screenX, event.screenY, event.clientX, event.clientY,
                                    event.ctrlKey, event.altKey, event.shiftKey, event.metaKey,
                                    0, null);
                                if (svgEl) {
                                    svgEl.node.dispatchEvent(mouseDownEvent);
                                } else {

                                    var label = getParentByTag(listItem, "foreignobject");
                                    if (label) {
                                        // rect, ellipse, path
                                        var grp = getGroupPrefix(label.id);
                                        var childNodes = gSvg.select("#" + grp + "g").selectAll("rect,ellipse,path");
                                        if (childNodes && childNodes.length > 0) {
                                            childNodes[0].node.dispatchEvent(mouseDownEvent);
                                        }
                                    }
                                }
                            }
                        );
                        listItem.addEventListener("focus", labelItemFocus);
                        listItem.addEventListener("blur", labelItemBlur);

                    }

                }
            );

        }
    }

}

function registerListener(id) {

    var type = getTypeById(id);
    if ("" == type) {
        return;
    }

    var svgEl = Snap("#" + id);
    var parentG;
    var label;
    var parentDiv;

    if ("rect" == type) {

        svgEl.mousedown(svgElMouseDown);
        svgEl.node.addEventListener("contextmenu", showContextMenu);
        svgEl.dblclick(textDblClick);

        parentG = svgEl.parent();
        parentG.selectAll("[id$='close']").forEach(function (close) {
            close.mousedown(closeClick);
        });

        parentG.selectAll("[id$='Resize']").forEach(function (nResize) {
            nResize.mousedown(resizeMouseDown);
        });
        // parentG.selectAll("[id$='sResize']").forEach(function (sResize) {
        //     sResize.mousedown(resizeMouseDown);
        // });
        // parentG.selectAll("[id$='wResize']").forEach(function (wResize) {
        //     wResize.mousedown(resizeMouseDown);
        // });
        // parentG.selectAll("[id$='eResize']").forEach(function (eResize) {
        //     eResize.mousedown(resizeMouseDown);
        // });

        label = parentG.selectAll("[id$='label']")[0];
        if (label) {
            parentDiv = label.select("div");
            registerListenerToLabel(parentDiv.node, svgEl);
        }

    } else if ("ellipse" == type) {

        svgEl.mousedown(svgElMouseDown);
        svgEl.node.addEventListener("contextmenu", showContextMenu);
        svgEl.dblclick(textDblClick);

        parentG = svgEl.parent();
        parentG.selectAll("[id$='close']").forEach(function (close) {
            close.mousedown(closeClick);
        });

        parentG.selectAll("[id$='Resize']").forEach(function (nResize) {
            nResize.mousedown(resizeMouseDown);
        });
        // parentG.selectAll("[id$='sResize']").forEach(function (sResize) {
        //     sResize.mousedown(resizeMouseDown);
        // });
        // parentG.selectAll("[id$='wResize']").forEach(function (wResize) {
        //     wResize.mousedown(resizeMouseDown);
        // });
        // parentG.selectAll("[id$='eResize']").forEach(function (eResize) {
        //     eResize.mousedown(resizeMouseDown);
        // });

        label = parentG.selectAll("[id$='label']")[0];
        if (label) {
            parentDiv = label.select("div");
            registerListenerToLabel(parentDiv.node, svgEl);
        }

    } else if ("brace" == type) {

        svgEl.mousedown(svgElMouseDown);
        svgEl.node.addEventListener("contextmenu", showContextMenu);

        parentG = svgEl.parent();
        parentG.selectAll("[id$='close']").forEach(function (close) {
            close.mousedown(closeClick);
        });

        parentG.selectAll("[id$='Resize']").forEach(function (nResize) {
            nResize.mousedown(resizeMouseDown);
        });
        // parentG.selectAll("[id$='sResize']").forEach(function (sResize) {
        //     sResize.mousedown(sResizeBraceMouseDown);
        // });

    } else if ("break" == type) {

        svgEl.mousedown(svgElMouseDown);
        svgEl.node.addEventListener("contextmenu", showContextMenu);

        parentG = svgEl.parent();
        parentG.selectAll("[id$='close']").forEach(function (close) {
            close.mousedown(closeClick);
        });

        parentG.selectAll("[id$='Resize']").forEach(function (nResize) {
            nResize.mousedown(resizeMouseDown);
        });
        // parentG.selectAll("[id$='sResize']").forEach(function (sResize) {
        //     sResize.mousedown(sResizeBreakMouseDown);
        // });
        // parentG.selectAll("[id$='wResize']").forEach(function (wResize) {
        //     wResize.mousedown(wResizeBreakMouseDown);
        // });
        // parentG.selectAll("[id$='eResize']").forEach(function (eResize) {
        //     eResize.mousedown(eResizeBreakMouseDown);
        // });

    } else if ("image" == type) {

        svgEl.mousedown(svgElMouseDown);
        svgEl.node.addEventListener("contextmenu", showContextMenu);

        parentG = svgEl.parent();
        parentG.selectAll("[id$='close']").forEach(function (close) {
            close.mousedown(closeClick);
        });

        parentG.selectAll("[id$='Resize']").forEach(function (nResize) {
            nResize.mousedown(resizeMouseDown);
        });
        // parentG.selectAll("[id$='sResize']").forEach(function (sResize) {
        //     sResize.mousedown(sResizeImageMouseDown);
        // });
        // parentG.selectAll("[id$='wResize']").forEach(function (wResize) {
        //     wResize.mousedown(wResizeImageMouseDown);
        // });
        // parentG.selectAll("[id$='eResize']").forEach(function (eResize) {
        //     eResize.mousedown(eResizeImageMouseDown);
        // });

    } else if ("custom" == type) {

        svgEl.mousedown(svgElMouseDown);
        svgEl.node.addEventListener("contextmenu", showContextMenu);
        svgEl.dblclick(textDblClick);

        parentG = svgEl.parent();
        parentG.selectAll("[id$='close']").forEach(function (close) {
            close.mousedown(closeClick);
        });

        parentG.selectAll("[id$='Resize']").forEach(function (nResize) {
            nResize.mousedown(resizeMouseDown);
        });
        // parentG.selectAll("[id$='sResize']").forEach(function (sResize) {
        //     sResize.mousedown(sResizeCustomMouseDown);
        // });
        // parentG.selectAll("[id$='wResize']").forEach(function (wResize) {
        //     wResize.mousedown(wResizeCustomMouseDown);
        // });
        // parentG.selectAll("[id$='eResize']").forEach(function (eResize) {
        //     eResize.mousedown(eResizeCustomMouseDown);
        // });

        label = parentG.selectAll("[id$='label']")[0];
        if (label) {
            parentDiv = label.select("div");
            registerListenerToLabel(parentDiv.node, svgEl);
        }

    } else if ("connector" == type) {

        svgEl.mousedown(svgElMouseDown);
        svgEl.node.addEventListener("contextmenu", showContextMenu);
        svgEl.dblclick(textDblClick);

        parentG = svgEl.parent();
        parentG.selectAll("[id$='close']").forEach(function (close) {
            close.mousedown(closeClick);
        });

        label = parentG.selectAll("[id$='label']")[0];
        if (label) {
            label.mousedown(labelMouseDown);
            parentDiv = label.select("div");
            registerListenerToLabel(parentDiv.node, svgEl);
        }

    }

    return type;

}

//endregion

//region Events
function svgElMouseDown(event) {
    log("svgElMouseDown");
    event.stopPropagation();

    var id = this.attr("id");
    var grp = getGroupPrefix(id);

    setSelected(grp, gCurrent);
    gCurrent = grp;

    var type = getTypeById(id);

    if (EL_TYPES.indexOf(type) >= 0) {
        gDragAnchor = type;
    } else {
        gDragAnchor = "unknown";
    }
    //if (id.indexOf("rect") > 0) {
    //    gDragAnchor = "rect";
    //} else if (id.indexOf("connector") > 0) {
    //    gDragAnchor = "connector";
    //} else if (id.indexOf("ellipse") > 0) {
    //    gDragAnchor = "ellipse";
    //} else if (id.indexOf("line") > 0) {
    //    gDragAnchor = "line";
    //} else if (id.indexOf("break") > 0) {
    //    gDragAnchor = "break";
    //} else if (id.indexOf("brace") > 0) {
    //    gDragAnchor = "brace";
    //} else if (id.indexOf("image") > 0) {
    //    gDragAnchor = "image";
    //} else if (id.indexOf("custom") > 0) {
    //    gDragAnchor = "custom";
    //} else {
    //    gDragAnchor = "unknown";
    //}

    var eventTarget = gSvg.select("#" + grp + gDragAnchor);

    eventTarget.data("mousedown-x", event.clientX);
    eventTarget.data("mousedown-y", event.clientY);

    gDrawArea.onmousemove = svgElMouseMove;
    gDrawArea.onmouseup = svgElMouseUp;

}

function svgElMouseMove(event) {

    var grp;
    if ("" != gCurrent) {
        grp = gCurrent;
    } else {
        return;
    }

    var svgEl = gSvg.select("#" + grp + gDragAnchor);

    var x = toInteger(svgEl.data('mousedown-x'), 0);
    var y = toInteger(svgEl.data('mousedown-y'), 0);

    var dx = event.clientX - x;
    var dy = event.clientY - y;

    var myMatrix = new Snap.Matrix();
    myMatrix.translate(dx, dy);

    var grpId = grp + "g";
    gSvg.select("#" + grpId).transform(myMatrix);

}

function svgElMouseUp() {
    log("svgElMouseUp");
    if ("" != gCurrent) {

        var grp = getGroupPrefix(gCurrent);
        var svgEl = gSvg.select("#" + gCurrent + gDragAnchor);

        correctXY(grp, svgEl, gDragAnchor);

    }

    gDrawArea.onmousemove = null;
    gDrawArea.onmouseup = null;

    //gCurrent = "";
    gDragAnchor = "";
}

function showContextMenu(e) {
    log("showContextMenu");
    e.preventDefault();

    gContextMenu.classList.add("context-menu--active");
    gContextMenu.style["left"] = (e.clientX - gMenuWidth ) + CONTEXT_MENU_SHIFT_X + "px";
    gContextMenu.style["top"] = (e.clientY - gMenuHeight) + CONTEXT_MENU_SHIFT_Y + "px";
    gGrpTmp = gCurrent;

}

function closeClick(e) {
    log("closeClick");
    e.stopPropagation();

    var r = confirm(REMOVE_CONFIRM_MSG);
    if (!r) {
        return;
    }

    var grp = getGroupPrefix(this.attr("id"));
    var grpId = grp + "g";
    gSvg.select("#" + grpId).remove();

}

function textDblClick(e) {
    log("textDblClick");
    e.stopPropagation();

    var grp = getGroupPrefix(e.target.id);
    var label = gSvg.select("#" + grp + "label>div");

    if (label) {
        var items = label.selectAll("div,li");
        if (items && items.length > 0) {
            items[0].node.focus();
        }
    }

    //var input = label.select("div>div");
    //if (input) {
    //    input.node.focus();
    //}
    /*
     log("textDblClick, id=" + this.attr("id"));
     log("gCurrent=" + gCurrent);
     gTextEditing = true;
     event.stopPropagation();
     //
     //var grp = getGroupPrefix(this.attr("id"));
     //gCurrent = grp;
     var text = gSvg.select("#" + gCurrent + "text");

     var textBBox = text.node.getBoundingClientRect();
     text.addClass("hide");

     var input = document.getElementById("rectText");
     input.value = text.innerSVG();
     input.style["left"] = (textBBox.left - gMenuWidth) + "px";
     input.style["top"] = (textBBox.top - gMenuHeight) + "px";
     input.style["display"] = "";
     input.focus();

     input.addEventListener("blur", inputBlur);
     */
}

/*
 function inputBlur(event) {
 log("inputBlur");
 log("gCurrent=" + gCurrent);
 event.stopPropagation();

 var grp = gCurrent;
 var textId = grp + "text";
 var text = gSvg.select("#" + textId);

 if (this.value != "") {
 text.attr("text", this.value);
 } else {
 text.attr("text", "N/A");
 }
 text.removeClass("hide");
 this.style["display"] = "none";

 //gCurrent = "";
 this.removeEventListener("blur", inputBlur);

 var grp = getGroupPrefix(textId);
 var line = gSvg.select("#" + grp + "line");
 if (line) {

 var textBBox = text.getBBox();
 var textWidth = textBBox.width;

 text.attr("x", parseInt(line.attr("x1"), 10) - textWidth - 10);
 //text.attr("y", textXY[1]);

 }

 }
 */

function labelItemKeyDown(e) {
    log("labelItemKeyDown");
    e.stopPropagation();

    if (13 == e.keyCode) {

        if (!e.shiftKey) {

            var item = document.createElement(e.target.tagName);
            item.style.width = e.target.style.width;
            item.setAttribute("contentEditable", "true");
            //div.innerHTML = "";
            item.setAttribute("placeholder", "label");
            item.style["text-align"] = "center";

            e.target.parentNode.appendChild(item);
            var parentDiv = getParentByTag(e.target, "div");
            registerListenerToLabel(parentDiv);

            adjustLabelItemPosition(e.target);

            for (var next = e.target.nextSibling; next != null;) {
                if (next.tagName && ["div", "li"].indexOf(next.tagName.toLowerCase()) >= 0) {
                    next.focus();
                    break;
                } else {
                    next = next.nextSibling;
                }

            }

            e.preventDefault();

        } else {
            // TODO: shift+enter causes <br> in chrome
        }

        return false;
    } else if (8 == e.keyCode) {

        if ("" == e.target.innerHTML) {
            gGrpTmp = e.target;
            labelRemove();
        }
    }

}

function labelItemFocus(e) {

    log("labelItemFocus");

    e.stopPropagation();
    e.preventDefault();

    gEditingItem = e.target;

    setTextEditMenuState(gEditingItem);
    gEditingItem.style.cursor = "input";

    var label = getParentByTag(e.target, "foreignobject");
    if (label) {
        gCurrent = getGroupPrefix(label.id);
        setSelected(gCurrent);
    }

    //gTextEditContextMenu.classList.add("context-menu--active");
}

function labelItemBlur(e) {

    log("labelItemBlur");

    var label = getParentByTag(e.target, "foreignobject");
    if (label) {
        gCurrent = getGroupPrefix(label.id);
        setSelected(gCurrent);
    }

    //gTextEditContextMenu.classList.remove("context-menu--active");
    gLabelContextMenu.classList.remove("context-menu--active");

}

function showLabelContextMenu(e) {
    log("showLabelContextMenu");
    e.stopPropagation();
    e.preventDefault();

    gLabelContextMenu.classList.add("context-menu--active");
    gLabelContextMenu.style["left"] = (e.clientX - gMenuWidth ) + CONTEXT_MENU_SHIFT_X + "px";
    gLabelContextMenu.style["top"] = (e.clientY - gMenuHeight) + CONTEXT_MENU_SHIFT_Y + "px";
    gGrpTmp = e.target;

}

function labelMouseDown(event) {
    log("labelMouseDown");
    event.stopPropagation();

    var id = this.attr("id");
    var grp = getGroupPrefix(id);
    setSelected(grp, gCurrent);
    gCurrent = grp;

    var type = getTypeById(id);

    if ("label" != type) {
        return;
    } else {
        gDragAnchor = "label";
    }

    var eventTarget = gSvg.select("#" + id);

    eventTarget.data("mousedown-x", event.clientX);
    eventTarget.data("mousedown-y", event.clientY);

    gDrawArea.onmousemove = labelMouseMove;
    gDrawArea.onmouseup = labelMouseUp;

}

function labelMouseMove(event) {

    var grp;
    if ("" != gCurrent) {
        grp = gCurrent;
    } else {
        return;
    }

    var svgEl = gSvg.select("#" + grp + gDragAnchor);

    var x = toInteger(svgEl.data('mousedown-x'), 0);
    var y = toInteger(svgEl.data('mousedown-y'), 0);

    var dx = event.clientX - x;
    var dy = event.clientY - y;

    var myMatrix = new Snap.Matrix();
    myMatrix.translate(dx, dy);
    svgEl.transform(myMatrix);

}

function labelMouseUp() {
    log("labelMouseUp");

    var svgEl = gSvg.select("#" + gCurrent + gDragAnchor);
    var tStrAry = Snap.parseTransformString(svgEl.attr("transform"));

    var nowX = toInteger(svgEl.attr("x"), 0);
    var nowY = toInteger(svgEl.attr("y"), 0);

    if (tStrAry.length != 0) {

        var x = toInteger(tStrAry[0][1], 0);
        var y = toInteger(tStrAry[0][2], 0);

        x += nowX;
        y += nowY;

        svgEl.transform("translate(0 0)");
        svgEl.attr("x", x);
        svgEl.attr("y", y);

    }

    gDrawArea.onmousemove = null;
    gDrawArea.onmouseup = null;

    //gCurrent = "";
    gDragAnchor = "";
}

function borderColorChanged(e) {

    var childNodes = gSvg.select("#" + gCurrent + "g").selectAll("rect,ellipse,path");
    if (childNodes && childNodes.length > 0) {
        // childNodes[0].node.style.stroke = e.color.toHex();
        var clsName = childNodes[0].node.className.baseVal;
        gSvg.selectAll("." + clsName).forEach(function (item) {
            item.node.style.stroke = e.color.toHex();
        });
    }

}

function fillColorChanged(e) {

    var childNodes = gSvg.select("#" + gCurrent + "g").selectAll("rect,ellipse,path");
    if (childNodes && childNodes.length > 0) {
        // childNodes[0].node.style.fill = e.color.toHex();
        var clsName = childNodes[0].node.className.baseVal;
        gSvg.selectAll("." + clsName).forEach(function (item) {
            item.node.style.fill = e.color.toHex();
        });
    }

}

function borderColorShowPicker(e) {

    var childNodes = gSvg.select("#" + gCurrent + "g").selectAll("rect,ellipse,path");
    if (childNodes && childNodes.length > 0) {
        var color = childNodes[0].node.style.stroke;
        if ("" != color) {
            $("#borderColorPicker").colorpicker("setValue", childNodes[0].node.style.stroke);
        }
    }

}


function fillColorShowPicker(e) {

    var childNodes = gSvg.select("#" + gCurrent + "g").selectAll("rect,ellipse,path");
    if (childNodes && childNodes.length > 0) {
        var color = childNodes[0].node.style.fill;
        if ("" != color) {
            $("#fillColorPicker").colorpicker("setValue", childNodes[0].node.style.fill);
        }
    }

}

//endregion

//function addDiamond() {
//
//    var grp = getGroupPrefix(gSerialNo);
//    var breakId = grp + "break";
//
//    var myDiamond = gSvg.path("M20 0 L0 20 L20 40 L40 20 Z");
//    myDiamond.addClass("myDiamond");
//    myDiamond.attr("id", breakId);
//
//    myDiamond.mouseover(connectorMouseOver);
//    myDiamond.mouseout(connectorMouseOut);
//    myDiamond.mousedown(svgElMouseDown);
//    myDiamond.node.addEventListener("contextmenu", breakContextMenu);
//
//    var g = gSvg.g(myDiamond);
//    var grpId = grp + "g";
//    g.attr("id", grpId);
//
//    gSerialNo++;
//}

//function addClippingSquare() {
//
//    var grp = getGroupPrefix(gSerialNo);
//    var breakId = grp + "break";
//
//    var myDiamond = gSvg.path("M20 0 L120 0 L120 60 L0 60 L0 20 Z");
//    myDiamond.addClass("myClippingSquare");
//    myDiamond.attr("id", breakId);
//
//    myDiamond.mouseover(connectorMouseOver);
//    myDiamond.mouseout(connectorMouseOut);
//    myDiamond.mousedown(svgElMouseDown);
//    myDiamond.node.addEventListener("contextmenu", breakContextMenu);
//
//    var g = gSvg.g(myDiamond);
//    var grpId = grp + "g";
//    g.attr("id", grpId);
//
//    gSerialNo++;
//}

