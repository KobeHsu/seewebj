<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="bs-docs-nav" id="top" role="banner">
	<div class="container">
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">		
		<div class="navbar-header">
	    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">	    
		    <span class="sr-only">Toggle navigation</span>
		    <span class="icon-bar"></span>
		    <span class="icon-bar"></span>
		    <span class="icon-bar"></span>
	    </button>
	    <a class="navbar-brand logo" rel="home" href="./Index"><img src="<c:url value='/res/images/logo_see.png' />" /></a>				
		</div>
		
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<!--
	      <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> 個案資料管理<b class="caret"></b></a>
	      	<ul class="dropdown-menu">
						<li><a href="CaseExtraDefinitionManagement">進階資料欄位定義</a></li>
						<li><a href="CaseList?projectUuid=1">個案資料維護</a></li>
	      	</ul>
	    	</li>
	    	-->
				<!--
	      <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-file-text-o"></i> 問卷管理<b class="caret"></b></a>
	      	<ul class="dropdown-menu">
						<li><a href="#">問卷設計</a></li>
						<li><a href="#">問卷結果</a></li>
						<li><a href="#">問卷結果統計分析</a></li>
	      	</ul>
	    	</li>
	      
	      <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-magic"></i> 易用性測試表單<b class="caret"></b></a>
	      	<ul class="dropdown-menu">
						<li><a href="UsibilityTestFormManagement">表單範本維護</a></li>
						<li><a href="ViewUsibilityTestFormTemplate?projectUuid=1">表單範本下載</a></li>
	      		<li><a href="#">表單產生</a></li>
	      	</ul>
	    	</li>
	    	-->
				<!--
	      <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-pie-chart"></i> 易用性測試結果<b class="caret"></b></a>
	      	<ul class="dropdown-menu">
						<li><a href="#">測試紀錄工作表</a></li>
	      		<li><a href="#">測試結果分析</a></li>
	      	</ul>
	    	</li>

	      <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-pie-chart"></i> 易用性測試結果<b class="caret"></b></a>
	      	<ul class="dropdown-menu">
						<li><a href="#">測試紀錄工作表</a></li>
	      		<li><a href="#">測試結果分析</a></li>
	      	</ul>
	    	</li>
				-->
	      <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-cog"></i> 專案設定<b class="caret"></b></a>
	      	<ul class="dropdown-menu">
						<li><a href="ProjectWizard">專案精靈(程序型)</a></li>
						<li><a href="CaseExtraDefinitionManagement">進階資料欄位定義</a></li>
	      	</ul>
	    	</li>

	      <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-files-o"></i> 文件管理<b class="caret"></b></a>
	      	<ul class="dropdown-menu">
						<li><a href="ViewDocument">文件中心</a></li>
						<li><a href="UploadFile">上傳檔案</a></li>
	      	</ul>
	    	</li>

<c:if test="${sessionScope.user.account eq 'sysadmin'}">
	      <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-cube"></i> 系統管理<b class="caret"></b></a>
	      	<ul class="dropdown-menu">
						<li><a href="AccountManagement">帳號管理</a></li>
						<li><a href="OnlineIssueManagement">線上反映問題管理</a></li>
	      	</ul>
	    	</li>
</c:if>
	      
	    	
<c:if test="${not empty sessionScope.user}">
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> 帳號<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="EditAccount"> 設定</a></li>
						<li><a href="Logout"> 登出</a></li>
					</ul>	
				</li>			
</c:if>
	    	
			</ul>			
		</div>		
	</nav>
	
	</div>
</header>