<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel-default margin-top-lg">
	
<form role="form" class="form-horizontal" id="newForumThreadTab1Form" name="newForumThreadTab1Form">
		<input type="hidden" id="projectUuid" name="projectUuid" value="${projectUuid}" />
		<input type="hidden" id="forumUuid" name="forumUuid" value="${forumUuid}" />
		<input type="hidden" id="functionName" name="functionName" />
		
		<div class="form-group">
			<label for="title" class="control-label col-md-2 text-right">主題*</label>
			<div class="col-md-10">
					<input type="text" class="form-control" id="title" name="title" maxlength="32" placeholder="請輸入討論主題" />
			</div>
		</div>
			
		<div class="form-group">
				<label for="title" class="control-label col-md-2 text-right">內容*</label>
			  <div class="col-md-10">
			  		<textarea class="form-control" rows="10" id="content" name="content"  placeholder="請輸入討論內容"></textarea>
			  </div>
		</div>
	
</form>
</div>