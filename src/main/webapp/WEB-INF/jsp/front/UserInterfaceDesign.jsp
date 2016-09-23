<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value='/res/css/html_edit.css' />" rel="stylesheet">		

<script type="text/javascript" src="<c:url value='/res/js/seeweb/html_edit.js' />"></script>


<header>介面設計模型</header>

<nav></nav>
<section id="mainArea">
    <div id="component" class="round-block">
        <span draggable="true" ondragstart="dragStart(event, 'span')">LABEL</span>        
        <input type="text" placeholder="TextField" disabled="disabled" draggable="true"
               ondragstart="dragStart(event, 'text')">
        <input list="newList" draggable="true" ondragstart="dragStart(event, 'list')" value="Drop down">
        <datalist id="newList">
            <option>Job Inquiry</option>
            <option>General Question</option>
        </datalist>
        <!--<select disabled="disabled" style="cursor: not-allowed">
            <option>DropList</option>
        </select>-->
        <input type="button" value="Button" draggable="true" ondragstart="dragStart(event, 'button')">
        <span draggable="true" ondragstart="dragStart(event, 'checkbox')">
            <input type="checkbox" draggable="true" ondragstart="dragStart(event, 'checkbox')">
        </span>
        <span draggable="true" ondragstart="dragStart(event, 'radio')">
            <input type="radio" draggable="true" ondragstart="dragStart(event, 'radio')">
        </span>
        <textarea placeholder="Textarea" disabled="disabled" draggable="true"
                  ondragstart="dragStart(event, 'textarea')"></textarea>
    </div>
    <div id="container" class="round-block" ondrop="dropDown(event)" ondragover="allowDrop(event)">
        <span id="span_10001" draggable="true" ondragstart="dragStart(event, 'move')"
              style="position:absolute;left:300px;top:50px;">Your Name :</span>
        <input id="text_10002" type="text" placeholder="Your Full Name" draggable="true"
               ondragstart="dragStart(event, 'move')" style="position:absolute;left:420px;top:50px;">
        <span id="span_10003" draggable="true" ondragstart="dragStart(event, 'move')"
              style="position:absolute;left:300px;top:90px;">Your Email :</span>
        <input id="text_10004" type="text" placeholder="Email Address" draggable="true"
               ondragstart="dragStart(event, 'move')" style="position:absolute;left:420px;top:90px;">
        <span id="span_10005" draggable="true" ondragstart="dragStart(event, 'move')"
              style="position:absolute;left:290px;top:130px;">Subject :</span>
        <input id="span_10006" list="newList" draggable="true" ondragstart="dragStart(event, 'move')"
               value="Job Inquiry" style="position:absolute;left:420px;top:130px;">
        <textarea id="span_10007" placeholder="Your Message to Us" draggable="true"
                  ondragstart="dragStart(event, 'move')" style="position:absolute;left:320px;top:170px;"></textarea>
        <input id="span_10008" type="button" value="Send" draggable="true" ondragstart="dragStart(event, 'move')"
               style="position:absolute;left:530px;top:200px;">
    </div>
</section>

