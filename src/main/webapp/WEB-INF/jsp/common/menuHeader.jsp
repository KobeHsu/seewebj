<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">		
		
	<div class="navbar-header">	    	
		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-controls="navbar">
    	<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="./Index"><img src="<c:url value='/res/images/logo_see.png' />" /></a>
	</div>
			
	<div class="collapse navbar-collapse" id="navbar">
		<ul class="nav navbar-nav">

<c:if test="${not empty sessionScope.projectList}">
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-angle-double-left"></i> ${sessionScope.activeProjectName} <i class="fa fa-angle-double-right"></i></a>
				<ul class="dropdown-menu">
	<c:forEach var="project" items="${sessionScope.projectList.items}" varStatus="projectLoop">
					<li><a href="Index?projectUuid=${project.key}"> ${project.value}</a></li>
	</c:forEach>			
				</ul>	
			</li>			
</c:if>			
			
<c:if test="${sessionScope.user.ownPrjoectAuthority eq 'Y'}">
			<li><a href="NewProject"><i class="fa fa-magic"></i> 新增專案</a></li>		
</c:if>			

			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> 帳號<b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="EditAccount"> 設定</a></li>
					<li><a href="Logout"> 登出</a></li>
				</ul>	
			</li>			
		</ul>

		<ul class="nav navbar-nav navbar-right margin-right-sm">
			<li><a href="AnnouncementList?projectUuid=${sessionScope.activeProjectUuid}"><i class="fa fa-bullhorn"></i> 公佈欄</a></li>
			<li><a href="ForumList?projectUuid=${sessionScope.activeProjectUuid}"><i class="fa fa-comments-o"></i> 討論區</a></li>
			<li><a href="NewOnlineIssue?projectUuid=${sessionScope.activeProjectUuid}"><i class="fa fa-exclamation-triangle"></i> 問題提報</a></li>
	  </ul>
				
	</div>
</nav>
