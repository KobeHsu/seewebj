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
		<ul class="nav navbar-nav margin-left-sm">
			<li><a href="Index"><i class="fa fa-home"></i></a></li>		
		</ul>			
		<p class="navbar-text"><tiles:getAsString name="title"/></p>	
	</div>


</nav>
