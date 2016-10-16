<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<nav class="navbar navbar-default navbar-static-top navbar-tool" data-spy="affix" data-offset-top="40" role="navigation">		
		
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sub-navbar" aria-controls="navbar">
    	<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
		</button>
	</div>
			
	<div class="collapse navbar-collapse" id="sub-navbar">
		<ul class="nav navbar-nav navbar-tool margin-left-sm">
			<li><a href="Index"><i class="fa fa-home"></i></a></li>		
		</ul>			
		<p class="navbar-text"><tiles:getAsString name="title"/></p>	
		<ul class="nav navbar-nav navbar-tool margin-left-sm">
			<li class="divider-vertical"></li>
			<li><a class="functionButton" href="javascript: newDraw();"><i class="fa fa-fw fa-file-o"></i></a></li>		
			<li><a class="functionButton" href="javascript: loadDraw();"><i class="fa fa-fw fa-folder-open-o"></i></a></li>	
			<li><a class="functionButton" class="functionButton" href="javascript: saveDraw();"><i class="fa fa-fw fa-floppy-o"></i></a></li>	
			<li><a class="functionButton" href="javascript: deleteDraw();"><i class="fa fa-fw fa-trash-o"></i></a></li>	
			<li><a class="functionButton" href="javascript: exportDraw();"><i class="fa fa-fw fa-file-image-o"></i></a></li>
			<li id="svgUndoBtn" class="hide"><a class="functionButton" href="javascript: undo();"><i class="fa fa-undo"></i></a></li>
			<li class="divider-vertical"></li>			
			<!--
			<li><a href="javascript: toggleGrid();"><i class="fa fa-th"></i></a></li>	
			-->
		</ul>			
		<ul class="nav navbar-nav navbar-tool margin-left-sm">
			<li><select class="form-control input-sm" id="textEditFontFamily" onchange="textEdit('family')"></select></li>		
			<li><select class="form-control input-sm" id="textEditFontSize" onchange="textEdit('size')"></select></li>		
		</ul>
		<ul class="nav navbar-nav navbar-tool margin-left-sm">
			<div class="btn-group">
      	<a class="btn btn-default" href="#" onmousedown="textEdit('weight', 'bold')" id="textEditFontBold"><i class="fa fa-bold"></i></a>
				<a class="btn btn-default" href="#" onmousedown="textEdit('style', 'italic')" id="textEditFontItalic"><i class="fa fa-italic"></i></a>
			</div>
		</ul>        	
		<ul class="nav navbar-nav navbar-tool margin-left-sm">
			<div class="btn-group">
				<a class="btn btn-default" href="#" onmousedown="textEdit('align', 'left')" id="textEditAlignLeft"><i class="fa fa-align-left"></i></a>
				<a class="btn btn-default" href="#" onmousedown="textEdit('align', 'center')" id="textEditAlignCenter"><i class="fa fa-align-center"></i></a>
				<a class="btn btn-default" href="#" onmousedown="textEdit('align', 'right')" id="textEditAlignRight"><i class="fa fa-align-right"></i></a>
			</div>
		</ul>        	
		<ul class="nav navbar-nav navbar-tool margin-left-sm">
			<div class="btn-group">
				<a class="btn btn-default" href="#" onmousedown="textEdit('list', 'bulleted')" id="textEditBulleted"><i class="fa fa-list-ul"></i></a>
				<a class="btn btn-default" href="#" onmousedown="textEdit('list', 'numbered')" id="textEditNumbered"><i class="fa fa-list-ol"></i></a>
			</div>
		</ul>        	
		
	</div>


</nav>
