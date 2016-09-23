<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel-default margin-top-lg">
	
<form role="form" class="form-horizontal" id="newForumTab1Form" name="newForumTab1Form">
		<input type="hidden" id="projectUuid" name="projectUuid" value="${projectUuid}" />
		<input type="hidden" id="uuid" name="uuid" value="${dataUuid}" />
		<input type="hidden" id="functionName" name="functionName" />
		
		<div class="form-group">
			<label for="title" class="control-label col-md-2 text-right">名稱*</label>
			<div class="col-md-10">
					<input type="text" class="form-control" id="title" name="title" maxlength="32" placeholder="請輸入討論區名稱" />
			</div>
		</div>
			
		<div class="form-group">
				<label for="title" class="control-label col-md-2 text-right">摘要說明*</label>
			  <div class="col-md-10">
			  		<textarea class="form-control" rows="3" id="summary" name="summary"  maxlength="256"  placeholder="請輸入討論區摘要說明"></textarea>
			  </div>
		</div>

	 	<div class="form-group">
      	<label for="listOrder" class="control-label col-md-2 text-right">顯示排序</label>
      	<div class="col-md-3">
      			<input type="text" class="form-control" id="serialNo" name="serialNo" />
      	</div>
    </div>

		<div class="form-group">
      	<label for="status" class="control-label col-md-2 text-right">狀態</label>
      	<div class="col-md-3">
      		<select class="form-control" name="status" id="status">
      		</select>
      	</div>
		</div>
	
</form>
</div>