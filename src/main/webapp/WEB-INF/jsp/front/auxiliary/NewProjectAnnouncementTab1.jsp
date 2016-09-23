<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel-default margin-top-lg">
	
<form role="form" class="form-horizontal" id="newProjectAnnouncementTab1Form" name="newProjectAnnouncementTab1Form">
		<input type="hidden" id="projectUuid" name="projectUuid" value="${projectUuid}" />
		<input type="hidden" id="uuid" name="uuid" value="${dataUuid}" />
		<input type="hidden" id="functionName" name="functionName" />
		
		<div class="form-group">
			<label for="title" class="control-label col-md-2 text-right">標題*</label>
			<div class="col-md-10">
					<input type="text" class="form-control" id="title" name="title" maxlength="32" placeholder="請輸入公告標題" />
			</div>
		</div>
			
		<div class="form-group">
				<label for="title" class="control-label col-md-2 text-right">摘要說明*</label>
			  <div class="col-md-10">
			  		<textarea class="form-control" rows="3" id="summary" name="summary"  maxlength="256"  placeholder="請輸入公告摘要說明"></textarea>
			  </div>
		</div>

		<div class="form-group">
				<label for="title" class="control-label col-md-2 text-right">詳細內容*</label>
			  <div class="col-md-10">
			  		<textarea class="form-control" rows="6" id="content" name="content"  maxlength="256"  placeholder="請輸入公告詳細內容"></textarea>
			  </div>
		</div>

	 	<div class="form-group">
      	<label for="listOrder" class="control-label col-md-2 text-right">顯示排序</label>
      	<div class="col-md-3">
	      		<select class="form-control" name="serialNo" id="serialNo">
	      		</select>
      	</div>
    </div>

	 	<div class="form-group">
      	<label for="workshopDate" class="control-label col-md-2 text-right">公告日期</label>
      	<div class="col-md-3">
	        <div class="input-group date-picker input-daterange" data-date-format="yyyy/mm/dd">
	          <input type="text" class="form-control" name="beginDate" id="beginDate" />
	          <span class="input-group-addon"> 至 </span>
	          <input type="text" class="form-control" name="endDate" id="endDate" />
	        </div>	        
      	</div>
    </div>    	

		<!--
		<div class="form-group">
      	<label for="status" class="control-label col-md-2 text-right">狀態</label>
      	<div class="col-md-3">
      		<select class="form-control" name="status" id="status">
      		</select>
      	</div>
		</div>
		-->
	
</form>
</div>