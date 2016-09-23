<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="well well-lg">塑模指引 -  工具器物模型</div>
<div class="panel panel-default margin-top-lg">
	<div class="panel-body">
		<img id="tuturialFigure" src="<c:url value='/res/images/ArtifactModelTutorial001.png' />" style="width:100%; height:auto" />
	</div>
	<div class="panel-footer text-center">
		<button type="button" class="btn btn-default" id="prevBtn" name="prevBtn"><i class="fa fa-caret-left"></i></button>
		 第 <span id="currentPage">1</span> / <span id="totalPage">4</span> 頁 
		<button type="button" class="btn btn-default" id="nextBtn" name="nextBtn"><i class="fa fa-caret-right"></i></button>
	</div>
</div>

